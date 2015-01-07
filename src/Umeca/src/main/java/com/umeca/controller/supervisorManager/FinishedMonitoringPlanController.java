package com.umeca.controller.supervisorManager;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanView;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FinishedMonitoringPlanController {

    @RequestMapping(value = "/supervisorManager/finishedMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisorManager/finishedMonitoringPlan/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisorManager/finishedMonitoringPlan/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>(){{add(MonitoringConstants.STATUS_END);}},JqGridFilterModel.COMPARE_IN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<MonitoringPlan,Case> joinCd = r.join("caseDetention");
                final javax.persistence.criteria.Join<Meeting,Imputed> joinIm = joinCd.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(joinCd.get("id"));
                    add(joinCd.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                    add(r.get("creationTime"));
                    add(r.get("generationTime"));
                    add(r.get("authorizationTime"));
                    add(r.get("status"));
                    add(r.join("supervisor").get("username"));
                    add(r.get("posAuthorizationChangeTime"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if(field.equals("idMP"))
                    return r.join("caseDetention").get("idMP");
                if(field.equals("stCreationTime"))
                    return r.get("creationTime");
                if(field.equals("stGenerationTime"))
                    return r.get("generationTime");
                if(field.equals("stAuthorizationTime"))
                    return r.get("authorizationTime");
                if(field.equals("supervisor"))
                    return r.join("supervisor").get("username");
                return null;
            }
        }, MonitoringPlan.class, MonitoringPlanView.class);

        return result;

    }

}
