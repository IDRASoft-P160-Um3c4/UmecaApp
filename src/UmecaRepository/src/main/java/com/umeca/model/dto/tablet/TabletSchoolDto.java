package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletDegreeDto;

import java.util.List;

public class TabletSchoolDto {

    public TabletSchoolDto() {
    }

    public TabletSchoolDto(Long id, String name, String phone, String address, String specification, Boolean block) {
        this.id = id;
        this.webId = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.specification = specification;
        this.block = block;
    }

    private Long webId;
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String specification;
    private Boolean block;
    private TabletDegreeDto degree;
    private List<TabletScheduleDto> schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public TabletDegreeDto getDegree() {
        return degree;
    }

    public void setDegree(TabletDegreeDto degree) {
        this.degree = degree;
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
