package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 03:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="current_criminal_proceeding")
public class CurrentCriminalProceeding {

    @Id
    @GeneratedValue
    @Column(name="id_current_criminal_proceeding")
    private Long id;

    @Column(name="crime", length = 500, nullable = false)
    private String crime;

    @Column(name="article", length = 500, nullable = false)
    private String article;

    @Column(name="place_detention", length = 500, nullable = false)
    private String placeDetention;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_co_accused", nullable = false)
    private Election coAccused;

    @Column(name="name_co_accused", length = 500, nullable = true)
    private String nameCoAccused;

    @Column(name = "behavior_detention", nullable = false)
    private String behaviorDetention;

    @Column(name = "name_victim", nullable = false)
    private String nameVictim;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_relationship_victim", nullable = false)
    private Relationship relationshipVictim;

    @Column(name = "domicile_victim", nullable = false)
    private String domicileVictim;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPlaceDetention() {
        return placeDetention;
    }

    public void setPlaceDetention(String placeDetention) {
        this.placeDetention = placeDetention;
    }

    public Election getCoAccused() {
        return coAccused;
    }

    public void setCoAccused(Election coAccused) {
        this.coAccused = coAccused;
    }

    public String getNameCoAccused() {
        return nameCoAccused;
    }

    public void setNameCoAccused(String nameCoAccused) {
        this.nameCoAccused = nameCoAccused;
    }

    public String getBehaviorDetention() {
        return behaviorDetention;
    }

    public void setBehaviorDetention(String behaviorDetention) {
        this.behaviorDetention = behaviorDetention;
    }

    public String getNameVictim() {
        return nameVictim;
    }

    public void setNameVictim(String nameVictim) {
        this.nameVictim = nameVictim;
    }

    public Relationship getRelationshipVictim() {
        return relationshipVictim;
    }

    public void setRelationshipVictim(Relationship relationshipVictim) {
        this.relationshipVictim = relationshipVictim;
    }

    public String getDomicileVictim() {
        return domicileVictim;
    }

    public void setDomicileVictim(String domicileVictim) {
        this.domicileVictim = domicileVictim;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
