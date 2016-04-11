package com.umeca.ws;

import com.umeca.infrastructure.model.ResponseMessage;

public interface BiometricWS {

    public ResponseMessage getDevices(String deviceUse);

    public ResponseMessage getUsersFromDB();

    public ResponseMessage getImputedUsersFromDB();

    public ResponseMessage updateUserFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);

    public ResponseMessage updateAttendanceLogs(String logsList);

    public ResponseMessage getImputed(long imputed);

    public ResponseMessage updateImputedFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);
}
