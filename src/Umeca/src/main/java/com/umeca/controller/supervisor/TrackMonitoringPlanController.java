package com.umeca.controller.supervisor;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.OptionList;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.TrackMonPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 12:03 PM
 */

@Controller
public class TrackMonitoringPlanController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisor/trackMonitoringPlan/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private SharedUserService userService;

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>(){{add(MonitoringConstants.STATUS_PENDING_AUTHORIZATION);add(MonitoringConstants.STATUS_AUTHORIZED);
                    add(MonitoringConstants.STATUS_MONITORING);add(MonitoringConstants.STATUS_REJECTED_END);}},JqGridFilterModel.COMPARE_IN);
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
                    add(r.join("supervisor").get("username"));
                    add(joinCd.join("technicalReview", JoinType.LEFT).get("id").alias("idTec"));
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
    private ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    private SupervisionActivityRepository supervisionActivityRepository;


    @RequestMapping(value = "/supervisor/trackMonitoringPlan/trackCalendar", method = RequestMethod.GET)
    public @ResponseBody ModelAndView generate(@RequestParam(required = false) Long id, @RequestParam(required = false) Integer redirect){ //Id monitoring plan
        ModelAndView model = new ModelAndView("/supervisor/trackMonitoringPlan/trackCalendar");

        if(id == null){
            model.addObject("monitoringPlanId",-1);
        }
        else{
            model.addObject("monitoringPlanId",id);
        }

        model.addObject("urlGetActivities","/supervisor/trackMonitoringPlan/getActivities.json");
        model.addObject("urlShowActivity","/supervisor/trackMonitoringPlan/showActivity.html");
        if(redirect==null){
            model.addObject("urlReturn","/supervisor/trackMonitoringPlan/index.html");
        }else if(redirect.equals(1)) {
            model.addObject("urlReturn","/supervisor/manageMonitoringPlan/index.html");
        }
        trackMonPlanService.setLstActivitiesSupervision(model);
        return model;
    }


    @Autowired
    TrackMonPlanService trackMonPlanService;

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/getActivities", method = RequestMethod.POST)
    public @ResponseBody ResponseActivities getActivities(@RequestBody RequestActivities req){
        ResponseActivities response = new ResponseActivities();

        try{
            Long userId = sharedUserService.GetLoggedUserId();

            trackMonPlanService.getLstActivitiesByUserAndFilters(req, userId, new ArrayList<String>() {{
                        add(MonitoringConstants.STATUS_PENDING_AUTHORIZATION);
                        add(MonitoringConstants.STATUS_AUTHORIZED);
                        add(MonitoringConstants.STATUS_MONITORING);
                        add(MonitoringConstants.STATUS_PENDING_END);
                        add(MonitoringConstants.STATUS_REJECTED_END);
                        add(MonitoringConstants.STATUS_END);
                    }},
                    new ArrayList<String>() {{
                        add(MonitoringConstants.STATUS_ACTIVITY_DELETED);
                        add(MonitoringConstants.STATUS_ACTIVITY_PRE_NEW);
                        add(MonitoringConstants.STATUS_ACTIVITY_PRE_MODIFIED);
                        add(MonitoringConstants.STATUS_ACTIVITY_PRE_DELETED);
                    }}, response
            );
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "getActivities", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se sucitó un problema, por favor reinicie su navegador e intente de nuevo.");
            return response;
        }

        return response;
    }

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/showActivity", method = RequestMethod.POST)
    public ModelAndView showActivity(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/supervisor/trackMonitoringPlan/showActivity");

        try{
            trackMonPlanService.getActivityToShow(id, model);
            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "showActivity", sharedUserService);
            return null;
        }
    }

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/doActionActivity", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doActionActivity(@ModelAttribute ActionActivity model){
        ResponseMessage response = new ResponseMessage();
        try{
            String sCommentOk = model.getCommentsOk();
            String sCommentFail = model.getCommentsFail();

            OptionList[] optLst = model.getArrOptArrangementsValues();

            if(optLst == null){
                response.setHasError(true);
                response.setMessage("No existen estados para las obligaciones procesales");
                return response;
            }

            if((sCommentOk == null || sCommentOk.trim().isEmpty()) && (sCommentFail == null || sCommentFail.trim().isEmpty())){
                response.setHasError(true);
                response.setMessage("Debe elegir si la actividad fue realizada o no y debe escribir un comentario");
                return response;
            }

            if((sCommentOk != null && sCommentOk.trim().isEmpty() == false) && (sCommentFail != null && sCommentFail.trim().isEmpty() == false)){
                response.setHasError(true);
                response.setMessage("No puede escribir comentarios de realizado o no realizado sobre una misma actividad");
                return response;
            }

            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            ActivityMonitoringPlan activityMonitoringPlan = activityMonitoringPlanRepository.findByIdAndUserId(model.getActMonPlanId(), user.getId());

            if(activityMonitoringPlan == null){
                response.setHasError(true);
                response.setMessage("Usted no puede establecer la actividad como realizada o no realizada. Revise que usted sea el asignado para la supervisión del caso");
                return response;
            }

            String status = activityMonitoringPlan.getStatus();
            if(status.equals(MonitoringConstants.STATUS_ACTIVITY_DONE) || status.equals(MonitoringConstants.STATUS_ACTIVITY_FAILED)){
                response.setHasError(true);
                response.setMessage("Ya fue establecido un estatus de realizada o no realizada.");
                return response;
            }

            if(status.equals(MonitoringConstants.STATUS_ACTIVITY_DELETED)){
                response.setHasError(true);
                response.setMessage("La actividad fue eliminada.");
                return response;
            }

            boolean bIsFailed;
            String sComments;
            String sStatus;
            if(sCommentOk.trim().isEmpty() == false){
                sComments = sCommentOk;
                sStatus = MonitoringConstants.STATUS_ACTIVITY_DONE;
                bIsFailed = false;
            }
            else{
                sComments = sCommentFail;
                sStatus = MonitoringConstants.STATUS_ACTIVITY_FAILED;
                bIsFailed = true;
            }

            List<ActivityMonitoringPlanArrangement> lstAssignedArrangement = activityMonitoringPlan.getLstAssignedArrangement();
            boolean bHasValue;

            if(bIsFailed){
                for(ActivityMonitoringPlanArrangement ampa : lstAssignedArrangement){
                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED);
                }
            }
            else{
                for(ActivityMonitoringPlanArrangement ampa : lstAssignedArrangement){
                    Long assignedArrangementId = ampa.getAssignedArrangement().getId();
                    bHasValue = false;
                    for(OptionList optionList: optLst){
                        if(assignedArrangementId == optionList.getId()){
                            switch (Integer.parseInt(optionList.getValue())){
                                case MonitoringConstants.ACTIVITY_ARRANGEMENT_DONE:
                                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_DONE);
                                    break;
                                case MonitoringConstants.ACTIVITY_ARRANGEMENT_FAILED:
                                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_FAILED);
                                    break;
                                case MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED:
                                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED);
                                    break;
                                default:
                                    response.setHasError(true);
                                    response.setMessage("Se ha asignado un valor no válido para el estado de las obligaciones procesales.");
                                    return response;
                            }
                            bHasValue = true;
                            break;
                        }
                    }

                    if(bHasValue == false){
                        response.setHasError(true);
                        response.setMessage("No existe un valor asignado para el estado de las obligaciones procesales.");
                        return response;
                    }
                }
            }


            activityMonitoringPlan.setStatus(sStatus);
            activityMonitoringPlan.setComments(sComments);
            activityMonitoringPlan.setSupervisorDone(user);
            activityMonitoringPlan.setDoneTime(Calendar.getInstance());

            activityMonitoringPlanRepository.save(activityMonitoringPlan);

            response.setReturnData(sStatus);


        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doActionActivity", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }
}
