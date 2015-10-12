package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletCrimeCatalogDto;
import com.umeca.model.dto.tablet.catalog.TabletElectionDto;

public class TabletCrimeDto {

    public TabletCrimeDto() {
    }

    public TabletCrimeDto(Long id, String comment, String article,
                          Long idE, String nameE,
                          Long idC, String nameC, String descriptionC, Boolean obsoleteC) {
        this.id = id;
        this.comment = comment;
        this.article = article;

        if (idE != null) {
            this.federal = new TabletElectionDto(idE, nameE);
        }

        if (idC != null) {
            this.crime = new TabletCrimeCatalogDto(idC, nameC, descriptionC, obsoleteC);
        }
    }

    private Long id;
    private String comment;
    private String article;
    private TabletElectionDto federal;
    private TabletCrimeCatalogDto crime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public TabletCrimeCatalogDto getCrime() {
        return crime;
    }

    public void setCrime(TabletCrimeCatalogDto crime) {
        this.crime = crime;
    }

    public TabletElectionDto getFederal() {
        return federal;
    }

    public void setFederal(TabletElectionDto federal) {
        this.federal = federal;
    }

}
