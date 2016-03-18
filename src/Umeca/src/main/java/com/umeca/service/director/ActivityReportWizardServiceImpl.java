package com.umeca.service.director;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.activityReport.WizardActivityReport;
import com.umeca.model.entities.director.activityReport.WizardReportInfo;
import com.umeca.model.entities.director.activityReport.WizardReportModel;
import com.umeca.model.entities.director.agenda.ActivityAgendaNotice;
import com.umeca.model.entities.shared.activityReport.ActivityReportDto;
import com.umeca.model.shared.Constants;
import com.umeca.repository.director.ProjectActivityRepository;
import com.umeca.repository.director.WizardActivityReportRepository;
import com.umeca.repository.shared.ActivityReportRepository;
import com.umeca.repository.supervisor.ActivityAgendaRepository;
import com.umeca.service.shared.UpDwFileGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ActivityReportWizardServiceImpl implements ActivityReportWizardService {


    @Override
    public boolean valid(WizardReportModel req, ResponseMessage response) {

        if (req == null) {
            response.setMessage("No hay informe definido");
            return false;
        }

        WizardReportInfo report = req.getReport();

        if (report == null) {
            response.setMessage("No hay información para generar el informe");
            return false;
        }

        if (report.getClStartDate() == null) {
            response.setMessage("No está definida la fecha para generar el informe");
            return false;
        }

        if (StringExt.isNullOrWhiteSpace(report.getReportName())) {
            response.setMessage("No está definido el nombre del informe");
            return false;
        }

        if (StringExt.isNullOrWhiteSpace(report.getReportDesc())) {
            response.setMessage("No está definido la descripción del informe");
            return false;
        }

        if (req.activity != null && req.activity.size() > 0) return true;
        if (req.channeling != null && req.channeling.size() > 0) return true;
        if (req.evaluation != null && req.evaluation.size() > 0) return true;
        if (req.management != null && req.management.size() > 0) return true;
        if (req.minute != null && req.minute.size() > 0) return true;
        if (req.project != null && req.project.size() > 0) return true;
        if (req.supervision != null && req.supervision.size() > 0) return true;

        response.setMessage("Debe existir al menos una actividad (archivo) anexo al informe");
        return false;
    }

    @Autowired
    WizardActivityReportRepository wizardActivityReportRepository;

    @Autowired
    ActivityReportRepository activityReportRepository;

    @Autowired
    ProjectActivityRepository projectActivityRepository;

    @Autowired
    ActivityAgendaRepository activityAgendaRepository;

    @Autowired
    UpDwFileGenericService upDwFileGenericService;

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

        if (model == null || model.getIsObsolete().equals(true)) {
            responseMessage.setHasError(true);
            responseMessage.setMessage("El informe de actividades no existe o ya fue eliminado");
            return;
        }

        model.setIsObsolete(true);
        model.setDeleteUser(user);
        wizardActivityReportRepository.save(model);
        responseMessage.setHasError(false);
    }

    @Override
    public WizardActivityReport findOne(Long id) {
        return wizardActivityReportRepository.findOne(id);
    }

    @Override
    public File downloadFilesByReport(WizardActivityReport actRep, Long userId, HttpServletRequest request) throws IOException {

        File fileOut = upDwFileGenericService.createDownloadableFile("Informe Global de Actividades - " + actRep.getReportName(), ".zip", request);
        FileOutputStream fos = new FileOutputStream(fileOut);
        ZipOutputStream zos = new ZipOutputStream(fos);
        byte[] buffer = new byte[1024];

        CreateMainInfoReport(actRep, zos, buffer);
        if (actRep.getActivity() != null) CreateActivityReport(actRep, zos, buffer, userId);
        if (actRep.getSupervision() != null) CreateSupervisionReport(actRep, zos, buffer, request);
        if (actRep.getEvaluation() != null) CreateEvaluationReport(actRep, zos, buffer, request);
        if (actRep.getManagement() != null) CreateManagementReport(actRep, zos, buffer, request);
        if (actRep.getProject() != null) CreateProjectReport(actRep, zos, buffer, userId);
        if (actRep.getChanneling() != null) CreateChannelingReport(actRep, zos, buffer, request);
        if (actRep.getMinute() != null) CreateMinuteReport(actRep, zos, buffer, request);

        zos.close();
        fileOut.deleteOnExit();
        return fileOut;

    }

    private void CreateMainInfoReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer) throws IOException {
        String sAct = "<html><head><style>table {border-collapse: collapse;} table, td, th {border: 1px solid black;}</style></head>" +
                "<body><h2 style=\"text-align:center;\">Informe global de actividades</h2>" +
                "<br><br><table style='text-align:center;width:500px; margin:auto;'>" +
                "<tr><td style=\"width:200px;\">Nombre del informe:</td><td style=\"width:300px;\"><b>" + actRep.getReportName() + "</b></td></tr>" +
                "<tr><td>Descripci&oacute;n del informe:</td><td><b>" + actRep.getReportDescription() + "</b></td></tr>" +
                "<tr><td>Fecha del informe:</td><td><b>" + CalendarExt.calendarToFormatString(actRep.getReportDate(), Constants.FORMAT_CALENDAR_II) + "</b></td></tr>" +
                "<tr><td>Fecha de creaci&oacute;n:</td><td><b>" + CalendarExt.calendarToFormatString(actRep.getCreationDate(), Constants.FORMAT_CALENDAR_I) + "</b></td></tr>" +
                "<tr><td>Usuario: </td><td><b>" + actRep.getCreatorUser().getFullname() + "</b></td></tr>" +
                "</table></body></html>";

        String filename = "Datos del informe.doc";
        zos.putNextEntry(new ZipEntry(filename));
        SendToZipStream(zos, buffer, sAct);
    }

    private void SendToZipStream(ZipOutputStream zos, byte[] buffer, String sAct) throws IOException {
        InputStream in = new ByteArrayInputStream(sAct.getBytes());
        int len;
        while ((len = in.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        in.close();
        zos.closeEntry();
    }

    private void CreateActivityReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, Long userId) throws IOException {
        List<Long> lstIds = StringExt.getListIds(actRep.getActivity());
        if (lstIds == null || lstIds.size() == 0)
            return;
        List<ActivityAgendaNotice> lstAct = activityAgendaRepository.getLstActivitiesByIds(lstIds, userId);
        if (lstAct == null || lstAct.size() == 0)
            return;

        StringBuilder sb = new StringBuilder();

        for (ActivityAgendaNotice aa : lstAct) {
            sb.append(String.format("<tr><td><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td><td >" +
                            "<small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td></tr>",
                    aa.getStart(), aa.getEnd(), aa.getPlace(), aa.getDescription(), aa.getStatus(), aa.getPriority(), aa.getCreationCalTx(), aa.getDoneCalTx(), aa.getIsDoneTx(), aa.getComments()));
        }

        String sAct = String.format("<html><head><style>table {border-collapse: collapse;} table, td, th {border: 1px solid black;}</style></head>" +
                "<body><h2 style=\"text-align:center;\">Actividades del director</h2>" +
                "<br><br><table style='text-align:center; margin:auto;'>" +
                "<tr><td style=\"width:100px;\"><b><small>Inicio</small></b></td><td style=\"width:100px;\"><b><small>Fin</small></b></td><td style=\"width:100px;\"><b><small>Lugar</small></b></td><td style=\"width:100px;\"><b><small>Descripci&oacute;n</small></b></td><td style=\"width:100px;\"><b><small>Estatus</small></b></td><td style=\"width:100px;\"><b><small>Prioridad</small></b></td><td style=\"width:100px;\"><b><small>Fecha creaci&oacute;n</small></b></td><td style=\"width:100px;\"><b><small>Fecha terminaci&oacute;n</small></b></td><td style=\"width:100px;\"><b><small>&iquest;Terminado?</small></b></td><td style=\"width:100px;\"><b><small>Comentarios</small></b></td>" +
                "</tr>%s</table></body></html>", sb.toString());

        String filename = "Actividades del director.doc";
        zos.putNextEntry(new ZipEntry(filename));
        SendToZipStream(zos, buffer, sAct);
    }

    private void CreateSupervisionReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, HttpServletRequest request) throws IOException {
        CreateZipInfoAndFiles(actRep, zos, buffer, request, actRep.getSupervision(), Constants.ROLE_SUPERVISOR_MANAGER,
                "Actividades del coordinador de supervisi&oacute;n", "Actividades del coordinador de supervisores", "supervisor/");
    }


    private void CreateEvaluationReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, HttpServletRequest request) throws IOException {
        CreateZipInfoAndFiles(actRep, zos, buffer, request, actRep.getEvaluation(), Constants.ROLE_EVALUATION_MANAGER,
                "Actividades del coordinador de evaluaci&oacute;n", "Actividades del coordinador de evaluadores", "evaluador/");
    }

    private void CreateManagementReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, HttpServletRequest request) throws IOException {
        CreateZipInfoAndFiles(actRep, zos, buffer, request, actRep.getManagement(), Constants.ROLE_DIRECTOR,
                "Actividades del director", "Actividades del director", "director/");
    }

    private void CreateProjectReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, Long userId) {
        List<Long> lstIds = StringExt.getListIds(actRep.getProject());
        if (lstIds == null || lstIds.size() == 0)
            return;
        List<ActivityReportDto> lstAct = projectActivityRepository.getListOfFiles(lstIds);
        if (lstAct == null || lstAct.size() == 0)
            return;

    }

    private void CreateChannelingReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, HttpServletRequest request) throws IOException {
        CreateZipInfoAndFiles(actRep, zos, buffer, request, actRep.getChanneling(), Constants.ROLE_CHANNELING_MANAGER,
                "Actividades del personal de canalizaciones", "Actividades del personal de canalizaciones", "canalizaciones/");
    }

    private void CreateMinuteReport(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, HttpServletRequest request) throws IOException {
        CreateZipInfoAndFiles(actRep, zos, buffer, request, actRep.getMinute(), Constants.ROLE_HUMAN_RESOURCES,
                "Minutas", "Minutas", "minutas/");
    }

    private void CreateZipInfoAndFiles(WizardActivityReport actRep, ZipOutputStream zos, byte[] buffer, HttpServletRequest request, String ids,
                                       String role, String title, String filenamept, String zipPath) throws IOException {
        List<Long> lstIds = StringExt.getListIds(ids);
        if (lstIds == null || lstIds.size() == 0)
            return;
        List<ActivityReportDto> lstAct = activityReportRepository.getListOfFiles(lstIds, role);
        if (lstAct == null || lstAct.size() == 0)
            return;

        CreateZipInfoAndFilesFromLstAct(zos, buffer, request, title, filenamept, zipPath, lstAct);
    }

    private void CreateZipInfoAndFilesFromLstAct(ZipOutputStream zos, byte[] buffer, HttpServletRequest request, String title,
                                                 String filenamept, String zipPath, List<ActivityReportDto> lstAct) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (ActivityReportDto aa : lstAct) {
            sb.append(String.format("<tr><td><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td><td ><small>%s</small></td>",
                    aa.getCreationDateTx(), aa.getReportName(), aa.getDescription(), aa.getCreatorUser(), aa.getFileName()));
        }

        String sAct = String.format("<html><head><style>table {border-collapse: collapse;} table, td, th {border: 1px solid black;}</style></head>" +
                "<body><h2 style=\"text-align:center;\">" + title + "</h2>" +
                "<br><br><table style='text-align:center; margin:auto;'>" +
                "<tr><td style=\"width:100px;\"><b><small>Fecha de creaci&oacute;n</small></b></td><td style=\"width:100px;\"><b><small>Actividad</small></b></td><td style=\"width:100px;\"><b><small>Descripci&oacute;n</small></b></td><td style=\"width:100px;\"><b><small>Usuario</small></b></td><td style=\"width:100px;\"><b><small>Archivo</small></b></td>" +
                "</tr>%s</table></body></html>", sb.toString());

        String filename = filenamept + " - " + CalendarExt.calendarToFormatString(Calendar.getInstance(), Constants.FORMAT_CALENDAR_III) + ".doc";

        zos.putNextEntry(new ZipEntry(zipPath + filename));
        SendToZipStream(zos, buffer, sAct);

        for (ActivityReportDto aa : lstAct) {

            if (aa.getFileName() == null)
                continue;

            ZipEntry ze = new ZipEntry(zipPath + aa.getFileName());
            zos.putNextEntry(ze);

            String path = request.getSession().getServletContext().getRealPath("");
            File fileIn = new File(path, aa.getPath());
            FileInputStream in = new FileInputStream(new File(fileIn, aa.getRealFileName()));

            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            in.close();

            zos.closeEntry();
        }
    }
}
