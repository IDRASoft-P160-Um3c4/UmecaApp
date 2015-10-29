package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.AbsenceDto;

/**
 * Created by Administrator on 10/29/2015.
 */
public interface AbsenceService {
    public ResponseMessage justifyAbsence(AbsenceDto absenceDto);

    public ResponseMessage addAbsence(AbsenceDto absenceDto) throws Exception;
}
