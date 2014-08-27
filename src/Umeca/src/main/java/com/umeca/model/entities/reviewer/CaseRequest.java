package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.RequestType;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.Message;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/08/14
 * Time: 06:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="case_request")
public class CaseRequest {

    @Id
    @GeneratedValue
    @Column(name="id_request")
    private Long id;

    @Column(name="state_before", length = 500)
    private String stateBefore;

    @ManyToOne
    @JoinColumn(name="id_request_type", nullable = false)
    private RequestType requestType;

    @OneToMany(mappedBy="caseRequest", cascade={CascadeType.ALL})
    private List<SourceVerification> sources;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_response_message")
    private Message responseMessage;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_request_message")
    private Message requestMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateBefore() {
        return stateBefore;
    }

    public void setStateBefore(String stateBefore) {
        this.stateBefore = stateBefore;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public List<SourceVerification> getSources() {
        return sources;
    }

    public void setSources(List<SourceVerification> sources) {
        this.sources = sources;
    }

    public Message getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(Message responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Message getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(Message requestMessage) {
        this.requestMessage = requestMessage;
    }
}
