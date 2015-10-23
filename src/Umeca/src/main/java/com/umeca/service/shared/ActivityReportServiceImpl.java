package com.umeca.service.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.validate.Validator;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.activityReport.ActivityReport;
import com.umeca.model.entities.shared.activityReport.ActivityReportRequest;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.ActivityReportRepository;
import com.umeca.repository.shared.UploadFileGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class ActivityReportServiceImpl implements ActivityReportService {

    @Autowired
    ActivityReportRepository activityReportRepository;

    @Autowired
    UploadFileGenericRepository uploadFileGenericRepository;

    @Override
    @Transactional
    public boolean save(ActivityReportRequest model, User user, String role) {

        ActivityReport activityReport = new ActivityReport();

        activityReport.setDescription(model.getDescription());
        activityReport.setIsObsolete(false);
        activityReport.setCreationDate(Calendar.getInstance());
        Integer reportFor = model.getReportFor();
        activityReport.setReportFor(reportFor == null ? Constants.ACT_REPORT_FOR_NOBODY : reportFor);
        activityReport.setCreatorUser(user);
        activityReport.setReportName(model.getReportName());
        activityReport.setReportRole(role);

        UploadFileGeneric uploadFileGeneric = uploadFileGenericRepository.findOne(model.getFileUploadGenericId());
        activityReport.setUploadFileGeneric(uploadFileGeneric);
        uploadFileGeneric.setObsolete(false);
        uploadFileGenericRepository.save(uploadFileGeneric);
        activityReportRepository.save(activityReport);
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id, User user, ResponseMessage response) {

        ActivityReport activityReport = activityReportRepository.findOne(id);

        if(activityReport == null || activityReport.getIsObsolete()){
            response.setHasError(true);
            response.setMessage("No existe el reporte de actividades o ya fue eliminado");
        }

        activityReport.setIsObsolete(true);

        UploadFileGeneric uploadFile = activityReport.getUploadFileGeneric();
        uploadFile.setDeleteTime(Calendar.getInstance());
        uploadFile.setDeleteUser(user);
        uploadFile.setObsolete(true);
        activityReportRepository.save(activityReport);
        uploadFileGenericRepository.save(uploadFile);
        activityReportRepository.flush();
        uploadFileGenericRepository.flush();

        return false;
    }
}
