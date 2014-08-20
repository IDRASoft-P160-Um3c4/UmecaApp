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
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.RequestTypeRepository;
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

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCase",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_MEETING);
                    add(Constants.CASE_STATUS_VERIFICATION);
                    add(Constants.CASE_STATUS_VERIFICATION_COMPLETE);
                    add(Constants.CASE_STATUS_TECHNICAL_REVIEW);
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
                if(field.equals("fullName")){
                    return r.join("imputed").get("name");
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

    @RequestMapping(value = "/reviewer/caseRequest/doMakeRequest", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doMakeRequest(@ModelAttribute RequestDto requestDto) {
        try{
//            Gson gson = new Gson();
//            CaseRequest caseRequest = new CaseRequest();
//            Message requestMessage = new Message();
//            Long userId = userService.GetLoggedUserId();
//            List<SelectList> usersReceiver = userRepository.getLstValidUsersByRole(Constants.ROLE_EVALUATION_MANAGER);
//
//            User userSender = userRepository.findOne(userId);
//            StatusEvaluation statusEvaluation = qCaseRepository.getStatusEvaluation(requestDto.getCaseId());
//            caseRequest.setStateBefore(gson.toJson(statusEvaluation));
//            caseRequest.setRequestType(requestTypeRepository.findByCode(requestDto.getRequestType()));
//            caseRequest.setSender(userSender);
//            Case c=qCaseRepository.findOne(requestDto.getCaseId());
//            caseRequest.setCaseDetention(c);
//            requestMessage.setSender(userSender);
//            if(usersReceiver!= null && usersReceiver.size()>0){
//                User u = userRepository.findOne(usersReceiver.get(0).getId());
//                caseRequest.setReceiver(u);
//                Message m = new Message();
//                m.setText(requestDto.getReason());
//                m.setCaseDetention(c);
//                m.setSender(userSender);
//                RelMessageUserReceiver rmur = new RelMessageUserReceiver();
//                rmur.setUser(u);
//                rmur.setMessage(m);
//                List<RelMessageUserReceiver> listrmur = new ArrayList<>();
//                listrmur.add(rmur);
//                m.setMessageUserReceivers(listrmur);
//                m.setCreationDate(new Date());
//                requestMessage.setMessageUserReceivers(listrmur);
//                m = messageRepository.save(m);
//                caseRequest.setRequestMessage(m);
//                if (requestDto.getRequestType().equals(Constants.ST_REQUEST_CHANGE_SOURCE)){
//                    caseRequest.setText("Mensaje para fuentes");
//                }
//                caseRequest.setText("lolololo");
//
//                caseRequestRepository.save(caseRequest);
                return new ResponseMessage(false,"");
//            }else{
//                return new ResponseMessage(true,"No existen coordinadores registrados para realizar tu solicitud");
//            }

        }catch (Exception e){
            return new ResponseMessage(true, "Ha ocurrido un error al enviar la solicitud");
        }

    }

}
