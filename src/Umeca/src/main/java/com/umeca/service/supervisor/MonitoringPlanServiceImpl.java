package com.umeca.service.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/13/14
 * Time: 5:47 PM
 */
@Service
public class MonitoringPlanServiceImpl implements MonitoringPlanService{

    Calendar today = CalendarExt.getToday();

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    ActivityMonitoringPlanRepository actMpRepository;

    @Autowired
    ArrangementRepository arRepository;

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Override
    public boolean doUpsertDelete(MonitoringPlanRepository monitoringPlanRepository, ActivityMonitoringPlanRequest fullModel, User user, ResponseMessage response) {

        MonitoringPlan monitoringPlan = monitoringPlanRepository.getStatus(user.getId(), fullModel.getCaseId(), fullModel.getMonitoringPlanId());

        if(ValidatePlanMonitoring(monitoringPlan, monitoringPlanRepository, fullModel, user, response) == false)
            return false;

        fullModel.setNow(Calendar.getInstance());

        //First set status to delete for all activities
        List<Long> lstActivitiesDel = fullModel.getLstActivitiesDel();
        for(Long id : lstActivitiesDel){
            delete(id, fullModel, user.getUsername());
        }
        actMpRepository.flush();

        List<Long> lastHearingFormatId = hearingFormatRepository.getLastHearingFormatByMonPlan(fullModel.getMonitoringPlanId(), new PageRequest(0,1));
        List<SelectList> lstArrangementSelected = arRepository.findLstArrangementByHearingFormatId(lastHearingFormatId.get(0));

        Long idCase = fullModel.getCaseId();
        List<ActivityMonitoringPlanDto> lstActivitiesUpsert = fullModel.getLstActivitiesUpsert();
        String groupUid = UUID.randomUUID().toString();
        for(ActivityMonitoringPlanDto dto:lstActivitiesUpsert){

            if(validateDates(dto.getStartCalendar(), dto.getEndCalendar()) == false)
                continue;

            if(idCase != dto.getCaseId())
                continue;
            try{
                if(dto.getActivityId() > 0){
                    //Si está en modo autorizado, se agrega uno nuevo para, en caso de no ser aceptado se toma la actividad anterior
                    if(fullModel.isInAuthorizeReady())
                        create(dto, actMpRepository, user, fullModel, lstArrangementSelected, groupUid, true);
                    else
                        update(dto, actMpRepository, user, fullModel, lstArrangementSelected, null);
                }
                else{
                    create(dto, actMpRepository, user, fullModel, lstArrangementSelected, groupUid, false);
                }
            }catch(Exception ex){
                logException.Write(ex, this.getClass(), "doUpsertDelete", user.getUsername());
            }
        }

        if(monitoringPlan.getPosAuthorizationChangeTime() == null && fullModel.hasActivitiesInPreAuthorizeMode()){
            //Se añade una notificación para informar al coordinador que tiene que revisar el cambio del plan de seguimiento

            monitoringPlan.setPosAuthorizationChangeTime(fullModel.getNow());
        }

        actMpRepository.flush();
        return true;
    }

    private boolean validateDates(Calendar startCalendar, Calendar endCalendar) {
        if(startCalendar.before(today) || endCalendar.before(today))
            return false;
        return true;
    }

