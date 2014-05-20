package com.umeca.model.entities.reviewer;

import com.umeca.model.Catalog.QuestionType;
import com.umeca.model.Catalog.Questionary;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "technical_review")
public class TechnicalReview {

    @Id
    @GeneratedValue
    @Column(name = "id_technical_review")
    private Long id;

    @Column(name = "total", nullable = false)
    private Integer totalRisk;

    @Column(name = "comments", length = 1000, nullable = false)
    private String comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technicalReview", fetch = FetchType.LAZY)
    private List<QuestionReviewRel> answersSel;

    @Transient
    private Long meetingId;

    @Transient
    private String txtListQuest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalRisk() {
        return totalRisk;
    }

    public void setTotalRisk(Integer totalRisk) {
        this.totalRisk = totalRisk;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<QuestionReviewRel> getAnswersSel() {
        return answersSel;
    }

    public void setAnswersSel(List<QuestionReviewRel> answersSel) {
        this.answersSel = answersSel;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getTxtListQuest() {
        return txtListQuest;
    }

    public void setTxtListQuest(String txtListQuest) {
        this.txtListQuest = txtListQuest;
    }
}
