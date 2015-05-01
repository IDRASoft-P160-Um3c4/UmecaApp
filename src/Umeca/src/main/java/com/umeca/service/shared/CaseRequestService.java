package com.umeca.service.shared;

import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.CaseRequest;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.shared.Constants;
import com.umeca.repository.catalog.RequestTypeRepository;
import com.umeca.repository.catalog.ResponseTypeRepository;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CaseRequestService {

    public static void CreateCaseRequestToRole(RequestTypeRepository requestTypeRepository, CaseRequestRepository caseRequestRepository, MessageRepository messageRepository,
                                               SharedUserService sharedUserService, User user, MonitoringPlan monPlan, String text, String role, String requestType) {
        CaseRequest caseRequest = new CaseRequest();
        final Message msg = new Message();
        msg.setCaseDetention(monPlan.getCaseDetention());
        msg.setCreationDate(Calendar.getInstance());
        msg.setSender(user);
        msg.setBody(StringEscape.escapeText(text));
        List<User> lstUserReceivers = sharedUserService.getLstValidUserIdsByRole(role);
        List<RelMessageUserReceiver> lstRmUr = new ArrayList<>();

        for(final User userRcv : lstUserReceivers){
            lstRmUr.add(new RelMessageUserReceiver(){{
                setMessage(msg);
                setUser(userRcv);
            }});
        }
        msg.setMessageUserReceivers(lstRmUr);
        messageRepository.save(msg);

        caseRequest.setRequestMessage(msg);
        caseRequest.setRequestType(requestTypeRepository.findByCode(requestType));
        caseRequestRepository.save(caseRequest);
    }

    public static void CreateCaseRequestToRole(RequestTypeRepository requestTypeRepository, CaseRequestRepository caseRequestRepository, MessageRepository messageRepository,
                                               SharedUserService sharedUserService, User user, Case caseDet, String text, String role, String requestType) {
        CaseRequest caseRequest = new CaseRequest();
        final Message msg = new Message();
        msg.setCaseDetention(caseDet);
        msg.setCreationDate(Calendar.getInstance());
        msg.setSender(user);
        msg.setBody(StringEscape.escapeText(text));
        List<User> lstUserReceivers = sharedUserService.getLstValidUserIdsByRole(role);
        List<RelMessageUserReceiver> lstRmUr = new ArrayList<>();

        for(final User userRcv : lstUserReceivers){
            lstRmUr.add(new RelMessageUserReceiver(){{
                setMessage(msg);
                setUser(userRcv);
            }});
        }
        msg.setMessageUserReceivers(lstRmUr);
        messageRepository.save(msg);

        caseRequest.setRequestMessage(msg);
        caseRequest.setRequestType(requestTypeRepository.findByCode(requestType));
        caseRequestRepository.save(caseRequest);
    }

    public static void CreateCaseResponseToUser(ResponseTypeRepository responseTypeRepository, CaseRequestRepository caseRequestRepository, MessageRepository messageRepository,
                                           SharedUserService sharedUserService, SharedLogExceptionService logException, User user,
                                           Case caseDetention, String text, String requestType){
        try{
            List<CaseRequest> lstCaseRequest = caseRequestRepository.findCaseRequestByCaseAndType(caseDetention.getId(), requestType, new PageRequest(0,1));

            if(lstCaseRequest == null && lstCaseRequest.size() <= 0){
                return;
            }

            final CaseRequest caseRequest = lstCaseRequest.get(0);

            final Message msg = new Message();
            msg.setCaseDetention(caseDetention);
            msg.setCreationDate(Calendar.getInstance());
            msg.setSender(user);
            msg.setBody(StringEscape.escapeText(text));

            List<RelMessageUserReceiver> lstRmUr = new ArrayList<>();

            lstRmUr.add(new RelMessageUserReceiver(){{
                setMessage(msg);
                setUser(caseRequest.getRequestMessage().getSender());
            }});

            msg.setMessageUserReceivers(lstRmUr);
            messageRepository.save(msg);

            caseRequest.setResponseMessage(msg);
            caseRequest.setResponseType(responseTypeRepository.findByCode(Constants.RESPONSE_TYPE_DRESSED));
            caseRequestRepository.save(caseRequest);


        }catch (Exception ex){
            logException.Write(ex, CaseRequest.class.getClass(), "CreateCaseResponseToUser", sharedUserService);
        }
    }

    public static void CreateCaseRequestByCase(RequestTypeRepository requestTypeRepository,
                                         CaseRequestRepository caseRequestRepository,
                                         SharedUserService sharedUserService,
                                         MessageRepository messageRepository,
                                         Case caseDetention, String text,
                                         String requestType,String statusBefore,String roleSender){

        CaseRequest caseRequest = new CaseRequest();
        caseRequest.setStateBefore(statusBefore);
        final Message msg = new Message();
        msg.setCaseDetention(caseDetention);
        msg.setCreationDate(Calendar.getInstance());
        Long userId = sharedUserService.GetLoggedUserId();
        User u = new User();
        u.setId(userId);
        msg.setSender(u);
        msg.setBody(StringEscape.escapeText(text));
        List<User> lstUserReceivers = sharedUserService.getLstValidUserIdsByRole(roleSender);
        List<RelMessageUserReceiver> lstRmUr = new ArrayList<>();
        for(final User userRcv : lstUserReceivers){
            lstRmUr.add(new RelMessageUserReceiver(){{
                setMessage(msg);
                setUser(userRcv);
            }});
        }
        msg.setMessageUserReceivers(lstRmUr);
        messageRepository.save(msg);

        caseRequest.setRequestMessage(msg);
        caseRequest.setRequestType(requestTypeRepository.findByCode(requestType));
        caseRequestRepository.save(caseRequest);
    }

    public static void ResponseRequestCase(CaseRequestRepository caseRequestRepository,SharedUserService sharedUserService,
                                           MessageRepository messageRepository, RequestTypeRepository requestTypeRepository,
                                           Case caseDetention, String text, String requestType, String typeResponse){
        List<CaseRequest> lstCaseRequest = caseRequestRepository.findCaseRequestByCaseAndType(caseDetention.getId(), requestType, new PageRequest(0,1));
        if(lstCaseRequest == null && !(lstCaseRequest.size() > 0)){
            return;
        }
        final CaseRequest caseRequest = lstCaseRequest.get(0);
        final Message msg = new Message();
        msg.setCaseDetention(caseDetention);
        msg.setCreationDate(Calendar.getInstance());
        Long userId = sharedUserService.GetLoggedUserId();
        User u = new User();
        u.setId(userId);
        msg.setSender(u);
        msg.setBody(StringEscape.escapeText(text));
        List<RelMessageUserReceiver> lstRmUr = new ArrayList<>();

        lstRmUr.add(new RelMessageUserReceiver(){{
            setMessage(msg);
            setUser(caseRequest.getRequestMessage().getSender());
        }});

        msg.setMessageUserReceivers(lstRmUr);
        messageRepository.save(msg);

        caseRequest.setRequestMessage(msg);
        caseRequest.setRequestType(requestTypeRepository.findByCode(typeResponse));
        caseRequestRepository.save(caseRequest);
    }


}
