package com.umeca.ws;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.TabletAssignmentInfo;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.tablet.TabletService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UmecaWSImp implements UmecaWS {

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TabletService tabletService;

    public UmecaWSImp() {
    }

    public String loginFromTablet(String user, String encodedPass) {
        try {
            return sharedUserService.confirmLoginData(user, encodedPass);
        } catch (Exception e) {
            System.out.println("error en loginFromTablet");
            return new Gson().toJson(new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente"));
        }
    }

    public String getAssignmentsByUser(String user, String guid) {
        ResponseMessage response;
        Gson gson = new Gson();

        try {

            if (sharedUserService.validateUserGuid(user, guid)) {

                User u = userRepository.findByUsername(user);
                List<TabletAssignmentInfo> assignmentInfoList = caseRepository.getAssignmentIdsTypesByUser(u.getId());
                if (assignmentInfoList != null && assignmentInfoList.size() > 0) {
                    response = new ResponseMessage(false, "Acceso correcto");
                    response.setReturnData(gson.toJson(assignmentInfoList));
                } else {
                    response = new ResponseMessage(true, "No existen casos asignados para su usuario.");
                }

            } else {
                response = new ResponseMessage(true, "Debe acceder nuevamente a la aplicación.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }

        return gson.toJson(response);
    }

    public String getAssignedCaseByAssignmentId(String user, String guid, Long assignmentId) {
        ResponseMessage response;
        Gson gson = new Gson();

        try {
            if (sharedUserService.validateUserGuid(user, guid)) {
                User usr = userRepository.findByUsername(user);
                response = tabletService.getCaseByAssignmentId(assignmentId, usr.getId());
            } else {
                response = new ResponseMessage(true, "Debe acceder nuevamente a la aplicación.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }

        return gson.toJson(response);
    }

    public String confirmReceivedAssignment(String user, String guid, Long assignmentId) {
        ResponseMessage response;
        Gson gson = new Gson();

        try {
            if (sharedUserService.validateUserGuid(user, guid)) {
                response = tabletService.setDownloadDateToAssignment(assignmentId);
            } else {
                response = new ResponseMessage(true, "Debe acceder nuevamente a la aplicación.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }

        return gson.toJson(response);
    }

}
