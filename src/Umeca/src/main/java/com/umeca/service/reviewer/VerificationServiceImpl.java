package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.*;
import com.umeca.model.catalog.dto.*;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.ChoiceView;
import com.umeca.model.entities.reviewer.View.SearchToChoiceIds;
import com.umeca.model.entities.reviewer.dto.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

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

    @Override
    public void createVerification(Case c) {
        c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_SOURCE_VALIDATION));
        Verification verification = new Verification();
        verification.setCaseDetention(c);
        verification.setStatus(statusVerificationRepository.findByCode(Constants.VERIFICATION_STATUS_NEW_SOURCE));
        verification.setReviewer(userRepository.findOne(userService.GetLoggedUserId()));
        //TODO agregar la entrevista verificada como la incial.
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

    @Override
    public ModelAndView showVerificationBySource(Long idCase, Long idSource) {

        ModelAndView model = new ModelAndView("/reviewer/verification/verificationBySource");
        setImputedData(idCase, model);
        Gson gson = new Gson();
        feelMeeting(model,idCase);
        SourceVerification sv = sourceVerificationRepository.findOne(idSource);
        model.addObject("idSource", idSource);
        model.addObject("source", gson.toJson(new SourceVerificationDto().dtoSourceVerification(sv)));
        return model;
    }

    @Override
    public ModelAndView showChoices(Long idCase, String code, Long idList) {
        ModelAndView model = new ModelAndView("/reviewer/verification/showChoices");
        model.addObject("idCase",idCase);
        List<Long> idAllSources = sourceVerificationRepository.getAllSourcesByCase(idCase);
        List<SearchToChoiceIds> idSources;
        if(idList==null){
            idSources = fieldMeetingSourceRepository.getIdSourceByCode(idCase,code);
        }else{
            idSources = fieldMeetingSourceRepository.getIdSourceByCodeWhithIdList(idCase,code,idList);
        }
        List<ChoiceView> list = new ArrayList<>();
        Boolean addUnable = true;
        for(SearchToChoiceIds e: idSources){
            if(idAllSources.contains(e.getIdSource())){
                idAllSources.remove(idAllSources.indexOf(e.getIdSource()));
            }
            List<FieldMeetingSource> result = new ArrayList<>();
            if(idList==null){
                result = fieldMeetingSourceRepository.getGroupFieldMeeting(e.getIdSource(), e.getIdSubsection());
            }else{
                result = fieldMeetingSourceRepository.getGroupFieldMeetingWithIdList(e.getIdSource(),e.getIdSubsection(),idList);
            }
            if(result!=null){
                list.add(new ChoiceView().choiceDto(result));
                if(result.size()>0 && result.get(0).getStatusFieldVerification().getName().equals(Constants.ST_FIELD_VERIF_UNABLE)){
                    addUnable = false;
                }
            }
        }
        for(Long id: idAllSources){
            SourceVerification sv = sourceVerificationRepository.findOne(id);
            ChoiceView choiceView = new ChoiceView();
            choiceView.setNameSource(sv.getFullName());
            choiceView.setStatus("NOT_FOUND");
            List listString = new ArrayList<String>();
            listString.add("Esta fuente no proporcionó información para este campo o sección");
            choiceView.setValues(listString);
            list.add(choiceView);
        }
        if(addUnable){
            FieldMeetingSource unableVerification = new FieldMeetingSource();
            unableVerification.setId(-1L);
            SourceVerification aux= new SourceVerification();
            aux.setFullName("No hay forma de verificar la información");
            unableVerification.setSourceVerification(aux);
            unableVerification.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_UNABLE));
            unableVerification.setFieldVerification(fieldVerificationRepository.findByCode(code));
            Case c = caseRepository.findOne(idCase);
            List<Long> idFields = fieldVerificationRepository.getListSubsectionByCode(code);
            List<FieldMeetingSource> fmsAuxSecond= new ArrayList<>();
            for(Long id:  idFields){
              FieldVerification fvSubsection = fieldVerificationRepository.findOne(id);
              fmsAuxSecond.addAll(getValueOfMeetingByCode(fvSubsection.getCode(),c.getMeeting(),unableVerification));
            }
            list.add(new ChoiceView().choiceDto(fmsAuxSecond));
        }

        Gson gson = new Gson();
        model.addObject("listChoice", gson.toJson(list));
        return model;
    }

    @Override
    public ResponseMessage saveSchedule(Long idCase, Long idSource, Long idList, String schedule, String code) {
        try{
            Gson gson = new Gson();
            Long idF ;
            if( idList != null){
                idF =fieldMeetingSourceRepository.getIdMeetingSourceByCodeWithIdList(idCase,idSource,code,idList);
            }else{
                idF =fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase,idSource,code);
            }
            FieldMeetingSource fms = new FieldMeetingSource();
            fms.setId(idF);
            fms.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_NOEQUALS));
            fms.setSourceVerification(sourceVerificationRepository.findOne(idSource));
            fms.setFieldVerification(fieldVerificationRepository.findByCode(code));
            fms.setJsonValue(schedule);
            List<Schedule> listSchedules = gson.fromJson(schedule,new TypeToken<List<Schedule>>(){}.getType());
            String val="";
            for(Schedule sch : listSchedules){
                val +="Día(s): "+sch.getDay()+" Inicio: "+sch.getStart()+ "Fin: "+sch.getEnd()+"<br/>";
            }
            fms.setValue(val);
            fms.setFinal(false);
            fms.setIdFieldList(idList);
            fieldMeetingSourceRepository.save(fms);
            return new ResponseMessage(false, "Se ha guardado exitosamente el registro");
        }catch (Exception e){
         return new ResponseMessage(true, "Ha ocurrido un error al guardar la lista");
        }
    }

    @Override
    public ModelAndView showChoiceInformation(Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/verification/choiceInformation");
        setImputedData(idCase, model);
        feelMeeting(model,idCase);
        return model;
    }

    private void feelMeeting(ModelAndView model,Long idCase){
        Gson gson = new Gson();
        Case c = caseRepository.findOne(idCase);
        model.addObject("idCase", idCase);
        model.addObject("m", c.getMeeting());
        if (c.getMeeting().getSocialEnvironment() != null) {
            if (c.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities() != null) {
                List<RelActivitySocialEnvironmentDto> listRel = new ArrayList<>();
                for (RelSocialEnvironmentActivity r : c.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities()) {
                    RelActivitySocialEnvironmentDto rNew = new RelActivitySocialEnvironmentDto();
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
        List<Country> listCountry = countryRepository.findAll();
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
        for (RegisterType rt : registerTypeRepository.findAll()) {
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
        String fullName = c.getMeeting().getImputed().getName() + " " + c.getMeeting().getImputed().getLastNameP() + " " + c.getMeeting().getImputed().getLastNameM();
        model.addObject("fullNameImputed", fullName);
        model.addObject("idCase", id);
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
        if(sv!=null){
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
            if(!sv.getVerification().getCaseDetention().getId().equals(idCase)){
                return new ResponseMessage(true, "Esta fuente no pertenece al caso");
            }
            if(sv.getDateAuthorized()!= null){
                return new ResponseMessage(true,"Esta entrevista ya fue terminada anteriormente");
            }
            sv.setDateComplete(new Date());
            sourceVerificationRepository.save(sv);
            sourceVerificationRepository.flush();
            return new ResponseMessage(false, "Se ha terminado la entrevista con éxito");
        }catch (Exception e){
            return  new ResponseMessage(true, "Ha ocurrido un error al terminar la entrevista");
        }
    }

    @Override
    public void showButtonsSource(ModelAndView model,Long idCase) {
        Long noSources = sourceVerificationRepository.getNoSourceAvailable(idCase);
        if(noSources>0){
            model.addObject("sourceAvailable", true);
        }else{
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
            if(sv!=null){
                return new ResponseMessage(true, "No se puede modificar la información de esta fuente");
            }
            StatusFieldVerification st = statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_NOEQUALS);
            List<FieldMeetingSource> result = createFieldVerification(list, idCase, idSource, idList, st);
            if (result == null) {
                return new ResponseMessage(true, "Ha ocurrido un error al crear la lista.");
            }
            fieldMeetingSourceRepository.save(result);
            return new ResponseMessage(false, "El dato se ha guardado correctamente");
        } catch (Exception e) {
            return new ResponseMessage(true, "Ha ocurrido un error ");
        }

    }

    @Override
    public ResponseMessage saveFieldVerifiedEqual(String code, Long idCase, Long idSource, Long idList) {
        if (!caseService.validateStatus(idCase, Constants.CASE_STATUS_VERIFICATION, Verification.class, Constants.VERIFICATION_STATUS_AUTHORIZED)) {
            return new ResponseMessage(true, "De acuerdo al estado del caso y la verificación no se puede realizar esta acción");
        }
        Date sv = sourceVerificationRepository.getDateCompleteBySource(idSource);
        if(sv!=null){
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
            return new ResponseMessage(true, "Ha ocurrido un error");
        }
    }

    private List<FieldMeetingSource> createFieldMeetingByImputed(String code, Long idCase, Long idList, StatusFieldVerification st, Long idSource) {
        try {
            List<FieldMeetingSource> result = new ArrayList<>();
            List<Long> listFieldSection = fieldVerificationRepository.getListSubsectionByCode(code);
            for (Long idFv : listFieldSection) {
                FieldMeetingSource fms = new FieldMeetingSource();
                if(idList != null){
                    fms = fieldMeetingSourceRepository.findMeetingSourceByIdFieldVerification(idCase, idList, idFv);
                }else{
                    fms = fieldMeetingSourceRepository.findMeetingSourceByIdFieldVerificationWithoutId(idCase, idFv);
                }
                FieldMeetingSource fmsNew = new FieldMeetingSource();
                Long fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase,idSource, code);
                fmsNew.setId(fieldMeetingSourceId);
                fmsNew.setSourceVerification(sourceVerificationRepository.findOne(idSource));
                fmsNew.setFieldVerification(fieldVerificationRepository.findByCode(code));
                fmsNew.setValue(fms.getValue());
                fmsNew.setJsonValue(fms.getJsonValue());
                fmsNew.setStatusFieldVerification(st);
                fmsNew.setFinal(false);
                fmsNew.setIdFieldList(idList);
                result.add(fmsNew);
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
            for (Long idFv : listFieldSection) {
                FieldMeetingSource fmsNew = new FieldMeetingSource();
                Long fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase,idSource, code);
                fmsNew.setId(fieldMeetingSourceId);
                fmsNew.setSourceVerification(sourceVerificationRepository.findOne(idSource));
                fmsNew.setFieldVerification(fieldVerificationRepository.findByCode(code));
                fmsNew.setValue(Constants.VALUE_NOT_KNOW_SOURCE);
                fmsNew.setJsonValue(Constants.VALUE_NOT_KNOW_SOURCE);
                fmsNew.setStatusFieldVerification(st);
                fmsNew.setFinal(false);
                fmsNew.setIdFieldList(idList);
                result.add(fmsNew);
            }
           return result;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<FieldMeetingSource> createAllFieldVerificationOfImputed(Long idCase){
        List<FieldVerification> listField  = fieldVerificationRepository.findValidFields();
        List<FieldMeetingSource> listFieldMeetingSource = new ArrayList<>();
        Case c = caseRepository.findOne(idCase);
        FieldMeetingSource fms = new FieldMeetingSource();
        fms.setSourceVerification(sourceVerificationRepository.findSourceImputed(idCase));
        fms.setStatusFieldVerification(statusFieldVerificationRepository.findStatusByCode(Constants.ST_FIELD_VERIF_IMPUTED));
        fms.setFinal(false);
        for(FieldVerification field : listField){
            fms.setFieldVerification(field);
            listFieldMeetingSource.addAll(getValueOfMeetingByCode(field.getCode(),c.getMeeting(), fms));
        }
       return listFieldMeetingSource;
       // fieldMeetingSourceRepository.save(listFieldMeetingSource);
    }


    private List<FieldMeetingSource> getValueOfMeetingByCode(String code, Meeting m, FieldMeetingSource template) {
        List<FieldMeetingSource> listFMS = new ArrayList<>();
        String[] name = code.split("\\.");
        Gson gson = new Gson();
        switch (name[0]){
            case "imputed":
                Imputed imputed = m.getImputed();
                CatalogDto cdto = new CatalogDto();
                switch (name[1]){
                    case "name":
                        listFMS.add(new FieldMeetingSource(imputed.getName(), imputed.getName()));
                        break;
                    case "lastNameP":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameP(),imputed.getLastNameP()));
                        break;
                    case "lastNameM":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameM(), imputed.getLastNameM()));
                        break;
                    case "birthDate":
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        listFMS.add(new FieldMeetingSource(formatter.format(imputed.getBirthDate()),String.valueOf(imputed.getBirthDate())));
                        break;
                    case "gender":
                        Boolean gender =imputed.getGender();
                        String genderString;
                        if (gender.equals(Constants.GENDER_FEMALE))
                            genderString = "Femenino";
                        else
                            genderString = "Masculino";
                        listFMS.add(new FieldMeetingSource(genderString, m.getImputed().getGender().toString()));
                        break;
                    case "celPhone":
                        listFMS.add(new FieldMeetingSource(imputed.getCelPhone(),imputed.getCelPhone()));
                        break;
                    case "maritalStatus":
                        cdto.setName(imputed.getMaritalStatus().getName());
                        cdto.setId(imputed.getMaritalStatus().getId());
                        listFMS.add(new FieldMeetingSource(cdto.getName(), gson.toJson(cdto)));
                        break;
                    case "yearsMartialStatus":
                        listFMS.add(new FieldMeetingSource(imputed.getYearsMaritalStatus().toString(), imputed.getYearsMaritalStatus().toString()));
                        break;
                    case "boys":
                        listFMS.add(new FieldMeetingSource(imputed.getBoys().toString(), imputed.getBoys().toString()));
                        break;
                    case "dependentBoys":
                        listFMS.add(new FieldMeetingSource(imputed.getDependentBoys().toString(), imputed.getDependentBoys().toString()));
                        break;
                    case "birthCountry":
                        cdto.setName(imputed.getBirthCountry().getName());
                        cdto.setId(imputed.getBirthCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdto.getName(), gson.toJson(cdto)));
                        break;
                    case "birthState":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthState(), imputed.getBirthState()));
                        break;
                    case "birthMunicipality":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthMunicipality(), imputed.getBirthMunicipality()));
                        break;
                    case "birthLocation":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthLocation(), imputed.getBirthLocation()));
                        break;
                }
            break;
            case "socialEnvironment":
                SocialEnvironment se = m.getSocialEnvironment();
                switch (name[1]){
                    case "physicalCondition":
                        listFMS.add(new FieldMeetingSource(se.getPhysicalCondition(), se.getPhysicalCondition()));
                        break;
                    case "comment":
                        if(se.getComment()!=null && !se.getComment().equals(""))
                        listFMS.add(new FieldMeetingSource(se.getComment(), se.getComment()));
                        break;
                }
                break;
            case "imputedHomes":
                for(ImputedHome ih: m.getImputedHomes()){
                    CatalogDto cDto = new CatalogDto();
                    switch(name[1]){
                        case "address":
                            AddressDto adDto = new AddressDto();
                            adDto.addressDto(ih.getAddress());
                            listFMS.add(new FieldMeetingSource(ih.getAddress().getAddressString(), gson.toJson(adDto),ih.getId()));
                            break;
                        case "belong":
                            cDto.setId(ih.getBelong().getId());
                            cDto.setName(ih.getBelong().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),ih.getId()));
                        break;
                        case "registerType":
                            cDto.setId(ih.getRegisterType().getId());
                            cDto.setName(ih.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),ih.getId()));
                            break;
                        case "timeLive":
                            listFMS.add(new FieldMeetingSource(ih.getTimeLive(), ih.getTimeLive(),ih.getId()));
                            break;
                        case "reasonChange":
                            if(ih.getReasonChange()!=null && !ih.getReasonChange().equals(""))
                            listFMS.add(new FieldMeetingSource(ih.getReasonChange(), ih.getReasonChange(),ih.getId()));
                            break;
                        case "description":
                            if(ih.getDescription()!=null && !ih.getDescription().equals(""))
                            listFMS.add(new FieldMeetingSource(ih.getDescription(), ih.getDescription(),ih.getId()));
                            break;
                        case "schedule":
                            String s = (String)scheduleService.getSchedules(ih.getId(), ImputedHome.class);
                            if(!s.equals("[]")){
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(ih.getId(),ImputedHome.class),s,ih.getId()));
                            }
                            break;
                    }
                }
                break;
            case "socialNetwork":
                for(PersonSocialNetwork psn: m.getSocialNetwork().getPeopleSocialNetwork()){
                    CatalogDto cDto=  new CatalogDto();
                    switch (name[1]){
                        case "name":
                            listFMS.add(new FieldMeetingSource(psn.getName(), psn.getName(),psn.getId()));
                            break;
                        case "relationship":
                            cDto.setName(psn.getRelationship().getName());
                            cDto.setId(psn.getRelationship().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(psn.getPhone(), psn.getPhone(),psn.getId()));
                            break;
                        case "documentType":
                            cDto.setName(psn.getDocumentType().getName());
                            cDto.setId(psn.getDocumentType().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),psn.getId()));
                            break;
                        case "specification":
                            if(psn.getSpecification()!= null && !psn.getSpecification().equals("")){
                                    listFMS.add(new FieldMeetingSource(psn.getSpecification(), psn.getSpecification(),psn.getId()));
                            }
                            break;
                        case "age":
                            listFMS.add(new FieldMeetingSource(psn.getAge().toString(), psn.getAge().toString(),psn.getId()));
                            break;
                        case "dependent":
                            cDto.setName(psn.getDependent().getName());
                            cDto.setId(psn.getDependent().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "livingWith":
                            cDto.setName(psn.getLivingWith().getName());
                            cDto.setId(psn.getLivingWith().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(psn.getAddress(), psn.getAddress(),psn.getId()));
                            break;
                    }
                }
                break;
            case "references":
                for(Reference r: m.getReferences()){
                    CatalogDto cDto=  new CatalogDto();
                    switch (name[1]){
                        case "fullName":
                            listFMS.add(new FieldMeetingSource(r.getFullName(), r.getFullName(),r.getId()));
                            break;
                        case "relationship":
                            cDto.setName(r.getRelationship().getName());
                            cDto.setId(r.getRelationship().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),r.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(r.getPhone(), r.getPhone(),r.getId()));
                            break;
                        case "documentType":
                            cDto.setName(r.getDocumentType().getName());
                            cDto.setId(r.getDocumentType().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),r.getId()));
                            break;
                        case "specification":
                            if(r.getSpecification()!= null && !r.getSpecification().equals("")){
                                listFMS.add(new FieldMeetingSource(r.getSpecification(), r.getSpecification(),r.getId()));
                            }
                            break;
                        case "age":
                            listFMS.add(new FieldMeetingSource(r.getAge().toString(), r.getAge().toString(),r.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(r.getAddress(), r.getAddress(),r.getId()));
                            break;
                    }
                }
                break;
            case "jobs":
                for(Job j: m.getJobs()){
                    CatalogDto cDto=  new CatalogDto();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    switch (name[1]){
                        case "company":
                             listFMS.add(new FieldMeetingSource(j.getCompany(),j.getCompany(),j.getId()));
                            break;
                        case "post":
                            listFMS.add(new FieldMeetingSource(j.getPost(), j.getPost(),j.getId()));
                            break;
                        case "nameHead":
                            listFMS.add(new FieldMeetingSource(j.getNameHead(), j.getNameHead(),j.getId()));
                           break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(j.getPhone(), j.getPhone(),j.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(j.getAddress(), j.getAddress(),j.getId()));
                            break;
                        case "registerType":
                            cDto.setId(j.getRegisterType().getId());
                            cDto.setName(j.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),j.getId()));
                            break;
                        case "start":
                            if(!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                            listFMS.add(new FieldMeetingSource(formatter.format(j.getStart()),String.valueOf(j.getStart()),j.getId()));
                            break;
                        case "salaryWeek":
                            if(!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getSalaryWeek().toString(), j.getSalaryWeek().toString(),j.getId()));
                            break;
                        case "startPrev":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getStartPrev()),String.valueOf(j.getStartPrev()),j.getId()));
                            break;
                        case "end":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getEnd()),String.valueOf(j.getEnd()),j.getId()));
                            break;
                        case "reasonChange":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getReasonChange(), j.getReasonChange(),j.getId()));
                            break;
                        case "schedule":
                            String s = (String)scheduleService.getSchedules(j.getId(), Job.class);
                            if(!s.equals("[]")){
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(j.getId(),Job.class),s,j.getId()));
                            }
                            break;
                    }
                }
                break;
            case "school":
                School s = m.getSchool();
                switch (name[1]){
                    case "name":
                        listFMS.add(new FieldMeetingSource(s.getName(), s.getName()));
                        break;
                    case "phone":
                        listFMS.add(new FieldMeetingSource(s.getPhone(), s.getPhone()));
                        break;
                    case "address":
                        listFMS.add(new FieldMeetingSource(s.getAddress(), s.getAddress()));
                        break;
                    case "degree":
                        String level = "Nivel: "+s.getDegree().getAcademicLevel().getName()+" Grado: "+s.getDegree().getName();
                        listFMS.add(new FieldMeetingSource(level, gson.toJson(new DegreeDto().dtoGrade(s.getDegree()))));
                        break;
                    case "schedule":
                        String sc = (String)scheduleService.getSchedules(s.getId(), School.class);
                        if(!s.equals("[]")){
                            listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(s.getId(),ImputedHome.class),sc));
                        }
                        break;
                }
                break;
            case "drugs":
                for(Drug d: m.getDrugs()){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    CatalogDto cdtod = new CatalogDto();
                    switch (name[1]){
                        case "drugType":
                            cdtod.setName(d.getDrugType().getName());
                            cdtod.setId(d.getDrugType().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod),d.getId()));
                            break;
                        case "specificationType":
                            if(d.getSpecificationType()!=null && !d.getSpecificationType().equals(""))
                                listFMS.add(new FieldMeetingSource(d.getSpecificationType(), d.getSpecificationType(),d.getId()));
                            break;
                        case "periodicity":
                            cdtod.setName(d.getPeriodicity().getName());
                            cdtod.setId(d.getPeriodicity().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod),d.getId()));
                            break;
                        case "specificationPeriodicity":
                            if(d.getSpecificationPeriodicity()!=null && !d.getSpecificationPeriodicity().equals("")){
                                listFMS.add(new FieldMeetingSource(d.getSpecificationPeriodicity(), d.getSpecificationPeriodicity(),d.getId()));
                            }
                            break;
                        case "quantity":
                            listFMS.add(new FieldMeetingSource(d.getQuantity(),d.getQuantity(),d.getId()));
                            break;
                        case "lastUse":
                            listFMS.add(new FieldMeetingSource(formatter.format(d.getLastUse()),String.valueOf(d.getLastUse()),d.getId()));
                            break;
                    }
                }
                break;
            case "leaveCountry":
                LeaveCountry l=m.getLeaveCountry();
                CatalogDto cdtol = new CatalogDto();
                switch (name[1]){
                    case "officialDocumentation":
                        cdtol.setName(l.getOfficialDocumentation().getName());
                        cdtol.setId(l.getOfficialDocumentation().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "livedCountry":
                        cdtol.setName(l.getLivedCountry().getName());
                        cdtol.setId(l.getLivedCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "country":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            cdtol.setId(l.getCountry().getId());
                            cdtol.setName(l.getCountry().getName());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "state":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getState(), l.getState()));
                        }
                        break;
                    case "timeAgo":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getTimeAgo(), l.getTimeAgo()));
                        }
                        break;
                    case "reason":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getReason(), l.getReason()));
                        }
                        break;
                    case "address":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getAddress(),l.getAddress()));
                        }
                        break;
                    case "familyAnotherCountry":
                        cdtol.setName(l.getFamilyAnotherCountry().getName());
                        cdtol.setId(l.getFamilyAnotherCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "communicationFamily":
                        if(l.getFamilyAnotherCountry().getId().equals(Constants.ELECTION_YES)){
                            cdtol.setName(l.getCommunicationFamily().getName());
                            cdtol.setId(l.getCommunicationFamily().getId());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "media":
                        if(l.getMedia()!=null && !l.getMedia().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getMedia(),l.getMedia()));
                          }
                        break;
                }
                break;
        }
        for(FieldMeetingSource fms: listFMS){
            fms.setFieldVerification(template.getFieldVerification());
            fms.setSourceVerification(template.getSourceVerification());
            fms.setStatusFieldVerification(template.getStatusFieldVerification());
            fms.setFinal(false);
        }
        return listFMS;
    }


    private List<FieldMeetingSource> createFieldVerification(List<FieldVerified> list, Long idCase, Long idSource, Long idList, StatusFieldVerification st) {
        try {
            List<FieldMeetingSource> listFieldVerficiation = new ArrayList<>();
            for (FieldVerified field : list) {
                FieldMeetingSource fms = new FieldMeetingSource();
                Long fieldMeetingSourceId = fieldMeetingSourceRepository.getIdMeetingSourceByCode(idCase, idSource, list.get(0).getName());
                fms.setId(fieldMeetingSourceId);
                FieldVerification fv = fieldVerificationRepository.findByCode(field.getName());
                fms.setFieldVerification(fv);
                fms.setSourceVerification(sourceVerificationRepository.findOne(idSource));
                String[] vars = field.getName().split("\\.");
                setObjectNameOfCatalog(fms, vars, field.getValue(), fv);
                fms.setFinal(false);
                fms.setIdFieldList(idList);
                fms.setStatusFieldVerification(st);
                listFieldVerficiation.add(fms);
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

    private void setObjectNameOfCatalog(FieldMeetingSource fms, String[] name, String value, FieldVerification fv) {
        Long idCat = 0L;
        if (name[name.length - 1].equals("id")) {
            idCat = Long.parseLong(value);
        }
        Gson gson = new Gson();
        switch (fv.getType()) {
            case "Country":
                Country c = countryRepository.findOne(idCat);
                fms.setValue(c.getName());
                fms.setJsonValue(gson.toJson(c));
                break;
            case "MaritalStatus":
                MaritalStatus m = maritalStatusRepository.findOne(idCat);
                fms.setValue(m.getName());
                fms.setJsonValue(gson.toJson(m));
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
                fms.setValue(r.getName());
                fms.setJsonValue(gson.toJson(r));
                break;
            case "Relationship":
                Relationship rel = relationshipRepository.findOne(idCat);
                fms.setValue(rel.getName());
                fms.setJsonValue(gson.toJson(rel));
                break;
            case "DocumentType":
                DocumentType doc = documentTypeRepository.findOne(idCat);
                fms.setValue(doc.getName());
                fms.setJsonValue(gson.toJson(doc));
                break;
            case "DrugType":
                DrugType dt = drugTypeRepository.findOne(idCat);
                fms.setValue(dt.getName());
                fms.setJsonValue(gson.toJson(dt));
                break;
            case "Periodicity":
                Periodicity p = periodicityRepository.findOne(idCat);
                fms.setValue(p.getName());
                fms.setJsonValue(gson.toJson(p));
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
                    fms.setValue(valueJson);
                }
                break;
            case "Boolean":
                Boolean gender = value.equals("0");
                String genderString;
                if (gender.equals(Constants.GENDER_FEMALE))
                    genderString = "Femenino";
                else
                    genderString = "Masculino";
                fms.setValue(genderString);
                fms.setJsonValue(value);
                break;
            default:
                fms.setValue(value);
                fms.setJsonValue(value);
                break;
        }

    }
}
