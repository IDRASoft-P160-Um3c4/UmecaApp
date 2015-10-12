package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "tablet_assignment_case")
public class TabletAssignmentCase {

    @Id
    @GeneratedValue
    @Column(name = "id_tablet_assignment_case")
    private Long id;

    @Column(name = "assignment_date")
    private Calendar assignmentDate;

    @Column(name = "synchronized_date")
    private Calendar synchronizedDate;

    @Column(name = "downloaded_date")
    private Calendar downloadedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case")
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User assignedUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tabletAssignmentCase", cascade = CascadeType.ALL)
    private List<TabletRelAssignmentSource> listRelAssignedSources;

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

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Calendar getDownloadedDate() {
        return downloadedDate;
    }

    public void setDownloadedDate(Calendar downloadedDate) {
        this.downloadedDate = downloadedDate;
    }

    public List<TabletRelAssignmentSource> getListRelAssignedSources() {
        return listRelAssignedSources;
    }

    public void setListRelAssignedSources(List<TabletRelAssignmentSource> listRelAssignedSources) {
        this.listRelAssignedSources = listRelAssignedSources;
    }
}
