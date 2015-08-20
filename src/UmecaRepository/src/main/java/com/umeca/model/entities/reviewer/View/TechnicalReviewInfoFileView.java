package com.umeca.model.entities.reviewer.View;

import com.umeca.model.entities.reviewer.PreviousCriminalProceeding;

import java.util.ArrayList;
import java.util.List;

public class TechnicalReviewInfoFileView {

    private String name;
    private String lastNameP;
    private String lastNameM;
    private String idFolder;
    private String address;

    private String nameV;
    private String lastNamePV;
    private String lastNameMV;
    private String addressV;

    private List<String> questSel;
    private List<String> questRisk;
    private List<String> questLinks;
    private List<String> sources;
    private String comment;
    private String result;
    private List<Section> sections;

    private PreviousCriminalProceeding previousCriminalProceeding;

    public TechnicalReviewInfoFileView() {
        sections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }


    public List<String> getQuestSel() {
        return questSel;
    }

    public void setQuestSel(List<String> questSel) {
        this.questSel = questSel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getQuestRisk() {
        return questRisk;
    }

    public void setQuestRisk(List<String> questRisk) {
        this.questRisk = questRisk;
    }

    public List<String> getQuestLinks() {
        return questLinks;
    }

    public void setQuestLinks(List<String> questLinks) {
        this.questLinks = questLinks;
    }

    public String getNameV() {
        return nameV;
    }

    public void setNameV(String nameV) {
        this.nameV = nameV;
    }

    public String getLastNamePV() {
        return lastNamePV;
    }

    public void setLastNamePV(String lastNamePV) {
        this.lastNamePV = lastNamePV;
    }

    public String getLastNameMV() {
        return lastNameMV;
    }

    public void setLastNameMV(String lastNameMV) {
        this.lastNameMV = lastNameMV;
    }

    public String getAddressV() {
        return addressV;
    }

    public void setAddressV(String addressV) {
        this.addressV = addressV;
    }

    public PreviousCriminalProceeding getPreviousCriminalProceeding() {
        return previousCriminalProceeding;
    }

    public void setPreviousCriminalProceeding(PreviousCriminalProceeding previousCriminalProceeding) {
        this.previousCriminalProceeding = previousCriminalProceeding;
    }
}
