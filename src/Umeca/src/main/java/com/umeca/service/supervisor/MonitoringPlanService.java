package com.umeca.service.supervisor;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanRequest;
import com.umeca.repository.supervisor.MonitoringPlanRepository;
import com.umeca.service.account.SharedUserService;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/13/14
 * Time: 5:47 PM
 */
public interface MonitoringPlanService {
    boolean doUpsertDelete(SharedUserService sharedUserService, MonitoringPlanRepository monitoringPlanRepository, ActivityMonitoringPlanRequest model, User user, ResponseMessage response);
}
