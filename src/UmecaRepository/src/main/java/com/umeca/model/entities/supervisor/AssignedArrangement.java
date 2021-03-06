package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Arrangement;

import javax.persistence.*;

@Entity
@Table(name = "assigned_arrangement")
public class AssignedArrangement {

    @Id
    @GeneratedValue
    @Column(name = "id_assigned_arrangement")
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_arrangement")
    private Arrangement arrangement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hearing_format", nullable = false)
    private HearingFormat hearingFormat;

    @Column(name = "require_presence", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean requirePresence;

    public boolean isRequirePresence() {
        return requirePresence;
    }

    public void setRequirePresence(boolean requirePresence) {
        this.requirePresence = requirePresence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public HearingFormat getHearingFormat() {
        return hearingFormat;
    }

    public void setHearingFormat(HearingFormat hearingFormat) {
        this.hearingFormat = hearingFormat;
    }
}
