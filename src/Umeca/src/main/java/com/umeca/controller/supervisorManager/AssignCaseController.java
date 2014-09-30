package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.LogNotificationReviewer;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisorManager.AssignCaseView;
import com.umeca.infrastructure.jqgrid.model.*;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.shared.*;

import com.umeca.repository.supervisor.LogNotificationReviewerRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by dcortesr on 16/07/14.
 */
@Controller
public class AssignCaseController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MonitoringPlanRepository monitoringPlanRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private LogCommentRepository logCommentRepository;

    @Autowired
    SharedUserService userService;

    @ModelAttribute("users")
    public String getVerifySources() {
        Gson gson = new Gson();
        List<Long> userAll = userRepository.getIdValidUsersByRole(Constants.ROLE_SUPERVISOR);
        List<SelectList> users = userRepository.getUsersAssignCase(Constants.ROLE_SUPERVISOR,userAll);
        for(SelectList u: users){
            userAll.remove(u.getId());
        }
        for(Long uid: userAll){
            User user = userRepository.findOne(uid);
            users.add(new SelectList(user.getId(),user.getUsername(),user.getFullname(),0L));
        }
        Collections.sort(users,new SelectListComparator());
        return gson.toJson(users);
    }

    @RequestMapping(value = {"/supervisorManager/assignCase/index"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("/supervisorManager/assignCase/index");
    }

    public class SelectListComparator implements Comparator<SelectList> {
        @Override
        public int compare(SelectList o1, SelectList o2) {
            return o1.getAux().compareTo(o2.getAux());
        }
    }

    @RequestMapping(value = {"/supervisorManager/assignCase/list"}, method = RequestMethod.POST)
    @ResponseBody
    public JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){
        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("status",
                Constants.CASE_STATUS_FRAMING_COMPLETE,JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, MonitoringPlan> joinMP = r.join("monitoringPlan", JoinType.LEFT);
                final Join<MonitoringPlan, User> joinSup = joinMP.join("supervisor", JoinType.LEFT);
                final Join<Case,Meeting> joinMeVe = r.join("meeting");
                final Join<Meeting,Imputed> joinMee = joinMeVe.join("imputed");
                final Join<Case, StatusCase> joinSt = r.join("status");


                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("idFolder"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinSt.get("description").alias("status"));
                    add(joinSup.get("id").alias("supervisor"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("status"))
                    return r.join("status").get("name");

                return null;
            }
        }, Case.class, AssignCaseView.class);
        return result;
    }

    @RequestMapping(value = "/supervisorManager/assignCase/save", method = RequestMethod.POST)
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public String save(@RequestBody AssignCaseSaveInformation data) {
        data.setComments(data.getComments().trim());
        Long userSenderId = userService.GetLoggedUserId();
        Case case_ = caseRepository.findOne(data.getCaseId());
        User user = userRepository.findOne(data.getSupervisorId());
        User userSender = userRepository.findOne(userSenderId);

        MonitoringPlan monitoringPlan = new MonitoringPlan();
        monitoringPlan.setCaseDetention(case_);
        monitoringPlan.setSupervisor(user);
        monitoringPlan.setStatus(MonitoringConstants.STATUS_NEW);
        monitoringPlan.setCreationTime(Calendar.getInstance());
        monitoringPlanRepository.save(monitoringPlan);

        LogComment logComment = new LogComment();

        logComment.setComments(data.getComments());
        logComment.setAction("Debe generar el plan de seguimiento");
        logComment.setCaseDetention(case_);
        logComment.setMonitoringPlan(monitoringPlan);
        logComment.setSenderUser(userSender);
        logComment.setReceiveUser(user);
        logComment.setTimestamp(Calendar.getInstance());
        logComment.setType(MonitoringConstants.TYPE_COMMENT_ASSIGNED_CASE);

        logComment.setSenderUser(userSender);
        logComment.setReceiveUser(user);
        logComment.setObsolete(false);

        logCommentRepository.save(logComment);
        return "{}";
    }
}
