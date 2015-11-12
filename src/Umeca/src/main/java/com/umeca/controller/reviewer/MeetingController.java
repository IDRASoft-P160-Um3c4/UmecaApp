package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.model.managerEval.ManagerevalView;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.managereval.Formulation;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.CriminalProceedingView;
import com.umeca.model.entities.reviewer.View.MeetingView;
import com.umeca.model.entities.reviewer.View.PersonSocialNetworkView;
import com.umeca.model.shared.ConsMessage;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.managereval.FormulationRepository;
import com.umeca.repository.reviewer.MeetingRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.MeetingService;
import com.umeca.service.reviewer.ScheduleService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @RequestMapping(value = "/reviewer/meeting/index", method = RequestMethod.GET)
    public String index() {
        return "/reviewer/meeting/index";
    }

    @RequestMapping(value = {"/reviewer/meeting/declined","/reviewer/formulation/declined"}, method = RequestMethod.GET)
    public String decline() {
        return "/reviewer/declined/index";
    }

    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    FormulationRepository formulationRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/reviewer/meeting/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("reviewerId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCode",
                new ArrayList<String>() {{
                    add(Constants.S_MEETING_INCOMPLETE);
                    add(Constants.S_MEETING_INCOMPLETE_LEGAL);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter2);
        JqGridRulesModel extraFilter3 =new JqGridRulesModel("statusCase", Constants.CASE_STATUS_MEETING, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter3);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, Meeting> joinM = r.join("meeting");
                final javax.persistence.criteria.Join<Meeting, StatusMeeting> joinSM = joinM.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinUsr = joinM.join("reviewer");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinSM.get("name").alias("statusCode"));
                    add(r.get("idFolder"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinImp.get("gender"));
                    add(joinSM.get("description"));
                    add(joinUsr.get("id").alias("reviewerId"));
                    add(r.join("status").get("name").alias("statusCase"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCode"))
                    return r.join("meeting").join("status").get("name");
                else if (field.equals("reviewerId"))
                    return r.join("meeting").join("reviewer").get("id");
                if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("statusCase")){
                    return r.join("status").get("name");
                }else
                    return null;
            }
        }, Case.class, MeetingView.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listDeclined", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listDeclined(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("reviewerId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCode",
                new ArrayList<String>() {{
                    add(Constants.S_MEETING_DECLINE);
                    add(Constants.S_MEETING_INCOMPLETE);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter2);



        JqGridRulesModel extraFilter3 =new JqGridRulesModel("statusCase", Constants.CASE_STATUS_NOT_PROSECUTE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter3);


        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, Meeting> joinM = r.join("meeting");
                final javax.persistence.criteria.Join<Meeting, StatusMeeting> joinSM = joinM.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinUsr = joinM.join("reviewer");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinSM.get("name").alias("statusCode"));
                    add(r.get("idFolder"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinImp.get("gender"));
                    add(joinSM.get("description"));
                    add(joinUsr.get("id").alias("reviewerId"));
                    add(joinUsr.get("fullname").alias("reviewerName"));
                    add(r.join("status").get("name").alias("statusCase"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCode"))
                    return r.join("meeting").join("status").get("name");
                else if  (field.equals("reviewerId"))
                    return r.join("meeting").join("reviewer").get("id");
                if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("statusCase")){
                    return r.join("status").get("name");
                }else
                    return null;
            }
        }, Case.class, MeetingView.class);

        return result;

    }


        @RequestMapping(value = "/reviewer/meeting/declined/printSheet", method = RequestMethod.GET)
    public ModelAndView printSheet(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = null;
        try {

            MeetingView sheetInfo = meetingService.getMeetingSheetById(id);
            //ChannelingModelSheet sheetInfo = channelingService.getChannelingSheetById(id);
            if (sheetInfo == null) {
                model = new ModelAndView("/reviewer/declined/notSheet");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-informe-negacion.doc\"");
                return model;
            }

            model = new ModelAndView("/reviewer/declined/printSheet");
            model.addObject("data", sheetInfo);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"informe-negación-" +sheetInfo.getName() + sheetInfo.getLastNameP() +sheetInfo.getLastNameM() + ".doc\"");
