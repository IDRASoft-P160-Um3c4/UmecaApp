package com.umeca.model.dto.shared;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgreementDto implements EntityGrid {

    private Long id;
    private Long minuteId;
    private String title;
    private String theme;
    private String agreementDate;
    private String comments;
    private String area;
    private String specArea;
    private Long areaId;
    private String isFinishedStr;
    private Boolean isFinished;
    private String isDoneStr;
    private Boolean isDone;
    private String password;
    private String stCode;

    public AgreementDto() {
    }

    //grid
    public AgreementDto(Long id, String title, Boolean isDone, Boolean isFinished, String stCode) {
        this.id = id;
        this.title = title;
        if (isDone == true) {
            this.isDoneStr = Constants.AGREEMENT_IS_DONE;
        } else {
            this.isDoneStr = Constants.AGREEMENT_IS_NOT_DONE;
        }
        this.isFinished = isFinished;
        if (isFinished == true) {
            this.isFinishedStr = Constants.AGREEMENT_IS_FINISHED;
        } else {
            this.isFinishedStr = Constants.AGREEMENT_IS_NOT_FINISHED;
        }

        this.stCode = stCode;
    }

    //datos generales
    public AgreementDto(String title, String theme, Date agreementDate, String area, Boolean spec, String specArea, Boolean isFinished, Boolean isDone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.title = title;
        this.theme = theme;
        if (agreementDate != null)
            this.agreementDate = sdf.format(agreementDate);
        this.area = area;
        if (spec == true) {
            this.area = area + " - " + specArea;
        }
        if (isDone == true) {
            this.isDoneStr = Constants.AGREEMENT_IS_DONE;
        } else {
            this.isDoneStr = Constants.AGREEMENT_IS_NOT_DONE;
        }
        if (isFinished == true) {
            this.isFinishedStr = Constants.AGREEMENT_IS_FINISHED;
        } else {
            this.isFinishedStr = Constants.AGREEMENT_IS_NOT_FINISHED;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(String agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getIsFinishedStr() {
        return isFinishedStr;
    }

    public void setIsFinishedStr(String isFinishedStr) {
        this.isFinishedStr = isFinishedStr;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getIsDoneStr() {
        return isDoneStr;
    }

    public void setIsDoneStr(String isDoneStr) {
        this.isDoneStr = isDoneStr;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Long getMinuteId() {
        return minuteId;
    }

    public void setMinuteId(Long minuteId) {
        this.minuteId = minuteId;
    }

    public String getSpecArea() {
        return specArea;
    }

    public void setSpecArea(String specArea) {
        this.specArea = specArea;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStCode() {
        return stCode;
    }

    public void setStCode(String stCode) {
        this.stCode = stCode;
    }
}
