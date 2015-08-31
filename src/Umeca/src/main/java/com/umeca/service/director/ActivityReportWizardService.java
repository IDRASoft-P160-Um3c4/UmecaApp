package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.activityReport.WizardReportModel;

public interface ActivityReportWizardService {
    boolean valid(WizardReportModel req, ResponseMessage response);
    void saveReport(WizardReportModel req, User user);
    void doObsolete(User user, Long id, ResponseMessage response);
}
