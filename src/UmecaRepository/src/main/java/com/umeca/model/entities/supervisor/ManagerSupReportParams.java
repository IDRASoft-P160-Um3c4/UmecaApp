package com.umeca.model.entities.supervisor;

import java.util.Date;

/**
 * Created by Vmware on 15/01/2015.
 */
public class ManagerSupReportParams {

    private Boolean countArrangement;
    private Boolean countDrug;
    private Boolean countCivOrg;
    private Boolean countJob;
    private Boolean countClosed;
    private Boolean countDetPlace;
    private Long districtId;
    private Long locationId;
    private String initDate;
    private String endDate;
    private Date iDate;
    private Date eDate;

    public Boolean getCountArrangement() {
        return countArrangement;
    }

    public void setCountArrangement(Boolean countArrangement) {
        this.countArrangement = countArrangement;
    }

    public Boolean getCountDrug() {
        return countDrug;
    }

    public void setCountDrug(Boolean countDrug) {
        this.countDrug = countDrug;
    }

    public Boolean getCountCivOrg() {
        return countCivOrg;
    }

    public void setCountCivOrg(Boolean countCivOrg) {
        this.countCivOrg = countCivOrg;
    }

    public Boolean getCountJob() {
        return countJob;
    }

    public void setCountJob(Boolean countJob) {
        this.countJob = countJob;
    }

    public Boolean getCountClosed() {
        return countClosed;
    }

    public void setCountClosed(Boolean countClosed) {
        this.countClosed = countClosed;
    }

    public Boolean getCountDetPlace() {
        return countDetPlace;
    }

    public void setCountDetPlace(Boolean countDetPlace) {
        this.countDetPlace = countDetPlace;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getiDate() {
        return iDate;
    }

    public void setiDate(Date iDate) {
        this.iDate = iDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }
}