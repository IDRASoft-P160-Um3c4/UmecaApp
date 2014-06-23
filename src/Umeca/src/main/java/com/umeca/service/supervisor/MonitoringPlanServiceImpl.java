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
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Override
    public boolean doUpsertDelete(MonitoringPlanRepository monitoringPlanRepository, ActivityMonitoringPlanRequest fullModel, User user, ResponseMessage response) {
        if(ValidatePlanMonitoring(monitoringPlanRepository, fullModel, user, response) == false)
            return false;

        //First set status to delete for all activities
        List<Long> lstActivitiesDel = fullModel.getLstActivitiesDel();
        for(Long id : lstActivitiesDel){
            delete(id, fullModel, user.getUsername());
        }
        actMpRepository.flush();

        List<SelectList> lstArrangementSelected = arRepository.findLstArrangement(fullModel.getMonitoringPlanId());

        Long idCase = fullModel.getCaseId();
        List<ActivityMonitoringPlanDto> lstActivitiesUpsert = fullModel.getLstActivitiesUpsert();
        for(ActivityMonitoringPlanDto dto:lstActivitiesUpsert){

            if(validateDates(dto.getStartCalendar(), dto.getEndCalendar()) == false)
                continue;

            if(idCase != dto.getCaseId())
                continue;
            try{
                if(dto.getActivityId() > 0)
                    update(dto, actMpRepository, user, fullModel, lstArrangementSelected);
                else
                    create(dto, actMpRepository, user, fullModel, lstArrangementSelected);
            }catch(Exception ex){
                logException.Write(ex, this.getClass(), "doUpsertDelete", user.getUsername());
            }
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
        if(status == MonitoringConstants.STATUS_ACTIVITY_DELETED || status == MonitoringConstants.STATUS_ACTIVITY_DONE || status == MonitoringConstants.STATUS_ACTIVITY_FAILED)
            return;

        if(validateDates(activityMonitoringPlan.getStart(), activityMonitoringPlan.getEnd()) == false)
            return;

        ActivityMonitoringPlanJson jsonOld = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);
        activityMonitoringPlan.setStatus(MonitoringConstants.STATUS_ACTIVITY_DELETED);
        ActivityMonitoringPlanJson jsonNew = ActivityMonitoringPlanJson.convertToJson(activityMonitoringPlan);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, username, fullModel.getCaseId(), fullModel.getMonitoringPlanStatus()));
        actMpRepository.save(activityMonitoringPlan);
        fullModel.addActsDel();

    }

    private void create(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                        List<SelectList> lstArrangementSelected) {
        DtoToModelAndSave(dto, actMpRepository, user, fullModel, new ActivityMonitoringPlan(), lstArrangementSelected, true);
        fullModel.addActsIns();
    }

    private void update(ActivityMonitoringPlanDto dto, ActivityMonitoringPlanRepository actMpRepository, User user, ActivityMonitoringPlanRequest fullModel,
                        List<SelectList> lstArrangementSelected) {
        ActivityMonitoringPlan activityMonitoringPlan = actMpRepository.findOneValid(dto.getActivityId(), dto.getMonitoringPlanId(), dto.getCaseId());

        if(activityMonitoringPlan == null)
            return;

        String status = activityMonitoringPlan.getStatus();
        if(status == MonitoringConstants.STATUS_ACTIVITY_DELETED || status == MonitoringConstants.STATUS_ACTIVITY_DONE || status == MonitoringConstants.STATUS_ACTIVITY_FAILED)
            return;

        //Validate dates of saved activity before change something...
        if(validateDates(activityMonitoringPlan.getStart(), activityMonitoringPlan.getEnd()) == false)
            return;

        DtoToModelAndSave(dto, actMpRepository, user, fullModel, activityMonitoringPlan, lstArrangementSelected, false);
        fullModel.addActsUpd();
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
        AidSource aidSource = new AidSource();
        aidSource.setId(dto.getSourceId());
        activityMonitoringPlan.setAidSource(aidSource);

        String sAssignedArrangements = null;
        List<AssignedArrangement> lstAssignedArrangements = new ArrayList<>();
        for(Long idAssignedArr : dto.getLstArrangements()){
            for(int i=0; i<lstArrangementSelected.size(); i++){
                SelectList slAa = lstArrangementSelected.get(i);

                if(slAa.getId() == idAssignedArr)
                {
                    sAssignedArrangements = (sAssignedArrangements == null ? slAa.getName() : sAssignedArrangements + ", " + slAa.getName());
                    AssignedArrangement aa = new AssignedArrangement();
                    aa.setId(idAssignedArr);
                    lstAssignedArrangements.add(aa);
                    break;
                }
            }
        }

        activityMonitoringPlan.setLstAssignedArrangement(lstAssignedArrangements);
        activityMonitoringPlan.setAssignedArrangements(sAssignedArrangements);


        SupervisionActivity supervisionActivity = new SupervisionActivity();
        supervisionActivity.setId(dto.getActivityMonId());
        activityMonitoringPlan.setSupervisionActivity(supervisionActivity);

        activityMonitoringPlan.setEnd(dto.getEndCalendar());
        activityMonitoringPlan.setStart(dto.getStartCalendar());

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
        fullModel.getLstActivitiesUpserted().add(new ActivityMonitoringPlanEvent(activityMonitoringPlan.getId(), dto.getEventId()));
    }


    private boolean ValidatePlanMonitoring(MonitoringPlanRepository monitoringPlanRepository, ActivityMonitoringPlanRequest fullModel, User user, ResponseMessage response) {

        MonitoringPlan monitoringPlan = monitoringPlanRepository.getStatus(user.getId(), fullModel.getCaseId(), fullModel.getMonitoringPlanId());
        String status = monitoringPlan.getStatus();

        fullModel.setMonitoringPlanStatus(status);
        if(status != null && (status.equals(MonitoringConstants.STATUS_NEW) || status.equals(MonitoringConstants.STATUS_PENDING_CREATION) || status.equals(MonitoringConstants.STATUS_AUTHORIZED))){
            if(status.equals(MonitoringConstants.STATUS_NEW)){
                monitoringPlan.setStatus(MonitoringConstants.STATUS_PENDING_CREATION);
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
