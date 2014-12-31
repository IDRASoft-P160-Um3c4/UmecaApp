package com.umeca.controller.supervisorManager;


import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.TechnicalReview;
import com.umeca.model.entities.supervisor.ForFramingMeetingGrid;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.shared.SelectFilterFields;
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
import javax.persistence.criteria.JoinType;
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

    @RequestMapping(value = "/supervisorManager/caseActive/prisonCases", method = RequestMethod.GET)
    public String indexPrisonCases() {
        return "/supervisorManager/caseActive/prisonCases";
    }

    @RequestMapping(value = "/supervisorManager/caseObsolete", method = RequestMethod.GET)
    public String indexObsoleteCase() {
        return "/supervisorManager/caseClosed/obsoleteCase";
    }

    @RequestMapping(value = "/supervisorManager/caseActive/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_CLOSED);
                    add(Constants.CASE_STATUS_PRISON_CLOSED);
                    add(Constants.CASE_STATUS_OBSOLETE_EVALUATION);
                    add(Constants.CASE_STATUS_OBSOLETE_SUPERVISION);
                }}, JqGridFilterModel.COMPARE_NOT_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {

            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, StatusCase> joinSt = r.join("status");
                final javax.persistence.criteria.Join<Case, StatusCase> joinM = r.join("meeting").join("imputed");

                final javax.persistence.criteria.Join<Case, TechnicalReview> joinTR = r.join("technicalReview", JoinType.LEFT);
                final javax.persistence.criteria.Join<Case, FramingMeeting> joinFM = r.join("framingMeeting", JoinType.LEFT);


                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinSt.get("name"));
                    add(joinSt.get("description"));
                    add(r.get("idMP"));
                    add(joinM.get("name"));
                    add(joinM.get("lastNameP"));
                    add(joinM.get("lastNameM"));
                    add(joinM.get("birthDate"));
                    add(joinTR.get("id"));
                    add(joinFM.get("id"));
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
                else
                    return null;
            }
        }, Case.class, ForFramingMeetingGrid.class);

        return result;
    }


    @RequestMapping(value = "/supervisorManager/caseActive/listPrison", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listPrison(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("arrangement",
                new ArrayList<String>() {{
                    add(HearingFormatConstants.ID_PRISON_ARRANGEMENT_LOC.toString());
                    add(HearingFormatConstants.ID_PRISON_ARRANGEMENT_NAC.toString());
                    add(HearingFormatConstants.ID_IMPUTED_PROMISE_ARRANGEMENT_LOC.toString());
                }}, JqGridFilterModel.COMPARE_IN
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
                else if (field.equals("fullName"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("statausName"))
                    return r.join("status").get("name");
                else if (field.equals("arrangement"))
                    return r.join("hearingFormats").join("assignedArrangements").join("arrangement").get("id");
                else
                    return null;
            }
        }, Case.class, ForFramingMeetingGrid.class);

        return result;
    }

    @RequestMapping(value = "/supervisorManager/caseObsolete/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listObsolete(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("status", Constants.CASE_STATUS_OBSOLETE_SUPERVISION, JqGridFilterModel.COMPARE_EQUAL
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
                else if (field.equals("fullName"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("status"))
                    return r.join("status").get("name");
                else if (field.equals("arrangement"))
                    return r.join("hearingFormats").join("assignedArrangements").join("arrangement").get("id");
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

    @RequestMapping(value = "/supervisorManager/caseActive/authClose", method = RequestMethod.POST)
    public ModelAndView authorize(@RequestParam Long id, @RequestParam(required = false) Integer authObs) { //Case Id
        ModelAndView model = new ModelAndView("/supervisorManager/caseActive/authorizeRejectClose");

        try {
            GetCaseInfo(id, model, caseRepository, hearingFormatRepository, authObs);
            model.addObject("isAuthorized", 1);
            model.addObject("isAuthObs", authObs);
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "authorize", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/caseActive/rejectClose", method = RequestMethod.POST)
    public ModelAndView reject(@RequestParam Long id, @RequestParam(required = false) Integer authObs) {
        ModelAndView model = new ModelAndView("/supervisorManager/caseActive/authorizeRejectClose");

        try {
            GetCaseInfo(id, model, caseRepository, hearingFormatRepository, authObs);
            model.addObject("isAuthorized", 0);
            model.addObject("isAuthObs", authObs);
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "reject", sharedUserService);
            return null;
        }
    }


    public static void GetCaseInfo(Long id, ModelAndView model, CaseRepository caseRepository, HearingFormatRepository hearingFormatRepository, Integer isAuthObs) {
        CaseInfo caseInfo = caseRepository.getInfoById(id);
        model.addObject("caseId", caseInfo.getCaseId());
        model.addObject("mpId", caseInfo.getMpId());
        model.addObject("fullName", caseInfo.getPersonName());
        model.addObject("status", caseInfo.getStatus());
        model.addObject("folderId", caseInfo.getFolderId());

        if (isAuthObs != null) {
            model.addObject("urlToGo", "/supervisorManager/caseActive/doObsoleteCase.json");
            model.addObject("msgPlan", "la eliminaci&oacute;n del caso");
        } else {
            model.addObject("urlToGo", "/supervisorManager/caseActive/doAuthorizeRejectCase.json");
            model.addObject("msgPlan", "el cierre del caso");
        }

        List<String> lstSupervisor = hearingFormatRepository.findLastFullNameSupervisorByCaseId(id, new PageRequest(0, 1));
        if (lstSupervisor != null && lstSupervisor.size() > 0)
            model.addObject("supervisor", lstSupervisor.get(0));
    }


    @Autowired
    CaseService caseService;

    @RequestMapping(value = "/supervisorManager/caseActive/doAuthorizeRejectCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doAuthorizeRejectCase(@ModelAttribute AuthorizeRejectMonPlan model) {

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try {
            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            if (sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false) {
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            Case caseDet = caseRepository.findOne(model.getCaseId());

            if (caseDet == null) {
                response.setMessage("No se encontró el caso. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            String caseStatus = caseDet.getStatus().getName();
            if (caseStatus.equals(Constants.CASE_STATUS_PRE_CLOSED) == false) {
                response.setMessage("El caso se encuentra en estado " + caseStatus + ", por ello no puede ser autorizado\rechazado para cerrarse");
                return response;
            }

            caseService.saveAuthRejectCloseCase(model, user, caseDet);

            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doAuthorizeRejectCase", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }

    @RequestMapping(value = "/supervisorManager/caseActive/doObsoleteCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doObsoleteCase(@ModelAttribute AuthorizeRejectMonPlan model) {

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try {
            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            if (sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false) {
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            Case caseDet = caseRepository.findOne(model.getCaseId());

            if (caseDet == null) {
                response.setMessage("No se encontró el caso. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            String caseStatus = caseDet.getStatus().getName();
            if (caseStatus.equals(Constants.CASE_STATUS_REQUEST_SUPERVISION) == false) {
                response.setMessage("El caso se encuentra en estado " + caseStatus + ", por ello no puede ser autorizado\rechazado para eliminar");
                return response;
            }

            caseService.saveAuthRejectObsoleteCase(model, user, caseDet);

            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doObsoleteCase", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }
        return response;
    }

    @RequestMapping(value = "/supervisorManager/caseActive/closePrisonCase", method = RequestMethod.POST)
    public ModelAndView closePrisonCase(@RequestParam Long id) { //Case Id
        ModelAndView model = new ModelAndView("/supervisorManager/caseActive/closePrisonCase");

        try {
            GetCaseInfo(id, model, caseRepository, hearingFormatRepository, null);
            model.addObject("isAuthorized", 1);
            model.addObject("urlToGo", "/supervisorManager/caseActive/doClosePrisonCase.json");
            return model;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "authorize", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/caseActive/doClosePrisonCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doClosePrisonCase(@ModelAttribute AuthorizeRejectMonPlan model) {

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try {
            User user = new User();
            if (sharedUserService.isValidUser(user, response) == false)
                return response;

            if (sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false) {
                response.setMessage("La contrase&ntilde;a no corresponde al usuario en sesi&oacute;n");
                return response;
            }

            Case caseDet = caseRepository.findOne(model.getCaseId());

            if (caseDet == null) {
                response.setMessage("No se encontr&oacute; el caso. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            String caseStatus = caseDet.getStatus().getName();
            if (caseStatus.equals(Constants.CASE_STATUS_PRE_CLOSED) == true ||
                    caseStatus.equals(Constants.CASE_STATUS_PRISON_CLOSED) == true ||
                    caseStatus.equals(Constants.CASE_STATUS_PRE_CLOSED) == true) {
                response.setMessage("No es posible cerrar el caso, ya ha sido cerrado.");
                return response;
            }

            caseService.doClosePrisonCase(caseDet, model);

            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doClosePrisonCase", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se present&oacute; un error inesperado. Por favor revise que la informaci&oacute;n e intente de nuevo");
        }
        return response;
    }
}
