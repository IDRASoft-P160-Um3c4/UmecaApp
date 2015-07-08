package com.umeca.service.tablet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.catalog.*;
import com.umeca.model.dto.tablet.*;
import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.shared.LogCase;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.*;
import com.umeca.repository.shared.LogCaseRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("tabletService")
public class TabletServiceImpl implements TabletService {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    ImputedRepository imputedRepository;

    @Autowired
    SocialNetworkRepository socialNetworkRepository;

    @Autowired
    PersonSocialNetworkRepository personSocialNetworkRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    SocialEnvironmentRepository socialEnvironmentRepository;

    @Autowired
    LeaveCountryRepository leaveCountryRepository;

    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    ImputedHomeRepository imputedHomeRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    SourceVerificationRepository sourceVerificationRepository;

    @Autowired
    StatusMeetingRepository statusMeetingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationMethodRepository verificationMethodRepository;

    @Autowired
    StatusFieldVerificationRepository statusFieldVerificationRepository;

    @Autowired
    FieldVerificationRepository fieldVerificationRepository;

    @Autowired
    VerificationRepository verificationRepository;

    @Autowired
    RelSocialEnvironmentActivityRepository relSocialEnvironmentActivityRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    LogCaseRepository logCaseRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    CrimeCatalogRepository crimeCatalogRepository;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Autowired
    HearingFormatService hearingFormatService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");

    private TabletCaseDto getCaseDataByCaseId(Long caseId) {
        return caseRepository.getInfoCaseByCaseId(caseId);
    }

    private TabletStatusCaseDto getStatusCaseByCaseId(Long caseId) {
        return caseRepository.getStatusCaseByCaseId(caseId);
    }

    private TabletImputedDto getImputedDataByCaseId(Long caseId) {
        TabletImputedDto currentImputed = caseRepository.getImputedDataByCaseId(caseId);
        currentImputed.setMaritalStatus(caseRepository.getImputedMaritalStatusByCaseId(caseId));
        currentImputed.setBirthCountry(caseRepository.getImputedBirthCountryByCaseId(caseId));
        currentImputed.setLocation(caseRepository.getImputedLocationByCaseId(caseId));
        return currentImputed;
    }

    private TabletSocialNetworkDto getSocialNetworkByCaseId(Long caseId) {
        TabletSocialNetworkDto currentSN = caseRepository.getSocialNetworkByCaseId(caseId);

        if (currentSN != null) {
            List<TabletPersonSocialNetworkDto> lst = caseRepository.getPeopleSocialNetworkByCaseId(caseId);

            for (TabletPersonSocialNetworkDto curr : lst) {
                curr.setRelationship(caseRepository.getRelationshipByPersonId(curr.getId()));
                curr.setDocumentType(caseRepository.getDocumentTypeByPersonId(curr.getId()));
                curr.setDependent(caseRepository.getDependentByPersonId(curr.getId()));
                curr.setLivingWith(caseRepository.getLivingWithByPersonId(curr.getId()));
            }

            currentSN.setPeopleSocialNetwork(lst);
        }

        return currentSN;
    }

    private TabletSchoolDto getSchoolByCaseId(Long caseId) {

        TabletSchoolDto currSch = caseRepository.getSchoolByCaseId(caseId);
        if (currSch != null) {
            currSch.setDegree(caseRepository.getDegreeBySchoolId(currSch.getId()));
            currSch.setSchedule(caseRepository.getScheduleSchoolId(currSch.getId()));
        }

        return currSch;
    }

    private TabletSocialEnvironmentDto getSocialEnvironmentByCaseId(Long caseId) {
        TabletSocialEnvironmentDto se = caseRepository.getSocialEnvironmentByCaseId(caseId);

        if (se != null) {
            se.setRelSocialEnvironmentActivities(caseRepository.getRelSocialEnvironmentActivityBySocialEnvId(se.getId()));
        }

        return se;
    }

    private List<TabletImputedHomeDto> getImputedHomeByCaseId(Long caseId) {
        List<TabletImputedHomeDto> lst = caseRepository.getImputedHomeByCaseId(caseId);

        if (lst != null && lst.size() > 0) {
            for (TabletImputedHomeDto currIH : lst) {
                currIH.setSchedule(caseRepository.getScheduleByImputedHomeId(currIH.getId()));
            }
        }
        return lst;
    }

    private List<TabletJobDto> getJobByCaseId(Long caseId) {
        List<TabletJobDto> lst = caseRepository.getJobByCaseId(caseId);

        if (lst != null && lst.size() > 0) {
            for (TabletJobDto currJ : lst) {
                currJ.setSchedule(caseRepository.getScheduleByJobId(currJ.getId()));
            }
        }
        return lst;
    }

    private TabletVerificationDto getVerificationByCaseId(Long caseId) {
        TabletVerificationDto currVer = caseRepository.getVerificationByCaseId(caseId);

        if (currVer != null) {
            List<TabletSourceVerificationDto> lst = caseRepository.getSourceVerificationByVerificationId(currVer.getId());

            if (lst != null && lst.size() > 0) {
                for (TabletSourceVerificationDto currSV : lst) {
                    currSV.setFieldMeetingSourceList(caseRepository.getFieldMeetingSourceBySourceId(currSV.getId()));
                }
            }

            currVer.setSourceVerifications(lst);
        }

        return currVer;
    }

