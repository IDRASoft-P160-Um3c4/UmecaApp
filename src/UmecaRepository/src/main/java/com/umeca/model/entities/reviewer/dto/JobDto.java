package com.umeca.model.entities.reviewer.dto;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.entities.reviewer.Job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 30/06/14
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class JobDto implements EntityGrid{
    private Long id;
    private Long idCase;
    private Long idEmployee;
    private String post;
    private String nameHead;
    private String company;
    private String phone;
    private String startPrev;
    private String start;
    private Float salaryWeek;
    private String end;
    private String reasonChange;
    private String address;
    private Long registerTypeId;
    private String schedule;
    private Boolean block;

    public JobDto() {

    }

    public JobDto(Long id, String company, String post, String nameHead, Float salaryWeek, String phone, Date iDate, Date eDate, Long idEmployee) {
        this.id = id;
        this.company = company;
        this.post = post;
        this.nameHead = nameHead;
        this.salaryWeek = salaryWeek;
        this.phone = phone;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (iDate != null) {
            this.start = formatter.format(iDate);
        }
        if (eDate != null) {
            this.end = formatter.format(eDate);
        }
        this.idEmployee = idEmployee;
    }

    public JobDto(Long id, String company, String post, String nameHead, Date iDate, Date eDate) {
        this.id = id;
        this.company = company;
        this.post = post;
        this.nameHead = nameHead;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (iDate != null) {
            this.start = formatter.format(iDate);
        }
        if (eDate != null) {
            this.end = formatter.format(eDate);
        }
    }

    public JobDto dtoJob(Job job, String schedule) {
        this.id = job.getId();
        this.post = job.getPost();
        this.nameHead = job.getNameHead();
        this.company = job.getCompany();
        this.phone = job.getPhone();
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (job.getStartPrev() != null) {
            date.setTime(job.getStartPrev().getTime());
            this.startPrev = formatter.format(date);
        }
        if (job.getStart() != null) {
            date.setTime(job.getStart().getTime());
            this.start = formatter.format(date);
        }
        this.salaryWeek = job.getSalaryWeek();
        if (job.getEnd() != null) {
            date.setTime(job.getEnd().getTime());
            this.end = formatter.format(date);
        }
        this.reasonChange = job.getReasonChange();
        this.address = job.getAddress();
        if (job.getRegisterType() != null) {
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

    public Float getSalaryWeek() {
        return salaryWeek;
    }

    public void setSalaryWeek(Float salaryWeek) {
        this.salaryWeek = salaryWeek;
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

    public String getStartPrev() {
        return startPrev;
    }

    public void setStartPrev(String startPrev) {
        this.startPrev = startPrev;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }
}
