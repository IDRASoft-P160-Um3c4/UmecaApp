package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.SelectList;

import java.util.List;


public class ReportExcelSummary {
/*
    $scope.m.filtersModel["iDt"] = $scope.initDate;
    $scope.m.filtersModel["eDt"] = $scope.endDate;
    $scope.m.filtersModel["mP"] = $scope.hasMonP;
    $scope.m.filtersModel["hJ"] = $scope.hasJob;

    $scope.m.filtersModel["l1"] = $scope.lstStatusCase;
    $scope.m.filtersModel["l2"] = $scope.lstStatusMeeting;
    $scope.m.filtersModel["l3"] = $scope.lstStatusVerification;
    $scope.m.filtersModel["l4"] = $scope.lstGender;
    $scope.m.filtersModel["l5"] = $scope.lstMaritalSt;
    $scope.m.filtersModel["l6"] = $scope.lstJob;
    $scope.m.filtersModel["l7"] = $scope.lstAcademicLvl;
    $scope.m.filtersModel["l8"] = $scope.lstDrugs;
    $scope.m.filtersModel["l9"] = $scope.lstLvlRisk;
    $scope.m.filtersModel["l10"] = $scope.lstHearingType;*/

    //para obtener los ids de los filtros seleccionados
    private String iDt;
    private String eDt;
    private Boolean mP;
    private Boolean hJ;

    private List<String> lstStCaseStr;
    private List<String> lstGenderStr;
    private List<String> lstMarStStr;
    private List<String> lstAcLvlStr;
    private List<String> lstDrugsStr;
    private List<String> lstLvlRkStr;
    private List<String> lstHearingTpStr;
    private List<String> lstCrimeStr;
    private List<String> lstArrangementStr;
    private List<String> lstActivitiesStr;

    //para obtener los ids de los filtros seleccionados

    //para mostrar la informacion
    private Long totCases;
    private Long totCasesEv;
    private Long totCasesSup;

    //genero
    private Long totFemEv;
    private Long totMascEv;
    private Long totFemSup;
    private Long totMascSup;

    //estado civil
    private Long totSoltEv;
    private Long totCasEv;
    private Long totDivEv;
    private Long totULEv;
    private Long totViuEv;

    private Long totSoltSup;
    private Long totCasSup;
    private Long totDivSup;
    private Long totULSup;
    private Long totViuSup;

    //empleo
    private Long totEmpEv;
    private Long totDesempEv;

    private Long totEmpSup;
    private Long totDesempSup;

    //nivel de estudios
    private Long totSIAEv;
    private Long totPrimEv;
    private Long totSecuEv;
    private Long totBachEv;
    private Long totLicEv;
    private Long totPostgEv;
    private Long totAcLvlOtroEv;
    private Long totSIASup;
    private Long totPrimSup;
    private Long totSecuSup;
    private Long totBachSup;
    private Long totLicSup;
    private Long totPostgSup;
    private Long totAcLvlOtroSup;

    //drogas
    private Long totAlcoEv;
    private Long totMariEv;
    private Long totCocaEv;
    private Long totHeroEv;
    private Long totOpioEv;
    private Long totPBCEv;
    private Long totSolvenEv;
    private Long totCementEv;
    private Long totLSDEv;
    private Long totAnfetEv;
    private Long totMetanfEv;
    private Long totExtaEv;
    private Long totHongoEv;
    private Long totDrgOtroEv;

    private Long totAlcoSup;
    private Long totMariSup;
    private Long totCocaSup;
    private Long totHeroSup;
    private Long totOpioSup;
    private Long totPBCSup;
    private Long totSolvenSup;
    private Long totCementSup;
    private Long totLSDSup;
    private Long totAnfetSup;
    private Long totMetanfSup;
    private Long totExtaSup;
    private Long totHongoSup;
    private Long totDrgOtroSup;


    //status del caso
    private Long totMeeting;
    private Long totLegal;
    private Long totVerif;
    private Long totTechRev;
    private Long totHearingF;
    private Long totFMeeting;
    private Long totMonP;

    //para contar los casos en cada estatus
    private List<ExcelStatusCasesInfo> allCasesIds;

    //para contar casos por obligacion
    private List<SelectList> lstCasesByArrangement;

