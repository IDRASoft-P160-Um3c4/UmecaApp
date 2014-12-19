package com.umeca.service.supervisor;


import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.ScheduleNewHearingReq;

public interface ScheduleHearingsService {
    boolean doScheduleNewHearing(ScheduleNewHearingReq model, User user, ResponseMessage response);
}
