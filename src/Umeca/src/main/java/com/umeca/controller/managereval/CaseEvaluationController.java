package com.umeca.controller.managereval;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.entities.managereval.CaseEvaluationView;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.managereval.ManagerevalView;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.StatusVerificationRepository;
import com.umeca.repository.catalog.VerificationMethodRepository;
import com.umeca.repository.managereval.SourceVerificationRepository;
import com.umeca.repository.reviewer.VerificationRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping(value = { "/managereval/showCaseEvaluation/list" }, method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusMeeting",
                new ArrayList<String>() {{
                    add(Constants.S_MEETING_INCOMPLETE_LEGAL);
                    add(Constants.S_MEETING_COMPLETE);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);
            JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Meeting,Case> joinMeCa = r.join("caseDetention");
                final Join<Meeting,Imputed> joinMeIm = r.join("imputed");
                final Join<Meeting,StatusMeeting> joinMeSt = r.join("status",JoinType.LEFT);
                final Join<Verification,StatusVerification> joinVeSt = joinMeCa.join("verification",JoinType.LEFT).join("status",JoinType.LEFT);
                final Join<Meeting,TechnicalReview> joinTR = joinMeCa.join("technicalReview",JoinType.LEFT);
                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>(){{
                    add(joinMeCa.get("id"));
                    add(joinMeCa.get("idFolder"));
                    add(joinMeIm.get("name"));
                    add(joinMeIm.get("lastNameP"));
                    add(joinMeIm.get("lastNameM"));
                    add(joinMeSt.get("name").alias("statusMeeting"));
                    add(joinVeSt.get("name").alias("statusVerification"));
                    add(joinTR.get("id").alias("idTec"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");

                return null;
            }
        }, Meeting.class, CaseEvaluationView.class);
        return result;
    }

}