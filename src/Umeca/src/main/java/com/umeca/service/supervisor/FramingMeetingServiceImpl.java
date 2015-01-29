package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.Convert;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Degree;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.dto.ScheduleDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.GroupMessageMeetingDto;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.reviewer.dto.TerminateMeetingMessageDto;
import com.umeca.model.entities.shared.Victim;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
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
import java.util.*;

@Service("framingMeetingService")
public class FramingMeetingServiceImpl implements FramingMeetingService {

    @Qualifier("qFramingMeetingRepository")
    @Autowired
    private FramingMeetingRepository framingMeetingRepository;

    @Autowired
    private StatusCaseRepository statusCaseRepository;

    @Autowired
    private FramingMeetingLogRepository framingMeetingLogRepository;

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

    private List<FramingSelectedSourceRel> generateSourceRel(Long idCase, String lstJson, List<Long> lstDependentSources) {

        Type listType = new TypeToken<List<Long>>() {
        }.getType();

        List<Long> ids = new Gson().fromJson(lstJson, listType);

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

        List<FramingSelectedSourceRel> sourceRel = new ArrayList<>();

        for (Long currId : ids) {
            if (!lstDependentSources.contains(currId)) {
                FramingSelectedSourceRel rel = new FramingSelectedSourceRel();
                rel.setFramingMeeting(existFraming);

                FramingReference existRef = framingReferenceRepository.findOne(currId);

                if (existRef != null) {
                    rel.setFramingReference(existRef);
                    sourceRel.add(rel);
                }
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

            Iterator<FramingSelectedSourceRel> lstExistSources = existFraming.getSelectedSourcesRel().iterator();
            List<Long> lstDependentSourceRel = framingReferenceRepository.findDependentSourceRel(idCase);
            List<Long> lstDependentSources = framingReferenceRepository.findDependentReferences(idCase);

            while (lstExistSources.hasNext()) {
                FramingSelectedSourceRel sel = lstExistSources.next();
                if (!lstDependentSourceRel.contains(sel.getId()) &&
                        !sel.getFramingReference().getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_IMPUTED) &&
                        !sel.getFramingReference().getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_OTHER)) {

                    lstExistSources.remove();
                    sel.setFramingReference(null);
                    sel.setFramingMeeting(null);
                    framingSelectedSourceRelRepository.delete(sel);
                }
            }

            existFraming.getSelectedSourcesRel().addAll((this.generateSourceRel(idCase, view.getLstSelectedSources(), lstDependentSources)));

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

            if (existFraming.getIsTerminated() != null && existFraming.getIsTerminated().equals(true))
                framingMeetingLogRepository.save(getEnvironmentAnalysisLog(existFraming, view, FramingMeetingConstants.LOG_TYPE_MODIFIED));

            framingMeetingRepository.flush();

            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            e.printStackTrace();
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

            Boolean isTerm = existCase.getFramingMeeting().getIsTerminated();

            if (isTerm != null && isTerm.equals(true)) {
                String logType = "";
                if (newReference.getId() != null && newReference.getId() > 0)
                    logType = FramingMeetingConstants.LOG_TYPE_MODIFIED;
                else
                    logType = FramingMeetingConstants.LOG_TYPE_ADDED;

                framingMeetingLogRepository.save(getFramingReferenceLog(existCase.getFramingMeeting(), newReference, logType));
            }

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

            Boolean isNew = true;

            List<AccompanimentInfo> lstAccomInf = accompanimentInfoRepository.getAccompanimentInfoByIdRef(newReference.getId(), new PageRequest(0, 1));

            AccompanimentInfo accompanimentInfo = null;

            if (newReference.getId() != null && newReference.getId() > 0)
                isNew = false;

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

            FramingMeeting existFraming = existCase.getFramingMeeting();

            if (existFraming.getIsTerminated() != null && existFraming.getIsTerminated().equals(true)) {
                String logType;

                if (isNew == true)
                    logType = FramingMeetingConstants.LOG_TYPE_ADDED;
                else
                    logType = FramingMeetingConstants.LOG_TYPE_MODIFIED;

                framingMeetingLogRepository.save(getFramingReferenceLog(existFraming, newReference, logType));
            }

            newReference.setFramingMeeting(existFraming);

            framingReferenceRepository.save(newReference);
            return new ResponseMessage(false, "Se ha guardado la informaci&oacute;n con &eacute;xito.");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "saveReference", sharedUserService);
            e.printStackTrace();
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
        view.setLstSources(conv.toJson(framingReferenceRepository.getAccompanimentRefByIdCase(idCase)));

        List<FramingRisk> lstRisks = framingRiskRepository.findNoObsolete();
        view.setLstRisk(conv.toJson(lstRisks));

        List<FramingThreat> lstThreat = framingThreatRepository.findAll();
        view.setLstThreat(conv.toJson(lstThreat));

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

        view.setEnvironmentComments(existFraming.getEnvironmentComments());

        List<Long> lstSelectedSources = framingReferenceRepository.findSelectedReferences(idCase, new ArrayList<String>() {{
            add(FramingMeetingConstants.PERSON_TYPE_IMPUTED);
            add(FramingMeetingConstants.PERSON_TYPE_OTHER);
        }});
        view.setLstSelectedSources(conv.toJson(lstSelectedSources));

        List<Long> lstDependentSources = framingReferenceRepository.findDependentReferences(idCase);
        view.setLstDependentSources(conv.toJson(lstDependentSources));

        List<Long> lstSelectedRisk = framingRiskRepository.findSelectedRisk(idCase);
        view.setLstSelectedRisk(conv.toJson(lstSelectedRisk));

        List<Long> lstSelectedThreat = framingThreatRepository.findSelectedThreat(idCase);
        view.setLstSelectedThreat(conv.toJson(lstSelectedThreat));

        List<HearingFormat> lstFormats = caseRepository.findOne(idCase).getHearingFormats();
        Collections.sort(lstFormats, HearingFormat.hearingFormatComparator);
        HearingFormat lastFormat = lstFormats.get(lstFormats.size() - 1);

        List<String> lstAssArrg = arrangementRepository.findArrangementsByIdFormat(lastFormat.getId());
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
            obj.setAddressStr(existAddress.getAddress().getAddressString());
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
            Boolean isNew = true;
            FramingAddress existFramingAddress = null;
            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

