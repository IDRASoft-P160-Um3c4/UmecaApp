package com.umeca.model.catalog;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="questionary_section")
public class QuestionarySection {

    @Id
    @GeneratedValue
    @Column(name="id_questionary_section")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="code", length = 20, nullable = false)
    private String code;

    @Column(name="description")
    private String description;

    @Column(name="is_obsolete",nullable = false)
    private Boolean isObsolete;

    @Column(name="extras_json")
    private String extras;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_parent_section")
    private QuestionarySection parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
    private List<QuestionarySection> childs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section", fetch = FetchType.LAZY)
    private List<Question> questions;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_questionary")
    private Questionary questionary;

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

    public QuestionarySection getParent() {
        return parent;
    }

    public void setParent(QuestionarySection parent) {
        this.parent = parent;
    }

    public List<QuestionarySection> getChilds() {
        return childs;
    }

    public void setChilds(List<QuestionarySection> childs) {
        this.childs = childs;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Questionary getQuestionary() {
        return questionary;
    }

    public void setQuestionary(Questionary questionary) {
        this.questionary = questionary;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
