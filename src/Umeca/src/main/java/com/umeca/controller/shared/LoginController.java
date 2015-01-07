package com.umeca.controller.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.shared.Constants;
import com.umeca.service.account.LoginService;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MainPageService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    MainPageService mainPageService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public @ResponseBody ModelAndView index(ModelMap map){
        ModelAndView model = new ModelAndView("/index");

        try{
            Long userId = sharedUserService.GetLoggedUserId();

            if(userId == null || userId < 1)
                return mainPageService.generatePage(Constants.ROLE_ANONYMOUS, model, userId);

            List<String> lstRoles = sharedUserService.getLstRolesByUserId(userId);

            if(lstRoles == null || lstRoles.size() < 1)
                return mainPageService.generatePage(Constants.ROLE_ANONYMOUS, model, userId);


            return mainPageService.generatePage(lstRoles.get(0), model, userId);

        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "index", sharedUserService);
            return model;
        }
    }



    @RequestMapping(value = "loginAccount", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage login(@RequestBody User user){

        return service.authenticate(user);
    }

     @RequestMapping(value = "/session/checkout", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage sessionCheckout(){
       if(sharedUserService.GetLoggedUserId() == null){
           return new ResponseMessage(true, "Session cerrada");
       }else{
           return new ResponseMessage(false, "Session reiniciada");
       }

    }

}
