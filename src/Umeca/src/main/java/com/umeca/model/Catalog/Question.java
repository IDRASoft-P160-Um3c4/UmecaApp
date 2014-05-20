package com.umeca.model.Catalog;

import sun.swing.SwingUtilities2;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="question")
public class Question {

    @Id
    @GeneratedValue
    @Column(name="id_question")
    private Long id;

    @Column(name="question", length = 1200, nullable = false)
    private String question;

    @Column(name="value", nullable = true)
    private Integer value;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_question_type", nullable = false)
    private QuestionType questionType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.LAZY)
    private List<QuestionAnswer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionary_section", nullable = false)
    private QuestionarySection section;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }

    public QuestionarySection getSection() {
        return section;
    }

    public void setSection(QuestionarySection section) {
        this.section = section;
    }
}
