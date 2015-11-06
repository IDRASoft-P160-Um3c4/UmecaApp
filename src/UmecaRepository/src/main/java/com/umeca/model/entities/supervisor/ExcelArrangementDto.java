package com.umeca.model.entities.supervisor;

/**
 * Created by DeveloperII on 05/11/2015.
 */
public class ExcelArrangementDto {


    private Long idCase;
    private String arrangement;

    public ExcelArrangementDto(Long idCase, String arrangement) {
        this.idCase = idCase;
        this.arrangement = arrangement;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }
}
