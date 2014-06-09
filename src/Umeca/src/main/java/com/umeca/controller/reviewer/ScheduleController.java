package com.umeca.controller.reviewer;

import com.umeca.service.reviewer.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 2/06/14
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ScheduleController {
          @Autowired
    ScheduleService scheduleService;
    @RequestMapping(value = "/reviewer/meeting/shared/shedule", method = RequestMethod.GET)
    public String index(){
        return "/reviewer/meeting/shared/schedule";
    }
}
