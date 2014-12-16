package com.umeca.model.entities.supervisor;

import java.util.Date;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelJobDto {

    private Long idCase;
    private String post;
    private String nameHead;
    private String company;
    private String phone;
    private Date startPrev;
    private Date start;
    private Float salary;
    private Date end;
    private String reasonChange;
    private String address;
    private String registerType;
    private Long registerTypeId;
    private Boolean block;

    public ExcelJobDto(Long idCase, String post, String nameHead, String company, String phone, Date startPrev, Date start, Float salary, Date end, String reasonChange, String address, String registerType, Long registerTypeId, Boolean block) {
        this.idCase = idCase;
        this.post = post;
        this.nameHead = nameHead;
        this.company = company;
        this.phone = phone;
        this.startPrev = startPrev;
        this.start = start;
        this.salary = salary;
        this.end = end;
        this.reasonChange = reasonChange;
        this.address = address;
        this.registerType = registerType;
        this.registerTypeId = registerTypeId;
        this.block = block;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getNameHead() {
        return nameHead;
    }

    public void setNameHead(String nameHead) {
        this.nameHead = nameHead;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartPrev() {
        return startPrev;
    }

    public void setStartPrev(Date startPrev) {
        this.startPrev = startPrev;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Long getRegisterTypeId() {
        return registerTypeId;
    }

    public void setRegisterTypeId(Long registerTypeId) {
        this.registerTypeId = registerTypeId;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }
}
