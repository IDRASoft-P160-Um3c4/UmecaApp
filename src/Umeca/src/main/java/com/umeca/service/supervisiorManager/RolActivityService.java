package com.umeca.service.supervisiorManager;

import com.umeca.model.ResponseMessage;
import com.umeca.model.dto.supervisorManager.RolActivityRequest;
import com.umeca.model.entities.account.User;
import com.umeca.repository.supervisorManager.RolActivityRepository;

public interface RolActivityService {
    boolean doUpsertDelete(RolActivityRequest model, User user, ResponseMessage response);
}
