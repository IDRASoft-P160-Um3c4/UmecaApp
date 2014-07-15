package com.umeca.service.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;

public interface ManageMonitoringPlanService {

    boolean preAuthorize(Long monPlanId, User user, ResponseMessage message);
    boolean requestAccomplishmentLog(Long id, User user, String sAction, String sComments, ResponseMessage response);
}
