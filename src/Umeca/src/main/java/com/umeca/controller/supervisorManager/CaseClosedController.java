package com.umeca.controller.supervisorManager;


import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.CloseCause;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.ForFramingMeetingGrid;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CaseClosedController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    CaseService caseService;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisorManager/caseClosed/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisorManager/caseClosed/index";
    }

    @RequestMapping(value = "/supervisorManager/caseClosed/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                Constants.CASE_STATUS_CLOSED
                , JqGridFilterModel.COMPARE_EQUAL);

        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("closeCause",
                new ArrayList<String>() {{
                    add(Constants.CLOSE_CAUSE_OBSOLETE_SUPERVISION);
                    add(Constants.CLOSE_CAUSE_OBSOLETE_EVALUATION);
                }}
                , JqGridFilterModel.COMPARE_NOT_IN);

        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {

            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, StatusCase> joinSt = r.join("status");
                final javax.persistence.criteria.Join<Case, CloseCause> joinCloseCause = r.join("closeCause");
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
                    add(r.get("isSubstracted"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idMP"))
                    return r.get("idMP");
                else if (field.equals("fullName"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("statusName"))
                    return r.join("status").get("name");
                else if (field.equals("closeCause"))
                    return r.join("closeCause").get("code");
                else if (field.equals("substractedStr"))
                    return r.get("isSubstracted");
                else
                    return null;
            }
        }, Case.class, ForFramingMeetingGrid.class);

        return result;
    }

    @Autowired
    CaseRepository caseRepository;
    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @RequestMapping(value = "/supervisorManager/caseClosed/showReopenCase", method = RequestMethod.POST)
    public ModelAndView showReopen(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/caseClosed/reopenCase");

        try {
            GetCaseInfo(id, model, caseRepository, hearingFormatRepository);
            model.addObject("isAuthorized", 1);
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "showReopen", sharedUserService);
            return null;
        }
    }

    public static void GetCaseInfo(Long id, ModelAndView model, CaseRepository caseRepository, HearingFormatRepository hearingFormatRepository) {
        CaseInfo caseInfo = caseRepository.getInfoById(id);
        model.addObject("caseId", caseInfo.getCaseId());
        model.addObject("mpId", StringEscape.escapeText(caseInfo.getMpId()));
        model.addObject("fullName", StringEscape.escapeText(caseInfo.getPersonName()));
        model.addObject("status", caseInfo.getStatus());
        model.addObject("folderId", caseInfo.getFolderId());
        model.addObject("msgPlan", "reabrir el caso");
        model.addObject("urlToGo", "/supervisorManager/caseClosed/doReopenCase.json");

        List<String> lstSupervisor = hearingFormatRepository.findLastFullNameSupervisorByCaseId(id, new PageRequest(0, 1));

        if (lstSupervisor != null && lstSupervisor.size() > 0)
            model.addObject("supervisor", lstSupervisor.get(0));
        else {
            Case existCase = caseRepository.findOne(id);
            model.addObject("supervisor", existCase.getCloserUser().getFullname());
        }
    }


    @RequestMapping(value = "/supervisorManager/caseClosed/doReopenCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doReopenCase(@ModelAttribute AuthorizeRejectMonPlan model) {
        return caseService.doReopenCase(model);
    }
}
