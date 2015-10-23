package com.umeca.model.entities.shared;

import com.umeca.model.entities.reviewer.SourceVerification;

import javax.persistence.*;

@Entity
@Table(name = "tablet_rel_assignment_source")
public class TabletRelAssignmentSource {

    @Id
    @GeneratedValue
    @Column(name = "tablet_rel_assignment_source")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tablet_assigned_case")
    private TabletAssignmentCase tabletAssignmentCase;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_source_verification")
    private SourceVerification sourceVerification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SourceVerification getSourceVerification() {
        return sourceVerification;
    }

    public void setSourceVerification(SourceVerification sourceVerification) {
        this.sourceVerification = sourceVerification;
    }

    public TabletAssignmentCase getTabletAssignmentCase() {
        return tabletAssignmentCase;
    }

    public void setTabletAssignmentCase(TabletAssignmentCase tabletAssignmentCase) {
        this.tabletAssignmentCase = tabletAssignmentCase;
    }
}
