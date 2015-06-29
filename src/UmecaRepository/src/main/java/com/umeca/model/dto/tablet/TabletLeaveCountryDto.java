package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletCountryDto;
import com.umeca.model.dto.tablet.catalog.TabletElectionDto;
import com.umeca.model.dto.tablet.catalog.TabletImmigrationDocumentDto;
import com.umeca.model.dto.tablet.catalog.TabletRelationshipDto;

public class TabletLeaveCountryDto {

    public TabletLeaveCountryDto(){}

    public TabletLeaveCountryDto(Long id, String timeAgo, String reason, String state, String media, String address, String timeResidence, String specficationImmigranDoc, String specificationRelationship,
                                 Long idFAC, String nameFAC,
                                 Long idCF, String nameCF,
                                 Long idOD, String nameOD,
                                 Long idLC, String nameLC,
                                 Long idID, String nameID, Boolean specificationID, Boolean obsoleteID,
                                 Long idC, String nameC, String alpha2C, String alpha3C, Long latitudeC, Long longitudeC,
                                 Long idR, String nameR, Boolean isObsoleteR, Boolean specificationR) {
        this.id = id;
        this.timeAgo = timeAgo;
        this.reason = reason;
        this.state = state;
        this.media = media;
        this.address = address;
        this.timeResidence = timeResidence;
        this.specficationImmigranDoc = specficationImmigranDoc;
        this.specificationRelationship = specificationRelationship;

        if(idFAC!=null){
            this.familyAnotherCountry = new TabletElectionDto(idFAC,nameFAC);
        }

        if(idCF!=null){
            this.communicationFamily= new TabletElectionDto(idCF,nameCF);
        }

        if(idOD!=null){
            this.familyAnotherCountry = new TabletElectionDto(idOD,nameOD);
        }

        if(idLC!=null){
            this.livedCountry = new TabletElectionDto(idLC,nameLC);
        }

        if(idID!=null){
            this.immigrationDocument = new TabletImmigrationDocumentDto(idID,nameID,specificationID,obsoleteID);
        }

        if(idC!=null) {
            this.country = new TabletCountryDto(idC,nameC,alpha2C,alpha3C,latitudeC,longitudeC);
        }

        if(idR!=null){
            this.relationship = new TabletRelationshipDto(idR,nameR,isObsoleteR,specificationR);
        }
    }

    private Long id;
    private String timeAgo;
    private String reason;
    private String state;
    private String media;
    private String address;
    private String timeResidence;
    private String specficationImmigranDoc;
    private String specificationRelationship;
    private TabletElectionDto familyAnotherCountry;
    private TabletElectionDto communicationFamily;
    private TabletElectionDto officialDocumentation;
    private TabletElectionDto livedCountry;
    private TabletImmigrationDocumentDto immigrationDocument;
    private TabletCountryDto country;
    private TabletRelationshipDto relationship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeResidence() {
        return timeResidence;
    }

    public void setTimeResidence(String timeResidence) {
        this.timeResidence = timeResidence;
    }

    public String getSpecficationImmigranDoc() {
        return specficationImmigranDoc;
    }

    public void setSpecficationImmigranDoc(String specficationImmigranDoc) {
        this.specficationImmigranDoc = specficationImmigranDoc;
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }

    public TabletElectionDto getFamilyAnotherCountry() {
        return familyAnotherCountry;
    }

    public void setFamilyAnotherCountry(TabletElectionDto familyAnotherCountry) {
        this.familyAnotherCountry = familyAnotherCountry;
    }

    public TabletElectionDto getCommunicationFamily() {
        return communicationFamily;
    }

    public void setCommunicationFamily(TabletElectionDto communicationFamily) {
        this.communicationFamily = communicationFamily;
    }

    public TabletElectionDto getOfficialDocumentation() {
        return officialDocumentation;
    }

    public void setOfficialDocumentation(TabletElectionDto officialDocumentation) {
        this.officialDocumentation = officialDocumentation;
    }

    public TabletElectionDto getLivedCountry() {
        return livedCountry;
    }

    public void setLivedCountry(TabletElectionDto livedCountry) {
        this.livedCountry = livedCountry;
    }

    public TabletImmigrationDocumentDto getImmigrationDocument() {
        return immigrationDocument;
    }

    public void setImmigrationDocument(TabletImmigrationDocumentDto immigrationDocument) {
        this.immigrationDocument = immigrationDocument;
    }

    public TabletCountryDto getCountry() {
        return country;
    }

    public void setCountry(TabletCountryDto country) {
        this.country = country;
    }

    public TabletRelationshipDto getRelationship() {
        return relationship;
    }

    public void setRelationship(TabletRelationshipDto relationship) {
        this.relationship = relationship;
    }
}
