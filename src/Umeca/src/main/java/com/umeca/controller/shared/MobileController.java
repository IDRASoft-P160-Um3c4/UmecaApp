package com.umeca.controller.shared;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.MeetingView;
import com.umeca.model.entities.reviewer.dto.SourceVerificationDto;
import com.umeca.model.shared.Constants;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MobileService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MobileController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private MobileService mobileService;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/shared/upload_info/meeting/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView index() {
        ModelAndView model = new ModelAndView("/shared/upload_info/meeting/index");
        Gson gson = new Gson();
        model.addObject("lstReviewer", gson.toJson(userRepository.getLstValidUsersByRole(Constants.ROLE_REVIEWER)));
//        /CryptoClass c = new CryptoClass();

//        System.out.println(c.HashPassword("hija de puerca"));
//        System.out.println(c.HashPassword("bastardo hijo de puta"));
//        System.out.println(c.HashPassword("puta la pinche mierda"));
//        System.out.println(c.HashPassword("coñooo"));

        return model;
    }

    @RequestMapping(value = "/shared/upload_info/meeting/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCode", Constants.S_MEETING_INCOMPLETE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("statusCase", Constants.CASE_STATUS_MEETING, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

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
                else if (field.equals("statusCase")) {
                    return r.join("status").get("name");
                } else
                    return null;
            }
        }, Case.class, MeetingView.class);

        return result;
    }

    @RequestMapping(value = "/shared/upload_info/verification/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView indexVerif() {
        ModelAndView model = new ModelAndView("/shared/upload_info/verification/index");
        Gson gson = new Gson();
        model.addObject("lstReviewer", gson.toJson(userRepository.getLstValidUsersByRole(Constants.ROLE_REVIEWER)));
        return model;
    }

    @RequestMapping(value = "/shared/upload_info/verification/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listVerif(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCase", Constants.CASE_STATUS_VERIFICATION, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("statusVerif", Constants.VERIFICATION_STATUS_AUTHORIZED, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, Meeting> joinM = r.join("meeting");
                final javax.persistence.criteria.Join<Case, Verification> joinVer = r.join("verification");
                final javax.persistence.criteria.Join<Case, StatusVerification> joinStVer = joinVer.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Meeting, User> joinUsr = joinM.join("reviewer");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinStVer.get("name").alias("statusCode"));
                    add(r.get("idFolder"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinImp.get("gender"));
                    add(joinStVer.get("description"));
                    add(joinUsr.get("id").alias("reviewerId"));
                    add(r.join("status").get("name").alias("statusCase"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusVerif"))
                    return r.join("verification").join("status").get("name");
                else if (field.equals("statusCase"))
                    return r.join("status").get("name");
                else if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("reviewerId"))
                    return r.join("meeting").join("reviewer").get("id");
                else
                    return null;
            }
        },Case.class, MeetingView.class);

        return result;
    }

    @RequestMapping(value = "/shared/upload_info/verification/sources/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listSources(@ModelAttribute JqGridFilterModel opts, @RequestParam Long idCase) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("isAuthorized", true, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("visible", true, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {

            private javax.persistence.criteria.Join<Case, SourceVerification> joinSources;
            private HashMap<Object, javax.persistence.criteria.Join<Case, SourceVerification>> mapJoins = new HashMap<>();

            public <T>javax.persistence.criteria.Join<Case, SourceVerification> selectJoin(final Root<T> r){
                if(mapJoins.containsKey(r)){
                    return mapJoins.get(r);
                }

                joinSources = r.join("verification").join("sourceVerifications");
                mapJoins.put(r, joinSources);

                return joinSources;
            }

            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<Case, SourceVerification> joinSources = selectJoin(r);

                return new ArrayList<Selection<?>>() {{
                    add(joinSources.get("id"));
                    add(joinSources.get("fullName"));
                    add(joinSources.join("relationship").get("name"));
                    add(joinSources.get("address"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase"))
                    return r.get("id");
                else if (field.equals("isAuthorized"))
                    return selectJoin(r).get("isAuthorized");
                else if (field.equals("visible"))
                    return selectJoin(r).get("visible");
                else
                    return null;
            }
        }, Case.class, SourceVerificationDto.class);

        return result;
    }

    @RequestMapping(value = "/shared/upload_info/hearingFormat/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView indexHearingFormat() {
        ModelAndView model = new ModelAndView("/shared/upload_info/hearingFormat/index");
        Gson gson = new Gson();
        model.addObject("lstSupervisor", gson.toJson(userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR)));
        return model;
    }

    @RequestMapping(value = "/shared/upload_info/hearingFormat/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listHearingFormat(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCase", new ArrayList<String>() {{

        }}, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("statusVerif", Constants.VERIFICATION_STATUS_AUTHORIZED, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, Meeting> joinM = r.join("meeting");
                final javax.persistence.criteria.Join<Case, Verification> joinVer = r.join("verification");
                final javax.persistence.criteria.Join<Case, StatusVerification> joinStVer = joinVer.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Case, User> joinUsr = joinM.join("reviewer");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinStVer.get("name").alias("statusCode"));
                    add(r.get("idFolder"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinImp.get("gender"));
                    add(joinStVer.get("description"));
                    add(joinUsr.get("id").alias("reviewerId"));
                    add(r.join("status").get("name").alias("statusCase"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusVerif"))
                    return r.join("verification").join("status").get("name");
                else if (field.equals("statusCase"))
                    return r.join("status").get("name");
                else if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("reviewerId"))
                    return r.join("meeting").join("reviewer").get("id");
                else
                    return null;
            }
        }, Case.class, MeetingView.class);

        return result;
    }

    @RequestMapping(value = "/shared/upload_info/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView indexEval() {
        ModelAndView model = new ModelAndView("/shared/upload_info/index");
        return model;
    }

    @RequestMapping(value = "/shared/upload_info/listTabletEval", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listTabletEval(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("assignmentType", new ArrayList<String>() {{
            add(Constants.MEETING_ASSIGNMENT_TYPE);
            add(Constants.VERIFICATION_ASSIGNMENT_TYPE);
        }}, JqGridFilterModel.COMPARE_IN);
        opts.extraFilters.add(extraFilter);

        extraFilter = new JqGridRulesModel("statusCase", Constants.CASE_STATUS_TABLET_ASSIGNMENT, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, Meeting> joinM = r.join("meeting");
                final javax.persistence.criteria.Join<Meeting, StatusMeeting> joinSM = joinM.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Meeting, User> joinUsr = joinM.join("reviewer");

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
                if (field.equals("assignmentType"))
                    return r.get("assignmentType");
                else if (field.equals("reviewerId"))
                    return r.join("meeting").join("reviewer").get("id");
                if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("statusCase")) {
                    return r.join("status").get("name");
                } else
                    return null;
            }
        }, Case.class, MeetingView.class);

        return result;
    }

    @RequestMapping(value = "/shared/upload_info/saveAssignedCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage saveAssignmentCase(@RequestParam Long idCase, @RequestParam(required = false) Long idUser, @RequestParam String type, @RequestParam(required = false) String sources) {
        ResponseMessage resp = new ResponseMessage();
        try {
            if (type.equals(Constants.MEETING_ASSIGNMENT_TYPE) || type.equals(Constants.MEETING_ASSIGNMENT_TYPE))
                resp = mobileService.saveAssignmentCase(idCase, idUser, type);
            else if (type.equals(Constants.VERIFICATION_ASSIGNMENT_TYPE))
                resp = mobileService.saveAssignmentCaseWhitSources(idCase, type, sources);
            else {
                resp.setHasError(true);
                resp.setTitle("Asginar caso a tableta");
                resp.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
            }
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "saveAssignmentCase", sharedUserService);
            resp.setHasError(true);
            resp.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }
        return resp;
    }

    @RequestMapping(value = "/shared/upload_info/unassignCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage unassignCases(@RequestParam Long idCase) {
        ResponseMessage resp = new ResponseMessage();
        try {
            resp = mobileService.unassignCases(idCase);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "unassignCases", sharedUserService);
            resp.setHasError(true);
            resp.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }
        return resp;
    }

//    @RequestMapping(value = "/shared/upload_info/consumir", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    String consumir() {
//
//        SaludaServiceImpl service = new SaludaServiceImpl("aa","bb");
//
////        SaludaService hello = service.gets
//
//// Possibly set an alternate request URL:
//// ((BindingProvider) greeter).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
////        "http://localhost:63081/greeter");
////        String sayHi = hello.sayHi();
//
//    }

}






