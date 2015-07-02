package com.umeca.model.dto.shared;

import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.shared.SelectList;

import java.util.List;

public class MinuteSummaryDto {

    private MinuteDto minuteDto;
    private SelectList attendant;
    private List<SelectList> assistants;
    private String place;
    private List<AgreementDto> agreements;

    public MinuteDto getMinuteDto() {
        return minuteDto;
    }

    public void setMinuteDto(MinuteDto minuteDto) {
        this.minuteDto = minuteDto;
    }

    public SelectList getAttendant() {
        return attendant;
    }

    public void setAttendant(SelectList attendant) {
        this.attendant = attendant;
    }

    public List<SelectList> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<SelectList> assistants) {
        this.assistants = assistants;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<AgreementDto> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<AgreementDto> agreements) {
        this.agreements = agreements;
    }
}