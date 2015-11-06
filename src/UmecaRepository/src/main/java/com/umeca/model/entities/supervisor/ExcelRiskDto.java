package com.umeca.model.entities.supervisor;

/**
 * Created by DeveloperII on 05/11/2015.
 */
public class ExcelRiskDto {
    Long idCase;
    String description;


    public ExcelRiskDto(Long idCase, String description){
        this.idCase = idCase;
        this.description = description;
    }


    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
