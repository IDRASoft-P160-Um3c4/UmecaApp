package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Relationship;

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
public class SourceVerification {

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

    @Column(name="address", length = 500, nullable = false)
    private String address;

    @Column(name="phone", length = 20, nullable =false)
    private String phone;

    @Column(name="isAuthorized", nullable = false)
    private Boolean isAuthorized;

    @Column(name="date_complete", nullable = true)
    private Date dateComplete;

    @Column(name="dateAuthorized", nullable=true)
    private Date dateAuthorized;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_verification_method", nullable = true)
    private VerificationMethod verificationMethod;

    @Column(name="visible")
    private Boolean visible;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_verification", nullable = false)
    private Verification verification;

    @OneToMany(mappedBy="sourceVerification", cascade={CascadeType.ALL})
    private List<MeetingSource> meetingSourceList;

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

    public List<MeetingSource> getMeetingSourceList() {
        return meetingSourceList;
    }

    public void setMeetingSourceList(List<MeetingSource> meetingSourceList) {
        this.meetingSourceList = meetingSourceList;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
