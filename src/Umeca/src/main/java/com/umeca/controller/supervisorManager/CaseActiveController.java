package com.umeca.controller.supervisorManager;


import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.ForFramingMeetingGrid;
import com.umeca.model.entities.supervisor.MonitoringPlanInfo;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.FramingMeetingRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CaseActiveController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisorManager/caseActive/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisorManager/caseActive/index";
    }

    @RequestMapping(value = "/supervisorManager/caseActive/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_CLOSED);
                }}, JqGridFilterModel.COMPARE_NOT_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {

            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, StatusCase> joinSt = r.join("status");
                final javax.persistence.criteria.Join<Case, StatusCase> joinM = r.join("meeting").join("imputed");


                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinSt.get("name"));
                    add(joinSt.get("description"));
                    add(r.get("idMP"));
                    add(joinM.get("name"));
                    add(joinM.get("lastNameP"));
                    add(joinM.get("lastNameM"));
                    add(joinM.get("birthDate"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idMP"))
                    return r.get("idMP");

                if (field.equals("statusName"))
                    return r.join("status").get("name");

                return null;
            }
        }, Case.class, ForFramingMeetingGrid.class);

        return result;
    }

    @Autowired
    CaseRepository caseRepository;

    @RequestMapping(value = "/supervisorManager/caseActive/authorize", method = RequestMethod.POST)
    public ModelAndView authorize(@RequestParam Long id){ //Case Id
        ModelAndView model = new ModelAndView("/supervisorManager/caseActive/authorizeRejectClose");

        try{
            GetCaseInfo(id, model, caseRepository);
            model.addObject("isAuthorized", 1);
            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "authorize", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/caseActive/reject", method = RequestMethod.POST)
    public ModelAndView reject(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/supervisorManager/caseActive/authorizeRejectClose");

        try{
            GetCaseInfo(id, model, caseRepository);
            model.addObject("isAuthorized", 0);
            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "reject", sharedUserService);
            return null;
        }
    }

    @Autowired
    FramingMeetingRepository framingMeetingRepository;

    public static void GetCaseInfo(Long id, ModelAndView model, CaseRepository caseRepository) {
        CaseInfo caseInfo = caseRepository.getInfoById(id);
        model.addObject("caseId", caseInfo.getIdCase());
        model.addObject("mpId", caseInfo.getIdMp());
        model.addObject("fullName", caseInfo.getPersonName());
        model.addObject("status", caseInfo.getStatus());
        model.addObject("msgPlan", "el cierre del caso");
        model.addObject("urlToGo", "/supervisorManager/caseActive/doAuthorizeRejectCase.json");

        //List<String> lstSupervisor = framingMeetingRepository.findLastSupervisorByCaseId()

    }

}
