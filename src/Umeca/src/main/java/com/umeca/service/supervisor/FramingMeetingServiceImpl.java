package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Degree;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.dto.AddressDto;
import com.umeca.model.catalog.dto.ScheduleDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.GroupMessageMeetingDto;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.reviewer.dto.RelActivityObjectDto;
import com.umeca.model.entities.reviewer.dto.TerminateMeetingMessageDto;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.*;
import com.umeca.repository.shared.SystemSettingRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogCommentService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("framingMeetingService")
public class FramingMeetingServiceImpl implements FramingMeetingService {

    @Qualifier("qFramingMeetingRepository")
    @Autowired
    private FramingMeetingRepository framingMeetingRepository;

    @Autowired
    private StatusCaseRepository statusCaseRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private FramingReferenceRepository framingReferenceRepository;

    @Autowired
    private FramingSelectedSourceRelRepository framingSelectedSourceRelRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProcessAccompanimentRepository processAccompanimentRepository;

    @Autowired
    private FramingRiskRepository framingRiskRepository;

    @Autowired
    private FramingThreatRepository framingThreatRepository;

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private FramingSelectedThreatRelRepository framingSelectedThreatRelRepository;

    @Autowired
    private FramingSelectedRiskRelRepository framingSelectedRiskRelRepository;

    @Autowired
    private FramingAddressRepository framingAddressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrugTypeRepository drugTypeRepository;

    @Autowired
    private PeriodicityRepository periodicityRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private RelativeAbroadRelRepository relativesAbroadRelRepository;

    @Autowired
    private AddictedAcquaintanceRelRepository addictedAcquaintanceRelRepository;

    @Autowired
    private LogCommentRepository logCommentRepository;

    @Autowired
    private ArrangementRepository arrangementRepository;

    @Autowired
    private ObligationIssuesRepository obligationIssuesRepository;

    @Autowired
    private SharedLogExceptionService logException;

    @Autowired
    private SharedUserService sharedUserService;

    @Autowired
    private AcademicLevelRepository academicLevelRepository;

    @Autowired
    private AccompanimentInfoRepository accompanimentInfoRepository;

    @Transactional
    @Override
    public ResponseMessage save(FramingMeeting framingMeeting) {

        ResponseMessage response = new ResponseMessage();
        try {
            framingMeeting.setInitDate(new Date());
            framingMeetingRepository.save(framingMeeting);
            response.setHasError(false);
            response.setMessage("Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "save", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde");
        } finally {
            return response;
        }
    }

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    public FramingPersonalDataView fillPersonalDataForView(Long idCase) {

        FramingPersonalDataView view = new FramingPersonalDataView();
        Case existCase = caseRepository.findOne(idCase);
        Verification existVer = existCase.getVerification();
        FramingMeeting existFraming = existCase.getFramingMeeting();

        if (existFraming.getPersonalData() != null) {

            view.setName(existFraming.getPersonalData().getName());
            view.setLastNameP(existFraming.getPersonalData().getLastNameP());
            view.setLastNameM(existFraming.getPersonalData().getLastNameM());
            view.setGender(existFraming.getPersonalData().getGender());
            view.setMaritalStatus(existFraming.getPersonalData().getMaritalStatus().getId());
            view.setMaritalStatusYears(existFraming.getPersonalData().getMaritalStatusYears());
            view.setBirthCountryId(existFraming.getPersonalData().getBirthCountry().getId());
            view.setBirthState(existFraming.getPersonalData().getBirthState());
            view.setBirthDate(existFraming.getPersonalData().getBirthDate());
            view.setPhysicalCondition(existFraming.getPersonalData().getPhysicalCondition());
            view.setPhone(existFraming.getPersonalData().getPhone());
            view.setCelPhone(existFraming.getPersonalData().getCelPhone());
            view.setEmail(existFraming.getPersonalData().getEmail());
            view.setSocialNetworking(existFraming.getPersonalData().getSocialNetworking());
            view.setComments(existFraming.getPersonalData().getComments());

            if (existFraming.getPersonalData().getBirthStateCmb() != null) {
                view.setBirthStateId(existFraming.getPersonalData().getBirthStateCmb().getId());
                view.setIsMexico(true);
            } else
                view.setIsMexico(false);

            return view;
        }


        if (existVer != null && existVer.getStatus().getName().equals(Constants.VERIFICATION_STATUS_COMPLETE)) {
            Meeting existVerifMeet = existVer.getMeetingVerified();

            view.setName(existVerifMeet.getImputed().getName());
            view.setLastNameP(existVerifMeet.getImputed().getLastNameP());
            view.setLastNameM(existVerifMeet.getImputed().getLastNameM());
            view.setBirthDate(existVerifMeet.getImputed().getBirthDate());

            if (existVerifMeet.getImputed().getGender() == true)
                view.setGender(1);
            else
                view.setGender(2);

            view.setMaritalStatus(existVerifMeet.getImputed().getMaritalStatus().getId());
            Integer years = existVerifMeet.getImputed().getYearsMaritalStatus();

            if (years != null)
                view.setMaritalStatusYears(years.toString());

            view.setBirthCountryId(existVerifMeet.getImputed().getBirthCountry().getId());

            if (existVerifMeet.getImputed().getLocation() != null) {
                view.setBirthStateId(existVerifMeet.getImputed().getLocation().getMunicipality().getState().getId());
                view.setIsMexico(true);
            }

            view.setBirthState(existVerifMeet.getImputed().getBirthState());

            view.setPhysicalCondition(existVerifMeet.getSocialEnvironment().getPhysicalCondition());

            return view;
        }

        List<HearingFormat> formats = hearingFormatRepository.findLastHearingFormatByCaseId(idCase, new PageRequest(0, 1));

        if (formats != null && formats.size() > 0) {

            HearingFormat lasF = formats.get(0);

            view.setName(lasF.getHearingImputed().getName());
            view.setLastNameP(lasF.getHearingImputed().getLastNameP());
            view.setLastNameM(lasF.getHearingImputed().getLastNameM());
            view.setBirthDate(lasF.getHearingImputed().getBirthDate());

            return view;
        }

        return new FramingPersonalDataView();
    }

    @Autowired
    private StateRepository stateRepository;

    public FramingImputedPersonalData fillPersonalData(Long idCase, FramingPersonalDataView view) {

        FramingImputedPersonalData personalData = caseRepository.findOne(idCase).getFramingMeeting().getPersonalData();

        if (personalData == null)
            personalData = new FramingImputedPersonalData();

        personalData.setName(view.getName());
        personalData.setLastNameP(view.getLastNameP());
        personalData.setLastNameM(view.getLastNameM());
        personalData.setGender(view.getGender());
        personalData.setMaritalStatus(maritalStatusRepository.findOne(view.getMaritalStatus()));
        personalData.setMaritalStatusYears(view.getMaritalStatusYears());
        personalData.setBirthCountry(countryRepository.findOne(view.getBirthCountryId()));
        personalData.setBirthState(view.getBirthState());
        personalData.setBirthDate(view.getBirthDate());
        personalData.setPhysicalCondition(view.getPhysicalCondition());
        personalData.setPhone(view.getPhone());
        personalData.setCelPhone(view.getCelPhone());
        personalData.setEmail(view.getEmail());
        personalData.setSocialNetworking(view.getSocialNetworking());
        personalData.setComments(view.getComments());

        if (view.getBirthStateId() != null && view.getBirthStateId() > 0 && view.getIsMexico() != null && view.getIsMexico() == true)
            personalData.setBirthStateCmb(stateRepository.findOne(view.getBirthStateId()));

        return personalData;
    }

