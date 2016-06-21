package com.umeca.controller.managereval;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.director.view.ReportExcelFiltersDto;
import com.umeca.model.entities.managereval.ExcelCaseInfoEvalDto;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.ExcelCaseInfoHearingFormatDto;
import com.umeca.model.entities.supervisorManager.ExcelCaseInfoSupDto;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.ExcelReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DeveloperII on 06/11/2015.
 */

@Controller
public class ManagerevalReportController {

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    ReportExcelRepository reportExcelRepository;

    @Autowired
    ExcelReportService excelReportService;

    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;



    @RequestMapping(value = "/managereval/excelReport/index", method = RequestMethod.GET)
    public ModelAndView excelReport(){
        ModelAndView model = new ModelAndView("/managereval/excelReport/index");
        return model;
    }


    @RequestMapping(value = "/managereval/excelReport/findCasesEv" +
            "", method = RequestMethod.POST)
    public ResponseMessage findCasesSup(@ModelAttribute ReportExcelFiltersDto filtersDto) {

        Date initDate = null;
        Date endDate = null;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";
        Gson gson = new Gson();

        try {
            initDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(filtersDto.getInitDate() + initTime);

            endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(filtersDto.getEndDate() + endTime);


            List<Long> lstStatusCase = new ArrayList<>();


            StatusCase verificationCompleted = statusCaseRepository.findByCode(Constants.CASE_STATUS_VERIFICATION_COMPLETE);
            StatusCase technicalReviewerCompleted = statusCaseRepository.findByCode(Constants.CASE_STATUS_TECHNICAL_REVIEW);
            StatusCase meeting = statusCaseRepository.findByCode(Constants.CASE_STATUS_MEETING);
            StatusCase sourceValidation = statusCaseRepository.findByCode(Constants.CASE_STATUS_SOURCE_VALIDATION);
            StatusCase verification  = statusCaseRepository.findByCode(Constants.CASE_STATUS_VERIFICATION);
            StatusCase closed  = statusCaseRepository.findByCode(Constants.CASE_STATUS_CLOSED);
            StatusCase technicalEdit = statusCaseRepository.findByCode(Constants.CASE_STATUS_EDIT_TEC_REV);
            StatusCase technicalInc = statusCaseRepository.findByCode(Constants.CASE_STATUS_INCOMPLETE_TECHNICAL_REVIEW);
            StatusCase notProsecute = statusCaseRepository.findByCode(Constants.CASE_STATUS_NOT_PROSECUTE);
            StatusCase gotFreedom = statusCaseRepository.findByCode(Constants.CASE_STATUS_GOT_FREEDOM);

            lstStatusCase.add(verificationCompleted.getId());
            lstStatusCase.add(technicalReviewerCompleted.getId());
            lstStatusCase.add(meeting.getId());
            lstStatusCase.add(sourceValidation.getId());
            lstStatusCase.add(verification.getId());
            lstStatusCase.add(closed.getId());
            lstStatusCase.add(technicalEdit.getId());
            lstStatusCase.add(technicalInc.getId());
            lstStatusCase.add(notProsecute.getId());
            lstStatusCase.add(gotFreedom.getId());


            filtersDto.setLstStatusCaseStr(gson.toJson(lstStatusCase));

            List<Long> idsCases = reportExcelRepository.findIdCasesByDates(initDate, endDate);

            idsCases = excelReportService.findCasesByFilters(idsCases, filtersDto);

            Gson conv = new Gson();

            return new ResponseMessage(false, conv.toJson(idsCases));

        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "save", sharedUserService);
            return new ResponseMessage(true, "Error de red, intente mas tarde.");
        }

    }



    @Autowired
    CaseRepository caseRepository;

    @RequestMapping(value = {"/managereval/excelReport/jxls","/director/excelReport/jxlsRev"}, method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response, String ids, String filt) {

        Map beans = new HashMap();

        XLSTransformer transformer = new XLSTransformer();

        try {

            Gson conv = new Gson();
            List<Long> casesIds = conv.fromJson(ids, new TypeToken<List<Long>>() {
            }.getType());

            List<ExcelCaseInfoEvalDto> listCases = caseRepository.getInfoCasesEval(casesIds);
            List<ExcelActivitiesDto> lstActivities = caseRepository.getInfoImputedActivities(casesIds);
            List<ExcelImputedHomeDto> lstHomes = caseRepository.getInfoImputedHomesEvl(casesIds);
            List<ExcelSocialNetworkDto> lstSN = caseRepository.getSocialNetworkEv(casesIds);
            List<ExcelReferenceDto> lstRef = caseRepository.getInfoReference(casesIds);

            List<ExcelJobDto> lstJob = caseRepository.getInfoJobs(casesIds);
            List<ExcelDrugDto> lstDrug = caseRepository.getInfoDrugs(casesIds);


            for (ExcelCaseInfoEvalDto cAct : listCases) {

                List<ExcelActivitiesDto> acts = new ArrayList<>();
                for (ExcelActivitiesDto aAct : lstActivities) {
                    if (aAct.getIdCase().equals(cAct.getIdCase())) {
                        acts.add(aAct);
                    }
                }
                cAct.setLstActivities(acts);



                List<ExcelImputedHomeDto> lstImHome = new ArrayList<>();
                for (ExcelImputedHomeDto hAct : lstHomes) {
                    if (hAct.getIdCase().equals(cAct.getIdCase())) {
                        lstImHome.add(hAct);
                    }
                }
                cAct.setLstHomes(lstImHome);


                List<ExcelSocialNetworkDto> lstCSN = new ArrayList<>();
                for (ExcelSocialNetworkDto snAct : lstSN) {
                    if (snAct.getIdCase().equals(cAct.getIdCase())) {
                        lstCSN.add(snAct);
                    }
                }
                cAct.setLstSN(lstCSN);



                List<ExcelReferenceDto> lstR = new ArrayList<>();
                for (ExcelReferenceDto rAct : lstRef) {
                    if (rAct.getIdCase().equals(cAct.getIdCase()) ) {
                        lstR.add(rAct);
                    }
                }
                cAct.setLstRef(lstR);


                List<ExcelJobDto> lstJ = new ArrayList<>();
                for (ExcelJobDto jAct : lstJob) {
                    if (jAct.getIdCase().equals(cAct.getIdCase())) {
                        lstJ.add(jAct);
                    }
                }


                cAct.setLstJob(lstJ);


                List<ExcelDrugDto> lstD = new ArrayList<>();
                for (ExcelDrugDto dAct : lstDrug) {
                    if (dAct.getIdCase().equals(cAct.getIdCase())) {
                        lstD.add(dAct);
                    }
                }
                cAct.setLstDrug(lstD);
            }


            ReportExcelSummary summ = conv.fromJson(filt, new TypeToken<ReportExcelSummary>() {
            }.getType());
            ;

            Date initDate = null;
            Date endDate = null;
            String initTime = " 00:00:00";
            String endTime = " 23:59:59";
            try {
                initDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                        .parse(summ.getiDt() + initTime);

                endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                        .parse(summ.geteDt() + endTime);
            } catch (Exception e) {
                e.printStackTrace();
                logException.Write(e, this.getClass(), "jxlsMethod", sharedUserService);
            }

            List<Long> idsCasesByDate = reportExcelRepository.findIdCasesByDates(initDate, endDate);

            summ.setTotCases(new Long(idsCasesByDate.size()));
            summ.setTotCasesEv(new Long(reportExcelRepository.countCasesEvaluation(initDate, endDate).size()));
            summ.setTotCasesSup(new Long(reportExcelRepository.countCasesSupervision(initDate, endDate).size()));

            if (!(idsCasesByDate.size() > 0))
                idsCasesByDate.add(-1L);

            //genero
            List<SelectList> lstAllCasesGenderEval = reportExcelRepository.getAllGenderEval(casesIds);
            List<SelectList> lstAllCasesGenderSup = reportExcelRepository.getAllGenderSup(casesIds);

            summ.setTotFemEv(summ.countGenderEval(lstAllCasesGenderEval, true));
            summ.setTotMascEv(summ.countGenderEval(lstAllCasesGenderEval, false));
            summ.setTotFemSup(summ.countGenderSup(lstAllCasesGenderSup, 1));
            summ.setTotMascSup(summ.countGenderSup(lstAllCasesGenderSup, 2));

            List<SelectList> lstAllCasesMarStEval = reportExcelRepository.getAllMaritalStatusEval(casesIds);
            List<SelectList> lstAllCasesMarStSup = reportExcelRepository.getAllMaritalStatusSup(casesIds);

            //estado civil
            summ.setTotSoltEv(summ.countLongId(lstAllCasesMarStEval, Constants.MARITAL_SINGLE));
            summ.setTotCasEv(summ.countLongId(lstAllCasesMarStEval, Constants.MARITAL_MARRIED));
            summ.setTotDivEv(summ.countLongId(lstAllCasesMarStEval, Constants.MARITAL_DIVORCED));
            summ.setTotULEv(summ.countLongId(lstAllCasesMarStEval, Constants.MARITAL_UNION_FREE));
            summ.setTotViuEv(summ.countLongId(lstAllCasesMarStEval, Constants.MARITAL_WIDOWER));

            summ.setTotSoltSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_SINGLE));
            summ.setTotCasSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_MARRIED));
            summ.setTotDivSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_DIVORCED));
            summ.setTotULSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_UNION_FREE));
            summ.setTotViuSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_WIDOWER));

            //empleo

            summ.setTotEmpEv(new Long(reportExcelRepository.getAllCurrentJobEv(idsCasesByDate).size()));
            summ.setTotDesempEv(new Long(reportExcelRepository.getAllNoJobEv(idsCasesByDate).size()));
            summ.setTotEmpSup(new Long(reportExcelRepository.getAllCurrentJobSup(idsCasesByDate).size()));
            summ.setTotDesempSup(new Long(reportExcelRepository.getAllNoJobSup(idsCasesByDate).size()));

            //nivel academico

            List<SelectList> lstAllCasesAcLvlEv = reportExcelRepository.getAllAcLvlEv(casesIds);
            List<SelectList> lstAllCasesAcLvlSup = reportExcelRepository.getAllAcLvlSup(casesIds);

            summ.setTotSIAEv(summ.countLongId(lstAllCasesAcLvlEv, Constants.AC_LVL_ILLITERATE));
            summ.setTotPrimEv(summ.countLongId(lstAllCasesAcLvlEv, Constants.AC_LVL_PRIMARY));
            summ.setTotSecuEv(summ.countLongId(lstAllCasesAcLvlEv, Constants.AC_LVL_HIGH_SCH));
            summ.setTotBachEv(summ.countLongId(lstAllCasesAcLvlEv, Constants.AC_LVL_BACHELOR));
            summ.setTotLicEv(summ.countLongId(lstAllCasesAcLvlEv, Constants.AC_LVL_UNIVERSITY));
            summ.setTotPostgEv(summ.countLongId(lstAllCasesAcLvlEv, Constants.AC_LVL_GRADUATE));
            summ.setTotAcLvlOtroEv(summ.countLongId(lstAllCasesAcLvlEv, Constants.AC_LVL_OTHER));

            summ.setTotSIASup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_ILLITERATE));
            summ.setTotPrimSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_PRIMARY));
            summ.setTotSecuSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_HIGH_SCH));
            summ.setTotBachSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_BACHELOR));
            summ.setTotLicSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_UNIVERSITY));
            summ.setTotPostgSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_GRADUATE));
            summ.setTotAcLvlOtroSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_OTHER));

            //drogas

            List<SelectList> lstAllDrugsEval = reportExcelRepository.getAllDrugsEval(casesIds);
            List<SelectList> lstAllDrugsSup = reportExcelRepository.getAllDrugsSup(casesIds);

            summ.setTotAlcoEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_ALCOHOL));
            summ.setTotMariEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_MARIHUANA));
            summ.setTotCocaEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_COCAIN));
            summ.setTotHeroEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_HEROIN));
            summ.setTotOpioEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_OPIUM));
            summ.setTotPBCEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_PBC));
            summ.setTotSolvenEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_SOLV));
            summ.setTotCementEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_CEME));
            summ.setTotLSDEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_LSD));
            summ.setTotAnfetEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_AMPH));
            summ.setTotMetanfEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_META));
            summ.setTotExtaEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_EXTA));
            summ.setTotHongoEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_MUSH));
            summ.setTotDrgOtroEv(summ.countLongId(lstAllDrugsEval, Constants.DRUG_OTHER));

            summ.setTotAlcoSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_ALCOHOL));
            summ.setTotMariSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_MARIHUANA));
            summ.setTotCocaSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_COCAIN));
            summ.setTotHeroSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_HEROIN));
            summ.setTotOpioSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_OPIUM));
            summ.setTotPBCSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_PBC));
            summ.setTotSolvenSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_SOLV));
            summ.setTotCementSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_CEME));
            summ.setTotLSDSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_LSD));
            summ.setTotAnfetSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_AMPH));
            summ.setTotMetanfSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_META));
            summ.setTotExtaSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_EXTA));
            summ.setTotHongoSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_MUSH));
            summ.setTotDrgOtroSup(summ.countLongId(lstAllDrugsSup, Constants.DRUG_OTHER));

            //no casos por obligación

            List<Object> lstObjects = reportExcelRepository.getCountCasesByArrangement(casesIds);
            List<SelectList> lstCasesByArrangement = new ArrayList<>();

            for (int a = 0; a < lstObjects.size(); a++) {
                Object[] obj = (Object[]) lstObjects.get(a);
                SelectList sl = new SelectList(Long.parseLong(obj[0].toString()), obj[1].toString());
                lstCasesByArrangement.add(sl);
            }

            summ.setLstCasesByArrangement(lstCasesByArrangement);

            //no casos por delito eval

            List<Object> lstObjectsCrimesEval = reportExcelRepository.getCountCasesByCrimeEv(casesIds);
            List<SelectList> lstCasesByCrimeEv = new ArrayList<>();

            for (int a = 0; a < lstObjectsCrimesEval.size(); a++) {
                Object[] obj = (Object[]) lstObjectsCrimesEval.get(a);
                SelectList sl = new SelectList(Long.parseLong(obj[0].toString()), obj[1].toString());
                lstCasesByCrimeEv.add(sl);
            }

            summ.setLstCasesByCrimeEv(lstCasesByCrimeEv);

            //no casos por delito sup

            List<Object> lstObjectsCrimesSup = reportExcelRepository.getCountCasesByCrimeSup(casesIds);
            List<SelectList> lstCasesByCrimeSup = new ArrayList<>();

            for (int a = 0; a < lstObjectsCrimesSup.size(); a++) {
                Object[] obj = (Object[]) lstObjectsCrimesSup.get(a);
                SelectList sl = new SelectList(Long.parseLong(obj[0].toString()), obj[1].toString());
                lstCasesByCrimeSup.add(sl);
            }

            summ.setLstCasesByCrimeSup(lstCasesByCrimeSup);

            //status
            List<ExcelStatusCasesInfo> allStatusCasesInfo = reportExcelRepository.getAllCasesAtEvaluationSupervision(casesIds);
            List<SelectList> allCasesWithHearingFormatFinished = reportExcelRepository.getAllCasesWithFinishedFormat(casesIds);
            summ.setAllCasesIds(allStatusCasesInfo);

            for (ExcelStatusCasesInfo actInfo : summ.getAllCasesIds()) {
                for (SelectList actHF : allCasesWithHearingFormatFinished) {
                    if (actHF.getId().equals(actInfo.getIdCase())) {
                        actInfo.setIdFirstFormatFinished(actHF.getAux());
                    }
                }
            }

            summ.doCountIds(); //realiza los conteos por status




            UUID uid = UUID.randomUUID();
            File temp = File.createTempFile(uid.toString(), ".xls");
            String tempPath = temp.getAbsolutePath();

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jxlsTemplate/ExcelEvReport.xls";

            beans.put("listCases", listCases);
            beans.put("summ", summ);

            transformer.transformXLS(realContextPath, beans, tempPath);

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"ExcelEvalReport.xls\"");

            FileInputStream istr = new FileInputStream(tempPath);
            OutputStream ostr = response.getOutputStream();

            int curByte = -1;

            while ((curByte = istr.read()) != -1)
                ostr.write(curByte);

            ostr.flush();
            ostr.close();
            istr.close();
            temp.delete();

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "jxlsMethod", sharedUserService);
        }
    }

}
