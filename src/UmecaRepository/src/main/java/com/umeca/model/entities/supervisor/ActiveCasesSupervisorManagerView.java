package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("SELECT c.id_case,  \n" +
        "stc.status,  \n" +
        "stc.description,  \n" +
        "c.id_mp,  \n" +
        "CONCAT (im.name, ' ', im.lastname_p, ' ', im.lastname_m ) as fullname,\n" +
        "date_format(im.birth_date,'%Y/%m/%d') birth_date\n" +
        "FROM case_detention c  \n" +
        "INNER JOIN cat_status_case stc on c.id_status = stc.id_status  \n" +
        "INNER JOIN meeting me on me.id_case = c.id_case  \n" +
        "INNER JOIN imputed im on im.id_meeting = me.id_meeting  \n" +
        "WHERE stc.status not in (  \n" +
        "'ST_CASE_CLOSED'  \n" +
        ") group by c.id_case ")

public class ActiveCasesSupervisorManagerView implements EntityGrid {

    @Id
    @Column(name = "id_case")
    private Long id;

    @Column(name = "status")
    private String codeStatus;

    @Column(name = "description")
    private String descStatus;

    @Column(name = "id_mp")
    private String idMP;

    @Column(name = "birth_date")
    private String brthDateTxt;

    @Column(name = "fullname")
    private String fullName;

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

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBrthDateTxt() {
        return brthDateTxt;
    }

    public void setBrthDateTxt(String brthDateTxt) {
        this.brthDateTxt = brthDateTxt;
    }
}