    private TabletMeetingDto getMeetingDataByCaseId(Long caseId) {

        TabletMeetingDto currentMeeting = caseRepository.getMeetingDataByCaseId(caseId);
        currentMeeting.setStatus(caseRepository.getStatusMeetingDataByCaseId(caseId));
        currentMeeting.setReviewer(caseRepository.getUserMeetingDataByCaseId(caseId));
        currentMeeting.setImputed(this.getImputedDataByCaseId(caseId));
        currentMeeting.setSocialNetwork(this.getSocialNetworkByCaseId(caseId));
        currentMeeting.setSchool(this.getSchoolByCaseId(caseId));
        currentMeeting.setSocialEnvironment(this.getSocialEnvironmentByCaseId(caseId));
        currentMeeting.setLeaveCountry(caseRepository.getLeaveCountryByCaseId(caseId));
        currentMeeting.setReferences(caseRepository.getReferencesByCaseId(caseId));
        currentMeeting.setImputedHomes(this.getImputedHomeByCaseId(caseId));
        currentMeeting.setJobs(this.getJobByCaseId(caseId));
        currentMeeting.setDrugs(caseRepository.getDrugByCaseId(caseId));

        return currentMeeting;
    }

    private List<TabletHearingFormatDto> getHearingFormatByCaseId(Long caseId) {
        List<TabletHearingFormatDto> lstHF = caseRepository.getLastHearingFormatByCaseId(caseId, new PageRequest(0, 1));

        if (lstHF != null && lstHF.size() > 0) {
            TabletHearingFormatDto currHF = lstHF.get(0);
            currHF.setAssignedArrangements(caseRepository.getAssignedArrangementByFormatId(currHF.getId()));
            currHF.setContacts(caseRepository.getContactsByFormatId(currHF.getId()));
            currHF.setCrimeList(caseRepository.getCrimesByFormatId(currHF.getId()));
        } else {
            HearingFormatView hfv = hearingFormatService.fillNewHearingFormatForView(caseId);
            TabletHearingFormatDto hf = new TabletHearingFormatDto(hfv);

            if (hfv.getIdAddres() != null) {
                TabletAddressDto tabletAddressDto = new TabletAddressDto(addressRepository.findOne(hfv.getIdAddres()));
                hf.getHearingImputed().setAddress(tabletAddressDto);
            }

            if (hfv.getListCrime() != null) {
                List<TabletCrimeDto> lstCrime = new Gson().fromJson(hfv.getListCrime(), new TypeToken<List<TabletCrimeDto>>() {
                }.getType());

                hf.setCrimeList(lstCrime);
            }

            lstHF.add(hf);
        }
        return lstHF;
    }

    //obtiene toda la informacion del caso en DTOS para enviarlos a la tableta
    public TabletCaseDto getAllCaseByIdCase(Long idCase, String assigmentType) {
        try {
            TabletCaseDto currentCase = this.getCaseDataByCaseId(idCase);
            currentCase.setStatus(this.getStatusCaseByCaseId(idCase));
            currentCase.setMeeting(this.getMeetingDataByCaseId(idCase));
            if (assigmentType == Constants.VERIFICATION_ASSIGNMENT_TYPE) {
                currentCase.setVerification(this.getVerificationByCaseId(idCase));
            } else if (assigmentType == Constants.HEARING_FORMAT_ASSIGNMENT_TYPE) {
                currentCase.setHearingFormats(this.getHearingFormatByCaseId(idCase));
            }
            return currentCase;
        } catch (Exception e) {
            System.out.println("error al obtener la info del caso");
            return null;
        }
    }

    private Case mergeCase(TabletCaseDto tabletCase) {
        Case webCase;

        if (tabletCase.getWebId() != null) {
            webCase = caseRepository.findOne(tabletCase.getWebId());
        } else {
            webCase = new Case();
        }

        webCase.setIdFolder(tabletCase.getIdFolder());
        webCase.setIdMP(tabletCase.getIdMP());
        webCase.setRecidivist(tabletCase.getRecidivist());

        try {
            webCase.setDateNotProsecute(tabletCase.getDateNotProsecute() == null ? null : sdf.parse(tabletCase.getDateNotProsecute()));
            webCase.setDateObsolete(tabletCase.getDateObsolete() == null ? null : sdf.parse(tabletCase.getDateObsolete()));
            webCase.setDateCreate(tabletCase.getDateCreate() == null ? null : sdf.parse(tabletCase.getDateCreate()));
        } catch (Exception e) {
            System.out.println("Error al parsear fechas del caso");
            return null;
        }

        webCase.setStatus(statusCaseRepository.findOne(webCase.getStatus().getId()));

        return webCase;
    }

    private Imputed mergeImputed(TabletImputedDto tabletImputed) {
        Imputed webImputed;

        if (tabletImputed.getWebId() != null) {
            webImputed = imputedRepository.findOne(tabletImputed.getWebId());
        } else {
            webImputed = new Imputed();
        }

        webImputed.setName(tabletImputed.getName());
        webImputed.setLastNameP(tabletImputed.getLastNameP());
        webImputed.setLastNameM(tabletImputed.getLastNameM());
        webImputed.setFoneticString(tabletImputed.getFoneticString());
        webImputed.setGender(tabletImputed.getGender());
        try {
            webImputed.setBirthDate(tabletImputed.getBirthDate() == null ? null : sdf.parse(tabletImputed.getBirthDate()));
        } catch (Exception e) {
            System.out.println("error al parsear la fecha de nacimiento de imputado");
            return null;
        }
        webImputed.setCelPhone(tabletImputed.getCelPhone());
        webImputed.setYearsMaritalStatus(tabletImputed.getYearsMaritalStatus());
        webImputed.setBoys(tabletImputed.getBoys());
        webImputed.setDependentBoys(tabletImputed.getDependentBoys());
        webImputed.setBirthMunicipality(tabletImputed.getBirthMunicipality());
        webImputed.setBirthState(tabletImputed.getBirthState());
        webImputed.setBirthLocation(tabletImputed.getBirthLocation());
        webImputed.setNickname(tabletImputed.getNickname());

        MaritalStatus mst = new MaritalStatus();
        mst.setId(webImputed.getMaritalStatus().getId());
        webImputed.setMaritalStatus(mst);

        if (tabletImputed.getBirthCountry() != null) {
            Country bc = new Country();
            bc.setId(tabletImputed.getBirthCountry().getId());
            webImputed.setBirthCountry(bc);
        }

        if (tabletImputed.getLocation() != null) {
            Location l = new Location();
            l.setId(tabletImputed.getLocation().getId());
            webImputed.setLocation(l);
        }

        return webImputed;
    }

