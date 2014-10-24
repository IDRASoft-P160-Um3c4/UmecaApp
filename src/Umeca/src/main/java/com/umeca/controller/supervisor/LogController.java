package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.shared.CommentRequest;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class LogController {
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    private SharedUserService userService;

    @RequestMapping(value = "/supervisor/log /index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/log/index";
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

    @RequestMapping(value = {"/supervisor/log/supervisionLog", "/supervisorManager/log/supervisionLog"}, method = RequestMethod.GET)
    public ModelAndView supervisionLog(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/log/supervisionLog");

        try {
            Long caseId = monitoringPlanRepository.getCaseIdByMonPlan(id);

            //Find last hearing format to get last assigned arrangements
            List<Long> lastHearingFormatId = hearingFormatRepository.getLastHearingFormatByMonPlan(id, new PageRequest(0, 1));
            Long lHearingFormatId = lastHearingFormatId.get(0);
            SupervisionLogReport slr = hearingFormatRepository.findSupervisionLogReportById(lHearingFormatId);

            model.addObject("imputedName", slr.getImputedName());
            model.addObject("mpId", id);
            model.addObject("crime", slr.getCrime());
            model.addObject("judge", slr.getJudge());
            model.addObject("defender", slr.getDefender());
            model.addObject("mp", slr.getMp());
            model.addObject("imputedTel", slr.getImputedTel());
            model.addObject("imputedAddr", slr.getImputedAddr());


            List<SelectList> lstMoral = framingReferenceRepository.findAccompanimentReferences(id);

            String cad = "";
            for (SelectList act : lstMoral) {
                if (cad != "")
                    cad += " / ";
                cad += act.getName() + " - " + act.getDescription();
            }

            model.addObject("moralName", cad);
            //model.addObject("moralPhone", slr.getMoralPhone());

            List<SelectList> lstResol = hearingFormatRepository.getInfoResolution(id);

            String lastResol = "", allResol = "";

            if (lstResol != null && lstResol.size() > 0) {
                for (int i = 0; i < lstResol.size(); i++) {

                    if (allResol != "")
                        allResol += " , ";

                    allResol += CalendarExt.calendarToFormatString(lstResol.get(i).getCalendar(), Constants.FORMAT_CALENDAR_I);

                    if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_MC)
                        allResol += " - MC";
                    else if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_SCP)
                        allResol += " - SCPP";
                }
            }

            String[] arr = allResol.split(",");
            if (arr.length > 0)
                lastResol = arr[arr.length - 1];
            else
                lastResol = allResol;

            model.addObject("prevResolution", allResol);
            model.addObject("lastResolution", lastResol);

            String closeComment = "";
            closeComment = monitoringPlanRepository.getCloseComment(id, MonitoringConstants.STATUS_PENDING_END, Constants.CASE_STATUS_CLOSED);

            model.addObject("closeComment", closeComment);

            List<SelectList> lstGeneric = arrangementRepository.findLstArrangementByCaseId(caseId);
            Gson gson = new Gson();
            String sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstHfAssignedArrangement", sLstGeneric);

            lstGeneric = supervisionActivityRepository.findByMonPlanId(id);
            sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstActivities", sLstGeneric);

            lstGeneric = activityGoalRepository.findByMonPlanId(id);
            sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstGoals", sLstGeneric);

            lstGeneric = framingReferenceRepository.findAllValidByCaseId(caseId);
            sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstSources", sLstGeneric);

            List<ActivityMonitoringPlanLog> lstActMonPlan = activityMonitoringPlanRepository.getListByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlan);
            model.addObject("lstActMonPlan", sLstGeneric);

            List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement = activityMonitoringPlanRepository.getListActMonPlanArrangementByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlanArrangement);
            model.addObject("lstActMonPlanArrangement", sLstGeneric);

            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "supervisionLog", userService);
            return null;
        }
    }

    //TODO DISEÑAR LA BITACORA PARA PODER DESCARGARLA EN FORMATO WORD
