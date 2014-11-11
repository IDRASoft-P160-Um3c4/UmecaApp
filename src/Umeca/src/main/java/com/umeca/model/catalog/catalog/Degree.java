package com.umeca.model.catalog.catalog;

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
@Table(name="cat_degree")
public class Degree {
    @Id
    @Column(name="id_degree")
    private Long id;

    @Column(name="degree", length=255, nullable=false)
    private String name;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_academic_level", nullable = true)
    @Expose private AcademicLevel academicLevel;

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

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }

    @JsonIgnore
    public AcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }
}