//
//            channelingService.addLogChannelingDoc(sheetInfo.getIdCase(),sheetInfo.getChannelingType());
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "printSheet", userService);
            model = null;
        }
        return model;
    }

    @RequestMapping(value = "/reviewer/meeting/listAddress", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listAddress(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("address").get("addressString").alias("addressString"));
                    add(r.get("phone"));
                    add(r.join("registerType").get("name").alias("registerTypeString"));
                    add(r.join("homeType").get("name").alias("homeType"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("registerTypeString")) {
                    return r.join("registerType").get("name");
                } else if (field.equals("addressString")) {
                    return r.join("address").get("addressString");
                }
                return null;
            }
        }, ImputedHome.class, ImputedHome.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listSocialNetwork", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listSocialNetwork(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.join("relationship").get("name").alias("relName"));
                    add(r.get("age"));
                    add(r.get("phone"));
                    add(r.get("isAccompaniment"));
                    add(r.join("dependent").get("id").alias("dependent"));
                    add(r.get("specificationRelationship"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("socialNetwork").join("meeting").join("caseDetention").get("id");
                } else if (field.equals("relName")) {
                    return r.join("relationship").get("name");
                }

                return null;
            }
        }, PersonSocialNetwork.class, PersonSocialNetworkView.class);

        return result;

    }

    @RequestMapping(value = {"/reviewer/meeting/listReference"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listReference(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("fullName"));
                    add(r.join("relationship").get("name").alias("relName"));
                    add(r.get("age"));
                    add(r.get("phone"));
                    add(r.get("isAccompaniment"));
                    add(r.get("specificationRelationship"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("relName")) {
                    return r.join("relationship").get("name");
                }

                return null;
            }
        }, Reference.class, Reference.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listDrug", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listDrug(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("periodicity").get("name").alias("perName"));
                    add(r.get("lastUse"));
                    add(r.join("drugType").get("name").alias("drugName"));
                    add(r.get("quantity"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("drugName")) {
                    return r.join("drugType").get("name");
                }
                return null;
            }
        }, Drug.class, Drug.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listJob", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listJob(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<Meeting, Case> joinRT = r.join("registerType");
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("company"));
                    add(r.get("post"));
                    add(r.get("nameHead"));
                    add(r.get("phone"));
                    add(joinRT.get("name").alias("registerTypeString"));
                    add(joinRT.get("id").alias("registerTypeId"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("registerTypeId")) {
                    return r.join("registerType").get("id");
                } else if (field.equals("registerTypeString")) {
                    return r.join("registerType").get("name");
                }
                return null;
            }
        }, Job.class, Job.class);

        return result;

    }

    @Autowired
    DistrictRepository districtRepository;

    @RequestMapping(value = "/reviewer/meeting/newMeeting", method = RequestMethod.POST)
    public ModelAndView newMeeting() {
        ModelAndView model = new ModelAndView("/reviewer/meeting/newMeeting");
        List<SelectList> lstDistrict = districtRepository.findNoObsolete();
        Gson gson = new Gson();
        model.addObject("lstDistrict", gson.toJson(lstDistrict));
        return model;
    }

    @RequestMapping(value = {"/reviewer/meeting/doNewMeeting","/reviewer/formulation/doNewMeeting"}, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doNewMeeting(@ModelAttribute Imputed imputed) {

        try{
            imputed.setFoneticString(sharedUserService.getFoneticByName(imputed.getName(),imputed.getLastNameP(),imputed.getLastNameM()));
            ResponseMessage validateCreate = meetingService.validateCreateMeeting(imputed);

            if(imputed.getFormulationId() != null){

                Formulation formulation = formulationRepository.findOne(imputed.getFormulationId());
                formulation.setPresence(true);
                formulationRepository.save(formulation);
            }
            Formulation formulation;
            if(imputed.getFormulationId() == null && imputed.getIsFromFormulation().equals(true)){
                formulation = new Formulation();

                formulation.setFirstName(imputed.getName());
                formulation.setLastNameP(imputed.getLastNameP());
                formulation.setLastNameM(imputed.getLastNameM());
                formulation.setRegistrationFormulationDate(new Date());
                formulation.setUmecaInterviewDate(new Date());
                formulation.setDocument("Sin oficio");
                formulation.setCertificateNotification("Sin cédula de notificación");
                formulation.setIsObsolete(false);
                formulation.setPresence(true);
                formulation.setReviewer(userRepository.findOne(userService.GetLoggedUserId()));
                formulation.setId(null);

                formulationRepository.save(formulation);
                imputed.setFormulationId(formulation.getId());

            }

            if (validateCreate != null)
                return validateCreate;
            Long idCase = meetingService.createMeeting(imputed);
            ResponseMessage result = new ResponseMessage(false, "Se ha guardado exitosamente");
            if(imputed.getMeeting().getDeclineReason() != null){
                Case c = caseRepository.findOne(idCase);
                c.setDateNotProsecute(new Date());
                caseRepository.save(c);
                result.setUrlToGo("declined.html");
            }else {
                result.setUrlToGo("meeting.html?id=" + idCase);
            }
            return result;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doNewMeeting", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al crear la entrevista");
        }

    }

    @RequestMapping(value = {"/reviewer/meeting/meeting","/reviewer/formulation/meeting"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView meeting(@RequestParam(required = true) Long id) {
        try{
            return meetingService.showMeeting(id);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "meeting", sharedUserService);
            throw ex;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/legal/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView legal(@RequestParam(required = true) Long id, @RequestParam(required = false) Integer showCase) {
        try {
            return meetingService.showLegalProcess(id, showCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "legal", sharedUserService);
            throw ex;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/address/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        try{
            return meetingService.upsertAddress(id, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsert", sharedUserService);
            throw ex;
        }
    }


    @RequestMapping(value = "/reviewer/meeting/address/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertAddress(@ModelAttribute ImputedHome imputedHome, @RequestParam Long idCase, @RequestParam(required = false) String sch) {
        try{
            return meetingService.doUpsertAddress(imputedHome, idCase, sch);

        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsertAddress", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }


    @RequestMapping(value = "/reviewer/meeting/address/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteAddress(@RequestParam Long id) {
        try{
            return meetingService.deleteAddress(id);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doDeleteAddress", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/upsert", method = RequestMethod.POST)
    public ModelAndView upsertSocialNetwork(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        try{
            return meetingService.upsertSocialNetwork(id, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertSocialNetwork", sharedUserService);
            throw ex;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertSocialNewtork(@ModelAttribute PersonSocialNetwork person, @RequestParam Long idCase) {
        try{
            return meetingService.doUpsertSocialNetwork(person, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsertSocialNewtork", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteSocialNewtork(@RequestParam Long id) {

        try{
            return meetingService.deleteSocialNetwork(id);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doDeleteSocialNewtork", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/drug/upsert", method = RequestMethod.POST)
    public ModelAndView upsertDrug(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        try{
            return meetingService.upsertDrug(id, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertDrug", sharedUserService);
            throw ex;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/drug/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDrugNewtork(@ModelAttribute Drug drug, @RequestParam Long idCase) {
        try{
            return meetingService.doUpsertDrug(drug, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doDrugNewtork", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/drug/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteDrug(@RequestParam Long id) {
        try{
            return meetingService.deleteDrug(id);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doDeleteDrug", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/job/upsert", method = RequestMethod.POST)
    public ModelAndView upsertJob(@RequestParam(required = false) Long id, @RequestParam Long idCase) {
        try{
            return meetingService.upsertJob(id, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertJob", sharedUserService);
            throw ex;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/job/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertJob(@ModelAttribute Job job, @RequestParam Long idCase, @RequestParam(required = false) String sch) {
        try{
            return meetingService.doUpsertJob(job, idCase, sch);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsertJob", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/job/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteJob(@RequestParam Long id) {
        try{
            return meetingService.deleteJob(id);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "deleteJob", sharedUserService);
            throw ex;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/reference/upsert", method = RequestMethod.POST)
    public ModelAndView upsertReference(@RequestParam(required = false) Long id, @RequestParam Long idCase) {
        try{
            return meetingService.upsertReference(id, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertReference", sharedUserService);
            throw ex;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/reference/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertReference(@ModelAttribute Reference reference, @RequestParam Long idCase) {
        try{
            return meetingService.doUpsertReference(reference, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsertReference", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/reference/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteReference(@RequestParam Long id) {

        try{
            return meetingService.deleteReference(id);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doDeleteReference", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/upsertPersonalData", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertPersonalData(@ModelAttribute Meeting meeting, String activities) {
        try{
            return meetingService.upsertPersonalData(meeting.getCaseDetention().getId(), meeting.getImputed(), meeting.getSocialEnvironment(), activities);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertPersonalData", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/upsertLeaveCountry", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertLeaveCountry(@ModelAttribute Meeting meeting) {
        try{
            return meetingService.upsertLeaveCountry(meeting.getCaseDetention().getId(), meeting.getLeaveCountry());
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertLeaveCountry", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/reviewer/meeting/school/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertSchool(@ModelAttribute Meeting meeting, @RequestParam String sch) {
        try {
            ResponseMessage result = meetingService.doUpsertSchool(meeting.getCaseDetention().getId(), meeting.getSchool(), sch);
            return result;
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "upsertSchool", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la historia escolar.");
        }

    }

    @RequestMapping(value = "/reviewer/meeting/terminateMeeting", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage terminateMeeting(@ModelAttribute Meeting meeting, @RequestParam String sch, String activities, @RequestParam(required = false,defaultValue="false") Boolean cancelMeeting) {
        try{
            return meetingService.doTerminateMeeting(meeting, sch, activities, cancelMeeting);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "terminateMeeting", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/saveProceedingLegal", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage saveProceedingLegal(@ModelAttribute CriminalProceedingView cpv) {
        try{
            return meetingService.saveProceedingLegal(cpv);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "saveProceedingLegal", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }


    @RequestMapping(value = "/reviewer/meeting/upsertSocialNetworkComment", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertSocialNetworkComment(@RequestParam String comment, @RequestParam Long idCase) {
        try{
            return meetingService.upsertSocialNetworkComment(comment, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertSocialNetworkComment", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/findPreviousCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage findPreviousCase(@RequestParam String sName, @RequestParam String sLastNameP,@RequestParam String sLastNameM, @RequestParam Long idCase) {
        try{
            return meetingService.findPreviousCase(sName, sLastNameP, sLastNameM, idCase);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "findPreviousCase", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/savePartialPrevious", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage savePartialPrevious(@ModelAttribute CriminalProceedingView cpv) {
        try{
            return meetingService.savePartialPrevious(cpv);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "savePartialPrevious", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/savePartialCurrent", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage savePartialCurrent(@ModelAttribute CriminalProceedingView cpv) {
        try{
            return meetingService.savePartialCurrent(cpv);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "savePartialCurrent", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/saveTimeDetention", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage saveHandingOverInfo(@ModelAttribute CriminalProceedingView cpv) {
        ResponseMessage responseMessage;
        try {
            responseMessage = meetingService.saveHandingOverInfo(cpv);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "savePartialPrevious", userService);
            responseMessage= new ResponseMessage(true, ConsMessage.MSG_ERROR_UPSERT);
        }
        return responseMessage;
    }

    @RequestMapping(value = "/reviewer/meeting/upsertComment", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertComment(@RequestParam String comment,@RequestParam Long idCase, @RequestParam Integer typeComment) {
        try{
            return meetingService.upsertComment(idCase, comment, typeComment);
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "upsertComment", sharedUserService);
            return new ResponseMessage(true, ex.getMessage());
        }
    }

    @RequestMapping(value = "/reviewer/meeting/showTerminateMeeting", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView showTerminateMeeting() {
        ModelAndView model = new ModelAndView("reviewer/meeting/terminateMeeting");
        return  model;
    }


    @RequestMapping(value = {"/reviewer/handingOver/index","/managereval/handingOver/index"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView handingOverIndex() {
        ModelAndView model = new ModelAndView("reviewer/detainedEval");
        return  model;
    }


    @RequestMapping(value = {"/reviewer/handingOver/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel handingOverList(@ModelAttribute JqGridFilterModel opts) {
        opts.extraFilters = new ArrayList<>();

    /*    JqGridRulesModel extraFilter = new JqGridRulesModel("statusMeeting",
                new ArrayList<String>() {{
                    add(Constants.S_MEETING_INCOMPLETE_LEGAL);
                    add(Constants.S_MEETING_COMPLETE);
                    add(Constants.S_MEETING_INCOMPLETE);
                }}
                , JqGridFilterModel.COMPARE_IN
        );

      opts.extraFilters.add(extraFilter); */

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMe = r.join("meeting");
                final Join<Meeting, Imputed> joinImp = joinMe.join("imputed");
                final Join<Meeting, StatusMeeting> joinStatusMe = joinMe.join("status");
                final Join<Meeting, CurrentCriminalProceeding> joinLegal = joinMe.join("currentCriminalProceeding");
                final Join<CurrentCriminalProceeding, Crime> joinC = joinLegal.join("crimeList", JoinType.LEFT);
                final Join<CurrentCriminalProceeding, Crime> joinCC = joinC.join("crime",JoinType.LEFT);

                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("idFolder"));
                  //  add(joinStatusMe.get("status").alias("statusMeeting"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinCC.get("name").alias("crime"));
                    add(joinLegal.get("handingOverDate"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {;
                if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");

                if (field.equals("statusCode"))
                    return r.join("verification").join("status").get("name");
                if (field.equals("statusCaseCode"))
                    return r.join("status").get("name");

                return null;
            }
        }, Case.class, ManagerevalView.class);
        return result;
    }

    @RequestMapping(value = {"/reviewer/meeting/onlyMeeting"},method = RequestMethod.GET)
    public ModelAndView onlyMeeting(){
        ModelAndView model = new ModelAndView("/reviewer/meeting/onlyMeeting");
        return model;
    }

    @RequestMapping(value = {"/reviewer/onlyMeeting/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel onlyMeetingList(@ModelAttribute JqGridFilterModel opts) {

        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("reviewerId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        JqGridRulesModel extraFilter3 =new JqGridRulesModel("statusCase", Constants.CASE_STATUS_GOT_FREEDOM, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter3);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, Meeting> joinM = r.join("meeting");
                final javax.persistence.criteria.Join<Meeting, StatusMeeting> joinSM = joinM.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinUsr = joinM.join("reviewer");


                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinSM.get("name").alias("statusCode"));
                    add(r.get("idFolder"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinImp.get("gender"));
                    add(joinSM.get("description"));
                    add(joinUsr.get("id").alias("reviewerId"));
                    add(joinUsr.get("fullname").alias("fullname"));
                    add(r.join("status").get("name").alias("statusCase"));
                }};
            }
            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCode"))
                    return r.join("meeting").join("status").get("name");
                else if (field.equals("reviewerId"))
                    return r.join("meeting").join("reviewer").get("id");
                if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("statusCase")){
                    return r.join("status").get("name");
                }else
                    return null;
            }
        }, Case.class, MeetingView.class);
        return result;
    }
}
