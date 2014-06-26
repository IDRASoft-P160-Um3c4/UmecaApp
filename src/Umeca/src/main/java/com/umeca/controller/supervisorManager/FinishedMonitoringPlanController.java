package com.umeca.controller.supervisorManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FinishedMonitoringPlanController {

    @RequestMapping(value = "/supervisorManager/finishedMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisorManager/finishedMonitoringPlan/index";
    }

}
