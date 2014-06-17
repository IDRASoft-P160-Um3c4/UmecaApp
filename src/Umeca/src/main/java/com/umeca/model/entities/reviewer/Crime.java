package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Election;

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

    @Column(name="nombre", nullable = false, length = 200)
    private String name;

    @Column(name="article", nullable = false, length = 100)
    private String article;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_federal", nullable = false)
    private Election federal;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_criminal_proceeding", nullable = true)
    private CurrentCriminalProceeding criminalProceeding;

    @Transient
    private String $$hashKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
