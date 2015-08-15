package com.umeca.service.managereval;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.managereval.Formulation;
import com.umeca.model.shared.ConsMessage;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.managereval.FormulationRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MessageService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;


@Service("formulationService")
public class FormulationServiceImpl implements FormulationService {
    @Autowired
    SharedUserService userService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    FormulationRepository formulationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageService messageService;


    @Override
    public ModelAndView upsert(Long id) {

        Gson gson = new Gson();
        List<SelectList> lstReviewers = userRepository.getLstValidUsersByRole(Constants.ROLE_REVIEWER);

        ModelAndView model = new ModelAndView("/managereval/formulation/upsert");

        model.addObject("lstReviewers", gson.toJson(lstReviewers));
        if (id != null && id > 0) {
            Formulation formulation = formulationRepository.findOne(id);

            model.addObject("m", formulation);
            Long reviewerId = formulation.getReviewer().getId();
            model.addObject("reviewerId", reviewerId);
        }
        return model;
    }

    @Override
    public ResponseMessage doUpsert(Formulation formulation) {
        ResponseMessage result = new ResponseMessage();
        try {

            formulation.setManagereval(userRepository.findOne(userService.GetLoggedUserId()));
            formulation.setReviewer(userRepository.findOne(formulation.getReviewerId()));
            formulation.setIsObsolete(false);


            if (formulation.getId() != null && formulation.getId() == 0) {
                formulation.setId(null);
            }
            formulationRepository.save(formulation);


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            messageService.sendNotificationToUser(null,
                    String.format("<strong>Registrada por coordinador de evaluación: %s </strong><br/>" +
                                    "Fecha de registro: <b>%s</b><br/>" +
                                    "Oficio: <b>%s</b><br/>" +
                                    "Cedula de notificación: <b>%s</b> <br/>" +
                                    "Imputado: <b>%s</b> <br/>" +
                                    "Fecha de la cita de entrevista: <b>%s</b> <br/>" +
                                    "Fecha de audiencia: <b>%s</b> <br/>",
                            userService.getFullNameById(userService.GetLoggedUserId()),
                            formulation.getRegistrationFormulationDate() == null ? "" : sdf.format(formulation.getRegistrationFormulationDate()),
                            formulation.getDocument(),
                            formulation.getCertificateNotification(),
                            formulation.getLastNameP() + " " + formulation.getLastNameM() + " " + formulation.getFirstName(),
                            formulation.getUmecaInterviewDate() == null ? "" : sdf.format(formulation.getUmecaInterviewDate()),
                            formulation.getHearingDate() == null ? "" : sdf.format(formulation.getHearingDate()))
                    , userRepository.findOne(userService.GetLoggedUserId()),
                    userRepository.findOne(formulation.getReviewer().getId()), "Notificación REGISTRO DE CITA de FORMULACIÓN", "");
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsert", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
        }
        return result;
    }

    @Override
    public ResponseMessage confirmInformation(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            Formulation formulation;

            formulation = formulationRepository.findOne(id);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                messageService.sendNotificationToUser(null,
                        String.format("<strong>Registrada por evaluador: %s </strong><br/>" +
                                        "Fecha de registro: <b>%s</b><br/>" +
                                        "Oficio: <b>%s</b><br/>" +
                                        "Cedula de notificación: <b>%s</b> <br/>" +
                                        "Imputado: <b>%s</b> <br/>" +
                                        "Fecha de la cita de entrevista: <b>%s</b> <br/>" +
                                        "Fecha de audiencia: <b>%s</b> <br/>",
                                userService.getFullNameById(userService.GetLoggedUserId()),
                                formulation.getRegistrationFormulationDate() == null ? "" : sdf.format(formulation.getRegistrationFormulationDate()),
                                formulation.getDocument(),
                                formulation.getCertificateNotification(),
                                formulation.getLastNameP() + " " + formulation.getLastNameM() + " " + formulation.getFirstName(),
                                formulation.getUmecaInterviewDate() == null ? "" : sdf.format(formulation.getUmecaInterviewDate()),
                                formulation.getHearingDate() == null ? "" : sdf.format(formulation.getHearingDate()))
                        , userRepository.findOne(userService.GetLoggedUserId()),
                        userRepository.findOne(formulation.getManagereval().getId()), "Notificación REGISTRO ENTREGA INFORMACI&Oacute;N FORMULACI&Oacute;N", "");
            result.setHasError(false);
            result.setMessage("Se ha envíado la notificación de información");

        } catch (Exception e) {
            logException.Write(e, this.getClass(), "confirmInformation  ", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
        }
        return result;
    }

    @Override
    public ModelAndView absenceReport(Long id) {
        ModelAndView model = new ModelAndView("/managereval/formulation/absenceReport");
        return model;
    }

    @Override
    public ModelAndView printAbsenceReport(Long id, HttpServletResponse response) {
        ModelAndView model = null;
        try {
            Formulation formulation = formulationRepository.findOne(id);
            if (formulation == null) {
                model = new ModelAndView("/reviewer/declined/notSheet");
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename=\"sin-informe-negacion.doc\"");
                return model;
            }
            formulation.setPresence(false);
            formulationRepository.save(formulation);

            model = new ModelAndView("/managereval/formulation/absenceReport");
            model.addObject("data", formulation);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"informe-negación-" + formulation.getFirstName() + formulation.getLastNameP() + formulation.getLastNameM() + ".doc\"");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            messageService.sendNotificationToUser(null,
                    String.format("<strong>Registrada por evaluador: %s </strong><br/>" +
                                    "Fecha de registro: <b>%s</b><br/>" +
                                    "Oficio: <b>%s</b><br/>" +
                                    "Cedula de notificación: <b>%s</b> <br/>" +
                                    "Imputado: <b>%s</b> <br/>" +
                                    "Fecha de la cita de entrevista: <b>%s</b> <br/>" +
                                    "Fecha de audiencia: <b>%s</b> <br/>",
                            userService.getFullNameById(userService.GetLoggedUserId()),
                            formulation.getRegistrationFormulationDate() == null ? "" : sdf.format(formulation.getRegistrationFormulationDate()),
                            formulation.getDocument(),
                            formulation.getCertificateNotification(),
                            formulation.getLastNameP() + " " + formulation.getLastNameM() + " " + formulation.getFirstName(),
                            formulation.getUmecaInterviewDate() == null ? "" : sdf.format(formulation.getUmecaInterviewDate()),
                            formulation.getHearingDate() == null ? "" : sdf.format(formulation.getHearingDate()))
                    , userRepository.findOne(userService.GetLoggedUserId()),
                    userRepository.findOne(formulation.getManagereval().getId()), "Notificación REGISTRO INASISTENCIA DE CITA DE FORMULACI&Oacute;N", "");
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "printAbsenceReport", userService);
            model = null;
        }
        return model;

    }
}
