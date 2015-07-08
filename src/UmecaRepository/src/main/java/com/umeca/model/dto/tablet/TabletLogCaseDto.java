package com.umeca.model.dto.tablet;

public class TabletLogCaseDto {

    public TabletLogCaseDto() {
    }

    private String dateString;
    private String activityString;
    private String resume;
    private String title;
    private Long userId;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getActivityString() {
        return activityString;
    }

    public void setActivityString(String activityString) {
        this.activityString = activityString;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