            if (view.getId() != null) {
                isNew = false;
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
                existFramingAddress.setFramingMeeting(existFraming);
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

            if (existFraming.getIsTerminated() == true) {
                String logType = "";
                view.setAddressStr(existFramingAddress.getAddress().getAddressString());

                if (isNew == true)
                    logType = FramingMeetingConstants.LOG_TYPE_ADDED;
                else
                    logType = FramingMeetingConstants.LOG_TYPE_MODIFIED;

                framingMeetingLogRepository.save(getFramingAddressLog(existFraming, view, logType));
            }

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

            FramingAddress existAddress = framingAddressRepository.findOne(id);

            Boolean isTerminated = existAddress.getFramingMeeting().getIsTerminated();
            if (isTerminated != null && isTerminated == true) {
                FramingMeetingLog log = new FramingMeetingLog();
                log.setFramingMeeting(existAddress.getFramingMeeting());
                log.setLogType(FramingMeetingConstants.LOG_TYPE_DELETED);
                log.setLogDate(CalendarExt.getToday());
                log.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
                log.setTitle("Domicilio");
                FramingLogElement element = new FramingLogElement();
                element.setFieldName("Direcci&oacute;n");
                element.setValue(existAddress.getAddress().getAddressString());
                log.setFinalValue(new Gson().toJson(element));
                framingMeetingLogRepository.save(log);
            }

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

            FramingReference existReference = framingReferenceRepository.findOne(id);

            if (existReference.getFramingMeeting().getIsTerminated() != null && existReference.getFramingMeeting().getIsTerminated() == true)
                framingMeetingLogRepository.save(getFramingReferenceLog(existReference.getFramingMeeting(), existReference, FramingMeetingConstants.LOG_TYPE_DELETED));

            framingReferenceRepository.delete(existReference);

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
            Boolean isNew = false;
            drug.setDrugType(drugTypeRepository.findOne(drug.getDrugType().getId()));
            drug.setPeriodicity(periodicityRepository.findOne(drug.getPeriodicity().getId()));
            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
            drug.setFramingMeeting(existFraming);
            if (drug.getId() != null && drug.getId() == 0) {
                drug.setId(null);
                isNew = true;
            }

            if (existFraming.getIsTerminated() == true) {
                String logType;
                if (isNew == true)
                    logType = FramingMeetingConstants.LOG_TYPE_ADDED;
                else
                    logType = FramingMeetingConstants.LOG_TYPE_MODIFIED;

                framingMeetingLogRepository.save(getDrugLog(existFraming, drug, logType));
            }

            drugRepository.save(drug);
            result.setHasError(false);
            result.setMessage("Se ha guardado la informaci&oacute;n con &eacute;xito");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "doUpsertDrug", sharedUserService);
            result.setHasError(true);
            result.setMessage("Ocurri&oacute; un error al guardar la informaci&oacute;n. Int&eacute;nte m&aacute;s tarde.");
        }
        return result;
    }

    @Override
    @Transactional
    public ResponseMessage deleteDrug(Long id) {
        ResponseMessage result = new ResponseMessage();
        Drug existDrug = drugRepository.findOne(id);
        FramingMeeting existFraming = existDrug.getFramingMeeting();
        try {

            if (existFraming.getIsTerminated() == true) {
                framingMeetingLogRepository.save(getDrugLog(existFraming, existDrug, FramingMeetingConstants.LOG_TYPE_DELETED));
            }

            drugRepository.delete(existDrug);
            result.setHasError(false);
            result.setMessage("Se elimin&oacute; la sustancia con &eacute;xito");
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "deleteDrug", sharedUserService);
            result.setHasError(true);
            result.setMessage("Ocurri&oacute; un error al eliminar la sustancia. Int&eacute;nte m&aacute;s tarde");
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

            if (existFraming.getIsTerminated() == true) {
                framingMeetingLogRepository.save(getAdditionalQuestionLog(existFraming, view, FramingMeetingConstants.LOG_TYPE_MODIFIED));
            }

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
            List<String> lsVic = new ArrayList<>();
            List<String> lsJob = new ArrayList<>();
            List<String> lsSchool = new ArrayList<>();
            List<String> lsDrug = new ArrayList<>();

            if (existFraming.getPersonalData() == null) {
                List<String> ls = new ArrayList<>();
                ls.add("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Datos personales y entorno social\".");
                validate.getGroupMessage().add(new GroupMessageMeetingDto("imputed", ls));
            }

            List<String> lsDom = new ArrayList<>();

            if (existFraming.getFramingAddresses() == null || !(existFraming.getFramingAddresses().size() > 0)) {
                lsDom.add("Debe capturar al menos un registro en la secci&oacute;n \"Domicilios\".");
            } else {
                Boolean isComplete = true;
                for (FramingAddress act : existFraming.getFramingAddresses()) {
                    if (act.isComplete() == false) {
                        isComplete = false;
                        break;
                    }
                }
                if (isComplete == false) {
                    lsDom.add("Debe proporcionar toda la informaci&oacute;n para cada domicilio.");
                }
            }

            if (existFraming.getAddressComments() == null) {
                lsDom.add("Debe capturar las observaiones para la secci&oacute;n \"Domicilios\".");
            }

            if (lsDom.size() > 0)
                validate.getGroupMessage().add(new GroupMessageMeetingDto("imputedHome", lsDom));

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
                    lsVic.add("Debe capturar al menos una registro en en la secci&oacute;n \"V&iacute;ctimas o testigos\".");
                }

            } else {
                lsSN.add("Debe capturar al menos una registro en en la secci&oacute;n \"Personas que viven con el imputado\".");
                lsR.add("Debe capturar al menos una registro en en la secci&oacute;n \"Referencias personales\".");
            }

            int bandHM = 0;
            int bandREF = 0;
            int bandIncompHM = 0;

            if (existFraming.getReferences() != null && existFraming.getReferences().size() > 0) {
                for (FramingReference act : existFraming.getReferences()) {
                    if (act.getIsAccompaniment() != null && act.getIsAccompaniment() == true && act.getAccompanimentInfo() == null) {
                        if (act.getPersonType() != null && act.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {
                            bandHM++;
//                            if (act.getOccupation() == null || act.getOccupation().trim().equals(""))
//                                bandIncompHM++;
                        } else if (act.getPersonType() != null && act.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE))
                            bandREF++;
                    }
                }
            }


            if (bandIncompHM > 0) {
                lsSN.add("Debe completar la informaci&oacute;n de todas las persona registrada en la secci&oacute;n \"Personas que viven con el imputado\".");
            }

            if (bandHM > 0) {
                lsSN.add("Alguna persona registrada en la secci&oacute;n \"Personas que viven con el imputado\" ha sido marcada como acompa&ntilde;ante durante el proceso. Debe capturar la informaci&oacute;n adicional requerida.");
            }

            if (bandREF > 0) {
                lsR.add("Alguna persona registrada en la secci&oacute;n \"Referencias personales\" ha sido marcada como acompa&ntilde;ante durante el proceso. Debe capturar la informaci&oacute;n adicional requerida.");
            }

            if (existFraming.getHousemateComments() == null)
                lsSN.add("Debe capturar las observaciones para la secci&oacute;n \"Personas que viven con el imputado\".");

            if (existFraming.getReferencesComments() == null)
                lsR.add("Debe capturar las observaciones para la secci&oacute;n \"Referencias personales\".");

            if (existFraming.getVictimComments() == null) {
                lsVic.add("Debe capturar las observaciones para la secci&oacute;n \"V&iacute;ctimas y testigos\".");
            }


            if (lsSN.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("socialNetwork", lsSN));
            }
            if (lsR.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("reference", lsR));
            }
            if (lsVic.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("victim", lsVic));
            }

//            if (existFraming.getOccupation() == null || existFraming.getRelFramingMeetingActivities() == null || !(existFraming.getRelFramingMeetingActivities().size() > 0)) {
//                List<String> ls = new ArrayList<>();
//                ls.add("Debe proporcionar la informaci&oacute;n faltante para la secci&oacute;n \"Actividades que realiza el imputado\".");
//                validate.getGroupMessage().add(new GroupMessageMeetingDto("activities", ls));
//            }
            if (existFraming.getDrugs() == null || !(existFraming.getDrugs().size() > 0)) {
                lsDrug.add("Debe capturar al menos una registro en en la secci&oacute;n \"Consumo de sustancias\".");
            }
            if (existFraming.getDrugsComments() == null) {
                lsDrug.add("Debe capturar las observaciones para la secci&oacute;n \"Consumo de sustancias\".");
            }
            if (lsDrug.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("drug", lsDrug));
            }

