package com.umeca.service.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SharedSystemSetting;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.shared.SharedLogCommentService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.umeca.model.shared.MonitoringConstants.*;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/13/14
 * Time: 5:47 PM
 */
@Service
public class MonitoringPlanServiceImpl implements MonitoringPlanService {

    Calendar today = CalendarExt.getToday();

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    ActivityMonitoringPlanRepository actMpRepository;

    @Autowired
    ArrangementRepository arRepository;

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Autowired
    LogCommentRepository logCommentRepository;

    @Override
    public boolean doUpsertDelete(MonitoringPlanRepository monitoringPlanRepository, ActivityMonitoringPlanRequest fullModel, User user, ResponseMessage response) {

        MonitoringPlan monitoringPlan = monitoringPlanRepository.getStatus(user.getId(), fullModel.getCaseId(), fullModel.getMonitoringPlanId());

        fullModel.setNow(Calendar.getInstance());

        if (ValidatePlanMonitoring(monitoringPlan, monitoringPlanRepository, fullModel, response) == false)
            return false;


        //First set status to delete for all activities
        List<Long> lstActivitiesDel = fullModel.getLstActivitiesDel();
        for (Long id : lstActivitiesDel) {
            delete(id, fullModel, user.getUsername());
        }
        actMpRepository.flush();

        List<Long> lastHearingFormatId = hearingFormatRepository.getLastHearingFormatByMonPlan(fullModel.getMonitoringPlanId(), new PageRequest(0, 1));
        List<SelectList> lstArrangementSelected = arRepository.findLstArrangementByHearingFormatId(lastHearingFormatId.get(0));

        Long idCase = fullModel.getCaseId();
        List<ActivityMonitoringPlanDto> lstActivitiesUpsert = fullModel.getLstActivitiesUpsert();
        String groupUid = UUID.randomUUID().toString();
        for (ActivityMonitoringPlanDto dto : lstActivitiesUpsert) {

            if (validateDates(dto.getStartCalendar(), dto.getEndCalendar()) == false)
                continue;

            if (idCase != dto.getCaseId())
                continue;
            try {
                if (dto.getActivityId() > 0) {
                    //Si está en modo autorizado, se agrega uno nuevo para, en caso de no ser aceptado se toma la actividad anterior
                    if (fullModel.isInAuthorizeReady())
                        create(dto, actMpRepository, user, fullModel, lstArrangementSelected, groupUid, true);
                    else
                        update(dto, actMpRepository, user, fullModel, lstArrangementSelected, null);
                } else {
                    create(dto, actMpRepository, user, fullModel, lstArrangementSelected, groupUid, false);
                }
            } catch (Exception ex) {
                logException.Write(ex, this.getClass(), "doUpsertDelete", user.getUsername());
            }
        }

        if (monitoringPlan.getPosAuthorizationChangeTime() == null && fullModel.hasActivitiesInPreAuthorizeMode()) {
            //Se añade una notificación para informar al coordinador que tiene que revisar el cambio del plan de seguimiento
            SharedLogCommentService.generateLogComment(LOG_MSG_INFO_PENDING_ACTIVITY_AUTHORIZATION, user, monitoringPlan.getCaseDetention(),
                    STATUS_PENDING_AUTHORIZATION, null, TYPE_COMMENT_AUTHORIZED, logCommentRepository);
            monitoringPlan.setPosAuthorizationChangeTime(fullModel.getNow());
        }

        actMpRepository.flush();
        return true;
    }

    private boolean validateDates(Calendar startCalendar, Calendar endCalendar) {
        if (startCalendar.before(today) || endCalendar.before(today))
            return false;
        return true;
    }

