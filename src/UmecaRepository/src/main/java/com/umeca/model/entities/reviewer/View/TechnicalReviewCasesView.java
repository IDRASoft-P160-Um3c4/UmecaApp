package com.umeca.model.entities.reviewer.View;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Subselect("select v.id_verification, "+
        "stc.status, "+
        "stc.description, "+
        "c.id_folder, "+
        "c.id_mp, "+
        "concat(i.name, ' ', i.lastname_p,' ',i.lastname_m) imputed_name, "+
        "usr.id_user "+
        "from verification v "+
        "inner join case_detention c on v.id_case = c.id_case "+
        "inner join cat_status_case stc on c.id_status = stc.id_status "+
        "inner join meeting mv on v.id_meeting = mv.id_meeting "+
        "inner join imputed i on i.id_meeting = mv.id_meeting "+
        "inner join meeting mc on mc.id_case = c.id_case "+
        "inner join user usr on mc.id_reviewer = usr.id_user "+
        "where stc.status in('"+
        Constants.CASE_STATUS_VERIFICATION_COMPLETE+"','"+
        Constants.CASE_STATUS_TECHNICAL_REVIEW+"','"+
        Constants.CASE_STATUS_INCOMPLETE_TECHNICAL_REVIEW+"','"+
        Constants.CASE_STATUS_EDIT_TEC_REV+"')")

public class TechnicalReviewCasesView implements EntityGrid {

    @Id
    @Column(name = "id_verification")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String statusName;

    @Column(name = "id_folder")
    private String idFolder;

    @Column(name = "id_mp")
    private String idMP;

    @Column(name = "imputed_name")
    private String fullName;

    @Column(name = "id_user")
    private Long idUser;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
