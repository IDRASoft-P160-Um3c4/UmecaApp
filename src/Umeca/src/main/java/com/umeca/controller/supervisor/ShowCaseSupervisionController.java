package com.umeca.controller.supervisor;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
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
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        for (Role r : user.getRoles()){
            if(r.getRole().equals(Constants.ROLE_SUPERVISOR)){
                addFileter = true;
                break;
            }
        }
        if(addFileter){
            opts.extraFilters = new ArrayList<>();
            JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
            opts.extraFilters.add(extraFilter);
        }

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMe = r.join("meeting", JoinType.INNER);
                final Join<Meeting, Imputed> joinImp = joinMe.join("imputed", JoinType.INNER);
                final Join<Case, Verification> joinVer = r.join("verification", JoinType.LEFT);
                final Join<Case, FramingMeeting> joinFM = r.join("framingMeeting");
                final Join<Case, HearingFormat> joinHF = r.join("hearingFormats");
                final Join<Case, MonitoringPlan> joinMP = r.join("monitoringPlan");

                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("idMP"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinFM.get("id").alias("idFM"));
                    add(joinHF.get("id").alias("idHF"));
                    add(joinMP.get("id").alias("idMonP"));
                    add(joinVer.get("id").alias("idVerif"));
                    add(joinFM.get("isTerminated"));
                    add(joinMP.join("supervisor").get("fullname").alias("userName"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.join("caseDetention").get("idMP");
                if(field.equals("supervisorId"))
                    return r.join("monitoringPlan").join("supervisor").get("id");
                return null;
            }
        }, Case.class, CaseEvaluationView.class);
        return result;
    }

}
