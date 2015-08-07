package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.agenda.ActivityAgendaNotice;
import com.umeca.model.entities.reviewer.LogNotification;
import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
import com.umeca.model.entities.shared.CommentRequest;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.CommentMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ConstantsLogCase;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.repository.shared.RelMessageUserReceiverRepository;
import com.umeca.repository.supervisor.ActivityAgendaRepository;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.LogNotificationRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    LogCommentRepository logCommentRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    LogNotificationRepository logNotificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SystemSettingService systemSettingService;

    @Autowired
    RelMessageUserReceiverRepository relMessageUserReceiverRepository;

    @Override
    public ModelAndView generatePage(HttpServletRequest request, String sRole, ModelAndView model, Long userId) {

        systemSettingService.initSystemSettings();

        switch (sRole) {
            case Constants.ROLE_SUPERVISOR:
                constructSupervisorMainPage(model, userId);
                return model;

            case Constants.ROLE_SUPERVISOR_MANAGER:
                constructSupervisorManagerMainPage(model, userId);
                return model;

            case Constants.ROLE_REVIEWER:
                constructReviewerMainPage(model);
                return model;

            case Constants.ROLE_EVALUATION_MANAGER:
                constructEvaluationManagerPage(request, model);
                return model;

            case Constants.ROLE_HUMAN_RESOURCES:
                constructHumanResourcesPage(model);
                return model;

            case Constants.ROLE_DIRECTOR:
                constructDirectorMainPage(model, userId);
                return model;

            case Constants.ROLE_CHANNELING_MANAGER:
                constructChannelingManagerMainPage(model);
                return model;

            default:
                return model;
        }
    }


    @Override
    public boolean deleteComment(String sRole, CommentRequest model, User user, ResponseMessage response) {
        switch (sRole) {
            case Constants.ROLE_SUPERVISOR:
                if (validateSupervisorComment(model, user, response) == false)
                    return false;

                doDeleteComment(model, user);

                return true;
            case Constants.ROLE_SUPERVISOR_MANAGER:
                if (validateManagerSupervisorComment(model, user, response) == false)
                    return false;

                doDeleteComment(model, user);

                return true;
            default:
                response.setMessage("Usted no tiene los permisos suficientes para realizar esta operación");
                return false;
        }
    }


    private void doDeleteComment(CommentRequest model, User user) {
        LogComment logComment = model.getLogComment();
        logComment.setObsoleteUser(user);
        logComment.setTimestampObsolete(Calendar.getInstance());
        logComment.setObsolete(true);
        logCommentRepository.save(logComment);
    }

    private boolean validateManagerSupervisorComment(CommentRequest model, User user, ResponseMessage response) {
        LogComment logComment = logCommentRepository.getCommentByCommentIdAndNotSenderUserId(model.getId(), user.getId());

        if (logComment == null) {
            response.setMessage("Usted ya no es el propietario de este mensaje. Por favor contacte a su coordinador de supervisores");
            return false;
        }

        model.setLogComment(logComment);
        return true;
    }

    private boolean validateSupervisorComment(CommentRequest model, User user, ResponseMessage response) {
        LogComment logComment = logCommentRepository.getCommentByCommentIdAndUserId(model.getId(), user.getId());

        if (logComment == null) {
            response.setMessage("Usted ya no es el propietario de este mensaje. Por favor contacte a su coordinador de supervisores");
            return false;
        }

        model.setLogComment(logComment);
        return true;
    }

    private void constructSupervisorManagerMainPage(ModelAndView model, Long userId) {
        Gson json = new Gson();

        List<CommentMonitoringPlanNotice> lstGen = logCommentRepository.getEnabledCommentsByManagerSupRole(
                new ArrayList<String>() {{
                    add(MonitoringConstants.LOG_PENDING_ACCOMPLISHMENT);
                    add(MonitoringConstants.STATUS_PENDING_END);
                    add(MonitoringConstants.STATUS_PENDING_AUTHORIZATION);
                    add(MonitoringConstants.STATUS_PENDING_END);
                    add(MonitoringConstants.STATUS_END);
                    add(Constants.ACTION_AUTHORIZE_LOG_COMMENT);
                    add(MonitoringConstants.TYPE_INFORMATION);
                    add(ConstantsLogCase.ACT_ASSIGNMENT_SUPERVISOR_CASE);
                }}, userId, new PageRequest(0, 5)
        );

        String sLstGeneric = json.toJson(lstGen);
        model.addObject("lstNotification", sLstGeneric);
        model.addObject("urlToGo", "/supervisorManager/log/deleteComment.json");


        List<LogNotificationDto> lstNotification = messageRepository.getMessagesByUserId(sharedUserService.GetLoggedUserId(), new PageRequest(0, 5));

        model.addObject("lstNotificationA", json.toJson(lstNotification));
        model.addObject("urlToGoA", "/shared/messageHistory/deleteNotification.json?id=");


    }

    private void constructReviewerMainPage(ModelAndView model) {

        Long userId = sharedUserService.GetLoggedUserId();

        List<LogNotificationDto> lstA = caseRepository.getMeetingIncompleteInfo(userId, Constants.S_MEETING_INCOMPLETE, Constants.CASE_STATUS_MEETING, new PageRequest(0, 10));
        List<LogNotificationDto> lstB = caseRepository.getMeetingIncompleteInfo(userId, Constants.S_MEETING_INCOMPLETE_LEGAL, Constants.CASE_STATUS_MEETING, new PageRequest(0, 10));
        List<LogNotificationDto> lstC = caseRepository.getAuthorizedVerificationsInfo(userId, Constants.VERIFICATION_STATUS_AUTHORIZED, Constants.CASE_STATUS_VERIFICATION, new PageRequest(0, 10));

        List<LogNotificationDto> lstActivities = new ArrayList<>();

        lstActivities.addAll(lstA);
        lstActivities.addAll(lstB);
        lstActivities.addAll(lstC);

        Collections.sort(lstActivities, LogNotificationDto.dateSorter);

        List<LogNotificationDto> top10 = new ArrayList<>();


        int top = 0;
        for (LogNotificationDto act : lstActivities) {
            top10.add(act);
            top++;
            if (top == 10)
                break;
        }

        List<LogNotificationDto> lstNotificationA = logNotificationRepository.getReviewerNotifications(userId, new PageRequest(0, 5));
        List<LogNotificationDto> lstNotificationB = messageRepository.getMessagesByUserId(sharedUserService.GetLoggedUserId(), new PageRequest(0, 5));

//        List<LogNotificationDto> lstNotifications = new ArrayList<>();
//
//        lstNotifications.addAll(lstNotificationA);
//        lstNotifications.addAll(lstNotificationB);
//
//        Collections.sort(lstNotifications, LogNotificationDto.dateSorter);


//        List<LogNotificationDto> top10Notifications = new ArrayList<>();
//
//        int topN = 0;
//        for (LogNotificationDto not : lstNotifications) {
//            top10Notifications.add(not);
//            topN++;
//            if (topN == 10)
//                break;
//        }


        Gson conv = new Gson();


        model.addObject("lstActivities", conv.toJson(top10));
        model.addObject("lstNotification", conv.toJson(lstNotificationA));
        model.addObject("urlToGo", "/reviewer/log/deleteNotification.json?id=");


        model.addObject("lstNotificationA", conv.toJson(lstNotificationB));
        model.addObject("urlToGoA", "/shared/messageHistory/deleteNotification.json?id=");

    }

    private void constructEvaluationManagerPage(HttpServletRequest request, ModelAndView model) {

        List<LogNotificationDto> top10 = caseRepository.getNewSourceStatusCases(Constants.VERIFICATION_STATUS_NEW_SOURCE, Constants.CASE_STATUS_SOURCE_VALIDATION, new PageRequest(0, 10));
        Gson conv = new Gson();

        List<LogNotificationDto> top10Notif = logNotificationRepository.getManagerEvalNotifications(Constants.ROLE_EVALUATION_MANAGER, new PageRequest(0, 5));

        model.addObject("lstActivities", conv.toJson(top10));
        model.addObject("urlToGo", "/reviewer/log/deleteNotification.json?id=");

        List<LogNotificationDto> lstNotif = messageRepository.getMessagesByUserId(sharedUserService.GetLoggedUserId(), new PageRequest(0, 5));

        for (LogNotificationDto act : lstNotif) {
            act.setUrlToDelete(request.getContextPath() + "/shared/messageHistory/deleteNotification.json?id=");
        }

        top10Notif.addAll(lstNotif);

        Collections.sort(top10Notif, LogNotificationDto.dateSorter);

        model.addObject("lstNotification", conv.toJson(top10Notif));
    }

    @Transactional
    public void doDeleteNotificationReviewer(Long idNotif) {
        LogNotification notif = logNotificationRepository.findOne(idNotif);
        notif.setObsoleteUser(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        notif.setTimestampObsolete(Calendar.getInstance());
        notif.setIsObsolete(true);
        logNotificationRepository.save(notif);
    }

    @Override
    public boolean deleteNotification(Long id, Long userId, ResponseMessage response) {
        RelMessageUserReceiver relMessage = relMessageUserReceiverRepository.findOneByIdAndUserId(id, userId);

        if (relMessage == null) {
            response.setHasError(true);
            response.setMessage("No existe el registro que desea eliminar");
            return false;
        }

        relMessage.setIsObsolete(true);
        relMessage.setTimestampObsolete(Calendar.getInstance());
        relMessageUserReceiverRepository.save(relMessage);
        return true;
    }

    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;

    private void constructSupervisorMainPage(ModelAndView model, Long userId) {

        Calendar today = CalendarExt.getToday();

        List<ActivityMonitoringPlanNotice> lstGeneric = activityMonitoringPlanRepository.getLstActivitiesBeforeTodayByUserId(userId,
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_END);
                }},
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_ACTIVITY_NEW);
                    add(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
                }},
                today, new PageRequest(0, 10)
        );
        Gson json = new Gson();
        String sLstGeneric = json.toJson(lstGeneric);

        model.addObject("lstActivitiesOld", sLstGeneric);

        Calendar tomorrow = CalendarExt.getToday();
        tomorrow.add(Calendar.DATE, 1);

        lstGeneric = activityMonitoringPlanRepository.getLstActivitiesByUserIdAndDates(userId,
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_END);
                }},
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_ACTIVITY_NEW);
                    add(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
                }},
                today, tomorrow,
                new PageRequest(0, 10)
        );

        sLstGeneric = json.toJson(lstGeneric);
        model.addObject("lstActivitiesNew", sLstGeneric);

        List<CommentMonitoringPlanNotice> lstGen = logCommentRepository.getEnabledCommentsByUserId(userId, new PageRequest(0, 5));

        sLstGeneric = json.toJson(lstGen);
        model.addObject("lstNotification", sLstGeneric);
        model.addObject("urlToGo", "/supervisor/log/deleteComment.json");

        List<LogNotificationDto> lstNotification = messageRepository.getMessagesByUserId(sharedUserService.GetLoggedUserId(), new PageRequest(0, 5));

        model.addObject("lstNotificationA", json.toJson(lstNotification));
        model.addObject("urlToGoA", "/shared/messageHistory/deleteNotification.json?id=");

    }


    @Autowired
    ActivityAgendaRepository activityAgendaRepository;

    @Autowired
    MessageRepository messageRepository;

    private void constructDirectorMainPage(ModelAndView model, Long userId) {
        Calendar today = CalendarExt.getToday();
        List<ActivityAgendaNotice> lstGeneric = activityAgendaRepository.getLstActivitiesBeforeTodayByUserId(userId,
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_ACTIVITY_NEW);
                    add(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
                }},
                today, new PageRequest(0, 20)
        );
        Gson json = new Gson();
        String sLstGeneric = json.toJson(lstGeneric);

        model.addObject("lstActivitiesOld", sLstGeneric);

        Calendar tomorrow = CalendarExt.getToday();
        tomorrow.add(Calendar.DATE, 1);

        lstGeneric = activityAgendaRepository.getLstActivitiesByUserIdAndDates(userId,
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_ACTIVITY_NEW);
                    add(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);
                }},
                today, tomorrow,
                new PageRequest(0, 20)
        );

        sLstGeneric = json.toJson(lstGeneric);
        model.addObject("lstActivitiesNew", sLstGeneric);

        List<LogNotificationDto> lstNotification = messageRepository.getMessagesByUserId(sharedUserService.GetLoggedUserId(), new PageRequest(0, 10));

        model.addObject("lstNotification", json.toJson(lstNotification));
        model.addObject("urlToGo", "/shared/messageHistory/deleteNotification.json?id=");
    }

    private void constructHumanResourcesPage(ModelAndView model) {
        List<LogNotificationDto> lstNotification = messageRepository.getMessagesByUserId(sharedUserService.GetLoggedUserId(), new PageRequest(0, 10));
        Gson json = new Gson();
        model.addObject("lstNotification", json.toJson(lstNotification));
        model.addObject("urlToGo", "/shared/messageHistory/deleteNotification.json?id=");
    }

    private void constructChannelingManagerMainPage(ModelAndView model) {
        List<LogNotificationDto> lstNotification = messageRepository.getMessagesByUserId(sharedUserService.GetLoggedUserId(), new PageRequest(0, 10));
        Gson json = new Gson();
        model.addObject("lstNotification", json.toJson(lstNotification));
        model.addObject("urlToGo", "/shared/messageHistory/deleteNotification.json?id=");
    }

}
