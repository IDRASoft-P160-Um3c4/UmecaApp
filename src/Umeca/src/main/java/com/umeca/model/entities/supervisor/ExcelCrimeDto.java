package com.umeca.model.entities.supervisor;

public class ExcelCrimeDto {

    private Long idCase;
    private String crime;
    private String federal;
    private String article;
    private String comment;

    public ExcelCrimeDto(Long idCase, String crime, String federal, String article, String comment) {
        this.idCase = idCase;
        this.crime = crime;
        this.federal = federal;
        this.article = article;
        this.comment = comment;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getFederal() {
        return federal;
    }

    public void setFederal(String federal) {
        this.federal = federal;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
