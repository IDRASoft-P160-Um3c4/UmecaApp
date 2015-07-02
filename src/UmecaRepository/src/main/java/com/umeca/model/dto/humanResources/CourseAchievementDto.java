package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseAchievementDto implements EntityGrid {

    private Long id;
    private Long idEmployee;
    private String name;
    private String place;
    private String duration;
    private Long idCourseType;
    private String specCourseType;
    private Long idDocType;
    private String specDocType;
    private String start;
    private String end;
    private String courseType;
    private String documentType;
    private Boolean isTraining;
    private Long fileId;

    public CourseAchievementDto() {
    }

    //expediente digital
    public CourseAchievementDto(String name, String place, String courseType, String specCourseType, String documentType, String specDocType, Date start, Date end) {
        this.name = name;
        this.place = place;
        this.courseType = courseType;
        this.specCourseType = specCourseType;
        this.documentType = documentType;
        this.specDocType = specDocType;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
    }

    //upsert
    public CourseAchievementDto(Long id, Long idEmployee, String name, String place, Long idCourseType, String specCourseType, Long idDocType, String specDocType, Date start, Date end) {
        this.id = id;
        this.name = name;
        this.idEmployee = idEmployee;
        this.place = place;
        this.idCourseType = idCourseType;
        this.specCourseType = specCourseType;
        this.idDocType = idDocType;
        this.specDocType = specDocType;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
    }

    //grid cursos
    public CourseAchievementDto(Long id, String courseType, String name, String place, String documentType) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.courseType = courseType;
        this.documentType = documentType;
    }

    //upsert training
    public CourseAchievementDto(Long id, Long idEmployee, String name, String place, String duration, Date start, Date end, Long fileId) {
        this.id = id;
        this.name = name;
        this.idEmployee = idEmployee;
        this.place = place;
        this.duration = duration;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
        this.fileId = fileId;
    }

    //grid training
    public CourseAchievementDto(Long id, String name, String place, String duration, Date start, Date end, Long fileId) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.duration = duration;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
        this.fileId = fileId;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Boolean getIsTraining() {
        return isTraining;
    }

    public void setIsTraining(Boolean isTraining) {
        this.isTraining = isTraining;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}

