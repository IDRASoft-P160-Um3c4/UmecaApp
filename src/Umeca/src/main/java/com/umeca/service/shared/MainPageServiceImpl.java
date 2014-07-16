package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.CommentMonitoringPlanNotice;
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

    private void constructSupervisorManagerMainPage(ModelAndView model, Long userId) {
        Gson json = new Gson();
        List<CommentMonitoringPlanNotice> lstGen = logCommentMonitoringPlanRepository.getEnabledCommentsByManagerSupId(userId);
        String sLstGeneric = json.toJson(lstGen);
        model.addObject("lstNotification",sLstGeneric);
        model.addObject("urlToGo", "/supervisor/log/deleteComment.json");
    }

    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;

    @Autowired
    LogCommentMonitoringPlanRepository logCommentMonitoringPlanRepository;

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
        model.addObject("urlToGo", "/supervisorManager/log/deleteComment.json");

    }
}