    private void delete(Long id, ActivityMonitoringPlanRequest fullModel, String username) {
        ActivityMonitoringPlan activityMonitoringPlan = actMpRepository.findOneValid(id, fullModel.getMonitoringPlanId(), fullModel.getCaseId());
        if(activityMonitoringPlan == null)
            return;

        String status = activityMonitoringPlan.getStatus();
        if(status.equals(MonitoringConstants.STATUS_ACTIVITY_DELETED) || status.equals(MonitoringConstants.STATUS_ACTIVITY_DONE) || status.equals(MonitoringConstants.STATUS_ACTIVITY_FAILED))
            return;

        if(validateDates(activityMonitoringPlan.getStart(), activityMonitoringPlan.getEnd()) == false)
            return;

        ActivityMonitoringPlanJson jsonOld = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);
        activityMonitoringPlan.setStatus(MonitoringConstants.STATUS_ACTIVITY_DELETED);
        ActivityMonitoringPlanJson jsonNew = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, username, fullModel.getCaseId(), fullModel.getMonitoringPlanStatus()));
        actMpRepository.save(activityMonitoringPlan);
        fullModel.incActsDel();

    }

    private void create(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                        List<SelectList> lstArrangementSelected, String groupUid, boolean bIsAuthUpdate) {
        ActivityMonitoringPlan activityMonitoringPlan = new ActivityMonitoringPlan();

        if(bIsAuthUpdate){
            ActivityMonitoringPlan activityMonitoringPlanToUpdate = getValidActivityMonitoringPlanToUpdate(dto, actMpRepository);
            if (activityMonitoringPlanToUpdate == null) return;

            //Si no tiene una actividad a quien reemplazar, se debe crear una nueva, de lo contrario sólo se actualiza la anterior
            if(activityMonitoringPlan.getActMonPlanToReplace() == null){
                activityMonitoringPlan.setActMonPlanToReplace(activityMonitoringPlanToUpdate);
                activityMonitoringPlan.setGroup(activityMonitoringPlanToUpdate.getGroup());
            }
            else{
                update(dto, actMpRepository, user, fullModel, lstArrangementSelected, activityMonitoringPlanToUpdate);
                fullModel.decActsUpd();
                fullModel.incActsPreUpd();
                return;
            }
        }
        else{
            activityMonitoringPlan.setGroup(groupUid);
        }

        activityMonitoringPlan.setSupervisorCreate(user);
        activityMonitoringPlan.setCreationTime(fullModel.getNow());

        DtoToModelAndSave(dto, actMpRepository, user, fullModel, activityMonitoringPlan, lstArrangementSelected, true);

        if(fullModel.isInAuthorizeReady()){
            if(bIsAuthUpdate) fullModel.incActsPreUpd();
            else fullModel.incActsPreIns();

        }else{
            fullModel.incActsIns();
        }
    }

    private void update(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                        List<SelectList> lstArrangementSelected, ActivityMonitoringPlan activityMonitoringPlanToUpdate) {

        ActivityMonitoringPlan activityMonitoringPlan = activityMonitoringPlanToUpdate == null ?
                getValidActivityMonitoringPlanToUpdate(dto, actMpRepository) : activityMonitoringPlanToUpdate;

        if (activityMonitoringPlan == null) return;

        activityMonitoringPlan.setSupervisorModify(user);
        activityMonitoringPlan.setModifyTime(fullModel.getNow());

        DtoToModelAndSave(dto, actMpRepository, user, fullModel, activityMonitoringPlan, lstArrangementSelected, false);
        fullModel.incActsUpd();
    }

    private ActivityMonitoringPlan getValidActivityMonitoringPlanToUpdate(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository) {
        ActivityMonitoringPlan activityMonitoringPlan = actMpRepository.findOneValid(dto.getActivityId(), dto.getMonitoringPlanId(), dto.getCaseId());
        if(activityMonitoringPlan == null)
            return null;
        String status = activityMonitoringPlan.getStatus();
        if(status.equals(MonitoringConstants.STATUS_ACTIVITY_DELETED) || status.equals(MonitoringConstants.STATUS_ACTIVITY_DONE) || status.equals(MonitoringConstants.STATUS_ACTIVITY_FAILED))
            return null;
        //Validate dates of saved activity before change something...
        if(validateDates(activityMonitoringPlan.getStart(), activityMonitoringPlan.getEnd()) == false)
            return null;
        return activityMonitoringPlan;
    }


    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    private void DtoToModelAndSave(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                                   ActivityMonitoringPlan activityMonitoringPlan, List<SelectList> lstArrangementSelected, boolean isNew) {

        ActivityMonitoringPlanJson jsonOld = null;

        if(isNew == false){
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
        for(Long idAssignedArr : dto.getLstArrangements()){
            for(int i=0; i<lstArrangementSelected.size(); i++){
                SelectList slAa = lstArrangementSelected.get(i);

                if(slAa.getId().equals(idAssignedArr))
                {
                    sAssignedArrangements = (sAssignedArrangements == null ? slAa.getName() : sAssignedArrangements + ", " + slAa.getName());
                    sAssignedArrangementsIds = (sAssignedArrangementsIds == null ? idAssignedArr.toString() : sAssignedArrangementsIds + ", " + idAssignedArr.toString());
                    AssignedArrangement aa = new AssignedArrangement();
                    aa.setId(idAssignedArr);
                    ActivityMonitoringPlanArrangement ampa = new ActivityMonitoringPlanArrangement();
                    ampa.setActivityMonitoringPlan(activityMonitoringPlan);
                    ampa.setAssignedArrangement(aa);
                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED);
                    lstAssignedArrangements.add(ampa);
                    break;
                }
            }
        }

        activityMonitoringPlan.setLstAssignedArrangement(lstAssignedArrangements);
        activityMonitoringPlan.setAssignedArrangements(sAssignedArrangements);
        activityMonitoringPlan.setAssignedArrangementsIds(sAssignedArrangementsIds);

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

        if(isNew == true){
            Case caseDetention = new Case();
            caseDetention.setId(dto.getCaseId());
            activityMonitoringPlan.setCaseDetention(caseDetention);

            MonitoringPlan monitoringPlan = new MonitoringPlan();
            monitoringPlan.setId(dto.getMonitoringPlanId());
            activityMonitoringPlan.setMonitoringPlan(monitoringPlan);

            activityMonitoringPlan.setSupervisorCreate(user);
            activityMonitoringPlan.setStatus(MonitoringConstants.STATUS_ACTIVITY_NEW);

        }else{
            activityMonitoringPlan.setSupervisorModify(user);
            activityMonitoringPlan.setStatus(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
            ActivityMonitoringPlanJson jsonNew = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);
            logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), fullModel.getCaseId(), fullModel.getMonitoringPlanStatus()));
        }

        actMpRepository.save(activityMonitoringPlan);
        fullModel.getLstActivitiesUpserted().add(new ActivityMonitoringPlanEvent(activityMonitoringPlan.getId(), dto.getEventId(), activityMonitoringPlan.getGroup()));
    }


    private boolean ValidatePlanMonitoring(MonitoringPlan monitoringPlan, MonitoringPlanRepository monitoringPlanRepository, ActivityMonitoringPlanRequest fullModel, User user, ResponseMessage response) {

        String status = monitoringPlan.getStatus();
        fullModel.setInAuthorizeReady(MonitoringConstants.LST_STATUS_AUTHORIZE_READY.contains(status));

        fullModel.setMonitoringPlanStatus(status);
        if(status != null && (status.equals(MonitoringConstants.STATUS_NEW) || status.equals(MonitoringConstants.STATUS_PENDING_CREATION) ||
                status.equals(MonitoringConstants.STATUS_AUTHORIZED) || status.equals(MonitoringConstants.STATUS_REJECTED_AUTHORIZED) ||
                status.equals(MonitoringConstants.STATUS_MONITORING) || status.equals(MonitoringConstants.STATUS_REJECTED_END))){

            if(status.equals(MonitoringConstants.STATUS_NEW) || status.equals(MonitoringConstants.STATUS_REJECTED_AUTHORIZED)){
                monitoringPlan.setStatus(MonitoringConstants.STATUS_PENDING_CREATION);
                monitoringPlanRepository.save(monitoringPlan);
                monitoringPlanRepository.flush();
            }else if(status.equals(MonitoringConstants.STATUS_REJECTED_END)){
                monitoringPlan.setStatus(MonitoringConstants.STATUS_MONITORING);
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
