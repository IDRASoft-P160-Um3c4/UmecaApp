package com.umeca.service.account;

import com.umeca.infrastructure.security.BcryptUtil;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private List<String> lstRolesByUserId;

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
            response.setMessage("Usted no tiene permisos para realizar esta acci&oacute;n. Por favor solicite los permisos suficientes para realizar esta acci&oacute;n e intente de nuevo.");
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

    public List<SelectList> getLstValidUsersByRole(String sRole) {
        return userRepository.getLstValidUsersByRole(sRole);
    }

    public List<User> getLstValidUserIdsByRole(String sRole) {
        return userRepository.getLstValidUserIdsByRole(sRole);
    }

    public List<SelectList> getLstValidUsersByRoleExceptUserId(String sRole, Long supervisorId) {
        return userRepository.getLstValidUsersByRoleExceptUserId(supervisorId, sRole);
    }

    public boolean isUserInRole(Long supervisorId, String sRole) {
        return (userRepository.isUserInRole(supervisorId, sRole) > 0);

    }
    public boolean isUserInRoles(Long supervisorId, List<String> lstRole) {
        return (userRepository.isUserInRoles(supervisorId, lstRole) > 0);

    }

    public static String convertToValidString(String s){
        s = s.replace("�","&aacute;");
        s = s.replace("�","&eacute;");
        s = s.replace("�","&iacute;");
        s = s.replace("�","&oacute;");
        s = s.replace("�","&uacute;");
        s = s.replace("�","&Aacute;");
        s = s.replace("�","&Eacute;");
        s = s.replace("�","&Iacute;");
        s = s.replace("�","&Oacute;");
        s = s.replace("�","&Uacute;");
        s = s.replace("�","&ntilde;");
        s = s.replace("�","&Ntilde;");
        s = s.replace("�","&iquest;");
        return s;
    }

    public List<String> getLstRolesByUserId(Long userId) {
        return userRepository.getLstRolesByUserId(userId);
    }

    public String getFoneticByName(String name, String lastNameP, String lastNameM) {
        return name.trim().toLowerCase()+lastNameP.trim().toLowerCase()+lastNameM.trim().toLowerCase();
    }
}
