package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.EntityGrid;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForFramingMeetingGrid implements EntityGrid {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    private Long id;
    private String codeStatus;
    private String descStatus;
    private String idMP;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;
    private Date brthDate;
    private String brthDateTxt;
    //    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public ForFramingMeetingGrid(Long id, String codeStatus, String descStatus, String idMP, String name, String lastNameP, String lastNameM, Date brthDate) {
        this.id = id;
        this.codeStatus = codeStatus;
        this.descStatus = descStatus;
        this.idMP = idMP;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.brthDate = brthDate;

        StringBuilder strBld = new StringBuilder();

        strBld.append(this.name);
        strBld.append(" ");
        strBld.append(this.lastNameP);
        strBld.append(" ");
        strBld.append(this.lastNameM);
        this.fullName = strBld.toString();

        strBld = new StringBuilder();

        try {
            this.brthDateTxt = sdf.format(this.brthDate);

            String[] arrDt = brthDateTxt.split("/");

            Integer mnth = Integer.parseInt(arrDt[1]) + 1;

            if (mnth < 10) {
                strBld.append(0);
                strBld.append(mnth);
                arrDt[1] = strBld.toString();
            } else
                arrDt[1] = Integer.toString(mnth);

            strBld = new StringBuilder();
            strBld.append(arrDt[0]);
            strBld.append("/");
            strBld.append(arrDt[1]);
            strBld.append("/");
            strBld.append(arrDt[2]);

        } catch (Exception e) {
            logException.Write(e,this.getClass(),"ForFramingMeetingGrid",sharedUserService);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBrthDate() {
        return brthDate;
    }

    public void setBrthDate(Date brthDate) {
        this.brthDate = brthDate;
    }

    public String getBrthDateTxt() {
        return brthDateTxt;
    }

    public void setBrthDateTxt(String brthDateTxt) {
        this.brthDateTxt = brthDateTxt;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }
}
