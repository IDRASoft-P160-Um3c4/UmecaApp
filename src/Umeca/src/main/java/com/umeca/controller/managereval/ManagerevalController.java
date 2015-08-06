package com.umeca.controller.managereval;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.model.managerEval.ManagerevalView;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.RequestType;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.managereval.ResponseRequestView;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.RequestDto;
import com.umeca.model.entities.reviewer.dto.SourceVerificationDto;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import com.umeca.repository.reviewer.VerificationRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.repository.supervisor.CloseCauseRepository;
import com.umeca.repository.supervisor.LogNotificationRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    LogNotificationRepository logNotificationRepository;


    @RequestMapping(value = "/managereval/save", method = RequestMethod.POST)
    @Transactional
    public
    @ResponseBody
    ResponseMessage save(@ModelAttribute SourcesDataView sourcesInfo, @RequestParam Long c) {
           try{
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
        StatusVerification sm = statusVerification.findByCode(Constants.VERIFICATION_STATUS_AUTHORIZED);
        _verification.setStatus(sm);

        StatusCase sc = statusCase.findOne(9L);
        Case __case = _case.findOne(c);

        __case.setStatus(sc);

        verification.save(_verification);
        _case.save(__case);

        LogNotification notif = new LogNotification();
        notif.setIsObsolete(false);
        notif.setSubject("Se han verificado las fuentes para el caso con carpeta de investigaci&oacute;n "+StringEscape.escapeText(__case.getIdFolder())+".");
        notif.setMessage(sourcesInfo.getComment());
        User uSender = userRepository.findOne(userService.GetLoggedUserId());
        notif.setSenderUser(uSender);
        User reviewer =__case.getMeeting().getReviewer();
        notif.setReceiveUser(reviewer);

        logNotificationRepository.save(notif);
        //Responder ultima solicitud
        Long lastRequestID = caseRequestRepository.findLastRequestAuhtorizeIdByCase(c,Constants.ST_REQUEST_AUTHORIZE_SOURCE);
        if(lastRequestID!=null){
            CaseRequest caseRequest= caseRequestRepository.findOne(lastRequestID);
            Message m = new Message();
            m.setCaseDetention(__case);
            m.setSender(uSender);
            m.setTitle("");
            m.setIsObsolete(false);
            List<RelMessageUserReceiver> rmur  = new ArrayList<>();
            RelMessageUserReceiver r = new RelMessageUserReceiver();
            r.setUser(reviewer);
            r.setMessage(m);
            r.setIsObsolete(false);
            rmur.add(r);
            m.setMessageUserReceivers(rmur);
            m.setCreationDate(Calendar.getInstance());
            m.setBody(StringEscape.escapeText(sourcesInfo.getComment()));
            messageRepository.save(m);
            caseRequest.setResponseMessage(m);
            caseRequest.setResponseType(responseTypeRepository.findByCode(Constants.RESPONSE_TYPE_DRESSED));
            caseRequestRepository.save(caseRequest);
        }
        //////////
           }catch (Exception e){
               return new ResponseMessage(true,"");
           }

        return new ResponseMessage(false, "");
    }

    @RequestMapping(value = {"/managereval/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCode",
                Constants.VERIFICATION_STATUS_NEW_SOURCE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCaseCode",
                Constants.CASE_STATUS_SOURCE_VALIDATION, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter2);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMeVe = r.join("meeting");
                final Join<Meeting, Imputed> joinMee = joinMeVe.join("imputed");
                final Join<Meeting, CurrentCriminalProceeding> joinCCP = joinMeVe.join("currentCriminalProceeding");
                final Join<CurrentCriminalProceeding, Crime> joinC = joinCCP.join("crimeList");

                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("idFolder"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinC.join("crime").get("name").alias("crime"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCode"))
                    return r.join("verification").join("status").get("name");
                if (field.equals("statusCaseCode"))
                    return r.join("status").get("name");

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

    @RequestMapping(value = {"/managereval/authorizeRequest/index"}, method = RequestMethod.GET)
    public ModelAndView authorizeRequest() {
        ModelAndView mv = new ModelAndView("/managereval/authorizeRequest/index");
        return mv;
    }

    @RequestMapping(value = "/managereval/authorizeRequest/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel authorizeRequestList(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();
        opts.extraFilters = new ArrayList<>();
//        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCase",
//                new ArrayList<String>() {{
//                    add(Constants.CASE_STATUS_REQUEST);
//                }}
//                , JqGridFilterModel.COMPARE_IN
//        );
//        opts.extraFilters.add(extraFilter);
        JqGridRulesModel extraFilter2 = new JqGridRulesModel("responseType",
                new ArrayList<String>() {{
                    add(Constants.RESPONSE_TYPE_PENDING);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter2);
        JqGridRulesModel extraFilter3 = new JqGridRulesModel("requestType",
                new ArrayList<String>() {{
                    add(Constants.ST_REQUEST_AUTHORIZE_SOURCE);
                }}
                , JqGridFilterModel.COMPARE_NOT_IN
        );
        opts.extraFilters.add(extraFilter3);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<CaseRequest, Message> joinCRM = r.join("requestMessage");
                final Join<Message,Case> joinMessCa = joinCRM.join("caseDetention");
                final Join<Case,Meeting> joinMeCa = joinMessCa.join("meeting");
                final Join<Meeting,Imputed> joinMeIm = joinMeCa.join("imputed");
                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(joinMessCa.get("idFolder"));
                    add(joinMeIm.get("name"));
                    add(joinMeIm.get("lastNameP"));
                    add(joinMeIm.get("lastNameM"));
                    add(r.join("requestType").get("description").alias("typeRequest"));
                    add(joinCRM.join("sender").get("fullname").alias("fullNameUser"));
                    add(r.join("responseType").get("name").alias("responseType"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");
                if(field.equals("fullName")){
                    return r.join("imputed").get("name");
                }if(field.equals("statusCase")){
                    return r.join("requestMessage").join("caseDetention").join("status").get("name");
                }if(field.equals("responseType")){
                    return r.join("responseType").get("name");
                }if(field.equals("requestType")){
                    return r.join("requestType").get("name");
                }
                return null;
            }
        }, CaseRequest.class, ResponseRequestView.class);
        return result;
    }

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Autowired
    CaseRequestRepository caseRequestRepository;

    @Autowired
    CaseRepository qCaseRepository;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @RequestMapping(value = "/managereval/responseRequest", method = RequestMethod.POST)
    public ModelAndView responseRequest(@RequestParam Long id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        ModelAndView model = new ModelAndView("/managereval/authorizeRequest/responseRequest");
        try{
            CaseRequest caseRequest = caseRequestRepository.findOne(id);
            Gson gson = new Gson();
            RequestType requestType = caseRequest.getRequestType();
            model.addObject("requestTypeDes", requestType.getDescription());
            model.addObject("requestType", requestType.getName());
            model.addObject("idRequest", id);
            StatusEvaluation statusBefore = gson.fromJson(caseRequest.getStateBefore(), StatusEvaluation.class);
            StatusCase st = statusCaseRepository.findByCode(statusBefore.getCaseDetention());
            model.addObject("statusCase",st.getDescription());
            CaseInfo caseInfo = qCaseRepository.getInfoById(caseRequest.getRequestMessage().getCaseDetention().getId());

            String[] arrProp = new String[]{"folderId", "personName"};
            caseInfo = (CaseInfo) StringEscape.escapeAttrs(caseInfo, arrProp);

            model.addObject("caseInfo", caseInfo);
            Message requestMessage= caseRequest.getRequestMessage();
            model.addObject("reason", requestMessage.getBody());
            model.addObject("user", requestMessage.getSender().getFullname());
            model.addObject("dateRequest", dateFormat.format(requestMessage.getCreationDate()));
            List<SourceVerification> sources = new ArrayList<>();
            if(requestType.getName().equals(Constants.ST_REQUEST_CHANGE_SOURCE)){
                sources = caseRequest.getSources();
            }
            List<SourceVerificationDto> listSourceDto = new ArrayList<>();
            for(SourceVerification s : sources){
                listSourceDto.add(new SourceVerificationDto().dtoSourceVerification(s));
            }
            model.addObject("sources", gson.toJson(listSourceDto));

        }catch (Exception e){

        }
        return model;
    }


    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    StatusMeetingRepository statusMeetingRepository;
    @Autowired
    StatusVerificationRepository statusVerificationRepository;
    @Autowired
    ResponseTypeRepository responseTypeRepository;
    @Autowired
    VerificationRepository verificationRepository;
    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;
    @Autowired
    SourceVerificationRepository sourceVerificationRepository;
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    CloseCauseRepository closeCauseRepository;

    @Transactional
    @RequestMapping(value = "/managereval/authorizeRequest/doResponseRequest", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doMakeRequest(@ModelAttribute RequestDto requestDto) {
        try{
            if (!sharedUserService.isValidPasswordForUser(sharedUserService.GetLoggedUserId(), requestDto.getPassword())) {
                return new ResponseMessage(true, "La contrase&ntilde;a es incorrecta, verifique los datos.");
            }
            if(requestDto.getReason().equals("")){
                return new ResponseMessage(true, "Debes ingresar una raz&oacute;n por la cu&aacute;l quieres realizar la solicitud");
            }
            Gson gson = new Gson();
            Long userId = userService.GetLoggedUserId();
            User userSender =userRepository.findOne(userId);
            CaseRequest caseRequest = caseRequestRepository.findOne(requestDto.getIdRequest());

            Message messageResponse = new Message();
            messageResponse.setIsObsolete(false);
            messageResponse.setTitle("");
            messageResponse.setSender(userSender);
            messageResponse.setBody(StringEscape.escapeText(requestDto.getReason()));
            messageResponse.setCreationDate(Calendar.getInstance());
            RelMessageUserReceiver lisRel = new RelMessageUserReceiver();
            lisRel.setMessage(messageResponse);
            lisRel.setUser(caseRequest.getRequestMessage().getSender());
            lisRel.setIsObsolete(false);
            List<RelMessageUserReceiver> lrmur = new ArrayList<>();
            lrmur.add(lisRel);
            messageResponse.setMessageUserReceivers(lrmur);
            caseRequest.setResponseMessage(messageRepository.save(messageResponse));
            Case c = qCaseRepository.findOne(requestDto.getCaseId());
            switch (requestDto.getResponse()){
                case Constants.RESPONSE_TYPE_ACCEPTED:
                    caseRequest.setResponseType(responseTypeRepository.findByCode(Constants.RESPONSE_TYPE_ACCEPTED));
                    switch (requestDto.getRequestType()){
                        case Constants.ST_REQUEST_CASE_OBSOLETE:
                            c.setStatus(statusCase.findByCode(Constants.CASE_STATUS_CLOSED));
                            c.setCloseCause(closeCauseRepository.findByCode(Constants.CLOSE_CAUSE_OBSOLETE_EVALUATION));
                            c.setCloseDate(new Date());
                            User u = new User();
                            u.setId(sharedUserService.GetLoggedUserId());
                            c.setCloserUser(u);
                            c.getMeeting().setStatus(statusMeetingRepository.findByCode(Constants.S_MEETING_OBSOLETE));
                            c.setDateObsolete(new Date());
                            break;
                        case Constants.ST_REQUEST_EDIT_TECHNICAL_REVIEW:
                            c.setStatus(statusCase.findByCode(Constants.CASE_STATUS_EDIT_TEC_REV));
                            break;
                        case Constants.ST_REQUEST_CHANGE_SOURCE:
                            List<SourceVerification> ls = caseRequest.getSources();
                            for(SourceVerification s : ls){
                                s.setDateComplete(null);
                            }
                            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_VERIFICATION));
                           break;
                        case Constants.ST_REQUEST_EDIT_LEGAL_INFORMATION:
                            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_MEETING));
                            c.getMeeting().setStatus(statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE_LEGAL));
                            Verification v =c.getVerification();

                            List<SourceVerification> svList = v.getSourceVerifications();
                            v.setSourceVerifications(null);
                            for(SourceVerification sv:svList){
                                List<FieldMeetingSource> fmsList = sv.getFieldMeetingSourceList();
                                sv.setFieldMeetingSourceList(null);
                                if(fmsList!=null && fmsList.size()>0){
                                    for(FieldMeetingSource fms: fmsList){
                                        fms.setSourceVerification(null);
                                        fieldMeetingSourceRepository.delete(fms);
                                    }
                                }
                                sv.setVerification(null);
                                sourceVerificationRepository.delete(sv);
                            }

                            c.setVerification(null);
                            verificationRepository.delete(v);
                        break;
                        case Constants.ST_REQUEST_EDIT_MEETING:
                            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_MEETING));
                            c.getMeeting().setStatus(statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE));
                            break;
                        case Constants.ST_REQUEST_NOT_PROSECUTE:
                            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_NOT_PROSECUTE));
                            c.setDateNotProsecute(new Date());

                            break;
                    }
                    break;
                case Constants.RESPONSE_TYPE_REJECTED:
                    caseRequest.setResponseType(responseTypeRepository.findByCode(Constants.RESPONSE_TYPE_REJECTED));
                    StatusEvaluation statusBefore = gson.fromJson(caseRequest.getStateBefore(), StatusEvaluation.class);
                    c.setStatus(statusCaseRepository.findByCode(statusBefore.getCaseDetention()));
                    c.getMeeting().setStatus(statusMeetingRepository.findByCode(statusBefore.getMeeting()));
                    Verification v= c.getVerification();
                    if(v!=null){
                       v.setStatus(statusVerification.findByCode(statusBefore.getVerification()));
                    }
                    break;
            }
            caseRequestRepository.save(caseRequest);
            qCaseRepository.save(c);
            LogNotification notif = new LogNotification();
            notif.setIsObsolete(false);
            User uSender = userRepository.findOne(userService.GetLoggedUserId());
            notif.setSenderUser(uSender);
            String request = requestDto.getResponse().equals(Constants.RESPONSE_TYPE_ACCEPTED)? " acept&oacute; ":" rechaz&oacute; ";
            notif.setSubject("El Coordinador de Evaluaci&oacute;n "+uSender.getFullname()+request+"la solcitud");
            notif.setMessage("Carpeta de investigaci&oacute;n: "+StringEscape.escapeText(c.getIdFolder())+"<br/>Solicitud: "+caseRequest.getRequestType().getDescription()+"<br/>Raz&oacute;n: "+requestDto.getReason());
            notif.setReceiveUser(caseRequest.getRequestMessage().getSender());
            logNotificationRepository.save(notif);

            return new ResponseMessage(false,"Se ha guardado la respuesta con exito");

        }catch (Exception e){
            logException.Write(e, this.getClass(), "doMakeRequest", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al responder la solicitud");
        }

    }


    @RequestMapping(value = {"/managereval/formulationDate/index"}, method = RequestMethod.GET)
    public ModelAndView formulationDate() {
        ModelAndView mv = new ModelAndView("/managereval/formulationDate/index");
        return mv;
    }

    @RequestMapping(value = {"/managereval/formulationDate/upsert"}, method = RequestMethod.POST)
    public ModelAndView upsert(){
        ModelAndView vm = new ModelAndView("/managereval/formulationDate/upsert");
        return vm;
    }
}