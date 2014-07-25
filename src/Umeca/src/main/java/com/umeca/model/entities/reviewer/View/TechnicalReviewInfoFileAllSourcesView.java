package com.umeca.model.entities.reviewer.View;

import com.umeca.model.entities.reviewer.dto.SourceVerificationDto;

import java.util.ArrayList;
import java.util.List;

public class TechnicalReviewInfoFileAllSourcesView {

    private String name;
    private String lastNameP;
    private String lastNameM;
    private String idFolder;
    private String address;
    private List<SourceVerificationDto> sources;

    public TechnicalReviewInfoFileAllSourcesView() {
        sources = new ArrayList<>();
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

    public List<SourceVerificationDto> getSources() {
        return sources;
    }

    public void setSources(List<SourceVerificationDto> sources) {
        this.sources = sources;
    }
}
