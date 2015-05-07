package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.dto.shared.ObservationDto;
import com.umeca.model.entities.humanReources.RequestAgreement;
import com.umeca.model.entities.humanReources.RequestAgreementDto;
import com.umeca.model.entities.humanReources.RequestMinute;
import com.umeca.model.entities.shared.LogException;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.SelectList;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface MinuteService {

    MinuteDto getMinuteDtoById(Long minuteId);

    ResponseMessage doUpsertMinute(MinuteDto minuteDto);

    ResponseMessage doCloseMinute(Long id);

    ResponseMessage doUpsertAgreement(AgreementDto agreementDto);

    ResponseMessage doAuthRejFinishAgreementRequest(RequestAgreementDto requestAgreementDto);

    ResponseMessage doUpsertObservation(ObservationDto observationDto);

    List<SelectList> getAllObsDtoByAgreementId(Long id);

    AgreementDto getGrlAgreementInfoById(Long id);

    Long countPendingRequestByAgreementId(Long id);

    Long countPendingRequestByMinuteId(Long id);

    ResponseMessage doRequestFinishAgreement(AgreementDto agreementDto);

    RequestAgreementDto getLastRequestInfoByIdAgreementIdType(Long id, String requestType);

    RequestAgreementDto getLastResponseInfoByIdAgreementIdType(Long id, String requestType);

    ResponseMessage doRequestFinishMinute(RequestAgreementDto requestDto);

    ResponseMessage doAuthRejFinishMinuteRequest(RequestAgreementDto requestDto);

    RequestAgreementDto getLastRequestInfoByMinuteIdType(Long id, String requestType);

    RequestAgreementDto getLastResponseInfoByMinuteIdType(Long id, String requestType);

    MinuteDto getMinuteGrlDataById(Long minuteId);

    List<SelectList> getMinuteAssistantsDtoByMinuteId(Long minuteId);

    List<SelectList> getMinuteAttendantByMinuteId(Long minuteId);

    AgreementDto getAgreementDtoByAgreementId(Long agreementId);

    ResponseMessage doUploadAgreementFile(UploadFileRequest uploadRequest,
                                          MultipartHttpServletRequest request, SharedLogExceptionService logExceptionService);

    List<UploadFileGeneric> getAgreementFilesByAgreementId(Long agreementId);

    String getAgreementTitleByAgreementId(Long agreementId);

}
