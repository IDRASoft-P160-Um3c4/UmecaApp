package com.umeca.model.entities.humanReources;

import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incapacity")
public class Incapacity {

    @Id
    @GeneratedValue
    @Column(name = "id_incapacity")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public UploadFileGeneric getFile() {
        return file;
    }

    public void setFile(UploadFileGeneric file) {
        this.file = file;
    }
}