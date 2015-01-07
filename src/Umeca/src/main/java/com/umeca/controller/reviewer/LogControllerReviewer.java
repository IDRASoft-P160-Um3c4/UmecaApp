package com.umeca.controller.reviewer;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MainPageService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LogControllerReviewer {


    @Autowired
    SharedUserService userService;
    @Autowired
    MainPageService mainPageService;
    @Autowired
    SharedLogExceptionService logException;


    @RequestMapping(value = "/reviewer/log/deleteNotification", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseMessage deleteNotification(@RequestParam Long id) {
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

            mainPageService.doDeleteNotificationReviewer(id);

            response.setReturnData(id);
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