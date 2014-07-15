package com.umeca.model.catalog;

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

    @Column(name = "code", length = 255,unique = true)
    private String code;

    @Column(name="section")
    private String section;

    @Column(name="section_code")
    private Integer sectionCode;

    @Column(name="field_name", length = 500)
    private String fieldName;

    @Column(name="index_field")
    private Integer index;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    @Column(name="id_subsection")
    private Integer idSubsection;

    @Column(name="type_field")
    private String type;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSectionCode() {

        return sectionCode;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getIdSubsection() {
        return idSubsection;
    }

    public void setIdSubsection(Integer idSubsection) {
        this.idSubsection = idSubsection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
