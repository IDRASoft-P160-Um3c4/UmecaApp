package com.umeca.model.entities.supervisor;
import com.umeca.infrastructure.Convert;
import com.umeca.infrastructure.extensions.DateExt;
import com.umeca.infrastructure.extensions.LongExt;

import java.util.Date;

public class ChannelingModelSheet {

    private String idMP;
    private String imputed;
    private String birthdayTx;
    private String gender;
    private String phone;

    private String channelingType;
    private String name;
    private String institutionType;
    private String institutionName;
    private String consecutiveTx;
    private Long idCase;
    private Boolean isVolunteer;
    private String isFulFilledTx;

    public ChannelingModelSheet(String idMP, String first, String lastNameP, String lastNameM, Date birthday, Boolean gender,
                                String phone, String channelingType, String name,
                                String institutionType, String institutionName,
                                Long consecutive, Long idCase, Boolean isVolunteer, Boolean isFulFilled) {
        this.isVolunteer = isVolunteer;
        this.isFulFilledTx = (isFulFilled == null ? "NA" : (isFulFilled ? "SI" : "NO"));
        this.idMP = Convert.convertToValidString(idMP);
        this.imputed = Convert.convertToValidString(first + " " + lastNameP + " " + lastNameM);
        this.birthdayTx = DateExt.dateToString(birthday) ;
        this.gender = (gender !=  null && gender == true) ? "Femenino" : "Masculino";
        this.phone = (phone != null && phone.isEmpty() == false ? phone : "NA");
        this.channelingType = Convert.convertToValidString(channelingType);
        this.name = Convert.convertToValidString(name);
        this.institutionType = Convert.convertToValidString(institutionType);
        this.institutionName = Convert.convertToValidString(institutionName);
        this.consecutiveTx = LongExt.paddingLeft("0", "4", consecutive);
        this.idCase=idCase;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }

    public String getBirthdayTx() {
        return birthdayTx;
    }

    public void setBirthdayTx(String birthdayTx) {
        this.birthdayTx = birthdayTx;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChannelingType() {
        return channelingType;
    }

    public void setChannelingType(String channelingType) {
        this.channelingType = channelingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getConsecutiveTx() {
        return consecutiveTx;
    }

    public void setConsecutiveTx(String consecutiveTx) {
        this.consecutiveTx = consecutiveTx;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Boolean getIsVolunteer() {
        return isVolunteer;
    }

    public void setIsVolunteer(Boolean isVolunteer) {
        this.isVolunteer = isVolunteer;
    }

    public String getIsFulFilledTx() {
        return isFulFilledTx;
    }

    public void setIsFulFilledTx(String isFulFilledTx) {
        this.isFulFilledTx = isFulFilledTx;
    }
}
