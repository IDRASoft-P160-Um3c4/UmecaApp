package com.umeca.model.dto.humanResources;

import com.umeca.model.entities.reviewer.dto.JobDto;

import java.util.List;

public class DigitalRecordSummaryDto {

    private EmployeeGeneralDataDto generalData;
    private List<JobDto> jobs;
    private List<CourseAchievementDto> courses;
    private String photo;

    public EmployeeGeneralDataDto getGeneralData() {
        return generalData;
    }

    public void setGeneralData(EmployeeGeneralDataDto generalData) {
        this.generalData = generalData;
    }

    public List<JobDto> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobDto> jobs) {
        this.jobs = jobs;
    }

    public List<CourseAchievementDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseAchievementDto> courses) {
        this.courses = courses;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
