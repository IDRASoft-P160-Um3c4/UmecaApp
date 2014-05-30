 package com.umeca.model.catalog;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

import javax.persistence.*;
import java.util.Comparator;
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

    @Column(name="value", nullable = false)
    private Integer value;

    @Column(name="quest_index", nullable = false)
    private Integer index;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_question_type", nullable = false)
    private QuestionType questionType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionary_section", nullable = false)
    private QuestionarySection section;


    @Transient
    public static final Comparator<Question> questComparator = new Comparator<Question>() {
        @Override
        public int compare(Question q1, Question q2) {
            return  q1.getIndex().compareTo(q2.getIndex());
        }
    };


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

    public QuestionarySection getSection() {
        return section;
    }

    public void setSection(QuestionarySection section) {
        this.section = section;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