    private Meeting mergeMeeting(TabletMeetingDto tabletMeeting) {
        Meeting webMeeting;

        if (tabletMeeting.getWebId() != null) {
            webMeeting = meetingRepository.findOne(tabletMeeting.getWebId());
        } else {
            webMeeting = new Meeting();
        }

        webMeeting.setMeetingType(tabletMeeting.getMeetingType());
        webMeeting.setCommentReference(tabletMeeting.getCommentReference());
        webMeeting.setCommentJob(tabletMeeting.getCommentJob());
        webMeeting.setCommentSchool(tabletMeeting.getCommentSchool());
        webMeeting.setCommentCountry(tabletMeeting.getCommentCountry());
        webMeeting.setCommentHome(tabletMeeting.getCommentHome());
        webMeeting.setCommentDrug(tabletMeeting.getCommentDrug());

        try {
            webMeeting.setDateCreate(tabletMeeting.getDateCreate() == null ? null : sdf.parse(tabletMeeting.getDateCreate()));
            webMeeting.setDateTerminate(tabletMeeting.getDateTerminate() == null ? null : sdf.parse(tabletMeeting.getDateTerminate()));
        } catch (Exception e) {
            System.out.println("Error al parsear fechas del caso");
            return null;
        }

        webMeeting.setStatus(statusMeetingRepository.findOne(tabletMeeting.getStatus().getId()));
        webMeeting.setReviewer(userRepository.findOne(tabletMeeting.getReviewer().getId()));

        return webMeeting;
    }

    private SocialNetwork mergeSocialNetwork(Meeting m, TabletSocialNetworkDto tabletSN) {
        SocialNetwork webSN;
        if (tabletSN.getWebId() != null) {
            webSN = socialNetworkRepository.findOne(tabletSN.getWebId());
        } else {
            webSN = new SocialNetwork();
        }

        webSN.setComment(tabletSN.getComment());

        List<PersonSocialNetwork> lstPSN = lstPSN = new ArrayList<>();

        for (TabletPersonSocialNetworkDto tabletPerson : tabletSN.getPeopleSocialNetwork()) {
            PersonSocialNetwork psn = this.mergePersonSN(tabletPerson);
            psn.setSocialNetwork(webSN);
            lstPSN.add(psn);
        }

        webSN.setPeopleSocialNetwork(lstPSN);
        webSN.setMeeting(m);

        return webSN;
    }

    private PersonSocialNetwork mergePersonSN(TabletPersonSocialNetworkDto tabletPerson) {
        PersonSocialNetwork webPerson;
        if (tabletPerson.getWebId() != null) {
            webPerson = personSocialNetworkRepository.findOne(tabletPerson.getWebId());
        } else {
            webPerson = new PersonSocialNetwork();
        }

        webPerson.setName(tabletPerson.getName());
        webPerson.setAge(tabletPerson.getAge());
        webPerson.setPhone(tabletPerson.getPhone());
        webPerson.setAddress(tabletPerson.getAddress());
        webPerson.setSpecification(tabletPerson.getSpecification());
        webPerson.setIsAccompaniment(tabletPerson.getIsAccompaniment());
        webPerson.setSpecificationRelationship(tabletPerson.getSpecificationRelationship());
        webPerson.setBlock(tabletPerson.getBlock());

        Relationship r = new Relationship();
        r.setId(tabletPerson.getRelationship().getId());
        webPerson.setRelationship(r);

        DocumentType dt = new DocumentType();
        dt.setId(tabletPerson.getDocumentType().getId());
        webPerson.setDocumentType(dt);

        Election dep = new Election();
        dep.setId(tabletPerson.getDependent().getId());
        webPerson.setDependent(dep);

        Election lw = new Election();
        lw.setId(tabletPerson.getLivingWith().getId());
        webPerson.setLivingWith(lw);

        return webPerson;
    }

    private School mergeSchool(Meeting m, TabletSchoolDto tabletSchool) {
        School webSchool;

        if (tabletSchool.getWebId() != null) {
            webSchool = schoolRepository.findOne(tabletSchool.getWebId());
        } else {
            webSchool = new School();
        }

        webSchool.setName(tabletSchool.getName());
        webSchool.setPhone(tabletSchool.getPhone());
        webSchool.setAddress(tabletSchool.getAddress());
        webSchool.setSpecification(tabletSchool.getSpecification());
        webSchool.setBlock(tabletSchool.getBlock());

        Degree d = new Degree();
        d.setId(tabletSchool.getDegree().getId());
        webSchool.setDegree(d);

        List<Schedule> lstSch = webSchool.getSchedule();
        webSchool.setSchedule(null);

        if (lstSch != null && lstSch.size() > 0) {
            for (Schedule s : lstSch) {
                s.setSchool(null);
                scheduleRepository.delete(s);
            }
        }

        lstSch = new ArrayList<>();

        for (TabletScheduleDto tabletSchedule : tabletSchool.getSchedule()) {
            Schedule schedule = new Schedule();
            schedule.setDay(tabletSchedule.getDay());
            schedule.setStart(tabletSchedule.getStart());
            schedule.setEnd(tabletSchedule.getEnd());
            schedule.setSchool(webSchool);
            lstSch.add(schedule);
        }

        webSchool.setSchedule(lstSch);
        webSchool.setMeeting(m);

        return webSchool;
    }

