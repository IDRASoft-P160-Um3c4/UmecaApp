package com.umeca.service.supervisor;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.ScheduleHearing;
import com.umeca.model.entities.shared.SystemSettingValues;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.RelationModel;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.shared.ScheduleHearingRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.shared.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.umeca.model.shared.MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED;

@Service("scheduleHearingsService")
public class ScheduleHearingsServiceImpl implements ScheduleHearingsService{

    @Autowired
    ActivityMonitoringPlanRepository actMonPlanRepository;

    @Autowired
    MonitoringPlanRepository monPlanRepository;

    @Autowired
    FramingReferenceRepository framingReferenceRepository;

    @Autowired
    SystemSettingService systemSettingService;

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    ScheduleHearingRepository scheduleHearingRepository;

    @Autowired
    AssignedArrangementRepository assignedArrangementRepository;

    @Autowired
    LogCommentRepository logCommentRepository;

    @Override
    @Transactional
    public boolean doScheduleNewHearing(final ScheduleNewHearingReq model, User user, ResponseMessage response) {

        Calendar now = Calendar.getInstance();
        SystemSettingValues ssValues = systemSettingService.getSystemSettingsValues();

        for(final Long monPlanId : model.getLstMonPlanIds()){

            //Add activity schedule hearing
            final Long userToReceiveId = monPlanRepository.getUserIdByMonPlanId(monPlanId);
            final Long caseId = monPlanRepository.getCaseIdByMonPlan(monPlanId);
            String groupUuid = UUID.randomUUID().toString();
            List<Long> lstLastHearingFormatId = hearingFormatRepository.getLastHearingFormatByMonPlan(caseId, new PageRequest(0, 1));
            final Long lastHearingFormatId = lstLastHearingFormatId.get(0);
            List<Long> lstSelSourceRel = framingReferenceRepository.findSelectedReferencesRel(caseId, new ArrayList<String>() {{
                add(FramingMeetingConstants.PERSON_TYPE_IMPUTED);
            }});

            List<SelectList> lstAssignedArrangements = getLstAssignedArrangements(ssValues.getLstIdsArrangement(), lastHearingFormatId);

            ActivityMonitoringPlan actMonPlanHearing = createActMonPlan(user, now, ssValues.getGoalActivityId(),
                    lstSelSourceRel.get(0), ssValues.getSupervisionActivityId(), monPlanId, caseId, groupUuid,
                    lstAssignedArrangements, (Calendar)model.getHearingDate().clone(), (Calendar)model.getHearingDate().clone());


            //Save schedule hearing
            ScheduleHearing scheduleHearing = new ScheduleHearing();
            scheduleHearing.setUser(user);
            scheduleHearing.setActivityMonitoringPlanHearing(actMonPlanHearing);
            MonitoringPlan monPlan = new MonitoringPlan(){{setId(monPlanId);}};
            scheduleHearing.setMonitoringPlan(monPlan);
            scheduleHearing.setTimestamp(now);
            Case caseDetention = new Case(){{setId(caseId);}};
            scheduleHearing.setCaseDetention(caseDetention);
            scheduleHearing.setHasReminder(model.getHasReminder());
            scheduleHearing.setHearingDate(model.getHearingDate());
            scheduleHearing.setRoom(model.getHearingRoom());
            scheduleHearing.setHearingType(new HearingType(){{setId(model.getHearingTypeId());}});

            //Add activity reminder hearing
            if(model.getHasReminder()){

                List<SelectList> lstAssignedArrangementsReminder = getLstAssignedArrangements(ssValues.getLstIdsArrangementReminder(), lastHearingFormatId);
                ActivityMonitoringPlan actMonPlanHearingReminder = createActMonPlan(user, now, ssValues.getGoalActivityIdReminder(),
                        lstSelSourceRel.get(0), ssValues.getSupervisionActivityIdReminder(), monPlanId, caseId, groupUuid,
                        lstAssignedArrangementsReminder, (Calendar)model.getHearingDateReminder().clone(), (Calendar)model.getHearingDateReminder().clone());

                scheduleHearing.setActivityMonitoringPlanHearingReminder(actMonPlanHearingReminder);
                scheduleHearing.setHearingReminderDate(model.getHearingDateReminder());
            }

            scheduleHearingRepository.save(scheduleHearing);

            //Save schedule notification
            LogComment commentModel = new LogComment();
            commentModel.setComments("Se ha agregado un actividad al plan de monitoreo para agendar la audiencia");
            commentModel.setAction(MonitoringConstants.STATUS_ACTIVITY_NEW);
            commentModel.setMonitoringPlan(monPlan);
            commentModel.setCaseDetention(caseDetention);
            commentModel.setSenderUser(user);
            commentModel.setReceiveUser(new User(){{setId(userToReceiveId);}});
            commentModel.setTimestamp(now);
            commentModel.setType(MonitoringConstants.TYPE_COMMENT_SCHEDULE_HEARING);
            logCommentRepository.save(commentModel);

        }

        return true;
    }

