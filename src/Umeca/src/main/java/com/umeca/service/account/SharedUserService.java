package com.umeca.service.account;

import com.umeca.infrastructure.security.BcryptUtil;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/4/14
 * Time: 5:53 PM
 */

@Service
public class SharedUserService {

    @Autowired
    private UserRepository userRepository;

    public Long GetLoggedUserId(){
        try {
            String sUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            Long idUser = userRepository.findIdByUsername(sUsername);
            return idUser;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return -1L;
    }


    public String GetLoggedUsername(){
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }catch(Exception ex){
            return "@NA";
        }
    }

    public Boolean isEnabled(Long userId){
        return userRepository.isEnabled(userId);
    }

    public boolean isValidUser(User user, ResponseMessage response) {
        String sUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User userToValidate = userRepository.getInfoToValidate(sUsername);

        if(userToValidate.getEnabled() == false){
            response.setMessage("Usted no tiene permisos para realizar esta acción. Por favor solicite los permisos suficientes para realizar esta acción e intente de nuevo.");
            response.setHasError(true);
            return false;
        }

        user.setUsername(sUsername);
        user.setId(userToValidate.getId());
        user.setEnabled(userToValidate.getEnabled());

        return true;
    }

    public Integer calculateAge(Date birthDate){
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        Integer age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
            age--;
        return age;
    }

    public boolean isValidPasswordForUser(Long userId, String password) {
        String encodePassword = userRepository.getEncodedPassword(userId);
        return BcryptUtil.match(password, encodePassword);
    }
}
