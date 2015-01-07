package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.shared.CommentRequest;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangementLog;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanLog;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanView;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.FulfillmentReportTypeRepository;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.LogCaseService;
import com.umeca.service.shared.MainPageService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ManageMonitoringPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
public class LogController {
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    private SharedUserService userService;
    @Autowired
    private FulfillmentReportTypeRepository fulfillmentReportTypeRepository;

    @RequestMapping(value = "/supervisor/log/index", method = RequestMethod.GET)
    public ModelAndView  index() {
        ModelAndView model = new ModelAndView("/supervisor/log/index");

        Gson gson = new Gson();
        String sLstGeneric ;
        List<SelectList> lstGeneric = fulfillmentReportTypeRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstFulfillmentReport", sLstGeneric);

        return model;
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/log/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<MonitoringPlan, Case> joinCd = r.join("caseDetention");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinIm = joinCd.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>() {{
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
                    add(r.get("statusLog"));
                    add(r.get("posAuthorizationChangeTime"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if (field.equals("stCreationTime"))
                    return r.get("creationTime");
                if (field.equals("stGenerationTime"))
                    return r.get("generationTime");
                if (field.equals("stAuthorizationTime"))
                    return r.get("authorizationTime");
                if (field.equals("supervisorId"))
                    return r.join("supervisor").get("id");
                return null;
            }
        }, MonitoringPlan.class, MonitoringPlanView.class);
        return result;
    }

    @Autowired
    private HearingFormatRepository hearingFormatRepository;
    @Autowired
    private ArrangementRepository arrangementRepository;
    @Autowired
    private ActivityGoalRepository activityGoalRepository;
    @Autowired
    private SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    private ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    private MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    private FramingReferenceRepository framingReferenceRepository;
    @Autowired
    private LogCaseService logCaseService;

    @RequestMapping(value = {"/supervisor/log/supervisionLog", "/supervisorManager/log/supervisionLog"}, method = RequestMethod.GET)
    public ModelAndView supervisionLog(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/log/supervisionLog");

        try {
            Gson gson = new Gson();
            Long caseId = monitoringPlanRepository.getCaseIdByMonPlan(id);
            String sLstGeneric ;
            List<SelectList> lstGeneric = new ArrayList<>();
            //Find last hearing format to get last assigned arrangements
            logCaseService.fillgeneralDataLog(caseId, model);
            model.addObject("titleDoc","ESTRATEGIA DE SUPERVISI&Oacute;N");
            model.addObject("caseId", caseId);

            lstGeneric = framingReferenceRepository.findAllValidByCaseId(caseId);
            sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstSources", sLstGeneric);

            List<ActivityMonitoringPlanLog> lstActMonPlan = activityMonitoringPlanRepository.getListByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlan);
            model.addObject("lstActMonPlan", sLstGeneric);

            List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement = activityMonitoringPlanRepository.getListActMonPlanArrangementByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlanArrangement);
            model.addObject("lstActMonPlanArrangement", sLstGeneric);

            model.addObject("lstRisk", gson.toJson(framingMeetingRepository.getSelectedTRiskByIdCase(caseId)));
            model.addObject("lstThreat", gson.toJson(framingMeetingRepository.getSelectedThreatByIdCase(caseId)));

            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "supervisionLog", userService);
            return null;
        }
    }

    @Autowired
    private FramingMeetingRepository framingMeetingRepository;

    @RequestMapping(value = {"/supervisor/log/accomplishmentLog", "/supervisorManager/log/accomplishmentLog"}, method = RequestMethod.GET)
    public ModelAndView accomplishmentLog(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/log/accomplishmentLog");

        try {
            Long caseId = monitoringPlanRepository.getCaseIdByMonPlan(id);
            logCaseService.fillgeneralDataLog(caseId,model);
            Gson gson = new Gson();
            List<SelectList> lstGeneric = framingReferenceRepository.findAllValidByCaseId(caseId);
            String sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstSources", sLstGeneric);

            List<ActivityMonitoringPlanLog> lstActMonPlan = activityMonitoringPlanRepository.getListAccomplishmentByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlan);
            model.addObject("lstActMonPlan", sLstGeneric);

            List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement = activityMonitoringPlanRepository.getListAccomplishmentActMonPlanArrangementByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlanArrangement);
            model.addObject("lstActMonPlanArrangement", sLstGeneric);
            model.addObject("titleDoc","REPORTE DE INCUMPLIMIENTO Y CUMPLIMIENTO");

            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "supervisionLog", userService);
            return null;
        }
    }

    @RequestMapping(value = "/supervisor/log/accomplishmentFile", method = RequestMethod.GET)
    public ModelAndView generateFile(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/supervisor/log/accomplishmentFile");
        logCaseService.fillModelAccomplishmentFile(id, model);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"reporteIncumplimiento.doc\"");


        return model;
    }

    @RequestMapping(value = "/supervisor/log/fillByFilter", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage filterLog(@RequestParam Long id, @RequestParam Long assignedArrangementId, @RequestParam Long activityId) { //Id de MonitoringPlan
        ResponseMessage response = new ResponseMessage();

        try {
            Gson gson = new Gson();
            List<ActivityMonitoringPlanLog> lstActMonPlan = new ArrayList<>();
            if (activityId == 0) {
                lstActMonPlan = activityMonitoringPlanRepository.getListByMonPlanId(id);
            } else {
                lstActMonPlan = activityMonitoringPlanRepository.getListByMonPlanIdWhitArrangementId(id, assignedArrangementId, activityId);
            }
            response.setReturnData(gson.toJson(lstActMonPlan));
            response.setHasError(false);
            return response;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "requestAccomplishmentLog", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
            return response;
        }
    }


    @Autowired
    ManageMonitoringPlanService manageMonitoringPlanService;

    @RequestMapping(value = "/supervisor/log/requestAccomplishmentLog", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage requestAccomplishmentLog(@RequestParam Long id, @RequestParam Long fulfillmentReportId) { //Id de MonitoringPlan
        ResponseMessage response = new ResponseMessage();

        try {
            response.setTitle("Reporte de incumplimiento");

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            if (manageMonitoringPlanService.requestAccomplishmentLog(id, fulfillmentReportId, user, MonitoringConstants.LOG_PENDING_ACCOMPLISHMENT,
                    "Solicitud de la autorización del reporte de incumplimiento por parte del usuario " + user.getUsername(), response) == false) {
                response.setHasError(true);
                return response;
            }

            response.setHasError(false);
            return response;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "requestAccomplishmentLog", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
            return response;
        }
    }

    @Autowired
    MainPageService mainPageService;

    @RequestMapping(value = {"/supervisorManager/log/deleteComment", "/supervisor/log/deleteComment"}, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage deleteComment(@RequestBody CommentRequest model) {
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Eliminar comentarios");
        response.setHasError(true);

        try {
            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            List<String> lstRole = userService.getLstRolesByUserId(user.getId());

            if (lstRole == null || lstRole.size() == 0) {
                response.setMessage("Usted no tiene los permisos para realizar esta operación");
                return response;
            }

            if (mainPageService.deleteComment(lstRole.get(0), model, user, response) == false)
                return response;

            response.setReturnData(model.getId());
            response.setHasError(false);
            return response;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteComment", userService);
            response.setHasError(true);
        }
        response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        return response;
    }

}
