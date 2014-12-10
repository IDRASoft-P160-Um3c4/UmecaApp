package com.umeca.model.entities.shared;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/12/14
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActMonPlanDto {
    private String start;
    private String end;
    private String supActivity;
    private String aidSource;
    private List<String> lstAssignedArrangements;
    private String status;
    private String comments;

    public ActMonPlanDto() {
    }

    public ActMonPlanDto(String start, String end, String supActivity, String aidSource, List<String> lstAssignedArrangements, String status, String comments) {
        if(lstAssignedArrangements.size()>0){
        this.start = start;
        this.end = end;
        this.supActivity = supActivity;
        this.aidSource = aidSource;
        this.lstAssignedArrangements = lstAssignedArrangements;
        this.status = status;
        this.comments = comments != null ? comments:"NA";
        }
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSupActivity() {
        return supActivity;
    }

    public void setSupActivity(String supActivity) {
        this.supActivity = supActivity;
    }

    public String getAidSource() {
        return aidSource;
    }

    public void setAidSource(String aidSource) {
        this.aidSource = aidSource;
    }

    public List<String> getLstAssignedArrangements() {
        return lstAssignedArrangements;
    }

    public void setLstAssignedArrangements(List<String> lstAssignedArrangements) {
        this.lstAssignedArrangements = lstAssignedArrangements;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


}

