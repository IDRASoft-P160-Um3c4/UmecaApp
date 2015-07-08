package com.umeca.ws;

public interface UmecaWS {
    public String loginFromTablet(String user, String encodedPass);
    public String getAssignmentsByUser(String user, String guid);
//    String getCasesIdByUser(String user,String codePass);
//    String downloadMeetingCases(String user,String codePass);
}
