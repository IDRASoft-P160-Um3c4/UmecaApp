package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelActivitiesDto {

    private Long idCase;
    private String description;
    private String nameAct;

    public ExcelActivitiesDto(Long idCase, String nameAct, String description) {
        this.idCase = idCase;
        this.nameAct = nameAct;
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

    public String getNameAct() {
        return nameAct;
    }

    public void setNameAct(String nameAct) {
        this.nameAct = nameAct;
    }
}
