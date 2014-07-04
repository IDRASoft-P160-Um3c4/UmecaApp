package com.umeca.model.entities.supervisor;


public class ActionActivity {
    private Long actMonPlanId;
    private String commentsOk;
    private String commentsFail;

    public Long getActMonPlanId() {
        return actMonPlanId;
    }

    public void setActMonPlanId(Long actMonPlanId) {
        this.actMonPlanId = actMonPlanId;
    }

    public String getCommentsOk() {
        return commentsOk;
    }

    public void setCommentsOk(String commentsOk) {
        this.commentsOk = commentsOk;
    }

    public String getCommentsFail() {
        return commentsFail;
    }

    public void setCommentsFail(String commentsFail) {
        this.commentsFail = commentsFail;
    }
}
