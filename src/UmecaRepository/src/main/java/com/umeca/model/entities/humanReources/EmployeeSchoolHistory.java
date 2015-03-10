package com.umeca.model.entities.humanReources;

import com.umeca.model.catalog.Degree;
import com.umeca.model.entities.reviewer.dto.GroupMessageMeetingDto;
import com.umeca.model.entities.reviewer.dto.TerminateMeetingMessageDto;
import com.umeca.model.entities.shared.SchoolDocumentType;
import com.umeca.model.entities.supervisor.FramingMeeting;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_school_history")
public class EmployeeSchoolHistory {

    @Id
    @GeneratedValue
    @Column(name = "id_employee_school_history")
    private Long id;

    @Column(name = "school")
    private String school;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_degree")
    private Degree degree;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_school_document_type")
    private SchoolDocumentType schoolDocumentType;

    @Column(name = "spec_doc_type")
    private String specDocType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
