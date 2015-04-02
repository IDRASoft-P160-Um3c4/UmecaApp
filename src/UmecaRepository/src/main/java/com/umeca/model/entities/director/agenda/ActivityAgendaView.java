package com.umeca.model.entities.director.agenda;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.catalog.CatPriority;
import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityAgendaView implements EntityGrid {

    private Long id;

    private Calendar start;

    private Calendar end;

    private CatPriority priority;

    private String place;

    private String description;

    private String status;

    private String stCreationDate;

    public ActivityAgendaView(Long id, String place, String description, Calendar start, String status) {
        this.id = id;
        this.place = place;
        this.description = description;
        this.start = start;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public CatPriority getPriority() {
        return priority;
    }

    public void setPriority(CatPriority priority) {
        this.priority = priority;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStCreationDate() {
        if(start == null)
            return "";

        return CalendarExt.calendarToFormatString(start, "dd-MM-yyyy HH:mm");
    }

    public void setStCreationTime(String stCreationDate) {
        this.stCreationDate = stCreationDate;
    }
}
