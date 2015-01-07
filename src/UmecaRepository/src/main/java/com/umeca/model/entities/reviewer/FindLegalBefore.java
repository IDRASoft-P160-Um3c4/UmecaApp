package com.umeca.model.entities.reviewer;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 1/07/14
 * Time: 06:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindLegalBefore {
    Long idCase;
    String idMP;
    String idFolder;
    String statusCase;
    String arrangement;
    String dateCreateString;
    String imputedInfo;


    public FindLegalBefore(Long idCase,String idMP, String idFolder, String statusCase, Date dateCreate, String fullName, Date birthDate) {
        this.idCase = idCase;
        if(idMP==null ||(idMP!=null && idMP.equals("")))
            idMP="No asignado";
        this.idMP = idMP;
        this.idFolder = idFolder;
        this.statusCase = statusCase;
        this.dateCreateString = Constants.df.format(dateCreate);
        this.imputedInfo = "Nombre: "+fullName+"<br/> Fecha de nacimiento: "+Constants.df.format(birthDate);
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

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public void convertListArrangementToString(List<SelectList> lstArrangement) {
        this.arrangement = "";
        for(SelectList sl: lstArrangement){
            this.arrangement += "<li>"+sl.getName() + ". "+sl.getDescription()+".</li>";
        }
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getDateCreateString() {
        return dateCreateString;
    }

    public void setDateCreateString(String dateCreateString) {
        this.dateCreateString = dateCreateString;
    }

    public String getImputedInfo() {
        return imputedInfo;
    }

    public void setImputedInfo(String imputedInfo) {
        this.imputedInfo = imputedInfo;
    }
}
