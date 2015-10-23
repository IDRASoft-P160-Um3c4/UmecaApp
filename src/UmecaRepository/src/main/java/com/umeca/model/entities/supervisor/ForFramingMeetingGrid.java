package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ForFramingMeetingGrid implements EntityGrid {

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
    private Long idTR;
    private Long framingMeetingId;
    private String umecaDateStr;
    private String umecaTimeStr;
    private Date umecaTime;
    private String fullNameSupervisor;
    private Boolean umecaDateExpired;
    //    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public ForFramingMeetingGrid(Long id, String codeStatus, String descStatus, String idMP, String name, String lastNameP, String lastNameM, Date brthDate, Long idTR, Long framingMeetingId) {
        this.id = id;
        this.codeStatus = codeStatus;
        this.descStatus = descStatus;
        this.idMP = idMP;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.brthDate = brthDate;
        this.idTR = idTR;
        this.framingMeetingId = framingMeetingId;


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
        }
    }

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
        }
    }


    public ForFramingMeetingGrid(Long id, String codeStatus,String descStatus, String idMP,String name, String lastNameP, String lastNameM, Date birthDate, Date umecaDate, Date umecaTime,String fullname){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Date currentDate = new Date();

        Calendar calendarUmeca = new GregorianCalendar();
        calendarUmeca.setTime(umecaDate);
        Calendar calendarTime = new GregorianCalendar();
        calendarTime.setTime(umecaTime);

        long hour = calendarTime.get(Calendar.HOUR_OF_DAY);
        calendarUmeca.add(Calendar.HOUR, calendarTime.get(Calendar.HOUR_OF_DAY));
        calendarUmeca.add(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
        calendarUmeca.add(Calendar.SECOND, calendarTime.get(Calendar.SECOND));

        Date umecaT = calendarUmeca.getTime();


        this.id = id;
        this.codeStatus = codeStatus;
        this.descStatus = descStatus;
        this.idMP = idMP;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.umecaDateStr = umecaDate == null ? "" : sdf.format(umecaDate);
        this.brthDateTxt = sdf.format(birthDate);
        this.umecaTime = umecaTime;
        this.fullNameSupervisor = fullname;
        StringBuilder strBld = new StringBuilder();
        strBld.append(this.name);
        strBld.append(" ");
        strBld.append(this.lastNameP);
        strBld.append(" ");
        strBld.append(this.lastNameM);
        this.fullName = strBld.toString();
        this.umecaDateExpired = calendarUmeca.getTime().before(Calendar.getInstance().getTime());
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

    public Long getFramingMeetingId() {
        return framingMeetingId;
    }

    public void setFramingMeetingId(Long framingMeetingId) {
        this.framingMeetingId = framingMeetingId;
    }

    public Long getIdTR() {
        return idTR;
    }

    public void setIdTR(Long idTR) {
        this.idTR = idTR;
    }

    public String getUmecaDateStr() {
        return umecaDateStr;
    }

    public void setUmecaDateStr(String umecaDateStr) {
        this.umecaDateStr = umecaDateStr;
    }

    public String getUmecaTimeStr() {
        return umecaTimeStr;
    }

    public void setUmecaTimeStr(String umecaTimeStr) {
        this.umecaTimeStr = umecaTimeStr;
    }

    public Date getUmecaTime() {
        return umecaTime;
    }

    public void setUmecaTime(Date umecaTime) {
        this.umecaTime = umecaTime;
    }

    public String getFullNameSupervisor() {
        return fullNameSupervisor;
    }

    public void setFullNameSupervisor(String fullNameSupervisor) {
        this.fullNameSupervisor = fullNameSupervisor;
    }

    public Boolean getUmecaDateExpired() {
        return umecaDateExpired;
    }

    public void setUmecaDateExpired(Boolean umecaDateExpired) {
        this.umecaDateExpired = umecaDateExpired;
    }
}
