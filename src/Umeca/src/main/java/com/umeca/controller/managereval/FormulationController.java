package com.umeca.controller.managereval;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.managereval.Formulation;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.managereval.FormulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FormulationController {

    @Autowired
    FormulationService formulationService;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SharedUserService userService;


    @RequestMapping(value = {"/managereval/formulation/list"} )
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", false, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("registrationFormulationDate"));
                    add(r.get("document"));
                    add(r.get("certificateNotification"));
                    add(r.get("firstName"));
                    add(r.get("lastNameP"));
                    add(r.get("lastNameM"));
                    add(r.get("umecaInterviewDate"));
                    add(r.get("hearingDate"));
                    add(r.get("presence"));
                    add(r.join("reviewer").get("fullname"));
                }};
            }
            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("isObsolete"))
                    return r.get("isObsolete");
                else
                return null;
            }
        }, Formulation.class,Formulation.class);

        return result;
    }

    @RequestMapping(value = {"/reviewer/formulation/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listFormulationReviewer(@ModelAttribute JqGridFilterModel opts){

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", false, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reviewer", userService.GetLoggedUserId().toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("registrationFormulationDate"));
                    add(r.get("document"));
                    add(r.get("certificateNotification"));
                    add(r.get("firstName"));
                    add(r.get("lastNameP"));
                    add(r.get("lastNameM"));
                    add(r.get("umecaInterviewDate"));
                    add(r.get("hearingDate"));
                    add(r.get("presence"));
                    add(r.join("reviewer").get("fullname"));
                }};
            }
            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("isObsolete"))
                    return r.get("isObsolete");
                else
                    return null;
            }
        }, Formulation.class,Formulation.class);

        return result;
    }

    @RequestMapping(value = {"/managereval/formulation/index", "/reviewer/formulation/index"}, method = RequestMethod.GET)
    public ModelAndView formulationDate() {
        ModelAndView mv = new ModelAndView("/managereval/formulation/index");
        return mv;
    }

    @RequestMapping(value = {"/managereval/formulation/upsert"}, method = RequestMethod.POST)
    public ModelAndView upsert(Long id){
        return formulationService.upsert(id);
    }

    @RequestMapping(value = {"/managereval/formulation/absenceReport","/reviewer/reviewer/absenceReport"}, method = RequestMethod.POST)
    public ModelAndView absenceReport(Long id){
        return formulationService.absenceReport(id);
    }

    @RequestMapping(value = {"/managereval/formulation/doupsert"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doupsert(Formulation formulation){
        return formulationService.doUpsert(formulation);
    }

    @RequestMapping(value = {"/reviewer/formulation/confirmInformation"}, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage confirmInformation(Long id){
        return formulationService.confirmInformation(id);
    }

    @RequestMapping(value = "/reviewer/formulation/printAbsenceReport", method = RequestMethod.GET)
    public ModelAndView printAbsenceReport(@RequestParam(required = true) Long id, HttpServletResponse response) {
        return formulationService.printAbsenceReport(id, response);
    }


    @RequestMapping(value = "/reviewer/formulation/showAttendaneRecord", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView showAttendanceRecord(Long id){
        return formulationService.showAttendanceRecord(id);
    }

    @RequestMapping(value = "/reviewer/formulation/doAttendanceRecord")
    public
    @ResponseBody
    ResponseMessage doAttendanceRecord(Long id, boolean attendance){
        return formulationService.doAttendanceRecord(id,attendance);

    }


    public void setFormulationService(FormulationService formulationService) {
        this.formulationService = formulationService;
    }
}