    private List<SelectList> getLstAssignedArrangements(List<Long> lstArrangements, final Long lastHearingFormatId) {
        List<RelationModel> relAssignedArrAndArrangements =
                arrangementRepository.findLstHearingFormatIdsAndArrangementIdsByHearingFormatId(lastHearingFormatId);
        List<Long> lstArrangementsNotFound = new ArrayList<>();
        List<SelectList> lstAssignedArrangements = new ArrayList<>();

        boolean bFound;
        for(Long arrangementId : lstArrangements){
            bFound = false;
            for(final RelationModel relAssArr : relAssignedArrAndArrangements){
                if(relAssArr.getSecondId() == arrangementId){
                    bFound = true;
                    lstAssignedArrangements.add(new SelectList(relAssArr.getFirstId(), relAssArr.getName()));
                    break;
                }
            }

            if(bFound == false)
                lstArrangementsNotFound.add(arrangementId);
        }

        String arrangementDesc;
        for(final Long arrangementId : lstArrangementsNotFound){
            final AssignedArrangement assignedArrangement = new AssignedArrangement();
            arrangementDesc = arrangementRepository.findArrangementDescById(arrangementId);
            assignedArrangement.setArrangement(new Arrangement(){{setId(arrangementId);}});
            assignedArrangement.setDescription(HearingFormatConstants.DESCRIPTION_ASSIGNED_ARRANGEMENT_BY_SYSTEM);
            assignedArrangement.setHearingFormat(new HearingFormat(){{setId(lastHearingFormatId);}});
            assignedArrangement.setRequirePresence(false);
            assignedArrangementRepository.save(assignedArrangement);
            lstAssignedArrangements.add(new SelectList(assignedArrangement.getId(), arrangementDesc));
        }
        return lstAssignedArrangements;
    }

    private ActivityMonitoringPlan createActMonPlan(User user, Calendar now,
                                                    Long goalId, Long selSourceRelId, Long supervisionActivityId,
                                                    Long monPlanId, Long caseId, String groupUuid,
                                                    List<SelectList> lstAssignedArrangementsSel, Calendar calStart, Calendar calEnd) {

        ActivityMonitoringPlan activityMonitoringPlan = new ActivityMonitoringPlan();
        activityMonitoringPlan.setGroup(groupUuid);
        activityMonitoringPlan.setCreationTime(now);

        ActivityGoal activityGoal = new ActivityGoal();
        activityGoal.setId(goalId);
        activityMonitoringPlan.setActivityGoal(activityGoal);
        activityMonitoringPlan.setStatus(MonitoringConstants.STATUS_ACTIVITY_NEW);

        FramingSelectedSourceRel framingSelectedSourceRel = new FramingSelectedSourceRel();
        framingSelectedSourceRel.setId(selSourceRelId);
        activityMonitoringPlan.setFramingSelectedSourceRel(framingSelectedSourceRel);

        SupervisionActivity supervisionActivity = new SupervisionActivity();
        supervisionActivity.setId(supervisionActivityId);
        activityMonitoringPlan.setSupervisionActivity(supervisionActivity);


        String sAssignedArrangements = null;
        String sAssignedArrangementsIds = null;
        List<ActivityMonitoringPlanArrangement> lstAssignedArrangements = new ArrayList<>();

        for (SelectList slAa : lstAssignedArrangementsSel) {
            Long idAssignedArr = slAa.getId();
            sAssignedArrangements = (sAssignedArrangements == null ? slAa.getName() : sAssignedArrangements + ", " + slAa.getName());
            sAssignedArrangementsIds = (sAssignedArrangementsIds == null ? idAssignedArr.toString() : sAssignedArrangementsIds + ", " + idAssignedArr.toString());
            AssignedArrangement aa = new AssignedArrangement();
            aa.setId(idAssignedArr);
            ActivityMonitoringPlanArrangement ampa = new ActivityMonitoringPlanArrangement();
            ampa.setActivityMonitoringPlan(activityMonitoringPlan);
            ampa.setAssignedArrangement(aa);
            ampa.setStatus(ACTIVITY_ARRANGEMENT_UNDEFINED);
            lstAssignedArrangements.add(ampa);
        }

        if (activityMonitoringPlan.getLstAssignedArrangement() != null)
            activityMonitoringPlan.getLstAssignedArrangement().clear();
        else
            activityMonitoringPlan.setLstAssignedArrangement(new ArrayList<ActivityMonitoringPlanArrangement>());

        activityMonitoringPlan.getLstAssignedArrangement().addAll(lstAssignedArrangements);
        activityMonitoringPlan.setAssignedArrangements(sAssignedArrangements);
        activityMonitoringPlan.setAssignedArrangementsIds(sAssignedArrangementsIds);

        calEnd.add(Calendar.MINUTE, 15);
        activityMonitoringPlan.setEnd(calEnd);
        activityMonitoringPlan.setSearchEnd((calEnd.get(Calendar.YEAR) * 100) + (calEnd.get(Calendar.MONTH) + 1));
        activityMonitoringPlan.setStart(calStart);
        activityMonitoringPlan.setSearchStart((calStart.get(Calendar.YEAR) * 100) + (calStart.get(Calendar.MONTH) + 1));

        Case caseDetention = new Case();
        caseDetention.setId(caseId);
        activityMonitoringPlan.setCaseDetention(caseDetention);

        MonitoringPlan monitoringPlan = new MonitoringPlan();
        monitoringPlan.setId(monPlanId);
        activityMonitoringPlan.setMonitoringPlan(monitoringPlan);

        activityMonitoringPlan.setSupervisorCreate(user);
        actMonPlanRepository.save(activityMonitoringPlan);

        return activityMonitoringPlan;
    }
}
