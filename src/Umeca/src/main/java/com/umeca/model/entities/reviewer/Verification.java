package com.umeca.model.entities.reviewer;

import com.umeca.model.catalogs.StatusVerification;
import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 01:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="verification")
public class Verification {

    @Id
    @GeneratedValue
    @Column(name="id_verification")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_reviewer", nullable = true)
    private User reviewer;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_case")
    private Case caseDetention;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_status", nullable = false)
    private StatusVerification status;

    @OneToMany(mappedBy="verification", cascade={CascadeType.ALL})
    private List<SourceVerification> sourceVerifications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public StatusVerification getStatus() {
        return status;
    }

    public void setStatus(StatusVerification status) {
        this.status = status;
    }

    public List<SourceVerification> getSourceVerifications() {
        return sourceVerifications;
    }

    public void setSourceVerifications(List<SourceVerification> sourceVerifications) {
        this.sourceVerifications = sourceVerifications;
    }
}
