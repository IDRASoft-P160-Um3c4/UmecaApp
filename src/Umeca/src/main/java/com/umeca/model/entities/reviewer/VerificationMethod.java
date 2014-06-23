package com.umeca.model.entities.reviewer;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 17/06/14
 * Time: 05:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="verification_method")
public class VerificationMethod {
    @Id
    @Column(name="id_verification_method")
    private Long id;

    @Column(name="verification_method", length=255, nullable=false)
    private String name;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }
}
