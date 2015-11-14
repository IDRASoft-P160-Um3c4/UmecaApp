package com.umeca.model.entities.supervisor;

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Subselect("select\n" +
        "\t\tcaseD.id_case,\n" +
        "        statusC.status,\n" +
        "        statusC.description,\n" +
        "        caseD.id_mp,\n" +
        "        imput.name,\n" +
        "        imput.lastname_p,\n" +
        "        imput.lastname_m,\n" +
        "        imput.birth_date,\n" +
        "        heaF.idHearingFormat,\n" +
        "        heaF.arrangementType\n" +
        "    from\n" +
        "        case_detention caseD \n" +
        "\tinner join\n" +
        "        cat_status_case statusC on caseD.id_status = statusC.id_status\n" +
        "\tinner join\n" +
        "\t\tmeeting  meet on caseD.id_case =  meet.id_case\n" +
        "\tinner join\n" +
        "\t\timputed imput on meet.id_meeting =  imput.id_meeting\n" +
        "\tinner join\n" +
        "        user usr \n" +
        "            on caseD.id_umeca_supervisor = usr.id_user\n" +
        "    inner join\n" +
        "        (select\n" +
        "\t\t\tcaseD.id_case idCase,\n" +
        "\t\t\tmax(heaF.id_hearing_format) idHearingFormat,\n" +
        "\t\t\theaFS.arrangement_type arrangementType\n" +
        "\t\tfrom\n" +
        "\t\t\tcase_detention caseD \n" +
        "\t\tinner join\n" +
        "\t\t\thearing_format heaF on caseD.id_case = heaF.id_case\n" +
        "\t\tinner join\n" +
        "\t\t\thearing_format_specs heaFS on heaF.id_format_specs = heaFS.id_format_specs\n" +
        "\t\tgroup by \n" +
        "\t\t\tcaseD.id_case\n" +
        "\t\torder by\n" +
        "\t\t\theaF.id_hearing_format desc) heaF on caseD.id_case = heaF.idCase\n" +
        "    where \n" +
        "        statusC.status in('ST_CASE_HEARING_FORMAT_END', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_FRAMING_MEETING_INCOMPLETE')\n" +
        "    order by\n" +
        "        caseD.id_mp desc")
public class ForFramingMeetingGridView {
    @Id
    @Column(name = "id_case")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "id_mp")
    private String idMP;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname_p")
    private String lastNameP;

    @Column(name = "lastname_m")
    private String lastNameM;

    @Column(name = "idHearingFormat")
    private Long idHearingFormat;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "arrangementType")
    private Integer arrangementType;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getIdHearingFormat() {
        return idHearingFormat;
    }

    public void setIdHearingFormat(Long idHearingFormat) {
        this.idHearingFormat = idHearingFormat;
    }


    public Integer getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(Integer arrangementType) {
        this.arrangementType = arrangementType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }
}
