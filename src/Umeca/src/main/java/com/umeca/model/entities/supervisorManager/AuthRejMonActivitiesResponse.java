package com.umeca.model.entities.supervisorManager;

public class AuthRejMonActivitiesResponse {

    private long authIns;
    private long authUpd;
    private long authDel;
    private long rejIns;
    private long rejUpd;
    private long rejDel;
    private String result;

    public AuthRejMonActivitiesResponse() {
        authIns = 0;
        authUpd = 0;
        authDel = 0;
        rejIns = 0;
        rejUpd = 0;
        rejDel = 0;
        result = null;
    }

    public long getAuthIns() {
        return authIns;
    }

    public void setAuthIns(long authIns) {
        this.authIns = authIns;
    }

    public void incAuthIns(){
        authIns++;
    }

    public long getAuthUpd() {
        return authUpd;
    }

    public void setAuthUpd(long authUpd) {
        this.authUpd = authUpd;
    }

    public void incAuthUpd(){
        authUpd++;
    }

    public long getAuthDel() {
        return authDel;
    }

    public void setAuthDel(long authDel) {
        this.authDel = authDel;
    }

    public void incAuthDel(){
        authDel++;
    }

    public long getRejIns() {
        return rejIns;
    }

    public void incRejIns(){
        rejIns++;
    }

    public void setRejIns(long rejIns) {
        this.rejIns = rejIns;
    }

    public long getRejUpd() {
        return rejUpd;
    }

    public void incRejUpd(){
        rejUpd++;
    }

    public void setRejUpd(long rejUpd) {
        this.rejUpd = rejUpd;
    }

    public long getRejDel() {
        return rejDel;
    }

    public void setRejDel(long rejDel) {
        this.rejDel = rejDel;
    }

    public void incRejDel(){
        rejDel++;
    }

    public String getResult() {
        if(result == null){
            result = "La operación se realizó de forma correcta. " +
                    (rejIns == 0 ? "" : ("<br/>" + rejIns + " actividad(es) fue(ron) rechazada(s) para insertar")) +
                    (rejUpd == 0 ? "" : ("<br/>" + rejUpd + " actividad(es) fue(ron) rechazada(s) para modificar")) +
                    (rejDel == 0 ? "" : ("<br/>" + rejDel + " actividad(es) fue(ron) rechazada(s) para eliminar")) +
                    (authIns == 0 ? "" : ("<br/>" + authIns + " actividad(es) fue(ron) autorizada(s) para insertar")) +
                    (authUpd == 0 ? "" : ("<br/>" + authUpd + " actividad(es) fue(ron) autorizada(s) para modificar")) +
                    (authDel == 0 ? "" : ("<br/>" + authDel + " actividad(es) fue(ron) autorizada(s) para eliminar"));
        }

        return result;
    }
}
