package com.umeca.service.director;

import antlr.debug.MessageAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Area;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.dto.humanResources.CourseAchievementDto;
import com.umeca.model.dto.humanResources.DigitalRecordSummaryDto;
import com.umeca.model.dto.humanResources.EmployeeGeneralDataDto;
import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.dto.shared.MinuteSummaryDto;
import com.umeca.model.dto.shared.ObservationDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.minutes.Agreement;
import com.umeca.model.entities.director.minutes.Assistant;
import com.umeca.model.entities.director.minutes.Minute;
import com.umeca.model.entities.humanReources.*;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.shared.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.director.AgreementRepository;
import com.umeca.repository.director.MinuteRepository;
import com.umeca.repository.director.ObservationRepository;
import com.umeca.repository.humanResources.AgreementFileRelRepository;
import com.umeca.repository.humanResources.RequestAgreementRepository;
import com.umeca.repository.humanResources.RequestMinuteRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MinuteServiceImpl implements MinuteService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private MinuteRepository minuteRepository;
    @Autowired
    private AgreementRepository agreementRepository;
    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private RequestAgreementRepository requestAgreementRepository;
    @Autowired
    private RequestMinuteRepository requestMinuteRepository;
    @Autowired
    private UpDwFileGenericService upDwFileGenericService;
    @Autowired
    private AgreementFileRelRepository agreementFileRelRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MinuteDto getMinuteDtoById(Long minuteId) {
        return minuteRepository.getMinuteDtoById(minuteId);
    }

    private Minute fillMinute(MinuteDto minuteDto) {
        Minute minute = new Minute();

        if (minuteDto.getId() != null) {
            minute = minuteRepository.findOne(minuteDto.getId());
        } else {
            minute.setIsFinished(false);
        }

        minute.setTitle(minuteDto.getTitle());
        minute.setAgenda(minuteDto.getAgenda());

        try {
            minute.setMinuteDate(sdf.parse(minuteDto.getMinuteDate()));
            minute.setStartTime(sdfT.parse(minuteDto.getStartTime()));
            minute.setEndTime(sdfT.parse(minuteDto.getEndTime()));
        } catch (Exception e) {

        }

        if (minuteDto.getAttendantId() != null) {
            Employee e = new Employee();
            e.setId(minuteDto.getAttendantId());
            minute.setAttendant(e);
        }
        minute.setPlace(minuteDto.getPlace());
        minute.setStCode(Constants.ST_CODE_MINUTE_OPEN);
        return minute;
    }

    @Override
    @Transactional
    public ResponseMessage doUpsertMinute(MinuteDto minuteDto) {
        ResponseMessage resp = new ResponseMessage();

        Minute minute = fillMinute(minuteDto);
        List<Assistant> newAssistants = new ArrayList<>();

        List<SelectList> assistants = new Gson().fromJson(minuteDto.getAssistants(), new TypeToken<List<SelectList>>() {
        }.getType());

        Boolean isNew = false;

        if (assistants != null && assistants.size() > 0) {
            for (SelectList act : assistants) {
                Assistant as = new Assistant();
                Employee e = new Employee();
                e.setId(act.getId());
                as.setEmployee(e);
                as.setMinute(minute);
                newAssistants.add(as);
            }

            if (minute.getId() != null) {
                minute.getAssistants().clear();
                minute.getAssistants().addAll(newAssistants);
            } else {
                minute.setAssistants(newAssistants);
                isNew = true;
            }
        }

        minuteRepository.save(minute);
        minuteRepository.flush();

        resp.setHasError(false);
        resp.setMessage("Se ha guardado la minuta con éxito.");
        SelectList retData = new SelectList();
        retData.setId(minute.getId());
        retData.setSpecification(isNew);
        resp.setReturnData(retData);
        return resp;
    }

    @Override
    @Transactional
    public ResponseMessage doUpsertAgreement(AgreementDto agreementDto) {
        ResponseMessage resp = new ResponseMessage();
        agreementRepository.save(fillAgreement(agreementDto));
        resp.setHasError(false);
        resp.setMessage("Se ha guardado la minuta con éxito.");
        return resp;
    }

    private Agreement fillAgreement(AgreementDto agreementDto) {


        Agreement ag = new Agreement();

        if (agreementDto.getId() != null) {
            ag = agreementRepository.findOne(agreementDto.getId());
        }

        ag.setTitle(agreementDto.getTitle());
        ag.setTheme(agreementDto.getTheme());

        try {
            ag.setAgreementDate(sdf.parse(agreementDto.getAgreementDate()));
        } catch (Exception e) {
        }

        ag.setComments(agreementDto.getComments());
        Area ar = new Area();
        ar.setId(agreementDto.getAreaId());
        ag.setArea(ar);
        ag.setIsDone(false);
        ag.setIsFinished(false);
        ag.setSpecArea(agreementDto.getSpecArea());
        Minute m = new Minute();
        m.setId(agreementDto.getMinuteId());
        ag.setMinute(m);
        ag.setStCode(Constants.ST_CODE_AGREEMENT_OPEN);
        return ag;
    }

    @Override
    @Transactional
    public ResponseMessage doAuthRejFinishAgreementRequest(RequestAgreementDto requestAgreementDto) {
        ResponseMessage resp = new ResponseMessage();

        List<RequestAgreement> allRequest = requestAgreementRepository.getAllRequestByAgreementIdType(requestAgreementDto.getId(), Constants.REQUEST_AGREEMENT_TYPE_FINISH);
        RequestAgreement req = allRequest.get(0);

        req.setResponseDate(new Date());
        req.setResponseComment(requestAgreementDto.getComments());
        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        req.setResponseUser(u);

        Agreement agreement = req.getAgreement();

        if (requestAgreementDto.getAuthorize() == true) {
            req.setResponseType(Constants.RESPONSE_AGREEMENT_TYPE_FINISH_AUTH);
            agreement.setIsFinished(true);
            agreement.setFinishDate(new Date());
            agreement.setFinishedComment(requestAgreementDto.getComments());
            agreement.setFinishUser(u);
            agreement.setStCode(Constants.ST_CODE_AGREEMENT_FINISHED);
            req.setAgreement(agreement);
        } else {
            req.setResponseType(Constants.RESPONSE_AGREEMENT_TYPE_FINISH_REJECT);
            agreement.setIsDone(false);
            agreement.setStCode(Constants.ST_CODE_AGREEMENT_FINISH_REJECT);
            req.setAgreement(agreement);
        }

        Message me = fillAgreementMessage(req);

        requestAgreementRepository.save(req);
        messageRepository.save(me);

        resp.setHasError(false);
        resp.setMessage("Se ha guardado la respuesta de  solicitud con éxito.");
        return resp;
    }

    @Override
    @Transactional
    public ResponseMessage doUpsertObservation(ObservationDto observationDto) {
        ResponseMessage resp = new ResponseMessage();
        observationRepository.save(fillObservation(observationDto));
        resp.setHasError(false);
        resp.setMessage("Se ha guardado la observación con éxito.");
        return resp;
    }

    private Observation fillObservation(ObservationDto observationDto) {
        Observation obs = new Observation();
        obs.setComment(observationDto.getComment());
        Agreement agreement = new Agreement();
        agreement.setId(observationDto.getAgreementId());
        obs.setAgreement(agreement);
        User user = new User();
        user.setId(sharedUserService.GetLoggedUserId());
        obs.setRegisterUser(user);
        obs.setRegisterDate(new Date());
        return obs;
    }

    @Override
    public List<SelectList> getAllObsDtoByAgreementId(Long id) {
        return observationRepository.getAllObsDtoByAgreementId(id);
    }

    @Override
    public AgreementDto getGrlAgreementInfoById(Long id) {
        return agreementRepository.getGrlAgreementInfoById(id);
    }

    @Override
    @Transactional
    public ResponseMessage doRequestFinishAgreement(AgreementDto agreementDto) {
        ResponseMessage resp = new ResponseMessage();
        RequestAgreement reqA = fillRequestFinishAgreement(agreementDto);
        Message m = fillAgreementMessage(reqA);
        requestAgreementRepository.save(reqA);
        messageRepository.save(m);
        resp.setHasError(false);
        resp.setMessage("Se ha guardado la solicitud con éxito.");
        return resp;
    }

    private RequestAgreement fillRequestFinishAgreement(AgreementDto agreementDto) {
        RequestAgreement requestAgreement = new RequestAgreement();
        Agreement a = agreementRepository.findOne(agreementDto.getId());
        a.setStCode(Constants.ST_CODE_AGREEMENT_FINISH_REQUEST);
        a.setIsDone(agreementDto.getIsDone());
        requestAgreement.setAgreement(a);
        requestAgreement.setRequestComment(agreementDto.getComments());
        requestAgreement.setRequestType(Constants.REQUEST_AGREEMENT_TYPE_FINISH);
        requestAgreement.setRequestDate(new Date());
        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        requestAgreement.setRequestUser(u);
        return requestAgreement;
    }

    @Override
    public Long countPendingRequestByAgreementId(Long id) {
        return requestAgreementRepository.countPendingRequestByAgreementId(id);
    }

    @Override
    public Long countPendingRequestByMinuteId(Long id) {
        return requestMinuteRepository.countPendingRequestByMinuteId(id);
    }

    @Override
    public RequestAgreementDto getLastRequestInfoByIdAgreementIdType(Long id, String requestType) {
        List<RequestAgreement> allRequest = requestAgreementRepository.getAllRequestByAgreementIdType(id, requestType);
        RequestAgreement req = allRequest.get(0);
        RequestAgreementDto dto = new RequestAgreementDto(req, null);
        return dto;
    }

    @Override
    public RequestAgreementDto getLastResponseInfoByIdAgreementIdType(Long id, String requestType) {
        List<RequestAgreement> allRequest = requestAgreementRepository.getAllResponsedRequestByAgreementIdType(id, requestType);
        RequestAgreement req = allRequest.get(0);
        RequestAgreementDto dto = new RequestAgreementDto(req, true);
        return dto;
    }

    @Override
    @Transactional
    public ResponseMessage doRequestFinishMinute(RequestAgreementDto requestDto) {
        ResponseMessage resp = new ResponseMessage();

        RequestMinute rM = fillRequestFinishMinute(requestDto);
        Message me = fillMinuteMessage(rM);
        requestMinuteRepository.save(rM);
        messageRepository.save(me);
        resp.setHasError(false);
        resp.setMessage("Se ha guardado la solicitud con éxito.");
        return resp;
    }

    private Message fillAgreementMessage(RequestAgreement request) {
        Message me = new Message();

        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        me.setSender(u);

        String requestType = request.getRequestType();
        String responseType = request.getResponseType();
        String title = "", body = "", auth = "";
        List<RelMessageUserReceiver> lstReceiver = new ArrayList<>();
        List<User> lstUsr = new ArrayList<>();

        if (request.getId() != null && responseType != null && responseType != "") {

            switch (responseType) {
                case Constants.RESPONSE_AGREEMENT_TYPE_FINISH_AUTH:
                    auth = "autoriza";
                    break;
                case Constants.RESPONSE_AGREEMENT_TYPE_FINISH_REJECT:
                    auth = "rechaza";
                    break;
            }

            title = "Se " + auth + " la solicitud de conclusi&oacute;n de acuerdo";
            body = "El usuario <strong>" + sharedUserService.findOne(request.getResponseUser().getId()).getFullname() + "</strong> " + auth + " la solicitud de conclusi&oacute;n del acuerdo <strong>\"" + request.getAgreement().getTitle() + "\"</strong>: <br/> " + request.getResponseComment();

            lstUsr.addAll(sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_HUMAN_RESOURCES));
            lstUsr.addAll(sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_SUPERVISOR_MANAGER));
            lstUsr.addAll(sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_EVALUATION_MANAGER));

        } else if (requestType != null && requestType != "") {
            switch (requestType) {
                case Constants.REQUEST_AGREEMENT_TYPE_FINISH:
                    title = "Solicitud de conclusi&oacute;n de acuerdo";
                    body = "El usuario <strong>" + sharedUserService.findOne(request.getRequestUser().getId()).getFullname() + "</strong> solicita la conclusi&oacute;n del acuerdo <strong>\"" + request.getAgreement().getTitle() + "\"</strong>: <br/> " + request.getRequestComment();
                    break;
            }

            lstUsr = sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_DIRECTOR);

        }

        me.setTitle(title);
        me.setBody(body);
        me.setCreationDate(Calendar.getInstance());
        me.setIsObsolete(false);

        for (User act : lstUsr) {
            RelMessageUserReceiver actRel = new RelMessageUserReceiver();
            actRel.setIsObsolete(false);
            actRel.setMessage(me);
            actRel.setUser(act);
            lstReceiver.add(actRel);
        }

        me.setMessageUserReceivers(lstReceiver);

        return me;
    }

    private Message fillMinuteMessage(RequestMinute request) {
        Message me = new Message();

        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        me.setSender(u);

        String requestType = request.getRequestType();
        String responseType = request.getResponseType();
        String title = "", body = "", auth = "";
        List<RelMessageUserReceiver> lstReceiver = new ArrayList<>();
        List<User> lstUsr = new ArrayList<>();

        if (request.getId() != null && responseType != null && responseType != "") {

            switch (responseType) {
                case Constants.RESPONSE_MINUTE_TYPE_FINISH_AUTH:
                    auth = "autoriza";
                    break;
                case Constants.RESPONSE_MINUTE_TYPE_FINISH_REJECT:
                    auth = "rechaza";
                    break;
            }

            title = "Se " + auth + " la solicitud de cierre de minuta";
            body = "El usuario <strong>" + sharedUserService.findOne(request.getResponseUser().getId()).getFullname() + "</strong> " + auth + " la solicitud de cierre de la minuta <strong>\"" + request.getMinute().getTitle() + "\"</strong>: <br/> " + request.getResponseComment();

            lstUsr.addAll(sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_HUMAN_RESOURCES));
            lstUsr.addAll(sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_SUPERVISOR_MANAGER));
            lstUsr.addAll(sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_EVALUATION_MANAGER));

        } else if (requestType != null && requestType != "") {
            switch (requestType) {
                case Constants.REQUEST_MINUTE_TYPE_FINISH:
                    title = "Solicitud de cierre de minuta";
                    body = "El usuario <strong>" + sharedUserService.findOne(request.getRequestUser().getId()).getFullname() + "</strong> solicita el cierre de la minuta <strong>\"" + request.getMinute().getTitle() + "\"</strong>: <br/> " + request.getRequestComment();
                    break;
            }

            lstUsr = sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_DIRECTOR);
        }

        me.setTitle(title);
        me.setBody(body);
        me.setCreationDate(Calendar.getInstance());
        me.setIsObsolete(false);

        for (User act : lstUsr) {
            RelMessageUserReceiver actRel = new RelMessageUserReceiver();
            actRel.setIsObsolete(false);
            actRel.setMessage(me);
            actRel.setUser(act);
            lstReceiver.add(actRel);
        }

        me.setMessageUserReceivers(lstReceiver);

        return me;
    }

    private RequestMinute fillRequestFinishMinute(RequestAgreementDto requestDto) {
        RequestMinute requestMinute = new RequestMinute();
        Minute m = minuteRepository.findOne(requestDto.getId());
        m.setStCode(Constants.ST_CODE_MINUTE_FINISH_REQUEST);
        requestMinute.setMinute(m);
        requestMinute.setRequestComment(requestDto.getComments());
        requestMinute.setRequestType(Constants.REQUEST_MINUTE_TYPE_FINISH);
        requestMinute.setRequestDate(new Date());
        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        requestMinute.setRequestUser(u);
        return requestMinute;
    }

    @Override
    @Transactional
    public ResponseMessage doAuthRejFinishMinuteRequest(RequestAgreementDto requestDto) {
        ResponseMessage resp = new ResponseMessage();

        List<RequestMinute> allRequest = requestMinuteRepository.getAllRequestByMinuteIdType(requestDto.getId(), Constants.REQUEST_MINUTE_TYPE_FINISH);
        RequestMinute req = allRequest.get(0);

        req.setResponseDate(new Date());
        req.setResponseComment(requestDto.getComments());
        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        req.setResponseUser(u);

        Minute minute = req.getMinute();

        if (requestDto.getAuthorize() == true) {
            req.setResponseType(Constants.RESPONSE_MINUTE_TYPE_FINISH_AUTH);
            minute.setIsFinished(true);
            minute.setFinishDate(new Date());
            minute.setFinishComment(requestDto.getComments());
            minute.setFinishUser(u);
            minute.setStCode(Constants.ST_CODE_MINUTE_FINISHED);
        } else {
            req.setResponseType(Constants.RESPONSE_MINUTE_TYPE_FINISH_REJECT);
            minute.setIsFinished(false);
            minute.setStCode(Constants.ST_CODE_MINUTE_FINISH_REJECT);
        }

        for (Agreement agreement : minute.getAgreements()) {
            if (agreement.getIsFinished() == false) {
                agreement.setStCode(Constants.ST_CODE_MINUTE_FINISHED);
                agreement.setFinishedComment("La minuta ha sido cerrada: " + requestDto.getComments());
                agreement.setIsFinished(true);
                agreement.setFinishDate(new Date());
                agreement.setFinishUser(u);
            }
        }

        req.setMinute(minute);

        Message me = fillMinuteMessage(req);
        requestMinuteRepository.save(req);
        messageRepository.save(me);

        resp.setHasError(false);
        resp.setMessage("Se ha guardado la respuesta de  solicitud con éxito.");
        return resp;
    }

    @Override
    public RequestAgreementDto getLastRequestInfoByMinuteIdType(Long id, String requestType) {
        List<RequestMinute> allRequest = requestMinuteRepository.getAllRequestByMinuteIdType(id, requestType);
        RequestMinute req = allRequest.get(0);
        RequestAgreementDto dto = new RequestAgreementDto(req, null);
        return dto;
    }

    @Override
    public RequestAgreementDto getLastResponseInfoByMinuteIdType(Long id, String requestType) {
        List<RequestMinute> allRequest = requestMinuteRepository.getAllResponsedRequestByMinuteIdType(id, requestType);
        RequestMinute req = allRequest.get(0);
        RequestAgreementDto dto = new RequestAgreementDto(req, true);
        return dto;
    }

    @Override
    public MinuteDto getMinuteGrlDataById(Long minuteId) {
        return minuteRepository.getMinuteGrlDataById(minuteId);
    }

    @Override
    public List<SelectList> getMinuteAssistantsDtoByMinuteId(Long minuteId) {
        return sharedUserService.doFinalUsrEmployeeList(minuteRepository.getAssistantsDtoByMinuteId(minuteId));
    }

    @Override
    public List<SelectList> getMinuteAttendantByMinuteId(Long minuteId) {
        return sharedUserService.doFinalUsrEmployeeList(minuteRepository.getAattendantByMinuteId(minuteId));
    }

    @Override
    public AgreementDto getAgreementDtoByAgreementId(Long agreementId) {
        return agreementRepository.getAgreementDtoById(agreementId);
    }

    @Override
    public ResponseMessage doUploadAgreementFile(UploadFileRequest uploadRequest,
                                                 MultipartHttpServletRequest request, SharedLogExceptionService logExceptionService) {
        ResponseMessage resMsg = new ResponseMessage();

        Long userId = sharedUserService.GetLoggedUserId();

        Iterator<String> itr = request.getFileNames();

        if (upDwFileGenericService.isValidRequestFile(itr, resMsg) == false) {
            return resMsg;
        }

        UploadFileGeneric file = new UploadFileGeneric();

        MultipartFile mpf = request.getFile(itr.next());
        if (upDwFileGenericService.isValidExtension(mpf, file, resMsg) == false)
            return resMsg;

        User user = new User();
        user.setId(userId);
        upDwFileGenericService.fillUploadFileGeneric(mpf, file, uploadRequest, user);

        //valida nombre archivo
        if (upDwFileGenericService.hasAvailability(file, resMsg, userId) == false)
            return resMsg;

        String path = request.getSession().getServletContext().getRealPath("");
        path = new File(path, file.getPath()).toString();

        if (upDwFileGenericService.saveOnDiskUploadFile(mpf, path, file, resMsg, logExceptionService, sharedUserService) == false)
            return resMsg;


        Agreement a = new Agreement();
        a.setId(uploadRequest.getAgreementId());
        AgreementFileRel rel = new AgreementFileRel();
        rel.setAgreement(a);
        file.setObsolete(false);
        upDwFileGenericService.save(file);
        rel.setUploadFileGeneric(file);
        agreementFileRelRepository.save(rel);

        resMsg.setMessage("El archivo " + file.getFileName() + " fue subido de forma correcta. Por favor presione el botón guardar para finalizar el proceso.");
        resMsg.setHasError(false);
        if (uploadRequest.getCloseUploadFile() != null && uploadRequest.getCloseUploadFile()) {

            resMsg.setUrlToGo("close");
            resMsg.setReturnData(file.getPath() + "/" + file.getRealFileName());
        } else {
            resMsg.setReturnData(file.getId());
        }

        return resMsg;
    }

    public List<UploadFileGeneric> getAgreementFilesByAgreementId(Long agreementId) {
        return agreementFileRelRepository.getAgreementFilesByAgreementId(agreementId);
    }

    public String getAgreementTitleByAgreementId(Long agreementId) {
        return agreementRepository.getAgreementTitleByAgreementId(agreementId);
    }

    @Override
    public MinuteSummaryDto fillMinuteSummary(Long minuteId) {

        MinuteSummaryDto summary = new MinuteSummaryDto();

        summary.setMinuteDto(minuteRepository.getMinuteGrlDataById(minuteId));
        summary.setAttendant(getMinuteAttendantByMinuteId(minuteId).get(0));
        summary.setAssistants(getMinuteAssistantsDtoByMinuteId(minuteId));
        summary.setAgreements(agreementRepository.getAgreementsInfoByMinuteId(minuteId));

        return summary;
    }
}