    @Override
    public FramingMeetingView fillForView(Case existCase) {

        FramingMeetingView framingMeetingView = new FramingMeetingView();

        framingMeetingView.setIdFolder(existCase.getIdFolder());
        framingMeetingView.setIdCase(existCase.getId());
        framingMeetingView.setCanTerminate(true);
        framingMeetingView.setUserName(existCase.getFramingMeeting().getSupervisor().getFullname());

        framingMeetingView.setAddressComments(existCase.getFramingMeeting().getAddressComments());
        framingMeetingView.setHousemateComments(existCase.getFramingMeeting().getHousemateComments());
        framingMeetingView.setReferencesComments(existCase.getFramingMeeting().getReferencesComments());
        framingMeetingView.setDrugsComments(existCase.getFramingMeeting().getDrugsComments());
        framingMeetingView.setActivitiesComments(existCase.getFramingMeeting().getActivitiesComments());
        framingMeetingView.setJobComments(existCase.getFramingMeeting().getJobComments());
        framingMeetingView.setVictimComments(existCase.getFramingMeeting().getVictimComments());

        if (existCase.getFramingMeeting().getIsTerminated() == true)
            framingMeetingView.setCanTerminate(false);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        String init = "";
        String end = "";

        try {
            Date iniD = existCase.getFramingMeeting().getInitDate();
            Date endD = existCase.getFramingMeeting().getEndDate();

            if (iniD != null)
                init = sdf.format(iniD);

            if (endD != null)
                end = sdf.format(endD);

        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "fillForView_FramingMeetingServiceImpl", sharedUserService);
        } finally {
            framingMeetingView.setInitDate(init);
            framingMeetingView.setEndDate(end);
        }

