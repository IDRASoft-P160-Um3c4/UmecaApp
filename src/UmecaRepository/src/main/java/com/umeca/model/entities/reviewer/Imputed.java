package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Country;
import com.umeca.model.catalog.InformationAvailability;
import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.MaritalStatus;
import com.umeca.model.entities.reviewer.dto.GroupMessageMeetingDto;
import com.umeca.model.entities.reviewer.dto.TerminateMeetingMessageDto;
import com.umeca.model.shared.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 01:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "imputed")
public class Imputed {
    @Id
    @GeneratedValue
    @Column(name = "id_imputed")
    protected Long id;

    @Column(name = "name", length = 50, nullable = false)
    protected String name;

    @Column(name = "lastname_p", length = 50, nullable = false)
    protected String lastNameP;

    @Column(name = "lastname_m", length = 50, nullable = false)
    protected String lastNameM;

    @Column(name = "fonetic_string", length = 150, nullable = false)
    protected String foneticString;

    @Column(name = "gender", nullable = true)
    protected Boolean gender;

    @Column(name = "birth_date", nullable = false)
    protected Date birthDate;

    @Column(name = "cel_phone", length = 20, nullable = true)
    protected String celPhone;

    @Column(name = "years_marital_status", nullable = true)
    protected String yearsMaritalStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marital_status", nullable = true)
    protected MaritalStatus maritalStatus;

    @Column(name = "boys", nullable = true)
    protected Integer boys;

    @Column(name = "dependent_boys", nullable = true)
    protected Integer dependentBoys;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country", nullable = true)
    protected Country birthCountry;

    @Column(name = "birth_municipality", nullable = true, length = 500)
    protected String birthMunicipality;

    @Column(name = "birth_state", nullable = true, length = 500)
    protected String birthState;

    @Column(name = "birth_location", nullable = true, length = 500)
    protected String birthLocation;

    @Column(name = "nickname", length = 100, nullable = true)
    protected String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", nullable = true)
    protected Location location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_birth_info_availability",nullable = true)
    protected InformationAvailability birthInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_meeting", nullable = false)
    protected Meeting meeting;

    @Column(name = "isFromFomulation", nullable = true)
    protected Boolean isFromFormulation;

    @Transient
    protected Long formulationId;

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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) {
        this.celPhone = celPhone;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getYearsMaritalStatus() {
        return yearsMaritalStatus;
    }

    public void setYearsMaritalStatus(String yearsMaritalStatus) {
        this.yearsMaritalStatus = yearsMaritalStatus;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getBoys() {
        return boys;
    }

    public void setBoys(Integer boys) {
        this.boys = boys;
    }

    public Integer getDependentBoys() {
        return dependentBoys;
    }

    public void setDependentBoys(Integer dependentBoys) {
        this.dependentBoys = dependentBoys;
    }

    public Country getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(Country birthCountry) {
        this.birthCountry = birthCountry;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthMunicipality() {
        return birthMunicipality;
    }

    public void setBirthMunicipality(String birthMunicipality) {
        this.birthMunicipality = birthMunicipality;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public String getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getIsFromFormulation() {
        return isFromFormulation;
    }

    public void setIsFromFormulation(Boolean isFromFormulation) {
        this.isFromFormulation = isFromFormulation;
    }

    public void validateMeeting(TerminateMeetingMessageDto t) {
        List<String> result = new ArrayList<>();
        String e = "entity";
        if (this.name == null || (this.name != null && this.name.trim().equals(""))) {
            result.add(t.template.replace(e, "El nombre"));
        }
        if (this.lastNameP == null || (this.lastNameP != null && this.lastNameP.trim().equals(""))) {
            result.add(t.template.replace(e, "El apellido paterno"));
        }
        if (this.lastNameM == null || (this.lastNameM != null && this.lastNameM.trim().equals(""))) {
            result.add(t.template.replace(e, "El apellido materno"));
        }
        if (this.gender == null) {
            result.add(t.template.replace(e, "El g&eacute;nero"));
        }
        if (this.celPhone == null || (this.celPhone != null && this.celPhone.trim().equals(""))) {
            result.add(t.template.replace(e, "El n&uacute;mero celular"));
        }
        if (maritalStatus == null || (maritalStatus.getId() == null)) {
            result.add(t.template.replace(e, "El estado civil"));
        } else if ((maritalStatus.getId().equals(Constants.MARITAL_MARRIED) || maritalStatus.getId().equals(Constants.MARITAL_UNION_FREE))
                && yearsMaritalStatus == null) {
            result.add(t.template.replace(e, "El n&uacute;mero de años en el estado civil"));
        }
        if (boys == null) {
            result.add(t.template.replace(e, "El total de hijos"));
        }

        if (nickname == null || (nickname != null && nickname.equals(""))) {
            result.add(t.template.replace(e, "El ap&oacute;do"));
        }
        if (dependentBoys == null) {
            result.add(t.template.replace(e, "El n&uacute;mero de dependientes econ&oacute;micos"));
        }

        if (birthInfo == null) {
            result.add(t.template.replace(e, "Lugar de nacimiento"));
        } else if (birthInfo.getSpecification() == true) {
            if (birthCountry == null) {
                result.add(t.template.replace(e, "El pa&iacute;s de nacimiento"));
            } else {
                if (birthCountry.getAlpha2().equals(Constants.ALPHA2_MEXICO)) {
                    if (location == null || (location != null && location.getId() == null)) {
                        result.add(t.template.replace(e, "La localidad"));
                    }
                } else {
                    if (birthMunicipality == null || (birthMunicipality != null && birthMunicipality.trim().equals(""))) {
                        result.add(t.template.replace(e, "El municipio de nacimiento"));
                    }
                    if (birthState == null || (birthState != null && birthState.trim().equals(""))) {
                        result.add(t.template.replace(e, "El estado de naciemiento"));
                    }
                    if (birthLocation == null || (birthLocation != null && birthLocation.trim().equals(""))) {
                        result.add(t.template.replace(e, "La ciudad o localidad de nacimiento"));
                    }
                }
            }
        }
        t.getGroupMessage().add(new GroupMessageMeetingDto("personalData", result));
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFoneticString() {
        return foneticString;
    }

    public void setFoneticString(String foneticString) {
        this.foneticString = foneticString;
    }

    public InformationAvailability getBirthInfo() {
        return birthInfo;
    }

    public void setBirthInfo(InformationAvailability birthInfo) {
        this.birthInfo = birthInfo;
    }

    public ImputedInitial cloneObj() {
        ImputedInitial imp = new ImputedInitial();
        imp.setId(id);
        imp.setBirthCountry(birthCountry);
        imp.setBirthDate(birthDate);
        imp.setBirthLocation(birthLocation);
        imp.setName(name);
        imp.setBirthMunicipality(this.birthMunicipality);
        imp.setBirthState(birthState);
        imp.setBoys(boys);
        imp.setCelPhone(celPhone);
        imp.setDependentBoys(dependentBoys);
        imp.setFoneticString(foneticString);
        imp.setGender(gender);
        imp.setLastNameM(lastNameM);
        imp.setLastNameP(lastNameP);
        imp.setLocation(location);
        imp.setMaritalStatus(maritalStatus);
        imp.setMeeting(meeting);
        imp.setNickname(nickname);
        imp.setYearsMaritalStatus(yearsMaritalStatus);
        imp.setBirthInfo(birthInfo);
        return imp;
    }

    public Long getFormulationId() {
        return formulationId;
    }

    public void setFormulationId(Long formulationId) {
        this.formulationId = formulationId;
    }
}

