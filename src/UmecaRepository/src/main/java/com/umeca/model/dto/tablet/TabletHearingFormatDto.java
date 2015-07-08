package com.umeca.model.dto.tablet;

import com.umeca.model.entities.supervisor.HearingFormatView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TabletHearingFormatDto {

    public TabletHearingFormatDto() {
    }

    public TabletHearingFormatDto(Long id, Calendar registerTime, String idFolder, String idJudicial, String room, Date appointmentDate, Date initTime, Date endTime,
                                  String judgeName, String mpName, String defenderName, String terms, String confirmComment, Boolean isFinished, String comments, Date umecaDate, Date umecaTime, String hearingTypeSpecification, Integer imputedPresence, String hearingResult, Integer previousHearing, Boolean showNotification,
                                  Long idHT, String descriptionHT, Boolean isObsoleteHT, Boolean lockHT, Boolean specificationHT,
                                  Long idHFS, Integer controlDetentionHFS, Integer extensionHFS, Integer imputationFormulationHFS, Date imputationDateHFS, Integer linkageProcessHFS, String linkageRoomHFS, Date linkageDateHFS, Date extDateHFS, Date linkageTimeHFS, Integer arrangementTypeHFS, Boolean nationalArrangementHFS,
                                  Long idI, String nameI, String lastNamePI, String lastNameMI, Date birthDateI, String imputeTelI,
                                  Long idA, String streetA, String outNumA, String innNumA, String latA, String lngA, String addressStringA,
                                  Long idL, String nameL, String abbreviationL, String descriptionL, String zipCodeL) {

        SimpleDateFormat sdfTS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdfD = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");

        this.id = id;
        this.registerTime = registerTime == null ? null : sdfTS.format(registerTime.getTime());
        this.idFolder = idFolder;
        this.idJudicial = idJudicial;
        this.room = room;
        this.appointmentDate = appointmentDate == null ? null : sdfD.format(appointmentDate);
        this.initTime = initTime == null ? null : sdfT.format(initTime);
        this.endTime = endTime == null ? null : sdfT.format(endTime);
        this.judgeName = judgeName;
        this.mpName = mpName;
        this.defenderName = defenderName;
        this.terms = terms;
        this.confirmComment = confirmComment;
        this.isFinished = isFinished;
        this.comments = comments;
        this.umecaDate = umecaDate == null ? null : sdfD.format(umecaDate);
        this.umecaTime = umecaTime == null ? null : sdfT.format(umecaTime);
        this.hearingTypeSpecification = hearingTypeSpecification;
        this.imputedPresence = imputedPresence;
        this.hearingResult = hearingResult;
        this.previousHearing = previousHearing;
        this.showNotification = showNotification;

        if (idHT != null) {
            this.hearingType = new TabletHearingTypeDto(idHT, descriptionHT, isObsoleteHT, lockHT, specificationHT);
        }

        if (idHFS != null) {
            this.hearingFormatSpecs = new TabletHearingFormatSpecsDto(idHFS, controlDetentionHFS, extensionHFS, imputationFormulationHFS, imputationDateHFS, linkageProcessHFS, linkageRoomHFS, linkageDateHFS, extDateHFS, linkageTimeHFS, arrangementTypeHFS, nationalArrangementHFS);
        }

        if (idI != null) {
            this.hearingImputed = new TabletHearingFormatImputedDto(idI, nameI, lastNamePI, lastNameMI, birthDateI, imputeTelI,
                    idA, streetA, outNumA, innNumA, latA, lngA, addressStringA,
                    idL, nameL, abbreviationL, descriptionL, zipCodeL);
        }

    }

    public TabletHearingFormatDto(HearingFormatView hfV) {

        this.idFolder = hfV.getIdFolder();
        this.idJudicial = hfV.getIdJudicial();

        TabletHearingFormatImputedDto hfImp = new TabletHearingFormatImputedDto();
        hfImp.setName(hfV.getImputedName());
        hfImp.setLastNameP(hfV.getImputedFLastName());
        hfImp.setLastNameM(hfV.getImputedSLastName());
        hfImp.setBirthDate(hfV.getImputedBirthDate() == null ? null : new SimpleDateFormat("yyyy/MM/dd").format(hfV.getImputedBirthDate()));
        this.hearingImputed = hfImp;
    }

    private Long id;
    private String registerTime;
    private String idFolder;
    private String idJudicial;
    private String room;
    private String appointmentDate;
    private String initTime;
    private String endTime;
    private String judgeName;
    private String mpName;
    private String defenderName;
    private String terms;
    private String confirmComment;
    private Boolean isFinished;
    private String comments;
    private String umecaDate;
    private String umecaTime;
    private String hearingTypeSpecification;
    private Integer imputedPresence;
    private String hearingResult;
    private Integer previousHearing;
    private Boolean showNotification;
    private TabletHearingTypeDto hearingType;
    private TabletHearingFormatSpecsDto hearingFormatSpecs;
    private TabletHearingFormatImputedDto hearingImputed;
    private List<TabletAssignedArrangementDto> assignedArrangements;
    private List<TabletContactDataDto> contacts;
    private List<TabletCrimeDto> crimeList;
    private TabletUserDto umecaSupervisor;
    private TabletUserDto supervisor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdJudicial() {
        return idJudicial;
    }

    public void setIdJudicial(String idJudicial) {
        this.idJudicial = idJudicial;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getInitTime() {
        return initTime;
    }

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getMpName() {
        return mpName;
    }

    public void setMpName(String mpName) {
        this.mpName = mpName;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public void setDefenderName(String defenderName) {
        this.defenderName = defenderName;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getConfirmComment() {
        return confirmComment;
    }

    public void setConfirmComment(String confirmComment) {
        this.confirmComment = confirmComment;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUmecaDate() {
        return umecaDate;
    }

    public void setUmecaDate(String umecaDate) {
        this.umecaDate = umecaDate;
    }

    public String getUmecaTime() {
        return umecaTime;
    }

    public void setUmecaTime(String umecaTime) {
        this.umecaTime = umecaTime;
    }

    public String getHearingTypeSpecification() {
        return hearingTypeSpecification;
    }

    public void setHearingTypeSpecification(String hearingTypeSpecification) {
        this.hearingTypeSpecification = hearingTypeSpecification;
    }

    public Integer getImputedPresence() {
        return imputedPresence;
    }

    public void setImputedPresence(Integer imputedPresence) {
        this.imputedPresence = imputedPresence;
    }

    public String getHearingResult() {
        return hearingResult;
    }

    public void setHearingResult(String hearingResult) {
        this.hearingResult = hearingResult;
    }

    public Integer getPreviousHearing() {
        return previousHearing;
    }

    public void setPreviousHearing(Integer previousHearing) {
        this.previousHearing = previousHearing;
    }

    public Boolean getShowNotification() {
        return showNotification;
    }

    public void setShowNotification(Boolean showNotification) {
        this.showNotification = showNotification;
    }

    public TabletHearingTypeDto getHearingType() {
        return hearingType;
    }

    public void setHearingType(TabletHearingTypeDto hearingType) {
        this.hearingType = hearingType;
    }

    public TabletHearingFormatSpecsDto getHearingFormatSpecs() {
        return hearingFormatSpecs;
    }

    public void setHearingFormatSpecs(TabletHearingFormatSpecsDto hearingFormatSpecs) {
        this.hearingFormatSpecs = hearingFormatSpecs;
    }

    public TabletHearingFormatImputedDto getHearingImputed() {
        return hearingImputed;
    }

    public void setHearingImputed(TabletHearingFormatImputedDto hearingImputed) {
        this.hearingImputed = hearingImputed;
    }

    public List<TabletAssignedArrangementDto> getAssignedArrangements() {
        return assignedArrangements;
    }

    public void setAssignedArrangements(List<TabletAssignedArrangementDto> assignedArrangements) {
        this.assignedArrangements = assignedArrangements;
    }

    public List<TabletContactDataDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<TabletContactDataDto> contacts) {
        this.contacts = contacts;
    }

    public List<TabletCrimeDto> getCrimeList() {
        return crimeList;
    }

    public void setCrimeList(List<TabletCrimeDto> crimeList) {
        this.crimeList = crimeList;
    }

    public TabletUserDto getUmecaSupervisor() {
        return umecaSupervisor;
    }

    public void setUmecaSupervisor(TabletUserDto umecaSupervisor) {
        this.umecaSupervisor = umecaSupervisor;
    }

    public TabletUserDto getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(TabletUserDto supervisor) {
        this.supervisor = supervisor;
    }
}
