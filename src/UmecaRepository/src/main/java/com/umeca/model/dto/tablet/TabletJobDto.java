package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletRegisterTypeDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TabletJobDto {

    public TabletJobDto(Long id, String post, String nameHead, String company, String phone, Date startPrev, Date start, Float salaryWeek, Date end, String reasonChange, String address, Boolean block,
                        Long idRT, String nameRT) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        this.id = id;
        this.webId = id;
        this.post = post;
        this.nameHead = nameHead;
        this.company = company;
        this.phone = phone;
        this.startPrev = startPrev == null ? null : sdf.format(startPrev);
        this.start = start == null ? null : sdf.format(start);
        this.salaryWeek = salaryWeek;
        this.end = end == null ? null : sdf.format(end);
        this.reasonChange = reasonChange;
        this.address = address;
        this.block = block;

        if (idRT != null) {
            this.registerType = new TabletRegisterTypeDto(idRT, nameRT);
        }
    }

    private Long webId;
    private Long id;
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
    private Boolean block;
    private TabletRegisterTypeDto registerType;
    private List<TabletScheduleDto> schedule;

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

    public Float getSalaryWeek() {
        return salaryWeek;
    }

    public void setSalaryWeek(Float salaryWeek) {
        this.salaryWeek = salaryWeek;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
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

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public TabletRegisterTypeDto getRegisterType() {
        return registerType;
    }

    public void setRegisterType(TabletRegisterTypeDto registerType) {
        this.registerType = registerType;
    }

    public List<TabletScheduleDto> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<TabletScheduleDto> schedule) {
        this.schedule = schedule;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }
}
