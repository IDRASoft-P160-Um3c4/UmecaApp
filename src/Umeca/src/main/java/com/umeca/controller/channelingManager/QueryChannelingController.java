package com.umeca.controller.channelingManager;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.CatChannelingInstitutionName;
import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.CatInstitutionType;
import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileView;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.GridService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ChannelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QueryChannelingController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/channelingManager/queryChanneling/index", method = RequestMethod.GET)
    public String index() {
        return "/channelingManager/queryChanneling/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/channelingManager/queryChanneling/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCase",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_CLOSED);
                }}, JqGridFilterModel.COMPARE_NOT_IN);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isTerminated", "1", JqGridFilterModel.COMPARE_EQUAL);
        extraFilter.setbData(true);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMeVe = r.join("meeting");
                final Join<Meeting, Imputed> joinMee = joinMeVe.join("imputed");
                final Join<Case, District> joinDi = r.join("district");
                final Join<Case, FramingMeeting> joinFr = r.join("framingMeeting");
                final Join<FramingMeeting, User> joinFrUs = joinFr.join("supervisor");
                final Join<Case, Channeling> joinChann = r.join("lstChanneling");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("idMP"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinDi.get("name"));
                    add(joinFrUs.get("fullname"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCase"))
                    return r.join("status").get("name");
                if (field.equals("isTerminated"))
                    return r.join("framingMeeting").get("isTerminated");
                if (field.equals("imputed"))
                    return r.join("meeting").join("imputed").get("name");
                if (field.equals("district"))
                    return r.join("district").get("name");
                if (field.equals("supervisor"))
                    return r.join("framingMeeting").join("supervisor").get("fullname");
                return null;
            }
        }, Case.class, ChannelingCaseView.class);

        return result;
    }

    @Autowired
    GridService gridService;

    @RequestMapping(value = {"/channelingManager/queryChanneling/listB"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listB(@ModelAttribute JqGridFilterModel opts) {
        try {
            return gridService.toGrid(QueryChannelingView.class, null, opts);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "listB", userService);
            return null;
        }
    }

    @RequestMapping(value = {"/channelingManager/queryChanneling/listChannelings"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts, Long id) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("caseId", id.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Channeling, CatChannelingType> joinChTy = r.join("channelingType");
                final Join<Channeling, CatInstitutionType> joinInTy = r.join("institutionType");
                final Join<Channeling, CatChannelingInstitutionName> joinChIn = r.join("institutionName");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("consecutive"));
                    add(r.get("isAuthorizeToDrop"));
                    add(joinChTy.get("name"));
                    add(r.get("name"));
                    add(joinInTy.get("name"));
                    add(joinChIn.get("name"));
                    add(r.get("isFulfilled"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if (field.equals("channelingType"))
                    return r.join("channelingType").get("name");
                if (field.equals("institutionType"))
                    return r.join("institutionType").get("name");
                if (field.equals("institutionName"))
                    return r.join("institutionName").get("name");
                if (field.equals("isFulfilledTx"))
                    return r.get("isFulfilled");
                return null;
            }
        }, Channeling.class, ChannelingView.class);

        return result;
    }


    @Autowired
    ChannelingService channelingService;

    @RequestMapping(value = "/channelingManager/queryChanneling/reportAttendance", method = RequestMethod.GET)
    public ModelAndView reportAttendance(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = null;
        try {
            ChannelingModelSheet sheetInfo = channelingService.getChannelingSheetById(id);

            if (sheetInfo == null) {
                model = new ModelAndView("/channelingManager/queryChanneling/notReport");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-informe.doc\"");
                return model;
            }

            List<ActivityChannelingModel> lstActivitiesChanneling = channelingService.getLstActivitiesChanneling(id);

            if (lstActivitiesChanneling == null ||  lstActivitiesChanneling.size() < 1) {
                model = new ModelAndView("/channelingManager/queryChanneling/notReport");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-actividades.doc\"");
                return model;
            }

            model = new ModelAndView("/channelingManager/queryChanneling/reportAttendance");
            model.addObject("data", sheetInfo);
            model.addObject("lstActAtt", lstActivitiesChanneling);

            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"actividades-" +
                    sheetInfo.getIdMP() + "-" + sheetInfo.getConsecutiveTx() + ".doc\"");


            channelingService.addLogChannelingDoc(sheetInfo.getIdCase(),sheetInfo.getChannelingType());
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "reportAttendance", userService);
            model = null;
        }
        return model;
    }

    @RequestMapping(value = "/channelingManager/queryChanneling/queryChannelingFiles", method = RequestMethod.GET)
    public ModelAndView queryChannelingFiles(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/channelingManager/queryChanneling/queryChannelingFiles");
        model.addObject("caseId", id);
        return model;
    }

    @RequestMapping(value = {"/channelingManager/queryChanneling/queryChannelingFilesList"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel queryChannelingFilesList(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long caseId) {
        Long userId = userService.GetLoggedUserId();

        if (userId == null)
            return null;

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("caseId", caseId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("typeNameCode", new ArrayList<String>(){{add(Constants.CHANNELING_END_CODE);}}, JqGridFilterModel.COMPARE_IN);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("fileName"));
                    add(r.get("description"));
                    add(r.get("size"));
                    add(r.join("creationUser").get("fullname"));
                    add(r.get("creationTime"));
                    add(r.join("caseDetention").get("id"));
                    add(r.join("typeNameFile").get("name").alias("typeName"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("fullname"))
                    return r.join("creationUser").get("fullname");
                if (field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if (field.equals("typeName"))
                    return r.join("typeNameFile").get("name");
                if (field.equals("typeNameCode"))
                    return r.join("typeNameFile").get("code");
                return null;
            }
        }, UploadFile.class, UploadFileView.class);
        return result;
    }

}
