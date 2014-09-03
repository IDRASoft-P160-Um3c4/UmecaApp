package com.umeca.model.entities.reviewer.View;

import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.CoDefendant;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.Meeting;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 03:43 PM
 * To change this template use File | Settings | File Templates.
 */

public class CriminalProceedingView {

    private Long idCase;

    private String listCrime;

    private Boolean  haveCoDependant;

    private String listCoDefendant;

    private String placeDetention;

    private String behaviorDetention;

    private String nameVictim;

    private Long relVictimId;

    private Address domicileVictim;

    private String firstProceeding;

    private Integer openProcessNumber;

    private Integer numberConvictions;

    private Long complyPMId;

    private Long complyCSPPId;

    private Long complyProcessAboveId;

    public Long getIdCase() {

        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getListCrime() {
        return listCrime;
    }

    public void setListCrime(String listCrime) {
        this.listCrime = listCrime;
    }

    public String getListCoDefendant() {
        return listCoDefendant;
    }

    public void setListCoDefendant(String listCoDefendant) {
        this.listCoDefendant = listCoDefendant;
    }

    public String getPlaceDetention() {
        return placeDetention;
    }

    public void setPlaceDetention(String placeDetention) {
        this.placeDetention = placeDetention;
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

    public Long getRelVictimId() {
        return relVictimId;
    }

    public void setRelVictimId(Long relVictimId) {
        this.relVictimId = relVictimId;
    }

    public Address getDomicileVictim() {
        return domicileVictim;
    }

    public void setDomicileVictim(Address domicileVictim) {
        this.domicileVictim = domicileVictim;
    }

    public String getFirstProceeding() {
        return firstProceeding;
    }

    public void setFirstProceeding(String firstProceeding) {
        this.firstProceeding = firstProceeding;
    }

    public Integer getOpenProcessNumber() {
        return openProcessNumber;
    }

    public void setOpenProcessNumber(Integer openProcessNumber) {
        this.openProcessNumber = openProcessNumber;
    }

    public Integer getNumberConvictions() {
        return numberConvictions;
    }

    public void setNumberConvictions(Integer numberConvictions) {
        this.numberConvictions = numberConvictions;
    }

    public Long getComplyPMId() {
        return complyPMId;
    }

    public void setComplyPMId(Long complyPMId) {
        this.complyPMId = complyPMId;
    }

    public Long getComplyCSPPId() {
        return complyCSPPId;
    }

    public void setComplyCSPPId(Long complyCSPPId) {
        this.complyCSPPId = complyCSPPId;
    }

    public Long getComplyProcessAboveId() {
        return complyProcessAboveId;
    }

    public void setComplyProcessAboveId(Long complyProcessAboveId) {
        this.complyProcessAboveId = complyProcessAboveId;
    }

    public Boolean getHaveCoDependant() {
        return haveCoDependant;
    }

    public void setHaveCoDependant(Boolean haveCoDependant) {
        this.haveCoDependant = haveCoDependant;
    }
}
