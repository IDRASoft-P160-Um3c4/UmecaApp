package com.umeca.controller.managereval;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.managereval.CaseEvaluationView;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CaseEvaluationController {
    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;


    @RequestMapping(value = "/managereval/showCaseEvaluation/index", method = RequestMethod.GET)
    public String index() {
        return "/managereval/showCaseEvaluation/index";
    }

    @RequestMapping(value = {"/managereval/showCaseEvaluation/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("statusMeeting",
                new ArrayList<String>() {{
                    add(Constants.S_MEETING_INCOMPLETE_LEGAL);
                    add(Constants.S_MEETING_COMPLETE);
                    add(Constants.S_MEETING_INCOMPLETE);
                }}
                , JqGridFilterModel.COMPARE_IN
        );

        opts.extraFilters.add(extraFilter);

        List<String> usrRoles = userService.getLstRolesByUserId(userService.GetLoggedUserId());
        if (usrRoles != null && usrRoles.size() > 0) {
            if (usrRoles.contains(Constants.ROLE_REVIEWER)) {
                extraFilter = new JqGridRulesModel("user", userService.GetLoggedUserId().toString()
                        , JqGridFilterModel.COMPARE_EQUAL
                );
                opts.extraFilters.add(extraFilter);
            }
        }


        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Meeting, Case> joinMeCa = r.join("caseDetention");
                final Join<Meeting, Imputed> joinMeIm = r.join("imputed");
                final Join<Meeting, StatusMeeting> joinMeSt = r.join("status", JoinType.INNER);
                final Join<Verification, StatusVerification> joinVer = joinMeCa.join("verification", JoinType.LEFT);
                final Join<Verification, StatusVerification> joinVeSt = joinVer.join("status", JoinType.LEFT);
                final Join<Meeting, TechnicalReview> joinTR = joinMeCa.join("technicalReview", JoinType.LEFT);
                final Join<Meeting, User> joinUsr = r.join("reviewer", JoinType.INNER);

                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(joinMeCa.get("id"));
                    add(joinVer.get("id"));
                    add(joinMeCa.get("idFolder"));
                    add(joinMeIm.get("name"));
                    add(joinMeIm.get("lastNameP"));
                    add(joinMeIm.get("lastNameM"));
                    add(joinMeSt.get("name").alias("statusMeeting"));
                    add(joinVeSt.get("name").alias("statusVerification"));
                    add(joinTR.get("id").alias("idTec"));
                    add(joinUsr.get("fullname"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");
                else if (field.equals("statusMeeting"))
                    return r.join("status").get("name");
                else if (field.equals("user"))
                    return r.join("reviewer").get("id");

                return null;
            }
        }, Meeting.class, CaseEvaluationView.class);
        return result;
    }

    @RequestMapping(value = "/managereval/showCaseEvaluation/obsoleteCase", method = RequestMethod.GET)
    public String obsoleteCase() {
        return "/managereval/showCaseEvaluation/obsoleteCase";
    }

    @RequestMapping(value = {"/managereval/showCaseEvaluation/obsoleteCase/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listObsoleteCase(@ModelAttribute JqGridFilterModel opts) {
        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("statusMeeting",Constants.S_MEETING_OBSOLETE
                , JqGridFilterModel.COMPARE_EQUAL
        );

        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("statusCase",
                Constants.CASE_STATUS_OBSOLETE_EVALUATION
                , JqGridFilterModel.COMPARE_EQUAL
        );

        opts.extraFilters.add(extraFilter);
        Long userId =userService.GetLoggedUserId();
        if (userService.isUserInRole(userId,Constants.ROLE_REVIEWER)) {
                extraFilter = new JqGridRulesModel("user", userId.toString()
                        , JqGridFilterModel.COMPARE_EQUAL
                );
                opts.extraFilters.add(extraFilter);
            }



        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Meeting, Case> joinMeCa = r.join("caseDetention");
                final Join<Meeting, Imputed> joinMeIm = r.join("imputed");
                final Join<Meeting, StatusMeeting> joinMeSt = r.join("status", JoinType.INNER);
                final Join<Verification, StatusVerification> joinCdSt = joinMeCa.join("status", JoinType.INNER);
                final Join<Meeting, User> joinUsr = r.join("reviewer", JoinType.INNER);

                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(joinMeCa.get("id"));
                    add(joinMeCa.get("idFolder"));
                    add(joinMeIm.get("name"));
                    add(joinMeIm.get("lastNameP"));
                    add(joinMeIm.get("lastNameM"));
                    add(joinUsr.get("fullname").alias("reviewer"));
                    add(joinMeCa.get("dateObsolete"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");
                else if (field.equals("statusMeeting"))
                    return r.join("status").get("name");
                else if (field.equals("user"))
                    return r.join("reviewer").get("id");
                else if(field.equals("statusCase"))
                    return r.join("caseDetention").join("status").get("name");

                return null;
            }
        }, Meeting.class, CaseEvaluationView.class);
        return result;
    }

}