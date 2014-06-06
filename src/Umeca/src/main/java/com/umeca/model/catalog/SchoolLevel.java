package com.umeca.model.catalog;
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
@Table(name="school_level")
public class SchoolLevel {
    @Id
    @Column(name="id_school_level")
    private Long id;

    @Column(name="school_level", length=255, nullable=false)
    private String name;

    @Transient
    private String value;

    @OneToMany(mappedBy="schoolLevel", cascade={CascadeType.ALL})
    private List<Grade> grades;

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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
