package com.umeca.controller.director;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.controller.shared.ExcelConv;
import com.umeca.infrastructure.extensions.IntegerExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.director.view.ReportExcelFiltersDto;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.FieldMeetingSource;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ExcelReportController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;


    @RequestMapping(value = "/director/excelReport/listCases", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listCases(@ModelAttribute JqGridFilterModel opts, String ids) {

        opts.extraFilters = new ArrayList<>();

        Gson conv = new Gson();

        List<String> idsCases = conv.fromJson(ids, new TypeToken<List<String>>() {
        }.getType());

        if (!(idsCases.size() > 0))
            idsCases.add("-1");

        JqGridRulesModel extraFilter = new JqGridRulesModel("idsCases", idsCases, JqGridFilterModel.COMPARE_IN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Meeting, Imputed> joinIm = r.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("status").get("name"));
                    add(r.join("status").get("description"));
                    add(r.get("idFolder"));
                    add(r.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idsCases"))
                    return r.get("id");
                return null;
            }
        }, Case.class, ForCasesHFGrid.class);

        return result;
    }


    @RequestMapping(value = "/director/excelReport/index", method = RequestMethod.GET)
    public String index() {
        return "/director/excelReport/index";
    }


    @Autowired
    ReportExcelRepository reportExcelRepository;

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/director/excelReport/findCases", method = RequestMethod.POST)
    public ResponseMessage findCases(@ModelAttribute ReportExcelFiltersDto filtersDto) {

        Date initDate = null;
        Date endDate = null;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";
        try {
            initDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(filtersDto.getInitDate() + initTime);

            endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(filtersDto.getEndDate() + endTime);
        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "save", sharedUserService);
            return new ResponseMessage(true, "Error de red, intente mas tarde.");
        }

        List<Long> idsCases = reportExcelRepository.findIdCasesByDates(initDate, endDate);

        idsCases = findCasesByFilters(idsCases, filtersDto);

        Gson conv = new Gson();

        return new ResponseMessage(false, conv.toJson(idsCases));
    }


    private List<Long> findCasesByFilters(List<Long> idsCasesInDateRange, ReportExcelFiltersDto filtersDto) {

        List<Long> idsStatusCase = null;
        List<Long> idsStatusMeeting = null;
        List<Long> idsStatusVerfi = null;

        List<Long> idsGender = null;
        List<Long> idsMartialSt = null;

        List<Long> idsAcademicLvl = null;
        List<Long> idsActualJob = null;
        List<Long> idsDrugs = null;

        List<Long> idsRiskLvl = null;
        List<Long> idsHearingType = null;

        List<Long> idsWithMonP = null;

        List<Long> finalIds = null;

        if (idsCasesInDateRange.size() > 0) {

            if (filtersDto.getLstGenderBool().size() > 0) {
                List<Integer> lstGenderInt = new ArrayList<>();

                for (Boolean act : filtersDto.getLstGenderBool()) {
                    if (act == true)
                        lstGenderInt.add(1);
                    else if (act == false)
                        lstGenderInt.add(2);
                }

                idsGender = reportExcelRepository.findIdCasesByGender(filtersDto.getLstGenderBool(), lstGenderInt, idsCasesInDateRange);

            }

            if (filtersDto.getLstStatusCase().size() > 0) {
                idsStatusCase = reportExcelRepository.findIdCasesByStatusCase(filtersDto.getLstStatusCase(), idsCasesInDateRange);
            }

            if (filtersDto.getLstStatusMeeting().size() > 0) {
                idsStatusMeeting = reportExcelRepository.findIdCasesByStatusMeeting(filtersDto.getLstStatusMeeting(), idsCasesInDateRange);
            }

            if (filtersDto.getLstStatusVerification().size() > 0) {
                idsStatusVerfi = reportExcelRepository.findIdCasesByStatusVerification(filtersDto.getLstStatusVerification(), idsCasesInDateRange);
            }


            if (filtersDto.getLstMaritalSt().size() > 0) {
                idsMartialSt = reportExcelRepository.findIdCasesByMaritalSt(filtersDto.getLstMaritalSt(), idsCasesInDateRange);
            }

            if (filtersDto.getHasJob() != null && filtersDto.getHasJob() == true) {
                idsActualJob = reportExcelRepository.findIdCasesWithActualJob(idsCasesInDateRange);
            }

            if (filtersDto.getLstAcademicLvl().size() > 0) {
                idsAcademicLvl = reportExcelRepository.findIdCasesByAcademicLvl(filtersDto.getLstAcademicLvl(), idsCasesInDateRange);
            }

            if (filtersDto.getLstDrugs().size() > 0) {
                idsDrugs = reportExcelRepository.findIdCasesByDrugs(filtersDto.getLstDrugs(), idsCasesInDateRange);
            }

            if (filtersDto.getLstLvlRisk().size() > 0) {
                idsRiskLvl = reportExcelRepository.findIdCasesByRiskLvl(filtersDto.getLstLvlRisk(), idsCasesInDateRange);
            }

            if (filtersDto.getLstHearingType().size() > 0) {
                idsHearingType = reportExcelRepository.findIdCasesByHearingType(filtersDto.getLstHearingType(), idsCasesInDateRange);
            }

            if (filtersDto.getHasMonP() != null && filtersDto.getHasMonP() == true) {
                idsWithMonP = reportExcelRepository.findIdCasesWithMonP(idsCasesInDateRange);
            }

            //intersecciones de las listas
            finalIds = idsCasesInDateRange;

            if (idsGender != null) {
                finalIds = this.intersectIds(idsCasesInDateRange, idsGender);
            }

            if (idsStatusCase != null) {
                finalIds = this.intersectIds(finalIds, idsStatusCase);
            }

            if (idsStatusMeeting != null) {
                finalIds = this.intersectIds(finalIds, idsStatusMeeting);
            }

            if (idsStatusVerfi != null) {
                finalIds = this.intersectIds(finalIds, idsStatusVerfi);
            }

            if (idsMartialSt != null) {
                finalIds = this.intersectIds(finalIds, idsMartialSt);
            }

            if (idsActualJob != null) {
                finalIds = this.intersectIds(finalIds, idsActualJob);
            }

            if (idsDrugs != null) {
                finalIds = this.intersectIds(finalIds, idsDrugs);
            }

            if (idsAcademicLvl != null) {
                finalIds = this.intersectIds(finalIds, idsAcademicLvl);
            }

            if (idsRiskLvl != null) {
                finalIds = this.intersectIds(finalIds, idsRiskLvl);
            }

            if (idsRiskLvl != null) {
                finalIds = this.intersectIds(finalIds, idsRiskLvl);
            }

            if (idsHearingType != null) {
                finalIds = this.intersectIds(finalIds, idsHearingType);
            }

            if (idsWithMonP != null) {
                finalIds = this.intersectIds(finalIds, idsWithMonP);
            }
        }

        return finalIds;
    }


    private List<Long> intersectIds(List<Long> listA, List<Long> listB) {

        List<Long> intersectList = new ArrayList<>();

        for (Long act : listB) {
            if (listA.contains(act))
                intersectList.add(act);
        }

        return intersectList;
    }


    @Autowired
    CaseRepository caseRepository;
    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;

    @RequestMapping(value = "/director/excelReport/jxls", method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response, String ids) {

        Map beans = new HashMap();

        XLSTransformer transformer = new XLSTransformer();

        try {

            Gson conv = new Gson();

            List<Long> casesIds = conv.fromJson(ids, new TypeToken<List<Long>>() {
            }.getType());
            ;

            if (!(casesIds.size() > 0))
                casesIds.add(-1L);

            List<ExcelCaseInfoDto> listCases = caseRepository.getInfoCases(casesIds);

            List<ExcelActivitiesDto> lstActivities = caseRepository.getInfoImputedActivities(casesIds);
            List<ExcelImputedHomeDto> lstHomes = caseRepository.getInfoImputedHomes(casesIds);
            List<ExcelSocialNetworkDto> lstSN = caseRepository.getInfoSocialNetwork(casesIds);
            List<ExcelReferenceDto> lstRef = caseRepository.getInfoReference(casesIds);
            List<ExcelJobDto> lstJob = caseRepository.getInfoJobs(casesIds);
            List<ExcelDrugDto> lstDrug = caseRepository.getInfoDrugs(casesIds);
            List<ExcelCrimeDto> lstCrimes = caseRepository.getInfoCrimes(casesIds);
            List<ExcelCoDefDto> lstCoDef = caseRepository.getInfoCoDef(casesIds);
            List<ExcelTecRevSelQuestDto> lstSelQuest = caseRepository.getInfoTecRevSelQuest(casesIds);
            List<ExcelVerificationDto> lstVerif = caseRepository.getInfoVerification(casesIds);
            String template = "{0}: {1} \n";
            for (ExcelVerificationDto evdto : lstVerif) {
                for (int i = 0; i < Constants.NAMES_MEETING.length; i++) {
                    String finalString = "";
                    List<FieldMeetingSource> fieldMeetingSources = fieldMeetingSourceRepository.getFieldMeetingBySource(evdto.getIdCase(), evdto.getIdSource(), Constants.ST_FIELD_VERIF_UNABLE, (i + 1));
                    if (fieldMeetingSources != null && fieldMeetingSources.size() > 0) {
                        for (FieldMeetingSource fms : fieldMeetingSources) {
                            String aux = template.replace("{0}", fms.getFieldVerification().getFieldName());
                            aux = aux.replace("{1}", fms.getValue());
                            finalString = finalString + aux;
                        }
                    }
                    switch (i) {
                        case 0:
                            evdto.setPersonalData(finalString);
                            break;
                        case 1:
                            evdto.setImputedHome(finalString);
                            break;
                        case 2:
                            evdto.setSocialNetwork(finalString);
                            break;
                        case 3:
                            evdto.setReference(finalString);
                            break;
                        case 4:
                            evdto.setJob(finalString);
                            break;
                        case 5:
                            evdto.setSchool(finalString);
                            break;
                        case 6:
                            evdto.setDrug(finalString);
                            break;
                        case 7:
                            evdto.setLeaveCountry(finalString);
                            break;
                    }
                }

            }

            for (ExcelCaseInfoDto cAct : listCases) {

                List<ExcelActivitiesDto> acts = new ArrayList<>();
                for (ExcelActivitiesDto aAct : lstActivities) {
                    if (aAct.getIdCase() == cAct.getIdCase()) {
                        acts.add(aAct);
                    }
                }
                cAct.setLstActivities(acts);

                List<ExcelImputedHomeDto> lstImHome = new ArrayList<>();
                for (ExcelImputedHomeDto hAct : lstHomes) {
                    if (hAct.getIdCase() == cAct.getIdCase()) {
                        lstImHome.add(hAct);
                    }
                }
                cAct.setLstHomes(lstImHome);

                List<ExcelSocialNetworkDto> lstCSN = new ArrayList<>();
                for (ExcelSocialNetworkDto snAct : lstSN) {
                    if (snAct.getIdCase() == cAct.getIdCase()) {
                        lstCSN.add(snAct);
                    }
                }
                cAct.setLstSN(lstCSN);

                List<ExcelReferenceDto> lstR = new ArrayList<>();
                for (ExcelReferenceDto rAct : lstRef) {
                    if (rAct.getIdCase() == cAct.getIdCase()) {
                        lstR.add(rAct);
                    }
                }
                cAct.setLstRef(lstR);

                List<ExcelJobDto> lstJ = new ArrayList<>();
                for (ExcelJobDto jAct : lstJob) {
                    if (jAct.getIdCase() == cAct.getIdCase()) {
                        lstJ.add(jAct);
                    }
                }
                cAct.setLstJob(lstJ);

                List<ExcelDrugDto> lstD = new ArrayList<>();
                for (ExcelDrugDto dAct : lstDrug) {
                    if (dAct.getIdCase() == cAct.getIdCase()) {
                        lstD.add(dAct);
                    }
                }
                cAct.setLstDrug(lstD);

                List<ExcelCrimeDto> lstCr = new ArrayList<>();
                for (ExcelCrimeDto crAct : lstCrimes) {
                    if (crAct.getIdCase() == cAct.getIdCase()) {
                        lstCr.add(crAct);
                    }
                }
                cAct.setLstCrimes(lstCr);

                List<ExcelCoDefDto> lstCo = new ArrayList<>();
                for (ExcelCoDefDto coAct : lstCoDef) {
                    if (coAct.getIdCase() == cAct.getIdCase()) {
                        lstCo.add(coAct);
                    }
                }
                cAct.setLstCoDef(lstCo);

                List<ExcelTecRevSelQuestDto> lstQu = new ArrayList<>();
                for (ExcelTecRevSelQuestDto quAct : lstSelQuest) {
                    if (quAct.getIdCase() == cAct.getIdCase()) {
                        lstQu.add(quAct);
                    }
                }
                cAct.setLstSelQuest(lstQu);
            }

            beans.put("listCases", listCases);

            beans.put("listCasesV", lstVerif);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            beans.put("dateFormat", dateFormat);

            ExcelConv excelConv = new ExcelConv();
            beans.put("excelConv", excelConv);

            UUID uid = UUID.randomUUID();

            File temp = File.createTempFile(uid.toString(), ".xls");
            String tempPath = temp.getAbsolutePath();

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jxlsTemplate/reportCase.xls";

            transformer.transformXLS(realContextPath, beans, tempPath);

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"infoCase.xls\"");

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
            ex.printStackTrace();
        }
    }


}