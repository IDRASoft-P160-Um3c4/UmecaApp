package com.umeca.service.account;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: Israel
 * Date: 4/24/14
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ILoginService {
    ResponseMessage authenticate(User user);
}
