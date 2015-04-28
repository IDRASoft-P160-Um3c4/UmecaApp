package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.repository.supervisor.HearingTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.shared.CrimeService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HearingFormatController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private HearingFormatService hearingFormatService;

    @Autowired
    private StatusCaseRepository statusCaseRepository;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/supervisor/hearingFormat/listCases", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listCases(@ModelAttribute JqGridFilterModel opts) {


        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{

                    add(Constants.CASE_STATUS_TECHNICAL_REVIEW);
                    add(Constants.CASE_STATUS_HEARING_FORMAT_END);
                    add(Constants.CASE_STATUS_HEARING_FORMAT_INCOMPLETE);
                    add(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE);
                    add(Constants.CASE_STATUS_FRAMING_INCOMPLETE);
                    add(Constants.CASE_STATUS_FRAMING_COMPLETE);
                    add(Constants.CASE_STATUS_NOT_PROSECUTE_OPEN);

                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Meeting, Imputed> joinIm = r.join("meeting").join("imputed");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinFrMe = r.join("framingMeeting", JoinType.LEFT);
                final javax.persistence.criteria.Join<Meeting, Imputed> joinTR = r.join("technicalReview", JoinType.LEFT);
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("status").get("name"));
                    add(r.join("status").get("description"));
                    add(r.get("idFolder"));
                    add(r.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                    add(joinFrMe.get("id"));
                    add(joinTR.get("id").alias("idTR"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("idMP"))
                    return r.get("idMP");
                else if (field.equals("statusName"))
                    return r.join("status").get("name");
                else if (field.equals("fullName"))
                    return r.join("meeting").join("imputed").get("name");
                return null;
            }
        }, Case.class, ForCasesHFGrid.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/hearingFormat/listFormats", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listFormats(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<HearingFormat, HearingFormatImputed> joinHImp = r.join("hearingImputed");
                final javax.persistence.criteria.Join<HearingFormat, HearingFormatSpecs> joinSpecs = r.join("hearingFormatSpecs");
                final javax.persistence.criteria.Join<HearingFormat, User> joinUsr = r.join("supervisor");

                return new ArrayList<Selection<?>>() {{

                    add(r.get("id"));
                    add(r.get("isFinished"));
                    add(r.get("idFolder"));
                    add(r.get("idJudicial"));
                    add(joinHImp.get("name"));
                    add(joinHImp.get("lastNameP"));
                    add(joinHImp.get("lastNameM"));
                    add(joinSpecs.get("arrangementType"));
                    add(joinSpecs.get("extension"));
                    add(joinSpecs.get("linkageProcess"));
                    add(r.get("registerTime"));
                    add(joinUsr.get("fullname"));

                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase"))
                    return r.join("caseDetention").get("id");

                return null;
            }
        }, HearingFormat.class, ForHearingFormatGrid.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/hearingFormat/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/hearingFormat/index";
    }

    @RequestMapping(value = "/supervisor/hearingFormat/indexFormats", method = RequestMethod.GET)
    public ModelAndView indexFormats(@RequestParam(required = true) Long id, Integer returnId) {

        ModelAndView model = new ModelAndView("/supervisor/hearingFormat/indexFormats");

        model.addObject("idCase", id);
        model.addObject("returnId", returnId);
        return model;
    }

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HearingTypeRepository hearingTypeRepository;

    @Autowired
    CrimeService crimeService;

    @RequestMapping(value = "/supervisor/hearingFormat/newHearingFormat", method = RequestMethod.GET)
    public ModelAndView newHearingFormat(@RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView();

        if (caseRepository.findOne(idCase).getStatus().getName().equals(Constants.CASE_STATUS_PRE_CLOSED)) {
            model.setViewName("/supervisor/hearingFormat/indexFormats");
            model.addObject("idCase", idCase);
            model.addObject("showErr", true);
            model.addObject("msgError", "No es posible agregar mas formatos, el caso se encuentra pre-cerrado.");
        } else if (hearingFormatRepository.findHearingFormatIncomplete(idCase) != null && hearingFormatRepository.findHearingFormatIncomplete(idCase) > 0) {
            model.setViewName("/supervisor/hearingFormat/indexFormats");
            model.addObject("idCase", idCase);
            model.addObject("showErr", true);
            model.addObject("msgError", "No es posible agregar mas formatos, el caso tiene un formato de audiencia icompleto.");
        } else if (caseRepository.findOne(idCase).getStatus().getName().equals(Constants.CASE_STATUS_CLOSED)) {
            model.setViewName("/supervisor/hearingFormat/indexFormats");
            model.addObject("idCase", idCase);
            model.addObject("showErr", true);
            model.addObject("msgError", "No es posible agregar mas formatos, el caso se encuentra cerrado.");
        } else {
            model.setViewName("/supervisor/hearingFormat/hearingFormat");
            HearingFormatView hfView = hearingFormatService.fillNewHearingFormatForView(idCase);
            Gson conv = new Gson();
            model.addObject("hfView", conv.toJson(hfView));
            model.addObject("hasPrevHF", hfView.getHasPrevHF());
            model.addObject("listCrime", hfView.getListCrime());

            crimeService.fillCatalogModel(model);
            model.addObject("readonlyBand", false);
            if (hfView.getHasPrevHF() != null && hfView.getHasPrevHF() == true)
                model.addObject("lstHearingType", conv.toJson(hearingTypeRepository.getValidaHearingType()));
            else
                model.addObject("lstHearingType", "[]");

            addressService.fillCatalogAddress(model);

            List<SelectList> lstSuper = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);

            model.addObject("lstSupervisor", conv.toJson(lstSuper));

            if (hfView.getIdAddres() != null)
                addressService.fillModelAddress(model, hfView.getIdAddres());

        }
        return model;


    }


    @RequestMapping(value = "/supervisor/hearingFormat/viewHearingFormat", method = RequestMethod.GET)
    public ModelAndView viewHearingFormat(@RequestParam(required = true) Long idFormat, Integer returnId) {

        ModelAndView model = new ModelAndView("/supervisor/hearingFormat/hearingFormat");

        HearingFormatView hfView = hearingFormatService.fillExistHearingFormatForView(idFormat, false);
        Gson conv = new Gson();

        hfView.setIsView(true);
        model.addObject("hfView", conv.toJson(hfView));
        model.addObject("returnId", conv.toJson(returnId));
        List<SelectList> lstSuper = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
        model.addObject("lstSupervisor", conv.toJson(lstSuper));
        crimeService.fillCatalogModel(model);
        model.addObject("readonlyBand", true);
        model.addObject("listCrime", crimeService.getListCrimeHearingformatByIdFormat(idFormat));
        model.addObject("hasPrevHF", hfView.getHasPrevHF());

        if (hfView.getHasPrevHF() != null && hfView.getHasPrevHF() == true)
            model.addObject("lstHearingType", conv.toJson(hearingTypeRepository.getValidaHearingType()));
        else
            model.addObject("lstHearingType", "[]");

        addressService.fillCatalogAddress(model);

        if (hfView.getIdAddres() != null)
            addressService.fillModelAddress(model, hfView.getIdAddres());

        return model;
    }

    @RequestMapping(value = "/supervisor/hearingFormat/editHearingFormat", method = RequestMethod.GET)
    public ModelAndView editHearingFormat(@RequestParam(required = true) Long idFormat) {

        ModelAndView model = new ModelAndView("/supervisor/hearingFormat/hearingFormat");

        HearingFormatView hfView = hearingFormatService.fillIncompleteFormatForView(idFormat);
        Gson conv = new Gson();
        model.addObject("hfView", conv.toJson(hfView));
        List<SelectList> lstSuper = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
        model.addObject("lstSupervisor", conv.toJson(lstSuper));
        model.addObject("listCrime", crimeService.getListCrimeHearingformatByIdFormat(idFormat));
        model.addObject("hasPrevHF", hfView.getHasPrevHF());

        if (hfView.getHasPrevHF() != null && hfView.getHasPrevHF() == true)
            model.addObject("lstHearingType", conv.toJson(hearingTypeRepository.getValidaHearingType()));
        else
            model.addObject("lstHearingType", "[]");

        addressService.fillCatalogAddress(model);

        if (hfView.getIdAddres() != null)
            addressService.fillModelAddress(model, hfView.getIdAddres());
        crimeService.fillCatalogModel(model);
        return model;
    }

    @RequestMapping(value = "/supervisor/hearingFormat/newConditionalReprieve", method = RequestMethod.POST)
    public String hearingFormat() {
        return "/supervisor/hearingFormat/newConditionalReprieve";
    }


    @RequestMapping(value = "/supervisor/hearingFormat/doNewCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doNewCase(@RequestParam(required = true) String idJudicial, @ModelAttribute Imputed imputed) {

        ResponseMessage response = new ResponseMessage();

        if (imputed.getBirthDate() != null) {
            Integer age = sharedUserService.calculateAge(imputed.getBirthDate());
            if (age.compareTo(18) == -1) {
                return new ResponseMessage(true, "El imputado debe tener m&aacute;s de 18 a&ntilde;os para continuar.");
            }
        }

        try {
            Case caseDet;

            caseDet = caseService.generateNewCase(imputed, HearingFormatConstants.MEETING_CONDITIONAL_REPRIEVE);
            caseDet.setIdMP(idJudicial);
            Long num = caseRepository.findJudicialFoneticBrthDayImputed(caseDet.getIdMP(), caseDet.getMeeting().getImputed().getFoneticString(), caseDet.getMeeting().getImputed().getBirthDate());

            if (num != null && num > 0) {
                response.setHasError(true);
                response.setTitle("Formato de audiencia");
                response.setMessage("El n&uacute;mero de Carpeta Judicial e Imputado ya existen, verifique los datos.");
            } else {
                caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE));
                response = caseService.saveConditionaReprieveCase(caseDet);
            }

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doNewCase", sharedUserService);
            response.setHasError(true);
            response.setTitle("Formato de audiencia");
            response.setMessage("Ha ocurrido un error, intente nuevamente.");

        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/supervisor/hearingFormat/searchArrangementsByType", method = RequestMethod.POST)
    public
    @ResponseBody
    String searchArrangement(@RequestParam(required = true) Boolean national, @RequestParam(required = true) Integer idTipo) {

        Gson conv = new Gson();
        List<ArrangementView> lstArrangementView = hearingFormatService.getArrangmentLst(national, idTipo);
        String jsonLst = conv.toJson(lstArrangementView);

        return jsonLst;
    }

    @RequestMapping(value = "supervisor/hearingFormat/obsoleteCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage obsoleteCase(@RequestParam(required = true) Long id) {
        try {
            return hearingFormatService.requestObsoleteCase(id);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "obsoleteCase", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error, intente nuevamente");
        }
    }

    @Autowired
    CaseRepository caseRepository;

    @RequestMapping(value = "/supervisor/hearingFormat/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute HearingFormatView result, HttpServletRequest request) {

        try {
            Long incompleteHFId = hearingFormatRepository.findHearingFormatIncomplete(result.getIdCase());

            if (incompleteHFId != null && incompleteHFId > 0 && incompleteHFId != result.getIdFormat())
                return new ResponseMessage(true, "Tiene un formato de audiencia anterior incompleto, debe terminarlo para poder agregar un nuevo formato de audiencia.");

            if (result.getIsFinished() != null && result.getIsFinished()) {
                if (result.getVincProcess() != null && result.getVincProcess().equals(HearingFormatConstants.PROCESS_VINC_NO)) {
                    ResponseMessage resp = hearingFormatService.validatePassCredential(result.getCredPass());
                    if (resp != null)
                        return resp;
                }
            }

            HearingFormat hearingFormat = hearingFormatService.fillHearingFormat(result);
            hearingFormat.setCaseDetention(caseRepository.findOne(result.getIdCase()));

            return hearingFormatService.save(hearingFormat, request);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsert_hearing_format", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error, intente nuevamente");
        }
    }


}
