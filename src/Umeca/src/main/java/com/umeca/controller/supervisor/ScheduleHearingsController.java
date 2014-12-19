package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.shared.ScheduleHearing;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.HearingTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.SystemSettingService;
import com.umeca.service.supervisor.ScheduleHearingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class ScheduleHearingsController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/scheduleHearings/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/scheduleHearings/index";
    }

    @RequestMapping(value = "/supervisor/scheduleHearings/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts, String searchField) {

        if(searchField == null || searchField.isEmpty())
            return null;

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("searchField", searchField, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<MonitoringPlan,Case> joinCd = r.join("caseDetention");
                final javax.persistence.criteria.Join<Meeting,Imputed> joinIm = joinCd.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>() {{
                    add(joinCd.get("id"));
                    add(r.get("status"));
                    add(joinCd.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("searchField"))
                    return r.join("caseDetention").get("idMP");
                if (field.equals("fullName"))
                    return r.join("caseDetention").join("meeting").join("imputed").get("name");
                return null;
            }
        }, MonitoringPlan.class, ForCasesHFGrid.class);
        return result;
    }


    @RequestMapping(value = "/supervisor/scheduleHearings/sublist", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel sublist(@ModelAttribute JqGridFilterModel opts, String monPlanId) {

        if(monPlanId == null || monPlanId.isEmpty())
            return null;

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("monPlanId", monPlanId, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<ScheduleHearing,User> joinUs = r.join("user");
                final javax.persistence.criteria.Join<ScheduleHearing,HearingType> joinHt = r.join("hearingType");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("room"));
                    add(r.get("hearingDate"));
                    add(r.get("timestamp"));
                    add(r.get("hearingReminderDate"));
                    add(r.get("hasReminder"));
                    add(joinUs.get("username"));
                    add(joinHt.get("description"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("hearingDateSt"))
                    return r.get("hearingDate");
                if (field.equals("hearingCreationDateSt"))
                    return r.get("timestamp");
                if (field.equals("hearingReminderDateSt"))
                    return r.get("hearingReminderDate");
                if (field.equals("usernameCreate"))
                    return r.join("user").get("username");
                if (field.equals("hearingTypeName"))
                    return r.join("hearingType").get("description");
                if (field.equals("monPlanId"))
                    return r.join("monitoringPlan").get("id");
                return null;
            }
        }, ScheduleHearing.class, ScheduleHearingSubListView.class);
        return result;
    }



    @Autowired
    HearingTypeRepository hearingTypeRepository;

    @Autowired
    SystemSettingService systemSettingService;

    @RequestMapping(value = "/supervisor/scheduleHearings/scheduleNewHearing", method = RequestMethod.POST)
    public ModelAndView scheduleNewHearing(@RequestParam String id){
        ModelAndView model = new ModelAndView("/supervisor/scheduleHearings/scheduleNewHearing");

        try{
            Gson gson = new Gson();

            List<SelectList> lstGeneric = hearingTypeRepository.getValidaHearingType();
            String sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstHearingType", sLstGeneric);
            model.addObject("lstIds", id);
            model.addObject("daysBefReminder", systemSettingService.getSystemSettingsValues().getDaysBeforeForReminder());

            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "scheduleNewHearing", sharedUserService);
            return null;
        }
    }



    @Autowired
    ScheduleHearingsService scheduleHearingsService;

    @RequestMapping(value = "/supervisor/scheduleHearings/doScheduleNewHearing", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doScheduleNewHearing(@ModelAttribute ScheduleNewHearingReq model){

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try{
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            Calendar dtFloor = CalendarExt.getFloorDate(model.getHearingDate());
            Calendar today = CalendarExt.getToday();

            if(dtFloor.compareTo(today) < 0){
                response.setMessage("La fecha de la audicencia no puede ser menor a la fecha actual");
                return response;
            }

            if(model.getHasReminder()){
                Calendar dtRemFloor = CalendarExt.getFloorDate(model.getHearingDateReminder());

                if(dtRemFloor.compareTo(today) < 0){
                    response.setMessage("La fecha del recordatorio no puede ser menor a la fecha actual");
                    return response;
                }

                if(dtFloor.compareTo(dtRemFloor) <= 0){
                    response.setMessage("La fecha del recordatorio no puede ser el mismo d&iacute;a o d&eacute;spues de la audiencia");
                    return response;
                }
            }

            if(model.getLstMonPlanIds().size() == 0){
                response.setMessage("Al menos debe existir un plan de monitoreo para agendar la audiencia");
                return response;
            }

            if(scheduleHearingsService.doScheduleNewHearing(model, user, response)){
                response.setHasError(false);
            }

        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doScheduleNewHearing", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }
}

