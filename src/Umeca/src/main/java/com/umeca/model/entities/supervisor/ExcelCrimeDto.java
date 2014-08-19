package com.umeca.model.entities.supervisor;

public class ExcelCrimeDto {

    private Long idCase;
    private String crime;
    private String federal;
    private String article;

    public ExcelCrimeDto(Long idCase, String crime, String federal, String article) {
        this.idCase = idCase;
        this.crime = crime;
        this.federal = federal;
        this.article = article;
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
}
