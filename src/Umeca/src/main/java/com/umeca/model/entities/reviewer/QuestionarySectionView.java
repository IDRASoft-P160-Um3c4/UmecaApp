package com.umeca.model.entities.reviewer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vmware on 15/05/2014.
 */
public class QuestionarySectionView {

    public QuestionarySectionView() {
        this.sectionName="";
        this.extras="";
        this.totSect="0";
        this.childs= new ArrayList<>();
    }

    private String sectionName;
    private String extras;
    private String totSect;
    private String tabId;

    private List<QuestionarySectionView> childs;
    private List<QuestionView> questions;


    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getTotSect() {
        return totSect;
    }

    public void setTotSect(String totSect) {
        this.totSect = totSect;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public List<QuestionarySectionView> getChilds() {
        return childs;
    }

    public void setChilds(List<QuestionarySectionView> childs) {
        this.childs = childs;
    }

    public List<QuestionView> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionView> questions) {
        this.questions = questions;
    }
}
