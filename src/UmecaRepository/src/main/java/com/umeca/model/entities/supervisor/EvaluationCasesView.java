package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("select c.id_case,\n" +
        "v.id_verification,\n" +
        "c.id_folder,\n" +
        "concat(i.name,' ',i.lastname_p,' ',i.lastname_m) as imputed_name,\n" +
        "stm.status as status_meeting,\n" +
        "stv.status status_verification,\n" +
        "usr.fullname as user_name,\n" +
        "tr.id_technical_review,\n" +
        "usr.id_user,\n" +
        "stc.status as status_case_code, " +
        "0 as status, " +
        "'' as status_string\n" +
        "from meeting m\n" +
        "join case_detention c on m.id_case = c.id_case\n" +
        "join cat_status_case stc on c.id_status = stc.id_status\n" +
        "join imputed i on i.id_meeting = m.id_meeting\n" +
        "join cat_status_meeting stm on m.id_status = stm.id_status\n" +
        "join user usr on usr.id_user = m.id_reviewer\n" +
        "left join verification v on v.id_case=c.id_case\n" +
        "left join cat_status_verification stv on v.id_status = stv.id_status\n" +
        "left join technical_review tr on tr.id_case_detention = c.id_case\n" +
        "where stm.status in ('INCOMPLETE_LEGAL', 'COMPLETE', 'INCOMPLETE', 'DECLINE', 'OBSOLETE')\n" +
        "order by c.id_case asc\n")

public class EvaluationCasesView implements EntityGrid {

    @Id
    @Column(name = "id_case")
    private Long id;

    @Column(name = "id_verification")
    private Long idVerif;

    @Column(name = "id_folder")
    private String idFolder;

    @Column(name = "imputed_name")
    private String fullname;

    @Column(name = "status_meeting")
    private String statusMeeting;

    @Column(name = "status_verification")
    private String statusVerification;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "id_technical_review")
    private Long idTec;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "status_case_code")
    private String statusCaseCode;

    @Column(name = "status")
    private Integer status;

    @Column(name = "status_string")
    private String statusString;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVerif() {
        return idVerif;
    }

    public void setIdVerif(Long idVerif) {
        this.idVerif = idVerif;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatusMeeting() {
        return statusMeeting;
    }

    public void setStatusMeeting(String statusMeeting) {
        this.statusMeeting = statusMeeting;
    }

    public String getStatusVerification() {
        return statusVerification;
    }

    public void setStatusVerification(String statusVerification) {
        this.statusVerification = statusVerification;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusString() {

        if (statusMeeting.equals(Constants.S_MEETING_INCOMPLETE)) {
            statusString = "Entrevista de riesgos procesales incompleta";
        } else {
            if(statusMeeting.equals(Constants.S_MEETING_DECLINE)){
                statusString = "Entrevista negada";
            }
            if (statusMeeting.equals(Constants.S_MEETING_INCOMPLETE_LEGAL)) {
                statusString = "Por agregar informaci&oacute;n legal";
            } else if (statusMeeting.equals(Constants.S_MEETING_COMPLETE)) {
                statusString = "Entrevista completa";
            }
            if (statusVerification != null && statusVerification.equals(Constants.VERIFICATION_STATUS_COMPLETE)) {
                statusString = "Verificaci&oacute;n  terminada";
            }
            if (idTec != null) {
                if(statusCaseCode!=null && statusCaseCode.equals(Constants.CASE_STATUS_INCOMPLETE_TECHNICAL_REVIEW)) {
                    statusString = "Opini&oacute;n t&eacute;cnica incompleta";
                }else if(statusCaseCode!=null && statusCaseCode.equals(Constants.CASE_STATUS_TECHNICAL_REVIEW)) {
                    statusString = "Opini&oacute;n t&eacute;cnica terminada";
                }
            }
        }
        if(statusMeeting.equals(Constants.S_MEETING_OBSOLETE)){
            statusString = "Eliminado";
        }


        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public Long getIdTec() {
        return idTec;
    }

    public void setIdTec(Long idTec) {
        this.idTec = idTec;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getStatusCaseCode() {
        return statusCaseCode;
    }

    public void setStatusCaseCode(String statusCaseCode) {
        this.statusCaseCode = statusCaseCode;
    }

    public Integer getStatus() {

            if(statusMeeting.equals(Constants.S_MEETING_DECLINE)){
                status++;
            }
            if (statusMeeting.equals(Constants.S_MEETING_INCOMPLETE_LEGAL)) {
                status++;
            } else if (statusMeeting.equals(Constants.S_MEETING_COMPLETE)) {
                status += 2;
            }
            if (statusVerification != null && statusVerification.equals(Constants.VERIFICATION_STATUS_COMPLETE)) {
                status++;
            }
            if (idTec != null) {
                status++;
            }

        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

