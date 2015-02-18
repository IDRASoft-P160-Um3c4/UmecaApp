package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.agenda.ActivityAgendaRequest;
import com.umeca.service.account.SharedUserService;

public interface ActivityAgendaService {
    boolean doUpsertDelete(SharedUserService sharedUserService, ActivityAgendaRequest model, User user, ResponseMessage response);
}
