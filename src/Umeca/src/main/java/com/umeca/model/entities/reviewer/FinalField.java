package com.umeca.model.entities.reviewer;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 17/06/14
 * Time: 05:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="final_field")
public class FinalField {
    @Id
    @GeneratedValue
    @Column(name="id_final_field")
    private Long id;

    @Column(name="reason", length = 500, nullable = false)
    private String reason;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private FieldMeetingSource fieldMeetingSource;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_verification", nullable = false)
    private Verification verification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public FieldMeetingSource getFieldMeetingSource() {
        return fieldMeetingSource;
    }

    public void setFieldMeetingSource(FieldMeetingSource fieldMeetingSource) {
        this.fieldMeetingSource = fieldMeetingSource;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }


}
