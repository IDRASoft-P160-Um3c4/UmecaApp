package com.umeca.controller.director;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.activityReport.ActivityReportDirector;
import com.umeca.model.entities.director.activityReport.ActivityReportDirectorView;
import com.umeca.model.entities.shared.activityReport.ActivityReport;
import com.umeca.model.entities.shared.activityReport.ActivityReportRequest;
import com.umeca.model.entities.shared.activityReport.ActivityReportView;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.ActivityReportService;
import com.umeca.service.shared.SharedLogExceptionService;
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
public class ActivityReportDirectorController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/director/activityReport/index", method = RequestMethod.GET)
    public String index() {
        return "/director/activityReport/index";
    }

    @Autowired
    SharedUserService userService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/director/activityReport/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("userId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("creationDate"));
                    add(r.get("reportName"));
                    add(r.get("description"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                if (field.equals("userId"))
                    return r.join("creatorUser").get("id");
                return null;
            }
        }, ActivityReportDirector.class, ActivityReportDirectorView.class);

        return result;
    }


    @RequestMapping(value = "/director/activityReport/wizardUpsert", method = RequestMethod.GET)
    public String wizardUpsert() {
        return "/director/activityReport/wizardUpsert";
    }

    /*
    @RequestMapping(value = "/shared/activityReport/upsert", method = RequestMethod.POST)
    public @ResponseBody ModelAndView upsertActivityReport(@RequestParam(required = false) Long id) {
        ModelAndView model = new ModelAndView("/shared/activityReport/upsert");
        return model;
    }

    @Autowired
    ActivityReportService activityReportService;

    @RequestMapping(value = "/shared/activityReport/doUpsert", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage doUpsert(@RequestBody ActivityReportRequest model){
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Agenda de actividades ");

        try {
            User user = new User();
            if(userService.isValidUser(user, response) == false)
                return response;

            List<String> lstRole = userService.getLstRolesByUserId(user.getId());

            if(lstRole.isEmpty()){
                response.setMessage("El usuario no tiene un rol asignado");
                response.setHasError(true);
                return response;
            }

            if(activityReportService.save(model, user, lstRole.get(0)) == false)
                return response;

            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsert", userService);
            response.setHasError(true);
        }

        response.setMessage("Se present&oacute; un error inesperado. Por favor revise que la informaci&oacute;n e intente de nuevo");
        return response;
    }


    @RequestMapping(value = "/shared/activityReport/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage delete(@RequestParam Long id){
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Agenda de actividades ");

        try {
            User user = new User();
            if(userService.isValidUser(user, response) == false)
                return response;

            List<String> lstRole = userService.getLstRolesByUserId(user.getId());

            if(lstRole.isEmpty()){
                response.setMessage("El usuario no tiene un rol asignado");
                response.setHasError(true);
                return response;
            }

            if(activityReportService.delete(id, user, response))
                return response;

            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "delete", userService);
            response.setHasError(true);
        }

        response.setMessage("Se present&oacute; un error inesperado. Por favor revise que la informaci&oacute;n e intente de nuevo");
        return response;
    }*/
}
