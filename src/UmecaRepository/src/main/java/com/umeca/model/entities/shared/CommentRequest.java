package com.umeca.model.entities.shared;

import com.umeca.model.entities.supervisorManager.LogComment;

public class CommentRequest {
    private Long id;
    private LogComment logComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogComment getLogComment() {
        return logComment;
    }

    public void setLogComment(LogComment logComment) {
        this.logComment = logComment;
    }
}
