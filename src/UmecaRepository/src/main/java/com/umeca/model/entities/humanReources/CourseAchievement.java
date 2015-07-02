package com.umeca.model.entities.humanReources;

import com.umeca.model.entities.shared.CourseType;
import com.umeca.model.entities.shared.SchoolDocumentType;
import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_achievement")
public class CourseAchievement {

    @Id
    @GeneratedValue
    @Column(name = "id_course_achievement")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @Column(name = "duration")
    private String duration;

    @Column(name = "start_date")
    private Date start;

    @Column(name = "end_date")
    private Date end;

    @Column(name = "training")
    private Boolean isTraining;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_school_document_type")
    private SchoolDocumentType schoolDocumentType;

    @Column(name = "spec_doc_type")
    private String specDocType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course_type")
    private CourseType courseType;

    @Column(name = "spec_course_type")
    private String specCourseType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_generic_file")
    private UploadFileGeneric file;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public SchoolDocumentType getSchoolDocumentType() {
        return schoolDocumentType;
    }

    public void setSchoolDocumentType(SchoolDocumentType schoolDocumentType) {
        this.schoolDocumentType = schoolDocumentType;
    }

    public String getSpecDocType() {
        return specDocType;
    }

    public void setSpecDocType(String specDocType) {
        this.specDocType = specDocType;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getSpecCourseType() {
        return specCourseType;
    }

    public void setSpecCourseType(String specCourseType) {
        this.specCourseType = specCourseType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public UploadFileGeneric getFile() {
        return file;
    }

    public void setFile(UploadFileGeneric file) {
        this.file = file;
    }
}
