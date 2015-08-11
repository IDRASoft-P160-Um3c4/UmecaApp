package com.umeca.service.supervisor;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.ChannelingInfoDropModel;
import com.umeca.service.account.SharedUserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ChannelingService {
    ChannelingModel getChannelingInfoByCaseId(Long id);
    ChannelingModel getChannelingInfoByCaseIdAndChannelingId(Long id, Long channelingId);
    void getChannelingCatalogs(ModelAndView model);
    void doUpsert(ChannelingModel model, User user, ResponseMessage response);
    void doObsolete(Long id, Long channelingId, User user, ResponseMessage response);
    ChannelingModelSheet getChannelingSheetById(Long id);
    void addLogChannelingDoc(Long caseId, String channelingType);
    void requestDrop(ChannelingDropModel model, User user, ResponseMessage response, SharedUserService userService);
    ChannelingInfoDropModel getAuthRejChannelingDropInfoById(Long id);
    void doAuthRejChannelingDrop(ChannelingInfoDropModel model, User user, SharedUserService userService, ResponseMessage response);
    List<ActivityChannelingModel> getLstActivitiesChanneling(Long id);
    Boolean isFulfilledByChannelingId(Long id);
    void doIsFulfilled(ChannelingFulfilledModel model, User user, ResponseMessage response, SharedUserService userService);
}