    private SocialEnvironment mergeSocialEnvironment(Meeting m, TabletSocialEnvironmentDto tabletSE) {
        SocialEnvironment webSE;

        if (tabletSE.getWebId() != null) {
            webSE = socialEnvironmentRepository.findOne(tabletSE.getWebId());
        } else {
            webSE = new SocialEnvironment();
        }

        webSE.setPhysicalCondition(tabletSE.getPhysicalCondition());
        webSE.setComment(tabletSE.getComment());

        List<RelSocialEnvironmentActivity> lstSEA = webSE.getRelSocialEnvironmentActivities();
        webSE.setRelSocialEnvironmentActivities(null);

        if (lstSEA != null & lstSEA.size() > 0) {
            for (RelSocialEnvironmentActivity rSEA : lstSEA) {
                rSEA.setSocialEnvironment(null);
                relSocialEnvironmentActivityRepository.delete(rSEA);
            }
        }

        lstSEA = new ArrayList<>();

        for (TabletRelSocialEnvironmentActivityDto tabletRelActivity : tabletSE.getRelSocialEnvironmentActivities()) {
            RelSocialEnvironmentActivity webRelAct = new RelSocialEnvironmentActivity();
            webRelAct.setSpecification(tabletRelActivity.getSpecification());
            webRelAct.setActivity(activityRepository.findOne(tabletRelActivity.getActivity().getId()));
            webRelAct.setSocialEnvironment(webSE);
            lstSEA.add(webRelAct);
        }

        webSE.setRelSocialEnvironmentActivities(lstSEA);
        webSE.setMeeting(m);

        return webSE;
    }

    private LeaveCountry mergeLeaveCountry(Meeting m, TabletLeaveCountryDto tabletLC) {
        LeaveCountry webLC;
        if (tabletLC.getWebId() != null) {
            webLC = leaveCountryRepository.findOne(tabletLC.getWebId());
        } else {
            webLC = new LeaveCountry();
        }

        webLC.setTimeAgo(tabletLC.getTimeAgo());
        webLC.setReason(tabletLC.getReason());
        webLC.setState(tabletLC.getState());
        webLC.setMedia(tabletLC.getMedia());
        webLC.setAddress(tabletLC.getAddress());
        webLC.setTimeResidence(tabletLC.getTimeResidence());
        webLC.setSpecficationImmigranDoc(tabletLC.getSpecficationImmigranDoc());
        webLC.setSpecificationRelationship(tabletLC.getSpecificationRelationship());

        if (tabletLC.getFamilyAnotherCountry() != null) {
            Election fac = new Election();
            fac.setId(tabletLC.getFamilyAnotherCountry().getId());
            webLC.setFamilyAnotherCountry(fac);
        }

        if (tabletLC.getCommunicationFamily() != null) {
            Election cf = new Election();
            cf.setId(tabletLC.getCommunicationFamily().getId());
            webLC.setCommunicationFamily(cf);
        }

        if (tabletLC.getOfficialDocumentation() != null) {
            Election oD = new Election();
            oD.setId(tabletLC.getOfficialDocumentation().getId());
            webLC.setOfficialDocumentation(oD);
        }

        if (tabletLC.getLivedCountry() != null) {
            Election lC = new Election();
            lC.setId(tabletLC.getLivedCountry().getId());
            webLC.setLivedCountry(lC);
        }

        if (tabletLC.getImmigrationDocument() != null) {
            ImmigrationDocument iD = new ImmigrationDocument();
            iD.setId(tabletLC.getImmigrationDocument().getId());
            webLC.setImmigrationDocument(iD);
        }

        if (tabletLC.getCountry() != null) {
            Country country = new Country();
            country.setId(tabletLC.getCountry().getId());
            webLC.setCountry(country);
        }

        if (tabletLC.getRelationship() != null) {
            Relationship r = new Relationship();
            r.setId(tabletLC.getRelationship().getId());
            webLC.setRelationship(r);
        }

        webLC.setMeeting(m);

        return webLC;
    }

    private List<Reference> mergeReferences(Meeting m, List<TabletReferenceDto> tabletLst) {
        List<Reference> webList = new ArrayList<>();

        for (TabletReferenceDto tabletReference : tabletLst) {
            Reference ref;
            if (tabletReference.getWebId() != null) {
                ref = referenceRepository.findOne(tabletReference.getWebId());
            } else {
                ref = new Reference();
            }

            ref.setFullName(tabletReference.getFullName());
            ref.setAge(tabletReference.getAge());
            ref.setAddress(tabletReference.getAddress());
            ref.setPhone(tabletReference.getPhone());
            ref.setSpecification(tabletReference.getSpecification());
            ref.setIsAccompaniment(tabletReference.getIsAccompaniment());
            ref.setSpecificationRelationship(tabletReference.getSpecificationRelationship());
            ref.setBlock(tabletReference.getBlock());
            ref.setMeeting(m);

            if (tabletReference.getDocumentType() != null) {
                DocumentType dt = new DocumentType();
                dt.setId(tabletReference.getDocumentType().getId());
                ref.setDocumentType(dt);
            }

            if (tabletReference.getRelationship() != null) {
                Relationship r = new Relationship();
                r.setId(tabletReference.getRelationship().getId());
                ref.setRelationship(r);
            }

            webList.add(ref);
        }

        return webList;
    }

