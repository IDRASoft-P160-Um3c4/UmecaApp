package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.AcademicLevelRepository;
import com.umeca.repository.catalog.CountryRepository;
import com.umeca.repository.catalog.RelationshipRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.FramingReferenceRepository;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.supervisor.FramingMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FramingMeetingController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private FramingMeetingService framingMeetingService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private FramingReferenceRepository framingReferenceRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AcademicLevelRepository academicLevelRepository;

    @RequestMapping(value = "/supervisor/framingMeeting/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/framingMeeting/index";
    }

    @RequestMapping(value = "/supervisor/framingMeeting/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("status").get("name"));
                    add(r.join("status").get("description"));
                    add(r.get("idFolder"));
                    add(r.join("meeting").join("imputed").get("name"));
                    add(r.join("meeting").join("imputed").get("lastNameP"));
                    add(r.join("meeting").join("imputed").get("lastNameM"));
                    add(r.join("meeting").join("imputed").get("birthDate"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");
                return null;
            }
        }, Case.class, ForFramingMeetingGrid.class);

        return result;
    }


    @RequestMapping(value = "/supervisor/framingMeeting/framingMeeting", method = RequestMethod.GET)
    public ModelAndView framingMeeting(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/framingMeeting");

        Case caseDet = caseRepository.findOne(id);

        if (caseDet.getFramingMeeting() == null) {
            FramingMeeting framingMeeting = new FramingMeeting();
            caseDet.setFramingMeeting(framingMeeting);
            framingMeeting.setCaseDetention(caseDet);
            framingMeetingService.save(framingMeeting);

        }

        FramingMeetingView framingMeetingView = framingMeetingService.fillForView(caseDet);

        Gson conv = new Gson();

        model.addObject("objView", conv.toJson(framingMeetingView));
        model.addObject("lstCountry", conv.toJson(countryRepository.findAll()));
        model.addObject("listState", conv.toJson(stateRepository.findAll()));
        model.addObject("lstRelationship",conv.toJson(relationshipRepository.findAll()));
        model.addObject("lstAcademicLevel",conv.toJson(academicLevelRepository.findAll()));

        return model;
    }


    @RequestMapping(value = "/supervisor/framingMeeting/listHousemate", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listHousemate(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilterA = new JqGridRulesModel("personType", FramingMeetingConstants.PERSON_TYPE_HOUSEMATE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilterA);


        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("age"));
                    add(r.join("relationship").get("name"));
                    add(r.get("occupation"));
                    add(r.get("personType"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("idCase"))
                    return r.join("framingMeeting").join("caseDetention").get("id");

                if (field.equals("personType"))
                    return r.get("personType");

                return null;
            }
        }, FramingReference.class, FramingReference.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/listReferences", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listReferences(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilterA = new JqGridRulesModel("personType", FramingMeetingConstants.PERSON_TYPE_REFERENCE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilterA);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("phone"));
                    add(r.join("relationship").get("name"));
                    add(r.get("address"));
                    add(r.get("personType"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("idCase"))
                    return r.join("framingMeeting").join("caseDetention").get("id");

                if (field.equals("personType"))
                    return r.get("personType");

                return null;
            }
        }, FramingReference.class, FramingReference.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/housemate/upsert", method = RequestMethod.POST)
    public ModelAndView showHousemateUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/housemate/upsert");

        FramingReference housemate = new FramingReference();
        Gson conv = new Gson();

        if (id != null)
            housemate = framingReferenceRepository.findOne(id);

        housemate.setIdCase(idCase);

        model.addObject("housemate", conv.toJson(housemate));
        model.addObject("lstRelationship", conv.toJson(relationshipRepository.findAll()));

        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/references/upsert", method = RequestMethod.POST)
    public ModelAndView showReferencesUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/references/upsert");

        FramingReference reference = new FramingReference();
        Gson conv = new Gson();

        if (id != null)
            reference = framingReferenceRepository.findOne(id);

        reference.setIdCase(idCase);

        model.addObject("reference", conv.toJson(reference));
        model.addObject("lstRelationship", conv.toJson(relationshipRepository.findAll()));

        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/references/doReferenceUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doReferenceUpsert(@RequestParam Long idCase, @ModelAttribute FramingReference framingReference) {

        Case existCase = caseRepository.findOne(idCase);

        if (existCase == null)
            return new ResponseMessage(true, "Ocurrio un error al guardar la información. Intente mas tarde.");

        return framingMeetingService.saveReference(existCase,framingReference);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/loadExistSources", method = RequestMethod.POST)
    public
    @ResponseBody
    String loadExistSources(@RequestParam(required = true) Long idCase) {
        Gson conv = new Gson();
        return conv.toJson(framingMeetingService.loadExistSources(idCase));
    }

    @RequestMapping(value = "/supervisor/framingMeeting/environmentAnalysis/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage environmentAnalysisDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute FramingEnvironmentAnalysisForView view) {
        return framingMeetingService.saveSelectedSource(idCase, view.getLstSelectedSources());
    }

    @RequestMapping(value = "/supervisor/framingMeeting/processAccompaniment/loadProcessAccompaniment", method = RequestMethod.POST)
    public
    @ResponseBody
    ProcessAccompanimentForView loadProcessAccompaniment(@RequestParam(required = true) Long idCase) {
        return framingMeetingService.fillProcessAccompanimentForView(idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/processAccompaniment/doUpsert", method = RequestMethod.POST)
     public
     @ResponseBody
     ResponseMessage processAccompanimentDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute ProcessAccompanimentForView view) {

        ProcessAccompaniment processAccompaniment=framingMeetingService.fillProcessAccompaniment(view);
        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
        existFraming.setProcessAccompaniment(processAccompaniment);

        return framingMeetingService.save(existFraming);
    }



}
