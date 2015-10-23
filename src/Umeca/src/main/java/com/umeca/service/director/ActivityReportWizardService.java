package com.umeca.service.director;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.activityReport.WizardActivityReport;
import com.umeca.model.entities.director.activityReport.WizardReportModel;
import org.springframework.core.io.FileSystemResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface ActivityReportWizardService {
    boolean valid(WizardReportModel req, ResponseMessage response);
    void saveReport(WizardReportModel req, User user);
    void doObsolete(User user, Long id, ResponseMessage response);
    WizardActivityReport findOne(Long id);
    File downloadFilesByReport(WizardActivityReport actRep, Long userId, HttpServletRequest request) throws IOException;
}
