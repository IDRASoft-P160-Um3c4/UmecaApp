package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("SELECT c.id_case, " +
        "stc.status, " +
        "stc.description, " +
        "c.id_mp, " +
        "CONCAT (im.name, ' ', im.lastname_p, ' ', im.lastname_m ) as fullname " +
        "FROM case_detention c " +
        "INNER JOIN meeting me on me.id_case = c.id_case " +
        "INNER JOIN imputed im on im.id_meeting = me.id_meeting " +
        "INNER JOIN cat_status_case stc on c.id_status = stc.id_status " +
        "WHERE stc.status in ( " +
        "'ST_CASE_TECHNICAL_REVIEW_COMPLETE', " +
        "'ST_CASE_CONDITIONAL_REPRIEVE', " +
        "'ST_CASE_HEARING_FORMAT_INCOMPLETE', " +
        "'ST_CASE_HEARING_FORMAT_END', " +
        "'ST_CASE_FRAMING_MEETING_INCOMPLETE', " +
        "'ST_CASE_FRAMING_MEETING_COMPLETE', " +
        "'ST_CASE_NOT_PROSECUTE_OPEN' " +
        ") group by c.id_case ")

public class CasesForRequestCloseView implements EntityGrid {

    @Id
    @Column(name = "id_case")
    private Long id;

    @Column(name = "id_mp")
    private String idMP;

    @Column(name = "status")
    private String codeStatus;

    @Column(name = "description")
    private String descStatus;

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
}
