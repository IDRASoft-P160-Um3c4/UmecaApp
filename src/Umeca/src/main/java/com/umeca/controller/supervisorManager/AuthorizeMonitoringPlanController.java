package com.umeca.controller.supervisorManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorizeMonitoringPlanController {
    @RequestMapping(value = "/supervisorManager/authorizeMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisorManager/authorizeMonitoringPlan/index";
    }
}
