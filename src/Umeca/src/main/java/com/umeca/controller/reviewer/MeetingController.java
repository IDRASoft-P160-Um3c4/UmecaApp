package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.jqgrid.operation.JqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.DomicileView;
import com.umeca.model.entities.reviewer.View.MeetingView;
import com.umeca.model.entities.reviewer.View.PersonSocialNetworkView;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.reviewer.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @RequestMapping(value = "/reviewer/meeting/index", method = RequestMethod.GET)
       public String index(){
        return "/reviewer/meeting/index";
    }

    @Autowired
    private  GenericJqGridPageSortFilter gridFilter;
               ////////////////////////////////Grids--------------------
    @RequestMapping(value = "/reviewer/meeting/list", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.join("meeting").join("caseDetention").get("id"));
                    add(r.get("rfc"));
                    add(r.get("name"));
                    add(r.get("lastNameP"));
                    add(r.get("lastNameM"));
                    add(r.get("dateBirth"));
                    add(r.get("gender"));
                    add(r.join("meeting").join("status").get("description"));
                    add(r.join("meeting").join("status").get("id").alias("idStatus"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                //if(field.equals("rfc"))
                //    return r.get("rfc");
                return null;
            }
        }, Imputed.class, MeetingView.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listAddress", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel listAddress(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase){
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("domicile"));
                    add(r.get("timeLive"));
                    add(r.join("registerType").get("name").alias("registerTypeString"));
                    add(r.join("belong").get("name").alias("belongString"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idCase")){
                    return r.join("socialNetwork").join("meeting").join("caseDetention").get("id");
                }else if(field.equals("registerTypeString")){
                    return r.join("registerType").get("name");
                }
                return null;
            }
        }, Domicile.class, Domicile.class);

        return result;

    }


  @RequestMapping(value = "/reviewer/meeting/listSocialNetwork", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel listSocialNetwork(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase){

      opts.extraFilters = new ArrayList<>();
      JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
      opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.join("relationship").get("name").alias("relName"));
                    add(r.get("age"));
                    add(r.get("phone"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idCase")){
                    return r.join("socialNetwork").join("meeting").join("caseDetention").get("id");
                }

                return null;
            }
        }, PersonSocialNetwork.class, PersonSocialNetworkView.class);

        return result;

    }

  @RequestMapping(value = "/reviewer/meeting/listReference", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel listReference(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase){

      opts.extraFilters = new ArrayList<>();
      JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
      opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("fullName"));
                    add(r.join("relationship").get("name").alias("relName"));
                    add(r.get("age"));
                    add(r.get("phone"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idCase")){
                    return r.join("meeting").join("caseDetention").get("id");
                }

                return null;
            }
        }, Reference.class, Reference.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listDrug", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel listDrug(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase){

      opts.extraFilters = new ArrayList<>();
      JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
      opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.join("periodicity").get("name").alias("perName"));
                    add(r.get("lastUse"));
                    add(r.join("drugType").get("name").alias("drugName"));
                    add(r.get("quantity"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idCase")){
                    return r.join("meeting").join("caseDetention").get("id");
                }else if(field.equals("drugName")){
                    return r.join("drugType").get("name");
                }
                    return null;
                }
        }, Drug.class, Drug.class);

        return result;

    }
    @RequestMapping(value = "/reviewer/meeting/listJob", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel listJob(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase){

      opts.extraFilters = new ArrayList<>();
      JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
      opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("company"));
                    add(r.get("post"));
                    add(r.get("nameHead"));
                    add(r.get("phone"));
                    add(r.join("registerType").get("name").alias("registerTypeString"));
                    add(r.join("registerType").get("id").alias("registerTypeId"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idCase")){
                    return r.join("meeting").join("caseDetention").get("id");
                }else if(field.equals("registerTypeId")){
                    return r.join("registerType").get("id");
                }
                    return null;
                }
        }, Job.class, Job.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/newMeeting", method = RequestMethod.POST)
    public String newMeeting(){
            return "/reviewer/meeting/newMeeting";
    }

    @RequestMapping(value = "/reviewer/meeting/doNewMeeting", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doNewMeeting(@ModelAttribute Imputed imputed){
        Long idCase = meetingService.createMeeting(imputed);
        ResponseMessage result = new ResponseMessage(false,"Se ha guardado exitosamente");
        result.setUrlToGo("/reviewer/meeting/meeting.html?id="+ idCase);
        return result;
    }

    @RequestMapping(value = "/reviewer/meeting/meeting", method = RequestMethod.GET)
    public @ResponseBody ModelAndView meeting(@RequestParam(required = true) Long id){
        return meetingService.showMeeting(id);
    }

    @RequestMapping(value = "/reviewer/meeting/legal/index", method = RequestMethod.GET)
    public @ResponseBody ModelAndView legal(@RequestParam(required = true) Long id){
        return meetingService.showLegalProcess(id);
    }

    @RequestMapping(value = "/reviewer/meeting/address/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase){
        return meetingService.upsertAddress(id,idCase);
    }


    @RequestMapping(value = "/reviewer/meeting/address/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsertAddress(@ModelAttribute Domicile domicile, @RequestParam Long idCase,@RequestParam(required = false) String sch){
        return meetingService.doUpsertAddress(domicile, idCase, sch);
    }


    @RequestMapping(value = "/reviewer/meeting/address/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doDeleteAddress(@RequestParam Long id){
        return meetingService.deleteAddress(id);
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/upsert", method = RequestMethod.POST)
    public ModelAndView upsertSocialNetwork(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase){
        return meetingService.upsertSocialNetwork(id, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsertSocialNewtork(@ModelAttribute PersonSocialNetwork person, @RequestParam Long idCase){
        return meetingService.doUpsertSocialNetwork(person, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doDeleteSocialNewtork(@RequestParam Long id){
        return meetingService.deleteSocialNetwork(id);
    }

    @RequestMapping(value = "/reviewer/meeting/drug/upsert", method = RequestMethod.POST)
    public ModelAndView upsertDrug(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase){

        return meetingService.upsertDrug(id, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/drug/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doDrugNewtork(@ModelAttribute Drug drug, @RequestParam Long idCase){
        return meetingService.doUpsertDrug(drug, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/drug/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doDeleteDrug(@RequestParam Long id){
        return meetingService.deleteDrug(id);
    }

    @RequestMapping(value = "/reviewer/meeting/job/upsert", method = RequestMethod.POST)
    public ModelAndView upsertJob(@RequestParam(required = false) Long id,@RequestParam Long idCase){
        return meetingService.upsertJob(id,idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/job/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsertJob(@ModelAttribute Job job, @RequestParam Long idCase,@RequestParam(required = false) String sch){
        return meetingService.doUpsertJob(job, idCase, sch);
    }

    @RequestMapping(value = "/reviewer/meeting/job/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doDeleteJob(@RequestParam Long id){
        return meetingService.deleteJob(id);
    }

    @RequestMapping(value = "/reviewer/meeting/reference/upsert", method = RequestMethod.POST)
    public ModelAndView upsertReference(@RequestParam(required = false) Long id,@RequestParam Long idCase){
        return meetingService.upsertReference(id, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/reference/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsertReference(@ModelAttribute Reference reference, @RequestParam Long idCase){
        return meetingService.doUpsertReference(reference, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/reference/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doDeleteReference(@RequestParam Long id){
        return meetingService.deleteReference(id);
    }

    @RequestMapping(value = "/reviewer/meeting/upsertPersonalData", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage upsertPersonalData(@ModelAttribute Meeting meeting, Integer[] physicalCondition, Integer[] activity){
       return meetingService.upsertPersonalData(meeting.getCaseDetention().getId(),meeting.getImputed(), meeting.getSocialEnvironment(), physicalCondition,activity);
    }

    @RequestMapping(value = "/reviewer/meeting/school/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage upsertSchool(@ModelAttribute Meeting meeting,@RequestParam String sch){
                return meetingService.doUpsertSchool(meeting.getCaseDetention().getId(), meeting.getSchool(),sch);
    }
}