    private List<ImputedHome> mergeImputedHomes(Meeting m, List<TabletImputedHomeDto> tabletLst) {

        List<ImputedHome> webLst = new ArrayList<>();

        for (TabletImputedHomeDto tabletHome : tabletLst) {
            ImputedHome webHome;

            if (tabletHome.getWebId() != null) {
                webHome = imputedHomeRepository.findOne(tabletHome.getWebId());
            } else {
                webHome = new ImputedHome();
            }

            webHome.setTimeLive(tabletHome.getTimeLive());
            webHome.setReasonChange(tabletHome.getReasonChange());
            webHome.setDescription(tabletHome.getDescription());
            webHome.setPhone(tabletHome.getPhone());
            webHome.setSpecification(tabletHome.getSpecification());
            webHome.setReasonSecondary(tabletHome.getReasonSecondary());

            Address address = webHome.getAddress();

            if (address == null) {
                address = new Address();
            }

            address.setStreet(tabletHome.getAddress().getStreet());
            address.setOutNum(tabletHome.getAddress().getOutNum());
            address.setInnNum(tabletHome.getAddress().getInnNum());
            address.setLat(tabletHome.getAddress().getLat());
            address.setLng(tabletHome.getAddress().getLng());
            address.setAddressString(address.toString());

            Location l = new Location();
            l.setId(tabletHome.getAddress().getLocation().getId());
            address.setLocation(l);

            addressRepository.save(address);
            webHome.setAddress(address);

            if (tabletHome.getHomeType() != null) {
                HomeType ht = new HomeType();
                ht.setId(tabletHome.getHomeType().getId());
                webHome.setHomeType(ht);
            }

            if (tabletHome.getRegisterType() != null) {
                RegisterType rt = new RegisterType();
                rt.setId(tabletHome.getRegisterType().getId());
                webHome.setRegisterType(rt);
            }

            List<Schedule> lstSchedule = webHome.getSchedule();
            webHome.setSchedule(null);

            if (lstSchedule != null && lstSchedule.size() > 0) {
                for (Schedule s : lstSchedule) {
                    s.setImputedHome(null);
                    scheduleRepository.delete(s);
                }
            }

            lstSchedule = new ArrayList<>();

            for (TabletScheduleDto tabletSchedule : tabletHome.getSchedule()) {
                Schedule schedule = new Schedule();
                schedule.setDay(tabletSchedule.getDay());
                schedule.setStart(tabletSchedule.getStart());
                schedule.setEnd(tabletSchedule.getEnd());
                schedule.setImputedHome(webHome);
                lstSchedule.add(schedule);
            }

            webHome.setSchedule(lstSchedule);
            webHome.setMeeting(m);
            webLst.add(webHome);
        }

        return webLst;
    }

    private List<Job> mergeJob(Meeting m, List<TabletJobDto> tabletLst) {
        List<Job> webLst = new ArrayList<>();

        for (TabletJobDto tabletJob : tabletLst) {

            Job webJob;
            if (tabletJob.getWebId() != null) {
                webJob = jobRepository.findOne(tabletJob.getWebId());
            } else {
                webJob = new Job();
            }

            webJob.setPost(tabletJob.getPost());
            webJob.setNameHead(tabletJob.getNameHead());
            webJob.setCompany(tabletJob.getCompany());
            webJob.setPhone(tabletJob.getPhone());

            try {
                webJob.setStartPrev(tabletJob.getStartPrev() == null ? null : sdf.parse(tabletJob.getStartPrev()));
                webJob.setStart(tabletJob.getStart() == null ? null : sdf.parse(tabletJob.getStart()));
                webJob.setEnd(tabletJob.getEnd() == null ? null : sdf.parse(tabletJob.getEnd()));
            } catch (Exception e) {
                System.out.println("error al parsear fechas de job");
                return null;
            }

            webJob.setSalaryWeek(tabletJob.getSalaryWeek());
            webJob.setReasonChange(tabletJob.getReasonChange());
            webJob.setAddress(tabletJob.getAddress());
            webJob.setBlock(tabletJob.getBlock());

            RegisterType rt = new RegisterType();
            rt.setId(tabletJob.getRegisterType().getId());

            List<Schedule> lstSchedule = webJob.getSchedule();
            webJob.setSchedule(null);

            if (lstSchedule != null && lstSchedule.size() > 0) {
                for (Schedule s : lstSchedule) {
                    s.setJob(null);
                    scheduleRepository.delete(s);
                }
            }

            lstSchedule = new ArrayList<>();

            for (TabletScheduleDto tabletSchedule : tabletJob.getSchedule()) {
                Schedule schedule = new Schedule();
                schedule.setDay(tabletSchedule.getDay());
                schedule.setStart(tabletSchedule.getStart());
                schedule.setEnd(tabletSchedule.getEnd());
                schedule.setJob(webJob);
                lstSchedule.add(schedule);
            }

            webJob.setSchedule(lstSchedule);
            webJob.setMeeting(m);
        }

        return webLst;
    }

