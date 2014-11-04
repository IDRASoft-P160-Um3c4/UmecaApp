package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.Crime;

public class CrimeDto {

    private String comment;
    private String article;
    private CatalogDto crime;
    private CatalogDto federal;


    public CrimeDto dtoCrime(Crime crime){
        this.comment = crime.getComment();
        this.crime =new CatalogDto(crime.getCrime().getId(), crime.getCrime().getName());
        article = crime.getArticle();
        CatalogDto aux =new CatalogDto();
        aux.setName(crime.getFederal().getName());
        aux.setId(crime.getFederal().getId());
        federal = aux;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CatalogDto getCrime() {
        return crime;
    }

    public void setCrime(CatalogDto crime) {
        this.crime = crime;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public CatalogDto getFederal() {
        return federal;
    }

    public void setFederal(CatalogDto federal) {
        this.federal = federal;
    }

}
