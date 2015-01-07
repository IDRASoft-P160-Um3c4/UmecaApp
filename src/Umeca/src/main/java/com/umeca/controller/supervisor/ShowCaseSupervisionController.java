package com.umeca.controller.supervisor;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.managereval.CaseEvaluationView;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.shared.Constants;
import com.umeca.repository.account.UserRepository;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 12:03 PM
 */

@Controller
public class ShowCaseSupervisionController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/showCaseSupervision/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/showCaseSupervision/index";
    }

    @Autowired
    SharedUserService userService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/supervisor/showCaseEvaluation/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();
        User user = userRepository.findOne(userId);
        Boolean addFileter = false;
        for (Role r : user.getRoles()) {
            if (r.getRole().equals(Constants.ROLE_SUPERVISOR)) {
                addFileter = true;
                break;
            }
        }
        if (addFileter) {
            opts.extraFilters = new ArrayList<>();
            JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_LEFT_JOIN_EQUAL);
            opts.extraFilters.add(extraFilter);
        }

        JqGridRulesModel extraFilter1 = new JqGridRulesModel("statusCase", new ArrayList() {{
            add(Constants.CASE_STATUS_TECHNICAL_REVIEW);
            add(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE);
            add(Constants.CASE_STATUS_HEARING_FORMAT_INCOMPLETE);
            add(Constants.CASE_STATUS_HEARING_FORMAT_END);
            add(Constants.CASE_STATUS_FRAMING_INCOMPLETE);
            add(Constants.CASE_STATUS_FRAMING_COMPLETE);
        }}, JqGridFilterModel.COMPARE_IN);
        opts.extraFilters.add(extraFilter1);


        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            Join<MonitoringPlan, User> joinUs;
            Join<Case, MonitoringPlan> joinMP;
            Join<Case, StatusCase> joinSt;

            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMe = r.join("meeting", JoinType.INNER);
                final Join<Meeting, Imputed> joinImp = joinMe.join("imputed", JoinType.INNER);
                final Join<Case, Verification> joinVer = r.join("verification", JoinType.LEFT);
                final Join<Case, FramingMeeting> joinFM = r.join("framingMeeting", JoinType.LEFT);
                final Join<Case, HearingFormat> joinHF = r.join("hearingFormats", JoinType.LEFT);
                joinMP = r.join("monitoringPlan", JoinType.LEFT);
                joinUs = joinMP.join("supervisor", JoinType.LEFT);
                joinSt = r.join("status");


                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(r.get("id").alias("idCase"));
                    add(r.get("idMP"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinFM.get("id").alias("idFM"));
                    add(joinHF.get("id").alias("idHF"));
                    add(joinMP.get("id").alias("idMonP"));
                    add(joinVer.get("id").alias("idVerif"));
                    add(joinFM.get("isTerminated"));
                    add(joinUs.get("fullname").alias("userName"));
                    add(joinMP.get("resolution"));
                }};

                return result;
            }

            boolean isCountSupId = false; //para saber si el conteo o la consulta con la informacion
            boolean isCountSupSt = false;
            boolean isCountSupRes = false;

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.join("caseDetention").get("idMP");
                if (field.equals("supervisorId")) {
                    if (isCountSupId == false) {//para evitar el doble join sobre una misma tabla
                        isCountSupId = true;
                        return r.join("monitoringPlan", JoinType.LEFT).join("supervisor", JoinType.LEFT).get("id");
                    }
                    return joinUs.get("id");
                }

                if (field.equals("statusCase")) {
                    if (isCountSupSt == false) {//para evitar el doble join sobre una misma tabla
                        isCountSupSt = true;
                        return r.join("status").get("name");
                    }
                    return joinSt.get("name");
                }

                if (field.equals("resolutionStr")) {
                    if (isCountSupRes == false) {//para evitar el doble join sobre una misma tabla
                        isCountSupRes = true;
                        return r.join("monitoringPlan", JoinType.LEFT).get("resolution");
                    }
                    return joinMP.get("resolution");
                }
                return null;
            }
        }, Case.class, CaseEvaluationView.class);
        return result;
    }

}
