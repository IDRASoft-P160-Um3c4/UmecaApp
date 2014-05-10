package com.umeca.model.Catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="academic_year")
public class AcademicYear {
    @Id
    @Column(name="id_academic_year")
    private Long id;

    @Column(name="academic_year", length=255, nullable=false)
    private String name;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_academic_degree", nullable = false)
    private AcademicDegree academicDegree;

    @Transient
    private String value;

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

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getValue() {

        if(id == null)
            return null;

        value = id.toString();

        return value;
    }


    public void setValue(String value) {

        // id = Convert.ToLong(value);

        this.value = value;
    }
}
