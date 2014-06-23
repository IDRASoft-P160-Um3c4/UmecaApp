package com.umeca.model.entities.reviewer;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 17/06/14
 * Time: 04:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="meeting_source")
public class MeetingSource {
    @Id
    @GeneratedValue
    @Column(name="id_meeting_source")
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_source_verification", nullable = false)
    private SourceVerification sourceVerification;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_field", nullable = false)
    private FieldVerification fieldVerification;

    @Column(name="value", length = 1000, nullable = false)
    private String value;

    @Column(name="equal")
    private Boolean equal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SourceVerification getSourceVerification() {
        return sourceVerification;
    }

    public void setSourceVerification(SourceVerification sourceVerification) {
        this.sourceVerification = sourceVerification;
    }

    public FieldVerification getFieldVerification() {
        return fieldVerification;
    }

    public void setFieldVerification(FieldVerification fieldVerification) {
        this.fieldVerification = fieldVerification;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getEqual() {
        return equal;
    }

    public void setEqual(Boolean equal) {
        this.equal = equal;
    }
}
