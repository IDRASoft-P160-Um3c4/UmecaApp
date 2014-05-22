package com.umeca.model.entities.reviewer;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="case_detention")
public class Case {
    @Id
    @GeneratedValue
    @Column(name="id_case")
    private Long id;

    @Column(name="id_folder", length = 15, nullable = true)
    private String idFolder;

    @Column(name="id_mp", length = 15, nullable = true)
    private String idMP;

    @Column(name="recidivist", nullable = false)
    private Boolean recidivist;

    @OneToOne(mappedBy="caseDetention", cascade={CascadeType.ALL})
    private Meeting meeting;

    @Column(name = "status",nullable = false)
    private String status;

    @Transient
    private String idString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Boolean getRecidivist() {
        return recidivist;
    }

    public void setRecidivist(Boolean recidivist) {
        this.recidivist = recidivist;
    }

    public String getIdString() {
        this.idString =  String.format("%010d", id);
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }
}
