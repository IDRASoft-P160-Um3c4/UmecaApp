package com.umeca.service.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.supervisor.SupervisionCloseCaseLog;
import com.umeca.model.entities.supervisorManager.AuthorizeRejectMonPlan;

import java.util.List;

public interface CaseService {

    List<Case> findByIdFolder(String idFolder);

    Case generateNewCase(Imputed imputed, Integer type);

    Case save(Case caseDet);

    Boolean validateStatus(Long idCase, String statusCase);

    Boolean validateStatus(Long idCase, String statusCase, Class entityCase, String statusEntity);

    ResponseMessage saveConditionaReprieveCase(Case caseDet);

    void saveAuthRejectCloseCase(AuthorizeRejectMonPlan model, User user, Case caseDet);

    boolean isValidCase(Long caseId);

    void doClosePrisonCase(Case caseDet, AuthorizeRejectMonPlan model);

    SupervisionCloseCaseLog generateCloseLog(Case caseDetention);

    ResponseMessage doReopenCase(AuthorizeRejectMonPlan model);

    void saveAuthRejectObsoleteCase(AuthorizeRejectMonPlan model, User user, Case caseDet);
}
