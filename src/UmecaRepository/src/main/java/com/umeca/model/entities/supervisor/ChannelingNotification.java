package com.umeca.model.entities.supervisor;

public class ChannelingNotification{
    private String channelingType;
    private String imputed;
    private String idMp;
    private String user;

    public ChannelingNotification(String channelingType, String firstName, String lastNameA, String lastNameB, String idMp, String user) {
        this.channelingType = channelingType;
        this.imputed = firstName + " " + lastNameA + " " + lastNameB;
        this.idMp = idMp;
        this.user = user;
    }

    public String getChannelingType() {
        return channelingType;
    }

    public void setChannelingType(String channelingType) {
        this.channelingType = channelingType;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }

    public String getIdMp() {
        return idMp;
    }

    public void setIdMp(String idMp) {
        this.idMp = idMp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
