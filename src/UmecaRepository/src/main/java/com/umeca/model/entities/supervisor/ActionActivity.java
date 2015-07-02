package com.umeca.model.entities.supervisor;


import com.google.gson.Gson;
import com.umeca.model.shared.OptionList;

public class ActionActivity {
    private Long actMonPlanId;
    private String commentsOk;
    private String commentsFail;
    private String arrangementsValues;
    private OptionList[] arrOptArrangementsValues;
    private Integer channelingAssistance;


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

    public String getArrangementsValues() {
        return arrangementsValues;
    }

    public void setArrangementsValues(String arrangementsValues) {
        this.arrangementsValues = arrangementsValues;
    }

    public OptionList[] getArrOptArrangementsValues() {
        if(arrOptArrangementsValues != null)
            return arrOptArrangementsValues;

        Gson gson = new Gson();
        arrOptArrangementsValues = gson.fromJson(arrangementsValues, OptionList[].class);
        return arrOptArrangementsValues;
    }

    public Integer getChannelingAssistance() {
        return channelingAssistance;
    }

    public void setChannelingAssistance(Integer channelingAssistance) {
        this.channelingAssistance = channelingAssistance;
    }
}
