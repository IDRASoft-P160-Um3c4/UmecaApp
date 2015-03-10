package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.util.Date;

public class EmployeeSchoolHistoryDto {

    private Long idEmployee;
    private String name;
    private Long degreeId;
    private Long acLvlId;
    private Long documentId;
    private String docSpec;

    public EmployeeSchoolHistoryDto() {
    }

    public EmployeeSchoolHistoryDto(Long idEmployee, String name, Long degreeId, Long acLvlId, Long documentId, String docSpec) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.degreeId = degreeId;
        this.acLvlId = acLvlId;
        this.documentId = documentId;
        this.docSpec = docSpec;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
    }

    public Long getAcLvlId() {
        return acLvlId;
    }

    public void setAcLvlId(Long acLvlId) {
        this.acLvlId = acLvlId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocSpec() {
        return docSpec;
    }

    public void setDocSpec(String docSpec) {
        this.docSpec = docSpec;
    }
}
