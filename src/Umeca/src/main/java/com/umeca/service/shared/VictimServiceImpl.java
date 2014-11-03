package com.umeca.service.shared;

import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.StatusMeetingRepository;
import com.umeca.repository.reviewer.ImputedRepository;
import com.umeca.repository.supervisor.FolderConditionalReprieveRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.CaseService;
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

@Service("victimService")
public class VictimServiceImpl implements CaseService {

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

            caseDet.setIdFolder("SIN EVALUACI�N REGISTRADA");
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
    public boolean isValidCase(Long caseId) {
        return caseRepository.existsCaseNotClosed(caseId, Constants.CASE_STATUS_CLOSED) == 1;
    }

}
