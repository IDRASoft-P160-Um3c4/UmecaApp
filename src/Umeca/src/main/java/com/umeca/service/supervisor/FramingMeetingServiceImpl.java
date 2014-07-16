package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.catalog.dto.AddressDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.AddressRepository;
import com.umeca.repository.reviewer.DrugRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.service.catalog.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private AddressRepository addressRepository;

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
    private AdditionalFramingQuestionsRepository additionalFramingQuestionsRepository;

    @Autowired
    private ArrangementRepository arrangementRepository;

    @Autowired
    private ObligationIssuesRepository obligationIssuesRepository;

    @Transactional
    @Override
    public ResponseMessage save(FramingMeeting framingMeeting) {

        ResponseMessage response = new ResponseMessage();
        try {
            framingMeetingRepository.save(framingMeeting);
            response.setHasError(false);
            response.setMessage("Se ha guardado la informaci?n con exito.");
        } catch (Exception e) {
            System.out.println("Error al guardar framingMeeting!!!");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error al guardar la informaci?n. Intente más tarde");
        } finally {
            return response;
        }
    }

    @Override
    public FramingMeeting fillFramingMeeting(FramingMeetingView viewFraming) {

        FramingMeeting framingMeeting = new FramingMeeting();


        return framingMeeting;
    }

    public FramingPersonalDataView fillPersonalDataForView(Long idCase) {

        FramingPersonalDataView view = new FramingPersonalDataView();
        FramingMeeting existFramning = caseRepository.findOne(idCase).getFramingMeeting();

        if (existFramning.getPersonalData() != null) {
            view.setName(existFramning.getPersonalData().getName());
            view.setLastNameP(existFramning.getPersonalData().getLastNameP());
            view.setLastNameM(existFramning.getPersonalData().getLastNameM());
            view.setGender(existFramning.getPersonalData().getGender());
            view.setMaritalStatus(existFramning.getPersonalData().getMaritalStatus().getId());
            view.setMaritalStatusYears(existFramning.getPersonalData().getMaritalStatusYears());
            view.setBirthCountryId(existFramning.getPersonalData().getBirthCountry().getId());
            view.setBirthState(existFramning.getPersonalData().getBirthState());
            view.setBirthDate(existFramning.getPersonalData().getBirthDate());
            view.setPhysicalCondition(existFramning.getPersonalData().getPhysicalCondition());
        }

        return view;
    }

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

        return personalData;
    }

    @Override
    public FramingMeetingView fillForView(Case existCase) {

        FramingMeetingView framingMeetingView = new FramingMeetingView();

        framingMeetingView.setIdFolder(existCase.getIdFolder());
        framingMeetingView.setIdCase(existCase.getId());

        return framingMeetingView;
    }

    @Override
    public List<FramingReferenceForView> loadExistSources(Long idCase) {

        List<FramingReferenceForView> lstView = new ArrayList<>();

        List<FramingReference> existSources = caseRepository.findOne(idCase).getFramingMeeting().getReferences();

        for (FramingReference fr : existSources) {

            FramingReferenceForView objView = new FramingReferenceForView();
            objView.setId(fr.getId());
            objView.setDescription(fr.getName() + ", " + fr.getRelationship().getName());
            objView.setValSel(false);
            lstView.add(objView);
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
            rel.setFramingReference(framingReferenceRepository.findOne(currId));
            sourceRel.add(rel);
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

            existFraming = framingMeetingRepository.save(existFraming);
            framingMeetingRepository.flush();

            return new ResponseMessage(false, "Se ha guardado la informaci?n con exito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci?n. Intente más tarde.");
        }
    }

    public ResponseMessage saveReference(Case existCase, FramingReference newReference) {

        try {
            newReference.setRelationship(relationshipRepository.findOne(newReference.getRelationshipId()));
            newReference.setFramingMeeting(existCase.getFramingMeeting());
            framingReferenceRepository.save(newReference);
            return new ResponseMessage(false, "Se ha guardado la informaci?n con exito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci?n. Intente más tarde.");
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
        view.setAddress(conv.toJson(new AddressDto().addressDto(addressRepository.findOne(processAccompaniment.getAddress().getId()))));

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
            return new ResponseMessage(false, "Se ha guardado la informaci?n con exito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci?n. Intente más tarde.");
        }
    }

    public FramingMeeting setActivities(FramingMeeting existFraming, FramingActivitiesForView view) {

        Occupation occ = existFraming.getOccupation();

        if (occ == null)
            occ = new Occupation();

        occ.setName(view.getOccName());
        occ.setPlace(view.getOccPlace());
        occ.setPhone(view.getOccPhone());

        existFraming.setOccupation(occ);
        existFraming.setActivities(view.getActivities());

        return existFraming;
    }

    public FramingEnvironmentAnalysisForView loadEnvironmentAnalysis(Long idCase) {//todo
        Gson conv = new Gson();
        FramingEnvironmentAnalysisForView view = new FramingEnvironmentAnalysisForView();

        view.setLstSources(conv.toJson(this.loadExistSources(idCase)));

        List<FramingRisk> lstRisks = framingRiskRepository.findAll();
        Collections.sort(lstRisks, FramingRisk.framingRiskComparator);

        view.setLstRisk(conv.toJson(lstRisks));

        List<FramingThreat> lstThreat = framingThreatRepository.findAll();
        Collections.sort(lstThreat, FramingThreat.framingThreatComparator);

        view.setLstThreat(conv.toJson(lstThreat));

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

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

    public Address fillAddress(Address address, AddressDto view) {

        if (address == null)
            address = new Address();

        address.setStreet(view.getStreet());
        address.setOutNum(view.getOutNum());
        address.setInnNum(view.getInnNum());
        address.setLocation(locationRepository.findOne(view.getLocation().getId()));
        address.setAddressString(address.toString());

        return address;
    }

    public FramingActivitiesForView fillActivitiesForView(Long idCase) {

        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

        FramingActivitiesForView view = new FramingActivitiesForView();

        if (existFraming.getOccupation() != null) {
            view.setOccName(existFraming.getOccupation().getName());
            view.setOccPlace(existFraming.getOccupation().getPlace());
            view.setOccPhone(existFraming.getOccupation().getPhone());
        }

        if (existFraming.getActivities() != null) {
            view.setActivities(existFraming.getActivities());
        }

        return view;
    }

    public ResponseMessage saveFramingAddress(Long idCase, AddressDto view) {

        try {
            FramingAddress framingAddress;
            if (view.getId() != null)
                framingAddress = framingAddressRepository.findOne(view.getId());
            else
                framingAddress = new FramingAddress();

            framingAddress.setAddress(this.fillAddress(framingAddress.getAddress(), view));
            framingAddress.setFramingMeeting(caseRepository.findOne(idCase).getFramingMeeting());
            framingAddressRepository.save(framingAddress);

            return new ResponseMessage(false, "Se ha guardado la informaci?n con ?xito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la informaci?n. Intente más tarde.");
        }
    }

    public ResponseMessage deleteFramingAddress(Long id) {

        try {

            framingAddressRepository.delete(id);

            return new ResponseMessage(false, "Se ha eliminado el registro con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la información. Intente más tarde.");
        }
    }

    public ResponseMessage deleteReference(Long id) {

        try {

            if (framingSelectedSourceRelRepository.findSourceRelByIdSource(id) != null)
                return new ResponseMessage(true, "No se puede eliminar la información. El registro ha sido seleccionado en la sección Análisis del entorno.");

            framingReferenceRepository.delete(id);

            return new ResponseMessage(false, "Se ha eliminado el registro con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la información. Intente más tarde.");
        }
    }

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
            result.setMessage("Se ha guardado la información con éxito");
        } catch (Exception e) {
            result.setHasError(true);
            result.setMessage("Ocurrio un error al guardar la información. Inténte más tarde.");
        }
        return result;
    }

    @Transactional
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

        for (Relationship relShip : relationshipRepository.findAll()) {
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

            return new ResponseMessage(false, "Se ha guardado la información con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la información. Intente más tarde.");
        }

    }

    public ResponseMessage doTerminate(Long idCase) {

        StringBuilder sb = new StringBuilder();
        try {
            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

            if (existFraming.getPersonalData() == null)
                if (sb.toString().equals(""))
                    sb.append("Debe proporcionar la información faltante para la sección \"Datos personales y entorno social\".");
                else
                    sb.append("|Debe proporcionar la información faltante para la sección \"Datos personales y entorno social\".");

            if (existFraming.getFramingAddresses() == null || !(existFraming.getFramingAddresses().size() > 0))
                if (sb.toString().equals(""))
                    sb.append("Debe registrar al menos un registro en la sección \"Domicilios\".");
                else
                    sb.append("|Debe registrar al menos un registro en la sección \"Domicilios\".");

            if (existFraming.getProcessAccompaniment() == null)
                if (sb.toString().equals(""))
                    sb.append("Debe proporcionar la información faltante para la sección \"Persona que acompaña en el proceso\".");
                else
                    sb.append("|Debe proporcionar la información faltante para la sección \"Persona que acompaña en el proceso\".");


            if (existFraming.getReferences() != null&&existFraming.getReferences().size()>0) {
                int noHousemate = 0, noReferences = 0;

                for (FramingReference fr : existFraming.getReferences()) {
                    if (fr.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE))
                        noHousemate++;
                    if (fr.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE))
                        noReferences++;
                }

                if (noHousemate == 0)
                    if (sb.toString().equals(""))
                        sb.append("Debe registrar al menos una registro en en la sección \"Personas que viven con el imputado\".");
                    else
                        sb.append("|Debe registrar al menos una registro en en la sección \"Personas que viven con el imputado\".");
                if (noReferences == 0)
                    if (sb.toString().equals(""))
                        sb.append("Debe registrar al menos una registro en en la sección \"Referencias personales\".");
                    else
                        sb.append("|Debe registrar al menos una registro en en la sección \"Referencias personales\".");

            } else {

                if (sb.toString().equals(""))
                    sb.append("Debe registrar al menos una registro en en la sección \"Personas que viven con el imputado\".");
                else
                    sb.append("|Debe registrar al menos una registro en en la sección \"Personas que viven con el imputado\".");

                sb.append("|Debe registrar al menos una registro en en la sección \"Referencias personales\".");
            }


            if (existFraming.getOccupation() == null && existFraming.getActivities() == null)
                if (sb.toString().equals(""))
                    sb.append("Debe proporcionar la información faltante para la sección \"Actividades que realiza el imputado\".");
                else
                    sb.append("|Debe proporcionar la información faltante para la sección \"Actividades que realiza el imputado\".");
            if (existFraming.getDrugs() == null || !(existFraming.getDrugs().size() > 0))
                if (sb.toString().equals(""))
                    sb.append("Debe registrar al menos una registro en en la sección \"Consumo de substancias\".");
                else
                    sb.append("|Debe registrar al menos una registro en en la sección \"Consumo de substancias\".");
            if (existFraming.getSelectedSourcesRel() == null || !(existFraming.getSelectedSourcesRel().size() > 0) ||
                    existFraming.getSelectedRisksRel() == null || !(existFraming.getSelectedRisksRel().size() > 0) ||
                    existFraming.getSelectedThreatsRel() == null || !(existFraming.getSelectedThreatsRel().size() > 0))
                if (sb.toString().equals(""))
                    sb.append("Debe proporcionar la información faltante para la sección \"Anáisis del entorno\".");
                else
                    sb.append("|Debe proporcionar la información faltante para la sección \"Anáisis del entorno\".");
            if (existFraming.getAdditionalFramingQuestions() == null)
                if (sb.toString().equals(""))
                    sb.append("Debe proporcionar la información faltante para la sección \"Formulario de preguntas al supervisado\".");
                else
                    sb.append("|Debe proporcionar la información faltante para la sección \"Formulario de preguntas al supervisado\".");
            if (!sb.toString().equals("")) {
                return new ResponseMessage(true, sb.toString());
            }

            Case existCase = caseRepository.findOne(idCase);
            existCase.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_FRAMING_COMPLETE));
            caseRepository.save(existCase);
            return new ResponseMessage(false, "Se ha guardado la información con exito");

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage(false, "Ha ocurrido un error al guardar la información. Intente más tarde.");
        }
    }

}