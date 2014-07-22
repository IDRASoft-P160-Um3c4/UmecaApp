package com.umeca.model.entities.supervisor;

public class FramingMeetingView {

    private String idFolder;
    private Long idCase;
    private FramingPersonalDataView personalData;
    private Boolean canTerminate;

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public FramingPersonalDataView getPersonalData() {
        return personalData;
    }

    public void setPersonalData(FramingPersonalDataView personalData) {
        this.personalData = personalData;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Boolean getCanTerminate() {
        return canTerminate;
    }

    public void setCanTerminate(Boolean canTerminate) {
        this.canTerminate = canTerminate;
    }
}
