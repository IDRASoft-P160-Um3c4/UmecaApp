package com.umeca.service.director;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.activityReport.WizardActivityReport;
import com.umeca.model.entities.director.activityReport.WizardReportInfo;
import com.umeca.model.entities.director.activityReport.WizardReportModel;
import com.umeca.repository.director.WizardActivityReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ActivityReportWizardServiceImpl implements ActivityReportWizardService {


    @Override
    public boolean valid(WizardReportModel req, ResponseMessage response) {

        if(req == null){
            response.setMessage("No hay informe definido");
            return false;
        }

        WizardReportInfo report = req.getReport();

        if (report == null) {
            response.setMessage("No hay información para generar el informe");
            return false;
        }

        if(report.getClStartDate() == null){
            response.setMessage("No está definida la fecha para generar el informe");
            return false;
        }

        if(StringExt.isNullOrWhiteSpace(report.getReportName())){
            response.setMessage("No está definido el nombre del informe");
            return false;
        }

        if(StringExt.isNullOrWhiteSpace(report.getReportDesc())){
            response.setMessage("No está definido la descripción del informe");
            return false;
        }

        if(req.activity != null && req.activity.size() > 0) return true;
        if(req.channeling != null && req.channeling.size() > 0) return true;
        if(req.evaluation != null && req.evaluation.size() > 0) return true;
        if(req.management != null && req.management.size() > 0) return true;
        if(req.minute != null && req.minute.size() > 0) return true;
        if(req.project != null && req.project.size() > 0) return true;
        if(req.supervision != null && req.supervision.size() > 0) return true;

        response.setMessage("Debe existir al menos una actividad (archivo) anexo al informe");
        return false;
    }

    @Autowired
    WizardActivityReportRepository wizardActivityReportRepository;

    @Override
    public void saveReport(WizardReportModel req, User user) {
        WizardActivityReport model = new WizardActivityReport();

        model.setActivity(StringExt.joinToString(req.getActivity()));
        model.setChanneling(StringExt.joinToString(req.getChanneling()));
        model.setEvaluation(StringExt.joinToString(req.getEvaluation()));
        model.setManagement(StringExt.joinToString(req.getManagement()));
        model.setMinute(StringExt.joinToString(req.getMinute()));
        model.setProject(StringExt.joinToString(req.getProject()));
        model.setSupervision(StringExt.joinToString(req.getSupervision()));

        model.setIsObsolete(false);
        model.setCreationDate(Calendar.getInstance());
        model.setCreatorUser(user);

        WizardReportInfo report = req.getReport();
        model.setReportDate(report.getClStartDate());
        model.setReportDescription(report.getReportDesc());
        model.setReportName(report.getReportName());

        wizardActivityReportRepository.save(model);
    }

    @Override
    public void doObsolete(User user, Long id, ResponseMessage responseMessage) {
        WizardActivityReport model = wizardActivityReportRepository.findOne(id);

        if(model == null || model.getIsObsolete())
        {
            responseMessage.setHasError(true);
            responseMessage.setMessage("El informe de actividades no existe o ya fue eliminado");
            return;
        }

        model.setIsObsolete(true);
        model.setDeleteUser(user);
        wizardActivityReportRepository.save(model);
        responseMessage.setHasError(false);
    }
}
