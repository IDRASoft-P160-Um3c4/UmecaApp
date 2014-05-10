package com.umeca.model.Catalog;
import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="academic_degree")
public class AcademicDegree {
    @Id
    @Column(name="id_academic_degree")
    private Long id;

    @Column(name="academic_degree", length=255, nullable=false)
    private String name;

    @Transient
    private String value;

    @OneToMany(mappedBy="academicDegree", cascade={CascadeType.ALL})
    private List<AcademicYear> academicYears;

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

    public List<AcademicYear> getAcademicYears() {
        return academicYears;
    }

    public void setAcademicYears(List<AcademicYear> academicYears) {
        this.academicYears = academicYears;
    }
}
