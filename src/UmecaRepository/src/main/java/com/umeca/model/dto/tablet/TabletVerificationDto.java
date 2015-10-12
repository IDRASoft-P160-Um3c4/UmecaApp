package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletStatusVerificationDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Developer on 22/06/2015.
 */
public class TabletVerificationDto {

    public TabletVerificationDto(Long id, Date dateComplete, Date dateCreate,
                                 Long idUsr, String fullnameUsr,
                                 Long idStV, String nameStV, String descriptionStV) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.dateComplete = dateComplete == null ? null : sdf.format(dateComplete);
        this.dateCreate = dateCreate == null ? null : sdf.format(dateCreate);;

        if (idUsr != null) {
            this.reviewer = new TabletUserDto(idUsr, fullnameUsr);
        }

        if (idStV != null) {
            this.status = new TabletStatusVerificationDto(idStV, nameStV, descriptionStV);
        }
    }

    private Long id;
    private String dateComplete;
    private String dateCreate;
    private TabletUserDto reviewer;
    private TabletStatusVerificationDto status;
    private List<TabletSourceVerificationDto> sourceVerifications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(String dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public TabletUserDto getReviewer() {
        return reviewer;
    }

    public void setReviewer(TabletUserDto reviewer) {
        this.reviewer = reviewer;
    }

    public TabletStatusVerificationDto getStatus() {
        return status;
    }

    public void setStatus(TabletStatusVerificationDto status) {
        this.status = status;
    }

    public List<TabletSourceVerificationDto> getSourceVerifications() {
        return sourceVerifications;
    }

    public void setSourceVerifications(List<TabletSourceVerificationDto> sourceVerifications) {
        this.sourceVerifications = sourceVerifications;
    }

}
