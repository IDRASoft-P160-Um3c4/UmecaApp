package com.umeca.model.shared;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectList implements EntityGrid{
    private Long id;
    private Integer idAux;
    private Calendar calendar;
    private String name;
    private String description;
    private String code;
    private Long aux;
    private Boolean lock;
    private Boolean isSelected;
    private Boolean specification;
    private String strDate;
    private String logType;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private Long value;

    public SelectList() {
    }

    public SelectList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SelectList(Long id, Boolean lock) {
        this.id = id;
        this.lock = lock;
    }

    public SelectList(Long id, Boolean isSelected,String name) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
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

    public SelectList(Long id, String name, String description, Boolean isSelected) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isSelected = isSelected;
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

    public SelectList(Long id, String name, Boolean specification, String code, Long aux) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.code = code;
        this.aux = aux;
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

    public SelectList(Long id, String name, boolean specification, Long aux) {
        this.id = id;
        this.name = name;
        this.specification = specification;
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

    //para hacer la lista de observaciones del historial
    public SelectList(Date dateObs, String userName, String role, String observation) {
        if (dateObs != null)
            this.strDate = sdf.format(dateObs);
        this.name = userName;
        this.logType = role;
        this.description = observation;
    }

    public SelectList(Long id, Long aux) {
        this.id = id;
        this.aux = aux;
    }

    public SelectList(Long id, Integer idAux) {
        this.id = id;
        this.idAux = idAux;
    }

    public SelectList(Long id, Long aux, String code, String status) {
        this.id = id;
        this.aux = aux;
        this.description = code;
        this.name = status;
    }

    public SelectList(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public SelectList(Long id, String name, Long aux) {
        this.id = id;
        this.name = name;
        this.aux = aux;
    }

    public SelectList(Long id, String name, String description, Calendar calendar) {
        this.id = id;
        this.name = name;
        this.description = description;
        if (calendar != null)
            this.strDate = sdf.format(calendar.getTime());
    }

    public SelectList(Long id, Long value, String code){
        this.id = id;
        this.value = value;
        this.description= code;
        if (description.equals(Constants.EVENT_INTERVIEW_DECLINED))
            this.name = "Negación";
        else if (description.equals(Constants.EVENT_CASE_REPORT))
            this.name = "Informe";
        else if (description.equals(Constants.EVENT_CASE_OPINION))
            this.name = "Opinión";
        else if (description.equals(Constants.EVENT_ONLY_INTERVIEW))
            this.name = "Solo entrevista";
    }

    public SelectList(Boolean gender, long numberGender){
        this.value = numberGender;
        if(gender == Constants.GENDER_MALE)
            this.name = "Hombre";
        else
            this.name = "Mujer";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectList that = (SelectList) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
