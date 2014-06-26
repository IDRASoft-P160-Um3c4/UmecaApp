package com.umeca.model.entities.supervisor;


import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.ImputedHome;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="framing_meeting")
public class FramingMeeting {

    @Id
    @GeneratedValue
    @Column(name="id_framing_meeting")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_framing_occupation")
    private Occupation occupation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_additional_framing_questions")
    private AdditionalFramingQuestions additionalFramingQuestions;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ImputedHome> imputedHomes;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingHousemate> housemates;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingReference> references;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Drug> drugs;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingPhysicalConditionRel> framingPhysicalConditionRel;

    @OneToOne
    @JoinColumn(name = "id_case")
    private Case caseDetention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public AdditionalFramingQuestions getAdditionalFramingQuestions() {
        return additionalFramingQuestions;
    }

    public void setAdditionalFramingQuestions(AdditionalFramingQuestions additionalFramingQuestions) {
        this.additionalFramingQuestions = additionalFramingQuestions;
    }

    public List<ImputedHome> getImputedHomes() {
        return imputedHomes;
    }

    public void setImputedHomes(List<ImputedHome> imputedHomes) {
        this.imputedHomes = imputedHomes;
    }

    public List<FramingHousemate> getHousemates() {
        return housemates;
    }

    public void setHousemates(List<FramingHousemate> housemates) {
        this.housemates = housemates;
    }

    public List<FramingReference> getReferences() {
        return references;
    }

    public void setReferences(List<FramingReference> references) {
        this.references = references;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public List<FramingPhysicalConditionRel> getFramingPhysicalConditionRel() {
        return framingPhysicalConditionRel;
    }

    public void setFramingPhysicalConditionRel(List<FramingPhysicalConditionRel> framingPhysicalConditionRel) {
        this.framingPhysicalConditionRel = framingPhysicalConditionRel;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }
}
