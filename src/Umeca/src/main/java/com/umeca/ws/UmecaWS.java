package com.umeca.ws;

public interface UmecaWS {

    public String loginFromTablet(String user, String encodedPass);
    public String getAssignmentsByUser(String user, String guid);
    public String getAssignedCaseByAssignmentId(String user, String guid, Long assignmentId);
    public String confirmReceivedAssignment(String user, String guid, Long assignmentId);
    public String synchronizeMeeting(String user, String guid, Long assignmentId, String jsonCase);
    public String synchronizeSourcesVerification(String user, String guid, Long assignmentId, String jsonCase);
    public String synchronizeHearingFormat(String user, String guid, Long assignmentId, String jsonCase);
    
}
