package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Arrangement;
import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

@Entity
@Table(name="obligation_issues")
public class ObligationIssues {

    @Id
    @GeneratedValue
    @Column(name="id_obligation_issues")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_additional_framing_question")
    private AdditionalFramingQuestions additionalFramingQuestions;

    @OneToOne
    @JoinColumn(name = "id_arrangement")
    private Arrangement arrangement;

    @Column(name = "cause")
    private String cause;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdditionalFramingQuestions getAdditionalFramingQuestions() {
        return additionalFramingQuestions;
    }

    public void setAdditionalFramingQuestions(AdditionalFramingQuestions additionalFramingQuestions) {
        this.additionalFramingQuestions = additionalFramingQuestions;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
