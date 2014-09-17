package com.umeca.model.entities.supervisor;

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
    private List<Long> l1;
    private List<Long> l2;
    private List<Long> l3;

    private List<Boolean> l4;
    private List<Long> l5;
    private List<Long> l6;
    private List<Long> l7;
    private List<Long> l8;
    private List<Long> l9;
    private List<Long> l10;

    private List<String> lstStCaseStr;
    private List<String> lstGenderStr;
    private List<String> lstMarStStr;
    private List<String> lstAcLvlStr;
    private List<String> lstDrugsStr;
    private List<String> lstLvlRkStr;
    private List<String> lstHearingTpStr;

    //para obtener los ids de los filtros seleccionados

    //para mostrar la informacion
    private Long totCases;
    private Long totFem;
    private Long totMasc;
    private Long totSolt;
    private Long totCas;
    private Long totDiv;
    private Long totUL;
    private Long totViu;
    private Long totEmp;
    private Long totDesemp;
    private Long totSIA;
    private Long totPrim;
    private Long totSecu;
    private Long totBach;
    private Long totLic;
    private Long totPostg;
    private Long totAcLvlOtro;
    private Long totAlco;
    private Long totMari;
    private Long totCoca;
    private Long totHero;
    private Long totOpio;
    private Long totPBC;
    private Long totSolven;
    private Long totCement;
    private Long totLSD;
    private Long totAnfet;
    private Long totMetanf;
    private Long totExta;
    private Long totHongo;
    private Long totDrgOtro;
    private Long totMeeting;
    private Long totLegal;
    private Long totVerif;
    private Long totTechRev;
    private Long totHearingF;
    private Long totFMeeting;
    private Long totMonP;
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

    public List<Long> getL1() {
        return l1;
    }

    public void setL1(List<Long> l1) {
        this.l1 = l1;
    }

    public List<Long> getL2() {
        return l2;
    }

    public void setL2(List<Long> l2) {
        this.l2 = l2;
    }

    public List<Long> getL3() {
        return l3;
    }

    public void setL3(List<Long> l3) {
        this.l3 = l3;
    }

    public List<Boolean> getL4() {
        return l4;
    }

    public void setL4(List<Boolean> l4) {
        this.l4 = l4;
    }

    public List<Long> getL5() {
        return l5;
    }

    public void setL5(List<Long> l5) {
        this.l5 = l5;
    }

    public List<Long> getL6() {
        return l6;
    }

    public void setL6(List<Long> l6) {
        this.l6 = l6;
    }

    public List<Long> getL7() {
        return l7;
    }

    public void setL7(List<Long> l7) {
        this.l7 = l7;
    }

    public List<Long> getL8() {
        return l8;
    }

    public void setL8(List<Long> l8) {
        this.l8 = l8;
    }

    public List<Long> getL9() {
        return l9;
    }

    public void setL9(List<Long> l9) {
        this.l9 = l9;
    }

    public List<Long> getL10() {
        return l10;
    }

    public void setL10(List<Long> l10) {
        this.l10 = l10;
    }

    public Long getTotCases() {
        return totCases;
    }

    public void setTotCases(Long totCases) {
        this.totCases = totCases;
    }

    public Long getTotFem() {
        return totFem;
    }

    public void setTotFem(Long totFem) {
        this.totFem = totFem;
    }

    public Long getTotMasc() {
        return totMasc;
    }

    public void setTotMasc(Long totMasc) {
        this.totMasc = totMasc;
    }

    public Long getTotSolt() {
        return totSolt;
    }

    public void setTotSolt(Long totSolt) {
        this.totSolt = totSolt;
    }

    public Long getTotCas() {
        return totCas;
    }

    public void setTotCas(Long totCas) {
        this.totCas = totCas;
    }

    public Long getTotDiv() {
        return totDiv;
    }

    public void setTotDiv(Long totDiv) {
        this.totDiv = totDiv;
    }

    public Long getTotUL() {
        return totUL;
    }

    public void setTotUL(Long totUL) {
        this.totUL = totUL;
    }

    public Long getTotViu() {
        return totViu;
    }

    public void setTotViu(Long totViu) {
        this.totViu = totViu;
    }

    public Long getTotEmp() {
        return totEmp;
    }

    public void setTotEmp(Long totEmp) {
        this.totEmp = totEmp;
    }

    public Long getTotDesemp() {
        return totDesemp;
    }

    public void setTotDesemp(Long totDesemp) {
        this.totDesemp = totDesemp;
    }

    public Long getTotSIA() {
        return totSIA;
    }

    public void setTotSIA(Long totSIA) {
        this.totSIA = totSIA;
    }

    public Long getTotPrim() {
        return totPrim;
    }

    public void setTotPrim(Long totPrim) {
        this.totPrim = totPrim;
    }

    public Long getTotSecu() {
        return totSecu;
    }

    public void setTotSecu(Long totSecu) {
        this.totSecu = totSecu;
    }

    public Long getTotBach() {
        return totBach;
    }

    public void setTotBach(Long totBach) {
        this.totBach = totBach;
    }

    public Long getTotLic() {
        return totLic;
    }

    public void setTotLic(Long totLic) {
        this.totLic = totLic;
    }

    public Long getTotPostg() {
        return totPostg;
    }

    public void setTotPostg(Long totPostg) {
        this.totPostg = totPostg;
    }

    public Long getTotAcLvlOtro() {
        return totAcLvlOtro;
    }

    public void setTotAcLvlOtro(Long totAcLvlOtro) {
        this.totAcLvlOtro = totAcLvlOtro;
    }

    public Long getTotAlco() {
        return totAlco;
    }

    public void setTotAlco(Long totAlco) {
        this.totAlco = totAlco;
    }

    public Long getTotMari() {
        return totMari;
    }

    public void setTotMari(Long totMari) {
        this.totMari = totMari;
    }

    public Long getTotCoca() {
        return totCoca;
    }

    public void setTotCoca(Long totCoca) {
        this.totCoca = totCoca;
    }

    public Long getTotHero() {
        return totHero;
    }

    public void setTotHero(Long totHero) {
        this.totHero = totHero;
    }

    public Long getTotOpio() {
        return totOpio;
    }

    public void setTotOpio(Long totOpio) {
        this.totOpio = totOpio;
    }

    public Long getTotPBC() {
        return totPBC;
    }

    public void setTotPBC(Long totPBC) {
        this.totPBC = totPBC;
    }

    public Long getTotSolven() {
        return totSolven;
    }

    public void setTotSolven(Long totSolven) {
        this.totSolven = totSolven;
    }

    public Long getTotCement() {
        return totCement;
    }

    public void setTotCement(Long totCement) {
        this.totCement = totCement;
    }

    public Long getTotLSD() {
        return totLSD;
    }

    public void setTotLSD(Long totLSD) {
        this.totLSD = totLSD;
    }

    public Long getTotAnfet() {
        return totAnfet;
    }

    public void setTotAnfet(Long totAnfet) {
        this.totAnfet = totAnfet;
    }

    public Long getTotMetanf() {
        return totMetanf;
    }

    public void setTotMetanf(Long totMetanf) {
        this.totMetanf = totMetanf;
    }

    public Long getTotExta() {
        return totExta;
    }

    public void setTotExta(Long totExta) {
        this.totExta = totExta;
    }

    public Long getTotHongo() {
        return totHongo;
    }

    public void setTotHongo(Long totHongo) {
        this.totHongo = totHongo;
    }

    public Long getTotDrgOtro() {
        return totDrgOtro;
    }

    public void setTotDrgOtro(Long totDrgOtro) {
        this.totDrgOtro = totDrgOtro;
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
                if (retStr != "")
                    retStr += "\n";

                if (act != null && !act.equals(""))
                    retStr += "-" + act;
            }

        return retStr;
    }

    public Double calcPercent(Long subt) {

        Double percent = 0.0;

        if (this.totCases > 0)
            percent = subt.doubleValue() / this.totCases.doubleValue();

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
                if (str != "")
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
                if (str != "")
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
                if (str != "")
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
                if (str != "")
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
                if (str != "")
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
        if (this.lstLvlRkStr!= null && this.lstLvlRkStr.size() > 0) {
            for (String act : this.lstLvlRkStr) {
                if (str != "")
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
        if (this.lstLvlRkStr!= null && this.lstLvlRkStr.size() > 0) {
            for (String act : this.lstLvlRkStr) {
                if (str != "")
                    str += "\n";

                if (act != null && !act.equals(""))
                    str += act;
            }
        } else
            return "No seleccionado.";

        return str;
    }

}