    private List<Drug> mergeDrugs(Meeting m, List<TabletDrugDto> tabletLst) {

        List<Drug> webLst = new ArrayList<>();

        for (TabletDrugDto tabletDrug : tabletLst) {
            Drug webDrug;

            if (tabletDrug.getWebId() != null) {
                webDrug = drugRepository.findOne(tabletDrug.getWebId());
            } else {
                webDrug = new Drug();
            }

            webDrug.setQuantity(tabletDrug.getQuantity());

            try {
                webDrug.setLastUse(tabletDrug.getLastUse() == null ? null : sdf.parse(tabletDrug.getLastUse()));
            } catch (Exception e) {
                System.out.println("error al parsear fecha drogas");
                return null;
            }

            webDrug.setBlock(tabletDrug.getBlock());
            webDrug.setSpecificationType(tabletDrug.getSpecificationType());
            webDrug.setSpecificationPeriodicity(tabletDrug.getSpecificationPeriodicity());
            webDrug.setOnsetAge(tabletDrug.getOnsetAge());

            DrugType dt = new DrugType();
            dt.setId(tabletDrug.getDrugType().getId());
            webDrug.setDrugType(dt);

            Periodicity p = new Periodicity();
            p.setId(tabletDrug.getPeriodicity().getId());
            webDrug.setPeriodicity(p);

            webDrug.setMeeting(m);
            webLst.add(webDrug);
        }

        return webLst;
    }


    private Case saveCaseMeetingImputedDetention(TabletCaseDto tabletCase) {

        Case c = this.mergeCase(tabletCase);

        Meeting m = this.mergeMeeting(tabletCase.getMeeting());
        m.setCaseDetention(c);
        c.setMeeting(m);

        Imputed im = this.mergeImputed(tabletCase.getMeeting().getImputed());
        im.setMeeting(c.getMeeting());
        c.getMeeting().setImputed(im);

        caseRepository.save(c);//se guarda caso meeting e imputado

        return c;
    }

    private List<SourceVerification> mergeVerificationSources(Verification v, List<TabletSourceVerificationDto> tabletLst) {

        List<SourceVerification> webLst = new ArrayList<>();

        for (TabletSourceVerificationDto tabletSource : tabletLst) {
            SourceVerification webSource;
            if (tabletSource.getWebId() != null) {
                webSource = sourceVerificationRepository.findOne(tabletSource.getWebId());
            } else {
                webSource = new SourceVerification();
            }

            webSource.setFullName(tabletSource.getFullName());
            webSource.setAge(tabletSource.getAge());
            webSource.setAddress(tabletSource.getAddress());
            webSource.setPhone(tabletSource.getPhone());
            webSource.setIsAuthorized(tabletSource.getIsAuthorized());

            try {
                webSource.setDateComplete(tabletSource.getDateComplete() == null ? null : sdf.parse(tabletSource.getDateComplete()));
                webSource.setDateAuthorized(tabletSource.getDateAuthorized() == null ? null : sdf.parse(tabletSource.getDateAuthorized()));
            } catch (Exception e) {
                System.out.println("error al parsear fechas de fuente de verificacion");
            }

            webSource.setSpecification(tabletSource.getSpecification());
            webSource.setVisible(tabletSource.getVisible());

            VerificationMethod vm = new VerificationMethod();
            vm.setId(Constants.VERIFICATION_METHOD_VISIT_ID);
            webSource.setVerificationMethod(vm);

            Relationship r = new Relationship();
            r.setId(tabletSource.getRelationship().getId());
            webSource.setRelationship(r);

            List<FieldMeetingSource> lstFields = webSource.getFieldMeetingSourceList();

            if (lstFields != null) {
                lstFields.clear();
            } else {
                lstFields = new ArrayList<>();
            }

            for (TabletFieldMeetingSourceDto tabletField : tabletSource.getFieldMeetingSourceList()) {

                FieldMeetingSource webField = new FieldMeetingSource();
                webField.setValue(tabletField.getValue());
                webField.setJsonValue(tabletField.getJsonValue());
                webField.setFinal(tabletField.getIsFinal());
                webField.setIdFieldList(tabletField.getIdFieldList());
                webField.setReason(tabletField.getReason());
                webField.setStatusFieldVerification(statusFieldVerificationRepository.findOne(tabletField.getStatusFieldVerification().getId()));
                webField.setFieldVerification(fieldVerificationRepository.findOne(tabletField.getFieldVerification().getId()));
                webField.setSourceVerification(webSource);
                lstFields.add(webField);
            }

            webSource.setFieldMeetingSourceList(lstFields);
            webLst.add(webSource);
        }

        return webLst;
    }

    private Verification mergeVerification(TabletCaseDto tabletCase) {
        Case webC = caseRepository.findOne(tabletCase.getWebId());

        Verification v = webC.getVerification();

        User u = new User();
        u.setId(tabletCase.getVerification().getReviewer().getId());
        v.setReviewer(u);

        StatusVerification stv = new StatusVerification();
        stv.setId(tabletCase.getVerification().getStatus().getId());
        v.setStatus(stv);

        v.setSourceVerifications(this.mergeVerificationSources(v, tabletCase.getVerification().getSourceVerifications()));

        return v;
    }

