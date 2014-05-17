package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.umeca.model.ResponseMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/05/14
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class VerificationController {

    @RequestMapping(value = "/reviewer/verification/index", method = RequestMethod.GET)
    public String index(){
        return "/reviewer/verification/index";
    }

    @RequestMapping(value = "/reviewer/verification/sources", method = RequestMethod.GET)
    public String sources(){
        return "/reviewer/verification/sources";
    }

    @RequestMapping(value = "/reviewer/verification/verificationBySource", method = RequestMethod.GET)
    public String verificationBySource(){
        return "/reviewer/verification/verificationBySource";
    }

    @RequestMapping(value = "/reviewer/verification/detailVerification", method = RequestMethod.POST)
    public ModelAndView detailVerification(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/verification/detailVerification");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @RequestMapping(value = "/reviewer/verification/checkSource", method = RequestMethod.GET)
    public String checkSource(){
        return "/reviewer/verification/checkSource";
    }

    @RequestMapping(value = "/reviewer/verification/detailSource", method = RequestMethod.POST)
    public ModelAndView detailSource(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/verification/detailSource");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }
}
