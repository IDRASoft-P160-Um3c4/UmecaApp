package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.IncidenceDto;

/**
 * Created by Administrator on 10/30/2015.
 */
public interface IncidenceService {
    public ResponseMessage addIncidence(IncidenceDto incidenceDto) throws Exception;

    public ResponseMessage justifyIncidence(IncidenceDto incidenceDto);
}
