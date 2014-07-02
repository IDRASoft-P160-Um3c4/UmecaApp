package com.umeca.model.entities.reviewer;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 1/07/14
 * Time: 06:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindLegalBefore {
    String idMP;
    String idFolder;
    String statusCase;

    public FindLegalBefore(String idMP, String idFolder, String statusCase) {
        if(idMP==null ||(idMP!=null && idMP.equals("")))
            idMP="No asignado";
        this.idMP = idMP;
        this.idFolder = idFolder;
        this.statusCase = statusCase;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getStatusCase() {
        return statusCase;
    }

    public void setStatusCase(String statusCase) {
        this.statusCase = statusCase;
    }
}
