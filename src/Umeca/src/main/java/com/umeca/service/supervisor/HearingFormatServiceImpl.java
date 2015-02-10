package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.extensions.IntegerExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.shared.LogCase;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ConstantsLogCase;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.RequestTypeRepository;
import com.umeca.repository.reviewer.AddressRepository;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.reviewer.CrimeRepository;
import com.umeca.repository.shared.MessageRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.CatalogService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.shared.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("hearingFormatService")
public class HearingFormatServiceImpl implements HearingFormatService {

    @Qualifier("qHearingFormatRepository")
    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    CatalogService catalogService;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    CaseService caseService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    HearingTypeRepository hearingTypeRepository;

    private Gson conv = new Gson();

    @Override
    public HearingFormat fillHearingFormat(HearingFormatView viewFormat) {

        HearingFormat hearingFormat = null;

        if (viewFormat.getIdFormat() != null)
            hearingFormat = hearingFormatRepository.findOne(viewFormat.getIdFormat());
        else
            hearingFormat = new HearingFormat();

        hearingFormat.setRegisterTime(Calendar.getInstance());
        hearingFormat.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));

        Boolean hasFirstFH = false;
        HearingFormat lastHF = null;
        Case existCase = caseRepository.findOne(viewFormat.getIdCase());

        List<HearingFormat> existFinishedFormats = hearingFormatRepository.findLastHearingFormatByCaseId(existCase.getId(), new PageRequest(0, 1));

        if (existFinishedFormats != null && existFinishedFormats.size() > 0) {
            hasFirstFH = true;
            lastHF = existFinishedFormats.get(0);
        }

        if (hasFirstFH) {
            hearingFormat.setIdFolder(lastHF.getIdFolder());
            hearingFormat.setIdJudicial(lastHF.getIdJudicial());

            if (viewFormat.getHearingTypeId() != null) {
                hearingFormat.setHearingType(hearingTypeRepository.findOne(viewFormat.getHearingTypeId()));
                hearingFormat.setHearingTypeSpecification(viewFormat.getHearingTypeSpecification());
            }

            hearingFormat.setImputedPresence(viewFormat.getImputedPresence());
            hearingFormat.setHearingResult(viewFormat.getHearingResult());

        } else {
            hearingFormat.setIdFolder(viewFormat.getIdFolder());
            hearingFormat.setIdJudicial(viewFormat.getIdJudicial());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfA = new SimpleDateFormat("HH:mm:ss");

        hearingFormat.setRoom(viewFormat.getRoom());

        try {

            if (viewFormat.getAppointmentDateStr() != null && !viewFormat.getAppointmentDateStr().trim().equals(""))
                hearingFormat.setAppointmentDate(sdf.parse(viewFormat.getAppointmentDateStr()));
            else
                hearingFormat.setAppointmentDate(null);

            if (viewFormat.getInitTimeStr() != null && !viewFormat.getInitTimeStr().trim().equals(""))
                hearingFormat.setInitTime(new Time(sdfA.parse(viewFormat.getInitTimeStr()).getTime()));
            else
                hearingFormat.setInitTime(null);

            if (viewFormat.getEndTimeStr() != null && !viewFormat.getEndTimeStr().trim().equals(""))
                hearingFormat.setEndTime(new Time(sdfA.parse(viewFormat.getEndTimeStr()).getTime()));
            else
                hearingFormat.setEndTime(null);

            if (viewFormat.getUmecaDateStr() != null && !viewFormat.getUmecaDateStr().trim().equals(""))
                hearingFormat.setUmecaDate(sdf.parse(viewFormat.getUmecaDateStr()));
            else
                hearingFormat.setAppointmentDate(null);

            if (viewFormat.getUmecaTimeStr() != null && !viewFormat.getUmecaTimeStr().trim().equals(""))
                hearingFormat.setUmecaTime(new Time(sdfA.parse(viewFormat.getUmecaTimeStr()).getTime()));
            else
                hearingFormat.setUmecaTime(null);

            if (viewFormat.getUmecaSupervisorId() != null)
                hearingFormat.setUmecaSupervisor(userRepository.findOne(viewFormat.getUmecaSupervisorId()));

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error fillHearingFormat");
            e.printStackTrace();
            logException.Write(e, this.getClass(), "parsingDataHearingFormat", sharedUserService);
            return null;
        }

        hearingFormat.setJudgeName(viewFormat.getJudgeName());
        hearingFormat.setMpName(viewFormat.getMpName());
        hearingFormat.setDefenderName(viewFormat.getDefenderName());

        hearingFormat.setPreviousHearing(viewFormat.getPreviousHearing());

        //if (hasFirstFH) {
        //hearingFormat.setHearingImputed(lastHF.getHearingImputed());
        //} else {

        HearingFormatImputed hearingImputed = hearingFormat.getHearingImputed();

        if (hearingImputed == null)
            hearingImputed = new HearingFormatImputed();

        hearingImputed.setName(viewFormat.getImputedName());
        hearingImputed.setLastNameP(viewFormat.getImputedFLastName());
        hearingImputed.setLastNameM(viewFormat.getImputedSLastName());

        try {
            if (viewFormat.getImputedBirthDateStr() != null && !viewFormat.getImputedBirthDateStr().trim().equals(""))
                hearingImputed.setBirthDate(sdf.parse(viewFormat.getImputedBirthDateStr()));
            else
                hearingImputed.setBirthDate(null);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error fillHearingFormat");
            e.printStackTrace();
            logException.Write(e, this.getClass(), "parsingBirthDateHearingFormat", sharedUserService);
            return null;
        }

        hearingImputed.setImputeTel(viewFormat.getImputedTel());

        if (viewFormat.getLocation() != null && viewFormat.getLocation().getId() != null) {
            Address address = hearingImputed.getAddress();
            if (address == null)
                address = new Address();

            address.setStreet(viewFormat.getStreet());
            address.setOutNum(viewFormat.getOutNum());
            address.setInnNum(viewFormat.getInnNum());
            address.setLat(viewFormat.getLat());
            address.setLng(viewFormat.getLng());
            address.setLocation(locationRepository.findOne(viewFormat.getLocation().getId()));
            address.setAddressString(address.toString());

            hearingImputed.setAddress(address);
        }

        hearingFormat.setHearingImputed(hearingImputed);
        //}

        HearingFormatSpecs hearingSpecs = hearingFormat.getHearingFormatSpecs();
        if (hearingSpecs == null) {
            hearingSpecs = new HearingFormatSpecs();
        }
        hearingSpecs.setControlDetention(viewFormat.getControlDetention());
        hearingSpecs.setExtension(viewFormat.getExtension());
        hearingSpecs.setImputationFormulation(viewFormat.getImpForm());

        try {
            if (viewFormat.getImputationDateStr() != null && !viewFormat.getImputationDateStr().trim().equals(""))
                hearingSpecs.setImputationDate(sdf.parse(viewFormat.getImputationDateStr()));
            else
                hearingSpecs.setImputationDate(null);

            if (viewFormat.getExtDateStr() != null && !viewFormat.getExtDateStr().trim().equals(""))
                hearingSpecs.setExtDate(sdf.parse(viewFormat.getExtDateStr()));
            else
                hearingSpecs.setExtDate(null);

            if (viewFormat.getLinkageDateStr() != null && !viewFormat.getLinkageDateStr().trim().equals(""))
                hearingSpecs.setLinkageDate(sdf.parse(viewFormat.getLinkageDateStr()));
            else
                hearingSpecs.setLinkageDate(null);

            if (viewFormat.getLinkageTimeStr() != null && !viewFormat.getLinkageTimeStr().trim().equals(""))
                hearingSpecs.setLinkageTime(new Time(sdfA.parse(viewFormat.getLinkageTimeStr()).getTime()));

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error fillHearingFormat");
            e.printStackTrace();
            logException.Write(e, this.getClass(), "parsingSpecsHearingFormat", sharedUserService);
            return null;
        }

        hearingSpecs.setLinkageProcess(viewFormat.getVincProcess());
        hearingSpecs.setLinkageRoom(viewFormat.getLinkageRoom());


        if (viewFormat.getVincProcess() != null &&
                (viewFormat.getVincProcess().equals(HearingFormatConstants.PROCESS_VINC_YES)
                        || viewFormat.getVincProcess().equals(HearingFormatConstants.PROCESS_VINC_NO_REGISTER))) {

            hearingSpecs.setArrangementType(viewFormat.getArrangementType());
            hearingSpecs.setNationalArrangement(viewFormat.getNationalArrangement());

            hearingFormat.setTerms(viewFormat.getTerms());

            List<ArrangementView> lstAssignedArrnmtView;
            Type type = new TypeToken<List<ArrangementView>>() {
            }.getType();

            if (viewFormat.getLstArrangement() != null && !viewFormat.getLstArrangement().trim().equals("")) {

                lstAssignedArrnmtView = conv.fromJson(viewFormat.getLstArrangement(), type);

                if (lstAssignedArrnmtView.size() > 0) {

                    List<AssignedArrangement> lstNewAssigArrmnt = new ArrayList<>();

                    for (ArrangementView arrV : lstAssignedArrnmtView) {

                        if (arrV.getSelVal() == true) {
                            AssignedArrangement assArrmnt = new AssignedArrangement();
                            assArrmnt.setArrangement(arrangementRepository.findOne(arrV.getId()));
                            assArrmnt.setDescription(arrV.getDescription());
                            assArrmnt.setHearingFormat(hearingFormat);
                            lstNewAssigArrmnt.add(assArrmnt);
                        }
                    }

                    List<AssignedArrangement> oldArrangements = hearingFormat.getAssignedArrangements();
                    hearingFormat.setAssignedArrangements(null);

                    if (oldArrangements != null) {
                        for (AssignedArrangement actArr : oldArrangements) {
                            actArr.setHearingFormat(null);
                            actArr.setArrangement(null);
                            assignedArrangementRepository.delete(actArr);
                        }
                    }

                    hearingFormat.setAssignedArrangements(lstNewAssigArrmnt);
                } else
                    hearingFormat.setAssignedArrangements(null);
            }

            List<ContactDataView> lstContactDataView;

            Type typeA = new TypeToken<List<ContactDataView>>() {
            }.getType();


            if (viewFormat.getLstContactData() != null && !viewFormat.getLstContactData().trim().equals("")) {
                lstContactDataView = conv.fromJson(viewFormat.getLstContactData(), typeA);

                if (lstContactDataView.size() > 0) {
                    List<ContactData> lstNewContactData = new ArrayList<>();

                    for (ContactDataView conV : lstContactDataView) {

                        ContactData contact = new ContactData();
                        contact.setNameTxt(conV.getName());
                        contact.setPhoneTxt(conV.getPhone());
                        contact.setAddressTxt(conV.getAddress());
                        contact.setHearingFormat(hearingFormat);
                        lstNewContactData.add(contact);
                    }

                    List<ContactData> oldContacts = hearingFormat.getContacts();
                    hearingFormat.setContacts(null);

                    if (oldContacts != null) {
                        for (ContactData act : oldContacts) {
                            act.setHearingFormat(null);
                            contactDataRepository.delete(act);
                        }
                    }

                    hearingFormat.setContacts(lstNewContactData);
                } else {
                    List<ContactData> oldContacts = hearingFormat.getContacts();
                    hearingFormat.setContacts(null);

                    if (oldContacts != null && oldContacts.size() > 0)
                        for (ContactData act : oldContacts) {
                            act.setHearingFormat(null);
                            contactDataRepository.delete(act);
                        }
                }
            }

        } else {
            hearingFormat.setConfirmComment(viewFormat.getConfirmComment());
        }
        List<Crime> auxCrime = hearingFormat.getCrimeList();
        if (auxCrime != null) {
            hearingFormat.setCrimeList(null);
            for (Crime c : auxCrime) {
                c.setHearingFormat(null);
                crimeRepository.delete(c);
            }
        }

        if (viewFormat.getIsSubstracted() != null)
            hearingFormat.setIsSubstracted(viewFormat.getIsSubstracted());
        hearingFormat.setCrimeList(crimeService.getListOfString(viewFormat.getListCrime(), hearingFormat));
        hearingFormat.setHearingFormatSpecs(hearingSpecs);
        hearingFormat.setIsFinished(viewFormat.getIsFinished());
        hearingFormat.setComments(viewFormat.getComments());

        return hearingFormat;
    }

    @Autowired
    CrimeService crimeService;
    @Autowired
    CrimeRepository crimeRepository;

    @Override
    public HearingFormatView fillNewHearingFormatForView(Long idCase) {

        HearingFormatView hearingFormatView = new HearingFormatView();

        Case existCase = caseRepository.findOne(idCase);
        Integer meetType = existCase.getMeeting().getMeetingType();

        List<HearingFormat> existFormats = hearingFormatRepository.findLastHearingFormatByCaseId(idCase, new PageRequest(0, 1));

        if (existFormats != null && existFormats.size() > 0) {//busco si ya existe algun formato completo

            hearingFormatView = this.fillExistHearingFormatForView(existFormats.get(0).getId(), true);

            hearingFormatView.setCanSave(true);
            hearingFormatView.setCanEdit(true);
            hearingFormatView.setDisableAll(false);
            hearingFormatView.setHasPrevHF(true);

        } else {//si no existe un formato de audiencia anterior
            //evaluo el origen del meeting
            hearingFormatView.setInitTime(new Time(new Date().getTime()));
            hearingFormatView.setAppointmentDate(new Date());

            if (meetType.equals(HearingFormatConstants.MEETING_CONDITIONAL_REPRIEVE)) { //si el meeting fue creado como suspension condicional

                //obtengo los datos de meeting
                hearingFormatView.setIdCase(existCase.getId());
                hearingFormatView.setIdFolder(existCase.getIdFolder());
                hearingFormatView.setIdJudicial(existCase.getIdMP());
                hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getBirthDate());

                User us = userRepository.findOne(sharedUserService.GetLoggedUserId());
                hearingFormatView.setUserName(us.getFullname());

                hearingFormatView.setCanSave(true);
                hearingFormatView.setCanEdit(true);
                hearingFormatView.setDisableAll(false);
                hearingFormatView.setHasPrevHF(false);

            } else if (meetType.equals(HearingFormatConstants.MEETING_PROCEDURAL_RISK)) {//si el meeting fue creado normalmente y ya esta completo


                hearingFormatView.setIdCase(existCase.getId());
                hearingFormatView.setIdFolder(existCase.getIdFolder());
                hearingFormatView.setIdJudicial(existCase.getIdMP());
                Meeting verif = new Meeting();
                if (existCase.getVerification() != null && existCase.getVerification().getMeetingVerified() != null) {
                    verif = existCase.getVerification().getMeetingVerified();
                } else {
                    verif = existCase.getMeeting();
                }


                if (verif.getImputed() != null) {
                    hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                    hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                    hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                    hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getBirthDate());
                }

                List<ImputedHome> homes = verif.getImputedHomes();

                if (homes != null && homes.size() > 0) {
                    Collections.sort(homes, ImputedHome.imputedHomeComparator);
                    hearingFormatView.setIdAddres(homes.get(0).getAddress().getId());
                }

                User us = userRepository.findOne(sharedUserService.GetLoggedUserId());
                hearingFormatView.setUserName(us.getFullname());

                List<Crime> lstCrime = existCase.getMeeting().getCurrentCriminalProceeding().getCrimeList();
                hearingFormatView.setListCrime(new Gson().toJson(crimeService.fromListToStringCrime(lstCrime)));

                hearingFormatView.setCanSave(true);
                hearingFormatView.setCanEdit(true);
                hearingFormatView.setDisableAll(false);
                hearingFormatView.setHasPrevHF(false);
            }

        }

        hearingFormatView.setIsFinished(false);

        return hearingFormatView;

    }

    @Override
    public HearingFormatView fillExistHearingFormatForView(Long idFormat, Boolean newFormat) {
        HearingFormatView hearingFormatView = new HearingFormatView();

        HearingFormat existHF = hearingFormatRepository.findOne(idFormat);
        hearingFormatView.setIdCase(existHF.getCaseDetention().getId());

        hearingFormatView.setCanSave(false);
        hearingFormatView.setCanEdit(false);
        hearingFormatView.setDisableAll(true);

        hearingFormatView.setIsSubstracted(existHF.getCaseDetention().getIsSubstracted());

        hearingFormatView.setIdFolder(existHF.getIdFolder());
        hearingFormatView.setIdJudicial(existHF.getIdJudicial());
        hearingFormatView.setAppointmentDate(existHF.getAppointmentDate());
        hearingFormatView.setRoom(existHF.getRoom());
        hearingFormatView.setInitTime(existHF.getInitTime());
        hearingFormatView.setEndTime(existHF.getEndTime());
        hearingFormatView.setJudgeName(existHF.getJudgeName());
        hearingFormatView.setMpName(existHF.getMpName());
        hearingFormatView.setDefenderName(existHF.getDefenderName());

        hearingFormatView.setImputedName(existHF.getHearingImputed().getName());
        hearingFormatView.setImputedFLastName(existHF.getHearingImputed().getLastNameP());
        hearingFormatView.setImputedSLastName(existHF.getHearingImputed().getLastNameM());
        hearingFormatView.setImputedBirthDate(existHF.getHearingImputed().getBirthDate());
        hearingFormatView.setImputedTel(existHF.getHearingImputed().getImputeTel());

        hearingFormatView.setUmecaDate(existHF.getUmecaDate());
        hearingFormatView.setUmecaTime(existHF.getUmecaTime());
        if (existHF.getUmecaSupervisor() != null)
            hearingFormatView.setUmecaSupervisorId(existHF.getUmecaSupervisor().getId());

        if (existHF.getHearingType() != null) {
            hearingFormatView.setHearingTypeId(existHF.getHearingType().getId());
            hearingFormatView.setHearingTypeSpecification(existHF.getHearingTypeSpecification());
        }

        hearingFormatView.setImputedPresence(existHF.getImputedPresence());
        hearingFormatView.setHearingResult(existHF.getHearingResult());
        hearingFormatView.setPreviousHearing(existHF.getPreviousHearing());

        List<Long> idsFormats = hearingFormatRepository.findHearingFormatIdsByIdCase(existHF.getCaseDetention().getId());

        if (existHF.getCaseDetention().getHearingFormats().size() > 0) {
            hearingFormatView.setHasPrevHF(true);
            if (idsFormats != null && idsFormats.get(0) == idFormat)
                hearingFormatView.setHasPrevHF(false);
        }

        hearingFormatView.setIdAddres(existHF.getHearingImputed().getAddress().getId());

        if (existHF.getHearingFormatSpecs() != null) {
            hearingFormatView.setControlDetention(existHF.getHearingFormatSpecs().getControlDetention());
            hearingFormatView.setExtension(existHF.getHearingFormatSpecs().getExtension());
            hearingFormatView.setExtDate(existHF.getHearingFormatSpecs().getExtDate());
            hearingFormatView.setImpForm(existHF.getHearingFormatSpecs().getImputationFormulation());
            hearingFormatView.setImputationDate(existHF.getHearingFormatSpecs().getImputationDate());
            hearingFormatView.setVincProcess(existHF.getHearingFormatSpecs().getLinkageProcess());
            hearingFormatView.setLinkageRoom(existHF.getHearingFormatSpecs().getLinkageRoom());
            hearingFormatView.setLinkageDate(existHF.getHearingFormatSpecs().getLinkageDate());
            hearingFormatView.setLinkageTime(existHF.getHearingFormatSpecs().getLinkageTime());
            hearingFormatView.setControlDetention(existHF.getHearingFormatSpecs().getControlDetention());
        }

        if (newFormat == true)
            hearingFormatView.setUserName(userRepository.findOne(sharedUserService.GetLoggedUserId()).getFullname());
        else
            hearingFormatView.setUserName(existHF.getSupervisor().getFullname());


        if (existHF.getHearingFormatSpecs() != null &&
                (existHF.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_YES) || existHF.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_NO_REGISTER))) {
            hearingFormatView.setArrangementType(existHF.getHearingFormatSpecs().getArrangementType());
            hearingFormatView.setNationalArrangement(existHF.getHearingFormatSpecs().getNationalArrangement());
            hearingFormatView.setTerms(existHF.getTerms());

            if (existHF.getHearingFormatSpecs().getNationalArrangement() != null && existHF.getHearingFormatSpecs().getArrangementType() != null) {

                List<ArrangementView> lstExistArrangement = this.getArrangmentLst(existHF.getHearingFormatSpecs().getNationalArrangement(), existHF.getHearingFormatSpecs().getArrangementType());
                hearingFormatView.setLstArrangement(conv.toJson(this.selectedAssignedArrangementForView(lstExistArrangement, existHF.getAssignedArrangements())));
            }
        }

        hearingFormatView.setComments(existHF.getComments());
        hearingFormatView.setLstContactData(conv.toJson(this.contactDataForView(existHF.getContacts())));
        hearingFormatView.setIsFinished(existHF.getIsFinished());
        hearingFormatView.setListCrime(crimeService.getListCrimeHearingformatByIdFormat(idFormat));
        return hearingFormatView;
    }

    @Override
    public HearingFormatView fillIncompleteFormatForView(Long idFormat) {
        HearingFormatView hearingFormatView = new HearingFormatView();

        HearingFormat existHF = hearingFormatRepository.findOne(idFormat);

        hearingFormatView.setIdFormat(existHF.getId());
        hearingFormatView.setIdCase(existHF.getCaseDetention().getId());

        hearingFormatView.setCanSave(true);
        hearingFormatView.setCanEdit(true);
        hearingFormatView.setDisableAll(false);
        hearingFormatView.setIsSubstracted(existHF.getCaseDetention().getIsSubstracted());

        if (existHF.getCaseDetention().getHearingFormats().size() > 1)
            hearingFormatView.setHasPrevHF(true);
        else
            hearingFormatView.setHasPrevHF(false);

        hearingFormatView.setIdFolder(existHF.getIdFolder());
        hearingFormatView.setIdJudicial(existHF.getIdJudicial());
        hearingFormatView.setAppointmentDate(existHF.getAppointmentDate());
        hearingFormatView.setRoom(existHF.getRoom());
        hearingFormatView.setInitTime(existHF.getInitTime());
        hearingFormatView.setEndTime(existHF.getEndTime());
        hearingFormatView.setJudgeName(existHF.getJudgeName());
        hearingFormatView.setMpName(existHF.getMpName());
        hearingFormatView.setDefenderName(existHF.getDefenderName());

        hearingFormatView.setImputedName(existHF.getHearingImputed().getName());
        hearingFormatView.setImputedFLastName(existHF.getHearingImputed().getLastNameP());
        hearingFormatView.setImputedSLastName(existHF.getHearingImputed().getLastNameM());
        hearingFormatView.setImputedBirthDate(existHF.getHearingImputed().getBirthDate());
        hearingFormatView.setImputedTel(existHF.getHearingImputed().getImputeTel());

        hearingFormatView.setUmecaDate(existHF.getUmecaDate());
        hearingFormatView.setUmecaTime(existHF.getUmecaTime());
        if (existHF.getUmecaSupervisor() != null)
            hearingFormatView.setUmecaSupervisorId(existHF.getUmecaSupervisor().getId());

        if (existHF.getHearingType() != null) {
            hearingFormatView.setHearingTypeId(existHF.getHearingType().getId());
            hearingFormatView.setHearingTypeSpecification(existHF.getHearingTypeSpecification());
        }

        hearingFormatView.setImputedPresence(existHF.getImputedPresence());
        hearingFormatView.setHearingResult(existHF.getHearingResult());

        if (existHF.getHearingImputed().getAddress() != null) {
            hearingFormatView.setIdAddres(existHF.getHearingImputed().getAddress().getId());
        }

        if (existHF.getHearingFormatSpecs() != null) {
            hearingFormatView.setControlDetention(existHF.getHearingFormatSpecs().getControlDetention());
            hearingFormatView.setExtension(existHF.getHearingFormatSpecs().getExtension());
            hearingFormatView.setExtDate(existHF.getHearingFormatSpecs().getExtDate());
            hearingFormatView.setImpForm(existHF.getHearingFormatSpecs().getImputationFormulation());
            hearingFormatView.setImputationDate(existHF.getHearingFormatSpecs().getImputationDate());
            hearingFormatView.setVincProcess(existHF.getHearingFormatSpecs().getLinkageProcess());
            hearingFormatView.setLinkageRoom(existHF.getHearingFormatSpecs().getLinkageRoom());
            hearingFormatView.setLinkageDate(existHF.getHearingFormatSpecs().getLinkageDate());
            hearingFormatView.setLinkageTime(existHF.getHearingFormatSpecs().getLinkageTime());
            hearingFormatView.setControlDetention(existHF.getHearingFormatSpecs().getControlDetention());
        }

        hearingFormatView.setUserName(existHF.getSupervisor().getFullname());

        if (existHF.getHearingFormatSpecs() != null && existHF.getHearingFormatSpecs().getLinkageProcess() != null &&
                (existHF.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_YES) || existHF.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_NO_REGISTER))) {
            hearingFormatView.setArrangementType(existHF.getHearingFormatSpecs().getArrangementType());
            hearingFormatView.setNationalArrangement(existHF.getHearingFormatSpecs().getNationalArrangement());
            hearingFormatView.setTerms(existHF.getTerms());

            if (existHF.getHearingFormatSpecs().getNationalArrangement() != null && existHF.getHearingFormatSpecs().getArrangementType() != null) {

                List<ArrangementView> lstExistArrangement = this.getArrangmentLst(existHF.getHearingFormatSpecs().getNationalArrangement(), existHF.getHearingFormatSpecs().getArrangementType());
                hearingFormatView.setLstArrangement(conv.toJson(this.selectedAssignedArrangementForView(lstExistArrangement, existHF.getAssignedArrangements())));
            }

            hearingFormatView.setLstContactData(conv.toJson(this.contactDataForView(existHF.getContacts())));
        }

        hearingFormatView.setImputedPresence(existHF.getImputedPresence());
        hearingFormatView.setHearingResult(existHF.getHearingResult());
        hearingFormatView.setPreviousHearing(existHF.getPreviousHearing());

        hearingFormatView.setComments(existHF.getComments());
        hearingFormatView.setIsFinished(false);

        return hearingFormatView;
    }


    public List<ArrangementView> getArrangmentLst(Boolean national, Integer idTipo) {

        List<ArrangementView> lstArrmntView = new ArrayList<>();

        List<Arrangement> lstArrmnt = arrangementRepository.findByType(national, idTipo);
        Collections.sort(lstArrmnt, Arrangement.arrangementComparator);

        for (Arrangement arrmnt : lstArrmnt) {
            ArrangementView arrV = new ArrangementView();
            arrV.setId(arrmnt.getId());
            arrV.setName(arrmnt.getDescription());
            arrV.setIsDefault(arrmnt.getIsDefault());
            if (arrV.getIsDefault() == true)
                arrV.setSelVal(true);
            else
                arrV.setSelVal(false);
            arrV.setIsExclusive(arrmnt.getIsExclusive());
            lstArrmntView.add(arrV);
        }

        return lstArrmntView;
    }

    public List<ArrangementView> selectedAssignedArrangementForView
            (List<ArrangementView> existArrangements, List<AssignedArrangement> assignedArrangements) {

        List<Long> idsSelected = new ArrayList<>();
        for (AssignedArrangement assArr : assignedArrangements) {
            idsSelected.add(assArr.getArrangement().getId());
        }

        for (ArrangementView existArr : existArrangements) {
            for (AssignedArrangement assArr : assignedArrangements) {
                if (assArr.getArrangement().getId() == existArr.getId()) {
                    existArr.setSelVal(true);
                    existArr.setDescription(assArr.getDescription());
                }
            }
        }

        for (ArrangementView existArr : existArrangements) {
            if (!idsSelected.contains(existArr.getId())) {
                existArr.setSelVal(false);
                existArr.setDescription("");
            }
        }

        return existArrangements;
    }

    @Autowired
    FramingMeetingRepository framingMeetingRepository;
    @Autowired
    RequestTypeRepository requestTypeRepository;
    @Autowired
    CaseRequestRepository caseRequestRepository;
    @Autowired
    MessageRepository messageRepository;


    @Transactional
    @Override
    public ResponseMessage requestObsoleteCase(Long id) {

        if (framingMeetingRepository.getIdByIdCase(id) != null)
            return new ResponseMessage(true, "No es posible eliminar el caso ya que cuenta con una entrevista de encuadre.", "Error al mandar la solicitud");
        Case c = caseRepository.findOne(id);
        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        User userReceiver = sharedUserService.getLstValidUserIdsByRole(Constants.ROLE_SUPERVISOR_MANAGER).get(0);
        CaseRequestService.CreateCaseRequestByCase(requestTypeRepository, caseRequestRepository, sharedUserService, messageRepository, c,
                "Se solicita eliminar el caso", Constants.ST_REQUEST_CASE_OBSOLETE_SUPERVISION, c.getStatus().getName(), Constants.ROLE_SUPERVISOR_MANAGER);
        c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_REQUEST_SUPERVISION));
        c.setDateObsolete(new Date());
        caseRepository.save(c);
        SharedLogCommentService.generateLogComment(MonitoringConstants.LOG_MSG_INFO_PENDING_AUTHORIZATION_OBSOLETE, u, c,
                Constants.ACTION_AUTHORIZE_LOG_COMMENT, userReceiver, Constants.TYPE_COMMENT_OBSOLETE_CASE_SUPERVISION, logCommentRepository);
        return new ResponseMessage(false, "Se ha mandado la solictud satisfactoriamente");
    }

    public List<ArrangementView> assignedArrangementForView(List<AssignedArrangement> assignedArrangements) {

        List<ArrangementView> lstArrmntView = new ArrayList<>();

        for (AssignedArrangement assArr : assignedArrangements) {
            ArrangementView arrV = new ArrangementView();
            arrV.setId(assArr.getId());
            arrV.setName(assArr.getArrangement().getDescription());
            arrV.setDescription(assArr.getDescription());
            arrV.setSelVal(true);
            lstArrmntView.add(arrV);
        }

        return lstArrmntView;
    }

    public List<ContactDataView> contactDataForView(List<ContactData> contacts) {

        List<ContactDataView> lstContactView = new ArrayList<>();

        for (ContactData contact : contacts) {
            ContactDataView conV = new ContactDataView();
            conV.setName(contact.getNameTxt());
            conV.setPhone(contact.getPhoneTxt());
            conV.setAddress(contact.getAddressTxt());
            lstContactView.add(conV);
        }

        return lstContactView;
    }


    @Autowired
    LogCommentRepository logCommentRepository;

    @Autowired
    private ContactDataRepository contactDataRepository;

    @Autowired
    private AssignedArrangementRepository assignedArrangementRepository;

    @Autowired
    private MonitoringPlanRepository monitoringPlanRepository;

    @Autowired
    private FramingAddressRepository framingAddressRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    LogCaseService logCaseService;

    @Override
    @Transactional
    public ResponseMessage save(HearingFormat hearingFormat, HttpServletRequest request) {
        ResponseMessage response = new ResponseMessage();
        StringBuilder sb = new StringBuilder();
        String idFolder = hearingFormat.getCaseDetention().getIdFolder();
        String idJudicial = hearingFormat.getCaseDetention().getIdMP();
        Long idCase = hearingFormat.getCaseDetention().getId();

        if (hearingFormat.getIsFinished() != null && hearingFormat.getIsFinished() == true) {
            hearingFormat.getCaseDetention().setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END));
            hearingFormat.setEndTime(new Time(new Date().getTime()));
            if (idJudicial == null || idJudicial.trim().equals("")) {
                hearingFormat.getCaseDetention().setIdMP(hearingFormat.getIdJudicial());
            }

            //verificacion de cambio de arrangementType

            Boolean actualValue = hearingFormat.getCaseDetention().getChangeArrangementType();

            if (!actualValue.equals(true)) {//si no se ha registrado cambio, realizo la verificacion

                List<Integer> listHearingTypes = hearingFormatRepository.getLastArrangementType(hearingFormat.getCaseDetention().getId(), new PageRequest(0, 1));
                Integer lastHearingType = -1;

                if (listHearingTypes != null && listHearingTypes.size() > 0) {
                    lastHearingType = listHearingTypes.get(0);
                }

                if (lastHearingType > 0 && !lastHearingType.equals(hearingFormat.getHearingFormatSpecs().getArrangementType())) {
                    hearingFormat.getCaseDetention().setChangeArrangementType(true);
                }
            }

            //si se indica que el imputado ha sido sustraido

            if (hearingFormat.getIsSubstracted() != null) {

                hearingFormat.getCaseDetention().setIsSubstracted(hearingFormat.getIsSubstracted());

                if (hearingFormat.getIsSubstracted().equals(true)) {//se debe realizar el cambio del estatus del plan si es que existe

                    MonitoringPlan existMonP = hearingFormat.getCaseDetention().getMonitoringPlan();

                    if (existMonP != null) {
                        existMonP.setStatus(MonitoringConstants.STATUS_SUSPENDED_SUBSTRACTED);
                    }
                }
            }

        } else {
            hearingFormat.getCaseDetention().setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_INCOMPLETE));
        }

        if (hearingFormat.getIsFinished() != null && hearingFormat.getIsFinished() == true && hearingFormat.getHearingFormatSpecs() != null && hearingFormat.getHearingFormatSpecs().getLinkageProcess() != null &&
                hearingFormat.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_NO)) {

            hearingFormat.getCaseDetention().setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_PRE_CLOSED));

            sb.append("Solicitud de cierre de caso: ");
            sb.append(StringEscape.escapeText(idFolder));
            sb.append(". Comentario: ");
            sb.append(StringEscape.escapeText(hearingFormat.getConfirmComment()));
            sb.append(".");

            SharedLogCommentService.generateLogComment(sb.toString(), userRepository.findOne(sharedUserService.GetLoggedUserId()),
                    hearingFormat.getCaseDetention(), MonitoringConstants.STATUS_PENDING_AUTHORIZATION, null, MonitoringConstants.TYPE_COMMENT_CASE_END, logCommentRepository);
        }

        if (hearingFormat.getIsFinished() != null && hearingFormat.getIsFinished() == true && hearingFormat.getHearingFormatSpecs() != null &&
                hearingFormat.getHearingFormatSpecs().getLinkageProcess() != null &&
                (hearingFormat.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_YES)
                        || hearingFormat.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_NO_REGISTER))) {
            MonitoringPlan monP = hearingFormat.getCaseDetention().getMonitoringPlan();
            hearingFormat.setShowNotification(true);
            if (monP != null) {
                monP.setResolution(hearingFormat.getHearingFormatSpecs().getArrangementType());
                monitoringPlanRepository.save(monP);
            }

            FramingMeeting existFramning = hearingFormat.getCaseDetention().getFramingMeeting();
            if (existFramning != null) { //si tiene una entrevista de encuadre se verifica si se ha cambiado la direccion en el formato de audiencia

                Address lastFormatAddress = addressRepository.findOne(hearingFormatRepository.getLastFormatAddressByIdCase(idCase));
                Address newFormatAddress = hearingFormat.getHearingImputed().getAddress();

                if (lastFormatAddress == null || !lastFormatAddress.equals(newFormatAddress)) {
                    FramingMeeting existFraming = hearingFormat.getCaseDetention().getFramingMeeting();

                    if (existFraming != null) {
                        Address fAddress = addressRepository.findOne(framingAddressRepository.getLastIdAddressByIdCase(idCase));

                        if (fAddress != null && !newFormatAddress.equals(fAddress)) {
                            FramingAddress newFramAddr = new FramingAddress();

                            newFramAddr.setFramingMeeting(existFraming);
                            Address addrObj = new Address();
                            addrObj.setStreet(newFormatAddress.getStreet());
                            addrObj.setOutNum(newFormatAddress.getOutNum());
                            addrObj.setInnNum(newFormatAddress.getInnNum());
                            addrObj.setLocation(newFormatAddress.getLocation());
                            addrObj.setAddressString(addrObj.toString());
                            newFramAddr.setAddress(addrObj);
                            framingAddressRepository.save(newFramAddr);
                        }
                    }
                }
            }
        }

        response.setHasError(false);

        hearingFormatRepository.save(hearingFormat);

        if (hearingFormat.getIsFinished() == true) {
            List<LogCase> logs = logCaseService.addLog(ConstantsLogCase.NEW_HEARING_FORMAT, hearingFormat.getCaseDetention().getId(), hearingFormat.getId());
            if (logs.size() > 0) {
                LogCase l = logs.get(logs.size() - 1);
                LogComment logComment = new LogComment();
                logComment.setComments(l.getResume());
                logComment.setAction(l.getTitle());
                Case c = l.getCaseDetention();
                logComment.setCaseDetention(c);
                logComment.setSenderUser(hearingFormat.getSupervisor());
                Long mp = monitoringPlanRepository.getMonPlanIdByCaseId(c.getId());
                logComment.setAction(ConstantsLogCase.TT_ADD_HEARING_FORMAT);
                if (mp != null) {
                    Long uid = monitoringPlanRepository.getUserIdByMonPlanId(mp);
                    User u = new User();
                    u.setId(uid);
                    logComment.setReceiveUser(u);
                } else {
                    logComment.setReceiveUser(hearingFormat.getSupervisor());
                }
                logComment.setTimestamp(Calendar.getInstance());
                logComment.setType(ConstantsLogCase.NEW_HEARING_FORMAT);
                logComment.setObsolete(false);
                logCommentRepository.save(logComment);
            }
            sb = new StringBuilder();
            sb.append(request.getContextPath());
            sb.append("/supervisor/hearingFormat/indexFormats.html?id=");
            sb.append(hearingFormat.getCaseDetention().getId());
            response.setUrlToGo(sb.toString());
        } else {
            response.setMessage(hearingFormat.getId() + "|Se ha registrado el formato de audiencia.");
        }

        return response;

    }

    @Override
    public ResponseMessage validatePassCredential(String pass) {

        if (!sharedUserService.isValidPasswordForUser(sharedUserService.GetLoggedUserId(), pass)) {
            return new ResponseMessage(true, "La contraseña es incorrecta, verifique los datos.");
        }
        return null;
    }

}
