package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.CloseCause;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanInfo;
import com.umeca.model.entities.supervisor.MonitoringPlanView;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.CloseCauseRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ManageMonitoringPlanService;
import com.umeca.service.supervisor.TrackMonPlanService;
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


@Controller
public class ManageMonitoringPlanController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;


    @RequestMapping(value = "/supervisor/manageMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisor/manageMonitoringPlan/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private SharedUserService userService;

    @RequestMapping(value = "/supervisor/manageMonitoringPlan/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>(){{add(MonitoringConstants.STATUS_NEW);add(MonitoringConstants.STATUS_PENDING_CREATION);
                    add(MonitoringConstants.STATUS_PENDING_END);add(MonitoringConstants.STATUS_END);}},JqGridFilterModel.COMPARE_NOT_IN);
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
    ManageMonitoringPlanService manageMonitoringPlanService;

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/preAuthorize", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage preAuthorize(@RequestParam Long id){ //Id de MonitoringPlan
        ResponseMessage response = new ResponseMessage();

        try{
            response.setTitle("Plan de seguimiento");

            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(manageMonitoringPlanService.preAuthorize(sharedUserService, id, user, response) == false){
                response.setHasError(true);
                return response;
            }

            response.setHasError(false);
            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "preAuthorize", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se present&oacute; un error inesperado. Por favor revise que la informaci&oacute;n e intente de nuevo");
            return response;
        }
    }

    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    CloseCauseRepository closeCauseRepository;

    @RequestMapping(value = "/supervisor/manageMonitoringPlan/reqEndPlan", method = RequestMethod.POST)
    public ModelAndView reqEndPlan(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/supervisor/manageMonitoringPlan/reqEndPlan");

        try{
            MonitoringPlanInfo monPlanInfo = monitoringPlanRepository.getInfoById(id);

            Long countMiss = activityMonitoringPlanRepository.countActivitiesByNotInLstStatus(id, new ArrayList<String>() {{
                add(MonitoringConstants.STATUS_ACTIVITY_FAILED);
                add(MonitoringConstants.STATUS_ACTIVITY_DELETED);
                add(MonitoringConstants.STATUS_ACTIVITY_DONE);
            }});

            model.addObject("monPlanId", id);
            model.addObject("caseId", monPlanInfo.getIdCase());
            model.addObject("mpId", StringEscape.escapeText(monPlanInfo.getIdMP()));
            model.addObject("fullName", StringEscape.escapeText(monPlanInfo.getPersonName()));
            model.addObject("status", monPlanInfo.getMonStatus());
            model.addObject("countMiss", countMiss);

            List<SelectList> lst = closeCauseRepository.findNoObsolete();

            model.addObject("lstCloseCause",new Gson().toJson(lst));

            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "reqEndPlan", sharedUserService);
            return null;
        }
    }

    @Autowired
    TrackMonPlanService trackMonPlanService;

    @RequestMapping(value = "/supervisor/manageMonitoringPlan/doReqEndPlan", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doReqEndPlan(@ModelAttribute AuthorizeRejectMonPlan model){

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try{
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false){
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            MonitoringPlan monPlan = monitoringPlanRepository.findOne(model.getMonPlanId());

            if(monPlan == null){
                response.setMessage("No se encontró el plan de seguimiento. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            if(monPlan.getStatus().equals(MonitoringConstants.STATUS_END) == true){
                response.setMessage("El plan de supervisión se encuentra en estado " + monPlan.getStatus() + ", por ello no puede ser terminado");
                return response;
            }

            if(MonitoringPlanView.calculateIsMonPlanSuspended(monPlan.getGenerationTime(), monPlan.getAuthorizationTime(), monPlan.getPosAuthorizationChangeTime())){
                response.setMessage("El plan de supervisión se encuentra suspendido, por ello no puede ser terminado. Favor de contactar al supervisor");
                return response;
            }

            trackMonPlanService.saveReqEndMonPlan(model, user, monPlan);

            response.setHasError(false);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doAuthorizeRejectMonPlan", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }

    @Autowired
    LogCommentRepository logCommentMonPlanRepository;

    @RequestMapping(value = "/supervisor/manageMonitoringPlan/showRejectAuthMsg", method = RequestMethod.POST)
    public @ResponseBody ModelAndView showRejectAuthMsg(@RequestParam Long id){ //Id de MonitoringPlan
        ModelAndView model = new ModelAndView("/supervisor/shared/showMsg");
        try {

            List<String> lstComment = logCommentMonPlanRepository.getLastCommentByMonPlanIdAndType(id, MonitoringConstants.TYPE_COMMENT_AUTHORIZED, new PageRequest(0, 1));

            model.addObject("type", "warning");
            model.addObject("title", "Comentarios del rechazo");
            model.addObject("subtitle", "Su plan de seguimiento fue rechazado debido a:");
            model.addObject("body", lstComment.get(0));

        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "showRejectAuthMsg", sharedUserService);
            model.addObject("type", "warning");
            model.addObject("title", "Comentarios del rechazo");
            model.addObject("subtitle", "Su plan de seguimiento fue rechazado debido a:");
            model.addObject("body", "Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return model;
    }

    @RequestMapping(value = "/supervisor/manageMonitoringPlan/showRejectAuthEndMsg", method = RequestMethod.POST)
    public @ResponseBody ModelAndView showRejectAuthEndMsg(@RequestParam Long id){ //Id de MonitoringPlan
        ModelAndView model = new ModelAndView("/supervisor/shared/showMsg");
        try {

            List<String> lstComment = logCommentMonPlanRepository.getLastCommentByMonPlanIdAndType(id, MonitoringConstants.TYPE_COMMENT_MONITORING_PLAN_END, new PageRequest(0, 1));

            model.addObject("type", "warning");
            model.addObject("title", "Comentarios del rechazo de la finalización");
            model.addObject("subtitle", "La finalización del plan de seguimiento fue rechazado debido a:");
            model.addObject("body", lstComment.get(0));

        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "showRejectAuthMsg", sharedUserService);
            model.addObject("type", "warning");
            model.addObject("title", "Comentarios del rechazo de la finalización");
            model.addObject("subtitle", "La finalización del plan de seguimiento fue rechazado debido a:");
            model.addObject("body", "Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return model;
    }

    @RequestMapping(value = "/supervisor/manageMonitoringPlan/showRejectAuthAccomplishmentMsg", method = RequestMethod.POST)
    public @ResponseBody ModelAndView showRejectAuthAccomplishmentMsg(@RequestParam Long id){ //Id de MonitoringPlan
        ModelAndView model = new ModelAndView("/supervisor/shared/showMsg");
        try {

            List<String> lstComment = logCommentMonPlanRepository.getLastCommentByMonPlanIdAndType(id, MonitoringConstants.TYPE_COMMENT_LOG_ACCOMPLISHMENT, new PageRequest(0, 1));

            model.addObject("type", "warning");
            model.addObject("title", "Comentarios del rechazo del reporte de incumplimiento");
            model.addObject("subtitle", "El reporte de incumplimiento fue rechazado debido a:");
            model.addObject("body", lstComment.get(0));

        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "showRejectAuthMsg", sharedUserService);
            model.addObject("type", "warning");
            model.addObject("title", "Comentarios del rechazo del reporte de incumplimiento");
            model.addObject("subtitle", "El reporte de incumplimiento fue rechazado debido a:");
            model.addObject("body", "Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return model;
    }
}
