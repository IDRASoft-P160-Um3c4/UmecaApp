package com.umeca.service.supervisiorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.EvaluationActivity;
import com.umeca.model.dto.supervisorManager.*;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.managereval.RelRolActivityEvaluationActivity;
import com.umeca.model.entities.supervisor.RequestActivities;
import com.umeca.model.entities.supervisorManager.RolActivity;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.managereval.EvaluationActivityRepository;
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.repository.supervisorManager.RolActivityRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MessageServiceImpl;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    @Autowired
    EvaluationActivityRepository evaluationActivityRepository;
    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    RolActivityService rolActivityService;


    @Override
    public boolean doUpsertDelete(RolActivityRequest fullModel, User user, ResponseMessage response) {

        fullModel.setNow(Calendar.getInstance());

        //First set status to delete for all activities
        List<Long> lstActivitiesDel = fullModel.getLstActivitiesDel();
        for(Long id : lstActivitiesDel){
            delete(id, fullModel, user);
            RolActivity act = rolActivityRepository.findOne(id);
            User userN = new User();
            userN.setId(act.getEvaluator().getId());
            messageService.sendNotificationToUser(null, "El evento asignado ha sido eliminado", user, userN, "ACTIVIDAD ROL EVALUACIÓN ELIMINADA", null);
        }
        rolActivityRepository.flush();

        List<RolActivityDto> lstActivitiesUpsert = fullModel.getLstActivitiesUpsert();

        for(RolActivityDto dto:lstActivitiesUpsert){
            if(!validateDates(dto.getStartCalendar(), dto.getEndCalendar()))
                continue;
            try{
                User userR = new User();
                String bodyMsg = null;
                String startDate = dto.getStartCalendar().get(GregorianCalendar.DAY_OF_MONTH) + "/"
                        + (dto.getStartCalendar().get(GregorianCalendar.MONTH) + 1)  + "/"
                        + dto.getStartCalendar().get(GregorianCalendar.YEAR);

                String endDate = dto.getEndCalendar().get(GregorianCalendar.DAY_OF_MONTH) + "/"
                        + (dto.getEndCalendar().get(GregorianCalendar.MONTH) + 1) + "/"
                        + dto.getEndCalendar().get(GregorianCalendar.YEAR);

                String startHour = dto.getStartCalendar().get(GregorianCalendar.HOUR_OF_DAY) + ":"
                        + dto.getStartCalendar().get(GregorianCalendar.MINUTE) + " hrs";

                String endHour = dto.getEndCalendar().get(GregorianCalendar.HOUR_OF_DAY) + ":"
                        + dto.getEndCalendar().get(GregorianCalendar.MINUTE) + " hrs";

                String activities = "";
                String title = "";

                if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER) && dto.getRolActivityId() < 0){
                    List<SelectList> lstActivities = dto.getActivities();
                    for(SelectList act: lstActivities){
                        if(act.getIsSelected() == true)
                            activities = activities + "<li>" + act.getName() + "</li>";
                    }
                }

                if(dto.getRolActivityId() > 0) {

                    update(dto, rolActivityRepository, user, fullModel);
                    if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER)){
                        userR.setId(dto.getEvaluatorId());
                        bodyMsg = "<br/><div class=\"row\"><div class=\"col-xs-3\"><strong>Lugar:</strong> " + dto.getPlace() + "</div>" +
                                "<div class=\"row\"><div class=\"col-xs-12\"><strong>Fecha inicio actividad:</strong> " + startDate +"<br/>" +
                                "<strong>Fecha fin actividad:</strong> " + endDate +"<br/><strong>Hora inicio actividad:</strong> "+ startHour +"<br/>" +
                                "<strong>Hora fin actividad:</strong> "+ endHour +"<br/></div></div>";
                        title = "ACTIVIDAD ROL EVALUACIÓN RE-AGENDADA";
                    }else{
                        userR.setId(dto.getSupervisorId());
                        bodyMsg = "<br/><div class=\"row\"><div class=\"col-xs-3\"><strong>Lugar:</strong> " + dto.getPlace() + "</div>" +
                                "<div class=\"row\"><div class=\"col-xs-12\"><strong>Fecha inicio actividad:</strong> " + startDate +"<br/>" +
                                "<strong>Fecha fin actividad:</strong> " + endDate +"<br/><strong>Hora inicio actividad:</strong> "+ startHour +"<br/>" +
                                "<strong>Hora fin actividad:</strong> "+ endHour +"<br/></div></div>";
                        title = "ACTIVIDAD ROL SUPERVISIÓN RE-AGENDADA";
                    }

                } else {
                    create(dto, rolActivityRepository, user, fullModel);
                    if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER)){
                        userR.setId(dto.getEvaluatorId());
                        bodyMsg = "<br/><div class=\"row\"><div class=\"col-xs-3\"><strong>Lugar:</strong> " + dto.getPlace() + "</div><div class=\"col-xs-3\"><strong>Actividad(es):</strong>" +
                                "<ul>"+ activities +"</ul>" +
                                "</div></div><div class=\"row\"><div class=\"col-xs-12\"><strong>Fecha inicio actividad:</strong> " + startDate +"<br/>" +
                                "<strong>Fecha fin actividad:</strong> " + endDate +"<br/><strong>Hora inicio actividad:</strong> "+ startHour +"<br/>" +
                                "<strong>Hora fin actividad:</strong> "+ endHour +"<br/></div></div>";
                        title = "ACTIVIDAD ROL EVALUACIÓN";
                    }else{
                        userR.setId(dto.getSupervisorId());
                        bodyMsg = "<br/><div class=\"row\"><div class=\"col-xs-12\"><strong>Fecha inicio actividad:</strong> " + startDate +"<br/>" +
                                "<strong>Fecha fin actividad:</strong> " + endDate +"<br/><strong>Hora inicio actividad:</strong> "+ startHour +"<br/>" +
                                "<strong>Hora fin actividad:</strong> "+ endHour +"<br/></div></div>";
                        title = "ACTIVIDAD ROL SUPERVISIÓN";
                    }

                }
                messageService.sendNotificationToUser(null, bodyMsg, user, userR, title, null);
            }catch(Exception ex){
                logException.Write(ex, this.getClass(), "doUpsertDelete", user.getUsername());
            }
        }

        rolActivityRepository.flush();
        return true;
    }

    @Override
    public void getLstActivities(RequestActivities req, String statusNotIn, ResponseRolActivities response) {

        if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER)){
            List<RolActivityResponse> lstAllActivities = rolActivityRepository.getAllActivitiesEvaluator(statusNotIn,
                    (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
            response.setLstRolActivities(lstAllActivities);

            List<SelectList> lstSupervisor = rolActivityRepository.getAllValidEvaluators(statusNotIn,
                    (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
            response.setLstSupervisor(lstSupervisor);
            response.setHasError(false);
        }
        else {
            List<RolActivityResponse> lstAllActivities = rolActivityRepository.getAllActivities(statusNotIn,
                    (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
            response.setLstRolActivities(lstAllActivities);

            List<SelectList> lstSupervisor = rolActivityRepository.getAllValidSupervisors(statusNotIn,
                    (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
            response.setLstSupervisor(lstSupervisor);
            response.setHasError(false);
        }
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

    @Autowired
    SharedUserService sharedUserService;

    private void create(RolActivityDto dto, RolActivityRepository rolActivityRepository, User user, RolActivityRequest fullModel) {

        RolActivity rolActivity = new RolActivity();
        rolActivity.setUserCreate(user);
        rolActivity.setCreationTime(fullModel.getNow());

        //
        if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER)){
            List<EvaluationActivity> lstAct= new ArrayList<>();

            for(SelectList curr : dto.getActivities()){
                if(curr.getIsSelected()==true){
                    EvaluationActivity act = evaluationActivityRepository.findOne(curr.getId());
                    lstAct.add(act);
                }
            }
            rolActivity.setActivities(lstAct);
        }

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

        if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER)){
            rolActivity.setPlace(dto.getPlace());
        }

        DtoToModelAndSave(dto, rolActivityRepository, user, fullModel, rolActivity, false);
        fullModel.addActsUpd();
    }


    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    private void DtoToModelAndSave(RolActivityDto dto, RolActivityRepository rolActivityRepository, User user, RolActivityRequest fullModel,
                                   RolActivity rolActivity, boolean isNew) {

        if(sharedUserService.isUserInRole(sharedUserService.GetLoggedUserId(), Constants.ROLE_EVALUATION_MANAGER)){
            User evaluator = new User();
            evaluator.setId(dto.getEvaluatorId());
            rolActivity.setEvaluator(evaluator);
            rolActivity.setPlace(dto.getPlace());
        }
        else {
            User supervisor = new User();
            supervisor.setId(dto.getSupervisorId());
            rolActivity.setSupervisor(supervisor);
        }

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
