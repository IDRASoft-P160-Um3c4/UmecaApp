package com.umeca.service.account;

import com.umeca.infrastructure.security.BcryptUtil;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.CryptoRfc2898;
import com.umeca.model.entities.account.User;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public Long GetLoggedUserId() {
        try {
            String sUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            Long idUser = userRepository.findIdByUsername(sUsername);
            return idUser;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return -1L;
    }


    public String GetLoggedUsername() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception ex) {
            return "@NA";
        }
    }

    public Boolean isEnabled(Long userId) {
        return userRepository.isEnabled(userId);
    }

    public boolean isValidUser(User user, ResponseMessage response) {
        String sUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User userToValidate = userRepository.getInfoToValidate(sUsername);

        if (userToValidate.getEnabled() == false) {
            response.setMessage("Usted no tiene permisos para realizar esta acci&oacute;n. Por favor solicite los permisos suficientes para realizar esta acci&oacute;n e intente de nuevo.");
            response.setHasError(true);
            return false;
        }

        user.setUsername(sUsername);
        user.setId(userToValidate.getId());
        user.setEnabled(userToValidate.getEnabled());

        return true;
    }

    public Integer calculateAge(Date birthDate) {
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
        //return BcryptUtil.match(password, encodePassword);
        CryptoRfc2898 rfc2898 = new CryptoRfc2898();
        return rfc2898.matches(password.subSequence(0, password.length()), encodePassword);
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

    public List<String> getLstRolesByUserId(Long userId) {
        return userRepository.getLstRolesByUserId(userId);
    }

    public String getFoneticByName(String name, String lastNameP, String lastNameM) {
        return name.trim().toLowerCase() + lastNameP.trim().toLowerCase() + lastNameM.trim().toLowerCase();
    }

    public String getCodedPassByUsername(String usrname) {
        return userRepository.getCodedPassByUsername(usrname);
    }

    public SelectList getIdAndFullnameByUsername(String usrname) {
        return userRepository.getIdAndFullnameByUsername(usrname);
    }

    public String getRoleByUsername(String usrname) {
        List<String> lst = userRepository.getRoleByUsername(usrname, new PageRequest(0, 1));
        if (lst != null & lst.size() > 0)
            return lst.get(0);
        else
            return null;
    }

}