        return framingMeetingView;
    }

    @Override
    public List<FramingReferenceForView> loadExistSources(Long idCase) {

        List<FramingReferenceForView> lstView = new ArrayList<>();

        List<FramingReference> existSources = framingReferenceRepository.getAccompanimentReferencesByIdCase(idCase);

        for (FramingReference fr : existSources) {
            if (fr.getIsAccompaniment() == true) {
                FramingReferenceForView objView = new FramingReferenceForView();
                objView.setId(fr.getId());
                String relationship = fr.getRelationship().getName();
                objView.setDescription(fr.getName() + ", " + relationship);
                objView.setValSel(false);
                lstView.add(objView);
            }
        }

        return lstView;
    }

    public List<FramingSelectedSourceRel> generateSourceRel(Long idCase, String lstJson) {

        Type listType = new TypeToken<List<Long>>() {
        }.getType();

        List<Long> ids = new Gson().fromJson(lstJson, listType);

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();


        List<FramingSelectedSourceRel> sourceRel = new ArrayList<>();

        for (Long currId : ids) {

            FramingSelectedSourceRel rel = new FramingSelectedSourceRel();
            rel.setFramingMeeting(existFraming);

            FramingReference existRef = framingReferenceRepository.findOne(currId);

            if (existRef != null) {
                rel.setFramingReference(existRef);
                sourceRel.add(rel);
            }

        }

        return sourceRel;
    }

    public List<FramingSelectedRiskRel> generateRiskRel(Long idCase, String lstJson) {

        Type listType = new TypeToken<List<Long>>() {
        }.getType();

        List<Long> ids = new Gson().fromJson(lstJson, listType);

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();


        List<FramingSelectedRiskRel> riskRel = new ArrayList<>();

        for (Long currId : ids) {
            FramingSelectedRiskRel rel = new FramingSelectedRiskRel();
            rel.setFramingMeeting(existFraming);
            rel.setFramingRisk(framingRiskRepository.findOne(currId));
            riskRel.add(rel);
        }

        return riskRel;
    }

    public List<FramingSelectedThreatRel> generateThreatRel(Long idCase, String lstJson) {

        Type listType = new TypeToken<List<Long>>() {
        }.getType();

        List<Long> ids = new Gson().fromJson(lstJson, listType);

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();


        List<FramingSelectedThreatRel> threatRel = new ArrayList<>();

        for (Long currId : ids) {
            FramingSelectedThreatRel rel = new FramingSelectedThreatRel();
            rel.setFramingMeeting(existFraming);
            rel.setFramingThreat(framingThreatRepository.findOne(currId));
            threatRel.add(rel);
        }

        return threatRel;
    }

    @Transactional
    public ResponseMessage saveSelectedItems(Long idCase, FramingEnvironmentAnalysisForView view) {

        try {
            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

            List<FramingSelectedSourceRel> lstExistSources = existFraming.getSelectedSourcesRel();

            if (lstExistSources != null && lstExistSources.size() > 0) {
                for (FramingSelectedSourceRel sel : lstExistSources) {
                    framingSelectedSourceRelRepository.delete(sel);
                }
            }

            existFraming.setSelectedSourcesRel((this.generateSourceRel(idCase, view.getLstSelectedSources())));


            List<FramingSelectedRiskRel> lstExistRisk = existFraming.getSelectedRisksRel();

            if (lstExistRisk != null && lstExistRisk.size() > 0) {
                for (FramingSelectedRiskRel sel : lstExistRisk) {
                    framingSelectedRiskRelRepository.delete(sel);
                }
            }

            existFraming.setSelectedRisksRel((this.generateRiskRel(idCase, view.getLstSelectedRisk())));

            List<FramingSelectedThreatRel> lstExistThreat = existFraming.getSelectedThreatsRel();

            if (lstExistThreat != null && lstExistThreat.size() > 0) {
                for (FramingSelectedThreatRel sel : lstExistThreat) {
                    framingSelectedThreatRelRepository.delete(sel);
                }
            }

            existFraming.setSelectedThreatsRel((this.generateThreatRel(idCase, view.getLstSelectedThreat())));

            existFraming.setEnvironmentComments(view.getEnvironmentComments());

            existFraming = framingMeetingRepository.save(existFraming);
            framingMeetingRepository.flush();

            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveSelectedItems", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }
    }

    @Override
    @Transactional
    public ResponseMessage saveReference(Case existCase, FramingReference newReference) {

        try {

            List<AccompanimentInfo> lstAccomInf = accompanimentInfoRepository.getAccompanimentInfoByIdRef(newReference.getId(), new PageRequest(0, 1));

            AccompanimentInfo accompanimentInfo = null;

            if (lstAccomInf != null && lstAccomInf.size() > 0)
                accompanimentInfo = lstAccomInf.get(0);

            if (newReference.getIsAccompaniment() != null && newReference.getIsAccompaniment() == true) {

                if (accompanimentInfo == null)
                    accompanimentInfo = new AccompanimentInfo();

                if (newReference.getPersonType() != null && newReference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {

                    accompanimentInfo.setGender(newReference.getGender());
                    accompanimentInfo.setOccupationPlace(newReference.getOccupationPlace());

                    if (newReference.getAcademicLvlId() != null && newReference.getAcademicLvlId() > 0)
                        accompanimentInfo.setAcademicLevel(academicLevelRepository.findOne(newReference.getAcademicLvlId()));

                } else if (newReference.getPersonType() != null && newReference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {
                    accompanimentInfo.setGender(newReference.getGender());
                    accompanimentInfo.setOccupationPlace(newReference.getOccupationPlace());

                    if (newReference.getAcademicLvlId() != null && newReference.getAcademicLvlId() > 0)
                        accompanimentInfo.setAcademicLevel(academicLevelRepository.findOne(newReference.getAcademicLvlId()));

                    Address address = accompanimentInfo.getAddress();

                    if (address == null) {
                        address = new Address();
                    }

                    address.setStreet(newReference.getStreetComponent());
                    address.setOutNum(newReference.getOutNumComponent());
                    address.setInnNum(newReference.getInnNumComponent());
                    address.setLat(newReference.getLat());
                    address.setLng(newReference.getLng());
                    address.setLocation(locationRepository.findOne(newReference.getLocation().getId()));
                    address.setAddressString(address.toString());
                    accompanimentInfo.setAddress(address);

                    newReference.setAddress(null);
                }

                newReference.setAccompanimentInfo(accompanimentInfo);
            } else {
                newReference.setIsAccompaniment(false);
                if (accompanimentInfo != null) {
                    newReference.setAccompanimentInfo(null);
                    accompanimentInfoRepository.delete(accompanimentInfo);
                }
            }

            newReference.setRelationship(relationshipRepository.findOne(newReference.getRelationshipId()));
            if (!newReference.getRelationship().getSpecification()) {
                newReference.setSpecificationRelationship("");
            }

            newReference.setFramingMeeting(existCase.getFramingMeeting());
            framingReferenceRepository.save(newReference);
            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveReference", sharedUserService);
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }

    }

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public ResponseMessage saveVictim(Case existCase, FramingReference newReference) {

        try {

            List<AccompanimentInfo> lstAccomInf = accompanimentInfoRepository.getAccompanimentInfoByIdRef(newReference.getId(), new PageRequest(0, 1));

            AccompanimentInfo accompanimentInfo = null;

            if (lstAccomInf != null && lstAccomInf.size() > 0)
                accompanimentInfo = lstAccomInf.get(0);

            if (newReference.getHasVictimWitnessInfo() != null && newReference.getHasVictimWitnessInfo() == false) {

                if (accompanimentInfo != null) {
                    newReference.setAccompanimentInfo(null);
                    accompanimentInfoRepository.delete(accompanimentInfo);
                }
                newReference.setRelationship(relationshipRepository.findNoneRelationship());

            } else if (newReference.getHasVictimWitnessInfo() != null && newReference.getHasVictimWitnessInfo() == true) {

                if (accompanimentInfo == null)
                    accompanimentInfo = new AccompanimentInfo();

                Address address = accompanimentInfo.getAddress();

                if (address == null) {
                    address = new Address();
                }

                address.setStreet(newReference.getStreetComponent());
                address.setOutNum(newReference.getOutNumComponent());
                address.setInnNum(newReference.getInnNumComponent());
                address.setLat(newReference.getLat());
                address.setLng(newReference.getLng());
                address.setLocation(locationRepository.findOne(newReference.getLocation().getId()));
                address.setAddressString(address.toString());
                accompanimentInfo.setAddress(address);

                newReference.setAccompanimentInfo(accompanimentInfo);
                newReference.setIsAccompaniment(true);
                newReference.setRelationship(relationshipRepository.findOne(newReference.getRelationshipId()));

                if (!newReference.getRelationship().getSpecification()) {
                    newReference.setSpecificationRelationship("");
                }
            }
            newReference.setFramingMeeting(existCase.getFramingMeeting());
            framingReferenceRepository.save(newReference);
            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveReference", sharedUserService);
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }

    }


    public ProcessAccompanimentForView fillProcessAccompanimentForView(Long idCase) {

        ProcessAccompaniment processAccompaniment = caseRepository.findOne(idCase).getFramingMeeting().getProcessAccompaniment();

        if (processAccompaniment == null)
            return new ProcessAccompanimentForView();

        ProcessAccompanimentForView view = new ProcessAccompanimentForView();

        Gson conv = new Gson();

        view.setName(processAccompaniment.getName());
        view.setLastNameP(processAccompaniment.getLastNameP());
        view.setLastNameM(processAccompaniment.getLastNameM());
        view.setGender(processAccompaniment.getGender());
        view.setAge(processAccompaniment.getAge());
        view.setPhone(processAccompaniment.getPhone());
        view.setCelphone(processAccompaniment.getCelphone());
        view.setDegree(processAccompaniment.getDegree());
        view.setOccName(processAccompaniment.getOccupation().getName());
        view.setOccPlace(processAccompaniment.getOccupation().getPlace());
        view.setOccPhone(processAccompaniment.getOccupation().getPhone());
        view.setIdAddres(processAccompaniment.getAddress().getId());
        view.setRelationshipId(processAccompaniment.getRelationship().getId());
        //view.setAddress(conv.toJson(new AddressDto().addressDto(addressRepository.findOne(processAccompaniment.getAddress().getId()))));

        return view;
    }

    public ProcessAccompaniment fillProcessAccompaniment(Long idCase, ProcessAccompanimentForView view) {
        ProcessAccompaniment processAccompaniment = caseRepository.findOne(idCase).getFramingMeeting().getProcessAccompaniment();

        if (processAccompaniment == null) {
            processAccompaniment = new ProcessAccompaniment();
        }

        processAccompaniment.setName(view.getName());
        processAccompaniment.setLastNameP(view.getLastNameP());
        processAccompaniment.setLastNameM(view.getLastNameM());
        processAccompaniment.setGender(view.getGender());
        processAccompaniment.setAge(view.getAge());
        processAccompaniment.setPhone(view.getPhone());
        processAccompaniment.setCelphone(view.getCelphone());
        processAccompaniment.setDegree(view.getDegree());

        Relationship relationship = relationshipRepository.findOne(view.getRelationshipId());

        processAccompaniment.setRelationship(relationship);

        Occupation occup = processAccompaniment.getOccupation();

        if (occup == null)
            occup = new Occupation();

        occup.setName(view.getOccName());
        occup.setPlace(view.getOccPlace());
        occup.setPhone(view.getOccPhone());

        processAccompaniment.setOccupation(occup);

        Address address = processAccompaniment.getAddress();

        if (address == null)
            address = new Address();

        address.setStreet(view.getStreet());
        address.setInnNum(view.getInnNum());
        address.setOutNum(view.getOutNum());
        address.setLocation(locationRepository.findOne(view.getLocation().getId()));
        address.setAddressString(address.toString());

        processAccompaniment.setAddress(address);

        return processAccompaniment;
    }

    @Transactional
    public ResponseMessage saveProcessAccompaniment(ProcessAccompaniment processAccompaniment) {
        try {
            processAccompanimentRepository.save(processAccompaniment);
            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveProcessAccompaniment", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }
    }

    @Autowired
    RelFramingMeetingActivityRepository relFramingActivityRepository;
    @Autowired
    ActivityRepository activityRepository;


    public FramingEnvironmentAnalysisForView loadEnvironmentAnalysis(Long idCase) {
        Gson conv = new Gson();
        FramingEnvironmentAnalysisForView view = new FramingEnvironmentAnalysisForView();

        view.setLstSources(conv.toJson(this.loadExistSources(idCase)));

        List<FramingRisk> lstRisks = framingRiskRepository.findNoObsolete();
        //Collections.sort(lstRisks, FramingRisk.framingRiskComparator);

        view.setLstRisk(conv.toJson(lstRisks));

        List<FramingThreat> lstThreat = framingThreatRepository.findAll();
        //Collections.sort(lstThreat, FramingThreat.framingThreatComparator);

        view.setLstThreat(conv.toJson(lstThreat));

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

        view.setEnvironmentComments(existFraming.getEnvironmentComments());

        List<Long> lstSelectedSources = new ArrayList<>();

        if (existFraming.getSelectedSourcesRel() != null && existFraming.getSelectedSourcesRel().size() > 0) {
            for (FramingSelectedSourceRel rel : existFraming.getSelectedSourcesRel()) {
                lstSelectedSources.add(rel.getFramingReference().getId());
            }
        }

        view.setLstSelectedSources(conv.toJson(lstSelectedSources));

        List<Long> lstSelectedRisk = new ArrayList<>();

        if (existFraming.getSelectedRisksRel() != null && existFraming.getSelectedRisksRel().size() > 0) {
            for (FramingSelectedRiskRel rel : existFraming.getSelectedRisksRel()) {
                lstSelectedRisk.add(rel.getFramingRisk().getId());
            }
        }

        view.setLstSelectedRisk(conv.toJson(lstSelectedRisk));

        List<Long> lstSelectedThreat = new ArrayList<>();

        if (existFraming.getSelectedThreatsRel() != null && existFraming.getSelectedThreatsRel().size() > 0) {
            for (FramingSelectedThreatRel rel : existFraming.getSelectedThreatsRel()) {
                lstSelectedThreat.add(rel.getFramingThreat().getId());
            }
        }
        view.setLstSelectedThreat(conv.toJson(lstSelectedThreat));

        List<HearingFormat> lstFormats = caseRepository.findOne(idCase).getHearingFormats();
        Collections.sort(lstFormats, HearingFormat.hearingFormatComparator);
        HearingFormat lastFormat = lstFormats.get(lstFormats.size() - 1);


        List<String> lstAssArrg = new ArrayList<>();

        if (lastFormat.getAssignedArrangements() != null && lastFormat.getAssignedArrangements().size() > 0) {
            for (AssignedArrangement rel : lastFormat.getAssignedArrangements()) {
                StringBuilder sb = new StringBuilder();
                sb.append(rel.getArrangement().getDescription());
                sb.append(", ");
                sb.append(rel.getDescription());
                lstAssArrg.add(sb.toString());
            }
        }

        view.setLstSelectedArrangement(conv.toJson(lstAssArrg));

        return view;
    }

    public Address fillAddress(Address address, FramingAddressDto view) {

        if (address == null)
            address = new Address();

        address.setStreet(view.getStreet());
        address.setOutNum(view.getOutNum());
        address.setInnNum(view.getInnNum());
        address.setLocation(locationRepository.findOne(view.getLocation().getId()));
        address.setAddressString(address.toString());

        return address;
    }

    @Autowired
    private HomeTypeRepository homeTypeRepository;

    public FramingAddressDto fillFramingAddressForView(FramingAddress existAddress) {

        FramingAddressDto obj = new FramingAddressDto();

        if (existAddress != null) {
            obj.setPhone(existAddress.getPhone());
            if (existAddress.getRegisterType() != null)
                obj.setRegisterTypeId(existAddress.getRegisterType().getId());
            if (existAddress.getHomeType() != null)
                obj.setHomeTypeId(existAddress.getHomeType().getId());
            obj.setSpecification(existAddress.getSpecification());
            obj.setTimeAgo(existAddress.getTimeAgo());
            obj.setAddressRef(existAddress.getAddressRef());
            obj.setReasonAnother(existAddress.getReasonAnother());
            obj.setTimeLive(existAddress.getTimeLive());
            obj.setReasonChange(existAddress.getReasonChange());

            List<ScheduleDto> scheduleDto = new ArrayList<>();

            for (Schedule act : existAddress.getSchedule()) {
                ScheduleDto newObj = new ScheduleDto();
                newObj.setDay(act.getDay());
                newObj.setStart(act.getStart());
                newObj.setEnd(act.getEnd());
                scheduleDto.add(newObj);
            }

            obj.setSchedule(new Gson().toJson(scheduleDto));
        }

        return obj;
    }

    @Override
    @Transactional
    public ResponseMessage saveFramingAddress(Long idCase, FramingAddressDto view) {

        try {

            FramingAddress existFramingAddress = null;

            if (view.getId() != null) {
                existFramingAddress = framingAddressRepository.findFramingAddressByIdAddress(view.getId());
                this.fillAddress(existFramingAddress.getAddress(), view);

                List<Schedule> existSche = existFramingAddress.getSchedule();

                for (Schedule act : existSche) {
                    act.setFramingAddress(null);
                    scheduleRepository.delete(act);
                }

                existFramingAddress.setSchedule(null);
                framingAddressRepository.save(existFramingAddress);

            } else {
                existFramingAddress = new FramingAddress();
                existFramingAddress.setFramingMeeting(caseRepository.findOne(idCase).getFramingMeeting());
                existFramingAddress.setAddress(this.fillAddress(null, view));
            }

            existFramingAddress.setPhone(view.getPhone());
            existFramingAddress.setHomeType(homeTypeRepository.findOne(view.getHomeTypeId()));
            existFramingAddress.setSpecification(view.getSpecification());
            existFramingAddress.setRegisterType(registerTypeRepository.findOne(view.getRegisterTypeId()));
            existFramingAddress.setTimeAgo(view.getTimeAgo());
            existFramingAddress.setAddressRef(view.getAddressRef());
            existFramingAddress.setReasonAnother(view.getReasonAnother());
            existFramingAddress.setTimeLive(view.getTimeLive());
            existFramingAddress.setReasonChange(view.getReasonChange());

            List<ScheduleDto> scheduleDto = new Gson().fromJson(view.getSchedule(), new TypeToken<List<ScheduleDto>>() {
            }.getType());
            List<Schedule> schedule = new ArrayList<>();

            for (ScheduleDto act : scheduleDto) {
                Schedule newObj = new Schedule();
                newObj.setDay(act.getDay());
                newObj.setStart(act.getStart());
                newObj.setEnd(act.getEnd());
                newObj.setFramingAddress(existFramingAddress);
                schedule.add(newObj);
            }

            existFramingAddress.setSchedule(schedule);
            framingAddressRepository.save(existFramingAddress);

            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "saveFramingAddress", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }
    }

    @Override
    @Transactional
    public ResponseMessage deleteFramingAddress(Long id) {

        try {

            framingAddressRepository.delete(id);

            return new ResponseMessage(false, "Se ha eliminado el registro con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteFramingAddress", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }
    }

    @Override
    @Transactional
    public ResponseMessage deleteReference(Long id) {

        try {

            if (framingSelectedSourceRelRepository.findSourceRelByIdSource(id) != null)
                return new ResponseMessage(true, "No se puede eliminar la informaci&oacute;n. El registro ha sido seleccionado en la secci&oacute;n An&aacute;lisis del entorno.");

            framingReferenceRepository.delete(id);

            return new ResponseMessage(false, "Se ha eliminado el registro con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteReference", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }
    }

    @Override
    @Transactional
    public ResponseMessage doUpsertDrug(Drug drug, Long idCase) {
        ResponseMessage result = new ResponseMessage();
        try {
            drug.setDrugType(drugTypeRepository.findOne(drug.getDrugType().getId()));
            drug.setPeriodicity(periodicityRepository.findOne(drug.getPeriodicity().getId()));
            drug.setFramingMeeting(caseRepository.findOne(idCase).getFramingMeeting());
            if (drug.getId() != null && drug.getId() == 0) {
                drug.setId(null);
            }
            drugRepository.save(drug);
            result.setHasError(false);
            result.setMessage("Se ha guardado la informaci&oacute;n con &eacute;xito");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertDrug", sharedUserService);
            result.setHasError(true);
            result.setMessage("Ocurrio un error al guardar la informaci&oacute;n. Int&eacute;nte m&aacute;s tarde.");
        }
        return result;
    }

    @Override
    @Transactional
    public ResponseMessage deleteDrug(Long id) {
        ResponseMessage result = new ResponseMessage();
        try {
            drugRepository.delete(drugRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se elimino la sustancia con &eacute;xito");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteDrug", sharedUserService);
            result.setHasError(true);
            result.setMessage("Ocurrio un error al eliminar la sustancia. Int�nte m&aacute;s tarde");
        }
        return result;
    }

    public AdditionalQuestionsForView fillAddtionalQuestionsForView(Long idCase) {

        Gson conv = new Gson();

        AdditionalFramingQuestions existQuest = caseRepository.findOne(idCase).getFramingMeeting().getAdditionalFramingQuestions();

        AdditionalQuestionsForView view = new AdditionalQuestionsForView();

        if (existQuest != null) {

            view.setObservations(existQuest.getObservations());
            view.setAddictionTreatment(existQuest.getAddictionTreatment());
            view.setAddictionTreatmentInstitute(existQuest.getAddictionTreatmentInstitute());
            view.setAddictionTreatmentDate(existQuest.getAddictionTreatmentDate());
            view.setAddictedAcquaintance(existQuest.getAddictedAcquaintance());
            view.setRelativeAbroad(existQuest.getRelativeAbroad());
            view.setObligationIssue(existQuest.getObligationIssue());
            view.setSelectedObligationIssues(conv.toJson(this.loadAssArrangements(idCase)));
        }

        view.setSelectedRelativesAbroad(conv.toJson(this.loadRelativeAbroad(idCase)));
        view.setSelectedAddictedAcquaintances(conv.toJson(this.loadAddictedAcquaintance(idCase)));
        view.setSelectedObligationIssues(conv.toJson(this.loadAssArrangements(idCase)));

        return view;
    }

    private List<RelativeAbroadView> loadAssArrangements(Long idCase) {

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
        AdditionalFramingQuestions addQuest = existFraming.getAdditionalFramingQuestions();

        List<HearingFormat> lstFormats = caseRepository.findOne(idCase).getHearingFormats();
        Collections.sort(lstFormats, HearingFormat.hearingFormatComparator);

        HearingFormat lastFormat = lstFormats.get(lstFormats.size() - 1);

        List<RelativeAbroadView> lstAssArrg = new ArrayList<>();

        if (lastFormat.getAssignedArrangements() != null && lastFormat.getAssignedArrangements().size() > 0) {
            for (AssignedArrangement rel : lastFormat.getAssignedArrangements()) {
                RelativeAbroadView item = new RelativeAbroadView();

                item.setRelationshipId(rel.getArrangement().getId());
                item.setName(rel.getArrangement().getDescription() + ", " + rel.getDescription());
                item.setRelationshipId(rel.getArrangement().getId());
                item.setDescription("");
                item.setSelVal(false);
                lstAssArrg.add(item);
            }
        }

        if (addQuest != null && addQuest.getObligationIssues() != null && addQuest.getObligationIssues().size() > 0) {
            for (RelativeAbroadView vw : lstAssArrg) {
                for (ObligationIssues rel : addQuest.getObligationIssues()) {
                    if (vw.getRelationshipId().equals(rel.getArrangement().getId())) {
                        vw.setDescription(rel.getCause());
                        vw.setSelVal(true);
                        break;
                    }
                }
            }
        }

        return lstAssArrg;
    }

    private List<RelativeAbroadView> loadRelativeAbroad(Long idCase) {

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

        List<RelativeAbroadView> lstView = new ArrayList<>();

        AdditionalFramingQuestions addQuest = existFraming.getAdditionalFramingQuestions();

        for (Relationship relShip : relationshipRepository.findAll()) {
            RelativeAbroadView vw = new RelativeAbroadView();
            vw.setRelationshipId(relShip.getId());
            vw.setName(relShip.getName());
            vw.setSelVal(false);
            vw.setDescription("");
            lstView.add(vw);
        }

        if (addQuest != null && addQuest.getRelativesAbroadRel() != null && addQuest.getRelativesAbroadRel().size() > 0) {
            for (RelativeAbroadView vw : lstView) {
                for (RelativesAbroadRel rel : addQuest.getRelativesAbroadRel()) {
                    if (vw.getRelationshipId().equals(rel.getRelationship().getId())) {
                        vw.setDescription(rel.getAddress());
                        vw.setSelVal(true);
                        break;
                    }
                }
            }
        }

        return lstView;
    }

    private List<RelativeAbroadView> loadAddictedAcquaintance(Long idCase) {

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

        List<RelativeAbroadView> lstView = new ArrayList<>();

        AdditionalFramingQuestions addQuest = existFraming.getAdditionalFramingQuestions();

        for (Relationship relShip : relationshipRepository.findNotObsolete()) {
            RelativeAbroadView vw = new RelativeAbroadView();
            vw.setRelationshipId(relShip.getId());
            vw.setName(relShip.getName());
            vw.setSelVal(false);
            lstView.add(vw);
        }

        if (addQuest != null && addQuest.getAddictedAcquaintancesRel() != null && addQuest.getAddictedAcquaintancesRel().size() > 0) {
            for (RelativeAbroadView vw : lstView) {
                for (AddictedAcquaintanceRel rel : addQuest.getAddictedAcquaintancesRel()) {
                    if (vw.getRelationshipId().equals(rel.getRelationship().getId())) {
                        vw.setSelVal(true);
                        break;
                    }
                }
            }
        }

        return lstView;
    }

    private List<AddictedAcquaintanceRel> generateAddAqRel(AdditionalFramingQuestions existAQ, String lstStr) {

        List<AddictedAcquaintanceRel> newRel = new ArrayList<>();

        Type listType = new TypeToken<List<RelativeAbroadView>>() {
        }.getType();

        List<RelativeAbroadView> viewLst = new Gson().fromJson(lstStr, listType);

        for (RelativeAbroadView act : viewLst) {
            if (act.getSelVal() == true) {
                AddictedAcquaintanceRel rel = new AddictedAcquaintanceRel();
                rel.setAdditionalFramingQuestions(existAQ);
                rel.setRelationship(relationshipRepository.findOne(act.getRelationshipId()));
                newRel.add(rel);
            }
        }

        return newRel;
    }

    private List<ObligationIssues> generateObligationIssuesRel(AdditionalFramingQuestions existAQ, String lstStr) {

        List<ObligationIssues> newRel = new ArrayList<>();

        Type listType = new TypeToken<List<RelativeAbroadView>>() {
        }.getType();

        List<RelativeAbroadView> viewLst = new Gson().fromJson(lstStr, listType);

        for (RelativeAbroadView act : viewLst) {
            if (act.getSelVal() == true) {
                ObligationIssues rel = new ObligationIssues();
                rel.setAdditionalFramingQuestions(existAQ);
                rel.setArrangement(arrangementRepository.findOne(act.getRelationshipId()));
                rel.setCause(act.getDescription());
                newRel.add(rel);
            }
        }

        return newRel;
    }

    private List<RelativesAbroadRel> generateRelativeAbroadRel(AdditionalFramingQuestions existAQ, String lstStr) {

        List<RelativesAbroadRel> newRel = new ArrayList<>();

        Type listType = new TypeToken<List<RelativeAbroadView>>() {
        }.getType();

        List<RelativeAbroadView> viewLst = new Gson().fromJson(lstStr, listType);

        for (RelativeAbroadView act : viewLst) {
            if (act.getSelVal() == true) {
                RelativesAbroadRel rel = new RelativesAbroadRel();
                rel.setAdditionalFramingQuestions(existAQ);
                rel.setAddress(act.getDescription());
                rel.setRelationship(relationshipRepository.findOne(act.getRelationshipId()));
                newRel.add(rel);
            }
        }

        return newRel;

    }

    @Override
    @Transactional
    public ResponseMessage saveAddQuest(Long idCase, AdditionalQuestionsForView view) {

        try {
            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
            AdditionalFramingQuestions addQuest = existFraming.getAdditionalFramingQuestions();

            if (addQuest == null) {
                addQuest = new AdditionalFramingQuestions();
            }

            addQuest.setObservations(view.getObservations());
            addQuest.setAddictionTreatment(view.getAddictionTreatment());
            addQuest.setAddictionTreatmentInstitute(view.getAddictionTreatmentInstitute());
            addQuest.setAddictionTreatmentDate(view.getAddictionTreatmentDate());
            addQuest.setAddictedAcquaintance(view.getAddictedAcquaintance());
            addQuest.setRelativeAbroad(view.getRelativeAbroad());
            addQuest.setObligationIssue(view.getObligationIssue());

            if (addQuest.getAddictedAcquaintancesRel() != null) {

                List<AddictedAcquaintanceRel> addAqRel = existFraming.getAdditionalFramingQuestions().getAddictedAcquaintancesRel();

                for (AddictedAcquaintanceRel rel : addAqRel) {
                    addictedAcquaintanceRelRepository.delete(rel);
                }
            }
            addQuest.setAddictedAcquaintancesRel(this.generateAddAqRel(addQuest, view.getSelectedAddictedAcquaintances()));


            if (addQuest.getRelativesAbroadRel() != null) {

                List<RelativesAbroadRel> addAqRel = existFraming.getAdditionalFramingQuestions().getRelativesAbroadRel();

                for (RelativesAbroadRel rel : addAqRel) {
                    relativesAbroadRelRepository.delete(rel);
                }
            }
            addQuest.setRelativesAbroadRel(this.generateRelativeAbroadRel(addQuest, view.getSelectedRelativesAbroad()));

            if (addQuest.getObligationIssues() != null) {

                List<ObligationIssues> addOblIss = existFraming.getAdditionalFramingQuestions().getObligationIssues();

                for (ObligationIssues rel : addOblIss) {
                    obligationIssuesRepository.delete(rel);
                }
            }
            addQuest.setObligationIssues(this.generateObligationIssuesRel(addQuest, view.getSelectedObligationIssues()));

            existFraming.setAdditionalFramingQuestions(addQuest);

            this.save(existFraming);

            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");

        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveAddQuest", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }

    }

    @Autowired
    private SystemSettingRepository systemSettingRepository;

    @Override
    @Transactional
    public ResponseMessage doTerminate(Long idCase) {


        try {
            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
            TerminateMeetingMessageDto validate = new TerminateMeetingMessageDto();
            List<String> lsSN = new ArrayList<>();
            List<String> lsR = new ArrayList<>();
            if (existFraming.getPersonalData() == null) {
                List<String> ls = new ArrayList<>();
                ls.add("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Datos personales y entorno social\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("imputed", ls));
            }

            if (existFraming.getFramingAddresses() == null || !(existFraming.getFramingAddresses().size() > 0)) {
                List<String> ls = new ArrayList<>();
                ls.add("Debe capturar al menos un registro en la secci&oacute;n \"Domicilios\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("imputedHome", ls));
            }
//            if (existFraming.getProcessAccompaniment() == null)
//                if (sb.toString().equals(""))
//                    sb.append("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Persona que acompa?a en el proceso\".");
//                else
//                    sb.append("|Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Persona que acompa?a en el proceso\".");

            if (existFraming.getReferences() != null && existFraming.getReferences().size() > 0) {
                int noHousemate = 0, noReferences = 0, noVictims = 0;

                for (FramingReference fr : existFraming.getReferences()) {
                    if (fr.getPersonType() != null) {
                        if (fr.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE))
                            noHousemate++;
                        else if (fr.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE))
                            noReferences++;
                        else if (fr.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_VICTIM) || fr.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_WITNESS))
                            noVictims++;
                    }
                }

                if (noHousemate == 0) {
                    lsSN.add("Debe capturar al menos una registro en en la secci&oacute;n \"Personas que viven con el imputado\".");
                }
                if (noReferences == 0) {
                    lsR.add("Debe capturar al menos una registro en en la secci&oacute;n \"Referencias personales\".");
                }
                if (noVictims == 0) {
                    List<String> ls = new ArrayList<>();
                    ls.add("Debe capturar al menos una registro en en la secci&oacute;n \"V&iacute;ctimas o testigos\".");
                    validate.getGroupMessage().add(new GroupMessageMeetingDto("victim", ls));
                }

            } else {
                lsSN.add("Debe capturar al menos una registro en en la secci&oacute;n \"Personas que viven con el imputado\".");
                lsR.add("Debe capturar al menos una registro en en la secci&oacute;n \"Referencias personales\".");
            }

            int bandHM = 0;
            int bandREF = 0;

            if (existFraming.getReferences() != null && existFraming.getReferences().size() > 0) {
                for (FramingReference act : existFraming.getReferences()) {
                    if (act.getIsAccompaniment() != null && act.getIsAccompaniment() == true && act.getAccompanimentInfo() == null) {
                        if (act.getPersonType() != null && act.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE))
                            bandHM++;
                        else if (act.getPersonType() != null && act.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE))
                            bandREF++;
                    }
                }
            }

            if (bandHM > 0) {
                lsSN.add("Alguna persona registrada en la secci&oacute;n \"Personas que viven con el imputado\" ha sido marcada como acompa&ntilde;ante durante el proceso. Debe capturar la informaci&oacute;n adicional requerida.");
            }

            if (bandREF > 0) {
                lsR.add("Alguna persona registrada en la secci&oacute;n \"Referencias personales\" ha sido marcada como acompa&ntilde;ante durante el proceso. Debe capturar la informaci&oacute;n adicional requerida.");
            }

            if (lsSN.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("socialNetwork", lsSN));
            }
            if (lsR.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("reference", lsR));
            }

//            if (existFraming.getOccupation() == null || existFraming.getRelFramingMeetingActivities() == null || !(existFraming.getRelFramingMeetingActivities().size() > 0)) {
//                List<String> ls = new ArrayList<>();
//                ls.add("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Actividades que realiza el imputado\".");
//                validate.getGroupMessage().add(new GroupMessageMeetingDto("activities", ls));
//            }
            if (existFraming.getDrugs() == null || !(existFraming.getDrugs().size() > 0)) {
                List<String> ls = new ArrayList<>();
                ls.add("Debe capturar al menos una registro en en la secci&oacute;n \"Consumo de sustancias\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("drug", ls));
            }
            if (existFraming.getSelectedSourcesRel() == null || !(existFraming.getSelectedSourcesRel().size() > 0) ||
                    existFraming.getSelectedRisksRel() == null || !(existFraming.getSelectedRisksRel().size() > 0) ||
                    existFraming.getSelectedThreatsRel() == null || !(existFraming.getSelectedThreatsRel().size() > 0)) {
                List<String> ls = new ArrayList<>();
                ls.add("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"An&aacute;isis del entorno\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("analysis", ls));
            }
            if (existFraming.getAdditionalFramingQuestions() == null) {
                List<String> ls = new ArrayList<>();
                ls.add("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Formulario de preguntas al supervisado\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("question", ls));
            }

            if (existFraming.getAdditionalFramingQuestions() == null) {
                List<String> ls = new ArrayList<>();
                ls.add("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Formulario de preguntas al supervisado\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("question", ls));
            }

            String cad = systemSettingRepository.findOneValue("FINGER_VAL", "ValidateFingerPrint");
            Boolean validateFingerprint = null;

            if (cad != null && !cad.trim().equals(""))
                validateFingerprint = Boolean.valueOf(cad);

            if (validateFingerprint != null && validateFingerprint == true)
                if (!(framingMeetingRepository.getFingerIdsByImputed(existFraming.getCaseDetention().getMeeting().getImputed().getId()).size() > 0)) {
                    List<String> ls = new ArrayList<>();
                    ls.add("Debe capturar al menos una huella dactilar en la secci&oacute;n \"Enrolamiento\".");
                    validate.getGroupMessage().add(new GroupMessageMeetingDto("fingerprint", ls));
                }

            if (existFraming.getSchool() == null) {
                List<String> arrMsg = new ArrayList<>();
                arrMsg.add(sharedUserService.convertToValidString("Debe proporcionar la información faltante en la sección \"Historia escolar\"."));
                validate.getGroupMessage().add(new GroupMessageMeetingDto("school", arrMsg));
            }

            if (existFraming.getJobs() == null || !(existFraming.getJobs().size() > 0)) {
                List<String> arrMsg = new ArrayList<>();
                arrMsg.add("Debe capturar al menos un trabajo en la secci&oacute;n \"Historia laboral\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("job", arrMsg));
            }

            if (existFraming.getActivities() == null || !(existFraming.getActivities().size() > 0)) {
                List<String> arrMsg = new ArrayList<>();
                arrMsg.add("Debe capturar al menos una actividad en la secci&oacute;n \"Actividades que realiza el imputado\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("activities", arrMsg));
            }

            if (validate.existsMessageProperties()) {
                List<String> listGeneral = new ArrayList<>();
                listGeneral.add(sharedUserService.convertToValidString("No se puede terminar la entrevista ya que faltan preguntas por responder, para m&aacute;s detalles revise los mensajes de cada secci&oacute;n"));
                validate.getGroupMessage().add(new GroupMessageMeetingDto("general", listGeneral));
                Gson gson = new Gson();
                validate.formatMessages();
                return new ResponseMessage(true, gson.toJson(validate));
            }

            Case existCase = caseRepository.findOne(idCase);
            existCase.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_FRAMING_COMPLETE));
            existCase.getFramingMeeting().setIsTerminated(true);
            existCase.getFramingMeeting().setEndDate(new Date());

            //para agregar al imputado en el listado de fuentes para las actividades

            if (framingReferenceRepository.findImputedReference(existFraming.getId(), Constants.NAME_RELATIONSHIP_IMPUTED) == null) {
                FramingReference imputedReference = new FramingReference();

                imputedReference.setRelationship(relationshipRepository.findImputedRelationship());
                imputedReference.setFramingMeeting(existFraming);
                imputedReference.setName(existFraming.getPersonalData().getName() + " " + existFraming.getPersonalData().getLastNameP() + " " + existFraming.getPersonalData().getLastNameM());
                imputedReference.setPersonType("");
                imputedReference = framingReferenceRepository.save(imputedReference);
                //caseRepository.save(existCase);

                FramingSelectedSourceRel imputedSourceRel = new FramingSelectedSourceRel();
                imputedSourceRel.setFramingMeeting(existFraming);
                existFraming.getSelectedSourcesRel().add(imputedSourceRel);
                imputedSourceRel.setFramingReference(imputedReference);
                imputedSourceRel = framingSelectedSourceRelRepository.save(imputedSourceRel);
                //para agregar al imputado en el listado de fuentes para las actividades
            }
            caseRepository.save(existCase);


            StringBuilder sb = new StringBuilder();
            sb.append("Entrevista de encuadre terminada: ");
            sb.append(existCase.getIdFolder());
            sb.append(". Comentario: ");
            sb.append("Debe asignar el caso a un supervisor para generar el plan de monitoreo");
            sb.append(".");

            SharedLogCommentService.generateLogComment(sb.toString(), userRepository.findOne(sharedUserService.GetLoggedUserId()),
                    existCase, MonitoringConstants.STATUS_PENDING_CREATION, null, MonitoringConstants.TYPE_COMMENT_ASSIGNED_CASE, logCommentRepository);

            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito");

        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doTerminate", sharedUserService);
            e.printStackTrace();
            return new ResponseMessage(false, "Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde.");
        }
    }

    @Autowired
    private ImputedHomeRepository imputedHomeRepository;

    @Override
    @Transactional
    public void fillSaveVerifiedInfo(FramingMeeting existFraming, Meeting verifMeeting) {

        //actividades

        List<RelSocialEnvironmentActivity> relAct = verifMeeting.getSocialEnvironment().getRelSocialEnvironmentActivities();

        if (relAct != null && relAct.size() > 0) {
            List<RelFramingMeetingActivity> relFramingMeetingActivities = new ArrayList<>();

            for (RelSocialEnvironmentActivity rel : relAct) {
                RelFramingMeetingActivity actRel = new RelFramingMeetingActivity();
                actRel.setFramingMeeting(existFraming);
                actRel.setSpecification(rel.getSpecification());
                actRel.setActivity(rel.getActivity());
                relFramingMeetingActivities.add(actRel);
            }

            existFraming.setRelFramingMeetingActivities(relFramingMeetingActivities);
        }

        //domicilios
        List<FramingAddress> listAddress = new ArrayList<>();

        for (ImputedHome act : verifMeeting.getImputedHomes()) {
            FramingAddress framingAddress = new FramingAddress();
            framingAddress.setFramingMeeting(existFraming);

            Address existAddress = act.getAddress();

            Address address = new Address();
            address.setLng(existAddress.getLng());
            address.setLat(existAddress.getLat());
            address.setInnNum(existAddress.getInnNum());
            address.setOutNum(existAddress.getOutNum());
            address.setStreet(existAddress.getStreet());
            address.setLocation(existAddress.getLocation());
            address.setAddressString(existAddress.getAddressString());

            framingAddress.setAddress(address);

            listAddress.add(framingAddress);

        }

        existFraming.setFramingAddresses(listAddress);

        //personas que viven con el imputado y referencias personales
        List<FramingReference> references = new ArrayList<>();

        for (PersonSocialNetwork person : verifMeeting.getSocialNetwork().getPeopleSocialNetwork()) {

            if (person.getLivingWith().getId() == Constants.ELECTION_YES) {

                FramingReference fRef = new FramingReference();

                fRef.setFramingMeeting(existFraming);
                fRef.setPersonType(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE);
                fRef.setAddress(person.getAddress());
                fRef.setName(person.getName());
                fRef.setAge(person.getAge().toString());
                fRef.setPhone(person.getPhone());
                fRef.setRelationship(person.getRelationship());
                fRef.setIsAccompaniment(person.getIsAccompaniment());

                List<String> actualHomes = imputedHomeRepository.getActualAddressStringByVerificationId(verifMeeting.getId(), new PageRequest(0, 1));

                if (actualHomes != null && actualHomes.size() > 0)
                    fRef.setAddress(actualHomes.get(0));
                else
                    fRef.setAddress(null);

                references.add(fRef);

            }
        }

        for (Reference reference : verifMeeting.getReferences()) {

            FramingReference fRef = new FramingReference();

            fRef.setFramingMeeting(existFraming);
            fRef.setPersonType(FramingMeetingConstants.PERSON_TYPE_REFERENCE);
            fRef.setAddress(reference.getAddress());
            fRef.setName(reference.getFullName());
            fRef.setAge(reference.getAge().toString());
            fRef.setPhone(reference.getPhone());
            fRef.setRelationship(reference.getRelationship());
            fRef.setIsAccompaniment(reference.getIsAccompaniment());

            references.add(fRef);
        }

        existFraming.setReferences(references);

        List<Drug> drugs = new ArrayList<>();

        for (Drug drug : verifMeeting.getDrugs()) {

            Drug fDrug = new Drug();

            fDrug.setFramingMeeting(existFraming);
            fDrug.setDrugType(drug.getDrugType());
            fDrug.setPeriodicity(drug.getPeriodicity());
            fDrug.setQuantity(drug.getQuantity());
            fDrug.setLastUse(drug.getLastUse());
            fDrug.setSpecificationPeriodicity(drug.getSpecificationPeriodicity());

            drugs.add(fDrug);
        }

        existFraming.setDrugs(drugs);

        framingMeetingRepository.save(existFraming);

    }


    public ResponseMessage upsertComments(Long idCase, Integer commentType, String comments) {
        ResponseMessage resp = new ResponseMessage();

        try {
            Case existCase = caseRepository.findOne(idCase);

            if (existCase != null && existCase.getFramingMeeting() != null) {
                switch (commentType) {
                    case 1:
                        existCase.getFramingMeeting().setAddressComments(comments);
                        resp.setMessage("1|Se ha guardado la informaci&oacute;n con &eacute;xito");
                        break;
                    case 2:
                        existCase.getFramingMeeting().setHousemateComments(comments);
                        resp.setMessage("2|Se ha guardado la informaci&oacute;n con &eacute;xito");
                        break;
                    case 3:
                        existCase.getFramingMeeting().setReferencesComments(comments);
                        resp.setMessage("3|Se ha guardado la informaci&oacute;n con &eacute;xito");
                        break;
                    case 4:
                        existCase.getFramingMeeting().setDrugsComments(comments);
                        resp.setMessage("4|Se ha guardado la informaci&oacute;n con &eacute;xito");
                        break;
                    case 5:
                        existCase.getFramingMeeting().setActivitiesComments(comments);
                        resp.setMessage("5|Se ha guardado la informaci&oacute;n con &eacute;xito");
                        break;
                    case 6:
                        existCase.getFramingMeeting().setJobComments(comments);
                        resp.setMessage("6|Se ha guardado la informaci&oacute;n con &eacute;xito");
                        break;
                    case 7:
                        existCase.getFramingMeeting().setVictimComments(comments);
                        resp.setMessage("7|Se ha guardado la informaci&oacute;n con &eacute;xito");
                        break;
                }
                caseRepository.save(existCase);

                resp.setHasError(false);

            } else {
                resp.setHasError(false);
                resp.setMessage("Ha ocurrido un error. Intente m&acute;s tarde");
            }
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "upsertComments", sharedUserService);
            resp.setHasError(false);
            resp.setMessage("Ha ocurrido un error. Intente m&acute;s tarde");
        } finally {
            return resp;
        }
    }

    @Autowired
    private FramingActivityRepository framingActivityRepository;

    public FramingActivityView fillActivityForView(Long idActivity, Long idCase) {

        FramingActivity fAct = null;

        if (idActivity != null)
            fAct = framingActivityRepository.findOne(idActivity);

        FramingActivityView view = new FramingActivityView();
        view.setIdCase(idCase);

        if (fAct != null) {
            view.setId(fAct.getId());
            view.setIdActivity(fAct.getActivity().getId());
            view.setDescription(fAct.getDescription());

            List<ScheduleDto> lstScheduleDto = new ArrayList<>();

            for (Schedule actSch : fAct.getListSchedule()) {
                ScheduleDto schDto = new ScheduleDto();
                schDto.setDay(actSch.getDay());
                schDto.setStart(actSch.getStart());
                schDto.setEnd(actSch.getEnd());
                lstScheduleDto.add(schDto);
            }

            Gson conv = new Gson();
            view.setLstSchedule(conv.toJson(lstScheduleDto));
        }

        return view;
    }

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Transactional
    @Override
    public ResponseMessage saveFramingActivity(FramingActivityView view, Long idCase) {

        ResponseMessage resp = new ResponseMessage();

        try {
            FramingActivity exisAct = new FramingActivity();

            if (view.getId() != null && view.getId() > 0) {
                for (Schedule actSch : scheduleRepository.getSchedulesActivty(view.getId())) {
                    actSch.setFramingActivity(null);
                    scheduleRepository.delete(actSch);
                }
                exisAct = framingActivityRepository.findOne(view.getId());
                exisAct.setListSchedule(null);
                framingActivityRepository.save(exisAct);
            }

            exisAct.setDescription(view.getDescription());
            exisAct.setActivity(activityRepository.findOne(view.getIdActivity()));

            List<Schedule> lstSchedule = new ArrayList<>();
            List<ScheduleDto> lstSchDto = new Gson().fromJson(view.getLstSchedule(), new TypeToken<List<ScheduleDto>>() {
            }.getType());
            ;

            for (ScheduleDto actDto : lstSchDto) {
                Schedule newObj = new Schedule();
                newObj.setDay(actDto.getDay());
                newObj.setStart(actDto.getStart());
                newObj.setEnd(actDto.getEnd());
                newObj.setFramingActivity(exisAct);
                lstSchedule.add(newObj);
            }

            exisAct.setListSchedule(lstSchedule);
            exisAct.setFramingMeeting(caseRepository.findOne(idCase).getFramingMeeting());

            framingActivityRepository.save(exisAct);
            resp.setHasError(false);
            resp.setMessage("Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveFramingActivity", sharedUserService);
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error. Intente m&aacute;s tarde.");
        } finally {
            return resp;
        }
    }

    @Transactional
    @Override
    public ResponseMessage deleteFramingActivity(Long id) {

        ResponseMessage resp = new ResponseMessage();

        try {
            FramingActivity exisAct = framingActivityRepository.findOne(id);

            for (Schedule actSch : scheduleRepository.getSchedulesActivty(id)) {
                actSch.setFramingActivity(null);
                scheduleRepository.delete(actSch);
            }
            exisAct.setListSchedule(null);
            framingActivityRepository.delete(exisAct);
            resp.setHasError(false);
            resp.setMessage("Se ha eliminado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteFramingActivity", sharedUserService);
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error. Intente m&aacute;s tarde.");
        } finally {
            return resp;
        }
    }


    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RegisterTypeRepository registerTypeRepository;

    @Override
    public JobDto fillJobForView(Long idJob, Long idCase) {
        JobDto view = new JobDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        if (idJob != null && idJob > 0) {

            Job existJob = jobRepository.findOne(idJob);
            view.setId(existJob.getId());

            view.setPost(existJob.getPost());
            view.setNameHead(existJob.getNameHead());
            view.setCompany(existJob.getCompany());
            view.setPhone(existJob.getPhone());
            view.setAddress(existJob.getAddress());
            view.setRegisterTypeId(existJob.getRegisterType().getId());
            view.setBlock(existJob.getBlock());

            if (existJob.getBlock() == true) {
                if (existJob.getStart() != null)
                    view.setStart(sdf.format(existJob.getStart()));
                if (existJob.getStartPrev() != null)
                    view.setStartPrev(sdf.format(existJob.getStartPrev()));
                if (existJob.getEnd() != null)
                    view.setEnd(sdf.format(existJob.getEnd()));

                view.setSalaryWeek(existJob.getSalaryWeek());
                view.setReasonChange(existJob.getReasonChange());


                List<Schedule> lstExistSch = jobRepository.getJobSchedule(idJob);
                List<ScheduleDto> lstDtoSch = new ArrayList<>();

                for (Schedule act : lstExistSch) {
                    ScheduleDto newDto = new ScheduleDto();
                    newDto.setStart(act.getStart());
                    newDto.setEnd(act.getEnd());
                    newDto.setDay(act.getDay());
                    lstDtoSch.add(newDto);
                }

                view.setSchedule(new Gson().toJson(lstDtoSch));
            }
        } else
            view.setBlock(true);

        view.setIdCase(idCase);

        return view;
    }

    @Transactional
    @Override
    public ResponseMessage saveFramingJob(JobDto view, Long idCase) {

        ResponseMessage resp = new ResponseMessage();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Job existJob = new Job();

            if (view.getId() != null && view.getId() > 0) {
                for (Schedule actSch : jobRepository.getJobSchedule(view.getId())) {
                    actSch.setJob(null);
                    scheduleRepository.delete(actSch);
                }
                existJob = jobRepository.findOne(view.getId());
                existJob.setSchedule(null);
                jobRepository.save(existJob);
            }

            existJob.setPost(view.getPost());
            existJob.setNameHead(view.getNameHead());
            existJob.setCompany(view.getCompany());
            existJob.setPhone(view.getPhone());
            existJob.setAddress(view.getAddress());
            existJob.setBlock(view.getBlock());
            existJob.setRegisterType(registerTypeRepository.findOne(view.getRegisterTypeId()));

            if (view.getBlock() == true) {
                if (view.getRegisterTypeId() != 3) {
                    existJob.setStart(sdf.parse(view.getStart()));
                    existJob.setSalaryWeek(view.getSalaryWeek());

                    List<Schedule> lstSchedule = new ArrayList<>();
                    List<ScheduleDto> lstSchDto = new Gson().fromJson(view.getSchedule(), new TypeToken<List<ScheduleDto>>() {
                    }.getType());
                    ;

                    for (ScheduleDto actDto : lstSchDto) {
                        Schedule newObj = new Schedule();
                        newObj.setDay(actDto.getDay());
                        newObj.setStart(actDto.getStart());
                        newObj.setEnd(actDto.getEnd());
                        newObj.setJob(existJob);
                        lstSchedule.add(newObj);
                    }

                    existJob.setSchedule(lstSchedule);
                    existJob.setStartPrev(null);
                    existJob.setEnd(null);
                    existJob.setReasonChange(null);
                } else {
                    existJob.setStartPrev(sdf.parse(view.getStartPrev()));
                    existJob.setEnd(sdf.parse(view.getEnd()));
                    existJob.setReasonChange(view.getReasonChange());
                    existJob.setStart(null);
                    existJob.setSalaryWeek(null);
                    existJob.setSchedule(null);
                }
            }

            existJob.setFramingMeeting(caseRepository.findOne(idCase).getFramingMeeting());

            jobRepository.save(existJob);
            resp.setHasError(false);
            resp.setMessage("Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveFramingJob", sharedUserService);
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error. Intente m&aacute;s tarde.");
        } finally {
            return resp;
        }
    }

    @Transactional
    @Override
    public ResponseMessage deleteFramingJob(Long id) {

        ResponseMessage resp = new ResponseMessage();

        try {
            Job existJob = jobRepository.findOne(id);

            for (Schedule actSch : jobRepository.getJobSchedule(id)) {
                actSch.setJob(null);
                scheduleRepository.delete(actSch);
            }
            existJob.setSchedule(null);
            jobRepository.delete(existJob);
            resp.setHasError(false);
            resp.setMessage("Se ha eliminado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteFramingActivity", sharedUserService);
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error. Intente m&aacute;s tarde.");
        } finally {
            return resp;
        }
    }

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    public SchoolDto fillSchoolForView(Long idCase) {
        SchoolDto view = new SchoolDto();
        view.setIdCase(idCase);

        School existSchool = schoolRepository.getSchoolByIdCase(idCase);

        if (existSchool != null) {

            view.setId(existSchool.getId());
            view.setName(existSchool.getName());
            view.setPhone(existSchool.getPhone());
            view.setAddress(existSchool.getAddress());
            view.setSpecification(existSchool.getSpecification());
            view.setCommentSchool(existSchool.getFramingMeeting().getSchoolComments());
            view.setHasActualSchool(existSchool.getBlock());
            view.setAcademicLvlId(existSchool.getDegree().getAcademicLevel().getId());
            view.setDegreeId(existSchool.getDegree().getId());

            List<ScheduleDto> lstSchDto = new ArrayList<>();

            for (Schedule schedule : existSchool.getSchedule()) {
                ScheduleDto newDto = new ScheduleDto();
                newDto.setDay(schedule.getDay());
                newDto.setStart(schedule.getStart());
                newDto.setEnd(schedule.getEnd());
                lstSchDto.add(newDto);
            }

            view.setSchedule(new Gson().toJson(lstSchDto));
        }

        return view;
    }

    @Transactional
    @Override
    public ResponseMessage saveSchool(SchoolDto view) {
        ResponseMessage response = new ResponseMessage();
        try {

            School existSchool = new School();

            if (view.getId() != null) {
                existSchool = schoolRepository.findOne(view.getId());
                for (Schedule act : existSchool.getSchedule()) {
                    act.setSchool(null);
                    scheduleRepository.delete(act);
                }
                existSchool.setSchedule(null);
                schoolRepository.save(existSchool);
            }

            existSchool.setName(view.getName());
            existSchool.setPhone(view.getPhone());
            existSchool.setAddress(view.getAddress());
            existSchool.setDegree(degreeRepository.findOne(view.getDegreeId()));
            existSchool.setBlock(view.getHasActualSchool());

            List<ScheduleDto> lstSchDto = new Gson().fromJson(view.getSchedule(), new TypeToken<List<ScheduleDto>>() {
            }.getType());
            ;

            List<Schedule> lstSchedule = new ArrayList<>();

            for (ScheduleDto act : lstSchDto) {
                Schedule newSch = new Schedule();
                newSch.setDay(act.getDay());
                newSch.setStart(act.getStart());
                newSch.setEnd(act.getEnd());
                newSch.setSchool(existSchool);
                lstSchedule.add(newSch);
            }

            FramingMeeting existFraming = caseRepository.findOne(view.getIdCase()).getFramingMeeting();
            existFraming.setSchoolComments(view.getCommentSchool());

            framingMeetingRepository.save(existFraming);

            existSchool.setSchedule(lstSchedule);
            existSchool.setFramingMeeting(existFraming);

            schoolRepository.save(existSchool);

            response.setHasError(false);
            response.setMessage("Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveSchool", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error al guardar la informaci&oacute;n. Intente m&aacute;s tarde");
        } finally {
            return response;
        }
    }

}