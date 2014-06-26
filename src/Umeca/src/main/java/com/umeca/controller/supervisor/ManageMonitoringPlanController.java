package com.umeca.controller.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ManageMonitoringPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 12:03 PM
 */

@Controller
public class ManageMonitoringPlanController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;


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

            if(manageMonitoringPlanService.preAuthorize(id, user, response) == false){
                response.setHasError(true);
                return response;
            }

            response.setHasError(false);
            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "preAuthorize", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
            return response;
        }

    }
}
