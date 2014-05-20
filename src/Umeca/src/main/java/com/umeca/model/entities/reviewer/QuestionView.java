package com.umeca.model.entities.reviewer;

public class QuestionView {


    public QuestionView() {
        this.questionId="";
        this.questionText = "";
        this.ptsValue = "";
        this.type = "";
    }

    private String questionId;
    private String questionText;
    private String ptsValue;
    private String type;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getPtsValue() {
        return ptsValue;
    }

    public void setPtsValue(String ptsValue) {
        this.ptsValue = ptsValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
