package com.umeca.model.entities.director.project;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;

public class ProjectModel {

    private Long id;

    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
