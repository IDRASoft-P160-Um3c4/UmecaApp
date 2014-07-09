package com.umeca.service.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.RequestActivities;
import com.umeca.model.entities.supervisor.ResponseActivities;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Project: Umeca
 * User: Israel
 * Date: 7/3/14
 * Time: 9:47 AM
 */
public interface TrackMonPlanService {
    void getLstActivitiesByUser(RequestActivities req, Long userId, ArrayList<String> lstMonPlanStatus, ArrayList<String> lstActStatus, ResponseActivities response);
    void getActivityToShow(Long id, ModelAndView model);
    void saveReqEndMonPlan(AuthorizeRejectMonPlan model, User user, MonitoringPlan monPlan);
    void saveAuthRejectMonPlan(AuthorizeRejectMonPlan model, User user, MonitoringPlan monPlan, String statusAuth, String statusReject, String type);
}
