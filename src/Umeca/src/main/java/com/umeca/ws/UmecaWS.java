package com.umeca.ws;

import com.umeca.infrastructure.model.ResponseMessage;

public interface UmecaWS {

    public ResponseMessage loginFromTablet(String user, String encodedPass);
    public ResponseMessage getAssignmentsByUser(String user, String guid);
    public ResponseMessage getAssignedCaseByAssignmentId(String user, String guid, Long assignmentId);
    public ResponseMessage confirmReceivedAssignment(String user, String guid, Long assignmentId);
    public ResponseMessage synchronizeMeeting(String user, String guid, Long assignmentId, String jsonCase);
    public ResponseMessage synchronizeSourcesVerification(String user, String guid, Long assignmentId, String jsonCase);
    public ResponseMessage synchronizeHearingFormat(String user, String guid, Long assignmentId, String jsonCase);
    
}
