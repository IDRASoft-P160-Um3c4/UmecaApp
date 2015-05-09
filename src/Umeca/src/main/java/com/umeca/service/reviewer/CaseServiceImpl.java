package com.umeca.service.reviewer;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.CloseCause;
import com.umeca.model.catalog.RequestType;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.RequestTypeRepository;
import com.umeca.repository.catalog.ResponseTypeRepository;
import com.umeca.repository.catalog.StatusMeetingRepository;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.reviewer.ImputedRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.CaseRequestService;
import com.umeca.service.shared.SharedLogCommentService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Vmware on 06/06/2014.
 */

@Service("caseService")
public class CaseServiceImpl implements CaseService {

    @Autowired
    ImputedRepository imputedRepository;

    @Autowired
    StatusMeetingRepository statusMeetingRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    StatusCaseRepository statusCaseRepository;
    private Long idCase;
    private String statusCase;

    @Autowired
    FolderConditionalReprieveRepository folderConditionalReprieveRepository;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;


    @Override
    public Case generateNewCase(Imputed imputed, Integer type) {

        Case caseDet = new Case();

        if (imputedRepository.findImputedRegister(imputed.getName(), imputed.getLastNameP(), imputed.getLastNameM(), imputed.getBirthDate()).size() > 0)
            caseDet.setRecidivist(true);
        else
            caseDet.setRecidivist(false);

        Meeting meeting = new Meeting();
        StatusMeeting statusMeeting = statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE);
        meeting.setStatus(statusMeeting);
        meeting.setDateCreate(new Date());
        imputed.setMeeting(meeting);
        imputed.setFoneticString(imputed.getName().trim().toLowerCase() + imputed.getLastNameP().trim().toLowerCase() + imputed.getLastNameM().trim().toLowerCase());
        meeting.setImputed(imputed);
        ImputedInitial imputedInitial = imputed.cloneObj();
        meeting.setImputedInitial(imputedInitial);
        meeting.setCaseDetention(caseDet);
        meeting.setMeetingType(type);
        caseDet.setMeeting(meeting);
        caseDet.setDateCreate(new Date());
        caseDet.setCreatorUser(userRepository.findOne(sharedUserService.GetLoggedUserId()));

