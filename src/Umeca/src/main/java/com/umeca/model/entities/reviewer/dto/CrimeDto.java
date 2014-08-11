package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;

import javax.persistence.*;

public class CrimeDto {

    private String name;
    private String article;
    private CatalogDto federal;


    public CrimeDto dtoCrime(Crime crime){
        name = crime.getName();
        article = crime.getArticle();
        CatalogDto aux =new CatalogDto();
        aux.setName(crime.getFederal().getName());
        aux.setId(crime.getFederal().getId());
        federal = aux;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
