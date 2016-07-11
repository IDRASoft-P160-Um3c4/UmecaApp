package com.umeca.controller.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ConstantsLogCase;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.OptionList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.reviewer.TechnicalReviewRepository;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.GridService;
import com.umeca.service.shared.LogCaseService;
import com.umeca.service.shared.MessageService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.TrackMonPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Controller
public class TrackMonitoringPlanController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/trackMonitoringPlan/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        Long userId = sharedUserService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("supervisorId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("status",
                new ArrayList<String>() {{
                    add(MonitoringConstants.STATUS_PENDING_AUTHORIZATION);
                    add(MonitoringConstants.STATUS_AUTHORIZED);
                    add(MonitoringConstants.STATUS_MONITORING);
                    add(MonitoringConstants.STATUS_SUSPENDED_SUBSTRACTED);
                    add(MonitoringConstants.STATUS_REJECTED_END);
                }}, JqGridFilterModel.COMPARE_IN
        );
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
                    add(joinCd.join("technicalReview", JoinType.LEFT).get("id").alias("idTec"));
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
    GridService gridService;

    @RequestMapping(value = {"/supervisor/trackMonitoringPlan/listB"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listB(@ModelAttribute JqGridFilterModel opts) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("idUser",sharedUserService.GetLoggedUserId());
            return gridService.toGrid(TrackingMonitoringPlanCasesView.class, map, opts);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "listB", sharedUserService);
            return null;
        }
    }


    @Autowired
    private ActivityMonitoringPlanRepository activityMonitoringPlanRepository;
    @Autowired
    private TechnicalReviewRepository qTechnicalReviewRepository;
    @Autowired
    private MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    private CaseRepository caseRepository;


    @RequestMapping(value = "/supervisor/trackMonitoringPlan/trackCalendar", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView generate(@RequestParam(required = false) Long id, @RequestParam(required = false) Integer redirect) { //Id monitoring plan
        ModelAndView model = new ModelAndView("/supervisor/trackMonitoringPlan/trackCalendar");

        try {

            if (id == null) {
                model.addObject("monitoringPlanId", -1);
            } else {
                model.addObject("monitoringPlanId", id);
                trackMonPlanService.notificationNewHearingFormat(id, model);
            }

            model.addObject("urlGetActivities", "/supervisor/trackMonitoringPlan/getActivities.json");
            model.addObject("urlShowActivity", "/supervisor/trackMonitoringPlan/showActivity.html");
            if (redirect == null) {
                model.addObject("urlReturn", "/supervisor/trackMonitoringPlan/index.html");
            } else if (redirect.equals(1)) {
                model.addObject("urlReturn", "/supervisor/manageMonitoringPlan/index.html");
            }
            Long idUser = sharedUserService.GetLoggedUserId();
            List<String> rolesUser = sharedUserService.getLstRolesByUserId(idUser);
            if (!rolesUser.contains(Constants.ROLE_SUPERVISOR)) {
                idUser = 0L;
            }
            model.addObject("idUser", idUser);
            trackMonPlanService.setLstActivitiesSupervision(model);
            trackMonPlanService.setListCaseFilter(model, idUser);
            trackMonPlanService.setListUserFilter(model, idUser);

            Long idCase = monitoringPlanRepository.getCaseIdByMonPlan(id);

            if(idCase != null && idCase.longValue() > 0){
                Long idTec = qTechnicalReviewRepository.getTechnicalReviewByCaseId(idCase);
                if (idTec != null) {
                    model.addObject("idTec", idTec);
                    model.addObject("caseId", idCase);
                }

                model.addObject("isSubstracted", caseRepository.findOne(idCase).getIsSubstracted());
                model.addObject("showIsSubstracted", true);
            }
            else{
                model.addObject("isSubstracted", false);
                model.addObject("showIsSubstracted", false);
            }

            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "generate", sharedUserService);
            throw ex;
        }
    }


    @Autowired
    TrackMonPlanService trackMonPlanService;

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/getActivities", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseActivities getActivities(@RequestBody RequestActivities req) {
        ResponseActivities response = new ResponseActivities();

        try {
            Long userId = sharedUserService.GetLoggedUserId();

            trackMonPlanService.getLstActivitiesByUserAndFilters(req, userId, new ArrayList<String>() {{
                        add(MonitoringConstants.STATUS_PENDING_AUTHORIZATION);
                        add(MonitoringConstants.STATUS_AUTHORIZED);
                        add(MonitoringConstants.STATUS_MONITORING);
                        add(MonitoringConstants.STATUS_PENDING_END);
                        add(MonitoringConstants.STATUS_REJECTED_END);
                        add(MonitoringConstants.STATUS_SUSPENDED_SUBSTRACTED);
                        add(MonitoringConstants.STATUS_END);
                    }},
                    new ArrayList<String>() {{
                        add(MonitoringConstants.STATUS_ACTIVITY_DELETED);
                        add(MonitoringConstants.STATUS_ACTIVITY_PRE_NEW);
                        add(MonitoringConstants.STATUS_ACTIVITY_PRE_MODIFIED);
                        add(MonitoringConstants.STATUS_ACTIVITY_PRE_DELETED);
                    }}, response
            );
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "getActivities", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se sucitó un problema, por favor reinicie su navegador e intente de nuevo.");
            return response;
        }

        return response;
    }

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/showActivity", method = RequestMethod.POST)
    public ModelAndView showActivity(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/trackMonitoringPlan/showActivity");

        try {
            trackMonPlanService.getActivityToShow(id, model);
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "showActivity", sharedUserService);
            return null;
        }
    }

    @Autowired
    LogCaseService logCaseService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/supervisor/trackMonitoringPlan/doActionActivity", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doActionActivity(@ModelAttribute ActionActivity model) {
        ResponseMessage response = new ResponseMessage();
        try {
            String sCommentOk = model.getCommentsOk();
            String sCommentFail = model.getCommentsFail();

            OptionList[] optLst = model.getArrOptArrangementsValues();

            if (optLst == null) {
                response.setHasError(true);
                response.setMessage("No existen estados para las obligaciones procesales");
                return response;
            }

            if ((sCommentOk == null || sCommentOk.trim().isEmpty()) && (sCommentFail == null || sCommentFail.trim().isEmpty())) {
                response.setHasError(true);
                response.setMessage("Debe elegir si la actividad fue realizada o no y debe escribir un comentario");
                return response;
            }

            if ((sCommentOk != null && sCommentOk.trim().isEmpty() == false) && (sCommentFail != null && sCommentFail.trim().isEmpty() == false)) {
                response.setHasError(true);
                response.setMessage("No puede escribir comentarios de realizado o no realizado sobre una misma actividad");
                return response;
            }

            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            ActivityMonitoringPlan activityMonitoringPlan = activityMonitoringPlanRepository.findByIdAndUserId(model.getActMonPlanId(), user.getId());

            if (activityMonitoringPlan == null) {
                response.setHasError(true);
                response.setMessage("Usted no puede establecer la actividad como realizada o no realizada. Revise que usted sea el asignado para la supervisión del caso");
                return response;
            }

            //Validar el estado del plan de seguimiento
            MonitoringPlanDto monPlanDto = monitoringPlanRepository.getMonPlanAuthInfo(activityMonitoringPlan.getMonitoringPlan().getId());

            if (monPlanDto.getMonPlanSuspended()) {
                response.setHasError(true);
                response.setMessage("El plan se encuentra suspendido, por favor conctate a su coordinador para reactivar el plan.");
                return response;
            }

            String status = activityMonitoringPlan.getStatus();
            if (status.equals(MonitoringConstants.STATUS_ACTIVITY_DONE) || status.equals(MonitoringConstants.STATUS_ACTIVITY_FAILED)) {
                response.setHasError(true);
                response.setMessage("Ya fue establecido un estatus de realizada o no realizada.");
                return response;
            }

            if (status.equals(MonitoringConstants.STATUS_ACTIVITY_DELETED)) {
                response.setHasError(true);
                response.setMessage("La actividad fue eliminada.");
                return response;
            }

            boolean bIsFailed;
            String sComments;
            String sStatus;
            if (sCommentOk.trim().isEmpty() == false) {
                sComments = sCommentOk;
                sStatus = MonitoringConstants.STATUS_ACTIVITY_DONE;
                bIsFailed = false;
            } else {
                sComments = sCommentFail;
                sStatus = MonitoringConstants.STATUS_ACTIVITY_FAILED;
                bIsFailed = true;
            }

            List<ActivityMonitoringPlanArrangement> lstAssignedArrangement = activityMonitoringPlan.getLstAssignedArrangement();
            boolean bHasValue;

            if (bIsFailed) {
                for (ActivityMonitoringPlanArrangement ampa : lstAssignedArrangement) {
                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED);
                }
            } else {
                for (ActivityMonitoringPlanArrangement ampa : lstAssignedArrangement) {
                    Long assignedArrangementId = ampa.getAssignedArrangement().getId();
                    bHasValue = false;
                    for (OptionList optionList : optLst) {
                        if (assignedArrangementId.equals(optionList.getId())) {
                            switch (Integer.parseInt(optionList.getValue())) {
                                case MonitoringConstants.ACTIVITY_ARRANGEMENT_DONE:
                                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_DONE);
                                    break;
                                case MonitoringConstants.ACTIVITY_ARRANGEMENT_FAILED:
                                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_FAILED);
                                    break;
                                case MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED:
                                    ampa.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED);
                                    break;
                                default:
                                    response.setHasError(true);
                                    response.setMessage("Se ha asignado un valor no válido para el estado de las obligaciones procesales.");
                                    return response;
                            }
                            bHasValue = true;
                            break;
                        }
                    }

                    if (bHasValue == false) {
                        response.setHasError(true);
                        response.setMessage("No existe un valor asignado para el estado de las obligaciones procesales.");
                        return response;
                    }
                }
            }


            Channeling channeling = activityMonitoringPlan.getChanneling();
            if (channeling != null && activityMonitoringPlan.getActivityGoal() != null && activityMonitoringPlan.getActivityGoal().getId() != null
                    && Constants.LstChannelingNotificationGoal.contains(activityMonitoringPlan.getActivityGoal().getId())) {
                //Asistencia a la canalización
                Integer channelingAssistance = model.getChannelingAssistance();
                if (channelingAssistance != null) {
                    activityMonitoringPlan.setChannelingAssistance(channelingAssistance);

                    try {
                        if (channelingAssistance.equals(0)) {
                            //Notificar inasistencia
                            Case caseDetention = activityMonitoringPlan.getCaseDetention();
                            Imputed imputed = caseDetention.getMeeting().getImputed();
                            String imputedFullName = imputed.getName() + " " + imputed.getLastNameP() + " " + imputed.getLastNameM();
                            String start = CalendarExt.calendarToFormatString(activityMonitoringPlan.getStart(), Constants.FORMAT_CALENDAR_I);
                            String end = CalendarExt.calendarToFormatString(activityMonitoringPlan.getEnd(), Constants.FORMAT_CALENDAR_I);

                            messageService.sendNotificationToRole(caseDetention.getId(),
                                    String.format("<strong>Descripción:</strong> Se registró una inasistencia a la canalización de tipo <strong>\"%s\"</strong><br/>" +
                                                    "Para el imputado: <strong>%s</strong>. Causa penal <strong>%s</strong><br/>Registrado por el supervisor: <strong>%s</strong><br/><br/>" +
                                                    "Fecha de inasistencia: <b>%s</b> al <b>%s</b>",
                                            channeling.getChannelingType().getName(), imputedFullName, caseDetention.getIdMP(),
                                            sharedUserService.getFullNameById(user.getId()), start, end),
                                    new ArrayList<String>() {{
                                        add(Constants.ROLE_CHANNELING_MANAGER);
                                    }}, Constants.CHANNELING_ABSENCE_TITLE);
                        }
                    } catch (Exception ex) {
                        logException.Write(ex, this.getClass(), "doActionActivity-sendNotification", sharedUserService);
                    }
                }
            }

            activityMonitoringPlan.setStatus(sStatus);
            activityMonitoringPlan.setComments(sComments);
            activityMonitoringPlan.setSupervisorDone(user);
            activityMonitoringPlan.setDoneTime(Calendar.getInstance());

            activityMonitoringPlanRepository.save(activityMonitoringPlan);

            response.setReturnData(sStatus);
            logCaseService.addLog(ConstantsLogCase.LOG_SUPERVISION_ACTIVITY, activityMonitoringPlan.getCaseDetention().getId(), activityMonitoringPlan.getId());

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doActionActivity", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }
        return response;
    }
}
