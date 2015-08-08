package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.*;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.shared.LogCase;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.ChannelingInfoDropModel;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ConstantsLogCase;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SelectOptsList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.ChannelingDropInfoRepository;
import com.umeca.repository.supervisor.ChannelingRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.LogCaseService;
import com.umeca.service.shared.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("channelingService")
public class ChannelingServiceImpl implements ChannelingService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private ChannelingRepository channelingRepository;


    @Override
    public ChannelingModel getChannelingInfoByCaseId(Long id) {
        return channelingRepository.getChannelingCaseViewByCaseId(id);
    }

    @Override
    public ChannelingModel getChannelingInfoByCaseIdAndChannelingId(Long id, Long channelingId) {
        return channelingRepository.getChannelingViewByCaseId(id, channelingId);
    }

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    PreventionTypeRepository preventionTypeRepository;

    @Autowired
    EconomicSupportRepository economicSupportRepository;

    @Autowired
    InstitutionTypeRepository institutionTypeRepository;

    @Autowired
    ChannelingTypeRepository channelingTypeRepository;

    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    LogCaseService logCaseService;

    @Autowired
    ChannelingDropInfoRepository channelingDropInfoRepository;

    @Override
    public void getChannelingCatalogs(ModelAndView model) {
        Gson gson = new Gson();
        List<SelectList> lstGeneric = districtRepository.findNoObsolete();
        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstDistrict", sLstGeneric);

        lstGeneric = educationLevelRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstEducationLevel", sLstGeneric);

        lstGeneric = preventionTypeRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstPreventionType", sLstGeneric);

        lstGeneric = economicSupportRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstEconomicSupport", sLstGeneric);

        List<SelectOptsList> lstGenericI = institutionTypeRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGenericI);
        model.addObject("lstInstitutionType", sLstGeneric);

        lstGenericI = channelingTypeRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGenericI);
        model.addObject("lstChannelingType", sLstGeneric);

        return;
    }

    @Override
    public void doUpsert(final ChannelingModel modelNew, User user, ResponseMessage response) {

        //Validar el caso
        if (isValidCase(modelNew.getCaseId(), response) == false)
            return;

        Long channelingId = modelNew.getChannelingId();
        final Channeling model;
        if (channelingId == null || channelingId <= 0) {
            //Insertar
            model = new Channeling();

            model.setCreatorUser(user);
            model.setCaseDetention(new Case() {{
                setId(modelNew.getCaseId());
            }});
            model.setIsObsolete(false);
            Boolean isVolunteer = modelNew.getIsVolunteer();
            model.setIsVolunteer(isVolunteer == null ? false : isVolunteer);

            model.setCreationDate(Calendar.getInstance());
            fillByChannelingType(model, modelNew, response);
            model.setConsecutive(calculateNextConsecutiveByCaseId(modelNew.getCaseId()));
        } else { //Modificar
            model = channelingRepository.findOne(modelNew.getChannelingId());
            if (model == null || model.getIsObsolete() == true) {
                response.setHasError(true);
                response.setMessage("El registro que desea modificar no se encuentra o ya no existe");
                return;
            }

            model.setUpdaterUser(user);
            model.setLastUpdateDate(Calendar.getInstance());
            fillByChannelingType(model, modelNew, response);

        }

        channelingRepository.save(model);
        channelingRepository.flush();
        ChannelingNotification notificationInfo = channelingRepository.getNotificationInfoWithUser(model.getId());

        CatChannelingType cat = channelingTypeRepository.findOne(modelNew.getChannelingTypeId());
        String msg = "Se ha registrado una actividad de canalizaci&oacute;n de tipo " + cat.getName();
        List<LogCase> logs = logCaseService.addLog(ConstantsLogCase.CODE_CHANNELING, modelNew.getCaseId(), msg);

        messageService.sendNotificationToRole(modelNew.getCaseId(),
                String.format("<strong>Descripción:</strong> Se registró una canalización de tipo <strong>\"%s\"</strong><br/>" +
                                "Para el imputado: <strong>%s</strong>. Causa penal <strong>%s</strong><br/>Registrado por el supervisor: <strong>%s</strong>",
                        notificationInfo.getChannelingType(), notificationInfo.getImputed(), notificationInfo.getIdMp(),
                        notificationInfo.getUser()),
                new ArrayList<String>() {{
                    add(Constants.ROLE_DIRECTOR);
                    add(Constants.ROLE_CHANNELING_MANAGER);
                }},
                Constants.CHANNELING_NOTIFICATION_TITLE);

        response.setHasError(false);
    }

    @Override
    public void doObsolete(Long caseId, Long channelingId, User user, ResponseMessage response) {
        //Validar el caso
        if (isValidCase(caseId, response) == false)
            return;

        ///TODO Validar si no existe una actividad en el plan de monitoreo que tenga una canalización asignada
        Long count = activityMonitoringPlanRepository.countInChanneling(channelingId);

        if (count > 0) {
            response.setHasError(true);
            response.setMessage("No es posible eliminar este registro, ya que se encuentra en uso");
            response.setTitle("Eliminar canalización");
            return;
        }

        Channeling model = channelingRepository.findOne(channelingId);

        model.setIsObsolete(true);
        model.setDeleteUser(user);
        model.setDeleteDate(Calendar.getInstance());
        channelingRepository.save(model);

        response.setHasError(false);
    }

    @Override
    public ChannelingModelSheet getChannelingSheetById(Long id) {
        return channelingRepository.getChannelingSheetById(id);
    }

    private boolean canSetInstitutionType(Channeling model, final ChannelingModel modelNew, ResponseMessage response) {

        CatInstitutionType institutionType = institutionTypeRepository.findOne(modelNew.getInstitutionTypeId());
        model.setInstitutionType(institutionType);

        if (institutionType.getHasSpec()) {
            String specOther = modelNew.getSpecOther();

            if (StringExt.isNullOrWhiteSpace(specOther)) {
                response.setHasError(true);
                response.setMessage("No se ha definido el campo de especificaciones");
                return false;
            }

            model.setSpecOther(specOther);
        } else {
            model.setSpecOther(null);
        }
        return true;
    }

    private Long calculateNextConsecutiveByCaseId(Long caseId) {
        List<Long> lastConsecutive = channelingRepository.getLastConsecutiveByCaseId(caseId, new PageRequest(0, 1));
        if (lastConsecutive == null || lastConsecutive.size() <= 0)
            return 1l;
        return lastConsecutive.get(0) + 1;
    }


    private void fillByChannelingType(final Channeling model, final ChannelingModel modelNew, ResponseMessage response) {

        if (canSetInstitutionType(model, modelNew, response) == false)
            return;

        model.setChannelingType(new CatChannelingType() {{
            setId(modelNew.getChannelingTypeId());
        }});
        model.setDistrict(new District() {{
            setId(modelNew.getDistrictId());
        }});

        model.setName(modelNew.getName());

        String code = channelingTypeRepository.getCodeById(modelNew.getChannelingTypeId());
        model.setInstitutionName(modelNew.getInstitutionName());

        model.setEconomicSupport(null);
        model.setPreventionType(null);
        model.setEducationLevel(null);

        switch (code) {
            case Constants.CHANNELING_TYPE_ECONOMIC_SUPPORT:
                if (isCatalogValid(modelNew.getEconomicSupportId(), response) == false)
                    return;
                model.setEconomicSupport(new CatEconomicSupport() {{
                    setId(modelNew.getEconomicSupportId());
                }});
                break;

            case Constants.CHANNELING_TYPE_TOXICOLOGICAL_TEST:
            case Constants.CHANNELING_TYPE_MEDICAL_TREATMENT:
            case Constants.CHANNELING_TYPE_JOB:
                break;

            case Constants.CHANNELING_TYPE_PREVENTION_TYPE:
                if (isCatalogValid(modelNew.getPreventionTypeId(), response) == false)
                    return;
                model.setPreventionType(new CatPreventionType() {{
                    setId(modelNew.getPreventionTypeId());
                }});
                break;

            case Constants.CHANNELING_TYPE_EDUCATION:
                if (isCatalogValid(modelNew.getEducationLevelId(), response) == false)
                    return;
                model.setEducationLevel(new CatEducationLevel() {{
                    setId(modelNew.getEducationLevelId());
                }});
                break;
        }
    }

    private boolean isCatalogValid(Long catalogId, ResponseMessage response) {

        if (catalogId == null || catalogId <= 0) {
            response.setHasError(true);
            response.setMessage("Faltan campos por seleccionar");
            return false;
        }

        return true;
    }

    private boolean isValidCase(Long caseId, ResponseMessage response) {
        Long count = caseRepository.isReadyForChanneling(caseId);
        if (count > 0)
            return true;

        response.setHasError(true);
        response.setMessage("No es posible agregar canalizaciones al caso debido a que no tiene entrevista de encuadre o el caso se encuentra cerrado");
        return false;
    }

    @Transactional
    public void addLogChannelingDoc(Long caseId, String channelingType) {
        String msg = "Se ha generado el oficio para la actividad de canalizaci&oacute;n de tipo " + channelingType+".";
        List<LogCase> logs = logCaseService.addLog(ConstantsLogCase.CODE_SHEET_CHANNELING, caseId, msg);
    }

    @Override
    @Transactional
    public void requestDrop(ChannelingDropModel model, User user, ResponseMessage response, SharedUserService userService) {
        //Validar el caso
        if(model == null){
            response.setHasError(true);
            response.setMessage("No existen datos correctos para procesar la solicitud");
        }

        if (isValidCase(model.getCaseId(), response) == false)
            return;

        Channeling channeling = channelingRepository.findOne(model.getChannelingId());

        if (channeling == null) {
            response.setHasError(true);
            response.setMessage("No existe la canalización que desea dar de baja");
            return;
        }

        if(channeling.isAuthorizeToDrop() != null) {
            response.setHasError(true);
            response.setMessage("La canalización ya está en proceso de darse da baja o ya está dada de baja");
            return;
        }

        ChannelingDropInfo channelingDropInfo = new ChannelingDropInfo();
        Calendar now = Calendar.getInstance();

        channelingDropInfo.setChanneling(channeling);
        channelingDropInfo.setCreatorComments(model.getComments());
        channelingDropInfo.setCreationDate(now);
        channelingDropInfo.setCreatorUser(user);
        CatChannelingDropType catChannelingDropType = new CatChannelingDropType();
        catChannelingDropType.setId(model.getChannelingDropTypeId());
        channelingDropInfo.setChannelingDropType(catChannelingDropType);
        channelingDropInfoRepository.save(channelingDropInfo);

        channeling.setAuthorizeToDrop(false);
        channelingRepository.save(channeling);
        channelingRepository.flush();

        response.setHasError(false);

        Case caseDetention = channeling.getCaseDetention();
        Imputed imputed = caseDetention.getMeeting().getImputed();
        String imputedFullName = imputed.getName() + " " + imputed.getLastNameP() + " " + imputed.getLastNameM();
        String scheduleRequest = CalendarExt.calendarToFormatString(now, Constants.FORMAT_CALENDAR_I);

        messageService.sendNotificationToRole(model.getCaseId(),
                String.format("<strong>Descripción:</strong> Canalización de tipo <strong>\"%s\"</strong><br/>" +
                                "Para el imputado: <strong>%s</strong>. Causa penal <strong>%s</strong><br/>" +
                                "Solicitado por el supervisor: <strong>%s</strong><br/><br/>" +
                                "Fecha de solicitud: <b>%s</b>",
                        channeling.getChannelingType().getName(), imputedFullName, caseDetention.getIdMP(),
                        userService.getFullNameById(user.getId()), scheduleRequest),
                new ArrayList<String>(){{add(Constants.ROLE_SUPERVISOR_MANAGER);}}, Constants.CHANNELING_DROP_CHANNELING_TITLE);

    }

    @Override
    public ChannelingInfoDropModel getAuthRejChannelingDropInfoById(Long id) {
        return channelingDropInfoRepository.getChannelingDropInfoById(id);
    }

    @Override
    @Transactional
    public void doAuthRejChannelingDrop(ChannelingInfoDropModel model, User user, SharedUserService userService, ResponseMessage response) {
        final ChannelingDropInfo channelingDropInfo = channelingDropInfoRepository.findOne(model.getId());

        if(model.getAuthRejValue() == null){
            response.setHasError(true);
            response.setMessage("Debe autorizar o rechazar la solicitud");
            return;
        }

        if(channelingDropInfo == null){
            response.setHasError(true);
            response.setMessage("La solicitud no existe, por favor reinicie y vuelva a intentar");
            return;
        }

        if(channelingDropInfo.getIsAuthorized() != null){
            response.setHasError(true);
            response.setMessage("La solicitud ya fue atendida");
            return;
        }

        Channeling channeling = channelingDropInfo.getChanneling();
        Boolean authRejVal = model.getAuthRejValue();
        Calendar now = Calendar.getInstance();

        channelingDropInfo.setAuthorizerComments(model.getCommentsAuthorizer());
        channelingDropInfo.setAuthorizerRejecterUser(user);
        channelingDropInfo.setAuthRejDate(now);

        if(authRejVal == false){
            channeling.setAuthorizeToDrop(null);
            channelingDropInfo.setIsAuthorized(false);
        }
        else{
            channeling.setAuthorizeToDrop(true);
            channelingDropInfo.setIsAuthorized(true);
        }

        channelingRepository.save(channeling);
        channelingDropInfoRepository.save(channelingDropInfo);
        channelingRepository.flush();

        Case caseDetention = channeling.getCaseDetention();
        Imputed imputed = caseDetention.getMeeting().getImputed();
        String imputedFullName = imputed.getName() + " " + imputed.getLastNameP() + " " + imputed.getLastNameM();
        String scheduleRequest = CalendarExt.calendarToFormatString(now, Constants.FORMAT_CALENDAR_I);



        if(authRejVal == false) {
            messageService.sendNotification(caseDetention.getId(),
                    String.format("<strong>La solicitud de baja fue rechazada</strong><br/>" +
                                    "<strong>Descripción:</strong> Canalización de tipo <strong>\"%s\"</strong><br/>" +
                                    "Para el imputado: <strong>%s</strong>. Causa penal <strong>%s</strong><br/>" +
                                    "Solicitado por el supervisor: <strong>%s</strong><br/>" +
                                    "Comentarios: %s<br/><br/>" +
                                    "Fecha del rechazo: <b>%s</b>",
                            channeling.getChannelingType().getName(), imputedFullName, caseDetention.getIdMP(),
                            userService.getFullNameById(user.getId()), model.getCommentsAuthorizer(), scheduleRequest), user,
                    Constants.CHANNELING_DROP_REJ_TITLE, "", new ArrayList<Long>() {{
                        add(channelingDropInfo.getCreatorUser().getId());
                    }});
        }
        else{
            messageService.sendNotification(caseDetention.getId(),
                    String.format("<strong>La solicitud de baja fue autorizada</strong><br/>" +
                                    "<strong>Descripción:</strong> Canalización de tipo <strong>\"%s\"</strong><br/>" +
                                    "Para el imputado: <strong>%s</strong>. Causa penal <strong>%s</strong><br/>" +
                                    "Solicitado por el supervisor: <strong>%s</strong><br/>" +
                                    "Comentarios: %s<br/><br/>" +
                                    "Fecha de la autorización: <b>%s</b>",
                            channeling.getChannelingType().getName(), imputedFullName, caseDetention.getIdMP(),
                            userService.getFullNameById(user.getId()), model.getCommentsAuthorizer(), scheduleRequest), user,
                    Constants.CHANNELING_DROP_AUTH_TITLE, "", new ArrayList<Long>() {{
                        add(channelingDropInfo.getCreatorUser().getId());
                    }});
        }

        response.setHasError(false);
    }

    @Override
    public List<ActivityChannelingModel> getLstActivitiesChanneling(Long id) {
        return channelingRepository.getLstActivitiesChanneling(id);
    }
}
