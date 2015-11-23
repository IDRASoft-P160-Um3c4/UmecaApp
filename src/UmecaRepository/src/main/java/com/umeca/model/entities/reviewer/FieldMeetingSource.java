package com.umeca.model.entities.reviewer;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.catalog.FieldVerification;
import com.umeca.model.catalog.StatusFieldVerification;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 17/06/14
 * Time: 04:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="field_meeting_source", uniqueConstraints = @UniqueConstraint(columnNames = {"id_field", "id_source_verification", "id_field_list"}))

public class FieldMeetingSource {

    public FieldMeetingSource() {
    }

    public FieldMeetingSource(String value, String jsonValue) {
        this.value = value;
        this.jsonValue = jsonValue;
    }

    public FieldMeetingSource(String value, String jsonValue, Boolean isFinal) {
        this.value = value;
        this.jsonValue = jsonValue;
        this.isFinal  = isFinal;
    }
    public FieldMeetingSource(String value, String jsonValue, Long idFieldList) {
        this.value = value;
        this.jsonValue = jsonValue;
        this.idFieldList = idFieldList;
    }

    public FieldMeetingSource(String value, String jsonValue, Boolean aFinal, Long idFieldList) {
        this.value = value;
        this.jsonValue = jsonValue;
        this.isFinal = aFinal;
        this.idFieldList = idFieldList;
    }

    @Id
    @GeneratedValue
    @Column(name="id_field_meeting_source")
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_source_verification", nullable = false)
    private SourceVerification sourceVerification;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_field", nullable = false)
    private FieldVerification fieldVerification;

    @Column(name="value", length = 1000, nullable = false)
    private String value;

    @Column(name="value_json", length = 1000, nullable = false)
    private String jsonValue;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_status",nullable = true)
    private StatusFieldVerification statusFieldVerification;

    @Column(name="is_final", nullable = true)
    private Boolean isFinal;

    @Column(name="id_field_list", nullable = true)
    private Long idFieldList;

    @Column(name="reason", length = 500, nullable = true)
    private String reason;

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
        this.value = StringExt.substringMax(value,1000);
    }

    public StatusFieldVerification getStatusFieldVerification() {
        return statusFieldVerification;
    }

    public void setStatusFieldVerification(StatusFieldVerification statusFieldVerification) {
        this.statusFieldVerification = statusFieldVerification;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }

    public Long getIdFieldList() {
        return idFieldList;
    }

    public void setIdFieldList(Long idFieldList) {
        this.idFieldList = idFieldList;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = StringExt.substringMax(jsonValue,1000);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = StringExt.substringMax(reason,500);
    }
}
