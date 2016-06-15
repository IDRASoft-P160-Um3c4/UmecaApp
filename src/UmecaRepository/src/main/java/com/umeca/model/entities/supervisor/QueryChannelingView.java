package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("SELECT c.id_case, c.id_mp,d.name as district, concat(i.name,' ',i.lastname_p,' ',i.lastname_m) as imputed, us.fullname as supervisor FROM case_detention c\n" +
        "INNER JOIN cat_status_case stc on c.id_status = stc.id_status\n" +
        "INNER JOIN meeting m on m.id_case =c.id_case\n" +
        "INNER JOIN imputed i on i.id_meeting =m.id_meeting\n" +
        "INNER JOIN cat_district d on c.id_district = d.id_district\n" +
        "INNER JOIN framing_meeting fm on fm.id_case = c.id_case\n" +
        "INNER JOIN user us on fm.id_user = us.id_user\n" +
        "WHERE (SELECT count(*) FROM channeling ch where ch.id_case = c.id_case) > 0")

public class QueryChannelingView implements EntityGrid {

    @Id
    @Column(name = "id_case")
    private Long id;

    @Column(name = "id_mp")
    private String idMP;

    @Column(name = "district")
    private String district;

    @Column(name = "imputed")
    private String imputed;

    @Column(name = "supervisor")
    private String supervisor;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }
}
