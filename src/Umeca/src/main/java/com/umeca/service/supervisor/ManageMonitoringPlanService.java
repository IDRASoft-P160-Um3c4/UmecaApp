package com.umeca.service.supervisor;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisorManager.AuthRejMonActivitiesRequest;
import com.umeca.service.account.SharedUserService;

public interface ManageMonitoringPlanService {

    boolean preAuthorize(SharedUserService sharedUserService, Long monPlanId, User user, ResponseMessage message);
    boolean requestAccomplishmentLog(Long id, Long fulfillmentReportId, User user, String sAction, String sComments, ResponseMessage response);
    boolean authRejLstMonAct(AuthRejMonActivitiesRequest model, SharedUserService sharedUserService, User user, ResponseMessage response);
}
