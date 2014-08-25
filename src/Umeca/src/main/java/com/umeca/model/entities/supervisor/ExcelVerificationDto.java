package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vmware on 12/08/2014.
 */
public class ExcelVerificationDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    private Long idCase;
    private String idFolder;
    private String idMP;
    private Date createDate;
    private String createDateString;
    private String imputedName;
    private String imputedAlias;
    private String sourceName;
    private String sourceRelationship;
    private Integer age;
    private String sourceAddress;
    private Long idSource;
    private String statusCase;
    private String fieldSourceList;
    private String personalData;
    private String imputedHome;
    private String socialNetwork;
    private String reference;
    private String job;
    private String school;
    private String drug;
    private String leaveCountry;

    public ExcelVerificationDto(Long idCase,
                                String idFolder,
                                String idMP,
                                Date createDate,
                                String imputedName,
                                String imputedAlias,
                                String sourceName,
                                String sourceRelationship,
                                Integer age,
                                String sourceAddress,
                                Long idSource,
                                String statusCase) {
        this.idCase = idCase;
        this.idFolder = idFolder;
        this.idMP = idMP;
        this.createDate = createDate;
        this.imputedName = imputedName;
        this.imputedAlias = imputedAlias;
        this.sourceName = sourceName;
        this.sourceRelationship = sourceRelationship;
        this.age = age;
        this.sourceAddress = sourceAddress;
        this.idSource = idSource;
        this.statusCase = statusCase;
    }

    public String getStatusCase() {
        return statusCase;
    }

    public void setStatusCase(String statusCase) {
        this.statusCase = statusCase;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getImputedName() {
        return imputedName;
    }

    public void setImputedName(String imputedName) {
        this.imputedName = imputedName;
    }

    public String getImputedAlias() {
        return imputedAlias;
    }

    public void setImputedAlias(String imputedAlias) {
        this.imputedAlias = imputedAlias;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceRelationship() {
        return sourceRelationship;
    }

    public void setSourceRelationship(String sourceRelationship) {
        this.sourceRelationship = sourceRelationship;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public String getFieldSourceList() {
        return fieldSourceList;
    }

    public void setFieldSourceList(String fieldSourceList) {
        this.fieldSourceList = fieldSourceList;
    }

    public String getCreateDateString() {
        createDateString = dateFormat.format(this.createDate);
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    public String getPersonalData() {
        return personalData;
    }

    public void setPersonalData(String personalData) {
        this.personalData = personalData;
    }

    public String getImputedHome() {
        return imputedHome;
    }

    public void setImputedHome(String imputedHome) {
        this.imputedHome = imputedHome;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getLeaveCountry() {
        return leaveCountry;
    }

    public void setLeaveCountry(String leaveCountry) {
        this.leaveCountry = leaveCountry;
    }
}

