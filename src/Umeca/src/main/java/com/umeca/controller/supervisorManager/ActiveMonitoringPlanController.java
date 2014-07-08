package com.umeca.controller.supervisorManager;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanInfo;
import com.umeca.model.entities.supervisor.MonitoringPlanView;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
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
    public String index(){
        return "/supervisorManager/activeMonitoringPlan/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private SharedUserService userService;

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>(){{add(MonitoringConstants.STATUS_END);}},JqGridFilterModel.COMPARE_NOT_IN);
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
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if(field.equals("idMP"))
                    return r.join("caseDetention").get("idMP");
                if(field.equals("stCreationTime"))
                    return r.get("creationTime");
                if(field.equals("stGenerationTime"))
                    return r.get("generationTime");
                if(field.equals("stAuthorizationTime"))
                    return r.get("authorizationTime");
                if(field.equals("supervisor"))
                    return r.join("supervisor").get("username");
                return null;
            }
        }, MonitoringPlan.class, MonitoringPlanView.class);

        return result;

    }

    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/authorizeEnd", method = RequestMethod.POST)
    public ModelAndView authorizeEnd(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/supervisorManager/authorizeMonitoringPlan/authorizeRejectMonPlan");

        try{
            GetMonPlanInfo(id, model, monitoringPlanRepository);
            model.addObject("isAuthorized", 1);
            model.addObject("isEnd", 1);
            model.addObject("msgPlan", "finalización del plan de seguimiento");
            model.addObject("urlToGo", "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectMonPlan.json");
            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "authorizeEnd", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/rejectEnd", method = RequestMethod.POST)
    public ModelAndView rejectEnd(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/supervisorManager/authorizeMonitoringPlan/authorizeRejectMonPlan");

        try{
            GetMonPlanInfo(id, model, monitoringPlanRepository);
            model.addObject("isEnd", 1);
            model.addObject("isAuthorized", 0);
            model.addObject("msgPlan", "finalización del plan de seguimiento");
            model.addObject("urlToGo", "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectMonPlan.json");
            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "rejectEnd", sharedUserService);
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

    @RequestMapping(value = "/supervisorManager/activeMonitoringPlan/doAuthorizeRejectMonPlan", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage doAuthorizeRejectMonPlan(@ModelAttribute AuthorizeRejectMonPlan model){

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

            if(monPlan.getStatus().equals(MonitoringConstants.STATUS_PENDING_END) == false){
                response.setMessage("El plan de supervisión se encuentra en estado " + monPlan.getStatus() + ", por ello no puede ser autorizado");
                return response;
            }

            trackMonPlanService.saveAuthRejectMonPlan(model, user, monPlan, MonitoringConstants.STATUS_END,
                    MonitoringConstants.STATUS_REJECTED_END, MonitoringConstants.TYPE_COMMENT_END);

            response.setHasError(false);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doAuthorizeRejectMonPlan", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }
}
