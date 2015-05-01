package com.umeca.service.shared;

import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    SharedLogExceptionService logException;


    //El caso puede ser nulo, así como el título, footer
    public Message sendNotificationToRole(final Long caseId, String body, User userSender,
                                       List<String> lstRoles, String title, String footer){

        List<Long> lstUserIdReceivers = sharedUserService.getLstValidUsersIdByLstRoles(lstRoles);
        return sendNotification(caseId, body, userSender, title, footer, lstUserIdReceivers);
    }

    private Message sendNotification(final Long caseId, String body, User userSender, String title, String footer, List<Long> lstUserIdReceivers) {
        final Message msg = new Message();

        try {

            if(caseId != null){
                msg.setCaseDetention(new Case() {{
                    setId(caseId);
                }});
            }

            if(userSender == null || userSender.getId() == null){
                userSender = new User() {{setId(sharedUserService.GetLoggedUserId());}};
            }

            msg.setBody(body);
            msg.setSender(userSender);
            msg.setCreationDate(Calendar.getInstance());
            msg.setFooter(footer);
            msg.setTitle(title);
            msg.setIsObsolete(false);

            List<RelMessageUserReceiver> lstRmUr = new ArrayList<>();

            for(final Long userRcvId : lstUserIdReceivers){
                lstRmUr.add(new RelMessageUserReceiver(){{
                    setMessage(msg);
                    setIsObsolete(false);
                    setUser(new User(){{setId(userRcvId);}});
                }});
            }

            msg.setMessageUserReceivers(lstRmUr);
            messageRepository.save(msg);
            messageRepository.flush();
        return msg;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "sendNotification", sharedUserService);
            return null;
        }
    }

    @Override
    public Message sendNotificationToRole(Long caseId, String body, final Long userSenderId, List<String> lstRoles, String title, String footer) {
        return sendNotificationToRole(caseId, body, new User(){{setId(userSenderId);}}, lstRoles, title, footer);
    }

    @Override
    public Message sendNotificationToRole(Long caseId, String body, List<String> lstRoles, String title, String footer) {
        return sendNotificationToRole(caseId, body, (User)null, lstRoles, title, footer);
    }

    @Override
    public Message sendNotificationToRole(Long caseId, String body, List<String> lstRoles) {
        return sendNotificationToRole(caseId, body, lstRoles, null, null);
    }

    @Override
    public Message sendNotificationToRole(Long caseId, String body, List<String> lstRoles, String title) {
        return sendNotificationToRole(caseId, body, lstRoles, title, null);
    }

    @Override
    public Message sendNotificationToRole(Long caseId, String body, final String role) {
        return sendNotificationToRole(caseId, body, (Long)null, new ArrayList<String>(){{add(role);}}, null, null);
    }

    @Override
    public Message sendNotificationToRole(Long caseId, String body, final String role, String title) {
        return sendNotificationToRole(caseId, body, (Long)null, new ArrayList<String>(){{add(role);}}, title, null);
    }

    @Override
    public Message sendNotificationToUser(Long caseId, String body,  User userSender,  final User userReceiver, String title, String footer){
        return sendNotification(caseId, body, userSender, title, footer, new ArrayList<Long>() {{
            add(userReceiver.getId());
        }});
    }

    @Override
    public Message sendNotificationToUser(Long caseId, String body,  final Long userSenderId,  final Long userReceiverId){
        return sendNotification(caseId, body, new User(){{setId(userSenderId);}}, null, null, new ArrayList<Long>() {{
            add(userReceiverId);
        }});
    }

    @Override
    public Message sendNotificationToUser(Long caseId, String body,  final Long userReceiverId){
        return sendNotification(caseId, body, null, null, null, new ArrayList<Long>() {{
            add(userReceiverId);
        }});
    }

}
