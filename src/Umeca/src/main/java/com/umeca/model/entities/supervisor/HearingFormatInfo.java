package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.HearingFormatConstants;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HearingFormatInfo {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    private Long idCase;
    private Long idFormat;
    private String idFolder;
    private String idJudicial;
    private String room;
    private Date initTime;
    private Date endTime;
    private String judgeName;
    private String mpName;
    private String defName;
    private String impName;
    private String impLNP;
    private String impLNM;
    private Date birthDate;
    private String phone;
    private String addressStr;
    private String crimes;
    private String additional;
    private Integer contDet;
    private Integer impForm;
    private Integer ext;
    private Date extDate;
    private Integer vincProc;
    private String vincRoom;
    private Date vincDate;
    private Date vincTime;
    private Integer arrangementType;
    private Boolean isNational;
    private String terms;
    private List<String> assignedArran;
    private List<ContactData> contacts;
    private Calendar registerTime;

    private String contDetStr;
    private String impFormStr;
    private String extStr;
    private String vincProcStr;
    private String arrangementTypeStr;
    private String isNationalStr;

    public HearingFormatInfo(Long idCase, Long idFormat, String idFolder, String idJudicial, String room, Date initTime, Date endTime,
                             String judgeName, String mpName, String defName, String impName, String impLNP, String impLNM,
                             Date birthDate, String phone, String addressStr,
                             Integer contDet, Integer impForm, Integer ext, Date extDate, Integer vincProc, String vincRoom, Date vincDate, Date vincTime,
                             Integer arrangementType, Boolean isNational, String terms, Calendar registerTime) {

        this.idCase = idCase;
        this.idFormat = idFormat;
        this.idFolder = idFolder;
        this.idJudicial = idJudicial;
        this.room = room;
        this.initTime = initTime;
        this.endTime = endTime;
        this.judgeName = judgeName;
        this.mpName = mpName;
        this.defName = defName;
        this.impName = impName;
        this.impLNP = impLNP;
        this.impLNM = impLNM;
        this.birthDate = birthDate;
        this.phone = phone;
        this.addressStr = addressStr;
        this.crimes = crimes;
        this.additional = additional;
        this.contDet = contDet;
        this.impForm = impForm;
        this.ext = ext;
        this.extDate = extDate;
        this.vincProc = vincProc;
        this.vincRoom = vincRoom;
        this.vincDate = vincDate;
        this.vincTime = vincTime;
        this.arrangementType = arrangementType;
        this.isNational = isNational;
        this.terms = terms;
        this.registerTime = registerTime;
    }

    public String dateToStr(Date date) {
        String str = "";
        try {
            str = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    public String timeToStr(Date time) {
        String str = "";
        try {
            str = timeFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    public String calendarToStr(Calendar calendar) {
        String str = "";
        try {
            str = timeStampFormat.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
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

    public String getIdJudicial() {
        return idJudicial;
    }

    public void setIdJudicial(String idJudicial) {
        this.idJudicial = idJudicial;
    }

    public void setEndTime(Time endTime) {
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

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getImpName() {
        return impName;
    }

    public void setImpName(String impName) {
        this.impName = impName;
    }

    public String getImpLNP() {
        return impLNP;
    }

    public void setImpLNP(String impLNP) {
        this.impLNP = impLNP;
    }

    public String getImpLNM() {
        return impLNM;
    }

    public void setImpLNM(String impLNM) {
        this.impLNM = impLNM;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public String getCrimes() {
        return crimes;
    }

    public void setCrimes(String crimes) {
        this.crimes = crimes;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Integer getContDet() {
        return contDet;
    }

    public void setContDet(Integer contDet) {
        this.contDet = contDet;
    }

    public Integer getImpForm() {
        return impForm;
    }

    public void setImpForm(Integer impForm) {
        this.impForm = impForm;
    }

    public Integer getExt() {
        return ext;
    }

    public void setExt(Integer ext) {
        this.ext = ext;
    }

    public Date getExtDate() {
        return extDate;
    }

    public void setExtDate(Date extDate) {
        this.extDate = extDate;
    }

    public Integer getVincProc() {
        return vincProc;
    }

    public void setVincProc(Integer vincProc) {
        this.vincProc = vincProc;
    }

    public String getVincRoom() {
        return vincRoom;
    }

    public void setVincRoom(String vincRoom) {
        this.vincRoom = vincRoom;
    }

    public Date getVincDate() {
        return vincDate;
    }

    public void setVincDate(Date vincDate) {
        this.vincDate = vincDate;
    }

    public Integer getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(Integer arrangementType) {
        this.arrangementType = arrangementType;
    }

    public Boolean getIsNational() {
        return isNational;
    }

    public void setIsNational(Boolean isNational) {
        this.isNational = isNational;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public List<String> getAssignedArran() {
        return assignedArran;
    }

    public void setAssignedArran(List<String> assignedArran) {
        this.assignedArran = assignedArran;
    }

    public List<ContactData> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactData> contacts) {
        this.contacts = contacts;
    }

    public String contactToStr() {
        String returnStr = "";

        if (this.contacts != null & this.contacts.size() > 0) {
            for (ContactData act : this.contacts) {
                if (returnStr != "")
                    returnStr += "\n";

                String tmp = "-" + act.getNameTxt() + ", Tel. " + act.getPhoneTxt() + ", " + act.getAddressTxt();
                returnStr += tmp;
            }
        }

        return returnStr;
    }

    public String arrangementToStr() {
        String returnStr = "";

        if (this.assignedArran != null & this.assignedArran.size() > 0) {
            for (String act : this.assignedArran) {
                if (returnStr != "")
                    returnStr += "\n-";
                else
                    returnStr += "-";

                returnStr += act;
            }
        }
        return returnStr;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Calendar getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Calendar registerTime) {
        this.registerTime = registerTime;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getVincTime() {
        return vincTime;
    }

    public void setVincTime(Date vincTime) {
        this.vincTime = vincTime;
    }

    public Long getIdFormat() {
        return idFormat;
    }

    public void setIdFormat(Long idFormat) {
        this.idFormat = idFormat;
    }

    public String getContDetStr() {
        if (this.contDet != null && this.contDet.equals(HearingFormatConstants.CONT_DET_LEGAL))
            this.contDetStr = "Legal";
        else if (this.contDet != null && this.contDet.equals(HearingFormatConstants.CONT_DET_ILEGAL))
            this.contDetStr = "Ilegal";

        return contDetStr;
    }

    public void setContDetStr(String contDetStr) {
        this.contDetStr = contDetStr;
    }

    public String getImpFormStr() {
        if (this.impForm != null && this.impForm.equals(HearingFormatConstants.IMP_FORM_YES))
            this.impFormStr = "Si";
        else if (this.impForm != null && this.impForm.equals(HearingFormatConstants.IMP_FORM_NO))
            this.impFormStr = "No";

        return impFormStr;
    }

    public void setImpFormStr(String impFormStr) {
        this.impFormStr = impFormStr;
    }

    public String getExtStr() {
        if (this.ext != null && this.ext.equals(HearingFormatConstants.EXTENSION_144))
            this.extStr = "144 Hrs.";
        else if (this.ext != null && this.ext.equals(HearingFormatConstants.EXTENSION_72))
            this.extStr = "72 Hrs.";
        else if (this.ext != null && this.ext.equals(HearingFormatConstants.EXTENSION_NO))
            this.extStr = "No";

        return extStr;
    }

    public void setExtStr(String extStr) {
        this.extStr = extStr;
    }

    public String getVincProcStr() {
        if (this.vincProc != null && this.vincProc.equals(HearingFormatConstants.PROCESS_VINC_NO))
            this.vincProcStr = "No";
        else if (this.vincProc != null && this.vincProc.equals(HearingFormatConstants.PROCESS_VINC_YES))
            this.vincProcStr = "Si";

        return vincProcStr;
    }

    public void setVincProcStr(String vincProcStr) {
        this.vincProcStr = vincProcStr;
    }

    public String getArrangementTypeStr() {
        if (this.arrangementType != null && this.arrangementType.equals(HearingFormatConstants.HEARING_TYPE_MC))
            this.arrangementTypeStr = "MC";
        else if (this.arrangementType != null && this.arrangementType.equals(HearingFormatConstants.HEARING_TYPE_SCP))
            this.arrangementTypeStr = "SCPP";

        return arrangementTypeStr;
    }

    public void setArrangementTypeStr(String arrangementTypeStr) {
        this.arrangementTypeStr = arrangementTypeStr;
    }

    public String getIsNationalStr() {

        this.isNationalStr = "";

        if (this.isNational != null) {
            if (this.isNational == true)
                this.isNationalStr = "Nacional";
            else
                this.isNationalStr = "Local";
        }

        return isNationalStr;
    }

    public void setIsNationalStr(String isNationalStr) {
        this.isNationalStr = isNationalStr;
    }
}


