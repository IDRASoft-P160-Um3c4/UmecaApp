package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.dto.ScheduleDto;

import java.util.List;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelActivitiesDto {

    private Long idCase;
    private Long id;
    private String description;
    private List<ScheduleDto> schedule;
    private String nameAct;

    public ExcelActivitiesDto(Long idCase, Long id, String nameAct, String description) {
        this.idCase = idCase;
        this.id = id;
        this.nameAct = nameAct;
        this.description = description;
    }

    public ExcelActivitiesDto(Long idCase, String nameAct, String description) {
        this.idCase = idCase;
        this.nameAct = nameAct;
        this.description = description;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameAct() {
        return nameAct;
    }

    public void setNameAct(String nameAct) {
        this.nameAct = nameAct;
    }

    public List<ScheduleDto> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleDto> schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String scheduleToStr() {
        String schStr = "";

        if (this.schedule != null && this.schedule.size() > 0) {
            for (ScheduleDto act : this.schedule) {
                if (schStr.isEmpty() == false)
                    schStr += "; ";
                schStr += act.getDay() + ", de " + act.getStart() + " a " + act.getEnd();
            }
        }
        return schStr;
    }
}
