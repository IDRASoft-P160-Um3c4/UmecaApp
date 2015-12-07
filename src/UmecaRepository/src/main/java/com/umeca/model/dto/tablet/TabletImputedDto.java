package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletCountryDto;
import com.umeca.model.dto.tablet.catalog.TabletLocationDto;
import com.umeca.model.dto.tablet.catalog.TabletMaritalStatusDto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabletImputedDto {

    public TabletImputedDto() {

    }

    public TabletImputedDto(Long id, String name, String lastNameP, String lastNameM, String foneticString, Boolean gender, Date birthDate, String celPhone, String yearsMaritalStatus, Integer boys, Integer dependentBoys, String birthMunicipality, String birthState, String birthLocation, String nickname) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.webId = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.foneticString = foneticString;
        this.gender = gender;
        this.birthDate = birthDate == null ? null : sdf.format(birthDate);
        this.celPhone = celPhone;
        this.yearsMaritalStatus = yearsMaritalStatus;
        this.boys = boys;
        this.dependentBoys = dependentBoys;
        this.birthMunicipality = birthMunicipality;
        this.birthState = birthState;
        this.birthLocation = birthLocation;
        this.nickname = nickname;
    }

    private Long webId;
    private Long id;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String foneticString;
    private Boolean gender;
    private String birthDate;
    private String celPhone;
    private String yearsMaritalStatus;
    private Integer boys;
    private Integer dependentBoys;
    private String birthMunicipality;
    private String birthState;
    private String birthLocation;
    private String nickname;
    private TabletMaritalStatusDto maritalStatus;
    private TabletCountryDto birthCountry;
    private TabletLocationDto location;

    private Integer birthInfoId;

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

    public String getFoneticString() {
        return foneticString;
    }

    public void setFoneticString(String foneticString) {
        this.foneticString = foneticString;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) {
        this.celPhone = celPhone;
    }

    public String getYearsMaritalStatus() {
        return yearsMaritalStatus;
    }

    public void setYearsMaritalStatus(String yearsMaritalStatus) {
        this.yearsMaritalStatus = yearsMaritalStatus;
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

    public TabletMaritalStatusDto getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(TabletMaritalStatusDto maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public TabletCountryDto getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(TabletCountryDto birthCountry) {
        this.birthCountry = birthCountry;
    }

    public TabletLocationDto getLocation() {
        return location;
    }

    public void setLocation(TabletLocationDto location) {
        this.location = location;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    public Integer getBirthInfoId() {
        return birthInfoId;
    }

    public void setBirthInfoId(Integer birthInfoId) {
        this.birthInfoId = birthInfoId;
    }
}


