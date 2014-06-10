package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.umeca.model.catalog.*;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.dto.CountryDto;
import com.umeca.model.catalog.dto.ElectionDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.catalog.dto.SchoolLevelDto;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
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
    private CaseRepository caseRepository;
    @Autowired
    private ImputedRepository imputedRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private StatusMeetingRepository statusMeetingRepository;

    @Override
    public Long createMeeting(Imputed imputed) {
        Long result = null;
        try {
            Case caseDetention = new Case();
            if (imputedRepository.findImputedRegister(imputed.getName(), imputed.getLastNameP(), imputed.getLastNameM(), imputed.getDateBirth()).size() > 0)
                caseDetention.setRecidivist(true);
            else
                caseDetention.setRecidivist(false);
            caseDetention.setIdFolder(imputed.getMeeting().getCaseDetention().getIdFolder());
            caseDetention = caseRepository.save(caseDetention);
            Meeting meeting = new Meeting();
            meeting.setCaseDetention(caseDetention);
            StatusMeeting statusMeeting = statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE);
            meeting.setStatus(statusMeeting);
            meeting = meetingRepository.save(meeting);
            imputed.setMeeting(meeting);
            imputedRepository.save(imputed);
            result = caseDetention.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Autowired
    PhysicalConditionRepository physicalConditionRepository;
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
    SchoolLevelRepository schoolLevelRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    CountryRepository countryRepository;

    @Override
    public ModelAndView showMeeting(Long id) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/meeting");
        Gson gson = new Gson();
        ////////////////////Personal data
        Case caseDetention = caseRepository.findOne(id);
        model.addObject("idCase", caseDetention.getId());
        model.addObject("m", caseDetention.getMeeting());
        if (caseDetention.getMeeting().getSocialEnvironment() != null) {
            if (caseDetention.getMeeting().getSocialEnvironment().getActivities() != null) {
                int tam = caseDetention.getMeeting().getSocialEnvironment().getActivities().size();
                Long[] activity = new Long[tam];
                for (int a = 0; a < tam; a++) {
                    activity[a] = caseDetention.getMeeting().getSocialEnvironment().getActivities().get(a).getId();
                }
                model.addObject("activity", gson.toJson(activity));
            }
            if (caseDetention.getMeeting().getSocialEnvironment().getPhysicalConditions() != null) {
                int tam = caseDetention.getMeeting().getSocialEnvironment().getPhysicalConditions().size();
                Long[] physicalCondition = new Long[tam];
                for (int p = 0; p < tam; p++) {
                    physicalCondition[p] = caseDetention.getMeeting().getSocialEnvironment().getPhysicalConditions().get(p).getId();
                }
                model.addObject("physicalCondition", gson.toJson(physicalCondition));
            }
        }
        model.addObject("lstPhysicalCondition", gson.toJson(physicalConditionRepository.findAll()));
        model.addObject("lstActivity", gson.toJson(activityRepository.findAll()));
        model.addObject("lstDayWeek", gson.toJson(dayWeekRepository.findAll()));
        List<Election> lstElection = electionRepository.findAll();
        List<ElectionDto> lstElectionDto = new ArrayList<ElectionDto>();
        for (Election e: lstElection){
            ElectionDto edto= new ElectionDto();
            lstElectionDto.add(edto.dtoElection(e));
        }
        model.addObject("listElection", gson.toJson(lstElectionDto));
        List<SchoolLevel> lstLevel = schoolLevelRepository.findAll();
        List<SchoolLevelDto> listDto = new ArrayList<SchoolLevelDto>();
        for (SchoolLevel s : lstLevel) {
            SchoolLevelDto dtoLevel = new SchoolLevelDto();
            listDto.add(dtoLevel.doDto(s));
        }
        model.addObject("lstLevel", gson.toJson(listDto));
        List<Country> listCountry = countryRepository.findAll();
        List<CountryDto> listCountryDto = new ArrayList<CountryDto>();
        for(Country c: listCountry){
            CountryDto cdto= new CountryDto();
            listCountryDto.add(cdto.dtoCountry(c));
        }
        model.addObject("lstCountry", gson.toJson(listCountryDto));
        if (caseDetention.getMeeting() != null && caseDetention.getMeeting().getSchool() != null) {
            if (caseDetention.getMeeting().getSchool().getGrade() != null) {
                model.addObject("gradeId", gson.toJson(caseDetention.getMeeting().getSchool().getGrade().getId()));
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
        ModelAndView model = new ModelAndView("/reviewer/meeting/legal/index");
        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        Case caseDetention = caseRepository.findOne(id);
        model.addObject("m", caseDetention.getMeeting());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Override
    public ResponseMessage upsertPersonalData(Long idCase, Imputed imputed, SocialEnvironment socialEnvironment, Integer[] physicalCondition, Integer[] activity) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case caseDetention = caseRepository.findOne(idCase);
            imputed.setMeeting(caseDetention.getMeeting());
            socialEnvironment.setMeeting(caseDetention.getMeeting());
            imputed.setId(caseDetention.getMeeting().getImputed().getId());
            if (caseDetention.getMeeting().getSocialEnvironment() != null && caseDetention.getMeeting().getSocialEnvironment().getId() != null) {
                socialEnvironment.setId(caseDetention.getMeeting().getSocialEnvironment().getId());
            }
            imputed.setName(caseDetention.getMeeting().getImputed().getName());
            imputed.setLastNameP(caseDetention.getMeeting().getImputed().getLastNameP());
            imputed.setLastNameM(caseDetention.getMeeting().getImputed().getLastNameM());
            imputed.setDateBirth(caseDetention.getMeeting().getImputed().getDateBirth());
            caseDetention.getMeeting().setImputed(imputed);

            if (imputed.getMaritalStatus() != null && imputed.getMaritalStatus().getId() != null) {
                MaritalStatus maritalStatus = maritalStatusRepository.findOne(imputed.getMaritalStatus().getId());
                imputed.setMaritalStatus(maritalStatus);
            }
            if (physicalCondition != null) {
                socialEnvironment.setPhysicalConditions(new ArrayList<PhysicalCondition>());
                for (int i = 0; i < physicalCondition.length; i++) {
                    socialEnvironment.getPhysicalConditions().add(physicalConditionRepository.findOne((Long.valueOf(physicalCondition[i])+1)));
                }
            }
            if (activity != null) {
                socialEnvironment.setActivities(new ArrayList<Activity>());
                for (int a = 0; a < activity.length; a++) {
                    socialEnvironment.getActivities().add(activityRepository.findOne((Long.valueOf(activity[a])+1)));
                }
            }
            caseDetention.getMeeting().setSocialEnvironment(socialEnvironment);
            caseRepository.saveAndFlush(caseDetention);
            result.setHasError(false);
            result.setMessage("Se ha guardado la información exitosamente");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ha ocurrdio un error al guardar los datos personales. Favor de intentar más tarde" + e.getMessage());
        }
        return result;
    }

    @Autowired
    PersonSocialNetworkRepository personSocialNetworkRepository;

    @Override
    public ModelAndView upsertSocialNetwork(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/socialNetwork/upsert");
        Gson gson = new Gson();
        model.addObject("lstRelationship", gson.toJson(relationshipRepository.findAll()));
        model.addObject("lstElection", gson.toJson(electionRepository.findAll()));
        model.addObject("lstDocumentType", gson.toJson(documentTypeRepository.findAll()));
        if (id != null) {
            PersonSocialNetwork p = personSocialNetworkRepository.findOne(id);
            PersonSocialNetwork pView = new PersonSocialNetwork();
            pView.setId(p.getId());
            pView.setName(p.getName());
            pView.setAge(p.getAge());
            pView.setPhone(p.getPhone());
            model.addObject("p", gson.toJson(pView));
            model.addObject("relId", gson.toJson(p.getRelationship().getId()));
            model.addObject("docId", gson.toJson(p.getDocumentType().getId()));
            model.addObject("depId", gson.toJson(p.getDependent().getId()));
            model.addObject("livId", gson.toJson(p.getLivingWith().getId()));
        }
        model.addObject("idCase", idCase);
        return model;
    }

    @Autowired
    SocialNetworkRepository socialNetworkRepository;

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
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al guardar la persona de red social.");
        }
        return result;
    }

    @Override
    public ResponseMessage deleteSocialNetwork(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            personSocialNetworkRepository.delete(personSocialNetworkRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se elimino la persona de red social exitosamente");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("No se ha podido eliminar la persona de red social. Intente más tarde");
        }
        return result;
    }

    @Autowired
    ReferenceRepository referenceRepository;

    @Override
    public ModelAndView upsertReference(Long id, Long idCase) {
        ModelAndView result = new ModelAndView("/reviewer/meeting/reference/upsert");
        Gson gson = new Gson();
        result.addObject("lstRelationship", gson.toJson(relationshipRepository.findAll()));
        result.addObject("lstElection", gson.toJson(electionRepository.findAll()));
        result.addObject("lstDocumentType", gson.toJson(documentTypeRepository.findAll()));
        if (id != null) {
            Reference reference = referenceRepository.findOne(id);
            result.addObject("r", reference);
            result.addObject("relId", gson.toJson(reference.getRelationship().getId()));
            result.addObject("docId", gson.toJson(reference.getDocumentType().getId()));
        }

        result.addObject("idCase", idCase);
        return result;
    }

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
            result.setHasError(true);
            result.setMessage("Ocurrio un error al guardar la refernecia. Intente más tarde.");
        }
        return result;
    }

    @Override
    public ResponseMessage deleteReference(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            referenceRepository.delete(referenceRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se ha eliminado la referencia exitosamente.");
        } catch (Exception e) {
            result.setHasError(false);
            result.setMessage("Ocurrio un error al eliminar la referencia. Intente más tarde.");
        }
        return result;
    }

    @Autowired
    DrugTypeRepository drugTypeRepository;

    @Autowired
    PeriodicityRepository periodicityRepository;

    @Autowired
    DrugRepository drugRepository;

    @Override
    public ModelAndView upsertDrug(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/drug/upsert");
        Gson gson = new Gson();
        model.addObject("lstDrugType", gson.toJson(drugTypeRepository.findAll()));
        model.addObject("lstPeriodicity", gson.toJson(periodicityRepository.findAll()));
        if (id != null && id != 0) {
            Drug d = drugRepository.findOne(id);
            model.addObject("d", d);
            model.addObject("typeId", d.getDrugType().getId());
            model.addObject("perId", d.getPeriodicity().getId());
        }
        model.addObject("idCase", idCase);
        return model;
    }

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
            result.setHasError(true);
            result.setMessage("Ocorrio un error al guardar la sustancia.Inténte más tarde.");
        }
        return result;
    }

    @Override
    public ResponseMessage deleteDrug(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            drugRepository.delete(drugRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se elimino la sustancia con éxito");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ocurrio un error al eliminar la sustancia. Inténte más tarde");
        }
        return result;
    }

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    GradeRepository gradeRepository;

    @Override
    public ResponseMessage doUpsertSchool(Long id, School school, String schedules) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case caseDetention = caseRepository.findOne(id);
            school.setMeeting(caseDetention.getMeeting());
            if (caseDetention.getMeeting().getSchool() != null) {
                school.setId(caseDetention.getMeeting().getSchool().getId());
            }
            Grade grade = gradeRepository.findOne(school.getGrade().getId());
            school.setGrade(grade);
            schoolRepository.save(school);
            scheduleService.saveSchedules(schedules, id, School.class);
            schoolRepository.saveAndFlush(school);
            result.setHasError(false);
            result.setMessage("Se ha actualizado su información exitosamente");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al actualizar su información" + e.getMessage());
        }
        return result;
    }

    @Autowired
    JobRepository jobRepository;
    @Autowired
    RegisterTypeRepository registerTypeRepository;

    @Override
    public ModelAndView upsertJob(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/job/upsert");
        Gson gson = new Gson();
        model.addObject("lstRegisterType", gson.toJson(registerTypeRepository.findAll()));
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
            Case c = caseRepository.findById(idCase);
            if (job.getId() != null && job.getId() == 0) {
                job.setId(null);
            }
            job.setMeeting(c.getMeeting());
            job.setRegisterType(registerTypeRepository.findOne(job.getRegisterType().getId()));
            jobRepository.save(job);
            if (!job.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                scheduleService.saveSchedules(sch, job.getId(), Job.class);
            }
            result.setHasError(false);
            result.setMessage("Se ha guardado la infomación exitosamente");
        } catch (Exception e) {
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

    @Override
    public ResponseMessage deleteJob(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            jobRepository.delete(id);
            result.setHasError(false);
            result.setMessage("Se ha eliminado correctamente el registro");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al intentar eliminar el registro. Inténte más tarde.");
        }
        return result;
    }

    @Autowired
    DomicileRepository domicileRepository;

    @Override
    public ModelAndView upsertAddress(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/address/upsert");
        try {
            Gson gson = new Gson();
            model.addObject("listRegisterType", gson.toJson(registerTypeRepository.findAll()));
            model.addObject("listElection", gson.toJson(electionRepository.findAll()));
            model.addObject("lstDayWeek", gson.toJson(dayWeekRepository.findAll()));
            model.addObject("idCase", idCase);
            if (id != null && id != 0) {
                Domicile domicile = domicileRepository.findOne(id);
                model.addObject("d", domicile);
                model.addObject("zipCode", domicile.getLocation().getZipCode());
                model.addObject("belongId", domicile.getBelong().getId());
                model.addObject("listSchedule", scheduleService.getSchedules(domicile.getId(), Domicile.class));
                model.addObject("typeId", domicile.getRegisterType().getId());
            }
        } catch (Exception e) {

        }
        return model;
    }

    @Autowired
    LocationRepository locationRepository;

    @Override
    public ResponseMessage doUpsertAddress(Domicile domicile, Long idCase, String sch) {
        ResponseMessage result = new ResponseMessage();
        try {
            if (!domicile.getRegisterType().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                ResponseMessage validate = validateSchedules(sch, "el domicilio");
                if (validate != null) {
                    return validate;
                }
            }
            Case c = caseRepository.findById(idCase);
            if (domicile.getId() != null && domicile.getId() == 0) {
                domicile.setId(null);
            }
            domicile.setMeeting(c.getMeeting());
            domicile.setBelong(electionRepository.findOne(domicile.getBelong().getId()));
            domicile.setRegisterType(registerTypeRepository.findOne(domicile.getRegisterType().getId()));
            domicile.setLocation(locationRepository.findOne(domicile.getLocation().getId()));
            domicile.setDomicile(domicile.toString());
            Domicile newDomicile = domicileRepository.save(domicile);
            if (!newDomicile.getRegisterType().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                scheduleService.saveSchedules(sch, domicile.getId(), Domicile.class);
            }
            result.setHasError(false);
            result.setMessage("Se ha guardado la información con éxito");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al guardar su inormación. Intente más tarde."+e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseMessage deleteAddress(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            domicileRepository.delete(id);
            result.setHasError(false);
            result.setMessage("Se ha eliminado el registro exitosamente");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al eliminar el registro. Inténte más tarde");
        }
        return result;
    }

    @Autowired
    StateRepository stateRepository;

    @Override
    public ResponseMessage upsertLeaveCountry(Long id, LeaveCountry leaveCountry) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case c = caseRepository.findOne(id);
            leaveCountry.setMeeting(c.getMeeting());
            leaveCountry.setOfficialDocumentation(electionRepository.findOne(leaveCountry.getOfficialDocumentation().getId()));
            Long family = leaveCountry.getFamilyAnotherCountry().getId();
            leaveCountry.setFamilyAnotherCountry(electionRepository.findOne(family));
            if(family.equals(Constants.ELECTION_YES)){
            leaveCountry.setCommunicationFamily(electionRepository.findOne(leaveCountry.getCommunicationFamily().getId()));
            }
            Long livedCountry = leaveCountry.getLivedCountry().getId();
            leaveCountry.setLivedCountry(electionRepository.findOne(livedCountry));
            if(livedCountry!=null && livedCountry.equals(Constants.ELECTION_YES)){
                leaveCountry.setCountry(countryRepository.findOne(leaveCountry.getCountry().getId()));
            }
            if(c.getMeeting().getLeaveCountry()!=null && c.getMeeting().getLeaveCountry().getId()!=null){
                leaveCountry.setId(c.getMeeting().getLeaveCountry().getId());
            }
            c.getMeeting().setLeaveCountry(leaveCountry);
            caseRepository.saveAndFlush(c);
            result.setHasError(false);
            result.setMessage("Se ha guardado su información exitosamente");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al guardar la información");
        }
        return result;
    }

    @Override
    public ResponseMessage doTerminateMeeting(Meeting meeting, String sch, Integer[] physicalCondition, Integer[] activity) {
       //valida estado, estado civil, genero
        //un domicilio actual
        //una persona de red social
        //una persona de referencia
        //toda la seccion leving
        return null;
    }

    @Override
    public ResponseMessage validateCreateMeeting(Imputed imputed) {
        if(imputed.getDateBirth()!=null){
            Calendar dob = Calendar.getInstance();
            dob.setTime(imputed.getDateBirth());
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
                age--;
            if(age<18){
                return new ResponseMessage(true, "El imputado debe tener más de 18 años para continuar");
            }
        }else{
            return new ResponseMessage(true, "Favor de ingresar la fecha de nacimiento del imputado.");
        }
        if(imputed.getMeeting()!=null && imputed.getMeeting().getCaseDetention()!=null && imputed.getMeeting().getCaseDetention().getIdFolder()!=null){
            Case c = caseRepository.findByIdFolder(imputed.getMeeting().getCaseDetention().getIdFolder());
            if(c!=null){
                return  new ResponseMessage(true,"El número de carpeta de investigación ya se encuentra registrado.");
            }
        }else{
            return new ResponseMessage(true,"Favor de ingresar el número de carpeta de investigación para continuar");
        }
        return null;
    }
}
