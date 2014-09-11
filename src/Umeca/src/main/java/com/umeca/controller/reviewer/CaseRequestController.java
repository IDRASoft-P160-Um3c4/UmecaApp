package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.RequestType;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.managereval.CaseEvaluationView;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.MeetingView;
import com.umeca.model.entities.reviewer.View.RequestEvaluationView;
import com.umeca.model.entities.reviewer.dto.RequestDto;
import com.umeca.model.entities.reviewer.dto.SourceVerificationDto;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.RequestTypeRepository;
import com.umeca.repository.catalog.ResponseTypeRepository;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/08/14
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CaseRequestController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = "/reviewer/caseRequest/index", method = RequestMethod.GET)
    public String index() {
        return "/reviewer/caseRequest/index";
    }

    @RequestMapping(value = "/reviewer/caseRequest/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("reviewerId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCase",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_MEETING);
                    add(Constants.CASE_STATUS_VERIFICATION);
                    add(Constants.CASE_STATUS_VERIFICATION_COMPLETE);
                    add(Constants.CASE_STATUS_TECHNICAL_REVIEW);
                    add(Constants.CASE_STATUS_REQUEST);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter2);
        JqGridRulesModel extraFilter3 = new JqGridRulesModel("statusMeeting",
                new ArrayList<String>() {{
                    add(Constants.S_MEETING_INCOMPLETE_LEGAL);
                    add(Constants.S_MEETING_COMPLETE);
                    add(Constants.S_MEETING_INCOMPLETE);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter3);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Meeting,Case> joinMeCa = r.join("caseDetention");
                final Join<Meeting,Imputed> joinMeIm = r.join("imputed");
                final Join<Case, StatusCase> joinCaSt = joinMeCa.join("status");
                final Join<Meeting,StatusMeeting> joinMeSt = r.join("status", JoinType.LEFT);
                final Join<Verification,StatusVerification> joinVeSt = joinMeCa.join("verification",JoinType.LEFT).join("status",JoinType.LEFT);
                final Join<Meeting,TechnicalReview> joinTR = joinMeCa.join("technicalReview",JoinType.LEFT);
                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>(){{
                    add(joinMeCa.get("id"));
                    add(joinMeCa.get("idFolder"));
                    add(joinMeIm.get("name"));
                    add(joinMeIm.get("lastNameP"));
                    add(joinMeIm.get("lastNameM"));
                    add(joinMeSt.get("name").alias("statusMeeting"));
                    add(joinVeSt.get("name").alias("statusVerification"));
                    add(joinCaSt.get("name").alias("statusCase"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");
                else if(field.equals("fullName")){
                    return r.join("imputed").get("name");
                } else if(field.equals("reviewerId")){
                    return r.join("reviewer").get("id");
                } else
                if(field.equals("statusCase")){
                    return r.join("caseDetention").join("status").get("name");
                } else
                if(field.equals("statusMeeting")){
                    return r.join("status").get("name");
                }
                return null;
            }
        }, Meeting.class, RequestEvaluationView.class);
        return result;
    }

    @Autowired
    RequestTypeRepository requestTypeRepository;
    @Autowired
    CaseRepository qCaseRepository;
    @Autowired
    SourceVerificationRepository sourceVerificationRepository;

    @RequestMapping(value = "/reviewer/caseRequest/makeRequest", method = RequestMethod.POST)
    public ModelAndView makeRequest(@RequestParam(required = true) String id, @RequestParam Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/caseRequest/makeRequest");
        try{
            RequestType requestType = requestTypeRepository.findByCode(id);
            if(requestType==null)
                return model;
            Gson gson = new Gson();
            model.addObject("requestTypeDes",requestType.getDescription());
            CaseInfo caseInfo = qCaseRepository.getInfoById(idCase);
            model.addObject("caseInfo", caseInfo);
            model.addObject("requestType", requestType.getName());
            List<SourceVerification> sources = new ArrayList<>();
            if(requestType.getName().equals(Constants.ST_REQUEST_CHANGE_SOURCE)){
                sources = sourceVerificationRepository.getAllSourceCompleteByIdCase(idCase);
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
    CaseRequestRepository caseRequestRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    StatusCaseRepository statusCaseRepository;
    @Autowired
    ResponseTypeRepository responseTypeRepository;

    @RequestMapping(value = "/reviewer/caseRequest/doMakeRequest", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doMakeRequest(@ModelAttribute RequestDto requestDto) {
        try{
            if (!sharedUserService.isValidPasswordForUser(sharedUserService.GetLoggedUserId(), requestDto.getPassword())) {
                return new ResponseMessage(true, "La contrase&ntilde;a es incorrecta, verfifique los datos.");
            }
            if(requestDto.getReason().equals("")){
                return new ResponseMessage(true, "Debes ingresar una raz&oacute;n por la cu&acute;l quieres realizar la solicitud");
            }
            Gson gson = new Gson();
            CaseRequest caseRequest = new CaseRequest();
            Message requestMessage = new Message();
            Long userId = userService.GetLoggedUserId();
            List<SelectList> usersReceiver = userRepository.getLstValidUsersByRole(Constants.ROLE_EVALUATION_MANAGER);
            User userSender = userRepository.findOne(userId);
            List<User> receiverList = new ArrayList<>();
            Case c=qCaseRepository.findOne(requestDto.getCaseId());
            StatusEvaluation statusEvaluation = qCaseRepository.getStatusEvaluation(requestDto.getCaseId());

            //fillCaseRequest
            caseRequest.setStateBefore(gson.toJson(statusEvaluation));
            caseRequest.setRequestType(requestTypeRepository.findByCode(requestDto.getRequestType()));
            requestMessage.setSender(userSender);
            if(usersReceiver!= null && usersReceiver.size()>0){
                Message m = new Message();
                m.setText(requestDto.getReason());
                m.setCaseDetention(c);
                m.setSender(userSender);
                List<RelMessageUserReceiver> listrmur = new ArrayList<>();
                for(SelectList ur : usersReceiver){
                    User u = userRepository.findOne(ur.getId());
                    RelMessageUserReceiver rmr = new RelMessageUserReceiver();
                    rmr.setUser(u);
                    rmr.setMessage(m);
                    listrmur.add(rmr);
                }
                m.setMessageUserReceivers(listrmur);
                m.setCreationDate(new Date());
                requestMessage.setMessageUserReceivers(listrmur);
                m = messageRepository.save(m);
                caseRequest.setRequestMessage(m);
                if (requestDto.getRequestType().equals(Constants.ST_REQUEST_CHANGE_SOURCE)){
                    if(requestDto.getSourcesId() == null || requestDto.getSourcesId().equals("")){
                        return new ResponseMessage(true,"Debes seleccionar una fuente para realizar la solicitud");
                    }
                    List<SourceVerification> sourcesSelected = new ArrayList<>();
                    String[] sources = requestDto.getSourcesId().split(",");
                    for(String s: sources){
                        SourceVerification sv = sourceVerificationRepository.findOne(Long.parseLong(s));
                        sv.setCaseRequest(caseRequest);
                        sourcesSelected.add(sv);
                    }
                    caseRequest.setSources(sourcesSelected);
                }
                caseRequest.setResponseType(responseTypeRepository.findByCode(Constants.RESPONSE_TYPE_PENDING));
                caseRequestRepository.save(caseRequest);
                c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_REQUEST));
                 qCaseRepository.save(c);
                return new ResponseMessage(false,"");
            }else{
                return new ResponseMessage(true,"No existen coordinadores registrados para realizar tu solicitud");
            }

        }catch (Exception e){
            return new ResponseMessage(true, "Ha ocurrido un error al enviar la solicitud");
        }

    }

}
