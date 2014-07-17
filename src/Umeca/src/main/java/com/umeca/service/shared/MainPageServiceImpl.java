package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.CommentRequest;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.CommentMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.LogCommentMonitoringPlan;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisorManager.LogCommentMonitoringPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    LogCommentMonitoringPlanRepository logCommentMonitoringPlanRepository;

    @Override
    public ModelAndView generatePage(String sRole, ModelAndView model, Long userId) {
        switch(sRole){
            case Constants.ROLE_SUPERVISOR:
                constructSupervisorMainPage(model, userId);
                return model;

            case Constants.ROLE_SUPERVISOR_MANAGER:
                constructSupervisorManagerMainPage(model, userId);
                return model;

            default:
                return model;
        }
    }

    @Override
    public boolean deleteComment(String sRole, CommentRequest model, User user, ResponseMessage response) {
        switch (sRole){
            case Constants.ROLE_SUPERVISOR:
                if(validateSupervisorComment(model, user, response) == false)
                    return false;

                doDeleteComment(model, user);

                return true;
            case Constants.ROLE_SUPERVISOR_MANAGER:
                if(validateManagerSupervisorComment(model, user, response) == false)
                    return false;

                doDeleteComment(model, user);

                return true;
            default:
                response.setMessage("Usted no tiene los permisos suficientes para realizar esta operación");
                return false;
        }
    }


    private void doDeleteComment(CommentRequest model, User user) {
        LogCommentMonitoringPlan logCommentMonitoringPlan = model.getLogCommentMonitoringPlan();
        logCommentMonitoringPlan.setObsoleteUser(user);
        logCommentMonitoringPlan.setTimestampObsolete(Calendar.getInstance());
        logCommentMonitoringPlan.setObsolete(true);
        logCommentMonitoringPlanRepository.save(logCommentMonitoringPlan);
    }

    private boolean validateManagerSupervisorComment(CommentRequest model, User user, ResponseMessage response) {
        LogCommentMonitoringPlan logCommentMonitoringPlan = logCommentMonitoringPlanRepository.getCommentByCommentIdAndNotSenderUserId(model.getId(), user.getId());

        if(logCommentMonitoringPlan == null){
            response.setMessage("Usted ya no es el propietario de este mensaje. Por favor contacte a su coordinador de supervisores");
            return false;
        }

        model.setLogCommentMonitoringPlan(logCommentMonitoringPlan);
        return true;
    }

    private boolean validateSupervisorComment(CommentRequest model, User user, ResponseMessage response) {
        LogCommentMonitoringPlan logCommentMonitoringPlan = logCommentMonitoringPlanRepository.getCommentByCommentIdAndUserId(model.getId(), user.getId());

        if(logCommentMonitoringPlan == null){
            response.setMessage("Usted ya no es el propietario de este mensaje. Por favor contacte a su coordinador de supervisores");
            return false;
        }

        model.setLogCommentMonitoringPlan(logCommentMonitoringPlan);
        return true;
    }

    private void constructSupervisorManagerMainPage(ModelAndView model, Long userId) {
        Gson json = new Gson();
        List<CommentMonitoringPlanNotice> lstGen = logCommentMonitoringPlanRepository.getEnabledCommentsByManagerSupId(userId);
        String sLstGeneric = json.toJson(lstGen);
        model.addObject("lstNotification",sLstGeneric);
        model.addObject("urlToGo", "/supervisorManager/log/deleteComment.json");
    }

    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;

    private void constructSupervisorMainPage(ModelAndView model, Long userId) {

        Calendar today = CalendarExt.getToday();

        List<ActivityMonitoringPlanNotice> lstGeneric = activityMonitoringPlanRepository.getLstActivitiesBeforeTodayByUserId(userId,
                new ArrayList<String>() {{add(MonitoringConstants.STATUS_END);}},
                new ArrayList<String>() {{add(MonitoringConstants.STATUS_ACTIVITY_NEW);add(MonitoringConstants.STATUS_ACTIVITY_MODIFIED);}},
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
        model.addObject("lstActivitiesNew",sLstGeneric);

        List<CommentMonitoringPlanNotice> lstGen = logCommentMonitoringPlanRepository.getEnabledCommentsByUserId(userId);
        sLstGeneric = json.toJson(lstGen);

        model.addObject("lstNotification",sLstGeneric);
        model.addObject("urlToGo", "/supervisor/log/deleteComment.json");

    }
}
