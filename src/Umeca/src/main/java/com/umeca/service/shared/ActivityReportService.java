package com.umeca.service.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.activityReport.ActivityReportRequest;

public interface ActivityReportService {
    boolean save(ActivityReportRequest model, User user, String role);
    boolean delete(Long id, User user, ResponseMessage response);
}