    private void delete(Long id, ActivityMonitoringPlanRequest fullModel, String username) {
        ActivityMonitoringPlan activityMonitoringPlan = actMpRepository.findOneValid(id, fullModel.getMonitoringPlanId(), fullModel.getCaseId());
        if (activityMonitoringPlan == null)
            return;

        String status = activityMonitoringPlan.getStatus();
        if (LST_STATUS_ACTIVITY_END.contains(status))
            return;

        if (validateDates(activityMonitoringPlan.getStart(), activityMonitoringPlan.getEnd()) == false)
            return;

        ActivityMonitoringPlan actMonPlanToReplace = activityMonitoringPlan.getActMonPlanToReplace();

        if (actMonPlanToReplace != null) {
            ActivityMonitoringPlan activityMonitoringPlanToReplace = activityMonitoringPlan;
            activityMonitoringPlan = actMpRepository.findOneValid(actMonPlanToReplace.getId(), fullModel.getMonitoringPlanId(), fullModel.getCaseId());
            activityMonitoringPlanToReplace.setStatus(STATUS_ACTIVITY_DELETED);
            activityMonitoringPlan.setReplaced(null);
        }


        ActivityMonitoringPlanJson jsonOld = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);
        activityMonitoringPlan.setStatus((fullModel.isInAuthorizeReady() && STATUS_ACTIVITY_PRE_NEW.equals(status) == false)
                ? STATUS_ACTIVITY_PRE_DELETED : STATUS_ACTIVITY_DELETED);
        ActivityMonitoringPlanJson jsonNew = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, username, fullModel.getCaseId(), fullModel.getMonitoringPlanStatus()));
        actMpRepository.save(activityMonitoringPlan);

        if (fullModel.isInAuthorizeReady())
            fullModel.incActsPreDel();
        else
            fullModel.incActsDel();

    }

    private void create(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                        List<SelectList> lstArrangementSelected, String groupUid, boolean bIsAuthUpdate) {
        ActivityMonitoringPlan activityMonitoringPlan = new ActivityMonitoringPlan();

        if (bIsAuthUpdate) {
            activityMonitoringPlan.setStatus(STATUS_ACTIVITY_PRE_MODIFIED);
            ActivityMonitoringPlan activityMonitoringPlanToUpdate = getValidActivityMonitoringPlanToUpdate(dto, actMpRepository);
            if (activityMonitoringPlanToUpdate == null) return;

            //Si no tiene una actividad a quien reemplazar, se debe crear una nueva, de lo contrario sólo se actualiza la anterior
            ActivityMonitoringPlan actMonReplace = activityMonitoringPlanToUpdate.getActMonPlanToReplace();
            if ((actMonReplace != null && actMonReplace.getId() != null) || STATUS_ACTIVITY_PRE_NEW.equals(activityMonitoringPlanToUpdate.getStatus())) {
                update(dto, actMpRepository, user, fullModel, lstArrangementSelected, activityMonitoringPlanToUpdate);

                fullModel.decActsUpd();
                fullModel.incActsPreUpd();
                return;
            } else {
                activityMonitoringPlan.setActMonPlanToReplace(activityMonitoringPlanToUpdate);
                activityMonitoringPlan.setGroup(activityMonitoringPlanToUpdate.getGroup());
                activityMonitoringPlanToUpdate.setReplaced(true);
            }
        } else {
            activityMonitoringPlan.setStatus(fullModel.isInAuthorizeReady() ? STATUS_ACTIVITY_PRE_NEW : STATUS_ACTIVITY_NEW);
            activityMonitoringPlan.setGroup(groupUid);
        }

        activityMonitoringPlan.setSupervisorCreate(user);
        activityMonitoringPlan.setCreationTime(fullModel.getNow());

        DtoToModelAndSave(dto, actMpRepository, user, fullModel, activityMonitoringPlan, lstArrangementSelected, true);

        if (fullModel.isInAuthorizeReady()) {
            if (bIsAuthUpdate) fullModel.incActsPreUpd();
            else fullModel.incActsPreIns();

        } else {
            fullModel.incActsIns();
        }
    }

    private void update(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                        List<SelectList> lstArrangementSelected, ActivityMonitoringPlan activityMonitoringPlanToUpdate) {

        ActivityMonitoringPlan activityMonitoringPlan = activityMonitoringPlanToUpdate == null ?
                getValidActivityMonitoringPlanToUpdate(dto, actMpRepository) : activityMonitoringPlanToUpdate;

        if (activityMonitoringPlan == null) return;

        activityMonitoringPlan.setStatus(fullModel.isInAuthorizeReady() ?
                (activityMonitoringPlan.getStatus().equals(MonitoringConstants.STATUS_ACTIVITY_PRE_NEW) ? STATUS_ACTIVITY_PRE_NEW : STATUS_ACTIVITY_PRE_MODIFIED)
                : STATUS_ACTIVITY_MODIFIED);

        activityMonitoringPlan.setSupervisorModify(user);
        activityMonitoringPlan.setModifyTime(fullModel.getNow());

        DtoToModelAndSave(dto, actMpRepository, user, fullModel, activityMonitoringPlan, lstArrangementSelected, false);
        fullModel.incActsUpd();
    }

    private ActivityMonitoringPlan getValidActivityMonitoringPlanToUpdate(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository) {
        ActivityMonitoringPlan activityMonitoringPlan = actMpRepository.findOneValid(dto.getActivityId(), dto.getMonitoringPlanId(), dto.getCaseId());
        if (activityMonitoringPlan == null)
            return null;
        String status = activityMonitoringPlan.getStatus();
        if (status.equals(STATUS_ACTIVITY_DELETED) || status.equals(STATUS_ACTIVITY_DONE) || status.equals(STATUS_ACTIVITY_FAILED))
            return null;
        //Validate dates of saved activity before change something...
        if (validateDates(activityMonitoringPlan.getStart(), activityMonitoringPlan.getEnd()) == false)
            return null;
        return activityMonitoringPlan;
    }


    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    private void DtoToModelAndSave(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                                   ActivityMonitoringPlan activityMonitoringPlan, List<SelectList> lstArrangementSelected, boolean isNew) {

        ActivityMonitoringPlanJson jsonOld = null;

        if (isNew == false) {
            jsonOld = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);
        }

        ActivityGoal activityGoal = new ActivityGoal();
        activityGoal.setId(dto.getGoalId());
        activityMonitoringPlan.setActivityGoal(activityGoal);
        FramingSelectedSourceRel framingSelectedSourceRel = new FramingSelectedSourceRel();
        framingSelectedSourceRel.setId(dto.getSourceId());
        activityMonitoringPlan.setFramingSelectedSourceRel(framingSelectedSourceRel);

        String sAssignedArrangements = null;
        String sAssignedArrangementsIds = null;
        List<ActivityMonitoringPlanArrangement> lstAssignedArrangements = new ArrayList<>();

        for (Long idAssignedArr : dto.getLstArrangements()) {
            for (SelectList slAa : lstArrangementSelected) {

                if (slAa.getId().equals(idAssignedArr)) {
                    sAssignedArrangements = (sAssignedArrangements == null ? slAa.getName() : sAssignedArrangements + ", " + slAa.getName());
                    sAssignedArrangementsIds = (sAssignedArrangementsIds == null ? idAssignedArr.toString() : sAssignedArrangementsIds + ", " + idAssignedArr.toString());
                    AssignedArrangement aa = new AssignedArrangement();
                    aa.setId(idAssignedArr);
                    ActivityMonitoringPlanArrangement ampa = new ActivityMonitoringPlanArrangement();
                    ampa.setActivityMonitoringPlan(activityMonitoringPlan);
                    ampa.setAssignedArrangement(aa);
                    ampa.setStatus(ACTIVITY_ARRANGEMENT_UNDEFINED);
                    lstAssignedArrangements.add(ampa);
                    break;
                }
            }
        }

        activityMonitoringPlan.getLstAssignedArrangement().clear();
        activityMonitoringPlan.getLstAssignedArrangement().addAll(lstAssignedArrangements);
        activityMonitoringPlan.setAssignedArrangements(sAssignedArrangements);
        activityMonitoringPlan.setAssignedArrangementsIds(sAssignedArrangementsIds);
        activityMonitoringPlan.setActivitySpec(dto.getActivitySpec());
        activityMonitoringPlan.setGoalSpec(dto.getGoalSpec());
        activityMonitoringPlan.setSourceSpec(dto.getSourceSpec());

        SupervisionActivity supervisionActivity = new SupervisionActivity();
        supervisionActivity.setId(dto.getActivityMonId());
        activityMonitoringPlan.setSupervisionActivity(supervisionActivity);

        Calendar cal = dto.getEndCalendar();
        activityMonitoringPlan.setEnd(cal);
        activityMonitoringPlan.setSearchEnd((cal.get(Calendar.YEAR) * 100) + (cal.get(Calendar.MONTH) + 1));
        cal = dto.getStartCalendar();
        activityMonitoringPlan.setStart(cal);
        activityMonitoringPlan.setSearchStart((cal.get(Calendar.YEAR) * 100) + (cal.get(Calendar.MONTH) + 1));
        //Se añade en modo por autorizar, y se enciende la bandera si esta autorizado el plan de lo contrario hacerlo nulo
        activityMonitoringPlan.setPreAuthorizeMode(fullModel.isInAuthorizeReady() ? true : null);

        if (isNew == true) {
            Case caseDetention = new Case();
            caseDetention.setId(dto.getCaseId());
            activityMonitoringPlan.setCaseDetention(caseDetention);

            MonitoringPlan monitoringPlan = new MonitoringPlan();
            monitoringPlan.setId(dto.getMonitoringPlanId());
            activityMonitoringPlan.setMonitoringPlan(monitoringPlan);

            activityMonitoringPlan.setSupervisorCreate(user);

        } else {
            activityMonitoringPlan.setSupervisorModify(user);
            ActivityMonitoringPlanJson jsonNew = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);
            logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), fullModel.getCaseId(), fullModel.getMonitoringPlanStatus()));
        }

        actMpRepository.save(activityMonitoringPlan);
        fullModel.getLstActivitiesUpserted().add(new ActivityMonitoringPlanEvent(activityMonitoringPlan.getId(), dto.getEventId(), activityMonitoringPlan.getGroup()));
    }

    @Autowired
    SystemSettingService systemSettingService;

    private boolean ValidatePlanMonitoring(MonitoringPlan monitoringPlan, MonitoringPlanRepository monitoringPlanRepository, ActivityMonitoringPlanRequest fullModel, ResponseMessage response) {

        int typeSuspended = MonitoringPlanView.typeIsMonPlanSuspended(monitoringPlan.getGenerationTime(), monitoringPlan.getAuthorizationTime(), monitoringPlan.getPosAuthorizationChangeTime());
        if (typeSuspended != MonitoringConstants.AUTHORIZATION_OK) {
            response.setHasError(true);

            if (typeSuspended == MonitoringConstants.AUTHORIZATION_MONPLAN)
                response.setMessage("El plan de seguimiento está suspendido dado que ha excedido el tiempo de espera ("
                        + SharedSystemSetting.MonPlanHoursToAuthorize + " horas) para la autorización del plan de seguimiento. Por favor consulte a su coordinador");
            else if (typeSuspended != MonitoringConstants.AUTHORIZATION_ACTMONPLAN)
                response.setMessage("El plan de seguimiento está suspendido dado que ha excedido el tiempo de espera ("
                        + SharedSystemSetting.MonPlanHoursToAuthorize + " horas). Por favor consulte a su coordinador");

            return false;
        }

        String status = monitoringPlan.getStatus();
        fullModel.setInAuthorizeReady(LST_STATUS_AUTHORIZE_READY.contains(status));

        fullModel.setMonitoringPlanStatus(status);
        if (status != null && (status.equals(STATUS_NEW) || status.equals(STATUS_PENDING_CREATION) ||
                status.equals(STATUS_AUTHORIZED) || status.equals(STATUS_REJECTED_AUTHORIZED) ||
                status.equals(STATUS_MONITORING) || status.equals(STATUS_REJECTED_END))) {

            if (status.equals(STATUS_NEW) || status.equals(STATUS_REJECTED_AUTHORIZED)) {
                monitoringPlan.setStatus(STATUS_PENDING_CREATION);
                monitoringPlanRepository.save(monitoringPlan);
                monitoringPlanRepository.flush();
            } else if (status.equals(STATUS_REJECTED_END)) {
                monitoringPlan.setStatus(STATUS_MONITORING);
                monitoringPlanRepository.save(monitoringPlan);
                monitoringPlanRepository.flush();
            }

            return true;
        }

        response.setHasError(true);
        response.setMessage("El plan de supervisión ya no está en un estado donde pueda ser modificado o fue asignado a otro supervisor");
        return false;

        /*
        if(monitoringPlanRepository.isValidToUpsertDelete(user.getId(), fullModel.getCaseId(), fullModel.getMonitoringPlanId(),
                new ArrayList<String>(){{add(MonitoringConstants.STATUS_NEW);add(MonitoringConstants.STATUS_PENDING_CREATION);add(MonitoringConstants.STATUS_AUTHORIZED);
                    add(MonitoringConstants.STATUS_MONITORING);}}) == 0){
            response.setHasError(true);
            response.setMessage("El plan de supervisión ya no está en un estado donde pueda ser modificado o fue asignado a otro supervisor");
            return false;
        }
        */
    }


}
