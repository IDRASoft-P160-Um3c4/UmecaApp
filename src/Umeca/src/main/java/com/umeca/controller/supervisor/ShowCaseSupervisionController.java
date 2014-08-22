package com.umeca.controller.supervisor;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.entities.managereval.CaseEvaluationView;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.SelectFilterFields;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = { "/supervisor/showCaseEvaluation/list"}, method = RequestMethod.POST)
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
                final Join<Meeting,StatusMeeting> joinMeSt = r.join("status", JoinType.LEFT);
                final Join<Verification,StatusVerification> joinVer = joinMeCa.join("verification",JoinType.LEFT);
                final Join<Verification,StatusVerification> joinVeSt = joinVer.join("status",JoinType.LEFT);
                final Join<Meeting,TechnicalReview> joinTR = joinMeCa.join("technicalReview",JoinType.LEFT);
                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>(){{
                    add(joinMeCa.get("id"));
                    add(joinVer.get("id"));
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
