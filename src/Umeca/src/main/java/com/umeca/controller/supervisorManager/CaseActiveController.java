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
import com.umeca.model.entities.supervisor.ForFramingMeetingGrid;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;
import com.umeca.model.shared.Constants;
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

    @RequestMapping(value = "/supervisorManager/caseActive/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_CLOSED);
                }}, JqGridFilterModel.COMPARE_NOT_IN
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

                if (field.equals("statusName"))
                    return r.join("status").get("name");

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
    public ModelAndView authorize(@RequestParam Long id){ //Case Id
        ModelAndView model = new ModelAndView("/supervisorManager/caseActive/authorizeRejectClose");

        try{
            GetCaseInfo(id, model, caseRepository, hearingFormatRepository);
            model.addObject("isAuthorized", 1);
            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "authorize", sharedUserService);
            return null;
        }
    }


    @RequestMapping(value = "/supervisorManager/caseActive/rejectClose", method = RequestMethod.POST)
    public ModelAndView reject(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/supervisorManager/caseActive/authorizeRejectClose");

        try{
            GetCaseInfo(id, model, caseRepository, hearingFormatRepository);
            model.addObject("isAuthorized", 0);
            return model;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "reject", sharedUserService);
            return null;
        }
    }


    public static void GetCaseInfo(Long id, ModelAndView model, CaseRepository caseRepository, HearingFormatRepository hearingFormatRepository) {
        CaseInfo caseInfo = caseRepository.getInfoById(id);
        model.addObject("caseId", caseInfo.getCaseId());
        model.addObject("mpId", caseInfo.getMpId());
        model.addObject("fullName", caseInfo.getPersonName());
        model.addObject("status", caseInfo.getStatus());
        model.addObject("folderId", caseInfo.getFolderId());
        model.addObject("msgPlan", "el cierre del caso");
        model.addObject("urlToGo", "/supervisorManager/caseActive/doAuthorizeRejectCase.json");

        List<String> lstSupervisor = hearingFormatRepository.findLastFullNameSupervisorByCaseId(id, new PageRequest(0, 1));
        model.addObject("supervisor", lstSupervisor.get(0));
    }


    @Autowired
    CaseService caseService;

    @RequestMapping(value = "/supervisorManager/caseActive/doAuthorizeRejectCase", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage doAuthorizeRejectMonPlan(@ModelAttribute AuthorizeRejectMonPlan model){

        ResponseMessage response = new ResponseMessage();
        response.setHasError(true);

        try{
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(sharedUserService.isValidPasswordForUser(user.getId(), model.getPassword()) == false){
                response.setMessage("La contraseña no corresponde al usuario en sesión");
                return response;
            }

            Case caseDet = caseRepository.findOne(model.getCaseId());

            if(caseDet == null){
                response.setMessage("No se encontró el caso. Por favor reinicie su navegador e intente de nuevo");
                return response;
            }

            String caseStatus = caseDet.getStatus().getName();
            if(caseStatus.equals(Constants.CASE_STATUS_PRE_CLOSED) == false){
                response.setMessage("El caso se encuentra en estado " + caseStatus + ", por ello no puede ser autorizado\rechazado para cerrarse");
                return response;
            }

            caseService.saveAuthRejectCloseCase(model, user, caseDet);

            response.setHasError(false);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doAuthorizeRejectMonPlan", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }
        return response;
    }
}
