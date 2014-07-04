package com.umeca.service.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.entities.supervisorManager.LogCommentMonitoringPlan;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.repository.supervisorManager.LogCommentMonitoringPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 7/3/14
 * Time: 9:47 AM
 */
@Service
public class TrackMonPlanServiceImpl implements TrackMonPlanService{

    @Autowired
    private ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    private SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    private ActivityGoalRepository activityGoalRepository;

    @Override
    public void getLstActivitiesByUser(RequestActivities req, Long userId, ArrayList<String> lstMonPlanStatus, ArrayList<String> lstActStatus, ResponseActivities response) {
        List<ActivityMonitoringPlanResponse> lstAllActivities =
                activityMonitoringPlanRepository.getAllActivities(userId, lstMonPlanStatus, lstActStatus,
                        (req.getYearStart() * 100) + req.getMonthStart(), (req.getYearEnd() * 100) + req.getMonthEnd());
        response.setLstMonPlanActivities(lstAllActivities);

        List<SelectList> lstActivities = supervisionActivityRepository.findAllSl();
        List<SelectList> lstGoals = activityGoalRepository.findAllSl();
        response.setLstActivities(lstActivities);
        response.setLstGoals(lstGoals);
        response.setHasError(false);
    }


    @Autowired
    private ArrangementRepository arrangementRepository;

    @Override
    public void getActivityToShow(Long id, ModelAndView model) {
        ActivityMonitoringPlanInfo actMonPlanInfo = activityMonitoringPlanRepository.getActivityInfo(id);

        model.addObject("actMonPlanId", id);
        model.addObject("caseId", actMonPlanInfo.getCaseId());
        model.addObject("mpId", actMonPlanInfo.getMpId());
        model.addObject("fullName", actMonPlanInfo.getPersonName());
        model.addObject("status", actMonPlanInfo.getStatus());

        List<SelectList> lstArrangement = arrangementRepository.findArrangementById(id);

        String sLstArrangement = null;

        for(SelectList sl : lstArrangement){
            if(sLstArrangement == null){
                sLstArrangement = sl.getName() + " / " + sl.getDescription();
            }
            else{
                sLstArrangement = sLstArrangement + " <br/> " + sl.getName() + " / " + sl.getDescription();
            }
        }

        String status = actMonPlanInfo.getActStatus();
        boolean isReadOnly = true;

        if(status.equals(MonitoringConstants.STATUS_ACTIVITY_MODIFIED) || status.equals(MonitoringConstants.STATUS_ACTIVITY_NEW)){
            isReadOnly = false;
        }

        model.addObject("lstArrangements", sLstArrangement);
        model.addObject("actSup", actMonPlanInfo.getSupervisionActivityName());
        model.addObject("actGoal", actMonPlanInfo.getActivityGoalName());
        model.addObject("actSources", actMonPlanInfo.getAidSourceName());
        model.addObject("actStatus", actMonPlanInfo.getActStatus());
        String[] sDate = actMonPlanInfo.getStartDateTime().split("\\|");
        model.addObject("actInitDate", sDate[0]);
        model.addObject("actInitTime", sDate[1]);
        sDate = actMonPlanInfo.getEndDateTime().split("\\|");
        model.addObject("actEndDate", sDate[0]);
        model.addObject("actEndTime", sDate[1]);
        model.addObject("isReadOnly", isReadOnly);
        model.addObject("actSupervisorDone", StringExt.naOnEmpty(actMonPlanInfo.getSupUserDone()));
        model.addObject("actComments", StringExt.naOnEmpty(actMonPlanInfo.getComments()));
        model.addObject("actEndFullDate", CalendarExt.calendarToFormatString(actMonPlanInfo.getEndDone(), "dd/MM/yyyy HH:mm"));
    }

    @Autowired
    LogCommentMonitoringPlanRepository logCommentMonPlanRepository;
    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    LogChangeDataRepository logChangeDataRepository;

    @Override
    @Transactional
    public void saveAuthRejectMonPlan(AuthorizeRejectMonPlan model, User user, MonitoringPlan monPlan) {
        LogCommentMonitoringPlan commentModel = new LogCommentMonitoringPlan();
        Calendar now = Calendar.getInstance();
        String statusAction = (model.getAuthorized() == 1 ? MonitoringConstants.STATUS_AUTHORIZED : MonitoringConstants.STATUS_REJECTED_AUTHORIZED);
        commentModel.setComments(model.getComments());
        commentModel.setAction(statusAction);
        commentModel.setMonitoringPlan(monPlan);
        commentModel.setSenderUser(user);
        commentModel.setTimestamp(now);
        commentModel.setType(MonitoringConstants.TYPE_COMMENT_AUTHORIZED);

        MonitoringPlanJson jsonOld = MonitoringPlanJson.convertToJson(monPlan);
        monPlan.setStatus(statusAction);
        monPlan.setAuthorizer(user);
        monPlan.setAuthorizationTime(now);
        MonitoringPlanJson jsonNew = MonitoringPlanJson.convertToJson(monPlan);

        logChangeDataRepository.save(new LogChangeData(ActivityMonitoringPlan.class.getName(), jsonOld, jsonNew, user.getUsername(), monPlan.getId()));
        logCommentMonPlanRepository.save(commentModel);
        monitoringPlanRepository.save(monPlan);
    }
}
