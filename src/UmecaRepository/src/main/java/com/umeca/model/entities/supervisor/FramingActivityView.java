package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class FramingActivityView implements EntityGrid {

    private Long id;
    private Long idCase;
    private Long idActivity;
    private String name;
    private String description;
    private String lstSchedule;

    public FramingActivityView() {
    }

    public FramingActivityView(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Long idActivity) {
        this.idActivity = idActivity;
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

    public String getLstSchedule() {
        return lstSchedule;
    }

    public void setLstSchedule(String lstSchedule) {
        this.lstSchedule = lstSchedule;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }
}
