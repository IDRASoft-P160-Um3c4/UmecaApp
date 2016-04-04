package com.umeca.ws;

import com.umeca.infrastructure.model.ResponseMessage;

public interface BiometricWS {

    public ResponseMessage getDevices();

    public ResponseMessage getUsersFromDB();

    public ResponseMessage updateUserFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);

    public ResponseMessage updateAttendanceLogs(String logsList);

    public ResponseMessage getImputed(long imputed);

    public ResponseMessage updateImputedFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation);
}
