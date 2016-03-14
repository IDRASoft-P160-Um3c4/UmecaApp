package com.umeca.model.dto.timeAttendance;

import java.util.List;

/**
 * Created by DevelopmentSv on 3/11/2016.
 */
public class UserInfoWsDto {

    private Long Id;

    private String EnrollNumber;

    private String Name;

    private String Password;

    private int Privilage;

    private boolean  Enabled;


    public UserInfoWsDto(Long id, String name){
        this.Id = id;
        this.EnrollNumber = String.valueOf(id);
        this.Name = name;
    }


    public List<FingerPrintWSDto> FingerPrints;

    public String getEnrollNumber() {
        return EnrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
        EnrollNumber = enrollNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPrivilage() {
        return Privilage;
    }

    public void setPrivilage(int privilage) {
        Privilage = privilage;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }

    public List<FingerPrintWSDto> setFingerPrints() {
        return FingerPrints;
    }

    public void setFingerPrints(List<FingerPrintWSDto> fingerPrints) {
        FingerPrints = fingerPrints;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
