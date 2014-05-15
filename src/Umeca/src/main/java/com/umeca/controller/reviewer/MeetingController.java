package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.operation.JqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.Role;
import com.umeca.repository.account.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeetingController {

    @RequestMapping(value = "/reviewer/meeting/index", method = RequestMethod.GET)
    public String index(){
        return "/reviewer/meeting/index";
    }

    @RequestMapping(value = "/reviewer/meeting/newMeeting", method = RequestMethod.POST)
    public String newMeeting(){
        return "/reviewer/meeting/newMeeting";
    }

    @RequestMapping(value = "/reviewer/meeting/doNewMeeting", method = RequestMethod.POST)
    public ResponseMessage doNewMeeting(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>en do new meeting");
        ResponseMessage result = new ResponseMessage();
        result.setHasError(false);
        result.setMessage("redireccion");
        result.setUrlToGo("/reviewer/meeting/meeting");
        return result;
    }

    @RequestMapping(value = "/reviewer/meeting/meeting", method = RequestMethod.GET)
    public String meeting(){
        return "/reviewer/meeting/meeting";
    }

    @RequestMapping(value = "/reviewer/meeting/address/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/meeting/address/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/upsert", method = RequestMethod.POST)
    public ModelAndView upsertSocialNetwork(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/meeting/socialNetwork/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @RequestMapping(value = "/reviewer/meeting/drug/upsert", method = RequestMethod.POST)
    public ModelAndView upsertDrug(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/meeting/drug/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @RequestMapping(value = "/reviewer/meeting/job/upsert", method = RequestMethod.POST)
    public ModelAndView upsertJob(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/meeting/job/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @RequestMapping(value = "/reviewer/meeting/reference/upsert", method = RequestMethod.POST)
    public ModelAndView upsertReference(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/meeting/reference/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @RequestMapping(value = "/reviewer/meeting/school/upsert", method = RequestMethod.POST)
    public ModelAndView upsertSchool(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/meeting/school/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }
}
