package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.RequestEvaluationView;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ConstantsLogCase;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.reviewer.CrimeRepository;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.repository.supervisor.LogNotificationRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.LogCaseService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/11/14
 * Time: 07:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CaseNotProsecuteController {
    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/caseNotProsecute/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/caseNotProsecute/index";
    }

    @RequestMapping(value = "/supervisor/caseNotProsecute/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCase",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_NOT_PROSECUTE);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter2);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case,Meeting> joinMeCa = r.join("meeting");
                final Join<Meeting,Imputed> joinMeIm = joinMeCa.join("imputed");
                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("idFolder"));
                    add(joinMeIm.get("name"));
                    add(joinMeIm.get("lastNameP"));
                    add(joinMeIm.get("lastNameM"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idFolder"))
                    return r.get("idFolder");
                else if(field.equals("fullName")){
                    return r.join("meeting").join("imputed").get("name");
                } else
                if(field.equals("statusCase")){
                    return r.join("status").get("name");
                }
                return null;
            }
        }, Case.class, RequestEvaluationView.class);
        return result;
    }

    @Autowired
    CrimeRepository crimeRepository;
    @Autowired
    CaseRepository caseRepository;

    @RequestMapping(value = {"/supervisor/caseNotProsecute/openNotProsecute"}, method = RequestMethod.POST)
    public ModelAndView openNotProsecute(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/caseNotProsecute/openNotProsecute");
        CaseInfo caseInfo = caseRepository.getInfoByIdNotProsecute(id);
        List<Crime> crimes = crimeRepository.findListCrimeLegalByIdCase(id);
        Gson gson = new Gson();
        List<String> crimeDtoList=new ArrayList<>();
        if(crimes.size()>0){
            for(Crime c: crimes){
                crimeDtoList.add(new CrimeDto().toStringCrime(c));
            }

        }else{
            crimeDtoList.add("No se agregaron delitos al caso.");
        }
        model.addObject("crimes",gson.toJson(crimeDtoList));
        model.addObject("caseInfo",gson.toJson(caseInfo));
        return model;
    }
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    StatusCaseRepository statusCaseRepository;
    @Autowired
    LogNotificationRepository logNotificationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HearingFormatService hearingFormatService;
    @Autowired
    LogCommentRepository logCommentRepository;
    @Autowired
    LogCaseService logCaseService;

    @Transactional
    @RequestMapping(value = "/supervisor/caseNotProsecute/doOpen", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertAddress(@RequestParam Long id, @RequestParam String comment,@RequestParam String password) {
        try{
            ResponseMessage resp = hearingFormatService.validatePassCredential(password);
            if (resp != null)
                return resp;
            Case c = caseRepository.findOne(id);
            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_NOT_PROSECUTE_OPEN));
            //TODO abrir bitacora en este momento para casos judicializdos
            caseRepository.save(c);
            LogNotification notif = new LogNotification();
            notif.setIsObsolete(false);
            notif.setSubject("Se abri&oacute; un caso no judicializado en supervisi&oacute;n");
            notif.setMessage("Se abri&oacute; el caso con Carpeta de Investigaci&oacute;n "+ StringEscape.escapeText(c.getIdFolder())+".<br/>El caso fue cerrado por el evaluador: "+c.getMeeting().getReviewer().getFullname()+".<br/>Raz&oacute;n: "+StringEscape.escapeText(comment)+".");
            User uSender = userRepository.findOne(userService.GetLoggedUserId());
            notif.setSenderUser(uSender);
            List<SelectList> userManagerEval = userRepository.getLstValidUsersByRole(Constants.ROLE_EVALUATION_MANAGER);
            if(userManagerEval!=null && userManagerEval.size()>0){
                User receiver=userRepository.findOne(userManagerEval.get(0).getId());
                notif.setReceiveUser(receiver);
            }
            logNotificationRepository.save(notif);

            LogComment logComment = new LogComment();

            logComment.setComments("Se abri&oacute; el caso con Carpeta de Investigaci&oacute;n "+StringEscape.escapeText(c.getIdFolder())+".<br/>El caso fue abierto por el supervisor: "+uSender.getFullname()+".<br/>Raz&oacute;n: "+StringEscape.escapeText(comment+"."));
            logComment.setAction(MonitoringConstants.TYPE_INFORMATION);
            logComment.setCaseDetention(c);
            logComment.setSenderUser(uSender);
            List<SelectList> userManagerSup = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR_MANAGER);
            if(userManagerSup!=null && userManagerSup.size()>0){
                User receiver=userRepository.findOne(userManagerSup.get(0).getId());
                logComment.setReceiveUser(receiver);
            }

            logComment.setTimestamp(Calendar.getInstance());
            logComment.setType("Se abri&oacute; un caso no judicializado en supervisi&oacute;n");
            logComment.setObsolete(false);

            logCommentRepository.save(logComment);
            logCaseService.addLog(ConstantsLogCase.OPEN_CASE_NOT_PROSECUTE,id,null);
            return  new ResponseMessage(false,"Se abrio el caso con exito");
        }catch (Exception e){
            logException.Write(e, this.getClass(), "doMakeRequest", userService);
            return  new ResponseMessage(true, "Ha ocurrido un error al abrir el caso.");
        }
    }
}

