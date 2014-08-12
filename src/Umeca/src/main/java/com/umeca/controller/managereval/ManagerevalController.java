package com.umeca.controller.managereval;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.managereval.ManagerevalView;
import com.umeca.model.shared.Constants;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.managereval.SourceVerificationRepository;
import com.umeca.repository.supervisor.LogNotificationReviewerRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.umeca.repository.reviewer.*;
import com.umeca.repository.reviewer.VerificationRepository;
import com.umeca.repository.catalog.*;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.*;

import com.umeca.infrastructure.jqgrid.model.*;
import com.umeca.repository.shared.*;
import com.umeca.repository.catalog.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ManagerevalController {
    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private VerificationMethodRepository verificationMethod;
    @Autowired
    private SourceVerificationRepository sourceVerification;
    @Autowired
    private VerificationRepository verification;
    @Autowired
    private StatusVerificationRepository statusVerification;
    @Autowired
    private CaseRepository _case;
    @Autowired
    private StatusCaseRepository statusCase;


    @RequestMapping(value = {"/managereval/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/managereval/index");
        return mv;
    }

    @ModelAttribute("verifySources")
    public String getVerifySources() {
        Gson gson = new Gson();
        return gson.toJson(verificationMethod.findNoObsolete());
    }

    @Autowired
    HearingFormatService hearingFormatService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogNotificationReviewerRepository logNotificationReviewerRepository;

    @RequestMapping(value = "/managereval/save", method = RequestMethod.POST)
    @Transactional
    public
    @ResponseBody
    ResponseMessage save(@ModelAttribute SourcesDataView sourcesInfo, @RequestParam Long c) {

        Gson conv = new Gson();
        List<SaveInformation> data = conv.fromJson(sourcesInfo.getDataInfo(), new TypeToken<List<SaveInformation>>() {
        }.getType());

        ResponseMessage resp = hearingFormatService.validatePassCredential(sourcesInfo.getPassword());

        if (resp != null)
            return resp;

        for (SaveInformation item : data) {
           SourceVerification source = sourceVerification.findOne(item.getId());
            VerificationMethod vm = verificationMethod.findOne(item.getRef());
            source.setAuthorized(true);
            source.setVerificationMethod(vm);
            source.setDateAuthorized(new Date());
            sourceVerification.save(source);
        }

        Verification _verification = verification.findByCase(c);
        StatusVerification sm = statusVerification.findByCode("AUTHORIZED");
        _verification.setStatus(sm);

        StatusCase sc = statusCase.findOne(9L);
        Case __case = _case.findOne(c);

        __case.setStatus(sc);

        verification.save(_verification);
        _case.save(__case);

        LogNotificationReviewer notif = new LogNotificationReviewer();
        notif.setIsObsolete(false);
        notif.setSubject("Se han verificado las fuentes para el caso con carpeta de investigación "+__case.getIdFolder()+".");
        notif.setMessage(sourcesInfo.getComment());
        notif.setSenderUser(userRepository.findOne(userService.GetLoggedUserId()));
        notif.setReceiveUser(__case.getMeeting().getReviewer());

        logNotificationReviewerRepository.save(notif);

        return new ResponseMessage(false, "");
    }

    @RequestMapping(value = {"/managereval/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCode",
                "NEW_SOURCE", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMeVe = r.join("meeting");
                final Join<Meeting, Imputed> joinMee = joinMeVe.join("imputed");
                final Join<Meeting, Case> joinCd = joinMeVe.join("caseDetention");
                final Join<Meeting, CurrentCriminalProceeding> joinCCP = joinMeVe.join("currentCriminalProceeding");
                final Join<CurrentCriminalProceeding, Crime> joinC = joinCCP.join("crimeList");

                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(joinCd.get("id"));
                    add(joinCd.get("idFolder"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinC.get("name").alias("crime"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCode"))
                    return r.join("verification").join("status").get("name");

                return null;
            }
        }, Case.class, ManagerevalView.class);
        return result;
    }

    @RequestMapping(value = {"/managereval/listVerification"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listReference(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("caseDetention", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final Join<Verification, SourceVerification> jSourceVerification = r.join("sourceVerifications");
                final Join<SourceVerification, Relationship> jRelationship = jSourceVerification.join("relationship");

                return new ArrayList<Selection<?>>() {{
                    add(jSourceVerification.get("id"));
                    add(jSourceVerification.get("fullName"));
                    add(jRelationship.get("name").alias("relationshipString"));
                    add(jSourceVerification.get("phone"));
                    add(jSourceVerification.get("address"));
                    add(jSourceVerification.join("verificationMethod", JoinType.LEFT).get("id").alias("idVerificationMethod"));
                    add(jSourceVerification.get("isAuthorized"));
                    add(r.get("id").alias("idCase"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.get("id");
                }
                return null;
            }
        }, Verification.class, SourceVerification.class);

        return result;
    }
}