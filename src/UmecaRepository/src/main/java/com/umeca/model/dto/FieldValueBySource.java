package com.umeca.model.dto;

public class FieldValueBySource {

    private Long id;
    private Long sourceId;
    private String code;
    private String section;
    private Integer sectionCode;
    private String fieldName;
    private Integer idSubsection;
    private String value;
    private Long idFieldList;

    public FieldValueBySource(Long id, Long sourceId, String code, String section, Integer sectionCode, String fieldName, Integer idSubsection, String value, Long idFieldList) {
        this.id = id;
        this.sourceId = sourceId;
        this.code = code;
        this.section = section;
        this.sectionCode = sectionCode;
        this.fieldName = fieldName;
        this.idSubsection = idSubsection;
        this.value = value;
        this.idFieldList = idFieldList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getIdSubsection() {
        return idSubsection;
    }

    public void setIdSubsection(Integer idSubsection) {
        this.idSubsection = idSubsection;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getIdFieldList() {
        return idFieldList;
    }

    public void setIdFieldList(Long idFieldList) {
        this.idFieldList = idFieldList;
    }
}
