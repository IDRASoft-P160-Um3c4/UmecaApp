package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Election;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 03:43 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "previous_criminal_proceeding")
public class PreviousCriminalProceeding {

    @Id
    @GeneratedValue
    @Column(name = "id_previous_criminal_proceeding")
    private Long id;

    @Column(name = "first_proceeding", length = 255, nullable = false)
    private String firstProceeding;

    @Column(name = "open_process_number", nullable = false)
    private Integer openProcessNumber;

    @Column(name = "number_convictions", nullable = false)
    private Integer numberConvictions;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_comply_measures", nullable = false)
    private Election complyPrecautionaryMeasures;

    @Column(name = "source_information", length = 255, nullable = false)
    private String sourceInformation;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

}
