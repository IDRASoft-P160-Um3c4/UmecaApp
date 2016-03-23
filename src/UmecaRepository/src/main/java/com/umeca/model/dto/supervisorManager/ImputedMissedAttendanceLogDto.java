package com.umeca.model.dto.supervisorManager;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ImputedMissedAttendanceLogDto implements EntityGrid {
    private Long id;
    private String dateStr;
    private Date date;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;

    public ImputedMissedAttendanceLogDto() {
    }

    public ImputedMissedAttendanceLogDto(Long id, String name, String lastNameP, String lastNameM, Date date) {
        SimpleDateFormat fDate = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat fTime = new SimpleDateFormat("HH:mm:ss");

        this.id = id;

        if (date != null) {
            this.dateStr = fDate.format(date) + " " + fTime.format(date);
            this.date = date;
        }
        this.name = name;
        this.lastNameM = lastNameM;
        this.lastNameP = lastNameP;
        this.fullName = name + " " + lastNameP + " " + lastNameM;
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
