package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Relationship;
import com.umeca.model.shared.EntityGrid;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 06:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="source_verification")
public class SourceVerification implements EntityGrid{

    public SourceVerification() {
    }

    public SourceVerification(Long id,String fullName, Integer age, String relationshipString, String address, String phone, Boolean isAuthorized, Date dateComplete, Long idCase, Boolean visible) {
        this.id =  id;
        this.fullName = fullName;
        this.age = age;
        this.relationshipString = relationshipString;
        this.address = address;
        this.phone = phone;
        this.isAuthorized = isAuthorized;
        this.dateComplete = dateComplete;
        if(dateComplete!=null){
           this.statusString ="Entrevista de verificaci&aacute;n terminada";
        }else{
            this.statusString = "Entrevista de verificaci&aacute;n incompleta";
        }
        this.idCase = idCase;
        this.visible = visible;
    }

    public SourceVerification(Long id,String fullName, String relationshipString, String phone, String address, Long idVerificationMethod, Boolean isAuthorized, Long idCase) {
        this.id =  id;
        this.fullName = fullName;
        this.relationshipString = relationshipString;
        this.address = address;
        this.phone = phone;
        this.idVerificationMethod = idVerificationMethod;
        this.isAuthorized = isAuthorized;
        this.idCase = idCase;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_source_verification")
    private Long id;

    @Column(name="name", length = 150, nullable = false)
    private String fullName;

    @Column(name="age", nullable = false)
    private Integer age;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_relationship", nullable = true)
    private Relationship relationship;

    @Transient
    private String relationshipString;

    @Column(name="address", length = 500, nullable = false)
    private String address;

    @Column(name="phone", length = 200, nullable =false)
    private String phone;

    @Column(name="isAuthorized", nullable = false)
    private Boolean isAuthorized;

    @Column(name="date_complete", nullable = true)
    private Date dateComplete;

    @Transient
    private String statusString;

    @Column(name="dateAuthorized", nullable=true)
    private Date dateAuthorized;

    @Transient
    private Long idCase;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_verification_method", nullable = true)
    private VerificationMethod verificationMethod;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_request", nullable = true)
    private CaseRequest caseRequest;

    public Long getIdVerificationMethod() {
        return idVerificationMethod;
    }

    public void setIdVerificationMethod(Long idVerificationMethod) {
        this.idVerificationMethod = idVerificationMethod;
    }

    @Transient
    private Long idVerificationMethod;

    @Column(name="visible")
    private Boolean visible;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_verification", nullable = false)
    private Verification verification;

    @OneToMany(mappedBy="sourceVerification", cascade={CascadeType.ALL})
    @OrderBy("id_field.sectionCode")
    private List<FieldMeetingSource> fieldMeetingSourceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(Boolean authorized) {
        isAuthorized = authorized;
    }

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    public Date getDateAuthorized() {
        return dateAuthorized;
    }

    public void setDateAuthorized(Date dateAuthorized) {
        this.dateAuthorized = dateAuthorized;
    }

    public VerificationMethod getVerificationMethod() {
        return verificationMethod;
    }

    public void setVerificationMethod(VerificationMethod verificationMethod) {
        this.verificationMethod = verificationMethod;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public List<FieldMeetingSource> getFieldMeetingSourceList() {
        return fieldMeetingSourceList;
    }

    public void setFieldMeetingSourceList(List<FieldMeetingSource> fieldMeetingSourceList) {
        this.fieldMeetingSourceList = fieldMeetingSourceList;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getRelationshipString() {
        return relationshipString;
    }

    public void setRelationshipString(String relationshipString) {
        this.relationshipString = relationshipString;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getStatusString() {

        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public Boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(Boolean authorized) {
        isAuthorized = authorized;
    }

    public CaseRequest getCaseRequest() {
        return caseRequest;
    }

    public void setCaseRequest(CaseRequest caseRequest) {
        this.caseRequest = caseRequest;
    }
}
