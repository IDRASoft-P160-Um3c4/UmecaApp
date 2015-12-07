package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.JustifyDto;

/**
 * Created by Administrator on 10/13/2015.
 */
public interface AssistenceService {
    ResponseMessage upsertDevice(JustifyDto delayJustification) throws Exception;
}
