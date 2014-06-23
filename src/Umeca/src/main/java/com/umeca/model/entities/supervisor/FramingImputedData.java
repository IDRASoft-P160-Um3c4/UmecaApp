package com.umeca.model.entities.supervisor;


import com.umeca.model.catalog.Country;
import com.umeca.model.catalog.MaritalStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="framing_imputed_data")
public class FramingImputedData {

    @Id
    @GeneratedValue
    @Column(name="id_framing_imputed_data")
    private Long id;

    @Column(name = "cel_phone", length = 20)
    private String celPhone;

    @Column(name = "years_marital_status")
    private Integer yearsMaritalStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "date_birth", nullable = false)
    private Date dateBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country")
    private Country birthCountry;

    @Column(name = "birth_place", length = 500)
    private String birthPlace;



}
