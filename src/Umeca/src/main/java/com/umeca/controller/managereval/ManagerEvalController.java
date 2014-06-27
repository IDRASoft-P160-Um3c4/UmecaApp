package com.umeca.controller.managereval;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dcortesr on 25/06/14.
 */
@Controller
public class ManagerEvalController {

    @RequestMapping(value = "/managereval/index", method = RequestMethod.GET)
    public String index(){
        return "/managereval/index";
    }
}
