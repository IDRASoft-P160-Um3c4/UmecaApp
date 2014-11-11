package com.umeca.model.catalog.catalog;

import javax.persistence.*;

@Entity
@Table(name="cat_question_type")
public class QuestionType {

    @Id
    @GeneratedValue
    @Column(name="id_question_type")
    private Long id;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="description", length = 100, nullable = true)
    private String description;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
