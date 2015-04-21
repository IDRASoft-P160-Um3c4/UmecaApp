package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class EmployeeScheduleDto implements EntityGrid {

    private Long id;
    private String name;
    private String description;
    private String scheduleDays;

    public EmployeeScheduleDto() {

    }

    //upsert & grid
    public EmployeeScheduleDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScheduleDays() {
        return scheduleDays;
    }

    public void setScheduleDays(String scheduleDays) {
        this.scheduleDays = scheduleDays;
    }
}