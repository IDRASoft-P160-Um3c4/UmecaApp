package com.umeca.service.supervisiorManager;


import com.umeca.infrastructure.model.ResponseMessage;

public interface ImputedMissedAttendanceService {

    ResponseMessage doObsoleteImputedMissed(Long id);
}
