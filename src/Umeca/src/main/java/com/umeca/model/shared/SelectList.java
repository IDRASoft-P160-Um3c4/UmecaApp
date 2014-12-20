package com.umeca.model.shared;

import com.umeca.model.entities.supervisor.HearingFormat;

import java.util.Calendar;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/4/14
 * Time: 9:11 PM
 */
public class SelectList {
    private Long id;
    private Integer idAux;
    private Calendar calendar;
    private String name;
    private String description;
    private Long aux;
    private Boolean lock;
    private Boolean specification;
    private String strDate;
    private String logType;

    public SelectList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SelectList(Integer id, String description) {
        this.idAux = id;
        this.description = description;
    }

    public SelectList(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public SelectList(Integer id, Calendar calendar) {
        this.idAux = id;
        this.calendar = calendar;
    }

    public SelectList(Long id, String name, String description, String secDescription) {
        this.id = id;
        this.name = description + " / " + name;
        this.description = secDescription;
    }

    public SelectList(Long id, String arrangement, String description, Integer typeArrangement) {
        this.id = id;
        this.description = description;
        this.name = arrangement;
        if (typeArrangement.equals(HearingFormatConstants.HEARING_TYPE_SCP))
            this.name += " - SCP";
        else if (typeArrangement.equals(HearingFormatConstants.HEARING_TYPE_MC))
            this.name += " - MC";

        this.name += " / " + description;
    }

    public SelectList(Long id, String name, String description, Long aux) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.aux = aux;
    }

    public SelectList(Long id, String description, Boolean lock, Boolean specification) {
        this.id = id;
        this.description = description;
        this.lock = lock;
        this.specification = specification;
    }

    public SelectList(Long id, String name, Boolean specification) {
        this.id = id;
        this.name = name;
        this.specification = specification;
    }

    public SelectList(String name, String description, String strDate, String logType) {
        this.name = name;
        this.description = description;
        this.strDate = strDate;
        this.logType = logType;
    }

    public SelectList(Long id, Long aux) {

    }

    public SelectList(Long id, Long aux, String code, String status) {
        this.id = id;
        this.aux = aux;
        this.description = code;
        this.name = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAux() {
        return aux;
    }

    public void setAux(Long aux) {
        this.aux = aux;
    }

    public Integer getIdAux() {
        return idAux;
    }

    public void setIdAux(Integer idAux) {
        this.idAux = idAux;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
