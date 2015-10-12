package com.umeca.model.entities.shared;

public class TabletAssignmentInfo {

    public TabletAssignmentInfo() {
    }

    public TabletAssignmentInfo(Long id, String assignmentTypeCode) {
        this.id = id;
        this.assignmentTypeCode = assignmentTypeCode;
    }

    Long id;
    String assignmentTypeCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentTypeCode() {
        return assignmentTypeCode;
    }

    public void setAssignmentTypeCode(String assignmentTypeCode) {
        this.assignmentTypeCode = assignmentTypeCode;
    }
}
