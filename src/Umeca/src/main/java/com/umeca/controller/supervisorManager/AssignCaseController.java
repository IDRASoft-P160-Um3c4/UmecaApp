package com.umeca.controller.supervisorManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.umeca.infrastructure.jqgrid.model.*;

/**
 * Created by dcortesr on 16/07/14.
 */
@Controller
public class AssignCaseController {

    @RequestMapping(value = {"/supervisorManager/assignCase/index"}, method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "/supervisorManager/assignCase/index";
    }

    @RequestMapping(value = {"/supervisorManager/assignCase/list"}, method = RequestMethod.POST)
    @ResponseBody
    public JqGridResultModel list(){
        return null;
    }
}
