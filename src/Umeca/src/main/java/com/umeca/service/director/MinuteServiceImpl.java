package com.umeca.service.director;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Area;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.dto.shared.ObservationDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.minutes.Agreement;
import com.umeca.model.entities.director.minutes.Assistant;
import com.umeca.model.entities.director.minutes.Minute;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.humanReources.RequestAgreement;
import com.umeca.model.entities.shared.Observation;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.director.AgreementRepository;
import com.umeca.repository.director.MinuteRepository;
import com.umeca.repository.director.ObservationRepository;
import com.umeca.repository.humanResources.RequestAgreementRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        return minute;
    }

    @Override
    @Transactional
    public ResponseMessage doUpsertMinute(MinuteDto minuteDto) {
        ResponseMessage resp = new ResponseMessage();

        Minute minute = fillMinute(minuteDto);
        List<Assistant> newAssistants = new ArrayList<>();

        List<Long> assistantsIds = new Gson().fromJson(minuteDto.getAssistantsIds(), new TypeToken<List<Long>>() {
        }.getType());

        Boolean isNew = false;

        if (assistantsIds != null && assistantsIds.size() > 0) {
            for (Long actId : assistantsIds) {
                Assistant as = new Assistant();
                Employee e = new Employee();
                e.setId(actId);
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
    public ResponseMessage doCloseMinute(Long minuteId) {
        ResponseMessage resp = new ResponseMessage();
        //todo
        resp.setHasError(false);
        resp.setMessage("Se ha cerrado la minuta con éxito.");
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
        return ag;
    }

    @Override
    @Transactional
    public ResponseMessage doCloseAgreement(Long minuteId) {
        ResponseMessage resp = new ResponseMessage();
        //todo
        resp.setHasError(false);
        resp.setMessage("Se ha cerrado el acuerdo con éxito.");
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
        requestAgreementRepository.save(fillRequestFinishAgreement(agreementDto));
        resp.setHasError(false);
        resp.setMessage("Se ha guardado la observación con éxito.");
        return resp;
    }

    private RequestAgreement fillRequestFinishAgreement(AgreementDto agreementDto) {
        RequestAgreement requestAgreement = new RequestAgreement();
        Agreement a = new Agreement();
        a.setId(agreementDto.getId());
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

}
