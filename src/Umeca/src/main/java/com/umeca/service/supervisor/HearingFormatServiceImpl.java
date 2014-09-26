package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.ImputedHome;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.supervisor.AssignedArrangementRepository;
import com.umeca.repository.supervisor.ContactDataRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.repository.supervisorManager.LogCommentRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.CatalogService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.reviewer.CaseServiceImpl;
import com.umeca.service.shared.SharedLogCommentService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

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

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
            e.printStackTrace();
            logException.Write(e, this.getClass(), "parsingDataHearingFormat", sharedUserService);
            return null;
        }

        hearingFormat.setJudgeName(viewFormat.getJudgeName());
        hearingFormat.setMpName(viewFormat.getMpName());
        hearingFormat.setDefenderName(viewFormat.getDefenderName());

        if (hasFirstFH) {
            hearingFormat.setHearingImputed(lastHF.getHearingImputed());
        } else {

            HearingFormatImputed hearingImputed = new HearingFormatImputed();

            hearingImputed.setName(viewFormat.getImputedName());
            hearingImputed.setLastNameP(viewFormat.getImputedFLastName());
            hearingImputed.setLastNameM(viewFormat.getImputedSLastName());

            try {
                if (viewFormat.getImputedBirthDateStr() != null && !viewFormat.getImputedBirthDateStr().trim().equals(""))
                    hearingImputed.setBirthDate(sdf.parse(viewFormat.getImputedBirthDateStr()));
                else
                    hearingImputed.setBirthDate(null);
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error ");
                e.printStackTrace();
                logException.Write(e, this.getClass(), "parsingBirthDateHearingFormat", sharedUserService);
                return null;
            }

            hearingImputed.setImputeTel(viewFormat.getImputedTel());

            if (viewFormat.getLocation() != null && viewFormat.getLocation().getId() != null) {
                Address address = new Address();
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
        }

        HearingFormatSpecs hearingSpecs = new HearingFormatSpecs();
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
            System.out.println("Ha ocurrido un error!!!");
            e.printStackTrace();
            logException.Write(e, this.getClass(), "parsingSpecsHearingFormat", sharedUserService);
            return null;
        }

        hearingSpecs.setLinkageProcess(viewFormat.getVincProcess());
        hearingSpecs.setLinkageRoom(viewFormat.getLinkageRoom());


        if (viewFormat.getVincProcess() != null && viewFormat.getVincProcess().equals(HearingFormatConstants.PROCESS_VINC_YES)) {

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

                    for (ContactData act : oldContacts) {
                        act.setHearingFormat(null);
                        contactDataRepository.delete(act);
                    }
                }
            }

        } else {
            hearingFormat.setConfirmComment(viewFormat.getConfirmComment());
        }

        hearingFormat.setHearingFormatSpecs(hearingSpecs);

        hearingFormat.setAdditionalData(viewFormat.getAdditionalData());
        hearingFormat.setCrimes(viewFormat.getCrimes());
        hearingFormat.setIsFinished(viewFormat.getIsFinished());

        return hearingFormat;
    }

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

                Meeting verif = existCase.getVerification().getMeetingVerified();

                if (verif.getImputed() != null) {
                    hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                    hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                    hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                    hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getBirthDate());
                }

                List<ImputedHome> homes = verif.getImputedHomes();

                if (homes != null && homes.size() > 0) {
                    Collections.sort(homes, ImputedHome.imputedHomeComparator);
                }

                hearingFormatView.setIdAddres(homes.get(0).getAddress().getId());

                User us = userRepository.findOne(sharedUserService.GetLoggedUserId());
                hearingFormatView.setUserName(us.getFullname());

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

        hearingFormatView.setAdditionalData(existHF.getAdditionalData());
        hearingFormatView.setCrimes(existHF.getCrimes());

        if (newFormat == true)
            hearingFormatView.setUserName(userRepository.findOne(sharedUserService.GetLoggedUserId()).getFullname());
        else
            hearingFormatView.setUserName(existHF.getSupervisor().getFullname());


        if (existHF.getHearingFormatSpecs() != null && existHF.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_YES)) {
            hearingFormatView.setArrangementType(existHF.getHearingFormatSpecs().getArrangementType());
            hearingFormatView.setNationalArrangement(existHF.getHearingFormatSpecs().getNationalArrangement());
            hearingFormatView.setTerms(existHF.getTerms());

            if (existHF.getHearingFormatSpecs().getNationalArrangement() != null && existHF.getHearingFormatSpecs().getArrangementType() != null) {

                List<ArrangementView> lstExistArrangement = this.getArrangmentLst(existHF.getHearingFormatSpecs().getNationalArrangement(), existHF.getHearingFormatSpecs().getArrangementType());
                hearingFormatView.setLstArrangement(conv.toJson(this.selectedAssignedArrangementForView(lstExistArrangement, existHF.getAssignedArrangements())));
            }
        }

        hearingFormatView.setLstContactData(conv.toJson(this.contactDataForView(existHF.getContacts())));
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

        hearingFormatView.setAdditionalData(existHF.getAdditionalData());
        hearingFormatView.setCrimes(existHF.getCrimes());
        hearingFormatView.setUserName(existHF.getSupervisor().getFullname());

        if (existHF.getHearingFormatSpecs() != null && existHF.getHearingFormatSpecs().getLinkageProcess() != null && existHF.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_YES)) {
            hearingFormatView.setArrangementType(existHF.getHearingFormatSpecs().getArrangementType());
            hearingFormatView.setNationalArrangement(existHF.getHearingFormatSpecs().getNationalArrangement());
            hearingFormatView.setTerms(existHF.getTerms());

            if (existHF.getHearingFormatSpecs().getNationalArrangement() != null && existHF.getHearingFormatSpecs().getArrangementType() != null) {

                List<ArrangementView> lstExistArrangement = this.getArrangmentLst(existHF.getHearingFormatSpecs().getNationalArrangement(), existHF.getHearingFormatSpecs().getArrangementType());
                hearingFormatView.setLstArrangement(conv.toJson(this.selectedAssignedArrangementForView(lstExistArrangement, existHF.getAssignedArrangements())));
            }

            hearingFormatView.setLstContactData(conv.toJson(this.contactDataForView(existHF.getContacts())));
        }

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
            arrV.setSelVal(false);
            lstArrmntView.add(arrV);
        }

        return lstArrmntView;
    }

    public List<ArrangementView> selectedAssignedArrangementForView(List<ArrangementView> existArrangements, List<AssignedArrangement> assignedArrangements) {

        List<ArrangementView> lstArrmntView = new ArrayList<>();

        for (ArrangementView existArr : existArrangements) {
            for (AssignedArrangement assArr : assignedArrangements) {

                if (assArr.getArrangement().getId() == existArr.getId()) {
                    existArr.setSelVal(true);
                    existArr.setDescription(assArr.getDescription());
                }
//                ArrangementView arrV = new ArrangementView();
//                arrV.setId(assArr.getId());
//                arrV.setName(assArr.getArrangement().getDescription());
//                arrV.setDescription(assArr.getDescription());
//                arrV.setSelVal(true);
//                lstArrmntView.add(arrV);
            }
        }

        return existArrangements;
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


    @Override
    @Transactional
    public ResponseMessage save(HearingFormat hearingFormat, HttpServletRequest request) {
        ResponseMessage response = new ResponseMessage();
        StringBuilder sb = new StringBuilder();

        try {

            if (hearingFormat.getIsFinished() != null && hearingFormat.getIsFinished() == true) {
                hearingFormat.getCaseDetention().setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_END));
                if (hearingFormat.getCaseDetention().getIdMP() == null || hearingFormat.getCaseDetention().getIdMP().trim().equals("")) {
                    hearingFormat.getCaseDetention().setIdMP(hearingFormat.getIdJudicial());
                }
            } else {
                hearingFormat.getCaseDetention().setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_HEARING_FORMAT_INCOMPLETE));
            }

            if (hearingFormat.getIsFinished() != null && hearingFormat.getIsFinished() == true && hearingFormat.getHearingFormatSpecs() != null && hearingFormat.getHearingFormatSpecs().getLinkageProcess() != null &&
                    hearingFormat.getHearingFormatSpecs().getLinkageProcess().equals(HearingFormatConstants.PROCESS_VINC_NO)) {

                hearingFormat.getCaseDetention().setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_PRE_CLOSED));

                sb.append("Solicitud de cierre de caso: ");
                sb.append(hearingFormat.getCaseDetention().getIdFolder());
                sb.append(". Comentario: ");
                sb.append(hearingFormat.getConfirmComment());
                sb.append(".");

                SharedLogCommentService.generateLogComment(sb.toString(), userRepository.findOne(sharedUserService.GetLoggedUserId()),
                        hearingFormat.getCaseDetention(), MonitoringConstants.STATUS_PENDING_AUTHORIZATION, null, MonitoringConstants.TYPE_COMMENT_CASE_END, logCommentRepository);
            }

            response.setHasError(false);

            if (hearingFormat.getIsFinished() == true) {
                sb = new StringBuilder();
                sb.append(request.getContextPath());
                sb.append("/supervisor/hearingFormat/indexFormats.html?id=");
                sb.append(hearingFormat.getCaseDetention().getId());
                response.setUrlToGo(sb.toString());
            } else {
                response.setMessage(hearingFormat.getId() + "|Se ha registrado el formato de audiencia.");

            }


        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al guardar el formato de audiencia (serviceImpl)!!!");
            e.printStackTrace();
            logException.Write(e, this.getClass(), "saveHearingFormat", sharedUserService);
            response.setHasError(true);
        } finally {
            return response;
        }
    }

    @Override
    public ResponseMessage validatePassCredential(String pass) {

        if (!sharedUserService.isValidPasswordForUser(sharedUserService.GetLoggedUserId(), pass)) {
            return new ResponseMessage(true, "El password es incorrecto, verfifique los datos.");
        }
        return null;
    }

}
