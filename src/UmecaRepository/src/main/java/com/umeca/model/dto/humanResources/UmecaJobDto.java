package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import sun.beans.editors.BooleanEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UmecaJobDto implements EntityGrid {

    private Long id;
    private Long idEmployee;
    private String nameHead;
    private Float salary;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private Long idRole;
    private Long idDistrict;
    private Long idRegisterType;
    private String registerType;
    private String district;
    private String role;

    public UmecaJobDto() {
    }

    //para upsert
    public UmecaJobDto(Long id, String nameHead, Long idEmployee, Float salary, Date startDate, Date endDate, Long idRole, Long idDistrict) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.nameHead = nameHead;
        this.idEmployee = idEmployee;
        this.salary = salary;
        try {
            if (startDate != null)
                this.startDate = sdf.format(startDate);
            if (endDate != null)
                this.endDate = sdf.format(endDate);
        } catch (Exception e) {
        }
        this.idRole = idRole;
        this.idDistrict = idDistrict;
    }

    //grid
    public UmecaJobDto(Long id, String nameHead, Float salary, Date startDate, Date endDate, String district, String role) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.nameHead = nameHead;
        this.salary = salary;
        try {
            if (startDate != null)
                this.startDate = sdf.format(startDate);
            if (endDate != null)
                this.endDate = sdf.format(endDate);
            else
                this.endDate = "";
        } catch (Exception e) {

        }
        this.district = district;
        this.role = role;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameHead() {
        return nameHead;
    }

    public void setNameHead(String nameHead) {
        this.nameHead = nameHead;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Long getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(Long idDistrict) {
        this.idDistrict = idDistrict;
    }

    public Long getIdRegisterType() {
        return idRegisterType;
    }

    public void setIdRegisterType(Long idRegisterType) {
        this.idRegisterType = idRegisterType;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
