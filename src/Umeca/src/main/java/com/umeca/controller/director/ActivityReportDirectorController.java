package com.umeca.controller.director;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.activityReport.WizardActivityReport;
import com.umeca.model.entities.director.activityReport.WizardActivityReportView;
import com.umeca.model.entities.director.activityReport.WizardReportModel;
import com.umeca.model.entities.director.agenda.ActivityAgenda;
import com.umeca.model.entities.director.agenda.ActivityAgendaView;
import com.umeca.model.entities.director.project.Project;
import com.umeca.model.entities.director.project.ProjectActivity;
import com.umeca.model.entities.director.project.ProjectActivityView;
import com.umeca.model.entities.shared.activityReport.ActivityReport;
import com.umeca.model.entities.shared.activityReport.ActivityReportView;
import com.umeca.model.shared.Constants;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.director.ActivityReportWizardService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.umeca.model.shared.MonitoringConstants.STATUS_ACTIVITY_DELETED;

@Controller
public class ActivityReportDirectorController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/director/activityReport/index", method = RequestMethod.GET)
    public String index() {
        return "/director/activityReport/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/director/activityReport/list"}, method = RequestMethod.POST)
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

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("reportDate"));
                    add(r.get("reportName"));
                    add(r.get("reportDescription"));
                    add(r.get("creationDate"));
                    add(r.join("creatorUser").get("username"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("stReportDate"))
                    return r.get("reportDate");
                if (field.equals("stCreationTime"))
                    return r.get("creationDate");
                if (field.equals("creatorUserName"))
                    return r.join("creatorUser").get("username");
                if (field.equals("userId"))
                    return r.join("creatorUser").get("id");
                return null;
            }
        }, WizardActivityReport.class, WizardActivityReportView.class);

        return result;
    }


    @RequestMapping(value = "/director/activityReport/wizardUpsert", method = RequestMethod.GET)
    public @ResponseBody ModelAndView wizardUpsert() {
        ModelAndView model = new ModelAndView("/director/activityReport/wizardUpsert");

        Calendar today = CalendarExt.getToday();
        model.addObject("todayDate", CalendarExt.calendarToFormatString(today, Constants.FORMAT_CALENDAR_II));
        today.add(Calendar.DAY_OF_MONTH, -15);
        model.addObject("startDate", CalendarExt.calendarToFormatString(today, Constants.FORMAT_CALENDAR_II));
        today.add(Calendar.DAY_OF_MONTH, 30);
        model.addObject("endDate", CalendarExt.calendarToFormatString(today, Constants.FORMAT_CALENDAR_II));

        return model;
    }

    @RequestMapping(value = {"/director/activityReport/listActivity"}, method = RequestMethod.POST)
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

    @RequestMapping(value = {"/director/activityReport/listSupervision"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listSupervision(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Integer.toString(Constants.ACT_REPORT_FOR_DIRECTOR), JqGridFilterModel.COMPARE_EQUAL);
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

    @RequestMapping(value = {"/director/activityReport/listEvaluation"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listEvaluation(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Integer.toString(Constants.ACT_REPORT_FOR_DIRECTOR), JqGridFilterModel.COMPARE_EQUAL);
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


    @RequestMapping(value = {"/director/activityReport/listDirector"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listDirector(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Integer.toString(Constants.ACT_REPORT_FOR_DIRECTOR), JqGridFilterModel.COMPARE_EQUAL);
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


    @RequestMapping(value = {"/director/activityReport/listChanneling"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listChanneling(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Integer.toString(Constants.ACT_REPORT_FOR_DIRECTOR), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportRole", Constants.ROLE_CHANNELING_MANAGER, JqGridFilterModel.COMPARE_EQUAL);
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


    @RequestMapping(value = {"/director/activityReport/listProject"}, method = RequestMethod.POST)
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

    @RequestMapping(value = {"/director/activityReport/listHumanRes"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listHumanRes(@ModelAttribute JqGridFilterModel opts, String startDate, String endDate) {

        if(StringExt.isNullOrWhiteSpace(startDate) || StringExt.isNullOrWhiteSpace(endDate)) {
            return null;
        }

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("reportFor", Integer.toString(Constants.ACT_REPORT_FOR_DIRECTOR), JqGridFilterModel.COMPARE_EQUAL);
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

    @Autowired
    ActivityReportWizardService activityReportWizardService;

    @RequestMapping(value = "/director/activityReport/doWizardUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doWizardUpsert(@RequestBody WizardReportModel req) {
        ResponseMessage response = new ResponseMessage();
        try {
            response.setHasError(true);
            response.setTitle("Informe de actividades");

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            if(activityReportWizardService.valid(req, response) == false){
                return response;
            }

            activityReportWizardService.saveReport(req, user);

            response.setUrlToGo("/director/activityReport/index.html");
            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doWizardUpsert", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }

        return response;
    }


    @RequestMapping(value = "/director/activityReport/doObsolete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doObsolete(@RequestParam(required = true) Long id) {

        ResponseMessage response = new ResponseMessage();
        try {
            response.setTitle("Informe de actividades");

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            activityReportWizardService.doObsolete(user, id, response);

            return response;

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doObsolete", userService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
            return response;
        }
    }

    @RequestMapping(value = "/director/activityReport/downloadFiles", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFileByCase(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
        try {
/*
            List<UploadFile> lstUpFiles = upDwFileService.getUploadFilesByCaseId(id);
            if (lstUpFiles == null || lstUpFiles.size() == 0) {
                File file = new File(UUID.randomUUID().toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("<html><body><h3>No existen archivos para generar el expediente.</h3></body></html>");
                writer.flush();
                return new FileSystemResource(file);
            }

            CaseInfo caseInfo = caseRepository.getInfoById(id);
            File fileOut = new File("Expediente - " + caseInfo.getPersonName() + ".zip");

            FileOutputStream fos = new FileOutputStream(fileOut);
            ZipOutputStream zos = new ZipOutputStream(fos);
            byte[] buffer = new byte[1024];

            for (UploadFile file : lstUpFiles) {

                ZipEntry ze = new ZipEntry(file.getFileName());
                zos.putNextEntry(ze);

                String path = request.getSession().getServletContext().getRealPath("");
                File fileIn = new File(path, file.getPath());
                FileInputStream in = new FileInputStream(new File(fileIn, file.getRealFileName()));

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }
            zos.closeEntry();
            zos.close();

            response.setContentType("application/force-download");
            response.setContentLength((int) fileOut.length());
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileOut.getName() + "\"");//fileName);

            return new FileSystemResource(fileOut);
           */
            return null;
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "downloadFiles", userService);
            try {
                File file = new File(UUID.randomUUID().toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("<html><body><h3>Ocurrió un error al momento de descargar el informe. Por favor intente de nuevo.</h3></body></html>");
                writer.flush();
                return new FileSystemResource(file);
            } catch (IOException ex) {
                logException.Write(ex, this.getClass(), "downloadFiles", userService);
                return null;
            }
        }
    }
}
