package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.CatChannelingInstitutionName;
import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.CatInstitutionType;
import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ChannelingDropTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ChannelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChannelingController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/channeling/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/channeling/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/supervisor/channeling/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCase",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_CLOSED);
                }}, JqGridFilterModel.COMPARE_NOT_IN);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isTerminated", "1", JqGridFilterModel.COMPARE_EQUAL);
        extraFilter.setbData(true);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMeVe = r.join("meeting");
                final Join<Meeting, Imputed> joinMee = joinMeVe.join("imputed");
                final Join<Case, District> joinDi = r.join("district");
                final Join<Case, FramingMeeting> joinFr = r.join("framingMeeting");
                final Join<FramingMeeting, User> joinFrUs = joinFr.join("supervisor");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("idMP"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinDi.get("name"));
                    add(joinFrUs.get("fullname"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCase"))
                    return r.join("status").get("name");
                if (field.equals("isTerminated"))
                    return r.join("framingMeeting").get("isTerminated");
                if (field.equals("imputed"))
                    return r.join("meeting").join("imputed").get("name");
                if (field.equals("district"))
                    return r.join("district").get("name");
                if (field.equals("supervisor"))
                    return r.join("framingMeeting").join("supervisor").get("fullname");
                return null;
            }
        }, Case.class, ChannelingCaseView.class);

        return result;
    }

    @RequestMapping(value = {"/supervisor/channeling/listChannelings"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts, Long id) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("caseId", id.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Channeling, CatChannelingType> joinChTy = r.join("channelingType");
                final Join<Channeling, CatInstitutionType> joinInTy = r.join("institutionType");
                final Join<Channeling, CatChannelingInstitutionName> joinChIn = r.join("institutionName");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("consecutive"));
                    add(r.get("isAuthorizeToDrop"));
                    add(joinChTy.get("name"));
                    add(r.get("name"));
                    add(joinInTy.get("name"));
                    add(joinChIn.get("name"));
                    add(r.get("isFulfilled"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if (field.equals("channelingType"))
                    return r.join("channelingType").get("name");
                if (field.equals("institutionType"))
                    return r.join("institutionType").get("name");
                if (field.equals("institutionName"))
                    return r.join("institutionName").get("name");
                if (field.equals("isFulfilledTx"))
                    return r.get("isFulfilled");

                return null;
            }
        }, Channeling.class, ChannelingView.class);

        return result;
    }

    @Autowired
    ChannelingService channelingService;

    @RequestMapping(value = "/supervisor/channeling/upsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView insert(@RequestParam(required = true) Long id, Long channelingId) {
        ModelAndView model = new ModelAndView("/supervisor/channeling/upsert");

        ChannelingModel channeling;
        if (channelingId == null) {
            channeling = channelingService.getChannelingInfoByCaseId(id);
        } else {
            channeling = channelingService.getChannelingInfoByCaseIdAndChannelingId(id, channelingId);
        }

        Gson gson = new Gson();
        String sObject = gson.toJson(channeling);
        model.addObject("channeling", sObject);

        channelingService.getChannelingCatalogs(model);
        return model;
    }


    @RequestMapping(value = "/supervisor/channeling/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute final ChannelingModel model) {
        ResponseMessage response = new ResponseMessage();

        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            channelingService.doUpsert(model, user, response);

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsert", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }

        return response;
    }


    @RequestMapping(value = "/supervisor/channeling/doObsolete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doObsolete(@RequestParam(required = true) Long id, @RequestParam(required = true) Long channelingId) {

        ResponseMessage response = new ResponseMessage();
        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            channelingService.doObsolete(id, channelingId, user, response);

            return response;

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doObsolete", userService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
            return response;
        }
    }


    @Autowired
    ChannelingDropTypeRepository channelingDropTypeRepository;

    @RequestMapping(value = "/supervisor/channeling/requestDrop", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView requestDrop(@RequestParam(required = true) Long id, @RequestParam(required = true) Long channelingId) {
        ModelAndView model = new ModelAndView("/supervisor/channeling/requestDrop");

        ChannelingModel channeling;
        channeling = channelingService.getChannelingInfoByCaseIdAndChannelingId(id, channelingId);

        Gson gson = new Gson();
        String sObject = gson.toJson(channeling);
        model.addObject("channeling", sObject);

        List<SelectList> lstChannelingDrop = channelingDropTypeRepository.findNotObsolete();
        model.addObject("lstChannelingDropType", gson.toJson(lstChannelingDrop));

        return model;
    }

    @RequestMapping(value = "/supervisor/channeling/doRequestDrop", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doRequestDrop(@ModelAttribute final ChannelingDropModel model) {

        ResponseMessage response = new ResponseMessage();
        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            channelingService.requestDrop(model, user, response, userService);

            return response;

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doRequestDrop", userService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
            return response;
        }
    }

    @RequestMapping(value = "/supervisor/channeling/printSheet", method = RequestMethod.GET)
    public ModelAndView printSheet(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = null;
        try {
            ChannelingModelSheet sheetInfo = channelingService.getChannelingSheetById(id);

            if (sheetInfo == null) {
                model = new ModelAndView("/supervisor/channeling/notSheet");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-oficio-canalización.doc\"");
                return model;
            }

            model = new ModelAndView("/supervisor/channeling/printSheet");
            model.addObject("data", sheetInfo);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"oficio-canalización-" +
                    sheetInfo.getIdMP() + "-" + sheetInfo.getConsecutiveTx() + ".doc\"");

            channelingService.addLogChannelingDoc(sheetInfo.getIdCase(),sheetInfo.getChannelingType());
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "printSheet", userService);
            model = null;
        }
        return model;
    }

    @RequestMapping(value = "/supervisor/channeling/reportAttendance", method = RequestMethod.GET)
    public ModelAndView reportAttendance(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = null;
        try {
            ChannelingModelSheet sheetInfo = channelingService.getChannelingSheetById(id);

            if (sheetInfo == null) {
                model = new ModelAndView("/supervisor/channeling/notReport");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-reporte-asistencias.doc\"");
                return model;
            }

            List<ActivityChannelingModel> lstActivitiesChanneling = channelingService.getLstActivitiesChanneling(id);

            if (lstActivitiesChanneling == null ||  lstActivitiesChanneling.size() < 1) {
                model = new ModelAndView("/supervisor/channeling/notReport");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-actividades-reporte-asistencias.doc\"");
                return model;
            }

            List<ActivityChannelingModel> lstActivities = new ArrayList<>();
            List<ActivityChannelingModel> lstActivitiesAttendance = new ArrayList<>();
            List<ActivityChannelingModel> lstActivitiesNotAttendance = new ArrayList<>();

            ActivityChannelingModel act;
            Integer attendance;

            for(int i = 0, len = lstActivitiesChanneling.size(); i<len; i++){
                act = lstActivitiesChanneling.get(i);
                attendance = act.getAttendance();

                if(attendance.equals(-1))
                    lstActivities.add(act);
                else if(attendance.equals(0))
                    lstActivitiesNotAttendance.add(act);
                else
                    lstActivitiesAttendance.add(act);
            }

            model = new ModelAndView("/supervisor/channeling/reportAttendance");
            model.addObject("data", sheetInfo);
            model.addObject("lstActAtt", lstActivitiesAttendance);
            model.addObject("lstActNoAtt", lstActivitiesNotAttendance);
            model.addObject("lstActNa", lstActivities);

            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte-asistencias-" +
                    sheetInfo.getIdMP() + "-" + sheetInfo.getConsecutiveTx() + ".doc\"");

            channelingService.addLogChannelingDoc(sheetInfo.getIdCase(),sheetInfo.getChannelingType());
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "reportAttendance", userService);
            model = null;
        }
        return model;
    }

    @RequestMapping(value = "/supervisor/channeling/isFulfilled", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView isFulfilled(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/supervisor/channeling/isFulfilled");
        Boolean isFulfilled = channelingService.isFulfilledByChannelingId(id);
        model.addObject("channelingId", id);
        model.addObject("isFulfilled", (isFulfilled == null ? false : isFulfilled));

        return model;
    }

    @RequestMapping(value = "/supervisor/channeling/doIsFulfilled", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doIsFulfilled(@ModelAttribute final ChannelingFulfilledModel model) {

        ResponseMessage response = new ResponseMessage();
        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            channelingService.doIsFulfilled(model, user, response, userService);

            return response;

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doIsFulfilled", userService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
            return response;
        }
    }


}
