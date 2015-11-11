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
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.dto.victim.VictimDto;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.view.ReportExcelFiltersDto;
import com.umeca.model.entities.managereval.ExcelCaseInfoEvalDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.CrimeRepository;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.ScheduleRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.ExcelReportService;
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
    private UserRepository userRepository;

    @Autowired
    private SharedUserService userService;

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

        List<SelectList> casesType = new ArrayList<>();
        SelectList sup = new SelectList();
        SelectList eval = new SelectList();

        eval.setId(1L);
        eval.setName("Evaluación");

        sup.setId(2L);
        sup.setName("Supervisión");

        casesType.add(eval);
        casesType.add(sup);



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
        model.addObject("casesType", conv.toJson(casesType));

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

    @Autowired
    ExcelReportService excelReportService;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @RequestMapping(value = "/director/excelReport/findCases", method = RequestMethod.POST)
    public ResponseMessage findCases(@ModelAttribute ReportExcelFiltersDto filtersDto,Long caseTypeId) {

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
        Gson gson = new Gson();

        List<Long> lstStatusCase = new ArrayList<>();
        if(caseTypeId.equals(Constants.EVALUATION_CASE_TYPE)){
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

        }
        else if(caseTypeId.equals(Constants.SUPERVISOR_CASE_TYPE)){
            StatusCase hearingFormatStatus = statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END);
            StatusCase framingMeeting = statusCaseRepository.findByCode(Constants.CASE_STATUS_FRAMING_COMPLETE);
            lstStatusCase.add(hearingFormatStatus.getId());
            lstStatusCase.add(framingMeeting.getId());
        }

        filtersDto.setLstStatusCaseStr(gson.toJson(lstStatusCase));


        List<Long> idsCases = reportExcelRepository.findIdCasesByDates(initDate, endDate);

        idsCases = excelReportService.findCasesByFilters(idsCases, filtersDto);

        Gson conv = new Gson();

        return new ResponseMessage(false, conv.toJson(idsCases));
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

            if (!(casesIds.size() > 0))
                casesIds.add(-1L);

          //  List<ExcelCaseInfoDto> listCases = caseRepository.getInfoCases(casesIds);
            List<ExcelCaseInfoEvalDto> listCases = caseRepository.getInfoCasesEval(casesIds);



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
                    if (rAct.getIdCase().equals(cAct.getIdCase())) {
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
/*
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
                cAct.setSummaryVerificationSources(lstSources); */
            }

            /*supervision*/

            List<HearingFormatInfo> allHearingFormat = reportExcelRepository.getHearingFormatInfo(casesIds);

            /*for (ExcelCaseInfoDto actCase : listCases) {
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
            }*/

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
                    if (actSch.getIdCase().equals(actFM.getIdCase())) {
                        actSch.setLstSchedule(scheduleRepository.getScheduleDtoBySchoolId(actSch.getId()));
                        actFM.setSchool(actSch);
                        break;
                    }
                }

                List<FramingReferenceInfo> refs = new ArrayList<>();
                for (FramingReferenceInfo actRef : allReferences) {
                    if (actRef.getId().equals(actFM.getIdCase()))
                        refs.add(actRef);
                }
                actFM.setReferences(refs);

                List<CatalogDto> homes = new ArrayList<>();
                for (CatalogDto actHome : allFramingHomes) {
                    if (actHome.getId().equals(actFM.getIdCase()))
                        homes.add(actHome);
                }
                actFM.setHomes(homes);

                List<CatalogDto> summaryFramingHomes = new ArrayList<>();
                for (CatalogDto actHome : allSummaryFramingHomes) {
                    if (actHome.getId().equals(actFM.getIdCase()))
                        summaryFramingHomes.add(actHome);
                }
                actFM.setSummaryHomes(summaryFramingHomes);

                List<ExcelActivitiesDto> activities = new ArrayList<>();
                for (ExcelActivitiesDto actAct : allFramingActivities) {
                    if (actAct.getIdCase().equals(actFM.getIdCase())) {
                        actAct.setSchedule(scheduleRepository.getScheduleByFramingActivityId(actAct.getId()));
                        activities.add(actAct);
                    }
                }
                actFM.setActivities(activities);

                List<ExcelJobDto> jobs = new ArrayList<>();
                for (ExcelJobDto actJob : allFramingJobs) {
                    if (actJob.getIdCase().equals(actFM.getIdCase())) {
                        actJob.setSchedule(scheduleRepository.getScheduleByFramingActivityId(actJob.getId()));
                        jobs.add(actJob);
                    }
                }
                actFM.setJobs(jobs);

                List<ExcelDrugDto> drugs = new ArrayList<>();
                for (ExcelDrugDto actDrug : allDrugs) {
                    if (actDrug.getIdCase().equals(actFM.getIdCase()))
                        drugs.add(actDrug);
                }
                actFM.setDrugs(drugs);

                List<CatalogDto> addictedAcquaintances = new ArrayList<>();
                for (CatalogDto actAA : allAddictedAcquaintances) {
                    if (actAA.getId().equals(actFM.getIdCase()))
                        addictedAcquaintances.add(actAA);
                }
                actFM.setAddictedAcquaintances(addictedAcquaintances);

                List<ObligationIssuesInfo> obligationIssues = new ArrayList<>();
                for (ObligationIssuesInfo actOI : allObligationIssues) {
                    if (actOI.getIdCase().equals(actFM.getIdCase()))
                        obligationIssues.add(actOI);
                }
                actFM.setObligationIssues(obligationIssues);

                List<ObligationIssuesInfo> relativesAbroad = new ArrayList<>();
                for (ObligationIssuesInfo actRA : allRelativesAbroad) {
                    if (actRA.getIdCase().equals(actFM.getIdCase()))
                        relativesAbroad.add(actRA);
                }
                actFM.setRelativesAbroad(relativesAbroad);

                List<CatalogDto> sourcesSel = new ArrayList<>();
                for (CatalogDto actSS : allSelectedSourcesRel) {
                    if (actSS.getId().equals(actFM.getIdCase()))
                        sourcesSel.add(actSS);
                }
                actFM.setLinks(sourcesSel);

                List<CatalogDto> threatsSel = new ArrayList<>();
                for (CatalogDto actTS : allSelectedThreatsRel) {
                    if (actTS.getId().equals(actFM.getIdCase()))
                        threatsSel.add(actTS);
                }
                actFM.setThreats(threatsSel);

                List<CatalogDto> riskSel = new ArrayList<>();
                for (CatalogDto actRS : allSelectedRiskRel) {
                    if (actRS.getId().equals(actFM.getIdCase()))
                        riskSel.add(actRS);
                }
                actFM.setRisks(riskSel);


                List<Long> idsHF = reportExcelRepository.getLastHearingFormatByCase(actFM.getIdCase(), new PageRequest(0, 1));
                List<String> arran = null;

                if (idsHF != null && idsHF.size() > 0)
                    arran = reportExcelRepository.getArrangementsByFormat(idsHF.get(0));

                actFM.setArrangements(arran);
            }

           /* for (ExcelCaseInfoDto actCase : listCases) {
                FramingMeetingInfo aa = new FramingMeetingInfo();
                for (FramingMeetingInfo actFM : allFramingMeeting) {
                    if (actCase.getIdCase() == actFM.getIdCase())
                        actCase.setFramingMeetingInfo(actFM);
                }
            }*/

           /* for (ExcelCaseInfoDto actCase : listCases) {
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

            }*/
            /*supervision*/


            /*estadisticas*///

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

