package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.catalog.*;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.dto.AcademicLevelDto;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.catalog.dto.CountryDto;
import com.umeca.model.catalog.dto.ElectionDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.CriminalProceedingView;
import com.umeca.model.entities.reviewer.dto.*;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import com.umeca.model.shared.ConsMessage;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.*;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.catalog.CatalogService;
import com.umeca.service.shared.SharedLogExceptionService;
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
    MaritalStatusRepository maritalStatusRepository;
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
            imputed.setName(imputed.getName().trim());
            imputed.setLastNameP(imputed.getLastNameP().trim());
            imputed.setLastNameM(imputed.getLastNameM().trim());
            if (imputedRepository.findImputedRegister(imputed.getName(), imputed.getLastNameP(), imputed.getLastNameM(), imputed.getBirthDate()).size() > 0)
                caseDetention.setRecidivist(true);
            else
                caseDetention.setRecidivist(false);
            caseDetention.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_MEETING));
            caseDetention.setIdFolder(imputed.getMeeting().getCaseDetention().getIdFolder());
            caseDetention.setDateCreate(new Date());
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
            e.printStackTrace();
            logException.Write(e, this.getClass(), "createMeeting", userService);
        } finally {
            return result;
        }
    }

    @Autowired
    HomeTypeRepository homeTypeRepository;

    @Override
    public ModelAndView showMeeting(Long id) {
        if (!validateMeetingUser(id))
            return new ModelAndView("/reviewer/meeting/index");
        ModelAndView model = new ModelAndView("/reviewer/meeting/meeting");
        Gson gson = new Gson();
        ////////////////////Personal data
        Case caseDetention = caseRepository.findOne(id);
        Meeting m  = caseDetention.getMeeting();
        model.addObject("idCase", caseDetention.getId());
        model.addObject("m", m);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        model.addObject("tStart",format.format(m.getDateCreate()));
        if(m.getDateTerminate()!=null){
            model.addObject("tEnd",format.format(m.getDateTerminate()));
        }else{
            model.addObject("tEnd","sin terminar");
        }
        model.addObject("reviewerFullname",m.getReviewer().getFullname());
        addressService.fillCatalogAddress(model);
        model.addObject("age", userService.calculateAge(caseDetention.getMeeting().getImputed().getBirthDate()));
        if (caseDetention.getMeeting().getSocialEnvironment() != null) {
            if (caseDetention.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities() != null) {
                List<RelActivityObjectDto> listRel = new ArrayList<>();
                for (RelSocialEnvironmentActivity r : caseDetention.getMeeting().getSocialEnvironment().getRelSocialEnvironmentActivities()) {
                    RelActivityObjectDto rNew = new RelActivityObjectDto();
                    listRel.add(rNew.relDto(r));
                }
                model.addObject("activity", gson.toJson(listRel));
            }
        }
        //model.addObject("lstPhysicalCondition", gson.toJson(physicalConditionRepository.findAll()));
        List<CatalogDto> listActivity = new ArrayList<>();
        for (Activity a : activityRepository.findNotObsolete()) {
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
        List<HomeType> lstHomeType = homeTypeRepository.findNotObsolete();
        List<CatalogDto> cdtoList = new ArrayList<>();
        for (HomeType ht : lstHomeType) {
            CatalogDto c = new CatalogDto();
            c.setId(ht.getId());
            c.setName(ht.getName());
            c.setSpecification(ht.getSpecification());
            cdtoList.add(c);
        }
        model.addObject("lstHomeType", gson.toJson(cdtoList));
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
        return model;
    }

    private void userConfigToView(ModelAndView model) {
        Long userId = userService.GetLoggedUserId();
        User u = userRepository.findOne(userId);
        Boolean band = false;
        if (u.getRoles().get(0).getRole().equals(Constants.ROLE_EVALUATION_MANAGER))
            band = true;
        model.addObject("managereval", band);
    }

    @Autowired
    ElectionNotApplyRepository electionNotApplyRepository;

    @Override
    public ModelAndView showLegalProcess(Long id, Integer showCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/legal/index");
        Case c = caseRepository.findOne(id);
        model.addObject("idFolder", c.getIdFolder());
        Imputed i = c.getMeeting().getImputed();
        String fullname= i.getName()+" "+i.getLastNameP()+" "+i.getLastNameM();
        model.addObject("sName", i.getName());
        model.addObject("sLastNameP", i.getLastNameP());
        model.addObject("sLastNameM",  i.getLastNameM());
        model.addObject("fullNameImputed", fullname);
        model.addObject("age", userService.calculateAge(i.getBirthDate()));
        model.addObject("idCase", id);
        addressService.fillCatalogAddress(model);
        Gson gson = new Gson();
        List<Election> lstElection = electionRepository.findAll();
        List<ElectionDto> lstElectionDto = new ArrayList<ElectionDto>();
        for (Election e : lstElection) {
            ElectionDto edto = new ElectionDto();
            lstElectionDto.add(edto.dtoElection(e));
        }
        model.addObject("listElection", gson.toJson(lstElectionDto));
        List<ElectionNotApply> lstElectionNotApply = electionNotApplyRepository.findAll();
        List<ElectionDto> lstElectionNotApplyDto = new ArrayList<ElectionDto>();
        for (ElectionNotApply e : lstElectionNotApply) {
            ElectionDto edto = new ElectionDto();
            lstElectionNotApplyDto.add(edto.dtoElection(e));
        }
        model.addObject("listElectionNotApply", gson.toJson(lstElectionNotApplyDto));
        List<Relationship> relationshipList = relationshipRepository.findNotObsolete();
        List<CatalogDto> catalogDtoList = new ArrayList<>();
        for (Relationship relationship : relationshipList) {
            CatalogDto cdto = new CatalogDto();
            cdto.setId(relationship.getId());
            cdto.setName(relationship.getName());
            cdto.setSpecification(relationship.getSpecification());
            catalogDtoList.add(cdto);
        }
        model.addObject("listRelationship", gson.toJson(catalogDtoList));
        String listLegalBefore =  findLegalBefore(id, c.getMeeting().getImputed().getName(), c.getMeeting().getImputed().getLastNameP(), c.getMeeting().getImputed().getLastNameM());
        model.addObject("listLegalBefore",listLegalBefore);
        CurrentCriminalProceeding ccp = c.getMeeting().getCurrentCriminalProceeding();
        if (ccp != null) {
            List<Crime> listCrime = ccp.getCrimeList();
            List<CrimeDto> listCrimeDto = new ArrayList<>();
            if (listCrime != null) {
                for (Crime crime : listCrime) {
                    listCrimeDto.add(new CrimeDto().dtoCrime(crime));
                }
                model.addObject("listCrime", gson.toJson(listCrimeDto));
            }
            List<CoDefendant> listCoDefendant = ccp.getCoDefendantList();
            Boolean haveCoDependant= false;
            if (listCoDefendant != null) {
                List<CoDefendantDto> listCodefendantDto = new ArrayList<>();
                for (CoDefendant cd : listCoDefendant) {
                    haveCoDependant = true;
                    listCodefendantDto.add(new CoDefendantDto().dtoCoDefendant(cd));
                }
                model.addObject("listCoDependant", gson.toJson(listCodefendantDto));
            }
            model.addObject("haveCoDependant",haveCoDependant);
            if(ccp.getDomicileVictim()!=null){
                addressService.fillModelAddress(model,ccp.getDomicileVictim().getId());
            }
            model.addObject("nameVictim",ccp.getNameVictim());
            if(ccp.getRelationshipVictim()!=null){
                model.addObject("relId",ccp.getRelationshipVictim().getId());
            }
            model.addObject("additionalInfo",ccp.getAdditionalInfo());
            model.addObject("behaviorDetention", ccp.getBehaviorDetention());
            model.addObject("placeDetention",ccp.getPlaceDetention());
        }
        PreviousCriminalProceeding pcp = c.getMeeting().getPreviousCriminalProceeding();
        if(pcp != null){
            model.addObject("firstProceeding",pcp.getFirstProceeding());
            model.addObject("openProcessNumber",pcp.getOpenProcessNumber());
            model.addObject("specificationOpenProcess", pcp.getSpecificationOpenProcess());
            model.addObject("specificationNumberConvictions", pcp.getSpecificationNumberConvictions());
            model.addObject("numberConvictions",pcp.getNumberConvictions());
            if(pcp.getComplyCSPP()!=null){
              model.addObject("complyCSPPId",pcp.getComplyCSPP().getId());
            }
            if(pcp.getComplyPM()!=null){
                model.addObject("complyPMId",pcp.getComplyPM().getId());
            }
            if(pcp.getComplyProcessAbove()!=null){
                model.addObject("complyProcessAboveId", pcp.getComplyProcessAbove().getId());
            }
        }else if(listLegalBefore.equals("[]")){
            model.addObject("firstProceeding","Ninguno");
            model.addObject("openProcessNumber","0");
            model.addObject("numberConvictions","0");
        }
        if(showCase!=null && showCase.equals(1)){
            model.addObject("managereval", true);
        }else {
            userConfigToView(model);
        }
        return model;
    }

    @Autowired
    ArrangementRepository qArrangementRepository;

    @Override
    public ResponseMessage findPreviousCase(String sName, String sLastNameP, String sLastNameM, Long idCase) {
        return new ResponseMessage(false,findLegalBefore(idCase,sName,sLastNameP,sLastNameM));
    }


    String findLegalBefore(Long id, String name, String lastNameP, String lastNameM) {
        String foneticName = sharedUserService.getFoneticByName(name, lastNameP, lastNameM);
        List<FindLegalBefore> list = caseRepository.findLegalBefore(id,foneticName);
        if(list!=null && list.size()>0){
            for(FindLegalBefore flb : list){
                List<SelectList> lstArrangement = qArrangementRepository.findLstArrangementByCaseId(flb.getIdCase());
                if(lstArrangement!=null && lstArrangement.size()>0){
                flb.convertListArrangementToString(lstArrangement);
                }else{
                    flb.setArrangement("Sin medidas cautelares asignadas");
                }
            }
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @Autowired
    SharedUserService sharedUserService;
    @Transactional
    @Override
    public ResponseMessage upsertPersonalData(Long idCase, Imputed imputed, SocialEnvironment socialEnvironment, String activity) {
        ResponseMessage result = new ResponseMessage();
        Case caseDetention = caseRepository.findOne(idCase);
        try {
            refreshPersonalData(imputed, socialEnvironment, activity, caseDetention);
            caseRepository.save(caseDetention);
            caseRepository.saveAndFlush(caseDetention);
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "upsertPersonalData", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
        }
        return result;
    }

    private void refreshPersonalData(Imputed imputed, SocialEnvironment socialEnvironment, String activity, Case caseDetention) {
        Imputed iCase = caseDetention.getMeeting().getImputed();
        SocialEnvironment seCase = caseDetention.getMeeting().getSocialEnvironment();
        Meeting m = caseDetention.getMeeting();
        iCase.setName(imputed.getName());
        iCase.setLastNameP(imputed.getLastNameP());
        iCase.setLastNameM(imputed.getLastNameM());
        iCase.setCelPhone(imputed.getCelPhone());
        iCase.setMaritalStatus(maritalStatusRepository.findOne(imputed.getMaritalStatus().getId()));
        iCase.setGender(imputed.getGender());
        iCase.setBoys(imputed.getBoys());
        iCase.setNickname(imputed.getNickname());
        iCase.setDependentBoys(imputed.getDependentBoys());
        Country country = countryRepository.findOne(imputed.getBirthCountry().getId());
        iCase.setBirthCountry(country);
        if(country.getAlpha2().equals(Constants.ALPHA2_MEXICO)){
            iCase.setBirthState("");
            iCase.setBirthLocation("");
            iCase.setBirthMunicipality("");
            if(imputed.getLocation()!=null && imputed.getLocation().getId() != null){
                iCase.setLocation(locationRepository.findOne(imputed.getLocation().getId()));
            }
        }else{
            iCase.setLocation(null);
            iCase.setBirthState(imputed.getBirthState());
            iCase.setBirthLocation(imputed.getBirthLocation());
            iCase.setBirthMunicipality(imputed.getBirthMunicipality());
        }
        iCase.setYearsMaritalStatus(imputed.getYearsMaritalStatus());
        if (seCase != null && seCase.getId() != null) {
            seCase.setId(seCase.getId());
        } else {
            seCase = socialEnvironment;
            seCase.setMeeting(m);
            m.setSocialEnvironment(seCase);
        }
        seCase.setComment(socialEnvironment.getComment());
        seCase.setPhysicalCondition(socialEnvironment.getPhysicalCondition());
        Gson gson = new Gson();
        if (seCase != null && seCase.getRelSocialEnvironmentActivities() != null) {
            List<RelSocialEnvironmentActivity> relAux = seCase.getRelSocialEnvironmentActivities();
            seCase.setRelSocialEnvironmentActivities(null);
            for (RelSocialEnvironmentActivity r : relAux) {
                r.setSocialEnvironment(null);
                r.setActivity(null);
                rsearRepository.delete(r);
            }
        }
        List<RelSocialEnvironmentActivity> rel = gson.fromJson(activity, new TypeToken<List<RelSocialEnvironmentActivity>>() {
        }.getType());
        if (rel != null) {
            seCase.setRelSocialEnvironmentActivities(new ArrayList<RelSocialEnvironmentActivity>());
            for (RelSocialEnvironmentActivity r : rel) {
                RelSocialEnvironmentActivity newRel = new RelSocialEnvironmentActivity();
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
            pView.setSpecificationRelationship(p.getSpecificationRelationship());
            pView.setBlock(p.getBlock());
            model.addObject("isAccomp",p.getIsAccompaniment());
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
            logException.Write(e, this.getClass(), "doUpsertSocialNetwork", userService);
            result.setHasError(true);
             result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
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
            result.setMessage(ConsMessage.MSG_SUCCESS_DELETE);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteSocialNetwork", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_DELETE);
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
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertReference", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
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
            result.setMessage(ConsMessage.MSG_SUCCESS_DELETE);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteReference", userService);
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_ERROR_DELETE);
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
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertDrug", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
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
            result.setMessage(ConsMessage.MSG_SUCCESS_DELETE);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteDrug", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_DELETE);
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
            refreshSchool(school, caseDetention);
            caseDetention.getMeeting().setCommentSchool(school.getCommentSchool());
            caseRepository.save(caseDetention);
            School s = caseDetention.getMeeting().getSchool();
            if (s == null) {
                s = new School();
            }
            List<Schedule> listToDelete = s.getSchedule();
            s.setSchedule(null);
            if (listToDelete != null) {
                for (Schedule schedule : listToDelete) {
                    schedule.setSchool(null);
                    scheduleRepository.delete(schedule.getId());
                }
            }
            List<Schedule> listSchedules = gson.fromJson(schedules, new TypeToken<List<Schedule>>() {
            }.getType());

            for (Schedule schedule : listSchedules) {
                schedule.setSchool(s);
            }
            scheduleRepository.save(listSchedules);
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertSchool", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
        }
        return result;
    }

    private void refreshSchool(School school, Case caseDetention) {
        try{
        School s = caseDetention.getMeeting().getSchool();
        Meeting m = caseDetention.getMeeting();

        if (s != null) {
            s.setName(school.getName());
            s.setAddress(school.getAddress());
            s.setPhone(school.getPhone());
            s.setBlock(school.getBlock());
            s.setSpecification(school.getSpecification());
            s.setDegree(degreeRepository.findOne(school.getDegree().getId()));
        } else {
            Degree degree = degreeRepository.findOne(school.getDegree().getId());
            school.setDegree(degree);
            m.setSchool(school);
            school.setMeeting(m);
        }
        }catch (Exception e){
            System.out.println();
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
            job = jobRepository.save(job);
            if (!job.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                List<Schedule> listToDelete = job.getSchedule();
                if (listToDelete != null) {
                    job.setSchedule(null);
                    for (Schedule schedule : listToDelete) {
                        schedule.setJob(null);
                        scheduleRepository.delete(schedule.getId());
                    }
                }
                Gson gson = new Gson();
                List<Schedule> listSchedules = gson.fromJson(sch, new TypeToken<List<Schedule>>() {
                }.getType());
                for (Schedule schedule : listSchedules) {
                    schedule.setJob(job);
                }
                scheduleRepository.save(listSchedules);
            }
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertJob", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
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
            result.setMessage(ConsMessage.MSG_SUCCESS_DELETE);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteJob", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_DELETE);
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
            model.addObject("lstHomeType", gson.toJson(homeTypeRepository.findNotObsolete()));
            model.addObject("idCase", idCase);
            addressService.fillCatalogAddress(model);
            if (id != null && id != 0) {

                ImputedHome imputedHome = imputedHomeRepository.findOne(id);
                model.addObject("d", imputedHome);
                if (imputedHome.getAddress() != null) {
                    addressService.fillModelAddress(model, imputedHome.getAddress().getId());
                }
                model.addObject("homeTypeId", imputedHome.getHomeType().getId());
                model.addObject("listSchedule", scheduleService.getSchedules(imputedHome.getId(), ImputedHome.class));
                model.addObject("typeId", imputedHome.getRegisterType().getId());
            }
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "upsertAddress", userService);
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
            imputedHome.setHomeType(homeTypeRepository.findOne(imputedHome.getHomeType().getId()));
            imputedHome.setRegisterType(registerTypeRepository.findOne(imputedHome.getRegisterType().getId()));
            if (imputedHome.getAddress() != null && imputedHome.getAddress().getLocation() != null && imputedHome.getAddress().getLocation().getId() != null) {
                Long locationId = imputedHome.getAddress().getLocation().getId();
                imputedHome.getAddress().setLocation(locationRepository.findOne(locationId));
                imputedHome.getAddress().setAddressString(imputedHome.getAddress().toString());
                imputedHome.setAddress(addressRepository.save(imputedHome.getAddress()));
            }
            ImputedHome newImputedHome = imputedHomeRepository.save(imputedHome);
            if (!newImputedHome.getRegisterType().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                List<Schedule> listToDelete = newImputedHome.getSchedule();
                if (listToDelete != null) {
                    newImputedHome.setSchedule(null);
                    for (Schedule schedule : listToDelete) {
                        schedule.setImputedHome(null);
                        scheduleRepository.delete(schedule.getId());
                    }
                }
                Gson gson = new Gson();
                List<Schedule> listSchedules = gson.fromJson(sch, new TypeToken<List<Schedule>>() {
                }.getType());
                for (Schedule schedule : listSchedules) {
                    schedule.setImputedHome(newImputedHome);
                }
                scheduleRepository.save(listSchedules);
            }
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertAddress", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage deleteAddress(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            ImputedHome imputedHome = imputedHomeRepository.findOne(id);
            Long idAddress = imputedHome.getAddress().getId();
            imputedHomeRepository.delete(id);
            addressRepository.delete(idAddress);
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_SUCCESS_DELETE);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteAddress", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_DELETE);
        }
        return result;
    }

    @Transactional
    @Override
    public ResponseMessage upsertLeaveCountry(Long id, LeaveCountry leaveCountry) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case c = caseRepository.findOne(id);
            c.getMeeting().setCommentCountry(leaveCountry.getCommentCountry());
            refreshLeaveCountry(leaveCountry, c);
            caseRepository.saveAndFlush(c);
            result.setHasError(false);
            result.setMessage(ConsMessage.MSG_SUCCESS_UPSERT);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "upsertLeaveCountry", userService);
            result.setHasError(true);
            result.setMessage(ConsMessage.MSG_ERROR_UPSERT);
        }
        return result;
    }

    private void refreshLeaveCountry(LeaveCountry leaveCountry, Case c) {
        Meeting m = c.getMeeting();
        LeaveCountry l = c.getMeeting().getLeaveCountry();
        if (l == null) {
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
            Meeting m = c.getMeeting();
            m.setCommentCountry(meeting.getLeaveCountry().getCommentCountry());
            m.setCommentSchool(meeting.getSchool().getCommentSchool());
            m.setCommentHome(meeting.getCommentHome());
            m.setCommentReference(meeting.getCommentReference());
            m.setCommentJob(meeting.getCommentJob());
            m.setCommentDrug(meeting.getCommentDrug());
            SocialNetwork sn = m.getSocialNetwork();
            if(sn==null){
                sn = new SocialNetwork();
                sn.setMeeting(m);
                m.setSocialNetwork(sn);
            }
            sn.setComment(meeting.getSocialNetwork().getComment());
            Gson gson = new Gson();
            refreshPersonalData(meeting.getImputed(), meeting.getSocialEnvironment(), activities, c);
            refreshSchool(meeting.getSchool(), c);
            List<Schedule> listToDelete = c.getMeeting().getSchool().getSchedule();
            m.getSchool().setSchedule(null);
            if (listToDelete != null) {
                for (Schedule schedule : listToDelete) {
                    schedule.setSchool(null);
                    scheduleRepository.delete(schedule.getId());
                }
            }
            List<Schedule> listSchedules = gson.fromJson(sch, new TypeToken<List<Schedule>>() {
            }.getType());
            School s = m.getSchool();
            for (Schedule schedule : listSchedules) {
                schedule.setSchool(s);
            }
            scheduleRepository.save(listSchedules);


            refreshLeaveCountry(meeting.getLeaveCountry(), c);

            caseRepository.save(c);
            TerminateMeetingMessageDto validate = new TerminateMeetingMessageDto();
           m.getImputed().validateMeeting(validate);
            if(m.getSocialEnvironment()==null){
                m.setSocialEnvironment(new SocialEnvironment());
            }
           m.getSocialEnvironment().validateMeeting(validate);
            if (m.getSchool() == null) {
                m.setSchool(new School());
            }
            m.getSchool().validateMeeting(validate);
            if (m.getLeaveCountry() == null) {
               m.setLeaveCountry(new LeaveCountry());
            }
            m.getLeaveCountry().validateMeeting(validate);
            m.validateMeeting(validate);
            if (validate .existsMessageProperties()) {
                List<String> listGeneral = new ArrayList<>();
                listGeneral.add(sharedUserService.convertToValidString("No se puede terminar la entrevista puesto que falta por responder preguntas, para más detalles revise los mensajes de cada sección"));
                validate.getGroupMessage().add(new GroupMessageMeetingDto("general", listGeneral));
                validate.formatMessages(sharedUserService);
                return new ResponseMessage(true, gson.toJson(validate));
            }
            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_MEETING));
            m.setStatus(statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE_LEGAL));
            m.setDateTerminate(new Date());
            caseRepository.save(c);
            result.setHasError(false);
            result.setMessage("Entrevista terminada con exito");
            result.setUrlToGo("/index.html");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doTerminateMeeting", userService);
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
                return new ResponseMessage(true, "El imputado debe tener m&aacute;s de 18 a&ntilde;os para continuar");
            }
        } else {
            return new ResponseMessage(true, "Favor de ingresar la fecha de nacimiento del imputado.");
        }
        if (imputed.getMeeting() != null && imputed.getMeeting().getCaseDetention() != null && imputed.getMeeting().getCaseDetention().getIdFolder() != null) {
            List<Case> c = caseRepository.findByIdFolder(imputed.getMeeting().getCaseDetention().getIdFolder());
                if(c!=null && c.size()>0){
                    for(Case cAux : c){
                        Imputed iCase = cAux.getMeeting().getImputed();
                        Long iCaseBD = iCase.getBirthDate().getTime();
                        if (iCase.getFoneticString().equals(imputed.getFoneticString())&& iCaseBD.equals(imputed.getBirthDate().getTime())) {
                            return new ResponseMessage(true, "El n&uacute;mero de carpeta de investigaci&oacute;n y el imputado ya se encuentran registrado.");
                        }
                    }
                }
        } else {
            return new ResponseMessage(true, "Favor de ingresar el n&uacute;mero de carpeta de investigaci&oacute;n para continuar");
        }
        return null;
    }

    public void refreshPreviousProceeding(CriminalProceedingView cpv, Case c){
        Meeting m = c.getMeeting();
        PreviousCriminalProceeding pcpc = m.getPreviousCriminalProceeding();
        if( pcpc == null){
            pcpc = new PreviousCriminalProceeding();
            pcpc.setMeeting(m);
            m.setPreviousCriminalProceeding(pcpc);
        }
        pcpc.setFirstProceeding(cpv.getFirstProceeding());
        pcpc.setOpenProcessNumber(cpv.getOpenProcessNumber());
        pcpc.setNumberConvictions(cpv.getNumberConvictions());
        pcpc.setSpecificationNumberConvictions(cpv.getSpecificationNumberConvictions());
        pcpc.setSpecificationOpenProcess(cpv.getSpecificationOpenProcess());
        pcpc.setComplyPM(electionNotApplyRepository.findOne(cpv.getComplyPMId()));
        pcpc.setComplyCSPP(electionNotApplyRepository.findOne(cpv.getComplyCSPPId()));
        pcpc.setComplyProcessAbove(electionNotApplyRepository.findOne(cpv.getComplyProcessAboveId()));

    }

    @Autowired
    CrimeRepository crimeRepository;

    @Autowired
    CoDefendantRepository coDefendantRepository;


    public void refreshCurrentProceeding(CriminalProceedingView cpv, Case c){
        Meeting m = c.getMeeting();
        CurrentCriminalProceeding ccpc = m.getCurrentCriminalProceeding();
        Address av;
        if( ccpc == null){
            ccpc = new CurrentCriminalProceeding();
            av = new Address();
            m.setCurrentCriminalProceeding(ccpc);
            ccpc.setMeeting(m);
        }else{
            av = ccpc.getDomicileVictim();
        }
        if(cpv.getDomicileVictim().getLocation().getId() != null){
            Long locationId = cpv.getDomicileVictim().getLocation().getId();
            av.setLocation(locationRepository.findOne(locationId));
        }
        av.setStreet(cpv.getDomicileVictim().getStreet());
        av.setInnNum(cpv.getDomicileVictim().getInnNum());
        av.setOutNum(cpv.getDomicileVictim().getOutNum());
        av.setAddressString(av.toString());
        av = addressRepository.save(av);
        ccpc.setMeeting(c.getMeeting());
        ccpc.setBehaviorDetention(cpv.getBehaviorDetention());
        ccpc.setDomicileVictim(av);
        ccpc.setNameVictim(cpv.getNameVictim());
        ccpc.setAdditionalInfo(cpv.getAdditionalInfo());
        List<Crime> listOldCrime = ccpc.getCrimeList();
        if(listOldCrime != null ){
            crimeRepository.delete(listOldCrime);
        }
        if(cpv.getListCrime()!= null && !cpv.getListCrime().equals("")){
            ccpc.setCrimeList(legalService.generateCrime(cpv.getListCrime(), ccpc));
        }
        List<CoDefendant> listOldCoDefendant  = ccpc.getCoDefendantList();
        if(listOldCoDefendant != null){
            coDefendantRepository.delete(listOldCoDefendant);
        }
        if (cpv.getListCoDefendant() != null && !cpv.getListCoDefendant().equals("")) {
            ccpc.setCoDefendantList(legalService.getnerateCoDefendant(cpv.getListCoDefendant(), ccpc));
        }
        if(cpv.getRelVictimId()!=null){
            ccpc.setRelationshipVictim(relationshipRepository.findOne(cpv.getRelVictimId()));
        }
        ccpc.setPlaceDetention(cpv.getPlaceDetention());
    }

    @Transactional
    @Override
    public ResponseMessage savePartialPrevious(CriminalProceedingView cpv) {
        try{
        Case c = caseRepository.findOne(cpv.getIdCase());
        refreshPreviousProceeding(cpv, c);
        caseRepository.save(c);
            return new ResponseMessage(false, ConsMessage.MSG_SUCCESS_UPSERT,"previous");
        }catch (Exception e){
            logException.Write(e, this.getClass(), "savePartialPrevious", userService);
            return new ResponseMessage(true,ConsMessage.MSG_ERROR_UPSERT);
        }
    }

    @Transactional
    @Override
    public ResponseMessage savePartialCurrent(CriminalProceedingView cpv) {
        try{
            Case c = caseRepository.findOne(cpv.getIdCase());
            refreshCurrentProceeding(cpv, c);
            caseRepository.save(c);
            return new ResponseMessage(false,ConsMessage.MSG_SUCCESS_UPSERT, "current");
        }catch (Exception e){
            logException.Write(e, this.getClass(), "savePartialPrevious", userService);
            return new ResponseMessage(true,ConsMessage.MSG_ERROR_UPSERT);
        }
    }

    @Override
    @Transactional
    public ResponseMessage upsertComment(Long idCase, String comment, Integer commentType) {
        try{
            Case c  = caseRepository.findOne(idCase);
            if ( c == null)
                return new ResponseMessage(true,"Ocurri&oacute; un error al guardar");
            switch (commentType){
                case 2: //Domicilios
                    c.getMeeting().setCommentHome(comment);
                    break;
                case 3:   //Red social
                    Meeting m = c.getMeeting();
                    SocialNetwork s = m.getSocialNetwork();
                    if(s== null){
                        s = new SocialNetwork();
                        s.setMeeting(m);
                        m.setSocialNetwork(s);
                    }
                    s.setComment(comment);
                    break;
                case 4: //Referencias
                    c.getMeeting().setCommentReference(comment);
                    break;
                case 5: //Trabajo
                    c.getMeeting().setCommentJob(comment);
                    break;
                case 6: //Escuela
                    c.getMeeting().setCommentSchool(comment);
                    break;
                case 7: //Substancias
                    c.getMeeting().setCommentDrug(comment);
                    break;
                case 8: //acilidad de abandonar el pais
                    c.getMeeting().setCommentCountry(comment);
                    break;
            }
            caseRepository.save(c);
            return new ResponseMessage(false,ConsMessage.MSG_SUCCESS_UPSERT);
        }catch (Exception e){
            return new ResponseMessage(true,ConsMessage.MSG_ERROR_UPSERT);
        }
    }

    @Autowired
    RequestTypeRepository requestTypeRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CaseRequestRepository caseRequestRepository;
    @Autowired
    ResponseTypeRepository responseTypeRepository;
    @Override
    @Transactional
    public ResponseMessage saveProceedingLegal(CriminalProceedingView cpv) {
        ResponseMessage result = new ResponseMessage();
        try {
            TerminateMeetingMessageDto validate = new TerminateMeetingMessageDto();
            validateProceedingLegal(cpv, validate);
            if (validate.existsMessageProperties()) {
                Gson gson = new Gson();
                List<String> listGeneral = new ArrayList<>();
                listGeneral.add(sharedUserService.convertToValidString("No se puede guardar la información legal puesto que falta por responder preguntas, para más detalles revise los mensajes de cada sección"));
                validate.getGroupMessage().add(new GroupMessageMeetingDto("general", listGeneral));
                result.setHasError(true);
                validate.formatMessages(sharedUserService);
                result.setMessage(gson.toJson(validate));
                return result;
            }
            Case c = caseRepository.findOne(cpv.getIdCase());
            refreshCurrentProceeding(cpv,c);
            refreshPreviousProceeding(cpv,c);
            StatusMeeting stm = statusMeetingRepository.findByCode(Constants.S_MEETING_COMPLETE);
            c.getMeeting().setStatus(stm);
            c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_SOURCE_VALIDATION));
            verificationService.createVerification(c);
            caseRepository.save(c);
            caseRepository.saveAndFlush(c);
            List<FieldMeetingSource> listFS = verificationService.createAllFieldVerificationOfImputed(c.getId());
            fieldMeetingSourceRepository.save(listFS);
            //add request to authorize sources
            Gson gson = new Gson();
            CaseRequest caseRequest = new CaseRequest();
            Message requestMessage = new Message();
            Long userId = userService.GetLoggedUserId();
            List<SelectList> usersReceiver = userRepository.getLstValidUsersByRole(Constants.ROLE_EVALUATION_MANAGER);
            User userSender = userRepository.findOne(userId);
            RequestType requestType = requestTypeRepository.findByCode(Constants.ST_REQUEST_AUTHORIZE_SOURCE);
            caseRequest.setRequestType(requestType);
            requestMessage.setSender(userSender);
            if(usersReceiver!= null && usersReceiver.size()>0){
                Message m = new Message();
                m.setText("Se termina de capturar informaci&oacute;n legal, se solicita autorizar fuentes.");
                m.setCaseDetention(c);
                m.setSender(userSender);
                List<RelMessageUserReceiver> listrmur = new ArrayList<>();
                User managerEval = new User();
                for(SelectList ur : usersReceiver){
                    User u= userRepository.findOne(ur.getId());
                    RelMessageUserReceiver rmr = new RelMessageUserReceiver();
                    rmr.setUser(u);
                    rmr.setMessage(m);
                    listrmur.add(rmr);
                    managerEval = u;
                }
                m.setMessageUserReceivers(listrmur);
                m.setCreationDate(new Date());
                requestMessage.setMessageUserReceivers(listrmur);
                m = messageRepository.save(m);
                caseRequest.setRequestMessage(m);
                caseRequest.setResponseType(responseTypeRepository.findByCode(Constants.RESPONSE_TYPE_PENDING));
                caseRequestRepository.save(caseRequest);
            }
            ///////////////////////////////////////////
            result.setHasError(false);
            result.setTitle("redirect");
            result.setMessage("Entrevista terminada con exito");
            result.setUrlToGo("/index.html");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveProceedingLegal", userService);
            result.setHasError(true);
            result.setMessage(sharedUserService.convertToValidString("Ha ocurrido un error al guardar la información"));
        } finally {
            return result;
        }
    }

    @Override
    public ResponseMessage upsertSocialNetworkComment(String comment, Long idCase) {
        try{
            SocialNetwork socialNetwork= socialNetworkRepository.findByCase(idCase);
            if(socialNetwork==null){
                socialNetwork = new SocialNetwork();
                Case c = caseRepository.findOne(idCase);
                socialNetwork.setMeeting(c.getMeeting());
            }
            socialNetwork.setComment(comment);
            socialNetworkRepository.save(socialNetwork);
            return new ResponseMessage(false, "Se ha guardado su información exitosamente");
        }catch (Exception e){
            logException.Write(e, this.getClass(), "upsertSocialNetworkComment", userService);
            return new ResponseMessage(true,"Ha ocurrido un error al terminar la entrevista. Intente más tarde");
        }
    }



    public List<String> validateProceedingLegal(CriminalProceedingView cpv, TerminateMeetingMessageDto v) {
        List<String> current = new ArrayList<>(), previous = new ArrayList<>();
        String e = "entity";
        List<String> messageError = new ArrayList<>();
        if (cpv.getListCrime().trim().equals("[]"))
            current.add("Debe agregar al menos un delito.");
        if (cpv.getHaveCoDependant() && cpv.getListCoDefendant().trim().equals(""))
            current.add("Ha marcado que existen coimputados. Por favor agregue los coimputados del caso");
        if (cpv.getPlaceDetention().trim().equals(""))
            current.add(sharedUserService.convertToValidString(v.template.replace(e,"El lugar de detención")));
        if (cpv.getBehaviorDetention().trim().equals(""))
            current.add(sharedUserService.convertToValidString(v.template.replace(e, "El comportamiento durante la detención")));
        if (cpv.getNameVictim().trim().equals(""))
            current.add(sharedUserService.convertToValidString(v.template.replace(e, "El nombre completo de la víctima")));
        if (cpv.getFirstProceeding().trim().equals(""))
            previous.add(sharedUserService.convertToValidString(v.template.replace(e, "El primer caso ")));
        if (cpv.getOpenProcessNumber() == null)
            previous.add(sharedUserService.convertToValidString(v.template.replace(e, "El número de procesos abiertos")));
        if (cpv.getNumberConvictions() == null)
            previous.add(sharedUserService.convertToValidString(v.template.replace(e, "El número de sentencias condenatorias")));
        current.addAll(addressService.validateAddress(cpv.getDomicileVictim()));
        v.getGroupMessage().add(new GroupMessageMeetingDto("legalActual", current));
        v.getGroupMessage().add(new GroupMessageMeetingDto("legalPrevious", previous));
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
            return new ResponseMessage(true, sharedUserService.convertToValidString("Este usuario no tiene permiso para ver la información solicitada"));
        }
        return null;
    }

}
