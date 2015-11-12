package com.umeca.model.entities.supervisorManager;

import com.umeca.model.entities.supervisor.ExcelArrangementDto;
import com.umeca.model.entities.supervisor.ExcelContactsDto;
import com.umeca.model.entities.supervisor.ExcelCrimeDto;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.shared.HearingFormatConstants;

import javax.swing.text.StyledEditorKit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 05/11/2015.
 */
public class ExcelCaseInfoHearingFormatDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    private Long idCase;
    private String idFolder;
    private Date imputedBirthday;
    private String imputedBirthdayStr;
    private String district;
    private String hearingFormatDate;
    private String hearingFormatTime;
    private String zipCode;
    private String municipality;
    private String state;
    private Boolean isHomeless;
    private String isHomelessStr;

    private Integer controlDetention;
    private String controlDetentionStr;

    private Integer imputationFormulation;
    private String imputationFormulationStr;

    private String terms;

    private Integer linkageProcess;
    private String linkageProcessStr;

    private Date umecaDate;
    private String umecaDateStr;

    private String hearingType;


    private List<ExcelCrimeDto> lstCrimes;
    private String crimesStr;


    private List<ExcelArrangementDto> lstArrangements;
    private String arrangements;

    private List<ExcelContactsDto> lstContacts;
    private String hasContacts;
    private String contactsLiveWith;

    private Date appointmentDate;
    private String appointmentDateStr;

    public ExcelCaseInfoHearingFormatDto(
            Long idCase,
            Date imputedBirthday,
            String idFolder,
            String district,
            String zipCode,
            String municipality,
            String state,
            Boolean isHomeless,
            Integer controlDetention,
            Integer imputationFormulation,
            String terms,
            Integer linkageProcess,
            Date umecaDate,
            String hearingType,
            Date appointmentDate
    ) {
        this.idCase = idCase;
        this.imputedBirthdayStr = dateFormat.format(imputedBirthday);
        this.idFolder = idFolder;
        this.district = district;
        this.zipCode = zipCode;
        this.municipality = municipality;
        this.state = state;
        this.isHomeless = isHomeless;


        this.controlDetention = controlDetention;
        if(this.controlDetention == null){
            this.controlDetentionStr = "";
        }
        else if(this.controlDetention.equals(HearingFormatConstants.CONT_DET_LEGAL)){
            this.controlDetentionStr = "Legal";
        }
        else if(this.controlDetention.equals(HearingFormatConstants.CONT_DET_ILEGAL)){
            this.controlDetentionStr = "Ilegal";
        }
        else{
            this.controlDetentionStr = "Sin registro";
        }

        this.imputationFormulation = imputationFormulation;
        if(this.imputationFormulation == null){
            this.imputationFormulationStr = "";
        }
        else if(this.imputationFormulation.equals(HearingFormatConstants.IMP_FORM_YES)){
            this.imputationFormulationStr = "Sí";
        }
        else if(this.imputationFormulation.equals(HearingFormatConstants.IMP_FORM_NO)){
            this.imputationFormulationStr = "No";
        }
        else {
            this.imputationFormulationStr = "Sin registro";
        }

        this.terms = terms;

        this.linkageProcess = linkageProcess;
        if(this.linkageProcess == null){
            this.linkageProcessStr = "";
        }
        else if(this.linkageProcess.equals(HearingFormatConstants.PROCESS_VINC_YES)){
            this.linkageProcessStr = "Sí";
        }
        else if(this.linkageProcess.equals(HearingFormatConstants.PROCESS_VINC_NO)){
            this.linkageProcessStr = "No";
        }
        else{
            this.linkageProcessStr = "Sin registro";
        }

        this.umecaDate = umecaDate;
        this.umecaDateStr = dateFormat.format(umecaDate);

        this.hearingType = hearingType;



        if(isHomeless == null){
            this.isHomelessStr = "";
        }
        else if(isHomeless.equals(true)){
            this.isHomelessStr = "Sí";
        }
        else {
            this.isHomelessStr = "No";
        }

        this.appointmentDateStr = dateFormat.format(appointmentDate);
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHearingFormatDate() {
        return hearingFormatDate;
    }

    public void setHearingFormatDate(String hearingFormatDate) {
        this.hearingFormatDate = hearingFormatDate;
    }

    public String getHearingFormatTime() {
        return hearingFormatTime;
    }

    public void setHearingFormatTime(String hearingFormatTime) {
        this.hearingFormatTime = hearingFormatTime;
    }

    public Date getImputedBirthday() {
        return imputedBirthday;
    }

    public void setImputedBirthday(Date imputedBirthday) {
        this.imputedBirthday = imputedBirthday;
    }

    public String getImputedBirthdayStr() {
        return imputedBirthdayStr;
    }

    public void setImputedBirthdayStr(String imputedBirthdayStr) {
        this.imputedBirthdayStr = imputedBirthdayStr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getIsHomeless() {
        return isHomeless;
    }

    public void setIsHomeless(Boolean isHomeless) {
        this.isHomeless = isHomeless;
    }

    public String getIsHomelessStr() {
        return isHomelessStr;
    }

    public void setIsHomelessStr(String isHomelessStr) {
        this.isHomelessStr = isHomelessStr;
    }


    public List<ExcelCrimeDto> getLstCrimes() {
        return lstCrimes;
    }

    public void setLstCrimes(List<ExcelCrimeDto> lstCrimes) {
        this.lstCrimes = lstCrimes;
    }

    public String getCrimesStr() {
        this.crimesStr = "";
        if(lstCrimes != null){
            for(int i = 0; i < lstCrimes.size(); i++ ){
                this.crimesStr += lstCrimes.get(i).getCrime();
                if(i < lstCrimes.size() - 1){
                    this.crimesStr += ",";
                }
            }
        }

        return this.crimesStr;
    }

    public void setCrimesStr(String crimesStr) {
        this.crimesStr = crimesStr;
    }


    public Integer getControlDetention() {
        return controlDetention;
    }

    public void setControlDetention(Integer controlDetention) {
        this.controlDetention = controlDetention;
    }

    public Integer getImputationFormulation() {
        return imputationFormulation;
    }

    public void setImputationFormulation(Integer imputationFormulation) {
        this.imputationFormulation = imputationFormulation;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Integer getLinkageProcess() {
        return linkageProcess;
    }

    public void setLinkageProcess(Integer linkageProcess) {
        this.linkageProcess = linkageProcess;
    }

    public Date getUmecaDate() {
        return umecaDate;
    }

    public void setUmecaDate(Date umecaDate) {
        this.umecaDate = umecaDate;
    }

    public String getHearingType() {
        return hearingType;
    }

    public void setHearingType(String hearingType) {
        this.hearingType = hearingType;
    }

    public String getControlDetentionStr() {
        return controlDetentionStr;
    }

    public void setControlDetentionStr(String controlDetentionStr) {
        this.controlDetentionStr = controlDetentionStr;
    }

    public String getImputationFormulationStr() {
        return imputationFormulationStr;
    }

    public void setImputationFormulationStr(String imputationFormulationStr) {
        this.imputationFormulationStr = imputationFormulationStr;
    }

    public String getLinkageProcessStr() {
        return linkageProcessStr;
    }

    public void setLinkageProcessStr(String linkageProcessStr) {
        this.linkageProcessStr = linkageProcessStr;
    }

    public String getUmecaDateStr() {
        return umecaDateStr;
    }

    public void setUmecaDateStr(String umecaDateStr) {
        this.umecaDateStr = umecaDateStr;
    }

    public List<ExcelArrangementDto> getLstArrangements() {
        return lstArrangements;
    }

    public void setLstArrangements(List<ExcelArrangementDto> lstArrangements) {
        this.lstArrangements = lstArrangements;
    }

    public String getArrangements() {
        this.arrangements = "";
        if(lstArrangements != null){
            for(int i = 0; i < lstArrangements.size(); i++ ){
                this.arrangements += lstArrangements.get(i).getArrangement();
                if(i < lstArrangements.size() - 1){
                    this.arrangements += ",";
                }
            }
        }
        return this.arrangements;
    }

    public void setArrangements(String arrangements) {
        this.arrangements = arrangements;
    }

    public List<ExcelContactsDto> getLstContacts() {
        return lstContacts;
    }

    public void setLstContacts(List<ExcelContactsDto> lstContacts) {
        this.lstContacts = lstContacts;
    }

    public String getHasContacts() {
        this.hasContacts = "";

        if(lstContacts.size() == 0 ){
            this.hasContacts = "No";
        }
        if(lstContacts.size() > 0){
            this.hasContacts = "Sí";
        }

        return this.hasContacts;
    }

    public void setHasContacts(String hasContacts){
        this.hasContacts = hasContacts;
    }

    public String getContactsLiveWith() {
        if(lstContacts.size() == 0 ){
            this.contactsLiveWith = "No aplica";
        }
        else{
            for(int i = 0; i < lstContacts.size(); i++ ){

                if(lstContacts.get(i).getLiveWith().equals(true)){
                    this.contactsLiveWith = "Sí";
                    break;
                }
                    this.contactsLiveWith = "No";
            }
        }


        return contactsLiveWith;
    }

    public void setContactsLiveWith(String contactsLiveWith) {
        this.contactsLiveWith = contactsLiveWith;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDateStr() {
        return appointmentDateStr;
    }

    public void setAppointmentDateStr(String appointmentDateStr) {
        this.appointmentDateStr = appointmentDateStr;
    }

}
