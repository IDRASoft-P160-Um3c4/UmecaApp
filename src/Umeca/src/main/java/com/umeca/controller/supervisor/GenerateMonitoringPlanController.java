package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanInfo;
import com.umeca.model.entities.supervisor.MonitoringPlanView;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.ActivityGoalRepository;
import com.umeca.repository.supervisor.AidSourceRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.repository.supervisor.SupervisionActivityRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 12:03 PM
 */

@Controller
public class GenerateMonitoringPlanController {

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/index", method = RequestMethod.GET)
    public String index(){
        return "/supervisor/generateMonitoringPlan/index";
    }


    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private SharedUserService userService;

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>(){{add(MonitoringConstants.STATUS_NEW);add(MonitoringConstants.STATUS_PENDING_CREATION);}},JqGridFilterModel.COMPARE_IN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.join("caseDetention").get("idFolder"));
                    add(r.join("caseDetention").get("idMP"));
                    add(r.join("caseDetention").join("meeting").join("imputed").get("name"));
                    add(r.join("caseDetention").join("meeting").join("imputed").get("lastNameP"));
                    add(r.join("caseDetention").join("meeting").join("imputed").get("lastNameM"));
                    add(r.get("creationTime"));
                    add(r.get("status"));
                    add(r.join("supervisor").get("username"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("stCreationTime"))
                    return r.get("creationTime");
                if(field.equals("supervisorId"))
                    return r.join("supervisor").get("id");
                return null;
            }
        }, MonitoringPlan.class, MonitoringPlanView.class);

        return result;

    }


    @Autowired
    private ArrangementRepository arrangementRepository;
    @Autowired
    private SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    private ActivityGoalRepository activityGoalRepository;
    @Autowired
    private AidSourceRepository aidSourceRepository;
    @Autowired
    private MonitoringPlanRepository monitoringPlanRepository;

    @RequestMapping(value = "/supervisor/generateMonitoringPlan/generate", method = RequestMethod.GET)
    public @ResponseBody ModelAndView generate(@RequestParam Long id){ //Id de MonitoringPlan
        ModelAndView model = new ModelAndView("/supervisor/generateMonitoringPlan/generate");
        Gson gson = new Gson();
        List<SelectList> lstGeneric = arrangementRepository.findLstArrangement(id);
        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstArrangement", sLstGeneric);

        lstGeneric = supervisionActivityRepository.findAllValid();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstActivities", sLstGeneric);

        lstGeneric = activityGoalRepository.findAllValid();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstGoals", sLstGeneric);

        lstGeneric = aidSourceRepository.findAllValid();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstSources", sLstGeneric);

        MonitoringPlanInfo mpi =  monitoringPlanRepository.getInfoById(id);
        model.addObject("caseId",mpi.getIdCase());
        model.addObject("folderId",mpi.getIdFolder());
        model.addObject("personName",mpi.getPersonName());
        model.addObject("monStatus",mpi.getPersonName());
        model.addObject("monitoringPlanId",mpi.getIdMonitoringPlan());
        return model;
    }

}
