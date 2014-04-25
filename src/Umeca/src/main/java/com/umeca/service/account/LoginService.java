package com.umeca.service.account;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.User;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Israel
 * Date: 4/24/14
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */


@Service("loginService")
public class LoginService implements ILoginService {
    @Override
    public ResponseMessage authenticate(User user) {
        ResponseMessage respMsg = new ResponseMessage();
        respMsg.setHasError(true);
        respMsg.setMessage("Prueba de ingreso para el usuario: " + user.getUsername() + " - " + user.getPassword());
        return respMsg;
    }
}
