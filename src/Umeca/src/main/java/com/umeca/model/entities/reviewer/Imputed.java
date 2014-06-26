package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Country;
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
@Table(name="imputed")
public class Imputed { @Id
    @GeneratedValue
    @Column(name="id_imputed")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="lastname_p", length = 50, nullable = false)
    private String lastNameP;

    @Column(name="lastname_m", length = 50, nullable = false)
    private String lastNameM;

    @Column(name="gender", nullable = true)
    private Boolean gender;

    @Column(name="birth_date", nullable = false)
    private Date birthDate;

    @Column(name="cel_phone", length = 20, nullable = true)
    private String celPhone;

    @Column(name="years_marital_status", nullable = true)
    private Integer yearsMaritalStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_marital_status", nullable = true)
    private MaritalStatus maritalStatus;

    @Column(name="boys", nullable = true)
    private Integer boys;

    @Column(name="dependent_boys", nullable = true)
    private Integer dependentBoys;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_country", nullable = true)
    private Country birthCountry;

    @Column(name="birth_municipality", nullable = true, length = 500)
    private String birthMunicipality;

    @Column(name="birth_state", nullable = true, length = 500)
    private String birthState;

    @Column(name="birth_location", nullable = true, length = 500)
    private String birthLocation;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

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

    public Integer getYearsMaritalStatus() {
        return yearsMaritalStatus;
    }

    public void setYearsMaritalStatus(Integer yearsMaritalStatus) {
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

    public  void validateMeeting(TerminateMeetingMessageDto t){
        List<String> result = new ArrayList<>();
        String e="entity";
        if(this.gender ==null){
            result.add(t.template.replace(e,"El género"));
        }
        if(this.celPhone==null||(this.celPhone!=null && this.celPhone.trim().equals(""))){
            result.add(t.template.replace(e,"El número celular"));
        }
        if(maritalStatus==null||(maritalStatus.getId()==null)){
            result.add(t.template.replace(e,"El estado civil"));
        }else if((maritalStatus.getId().equals(Constants.MARITAL_MARRIED) || maritalStatus.getId().equals(Constants.MARITAL_UNION_FREE))
                && yearsMaritalStatus==null){
            result.add(t.template.replace(e,"El número de años"));
        }
        if(boys==null){
            result.add(t.template.replace(e,"El total de hijos"));
        }
        if(dependentBoys==null){
            result.add(t.template.replace(e,"El número de dependientes económicos"));
        }
        if(birthCountry==null){
            result.add(t.template.replace(e,"El país de nacimiento"));
        }
        if(birthMunicipality==null || (birthMunicipality!= null && birthMunicipality.trim().equals(""))){
            result.add(t.template.replace(e,"El municipio de nacimiento"));
        }
        if(birthState==null ||(birthState!=null && birthState.trim().equals(""))){
            result.add(t.template.replace(e,"El estado de naciemiento"));
        }
        if(birthLocation ==null ||(birthLocation != null && birthLocation.trim().equals(""))){
            result.add(t.template.replace(e,"La ciudad o localidad de nacimiento"));
        }
        t.getGroupMessage().add(new GroupMessageMeetingDto("personalData",result));
    }
}


