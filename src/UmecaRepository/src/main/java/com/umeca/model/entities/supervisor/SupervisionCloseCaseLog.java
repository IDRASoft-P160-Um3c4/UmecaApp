package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "supervision_close_case_log")
public class SupervisionCloseCaseLog {

    @Id
    @GeneratedValue
    @Column(name = "id_supervision_close_case_log")
    private Long id;

    @Column(name = "close_date")
    private Calendar closeDate;

    @Column(name = "reopen_date")
    private Calendar reopenDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "close_authorizer")
    private User closeAuthorizer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reopen_authorizer")
    private User reopenAuthorizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_detention")
    private Case caseDetention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Calendar closeDate) {
        this.closeDate = closeDate;
    }

    public Calendar getReopenDate() {
        return reopenDate;
    }

    public void setReopenDate(Calendar reopenDate) {
        this.reopenDate = reopenDate;
    }

    public User getCloseAuthorizer() {
        return closeAuthorizer;
    }

    public void setCloseAuthorizer(User closeAuthorizer) {
        this.closeAuthorizer = closeAuthorizer;
    }

    public User getReopenAuthorizer() {
        return reopenAuthorizer;
    }

    public void setReopenAuthorizer(User reopenAuthorizer) {
        this.reopenAuthorizer = reopenAuthorizer;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }
}
