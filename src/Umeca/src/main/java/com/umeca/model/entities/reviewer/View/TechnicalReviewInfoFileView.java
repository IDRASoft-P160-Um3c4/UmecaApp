package com.umeca.model.entities.reviewer.View;

import java.util.List;

public class TechnicalReviewInfoFileView {

    private String name;
    private String lastNameP;
    private String lastNameM;
    private String idFolder;
    private String address;
    private String verifiedName;
    private String verifiedLastNameP;
    private String verifiedLastNameM;
    private List<String> questSel;
    private List<String> sources;
    private String comment;

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

    public String getVerifiedName() {
        return verifiedName;
    }

    public void setVerifiedName(String verifiedName) {
        this.verifiedName = verifiedName;
    }

    public String getVerifiedLastNameP() {
        return verifiedLastNameP;
    }

    public void setVerifiedLastNameP(String verifiedLastNameP) {
        this.verifiedLastNameP = verifiedLastNameP;
    }

    public String getVerifiedLastNameM() {
        return verifiedLastNameM;
    }

    public void setVerifiedLastNameM(String verifiedLastNameM) {
        this.verifiedLastNameM = verifiedLastNameM;
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
}
