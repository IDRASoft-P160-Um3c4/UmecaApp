package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.entities.director.minutes.Minute;
import com.umeca.repository.director.AgreementRepository;
import com.umeca.repository.director.MinuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinuteServiceImpl implements MinuteService {

    @Autowired
    private MinuteRepository minuteRepository;
    @Autowired
    private AgreementRepository agreementRepository;

    public MinuteDto getMinuteDtoById(Long minuteId) {
        return minuteRepository.getMinuteDtoById(minuteId);
    }

    public ResponseMessage doUpsertMinute(MinuteDto minuteDto) {
        ResponseMessage resp = new ResponseMessage();
        //TODO
        return resp;
    }

    public ResponseMessage doDeleteMinute(Long minuteId) {
        ResponseMessage resp = new ResponseMessage();
        //TODO
        return resp;
    }
}
