package com.umeca.controller.supervisor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 12:03 PM
 */

@Controller
public class GenerateMonitoringPlan {

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisor/generateMonitoringPlan/index";
    }



}
