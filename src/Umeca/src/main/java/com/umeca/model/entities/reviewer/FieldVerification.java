package com.umeca.model.entities.reviewer;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 17/06/14
 * Time: 04:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="field_verification")
public class FieldVerification {
    @Id
    @GeneratedValue
    @Column(name="id_field_verification")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name="isObsolete")
    private Boolean isObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }
}
