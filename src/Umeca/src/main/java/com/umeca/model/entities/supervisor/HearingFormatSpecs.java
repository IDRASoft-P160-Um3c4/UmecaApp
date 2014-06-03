package com.umeca.model.entities.supervisor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="hearing_format_specs")
public class HearingFormatSpecs {

    @Id
    @GeneratedValue
    @Column(name = "id_format_specs")
    private Long id;

    @Column(name = "control_detention", nullable = false)
    private Integer controlDetention;

    @Column(name = "imputation_date")
    private Date imputationDate;

    @Column(name = "extension", nullable = false)
    private Integer extension;

    @Column(name = "linkage_date")
    private Date linkageDate;

}
