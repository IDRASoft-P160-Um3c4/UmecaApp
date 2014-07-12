package com.umeca.model.entities.reviewer.View;

import com.umeca.model.entities.reviewer.FieldMeetingSource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 11/07/14
 * Time: 01:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchToChoiceIds {
    Long idSource;
    Integer idSubsection;

    public SearchToChoiceIds() {
    }

    public SearchToChoiceIds(Long idSource, Integer idSubsection) {
        this.idSource = idSource;
        this.idSubsection = idSubsection;
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Integer getIdSubsection() {
        return idSubsection;
    }

    public void setIdSubsection(Integer idSubsection) {
        this.idSubsection = idSubsection;
    }
}
