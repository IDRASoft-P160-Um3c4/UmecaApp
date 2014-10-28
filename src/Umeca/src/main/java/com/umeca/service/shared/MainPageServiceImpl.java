package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.LogNotificationReviewer;
import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
import com.umeca.model.entities.shared.CommentRequest;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.CommentMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.LogNotificationReviewerRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    LogCommentRepository logCommentRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    LogNotificationReviewerRepository logNotificationReviewerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SystemSettingService systemSettingService;

    @Override
    public ModelAndView generatePage(String sRole, ModelAndView model, Long userId) {

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
                constructEvaluationManagerPage(model);
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
                }}, userId
        );
        String sLstGeneric = json.toJson(lstGen);
        model.addObject("lstNotification", sLstGeneric);
        model.addObject("urlToGo", "/supervisorManager/log/deleteComment.json");
    }

    private void constructReviewerMainPage(ModelAndView model) {

        List<LogNotificationDto> lstA = caseRepository.getMeetingIncompleteInfo(sharedUserService.GetLoggedUserId(), Constants.S_MEETING_INCOMPLETE, Constants.CASE_STATUS_MEETING, new PageRequest(0, 10));

        List<LogNotificationDto> lstB = caseRepository.getMeetingIncompleteInfo(sharedUserService.GetLoggedUserId(), Constants.S_MEETING_INCOMPLETE_LEGAL, Constants.CASE_STATUS_MEETING, new PageRequest(0, 10));

        List<LogNotificationDto> lstC = caseRepository.getAuthorizedVerificationsInfo(sharedUserService.GetLoggedUserId(), Constants.VERIFICATION_STATUS_AUTHORIZED, Constants.CASE_STATUS_VERIFICATION, new PageRequest(0, 10));

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

        List<LogNotificationDto> lstNotif = logNotificationReviewerRepository.getReviewerNotifications(sharedUserService.GetLoggedUserId());
        List<LogNotificationDto> top10Notif = new ArrayList<>();
        int topN = 0;
        for (LogNotificationDto not : lstNotif) {
            top10Notif.add(not);
            topN++;
            if (topN == 10)
                break;
        }

        Gson conv = new Gson();

        model.addObject("lstActivities", conv.toJson(top10));
        model.addObject("lstNotification", conv.toJson(top10Notif));
        model.addObject("urlToGo", "/reviewer/log/deleteNotification.json?id=");

    }

    private void constructEvaluationManagerPage(ModelAndView model) {

        List<LogNotificationDto> lstA = caseRepository.getNewSourceStatusCases(Constants.VERIFICATION_STATUS_NEW_SOURCE, Constants.CASE_STATUS_SOURCE_VALIDATION, new PageRequest(0, 10));
        List<LogNotificationDto> lstActivities = new ArrayList<>();
        lstActivities.addAll(lstA);

        Collections.sort(lstActivities, LogNotificationDto.dateSorter);

        Gson conv = new Gson();
        List<LogNotificationDto> lstNotif = logNotificationReviewerRepository.getReviewerNotifications(Constants.ROLE_EVALUATION_MANAGER);

        List<LogNotificationDto> top10 = new ArrayList<>();
        int top = 0;
        for (LogNotificationDto act : lstActivities) {
            top10.add(act);
            top++;
            if (top == 10)
                break;
        }
        List<LogNotificationDto> top10Notif = new ArrayList<>();
        int topN = 0;
        for (LogNotificationDto not : lstNotif) {
            top10Notif.add(not);
            topN++;
            if (topN == 10)
                break;
        }
        model.addObject("lstActivities", conv.toJson(top10));
        model.addObject("lstNotification", conv.toJson(top10Notif));
        model.addObject("urlToGo", "/reviewer/log/deleteNotification.json?id=");

    }

    @Transactional
    public void doDeleteNotificationReviewer(Long idNotif) {
        LogNotificationReviewer notif = logNotificationReviewerRepository.findOne(idNotif);
        notif.setObsoleteUser(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        notif.setTimestampObsolete(Calendar.getInstance());
        notif.setIsObsolete(true);
        logNotificationReviewerRepository.save(notif);
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

        List<CommentMonitoringPlanNotice> lstGen = logCommentRepository.getEnabledCommentsByUserId(userId);

        sLstGeneric = json.toJson(lstGen);
        model.addObject("lstNotification", sLstGeneric);
        model.addObject("urlToGo", "/supervisor/log/deleteComment.json");
    }
}