/*    @RequestMapping(value = {"/supervisor/log/supervisionLogDownload", "/supervisorManager/log/supervisionLogDownload"}, method = RequestMethod.GET)
    public ModelAndView supervisionLogDownload(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/log/supervisionLogDown");

        try {
            Long caseId = monitoringPlanRepository.getCaseIdByMonPlan(id);

            //Find last hearing format to get last assigned arrangements
            List<Long> lastHearingFormatId = hearingFormatRepository.getLastHearingFormatByMonPlan(id, new PageRequest(0, 1));
            Long lHearingFormatId = lastHearingFormatId.get(0);
            SupervisionLogReport slr = hearingFormatRepository.findSupervisionLogReportById(lHearingFormatId);

            model.addObject("imputedName", slr.getImputedName());
            model.addObject("mpId", id);
            model.addObject("crime", slr.getCrime());
            model.addObject("judge", slr.getJudge());
            model.addObject("defender", slr.getDefender());
            model.addObject("mp", slr.getMp());
            model.addObject("imputedTel", slr.getImputedTel());
            model.addObject("imputedAddr", slr.getImputedAddr());


            List<SelectList> lstMoral = framingReferenceRepository.findAccompanimentReferences(id);

            String cad = "";
            for (SelectList act : lstMoral) {
                if (cad != "")
                    cad += " / ";
                cad += act.getName() + " - " + act.getDescription();
            }

            model.addObject("moralName", cad);

            List<SelectList> lstResol = hearingFormatRepository.getInfoResolution(id);

            String lastResol = "", allResol = "";

            if (lstResol != null && lstResol.size() > 0) {
                for (int i = 0; i < lstResol.size(); i++) {

                    if (allResol != "")
                        allResol += " , ";

                    allResol += CalendarExt.calendarToFormatString(lstResol.get(i).getCalendar(), Constants.FORMAT_CALENDAR_I);

                    if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_MC)
                        allResol += " - MC";
                    else if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_SCP)
                        allResol += " - SCPP";
                }
            }

            String[] arr = allResol.split(",");
            if (arr.length > 0)
                lastResol = arr[arr.length - 1];
            else
                lastResol = allResol;

            model.addObject("prevResolution", allResol);
            model.addObject("lastResolution", lastResol);

            String closeComment = "";
            closeComment = monitoringPlanRepository.getCloseComment(id, MonitoringConstants.STATUS_PENDING_END, Constants.CASE_STATUS_CLOSED);

            model.addObject("closeComment", closeComment);

            List<SelectList> lstGeneric = arrangementRepository.findLstArrangementByCaseId(caseId);
            Gson gson = new Gson();
            //String sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstHfAssignedArrangement", lstGeneric);

            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "supervisionLog", userService);
            return null;
        }
    }*/
    //TODO DISEÑAR LA BITACORA PARA PODER DESCARGARLA EN FORMATO WORD

    @RequestMapping(value = {"/supervisor/log/accomplishmentLog", "/supervisorManager/log/accomplishmentLog"}, method = RequestMethod.GET)
    public ModelAndView accomplishmentLog(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/log/accomplishmentLog");

        try {
            Long caseId = monitoringPlanRepository.getCaseIdByMonPlan(id);

            //Find last hearing format to get last assigned arrangements
            List<Long> lastHearingFormatId = hearingFormatRepository.getLastHearingFormatByMonPlan(id, new PageRequest(0, 1));
            Long lHearingFormatId = lastHearingFormatId.get(0);
            AccomplishmentLogReport alr = hearingFormatRepository.findSupervisionLogAccomplishmentById(lHearingFormatId);

            model.addObject("imputedName", alr.getImputedName());
            model.addObject("mpId", alr.getMpId());
            model.addObject("address", alr.getAddress());

            List<SelectList> lstGeneric = arrangementRepository.findLstArrangementByCaseId(caseId);
            Gson gson = new Gson();
            String sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstHfAssignedArrangement", sLstGeneric);

            lstGeneric = supervisionActivityRepository.findByMonPlanId(id);
            sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstActivities", sLstGeneric);

            lstGeneric = activityGoalRepository.findByMonPlanId(id);
            sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstGoals", sLstGeneric);

            lstGeneric = framingReferenceRepository.findAllValidByCaseId(caseId);
            sLstGeneric = gson.toJson(lstGeneric);
            model.addObject("lstSources", sLstGeneric);

            List<ActivityMonitoringPlanLog> lstActMonPlan = activityMonitoringPlanRepository.getListAccomplishmentByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlan);
            model.addObject("lstActMonPlan", sLstGeneric);

            List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement = activityMonitoringPlanRepository.getListAccomplishmentActMonPlanArrangementByMonPlanId(id);
            sLstGeneric = gson.toJson(lstActMonPlanArrangement);
            model.addObject("lstActMonPlanArrangement", sLstGeneric);

            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "supervisionLog", userService);
            return null;
        }
    }

    @RequestMapping(value = "/supervisor/log/fillByFilter", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage filterLog(@RequestParam Long id, @RequestParam Long activityId) { //Id de MonitoringPlan
        ResponseMessage response = new ResponseMessage();

        try {
            Gson gson = new Gson();
            List<ActivityMonitoringPlanLog> lstActMonPlan = new ArrayList<>();
            if (activityId == 0) {
                lstActMonPlan = activityMonitoringPlanRepository.getListByMonPlanId(id);
            } else {
                lstActMonPlan = activityMonitoringPlanRepository.getListByMonPlanIdWhitArrangementId(id, activityId);
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
    ResponseMessage requestAccomplishmentLog(@RequestParam Long id) { //Id de MonitoringPlan
        ResponseMessage response = new ResponseMessage();

        try {
            response.setTitle("Reporte de incumplimiento");

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            if (manageMonitoringPlanService.requestAccomplishmentLog(id, user, MonitoringConstants.LOG_PENDING_ACCOMPLISHMENT,
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
