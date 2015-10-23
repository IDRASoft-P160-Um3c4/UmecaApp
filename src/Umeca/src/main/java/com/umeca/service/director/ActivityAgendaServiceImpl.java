package com.umeca.service.director;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.CatPriority;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.agenda.*;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.shared.Constants;
import com.umeca.repository.supervisor.ActivityAgendaRepository;
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            delete(id, fullModel, user);
        }

        activityAgendaRepository.flush();

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

    @Override
    public void getLstActivitiesByUser(SharedUserService sharedUserService, RequestAgendaActivities req, Long userId, ResponseAgendaActivities response) {

        req.setUserId(Constants.ENTITY_ID_NULL);
        if(sharedUserService.isUserInRole(userId, Constants.ROLE_DIRECTOR)){
            req.setUserId(userId);
        }
        List<ActivityAgendaResponse> lstAllActivities = activityAgendaRepository.getAllActivitiesByUser(
                new ArrayList<String>() {{add(STATUS_ACTIVITY_DELETED);}},
                (req.getYearStart() * 100) + req.getMonthStart(),
                (req.getYearEnd() * 100) + req.getMonthEnd(), req.getUserId());
        response.setLstAgendaActivities(lstAllActivities);

        response.setHasError(false);
    }

    @Override
    public boolean endActivity(SharedUserService sharedUserService, ActivityAgendaEndRequest model, User user, ResponseMessage response) {

        ActivityAgenda activityAgenda = activityAgendaRepository.findOneValid(model.getActivityId(), user.getId(),
                new ArrayList<String>(){{add(STATUS_ACTIVITY_DONE); add(STATUS_ACTIVITY_DELETED);}});

        if (activityAgenda == null){
            response.setHasError(true);
            response.setMessage("La actividad ha sido eliminada anteriormente o ya fue realizada");
            return false;
        }

        ActivityAgendaJson jsonOld = ActivityAgendaJson.convertToJson(activityAgenda);
        activityAgenda.setStatus(STATUS_ACTIVITY_DONE);
        activityAgenda.setDone(model.getIsDone());
        activityAgenda.setDoneTime(Calendar.getInstance());
        activityAgenda.setComments(model.getComments());
        ActivityAgendaJson jsonNew = ActivityAgendaJson.convertToJson(activityAgenda);

        logChangeDataRepository.save(new LogChangeData(ActivityAgenda.class.getName(), jsonOld, jsonNew, user.getUsername()));
        activityAgendaRepository.save(activityAgenda);

        return true;
    }

    private boolean validateDates(Calendar startCalendar, Calendar endCalendar) {
        if (startCalendar.before(today) || endCalendar.before(today))
            return false;
        return true;
    }

    private void delete(Long id, ActivityAgendaRequest fullModel, User user) {

        ActivityAgenda activityAgenda = activityAgendaRepository.findOneValid(id, user.getId(),
                new ArrayList<String>(){{add(STATUS_ACTIVITY_DONE); add(STATUS_ACTIVITY_DELETED);}});

        if (activityAgenda == null)
            return;

        String status = activityAgenda.getStatus();
        if (LST_STATUS_ACTIVITY_END.contains(status))
            return;

        if (validateDates(activityAgenda.getStart(), activityAgenda.getEnd()) == false)
            return;

        ActivityAgendaJson jsonOld = ActivityAgendaJson.convertToJson(activityAgenda);
        activityAgenda.setStatus(STATUS_ACTIVITY_DELETED);
        ActivityAgendaJson jsonNew = ActivityAgendaJson.convertToJson(activityAgenda);

        logChangeDataRepository.save(new LogChangeData(ActivityAgenda.class.getName(), jsonOld, jsonNew, user.getUsername()));
        activityAgendaRepository.save(activityAgenda);

        fullModel.incActsDel();

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
        ActivityAgenda activityAgenda = activityAgendaRepository.findOneValid(dto.getActivityId(), userId,
                new ArrayList<String>(){{add(STATUS_ACTIVITY_DONE); add(STATUS_ACTIVITY_DELETED);}});
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
