package com.umeca.model.entities.reviewer;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.catalog.CrimeCatalog;
import com.umeca.model.catalog.Election;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.shared.Constants;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 12/06/14
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="crime")
public class Crime {

    @Id
    @GeneratedValue
    @Column(name="id_crime")
    private Long id;

    @Column(name="comment", nullable = true)
    private String comment;

    @Column(name="article", nullable = false, length = 100)
    private String article;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_crime_cat", nullable = false)
    private CrimeCatalog crime;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_federal", nullable = false)
    private Election federal;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_criminal_proceeding", nullable = true)
    private CurrentCriminalProceeding criminalProceeding;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hearingFormat", nullable = true)
    private HearingFormat hearingFormat;


    @Transient
    private String $$hashKey;

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
        this.comment = StringExt.substringMax(comment, Constants.DEFAULT_LEN_STRING);
    }

    public CrimeCatalog getCrime() {
        return crime;
    }

    public void setCrime(CrimeCatalog crime) {
        this.crime = crime;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = StringExt.substringMax(article, 100);
    }

    public Election getFederal() {
        return federal;
    }

    public void setFederal(Election federal) {
        this.federal = federal;
    }

    public CurrentCriminalProceeding getCriminalProceeding() {
        return criminalProceeding;
    }

    public void setCriminalProceeding(CurrentCriminalProceeding criminalProceeding) {
        this.criminalProceeding = criminalProceeding;
    }

    public String get$$hashKey() {
        return $$hashKey;
    }

    public void set$$hashKey(String $$hashKey) {
        this.$$hashKey = $$hashKey;
    }

    public HearingFormat getHearingFormat() {
        return hearingFormat;
    }

    public void setHearingFormat(HearingFormat hearingFormat) {
        this.hearingFormat = hearingFormat;
    }
}
