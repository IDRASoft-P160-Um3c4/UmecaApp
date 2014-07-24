          package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.catalog.*;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.dto.AcademicLevelDto;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.catalog.dto.CountryDto;
import com.umeca.model.catalog.dto.ElectionDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.CriminalProceedingView;
import com.umeca.model.entities.reviewer.dto.GroupMessageMeetingDto;
import com.umeca.model.entities.reviewer.dto.RelActivitySocialEnvironmentDto;
import com.umeca.model.entities.reviewer.dto.TerminateMeetingMessageDto;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.catalog.CatalogService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    SharedUserService userService;
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private ImputedRepository imputedRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private StatusMeetingRepository statusMeetingRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    ElectionRepository electionRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Autowired
    DayWeekRepository dayWeekRepository;
    @Autowired
    AcademicLevelRepository academicLevelRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CatalogService catalogService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MaritalStatusRepository  maritalStatusRepository;
    @Autowired
    PersonSocialNetworkRepository personSocialNetworkRepository;
    @Autowired
    SocialNetworkRepository socialNetworkRepository;
    @Autowired
    ReferenceRepository referenceRepository;
    @Autowired
    DrugTypeRepository drugTypeRepository;
    @Autowired
    PeriodicityRepository periodicityRepository;
    @Autowired
    DrugRepository drugRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    DegreeRepository degreeRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    RegisterTypeRepository registerTypeRepository;
    @Autowired
    ImputedHomeRepository imputedHomeRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    StatusCaseRepository statusCaseRepository;
    @Autowired
    LegalService legalService;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    CurrentCriminalProceedingRepository currentCriminalProceedingRepository;
    @Autowired
    PreviousCriminalProceedingRepository previousCriminalProceedingRepository;
    @Autowired
    VerificationService verificationService;
    @Autowired
    VerificationRepository verificationRepository;
    @Autowired
    RelSocialEnvironmentActivityRepository rsearRepository;
    @Autowired
    SharedLogExceptionService logException;

    @Transactional
    @Override
    public Long createMeeting(Imputed imputed) {
        Long result = null;
        try {
            Case caseDetention = new Case();
            if (imputedRepository.findImputedRegister(imputed.getName(), imputed.getLastNameP(), imputed.getLastNameM(), imputed.getBirthDate()).size() > 0)
                caseDetention.setRecidivist(true);
            else
                caseDetention.setRecidivist(false);
            caseDetention.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_MEETING));
            caseDetention.setIdFolder(imputed.getMeeting().getCaseDetention().getIdFolder());
            caseDetention = caseRepository.save(caseDetention);
            Meeting meeting = new Meeting();
            meeting.setMeetingType(HearingFormatConstants.MEETING_PROCEDURAL_RISK);
            meeting.setCaseDetention(caseDetention);
            StatusMeeting statusMeeting = statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE);
            meeting.setStatus(statusMeeting);
            meeting.setReviewer(userRepository.findOne(userService.GetLoggedUserId()));
            meeting.setDateCreate(new Date());
            meeting = meetingRepository.save(meeting);
            imputed.setMeeting(meeting);
            imputedRepository.save(imputed);
            result = caseDetention.getId();
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"createMeeting",userService);
        } finally {
            return result;
        }
    }

    @Override
    public ModelAndView showMeeting(Long id) {
        if (!validateMeetingUser(id))
            return new ModelAndView("/reviewer/meeting/index");
        ModelAndView model = new ModelAndView("/reviewer/meeting/meeting");
        Gson gson = new Gson();
        ////////////////////Personal data
        Case caseDetention = caseRepository.findOne(id);
        model.addObject("idCase", caseDetention.getId());
        model.addObject("m", caseDetention.getMeeting());
        model.addObject("age",userService.calculateAge(caseDetention.getMeeting().getImputed().getBirthDate()));
        if (caseDetention.getMeeting().getSocialEnvironment() != null) {
            if (caseDetention.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities() != null) {
                List<RelActivitySocialEnvironmentDto> listRel = new ArrayList<>();
              for(RelSocialEnvironmentActivity r: caseDetention.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities())
              {
                  RelActivitySocialEnvironmentDto rNew= new RelActivitySocialEnvironmentDto();
                  listRel.add(rNew.relDto(r));
              }
                model.addObject("activity", gson.toJson(listRel));
            }
        }
        //model.addObject("lstPhysicalCondition", gson.toJson(physicalConditionRepository.findAll()));
        List<CatalogDto> listActivity = new ArrayList<>();
        for(Activity a: activityRepository.findNotObsolete()){
            CatalogDto c = new CatalogDto();
            c.setName(a.getName());
            c.setId(a.getId());
            c.setSpecification(a.getSpecification());
            listActivity.add(c);
        }
        model.addObject("lstActivity", gson.toJson(listActivity));
        model.addObject("lstDayWeek", gson.toJson(dayWeekRepository.findAll()));
        List<Election> lstElection = electionRepository.findAll();
        List<ElectionDto> lstElectionDto = new ArrayList<ElectionDto>();
        for (Election e : lstElection) {
            ElectionDto edto = new ElectionDto();
            lstElectionDto.add(edto.dtoElection(e));
        }
        model.addObject("listElection", gson.toJson(lstElectionDto));
        List<AcademicLevel> lstLevel = academicLevelRepository.findNotObsolete();
        List<AcademicLevelDto> listDto = new ArrayList<AcademicLevelDto>();
        for (AcademicLevel s : lstLevel) {
            AcademicLevelDto dtoLevel = new AcademicLevelDto();
            listDto.add(dtoLevel.doDto(s));
        }
        model.addObject("lstLevel", gson.toJson(listDto));
        List<Country> listCountry = countryRepository.findAllOrderByName();
        List<CountryDto> listCountryDto = new ArrayList<CountryDto>();
        for (Country c : listCountry) {
            CountryDto cdto = new CountryDto();
            listCountryDto.add(cdto.dtoCountry(c));
        }
        model.addObject("lstCountry", gson.toJson(listCountryDto));
        if (caseDetention.getMeeting() != null && caseDetention.getMeeting().getSchool() != null) {
            if (caseDetention.getMeeting().getSchool().getDegree() != null) {
                model.addObject("degreeId", gson.toJson(caseDetention.getMeeting().getSchool().getDegree().getId()));
            }
            if (caseDetention.getMeeting().getSchool().getSchedule() != null && caseDetention.getMeeting().getSchool().getSchedule().size() > 0) {
                model.addObject("listSchedule", scheduleService.getSchedules(caseDetention.getId(), School.class));
            }
        }
        /////////////////////////Social Network

        return model;
    }

    @Override
    public ModelAndView showLegalProcess(Long id) {
        if (!validateMeetingUser(id))
            return new ModelAndView("/reviewer/meeting/index");
        ModelAndView model = new ModelAndView("/reviewer/meeting/legal/index");
        Case c = caseRepository.findOne(id);
        model.addObject("idFolder", c.getIdFolder());
        Imputed i= c.getMeeting().getImputed();
        String fullName = i.getName() + " " + i.getLastNameP() + " " + i.getLastNameM();
        model.addObject("fullNameImputed", fullName);
        model.addObject("age",userService.calculateAge(i.getBirthDate()));
        model.addObject("idCase", id);
        addressService.fillCatalogAddress(model);
        Gson gson = new Gson();
        List<Election> lstElection =   electionRepository.findAll();
        List<ElectionDto> lstElectionDto = new ArrayList<ElectionDto>();
        for (Election e : lstElection) {
            ElectionDto edto = new ElectionDto();
            lstElectionDto.add(edto.dtoElection(e));
        }
        model.addObject("listElection", gson.toJson(lstElectionDto));
        List<Relationship> relationshipList= relationshipRepository.findNotObsolete();
        List<CatalogDto> catalogDtoList = new ArrayList<>();
        for(Relationship relationship: relationshipList){
            CatalogDto cdto= new CatalogDto();
            cdto.setId(relationship.getId());
            cdto.setName(relationship.getName());
            catalogDtoList.add(cdto);
        }
        model.addObject("listRelationship", gson.toJson(catalogDtoList));
        model.addObject("listLegalBefore",findLegalBefore(id,c.getMeeting().getImputed().getName(),c.getMeeting().getImputed().getLastNameP(),c.getMeeting().getImputed().getLastNameM()));
        return model;
    }

    String findLegalBefore(Long id, String name, String lastNameP, String lastNameM){
        List<FindLegalBefore> list = caseRepository.findLegalBefore(id,name,lastNameP, lastNameM);
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @Transactional
    @Override
    public ResponseMessage upsertPersonalData(Long idCase, Imputed imputed, SocialEnvironment socialEnvironment, String activity) {
        ResponseMessage result = new ResponseMessage();
        Case caseDetention = caseRepository.findOne(idCase);
        try {
            refreshPersonalData(imputed,socialEnvironment,activity,caseDetention);
            caseRepository.save(caseDetention);
            caseRepository.saveAndFlush(caseDetention);
            result.setHasError(false);
            result.setMessage("Se ha guardado la información exitosamente");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"upsertPersonalData",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrdio un error al guardar los datos personales. Favor de intentar más tarde" + e.getMessage());
        }
        return result;
    }

    private void refreshPersonalData(Imputed imputed, SocialEnvironment socialEnvironment, String activity, Case caseDetention){
        Imputed iCase = caseDetention.getMeeting().getImputed();
        SocialEnvironment seCase = caseDetention.getMeeting().getSocialEnvironment();
        Meeting m = caseDetention.getMeeting();
        iCase.setCelPhone(imputed.getCelPhone());
        iCase.setMaritalStatus(maritalStatusRepository.findOne(imputed.getMaritalStatus().getId()));
        iCase.setGender(imputed.getGender());
        iCase.setBoys(imputed.getBoys());
        iCase.setDependentBoys(imputed.getDependentBoys());
        iCase.setBirthState(imputed.getBirthState());
        iCase.setBirthLocation(imputed.getBirthLocation());
        iCase.setBirthMunicipality(imputed.getBirthMunicipality());
        iCase.setBirthCountry(countryRepository.findOne(imputed.getBirthCountry().getId()));
        iCase.setYearsMaritalStatus(imputed.getYearsMaritalStatus());

        if (seCase != null && seCase.getId() != null) {
            seCase.setId(seCase.getId());
        }else{
            seCase = socialEnvironment;
            seCase.setMeeting(m);
            m.setSocialEnvironment(seCase);
        }
        Gson gson= new Gson();
        if(seCase!=null && seCase.getRelSocialEnvironmentActivities()!=null){
            List<RelSocialEnvironmentActivity> relAux =seCase.getRelSocialEnvironmentActivities();
            seCase.setRelSocialEnvironmentActivities(null);
            for(RelSocialEnvironmentActivity r: relAux){
                r.setSocialEnvironment(null);
                r.setActivity(null);
                rsearRepository.delete(r);
            }
        }
        List<RelSocialEnvironmentActivity> rel = gson.fromJson(activity,new TypeToken<List<RelSocialEnvironmentActivity>>(){}.getType());
        if (rel != null) {
            seCase.setRelSocialEnvironmentActivities(new ArrayList<RelSocialEnvironmentActivity>());
            for (RelSocialEnvironmentActivity r : rel) {
                RelSocialEnvironmentActivity newRel= new RelSocialEnvironmentActivity();
                newRel.setActivity(activityRepository.findOne(r.getActivity().getId()));
                newRel.setSpecification(r.getSpecification());
                newRel.setSocialEnvironment(seCase);
                seCase.getRelSocialEnvironmentActivities().add(newRel);
            }
        }
    }

    @Override
    public ModelAndView upsertSocialNetwork(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/socialNetwork/upsert");
        Gson gson = new Gson();
        model.addObject("lstRelationship", gson.toJson(relationshipRepository.findNotObsolete()));
        model.addObject("lstElection", gson.toJson(electionRepository.findAll()));
        model.addObject("lstDocumentType", gson.toJson(documentTypeRepository.findNotObsolete()));
        if (id != null) {
            PersonSocialNetwork p = personSocialNetworkRepository.findOne(id);
            PersonSocialNetwork pView = new PersonSocialNetwork();
            pView.setId(p.getId());
            pView.setName(p.getName());
            pView.setAge(p.getAge());
            pView.setPhone(p.getPhone());
            pView.setAddress(p.getAddress());
            pView.setSpecification(p.getSpecification());
            model.addObject("p", gson.toJson(pView));
            model.addObject("relId", gson.toJson(p.getRelationship().getId()));
            model.addObject("docId", gson.toJson(p.getDocumentType().getId()));
            model.addObject("depId", gson.toJson(p.getDependent().getId()));
            model.addObject("livId", gson.toJson(p.getLivingWith().getId()));
        }
        model.addObject("idCase", idCase);
        return model;
    }

    @Transactional
    @Override
    public ResponseMessage doUpsertSocialNetwork(PersonSocialNetwork person, Long idCase) {
        ResponseMessage result = new ResponseMessage();
        try {
            person.setRelationship(relationshipRepository.findOne(person.getRelationship().getId()));
            person.setDocumentType(documentTypeRepository.findOne(person.getDocumentType().getId()));
            person.setDependent(electionRepository.findOne(person.getDependent().getId()));
            person.setLivingWith(electionRepository.findOne(person.getLivingWith().getId()));
            Case caseDetention = caseRepository.findOne(idCase);
            SocialNetwork sn = caseDetention.getMeeting().getSocialNetwork();
            if (sn == null) {
                sn = new SocialNetwork();
                sn.setMeeting(caseDetention.getMeeting());
                socialNetworkRepository.save(sn);
            }
            person.setSocialNetwork(sn);
            if (person.getId() != null && person.getId() == 0) {
                person.setId(null);
            }
            personSocialNetworkRepository.save(person);

        } catch (Exception e) {
            logException.Write(e,this.getClass(),"doUpsertSocialNetwork",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al guardar la persona de red social.");
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage deleteSocialNetwork(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            personSocialNetworkRepository.delete(personSocialNetworkRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se elimino la persona de red social exitosamente");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"deleteSocialNetwork",userService);
            result.setHasError(true);
            result.setMessage("No se ha podido eliminar la persona de red social. Intente más tarde");
        }
        return result;
    }

    @Override
    public ModelAndView upsertReference(Long id, Long idCase) {
        ModelAndView result = new ModelAndView("/reviewer/meeting/reference/upsert");
        Gson gson = new Gson();
        result.addObject("lstRelationship", gson.toJson(relationshipRepository.findNotObsolete()));
        result.addObject("lstElection", gson.toJson(electionRepository.findAll()));
        result.addObject("lstDocumentType", gson.toJson(documentTypeRepository.findNotObsolete()));
        if (id != null) {
            Reference reference = referenceRepository.findOne(id);
            result.addObject("r", reference);
            result.addObject("relId", gson.toJson(reference.getRelationship().getId()));
            result.addObject("docId", gson.toJson(reference.getDocumentType().getId()));
        }

        result.addObject("idCase", idCase);
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage doUpsertReference(Reference reference, Long idCase) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case caseDetention = caseRepository.findOne(idCase);
            reference.setMeeting(caseDetention.getMeeting());
            reference.setRelationship(relationshipRepository.findOne(reference.getRelationship().getId()));
            reference.setDocumentType(documentTypeRepository.findOne(reference.getDocumentType().getId()));
            if (reference.getId() != null && reference.getId() == 0) {
                reference.setId(null);
            }
            referenceRepository.saveAndFlush(reference);
            result.setHasError(false);
            result.setMessage("Se ha guardado correctamente la referencia personal");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"doUpsertReference",userService);
            result.setHasError(true);
            result.setMessage("Ocurrio un error al guardar la refernecia. Intente más tarde.");
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage deleteReference(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            referenceRepository.delete(referenceRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se ha eliminado la referencia exitosamente.");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"deleteReference",userService);
            result.setHasError(false);
            result.setMessage("Ocurrio un error al eliminar la referencia. Intente más tarde.");
        }
        return result;
    }

    @Override
    public ModelAndView upsertDrug(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/drug/upsert");
        Gson gson = new Gson();
        model.addObject("lstDrugType", gson.toJson(drugTypeRepository.findNotObsolete()));
        model.addObject("lstPeriodicity", gson.toJson(periodicityRepository.findNotObsolete()));
        if (id != null && id != 0) {
            Drug d = drugRepository.findOne(id);
            model.addObject("d", d);
            model.addObject("typeId", d.getDrugType().getId());
            model.addObject("perId", d.getPeriodicity().getId());
        }
        model.addObject("idCase", idCase);
        return model;
    }

    @Transactional
    @Override
    public ResponseMessage doUpsertDrug(Drug drug, Long idCase) {
        ResponseMessage result = new ResponseMessage();
        try {
            drug.setDrugType(drugTypeRepository.findOne(drug.getDrugType().getId()));
            drug.setPeriodicity(periodicityRepository.findOne(drug.getPeriodicity().getId()));
            drug.setMeeting(caseRepository.findOne(idCase).getMeeting());
            if (drug.getId() != null && drug.getId() == 0) {
                drug.setId(null);
            }
            drugRepository.save(drug);
            result.setHasError(false);
            result.setMessage("Se ha guardado la sustancia con éxito");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"doUpsertDrug",userService);
            result.setHasError(true);
            result.setMessage("Ocorrio un error al guardar la sustancia.Inténte más tarde.");
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage deleteDrug(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            drugRepository.delete(drugRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se elimino la sustancia con éxito");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"deleteDrug",userService);
            result.setHasError(true);
            result.setMessage("Ocurrio un error al eliminar la sustancia. Inténte más tarde");
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage doUpsertSchool(Long id, School school, String schedules) {
        ResponseMessage result = new ResponseMessage();
        try {
            Gson gson = new Gson();
            Case caseDetention = caseRepository.findOne(id);
            refreshSchool(school,caseDetention);
            caseRepository.save(caseDetention);
            School s = caseDetention.getMeeting().getSchool();
            if(s==null){
                s=new School();
            }
            List<Schedule> listToDelete = s.getSchedule();
            s.setSchedule(null);
            if(listToDelete!=null){
                for(Schedule schedule: listToDelete){
                    schedule.setSchool(null);
                    scheduleRepository.delete(schedule.getId());
                }
            }
            List<Schedule> listSchedules = gson.fromJson(schedules,new TypeToken<List<Schedule>>(){}.getType());

            for(Schedule schedule: listSchedules){
                schedule.setSchool(s);
            }
            scheduleRepository.save(listSchedules);
            result.setHasError(false);
            result.setMessage("Se ha actualizado su información exitosamente");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"doUpsertSchool",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al actualizar su información" + e.getMessage());
        }
        return result;
    }

    private void refreshSchool(School school, Case caseDetention){
        School s = caseDetention.getMeeting().getSchool();
        if (s != null) {
            s.setName(school.getName());
            s.setAddress(school.getAddress());
            s.setPhone(school.getPhone());
            s.setSpecification(school.getSpecification());
            s.setDegree(degreeRepository.findOne(school.getDegree().getId()));
        }else{
            Degree degree = degreeRepository.findOne(school.getDegree().getId());
            school.setDegree(degree);
            Meeting m = caseDetention.getMeeting();
            m.setSchool(school);
            school.setMeeting(m);
        }

    }

    @Override
    public ModelAndView upsertJob(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/job/upsert");
        Gson gson = new Gson();
        model.addObject("lstRegisterType", gson.toJson(registerTypeRepository.findAllOrderByName()));
        model.addObject("lstDayWeek", gson.toJson(dayWeekRepository.findAll()));
        if (id != null && id != 0) {
            Job j = jobRepository.findOne(id);
            model.addObject("j", j);
            model.addObject("listSchedule", scheduleService.getSchedules(j.getId(), Job.class));
            model.addObject("registerTypeId", j.getRegisterType().getId());
        }
        model.addObject("idCase", idCase);
        return model;
    }

    @Transactional
    @Override
    public ResponseMessage doUpsertJob(Job job, Long idCase, String sch) {
        ResponseMessage result = new ResponseMessage();
        try {
            if (!job.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                ResponseMessage validate = validateSchedules(sch, "el trabajo");
                if (validate != null) {
                    return validate;
                }
            }
            Case c = caseRepository.findOne(idCase);
            if (job.getId() != null && job.getId() == 0) {
                job.setId(null);
            }
            job.setMeeting(c.getMeeting());
            job.setRegisterType(registerTypeRepository.findOne(job.getRegisterType().getId()));
            job=jobRepository.save(job);
            if (!job.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                List<Schedule> listToDelete = job.getSchedule();
                if(listToDelete !=null){
                    job.setSchedule(null);
                    for(Schedule schedule: listToDelete){
                        schedule.setJob(null);
                        scheduleRepository.delete(schedule.getId());
                    }
                }
                Gson gson = new Gson();
                List<Schedule> listSchedules = gson.fromJson(sch,new TypeToken<List<Schedule>>(){}.getType());
                for(Schedule schedule: listSchedules){
                    schedule.setJob(job);
                }
                scheduleRepository.save(listSchedules);
            }
            result.setHasError(false);
            result.setMessage("Se ha guardado la infomación exitosamente");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"doUpsertJob",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al actualizar su información. Intente más tarde.");
        }
        return result;
    }

    private ResponseMessage validateSchedules(String sch, String entidad) {
        if (sch == null || (sch != null && sch.equals("")) || (sch != null && sch.equals("[]"))) {
            return new ResponseMessage(true, "La disponibilidad de " + entidad + " actual es requerida.");
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseMessage deleteJob(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            jobRepository.delete(id);
            result.setHasError(false);
            result.setMessage("Se ha eliminado correctamente el registro");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"deleteJob",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al intentar eliminar el registro. Inténte más tarde.");
        }
        return result;
    }

    @Override
    public ModelAndView upsertAddress(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/address/upsert");
        try {
            Gson gson = new Gson();
            model.addObject("listRegisterType", gson.toJson(registerTypeRepository.findAllOrderByName()));
            model.addObject("listElection", gson.toJson(electionRepository.findAll()));
            model.addObject("lstDayWeek", gson.toJson(dayWeekRepository.findAll()));
            model.addObject("idCase", idCase);
            addressService.fillCatalogAddress(model);
            if (id != null && id != 0) {

                ImputedHome imputedHome = imputedHomeRepository.findOne(id);
                model.addObject("d", imputedHome);
                if(imputedHome.getAddress()!=null){
                    addressService.fillModelAddress(model, imputedHome.getAddress().getId());
                }
                model.addObject("belongId", imputedHome.getBelong().getId());
                model.addObject("listSchedule", scheduleService.getSchedules(imputedHome.getId(), ImputedHome.class));
                model.addObject("typeId", imputedHome.getRegisterType().getId());
                addressService.fillCatalogAddress(model);
            }
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"upsertAddress",userService);
        }
        return model;
    }

    @Transactional
    @Override
    public ResponseMessage doUpsertAddress(ImputedHome imputedHome, Long idCase, String sch) {
        ResponseMessage result = new ResponseMessage();
        try {
            if (!imputedHome.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                ResponseMessage validate = validateSchedules(sch, "el domicilio");
                if (validate != null) {
                    return validate;
                }
            }
            Case c = caseRepository.findOne(idCase);
            if (imputedHome.getId() != null && imputedHome.getId() == 0) {
                imputedHome.setId(null);
            }
            imputedHome.setMeeting(c.getMeeting());
            imputedHome.setBelong(electionRepository.findOne(imputedHome.getBelong().getId()));
            imputedHome.setRegisterType(registerTypeRepository.findOne(imputedHome.getRegisterType().getId()));
            if(imputedHome.getAddress()!=null && imputedHome.getAddress().getLocation()!=null && imputedHome.getAddress().getLocation().getId()!=null){
                Long locationId = imputedHome.getAddress().getLocation().getId();
                    imputedHome.getAddress().setLocation(locationRepository.findOne(locationId));
                    imputedHome.getAddress().setAddressString(imputedHome.getAddress().toString());
                    imputedHome.setAddress(addressRepository.save(imputedHome.getAddress()));
            }
            ImputedHome newImputedHome = imputedHomeRepository.save(imputedHome);
            if (!newImputedHome.getRegisterType().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                List<Schedule> listToDelete = newImputedHome.getSchedule();
                if(listToDelete !=null){
                    newImputedHome.setSchedule(null);
                    for(Schedule schedule: listToDelete){
                        schedule.setImputedHome(null);
                        scheduleRepository.delete(schedule.getId());
                    }
                }
                Gson gson = new Gson();
                List<Schedule> listSchedules = gson.fromJson(sch,new TypeToken<List<Schedule>>(){}.getType());
                for(Schedule schedule: listSchedules){
                    schedule.setImputedHome(newImputedHome);
                }
                scheduleRepository.save(listSchedules);
            }
            result.setHasError(false);
            result.setMessage("Se ha guardado la información con éxito");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"doUpsertAddress",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al guardar su inormación. Intente más tarde." + e.getMessage());
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage deleteAddress(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            ImputedHome imputedHome = imputedHomeRepository.findOne(id);
            Long idAddress =  imputedHome.getAddress().getId();
            imputedHomeRepository.delete(id);
            addressRepository.delete(idAddress);
            result.setHasError(false);
            result.setMessage("Se ha eliminado el registro exitosamente");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"deleteAddress",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al eliminar el registro. Inténte más tarde");
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage upsertLeaveCountry(Long id, LeaveCountry leaveCountry) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case c = caseRepository.findOne(id);
            refreshLeaveCountry(leaveCountry,c);
            caseRepository.saveAndFlush(c);
            result.setHasError(false);
            result.setMessage("Se ha guardado su información exitosamente");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"upsertLeaveCountry",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al guardar la información");
        }
        return result;
    }

    private void refreshLeaveCountry(LeaveCountry leaveCountry, Case c){
        Meeting m = c.getMeeting();
        LeaveCountry l = c.getMeeting().getLeaveCountry();
        if(l==null){
            l = new LeaveCountry();
            c.getMeeting().setLeaveCountry(l);
            l.setMeeting(m);
        }
        l.setOfficialDocumentation(electionRepository.findOne(leaveCountry.getOfficialDocumentation().getId()));
        Long family = leaveCountry.getFamilyAnotherCountry().getId();
        l.setFamilyAnotherCountry(electionRepository.findOne(family));
        if (family.equals(Constants.ELECTION_YES)) {
            l.setCommunicationFamily(electionRepository.findOne(leaveCountry.getCommunicationFamily().getId()));
        }
        Long livedCountry = leaveCountry.getLivedCountry().getId();
        l.setLivedCountry(electionRepository.findOne(livedCountry));
        if (livedCountry != null && livedCountry.equals(Constants.ELECTION_YES)) {
            l.setCountry(countryRepository.findOne(leaveCountry.getCountry().getId()));
        }
        l.setState(leaveCountry.getState());
        l.setTimeAgo(leaveCountry.getTimeAgo());
        l.setReason(leaveCountry.getReason());
        l.setAddress(leaveCountry.getAddress());
        l.setMedia(leaveCountry.getMedia());
    }

    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;
    @Transactional
    @Override
    public ResponseMessage doTerminateMeeting(Meeting meeting, String sch, String activities) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case c = caseRepository.findOne(meeting.getCaseDetention().getId());
            Gson gson = new Gson();
            refreshPersonalData(meeting.getImputed(),meeting.getSocialEnvironment(),activities,c);
            refreshSchool(meeting.getSchool(),c);
            List<Schedule> listToDelete = c.getMeeting().getSchool().getSchedule();
            c.getMeeting().getSchool().setSchedule(null);
            if(listToDelete!=null){
                for(Schedule schedule: listToDelete){
                    schedule.setSchool(null);
                    scheduleRepository.delete(schedule.getId());
                }
            }
            List<Schedule> listSchedules = gson.fromJson(sch,new TypeToken<List<Schedule>>(){}.getType());
            School s = c.getMeeting().getSchool();
            for(Schedule schedule: listSchedules){
                schedule.setSchool(s);
            }
            scheduleRepository.save(listSchedules);
            refreshLeaveCountry(meeting.getLeaveCountry(),c);
            TerminateMeetingMessageDto validate= new TerminateMeetingMessageDto();
            c.getMeeting().getImputed().validateMeeting(validate);
            if(c.getMeeting().getSchool()==null){
                c.getMeeting().setSchool(new School());
            }
            c.getMeeting().getSchool().validateMeeting(validate);
            if(c.getMeeting().getLeaveCountry()==null){
                c.getMeeting().setLeaveCountry(new LeaveCountry());
            }
            c.getMeeting().getLeaveCountry().validateMeeting(validate);
           c.getMeeting().validateMeeting(validate);
            if (validate.existsMessageProperties()) {
                List<String> listGeneral=new ArrayList<>();
                listGeneral.add("No se puede terminar la entrevista puesto que falta por responder preguntas, para más detalles revise los mensajes de cada sección");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("general",listGeneral));
                return new ResponseMessage(true,gson.toJson(validate));
            }
            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_MEETING));
            c.getMeeting().setStatus(statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE_LEGAL));
            caseRepository.save(c);
            result.setHasError(false);
            result.setMessage("Entrevista terminada con exito");
            result.setUrlToGo("/index.html");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"doTerminateMeeting",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al terminar la entrevista. Intente más tarde");
        }
        return result;
    }



    @Override
    public ResponseMessage validateCreateMeeting(Imputed imputed) {
        if (imputed.getBirthDate() != null) {
            Integer age = userService.calculateAge(imputed.getBirthDate());
            if (age.compareTo(18) == -1) {
                return new ResponseMessage(true, "El imputado debe tener más de 18 años para continuar");
            }
        } else {
            return new ResponseMessage(true, "Favor de ingresar la fecha de nacimiento del imputado.");
        }
        if (imputed.getMeeting() != null && imputed.getMeeting().getCaseDetention() != null && imputed.getMeeting().getCaseDetention().getIdFolder() != null) {
            /*Case c = caseRepository.findByIdFolder(imputed.getMeeting().getCaseDetention().getIdFolder());
            if (c != null) {
                return new ResponseMessage(true, "El número de carpeta de investigación ya se encuentra registrado.");
            }*/
        } else {
            return new ResponseMessage(true, "Favor de ingresar el número de carpeta de investigación para continuar");
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseMessage saveProceedingLegal(CriminalProceedingView cpv) {
        ResponseMessage result = new ResponseMessage();
        try {
           TerminateMeetingMessageDto validate =new TerminateMeetingMessageDto();
            validateProceedingLegal(cpv,validate);
            if (validate.existsMessageProperties()) {
                Gson gson = new Gson();
                List<String> listGeneral=new ArrayList<>();
                listGeneral.add("No se puede guardar la información legal puesto que falta por responder preguntas, para más detalles revise los mensajes de cada sección");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("general",listGeneral));
                result.setHasError(true);
                result.setMessage(gson.toJson(validate));
                return result;
            }
            Address av = new Address();
            Long locationId = cpv.getDomicileVictim().getLocation().getId();
            av.setLocation(locationRepository.findOne(locationId));
            av.setStreet(cpv.getDomicileVictim().getStreet());
            av.setInnNum(cpv.getDomicileVictim().getInnNum());
            av.setOutNum(cpv.getDomicileVictim().getOutNum());
            av.setAddressString(av.toString());
            av = addressRepository.save(av);
            Case c = caseRepository.findOne(cpv.getIdCase());
            CurrentCriminalProceeding ccp = new CurrentCriminalProceeding();
            ccp.setMeeting(c.getMeeting());
            ccp.setBehaviorDetention(cpv.getBehaviorDetention());
            ccp.setDomicileVictim(av);
            ccp.setNameVictim(cpv.getNameVictim());
            ccp.setCrimeList(legalService.generateCrime(cpv.getListCrime(), ccp));
            if (cpv.getListCoDefendant() != null && !cpv.getListCoDefendant().equals("")) {
                ccp.setCoDefendantList(legalService.getnerateCoDefendant(cpv.getListCoDefendant(), ccp));
            }
            ccp.setRelationshipVictim(relationshipRepository.findOne(cpv.getRelVictimId()));
            ccp.setPlaceDetention(cpv.getPlaceDetention());
            c.getMeeting().setCurrentCriminalProceeding(ccp);
            //currentCriminalProceedingRepository.save(ccp);
            PreviousCriminalProceeding pcp = new PreviousCriminalProceeding();
            pcp.setFirstProceeding(cpv.getFirstProceeding());
            pcp.setOpenProcessNumber(cpv.getOpenProcessNumber());
            pcp.setNumberConvictions(cpv.getNumberConvictions());
            pcp.setComplyPM(electionRepository.findOne(cpv.getComplyPMId()));
            pcp.setComplyCSPP(electionRepository.findOne(cpv.getComplyCSPPId()));
            pcp.setComplyProcessAbove(electionRepository.findOne(cpv.getComplyProcessAboveId()));
            pcp.setMeeting(c.getMeeting());
            c.getMeeting().setPreviousCriminalProceeding(pcp);
            StatusMeeting stm = statusMeetingRepository.findByCode(Constants.S_MEETING_COMPLETE);
            c.getMeeting().setStatus(stm);
            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_SOURCE_VALIDATION));
            verificationService.createVerification(c);
            caseRepository.save(c);
            caseRepository.saveAndFlush(c);
            List<FieldMeetingSource> listFS = verificationService.createAllFieldVerificationOfImputed(c.getId());
            fieldMeetingSourceRepository.save(listFS);
            //verificationRepository.save(c.getVerification());
            //c.getVerification().setSourceVerifications(verificationService.convertAllInitSourcesVerif(c));
            result.setHasError(false);
            result.setMessage("Entrevista terminada con exito");
            result.setUrlToGo("/index.html");
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"saveProceedingLegal",userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al guardar la información");
        } finally {
            return result;
        }
    }

    public List<String> validateProceedingLegal(CriminalProceedingView cpv, TerminateMeetingMessageDto v) {
        List<String> current = new ArrayList<>(), previous = new ArrayList<>();
        String e = "entity";
        List<String> messageError = new ArrayList<>();
        if (cpv.getListCrime().trim().equals(""))
            current.add("Debe agregar al menos un delito.");
        if (cpv.getHaveCoDEfendant() && cpv.getListCoDefendant().trim().equals(""))
            current.add("Ha marcado que existen coimputados. Por favor agregue los coimputados del caso");
        if (cpv.getPlaceDetention().trim().equals(""))
            current.add(v.template.replace(e,"El lugar de detención"));
        if (cpv.getBehaviorDetention().trim().equals(""))
            current.add(v.template.replace(e,"El comportamiento durante la detención"));
        if (cpv.getNameVictim().trim().equals(""))
            current.add(v.template.replace(e,"El nombre completo de la víctima"));
        if (cpv.getFirstProceeding().trim().equals(""))
            previous.add(v.template.replace(e,"El primer caso "));
        if (cpv.getOpenProcessNumber() == null)
            previous.add(v.template.replace(e,"El número de procesos abiertos"));
        if (cpv.getNumberConvictions() == null)
            previous.add(v.template.replace(e,"El número de sentencias condenatorias"));
        current.addAll(addressService.validateAddress(cpv.getDomicileVictim()));
        v.getGroupMessage().add(new GroupMessageMeetingDto("legalActual",current));
        v.getGroupMessage().add(new GroupMessageMeetingDto("legalPrevious",previous));
        return messageError;
    }


    public boolean validateMeetingUser(Long idCase) {
        Case c = caseRepository.findOne(idCase);
        Long userMeeting = c.getMeeting().getReviewer().getId();
        Long userLogged = userService.GetLoggedUserId();
        if (userLogged != null && userMeeting.equals(userLogged)) {
            return true;
        }
        return false;
    }

    public ResponseMessage messageValidateMeetingUser(Long idCase) {
        if (!validateMeetingUser(idCase)) {
            return new ResponseMessage(true, "Este usuario no tiene permiso para ver la información solicitada");
        }
        return null;
    }

}
