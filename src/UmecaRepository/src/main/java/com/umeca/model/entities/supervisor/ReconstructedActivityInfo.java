package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.SelectList;

import java.util.List;

/**
 * Created by Vmware on 24/09/2014.
 */
public class ReconstructedActivityInfo {

    private String start;
    private String end;
    private String supActivity;
    private String aidSource;
    private List<SelectList> lstAssignedArrangements;
    private String status;
    private String comments;
    private String user;

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

    public List<SelectList> getLstAssignedArrangements() {
        return lstAssignedArrangements;
    }

    public void setLstAssignedArrangements(List<SelectList> lstAssignedArrangements) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String lstAssignedArrangementsToStr() {
        String returnStr = "";

        if (lstAssignedArrangements != null && lstAssignedArrangements.size() > 0) {
            for (SelectList act : lstAssignedArrangements) {
                if (returnStr != "")
                    returnStr += "\n";

                if (act.getName() != null && act.getDescription() != null) {
                    returnStr += "-" + act.getName() + " - " + act.getDescription();
                }
            }

        }

        return returnStr;
    }

    public String actInfoToStr() {
        String returnStr = "";

        returnStr += "-Inicio: " + start;
        returnStr += "\n-Fin: -" + end;

        return returnStr;
    }

    public String detailToStr() {
        String returnStr = "";

        returnStr += "-Descripción: " + supActivity;
        returnStr += "\n-Registrada por: " + user;

        return returnStr;
    }

    public String supervisionDetailToStr() {
        String returnStr = "";

        if (status != null)
            returnStr += "-Estatus: " + status;
        if (comments != null)
            returnStr += "\n-Comentarios: " + comments;

        return returnStr;
    }
}
