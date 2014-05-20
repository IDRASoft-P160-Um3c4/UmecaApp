package com.umeca.controller.shared;

import com.umeca.model.Catalog.Questionary;
import com.umeca.model.Catalog.QuestionarySection;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.repository.shared.QuestionaryRepository;
import com.umeca.service.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.swing.SwingUtilities2;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap map){
        return "index";
    }

    @RequestMapping(value = "loginAccount", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage login(@RequestBody User user){

        return service.authenticate(user);
    }

}
