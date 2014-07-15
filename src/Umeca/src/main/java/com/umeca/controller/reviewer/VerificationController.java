package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.VerificationView;
import com.umeca.model.entities.reviewer.dto.FieldVerified;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.reviewer.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/05/14
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class VerificationController {

    @Autowired
    CaseService caseService;
    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/reviewer/verification/index", method = RequestMethod.GET)
    public String index(){
        //caseService.validateStatus(3L,Constants.CASE_STATUS_VERIFICATION_COMPLETE,Meeting.class,Constants.S_MEETING_INCOMPLETE);
        //verificationService.createAllFieldVerificationOfImputed(1L);
        return "/reviewer/verification/index";
    }

    @RequestMapping(value = "/reviewer/verification/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){
        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        opts.extraFilters.add(new JqGridRulesModel("reviewerId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL));
        //opts.extraFilters.add(new JqGridRulesModel("statusCodeCase",
        //       new ArrayList<String>(){{add(Constants.CASE_STATUS_VERIFICATION);}},JqGridFilterModel.COMPARE_EQUAL));
        opts.extraFilters.add(new JqGridRulesModel("statusCode",
                new ArrayList<String>(){{add(Constants.VERIFICATION_STATUS_AUTHORIZED);add(Constants.VERIFICATION_STATUS_MEETING_COMPLETE);}},JqGridFilterModel.COMPARE_IN));
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case,Verification> joinCase = r.join("caseDetention");
                final javax.persistence.criteria.Join<Case,Meeting> joinMee = joinCase.join("meeting");
                final javax.persistence.criteria.Join<Meeting,Imputed> joinIm = joinMee.join("imputed");
                final javax.persistence.criteria.Join<Verification,StatusVerification> joinStVer = r.join("status");
                return new ArrayList<Selection<?>>(){{
                    add(joinCase.get("id"));
                    add(joinCase.get("idFolder"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                    add(joinIm.get("gender"));
                    add(joinStVer.get("description").alias("statusDescription"));
                    add(joinStVer.get("name").alias("statusCode"));
                    add(joinStVer.get("name").alias("statusCodeCase"));
                    add(r.get("reviewer").get("id").alias("reviewerId"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("statusCode"))
                    return r.join("status").get("name");
                if(field.equals("statusDescription"))
                    return r.join("status").get("description");
                if(field.equals("reviewerId"))
                    return r.join("reviewer").get("id");
                return null;

            }
        }, Verification.class, VerificationView.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/verification/listSource", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel listAddress(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long id){
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", id.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        opts.extraFilters.add(new JqGridRulesModel("isAuthorized","1", JqGridFilterModel.COMPARE_EQUAL));
        opts.extraFilters.add(new JqGridRulesModel("visible", "1", JqGridFilterModel.COMPARE_EQUAL));
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("fullName"));
                    add(r.get("age"));
                    add(r.join("relationship").get("name").alias("relationshipString"));
                    add(r.get("address"));
                    add(r.get("phone"));
                    add(r.get("isAuthorized"));
                    add(r.get("dateComplete"));
                    add(r.join("verification").join("caseDetention").get("id").alias("idCase"));
                    add(r.get("visible"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idCase")){
                    return r.join("verification").join("caseDetention").get("id");
                }
                return null;
            }
        }, SourceVerification.class, SourceVerification.class);

        return result;

    }


    @Autowired
    VerificationService verificationService;
    @RequestMapping(value = "/reviewer/verification/sources", method = RequestMethod.GET)
    public ModelAndView sources(@RequestParam(required = true) Long id){
        ModelAndView model = new ModelAndView("/reviewer/verification/sources");
        verificationService.showButtonsSource(model,id);
        verificationService.setImputedData(id, model);
        return model;
    }

    @RequestMapping(value = "/reviewer/verification/verificationBySource", method = RequestMethod.GET)
    public ModelAndView verificationBySource(@RequestParam Long idCase, @RequestParam Long idSource){
        return verificationService.showVerificationBySource(idCase, idSource);
    }

    @RequestMapping(value = "/reviewer/verification/detailVerification", method = RequestMethod.POST)
    public ModelAndView detailVerification(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/reviewer/verification/detailVerification");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @RequestMapping(value = "/reviewer/verification/checkSource", method = RequestMethod.GET)
    public String checkSource(){
        return "/reviewer/verification/checkSource";
    }



    @RequestMapping(value = "/reviewer/verification/saveFieldVerification", method = RequestMethod.POST)
    public ResponseMessage detailSource(@RequestParam(required = true) String val, @RequestParam(required = true) Long idCase, @RequestParam(required = true) Long idSource,@RequestParam(required = false)Long idList){
        Gson gson = new Gson();
        List<FieldVerified> list = gson.fromJson(val,new TypeToken<List<FieldVerified>>(){}.getType());
        ResponseMessage saveFieldVerified = verificationService.saveFieldVerifiedInocrrect(list, idCase, idSource,idList);
            return saveFieldVerified;
    }

    @RequestMapping(value = "/reviewer/verification/verifBySourceEqual", method = RequestMethod.POST)
    public ResponseMessage detailSourceEqual(@RequestParam(required = true) String code, @RequestParam(required = true) Long idCase, @RequestParam(required = true) Long idSource,@RequestParam(required = false)Long idList){
        ResponseMessage saveFieldEqual = verificationService.saveFieldVerifiedEqual(code, idCase, idSource,idList);
        return saveFieldEqual;
    }

    @RequestMapping(value = "/reviewer/verification/verifBySourceNotKnow", method = RequestMethod.POST)
    public ResponseMessage detailSourceNotKnow(@RequestParam(required = true) String code, @RequestParam(required = true) Long idCase, @RequestParam(required = true) Long idSource,@RequestParam(required = false)Long idList){
        Gson gson = new Gson();
        ResponseMessage saveFieldEqual = verificationService.saveFieldVerifiedNotKnow(code, idCase, idSource,idList);
        return saveFieldEqual;
    }

    @RequestMapping(value = "/reviewer/verification/terminateMeetingSource", method = RequestMethod.POST)
    public ResponseMessage terminateMeetingSource(@RequestParam(required = true) Long idCase, @RequestParam(required = true) Long idSource){
        ResponseMessage result = verificationService.terminateMeetingSource(idCase,idSource);
        return result;
    }

    @RequestMapping(value = "/reviewer/verification/choiceInformation", method = RequestMethod.GET)
    public ModelAndView choiceInformation(@RequestParam(required = true) Long idCase){
        return verificationService.showChoiceInformation(idCase);
    }

    @RequestMapping(value = "/reviewer/verification/showChoices", method = RequestMethod.POST)
    public ModelAndView showChoice(@RequestParam(required = true) Long idCase,@RequestParam(required = true) String id,@RequestParam(required = false) Long idList){
        return verificationService.showChoices(idCase,id,idList);
    }

    @RequestMapping(value = "reviewer/verification/saveScheduleVerification", method = RequestMethod.POST)
    public ResponseMessage saveScheduleVerification(@RequestParam(required = true) Long idCase,@RequestParam(required = true) String schedule,@RequestParam(required = false) Long idList,@RequestParam(required = true)Long idSource, @RequestParam(required = true) String code){
        return verificationService.saveSchedule(idCase,idSource,idList,schedule, code);
    }

    @RequestMapping(value = "reviewer/verification/saveAddressVerification", method = RequestMethod.POST)
    public ResponseMessage saveAddressVerification(@RequestParam(required = true) Long idCase,@ModelAttribute Address address,@RequestParam(required = false) Long idList,@RequestParam(required = true)Long idSource, @RequestParam(required = true) String code){
        return verificationService.saveAddressVerification(idCase,idSource,idList,code,address);
    }

    @RequestMapping(value = "reviewer/verification/saveSelectChoice", method = RequestMethod.POST)
    public ResponseMessage saveSelectChoice(@RequestParam(required = true) Long idCase,@RequestParam(required = false) Long idFieldMeeting,@RequestParam(required = true) String code,@RequestParam(required = false)Long idList){
        return verificationService.saveSelectChoice(idCase,idFieldMeeting,code, idList);
    }

}
