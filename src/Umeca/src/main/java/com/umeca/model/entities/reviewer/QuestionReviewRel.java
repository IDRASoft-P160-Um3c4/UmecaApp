package com.umeca.model.entities.reviewer;


import com.umeca.model.Catalog.Question;
import javax.persistence.*;

@Entity
@Table(name="question_review_rel")
public class QuestionReviewRel {

    @Id
    @GeneratedValue
    @Column(name="id_question_review_rel")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_technical_review", nullable = false)
    private TechnicalReview technicalReview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public TechnicalReview getTechnicalReview() {
        return technicalReview;
    }

    public void setTechnicalReview(TechnicalReview technicalReview) {
        this.technicalReview = technicalReview;
    }
}
