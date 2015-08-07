package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.supervisorManager.ResponseRolActivities;
import com.umeca.model.dto.supervisorManager.RolActivityRequest;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.RequestActivities;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.managereval.EvaluationActivityRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.RolActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RolSupervisionController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EvaluationActivityRepository evaluationActivityRepository;

    @RequestMapping(value = {"/supervisorManager/rolSupervision/index", "/managereval/rolEvaluation/index"}, method = RequestMethod.GET)
    public @ResponseBody ModelAndView index(){
        ModelAndView model = new ModelAndView("/supervisorManager/rolSupervision/index");
        Gson gson = new Gson();
        if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER)){

            List<SelectList> lstEvaluator = userRepository.getLstValidUsersByRole(Constants.ROLE_REVIEWER);
            String sLstGeneric = gson.toJson(lstEvaluator);
            model.addObject("lstSupervisor", sLstGeneric);
            model.addObject("isEvaluator", true);
            model.addObject("urlGetActivities", "/managereval/rolEvaluation/getActivities.json");
            List<SelectList> lstEvaAct = evaluationActivityRepository.getAllNoObsolete();
            for(SelectList curr : lstEvaAct){
                curr.setIsSelected(false);
            }
            model.addObject("lstEvaAct", gson.toJson(lstEvaAct));
            return model;
        }
        List<SelectList> lstSupervisor = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
        String sLstGeneric = gson.toJson(lstSupervisor);
        model.addObject("isEvaluator", false);
        model.addObject("lstSupervisor", sLstGeneric);
        model.addObject("urlGetActivities", "/supervisorManager/rolSupervision/getActivities.json");
        return model;
    }


    @Autowired
    RolActivityService rolActivityService;

    @RequestMapping(value = {"/supervisorManager/rolSupervision/doUpsert", "/managereval/rolEvaluation/doUpsert"}, method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsert(@RequestBody RolActivityRequest model){
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Rol de supervisión");

        try {
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(rolActivityService.doUpsertDelete(model, user, response) == false)
                return response;

            if(model.getActsIns() == 0 && model.getActsUpd() == 0 && model.getActsDel() == 0){
                response.setMessage("No fue posible realizar la operación, revise que su información esté correcta o reinicie su navegador.");
                response.setHasError(true);
            }else{
                Gson gson = new Gson();
                response.setReturnData(gson.toJson(model.getLstActivitiesUpserted()));
                response.setHasError(false);
                response.setMessage("La operación se realizó de forma correcta." +
                        (model.getActsIns() == 0 ? "" : ("<br/>" + model.getActsIns() + " actividad(es) fue(ron) insertada(s)")) +
                        (model.getActsUpd() == 0 ? "" : ("<br/>" + model.getActsUpd() + " actividad(es) fue(ron) actualizada(s)")) +
                        (model.getActsDel() == 0 ? "" : ("<br/>" + model.getActsDel() + " actividad(es) fue(ron) eliminada(s)")));
            }

            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsert", sharedUserService);
            response.setHasError(true);
        }
        response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        return response;
    }


    @RequestMapping(value = {"/supervisorManager/rolSupervision/getActivities", "/managereval/rolEvaluation/getActivities"}, method = RequestMethod.POST)
    public @ResponseBody ResponseRolActivities getActivities(@RequestBody RequestActivities req){
        ResponseRolActivities response = new ResponseRolActivities();
        response.setHasError(true);

        try{
            rolActivityService.getLstActivities(req, MonitoringConstants.STATUS_ACTIVITY_DELETED, response);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "getActivities", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se sucitó un problema, por favor reinicie su navegador e intente de nuevo.");
            return response;
        }
        return response;
    }
}



