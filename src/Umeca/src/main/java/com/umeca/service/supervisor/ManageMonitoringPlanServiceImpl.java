package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.FulfillmentReportType;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.entities.shared.LogCommentJson;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlan;
import com.umeca.model.entities.supervisor.FulfillmentReport;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanJson;
import com.umeca.model.entities.supervisorManager.AuthRejMonActivitiesRequest;
import com.umeca.model.entities.supervisorManager.AuthRejMonActivitiesResponse;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.OptionListSimple;
import com.umeca.repository.catalog.FulfillmentReportTypeRepository;
import com.umeca.repository.catalog.RequestTypeRepository;
import com.umeca.repository.catalog.ResponseTypeRepository;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.FulfillmentReportRepository;
import com.umeca.repository.supervisor.LogChangeDataRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.CaseRequestService;
import com.umeca.service.shared.SharedLogCommentService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;

@Service
public class ManageMonitoringPlanServiceImpl implements ManageMonitoringPlanService{

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    MonitoringPlanRepository monPlanRepository;

    @Autowired
    CaseRequestRepository caseRequestRepository;

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Autowired
    ResponseTypeRepository responseTypeRepository;

    @Autowired
    ActivityMonitoringPlanRepository actMonPlanRepository;

    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    LogCommentRepository logCommentRepository;