    private List<HearingFormat> mergeFormats(TabletCaseDto tabletCase) {

        Case c;
        if (tabletCase.getWebId() != null) {
            c = caseRepository.findOne(tabletCase.getWebId());
        }

        c = this.saveCaseMeetingImputedDetention(tabletCase);

        List<TabletHearingFormatDto> tabletList = tabletCase.getHearingFormats();
        List<HearingFormat> webList = new ArrayList<>();

        for (TabletHearingFormatDto tabletHF : tabletList) {

            HearingFormat webHF = new HearingFormat();
            webHF.setCaseDetention(c);

            webHF.setIdFolder(tabletHF.getIdFolder());
            webHF.setIdJudicial(tabletHF.getIdJudicial());
            webHF.setRoom(tabletHF.getRoom());

            try {

                Calendar cal = Calendar.getInstance();
                Date rt = tabletHF.getRegisterTime() == null ? null : sdf.parse(tabletHF.getRegisterTime());

                if (rt != null) {
                    cal.setTime(rt);
                    webHF.setRegisterTime(cal);
                }

                webHF.setAppointmentDate(tabletHF.getAppointmentDate() == null ? null : sdf.parse(tabletHF.getAppointmentDate()));

                Date it = tabletHF.getInitTime() == null ? null : sdfT.parse(tabletHF.getInitTime());
                Time initT = it == null ? null : new Time(it.getTime());
                webHF.setInitTime(initT);

                Date et = tabletHF.getEndTime() == null ? null : sdfT.parse(tabletHF.getEndTime());
                Time endT = et == null ? null : new Time(et.getTime());
                webHF.setEndTime(new Time(endT.getTime()));

                webHF.setUmecaDate(tabletHF.getUmecaDate() == null ? null : sdf.parse(tabletHF.getUmecaDate()));

                Date ut = tabletHF.getUmecaTime() == null ? null : sdfT.parse(tabletHF.getInitTime());
                Time umecaT = it == null ? null : new Time(ut.getTime());
                webHF.setUmecaTime(umecaT);

            } catch (Exception e) {
                System.out.println("error al parsear fechas hearingformat");
                return null;
            }

            webHF.setJudgeName(tabletHF.getJudgeName());
            webHF.setMpName(tabletHF.getMpName());
            webHF.setDefenderName(tabletHF.getDefenderName());
            webHF.setTerms(tabletHF.getTerms());
            webHF.setConfirmComment(tabletHF.getConfirmComment());
            webHF.setIsFinished(tabletHF.getIsFinished());
            webHF.setComments(tabletHF.getComments());

            webHF.setHearingTypeSpecification(tabletHF.getHearingTypeSpecification());
            webHF.setImputedPresence(tabletHF.getImputedPresence());
            webHF.setHearingResult(tabletHF.getHearingResult());
            webHF.setPreviousHearing(tabletHF.getPreviousHearing());
            webHF.setShowNotification(tabletHF.getShowNotification());

            if (tabletHF.getHearingType() != null) {
                HearingType ht = new HearingType();
                ht.setId(tabletHF.getHearingType().getId());
                webHF.setHearingType(ht);
            }

            HearingFormatImputed hi = new HearingFormatImputed();

            hi.setName(tabletHF.getHearingImputed().getName());
            hi.setLastNameP(tabletHF.getHearingImputed().getLastNameP());
            hi.setLastNameM(tabletHF.getHearingImputed().getLastNameM());

            try {
                hi.setBirthDate(tabletHF.getHearingImputed().getBirthDate() == null ? null : sdf.parse(tabletHF.getHearingImputed().getBirthDate()));
            } catch (Exception e) {
                System.out.println("error al parasear fecha de hearing imputed");
                return null;
            }

            hi.setImputeTel(tabletHF.getHearingImputed().getImputeTel());

            Address add = new Address();

            add.setStreet(tabletHF.getHearingImputed().getAddress().getStreet());
            add.setOutNum(tabletHF.getHearingImputed().getAddress().getOutNum());
            add.setInnNum(tabletHF.getHearingImputed().getAddress().getInnNum());
            Location l = new Location();
            l.setId(tabletHF.getHearingImputed().getAddress().getLocation().getId());
            add.setLocation(l);
            add.setAddressString(add.toString());

            hi.setAddress(add);
            webHF.setHearingImputed(hi);

            if (tabletHF.getHearingFormatSpecs() != null) {
                HearingFormatSpecs hearingFormatSpecs = new HearingFormatSpecs();
                hearingFormatSpecs.setControlDetention(tabletHF.getHearingFormatSpecs().getControlDetention());
                hearingFormatSpecs.setExtension(tabletHF.getHearingFormatSpecs().getExtension());
                hearingFormatSpecs.setImputationFormulation(tabletHF.getHearingFormatSpecs().getImputationFormulation());

                try {

                    hearingFormatSpecs.setImputationDate(tabletHF.getHearingFormatSpecs().getImputationDate() == null ? null : sdf.parse(tabletHF.getHearingFormatSpecs().getImputationDate()));
                    hearingFormatSpecs.setLinkageDate(tabletHF.getHearingFormatSpecs().getLinkageDate() == null ? null : sdf.parse(tabletHF.getHearingFormatSpecs().getLinkageDate()));
                    hearingFormatSpecs.setExtDate(tabletHF.getHearingFormatSpecs().getExtDate() == null ? null : sdf.parse(tabletHF.getHearingFormatSpecs().getExtDate()));

                    Date ltD = tabletHF.getHearingFormatSpecs().getLinkageTime() == null ? null : sdfT.parse(tabletHF.getHearingFormatSpecs().getLinkageTime());
                    Time lt = ltD == null ? null : new Time(ltD.getTime());

                    hearingFormatSpecs.setLinkageTime(lt);


                } catch (Exception e) {
                    System.out.println("error al parasear fecha de hearing specs");
                    return null;
                }

                hearingFormatSpecs.setLinkageProcess(tabletHF.getHearingFormatSpecs().getLinkageProcess());
                hearingFormatSpecs.setLinkageRoom(tabletHF.getHearingFormatSpecs().getLinkageRoom());

                hearingFormatSpecs.setArrangementType(tabletHF.getHearingFormatSpecs().getArrangementType());
                hearingFormatSpecs.setNationalArrangement(tabletHF.getHearingFormatSpecs().getNationalArrangement());

                webHF.setHearingFormatSpecs(hearingFormatSpecs);
            }

            List<Crime> lstCrime = new ArrayList<>();

            for (TabletCrimeDto tabletCrime : tabletHF.getCrimeList()) {
                Crime webCrime = new Crime();
                webCrime.setComment(webCrime.getComment());
                webCrime.setArticle(webCrime.getArticle());
                webCrime.setFederal(electionRepository.findOne(tabletCrime.getFederal().getId()));
                webCrime.setCrime(crimeCatalogRepository.findOne(tabletCrime.getCrime().getId()));
                webCrime.setHearingFormat(webHF);
                lstCrime.add(webCrime);
            }

            webHF.setCrimeList(lstCrime);

            if (tabletHF.getAssignedArrangements() != null) {
                List<AssignedArrangement> webAA = new ArrayList<>();
                for (TabletAssignedArrangementDto tabletAA : tabletHF.getAssignedArrangements()) {
                    AssignedArrangement wAA = new AssignedArrangement();
                    wAA.setDescription(tabletAA.getDescription());
                    Arrangement a = new Arrangement();
                    a.setId(tabletAA.getId());
                    wAA.setArrangement(a);
                    wAA.setHearingFormat(webHF);
                    webAA.add(wAA);
                }
                webHF.setAssignedArrangements(webAA);
            }

            if (tabletHF.getContacts() != null) {
                List<ContactData> webContacts = new ArrayList<>();
                for (TabletContactDataDto tabletContact : tabletHF.getContacts()) {
                    ContactData webCont = new ContactData();
                    webCont.setAddressTxt(tabletContact.getAddressTxt());
                    webCont.setNameTxt(tabletContact.getNameTxt());
                    webCont.setPhoneTxt(tabletContact.getPhoneTxt());
                    webCont.setHearingFormat(webHF);
                    webContacts.add(webCont);
                }
                webHF.setContacts(webContacts);
            }

            if (tabletHF.getUmecaSupervisor() != null) {
                User uu = new User();
                uu.setId(tabletHF.getUmecaSupervisor().getId());
                webHF.setUmecaSupervisor(uu);
            }

            if (tabletHF.getSupervisor() != null) {
                User u = new User();
                u.setId(tabletHF.getSupervisor().getId());
                webHF.setSupervisor(u);
            }
        }

        return webList;
    }

