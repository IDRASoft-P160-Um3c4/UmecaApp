package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_fulfillment_report")
public class FulfillmentReportType {

    @Id
    @Column(name="id_fulfillment_report_type")
    private Long id;

    @Column(name="code", length=255, nullable=false)
    private String code;

    @Column(name="name", length=512, nullable=false)
    private String name;

    @Column(name="is_obsolete")
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