    //para contar casos por delito eval
    private List<SelectList> lstCasesByCrimeEv;

    //para contar casos por delito sup
    private List<SelectList> lstCasesByCrimeSup;

    //para mostrar la informacion


    public String getiDt() {
        return iDt;
    }

    public void setiDt(String iDt) {
        this.iDt = iDt;
    }

    public String geteDt() {
        return eDt;
    }

    public void seteDt(String eDt) {
        this.eDt = eDt;
    }

    public Boolean getmP() {
        return mP;
    }

    public void setmP(Boolean mP) {
        this.mP = mP;
    }

    public Boolean gethJ() {
        return hJ;
    }

    public void sethJ(Boolean hJ) {
        this.hJ = hJ;
    }

    public Long getTotCases() {
        return totCases;
    }

    public void setTotCases(Long totCases) {
        this.totCases = totCases;
    }

    public Long getTotMeeting() {
        return totMeeting;
    }

    public void setTotMeeting(Long totMeeting) {
        this.totMeeting = totMeeting;
    }

    public Long getTotLegal() {
        return totLegal;
    }

    public void setTotLegal(Long totLegal) {
        this.totLegal = totLegal;
    }

    public Long getTotVerif() {
        return totVerif;
    }

    public void setTotVerif(Long totVerif) {
        this.totVerif = totVerif;
    }

    public Long getTotTechRev() {
        return totTechRev;
    }

    public void setTotTechRev(Long totTechRev) {
        this.totTechRev = totTechRev;
    }

    public Long getTotHearingF() {
        return totHearingF;
    }

    public void setTotHearingF(Long totHearingF) {
        this.totHearingF = totHearingF;
    }

    public Long getTotFMeeting() {
        return totFMeeting;
    }

    public void setTotFMeeting(Long totFMeeting) {
        this.totFMeeting = totFMeeting;
    }

    public Long getTotMonP() {
        return totMonP;
    }

    public void setTotMonP(Long totMonP) {
        this.totMonP = totMonP;
    }

    public String toString(List<String> lstStr) {

        String retStr = "";
        if (lstStr != null && lstStr.size() > 0)
            for (String act : lstStr) {
                if (retStr.isEmpty() == false)
                    retStr += "\n";

                if (act != null && !act.equals(""))
                    retStr += "-" + act;
            }

        return retStr;
    }

    public Double calcPercentEv(Long subt) {
        Double percent = 0.0;
        if(subt != null) {
            if (this.totCasesEv > 0)
                percent = subt.doubleValue() / this.totCasesEv.doubleValue();

        }
        return percent;
    }

    public Double calcPercentSup(Long subt) {

        Double percent = 0.0;

        if (this.totCasesSup > 0)
            percent = subt.doubleValue() / this.totCasesSup.doubleValue();

        return percent;
    }


    public List<String> getLstStCaseStr() {
        return lstStCaseStr;
    }

    public void setLstStCaseStr(List<String> lstStCaseStr) {
        this.lstStCaseStr = lstStCaseStr;
    }

    public List<String> getLstGenderStr() {
        return lstGenderStr;
    }

    public void setLstGenderStr(List<String> lstGenderStr) {
        this.lstGenderStr = lstGenderStr;
    }

    public List<String> getLstMarStStr() {
        return lstMarStStr;
    }

    public void setLstMarStStr(List<String> lstMarStStr) {
        this.lstMarStStr = lstMarStStr;
    }

    public List<String> getLstAcLvlStr() {
        return lstAcLvlStr;
    }

    public void setLstAcLvlStr(List<String> lstAcLvlStr) {
        this.lstAcLvlStr = lstAcLvlStr;
    }

    public List<String> getLstDrugsStr() {
        return lstDrugsStr;
    }

    public void setLstDrugsStr(List<String> lstDrugsStr) {
        this.lstDrugsStr = lstDrugsStr;
    }

    public List<String> getLstLvlRkStr() {
        return lstLvlRkStr;
    }

    public void setLstLvlRkStr(List<String> lstLvlRkStr) {
        this.lstLvlRkStr = lstLvlRkStr;
    }

    public List<String> getLstHearingTpStr() {
        return lstHearingTpStr;
    }

    public void setLstHearingTpStr(List<String> lstHearingTpStr) {
        this.lstHearingTpStr = lstHearingTpStr;
    }

