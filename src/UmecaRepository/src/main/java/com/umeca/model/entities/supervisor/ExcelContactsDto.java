package com.umeca.model.entities.supervisor;

/**
 * Created by DeveloperII on 05/11/2015.
 */
public class ExcelContactsDto {
    private Long idCase;
    private Boolean liveWith;

    public ExcelContactsDto(Long idCase, Boolean liveWith) {
        this.idCase = idCase;
        this.liveWith = liveWith;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Boolean getLiveWith() {
        return liveWith;
    }

    public void setLiveWith(Boolean liveWith) {
        this.liveWith = liveWith;
    }
}
