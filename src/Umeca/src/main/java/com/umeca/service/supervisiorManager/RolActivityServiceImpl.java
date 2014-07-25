package com.umeca.service.supervisiorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.ResponseMessage;
import com.umeca.model.dto.supervisorManager.*;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.RequestActivities;
import com.umeca.model.entities.supervisorManager.RolActivity;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.repository.supervisorManager.RolActivityRepository;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/13/14
 * Time: 5:47 PM
 */
@Service
public class RolActivityServiceImpl implements RolActivityService{

    Calendar today = CalendarExt.getToday();

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    RolActivityRepository rolActivityRepository;

    @Override
    public boolean doUpsertDelete(RolActivityRequest fullModel, User user, ResponseMessage response) {

        fullModel.setNow(Calendar.getInstance());

        //First set status to delete for all activities
        List<Long> lstActivitiesDel = fullModel.getLstActivitiesDel();
        for(Long id : lstActivitiesDel){
            delete(id, fullModel, user);
        }
        rolActivityRepository.flush();

        List<RolActivityDto> lstActivitiesUpsert = fullModel.getLstActivitiesUpsert();
        for(RolActivityDto dto:lstActivitiesUpsert){

            if(!validateDates(dto.getStartCalendar(), dto.getEndCalendar()))
                continue;

            try{
                if(dto.getRolActivityId() > 0)
                    update(dto, rolActivityRepository, user, fullModel);
                else
                    create(dto, rolActivityRepository, user, fullModel);
            }catch(Exception ex){
                logException.Write(ex, this.getClass(), "doUpsertDelete", user.getUsername());
            }
        }

        rolActivityRepository.flush();
        return true;
    }

    @Override
    public void getLstActivities(RequestActivities req, String statusNotIn, ResponseRolActivities response) {
        List<RolActivityResponse> lstAllActivities = rolActivityRepository.getAllActivities(statusNotIn,
                        (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
        response.setLstRolActivities(lstAllActivities);

        List<SelectList> lstSupervisor = rolActivityRepository.getAllValidSupervisors(statusNotIn,
                (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
        response.setLstSupervisor(lstSupervisor);
        response.setHasError(false);
    }

    private boolean validateDates(Calendar startCalendar, Calendar endCalendar) {
        if(endCalendar.before(startCalendar))
            return false;

        if(startCalendar.before(today) || endCalendar.before(today))
            return false;
        return true;
    }

    private void delete(Long id, RolActivityRequest fullModel, User user) {
        RolActivity rolActivity = rolActivityRepository.findOne(id);
        if(rolActivity == null)
            return;

        String status = rolActivity.getStatus();
        if(status.equals(MonitoringConstants.STATUS_ACTIVITY_DELETED) || status.equals(MonitoringConstants.STATUS_ACTIVITY_DONE) || status.equals(MonitoringConstants.STATUS_ACTIVITY_FAILED))
            return;

        if(validateDates(rolActivity.getStart(), rolActivity.getEnd()) == false)
            return;

        rolActivity.setUserModify(user);
        rolActivity.setStatus(MonitoringConstants.STATUS_ACTIVITY_DELETED);

        rolActivityRepository.save(rolActivity);
        fullModel.addActsDel();

    }

    private void create(RolActivityDto dto, RolActivityRepository rolActivityRepository, User user, RolActivityRequest fullModel) {

        RolActivity rolActivity = new RolActivity();
        rolActivity.setUserCreate(user);
        rolActivity.setCreationTime(fullModel.getNow());

        DtoToModelAndSave(dto, rolActivityRepository, user, fullModel, rolActivity, true);
        fullModel.addActsIns();
    }

    private void update(RolActivityDto dto, RolActivityRepository rolActivityRepository, User user, RolActivityRequest fullModel) {
        RolActivity rolActivity = rolActivityRepository.findOne(dto.getRolActivityId());

        if(rolActivity == null)
            return;

        String status = rolActivity.getStatus();
        if(status.equals(MonitoringConstants.STATUS_ACTIVITY_DELETED))
            return;

        //Validate dates of saved activity before change something...
        if(validateDates(rolActivity.getStart(), rolActivity.getEnd()) == false)
            return;

        rolActivity.setUserModify(user);
        rolActivity.setModifyTime(fullModel.getNow());

        DtoToModelAndSave(dto, rolActivityRepository, user, fullModel, rolActivity, false);
        fullModel.addActsUpd();
    }


    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    private void DtoToModelAndSave(RolActivityDto dto, RolActivityRepository rolActivityRepository, User user, RolActivityRequest fullModel,
                                   RolActivity rolActivity, boolean isNew) {

        User supervisor = new User();
        supervisor.setId(dto.getSupervisorId());
        rolActivity.setSupervisor(supervisor);

        Calendar cal = dto.getEndCalendar();
        rolActivity.setEnd(cal);
        rolActivity.setSearchEnd((cal.get(Calendar.YEAR) * 100) + (cal.get(Calendar.MONTH) + 1));
        cal = dto.getStartCalendar();
        rolActivity.setStart(cal);
        rolActivity.setSearchStart((cal.get(Calendar.YEAR) * 100) + (cal.get(Calendar.MONTH) + 1));

        if(isNew){
            rolActivity.setUserCreate(user);
            rolActivity.setStatus(MonitoringConstants.STATUS_ACTIVITY_NEW);

        }else{
            rolActivity.setUserModify(user);
            rolActivity.setStatus(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
        }

        rolActivityRepository.save(rolActivity);
        fullModel.getLstActivitiesUpserted().add(new RolActivityEvent(rolActivity.getId(), dto.getEventId()));
    }

}
