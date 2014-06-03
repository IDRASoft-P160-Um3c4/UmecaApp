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

    @JoinColumn(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_arrangement")
    private Arrangement arrangement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hearing_format", nullable = false)
    private HearingFormat hearingFormat;


}
