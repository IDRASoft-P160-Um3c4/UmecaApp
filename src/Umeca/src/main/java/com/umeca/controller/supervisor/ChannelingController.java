package com.umeca.controller.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.District;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.activityReport.ActivityReportDirector;
import com.umeca.model.entities.director.activityReport.ActivityReportDirectorView;
import com.umeca.model.entities.director.agenda.ActivityAgenda;
import com.umeca.model.entities.director.agenda.ActivityAgendaView;
import com.umeca.model.entities.director.project.Project;
import com.umeca.model.entities.director.project.ProjectActivity;
import com.umeca.model.entities.director.project.ProjectActivityView;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.shared.activityReport.ActivityReport;
import com.umeca.model.entities.shared.activityReport.ActivityReportView;
import com.umeca.model.entities.supervisor.ChannelingCaseView;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.shared.Constants;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.umeca.model.shared.MonitoringConstants.STATUS_ACTIVITY_DELETED;

@Controller
public class ChannelingController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/channeling/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/channeling/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/supervisor/channeling/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCase",
                new ArrayList<String>() {{add(Constants.CASE_STATUS_CLOSED);}}, JqGridFilterModel.COMPARE_NOT_IN);
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


    @RequestMapping(value = "/supervisor/channeling/wizardUpsert", method = RequestMethod.GET)
    public @ResponseBody ModelAndView wizardUpsert() {
        ModelAndView model = new ModelAndView("/supervisor/channeling/wizardUpsert");

        Calendar today = CalendarExt.getToday();
        today.add(Calendar.DAY_OF_MONTH, -15);
        model.addObject("startDate", CalendarExt.calendarToFormatString(today, Constants.FORMAT_CALENDAR_II));
        today.add(Calendar.DAY_OF_MONTH, 30);
        model.addObject("endDate", CalendarExt.calendarToFormatString(today, Constants.FORMAT_CALENDAR_II));

        return model;
    }

    @RequestMapping(value = {"/supervisor/channeling/listActivity"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listActivity(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("userId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("status", STATUS_ACTIVITY_DELETED, JqGridFilterModel.COMPARE_NOT_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("start", CalendarExt.stringToCalendar(startDate, Constants.FORMAT_CALENDAR_II)
                , CalendarExt.stringToCalendar(endDate, Constants.FORMAT_CALENDAR_II) , JqGridFilterModel.BETWEEN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("place"));
                    add(r.get("description"));
                    add(r.get("start"));
                    add(r.get("status"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                if (field.equals("userId"))
                    return r.join("userOwner").get("id");
                return null;
            }
        }, ActivityAgenda.class, ActivityAgendaView.class);

        return result;
    }

    @RequestMapping(value = {"/supervisor/channeling/listSupervision"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listSupervision(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Constants.ACT_REPORT_FOR_DIRECTOR.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportRole", Constants.ROLE_SUPERVISOR_MANAGER, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("creationDate", CalendarExt.stringToCalendar(startDate, Constants.FORMAT_CALENDAR_II)
                , CalendarExt.stringToCalendar(endDate, Constants.FORMAT_CALENDAR_II) , JqGridFilterModel.BETWEEN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("reportName"));
                    add(r.get("description"));
                    add(r.get("creationDate"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                return null;
            }
        }, ActivityReport.class, ActivityReportView.class);

        return result;
    }

    @RequestMapping(value = {"/supervisor/channeling/listEvaluation"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listEvaluation(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Constants.ACT_REPORT_FOR_DIRECTOR.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportRole", Constants.ROLE_EVALUATION_MANAGER, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("creationDate", CalendarExt.stringToCalendar(startDate, Constants.FORMAT_CALENDAR_II)
                , CalendarExt.stringToCalendar(endDate, Constants.FORMAT_CALENDAR_II) , JqGridFilterModel.BETWEEN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("reportName"));
                    add(r.get("description"));
                    add(r.get("creationDate"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                return null;
            }
        }, ActivityReport.class, ActivityReportView.class);

        return result;
    }


    @RequestMapping(value = {"/supervisor/channeling/listDirector"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listDirector(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Constants.ACT_REPORT_FOR_DIRECTOR.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportRole", Constants.ROLE_DIRECTOR, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("creationDate", CalendarExt.stringToCalendar(startDate, Constants.FORMAT_CALENDAR_II)
                , CalendarExt.stringToCalendar(endDate, Constants.FORMAT_CALENDAR_II) , JqGridFilterModel.BETWEEN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("reportName"));
                    add(r.get("description"));
                    add(r.get("creationDate"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                return null;
            }
        }, ActivityReport.class, ActivityReportView.class);

        return result;
    }


    @RequestMapping(value = {"/supervisor/channeling/listProject"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listProject(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("creationDate", CalendarExt.stringToCalendar(startDate, Constants.FORMAT_CALENDAR_II)
                , CalendarExt.stringToCalendar(endDate, Constants.FORMAT_CALENDAR_II) , JqGridFilterModel.BETWEEN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<ProjectActivity, Project> joinPrPrAc = r.join("project");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinPrPrAc.get("name"));
                    add(r.get("name"));
                    add(r.get("description"));
                    add(r.get("creationDate"));
                    add(joinPrPrAc.get("status"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                if (field.equals("name"))
                    return r.join("project").get("name");
                if (field.equals("activityName"))
                    return r.get("name");
                if (field.equals("activityDescription"))
                    return r.get("description");
                if (field.equals("status"))
                    return r.join("project").get("status");
                return null;
            }
        }, ProjectActivity.class, ProjectActivityView.class);

        return result;
    }

    @RequestMapping(value = {"/supervisor/channeling/listHumanRes"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listHumanRes(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Constants.ACT_REPORT_FOR_DIRECTOR.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportRole", Constants.ROLE_HUMAN_RESOURCES, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("creationDate", CalendarExt.stringToCalendar(startDate, Constants.FORMAT_CALENDAR_II)
                , CalendarExt.stringToCalendar(endDate, Constants.FORMAT_CALENDAR_II) , JqGridFilterModel.BETWEEN);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("reportName"));
                    add(r.get("description"));
                    add(r.get("creationDate"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                return null;
            }
        }, ActivityReport.class, ActivityReportView.class);

        return result;
    }



}
