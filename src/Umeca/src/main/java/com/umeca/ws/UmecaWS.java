package com.umeca.ws;

public interface UmecaWS {
    public String loginFromTablet(String user, String encodedPass);
    public String getAssignmentsByUser(String user, String guid);
    public String getAssignedCaseByAssignmentId(String user, String guid, Long assignmentId);
    public String confirmReceivedAssignment(String user, String guid, Long assignmentId);
//    String getCasesIdByUser(String user,String codePass);
//    String downloadMeetingCases(String user,String codePass);
}
