package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.BonusTimeDto;


/**
 * Created by Administrator on 10/22/2015.
 */

public interface BonusTimeService {
    ResponseMessage upsertBonusTime(BonusTimeDto bonusTimeDto);
}
