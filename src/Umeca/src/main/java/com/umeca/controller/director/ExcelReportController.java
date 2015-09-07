package com.umeca.controller.director;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.controller.shared.ExcelConv;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.Municipality;
import com.umeca.model.catalog.State;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.dto.victim.VictimDto;
import com.umeca.model.entities.director.view.ReportExcelFiltersDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.CrimeRepository;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.ScheduleRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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

        if (idsCases == null || !(idsCases.size() > 0)) {
            idsCases = new ArrayList<>();
            idsCases.add("-1");
        }

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


    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private GroupCrimeRepository groupCrimeRepository;

    @RequestMapping(value = "/director/excelReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/director/excelReport/index");


        List<State> states = stateRepository.findStatesByCountryAlpha2("MX");
        List<CatalogDto> lstStates = new ArrayList<>();

        for (State act : states) {
            CatalogDto dto = new CatalogDto();
            dto.setId(act.getId());
            dto.setName(act.getName());
            lstStates.add(dto);
        }

        Gson conv = new Gson();

        List<SelectList> lstCrimes = new ArrayList<>();
        lstCrimes = groupCrimeRepository.findAllCrimeGroupsForView();

        List<ArrangementView> lstArrangement = new ArrayList<>();
        lstArrangement = arrangementRepository.findAllArrangementForView();

        List<SelectList> lstActivities = new ArrayList<>();
        lstActivities = supervisionActivityRepository.findAllValidSl();

        model.addObject("lstStates", conv.toJson(lstStates));
        model.addObject("lstCrimes", conv.toJson(lstCrimes));
        model.addObject("lstArrangement", conv.toJson(lstArrangement));
        model.addObject("lstActivities", conv.toJson(lstActivities));

        return model;
    }

    @RequestMapping(value = "/director/excelReport/getMunBySt", method = RequestMethod.POST)
    ResponseMessage getMunicipality(Long idSt) {
        ResponseMessage response = new ResponseMessage();
        List<CatalogDto> lstMun = new ArrayList<>();
        List<Municipality> municipalities = municipalityRepository.findByIdState(idSt);

        for (Municipality act : municipalities) {
            CatalogDto dto = new CatalogDto();
            dto.setId(act.getId());
            dto.setName(act.getName());
            lstMun.add(dto);
        }

        Gson conv = new Gson();

        response.setHasError(false);
        response.setMessage(conv.toJson(lstMun));

        return response;
    }

    @RequestMapping(value = "/director/excelReport/getLocationsByMun", method = RequestMethod.POST)
    ResponseMessage getLocations(Long idMun) {

        ResponseMessage response = new ResponseMessage();
        List<CatalogDto> lstLoc = new ArrayList<>();
        List<Location> locations = locationRepository.findLocationByMunId(idMun);

        for (Location act : locations) {
            CatalogDto dto = new CatalogDto();
            dto.setId(act.getId());
            dto.setName(act.getName());
            lstLoc.add(dto);
        }

        Gson conv = new Gson();

        response.setHasError(false);
        response.setMessage(conv.toJson(lstLoc));

        return response;
    }

    @Autowired
    ReportExcelRepository reportExcelRepository;
    @Autowired
    HearingFormatRepository hearingFormatRepository;
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

        List<Long> idsCases = reportExcelRepository.findIdCasesByDates(initDate, endDate); //OBTENGO TODOS LOS CASOS QUE NO ESTEN OBSOLETOS NI NO JUDICIALIZADOS

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
        List<Long> idsHomePlace = null;

        List<Long> idsCrimesInLegal = null;
        List<Long> idsCrimesInFormat = null;
        List<Long> idsFinalCrimes = null;

        List<Long> idsArrangements = null;
        List<Long> idsActivities = null;

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

            if (filtersDto.getHomePlace() != null && filtersDto.getHomePlace() == true) {
                idsHomePlace = reportExcelRepository.findIdCasesByLocation(idsCasesInDateRange, filtersDto.getIdLoc());
            }

            if (filtersDto.getLstCrime().size() > 0) {
                idsCrimesInLegal = reportExcelRepository.findIdCasesByCrimesInLegal(idsCasesInDateRange, filtersDto.getLstCrime());
            }

            if (filtersDto.getLstCrime().size() > 0) {
                idsCrimesInFormat = reportExcelRepository.findIdCasesByCrimesInFormat(idsCasesInDateRange, filtersDto.getLstCrime());
            }

            if (filtersDto.getLstActivities().size() > 0) {
                idsActivities = reportExcelRepository.findIdCasesByMonitoringActivities(idsCasesInDateRange, filtersDto.getLstActivities());
            }

            if (filtersDto.getLstArrangement().size() > 0) {
                idsArrangements = reportExcelRepository.findIdCasesByArrangements(idsCasesInDateRange, filtersDto.getLstArrangement());
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

            if (idsHomePlace != null) {
                finalIds = this.intersectIds(finalIds, idsHomePlace);
            }

            if (idsCrimesInLegal != null && idsCrimesInFormat != null) {
                idsFinalCrimes = this.conjunctionIds(idsCrimesInLegal, idsCrimesInFormat);
            }

            if (idsFinalCrimes != null) {
                finalIds = this.intersectIds(finalIds, idsFinalCrimes);
            }

            if (idsActivities != null) {
                finalIds = this.intersectIds(finalIds, idsActivities);
            }

            if (idsArrangements != null) {
                finalIds = this.intersectIds(finalIds, idsArrangements);
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

    private List<Long> conjunctionIds(List<Long> listA, List<Long> listB) {

        List<Long> conjunctionList = new ArrayList<>();

        for (Long act : listA) {
            conjunctionList.add(act);
        }

        for (Long act : listB) {
            if (!listA.contains(act))
                conjunctionList.add(act);
        }

        return conjunctionList;
    }

    private ReportExcelSummary fillSummary(String filters) {

        ReportExcelSummary summary = new ReportExcelSummary();


        return summary;
    }


    @Autowired
    CaseRepository caseRepository;
    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;
    @Autowired
    ArrangementRepository arrangementRepository;
    @Autowired
    SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    ActivityGoalRepository activityGoalRepository;
    @Autowired
    FramingReferenceRepository framingReferenceRepository;
    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    CrimeRepository crimeRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    FulfillmentReportRepository fulfillmentReportRepository;

    @RequestMapping(value = "/director/excelReport/jxls", method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response, String ids, String filt) {

        Map beans = new HashMap();

        XLSTransformer transformer = new XLSTransformer();

        try {

            Gson conv = new Gson();

            List<Long> casesIds = conv.fromJson(ids, new TypeToken<List<Long>>() {
            }.getType());
            ;

            if (casesIds == null || !(casesIds.size() > 0)) {
                casesIds = new ArrayList<Long>();
                casesIds.add(-1L);
            }


            List<ExcelActivitiesDto> lstActivities = caseRepository.getInfoImputedActivities(casesIds);
            List<ExcelImputedHomeDto> lstHomes = caseRepository.getInfoImputedHomes(casesIds);
            List<ExcelSocialNetworkDto> lstSN = caseRepository.getInfoSocialNetwork(casesIds);
            List<ExcelReferenceDto> lstRef = caseRepository.getInfoReference(casesIds);
            List<ExcelJobDto> lstJob = caseRepository.getInfoJobs(casesIds);
            List<ExcelDrugDto> lstDrug = caseRepository.getInfoDrugs(casesIds);
            List<ExcelCrimeDto> lstCrimes = caseRepository.getInfoCrimes(casesIds);
            List<VictimDto> lstVictims = caseRepository.getInfoVictims(casesIds);
            List<ExcelCoDefDto> lstCoDef = caseRepository.getInfoCoDef(casesIds);
            List<ExcelTecRevSelQuestDto> lstSelQuest = caseRepository.getInfoTecRevSelQuest(casesIds);


            //de la lista de casos en el rango de fechas se separan los que tienen meeting terminado y verificación finalizada y los que no

            List<ExcelCaseInfoDto> listCases = caseRepository.getInfoCases(casesIds);
            List<ExcelVerificationDto> lstVerif = caseRepository.getInfoVerification(casesIds);
            List<ExcelVerificationDto> lstAllSourcesVerif = caseRepository.getSourcesVerification(casesIds);


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

                List<VictimDto> lstVict = new ArrayList<>();
                for (VictimDto hAct : lstVictims) {
                    if (hAct.getId() == cAct.getIdCase()) {
                        lstVict.add(hAct);
                    }
                }
                cAct.setLstVictim(lstVict);

                List<ExcelVerificationDto> lstSources = new ArrayList<>();
                for (ExcelVerificationDto actSource : lstAllSourcesVerif) {
                    if (actSource.getIdCase() == cAct.getIdCase()) {
                        lstSources.add(actSource);
                    }
                }
                cAct.setSummaryVerificationSources(lstSources);
            }

            /*supervision*/

            List<HearingFormatInfo> allHearingFormat = reportExcelRepository.getHearingFormatInfo(casesIds);

            for (ExcelCaseInfoDto actCase : listCases) {
                List<HearingFormatInfo> lstFormats = new ArrayList<>();

                for (HearingFormatInfo actHF : allHearingFormat) {

                    actHF.setAssignedArran(reportExcelRepository.getArrangementsByFormat(actHF.getIdFormat()));
                    actHF.setContacts(reportExcelRepository.getContactsByFormat(actHF.getIdFormat()));

                    String crimesStr = "";
                    String crimesSummaryStr = "";

                    for (Crime actCrime : crimeRepository.findListCrimeHearingFormatByIdHF(actHF.getIdFormat())) {
                        if (crimesStr != "")
                            crimesStr += "\n";
                        crimesStr += CrimeDto.toStringCrime(actCrime);

                        if (crimesSummaryStr != "")
                            crimesSummaryStr += ", ";
                        crimesSummaryStr += actCrime.getCrime().getName();
                    }

                    actHF.setCrimes(crimesStr);
                    actHF.setSummaryCrimes(crimesSummaryStr);

                    if (actHF.getIdCase() == actCase.getIdCase()) {
                        lstFormats.add(actHF);
                    }
                }

                actCase.setFormatsInfo(lstFormats);

                if (lstFormats.size() > 0) {
                    actCase.setLastFormatInfo(lstFormats.get(lstFormats.size() - 1));
                    actCase.getLastFormatInfo().setTotalFormats(Integer.toString(lstFormats.size()));
                }
            }

            List<FramingMeetingInfo> allFramingMeeting = reportExcelRepository.getFramingMeetingInfo(casesIds);
            List<FramingReferenceInfo> allReferences = reportExcelRepository.getFramingReferenceInfo(casesIds);
            List<SchoolDto> allFramingSchool = reportExcelRepository.getAllFramingSchool(casesIds);

            List<ExcelJobDto> allFramingJobs = reportExcelRepository.getFramingInfoJobs(casesIds);
            List<ExcelActivitiesDto> allFramingActivities = reportExcelRepository.getFramingInfoActivities(casesIds);

            List<CatalogDto> allFramingHomes = reportExcelRepository.getFramingHomes(casesIds);
            List<CatalogDto> allSummaryFramingHomes = reportExcelRepository.getSummaryFramingHomes(casesIds);

            List<ExcelDrugDto> allDrugs = reportExcelRepository.getFramingInfoDrugs(casesIds);
            List<CatalogDto> allAddictedAcquaintances = reportExcelRepository.getFramingAddictedAcquaintances(casesIds);
            List<ObligationIssuesInfo> allObligationIssues = reportExcelRepository.getFramingObligationIssues(casesIds);
            List<ObligationIssuesInfo> allRelativesAbroad = reportExcelRepository.getFramingRelativesAbroad(casesIds);

            List<CatalogDto> allSelectedSourcesRel = reportExcelRepository.getFramingSelectedSourceRel(casesIds);
            List<CatalogDto> allSelectedThreatsRel = reportExcelRepository.getFramingSelectedThreatsRel(casesIds);
            List<CatalogDto> allSelectedRiskRel = reportExcelRepository.getFramingSelectedRiskRel(casesIds);

            for (FramingMeetingInfo actFM : allFramingMeeting) {

                for (SchoolDto actSch : allFramingSchool) {
                    if (actSch.getIdCase() == actFM.getIdCase()) {
                        actSch.setLstSchedule(scheduleRepository.getScheduleDtoBySchoolId(actSch.getId()));
                        actFM.setSchool(actSch);
                        break;
                    }
                }

                List<FramingReferenceInfo> refs = new ArrayList<>();
                for (FramingReferenceInfo actRef : allReferences) {
                    if (actRef.getId() == actFM.getIdCase())
                        refs.add(actRef);
                }
                actFM.setReferences(refs);

                List<CatalogDto> homes = new ArrayList<>();
                for (CatalogDto actHome : allFramingHomes) {
                    if (actHome.getId() == actFM.getIdCase())
                        homes.add(actHome);
                }
                actFM.setHomes(homes);

                List<CatalogDto> summaryFramingHomes = new ArrayList<>();
                for (CatalogDto actHome : allSummaryFramingHomes) {
                    if (actHome.getId() == actFM.getIdCase())
                        summaryFramingHomes.add(actHome);
                }
                actFM.setSummaryHomes(summaryFramingHomes);

                List<ExcelActivitiesDto> activities = new ArrayList<>();
                for (ExcelActivitiesDto actAct : allFramingActivities) {
                    if (actAct.getIdCase() == actFM.getIdCase()) {
                        actAct.setSchedule(scheduleRepository.getScheduleByFramingActivityId(actAct.getId()));
                        activities.add(actAct);
                    }
                }
                actFM.setActivities(activities);

                List<ExcelJobDto> jobs = new ArrayList<>();
                for (ExcelJobDto actJob : allFramingJobs) {
                    if (actJob.getIdCase() == actFM.getIdCase()) {
                        actJob.setSchedule(scheduleRepository.getScheduleByFramingActivityId(actJob.getId()));
                        jobs.add(actJob);
                    }
                }
                actFM.setJobs(jobs);

                List<ExcelDrugDto> drugs = new ArrayList<>();
                for (ExcelDrugDto actDrug : allDrugs) {
                    if (actDrug.getIdCase() == actFM.getIdCase())
                        drugs.add(actDrug);
                }
                actFM.setDrugs(drugs);

                List<CatalogDto> addictedAcquaintances = new ArrayList<>();
                for (CatalogDto actAA : allAddictedAcquaintances) {
                    if (actAA.getId() == actFM.getIdCase())
                        addictedAcquaintances.add(actAA);
                }
                actFM.setAddictedAcquaintances(addictedAcquaintances);

                List<ObligationIssuesInfo> obligationIssues = new ArrayList<>();
                for (ObligationIssuesInfo actOI : allObligationIssues) {
                    if (actOI.getIdCase() == actFM.getIdCase())
                        obligationIssues.add(actOI);
                }
                actFM.setObligationIssues(obligationIssues);

                List<ObligationIssuesInfo> relativesAbroad = new ArrayList<>();
                for (ObligationIssuesInfo actRA : allRelativesAbroad) {
                    if (actRA.getIdCase() == actFM.getIdCase())
                        relativesAbroad.add(actRA);
                }
                actFM.setRelativesAbroad(relativesAbroad);

                List<CatalogDto> sourcesSel = new ArrayList<>();
                for (CatalogDto actSS : allSelectedSourcesRel) {
                    if (actSS.getId() == actFM.getIdCase())
                        sourcesSel.add(actSS);
                }
                actFM.setLinks(sourcesSel);

                List<CatalogDto> threatsSel = new ArrayList<>();
                for (CatalogDto actTS : allSelectedThreatsRel) {
                    if (actTS.getId() == actFM.getIdCase())
                        threatsSel.add(actTS);
                }
                actFM.setThreats(threatsSel);

                List<CatalogDto> riskSel = new ArrayList<>();
                for (CatalogDto actRS : allSelectedRiskRel) {
                    if (actRS.getId() == actFM.getIdCase())
                        riskSel.add(actRS);
                }
                actFM.setRisks(riskSel);


                List<Long> idsHF = reportExcelRepository.getLastHearingFormatByCase(actFM.getIdCase(), new PageRequest(0, 1));
                List<String> arran = null;

                if (idsHF != null && idsHF.size() > 0)
                    arran = reportExcelRepository.getArrangementsByFormat(idsHF.get(0));

                actFM.setArrangements(arran);
            }

            for (ExcelCaseInfoDto actCase : listCases) {
                FramingMeetingInfo aa = new FramingMeetingInfo();
                for (FramingMeetingInfo actFM : allFramingMeeting) {
                    if (actCase.getIdCase() == actFM.getIdCase())
                        actCase.setFramingMeetingInfo(actFM);
                }
            }

            for (ExcelCaseInfoDto actCase : listCases) {
                if (actCase.getFramingMeetingInfo() == null)
                    actCase.setFramingMeetingInfo(new FramingMeetingInfo());
            }

            List<SelectList> allFulfillmentReport = fulfillmentReportRepository.finAllSummaryInfoFulfillmentReportInfo(casesIds);

            for (ExcelCaseInfoDto actCase : listCases) {

                MonitoringPlanExcelInfo monInfo = new MonitoringPlanExcelInfo();

                List<Long> idsHF = reportExcelRepository.getLastHearingFormatByCase(actCase.getIdCase(), new PageRequest(0, 1));


                SupervisionLogReport slr = null;
                Long lastFormatId = null;

                if (idsHF != null & idsHF.size() > 0)
                    lastFormatId = idsHF.get(0);

                if (lastFormatId != null)
                    slr = hearingFormatRepository.findSupervisionLogReportById(lastFormatId);

                monInfo.setSupervisionLogReport(slr);

                List<SelectList> lstSources = framingReferenceRepository.findAllValidByCaseId(actCase.getIdCase());
                monInfo.setLstSources(lstSources);
                List<SelectList> lstArr = arrangementRepository.findLstArrangementByCaseId(actCase.getIdCase());
                monInfo.setLstArrangement(lstArr);

                List<SelectList> lstActMP = new ArrayList<>();
                List<SelectList> lstGoals = new ArrayList<>();
                List<ActivityMonitoringPlanLog> lstActMonP = new ArrayList<>();
                List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement = new ArrayList<>();

                if (actCase.getIdMonP() != null && actCase.getIdMonP() > 0) {
                    lstActMP = supervisionActivityRepository.findByMonPlanId(actCase.getIdMonP());
                    lstGoals = activityGoalRepository.findByMonPlanId(actCase.getIdMonP());
                    lstActMonP = activityMonitoringPlanRepository.getListByMonPlanId(actCase.getIdMonP());
                    lstActMonPlanArrangement = activityMonitoringPlanRepository.getListActMonPlanArrangementByMonPlanId(actCase.getIdMonP());
                }

                monInfo.setLstActivities(lstActMP);
                monInfo.setLstGoals(lstGoals);
                monInfo.setLstActMonPlan(lstActMonP);
                monInfo.setLstActMonPlanArrangement(lstActMonPlanArrangement);
                monInfo.doReconstructedActivityInfo();


                List<SelectList> lstFulfimentRep = new ArrayList<>();
                for (SelectList actRep : allFulfillmentReport) {
                    if (actRep.getId() == actCase.getIdCase()) {
                        lstFulfimentRep.add(actRep);
                    }
                }

                monInfo.setLstFulfillment(lstFulfimentRep);
                actCase.setMonitoringPlanExcelInfo(monInfo);

            }
            /*supervision*/

            /*ESTADISTICAS*/

            ReportExcelSummary summ = conv.fromJson(filt, new TypeToken<ReportExcelSummary>() {
            }.getType());

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

            List<Long> idsCasesBetweenDates = reportExcelRepository.findIdCasesByDates(initDate, endDate);

            List<Long> idsEvaluation = reportExcelRepository.getCasesEvaluation(initDate, endDate);
            List<Long> idsMeeting = reportExcelRepository.getCasesIdsMeeting(initDate, endDate);
            List<Long> idsVerification = reportExcelRepository.getCasesIdsVerif(initDate, endDate);

            if(idsEvaluation.size()==0)
                idsEvaluation.add(-1L);
            if(idsMeeting.size()==0)
                idsMeeting.add(-1L);
            if(idsVerification.size()==0)
                idsVerification.add(-1L);

            List<Long> idsSupervision = reportExcelRepository.getCasesSupervision(initDate, endDate);
            if(idsSupervision.size()==0)
                idsSupervision.add(-1L);

            summ.setTotCases(new Long(idsCasesBetweenDates.size()));
            summ.setTotCasesEv(new Long(idsEvaluation.size()));
            summ.setTotCasesSup(new Long(idsSupervision.size()));

            //genero
            List<SelectList> lstAllCasesGenderMeeting = reportExcelRepository.getAllGenderMeeting(idsMeeting);
            List<SelectList> lstAllCasesGenderVerif = reportExcelRepository.getAllGenderVerification(idsVerification);
            List<SelectList> lstAllCasesGenderSup = reportExcelRepository.getAllGenderSup(idsSupervision);

            summ.setTotFemEv(summ.countGenderEval(lstAllCasesGenderMeeting, true) + summ.countGenderEval(lstAllCasesGenderVerif, true));
            summ.setTotMascEv(summ.countGenderEval(lstAllCasesGenderMeeting, false) + summ.countGenderEval(lstAllCasesGenderVerif, false));
            summ.setTotFemSup(summ.countGenderSup(lstAllCasesGenderSup, 1));
            summ.setTotMascSup(summ.countGenderSup(lstAllCasesGenderSup, 2));

            List<SelectList> lstAllCasesMarStMeeting = reportExcelRepository.getAllMaritalStatusMeeting(idsMeeting);
            List<SelectList> lstAllCasesMarStVerif = reportExcelRepository.getAllMaritalStatusVerif(idsVerification);
            List<SelectList> lstAllCasesMarStSup = reportExcelRepository.getAllMaritalStatusSup(idsSupervision);

            //estado civil
            summ.setTotSoltEv(summ.countLongId(lstAllCasesMarStMeeting, Constants.MARITAL_SINGLE) + summ.countLongId(lstAllCasesMarStVerif, Constants.MARITAL_SINGLE));
            summ.setTotCasEv(summ.countLongId(lstAllCasesMarStMeeting, Constants.MARITAL_MARRIED) + summ.countLongId(lstAllCasesMarStVerif, Constants.MARITAL_MARRIED));
            summ.setTotDivEv(summ.countLongId(lstAllCasesMarStMeeting, Constants.MARITAL_DIVORCED) + summ.countLongId(lstAllCasesMarStVerif, Constants.MARITAL_DIVORCED));
            summ.setTotULEv(summ.countLongId(lstAllCasesMarStMeeting, Constants.MARITAL_FREE_UNION) + summ.countLongId(lstAllCasesMarStVerif, Constants.MARITAL_FREE_UNION));
            summ.setTotViuEv(summ.countLongId(lstAllCasesMarStMeeting, Constants.MARITAL_WIDOWER) + summ.countLongId(lstAllCasesMarStVerif, Constants.MARITAL_WIDOWER));

            summ.setTotSoltSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_SINGLE));
            summ.setTotCasSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_MARRIED));
            summ.setTotDivSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_DIVORCED));
            summ.setTotULSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_FREE_UNION));
            summ.setTotViuSup(summ.countLongId(lstAllCasesMarStSup, Constants.MARITAL_WIDOWER));

            //empleo

            summ.setTotEmpEv(new Long(reportExcelRepository.getAllCurrentJobMeeting(idsMeeting).size() + reportExcelRepository.getAllCurrentJobVerifi(idsVerification).size()));
            summ.setTotDesempEv(new Long(reportExcelRepository.getAllNoJobMeeting(idsMeeting).size() + reportExcelRepository.getAllNoJobVerif(idsVerification).size()));

            summ.setTotEmpSup(new Long(reportExcelRepository.getAllCurrentJobSup(idsSupervision).size()));
            summ.setTotDesempSup(new Long(reportExcelRepository.getAllNoJobSup(idsSupervision).size()));

            //nivel academico

            List<SelectList> lstAllCasesAcLvlMeeting = reportExcelRepository.getAllAcLvlMeeting(idsMeeting);
            List<SelectList> lstAllCasesAcLvlVerif = reportExcelRepository.getAllAcLvlVerif(idsVerification);
            List<SelectList> lstAllCasesAcLvlSup = reportExcelRepository.getAllAcLvlSup(idsSupervision);

            summ.setTotSIAEv(summ.countLongId(lstAllCasesAcLvlMeeting, Constants.AC_LVL_ILLITERATE) + summ.countLongId(lstAllCasesAcLvlVerif, Constants.AC_LVL_ILLITERATE));
            summ.setTotPrimEv(summ.countLongId(lstAllCasesAcLvlMeeting, Constants.AC_LVL_PRIMARY) + summ.countLongId(lstAllCasesAcLvlVerif, Constants.AC_LVL_PRIMARY));
            summ.setTotSecuEv(summ.countLongId(lstAllCasesAcLvlMeeting, Constants.AC_LVL_HIGH_SCH) + summ.countLongId(lstAllCasesAcLvlVerif, Constants.AC_LVL_HIGH_SCH));
            summ.setTotBachEv(summ.countLongId(lstAllCasesAcLvlMeeting, Constants.AC_LVL_BACHELOR) + summ.countLongId(lstAllCasesAcLvlVerif, Constants.AC_LVL_BACHELOR));
            summ.setTotLicEv(summ.countLongId(lstAllCasesAcLvlMeeting, Constants.AC_LVL_UNIVERSITY) + summ.countLongId(lstAllCasesAcLvlVerif, Constants.AC_LVL_UNIVERSITY));
            summ.setTotPostgEv(summ.countLongId(lstAllCasesAcLvlMeeting, Constants.AC_LVL_GRADUATE) + summ.countLongId(lstAllCasesAcLvlVerif, Constants.AC_LVL_GRADUATE));
            summ.setTotAcLvlOtroEv(summ.countLongId(lstAllCasesAcLvlMeeting, Constants.AC_LVL_OTHER) + summ.countLongId(lstAllCasesAcLvlVerif, Constants.AC_LVL_OTHER));

            summ.setTotSIASup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_ILLITERATE));
            summ.setTotPrimSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_PRIMARY));
            summ.setTotSecuSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_HIGH_SCH));
            summ.setTotBachSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_BACHELOR));
            summ.setTotLicSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_UNIVERSITY));
            summ.setTotPostgSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_GRADUATE));
            summ.setTotAcLvlOtroSup(summ.countLongId(lstAllCasesAcLvlSup, Constants.AC_LVL_OTHER));

            //drogas

            List<SelectList> lstAllDrugsMeeting = reportExcelRepository.getAllDrugsMeeting(idsMeeting);
            List<SelectList> lstAllDrugsVerif = reportExcelRepository.getAllDrugsVerif(idsVerification);
            List<SelectList> lstAllDrugsSup = reportExcelRepository.getAllDrugsSup(idsSupervision);

            summ.setTotAlcoEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_ALCOHOL)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_ALCOHOL));
            summ.setTotMariEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_MARIHUANA)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_MARIHUANA));
            summ.setTotCocaEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_COCAIN)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_COCAIN));
            summ.setTotHeroEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_HEROIN)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_HEROIN));
            summ.setTotOpioEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_OPIUM)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_OPIUM));
            summ.setTotPBCEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_PBC)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_PBC));
            summ.setTotSolvenEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_SOLV)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_SOLV));
            summ.setTotCementEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_CEME)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_CEME));
            summ.setTotLSDEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_LSD)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_LSD));
            summ.setTotAnfetEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_AMPH)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_AMPH));
            summ.setTotMetanfEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_META)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_META));
            summ.setTotExtaEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_EXTA)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_EXTA));
            summ.setTotHongoEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_MUSH)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_MUSH));
            summ.setTotDrgOtroEv(summ.countLongId(lstAllDrugsMeeting, Constants.DRUG_OTHER)+summ.countLongId(lstAllDrugsVerif, Constants.DRUG_OTHER));

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

            //no casos por status
            List<ExcelStatusCasesInfo> allStatusCasesInfo = reportExcelRepository.getAllCasesAtEvaluationSupervision(casesIds);
            List<SelectList> allCasesWithHearingFormatFinished = reportExcelRepository.getAllCasesWithFinishedFormat(casesIds);
            summ.setAllCasesIds(allStatusCasesInfo);

            for (ExcelStatusCasesInfo actInfo : summ.getAllCasesIds()) {
                for (SelectList actHF : allCasesWithHearingFormatFinished) {
                    if (actHF.getId() == actInfo.getIdCase()) {
                        actInfo.setIdFirstFormatFinished(actHF.getAux());
                    }
                }
            }

            summ.doCountIds(); //realiza los conteos por status

            /*summary*/

            beans.put("summ", summ);
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