//            //estatus
//            summ.setTotMeeting(new Long(reportExcelRepository.findIdCasesByStatusMeetingStr(new ArrayList<String>() {{
//                add(Constants.S_MEETING_INCOMPLETE_LEGAL);
//            }}, idsCasesByDate).size()));
//
//            summ.setTotLegal(new Long(reportExcelRepository.findIdCasesByStatusMeetingStr(new ArrayList<String>() {{
//                add(Constants.S_MEETING_COMPLETE);
//            }}, idsCasesByDate).size()));
//
//            summ.setTotVerif(new Long(reportExcelRepository.findIdCasesByStatusMeetingStr(new ArrayList<String>() {{
//                add(Constants.VERIFICATION_STATUS_MEETING_COMPLETE);
//            }}, idsCasesByDate).size()));
//
//            summ.setTotTechRev(new Long(reportExcelRepository.findIdCasesByStatusCaseStr(new ArrayList<String>() {{
//                add(Constants.CASE_STATUS_TECHNICAL_REVIEW);
//            }}, idsCasesByDate).size()));
//
//            summ.setTotHearingF(new Long(reportExcelRepository.findIdCasesByStatusCaseStr(new ArrayList<String>() {{
//                add(Constants.CASE_STATUS_HEARING_FORMAT_END);
//            }}, idsCasesByDate).size()));
//
//            summ.setTotFMeeting(new Long(reportExcelRepository.findIdCasesByStatusCaseStr(new ArrayList<String>() {{
//                add(Constants.CASE_STATUS_FRAMING_COMPLETE);
//            }}, idsCasesByDate).size()));
//
//            summ.setTotMonP(new Long(reportExcelRepository.findIdCasesWithMonP(idsCasesByDate).size()));

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

            Long idCurrentUser  = sharedUserService.GetLoggedUserId();

            User currentUser = userRepository.findOne(idCurrentUser);

            Role currentRole = currentUser.getRoles().get(0);



            if(currentRole.getRole().equals(Constants.ROLE_DIRECTOR)) {
                // realContextPath += "/WEB-INF/jxlsTemplate/ExcelReportCasesDirector.xls";
                realContextPath += "/WEB-INF/jxlsTemplate/ExcelEvReport.xls";
            }
            else if(currentRole.getRole().equals(Constants.ROLE_EVALUATION_MANAGER)) {
                realContextPath += "/WEB-INF/jxlsTemplate/ExcelEvReport.xls";
            }
            if(currentRole.getRole().equals(Constants.ROLE_SUPERVISOR_MANAGER)) {
                realContextPath += "/WEB-INF/jxlsTemplate/ExcelReportCasesDirector.xls";
            }

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
            logException.Write(ex, this.getClass(), "jxlsMethod", sharedUserService);
        }
    }

}
