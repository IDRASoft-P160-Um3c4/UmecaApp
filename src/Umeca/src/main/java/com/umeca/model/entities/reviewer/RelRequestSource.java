package com.umeca.model.entities.reviewer;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.Message;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/08/14
 * Time: 05:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="rel_request_source")
public class RelRequestSource {
    @Id
    @GeneratedValue
    @Column(name="id_rel")
    private Long relId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_source", nullable = false)
    private SourceVerification source;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_request", nullable = false)
    private CaseRequest caseRequest;


    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public SourceVerification getSource() {
        return source;
    }

    public void setSource(SourceVerification source) {
        this.source = source;
    }

    public CaseRequest getCaseRequest() {
        return caseRequest;
    }

    public void setCaseRequest(CaseRequest caseRequest) {
        this.caseRequest = caseRequest;
    }
}
