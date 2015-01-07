package com.umeca.model.entities.supervisor;

public class SupervisionLogReport {

    private String imputedName;
    private String crime;
    private String judge;
    private String defender;
    private String mp;
    private String imputedTel;
    private String imputedAddr;
    private String moralName;
    private String moralPhone;

    public SupervisionLogReport(String sImpName, String sImpLastNameP, String sImpLastNameM, String judge, String defender, String mp, String imputedTel,
                                String imputedStreet, String imputedOutNum, String address, String sMoralName, String sMoralLastNameP, String sMoralLastNameM, String sMoralPhone){

        this.imputedName = sImpName + " " + sImpLastNameP + " " + sImpLastNameM;

        this.judge = judge;
        this.defender = defender;
        this.mp = mp;
        this.imputedTel = imputedTel;
        this.imputedAddr = imputedStreet + " " + imputedOutNum + " " + address;
        this.moralName = sMoralName + " " + sMoralLastNameP + " " + sMoralLastNameM;
        this.moralPhone = sMoralPhone;
    }

    public String getImputedName() {
        return imputedName;
    }

    public void setImputedName(String imputedName) {
        this.imputedName = imputedName;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getDefender() {
        return defender;
    }

    public void setDefender(String defender) {
        this.defender = defender;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getImputedTel() {
        return imputedTel;
    }

    public void setImputedTel(String imputedTel) {
        this.imputedTel = imputedTel;
    }

    public String getImputedAddr() {
        return imputedAddr;
    }

    public void setImputedAddr(String imputedAddr) {
        this.imputedAddr = imputedAddr;
    }

    public String getMoralName() {
        return moralName;
    }

    public void setMoralName(String moralName) {
        this.moralName = moralName;
    }

    public String getMoralPhone() {
        return moralPhone;
    }

    public void setMoralPhone(String moralPhone) {
        this.moralPhone = moralPhone;
    }
}
