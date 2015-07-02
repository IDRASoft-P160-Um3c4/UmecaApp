package com.umeca.service.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.Message;

import java.util.List;

public interface MessageService {

    Message sendNotificationToRole(final Long caseId, String body, User userSender, List<String> lstRoles, String title, String footer);
    Message sendNotificationToRole(final Long caseId, String body, Long userSenderId, List<String> lstRoles, String title, String footer);
    Message sendNotificationToRole(final Long caseId, String body, List<String> lstRoles, String title, String footer);
    Message sendNotificationToRole(final Long caseId, String body, List<String> lstRoles);
    Message sendNotificationToRole(final Long caseId, String body, List<String> lstRoles, String title);
    Message sendNotificationToRole(Long caseId, String body, final String role);
    Message sendNotificationToRole(Long caseId, String body, final String role, String title);

    Message sendNotificationToUser(Long caseId, String body,  User userSender,  final User userReceiver, String title, String footer);
    Message sendNotificationToUser(Long caseId, String body,  final Long userSenderId,  final Long userReceiverId);
    Message sendNotificationToUser(Long caseId, String body,  final Long userReceiverId);

}