        return caseDet;
    }

    @Override
    @Transactional
    public Case save(Case caseDet) {

        try {
            caseDet = caseRepository.save(caseDet);
            caseRepository.flush();
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "save", sharedUserService);
            System.out.println("Error al guardar el caso!!");
            System.out.println(e.getMessage());
        }

        return caseDet;
    }

    @Override
    @Transactional
    public ResponseMessage saveConditionaReprieveCase(Case caseDet) {

        ResponseMessage resp = new ResponseMessage();

        caseDet.setIdFolder("SIN EVALUACIÓN REGISTRADA");
        caseRepository.save(caseDet);

        resp.setHasError(false);
        resp.setMessage("Se ha guardado el caso con exito.");

        return resp;
    }

    @Override
    public Boolean validateStatus(Long idCase, String statusCase) {
        return validateStatus(idCase, statusCase, Case.class, "");
    }


    @Override
    public Boolean validateStatus(Long idCase, String statusCase, Class entityClass, String statusEntity) {
        Case c = caseRepository.findOne(idCase);
        if (c == null) {
            return false;
        }
        if (!c.getStatus().getName().equals(statusCase)) {
            return false;
        }
        if (entityClass == null && statusEntity == null) {
            return true;
        } else {
            Field[] fields = c.getClass().getDeclaredFields();
            String entityName = "";
            for (Field field : fields) {
                if (entityClass == field.getType()) {
                    entityName = field.getName();
                    break;
                }
            }
            try {
                for (PropertyDescriptor pd : Introspector.getBeanInfo(Case.class).getPropertyDescriptors()) {
                    if (pd.getReadMethod() != null && pd.getName().equals(entityName)) {
                        Object entity = pd.getReadMethod().invoke(c);
                        for (PropertyDescriptor pdEntity : Introspector.getBeanInfo(entity.getClass()).getPropertyDescriptors()) {
                            if (pdEntity.getReadMethod() != null && pdEntity.getName().equals("status")) {
                                Object status = pdEntity.getReadMethod().invoke(entity);
                                for (PropertyDescriptor pdStatus : Introspector.getBeanInfo(status.getClass()).getPropertyDescriptors()) {
                                    if (pdStatus.getReadMethod() != null && pdStatus.getName().equals("name")) {
                                        String statusCode = (String) pdStatus.getReadMethod().invoke(status);
                                        if (statusCode.equals(statusEntity)) {
                                            return true;
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                logException.Write(e, this.getClass(), "validateStatus", sharedUserService);
            } catch (IntrospectionException e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                logException.Write(e, this.getClass(), "validateStatus", sharedUserService);
            } catch (InvocationTargetException e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                logException.Write(e, this.getClass(), "validateStatus", sharedUserService);
            }
            return false;
        }
    }

    @Override
    public List<Case> findByIdFolder(String idFolder) {
        return caseRepository.findByIdFolder(idFolder);
    }

    @Autowired
    LogCommentRepository logCommentRepository;

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Autowired
    SupervisionCloseCaseLogRepository supervisionCloseCaseLogRepository;

    @Autowired
    CloseCauseRepository closeCauseRepository;

    @Override
    @Transactional
    public void saveAuthRejectCloseCase(AuthorizeRejectMonPlan model, User user, Case caseDet) {
        String statusAction;
        StatusCase statusCase = null;
        User supervisor = new User();
        List<Long> lstUserIds = hearingFormatRepository.findLastSupervisorIdByCaseId(caseDet.getId(), new PageRequest(0, 1));
        if (lstUserIds != null && lstUserIds.size() > 0)
            supervisor.setId(lstUserIds.get(0));
        else if (caseDet.getCloserUser() != null)
            supervisor = caseDet.getCloserUser();

        if (model.getAuthorized() == 1) {
            statusAction = MonitoringConstants.STATUS_AUTHORIZED;
            statusCase = statusCaseRepository.findByCode(Constants.CASE_STATUS_CLOSED);
            caseDet.setCloseDate(new Date());
            supervisionCloseCaseLogRepository.save(generateCloseLog(caseDet));
        } else {
            caseDet.setCloseCause(null);
            caseDet.setCloseDate(null);
            caseDet.setCloserUser(null);
            statusAction = MonitoringConstants.STATUS_REJECTED_AUTHORIZED;
            statusCase = statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END);
        }

        caseDet.setStatus(statusCase);

        CaseRequestService.CreateCaseResponseToUser(responseTypeRepository, caseRequestRepository, messageRepository,
                sharedUserService, logException, user, caseDet,
                "El cierre del caso fue " + (model.getAuthorized() == 1 ? "autorizado" : "rechazado") + ". Comentarios: " + StringEscape.escapeText(model.getComments()),
                Constants.ST_REQUEST_CLOSE_CASE);

        CloseCause cause = caseDet.getCloseCause();
        if (cause != null)
            SharedLogCommentService.generateLogComment("Causa: " + cause.getName() + "; " + model.getComments(), user, caseDet, statusAction, supervisor,
                    MonitoringConstants.TYPE_COMMENT_CASE_END, logCommentRepository);
        else
            SharedLogCommentService.generateLogComment(model.getComments(), user, caseDet, statusAction, supervisor,
                    MonitoringConstants.TYPE_COMMENT_CASE_END, logCommentRepository);

        caseRepository.save(caseDet);
    }

    @Override
    public SupervisionCloseCaseLog generateCloseLog(Case caseDetention) {
        SupervisionCloseCaseLog closeLog = new SupervisionCloseCaseLog();
        closeLog.setCloseDate(CalendarExt.getToday());
        closeLog.setCloseAuthorizer(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        closeLog.setCaseDetention(caseDetention);
        return closeLog;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValidCase(Long caseId) {
        return caseRepository.existsCaseNotClosed(caseId, Constants.CASE_STATUS_CLOSED) == 1;
    }

    @Override
    @Transactional
    public void doClosePrisonCase(Case caseDet, AuthorizeRejectMonPlan model) {
        caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_CLOSED));
        caseDet.setCloseCause(closeCauseRepository.findByCode(Constants.CLOSE_CAUSE_PROMISE_PRISION));
        caseDet.setCloseDate(new Date());
        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        caseDet.setCloserUser(u);
        caseDet.setDatePrison(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("Cierre de caso por prisión preventiva / promesa del imputado : ");
        sb.append(caseDet.getIdFolder());
        sb.append(". Comentario: ");
        sb.append(model.getComments());
        sb.append(".");

        Long idLastFormat = hearingFormatRepository.lastHearingFormatIdsByIdCase(caseDet.getId());
        HearingFormat lastFormat = hearingFormatRepository.findOne(idLastFormat);

        supervisionCloseCaseLogRepository.save(generateCloseLog(caseDet));

        caseRepository.save(caseDet);
        SharedLogCommentService.generateLogComment(sb.toString(), u, caseDet, MonitoringConstants.STATUS_END, lastFormat.getSupervisor(), MonitoringConstants.TYPE_COMMENT_CASE_END, logCommentRepository);
    }

    @Transactional
    public ResponseMessage doReopenCase(AuthorizeRejectMonPlan model) {
        ResponseMessage response = new ResponseMessage();
        try {

            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            if (sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false) {
                response.setHasError(true);
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            Case caseDet = caseRepository.findOne(model.getCaseId());

            if (caseDet == null) {
                response.setHasError(true);
                response.setMessage("No se encontró el caso. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }
            SupervisionCloseCaseLog lastLog;
            List<SupervisionCloseCaseLog> lstLog = supervisionCloseCaseLogRepository.findLastCloseLog(caseDet.getId(), new PageRequest(0, 1));

            if (lstLog != null & lstLog.size() > 0)
                lastLog = lstLog.get(0);
            else {
                response.setHasError(true);
                response.setMessage("No se puede reabrir el caso, no existe registro de que haya sido cerrado.");
                return response;
            }

            lastLog.setReopenAuthorizer(userRepository.findOne(sharedUserService.GetLoggedUserId()));
            lastLog.setReopenDate(CalendarExt.getToday());
            supervisionCloseCaseLogRepository.save(lastLog);

            MonitoringPlan existMonitoringPlan = caseDet.getMonitoringPlan();
            if (existMonitoringPlan != null) {
                existMonitoringPlan.setStatus(MonitoringConstants.STATUS_PENDING_CREATION);
                caseDet.setMonitoringPlan(existMonitoringPlan);
            }

            caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END));
            caseDet.setCloseDate(null);
            caseDet.setReopenDate(new Date());

            List<Long> lstUserIds = hearingFormatRepository.findLastSupervisorIdByCaseId(caseDet.getId(), new PageRequest(0, 1));
            User supervisor = new User();
            if (lstUserIds != null && lstUserIds.size() > 0)
                supervisor.setId(lstUserIds.get(0));
            else
                supervisor = caseDet.getCloserUser();

            SharedLogCommentService.generateLogComment(model.getComments(), userRepository.findOne(sharedUserService.GetLoggedUserId()),
                    caseDet, MonitoringConstants.STATUS_AUTHORIZED, supervisor, MonitoringConstants.TYPE_COMMENT_REOPEN_CASE, logCommentRepository);

            caseRepository.save(caseDet);

            response.setHasError(false);
            response.setMessage("El caso ha sido reabierto, debe registrar un formato de audiencia.");
        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "doReopenCase", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente m&aacute;as tarde.");
        } finally {
            return response;
        }
    }

    @Autowired
    CaseRequestRepository caseRequestRepository;
    @Autowired
    RequestTypeRepository requestTypeRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ResponseTypeRepository responseTypeRepository;

    @Transactional
    @Override
    public void saveAuthRejectObsoleteCase(AuthorizeRejectMonPlan model, User user, Case caseDet) {
        List<CaseRequest> lstCaseRequest = caseRequestRepository.findCaseRequestByCaseAndType(caseDet.getId(), Constants.ST_REQUEST_CASE_OBSOLETE_SUPERVISION, new PageRequest(0, 1));
        String typeRequest, commentNotification;
        CaseRequest request = lstCaseRequest.get(0);
        if (model.getAuthorized() == 1) {

            caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_CLOSED));
            caseDet.setCloseCause(closeCauseRepository.findByCode(Constants.CLOSE_CAUSE_OBSOLETE_SUPERVISION));
            caseDet.setCloseDate(new Date());
            caseDet.setDateObsolete(new Date());
            User u = new User();
            u.setId(sharedUserService.GetLoggedUserId());
            caseDet.setCloserUser(u);

            typeRequest = Constants.RESPONSE_TYPE_ACCEPTED;
            commentNotification = "Solicitud aceptada. Comentarios: " + model.getComments();
        } else {
            caseDet.setStatus(statusCaseRepository.findByCode(request.getStateBefore()));
            typeRequest = Constants.RESPONSE_TYPE_REJECTED;
            commentNotification = "Solicitud rechazada. Comentarios: " + model.getComments();
        }

        caseRepository.save(caseDet);
        if (lstCaseRequest == null && !(lstCaseRequest.size() > 0)) {
            return;
        }
        Message msg = new Message();
        msg.setCaseDetention(caseDet);
        msg.setCreationDate(Calendar.getInstance());
        Long userId = sharedUserService.GetLoggedUserId();
        User u = new User();
        u.setId(userId);
        msg.setSender(u);
        msg.setBody(StringEscape.escapeText(model.getComments()));
        msg.setIsObsolete(false);
        msg.setTitle("");
        List<RelMessageUserReceiver> lstRmUr = new ArrayList<>();
        RelMessageUserReceiver rmur = new RelMessageUserReceiver();
        rmur.setMessage(msg);
        rmur.setUser(request.getRequestMessage().getSender());
        rmur.setIsObsolete(false);
        msg.setMessageUserReceivers(lstRmUr);
        messageRepository.save(msg);
        request.setResponseMessage(msg);
        request.setResponseType(responseTypeRepository.findByCode(typeRequest));
        caseRequestRepository.save(request);
        SharedLogCommentService.generateLogComment(commentNotification, userRepository.findOne(sharedUserService.GetLoggedUserId()), caseDet,
                Constants.RESPONSE_OBSOLETE_CASE_SUPERVISION, request.getRequestMessage().getSender(), Constants.TYPE_COMMENT_OBSOLETE_CASE_SUPERVISION, logCommentRepository);

    }

    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    @Override
    @Transactional
    public void saveRequestCloseCase(AuthorizeRejectMonPlan model, User user) {

        LogComment commentModel = new LogComment();
        Calendar now = Calendar.getInstance();

        String msg = "";

        Case existCase = caseRepository.findOne(model.getCaseId());
        existCase.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_CLOSE_REQUEST));

        if (model.getIdCloseCause() != null) {
            CloseCause closeCause = closeCauseRepository.findOne(model.getIdCloseCause());
            msg += "Motivo: " + closeCause.getName() + " - ";
            existCase.setCloseCause(closeCause);
            existCase.setCloserUser(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }

        caseRepository.save(existCase);

        commentModel.setComments(msg + StringEscape.escapeText(model.getComments()));
        commentModel.setAction(MonitoringConstants.STATUS_PENDING_END);
        commentModel.setCaseDetention(existCase);
        commentModel.setSenderUser(user);
        commentModel.setTimestamp(now);
        commentModel.setType(MonitoringConstants.TYPE_COMMENT_CASE_END);

        logCommentRepository.save(commentModel);

        MonitoringPlan existMonP = existCase.getMonitoringPlan();

        if (existMonP != null) {
            MonitoringPlanJson jsonOld = MonitoringPlanJson.convertToJson(existMonP);
            existMonP.setStatus(MonitoringConstants.STATUS_PENDING_END);
            MonitoringPlanJson jsonNew = MonitoringPlanJson.convertToJson(existMonP);
            logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), existMonP.getId()));
            monitoringPlanRepository.save(existMonP);
        }

        //Create caseRequest
        CaseRequestService.CreateCaseRequestToRole(requestTypeRepository, caseRequestRepository, messageRepository, sharedUserService,
                user, existCase, "El usuario " + user.getUsername() + " solicita que se autorice el cierre del caso.",
                Constants.ROLE_SUPERVISOR_MANAGER, Constants.ST_REQUEST_CLOSE_CASE);
        //End caseRequest
    }

}
