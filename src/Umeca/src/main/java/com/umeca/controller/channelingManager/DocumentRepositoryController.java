package com.umeca.controller.channelingManager;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.activityReport.ActivityReport;
import com.umeca.model.entities.shared.activityReport.ActivityReportRequest;
import com.umeca.model.entities.shared.activityReport.ActivityReportView;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.ActivityReportService;
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
public class DocumentRepositoryController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/channelingManager/documentRepository/index", method = RequestMethod.GET)
    public String index() {
        return "/channelingManager/documentRepository/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/channelingManager/documentRepository/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("userId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<ActivityReport, UploadFileGeneric> joinArUf = r.join("uploadFileGeneric");
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("creationDate"));
                    add(r.get("reportName"));
                    add(r.get("description"));
                    add(joinArUf.get("id").alias("fileId"));
                    add(joinArUf.get("fileName"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                if (field.equals("fileName"))
                    return r.join("uploadFileGeneric").get("fileName");
                if (field.equals("userId"))
                    return r.join("creatorUser").get("id");
                return null;
            }
        }, ActivityReport.class, ActivityReportView.class);

        return result;
    }

    @RequestMapping(value = "/channelingManager/documentRepository/upsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView upsertActivityReport(@RequestParam(required = false) Long id) {
        ModelAndView model = new ModelAndView("/channelingManager/documentRepository/upsert");
        return model;
    }

}
