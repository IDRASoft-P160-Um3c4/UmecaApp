package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;

public interface HumanResourcesWSService {

    ResponseMessage getDevices(String deviceUse);

    ResponseMessage getEmployees();

    ResponseMessage updateUserFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);

    ResponseMessage updateImputedFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);

    ResponseMessage updateAttendanceLogs(String logsList);

    ResponseMessage getImputed(long imputed);
}
