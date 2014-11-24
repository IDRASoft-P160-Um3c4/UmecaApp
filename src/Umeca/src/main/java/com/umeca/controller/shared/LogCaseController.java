package com.umeca.controller.shared;

import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 20/11/14
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LogCaseController {
    @Autowired
    SharedUserService userService;

    @RequestMapping(value = "/shared/logCase/index", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView index(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/shared/uploadFile/index");
        return model;
    }


}
