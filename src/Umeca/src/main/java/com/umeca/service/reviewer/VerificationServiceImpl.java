package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.*;
import com.umeca.model.catalog.dto.*;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.ChoiceView;
import com.umeca.model.entities.reviewer.View.SearchToChoiceIds;
import com.umeca.model.entities.reviewer.dto.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.AddressRepository;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("verificationService")
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    StatusCaseRepository statusCaseRepository;
    @Autowired
    StatusVerificationRepository statusVerificationRepository;
    @Autowired
    SharedUserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SourceVerificationRepository sourceVerificationRepository;

    @Autowired
    SharedLogExceptionService logException;

    @Override
    public void createVerification(Case c) {
        c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_SOURCE_VALIDATION));
        Verification verification = new Verification();
        verification.setCaseDetention(c);
        verification.setStatus(statusVerificationRepository.findByCode(Constants.VERIFICATION_STATUS_NEW_SOURCE));
        verification.setReviewer(userRepository.findOne(userService.GetLoggedUserId()));
        verification.setDateCreate(new Date());
        c.setVerification(verification);
        c.getVerification().setSourceVerifications(convertAllInitSourcesVerif(c));
    }

    @Override
    public List<SourceVerification> convertAllInitSourcesVerif(Case c) {
        List<SourceVerification> svlist = new ArrayList<>();
        List<Reference> rlist = (c.getMeeting().getReferences() == null) ? new ArrayList<Reference>() : c.getMeeting().getReferences();
        for (Reference reference : rlist) {
            SourceVerification sv = new SourceVerification();
            sv.setFullName(reference.getFullName());
            sv.setPhone(reference.getPhone());
            sv.setAge(reference.getAge());
            sv.setAddress(reference.getAddress());
            sv.setRelationship(reference.getRelationship());
            sv.setSpecification(reference.getSpecificationRelationship());
            sv.setVisible(Boolean.TRUE);
            sv.setVerification(c.getVerification());
            sv.setAuthorized(Boolean.FALSE);
            svlist.add(sv);
        }
        List<PersonSocialNetwork> psnList = (c.getMeeting().getSocialNetwork() != null && c.getMeeting().getSocialNetwork().getPeopleSocialNetwork() != null)
                ? c.getMeeting().getSocialNetwork().getPeopleSocialNetwork() : new ArrayList<PersonSocialNetwork>();
        String addressImputed = "No se proporcionó dirección del imputado";
        String otherAddresSPN = "No proporcionado";
        if (c.getMeeting().getImputedHomes() != null) {
            for (ImputedHome d : c.getMeeting().getImputedHomes()) {
                if (d.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_CURRENT)) {
                    addressImputed = d.getAddress().getAddressString();
                    break;
                }
            }
        }
        for (PersonSocialNetwork psn : psnList) {
            SourceVerification sv = new SourceVerification();
            sv.setFullName(psn.getName());
            sv.setPhone(psn.getPhone());
            sv.setAge(psn.getAge());
            if (psn.getLivingWith().getId().equals(Constants.ELECTION_YES)) {
                sv.setAddress(addressImputed);
            } else {
                sv.setAddress(psn.getAddress());
            }
            sv.setRelationship(psn.getRelationship());
            sv.setSpecification(psn.getSpecificationRelationship());
            sv.setVisible(Boolean.TRUE);
            sv.setAuthorized(Boolean.FALSE);
            sv.setVerification(c.getVerification());
            svlist.add(sv);
        }
        SourceVerification svImputed = new SourceVerification();
        Imputed i = c.getMeeting().getImputed();
        String fullNameI = i.getName() + " " + i.getLastNameP() + " " + i.getLastNameM();
        svImputed.setFullName(fullNameI);
        String phone = "No proporcionado";
        if (i.getCelPhone() != null && !i.getCelPhone().equals("")) {
            phone = i.getCelPhone();
        }
        svImputed.setPhone(phone);
        svImputed.setAge(userService.calculateAge(i.getBirthDate()));
        svImputed.setAddress(addressImputed);
        svImputed.setVisible(Boolean.FALSE);
        svImputed.setAuthorized(Boolean.TRUE);
        svImputed.setVerification(c.getVerification());
        svlist.add(svImputed);
        return svlist;
    }

    @Autowired
    MeetingService meetingService;
    @Autowired
    DrugTypeRepository drugTypeRepository;
    @Autowired
    PeriodicityRepository periodicityRepository;
    @Autowired
    RegisterTypeRepository registerTypeRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    ElectionRepository electionRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    AcademicLevelRepository academicLevelRepository;
    @Autowired
    CountryRepository countryRepository;

    private void userConfigToView(ModelAndView model) {

        Long userId = userService.GetLoggedUserId();
        User u = userRepository.findOne(userId);
        Boolean band = false;
        if (u.getRoles().get(0).getRole().equals(Constants.ROLE_EVALUATION_MANAGER))
            band = true;
        model.addObject("managereval", band);
    }

    @Override
    public ModelAndView showVerificationBySource(Long idCase, Long idSource) {

        ModelAndView model = new ModelAndView("/reviewer/verification/verificationBySource");
        setImputedData(idCase, model);
        Gson gson = new Gson();
        fillMeeting(model, idCase);
        if (idSource != null) {
            SourceVerification sv = sourceVerificationRepository.findOne(idSource);
            model.addObject("idSource", idSource);
            model.addObject("source", gson.toJson(new SourceVerificationDto().dtoSourceVerification(sv)));
            userConfigToView(model);
        } else {
            model.addObject("idSource", 0);
            model.addObject("source", gson.toJson(new SourceVerificationDto().dtoSourceVerification(new SourceVerification())));
            model.addObject("managereval", true);
        }


        return model;
    }

    @Override
    @Transactional
    public ResponseMessage verifChoicesBySection(Long idCase, Integer idSection, Long idList, Long idSource, String comment) {
        try {
            Long idImputed = sourceVerificationRepository.findIdSourceImputed(idCase);
            List<FieldMeetingSource> fmsList;
            List<String> sListImputed = new ArrayList<>();
            String status = (idSource.equals(-1L)) ? Constants.ST_FIELD_VERIF_UNABLE : Constants.ST_FIELD_VERIF_IMPUTED;
            if (idList != null)
                fmsList = fieldMeetingSourceRepository.getGroupFieldMeetingByIdCaseWhitIdList(idCase, idSection, idList);
            else
                fmsList = fieldMeetingSourceRepository.getGroupFieldMeetingByIdCase(idCase, idSection);
            for (FieldMeetingSource fms : fmsList) {
                if (fms.getStatusFieldVerification().getName().equals(Constants.ST_FIELD_VERIF_IMPUTED) && status.equals(Constants.ST_FIELD_VERIF_UNABLE)) {
                    sListImputed.add(fms.getFieldVerification().getCode());
                }
                Boolean result = fms.getStatusFieldVerification().getName().equals(status) && fms.getSourceVerification().getId().equals(idImputed);
                fms.setFinal(result);
                if (result) {
                    fms.setReason(comment);
                }
            }
            if (status.equals(Constants.ST_FIELD_VERIF_UNABLE)) {
                for (FieldMeetingSource fms : fmsList) {
                    if (fms.getStatusFieldVerification().getName().equals(Constants.ST_FIELD_VERIF_UNABLE)) {
                        sListImputed.remove(fms.getFieldVerification().getCode());
                        if (sListImputed.size() == 0) {
                            break;
                        }
                    }
                }
                if (sListImputed.size() > 0) {
                    List<FieldMeetingSource> addNew = new ArrayList<>();
                    for (FieldMeetingSource fms : fmsList) {
                        if (sListImputed.contains(fms.getFieldVerification().getCode())) {
                            FieldMeetingSource fmsAux = new FieldMeetingSource();
                            fmsAux.setFieldVerification(fms.getFieldVerification());
                            fmsAux.setFinal(true);
                            fmsAux.setValue(fms.getValue());
                            fmsAux.setJsonValue(fms.getJsonValue());
                            fmsAux.setSourceVerification(fms.getSourceVerification());
                            fmsAux.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_UNABLE));
                            fmsAux.setIdFieldList(fms.getIdFieldList());
                            fmsAux.setReason(comment);
                            addNew.add(fmsAux);
                        }

                    }
                    fmsList.addAll(addNew);
                }
            }
            fieldMeetingSourceRepository.save(fmsList);
            return new ResponseMessage(false, "Se  ah guardado la i nformacion con exito");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "showChoicesBySection", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la in formacion");
        }
    }

    @Override
    public ResponseMessage searchInformationByeSourceCode(Long idCase, Long idSource, String code, Long idList) {
        ResponseMessage response = new ResponseMessage(true, "La fuente no  ha proporcionado informaci&oacute;n para &eacute;ste campo");
        try {
            List<FieldMeetingSource> result = new ArrayList<>();
            List<Long> listFieldSection = fieldVerificationRepository.getListSubsectionByCode(code);
            String aux = "";
            for (Long idFv : listFieldSection) {
                FieldVerification fv = fieldVerificationRepository.findOne(idFv);
                if (fv != null) {
                    FieldMeetingSource fmsNew = new FieldMeetingSource();
                    Long fieldMeetingSourceId;
                    if (idList == null) {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSource, fv.getCode());
                    } else {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSource, fv.getCode(), idList);
                    }

                    if (fieldMeetingSourceId != null) {
                        FieldMeetingSource fmsAux = fieldMeetingSourceRepository.findOne(fieldMeetingSourceId);
                        if (!fmsAux.getValue().trim().equals("")) {
                            switch (fmsAux.getStatusFieldVerification().getName()) {
                                case Constants.ST_FIELD_VERIF_EQUALS:
                                    aux += "<i class=\"icon-ok green  icon-only bigger-120\"></i>&nbsp;&nbsp;" + fmsAux.getFieldVerification().getFieldName() + ": " + fmsAux.getValue() + "<br/>";
                                    break;
                                case Constants.ST_FIELD_VERIF_NOEQUALS:
                                    aux += "<i class=\"icon-remove red  icon-only bigger-120\"></i>&nbsp;&nbsp;" + fmsAux.getFieldVerification().getFieldName() + ": " + fmsAux.getValue() + "<br/>";
                                    break;
                                case Constants.ST_FIELD_VERIF_DONTKNOW:
                                    aux += "<i class=\"icon-ban-circle grey  icon-only bigger-120\"></i>&nbsp;&nbsp;" + fmsAux.getFieldVerification().getFieldName() + ": " + fmsAux.getValue() + "<br/>";
                                    break;
                            }
                        }

                    }
                }
            }
            if (!aux.equals("")) {
                response.setHasError(false);
                response.setMessage(aux);
            }
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "searchInformationByeSourceCode", userService);
        } finally {
            return response;
        }
    }

    @Override
    public ModelAndView showChoices(Long idCase, String code, Long idList) {
        ModelAndView model = new ModelAndView("/reviewer/verification/showChoices");
        model.addObject("idCase", idCase);
        model.addObject("idList", idList);
        model.addObject("code", code);
        List<Long> idAllSources = sourceVerificationRepository.getAllSourcesByCase(idCase);
        List<SearchToChoiceIds> idSources;

        if (idList == null) {
            idSources = fieldMeetingSourceRepository.getIdSourceByCode(idCase, code);

        } else {
            idSources = fieldMeetingSourceRepository.getIdSourceByCodeWhithIdList(idCase, code, idList);
        }

        List<ChoiceView> list = new ArrayList<>();
        List<Long> listAdded = new ArrayList<>();
        Boolean addUnable = true;
        for (SearchToChoiceIds e : idSources) {
            if (idAllSources.contains(e.getIdSource())) {
                idAllSources.remove(idAllSources.indexOf(e.getIdSource()));
            }
            if (!listAdded.contains(e.getIdSource())) {
                List<FieldMeetingSource> result = new ArrayList<>();
                if (idList == null) {
                    result = fieldMeetingSourceRepository.getGroupFieldMeeting(e.getIdSource(), e.getIdSubsection(), Constants.ST_FIELD_VERIF_UNABLE);
                } else {
                    result = fieldMeetingSourceRepository.getGroupFieldMeetingWithIdList(e.getIdSource(), e.getIdSubsection(), idList, Constants.ST_FIELD_VERIF_UNABLE);
                }
                if (result != null) {
                    //for(FieldMeetingSource fAux: result){
                    //  if(!fAux.getStatusFieldVerification().getName().equals(Constants.ST_FIELD_VERIF_UNABLE)){
                    //    List<FieldMeetingSource> a = new ArrayList<>();
                    //  a.add(fAux);
                    list.add(new ChoiceView().choiceDto(result));
                    // }
                    //}
                }
                listAdded.add(e.getIdSource());
            }
        }
        List<FieldMeetingSource> resultAux;
        Integer idSubsection = fieldVerificationRepository.getIdSubsectionByCode(code);
        if (idList == null) {
            resultAux = fieldMeetingSourceRepository.getExistUnableFieldMeeting(idSubsection, idCase, Constants.ST_FIELD_VERIF_UNABLE);
        } else {
            resultAux = fieldMeetingSourceRepository.getExistUnableFieldMeetingWithIdList(idSubsection, idList, idCase, Constants.ST_FIELD_VERIF_UNABLE);
        }
        if (resultAux != null && resultAux.size() > 0) {
            addUnable = false;
            list.add(new ChoiceView().choiceDto(resultAux));
        }
        for (Long id : idAllSources) {
            SourceVerification sv = sourceVerificationRepository.findOne(id);
            ChoiceView choiceView = new ChoiceView();
            choiceView.setNameSource(sv.getFullName());
            choiceView.setStatus("NOT_FOUND");
            List listString = new ArrayList<String>();
            listString.add("Esta fuente no proporcionó información para este campo o sección");
            choiceView.setValues(listString);
            list.add(choiceView);
        }
        if (addUnable) {
            FieldMeetingSource unableVerification = new FieldMeetingSource();
            SourceVerification aux = new SourceVerification();
            aux.setFullName(Constants.UNABLE_VERIF_TEXT);
            unableVerification.setSourceVerification(aux);
            unableVerification.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_UNABLE));
            Case c = caseRepository.findOne(idCase);
            List<Long> idFields = fieldVerificationRepository.getListSubsectionByCode(code);
            List<FieldMeetingSource> fmsAuxSecond = new ArrayList<>();
            for (Long id : idFields) {
                FieldVerification fv = fieldVerificationRepository.findOne(id);
                unableVerification.setFieldVerification(fv);
                fmsAuxSecond.addAll(valuesOfMeetingService.getValueByCode(fv.getCode(), c.getMeeting(), unableVerification, idList));
            }
            for (FieldMeetingSource f : fmsAuxSecond) {
                f.setId(Long.valueOf(-1));
            }
            if (fmsAuxSecond.size() > 0) {
                list.add(new ChoiceView().choiceDto(fmsAuxSecond));
            }
        }

        Gson gson = new Gson();
        model.addObject("listChoice", gson.toJson(list));
        userConfigToView(model);
        return model;
    }

    @Override
    public ResponseMessage saveSchedule(Long idCase, Long idSource, Long idList, String schedule, String code) {
        try {
            Gson gson = new Gson();
            Long idF;
            if (idList != null) {
                idF = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSource, code, idList);
            } else {
                idF = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSource, code);
            }
            FieldMeetingSource fms = new FieldMeetingSource();
            fms.setId(idF);
            fms.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_NOEQUALS));
            fms.setSourceVerification(sourceVerificationRepository.findOne(idSource));
            fms.setFieldVerification(fieldVerificationRepository.findByCode(code));
            fms.setJsonValue(schedule);
            List<Schedule> listSchedules = gson.fromJson(schedule, new TypeToken<List<Schedule>>() {
            }.getType());
            String val = "";
            for (Schedule sch : listSchedules) {
                val += "Día(s): " + sch.getDay() + " Inicio: " + sch.getStart() + "Fin: " + sch.getEnd() + ";";
            }
            fms.setValue(val);
            fms.setFinal(false);
            fms.setIdFieldList(idList);
            fieldMeetingSourceRepository.save(fms);
            return new ResponseMessage(false, "Se ha guardado exitosamente el registro");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveSchedule", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la lista");
        }
    }

    @Autowired
    LocationRepository locationRepository;

    @Override
    public ResponseMessage saveAddressVerification(Long idCase, Long idSource, Long idList, String code, Address address) {
        try {
            Gson gson = new Gson();
            Long idF;
            if (idList != null) {
                idF = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSource, code, idList);
            } else {
                idF = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSource, code);
            }
            FieldMeetingSource fms = new FieldMeetingSource();
            fms.setId(idF);
            fms.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_NOEQUALS));
            fms.setSourceVerification(sourceVerificationRepository.findOne(idSource));
            fms.setFieldVerification(fieldVerificationRepository.findByCode(code));
            address.setLocation(locationRepository.findOne(address.getLocation().getId()));
            fms.setValue(address.toString());
            fms.setJsonValue(gson.toJson(new AddressDto().addressDto(address)));
            fms.setFinal(false);
            fms.setIdFieldList(idList);
            fieldMeetingSourceRepository.save(fms);
            return new ResponseMessage(false, "Se ha guardado exitosamente el registro");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveAddressVerification", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la lista");
        }
    }

    @Transactional
    @Override
    public ResponseMessage saveSelectChoice(Long idCase, Long idFieldMeeting, String code, Long idList, String reason) {
        try {
            Integer idSubsection = fieldVerificationRepository.getIdSubsectionByCode(code);
            List<FieldMeetingSource> fieldMeetingSourceList;
            if (idList == null) {
                fieldMeetingSourceList = fieldMeetingSourceRepository.findListFinalByIdSubsection(idCase, idSubsection);
            } else {
                fieldMeetingSourceList = fieldMeetingSourceRepository.findListFinalByIdSubsectionWithIdList(idCase, idSubsection, idList);
            }

            if (fieldMeetingSourceList.size() > 0) {
                for (FieldMeetingSource fms : fieldMeetingSourceList) {
                    fms.setFinal(false);
                }
                fieldMeetingSourceRepository.save(fieldMeetingSourceList);
            }
            FieldMeetingSource template = new FieldMeetingSource();
            List<FieldMeetingSource> fmsAuxSecond = new ArrayList<>();
            if (idFieldMeeting.equals(-1L)) {
                template.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_UNABLE));
                template.setSourceVerification(sourceVerificationRepository.findSourceImputed(idCase));
                Case c = caseRepository.findOne(idCase);
                List<Long> idFields = fieldVerificationRepository.getListSubsectionByCode(code);

                for (Long id : idFields) {
                    FieldVerification fv = fieldVerificationRepository.findOne(id);
                    template.setFieldVerification(fv);
                    fmsAuxSecond.addAll(valuesOfMeetingService.getValueByCode(fv.getCode(), c.getMeeting(), template, idList));
                }
            } else {
                template = fieldMeetingSourceRepository.findOne(idFieldMeeting);
                if (template.getStatusFieldVerification().getName().equals(Constants.ST_FIELD_VERIF_UNABLE)) {
                    if (idList == null) {
                        fmsAuxSecond = fieldMeetingSourceRepository.getGroupFieldMeeting(template.getSourceVerification().getId(), template.getFieldVerification().getIdSubsection(), Constants.ST_FIELD_VERIF_IMPUTED);
                    } else {
                        fmsAuxSecond = fieldMeetingSourceRepository.getGroupFieldMeetingWithIdList(template.getSourceVerification().getId(), template.getFieldVerification().getIdSubsection(), idList, Constants.ST_FIELD_VERIF_IMPUTED);
                    }
                } else {
                    if (idList == null) {
                        fmsAuxSecond = fieldMeetingSourceRepository.getGroupFieldMeeting(template.getSourceVerification().getId(), template.getFieldVerification().getIdSubsection(), Constants.ST_FIELD_VERIF_UNABLE);
                    } else {
                        fmsAuxSecond = fieldMeetingSourceRepository.getGroupFieldMeetingWithIdList(template.getSourceVerification().getId(), template.getFieldVerification().getIdSubsection(), idList, Constants.ST_FIELD_VERIF_UNABLE);
                    }
                }
            }

            for (FieldMeetingSource fms : fmsAuxSecond) {
                //fms.setId(template.getId());
                fms.setSourceVerification(template.getSourceVerification());
                fms.setStatusFieldVerification(template.getStatusFieldVerification());
                fms.setFinal(true);
                fms.setIdFieldList(idList);
                fms.setReason(reason);
            }
            fieldMeetingSourceRepository.save(fmsAuxSecond);
            return new ResponseMessage(false, "Se ha guardado la selección con éxito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveSelectChoice", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la selección");
        }
    }

    @PersistenceContext(unitName = "entityManagerFactory", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SharedUserService sharedUserService;

    @Transactional
    @Override
    public ResponseMessage terminateVerification(Long idCase) {
        try {
            Gson gson = new Gson();
            TerminateMeetingMessageDto v = new TerminateMeetingMessageDto();
            SourceVerification imputed = sourceVerificationRepository.findSourceImputed(idCase);
            String[] tabs = Constants.TABS_MEETING;
            String[] entities = Constants.ENTITIES_MEETING;
            String[] names = Constants.NAMES_MEETING;
            for (int i = 0; i < names.length; i++) {  //0,5,7
                List<Long> idsList = fieldMeetingSourceRepository.getIdsListOfSectionCodeOfSource((i + 1), idCase, imputed.getId());
                List<String> r = new ArrayList<>();
                String e = "entity";
                String f = "field";
                List<Integer> subsections;
                if (idsList.size() > 0 && idsList.get(0) != null) { //have list
                    int index = 0;
                    for (Long idList : idsList) {
                        index++;
                        subsections = fieldVerificationRepository.getSubsectionsBySectionCodeWithIdList(i + 1, idCase, idList);
                        for (Integer idSubsection : subsections) {
                            List<FieldVerification> fvs = fieldVerificationRepository.getMinFieldByIdSubsection(idSubsection);
                            if (fvs.size() > 0) {
                                List<Long> subsectionIdListList = fieldMeetingSourceRepository.getIdFieldMSFinalByCaseCodeIdList(idCase, idList, fvs.get(0).getCode());
                                if (subsectionIdListList.size() == 0) {
                                    String msg = v.templateVerification.replace(e, entities[i] + " " + index + ":");
                                    msg = msg.replace(f, fvs.get(0).getFieldName());
                                    r.add(msg);
                                }
                            }
                        }
                    }
                } else {
                    subsections = fieldVerificationRepository.getSubsectionsBySectionCode(i + 1, idCase);
                    for (Integer idSubsection : subsections) {
                        List<FieldVerification> fvs = fieldVerificationRepository.getMinFieldByIdSubsection(idSubsection);
                        if (fvs.size() > 0) {
                            List<Long> subsectionIdListList = fieldMeetingSourceRepository.getIdFieldMSFinalByCaseCode(idCase, fvs.get(0).getCode());
                            if (subsectionIdListList.size() == 0) {
                                r.add(v.templateVerificationSingle.replace(e, fvs.get(0).getFieldName()));
                            }
                        }
                    }
                }
                if (r.size() > 0) {
                    v.getGroupMessage().add(new GroupMessageMeetingDto(tabs[i], r));
                }
            }
            if (v.existsMessageProperties()) {
                List<String> listGeneral = new ArrayList<>();
                listGeneral.add("No se puede terminar la verificación puesto que falta por verificar campos y/o secciones, para más detalles revise los mensajes de cada sección");
                v.getGroupMessage().add(new GroupMessageMeetingDto("general", listGeneral));
                v.formatMessages(sharedUserService);
                return new ResponseMessage(true, gson.toJson(v));
            } else {
                Case caseDetention = caseRepository.findOne(idCase);
                valuesOfMeetingService.createMeetingVirified(idCase, caseDetention.getVerification());
                for (ImputedHome imputedHome : caseDetention.getVerification().getMeetingVerified().getImputedHomes()) {
                    imputedHome.setAddress(addressRepository.save(imputedHome.getAddress()));

                }
                caseDetention.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_VERIFICATION_COMPLETE));
                caseDetention.getVerification().setStatus(statusVerificationRepository.findByCode(Constants.VERIFICATION_STATUS_COMPLETE));
                caseRepository.save(caseDetention);
                return new ResponseMessage(false, "Se ha terminado con exito la verificación");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "terminateVerification", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al terminar la verificación");
        }
    }


    @Override
    public ModelAndView upsertSource(Long idCase, Long id) {

        ModelAndView result = new ModelAndView("/reviewer/verification/addSources/upsert");
        Gson gson = new Gson();
        result.addObject("lstRelationship", gson.toJson(relationshipRepository.findNotObsolete()));
        if (id != null) {
            SourceVerification reference = sourceVerificationRepository.findOne(id);
            result.addObject("r", reference);
            result.addObject("relId", gson.toJson(reference.getRelationship().getId()));
        }
        result.addObject("idCase", idCase);
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage doUpsertSources(Long idCase, SourceVerification sv) {
        try {
            Case c = caseRepository.findOne(idCase);
            if (sv.getId() == 0) {
                sv.setId(null);
            }
            SourceVerification sourceVerification = new SourceVerification();
            sourceVerification.setId(sv.getId());
            sourceVerification.setFullName(sv.getFullName());
            sourceVerification.setAge(sv.getAge());
            if (sv.getRelationship().getId() != null) {
                Relationship r = relationshipRepository.findOne(sv.getRelationship().getId());
                sourceVerification.setRelationship(r);
            }
            sourceVerification.setAddress(sv.getAddress());
            sourceVerification.setPhone(sv.getPhone());
            sourceVerification.setAuthorized(false);
            sourceVerification.setVisible(true);
            sourceVerification.setVerification(c.getVerification());
            sourceVerificationRepository.save(sourceVerification);
            return new ResponseMessage(false, "Se ha guardado la fuente exitosamente");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertSources", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar");
        }
    }

    @Override
    public ResponseMessage terminateAddSource(Long idCase) {
        if (!caseService.validateStatus(idCase, Constants.CASE_STATUS_VERIFICATION, Verification.class, Constants.VERIFICATION_STATUS_AUTHORIZED)) {
            return new ResponseMessage(true, "Esta acción no se puede llevar a cabo para este caso");
        }
        Case c = caseRepository.findOne(idCase);
        c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_SOURCE_VALIDATION));
        c.getVerification().setStatus(statusVerificationRepository.findByCode(Constants.VERIFICATION_STATUS_NEW_SOURCE));
        caseRepository.save(c);
        return new ResponseMessage(false, "Se ha modificado el caso exitosamente");
    }


    @Override
    public ModelAndView showChoiceInformation(Long idCase, Integer read) {
        ModelAndView model = new ModelAndView("/reviewer/verification/choiceInformation");
        setImputedData(idCase, model);
        fillMeeting(model, idCase);
        if (read != null) {
            model.addObject("managereval", true);
        } else {
            userConfigToView(model);
        }
        return model;
    }

    private void fillMeeting(ModelAndView model, Long idCase) {
        Gson gson = new Gson();
        Case c = caseRepository.findOne(idCase);
        model.addObject("idCase", idCase);
        model.addObject("m", c.getMeeting());
        model.addObject("age", userService.calculateAge(c.getMeeting().getImputed().getBirthDate()));
        if (c.getMeeting().getSocialEnvironment() != null) {
            if (c.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities() != null) {
                List<RelActivityObjectDto> listRel = new ArrayList<>();
                for (RelSocialEnvironmentActivity r : c.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities()) {
                    RelActivityObjectDto rNew = new RelActivityObjectDto();
                    listRel.add(rNew.relDto(r));
                }
                model.addObject("activity", gson.toJson(listRel));
            }
        }
        //model.addObject("lstPhysicalCondition", gson.toJson(physicalConditionRepository.findAll()));
        List<CatalogDto> listActivity = new ArrayList<>();
        for (Activity a : activityRepository.findNotObsolete()) {
            CatalogDto ca = new CatalogDto();
            ca.setName(a.getName());
            ca.setId(a.getId());
            ca.setSpecification(a.getSpecification());
            listActivity.add(ca);
        }
        model.addObject("lstActivity", gson.toJson(listActivity));
        List<Election> lstElection = electionRepository.findAll();
        List<ElectionDto> lstElectionDto = new ArrayList<ElectionDto>();
        for (Election e : lstElection) {
            ElectionDto edto = new ElectionDto();
            lstElectionDto.add(edto.dtoElection(e));
        }
        model.addObject("listElection", gson.toJson(lstElectionDto));
        model.addObject("lstElection", gson.toJson(lstElectionDto));
        List<AcademicLevel> lstLevel = academicLevelRepository.findNotObsolete();
        List<AcademicLevelDto> listDto = new ArrayList<AcademicLevelDto>();
        for (AcademicLevel s : lstLevel) {
            AcademicLevelDto dtoLevel = new AcademicLevelDto();
            listDto.add(dtoLevel.doDto(s));
        }
        model.addObject("lstLevel", gson.toJson(listDto));
        List<HomeType> lstHomeType = homeTypeRepository.findNotObsolete();
        List<CatalogDto> caDTo = new ArrayList<CatalogDto>();
        for (HomeType s : lstHomeType) {
            CatalogDto x = new CatalogDto();
            x.setId(s.getId());
            x.setName(s.getName());
            x.setSpecification(s.getSpecification());
            caDTo.add(x);
        }
        model.addObject("lstHomeType", gson.toJson(caDTo));
        model.addObject("lstLevel", gson.toJson(listDto));
        List<Country> listCountry = countryRepository.findAllOrderByName();
        List<CountryDto> listCountryDto = new ArrayList<CountryDto>();
        for (Country co : listCountry) {
            CountryDto cdto = new CountryDto();
            listCountryDto.add(cdto.dtoCountry(co));
        }
        model.addObject("lstCountry", gson.toJson(listCountryDto));
        if (c.getMeeting() != null && c.getMeeting().getSchool() != null) {
            if (c.getMeeting().getSchool().getDegree() != null) {
                model.addObject("degreeId", gson.toJson(c.getMeeting().getSchool().getDegree().getId()));
            }
            if (c.getMeeting().getSchool().getSchedule() != null && c.getMeeting().getSchool().getSchedule().size() > 0) {
                model.addObject("listScheduleSchool", scheduleService.getSchedules(c.getId(), School.class));
            }
        }
        ////////////////////
        List<Drug> drugs = c.getMeeting().getDrugs();
        if (drugs != null) {
            model.addObject("lstDrugType", gson.toJson(drugTypeRepository.findNotObsolete()));
            model.addObject("lstPeriodicity", gson.toJson(periodicityRepository.findNotObsolete()));
            List<DrugDto> listDrug = new ArrayList<>();
            for (Drug d : drugs) {
                listDrug.add(new DrugDto().dtoDrug(d));
            }
            model.addObject("listDrug", gson.toJson(listDrug));
        }
        List<CatalogDto> catalogDtoList = new ArrayList<>();
        for (RegisterType rt : registerTypeRepository.findAllOrderByName()) {
            CatalogDto cDto = new CatalogDto();
            cDto.setId(rt.getId());
            cDto.setName(rt.getName());
            catalogDtoList.add(cDto);
        }
        model.addObject("lstRegisterType", gson.toJson(catalogDtoList));
        List<Job> jobs = c.getMeeting().getJobs();
        if (jobs != null) {
            List<JobDto> jobDtoList = new ArrayList<>();
            for (Job j : jobs) {
                String schedule = (String) scheduleService.getSchedules(j.getId(), Job.class);
                jobDtoList.add(new JobDto().dtoJob(j, schedule));
            }
            model.addObject("listJob", gson.toJson(jobDtoList));
        }
        addressService.fillCatalogAddress(model);
        List<ImputedHome> homes = c.getMeeting().getImputedHomes();
        if (homes != null) {
            List<ImputedHomeDto> imputedHomeDtoList = new ArrayList<>();
            for (ImputedHome ih : homes) {
                String schedule = (String) scheduleService.getSchedules(ih.getId(), ImputedHome.class);
                imputedHomeDtoList.add(new ImputedHomeDto().dtoImputedHome(ih, schedule));
            }
            model.addObject("listImputedHome", gson.toJson(imputedHomeDtoList));
        }
        List<PersonSocialNetwork> peopleSN = (c.getMeeting().getSocialNetwork() != null) ? c.getMeeting().getSocialNetwork().getPeopleSocialNetwork() : null;
        if (peopleSN != null) {
            List<SocialNetworkDto> sndto = new ArrayList<>();
            for (PersonSocialNetwork p : c.getMeeting().getSocialNetwork().getPeopleSocialNetwork()) {
                sndto.add(new SocialNetworkDto().dtoSocialNetwork(p));
            }
            model.addObject("listSocialNetwork", gson.toJson(sndto));
            List<CatalogDto> lstRelationship = new ArrayList<>();
            for (Relationship r : relationshipRepository.findNotObsolete()) {
                CatalogDto ca = new CatalogDto();
                ca.setName(r.getName());
                ca.setSpecification(r.getSpecification());
                ca.setId(r.getId());
                lstRelationship.add(ca);
            }
            model.addObject("lstRelationship", gson.toJson(lstRelationship));

            List<CatalogDto> lstDocumentType = new ArrayList<>();
            for (DocumentType r : documentTypeRepository.findNotObsolete()) {
                CatalogDto ca = new CatalogDto();
                ca.setName(r.getName());
                ca.setId(r.getId());
                ca.setSpecification(r.getSpecification());
                lstDocumentType.add(ca);
            }
            model.addObject("lstDocumentType", gson.toJson(lstDocumentType));

        }
        List<Reference> references = c.getMeeting().getReferences();
        if (references != null) {
            List<ReferenceDto> lstReference = new ArrayList<>();
            for (Reference r : references) {
                lstReference.add(new ReferenceDto().dtoReference(r));
            }
            model.addObject("listReference", gson.toJson(lstReference));
        }
    }

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    AddressService addressService;

    @Override
    public void setImputedData(Long id, ModelAndView model) {
        Case c = caseRepository.findOne(id);
        model.addObject("idFolder", c.getIdFolder());
        Imputed i = c.getMeeting().getImputed();
        Meeting m = c.getMeeting();
        String fullName = i.getName() + " " + i.getLastNameP() + " " + i.getLastNameM();
        model.addObject("fullNameImputed", fullName);
        model.addObject("age", userService.calculateAge(i.getBirthDate()));
        model.addObject("idCase", id);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        model.addObject("tStart", format.format(m.getDateCreate()));
        if (m.getDateTerminate() != null) {
            model.addObject("tEnd", format.format(m.getDateTerminate()));
        } else {
            model.addObject("tEnd", "sin terminar");
        }
        model.addObject("reviewerFullname", m.getReviewer().getFullname());
    }

    @Autowired
    FieldVerificationRepository fieldVerificationRepository;
    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;
    @Autowired
    StatusFieldVerificationRepository statusFieldVerificationRepository;
    @Autowired
    CaseService caseService;

    @Override
    @Transactional
    public ResponseMessage saveFieldVerifiedNotKnow(String code, Long idCase, Long idSource, Long idList) {
        if (!caseService.validateStatus(idCase, Constants.CASE_STATUS_VERIFICATION, Verification.class, Constants.VERIFICATION_STATUS_AUTHORIZED)) {
            return new ResponseMessage(true, "De acuerdo al estado del caso y la verificación no se puede realizar esta acción");
        }
        Date sv = sourceVerificationRepository.getDateCompleteBySource(idSource);
        if (sv != null) {
            return new ResponseMessage(true, "No se puede modificar la información de esta fuente");
        }
        try {
            StatusFieldVerification st = statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_DONTKNOW);
            List<FieldMeetingSource> listValues = createFieldNotKnowByCode(code, idCase, idList, st, idSource);
            if (listValues == null) {
                return new ResponseMessage(true, "Ha ocurrido un error al guardar el registro.");
            }
            fieldMeetingSourceRepository.save(listValues);
            return new ResponseMessage(false, "El dato se ha guardado correctamente");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveFieldVerifiedNotKnow", userService);
            return new ResponseMessage(true, "Ha ocurrido un error");
        }
    }

    @Transactional
    @Override
    public ResponseMessage terminateMeetingSource(Long idCase, Long idSource) {
        try {
            if (!caseService.validateStatus(idCase, Constants.CASE_STATUS_VERIFICATION, Verification.class, Constants.VERIFICATION_STATUS_AUTHORIZED)) {
                return new ResponseMessage(true, "De acuerdo al estado del caso y la verificación no se puede realizar esta acción");
            }
            SourceVerification sv = sourceVerificationRepository.findOne(idSource);
            if (!sv.getVerification().getCaseDetention().getId().equals(idCase)) {
                return new ResponseMessage(true, "Esta fuente no pertenece al caso");
            }
            if (sv.getDateComplete() != null) {
                return new ResponseMessage(true, "Esta entrevista ya fue terminada anteriormente");
            }
            sv.setDateComplete(new Date());
            sourceVerificationRepository.save(sv);
            sourceVerificationRepository.flush();
            return new ResponseMessage(false, "Se ha terminado la entrevista con éxito");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "terminateMeetingSource", userService);
            return new ResponseMessage(true, "Ha ocurrido un error al terminar la entrevista");
        }
    }

    @Override
    public void showButtonsSource(ModelAndView model, Long idCase) {
        Long noSources = sourceVerificationRepository.getNoSourceAvailable(idCase);
        if (noSources > 0) {
            model.addObject("sourceAvailable", true);
        } else {
            model.addObject("sourceAvailable", false);
        }
    }


    @Transactional
    @Override
    public ResponseMessage saveFieldVerifiedInocrrect(List<FieldVerified> list, Long idCase, Long idSource, Long idList) {
        try {
            if (!caseService.validateStatus(idCase, Constants.CASE_STATUS_VERIFICATION, Verification.class, Constants.VERIFICATION_STATUS_AUTHORIZED)) {
                return new ResponseMessage(true, "De acuerdo al estado del caso y la verificación no se puede realizar esta acción");
            }
            Date sv = sourceVerificationRepository.getDateCompleteBySource(idSource);
            if (sv != null) {
                return new ResponseMessage(true, "No se puede modificar la información de esta fuente");
            }
            StatusFieldVerification st = statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_NOEQUALS);
            List<Long> fmsToDelete = new ArrayList<>();
            if(list.size()>0){
                Integer idSub = fieldVerificationRepository.getIdSubsectionByCode(list.get(0).getName());
                if(idList==null){
                    fmsToDelete = fieldMeetingSourceRepository.getFMSByIdSubsection(idCase,idSource,idSub);
                }else{
                    fmsToDelete = fieldMeetingSourceRepository.getFMSByIdSubsectionWithIdList(idCase, idSource, idSub, idList);
                }
            }
            List<FieldMeetingSource> result = createFieldVerification(list, idCase, idSource, idList, st, fmsToDelete);
            if (result == null) {
                return new ResponseMessage(true, "Ha ocurrido un error al crear la lista.");
            }
            fieldMeetingSourceRepository.save(result);
            for (Long id : fmsToDelete) {
                fieldMeetingSourceRepository.delete(id);
            }
            return new ResponseMessage(false, "El dato se ha guardado correctamente");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveFieldVerifiedInocrrect", userService);
            return new ResponseMessage(true, "Ha ocurrido un error ");
        }

    }

    @Transactional
    @Override
    public ResponseMessage saveFieldVerifiedEqual(String code, Long idCase, Long idSource, Long idList) {
        if (!caseService.validateStatus(idCase, Constants.CASE_STATUS_VERIFICATION, Verification.class, Constants.VERIFICATION_STATUS_AUTHORIZED)) {
            return new ResponseMessage(true, "De acuerdo al estado del caso y la verificación no se puede realizar esta acción");
        }
        Date sv = sourceVerificationRepository.getDateCompleteBySource(idSource);
        if (sv != null) {
            return new ResponseMessage(true, "No se puede modificar la información de esta fuente");
        }
        try {
            StatusFieldVerification st = statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_EQUALS);
            List<FieldMeetingSource> listValues = createFieldMeetingByImputed(code, idCase, idList, st, idSource);
            if (listValues == null) {
                return new ResponseMessage(true, "Ha ocurrido un error al obtener la información del imputado");
            }
            fieldMeetingSourceRepository.save(listValues);
            return new ResponseMessage(false, "El dato se ha guardado correctamente");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveFieldVerifiedEqual", userService);
            return new ResponseMessage(true, "Ha ocurrido un error");
        }
    }

    private List<FieldMeetingSource> createFieldMeetingByImputed(String code, Long idCase, Long idList, StatusFieldVerification st, Long idSource) {
        try {
            List<FieldMeetingSource> result = new ArrayList<>();
            Long idSourceImputed = sourceVerificationRepository.findIdSourceImputed(idCase);
            List<Long> listFieldSection = fieldVerificationRepository.getListSubsectionByCode(code);
            for (Long idFv : listFieldSection) {
                FieldVerification fv = fieldVerificationRepository.findOne(idFv);
                if (fv != null) {
                    FieldMeetingSource fmsNew = new FieldMeetingSource();
                    FieldMeetingSource fmsAux = new FieldMeetingSource();
                    Long fieldMeetingSourceId, fieldMeetingSourceImputedId;
                    if (idList == null) {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSource, fv.getCode());
                        fieldMeetingSourceImputedId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSourceImputed, fv.getCode());

                    } else {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSource, fv.getCode(), idList);
                        fieldMeetingSourceImputedId = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSourceImputed, fv.getCode(), idList);
                    }
                    fmsNew.setId(fieldMeetingSourceId);
                    fmsNew.setSourceVerification(sourceVerificationRepository.findOne(idSource));
                    fmsNew.setFieldVerification(fv);
                    if (fieldMeetingSourceImputedId != null) {
                        fmsAux = fieldMeetingSourceRepository.findOne(fieldMeetingSourceImputedId);
                        fmsNew.setValue(fmsAux.getValue());
                        fmsNew.setJsonValue(fmsAux.getJsonValue());
                        fmsNew.setStatusFieldVerification(st);
                        fmsNew.setFinal(false);
                        fmsNew.setIdFieldList(idList);
                        result.add(fmsNew);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private List<FieldMeetingSource> createFieldNotKnowByCode(String code, Long idCase, Long idList, StatusFieldVerification st, Long idSource) {
        try {
            List<FieldMeetingSource> result = new ArrayList<>();
            List<Long> listFieldSection = fieldVerificationRepository.getListSubsectionByCode(code);
            Long idSourceImputed = sourceVerificationRepository.findIdSourceImputed(idCase);
            for (Long idFv : listFieldSection) {
                FieldVerification fv = fieldVerificationRepository.findOne(idFv);
                if (fv != null) {
                    FieldMeetingSource fmsNew = new FieldMeetingSource();
                    Long fieldMeetingSourceId, fieldMeetingImputedId;
                    if (idList == null) {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSource, fv.getCode());
                        fieldMeetingImputedId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSourceImputed, fv.getCode());
                    } else {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSource, fv.getCode(), idList);
                        fieldMeetingImputedId = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSourceImputed, fv.getCode(), idList);
                    }
                    if (fieldMeetingImputedId != null) {
                        fmsNew.setId(fieldMeetingSourceId);
                        fmsNew.setSourceVerification(sourceVerificationRepository.findOne(idSource));
                        fmsNew.setFieldVerification(fv);
                        fmsNew.setValue(Constants.VALUE_NOT_KNOW_SOURCE);
                        fmsNew.setJsonValue(Constants.VALUE_NOT_KNOW_SOURCE);
                        fmsNew.setStatusFieldVerification(st);
                        fmsNew.setFinal(false);
                        fmsNew.setIdFieldList(idList);
                        result.add(fmsNew);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<FieldMeetingSource> createAllFieldVerificationOfImputed(Long idCase) {
        List<FieldVerification> listField = fieldVerificationRepository.findValidFields();
        List<FieldMeetingSource> listFieldMeetingSource = new ArrayList<>();
        Case c = caseRepository.findOne(idCase);
        FieldMeetingSource fms = new FieldMeetingSource();
        fms.setSourceVerification(sourceVerificationRepository.findSourceImputed(idCase));
        fms.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_IMPUTED));
        fms.setFinal(false);
        for (FieldVerification field : listField) {
            fms.setFieldVerification(field);
            listFieldMeetingSource.addAll(getValueOfMeetingByCode(field.getCode(), c.getMeeting(), fms));
        }
        return listFieldMeetingSource;
        // fieldMeetingSourceRepository.save(listFieldMeetingSource);
    }


    @Autowired
    ValuesOfMeetingService valuesOfMeetingService;

    private List<FieldMeetingSource> getValueOfMeetingByCode(String code, Meeting m, FieldMeetingSource template) {
        return valuesOfMeetingService.getValueOfMeetingByCode(code, m, template);
    }

    private List<FieldMeetingSource> createFieldVerification(List<FieldVerified> list, Long idCase, Long idSource, Long idList, StatusFieldVerification st, List<Long> fmsToDelete) {
        try {
            List<FieldMeetingSource> listFieldVerficiation = new ArrayList<>();


            for (FieldVerified field : list) {
                if (!field.getValue().equals("")) {
                    FieldMeetingSource fms = new FieldMeetingSource();
                    Long fieldMeetingSourceId;
                    if (idList == null) {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSource, field.getName());
                    } else {
                        fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase, idSource, field.getName(), idList);
                    }
                    fms.setId(fieldMeetingSourceId);
                    FieldVerification fv = fieldVerificationRepository.findByCode(field.getName());
                    fms.setFieldVerification(fv);
                    fms.setSourceVerification(sourceVerificationRepository.findOne(idSource));
                    String[] vars = field.getName().split("\\.");
                    Boolean adding = setObjectNameOfCatalog(fms, vars, field.getValue(), fv);
                    fms.setFinal(false);
                    fms.setIdFieldList(idList);
                    fms.setStatusFieldVerification(st);
                    if (adding) {
                        listFieldVerficiation.add(fms);
                    }
                    if(fieldMeetingSourceId!=null){
                    for(int i = 0; i< fmsToDelete.size(); i++){
                        if(fieldMeetingSourceId.equals(fmsToDelete.get(i))){
                            fmsToDelete.remove(i);
                            break;
                        }
                    }
                    }
                }
            }
            return listFieldVerficiation;
        } catch (Exception e) {
            return null;
        }
    }

    @Autowired
    MaritalStatusRepository maritalStatusRepository;
    @Autowired
    DegreeRepository degreeRepository;
    @Autowired
    HomeTypeRepository homeTypeRepository;

    private Boolean setObjectNameOfCatalog(FieldMeetingSource fms, String[] name, String value, FieldVerification fv) {
        Long idCat = 0L;
        if (name[name.length - 1].equals("id")) {
            idCat = Long.parseLong(value);
        }
        CatalogDto ca = new CatalogDto();
        Gson gson = new Gson();
        switch (fv.getType()) {
            case "Country":
                Country c = countryRepository.findOne(idCat);
                ca.setId(c.getId());
                ca.setName(c.getName());
                fms.setValue(c.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "MaritalStatus":
                MaritalStatus m = maritalStatusRepository.findOne(idCat);
                ca.setId(m.getId());
                ca.setName(m.getName());
                fms.setValue(m.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "Election":
                Election e = electionRepository.findOne(idCat);
                fms.setValue(e.getName());
                CatalogDto cdto = new CatalogDto();
                cdto.setId(e.getId());
                cdto.setName(e.getName());
                fms.setJsonValue(gson.toJson(cdto));
                break;
            case "RegisterType":
                RegisterType r = registerTypeRepository.findOne(idCat);
                ca.setId(r.getId());
                ca.setName(r.getName());
                fms.setValue(r.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "Relationship":
                Relationship rel = relationshipRepository.findOne(idCat);
                ca.setName(rel.getName());
                ca.setId(rel.getId());
                fms.setValue(rel.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "DocumentType":
                DocumentType doc = documentTypeRepository.findOne(idCat);
                ca.setId(doc.getId());
                ca.setName(doc.getName());
                fms.setValue(doc.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "DrugType":
                DrugType dt = drugTypeRepository.findOne(idCat);
                ca.setName(dt.getName());
                ca.setId(dt.getId());
                fms.setValue(dt.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "Periodicity":
                Periodicity p = periodicityRepository.findOne(idCat);
                ca.setName(p.getName());
                ca.setId(p.getId());
                fms.setValue(p.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "Degree":
                Degree degree = degreeRepository.findOne(idCat);
                fms.setValue("Nivel: " + degree.getAcademicLevel().getName() + " Grado: " + degree.getName());
                fms.setJsonValue(gson.toJson(new DegreeDto().dtoGrade(degree)));
                break;
            case "Date":
                String valueString = "", valueJson = "";
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
                    Date date = formatter.parse(value);
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    valueJson = String.valueOf(date.getTime());
                    valueString = formatter.format(date);
                } catch (Exception ex) {
                    valueJson = "Error en conversión";
                    valueString = value;
                } finally {
                    fms.setValue(valueString);
                    fms.setJsonValue(valueJson);
                }
                break;
            case "BooleanG":
                Boolean gender = value.equals("0");
                String genderString;
                if (gender.equals(Constants.GENDER_FEMALE))
                    genderString = "Femenino";
                else
                    genderString = "Masculino";
                fms.setValue(genderString);
                fms.setJsonValue(value);
                break;
            case "Boolean":
                String acString = value.equals("0") ? "No" : "Si";
                fms.setValue(acString);
                fms.setJsonValue(value);
                break;
            case "Activity":

                try {
                    List<RelSocialEnvironmentActivity> relSE = gson.fromJson(value, new TypeToken<List<RelSocialEnvironmentActivity>>() {
                    }.getType());
                    fms.setJsonValue(value);
                    String val = "";
                    if (relSE != null) {
                        for (RelSocialEnvironmentActivity re : relSE) {
                            val = val + activityRepository.findOne(re.getActivity().getId()).getName();
                            if (re.getSpecification() != null && !re.getSpecification().equals("")) {
                                val = val + ": " + re.getSpecification() + "; ";
                            } else {
                                val = val + "; ";
                            }
                        }
                    }
                    fms.setValue(val);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }

                break;
            case "HomeType":
                HomeType ht = homeTypeRepository.findOne(idCat);
                ca.setName(ht.getName());
                ca.setId(ht.getId());
                fms.setValue(ht.getName());
                fms.setJsonValue(gson.toJson(ca));
                break;
            case "Location":
                if (idCat != null && !idCat.equals(0L)) {
                    Location l = locationRepository.findOne(idCat);
                    ca.setName(l.getName());
                    ca.setId(l.getId());
                    fms.setValue("Estado: " + l.getMunicipality().getState().getName() + ", Municipio; " + l.getMunicipality().getName() + ", Localidad: " + l.getName() + ".");
                    fms.setJsonValue(gson.toJson(ca));
                } else {
                    return false;
                }
                break;
            default:
                fms.setValue(value);
                fms.setJsonValue(value);
                break;


        }
        return true;

    }


}

