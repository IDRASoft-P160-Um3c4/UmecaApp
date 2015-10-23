package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.DeviceDto;

/**
 * Created by Administrator on 8/18/2015.
 */
public interface DeviceService {
    ResponseMessage upsertDevice(DeviceDto deviceDto);
}
