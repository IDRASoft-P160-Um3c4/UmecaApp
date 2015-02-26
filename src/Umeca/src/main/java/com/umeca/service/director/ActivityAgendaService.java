package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.agenda.ActivityAgendaEndRequest;
import com.umeca.model.entities.director.agenda.ActivityAgendaRequest;
import com.umeca.model.entities.director.agenda.RequestAgendaActivities;
import com.umeca.model.entities.director.agenda.ResponseAgendaActivities;
import com.umeca.service.account.SharedUserService;

public interface ActivityAgendaService {
    boolean doUpsertDelete(SharedUserService sharedUserService, ActivityAgendaRequest model, User user, ResponseMessage response);
    void getLstActivitiesByUser(SharedUserService sharedUserService, RequestAgendaActivities req, Long userId, ResponseAgendaActivities response);
    boolean endActivity(SharedUserService sharedUserService, ActivityAgendaEndRequest model, User user, ResponseMessage response);
}
