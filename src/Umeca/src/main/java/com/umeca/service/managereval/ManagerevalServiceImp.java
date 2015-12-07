package com.umeca.service.managereval;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.controller.managereval.SaveInformation;
import com.umeca.controller.managereval.SourcesDataView;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.StatusVerification;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.RequestDto;
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
import com.umeca.repository.supervisor.CloseCauseRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.EventService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import com.umeca.repository.supervisor.LogNotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 03/11/2015.
 */

@Service("managerevalService")
public class ManagerevalServiceImp implements ManagerevalService {

    @Autowired
    private HearingFormatService hearingFormatService;
    @Autowired
    private SourceVerificationRepository sourceVerification;

    @Autowired
    private VerificationMethodRepository verificationMethod;

    @Autowired
    private VerificationRepository verification;

    @Autowired
    private StatusVerificationRepository statusVerification;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private StatusCaseRepository statusCase;

    @Autowired
    SharedUserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    LogNotificationRepository logNotificationRepository;

    @Autowired
    CaseRequestRepository caseRequestRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ResponseTypeRepository responseTypeRepository;



    @Transactional
    @Override
    public ResponseMessage save(SourcesDataView sourcesInfo, Long c) {
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
        Case caseDetention = caseRepository.findOne(c);

        caseDetention.setStatus(sc);

        verification.save(_verification);
        caseRepository.save(caseDetention);

        LogNotification notif = new LogNotification();
        notif.setIsObsolete(false);
        notif.setSubject("Se han verificado las fuentes para el caso con carpeta de investigaci&oacute;n " + StringEscape.escapeText(caseDetention.getIdFolder()) + ".");
        notif.setMessage(sourcesInfo.getComment());
        User uSender = userRepository.findOne(userService.GetLoggedUserId());
        notif.setSenderUser(uSender);
        User reviewer =caseDetention.getMeeting().getReviewer();
        notif.setReceiveUser(reviewer);

        logNotificationRepository.save(notif);
        //Responder ultima solicitud
        Long lastRequestID = caseRequestRepository.findLastRequestAuhtorizeIdByCase(c,Constants.ST_REQUEST_AUTHORIZE_SOURCE);
        if(lastRequestID!=null){
            CaseRequest caseRequest= caseRequestRepository.findOne(lastRequestID);
            Message m = new Message();
            m.setCaseDetention(caseDetention);
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

        return new ResponseMessage(false, "");
    }


    @Autowired
    SharedUserService sharedUserService;


    @Autowired
    CaseRepository qCaseRepository;


    @Autowired
    RequestTypeRepository requestTypeRepository;



    @Autowired
    CaseRepository closeCauseRepositoryRepository;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    CloseCauseRepository closeCauseRepository;

    @Autowired
    StatusMeetingRepository statusMeetingRepository;

    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;

    @Autowired
    SourceVerificationRepository sourceVerificationRepository;

    @Autowired
    VerificationRepository verificationRepository;

    @Autowired
    EventService eventService;



    @Override
    public ResponseMessage doMakeRequest(RequestDto requestDto) {
        if (!sharedUserService.isValidPasswordForUser(sharedUserService.GetLoggedUserId(), requestDto.getPassword())) {
            return new ResponseMessage(true, "La contrase&ntilde;a es incorrecta, verifique los datos.");
        }
        if(requestDto.getReason().equals("")){
            return new ResponseMessage(true, "Debes ingresar una raz&oacute;n por la cu&aacute;l quieres realizar la solicitud");
        }
        boolean onlyInterview = false;
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
                    case Constants.ST_REQUEST_GET_FREEDOM:
                        c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_GOT_FREEDOM));
                        c.setDateNotProsecute(new Date());
                        onlyInterview = true;
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

        if(onlyInterview == true){
            eventService.addEvent(Constants.EVENT_ONLY_INTERVIEW,c.getId(),null);
        }

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
    }


}
