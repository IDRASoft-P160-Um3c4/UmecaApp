package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.shared.ActMonPlanDto;
import com.umeca.model.entities.shared.LogCase;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.*;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.reviewer.CrimeRepository;
import com.umeca.repository.shared.LogCaseRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class LogCaseServiceImpl implements LogCaseService {

    @Autowired
    HearingFormatRepository hearingFormatRepository;
    @Autowired
    FramingReferenceRepository framingReferenceRepository;
    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    ArrangementRepository arrangementRepository;
    @Autowired
    SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    ActivityGoalRepository activityGoalRepository;
    @Autowired
    CrimeRepository crimeRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    FramingMeetingRepository framingMeetingRepository;
    @Autowired
    UpDwFileService upDwFileService;
    @Autowired
    LogCaseRepository logCaseRepository;
    @Autowired
    CaseRepository caseRepository;

    @Override
    public void fillgeneralDataLog(Long caseId, ModelAndView model){
        fillInfoCase(caseId,model,false);
    }

    public void fillInfoCase(Long caseId, ModelAndView model, Boolean isFile) {
        if(isFile== null){
            isFile =false;
        }
        Long lHearingFormatId = hearingFormatRepository.lastHearingFormatIdsByIdCase(caseId);
        SupervisionLogReport slr = hearingFormatRepository.findSupervisionLogReportById(lHearingFormatId);
        Long id = monitoringPlanRepository.getMonPlanIdByCaseId(caseId);
        List<Crime> listCrime = crimeRepository.findListCrimeHearingFormatByIdHF(lHearingFormatId);
        List<String> listCrimeDtos = new ArrayList<>();
        for(Crime c: listCrime){
            listCrimeDtos.add(new CrimeDto().toStringCrime(c));
        }
        Gson gson = new Gson();
        if(slr!=null){
            model.addObject("imputedName", StringEscape.escapeText(slr.getImputedName()));
            model.addObject("mpId", id);
            if(isFile){
                model.addObject("crime", listCrimeDtos);
            }else{
                model.addObject("crime", gson.toJson(listCrimeDtos));
            }
            model.addObject("judge", StringEscape.escapeText(slr.getJudge()));
            model.addObject("defender", StringEscape.escapeText(slr.getDefender()));
            model.addObject("mp", StringEscape.escapeText(slr.getMp()));
            model.addObject("imputedTel", StringEscape.escapeText(slr.getImputedTel()));
            model.addObject("imputedAddr", StringEscape.escapeText(slr.getImputedAddr()));
        }else{
            CaseInfo caseInfo = caseRepository.getInfoById(caseId);
            model.addObject("imputedName", StringEscape.escapeText(caseInfo.getPersonName()));
        }
        model.addObject("caseId", caseId);
        List<SelectList> lstMoral = framingReferenceRepository.findAccompanimentReferences(id);

        String cad = "";
        for (SelectList act : lstMoral) {
            if (cad != "")
                cad += " / ";
            cad += StringEscape.escapeText(act.getName()) + " - " + act.getDescription();
        }

        model.addObject("moralName", cad);
        //model.addObject("moralPhone", slr.getMoralPhone());

        List<SelectList> lstResol = hearingFormatRepository.getInfoResolution(id);

        String lastResol = "", allResol = "";

        if (lstResol != null && lstResol.size() > 0) {
            for (int i = 0; i < lstResol.size(); i++) {

                if (allResol != "")
                    allResol += " <br/> ";

                allResol += CalendarExt.calendarToFormatString(lstResol.get(i).getCalendar(), Constants.FORMAT_CALENDAR_I);

                if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_MC)
                    allResol += " - MC";
                else if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_SCP)
                    allResol += " - SCPP";
            }
        }

        String[] arr = allResol.split("<br/>");
        if (arr.length > 0)
            lastResol = arr[arr.length - 1];
        else
            lastResol = allResol;

        model.addObject("prevResolution", allResol);
        model.addObject("lastResolution", lastResol);

        String closeComment = "";
        closeComment = monitoringPlanRepository.getCloseComment(id, MonitoringConstants.STATUS_PENDING_END, Constants.CASE_STATUS_CLOSED);

        model.addObject("closeComment", StringEscape.escapeText(closeComment));

        List<SelectList> lstGeneric = arrangementRepository.findLstArrangementByCaseId(caseId);
        model.addObject("lstHfAssignedArrangement", isFile?lstGeneric:gson.toJson(lstGeneric));
        lstGeneric = supervisionActivityRepository.findByMonPlanId(id);
        model.addObject("lstActivities", isFile?lstGeneric:gson.toJson(lstGeneric));
        lstGeneric = activityGoalRepository.findByMonPlanId(id);
        model.addObject("lstGoals", isFile?lstGeneric:gson.toJson(lstGeneric));
        model.addObject("schedules",isFile?scheduleService.getFramingScheduleByIdCase(caseId):gson.toJson(scheduleService.getFramingScheduleByIdCase(caseId)));
        model.addObject("lstRisk", isFile?framingMeetingRepository.getSelectedTRiskByIdCase(caseId):gson.toJson(framingMeetingRepository.getSelectedTRiskByIdCase(caseId)));
        model.addObject("lstThreat", isFile?framingMeetingRepository.getSelectedThreatByIdCase(caseId):gson.toJson(framingMeetingRepository.getSelectedThreatByIdCase(caseId)));
        Long idFilePhoto = upDwFileService.getIdFileByCodeType(Constants.CODE_FILE_IMPUTED_PHOTO,id);
        model.addObject("fileIdPhoto",idFilePhoto);
        if(idFilePhoto!=null){
            UploadFile file= upDwFileService.getPathAndFilename(idFilePhoto);
            String path = new File(file.getPath(), file.getRealFileName()).toString();
            model.addObject("pathPhoto",path);
        }
    }

    @Override
    public void fillLogCase(Long id, ModelAndView model) {
        List<LogCase> logs = logCaseRepository.findAllByCase(id);
        Gson gson = new Gson();
        model.addObject("listLog",gson.toJson(logs));
    }


    @Override
    public void fillModelLogCaseFile(Long id, ModelAndView model) {
       fillInfoCase(id, model,true);
       model.addObject("activities",logCaseRepository.findAllByCase(id));
    }

    @Override
    public void fillModelAccomplishmentFile(Long caseId, ModelAndView model) {
        fillInfoCase(caseId, model, true);
        Gson gson = new Gson();
        List<SelectList> lstSources = framingReferenceRepository.findAllValidByCaseId(caseId);
        model.addObject("lstSources", lstSources);
        fillDtoOfActivities(lstSources,model, caseId);
    }

    private void fillDtoOfActivities(List<SelectList> lstSources, ModelAndView model, Long caseId) {
        Long idMon = monitoringPlanRepository.getMonPlanIdByCaseId(caseId);
        List<SelectList> lstActivities = supervisionActivityRepository.findByMonPlanId(idMon);
        List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement = activityMonitoringPlanRepository.getListAccomplishmentActMonPlanArrangementByMonPlanId(idMon);
        List<ActivityMonitoringPlanLog> lstActMonPlan = activityMonitoringPlanRepository.getListAccomplishmentByMonPlanIdToFile(idMon);
        List<SelectList> assigmentArringment = arrangementRepository.findLstArrangementByCaseId(caseId);
        List<ActMonPlanDto> lstActivityOk = new ArrayList<>(), lstActivityFailed = new ArrayList<>();
        List<String> lstArrangement=new ArrayList<>();

        for(ActivityMonitoringPlanLog ampl: lstActMonPlan){
           String sourceName = findElement(lstSources, ampl.getAidSourceId(), true);
           String supActivity = findElement(lstActivities, ampl.getActSupervisionId(),false);
            lstArrangement = new ArrayList<>();
            fillInfoActivity(ampl.getId(),lstArrangement, lstActMonPlanArrangement,assigmentArringment,ampl.getLaaStatus());
            if(ampl.getLaaStatus() == MonitoringConstants.ACTIVITY_ARRANGEMENT_DONE){
                if(lstArrangement.size()>0){
                    lstActivityOk.add(new ActMonPlanDto(ampl.getStart(), ampl.getEnd(),supActivity,sourceName,lstArrangement,ampl.getStatus(), ampl.getComments()));
                }
            }else if(ampl.getLaaStatus() == MonitoringConstants.ACTIVITY_ARRANGEMENT_FAILED){
                if(lstArrangement.size()>0){
                    lstActivityFailed.add(new ActMonPlanDto(ampl.getStart(), ampl.getEnd(), supActivity, sourceName, lstArrangement, ampl.getStatus(), ampl.getComments()));
                }
            }
        }
        model.addObject("lstActivityFailed",lstActivityFailed);
        model.addObject("lstActivityOk",lstActivityOk);
    }

    private void fillInfoActivity(Long idActivity, List<String> lstArrangement, List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement, List<SelectList> assigmentArringment, int status){
            for (ActivityMonitoringPlanArrangementLog ampal:lstActMonPlanArrangement){
                if(ampal.getActMonPlanId().equals(idActivity)){
                    ampal.setStatusString();
                    String assArr=findElement(assigmentArringment,ampal.getAssignedArrangementId(),true);
                    if(ampal.getStatus() == (status)){
                        lstArrangement.add(assArr +" - "+ ampal.getStatusSt());
                    }
                }
            }
    }

    private String findElement(List<SelectList> lst, Long id, Boolean addDescription){
        for(SelectList sl: lst){
            if(sl.getId().equals(id)){
                if(addDescription)
                    return sl.getName()+" / "+sl.getDescription();
                return sl.getName();
            }
        }
        return "NA";
    }

    @Autowired
    CrimeService crimeService;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;

    @Override
    public String generateResumeOfHearingFormat(Long idHearingFormat){
        HearingFormatDto hfdto =hearingFormatRepository.getInfoToLogCase(idHearingFormat);
        List<String> listCrime = crimeService.getListStringCrimeHFByHF(idHearingFormat);
        String crimes = crimeService.convertListCaseToCaseLog(listCrime);
        List<SelectList> aa = arrangementRepository.findLstArrangementByHearingFormatId(idHearingFormat);
        User umecaSupervisor = hearingFormatRepository.getAssignedSupervisor(idHearingFormat);
        return hfdto.toString(crimes,aa,umecaSupervisor);
    }


    public List<LogCase> addLog(String activityCode, Long idCase, Object detail){
        List<LogCase> newLogs = new ArrayList<>();
        try{
        Long rCount = logCaseRepository.countLogByIdCase(idCase);
        Long idUser = sharedUserService.GetLoggedUserId();
        User user = userRepository.findOne(idUser);
        Case cd = new Case();
        String resume="";
        cd.setId(idCase);

        if(rCount.equals(0L)){
            LogCase firstLog = new LogCase();
            firstLog.setDate(Calendar.getInstance());
            firstLog.setActivity(ConstantsLogCase.CREATE_LOG_CASE);
            firstLog.setTitle(ConstantsLogCase.CREATE_LOG_CASE);
            firstLog.setUser(user);
            firstLog.setCaseDetention(cd);
            newLogs.add(firstLog);
        }
        LogCase logCase = new LogCase();
        switch (activityCode){
            case ConstantsLogCase.NEW_HEARING_FORMAT:
                Long idObject = (Long) detail;
                resume = generateResumeOfHearingFormat(idObject);
                logCase.setResume(resume);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_ADD_HEARING_FORMAT);
                logCase.setTitle(ConstantsLogCase.TT_ADD_HEARING_FORMAT);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.OPEN_CASE_NOT_PROSECUTE:
                logCase.setResume(ConstantsLogCase.RESUME_OPEN_NOT_PROSECUTE);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_OPEN_CASE_NOT_PROSECUTE);
                logCase.setTitle(ConstantsLogCase.TT_OPEN_NOT_PROSECUTE);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.SPONTANEOUS_ACTIVITY:
                LogCase plogCase =(LogCase)detail;
                logCase.setResume(plogCase.getResume());
                logCase.setTitle(plogCase.getTitle());
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_SPONTANEOUS_ACTIVITY);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.CLOSE_CASE:
                //agregar razón de cerre de caso
                logCase.setResume((String) detail);
                logCase.setTitle(ConstantsLogCase.TT_CLOSE_CASE);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_CLOSE_CASE);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.LOG_SUPERVISION_ACTIVITY:
                ActivityMonitoringPlanInfo amp = activityMonitoringPlanRepository.getActivityInfoFull((Long) detail);
                List<ActivityMonitoringPlanArrangementLog> amplList = activityMonitoringPlanRepository.getListActMonPlanArrangementByActivityIdToShow((Long)detail);
                String aux = new ActivityMonitoringPlanArrangementLog().stringToLogCase(amplList);
                resume = amp.stringToLogCase(aux);
                logCase.setResume(resume);
                logCase.setTitle(ConstantsLogCase.TT_LOG_SUPERVISION_ACTIVITY);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_LOG_SUPERVISION_ACTIVITY);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.CREATE_MONITORING_PLAN:
                if(detail!=null)
                    logCase.setResume((String) detail);
                logCase.setTitle(ConstantsLogCase.TT_CREATE_MONITORING_PLAN);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_CREATE_MONITORING_PLAN);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.EDIT_MONITORING_PLAN:
                if(detail!=null)
                    logCase.setResume((String) detail);
                logCase.setTitle(ConstantsLogCase.TT_EDIT_MONITORING_PLAN);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_EDIT_MONITORING_PLAN);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.CREATE_FRAMING_MEETING:
                if(detail!=null)
                    logCase.setResume((String) detail);
                logCase.setTitle(ConstantsLogCase.T_CREATE_FRAMING_MEETING);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_FRAMING_MEETING);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.EDIT_FRAMING_MEETING:
                if(detail!=null)
                    logCase.setResume((String) detail);
                logCase.setTitle(ConstantsLogCase.TT_EDIT_FRAMING_MEETING);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_EDIT_FRAMING_MEETING);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.TERMINATE_FRAMING_MEETING:
                if(detail!=null)
                    logCase.setResume((String) detail);
                logCase.setTitle(ConstantsLogCase.TT_FRAMING_MEETING);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_FRAMING_MEETING);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
            case ConstantsLogCase.CODE_ASSIGN_FRAMING_MEETING:
                if(detail!=null)
                    logCase.setResume((String) detail);
                logCase.setTitle(ConstantsLogCase.T_ASSIGN_FRAMING_MEETING);
                logCase.setDate(Calendar.getInstance());
                logCase.setActivity(ConstantsLogCase.ACT_ASSIGN_FRAMING_MEETING);
                logCase.setUser(user);
                logCase.setCaseDetention(cd);
                newLogs.add(logCase);
                break;
        }
            logCaseRepository.save(newLogs);
        }catch (Exception e){
            logException.Write(e, this.getClass(), "addLog", sharedUserService);
            e.printStackTrace();
        }finally {
            return newLogs;
        }
    }

    @Override
    public String getLogCase(Long caseId) {
        List<LogCase> logs = logCaseRepository.findAllByCase(caseId);
        Gson gson = new Gson();
        return gson.toJson(logs);
    }


}
