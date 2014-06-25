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
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.ElectionRepository;
import com.umeca.repository.catalog.MaritalStatusRepository;
import com.umeca.repository.catalog.RegisterTypeRepository;
import com.umeca.repository.shared.SelectFilterFields;
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

    @Qualifier("electionRepository")
    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    CaseService caseService;

    @Autowired
    HearingFormatService hearingFormatService;

    @Autowired
    CatalogService catalogService;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/hearingFormat/listCases", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listCases(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_VERIFICATION_COMPLETE);
                    add(Constants.CASE_STATUS_HEARING_FORMAT_END);
                    add(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE);
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
                    return r.join("caseDetention").get("idFolder");

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


                final javax.persistence.criteria.Join<HearingFormat, Case> joinCase = r.join("caseDetention");
                final javax.persistence.criteria.Join<HearingFormat, HearingFormatImputed> joinHImp = r.join("hearingImputed");
                final javax.persistence.criteria.Join<HearingFormat, HearingFormatSpecs> joinSpecs = r.join("hearingFormatSpecs");

                return new ArrayList<Selection<?>>() {{

                    add(r.get("id"));
                    add(joinCase.get("idFolder"));
                    add(joinCase.get("idMP"));
                    add(joinHImp.get("name"));
                    add(joinHImp.get("lastNameP"));
                    add(joinHImp.get("lastNameM"));
                    add(joinSpecs.get("hearingType"));
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

        ModelAndView model = new ModelAndView("/supervisor/hearingFormat/hearingFormat");
        HearingFormatView hfView = new HearingFormatView(); // TODO cambiar por el servicio que llena la vista


        model.addObject("hfView", hfView);
        return model;
    }


    @RequestMapping(value = "/supervisor/hearingFormat/viewHearingFormat", method = RequestMethod.GET)
    public ModelAndView viewHearingFormat(@RequestParam(required = true) Long idFormat) {

        ModelAndView model = new ModelAndView("/supervisor/hearingFormat/hearingFormat");
        HearingFormatView hfView = new HearingFormatView(); // TODO cambiar por el servicio que llena la vista


        model.addObject("hfView", hfView);
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

        try {
            Case caseDet;

            caseDet = caseService.generateNewCase(imputed, HearingFormatConstants.MEETING_CONDITIONAL_REPRIEVE);
            caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE));
            caseDet.setIdMP(idJudicial);
            response = caseService.saveConditionaReprieveCase(caseDet);

        } catch (Exception ex) {
            System.out.println("Error al guardar el caso de suspensión condicional de proceso!!!");
            ex.printStackTrace();
            response.setHasError(true);
            response.setTitle("Formato de audiencia");
            response.setMessage("Error al guardar el caso de suspensión condicional de proceso!!!");

        }finally{
            return response;
        }
    }
/*
    @RequestMapping(value = "/supervisor/hearingFormat/searchArrangementsByType", method = RequestMethod.POST)
    public
    @ResponseBody
    String searchArrangement(@RequestBody Integer idTipo) {

        Gson conv = new Gson();
        List<ArrangementView> lstArrangementView = hearingFormatService.getArrangmentLst(idTipo);
        String jsonLst = conv.toJson(lstArrangementView);

        return jsonLst;
    }

    @RequestMapping(value = "/supervisor/hearingFormat/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute HearingFormatView result) {

        ResponseMessage response = new ResponseMessage();
        try {

            Case caseDet = caseService.findByIdFolder(result.getIdFolderCode());

            HearingFormat hearingFormat;
            hearingFormat = hearingFormatService.fillHearingFormat(result);

            if (caseDet != null && caseDet.getHearingFormat() == null) {

                caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END));
                caseDet.setHearingFormat(hearingFormat);
                hearingFormat.setCaseDetention(caseDet);
                response = hearingFormatService.save(hearingFormat);
            } else if (caseDet == null) {

                Imputed imp = new Imputed();
                imp.setName(result.getImputedName());
                imp.setLastNameP(result.getImputedFLastName());
                imp.setLastNameM(result.getImputedSLastName());
                imp.setCelPhone(result.getImputedTel());
                imp.setDateBirth(result.getImputedBirthDate());
                imp.setMaritalStatus(maritalStatusRepository.findOne(1L));

                caseDet = caseService.generateNewCase(imp, result.getHearingType());
                caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END));
                caseDet.setIdFolder(result.getIdFolderCode());
                caseDet.setIdMP(result.getIdJudicialFolderCode());

                Domicile currDom = new Domicile();

                currDom.setRegisterType(registerTypeRepository.findOne(Constants.REGYSTER_TYPE_CURRENT));
                currDom.setBelong(electionRepository.findOne(Constants.ELECTION_NO));
                currDom.setLocation(catalogService.findLocationById(result.getIdLocation()));

                currDom.setStreet(result.getStreet());
                currDom.setNoOut(result.getOutNum());
                currDom.setNoIn(result.getInnNum());
                currDom.setAddressString(currDom.toString());

                currDom.setMeeting(caseDet.getMeeting());
                List<Domicile> lstDom = new ArrayList<>();
                lstDom.add(currDom);
                caseDet.getMeeting().setDomiciles(lstDom);

                hearingFormat.setCaseDetention(caseDet);
                caseDet.setHearingFormat(hearingFormat);

                caseDet = caseService.save(caseDet);
            }

            response.setHasError(false);
            response.setMessage(caseDet.getIdFolder());

        } catch (Exception e) {

            System.out.println("Error al guardar el formato de audiencia!\n");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }
                  */



//    @RequestMapping(value = "/supervisor/hearingFormat/searchArrangements", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    String searchArrangement(@RequestBody String folderId) {
//
//        Gson conv = new Gson();
//        List<ArrangementView> lstArrangementView = hearingFormatService.getArrangmentLst(folderId);
//        String jsonLst = conv.toJson(lstArrangementView);
//
//        return jsonLst;
//    }

    /*
    @RequestMapping(value = "/supervisor/hearingFormat/hearingFormat", method = RequestMethod.GET)
    public String hearingFormat() {

        return "/supervisor/hearingFormat/hearingFormat";
    }

    @RequestMapping(value = "/supervisor/hearingFormat/searchCase", method = RequestMethod.POST)
    public
    @ResponseBody
    HearingFormatView searchCase(@RequestBody String idFolder) {

        HearingFormatView hearingFormatView;

        Case caseDet = caseService.findByIdFolder(idFolder);
        hearingFormatView = hearingFormatService.fillForView(caseDet, idFolder);

        return hearingFormatView;
    }







*/

}
