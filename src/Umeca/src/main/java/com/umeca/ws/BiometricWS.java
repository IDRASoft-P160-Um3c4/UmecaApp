package com.umeca.ws;

import com.umeca.infrastructure.model.ResponseMessage;

public interface BiometricWS {

    ResponseMessage getDevices(String deviceUse);

    ResponseMessage getUsersFromDB();

    ResponseMessage getImputedUsersFromDB();

    ResponseMessage updateUserFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);

    ResponseMessage updateAttendanceLogs(String logsList);

    ResponseMessage updateImputedLogs(String logList);

    ResponseMessage getImputed(long imputed);

    ResponseMessage updateImputedFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);
}
