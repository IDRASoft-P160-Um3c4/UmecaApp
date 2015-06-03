package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.CloseCause;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.entities.shared.LogCommentJson;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SharedSystemSetting;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.ResponseTypeRepository;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.repository.supervisorManager.LogChangeSupervisorRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.shared.CaseRequestService;
import com.umeca.service.shared.LogCaseService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 7/3/14
 * Time: 9:47 AM
 */
@Service
public class TrackMonPlanServiceImpl implements TrackMonPlanService {

    @Autowired
    private ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    private SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    private ActivityGoalRepository activityGoalRepository;

    @Override
    public void getLstActivitiesByUser(RequestActivities req, Long userId, ArrayList<String> lstMonPlanStatus, ArrayList<String> lstActStatus, ResponseActivities response) {
        List<ActivityMonitoringPlanResponse> lstAllActivities =
                activityMonitoringPlanRepository.getAllActivities(userId, lstMonPlanStatus, lstActStatus,
                        (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
        response.setLstMonPlanActivities(lstAllActivities);

        List<SelectList> lstActivities = supervisionActivityRepository.findAllSl();
        List<SelectList> lstGoals = activityGoalRepository.findAllSlMonitoring();
        response.setLstActivities(lstActivities);
        response.setLstGoals(lstGoals);
        response.setHasError(false);
    }

    @Override
    public void getLstActivitiesByUserAndFilters(RequestActivities req, Long userId, ArrayList<String> lstMonPlanStatus, ArrayList<String> lstActStatus, ResponseActivities response) {
        Calendar referenceDate = Calendar.getInstance();
        Long milDate = referenceDate.getTimeInMillis();
        Long millisecondsToAuthorize = SharedSystemSetting.MonPlanHoursToAuthorize * SharedSystemSetting.MILISECONDS_PER_HOUR;
        milDate = milDate - millisecondsToAuthorize;
        referenceDate.setTimeInMillis(milDate);
        Integer findActive = 0, findBlock = 0;
        Long findCase = req.getCaseFilterId() < 0 ? 0L : req.getCaseFilterId();
        if (req.getCaseFilterId().equals(MonitoringConstants.FILTER_ACTIVE_CASE)) {
            findActive = 1;
        } else if (req.getCaseFilterId().equals(MonitoringConstants.FILTER_SUSPENDED_CASE)) {
            findBlock = 1;
        }
        if (sharedUserService.isUserInRole(userId, Constants.ROLE_SUPERVISOR)) {
            req.setUserFilterId(userId);
        }
        List<ActivityMonitoringPlanResponse> lstAllActivities =
                activityMonitoringPlanRepository.getAllActivitiesWithFilters(lstMonPlanStatus, lstActStatus,
                        (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd(), req.getActivityId(),
                        req.getUserFilterId(), findCase, findBlock, referenceDate, findActive);
        response.setLstMonPlanActivities(lstAllActivities);

        List<MonitoringPlanDto> lstMonPlanSus = activityMonitoringPlanRepository.getAllMonPlanWithFilters(userId, lstMonPlanStatus, lstActStatus,
                (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());

        MonitoringPlanDto monitoringPlanDto = null;
        for (ActivityMonitoringPlanResponse act : lstAllActivities) {
            if (monitoringPlanDto == null || monitoringPlanDto.getMonPlanId() != act.getMonitoringPlanId()) {
                monitoringPlanDto = null;
                for (MonitoringPlanDto monPlan : lstMonPlanSus) {
                    if (act.getMonitoringPlanId() != monPlan.getMonPlanId())
                        continue;
                    monitoringPlanDto = monPlan;
                    break;
                }
            }
            //Si está suspendido, se debe marcar
            if (monitoringPlanDto != null && monitoringPlanDto.getMonPlanSuspended()) {
                act.setSuspended(true);
            }
        }


        List<SelectList> lstActivities = supervisionActivityRepository.findAllSl();
        List<SelectList> lstGoals = activityGoalRepository.findAllSlMonitoring();
        response.setLstActivities(lstActivities);
        response.setLstGoals(lstGoals);
        response.setHasError(false);
    }


    @Autowired
    private ArrangementRepository arrangementRepository;

    @Override
    public void getActivityToShow(Long id, ModelAndView model) {
        ActivityMonitoringPlanInfo actMonPlanInfo = activityMonitoringPlanRepository.getActivityInfo(id);
        Long genericId;

        model.addObject("actMonPlanId", id);
        model.addObject("caseId", actMonPlanInfo.getCaseId());
        model.addObject("mpId", StringEscape.escapeText(actMonPlanInfo.getMpId()));
        model.addObject("fullName", StringEscape.escapeText(actMonPlanInfo.getPersonName()));
        model.addObject("status", actMonPlanInfo.getStatus());

        List<SelectList> lstArrangement = arrangementRepository.findArrangementById(id);
        /*
        String sLstArrangement = null;

        for(SelectList sl : lstArrangement){
            if(sLstArrangement == null){
                sLstArrangement = sl.getName() + " / " + sl.getDescription();
            }
            else{
                sLstArrangement = sLstArrangement + " <br/> " + sl.getName() + " / " + sl.getDescription();
            }
        }*/

        String status = actMonPlanInfo.getActStatus();
        boolean isReadOnly = true;

        if (status.equals(MonitoringConstants.STATUS_ACTIVITY_MODIFIED) || status.equals(MonitoringConstants.STATUS_ACTIVITY_NEW)) {
            isReadOnly = false;
        }

        if (actMonPlanInfo.getSuspended())
            isReadOnly = true;

        Gson gson = new Gson();
        String sLstArrangement = gson.toJson(lstArrangement);
        model.addObject("lstArrangements", sLstArrangement);
        model.addObject("actSup", actMonPlanInfo.getSupervisionActivityName());

        model.addObject("actGoal", actMonPlanInfo.getActivityGoalName());
        genericId = actMonPlanInfo.getActivityGoalId();
        model.addObject("actGoalId", genericId);
        model.addObject("hasActGoalChannelingTrack", genericId == Constants.CHANNELING_NOTIFICATION_GOAL_TRACK);

        model.addObject("actSources", StringEscape.escapeText(actMonPlanInfo.getAidSourceName()));
        model.addObject("actStatus", actMonPlanInfo.getActStatus());
        String[] sDate = actMonPlanInfo.getStartDateTime().split("\\|");
        model.addObject("actInitDate", sDate[0]);
        model.addObject("actInitTime", sDate[1]);
        sDate = actMonPlanInfo.getEndDateTime().split("\\|");
        model.addObject("actEndDate", sDate[0]);
        model.addObject("actEndTime", sDate[1]);
        model.addObject("isReadOnly", isReadOnly);
        model.addObject("actSupervisorDone", StringExt.naOnEmpty(actMonPlanInfo.getSupUserDone()));
        model.addObject("actComments", StringEscape.escapeText(StringExt.naOnEmpty(actMonPlanInfo.getComments())));
        model.addObject("actEndFullDate", CalendarExt.calendarToFormatString(actMonPlanInfo.getEndDone(), Constants.FORMAT_CALENDAR_I));

        genericId = actMonPlanInfo.getChannelingId();
        model.addObject("hasChanneling", genericId != null && genericId > 0);
        model.addObject("channelingId", genericId);
        model.addObject("channelingName", actMonPlanInfo.getChannelingName());
        model.addObject("channelingType", actMonPlanInfo.getChannelingType());

        model.addObject("channelingAssistance",
                actMonPlanInfo.getChannelingAssistance() != null ? actMonPlanInfo.getChannelingAssistance() : 1); // Por defecto se usa que si asistió a la actividad en el seguimiento de la misma

    }

    public void getActivityToShow(Long id, ResponseMessage response) {
        final ActivityMonitoringPlanInfo actMonPlanInfo = activityMonitoringPlanRepository.getActivityInfoFull(id);

        Long actMonPlanToReplaceId = actMonPlanInfo.getActMonPlanToReplaceId();
        if (actMonPlanToReplaceId != null) {
            final ActivityMonitoringPlanInfo actMonPlanInfoRep = activityMonitoringPlanRepository.getActivityInfoFull(actMonPlanToReplaceId);
            response.setReturnData(new PreActivityMonitoringPlan() {{
                setNewActMonPlanInfo(actMonPlanInfo);
                setOldActMonPlanInfo(actMonPlanInfoRep);
            }});
        } else {
            response.setReturnData(new PreActivityMonitoringPlan() {{
                setNewActMonPlanInfo(actMonPlanInfo);
            }});
        }
    }

    @Autowired
    LogCommentRepository logCommentRepository;
    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    @Autowired
    CaseService caseService;

    @Autowired
    SupervisionCloseCaseLogRepository supervisionCloseCaseLogRepository;

    @Override
    @Transactional
    public void saveAuthRejectMonPlan(SharedUserService sharedUserService, SharedLogExceptionService logException, AuthorizeRejectMonPlan model, User user, MonitoringPlan monPlan, String statusAuth, String statusReject, String type) {
        LogComment commentModel = new LogComment();
        Calendar now = Calendar.getInstance();
        String statusAction = (model.getAuthorized() == 1 ? statusAuth : statusReject);
        commentModel.setComments(StringEscape.escapeText(model.getComments()));
        commentModel.setAction(statusAction);
        commentModel.setMonitoringPlan(monPlan);
        commentModel.setCaseDetention(monPlan.getCaseDetention());
        commentModel.setSenderUser(user);
        commentModel.setTimestamp(now);
        commentModel.setType(type);

        MonitoringPlanJson jsonOld = MonitoringPlanJson.convertToJson(monPlan);
        monPlan.setStatus(statusAction);
        monPlan.setAuthorizer(user);
        monPlan.setAuthorizationTime(now);
        MonitoringPlanJson jsonNew = MonitoringPlanJson.convertToJson(monPlan);

        if (type.equals(MonitoringConstants.TYPE_COMMENT_MONITORING_PLAN_END) && model.getAuthorized() == 1) {
            Case caseDetention = monPlan.getCaseDetention();
            caseDetention.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_CLOSED));
            caseDetention.setCloseDate(new Date());
            User u = new User();
            u.setId(sharedUserService.GetLoggedUserId());
            caseDetention.setCloserUser(u);
            caseDetention.setCloseCause(closeCauseRepository.findByCode(Constants.CLOSE_CAUSE_DISMISSAL));
            caseRepository.save(caseDetention);
        }

        //CaseRequest... Response
        if (type.equals(MonitoringConstants.TYPE_COMMENT_AUTHORIZED)) {
            CaseRequestService.CreateCaseResponseToUser(responseTypeRepository, caseRequestRepository, messageRepository,
                    sharedUserService, logException, user, monPlan.getCaseDetention(),
                    "El plan de monitoreo fue " + (model.getAuthorized() == 1 ? "autorizado" : "rechazado") + ". Comentarios: " + StringEscape.escapeText(model.getComments()),
                    Constants.ST_REQUEST_MONPLAN_AUTH);
        }


        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), monPlan.getId()));
        logCommentRepository.save(commentModel);
        supervisionCloseCaseLogRepository.save(caseService.generateCloseLog(monPlan.getCaseDetention()));
        monitoringPlanRepository.save(monPlan);
    }

    @Autowired
    ResponseTypeRepository responseTypeRepository;
    @Autowired
    CaseRequestRepository caseRequestRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    LogChangeSupervisorRepository logChangeSupervisorRepository;

    @Override
    @Transactional
    public void saveChangeSupervisorMonPlan(ChangeSupervisor model, User user, MonitoringPlan monPlan) {
        LogChangeSupervisor commentModel = new LogChangeSupervisor();
        Calendar now = Calendar.getInstance();
        commentModel.setComments(model.getComments());
        commentModel.setMonitoringPlan(monPlan);
        commentModel.setSupervisorOld(monPlan.getSupervisor());
        commentModel.setSupervisorManager(user);
        User userSupNew = new User();
        userSupNew.setId(model.getSupervisorId());
        commentModel.setSupervisorNew(userSupNew);
        commentModel.setTimestamp(now);

        MonitoringPlanJson jsonOld = MonitoringPlanJson.convertToJson(monPlan);
        monPlan.setSupervisor(userSupNew);
        MonitoringPlanJson jsonNew = MonitoringPlanJson.convertToJson(monPlan);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), monPlan.getId()));
        logChangeSupervisorRepository.save(commentModel);
        monitoringPlanRepository.save(monPlan);
    }

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    FulfillmentReportRepository fulfillmentReportRepository;

    @Override
    @Transactional
    public void saveAuthRejectAccomplishment(AuthorizeRejectMonPlan model, User user, MonitoringPlan monPlan, String type, FulfillmentReport fulfillmentReport) {
        LogComment commentModel = new LogComment();
        Calendar now = Calendar.getInstance();
        commentModel.setComments(model.getComments());

        String sAction = (model.getAuthorized() == 1 ? MonitoringConstants.LOG_ACCOMPLISHMENT_AUTHORIZED : MonitoringConstants.LOG_ACCOMPLISHMENT_REJECTED);
        commentModel.setAction(sAction);
        commentModel.setMonitoringPlan(monPlan);
        commentModel.setCaseDetention(monPlan.getCaseDetention());
        commentModel.setSenderUser(user);
        commentModel.setTimestamp(now);
        commentModel.setType(MonitoringConstants.TYPE_COMMENT_LOG_ACCOMPLISHMENT);

        LogCommentJson logJson = new LogCommentJson();
        logJson.setAction(sAction);
        logJson.setTimestampCalendar(now);
        logJson.setType(MonitoringConstants.TYPE_COMMENT_LOG_ACCOMPLISHMENT);

        Gson json = new Gson();
        MonitoringPlanJson jsonOld = MonitoringPlanJson.convertToJson(monPlan);
        monPlan.setStatusLog(json.toJson(logJson));
        MonitoringPlanJson jsonNew = MonitoringPlanJson.convertToJson(monPlan);
        fulfillmentReport.setStatus(sAction);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), monPlan.getId()));
        monitoringPlanRepository.save(monPlan);
        logCommentRepository.save(commentModel);
        fulfillmentReportRepository.save(fulfillmentReport);
    }

    @Override
    public void setLstActivitiesSupervision(ModelAndView model) {
        Gson gson = new Gson();
        List<SelectList> lstGeneric = supervisionActivityRepository.findAllValidSl();
        lstGeneric.add(0, new SelectList(0l, "--Todas las actividades--"));
        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstActivities", sLstGeneric);
    }

    @Autowired
    SharedUserService sharedUserService;

    @Override
    public void setListCaseFilter(ModelAndView model, Long idUser) {
        Gson gson = new Gson();
        List<SelectList> lstGeneric = monitoringPlanRepository.findAllCaseByIdSupervisor(idUser, MonitoringConstants.LST_STATUS_AUTHORIZE_READY);

        lstGeneric.add(0, new SelectList(0L, "--Todos los casos--", ""));
        lstGeneric.add(1, new SelectList(-1L, "Casos activos", ""));
        lstGeneric.add(2, new SelectList(-2L, "Casos suspendidos", ""));
        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstCases", sLstGeneric);
    }

    @Override
    public void setListUserFilter(ModelAndView model, Long idUser) {
        List<SelectList> lstGeneric = new ArrayList<>();
        Gson gson = new Gson();
        if (!idUser.equals(0L)) {
            lstGeneric.add(new SelectList(idUser, "UserLog"));
        } else {
            lstGeneric = sharedUserService.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
            lstGeneric.add(0, new SelectList(0L, "--Todos los supervisores--", ""));
        }
        model.addObject("lstUser", gson.toJson(lstGeneric));
    }

    @Autowired
    HearingFormatRepository hearingFormatRepository;
    @Autowired
    LogCaseService logCaseService;

    @Transactional
    @Override
    public void notificationNewHearingFormat(Long monId, ModelAndView model) {
        try {
            List<Long> listId = hearingFormatRepository.getLastHearingFormatByMonPlan(monId, new PageRequest(0, 1));
            Long hearingFormatId = listId.get(0);
            Boolean showNotification = hearingFormatRepository.getShowNotificationByIdFormat(hearingFormatId);
            if (showNotification) {
                HearingFormat hf = hearingFormatRepository.findOne(listId.get(0));
                String resume = "<strong>Registrado por:</strong> " + hf.getSupervisor().getFullname() + "<br/>";
                resume += logCaseService.generateResumeOfHearingFormat(hearingFormatId);
                model.addObject("infoHearingFormat", resume);
                hf.setShowNotification(false);
                hearingFormatRepository.save(hf);
            }
        } catch (Exception e) {
            model.addObject("infoHearingFormat", "No fue posible obtener informaci&oacute;n del &uacute;ltimo formato de audiencia registrado.");
        }


    }

    @Autowired
    private CloseCauseRepository closeCauseRepository;

    @Override
    @Transactional
    public void saveReqEndMonPlan(AuthorizeRejectMonPlan model, User user, MonitoringPlan monPlan) {
        LogComment commentModel = new LogComment();
        Calendar now = Calendar.getInstance();

        String msg = "";

        if (model.getIdCloseCause() != null) {
            CloseCause closeCause = closeCauseRepository.findOne(model.getIdCloseCause());
            msg += "Motivo: " + closeCause.getName() + " - ";
        }

        commentModel.setComments(msg + StringEscape.escapeText(model.getComments()));
        commentModel.setAction(MonitoringConstants.STATUS_PENDING_END);
        commentModel.setMonitoringPlan(monPlan);
        commentModel.setCaseDetention(monPlan.getCaseDetention());
        commentModel.setSenderUser(user);
        commentModel.setTimestamp(now);
        commentModel.setType(MonitoringConstants.TYPE_COMMENT_MONITORING_PLAN_END);

        MonitoringPlanJson jsonOld = MonitoringPlanJson.convertToJson(monPlan);
        monPlan.setStatus(MonitoringConstants.STATUS_PENDING_END);
        MonitoringPlanJson jsonNew = MonitoringPlanJson.convertToJson(monPlan);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), monPlan.getId()));
        logCommentRepository.save(commentModel);
        monitoringPlanRepository.save(monPlan);
    }
}
