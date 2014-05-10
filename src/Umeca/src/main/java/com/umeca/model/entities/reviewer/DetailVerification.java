package com.umeca.model.entities.reviewer;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 06:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="detail_verification")
public class DetailVerification {

    @Id
    @GeneratedValue
    @Column(name="id_verification")
    private Long id;

    @Column(name = "field_name", length = 50, nullable = false)
    private String fieldName;

    @Column(name = "field_value", length = 255, nullable = false)
    private String fieldValue;

    @Column(name = "field_verified", length = 255, nullable = false)
    private String fieldVerified;

    @Column(name = "comment", length = 1000, nullable = true)
    private String comment;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_source_verification", nullable = false)
    private SourceVerification  sourceVerification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldVerified() {
        return fieldVerified;
    }

    public void setFieldVerified(String fieldVerified) {
        this.fieldVerified = fieldVerified;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public SourceVerification getSourceVerification() {
        return sourceVerification;
    }

    public void setSourceVerification(SourceVerification sourceVerification) {
        this.sourceVerification = sourceVerification;
    }
}
