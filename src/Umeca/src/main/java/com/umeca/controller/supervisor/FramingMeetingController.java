package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.ForFramingMeetingGrid;
import com.umeca.model.entities.supervisor.FramingMeetingView;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.CountryRepository;
import com.umeca.repository.shared.SelectFilterFields;
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
    public ModelAndView framingMeeting(@RequestParam(required = true) String id) {
        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/framingMeeting");

        Case caseDet = caseRepository.findByIdFolder(id);

        FramingMeetingView framingMeetingView = framingMeetingService.fillForView(caseDet);

        Gson conv = new Gson();

        model.addObject("objView", conv.toJson(framingMeetingView));



        model.addObject("lstCountry", conv.toJson(countryRepository.findAll()));
    //    model.addObject("lstPhysicalCondition",conv.toJson(physicalConditionRepository.findAll()));

        return model;
    }
}
