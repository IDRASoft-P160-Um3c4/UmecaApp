package com.umeca.model.catalog.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_document_type")
public class TypeNameFile {
    @Id
    @Column(name="id_document_type")
    private Long id;

    @Column(name="document_type", length=255, nullable=false)
    private String name;

    @Column(name="code", length=255, nullable=false)
    private String code;

    @Column(name="only", length=255, nullable=false)
    private Boolean isOnly;

    @Column(name="obsolete", length=255, nullable=false)
    private Boolean obsolete;

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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsOnly() {
        return isOnly;
    }

    public void setIsOnly(Boolean only) {
        isOnly = only;
    }

    public Boolean getOnly() {
        return isOnly;
    }

    public void setOnly(Boolean only) {
        isOnly = only;
    }

    public Boolean getObsolete() {
        return obsolete;
    }

    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }
}

