package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.SourceVerification;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tablet_assignment_cases")
public class TabletAssignmentCase {

    @Id
    @GeneratedValue
    @Column(name = "id_tablet_assigned_cases")
    private Long id;

    @Column(name = "assignment_date")
    private Calendar assignmentDate;

    @Column(name = "synchronized_date")
    private Calendar synchronizedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case")
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User assignedUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_source")
    private SourceVerification sourceVerification;

    @Column(name = "is_obsolete")
    private Boolean isObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Calendar assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Calendar getSynchronizedDate() {
        return synchronizedDate;
    }

    public void setSynchronizedDate(Calendar synchronizedDate) {
        this.synchronizedDate = synchronizedDate;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public SourceVerification getSourceVerification() {
        return sourceVerification;
    }

    public void setSourceVerification(SourceVerification sourceVerification) {
        this.sourceVerification = sourceVerification;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
