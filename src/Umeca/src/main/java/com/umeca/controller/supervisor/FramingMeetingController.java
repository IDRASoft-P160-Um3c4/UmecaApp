
package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.dto.AddressDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.AcademicLevelRepository;
import com.umeca.repository.catalog.CountryRepository;
import com.umeca.repository.catalog.RelationshipRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.reviewer.AddressRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.*;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.supervisor.FramingMeetingService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.Collections;
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
    private FramingAddressRepository framingAddressRepository;

    @Autowired
    private AcademicLevelRepository academicLevelRepository;

    @Autowired
    private HearingFormatService hearingFormatService;

    @Autowired
    private AddressRepository addressRepository;

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
        model.addObject("lstRelationship", conv.toJson(relationshipRepository.findAll()));
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

    @RequestMapping(value = "/supervisor/framingMeeting/listAddress", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listAddress(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("address").get("addressString"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("idCase"))
                    return r.join("framingMeeting").join("caseDetention").get("id");

                if (field.equals("fullAddress"))
                    return r.join("address").get("addressString");

                return null;
            }
        }, FramingAddress.class, FramingAddressForGrid.class);

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

    @RequestMapping(value = "/supervisor/framingMeeting/address/upsert", method = RequestMethod.POST)
    public ModelAndView showAddressUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/framingAddress/upsert");

        Gson conv = new Gson();

        Address address;

        if (id != null) {
            address = addressRepository.findOne(id);
            addressService.fillModelAddress(model, address.getId());
        } else {
            address = new Address();
        }


        AddressDto addDto = new AddressDto();
        //addDto.addressDto(address);
        addDto.setIdCase(idCase);

        model.addObject("addObj", conv.toJson(addDto));
        model.addObject("listState", conv.toJson(stateRepository.findAll()));
        model.addObject("idCaseAdd", idCase);

        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/housemate/upsert", method = RequestMethod.POST)
    public ModelAndView showHousemateUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/housemate/upsert");

        FramingReferenceDto housemate;
        Gson conv = new Gson();

        if (id != null)
            housemate = new FramingReferenceDto(framingReferenceRepository.findOne(id));
        else
            housemate = new FramingReferenceDto();

        housemate.setIdCase(idCase);

        model.addObject("housemate", conv.toJson(housemate));

        model.addObject("lstRelationship", conv.toJson(relationshipRepository.findAll()));

        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/address/doAddressUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doAddressUpsert(@RequestParam Long idCase, @ModelAttribute AddressDto view) {

        return framingMeetingService.saveFramingAddress(idCase, view);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/references/upsert", method = RequestMethod.POST)
    public ModelAndView showReferencesUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/references/upsert");

        FramingReferenceDto housemate;
        Gson conv = new Gson();

        if (id != null)
            housemate = new FramingReferenceDto(framingReferenceRepository.findOne(id));
        else
            housemate = new FramingReferenceDto();

        housemate.setIdCase(idCase);

        model.addObject("reference", conv.toJson(housemate));

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

        return framingMeetingService.saveReference(existCase, framingReference);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/address/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage deleteAddress(@RequestParam Long id) {
        return framingMeetingService.deleteFramingAddress(id);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/reference/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage deleteReference(@RequestParam Long id) {
        return framingMeetingService.deleteReference(id);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/environmentAnalysis/loadEnvironmentAnalysis", method = RequestMethod.POST)
    public
    @ResponseBody
    FramingEnvironmentAnalysisForView loadExistSources(@RequestParam(required = true) Long idCase) {
        FramingEnvironmentAnalysisForView view = framingMeetingService.loadEnvironmentAnalysis(idCase);
        return view;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/environmentAnalysis/loadArrangements", method = RequestMethod.GET)
    public
    @ResponseBody
    String loadExistArrangements(@RequestParam(required = true) Long idCase) {
        Gson conv = new Gson();
        List<HearingFormat> lstHearing = caseRepository.findOne(idCase).getHearingFormats();
        return conv.toJson(hearingFormatService.assignedArrangementForView(lstHearing.get(lstHearing.size() - 1).getAssignedArrangements()));
    }

    @RequestMapping(value = "/supervisor/framingMeeting/environmentAnalysis/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage environmentAnalysisDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute FramingEnvironmentAnalysisForView view) {
        return framingMeetingService.saveSelectedItems(idCase, view);
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

        ProcessAccompaniment processAccompaniment = framingMeetingService.fillProcessAccompaniment(idCase,view);
        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
        existFraming.setProcessAccompaniment(processAccompaniment);

        return framingMeetingService.save(existFraming);
    }


    @RequestMapping(value = "/supervisor/framingMeeting/framingActivities/loadAFramingActivities", method = RequestMethod.POST)
    public
    @ResponseBody
    FramingActivitiesForView loadActivities(@RequestParam(required = true) Long idCase) {

        return framingMeetingService.fillActivitiesForView(idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/activities/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage activitiesDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute FramingActivitiesForView view) {

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
        existFraming = framingMeetingService.setActivities(existFraming, view);
        return framingMeetingService.save(existFraming);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/activities/loadRelativeAbroad", method = RequestMethod.GET)
    public
    @ResponseBody
    String loadRelativeAbroad(@RequestParam(required = true) Long idCase) {
        Gson conv = new Gson();
        return conv.toJson(framingMeetingService.loadRelativeAbroad(idCase));
    }

    @RequestMapping(value = "/supervisor/framingMeeting/personalData/loadPersonalData", method = RequestMethod.POST)
    public
    @ResponseBody
    FramingPersonalDataView loadImputedPersonalData(@RequestParam(required = true) Long idCase) {
        return framingMeetingService.fillPersonalDataForView(idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/personalData/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage personalDataDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute FramingPersonalDataView view) {

        FramingImputedPersonalData personalData = framingMeetingService.fillPersonalData(idCase, view);
        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
        existFraming.setPersonalData(personalData);

        return framingMeetingService.save(existFraming);

    }


}