    @Override
    @Transactional
    public boolean preAuthorize(SharedUserService sharedUserService, Long monPlanId, User user, ResponseMessage message) {
        if(validatePreAuthorize(monPlanId, user.getId(), message) == false)
            return false;

        MonitoringPlan monPlan = monPlanRepository.findOne(monPlanId);
        MonitoringPlanJson jsonOld = MonitoringPlanJson.convertToJson(monPlan);
        monPlan.setStatus(MonitoringConstants.STATUS_PENDING_AUTHORIZATION);
        monPlan.setGenerator(user);
        monPlan.setGenerationTime(Calendar.getInstance());
        MonitoringPlanJson jsonNew = MonitoringPlanJson.convertToJson(monPlan);

        SharedLogCommentService.generateLogComment(MonitoringConstants.LOG_MSG_INFO_PENDING_AUTHORIZATION, user, monPlan.getCaseDetention(),
                monPlan.getStatus(), null, MonitoringConstants.TYPE_COMMENT_AUTHORIZED, logCommentRepository);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), monPlanId));
        monPlanRepository.save(monPlan);

        //Create caseRequest
        CaseRequestService.CreateCaseRequestToRole(requestTypeRepository, caseRequestRepository, messageRepository, sharedUserService,
                user, monPlan, "El usuario " + user.getUsername() + " solicita que se autorice el plan de seguimiento.",
                Constants.ROLE_SUPERVISOR_MANAGER, Constants.ST_REQUEST_MONPLAN_AUTH);
        //End caseRequest

        return true;
    }


    @Autowired
    LogCommentRepository logCommentMonPlanRepository;

    @Autowired
    FulfillmentReportRepository fulfillmentReportRepository;

    @Autowired
    FulfillmentReportTypeRepository fulfillmentReportTypeRepository;

    @Override
    public boolean requestAccomplishmentLog(Long monPlanId, Long fulfillmentReportId, User user, String sAction, String sComments, ResponseMessage message) {

        if(validatePreAccomplishmentLog(monPlanId, user.getId(), message) == false)
            return false;

        FulfillmentReportType fulfillmentReportType = fulfillmentReportTypeRepository.findOne(fulfillmentReportId);

        if(fulfillmentReportType == null){
            message.setMessage("No se encontr&oacute; el tipo de reporte de cumplimiento.");
            message.setHasError(true);
            return false;
        }

        MonitoringPlan monPlan = monPlanRepository.findOne(monPlanId);

        LogComment commentModel = new LogComment();
        Calendar now = Calendar.getInstance();
        commentModel.setComments(StringEscape.escapeText(sComments));
        commentModel.setAction(sAction);
        commentModel.setCaseDetention(monPlan.getCaseDetention());
        commentModel.setMonitoringPlan(monPlan);
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

        FulfillmentReport fulfillmentReport = new FulfillmentReport();
        fulfillmentReport.setCaseDetention(monPlan.getCaseDetention());
        fulfillmentReport.setFulfillmentReportType(fulfillmentReportType);
        fulfillmentReport.setMonitoringPlan(monPlan);
        fulfillmentReport.setTimestamp(now);
        fulfillmentReport.setUserRequest(user);
        fulfillmentReport.setStatus(MonitoringConstants.LOG_ACCOMPLISHMENT_PENDING);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), monPlanId));
        monPlanRepository.save(monPlan);
        logCommentMonPlanRepository.save(commentModel);
        fulfillmentReportRepository.save(fulfillmentReport);
        return true;
    }

    @Override
    @Transactional
    public boolean authRejLstMonAct(AuthRejMonActivitiesRequest model, final SharedUserService sharedUserService, User user, ResponseMessage response) {

        Long monPlanId = null;
        AuthRejMonActivitiesResponse authRejMonActRes = new AuthRejMonActivitiesResponse();
        String fullComment = null;
        for(OptionListSimple act : model.getLstAutRejActMon()){
            try{
                ActivityMonitoringPlan actMon = actMonPlanRepository.findOne(act.getId());

                if(actMon == null || actMon.getId() == null)
                    continue;

                String status = actMon.getStatus();
                if(MonitoringConstants.LST_STATUS_ACTIVITY_PRE_AUTH.contains(status) == false)
                    continue;

                if(monPlanId == null)
                    monPlanId = actMon.getMonitoringPlan().getId();

                switch (act.getValue()){
                    case 1: //Autorizado
                        authorizeActivity(act, actMon, authRejMonActRes);
                        break;
                    case 0: //Rechazado
                        rejectActivity(act, actMon, authRejMonActRes);
                        break;
                    default:
                        continue;
                }

                if(fullComment == null){
                    fullComment = act.getDescription();
                }
                else{
                    fullComment = fullComment  + ", " + act.getDescription();
                }

            }catch (Exception ex){
                logException.Write(ex, this.getClass(), "authRejLstMonActImpl", sharedUserService);
            }
        }

        monPlanRepository.flush();

        Long activitiesInPre = actMonPlanRepository.countActivitiesByInLstStatus(monPlanId, new ArrayList<String>()
            {{add(MonitoringConstants.STATUS_ACTIVITY_PRE_NEW); add(MonitoringConstants.STATUS_ACTIVITY_PRE_MODIFIED); add(MonitoringConstants.STATUS_ACTIVITY_PRE_DELETED);}});

        //Si ya no hay actividades se debe quitar el tiempo, para que no se suspenda en caso de pasar las n horas
        MonitoringPlan monitoringPlan = monPlanRepository.findOne(monPlanId);
        String message;
        if(activitiesInPre == null || activitiesInPre == 0){
            monitoringPlan.setPosAuthorizationChangeTime(null);
            message = ".<br/>Todas las actividades fueron autorizada(s) o rechazada(s).";
            //CaseRequest... Response
            CaseRequestService.CreateCaseResponseToUser(responseTypeRepository, caseRequestRepository, messageRepository,
                    sharedUserService, logException, user, monitoringPlan.getCaseDetention(),
                    "Todas las actividades fueron autorizada(s) y/o rechazada(s). Comentarios: " + StringEscape.escapeText(model.getComments()),
                    Constants.ST_REQUEST_UPDATE_MONPLAN_AUTH);

        }
        else{
            message = ".<br/>No todas las actividades fueron autorizada(s) o rechazada(s). Debe revisar si durante la autorización se insertaron, modificaron o eliminaron actividades.";
        }

        Case caseDet = monitoringPlan.getCaseDetention();
        LogChangeData logChangeData = new LogChangeData(ActivityMonitoringPlan.class.getName(), "NA", fullComment,
                sharedUserService.GetLoggedUsername(), monitoringPlan.getCaseDetention().getId(), model.getComments());
        logChangeDataRepository.save(logChangeData);

        SharedLogCommentService.generateLogComment(StringEscape.escapeText(model.getComments()) + "<br/>" + authRejMonActRes.getResult(), user, caseDet, MonitoringConstants.STATUS_AUTHORIZED, monitoringPlan.getSupervisor(),
                MonitoringConstants.TYPE_COMMENT_AUTHORIZED, logCommentRepository);

        response.setMessage(authRejMonActRes.getResult() + message);
        return true;
    }

    private void rejectActivity(OptionListSimple act, ActivityMonitoringPlan actMon, AuthRejMonActivitiesResponse authRejMonActRes) {
        switch (actMon.getStatus()){
            case MonitoringConstants.STATUS_ACTIVITY_PRE_NEW:
                actMon.setReplaced(null);
                actMon.setPreAuthorizeMode(null);
                actMon.setStatus(MonitoringConstants.STATUS_ACTIVITY_DELETED);
                actMon.setActMonPlanToReplace(null);
                actMonPlanRepository.save(actMon);
                act.setDescription(actMon.getId() + " no insertado");
                authRejMonActRes.incRejIns();
                break;

            case MonitoringConstants.STATUS_ACTIVITY_PRE_MODIFIED:
                ActivityMonitoringPlan actMonOld = actMon.getActMonPlanToReplace();
                if(actMonOld != null && actMonOld.getId() != null){
                    actMonOld.setPreAuthorizeMode(null);
                    actMonOld.setReplaced(null);
                    actMonOld.setStatus(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
                    actMonOld.setActMonPlanToReplace(null);
                    actMonPlanRepository.save(actMonOld);
                }
                actMon.setReplaced(null);
                actMon.setPreAuthorizeMode(null);
                actMon.setStatus(MonitoringConstants.STATUS_ACTIVITY_DELETED);
                actMon.setActMonPlanToReplace(null);
                actMon.setActMonPlanReplaced(actMonOld);
                actMonPlanRepository.save(actMon);
                act.setDescription(actMon.getId() + " no actualizado");
                authRejMonActRes.incRejUpd();
                break;

            case MonitoringConstants.STATUS_ACTIVITY_PRE_DELETED:
                actMon.setReplaced(null);
                actMon.setPreAuthorizeMode(null);
                actMon.setStatus(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
                actMon.setActMonPlanToReplace(null);
                actMonPlanRepository.save(actMon);
                act.setDescription(actMon.getId() + " no eliminado");
                authRejMonActRes.incRejDel();
                break;
        }

    }

    private void authorizeActivity(OptionListSimple act, ActivityMonitoringPlan actMon, AuthRejMonActivitiesResponse authRejMonActRes) {
        switch (actMon.getStatus()){
            case MonitoringConstants.STATUS_ACTIVITY_PRE_NEW:
                actMon.setReplaced(null);
                actMon.setPreAuthorizeMode(null);
                actMon.setStatus(MonitoringConstants.STATUS_ACTIVITY_NEW);
                actMon.setActMonPlanToReplace(null);
                actMonPlanRepository.save(actMon);
                act.setDescription(actMon.getId() + " insertado");
                authRejMonActRes.incAuthIns();
                break;

            case MonitoringConstants.STATUS_ACTIVITY_PRE_MODIFIED:
                ActivityMonitoringPlan actMonOld = actMon.getActMonPlanToReplace();
                if(actMonOld != null && actMonOld.getId() != null){
                    actMonOld.setPreAuthorizeMode(null);
                    actMonOld.setStatus(MonitoringConstants.STATUS_ACTIVITY_DELETED);
                    actMonOld.setActMonPlanToReplace(null);
                    actMonPlanRepository.save(actMonOld);
                }
                actMon.setReplaced(null);
                actMon.setPreAuthorizeMode(null);
                actMon.setStatus(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
                actMon.setActMonPlanToReplace(null);
                actMon.setActMonPlanReplaced(actMonOld);
                actMonPlanRepository.save(actMon);
                act.setDescription(actMon.getId() + " actualizado");
                authRejMonActRes.incAuthUpd();
                break;

            case MonitoringConstants.STATUS_ACTIVITY_PRE_DELETED:
                actMon.setReplaced(null);
                actMon.setPreAuthorizeMode(null);
                actMon.setStatus(MonitoringConstants.STATUS_ACTIVITY_DELETED);
                actMon.setActMonPlanToReplace(null);
                actMonPlanRepository.save(actMon);
                act.setDescription(actMon.getId() + " eliminado");
                authRejMonActRes.incAuthDel();
                break;
        }
    }

    private boolean validatePreAccomplishmentLog(Long monPlanId, Long userId, ResponseMessage message) {
        Long monPlanUserId = monPlanRepository.getIdByUserAndNotStatus(monPlanId, MonitoringConstants.STATUS_END, userId);

        if(monPlanUserId == null || monPlanUserId != monPlanId){
            message.setMessage("El plan de seguimiento está en estado \"TERMINADO\" o usted ya no es propietario del plan de seguimiento, por favor contacte a su coordinador");
            return false;
        }

        return HasActivitiesNotDeleted(monPlanId, message);
    }

    private boolean HasActivitiesNotDeleted(Long monPlanId, ResponseMessage message) {
        Long countValidActivities = actMonPlanRepository.countActivitiesByNotInLstStatus(monPlanId, new ArrayList<String>() {{
            add(MonitoringConstants.STATUS_ACTIVITY_DELETED);
        }});

        if(countValidActivities == 0){
            message.setMessage("Al menos debe existir una actividad válida en el plan de seguimiento");
            return false;
        }

        return true;
    }

    public boolean validatePreAuthorize(Long monPlanId, Long userId, ResponseMessage message) { //Id de MonitoringPlan

        Long monPlanUserId = monPlanRepository.getIdByUser(monPlanId, MonitoringConstants.STATUS_PENDING_CREATION, userId);

        if(monPlanUserId == null || monPlanUserId != monPlanId){
            message.setMessage("El plan de seguimiento no está en estado \"EN PROCESO DE GENERAR\" o usted ya no es propietario del plan de seguimiento, por favor contacte a su coordinador");
            return false;
        }

        return HasActivitiesNotDeleted(monPlanId, message);
    }

}
