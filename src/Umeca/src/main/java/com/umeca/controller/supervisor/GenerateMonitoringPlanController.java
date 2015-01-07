package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.dto.ScheduleLogDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.reviewer.TechnicalReviewRepository;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.ScheduleService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.MonitoringPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 12:03 PM
 */

@Controller
public class GenerateMonitoringPlanController {

    @Autowired
    SharedLogExceptionService logException;

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisor/generateMonitoringPlan/index";
    }


    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private SharedUserService userService;

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>(){{add(MonitoringConstants.STATUS_PENDING_AUTHORIZATION);add(MonitoringConstants.STATUS_PENDING_END);
                    add(MonitoringConstants.STATUS_END);}},JqGridFilterModel.COMPARE_NOT_IN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<MonitoringPlan,Case> joinCd = r.join("caseDetention");
                final javax.persistence.criteria.Join<Meeting,Imputed> joinIm = joinCd.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(joinCd.get("id"));
                    add(joinCd.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                    add(r.get("creationTime"));
                    add(r.get("generationTime"));
                    add(r.get("authorizationTime"));
                    add(r.get("status"));
                    add(r.join("supervisor").get("fullname"));
                    add(r.get("posAuthorizationChangeTime"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if(field.equals("stCreationTime"))
                    return r.get("creationTime");
                if(field.equals("stGenerationTime"))
                    return r.get("generationTime");
                if(field.equals("stAuthorizationTime"))
                    return r.get("authorizationTime");
                if(field.equals("supervisorId"))
                    return r.join("supervisor").get("id");
                return null;
            }
        }, MonitoringPlan.class, MonitoringPlanView.class);

        return result;

    }


    @Autowired
    private ArrangementRepository arrangementRepository;
    @Autowired
    private HearingFormatRepository hearingFormatRepository;
    @Autowired
    private SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    private ActivityGoalRepository activityGoalRepository;
    @Autowired
    private FramingReferenceRepository framingReferenceRepository;
    @Autowired
    private MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    private ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    private TechnicalReviewRepository qTechnicalReviewRepository;
    @Autowired
    private ScheduleService scheduleService;


    @RequestMapping(value = "/supervisor/generateMonitoringPlan/generate", method = RequestMethod.GET)
    public @ResponseBody ModelAndView generate(@RequestParam Long id){ //Id de MonitoringPlan
        ModelAndView model = new ModelAndView("/supervisor/generateMonitoringPlan/generate");
        Gson gson = new Gson();

        Long caseId = monitoringPlanRepository.getCaseIdByMonPlan(id);

        //Find last hearing format to get last assigned arrangements
        List<Long> lastHearingFormatId = hearingFormatRepository.getLastHearingFormatByMonPlan(id, new PageRequest(0, 1));

        List<SelectList> lstGeneric = arrangementRepository.findLstArrangementByHearingFormatId(lastHearingFormatId.get(0));
        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstArrangements", sLstGeneric);

        lstGeneric = supervisionActivityRepository.findAllValidSl();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstActivities", sLstGeneric);

        lstGeneric = activityGoalRepository.findAllValidMonitoring();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstGoals", sLstGeneric);

        lstGeneric = framingReferenceRepository.findAllValidByCaseId(caseId);
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstSources", sLstGeneric);
        Long idTec = qTechnicalReviewRepository.getTechnicalReviewByCaseId(caseId);
        if(idTec!=null){
            model.addObject("idTec",idTec);
        }
        MonitoringPlanInfo mpi =  monitoringPlanRepository.getInfoById(id);
        model.addObject("caseId",mpi.getIdCase());
        model.addObject("mpId",mpi.getIdMP());
        model.addObject("personName",mpi.getPersonName());
        model.addObject("monStatus",mpi.getMonStatus());
        model.addObject("monitoringPlanId",mpi.getIdMonitoringPlan());

        model.addObject("isInAuthorizeReady", MonitoringConstants.LST_STATUS_AUTHORIZE_READY.contains(mpi.getMonStatus()));

        //It's done on client side
        //List<ActivityMonitoringGroupInfo> lstGroupInfo = activityMonitoringPlanRepository.findGroupInfoBy(id, MonitoringConstants.STATUS_ACTIVITY_DELETED);
        //sLstGeneric = gson.toJson(lstGroupInfo);
        //model.addObject("lstGroupInfo", sLstGeneric);

        List<ActivityMonitoringPlan> lstActivities = activityMonitoringPlanRepository.findValidActivitiesBy(id, MonitoringConstants.STATUS_ACTIVITY_DELETED);
        List<ActivityMonitoringPlanDto> lstDtoActivities = ActivityMonitoringPlanDto.convertToDtos(lstActivities);

        sLstGeneric = gson.toJson(lstDtoActivities);
        model.addObject("lstActivitiesMonPlan",sLstGeneric);
        List<ScheduleLogDto> listSchedules = scheduleService.getFramingScheduleByIdCase(caseId);
        model.addObject("lstSchedules",gson.toJson(listSchedules));
        return model;
    }

    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    MonitoringPlanService monitoringPlanService;

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsert(@RequestBody ActivityMonitoringPlanRequest model){
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Plan de seguimiento");

        try {
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(monitoringPlanService.doUpsertDelete(sharedUserService, monitoringPlanRepository, model, user, response) == false)
                return response;


            if(model.getActsIns() == 0 && model.getActsUpd() == 0 && model.getActsDel() == 0
                    && model.getActsPreIns() == 0 && model.getActsPreUpd() == 0 && model.getActsPreDel() == 0){
                response.setMessage("No fue posible realizar la operaci&oacute;n, revise que su informaci&oacute;n est&eacute; correcta o que la(s)" +
                        " actividad(es) que desea modificar y/o eliminar a&uacute;n no est&eacute;(n) finalizada(s) o sean actividades actuales y/o futuras.");
                response.setHasError(true);
            }else{
                Gson gson = new Gson();
                response.setReturnData(gson.toJson(model.getLstActivitiesUpserted()));
                response.setHasError(false);
                response.setMessage("La operaci&oacute;n se realiz&oacute; de forma correcta." +
                        (model.getActsIns() == 0 ? "" : ("<br/>" + model.getActsIns() + " actividad(es) fue(ron) insertada(s)")) +
                        (model.getActsUpd() == 0 ? "" : ("<br/>" + model.getActsUpd() + " actividad(es) fue(ron) actualizada(s)")) +
                        (model.getActsDel() == 0 ? "" : ("<br/>" + model.getActsDel() + " actividad(es) fue(ron) eliminada(s)")) +
                        (model.getActsPreIns() == 0 ? "" : ("<br/>" + model.getActsPreIns() + " actividad(es) fue(ron) insertada(s) en espera de autorizaci&oacute;n")) +
                        (model.getActsPreUpd() == 0 ? "" : ("<br/>" + model.getActsPreUpd() + " actividad(es) fue(ron) actualizada(s) en espera de autorizaci&oacute;n")) +
                        (model.getActsPreDel() == 0 ? "" : ("<br/>" + model.getActsPreDel() + " actividad(es) fue(ron) eliminada(s) en espera de autorizaci&oacute;n")));

            }
            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsert", sharedUserService);
            response.setHasError(true);
        }
        response.setMessage("Se present&oacute; un error inesperado. Por favor revise que la informaci&oacute;n e intente de nuevo");
        return response;
    }

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/deleteActMonPlan", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage deleteActMonPlan(@RequestBody ActivityMonitoringPlanRequest model){
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Eliminar actividades");

        try {
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false){
                response.setHasError(true);
                response.setMessage("La contrase&ntilde;a no corresponde al usuario en sesi&oacute;n");
                return response;
            }

            if(monitoringPlanService.doUpsertDelete(sharedUserService, monitoringPlanRepository, model, user, response) == false)
                return response;


            if(model.getActsIns() == 0 && model.getActsUpd() == 0 && model.getActsDel() == 0
                    && model.getActsPreIns() == 0 && model.getActsPreUpd() == 0 && model.getActsPreDel() == 0){
                response.setHasError(true);
                response.setMessage("No fue posible realizar la operaci&oacute;n, revise que su informaci&oacute;n est&eacute; correcta o que la(s)" +
                        " actividad(es) que desea modificar y/o eliminar a&uacute;n no est&eacute;(n) finalizada(s) o sean actividades actuales y/o futuras.");
            }else{
                Gson gson = new Gson();
                response.setReturnData(gson.toJson(model.getLstActivitiesUpserted()));
                response.setHasError(false);
                response.setMessage("La operaci&oacute;n se realiz&oacute; de forma correcta." +
                        (model.getActsIns() == 0 ? "" : ("<br/>" + model.getActsIns() + " actividad(es) fue(ron) insertada(s)")) +
                        (model.getActsUpd() == 0 ? "" : ("<br/>" + model.getActsUpd() + " actividad(es) fue(ron) actualizada(s)")) +
                        (model.getActsDel() == 0 ? "" : ("<br/>" + model.getActsDel() + " actividad(es) fue(ron) eliminada(s)")) +
                        (model.getActsPreIns() == 0 ? "" : ("<br/>" + model.getActsPreIns() + " actividad(es) fue(ron) insertada(s) en espera de autorizaci&oacute;n")) +
                        (model.getActsPreUpd() == 0 ? "" : ("<br/>" + model.getActsPreUpd() + " actividad(es) fue(ron) actualizada(s) en espera de autorizaci&oacute;n")) +
                        (model.getActsPreDel() == 0 ? "" : ("<br/>" + model.getActsPreDel() + " actividad(es) fue(ron) eliminada(s) en espera de autorizaci&oacute;n")));

            }
            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsert", sharedUserService);
            response.setHasError(true);
        }
        response.setMessage("Se present&oacute; un error inesperado. Por favor revise que la informaci&oacute;n e intente de nuevo");
        return response;
    }

}
