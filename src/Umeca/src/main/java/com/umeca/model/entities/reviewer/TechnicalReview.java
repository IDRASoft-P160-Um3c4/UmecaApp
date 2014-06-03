package com.umeca.model.entities.reviewer;

import javax.persistence.*;
import java.util.HashMap;
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

    @Column(name = "subtotals", length = 3000, nullable = false)
    private String subtotalsTxt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technicalReview", fetch = FetchType.LAZY)
    private List<QuestionReviewRel> questionsSel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case_detention")
    private Case caseDetention;

    @Transient
    private String txtListQuest;

    @Transient
    private Long idVerification;

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

    public List<QuestionReviewRel> getQuestionsSel() {
        return questionsSel;
    }

    public void setQuestionsSel(List<QuestionReviewRel> questionsSel) {
        this.questionsSel = questionsSel;
    }

    public String getTxtListQuest() {
        return txtListQuest;
    }

    public void setTxtListQuest(String txtListQuest) {
        this.txtListQuest = txtListQuest;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public Long getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(Long idVerification) {
        this.idVerification = idVerification;
    }

    public String getSubtotalsTxt() {
        return subtotalsTxt;
    }

    public void setSubtotalsTxt(String subtotalsTxt) {
        this.subtotalsTxt = subtotalsTxt;
    }
}
