package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
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
import com.umeca.model.entities.supervisorManager.AuthRejMonActivitiesRequest;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.entities.supervisorManager.ChangeSupervisor;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ManageMonitoringPlanService;
import com.umeca.service.supervisor.TrackMonPlanService;
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
public class ActiveMonitoringPlanController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;


    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisorManager/activeMonitoringPlan/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_END);
                }}, JqGridFilterModel.COMPARE_NOT_IN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<MonitoringPlan, Case> joinCd = r.join("caseDetention");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinIm = joinCd.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>() {{
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
                    add(r.get("statusLog"));
                    add(r.get("posAuthorizationChangeTime"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if (field.equals("idMP"))
                    return r.join("caseDetention").get("idMP");
                if (field.equals("stCreationTime"))
                    return r.get("creationTime");
                if (field.equals("stGenerationTime"))
                    return r.get("generationTime");
                if (field.equals("stAuthorizationTime"))
                    return r.get("authorizationTime");
                if (field.equals("supervisor"))
                    return r.join("supervisor").get("username");
                return null;
            }
        }, MonitoringPlan.class, MonitoringPlanView.class);

        return result;

    }

    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/authorizeEnd", method = RequestMethod.POST)
    public ModelAndView authorizeEnd(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/authorizeMonitoringPlan/authorizeRejectMonPlan");

        try {
            GetMonPlanInfo(id, model, monitoringPlanRepository);
            model.addObject("isAuthorized", 1);
            //model.addObject("isEnd", 1);
            model.addObject("msgPlan", "finalización del plan de seguimiento");
            model.addObject("urlToGo", "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectEndMonPlan.json");
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "authorizeEnd", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/rejectEnd", method = RequestMethod.POST)
    public ModelAndView rejectEnd(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/authorizeMonitoringPlan/authorizeRejectMonPlan");

        try {
            GetMonPlanInfo(id, model, monitoringPlanRepository);
            //model.addObject("isEnd", 1);
            model.addObject("isAuthorized", 0);
            model.addObject("msgPlan", "finalización del plan de seguimiento");
            model.addObject("urlToGo", "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectEndMonPlan.json");
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "rejectEnd", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/authorizeAccomplishment", method = RequestMethod.POST)
    public ModelAndView authorizeAccomplishment(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/authorizeMonitoringPlan/authorizeRejectMonPlan");

        try {
            GetMonPlanInfo(id, model, monitoringPlanRepository);
            model.addObject("isAuthorized", 1);
            model.addObject("msgPlan", "reporte de incumplimiento");
            model.addObject("urlToGo", "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectAccomplishment.json");
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "authorizeAccomplishment", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/rejectAccomplishment", method = RequestMethod.POST)
    public ModelAndView rejectAccomplishment(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/authorizeMonitoringPlan/authorizeRejectMonPlan");

        try {
            GetMonPlanInfo(id, model, monitoringPlanRepository);
            model.addObject("isAuthorized", 0);
            model.addObject("msgPlan", "reporte de incumplimiento");
            model.addObject("urlToGo", "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectAccomplishment.json");
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "rejectAccomplishment", sharedUserService);
            return null;
        }
    }

    public static void GetMonPlanInfo(Long id, ModelAndView model, MonitoringPlanRepository monitoringPlanRepository) {
        MonitoringPlanInfo monPlanInfo = monitoringPlanRepository.getInfoById(id);
        model.addObject("monPlanId", id);
        model.addObject("caseId", monPlanInfo.getIdCase());
        model.addObject("mpId", monPlanInfo.getIdMP());
        model.addObject("fullName", monPlanInfo.getPersonName());
        model.addObject("status", monPlanInfo.getMonStatus());
    }

    @Autowired
    TrackMonPlanService trackMonPlanService;

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectAccomplishment", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage doAuthorizeRejectAccomplishment(@ModelAttribute AuthorizeRejectMonPlan model) {

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try {
            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            if (sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false) {
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            MonitoringPlan monPlan = monitoringPlanRepository.findOne(model.getMonPlanId());

            if (monPlan == null) {
                response.setMessage("No se encontró el plan de seguimiento. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            if (monPlan.getStatus().equals(MonitoringConstants.STATUS_END) == true) {
                response.setMessage("El plan de supervisión se encuentra en estado " + monPlan.getStatus() + ", por ello no se puede realizar esta acción");
                return response;
            }

            trackMonPlanService.saveAuthRejectAccomplishment(model, user, monPlan, MonitoringConstants.TYPE_COMMENT_LOG_ACCOMPLISHMENT);

            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doAuthorizeRejectMonPlan", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectEndMonPlan", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doAuthorizeRejectEndMonPlan(@ModelAttribute AuthorizeRejectMonPlan model) {

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try {
            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            if (sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false) {
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            MonitoringPlan monPlan = monitoringPlanRepository.findOne(model.getMonPlanId());

            if (monPlan == null) {
                response.setMessage("No se encontró el plan de seguimiento. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            if (monPlan.getStatus().equals(MonitoringConstants.STATUS_PENDING_END) == false) {
                response.setMessage("El plan de supervisión se encuentra en estado " + monPlan.getStatus() + ", por ello no puede ser autorizado");
                return response;
            }

            trackMonPlanService.saveAuthRejectMonPlan(model, user, monPlan, MonitoringConstants.STATUS_END,
                    MonitoringConstants.STATUS_REJECTED_END, MonitoringConstants.TYPE_COMMENT_MONITORING_PLAN_END);

            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doAuthorizeRejectEndMonPlan", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/changeSupervisor", method = RequestMethod.POST)
    public ModelAndView changeSupervisor(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/activeMonitoringPlan/changeSupervisor");

        try {
            MonitoringPlanInfo monPlanInfo = monitoringPlanRepository.getInfoById(id);
            model.addObject("monPlanId", id);
            model.addObject("caseId", monPlanInfo.getIdCase());
            model.addObject("mpId", monPlanInfo.getIdMP());
            model.addObject("fullName", monPlanInfo.getPersonName());
            model.addObject("status", monPlanInfo.getMonStatus());

            List<SelectList> lstUsers = sharedUserService.getLstValidUsersByRoleExceptUserId(Constants.ROLE_SUPERVISOR, monPlanInfo.getSupervisorId());
            Gson gson = new Gson();
            String sLstUsers = gson.toJson(lstUsers);
            model.addObject("lstUsers", sLstUsers);
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "changeSupervisor", sharedUserService);
            return null;
        }
    }

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/doChangeSupervisor", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doChangeSupervisor(@ModelAttribute ChangeSupervisor model) {

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try {
            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            if (sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false) {
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            if (sharedUserService.isUserInRole(model.getSupervisorId(), Constants.ROLE_SUPERVISOR) == false) {
                response.setMessage("El usuario al quien desea asignar el caso no tiene el perfil adecuado. Intente con otro usuario");
                return response;
            }

            MonitoringPlan monPlan = monitoringPlanRepository.findOne(model.getMonPlanId());

            if (monPlan == null) {
                response.setMessage("No se encontró el plan de seguimiento. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            if (monPlan.getStatus().equals(MonitoringConstants.STATUS_END) == true) {
                response.setMessage("El plan de supervisión se encuentra en estado " + monPlan.getStatus() + ", por ello ya no es posible cambiar de supervisor");
                return response;
            }

            trackMonPlanService.saveChangeSupervisorMonPlan(model, user, monPlan);
            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doChangeSupervisor", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }

    @Autowired
    private ActivityMonitoringPlanRepository activityMonitoringPlanRepository;

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/authRejActMonPlan", method = RequestMethod.POST)
    public ModelAndView authRejActMonPlan(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/activeMonitoringPlan/authRejActMonPlan");

        try {
            MonitoringPlanInfo monPlan = monitoringPlanRepository.getInfoById(id);

            model.addObject("caseId", monPlan.getIdCase());
            model.addObject("mpId", monPlan.getIdMP());
            model.addObject("fullName", monPlan.getPersonName());
            model.addObject("status", monPlan.getMonStatus());

            List<ActivityMonitoringPlanNotice> lstActivities = activityMonitoringPlanRepository.getAllActivitiesByMonPlanIdInStatus(id,
                    new ArrayList<String>(){{add(MonitoringConstants.STATUS_ACTIVITY_PRE_NEW);
                        add(MonitoringConstants.STATUS_ACTIVITY_PRE_MODIFIED);add(MonitoringConstants.STATUS_ACTIVITY_PRE_DELETED); }});

            Gson gson = new Gson();
            String sLstGeneric = gson.toJson(lstActivities);
            model.addObject("lstActivities", sLstGeneric);

            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "authRejActMonPlan", sharedUserService);
            return null;
        }
    }

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/showMonActDetail", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage showMonActDetail(@RequestBody Long id){
        ResponseMessage response = new ResponseMessage();
        try{
            trackMonPlanService.getActivityToShow(id, response);
            response.setHasError(false);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "showMonActDetail", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }

    @Autowired
    ManageMonitoringPlanService manageMonitoringPlanService;

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/authRejLstMonAct", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage authRejLstMonAct(@RequestBody AuthRejMonActivitiesRequest model){
        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);
        try{
            //Validar contraseña y validar los comentarios
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(sharedUserService.isValidPasswordForUser(user.getId(), model.getPs()) == false){
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            if(model.getComments() == null || model.getComments().trim().isEmpty()){
                response.setMessage("Debe ingresar un comentario para continuar");
                return response;
            }

            if(model.getLstAutRejActMon() == null || model.getLstAutRejActMon().size() <= 0){
                response.setMessage("Debe tener al menos una actividad para autorizar o rechazar");
                return response;
            }

            if(manageMonitoringPlanService.authRejLstMonAct(model, sharedUserService, response) == false)
                return response;

            response.setHasError(false);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "authRejLstMonAct", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }
}
