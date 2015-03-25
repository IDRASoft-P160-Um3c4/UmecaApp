package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;

public interface MinuteService {

    MinuteDto getMinuteDtoById(Long minuteId);

    ResponseMessage doUpsertMinute(MinuteDto minuteDto);

    ResponseMessage doDeleteMinute(Long minuteId);

//    ResponseMessage doUpsertAgreement(MinuteDto minuteDto);
//
//    ResponseMessage doDeleteAgreement(MinuteDto minuteDto);

}
