package com.umeca.model.Catalog;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="questionary")
public class Questionary {

    @Id
    @GeneratedValue
    @Column(name="id_questionary")
    private Long id;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="code", length = 20, nullable = false)
    private String code;

    @Column(name="description", length = 100, nullable = true)
    private String description;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionary", fetch = FetchType.LAZY)
    private List<QuestionarySection> sections;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public List<QuestionarySection> getSections() {
        return sections;
    }

    public void setSections(List<QuestionarySection> sections) {
        this.sections = sections;
    }
}
