package com.umeca.model.entities.reviewer;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/08/14
 * Time: 06:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatusEvaluation {
    String caseDetention;
    String meeting;
    String verification;

    public StatusEvaluation(String caseDetention, String meeting, String verification) {
        this.caseDetention = caseDetention;
        this.meeting = meeting;
        this.verification = verification;
    }

    public String getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(String caseDetention) {
        this.caseDetention = caseDetention;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

}
