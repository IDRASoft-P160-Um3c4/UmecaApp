package com.umeca.model.entities.supervisor;


import javax.persistence.*;

@Entity
@Table(name="hearing_format_imputed")
public class HearingFormatImputed {

    @Id
    @GeneratedValue
    @Column(name="id_hearing_format_imputed")
    private Long id;

    @Column(name="name")
    private String name ;

    @Column(name="last_name_p")
    private String lastNameP;

    @Column(name="last_name_m")
    private String lastNameM;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }
}
