package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TabletCaseDto {

    public TabletCaseDto() {
    }

    public TabletCaseDto(Long id, String idFolder, String idMP, Boolean recidivist, Date dateNotProsecute, Date dateObsolete, Date dateCreate, String previousStateCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.webId = id;
        this.idFolder = idFolder;
        this.idMP = idMP;
        this.recidivist = recidivist;
        this.dateNotProsecute = dateNotProsecute == null ? null : sdf.format(dateNotProsecute);
        this.dateObsolete = dateObsolete == null ? null : sdf.format(dateObsolete);
        this.dateCreate = dateCreate == null ? null : sdf.format(dateCreate);
        this.previousStateCode = previousStateCode;
    }

    private Long webId;
    private Long id;
    private String idFolder;
    private String idMP;
    private Boolean recidivist;
    private String dateNotProsecute;
    private String dateObsolete;
    private String dateCreate;
    private String previousStateCode;
    private TabletStatusCaseDto status;
    private TabletMeetingDto meeting;
    private TabletVerificationDto verification;
    private List<TabletHearingFormatDto> hearingFormats;
    private List<TabletLogCaseDto> logsCase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public Boolean getRecidivist() {
        return recidivist;
    }

    public void setRecidivist(Boolean recidivist) {
        this.recidivist = recidivist;
    }

    public String getDateNotProsecute() {
        return dateNotProsecute;
    }

    public void setDateNotProsecute(String dateNotProsecute) {
        this.dateNotProsecute = dateNotProsecute;
    }

    public String getDateObsolete() {
        return dateObsolete;
    }

    public void setDateObsolete(String dateObsolete) {
        this.dateObsolete = dateObsolete;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public TabletStatusCaseDto getStatus() {
        return status;
    }

    public void setStatus(TabletStatusCaseDto status) {
        this.status = status;
    }

    public TabletMeetingDto getMeeting() {
        return meeting;
    }

    public void setMeeting(TabletMeetingDto meeting) {
        this.meeting = meeting;
    }

    public List<TabletHearingFormatDto> getHearingFormats() {
        return hearingFormats;
    }

    public void setHearingFormats(List<TabletHearingFormatDto> hearingFormats) {
        this.hearingFormats = hearingFormats;
    }

    public TabletVerificationDto getVerification() {
        return verification;
    }

    public void setVerification(TabletVerificationDto verification) {
        this.verification = verification;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    public List<TabletLogCaseDto> getLogsCase() {
        return logsCase;
    }

    public void setLogsCase(List<TabletLogCaseDto> logsCase) {
        this.logsCase = logsCase;
    }

    public String getPreviousStateCode() {
        return previousStateCode;
    }

    public void setPreviousStateCode(String previousStateCode) {
        this.previousStateCode = previousStateCode;
    }
}
