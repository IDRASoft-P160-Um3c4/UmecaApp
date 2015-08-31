package com.umeca.controller.reviewer;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.reviewer.View.CaseReportView;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileAllSourcesView;
import com.umeca.model.entities.shared.Event;
import com.umeca.model.shared.Constants;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.CaseReportService;
import com.umeca.service.reviewer.TechnicalReviewService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CaseReportController {


    @Autowired
    SharedUserService userService;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    CaseReportService caseReportService;


    @RequestMapping(value = "/reviewer/caseReport/index", method = RequestMethod.GET)
    public String index() {
        return "/reviewer/caseReport/index";
    }

    @RequestMapping(value = {"/reviewer/caseReport/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("reviewerId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("statusCode", Constants.S_MEETING_COMPLETE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("statusCase", Constants.CASE_STATUS_NOT_PROSECUTE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("eventCode", Constants.EVENT_CASE_REPORT, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Event, Case> joinC = r.join("caseDetention");
                final javax.persistence.criteria.Join<Case, Meeting> joinM = joinC.join("meeting");
                final javax.persistence.criteria.Join<Meeting, StatusMeeting> joinSM = joinM.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinUsr = joinM.join("reviewer");

                return new ArrayList<Selection<?>>() {{
                    add(joinC.get("id"));
                    add(joinSM.get("name").alias("statusCode"));
                    add(joinC.get("idFolder"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinImp.get("gender"));
                    add(joinSM.get("description"));
                    add(joinUsr.get("id").alias("reviewerId"));
                    add(joinUsr.get("fullname").alias("reviewerName"));
                    add(joinC.join("status").get("name").alias("statusCase"));
                    add(r.join("eventType").get("description").alias("eventString"));
                    add(r.join("eventType").get("name").alias("eventCode"));
                    add(r.get("id").alias("eventId"));

                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCode"))
                    return r.join("caseDetention").join("meeting").join("status").get("name");
                else if (field.equals("reviewerId"))
                    return r.join("caseDetention").join("meeting").join("reviewer").get("id");
                else if (field.equals("statusCase"))
                    return r.join("caseDetention").join("status").get("name");
                else if (field.equals("statusCase"))
                    return r.join("caseDetention").join("status").get("name");
                else if (field.equals("eventCode"))
                    return r.join("eventType").get("name");
                else
                    return null;
            }
        }, Event.class, CaseReportView.class);

        return result;

    }


    @RequestMapping(value = "/reviewer/caseReport/printSheet", method = RequestMethod.GET)
    public ModelAndView printSheet(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = null;
        try {

            CaseReportView sheetInfo = caseReportService.getCaseReportSheetById(id);
            //ChannelingModelSheet sheetInfo = channelingService.getChannelingSheetById(id);

            if (sheetInfo == null) {
                model = new ModelAndView("/reviewer/caseReport/notSheet");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-informe.doc\"");
                return model;
            }


            TechnicalReviewInfoFileAllSourcesView dataFile = caseReportService.fillInfoFileAllSourcesFromEvent(id);


            model = new ModelAndView("/reviewer/caseReport/printSheet");
            model.addObject("data", dataFile);
            model.addObject("dataEvent", sheetInfo);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"informe-" + sheetInfo.getName() + sheetInfo.getLastNameP() + sheetInfo.getLastNameM() + ".doc\"");

            //channelingService.addLogChannelingDoc(sheetInfo.getIdCase(),sheetInfo.getChannelingType());
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "printSheetCaseReport", userService);
            model = null;
        }
        return model;
    }

}
