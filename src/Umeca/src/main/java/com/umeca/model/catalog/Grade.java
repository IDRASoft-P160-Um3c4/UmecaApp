package com.umeca.model.catalog;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="grade")
public class Grade {
    @Id
    @Column(name="id_grade")
    private Long id;

    @Column(name="grade", length=255, nullable=false)
    private String name;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_school_level", nullable = true)
    @Expose private SchoolLevel schoolLevel;

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

    @JsonIgnore
    public SchoolLevel getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(SchoolLevel schoolLevel) {
        this.schoolLevel = schoolLevel;
    }
}
