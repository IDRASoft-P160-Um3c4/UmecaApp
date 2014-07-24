package com.umeca.service.supervisiorManager;

import com.umeca.model.ResponseMessage;
import com.umeca.model.dto.supervisorManager.ResponseRolActivities;
import com.umeca.model.dto.supervisorManager.RolActivityRequest;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.RequestActivities;

public interface RolActivityService {
    boolean doUpsertDelete(RolActivityRequest model, User user, ResponseMessage response);
    void getLstActivities(RequestActivities req, String statusActivityDeleted, ResponseRolActivities response);
}