    public String getGenderToStr() {

        String str = "";
        if (this.lstGenderStr != null && this.lstGenderStr.size() > 0) {
            for (String act : this.lstGenderStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public String getStatusToString() {

        String str = "";
        if (this.lstStCaseStr != null && this.lstStCaseStr.size() > 0) {
            for (String act : this.lstStCaseStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }


    public String getMarStToString() {

        String str = "";
        if (this.lstMarStStr != null && this.lstMarStStr.size() > 0) {
            for (String act : this.lstMarStStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public String getDrugsToString() {

        String str = "";
        if (this.lstDrugsStr != null && this.lstDrugsStr.size() > 0) {
            for (String act : this.lstDrugsStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public String getAcLvlToString() {

        String str = "";
        if (this.lstAcLvlStr != null && this.lstAcLvlStr.size() > 0) {
            for (String act : this.lstAcLvlStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public String getRskLvlToString() {

        String str = "";
        if (this.lstLvlRkStr != null && this.lstLvlRkStr.size() > 0) {
            for (String act : this.lstLvlRkStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public String getHearinTypeToString() {

        String str = "";
        if (this.lstLvlRkStr != null && this.lstLvlRkStr.size() > 0) {
            for (String act : this.lstLvlRkStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public Long getTotFemEv() {
        return totFemEv;
    }

    public void setTotFemEv(Long totFemEv) {
        this.totFemEv = totFemEv;
    }

    public Long getTotMascEv() {
        return totMascEv;
    }

    public void setTotMascEv(Long totMascEv) {
        this.totMascEv = totMascEv;
    }

    public Long getTotFemSup() {
        return totFemSup;
    }

    public void setTotFemSup(Long totFemSup) {
        this.totFemSup = totFemSup;
    }

    public Long getTotMascSup() {
        return totMascSup;
    }

    public void setTotMascSup(Long totMascSup) {
        this.totMascSup = totMascSup;
    }

    public Long getTotSoltEv() {
        return totSoltEv;
    }

    public void setTotSoltEv(Long totSoltEv) {
        this.totSoltEv = totSoltEv;
    }

    public Long getTotCasEv() {
        return totCasEv;
    }

    public void setTotCasEv(Long totCasEv) {
        this.totCasEv = totCasEv;
    }

    public Long getTotDivEv() {
        return totDivEv;
    }

    public void setTotDivEv(Long totDivEv) {
        this.totDivEv = totDivEv;
    }

    public Long getTotULEv() {
        return totULEv;
    }

    public void setTotULEv(Long totULEv) {
        this.totULEv = totULEv;
    }

    public Long getTotViuEv() {
        return totViuEv;
    }

    public void setTotViuEv(Long totViuEv) {
        this.totViuEv = totViuEv;
    }

    public Long getTotSoltSup() {
        return totSoltSup;
    }

    public void setTotSoltSup(Long totSoltSup) {
        this.totSoltSup = totSoltSup;
    }

    public Long getTotCasSup() {
        return totCasSup;
    }

    public void setTotCasSup(Long totCasSup) {
        this.totCasSup = totCasSup;
    }

    public Long getTotDivSup() {
        return totDivSup;
    }

    public void setTotDivSup(Long totDivSup) {
        this.totDivSup = totDivSup;
    }

    public Long getTotULSup() {
        return totULSup;
    }

    public void setTotULSup(Long totULSup) {
        this.totULSup = totULSup;
    }

    public Long getTotViuSup() {
        return totViuSup;
    }

    public void setTotViuSup(Long totViuSup) {
        this.totViuSup = totViuSup;
    }

    public Long getTotCasesEv() {
        return totCasesEv;
    }

    public void setTotCasesEv(Long totCasesEv) {
        this.totCasesEv = totCasesEv;
    }

    public Long getTotCasesSup() {
        return totCasesSup;
    }

    public void setTotCasesSup(Long totCasesSup) {
        this.totCasesSup = totCasesSup;
    }

    public Long getTotEmpEv() {
        return totEmpEv;
    }

    public void setTotEmpEv(Long totEmpEv) {
        this.totEmpEv = totEmpEv;
    }

    public Long getTotDesempEv() {
        return totDesempEv;
    }

    public void setTotDesempEv(Long totDesempEv) {
        this.totDesempEv = totDesempEv;
    }

    public Long getTotEmpSup() {
        return totEmpSup;
    }

    public void setTotEmpSup(Long totEmpSup) {
        this.totEmpSup = totEmpSup;
    }

    public Long getTotDesempSup() {
        return totDesempSup;
    }

    public void setTotDesempSup(Long totDesempSup) {
        this.totDesempSup = totDesempSup;
    }

    public Long getTotSIAEv() {
        return totSIAEv;
    }

    public void setTotSIAEv(Long totSIAEv) {
        this.totSIAEv = totSIAEv;
    }

    public Long getTotPrimEv() {
        return totPrimEv;
    }

    public void setTotPrimEv(Long totPrimEv) {
        this.totPrimEv = totPrimEv;
    }

    public Long getTotSecuEv() {
        return totSecuEv;
    }

    public void setTotSecuEv(Long totSecuEv) {
        this.totSecuEv = totSecuEv;
    }

    public Long getTotBachEv() {
        return totBachEv;
    }

    public void setTotBachEv(Long totBachEv) {
        this.totBachEv = totBachEv;
    }

    public Long getTotLicEv() {
        return totLicEv;
    }

    public void setTotLicEv(Long totLicEv) {
        this.totLicEv = totLicEv;
    }

    public Long getTotPostgEv() {
        return totPostgEv;
    }

    public void setTotPostgEv(Long totPostgEv) {
        this.totPostgEv = totPostgEv;
    }

    public Long getTotAcLvlOtroEv() {
        return totAcLvlOtroEv;
    }

    public void setTotAcLvlOtroEv(Long totAcLvlOtroEv) {
        this.totAcLvlOtroEv = totAcLvlOtroEv;
    }

    public Long getTotSIASup() {
        return totSIASup;
    }

    public void setTotSIASup(Long totSIASup) {
        this.totSIASup = totSIASup;
    }

    public Long getTotPrimSup() {
        return totPrimSup;
    }

    public void setTotPrimSup(Long totPrimSup) {
        this.totPrimSup = totPrimSup;
    }

    public Long getTotSecuSup() {
        return totSecuSup;
    }

    public void setTotSecuSup(Long totSecuSup) {
        this.totSecuSup = totSecuSup;
    }

    public Long getTotBachSup() {
        return totBachSup;
    }

    public void setTotBachSup(Long totBachSup) {
        this.totBachSup = totBachSup;
    }

    public Long getTotLicSup() {
        return totLicSup;
    }

    public void setTotLicSup(Long totLicSup) {
        this.totLicSup = totLicSup;
    }

    public Long getTotPostgSup() {
        return totPostgSup;
    }

    public void setTotPostgSup(Long totPostgSup) {
        this.totPostgSup = totPostgSup;
    }

    public Long getTotAcLvlOtroSup() {
        return totAcLvlOtroSup;
    }

    public void setTotAcLvlOtroSup(Long totAcLvlOtroSup) {
        this.totAcLvlOtroSup = totAcLvlOtroSup;
    }

    public Long getTotAlcoEv() {
        return totAlcoEv;
    }

    public void setTotAlcoEv(Long totAlcoEv) {
        this.totAlcoEv = totAlcoEv;
    }

    public Long getTotMariEv() {
        return totMariEv;
    }

    public void setTotMariEv(Long totMariEv) {
        this.totMariEv = totMariEv;
    }

    public Long getTotCocaEv() {
        return totCocaEv;
    }

    public void setTotCocaEv(Long totCocaEv) {
        this.totCocaEv = totCocaEv;
    }

    public Long getTotHeroEv() {
        return totHeroEv;
    }

    public void setTotHeroEv(Long totHeroEv) {
        this.totHeroEv = totHeroEv;
    }

    public Long getTotOpioEv() {
        return totOpioEv;
    }

    public void setTotOpioEv(Long totOpioEv) {
        this.totOpioEv = totOpioEv;
    }

    public Long getTotPBCEv() {
        return totPBCEv;
    }

    public void setTotPBCEv(Long totPBCEv) {
        this.totPBCEv = totPBCEv;
    }

    public Long getTotSolvenEv() {
        return totSolvenEv;
    }

    public void setTotSolvenEv(Long totSolvenEv) {
        this.totSolvenEv = totSolvenEv;
    }

    public Long getTotCementEv() {
        return totCementEv;
    }

    public void setTotCementEv(Long totCementEv) {
        this.totCementEv = totCementEv;
    }

    public Long getTotLSDEv() {
        return totLSDEv;
    }

    public void setTotLSDEv(Long totLSDEv) {
        this.totLSDEv = totLSDEv;
    }

    public Long getTotAnfetEv() {
        return totAnfetEv;
    }

    public void setTotAnfetEv(Long totAnfetEv) {
        this.totAnfetEv = totAnfetEv;
    }

    public Long getTotMetanfEv() {
        return totMetanfEv;
    }

    public void setTotMetanfEv(Long totMetanfEv) {
        this.totMetanfEv = totMetanfEv;
    }

    public Long getTotExtaEv() {
        return totExtaEv;
    }

    public void setTotExtaEv(Long totExtaEv) {
        this.totExtaEv = totExtaEv;
    }

    public Long getTotHongoEv() {
        return totHongoEv;
    }

    public void setTotHongoEv(Long totHongoEv) {
        this.totHongoEv = totHongoEv;
    }

    public Long getTotDrgOtroEv() {
        return totDrgOtroEv;
    }

    public void setTotDrgOtroEv(Long totDrgOtroEv) {
        this.totDrgOtroEv = totDrgOtroEv;
    }

    public Long getTotAlcoSup() {
        return totAlcoSup;
    }

    public void setTotAlcoSup(Long totAlcoSup) {
        this.totAlcoSup = totAlcoSup;
    }

    public Long getTotMariSup() {
        return totMariSup;
    }

    public void setTotMariSup(Long totMariSup) {
        this.totMariSup = totMariSup;
    }

    public Long getTotCocaSup() {
        return totCocaSup;
    }

    public void setTotCocaSup(Long totCocaSup) {
        this.totCocaSup = totCocaSup;
    }

    public Long getTotHeroSup() {
        return totHeroSup;
    }

    public void setTotHeroSup(Long totHeroSup) {
        this.totHeroSup = totHeroSup;
    }

    public Long getTotOpioSup() {
        return totOpioSup;
    }

    public void setTotOpioSup(Long totOpioSup) {
        this.totOpioSup = totOpioSup;
    }

    public Long getTotPBCSup() {
        return totPBCSup;
    }

    public void setTotPBCSup(Long totPBCSup) {
        this.totPBCSup = totPBCSup;
    }

    public Long getTotSolvenSup() {
        return totSolvenSup;
    }

    public void setTotSolvenSup(Long totSolvenSup) {
        this.totSolvenSup = totSolvenSup;
    }

    public Long getTotCementSup() {
        return totCementSup;
    }

    public void setTotCementSup(Long totCementSup) {
        this.totCementSup = totCementSup;
    }

    public Long getTotLSDSup() {
        return totLSDSup;
    }

    public void setTotLSDSup(Long totLSDSup) {
        this.totLSDSup = totLSDSup;
    }

    public Long getTotAnfetSup() {
        return totAnfetSup;
    }

    public void setTotAnfetSup(Long totAnfetSup) {
        this.totAnfetSup = totAnfetSup;
    }

    public Long getTotMetanfSup() {
        return totMetanfSup;
    }

    public void setTotMetanfSup(Long totMetanfSup) {
        this.totMetanfSup = totMetanfSup;
    }

    public Long getTotExtaSup() {
        return totExtaSup;
    }

    public void setTotExtaSup(Long totExtaSup) {
        this.totExtaSup = totExtaSup;
    }

    public Long getTotHongoSup() {
        return totHongoSup;
    }

    public void setTotHongoSup(Long totHongoSup) {
        this.totHongoSup = totHongoSup;
    }

    public Long getTotDrgOtroSup() {
        return totDrgOtroSup;
    }

    public void setTotDrgOtroSup(Long totDrgOtroSup) {
        this.totDrgOtroSup = totDrgOtroSup;
    }

    public Long countGenderEval(List<SelectList> lstGender, Boolean gender) {
        Long tot = 0L;

        if (lstGender != null)
            for (SelectList act : lstGender) {
                if (act.getLock().equals(gender))
                    tot++;
            }

        return tot;
    }

    public Long countGenderSup(List<SelectList> lstGender, Integer gender) {
        Long tot = 0L;

        if (lstGender != null)
            for (SelectList act : lstGender) {
                if (act.getIdAux().equals(gender))
                    tot++;
            }

        return tot;
    }

    public Long countLongId(List<SelectList> lstMaritalSt, Long idStatus) {
        Long tot = 0L;

        if (lstMaritalSt != null)
            for (SelectList act : lstMaritalSt) {
                if (act.getAux().equals(idStatus))
                    tot++;
            }

        return tot;
    }

    public List<ExcelStatusCasesInfo> getAllCasesIds() {
        return allCasesIds;
    }

    public void setAllCasesIds(List<ExcelStatusCasesInfo> allCasesIds) {
        this.allCasesIds = allCasesIds;
    }

    public void doCountIds() {

        this.totMeeting = 0L;
        this.totVerif = 0L;
        this.totTechRev = 0L;
        this.totHearingF = 0L;
        this.totFMeeting = 0L;
        this.totMonP = 0L;

        if (this.allCasesIds != null) {
            for (ExcelStatusCasesInfo act : this.allCasesIds) {
                //cuenta los que tiene meeting completo
                if (act.getIdMeeting() != null && act.getDateMeetingFinished() != null) {
                    this.totMeeting++;
                }
                if (act.getIdVerification() != null && act.getDateVerificationFinished() != null) {
                    this.totVerif++;
                }

                if (act.getIdTecRev() != null && act.getTecRevFinished() != null && act.getTecRevFinished().equals(true)) {
                    this.totTechRev++;
                }

                if (act.getIdFramingMeeting() != null && act.getFramingMeetingFinished() != null && act.getFramingMeetingFinished().equals(true)) {
                    this.totFMeeting++;
                }

                if (act.getIdFirstFormatFinished() != null) {
                    this.totHearingF++;
                }

                if (act.getIdMonitoringPlan() != null) {
                    this.totMonP++;
                }
            }
        }
    }

    public List<SelectList> getLstCasesByArrangement() {
        return lstCasesByArrangement;
    }

    public void setLstCasesByArrangement(List<SelectList> lstCasesByArrangement) {
        this.lstCasesByArrangement = lstCasesByArrangement;
    }

    public List<SelectList> getLstCasesByCrimeEv() {
        return lstCasesByCrimeEv;
    }

    public void setLstCasesByCrimeEv(List<SelectList> lstCasesByCrimeEv) {
        this.lstCasesByCrimeEv = lstCasesByCrimeEv;
    }

    public List<SelectList> getLstCasesByCrimeSup() {
        return lstCasesByCrimeSup;
    }

    public void setLstCasesByCrimeSup(List<SelectList> lstCasesByCrimeSup) {
        this.lstCasesByCrimeSup = lstCasesByCrimeSup;
    }

    public List<String> getLstCrimeStr() {
        return lstCrimeStr;
    }

    public void setLstCrimeStr(List<String> lstCrimeStr) {
        this.lstCrimeStr = lstCrimeStr;
    }

    public List<String> getLstArrangementStr() {
        return lstArrangementStr;
    }

    public void setLstArrangementStr(List<String> lstArrangementStr) {
        this.lstArrangementStr = lstArrangementStr;
    }

    public List<String> getLstActivitiesStr() {
        return lstActivitiesStr;
    }

    public void setLstActivitiesStr(List<String> lstActivitiesStr) {
        this.lstActivitiesStr = lstActivitiesStr;
    }

    public String getCrimesToString() {

        String str = "";
        if (this.lstCrimeStr != null && this.lstCrimeStr.size() > 0) {
            for (String act : this.lstCrimeStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public String getArrangementsToString() {

        String str = "";
        if (this.lstArrangementStr != null && this.lstArrangementStr.size() > 0) {
            for (String act : this.lstArrangementStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

    public String getActivitiesToString() {

        String str = "";
        if (this.lstActivitiesStr != null && this.lstActivitiesStr.size() > 0) {
            for (String act : this.lstActivitiesStr) {
                if (str.isEmpty() == false)
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }
}
