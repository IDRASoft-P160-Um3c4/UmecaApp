package com.umeca.controller;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.User;
import com.umeca.service.account.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Israel
 * Date: 4/24/14
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class LoginController {

    @Autowired
    private ILoginService service;

    @RequestMapping(value = "loginAccount", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage login(@RequestBody User user){
        return service.authenticate(user);
    }

}
