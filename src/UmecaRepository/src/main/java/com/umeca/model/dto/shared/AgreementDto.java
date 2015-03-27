package com.umeca.model.dto.shared;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgreementDto implements EntityGrid {

    private Long id;
    private String title;
    private String agenda;
    private String minuteDate;
    private Long attendantId;
    private String attendant;
    private String assistantsIds;
    private String place;
    private String startTime;
    private String endTime;
    private Boolean isObsolete;

    public AgreementDto() {

    }

    //grid
    public AgreementDto(Long id, Date minuteDate, Date startTime, String place, String attName, String attLastNameP, String attLastNameM, Boolean isObsolete) {
        this.id = id;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");
        if (minuteDate != null)
            this.minuteDate = sdf.format(minuteDate);
        if (startTime != null)
            this.startTime = sdfT.format(startTime);
        this.place = place;
        this.attendant = attLastNameM + attLastNameP + attLastNameM;
        this.isObsolete = isObsolete;
    }

    //upsert
    public AgreementDto(Long id, String title, String agenda, Date minuteDate, Long attendantId, String place, Date startTime, Date endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");
        this.id = id;
        this.title = title;
        this.agenda = agenda;
        if (minuteDate != null)
            this.minuteDate = sdf.format(minuteDate);
        this.attendantId = attendantId;
        this.place = place;
        if (startTime != null)
            this.startTime = sdfT.format(startTime);
        if (endTime != null)
            this.endTime = sdfT.format(endTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getMinuteDate() {
        return minuteDate;
    }

    public void setMinuteDate(String minuteDate) {
        this.minuteDate = minuteDate;
    }

    public Long getAttendantId() {
        return attendantId;
    }

    public void setAttendantId(Long attendantId) {
        this.attendantId = attendantId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAttendant() {
        return attendant;
    }

    public void setAttendant(String attendant) {
        this.attendant = attendant;
    }

    public String getAssistantsIds() {
        return assistantsIds;
    }

    public void setAssistantsIds(String assistantsIds) {
        this.assistantsIds = assistantsIds;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
