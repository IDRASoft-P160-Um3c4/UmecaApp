package com.umeca.service.account;

import com.google.gson.Gson;
import com.umeca.infrastructure.security.BcryptUtil;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.CryptoRfc2898;
import com.umeca.model.dto.tablet.TabletUserDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        CryptoRfc2898 cryptoRfc2898 = new CryptoRfc2898();
        return cryptoRfc2898.matches(password.subSequence(0, password.length()), encodePassword);
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


    public List<SelectList> getUserRoles(String employeeName, String username, Long employeeId) {

        List<SelectList> lstUsr = new ArrayList<>();
        if (username != null)
            lstUsr = userRepository.getUserRolesByUsername(username, new PageRequest(0, 20));
        else if (employeeName != null)
            lstUsr = userRepository.getUserRolesByEmployeeName(employeeName, new PageRequest(0, 20));
        else if (employeeId != null)
            lstUsr = userRepository.getUserRolesByEmployeeId(employeeId);

        return doFinalUsrEmployeeList(lstUsr);
    }

    public List<SelectList> doFinalUsrEmployeeList(List<SelectList> lstUsr) {
        List<SelectList> lst = new ArrayList<>();

        for (int a = 0; a < lstUsr.size(); a++) {
            SelectList itm = new SelectList(lstUsr.get(a).getId(), lstUsr.get(a).getName());

            if (!lst.contains(itm))
                lst.add(itm);
        }

        for (int i = 0; i < lst.size(); i++) {
            SelectList usr = lst.get(i);
            for (int j = 0; j < lstUsr.size(); j++) {
                SelectList usrRole = lstUsr.get(j);
                String cad = "";
                if (usr.equals(usrRole)) {

                    if (usr.getDescription() == null) {
                        usr.setDescription("");
                        cad = usrRole.getDescription();
                    } else {
                        cad = ", " + usrRole.getDescription();
                    }

                    usr.setDescription(usr.getDescription() + cad);
                }
            }
        }

        for (int i = 0; i < lst.size(); i++) {
            lst.get(i).setName(lst.get(i).getName() + " (" + lst.get(i).getDescription() + ")");
        }

        return lst;
    }

    public List<Long> getLstValidUsersIdByLstRoles(List<String> lstRoles) {
        return userRepository.getLstValidUsersIdByLstRoles(lstRoles);
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public String getFullNameById(Long id) {
        return userRepository.getFullNameById(id);
    }

    public String getCodedPassByUsername(String usrname) {
        return userRepository.getCodedPassByUsername(usrname);
    }

    //valida las ceredenciales enviadas desde la tableta y en caso de ser correctas genera un guid para el usuario que sera verificado en cada operacion
    @Transactional
    public ResponseMessage confirmLoginData(String user, String encodedPass) {

        Gson g = new Gson();

        if (user == null || user.length() == 0 || encodedPass == null || encodedPass.length() == 0)
            return new ResponseMessage(true, "El usuario y/o password son incorrectos. Favor de verificar los datos e intente nuevamente");

        String bdEncodedPass = this.getCodedPassByUsername(user);
        if (bdEncodedPass == null || bdEncodedPass.length() == 0)
            return new ResponseMessage(true, "El usuario y/o password son incorrectos. Favor de verificar los datos e intente nuevamente");

        CryptoRfc2898 rfc2898 = new CryptoRfc2898();

        if (rfc2898.ByteArraysEqual(encodedPass.getBytes(), bdEncodedPass.getBytes())) {
            ResponseMessage r = new ResponseMessage(false, "Acceso correcto");
            TabletUserDto info = new TabletUserDto();

            User u = userRepository.findByUsername(user);
            String guid = UUID.randomUUID().toString();
            u.setGuidTabletAssignment(guid);
            userRepository.save(u);

            info.setId(u.getId());
            info.setFullname(u.getFullname());
            info.setRoleCode(u.getRoles().get(0).getRole());
            info.setGuid(guid);

            r.setReturnData(g.toJson(info));
            return r;
        }

        return new ResponseMessage(true, "El usuario y/o password son incorrectos. Favor de verificar los datos e intente nuevamente");
    }

    public boolean validateUserGuid(String user, String guid) {
        User u = userRepository.findByUserGuid(user, guid);
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }
}
