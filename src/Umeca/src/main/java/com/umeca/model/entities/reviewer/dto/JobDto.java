package com.umeca.model.entities.reviewer.dto;

import com.google.gson.Gson;
import com.umeca.model.entities.reviewer.Job;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 30/06/14
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class JobDto
{
    private Long id;
    private String post;
    private String nameHead;
    private String company;
    private String phone;
    private Date startPrev;
    private Date start;
    private Float salaryWeek;
    private Date end;
    private String reasonChange;
    private String address;
    private Long registerTypeId;
    private String schedule;

    public JobDto dtoJob(Job job, String schedule){
        this.id = job.getId();
        this.post = job.getPost();
        this.nameHead = job.getNameHead();
        this.company = job.getCompany();
        this.phone = job.getPhone();
        if(job.getStartPrev()!=null)
            this.startPrev = job.getStartPrev();
        if(job.getStart()!=null)
            this.start = job.getStart();
        this.salaryWeek = job.getSalaryWeek();
        if(job.getEnd()!=null)
            this.end = job.getEnd();
        this.reasonChange = job.getReasonChange();
        this.address = job.getAddress();
        if(job.getRegisterType() !=null){
            this.registerTypeId = job.getRegisterType().getId();
        }
        this.schedule = schedule;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getSalaryWeek() {
        return salaryWeek;
    }

    public void setSalaryWeek(Float salaryWeek) {
        this.salaryWeek = salaryWeek;
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

    public Long getRegisterTypeId() {
        return registerTypeId;
    }

    public void setRegisterTypeId(Long registerTypeId) {
        this.registerTypeId = registerTypeId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
