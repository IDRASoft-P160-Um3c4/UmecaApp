package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.EntityGrid;
import com.umeca.model.shared.HearingFormatConstants;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Vmware on 22/05/2014.
 */
public class ForHearingFormatGrid implements EntityGrid {

    private Long id;
    private String idFolder;
    private String idMP;
    private String fullName;
    private String hearingType;
    private String extension;
    private String processVinc;
    private String registerTime;
    private String userName;

    private StringBuilder sb;

    public ForHearingFormatGrid(Long id, String idFolder, String idMP, String name, String lastNP, String lastNM, Integer hType, Integer ext, Integer pVinc,Calendar registerTime,String userName ) {
        this.id = id;
        this.idFolder = idFolder;
        this.idMP = idMP;

        sb = new StringBuilder();
        sb.append(name);
        sb.append(" ");
        sb.append(lastNP);
        sb.append(" ");
        sb.append(lastNM);


        this.fullName = sb.toString();

        this.userName =userName;



        if (hType != null) {

            if (hType.equals(HearingFormatConstants.HEARING_TYPE_MC))
                hearingType = "MC";
            if (hType.equals(HearingFormatConstants.HEARING_TYPE_SCP))
                hearingType = "SCPP";
        } else {
            hearingType = "No aplica";
        }

        if (pVinc.equals(HearingFormatConstants.PROCESS_VINC_NO))
            processVinc = "NO";
        if (pVinc.equals(HearingFormatConstants.PROCESS_VINC_YES))
            processVinc = "SI";

        if (ext.equals(HearingFormatConstants.EXTENSION_144))
            extension = "144 hrs";
        if (ext.equals(HearingFormatConstants.EXTENSION_72))
            extension = "72 hrs";
        if (ext.equals(HearingFormatConstants.EXTENSION_NO))
            extension = "No";

        this.registerTime = CalendarExt.calendarToFormatString(registerTime, Constants.FORMAT_CALENDAR_I);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHearingType() {
        return hearingType;
    }

    public void setHearingType(String hearingType) {
        this.hearingType = hearingType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getProcessVinc() {
        return processVinc;
    }

    public void setProcessVinc(String processVinc) {
        this.processVinc = processVinc;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}