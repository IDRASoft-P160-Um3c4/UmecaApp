package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.dto.shared.ObservationDto;
import com.umeca.model.shared.SelectList;

import java.util.List;

public interface MinuteService {

    MinuteDto getMinuteDtoById(Long minuteId);

    ResponseMessage doUpsertMinute(MinuteDto minuteDto);

    ResponseMessage doCloseMinute(Long id);

    ResponseMessage doUpsertAgreement(AgreementDto agreementDto);

    ResponseMessage doCloseAgreement(Long id);

    ResponseMessage doUpsertObservation(ObservationDto observationDto);

    List<SelectList> getAllObsDtoByAgreementId(Long id);

    AgreementDto getGrlAgreementInfoById(Long id);

    Long countPendingRequestByAgreementId(Long id);

    ResponseMessage doRequestFinishAgreement(AgreementDto agreementDto);
}
