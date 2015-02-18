package com.umeca.service.director;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.CatPriority;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.agenda.*;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.repository.supervisor.ActivityAgendaRepository;
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

import static com.umeca.model.shared.MonitoringConstants.*;

@Service
public class ActivityAgendaServiceImpl implements ActivityAgendaService {

    Calendar today = CalendarExt.getToday();

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    ActivityAgendaRepository activityAgendaRepository;

    @Override
    public boolean doUpsertDelete(SharedUserService sharedUserService, ActivityAgendaRequest fullModel, User user, ResponseMessage response) {

        fullModel.setNow(Calendar.getInstance());

        //First set status to delete for all activities
        List<Long> lstActivitiesDel = fullModel.getLstActivitiesDel();
        for (Long id : lstActivitiesDel) {
            delete(id, fullModel, user.getUsername());
        }
//        actMpRepository.flush();

        List<ActivityAgendaDto> lstActivitiesUpsert = fullModel.getLstActivitiesUpsert();

        for (ActivityAgendaDto dto : lstActivitiesUpsert) {
            if (validateDates(dto.getStartCalendar(), dto.getEndCalendar()) == false)
                continue;

            try {
                if (dto.getActivityId() > 0) {
                    update(dto, user, fullModel);
                } else {
                    create(dto, user, fullModel);
                }
            } catch (Exception ex) {
                logException.Write(ex, this.getClass(), "doUpsertDelete", user.getUsername());
            }
        }

        activityAgendaRepository.flush();
        return true;
    }

    private boolean validateDates(Calendar startCalendar, Calendar endCalendar) {
        if (startCalendar.before(today) || endCalendar.before(today))
            return false;
        return true;
    }

    private void delete(Long id, ActivityAgendaRequest fullModel, String username) {
        /*
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
        */
    }

    private void create(ActivityAgendaDto dto, User user, ActivityAgendaRequest fullModel) {
        ActivityAgenda activityAgenda = new ActivityAgenda();

        DtoToModelAndSave(dto, user, fullModel, activityAgenda, true);

        fullModel.incActsIns();
    }

    private void update(ActivityAgendaDto dto, User user, ActivityAgendaRequest fullModel) {

        ActivityAgenda activityAgenda = getValidActivityAgendaToUpdate(dto, user.getId());

        if (activityAgenda == null) return;


        DtoToModelAndSave(dto, user, fullModel, activityAgenda, false);
        fullModel.incActsUpd();
    }

    private ActivityAgenda getValidActivityAgendaToUpdate(ActivityAgendaDto dto, Long userId) {
        ActivityAgenda activityAgenda = activityAgendaRepository.findOneValid(dto.getActivityId(), userId, STATUS_ACTIVITY_DONE);
        if (activityAgenda == null)
            return null;
        //Validate dates of saved activity before change something...
        if (validateDates(activityAgenda.getStart(), activityAgenda.getEnd()) == false)
            return null;
        return activityAgenda;
    }


    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    private void DtoToModelAndSave(ActivityAgendaDto dto, User user, ActivityAgendaRequest fullModel,
                                   ActivityAgenda activityAgenda, boolean isNew) {

        ActivityAgendaJson jsonOld = null;

        if (isNew == false) {
            jsonOld = ActivityAgendaJson.convertToJson(activityAgenda);
        }

        CatPriority priority = new CatPriority();
        priority.setId(dto.getPriorityId());
        activityAgenda.setPriority(priority);

        activityAgenda.setPlace(dto.getPlace());
        activityAgenda.setDescription(dto.getDescription());

        Calendar cal = dto.getEndCalendar();
        activityAgenda.setEnd(cal);
        activityAgenda.setSearchEnd((cal.get(Calendar.YEAR) * 100) + (cal.get(Calendar.MONTH) + 1));
        cal = dto.getStartCalendar();
        activityAgenda.setStart(cal);
        activityAgenda.setSearchStart((cal.get(Calendar.YEAR) * 100) + (cal.get(Calendar.MONTH) + 1));

        if (isNew == true) {
            activityAgenda.setStatus(STATUS_ACTIVITY_NEW);
            activityAgenda.setUserOwner(user);
            activityAgenda.setCreationTime(fullModel.getNow());

        } else {
            activityAgenda.setStatus(STATUS_ACTIVITY_MODIFIED);
            activityAgenda.setUserUpdate(user);
            activityAgenda.setUpdateTime(fullModel.getNow());

            ActivityAgendaJson jsonNew = ActivityAgendaJson.convertToJson(activityAgenda);
            logChangeDataRepository.save(new LogChangeData(ActivityAgenda.class.getName(), jsonOld, jsonNew, user.getUsername()));
        }

        activityAgendaRepository.save(activityAgenda);
        fullModel.getLstActivitiesUpserted().add(new ActivityAgendaEvent(activityAgenda.getId(), dto.getEventId()));
    }
}
