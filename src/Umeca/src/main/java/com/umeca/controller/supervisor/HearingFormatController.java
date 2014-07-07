package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.RegisterTypeRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.HearingFormatTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.catalog.CatalogService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HearingFormatController {

    @Qualifier("qArrangementRepository")
    @Autowired
    ArrangementRepository arrangementRepository;

    @Qualifier("registerTypeRepository")
    @Autowired
    RegisterTypeRepository registerTypeRepository;

    @Autowired
    CaseService caseService;

    @Autowired
    HearingFormatService hearingFormatService;

    @Autowired
    CatalogService catalogService;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    SharedUserService userService;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    HearingFormatTypeRepository hearingFormatTypeRepository;

    @RequestMapping(value = "/supervisor/hearingFormat/listCases", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listCases(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{//TODO SE DEBEN AGREGAR LOS STATUS EN LOS CUALES SE PERMITE AGREGAR UN FORMATO DE AUDIENCIA
                    /*8. Se puede generar un formato de audiencia para cualquier estado del proceso de supervisión y para los casos con entrevista verificada.
                    (casos que se encuentren en entrevista de encuadre, que se encuentren en creación del plan de seguimiento,
                    o que se encuentren en seguimiento al plan de supervisión), ya no se puede generar un formato de audiencia para los casos que se encuentren cerrados,
                    o en autorización de cierre.*/
                    add(Constants.CASE_STATUS_VERIFICATION_COMPLETE);
                    add(Constants.CASE_STATUS_HEARING_FORMAT_END);
                    add(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE);
                    add(Constants.CASE_STATUS_PRE_CLOSED);

                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Meeting, Imputed> joinIm = r.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("status").get("name"));
                    add(r.join("status").get("description"));
                    add(r.get("idFolder"));
                    add(r.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.get("idFolder");

                if (field.equals("idMP"))
                    return r.get("idMP");

                if (field.equals("statusName"))
                    return r.join("status").get("name");

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

                //final javax.persistence.criteria.Join<HearingFormat, Case> joinCase = r.join("caseDetention");
                final javax.persistence.criteria.Join<HearingFormat, HearingFormatImputed> joinHImp = r.join("hearingImputed");
                final javax.persistence.criteria.Join<HearingFormat, HearingFormatSpecs> joinSpecs = r.join("hearingFormatSpecs");

                return new ArrayList<Selection<?>>() {{

                    add(r.get("id"));
                    //add(joinCase.get("idFolder"));
                    //add(joinCase.get("idMP"));
                    add(r.get("idFolder"));
                    add(r.get("idJudicial"));
                    add(joinHImp.get("name"));
                    add(joinHImp.get("lastNameP"));
                    add(joinHImp.get("lastNameM"));
                    add(joinSpecs.get("arrangementType"));
                    add(joinSpecs.get("extension"));
                    add(joinSpecs.get("linkageProcess"));

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
    public ModelAndView indexFormats(@RequestParam(required = true) Long id) {

        ModelAndView model = new ModelAndView("/supervisor/hearingFormat/indexFormats");

        model.addObject("idCase", id);
        return model;
    }

    @RequestMapping(value = "/supervisor/hearingFormat/newHearingFormat", method = RequestMethod.GET)
    public ModelAndView newHearingFormat(@RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView();

        if (caseRepository.findOne(idCase).getStatus().getName().equals(Constants.CASE_STATUS_PRE_CLOSED)) {
            model.setViewName("/supervisor/hearingFormat/indexFormats");
            model.addObject("idCase", idCase);
            model.addObject("showErr",true);
            model.addObject("msgError","No es posible agregar mas formatos, el caso se encuentra pre-cerrado.");

        } else {
            model.setViewName("/supervisor/hearingFormat/hearingFormat");
            HearingFormatView hfView = hearingFormatService.fillNewHearingFormatForView(idCase);
            Gson conv = new Gson();
            model.addObject("hfView", conv.toJson(hfView));

            model.addObject("listState", conv.toJson(stateRepository.findAll()));

            model.addObject("listHearingFormatType", conv.toJson(hearingFormatTypeRepository.findAllValid()));

            if (hfView.getIdAddres() != null)
                addressService.fillModelAddress(model, hfView.getIdAddres());

        }
        return model;


    }


    @RequestMapping(value = "/supervisor/hearingFormat/viewHearingFormat", method = RequestMethod.GET)
    public ModelAndView viewHearingFormat(@RequestParam(required = true) Long idFormat) {

        ModelAndView model = new ModelAndView("/supervisor/hearingFormat/hearingFormat");

        HearingFormatView hfView = hearingFormatService.fillExistHearingFormatForView(idFormat);
        Gson conv = new Gson();
        model.addObject("hfView", conv.toJson(hfView));
        model.addObject("listState", conv.toJson(stateRepository.findAll()));

        if (hfView.getIdAddres() != null)
            addressService.fillModelAddress(model, hfView.getIdAddres());

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
            Integer age = userService.calculateAge(imputed.getBirthDate());
            if (age.compareTo(18) == -1) {
                return new ResponseMessage(true, "El imputado debe tener más de 18 años para continuar.");
            }
        }

        if (caseRepository.findByIdMP(idJudicial) != null) {
            return new ResponseMessage(true, "El número de Carpeta Judicial ya existe, verifique los datos.");
        }

        try {
            Case caseDet;

            caseDet = caseService.generateNewCase(imputed, HearingFormatConstants.MEETING_CONDITIONAL_REPRIEVE);
            caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE));
            caseDet.setIdMP(idJudicial);
            response = caseService.saveConditionaReprieveCase(caseDet);

        } catch (Exception ex) {
            System.out.println("Error al guardar el caso de suspensi?n condicional de proceso!!!");
            ex.printStackTrace();
            response.setHasError(true);
            response.setTitle("Formato de audiencia");
            response.setMessage("Error al guardar el caso de suspensi?n condicional de proceso!!!");

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

    @Autowired
    CaseRepository caseRepository;

    @RequestMapping(value = "/supervisor/hearingFormat/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute HearingFormatView result, HttpServletRequest request) {

        if (result.getVincProcess().equals(HearingFormatConstants.PROCESS_VINC_NO)) {
            ResponseMessage resp = hearingFormatService.validatePassCredential(result.getCredPass());
            if (resp != null)
                return resp;
        }

        HearingFormat hearingFormat = hearingFormatService.fillHearingFormat(result);
        hearingFormat.setCaseDetention(caseRepository.findOne(result.getIdCase()));

        return hearingFormatService.save(hearingFormat, request);
    }


}
