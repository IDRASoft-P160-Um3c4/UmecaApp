package com.umeca.service.account;

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

    public Integer calculateAge(Date birthDate){
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        Integer age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
            age--;
        return age;
    }
}
