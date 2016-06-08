package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("select \n" +
        "cd.id_case ,\n" +
        "cd.id_folder ,\n" +
        "CONCAT ( imp.name, \" \", imp.lastname_p, \" \", imp.lastname_m ) as fullname,\n" +
        "fm.id_framing_meeting ,\n" +
        "hf.id_hearing_format, \n" +
        "mp.id_monitoring_plan ,\n" +
        "ver.id_verification ,\n" +
        "coalesce(fm.is_terminated,false) as is_terminated ,\n" +
        "usr.fullname as userName,\n" +
        "case mp.resolution\n" +
        "\twhen 1 then \"MC\"\n" +
        "    when 2 then \"SCPP\"\n" +
        "end as resolution, \n" +
        "mp.id_user_supervisor,\n" +
        "date_format(cd.date_create, '%Y/%m/%d') as registerDate,\n" +
        "coalesce(dist.name, '') as districtName \n" +
        "from case_detention cd\n" +
        "inner join meeting me on cd.id_case = me.id_case\n" +
        "inner join imputed imp on me.id_meeting = imp.id_meeting\n" +
        "left join verification ver on cd.id_case = ver.id_case\n" +
        "left join framing_meeting fm on cd.id_case = fm.id_case\n" +
        "left join hearing_format hf on cd.id_case = hf.id_case\n" +
        "left join monitoring_plan mp on cd.id_case = mp.id_case\n" +
        "left join user usr on mp.id_user_supervisor = usr.id_user\n" +
        "left join cat_district dist on cd.id_district = dist.id_district\n"+
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

public class SupervisionCaseInprocessView implements EntityGrid {


    @Id
    @Column(name = "id_case")
    private Long id;

    @Column(name = "id_folder")
    private String idFolder;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "id_framing_meeting")
    private Long idFM;

    @Column(name = "id_hearing_format")
    private Long idHF;

    @Column(name = "id_monitoring_plan")
    private Long idMonP;

    @Column(name = "id_verification")
    private Long idTec;

    @Column(name = "userName")
    private String userName;

    @Column(name = "resolution")
    private String resolutionStr;

    @Column(name = "is_terminated")
    private Boolean fmTerminated;

    @Column(name = "id_user_supervisor")
    private Long supervisorId;

    @Column(name = "registerDate")
    private String registerDate;

    @Column(name = "districtName")
    private String districtName;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResolutionStr() {
        return resolutionStr;
    }

    public void setResolutionStr(String resolutionStr) {
        this.resolutionStr = resolutionStr;
    }

    public Boolean getFmTerminated() {
        return fmTerminated;
    }

    public void setFmTerminated(Boolean fmTerminated) {
        this.fmTerminated = fmTerminated;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