    @Transactional
    private List<HearingFormat> saveCaseAndGetHearingFormats(TabletCaseDto tabletCaseDto) {
        Case c = this.saveCaseMeetingImputedDetention(tabletCaseDto);
        return this.mergeFormats(tabletCaseDto);
    }

//pasar a metodo del web service
//    private void updateHearingFormats(TabletCaseDto tabletCaseDto) {
//        try {
//            List<HearingFormat> hearingFormats = this.saveCaseAndGetHearingFormats(tabletCaseDto);
//            for (HearingFormat hearingFormat : hearingFormats) {
//                ResponseMessage rm = hearingFormatService.save(hearingFormat, null);
//                System.out.println("");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println("Error al guardar formatos de audiencia ");
//        }
//    }


    @Transactional
    private void synchronizeLogCaseActivities(TabletCaseDto caseDto) {

        Case c;

        if (caseDto.getWebId() != null) {
            c = caseRepository.findOne(caseDto.getWebId());
        }

        c = this.saveCaseMeetingImputedDetention(caseDto);

        List<TabletLogCaseDto> tabletLogs = caseDto.getLogsCase();
        List<LogCase> webLogs = new ArrayList<>();

        for (TabletLogCaseDto tabletLog : tabletLogs) {
            LogCase webLog = new LogCase();
            Date d;
            Calendar cal = Calendar.getInstance();

            try {
                d = tabletLog.getDateString() == null ? null : sdf.parse(tabletLog.getDateString());
                cal.setTime(d);
            } catch (Exception e) {
                System.out.println("error al parsear la fecha de log case");
            }

            webLog.setDate(cal);
            webLog.setActivity(tabletLog.getActivityString());
            webLog.setTitle(tabletLog.getTitle());
            webLog.setResume(tabletLog.getResume());
            User u = new User();
            u.setId(tabletLog.getUserId());
            webLog.setUser(u);


            webLog.setCaseDetention(c);
            webLogs.add(webLog);
        }

        logCaseRepository.save(webLogs);

    }

    @Transactional
    private void synchronizeVerification(TabletCaseDto tabletCase) {
        Case c = this.saveCaseMeetingImputedDetention(tabletCase);
        caseRepository.save(c);
        Verification v = this.mergeVerification(tabletCase);
        verificationRepository.save(v);
    }

    @Transactional
    private void synchronizeMeeting(TabletCaseDto tabletCase) {

        Case c = this.saveCaseMeetingImputedDetention(tabletCase);
        Meeting m = c.getMeeting();

        SocialNetwork sN = this.mergeSocialNetwork(m, tabletCase.getMeeting().getSocialNetwork());
        socialNetworkRepository.save(sN);

        School school = this.mergeSchool(m, tabletCase.getMeeting().getSchool());
        schoolRepository.save(school);

        SocialEnvironment se = this.mergeSocialEnvironment(m, tabletCase.getMeeting().getSocialEnvironment());
        socialEnvironmentRepository.save(se);

        LeaveCountry leaveCountry = this.mergeLeaveCountry(m, tabletCase.getMeeting().getLeaveCountry());
        leaveCountryRepository.save(leaveCountry);

        List<Reference> lstRef = this.mergeReferences(m, tabletCase.getMeeting().getReferences());
        referenceRepository.save(lstRef);

        List<ImputedHome> lstImputedHomes = this.mergeImputedHomes(m, tabletCase.getMeeting().getImputedHomes());
        imputedHomeRepository.save(lstImputedHomes);

        List<Job> lstJob = this.mergeJob(m, tabletCase.getMeeting().getJobs());
        jobRepository.save(lstJob);

        List<Drug> lstDrug = this.mergeDrugs(m, tabletCase.getMeeting().getDrugs());
        drugRepository.save(lstDrug);
    }

}
