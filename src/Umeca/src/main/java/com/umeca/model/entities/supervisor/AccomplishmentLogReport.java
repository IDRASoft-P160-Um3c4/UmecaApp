package com.umeca.model.entities.supervisor;

public class AccomplishmentLogReport {

    private String imputedName;
    private String mpId;
    private String address;

    public AccomplishmentLogReport(String sImpName, String sImpLastNameP, String sImpLastNameM, String mpId, String address){
        this.imputedName = sImpName + " " + sImpLastNameP + " " + sImpLastNameM;;
        this.mpId = mpId;
        this.address = address;
    }

    public String getImputedName() {
        return imputedName;
    }

    public void setImputedName(String imputedName) {
        this.imputedName = imputedName;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
