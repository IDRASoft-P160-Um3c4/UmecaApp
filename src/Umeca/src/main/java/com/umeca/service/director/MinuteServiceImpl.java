package com.umeca.service.director;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.entities.director.minutes.Assistant;
import com.umeca.model.entities.director.minutes.Minute;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.repository.director.AgreementRepository;
import com.umeca.repository.director.AssistantRepository;
import com.umeca.repository.director.MinuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinuteServiceImpl implements MinuteService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private MinuteRepository minuteRepository;
    @Autowired
    private AgreementRepository agreementRepository;

    public MinuteDto getMinuteDtoById(Long minuteId) {
        return minuteRepository.getMinuteDtoById(minuteId);
    }

    private Minute fillMinute(MinuteDto minuteDto) {
        Minute minute = new Minute();

        if (minuteDto.getId() != null) {
            minute = minuteRepository.findOne(minuteDto.getId());
        } else {
            minute.setIsObsolete(false);
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

    @Transactional
    public ResponseMessage doUpsertMinute(MinuteDto minuteDto) {
        ResponseMessage resp = new ResponseMessage();

        Minute minute = fillMinute(minuteDto);
        List<Assistant> newAssistants = new ArrayList<>();

        List<Long> assistantsIds = new Gson().fromJson(minuteDto.getAssistantsIds(), new TypeToken<List<Long>>() {
        }.getType());

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
            }
        }

        minuteRepository.save(minute);
        minuteRepository.flush();

        resp.setHasError(false);
        resp.setMessage("Se ha guardado la minuta con éxito.");
        resp.setReturnData(minute.getId());
        return resp;
    }

    public ResponseMessage doDeleteMinute(Long minuteId) {
        ResponseMessage resp = new ResponseMessage();
        //TODO
        return resp;
    }
}
