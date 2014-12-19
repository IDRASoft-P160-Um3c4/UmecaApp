package com.umeca.service.reviewer;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.CaseRequest;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.SupervisionCloseCaseLog;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ResponseTypeRepository;
import com.umeca.repository.catalog.StatusMeetingRepository;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.reviewer.ImputedRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.repository.supervisor.FolderConditionalReprieveRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.repository.supervisor.SupervisionCloseCaseLogRepository;
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
        meeting.setCaseDetention(caseDet);
        meeting.setMeetingType(type);
        caseDet.setMeeting(meeting);
        caseDet.setDateCreate(new Date());
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

        try {
            //FolderConditionalReprieve folderObj = new FolderConditionalReprieve();

            //folderObj = folderConditionalReprieveRepository.save(folderObj);

            //StringBuilder sb = new StringBuilder();

            //sb.append(HearingFormatConstants.FOLDER_CONDITIONAL_REPRIEVE_PREFIX);
            //sb.append(folderObj.getId());

            //caseDet.setIdFolder(sb.toString());
            //caseDet.setFolderConditionalReprieve(folderObj);
            //folderObj.setCaseDetention(caseDet);

            caseDet.setIdFolder("SIN EVALUACIÓN REGISTRADA");
            caseRepository.save(caseDet);

            resp.setHasError(false);
            resp.setMessage("Se ha guardado el caso con exito.");

        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveConditionaReprieveCase", sharedUserService);
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error en el servidor, intente mas tarde");
        }

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

    @Override
    @Transactional
    public void saveAuthRejectCloseCase(AuthorizeRejectMonPlan model, User user, Case caseDet) {
        String statusAction;
        StatusCase statusCase;

        if (model.getAuthorized() == 1) {
            statusAction = MonitoringConstants.STATUS_AUTHORIZED;
            statusCase = statusCaseRepository.findByCode(Constants.CASE_STATUS_CLOSED);
        } else {
            statusAction = MonitoringConstants.STATUS_REJECTED_AUTHORIZED;
            statusCase = statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END);
        }

        List<Long> lstUserIds = hearingFormatRepository.findLastSupervisorIdByCaseId(caseDet.getId(), new PageRequest(0, 1));
        User supervisor = new User();
        supervisor.setId(lstUserIds.get(0));

        caseDet.setStatus(statusCase);
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
        caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_PRISON_CLOSED));
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
        SharedLogCommentService.generateLogComment(sb.toString(), userRepository.findOne(sharedUserService.GetLoggedUserId()),
                caseDet, MonitoringConstants.STATUS_END, lastFormat.getSupervisor(), MonitoringConstants.TYPE_COMMENT_CASE_END, logCommentRepository);
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

            List<Long> lstUserIds = hearingFormatRepository.findLastSupervisorIdByCaseId(caseDet.getId(), new PageRequest(0, 1));
            User supervisor = new User();
            supervisor.setId(lstUserIds.get(0));

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
    ResponseTypeRepository responseTypeRepository;
    @Autowired
    MessageRepository messageRepository;
    @Transactional
    @Override
    public void saveAuthRejectObsoleteCase(AuthorizeRejectMonPlan model, User user, Case caseDet) {
        List<CaseRequest> lstCaseRequest = caseRequestRepository.findCaseRequestByCaseAndType(caseDet.getId(), Constants.ST_REQUEST_CASE_OBSOLETE_SUPERVISION, new PageRequest(0,1));
        String typeRequest, status, commentNotification;
        CaseRequest request = lstCaseRequest.get(0);
        if(model.getAuthorized()== 1){
            status = Constants.CASE_STATUS_OBSOLETE_SUPERVISION;
            typeRequest = Constants.RESPONSE_TYPE_ACCEPTED;
            commentNotification ="Solicitud aceptada. Comentarios: "+model.getComments();
        }else{
            status = request.getStateBefore();
            typeRequest = Constants.RESPONSE_TYPE_REJECTED;
            commentNotification = "Solicitud rechazada. Comentarios: "+model.getComments();
        }
        caseDet.setStatus(statusCaseRepository.findByCode(status));
        CaseRequestService.CreateCaseResponseToUser(responseTypeRepository,caseRequestRepository,messageRepository, sharedUserService,logException,user,caseDet,model.getComments(),typeRequest);
        SharedLogCommentService.generateLogComment(commentNotification, userRepository.findOne(sharedUserService.GetLoggedUserId()),caseDet,
                Constants.RESPONSE_OBSOLETE_CASE_SUPERVISION, request.getRequestMessage().getSender(), Constants.TYPE_COMMENT_OBSOLETE_CASE_SUPERVISION , logCommentRepository);
        caseRepository.save(caseDet);
    }

}
