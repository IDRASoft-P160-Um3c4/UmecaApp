package com.umeca.ws;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.TabletAssignmentInfo;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.tablet.TabletService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
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

    public ResponseMessage loginFromTablet(String user, String encodedPass) {
        try {
            return sharedUserService.confirmLoginData(user, encodedPass);
        } catch (Exception e) {
            System.out.println("error en loginFromTablet");
            return new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente");
        }
    }

    public ResponseMessage getAssignmentsByUser(String user, String guid) {
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

        return response;
    }

    public ResponseMessage getAssignedCaseByAssignmentId(String user, String guid, Long assignmentId) {
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

        return response;
    }

    public ResponseMessage confirmReceivedAssignment(String user, String guid, Long assignmentId) {
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

        return response;
    }


    public ResponseMessage synchronizeMeeting(String user, String guid, Long assignmentId, String jsonCase) {
        ResponseMessage response;
        Gson gson = new Gson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            if (sharedUserService.validateUserGuid(user, guid)) {

                TabletCaseDto tabletDto = gson.fromJson(jsonCase, new TypeToken<TabletCaseDto>() {{
                }}.getType());

                if (tabletDto.getWebId() == null && tabletService.validateExistCase(tabletDto.getIdFolder(), tabletDto.getMeeting().getImputed().getFoneticString(), tabletDto.getMeeting().getImputed().getBirthDate())) {
                    response = new ResponseMessage(true, "El número de carpeta de investigación y el imputado ya se encuentran registrados.");
                    return response;
                }

                Case c = tabletService.synchronizeMeeting(tabletDto, assignmentId);
                response = new ResponseMessage(false, "El caso se ha sincronizado con éxito.");

            } else {
                response = new ResponseMessage(true, "Debe acceder nuevamente a la aplicación.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }

        return response;
    }

    public ResponseMessage synchronizeSourcesVerification(String user, String guid, Long assignmentId, String jsonCase) {
        ResponseMessage response;
        Gson gson = new Gson();

        try {
            if (sharedUserService.validateUserGuid(user, guid)) {

                TabletCaseDto tabletDto = gson.fromJson(jsonCase, new TypeToken<TabletCaseDto>() {{
                }}.getType());

                Case c = tabletService.synchronizeVerification(tabletDto, assignmentId);

                response = new ResponseMessage(false, "El caso se ha sincronizado con éxito.");
            } else {
                response = new ResponseMessage(true, "Debe acceder nuevamente a la aplicación.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }

        return response;
    }

    public ResponseMessage synchronizeHearingFormat(String user, String guid, Long assignmentId, String jsonCase) {
        ResponseMessage response;
        Gson gson = new Gson();

        try {
            if (sharedUserService.validateUserGuid(user, guid)) {
                TabletCaseDto tabletDto = gson.fromJson(jsonCase, new TypeToken<TabletCaseDto>() {{
                }}.getType());

                if (tabletDto.getWebId() == null && tabletService.validateExistCase(tabletDto.getIdFolder(), tabletDto.getMeeting().getImputed().getFoneticString(), tabletDto.getMeeting().getImputed().getBirthDate())) {
                    response = new ResponseMessage(true, "El número de carpeta de investigación y el imputado ya se encuentran registrados.");
                    return response;
                }

                Case c = tabletService.synchronizeHearingFormats(tabletDto, assignmentId);

                response = new ResponseMessage(false, "El caso se ha sincronizado con éxito.");
            } else {
                response = new ResponseMessage(true, "Debe acceder nuevamente a la aplicación.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }

        return response;
    }

}