//            if (existFraming.getSelectedSourcesRel() == null || !(existFraming.getSelectedSourcesRel().size() > 0) ||
            if (existFraming.getSelectedRisksRel() == null || !(existFraming.getSelectedRisksRel().size() > 0) ||
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

            String cad = systemSettingRepository.findOneValue("FINGER_VAL", "ValidateFingerPrint");
            Boolean validateFingerprint = null;

            if (cad != null && !cad.trim().equals(""))
                validateFingerprint = Boolean.valueOf(cad);

            if (validateFingerprint != null && validateFingerprint == true) {
                if (!(framingMeetingRepository.getFingerIdsByImputed(existFraming.getCaseDetention().getMeeting().getImputed().getId()).size() > 0)) {
                    List<String> ls = new ArrayList<>();
                    ls.add("Debe capturar al menos una huella dactilar en la secci&oacute;n \"Enrolamiento\".");
                    validate.getGroupMessage().add(new GroupMessageMeetingDto("fingerprint", ls));
                }
            }

            if (existFraming.getSchool() == null) {
                lsSchool.add(Convert.convertToValidString("Debe proporcionar la informaci&oacute;n faltante en la secci&oacute;n \"Historia escolar\"."));
            } else if (existFraming.getSchool().getBlock() == true && (existFraming.getSchool().getSchedule() == null || !(existFraming.getSchool().getSchedule().size() > 0))) {
                lsSchool.add(Convert.convertToValidString("Debe proporcionar la informaci&oacute;n faltante en la secci&oacute;n \"Historia escolar\"."));
            }
            if (existFraming.getSchoolComments() == null) {
                lsSchool.add("Debe capturar las observaciones para la secci&oacute;n \"Historia escolar\".");
            }

            if (lsSchool.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("school", lsSchool));
            }

            if (existFraming.getJobs() == null || !(existFraming.getJobs().size() > 0)) {
                lsJob.add("Debe capturar al menos un trabajo en la secci&oacute;n \"Historia laboral\".");
            }

            if (existFraming.getJobComments() == null) {
                lsJob.add("Debe capturar las observaciones para la secci&oacute;n \"Historia laboral\".");
            }

            if (lsJob.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("job", lsJob));
            }

            List<String> arrMsgAct = new ArrayList<>();
            if (existFraming.getActivities() == null || !(existFraming.getActivities().size() > 0)) {
                arrMsgAct.add("Debe capturar al menos una actividad en la secci&oacute;n \"Actividades que realiza el imputado\".");
            } else {
                Boolean bandAct = true;
                for (FramingActivity act : existFraming.getActivities()) {
                    if (act.getListSchedule() == null || act.getListSchedule().size() == 0) {
                        bandAct = false;
                        break;
                    }
                }

                if (bandAct == false) {
                    arrMsgAct.add("Debe capturar la informaci&oacute;n faltante para cada actividad.");
                }
            }

            if (existFraming.getActivitiesComments() == null) {
                arrMsgAct.add("Debe capturar las observaciones para la secci&oacute;n \"Actividades que realiza el imputado\".");
            }

            if (arrMsgAct.size() > 0) {
                validate.getGroupMessage().add(new GroupMessageMeetingDto("activities", arrMsgAct));
            }

            if (validate.existsMessageProperties()) {
                List<String> listGeneral = new ArrayList<>();
                listGeneral.add(Convert.convertToValidString("No se puede terminar la entrevista ya que faltan preguntas por responder, para m&aacute;s detalles revise los mensajes de cada secci&oacute;n"));
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
                imputedReference.setPersonType(FramingMeetingConstants.PERSON_TYPE_IMPUTED);
                imputedReference = framingReferenceRepository.save(imputedReference);
                //caseRepository.save(existCase);

                FramingSelectedSourceRel imputedSourceRel = new FramingSelectedSourceRel();
                imputedSourceRel.setFramingMeeting(existFraming);
                existFraming.getSelectedSourcesRel().add(imputedSourceRel);
                imputedSourceRel.setFramingReference(imputedReference);
                imputedSourceRel = framingSelectedSourceRelRepository.save(imputedSourceRel);
                //para agregar al imputado en el listado de fuentes para las actividades
            }

            if (framingReferenceRepository.findReferenceOther(existFraming.getId(), Constants.NAME_RELATIONSHIP_OTHER) == null) {
                FramingReference otherReference = new FramingReference();

                otherReference.setRelationship(relationshipRepository.findOtherRelationship());
                otherReference.setFramingMeeting(existFraming);
                otherReference.setName("Otro");
                otherReference.setPersonType(FramingMeetingConstants.PERSON_TYPE_OTHER);
                otherReference = framingReferenceRepository.save(otherReference);

                FramingSelectedSourceRel otherSourceRel = new FramingSelectedSourceRel();
                otherSourceRel.setFramingMeeting(existFraming);
                existFraming.getSelectedSourcesRel().add(otherSourceRel);
                otherSourceRel.setFramingReference(otherReference);
                otherSourceRel = framingSelectedSourceRelRepository.save(otherSourceRel);
                //para agregar la opcion otro al combo para las actividades
            }

            caseRepository.save(existCase);
            framingMeetingLogRepository.save(getTerminateLog(idCase, existFraming));

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
            e.printStackTrace();
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

        try {
            //actividades

            List<RelSocialEnvironmentActivity> relAct = verifMeeting.getSocialEnvironment().getRelSocialEnvironmentActivities();
            List<FramingActivity> lstFramingActivities = new ArrayList<>();

            if (relAct != null && relAct.size() > 0) {


                for (RelSocialEnvironmentActivity rel : relAct) {
                    FramingActivity fAct = new FramingActivity();
                    fAct.setFramingMeeting(existFraming);
                    fAct.setDescription(rel.getSpecification());
                    fAct.setActivity(rel.getActivity());
                    lstFramingActivities.add(fAct);
                }
            }

            existFraming.setActivities(lstFramingActivities);

            //domicilios
            List<FramingAddress> listAddress = new ArrayList<>();

            for (ImputedHome act : verifMeeting.getImputedHomes()) {
                FramingAddress framingAddress = new FramingAddress();

                framingAddress.setAddressRef(act.getDescription());
                framingAddress.setSpecification(act.getSpecification());
                framingAddress.setTimeLive(act.getTimeLive());
                framingAddress.setTimeAgo(act.getTimeLive());
                framingAddress.setReasonAnother(act.getReasonSecondary());
                framingAddress.setReasonChange(act.getReasonChange());
                framingAddress.setPhone(act.getPhone());
                List<Schedule> lstSch = scheduleRepository.getScheduleByImputedHomeId(act.getId());

                for (Schedule addSch : lstSch) {
                    addSch.setFramingAddress(framingAddress);
                }

                framingAddress.setSchedule(lstSch);
                framingAddress.setSpecification(act.getSpecification());
                framingAddress.setRegisterType(act.getRegisterType());
                framingAddress.setHomeType(act.getHomeType());

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
                    fRef.setHasVictimWitnessInfo(person.getBlock());
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
                fRef.setHasVictimWitnessInfo(reference.getBlock());
                fRef.setName(reference.getFullName());
                fRef.setAge(reference.getAge().toString());
                fRef.setPhone(reference.getPhone());
                fRef.setRelationship(reference.getRelationship());
                fRef.setIsAccompaniment(reference.getIsAccompaniment());

                references.add(fRef);
            }

            List<Victim> lstVictims = existFraming.getCaseDetention().getMeeting().getCurrentCriminalProceeding().getVictims();

            for (Victim victim : lstVictims) {

                FramingReference fRef = new FramingReference();

                fRef.setFramingMeeting(existFraming);
                fRef.setPersonType(FramingMeetingConstants.PERSON_TYPE_VICTIM);
                fRef.setName(victim.getFullname());
                fRef.setPhone(victim.getPhone());
                fRef.setRelationship(victim.getRelationship());
                fRef.setAge(victim.getAge().toString());
                fRef.setIsAccompaniment(true);
                fRef.setHasVictimWitnessInfo(true);

                AccompanimentInfo accInf = new AccompanimentInfo();
                Address add = new Address(victim.getAddress());
                accInf.setAddress(add);
                fRef.setAccompanimentInfo(accInf);

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
                fDrug.setOnsetAge(drug.getOnsetAge());
                fDrug.setSpecificationPeriodicity(drug.getSpecificationPeriodicity());

                drugs.add(fDrug);
            }

            existFraming.setDrugs(drugs);

            //historia laboral
            List<Job> lstExistJobs = verifMeeting.getJobs();
            List<Job> lstNewJobs = new ArrayList<>();

            for (Job actJob : lstExistJobs) {
                Job newObj = new Job(actJob);
                List<Schedule> lstSch = scheduleRepository.getScheduleByJobId(actJob.getId());
                for (Schedule sch : lstSch) {
                    sch.setJob(newObj);
                }
                newObj.setSchedule(lstSch);
                actJob.setFramingMeeting(existFraming);

                lstNewJobs.add(actJob);
            }

            existFraming.setJobs(lstNewJobs);

            //historia escolar

            School existSchool = verifMeeting.getSchool();
            School framingSchool = new School(existSchool);
            List<Schedule> lstSch = scheduleRepository.getScheduleBySchoolId(existSchool.getId());
            for (Schedule sch : lstSch) {
                sch.setSchool(framingSchool);
            }
            framingSchool.setSchedule(lstSch);
            framingSchool.setFramingMeeting(existFraming);
            existFraming.setSchool(framingSchool);

            framingMeetingRepository.save(existFraming);
        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "fillSaveVerifiedInfo", sharedUserService);
        }
    }


    public ResponseMessage upsertComments(Long idCase, Integer commentType, String comments) {
        ResponseMessage resp = new ResponseMessage();

        try {
            Case existCase = caseRepository.findOne(idCase);
            String msg = "", title = "";

            if (existCase != null && existCase.getFramingMeeting() != null) {
                switch (commentType) {
                    case 1:
                        existCase.getFramingMeeting().setAddressComments(comments);
                        msg = "1|";
                        title = "Domicilios";
                        break;
                    case 2:
                        existCase.getFramingMeeting().setHousemateComments(comments);
                        msg = "2|";
                        title = "Personas que viven con el imputado";
                        break;
                    case 3:
                        existCase.getFramingMeeting().setReferencesComments(comments);
                        msg = "3|";
                        title = "Referencias persoanales";
                        break;
                    case 4:
                        existCase.getFramingMeeting().setDrugsComments(comments);
                        msg = "4|";
                        title = "Consumo de sustancias";
                        break;
                    case 5:
                        existCase.getFramingMeeting().setActivitiesComments(comments);
                        msg = "5|";
                        title = "Actividades que realiza el imputado";
                        break;
                    case 6:
                        existCase.getFramingMeeting().setJobComments(comments);
                        msg = "6|";
                        title = "Historia laboral";
                        break;
                    case 7:
                        existCase.getFramingMeeting().setVictimComments(comments);
                        msg = "7|";
                        title = "V&iacute;ctimas y testigos";
                        break;
                }

                caseRepository.save(existCase);
                FramingMeeting existFraming = existCase.getFramingMeeting();
                if (existFraming.getIsTerminated() != null && existFraming.getIsTerminated().equals(true))
                    framingMeetingLogRepository.save(getCommentLog(existFraming, comments, title, FramingMeetingConstants.LOG_TYPE_MODIFIED));

                resp.setHasError(false);
                resp.setMessage(msg + "Se ha guardado la informaci&oacute;n con &eacute;xito");

            } else {
                resp.setHasError(false);
                resp.setMessage("Ha ocurrido un error. Intente m&aacute;s tarde");
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
        Boolean isNew = true;
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
                isNew = false;
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

            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
            exisAct.setListSchedule(lstSchedule);
            exisAct.setFramingMeeting(existFraming);

            if (existFraming.getIsTerminated() == true) {
                String logType;
                if (isNew == true)
                    logType = FramingMeetingConstants.LOG_TYPE_ADDED;
                else
                    logType = FramingMeetingConstants.LOG_TYPE_MODIFIED;

                framingMeetingLogRepository.save(getActivityLog(existFraming, view, logType));
            }

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

            FramingMeeting existFraming = exisAct.getFramingMeeting();

            if (existFraming.getIsTerminated() == true) {
                framingMeetingLogRepository.save(getActivityLog(existFraming, fillActivityForView(exisAct.getId(), existFraming.getCaseDetention().getId()), FramingMeetingConstants.LOG_TYPE_DELETED));
            }

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

            if (existJob.getBlock() != null && existJob.getBlock() == true) {
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
        Boolean isNew = true;

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
                isNew = false;
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

            Case existCase = caseRepository.findOne(idCase);
            existJob.setFramingMeeting(existCase.getFramingMeeting());

            Boolean isTerm = existCase.getFramingMeeting().getIsTerminated();

            if (isTerm != null && isTerm.equals(true)) {
                String logType;
                if (isNew == true)
                    logType = FramingMeetingConstants.LOG_TYPE_ADDED;
                else
                    logType = FramingMeetingConstants.LOG_TYPE_MODIFIED;

                framingMeetingLogRepository.save(getJobLog(existCase.getFramingMeeting(), view, logType));
            }

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

            FramingMeeting existFraming = existJob.getFramingMeeting();

            if (existFraming.getIsTerminated() == true) {
                framingMeetingLogRepository.save(getJobLog(existFraming, fillJobForView(existJob.getId(), existFraming.getCaseDetention().getId()), FramingMeetingConstants.LOG_TYPE_DELETED));
            }

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

            School existSchool = caseRepository.findOne(view.getIdCase()).getFramingMeeting().getSchool();

            if (existSchool != null) {
                for (Schedule act : existSchool.getSchedule()) {
                    act.setSchool(null);
                    scheduleRepository.delete(act);
                }
                existSchool.setSchedule(null);
                schoolRepository.save(existSchool);
            } else
                existSchool = new School();

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

            if (existFraming.getIsTerminated() != null && existFraming.getIsTerminated() == true)
                framingMeetingLogRepository.save(getSchoolLog(existFraming, view, FramingMeetingConstants.LOG_TYPE_MODIFIED));

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

    public FramingMeetingLog getFramingPersonalDataLog(FramingMeeting framingMeeting, FramingPersonalDataView personalData, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();

        List<FramingLogElement> lstElements = new ArrayList<>();

        FramingLogElement element = new FramingLogElement();
        element.setFieldName("Nombre");
        element.setValue(personalData.getName());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Apellido paterno");
        element.setValue(personalData.getLastNameP());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Apellido materno");
        element.setValue(personalData.getLastNameM());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("G&eacute;nero");
        if (personalData.getGender() == 1)
            element.setValue("Femenino");
        else if (personalData.getGender() == 2)
            element.setValue("Masculino");
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Estado civil");
        switch (personalData.getMaritalStatus().intValue()) {
            case 1:
                element.setValue("Soltero");
                break;
            case 2:
                element.setValue("Casado");
                break;
            case 3:
                element.setValue("Divorciado");
                break;
            case 4:
                element.setValue("Uni&oacute;n libre");
                break;
            case 5:
                element.setValue("Viudo");
                break;
        }
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("A&ntilde;os estado civil");
        element.setValue(personalData.getMaritalStatusYears());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Pa&iacute;s de nacimiento");
        element.setValue(countryRepository.findOne(personalData.getBirthCountryId()).getName());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Estado de nacimiento");
        if (personalData.getIsMexico() == true) {
            element.setValue(stateRepository.findOne(personalData.getBirthStateId()).getName());
        } else {
            element.setValue(personalData.getBirthState());
        }
        lstElements.add(element);

        element = new FramingLogElement();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        element.setFieldName("Fecha de nacimiento");

        try {
            element.setValue(sdf.format(personalData.getBirthDate()));
            lstElements.add(element);
        } catch (Exception e) {
            System.out.println("error al convertir la fecha");
        }

        element = new FramingLogElement();
        element.setFieldName("Enfermedad / Condici&oacute;n f&iacute;sica");
        element.setValue(personalData.getPhysicalCondition());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("T&eacute;lefono");
        element.setValue(personalData.getPhone());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Celular");
        element.setValue(personalData.getCelPhone());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Email");
        element.setValue(personalData.getEmail());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Redes sociales");
        element.setValue(personalData.getSocialNetworking());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Observaciones");
        element.setValue(personalData.getComments());
        lstElements.add(element);

        Gson gson = new Gson();

        framingMeetingLog.setTitle("Datos personales y entorno social");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        framingMeetingLog.setFinalValue(gson.toJson(lstElements));

        return framingMeetingLog;
    }

    public FramingMeetingLog getFramingAddressLog(FramingMeeting framingMeeting, FramingAddressDto framingAddressDto, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();

        List<FramingLogElement> lstElements = new ArrayList<>();

        Gson gson = new Gson();

        FramingLogElement element = new FramingLogElement();

        element.setFieldName("Direcci&oacute;n");
        element.setValue(framingAddressDto.getAddressStr());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Referencias");
        element.setValue(framingAddressDto.getAddressRef());
        lstElements.add(element);

        String registerTypeName = registerTypeRepository.findOne(framingAddressDto.getRegisterTypeId()).getName();
        String homeTypeName = homeTypeRepository.findOne(framingAddressDto.getHomeTypeId()).getName();

        element = new FramingLogElement();
        element.setFieldName("Tipo de propiedad");
        element.setValue(homeTypeName);
        lstElements.add(element);

        if (homeTypeName.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_OTHER)) {
            element = new FramingLogElement();
            element.setFieldName("Especificaci&oacute;n");
            element.setValue(framingAddressDto.getSpecification());
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("Tipo de domicilio");
        element.setValue(registerTypeName);
        lstElements.add(element);

        if (registerTypeName.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_ACTUAL) || registerTypeName.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_SECONDARY)) {
            element = new FramingLogElement();
            element.setFieldName("Tiempo de vivir (actual/secundario)");
            element.setValue(framingAddressDto.getTimeAgo());
            lstElements.add(element);
        }

        if (registerTypeName.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_PREV)) {
            element = new FramingLogElement();
            element.setFieldName("Tiempo de residencia (anterior)");
            element.setValue(framingAddressDto.getTimeLive());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Motivo de la mudanza");
            element.setValue(framingAddressDto.getReasonChange());
            lstElements.add(element);
        }

        if (registerTypeName.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_SECONDARY)) {
            element = new FramingLogElement();
            element.setFieldName("Raz&oacute;n de domicilio secundario");
            element.setValue(framingAddressDto.getReasonAnother());
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("Tel&eacute;fono");
        element.setValue(framingAddressDto.getPhone());
        lstElements.add(element);

        if (registerTypeName.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_ACTUAL) || registerTypeName.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_SECONDARY)) {
            element = new FramingLogElement();
            element.setFieldName("Disponibilidad");
            element.setValue(framingAddressDto.getSchedule());
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        framingMeetingLog.setTitle("Domicilio");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        if (logType.equals(FramingMeetingConstants.LOG_TYPE_FINISHED))
            framingMeetingLog.setLstElements(lstElements);
        framingMeetingLog.setFinalValue(gson.toJson(lstElements));

        return framingMeetingLog;
    }

    private FramingMeetingLog getFramingReferenceLog(FramingMeeting framingMeeting, FramingReference reference, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();

        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();
        String gndr = "";

        element.setFieldName("Nombre");
        element.setValue(reference.getName());
        lstElements.add(element);

        if (reference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {

            framingMeetingLog.setTitle("Personas que viven con el imputado");

            element = new FramingLogElement();
            element.setFieldName("Relaci&oacute;n");
            element.setValue(reference.getRelationship().getName());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Edad");
            element.setValue(reference.getAge());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Ocupaci&oacute;n");
            element.setValue(reference.getOccupation());
            lstElements.add(element);


            if (reference.getIsAccompaniment() != null && reference.getIsAccompaniment() == true) {

                element = new FramingLogElement();
                element.setFieldName("Acompa&ntilde;a durante el proceso");
                element.setValue("Si");
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Direcci&oacute;n");
                element.setValue(reference.getAddress());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Referencias del domicilio");
                element.setValue(reference.getAddressRef());
                lstElements.add(element);

                if (reference.getAccompanimentInfo() != null) {
                    element = new FramingLogElement();
                    element.setFieldName("G&eacute;nero");

                    if (reference.getAccompanimentInfo().getGender() != null && reference.getAccompanimentInfo().getGender().equals(1))
                        gndr = "Femenino";
                    else if (reference.getAccompanimentInfo().getGender() != null && reference.getAccompanimentInfo().getGender().equals(2))
                        gndr = "Masculino";
                    element.setValue(gndr);
                    lstElements.add(element);


                    element = new FramingLogElement();
                    element.setFieldName("Escolaridad");
                    if (reference.getAccompanimentInfo().getAcademicLevel() != null)
                        element.setValue(reference.getAccompanimentInfo().getAcademicLevel().getName());
                    else
                        element.setValue("");
                    lstElements.add(element);

                    element = new FramingLogElement();
                    element.setFieldName("Lugar de ocupaci&oacute;n");
                    element.setValue(reference.getAccompanimentInfo().getOccupationPlace());
                    lstElements.add(element);
                }

                element = new FramingLogElement();
                element.setFieldName("Tel&eacute;fono");
                element.setValue(reference.getPhone());
                lstElements.add(element);
            } else {
                element.setValue("No");
            }

        } else if (reference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {

            framingMeetingLog.setTitle("Referencias personales");

            element = new FramingLogElement();
            element.setFieldName("Relaci&oacute;n");
            element.setValue(reference.getRelationship().getName());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Tel&eacute;fono");
            element.setValue(reference.getPhone());
            lstElements.add(element);

            if (reference.getIsAccompaniment() != null && reference.getIsAccompaniment() == true) {

                if (reference.getAccompanimentInfo().getGender() != null && reference.getAccompanimentInfo().getGender().equals(1))
                    gndr = "Femenino";
                else if (reference.getAccompanimentInfo().getGender() != null && reference.getAccompanimentInfo().getGender().equals(2))
                    gndr = "Masculino";
                element.setValue(gndr);
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Edad");
                element.setValue(reference.getAge());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Ocupaci&oacute;n");
                element.setValue(reference.getOccupation());
                lstElements.add(element);

                if (reference.getAccompanimentInfo() != null) {
                    element = new FramingLogElement();
                    element.setFieldName("Lugar de ocupaci&oacute;n");
                    element.setValue(reference.getAccompanimentInfo().getOccupationPlace());
                    lstElements.add(element);

                    element = new FramingLogElement();
                    element.setFieldName("Direcci&oacute;n");
                    if (reference.getAccompanimentInfo().getAddress() != null)
                        element.setValue(reference.getAccompanimentInfo().getAddress().getAddressString());
                    else
                        element.setValue("");
                    lstElements.add(element);
                }

                element = new FramingLogElement();
                element.setFieldName("Acompa&ntilde;a durante el proceso");
                element.setValue("Si");
                lstElements.add(element);
            } else {
                element = new FramingLogElement();
                element.setFieldName("Acompa&ntilde;a durante el proceso");
                element.setValue("No");
                lstElements.add(element);
            }

        } else if (reference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_VICTIM) ||
                reference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_WITNESS)) {

            framingMeetingLog.setTitle("V&iacute;ctima y testigos");

            if (reference.getHasVictimWitnessInfo() != null && reference.getHasVictimWitnessInfo() == true) {

                element = new FramingLogElement();
                element.setFieldName("Tipo");

                if (reference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_VICTIM)) {
                    element.setValue("V&iacute;ctima");
                } else if (reference.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_WITNESS)) {
                    element.setValue("Testigo");
                }

                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Relaci&oacute;n");
                element.setValue(reference.getRelationship().getName());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Edad");
                element.setValue(reference.getAge());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Tel&eacute;fono");
                element.setValue(reference.getPhone());
                lstElements.add(element);

                if (reference.getAccompanimentInfo() != null) {
                    element = new FramingLogElement();
                    element.setFieldName("Direcci&oacute;n");
                    if (reference.getAccompanimentInfo().getAddress() != null)
                        element.setValue(reference.getAccompanimentInfo().getAddress().getAddressString());
                    else
                        element.setValue("");
                    lstElements.add(element);
                }
            }
        }

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        if (logType.equals(FramingMeetingConstants.LOG_TYPE_FINISHED))
            framingMeetingLog.setLstElements(lstElements);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));

        return framingMeetingLog;
    }

    private FramingMeetingLog getSchoolLog(FramingMeeting framingMeeting, SchoolDto view, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();

        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();

        if (view.getHasActualSchool() != null && view.getHasActualSchool() == true) {
            element.setFieldName("Escuela");
            element.setValue(view.getName());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("T&eacute;lefono");
            element.setValue(view.getPhone());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Direcci&oacute;n");
            element.setValue(view.getAddress());
            lstElements.add(element);

            Degree selDeg = degreeRepository.findOne(view.getDegreeId());

            element = new FramingLogElement();
            element.setFieldName("Nivel acad&eacute;mico");
            element.setValue(selDeg.getAcademicLevel().getName());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Grado");
            element.setValue(selDeg.getName());
            lstElements.add(element);

            if (view.getSpecification() != null && !view.getSpecification().trim().equals("")) {
                element = new FramingLogElement();
                element.setFieldName("Especificaci&oacute;n");
                element.setValue(view.getSpecification());
                lstElements.add(element);
            }

            if (view.getSchedule() != null) {
                element = new FramingLogElement();
                element.setFieldName("Disponibilidad");
                element.setValue(view.getSchedule());
                lstElements.add(element);
            }
        } else {
            element = new FramingLogElement();
            element.setFieldName("Sin estudios actuales");
            element.setValue("");
            lstElements.add(element);

            Degree deg = degreeRepository.findOne(view.getDegreeId());

            element = new FramingLogElement();
            element.setFieldName("&Uacute;ltimo vivel acad&eacute;mico");
            element.setValue(deg.getAcademicLevel().getName());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("&Uacute;ltimo grado");
            element.setValue(deg.getName());
            lstElements.add(element);

            if (view.getSpecification() != null && !view.getSpecification().trim().equals("")) {
                element = new FramingLogElement();
                element.setFieldName("Especificaci&oacute;n");
                element.setValue(view.getSpecification());
                lstElements.add(element);
            }
        }

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Observaciones");
        element.setValue(framingMeeting.getSchoolComments());
        lstElements.add(element);

        framingMeetingLog.setTitle("Historia escolar");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));

        return framingMeetingLog;
    }

    private FramingMeetingLog getJobLog(FramingMeeting framingMeeting, JobDto view, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();

        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();

        if (view.getBlock() != null && view.getBlock().equals(true)) {
            element.setFieldName("Empresa");
            element.setValue(view.getCompany());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Puesto");
            element.setValue(view.getPost());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Tel&eacute;fono");
            element.setValue(view.getPhone());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Nombre del patr&oacute;n");
            element.setValue(view.getNameHead());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Direcci&oacute;n");
            element.setValue(view.getAddress());
            lstElements.add(element);

            String registerTypeStr = homeTypeRepository.findOne(view.getRegisterTypeId()).getName();

            element = new FramingLogElement();
            element.setFieldName("Tipo");
            element.setValue(registerTypeStr);
            lstElements.add(element);


            if (registerTypeStr.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_ACTUAL) ||
                    registerTypeStr.equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_SECONDARY)) {

                element = new FramingLogElement();
                element.setFieldName("Inicio");
                element.setValue(view.getStart());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Salario");
                element.setValue(view.getStart());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Disponibilidad");
                element.setValue(view.getSchedule());
                lstElements.add(element);

            } else if (registerTypeStr.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_PREV)) {
                element = new FramingLogElement();
                element.setFieldName("Inicio");
                element.setValue(view.getStartPrev());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Fin");
                element.setValue(view.getEnd());
                lstElements.add(element);

                element = new FramingLogElement();
                element.setFieldName("Motivo de cambio");
                element.setValue(view.getReasonChange());
                lstElements.add(element);
            }
        } else {
            element = new FramingLogElement();
            element.setFieldName("Sin trabajo actual");
            element.setValue("");
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        framingMeetingLog.setTitle("Historia laboral");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        if (logType.equals(FramingMeetingConstants.LOG_TYPE_FINISHED))
            framingMeetingLog.setLstElements(lstElements);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));

        return framingMeetingLog;
    }

    private FramingMeetingLog getActivityLog(FramingMeeting framingMeeting, FramingActivityView view, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();

        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();

        element.setFieldName("Actividad");
        element.setValue(activityRepository.findOne(view.getIdActivity()).getName());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Descripci&oacute;n");
        element.setValue(view.getDescription());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Disponibilidad");
        element.setValue(view.getLstSchedule());
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        framingMeetingLog.setTitle("Actividades que realiza el imputado");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        if (logType.equals(FramingMeetingConstants.LOG_TYPE_FINISHED))
            framingMeetingLog.setLstElements(lstElements);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));

        return framingMeetingLog;
    }

    private FramingMeetingLog getDrugLog(FramingMeeting framingMeeting, Drug drug, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();

        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();

        String drugType = drug.getDrugType().getName();
        if (drugType.toLowerCase().equals(FramingMeetingConstants.LOW_CASE_DRUG_TYPE_NONE)) {
            element.setFieldName("No consume sustancias");
            element.setValue("");
            lstElements.add(element);
        } else {

            element.setFieldName("Sustancia");
            element.setValue(drug.getDrugType().getName());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Frecuencia");
            element.setValue(drug.getPeriodicity().getName());
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Cantidad");
            element.setValue(drug.getQuantity());
            lstElements.add(element);

            try {
                element = new FramingLogElement();
                element.setFieldName("&Uacute;ltimo consumo");
                element.setValue(drug.getFormatter().format(drug.getLastUse()));
                lstElements.add(element);
            } catch (Exception e) {
                System.out.println("error al parsear la fecha de ultimo consumo");
            }

            element = new FramingLogElement();
            element.setFieldName("Edad de inicio");
            element.setValue(drug.getOnsetAge());
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        framingMeetingLog.setTitle("Consumo de sustancias");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        if (logType.equals(FramingMeetingConstants.LOG_TYPE_FINISHED))
            framingMeetingLog.setLstElements(lstElements);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));
        return framingMeetingLog;
    }

    private FramingMeetingLog getAdditionalQuestionLog(FramingMeeting framingMeeting, AdditionalQuestionsForView view, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();
        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        element.setFieldName("Se encuentra en tratamiento de adicciones");

        if (view.getAddictedAcquaintance().equals(1)) {
            element.setValue("Si");
            lstElements.add(element);

            element = new FramingLogElement();
            element.setFieldName("Instituci&oacute;n");
            element.setValue(view.getAddictionTreatmentInstitute());
            lstElements.add(element);

            try {
                element = new FramingLogElement();
                element.setFieldName("Desde cu&aacute;ando");
                element.setValue(sdf.format(view.getAddictionTreatmentDate()));
                lstElements.add(element);
            } catch (Exception e) {
                System.out.println("error al parsear la fecha");
            }

        } else {
            element.setValue("No");
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("Familiares y/o amigos consumen substancias adictivas");

        if (view.getAddictedAcquaintance().equals(1)) {

            element.setValue("Si");
            lstElements.add(element);

            Type listType = new TypeToken<List<RelativeAbroadView>>() {
            }.getType();

            List<RelativeAbroadView> viewLst = new Gson().fromJson(view.getSelectedAddictedAcquaintances(), listType);

            String addictedAcquaintancesStr = "";
            for (RelativeAbroadView act : viewLst) {
                if (act.getSelVal() == true) {
                    if (addictedAcquaintancesStr != "")
                        addictedAcquaintancesStr += ", ";
                    addictedAcquaintancesStr += act.getName();
                }
            }

            element = new FramingLogElement();
            element.setFieldName("Relaci&oacute;n");
            element.setValue(addictedAcquaintancesStr);
            lstElements.add(element);

        } else {
            element.setValue("No");
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("Familiares en el extranjero");

        if (view.getRelativeAbroad().equals(1)) {

            element.setValue("Si");
            lstElements.add(element);

            Type listType = new TypeToken<List<RelativeAbroadView>>() {
            }.getType();

            List<RelativeAbroadView> viewLst = new Gson().fromJson(view.getSelectedRelativesAbroad(), listType);

            String relativeAbroadStr = "";
            for (RelativeAbroadView act : viewLst) {
                if (act.getSelVal() == true) {
                    if (relativeAbroadStr != "")
                        relativeAbroadStr += "- ";
                    relativeAbroadStr += act.getName() + ", vive en " + act.getDescription();
                }
            }

            element = new FramingLogElement();
            element.setFieldName("Relaci&oacute;n");
            element.setValue(relativeAbroadStr);
            lstElements.add(element);

        } else {
            element.setValue("No");
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("Dificultades para cumplir obligaciones");

        if (view.getRelativeAbroad().equals(1)) {

            element.setValue("Si");
            lstElements.add(element);

            Type listType = new TypeToken<List<RelativeAbroadView>>() {
            }.getType();

            List<RelativeAbroadView> viewLst = new Gson().fromJson(view.getSelectedObligationIssues(), listType);

            String relativeAbroadStr = "";
            for (RelativeAbroadView act : viewLst) {
                if (act.getSelVal() == true) {
                    if (relativeAbroadStr != "")
                        relativeAbroadStr += "- ";
                    relativeAbroadStr += act.getName() + ", " + act.getDescription();
                }
            }

            element = new FramingLogElement();
            element.setFieldName("Causas");
            element.setValue(relativeAbroadStr);
            lstElements.add(element);

        } else {
            element.setValue("No");
            lstElements.add(element);
        }

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Observaciones");
        element.setValue(view.getObservations());
        lstElements.add(element);

        framingMeetingLog.setTitle("Formulario de preguntas al supervisado");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));

        return framingMeetingLog;
    }

    private FramingMeetingLog getEnvironmentAnalysisLog(FramingMeeting framingMeeting, FramingEnvironmentAnalysisForView view, String logType) {

        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();
        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();

        Type listType = new TypeToken<List<Long>>() {
        }.getType();

        List<Long> idsSelectedSources = new Gson().fromJson(view.getLstSelectedSources(), listType);
        List<Long> idsSelectedRisks = new Gson().fromJson(view.getLstSelectedRisk(), listType);
        List<Long> idsSelectedThreats = new Gson().fromJson(view.getLstSelectedThreat(), listType);
        List<SelectList> sources = new ArrayList<>();

        if (idsSelectedSources != null && idsSelectedSources.size() > 0) {
            sources = framingMeetingRepository.getEnvironmentSources(framingMeeting.getId(), idsSelectedSources);
        }

        List<String> risk = framingMeetingRepository.getRiskByFramingId(framingMeeting.getId(), idsSelectedRisks);
        List<String> threat = framingMeetingRepository.getRThreatByFramingId(framingMeeting.getId(), idsSelectedThreats);
        List<String> arrangements = new Gson().fromJson(view.getLstSelectedArrangement(), new TypeToken<List<String>>() {
        }.getType());

        element.setFieldName("V&iacute;nculos, v&iacute;ctimas y testigos");
        String cad = "";

        if (sources != null && sources.size() > 0) {
            for (SelectList act : sources) {

                if (cad != "")
                    cad += "; ";
                cad += act.getName();
                if (act.getDescription().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE))
                    cad += ", vive con el imputado";
                else if (act.getDescription().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE))
                    cad += ", referencia personal";
                else if (act.getDescription().equals(FramingMeetingConstants.PERSON_TYPE_VICTIM))
                    cad += ", v&iacute;ctima";
                else if (act.getDescription().equals(FramingMeetingConstants.PERSON_TYPE_WITNESS))
                    cad += ", testigo";
            }
        } else {
            cad = "No se seleccion&oacute; ninguno.";
        }

        element.setValue(cad);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Riesgos");
        cad = "";
        for (String act : risk) {
            if (cad != "")
                cad += ", ";
            cad += act;
        }
        element.setValue(cad);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Amenzas");
        cad = "";
        for (String act : threat) {
            if (cad != "")
                cad += ", ";
            cad += act;
        }
        element.setValue(cad);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Obligaciones procesales");
        cad = "";
        for (String act : arrangements) {
            if (cad != "")
                cad += ", ";
            cad += act;
        }
        element.setValue(cad);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("");
        element.setNewRow(true);
        lstElements.add(element);

        element = new FramingLogElement();
        element.setFieldName("Observaciones");
        element.setValue(view.getEnvironmentComments());
        lstElements.add(element);

        framingMeetingLog.setTitle("An&aacute;lisis del entorno");
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));

        return framingMeetingLog;
    }

    private List<FramingMeetingLog> getTerminateLog(Long idCase, FramingMeeting framingMeeting) {

        List<FramingMeetingLog> lstLog = new ArrayList<>();
        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingMeetingLog obj;
        FramingLogElement obs;

        //datos personales
        obj = getFramingPersonalDataLog(framingMeeting, fillPersonalDataForView(idCase), FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setLogIndex(1);
        lstLog.add(obj);

        //domicilios
        obj = new FramingMeetingLog();
        lstElements = new ArrayList<>();
        for (FramingAddress fa : framingMeeting.getFramingAddresses()) {
            lstElements.addAll(getFramingAddressLog(framingMeeting, fillFramingAddressForView(fa), FramingMeetingConstants.LOG_TYPE_FINISHED).getLstElements());
        }

        obj.setTitle("Domicilios");
        obj.setFramingMeeting(framingMeeting);
        obj.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        obj.setLogDate(CalendarExt.getToday());
        obj.setLogType(FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setFinalValue(new Gson().toJson(lstElements));
        obj.setLogIndex(2);
        lstLog.add(obj);

        //personas que viven con el
        obj = new FramingMeetingLog();
        lstElements = new ArrayList<>();
        for (FramingReference fr : framingMeetingRepository.getReferencesByPersonType(framingMeeting.getId(), new ArrayList<String>() {{
            add(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE);
        }})) {
            lstElements.addAll(getFramingReferenceLog(framingMeeting, fr, FramingMeetingConstants.LOG_TYPE_FINISHED).getLstElements());
        }

        obs = new FramingLogElement();
        obs.setFieldName("Observaciones");
        obs.setValue(framingMeeting.getHousemateComments());
        lstElements.add(obs);

        obj.setTitle("Personas que viven con el imputado");
        obj.setFramingMeeting(framingMeeting);
        obj.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        obj.setLogDate(CalendarExt.getToday());
        obj.setLogType(FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setFinalValue(new Gson().toJson(lstElements));
        obj.setLogIndex(3);
        lstLog.add(obj);


        //refencias personales
        obj = new FramingMeetingLog();
        lstElements = new ArrayList<>();
        for (FramingReference fr : framingMeetingRepository.getReferencesByPersonType(framingMeeting.getId(), new ArrayList<String>() {{
            add(FramingMeetingConstants.PERSON_TYPE_REFERENCE);
        }})) {
            lstElements.addAll(getFramingReferenceLog(framingMeeting, fr, FramingMeetingConstants.LOG_TYPE_FINISHED).getLstElements());
        }

        obs = new FramingLogElement();
        obs.setFieldName("Observaciones");
        obs.setValue(framingMeeting.getReferencesComments());
        lstElements.add(obs);

        obj.setTitle("Referencias personales");
        obj.setFramingMeeting(framingMeeting);
        obj.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        obj.setLogDate(CalendarExt.getToday());
        obj.setLogType(FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setFinalValue(new Gson().toJson(lstElements));
        obj.setLogIndex(4);
        lstLog.add(obj);

        //victimas y testigos

        obj = new FramingMeetingLog();
        lstElements = new ArrayList<>();
        for (FramingReference fr : framingMeetingRepository.getReferencesByPersonType(framingMeeting.getId(), new ArrayList<String>() {{
            add(FramingMeetingConstants.PERSON_TYPE_VICTIM);
            add(FramingMeetingConstants.PERSON_TYPE_WITNESS);
        }})) {
            lstElements.addAll(getFramingReferenceLog(framingMeeting, fr, FramingMeetingConstants.LOG_TYPE_FINISHED).getLstElements());
        }

        obs = new FramingLogElement();
        obs.setFieldName("Observaciones");
        obs.setValue(framingMeeting.getVictimComments());
        lstElements.add(obs);

        obj.setTitle("V&iacute;ctimas y testigos");
        obj.setFramingMeeting(framingMeeting);
        obj.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        obj.setLogDate(CalendarExt.getToday());
        obj.setLogType(FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setFinalValue(new Gson().toJson(lstElements));
        obj.setLogIndex(5);
        lstLog.add(obj);

        //historia escolar

        obj = new FramingMeetingLog();
        obj = getSchoolLog(framingMeeting, fillSchoolForView(idCase), FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setLogIndex(6);
        lstLog.add(obj);

        //historia laboral

        obj = new FramingMeetingLog();
        lstElements = new ArrayList<>();
        for (Job job : framingMeeting.getJobs()) {
            lstElements.addAll(getJobLog(framingMeeting, fillJobForView(job.getId(), idCase), FramingMeetingConstants.LOG_TYPE_FINISHED).getLstElements());
        }
        obs = new FramingLogElement();
        obs.setFieldName("Observaciones");
        obs.setValue(framingMeeting.getJobComments());
        lstElements.add(obs);

        obj.setTitle("Historia laboral");
        obj.setFramingMeeting(framingMeeting);
        obj.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        obj.setLogDate(CalendarExt.getToday());
        obj.setLogType(FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setFinalValue(new Gson().toJson(lstElements));
        obj.setLogIndex(7);
        lstLog.add(obj);

        //actividades

        obj = new FramingMeetingLog();
        lstElements = new ArrayList<>();
        for (FramingActivity activity : framingMeeting.getActivities()) {
            lstElements.addAll(getActivityLog(framingMeeting, fillActivityForView(activity.getId(), idCase), FramingMeetingConstants.LOG_TYPE_FINISHED).getLstElements());
        }

        obs = new FramingLogElement();
        obs.setFieldName("Observaciones");
        obs.setValue(framingMeeting.getActivitiesComments());
        lstElements.add(obs);

        obj.setTitle("Actividades que realiza el imputado");
        obj.setFramingMeeting(framingMeeting);
        obj.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        obj.setLogDate(CalendarExt.getToday());
        obj.setLogType(FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setFinalValue(new Gson().toJson(lstElements));
        obj.setLogIndex(8);
        lstLog.add(obj);

        //consumo de sustancias

        obj = new FramingMeetingLog();
        lstElements = new ArrayList<>();
        for (Drug drug : framingMeeting.getDrugs()) {
            lstElements.addAll(getDrugLog(framingMeeting, drug, FramingMeetingConstants.LOG_TYPE_FINISHED).getLstElements());
        }

        obs = new FramingLogElement();
        obs.setFieldName("Observaciones");
        obs.setValue(framingMeeting.getDrugsComments());
        lstElements.add(obs);

        obj.setTitle("Consumo de sustancias");
        obj.setFramingMeeting(framingMeeting);
        obj.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        obj.setLogDate(CalendarExt.getToday());
        obj.setLogType(FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setFinalValue(new Gson().toJson(lstElements));
        obj.setLogIndex(9);
        lstLog.add(obj);

        //formulario de preguntas

        obj = new FramingMeetingLog();
        obj = getAdditionalQuestionLog(framingMeeting, fillAddtionalQuestionsForView(idCase), FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setLogIndex(10);
        lstLog.add(obj);

        //analisis del entorno

        obj = new FramingMeetingLog();
        obj = getEnvironmentAnalysisLog(framingMeeting, loadEnvironmentAnalysis(idCase), FramingMeetingConstants.LOG_TYPE_FINISHED);
        obj.setLogIndex(11);
        lstLog.add(obj);

        return lstLog;
    }

    private FramingMeetingLog getCommentLog(FramingMeeting framingMeeting, String comment, String title, String logType) {
        FramingMeetingLog framingMeetingLog = new FramingMeetingLog();
        List<FramingLogElement> lstElements = new ArrayList<>();
        FramingLogElement element = new FramingLogElement();

        element.setFieldName("Observaciones");
        element.setValue(comment);
        lstElements.add(element);

        framingMeetingLog.setTitle(title);
        framingMeetingLog.setFramingMeeting(framingMeeting);
        framingMeetingLog.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        framingMeetingLog.setLogDate(CalendarExt.getToday());
        framingMeetingLog.setLogType(logType);
        framingMeetingLog.setFinalValue(new Gson().toJson(lstElements));

        return framingMeetingLog;
    }

}