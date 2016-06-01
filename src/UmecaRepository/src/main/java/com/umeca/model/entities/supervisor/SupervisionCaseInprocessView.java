package com.umeca.model.entities.supervisor;

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("select \n" +
        "cd.id_case as idCase,\n" +
        "cd.id_mp as idMP,\n" +
        "CONCAT ( imp.name, \" \", imp.lastname_p, \" \", imp.lastname_m ) as fullname,\n" +
        "fm.id_framing_meeting as idFM,\n" +
        "hf.id_hearing_format as idFH,\n" +
        "mp.id_monitoring_plan as idMonP,\n" +
        "ver.id_verification as idTec,\n" +
        "fm.is_terminated as fmTerminated,\n" +
        "usr.fullname as userName,\n" +
        "case mp.resolution\n" +
        "\twhen 1 then \"MC\"\n" +
        "    when 2 then \"SCPP\"\n" +
        "end as resolution\n" +
        "from case_detention cd\n" +
        "inner join meeting me on cd.id_case = me.id_case\n" +
        "inner join imputed imp on me.id_meeting = imp.id_meeting\n" +
        "left join verification ver on cd.id_case = ver.id_case\n" +
        "left join framing_meeting fm on cd.id_case = fm.id_case\n" +
        "left join hearing_format hf on cd.id_case = hf.id_case\n" +
        "left join monitoring_plan mp on cd.id_case = mp.id_case\n" +
        "left join user usr on mp.id_user_supervisor = usr.id_user\n" +
        "inner join cat_status_case sc on cd.id_status = sc.id_status\n" +
        "where sc.status in \n" +
        "(\"ST_CASE_TECHNICAL_REVIEW_COMPLETE\",\n" +
        "\"ST_CASE_CONDITIONAL_REPRIEVE\",\n" +
        "\"ST_CASE_HEARING_FORMAT_INCOMPLETE\",\n" +
        "\"ST_CASE_HEARING_FORMAT_END\",\n" +
        "\"ST_CASE_FRAMING_MEETING_INCOMPLETE\",\n" +
        "\"ST_CASE_FRAMING_MEETING_COMPLETE\")\n" +
        "group by cd.id_case\n" +
        "\n")




public class SupervisionCaseInProcessView {
    @Id
    @Column(name = "id_case")
    private Long idCase;

    @Column(name = "id_mp")
    private String idMP;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "number")
    private String number;

    @Column(name = "id_fm")
    private Long idFM;

    @Column(name = "id_hf")
    private Long idHF;

    @Column(name = "id_mon_p")
    private Long idMonP;

    @Column(name = "id_tec")
    private Long idTec;

    @Column(name = "fm_terminated")
    private boolean fmTerminated;

    @Column(name = "username")
    private String userName;

    @Column(name = "resolution")
    private Integer resolution;

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getIdFM() {
        return idFM;
    }

    public void setIdFM(Long idFM) {
        this.idFM = idFM;
    }

    public Long getIdHF() {
        return idHF;
    }

    public void setIdHF(Long idHF) {
        this.idHF = idHF;
    }

    public Long getIdMonP() {
        return idMonP;
    }

    public void setIdMonP(Long idMonP) {
        this.idMonP = idMonP;
    }

    public Long getIdTec() {
        return idTec;
    }

    public void setIdTec(Long idTec) {
        this.idTec = idTec;
    }

    public boolean isFmTerminated() {
        return fmTerminated;
    }

    public void setFmTerminated(boolean fmTerminated) {
        this.fmTerminated = fmTerminated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }
}
