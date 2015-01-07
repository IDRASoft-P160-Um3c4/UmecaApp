package com.umeca.model.catalog;

import javax.persistence.*;

@Entity
@Table(name="cat_answer")
public class Answer {


    @Id
    @GeneratedValue
    @Column(name="id_question_answer")
    private Long id;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="description", length = 100, nullable = true)
    private String description;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_question", nullable = false)
    private Question question;

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
