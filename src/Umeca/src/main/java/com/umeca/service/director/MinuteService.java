package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.dto.shared.ObservationDto;

public interface MinuteService {

    MinuteDto getMinuteDtoById(Long minuteId);

    ResponseMessage doUpsertMinute(MinuteDto minuteDto);

    ResponseMessage doCloseMinute(Long id);

    ResponseMessage doUpsertAgreement(AgreementDto agreementDto);

    ResponseMessage doCloseAgreement(Long id);

    ResponseMessage doUpsertObservation(ObservationDto observationDto);
}
