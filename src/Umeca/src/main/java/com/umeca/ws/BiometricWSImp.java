package com.umeca.ws;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.service.humanResources.HumanResourcesWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("BiometricWSImp")
public class BiometricWSImp implements BiometricWS {


    @Autowired
    private HumanResourcesWSService humanResourcesWSService;

    public BiometricWSImp() {
    }

    @Override
    public ResponseMessage getDevices() {
        ResponseMessage response;
        try {
                response = humanResourcesWSService.getDevices();

        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }

        return response;
    }

    @Override
    public ResponseMessage getUsersFromDB() {
        ResponseMessage response;
        try {
            response = humanResourcesWSService.getEmployees();

        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }
        return response;
    }

    @Override
    public ResponseMessage updateUserFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation) {
        ResponseMessage response;
        try {
            response = humanResourcesWSService.updateUserFingerPrint(enrollNumber, finger, fingerPrint, operation);

        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }
        return response;
    }

    @Override
    public ResponseMessage updateAttendanceLogs(String logsList) {
        ResponseMessage response;
        try {
            response = humanResourcesWSService.updateAttendanceLogs(logsList);

        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseMessage(true, "Ha ocurrido un error. Intente nuevamente.");
        }
        return response;
    }


}
