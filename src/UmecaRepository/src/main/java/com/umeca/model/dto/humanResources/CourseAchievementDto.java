package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseAchievementDto implements EntityGrid {

    private Long id;
    private Long idEmployee;
    private String place;
    private Long idCourseType;
    private String specCourseType;
    private Long idDocType;
    private String specDocType;
    private String startStr;
    private String endStr;
    private String courseType;
    private String documentType;


    public CourseAchievementDto() {
    }

    public CourseAchievementDto(Long id, Long idEmployee, String place, Long idCourseType, String specCourseType, Long idDocType, String specDocType, Date start, Date end) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.place = place;
        this.idCourseType = idCourseType;
        this.specCourseType = specCourseType;
        this.idDocType = idDocType;
        this.specDocType = specDocType;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        if (start != null) {
            this.startStr = formatter.format(start);
        }
        if (end != null) {
            this.endStr = formatter.format(end);
        }
    }

    public CourseAchievementDto(Long id, String courseType, String place, String documentType) {
        this.id = id;
        this.place = place;
        this.courseType = courseType;
        this.documentType = documentType;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getIdCourseType() {
        return idCourseType;
    }

    public void setIdCourseType(Long idCourseType) {
        this.idCourseType = idCourseType;
    }

    public String getSpecCourseType() {
        return specCourseType;
    }

    public void setSpecCourseType(String specCourseType) {
        this.specCourseType = specCourseType;
    }

    public Long getIdDocType() {
        return idDocType;
    }

    public void setIdDocType(Long idDocType) {
        this.idDocType = idDocType;
    }

    public String getSpecDocType() {
        return specDocType;
    }

    public void setSpecDocType(String specDocType) {
        this.specDocType = specDocType;
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }
}

