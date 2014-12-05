package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.shared.LogCase;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangementLog;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanInfo;
import com.umeca.model.entities.supervisor.HearingFormatDto;
import com.umeca.model.entities.supervisor.SupervisionLogReport;
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
    public void fillgeneralDataLog(Long caseId, ModelAndView model) {
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
            model.addObject("imputedName", slr.getImputedName());
            model.addObject("mpId", id);
            model.addObject("crime", gson.toJson(listCrimeDtos));
            model.addObject("judge", slr.getJudge());
            model.addObject("defender", slr.getDefender());
            model.addObject("mp", slr.getMp());
            model.addObject("imputedTel", slr.getImputedTel());
            model.addObject("imputedAddr", slr.getImputedAddr());
        }else{
            CaseInfo caseInfo = caseRepository.getInfoById(caseId);
            model.addObject("imputedName", caseInfo.getPersonName());
        }
        model.addObject("caseId", caseId);
        List<SelectList> lstMoral = framingReferenceRepository.findAccompanimentReferences(id);

        String cad = "";
        for (SelectList act : lstMoral) {
            if (cad != "")
                cad += " / ";
            cad += act.getName() + " - " + act.getDescription();
        }

        model.addObject("moralName", cad);
        //model.addObject("moralPhone", slr.getMoralPhone());

        List<SelectList> lstResol = hearingFormatRepository.getInfoResolution(id);

        String lastResol = "", allResol = "";

        if (lstResol != null && lstResol.size() > 0) {
            for (int i = 0; i < lstResol.size(); i++) {

                if (allResol != "")
                    allResol += " , ";

                allResol += CalendarExt.calendarToFormatString(lstResol.get(i).getCalendar(), Constants.FORMAT_CALENDAR_I);

                if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_MC)
                    allResol += " - MC";
                else if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_SCP)
                    allResol += " - SCPP";
            }
        }

        String[] arr = allResol.split(",");
        if (arr.length > 0)
            lastResol = arr[arr.length - 1];
        else
            lastResol = allResol;

        model.addObject("prevResolution", allResol);
        model.addObject("lastResolution", lastResol);

        String closeComment = "";
        closeComment = monitoringPlanRepository.getCloseComment(id, MonitoringConstants.STATUS_PENDING_END, Constants.CASE_STATUS_CLOSED);

        model.addObject("closeComment", closeComment);

        List<SelectList> lstGeneric = arrangementRepository.findLstArrangementByCaseId(caseId);

        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstHfAssignedArrangement", sLstGeneric);

        lstGeneric = supervisionActivityRepository.findByMonPlanId(id);
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstActivities", sLstGeneric);

        lstGeneric = activityGoalRepository.findByMonPlanId(id);
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstGoals", sLstGeneric);

        model.addObject("schedules",gson.toJson(scheduleService.getFramingScheduleByIdCase(caseId)));

        model.addObject("lstRisk", gson.toJson(framingMeetingRepository.getSelectedTRiskByIdCase(caseId)));
        model.addObject("lstThreat", gson.toJson(framingMeetingRepository.getSelectedThreatByIdCase(caseId)));
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


    public void addLog(String activityCode, Long idCase, Object detail){
        try{
        Long rCount = logCaseRepository.countLogByIdCase(idCase);
        Long idUser = sharedUserService.GetLoggedUserId();
        User user = userRepository.findOne(idUser);
        Case cd = new Case();
        String resume="";
        cd.setId(idCase);
        List<LogCase> newLogs = new ArrayList<>();

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
                HearingFormatDto hfdto =hearingFormatRepository.getInfoToLogCase(idObject);
                List<String> listCrime = crimeService.getListStringCrimeHFByHF(idObject);
               resume = hfdto.toString();
                if(listCrime.size()>0){
                    resume += "<strong>Delitos: </strong><br/><ul>";
                    for(String crime : listCrime){
                        resume += "<li>"+crime+"</li>";
                    }
                    resume+="</ul>";
                }
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
                logCase.setTitle(ConstantsLogCase.TT_FRAMING_MEETING);
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
        }
            logCaseRepository.save(newLogs);
        }catch (Exception e){
            logException.Write(e, this.getClass(), "addLog", sharedUserService);
        }
    }

    @Override
    public String getLogCase(Long caseId) {
        List<LogCase> logs = logCaseRepository.findAllByCase(caseId);
        Gson gson = new Gson();
        return gson.toJson(logs);
    }

}
