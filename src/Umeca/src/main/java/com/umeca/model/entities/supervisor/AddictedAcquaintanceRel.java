package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

@Entity
@Table(name="addicted_acquaintance_rel")
public class AddictedAcquaintanceRel {

    @Id
    @GeneratedValue
    @Column(name="id_addicted_acquaintance_rel")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_additional_framing_question")
    private AdditionalFramingQuestions additionalFramingQuestions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_relationship")
    private Relationship relationship;

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

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
}
