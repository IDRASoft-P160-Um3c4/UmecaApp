package com.umeca.controller.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MainPageService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogControllerReviewer {


    @Autowired
    SharedUserService userService;
    @Autowired
    MainPageService mainPageService;
    @Autowired
    SharedLogExceptionService logException;


    @RequestMapping(value = "/reviewer/log/deleteNotification", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage deleteNotification(@RequestBody Long idNotif) {
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Eliminar notificación");
        response.setHasError(true);

        try {
            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            List<String> lstRole = userService.getLstRolesByUserId(user.getId());

            if (lstRole == null || lstRole.size() == 0) {
                response.setMessage("Usted no tiene los permisos para realizar esta operación");
                return response;
            }

            mainPageService.doDeleteNotificationReviewer(idNotif);

            response.setReturnData(idNotif);
            response.setHasError(false);
            return response;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteNotification", userService);
            response.setHasError(true);
        }
        response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        return response;
    }

}