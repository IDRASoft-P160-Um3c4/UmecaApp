package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("hearingFormatService")
public class HearingFormatServiceImpl implements HearingFormatService {

    @Qualifier("qHearingFormatRepository")
    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Qualifier("locationRepository")
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

    private Gson conv = new Gson();

    /*
    @Transactional
    @Override
    public ResponseMessage save(HearingFormat hearingFormat) {

        ResponseMessage response = new ResponseMessage();
        try {
            hearingFormatRepository.save(hearingFormat);
            response.setHasError(false);
        } catch (Exception e) {
            System.out.println("error al guardar hearingFormat!\n");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage(e.getMessage());
        } finally {
            return response;
        }
    }
*/
    @Override
    public HearingFormat fillHearingFormat(HearingFormatView viewFormat) {

        HearingFormat hearingFormat = new HearingFormat();

        hearingFormat.setRegisterTimestamp(new Timestamp(new Date().getTime()));
        hearingFormat.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));

        Boolean hasFirstFH = false;
        HearingFormat lastHF = null;
        Case existCase = caseRepository.findOne(viewFormat.getIdCase());

        if (existCase.getHearingFormats().size() > 0) {
            hasFirstFH = true;
            Collections.sort(existCase.getHearingFormats(), HearingFormat.hearingFormatComparator);
            lastHF = existCase.getHearingFormats().get(0);
        }

        if (hasFirstFH) {
            hearingFormat.setIdFolder(lastHF.getIdFolder());
            hearingFormat.setIdJudicial(lastHF.getIdJudicial());
        } else {
            hearingFormat.setIdFolder(viewFormat.getIdFolder());
            hearingFormat.setIdJudicial(viewFormat.getIdJudicial());
        }

        hearingFormat.setRoom(viewFormat.getRoom());
        hearingFormat.setAppointmentDate(viewFormat.getAppointmentDate());
        hearingFormat.setInitTime(viewFormat.getInitTime());
        hearingFormat.setEndTime(viewFormat.getEndTime());
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
            hearingImputed.setBirthDate(viewFormat.getImputedBirthDate());
            hearingImputed.setImputeTel(viewFormat.getImputedTel());
            //TODO falta el domicilio

            hearingFormat.setHearingImputed(hearingImputed);
        }

        HearingFormatSpecs hearingSpecs = new HearingFormatSpecs();
        hearingSpecs.setControlDetention(viewFormat.getControlDetention());
        hearingSpecs.setExtension(viewFormat.getExtension());
        hearingSpecs.setImputationFormulation(viewFormat.getImpForm());
        hearingSpecs.setImputationDate(viewFormat.getImputationDate());
        hearingSpecs.setLinkageProcess(viewFormat.getVincProcess());
        hearingSpecs.setLinkageRoom(viewFormat.getLinkageRoom());
        hearingSpecs.setLinkageDate(viewFormat.getLinkageDate());
        hearingSpecs.setLinkageTime(viewFormat.getLinkageTime());
        hearingSpecs.setHearingType(viewFormat.getHearingType());

        hearingFormat.setHearingFormatSpecs(hearingSpecs);

        hearingFormat.setAdditionalData(viewFormat.getAdditionalData());
        hearingFormat.setCrimes(viewFormat.getCrimes());
        hearingFormat.setTerms(viewFormat.getTerms());

        List<ArrangementView> lstAssignedArrnmtView;
        Type type = new TypeToken<List<ArrangementView>>() {
        }.getType();
        lstAssignedArrnmtView = conv.fromJson(viewFormat.getLstArrangement(), type);

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

        hearingFormat.setAssignedArrangements(lstNewAssigArrmnt);

        //todo falta la lista de contactos

        return hearingFormat;
    }

    @Override
    public HearingFormatView fillNewHearingFormatForView(Long idCase) {

        HearingFormatView hearingFormatView = new HearingFormatView();

        Case existCase = caseRepository.findOne(idCase);
        Integer meetType = existCase.getMeeting().getMeetingType();

        if (existCase.getHearingFormats() != null && existCase.getHearingFormats().size() > 0) {//busco si ya existe algun formato

            if (existCase.getHearingFormats().size() > 1)//si hay mas de uno
                Collections.sort(existCase.getHearingFormats(), HearingFormat.hearingFormatComparator); //los ordeno por su id


            hearingFormatView = this.fillExistHearingFormatForView(existCase.getHearingFormats().get(0).getId());

            //existCase.getHearingFormats().get(0); //obtengo el primero

            /* solo carga la información del imputado y del caso
                hearingFormatView.setIdCase(existCase.getId());
                hearingFormatView.setIdFolder(existCase.getHearingFormats().get(0).getIdFolder());
                hearingFormatView.setIdJudicial(existCase.getHearingFormats().get(0).getIdJudicial());

                hearingFormatView.setImputedName(existCase.getHearingFormats().get(0).getHearingImputed().getName());
                hearingFormatView.setImputedFLastName(existCase.getHearingFormats().get(0).getHearingImputed().getLastNameP());
                hearingFormatView.setImputedSLastName(existCase.getHearingFormats().get(0).getHearingImputed().getLastNameM());
                hearingFormatView.setImputedBirthDate(existCase.getHearingFormats().get(0).getHearingImputed().getBirthDate());
                hearingFormatView.setImputedTel(existCase.getHearingFormats().get(0).getHearingImputed().getImputeTel());
            //todo falta el domicilio
            */

            hearingFormatView.setCanSave(true);
            hearingFormatView.setCanEdit(true);
            hearingFormatView.setDisableAll(false);

        } else {
            //pregunto el tipo de meeting
            if (meetType.equals(HearingFormatConstants.MEETING_CONDITIONAL_REPRIEVE)) { //si el meeting fue creado como suspension condicional

                //obtengo los datos de meeting
                hearingFormatView.setIdCase(existCase.getId());
                hearingFormatView.setIdFolder(existCase.getIdFolder());
                hearingFormatView.setIdJudicial(existCase.getIdMP());
                hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getDateBirth());

                //todo falta el domicilio

                hearingFormatView.setCanSave(true);
                hearingFormatView.setCanEdit(true);
                hearingFormatView.setDisableAll(false);

            } else if (meetType.equals(HearingFormatConstants.MEETING_PROCEDURAL_RISK)) {//si el meeting fue creado normalmente

                //busco si tiene verificacion y obtengo los datos
            }
        }

        return hearingFormatView;
    }

    @Override
    public HearingFormatView fillExistHearingFormatForView(Long idFormat) {
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
        //todo falta domicilio

        hearingFormatView.setControlDetention(existHF.getHearingFormatSpecs().getControlDetention());
        hearingFormatView.setExtension(existHF.getHearingFormatSpecs().getExtension());
        hearingFormatView.setImpForm(existHF.getHearingFormatSpecs().getImputationFormulation());
        hearingFormatView.setImputationDate(existHF.getHearingFormatSpecs().getImputationDate());
        hearingFormatView.setVincProcess(existHF.getHearingFormatSpecs().getLinkageProcess());
        hearingFormatView.setLinkageRoom(existHF.getHearingFormatSpecs().getLinkageRoom());
        hearingFormatView.setLinkageDate(existHF.getHearingFormatSpecs().getLinkageDate());
        hearingFormatView.setLinkageTime(existHF.getHearingFormatSpecs().getLinkageTime());
        hearingFormatView.setControlDetention(existHF.getHearingFormatSpecs().getControlDetention());
        hearingFormatView.setHearingType(existHF.getHearingFormatSpecs().getHearingType());
        hearingFormatView.setAdditionalData(existHF.getAdditionalData());
        hearingFormatView.setCrimes(existHF.getCrimes());
        hearingFormatView.setTerms(existHF.getTerms());

        hearingFormatView.setLstArrangement(conv.toJson(this.assignedArrangementForView(existHF.getAssignedArrangements())));

        //todo falta la lista de contactos

        return hearingFormatView;
    }


    public List<ArrangementView> getArrangmentLst(Integer idTipo) {

        List<ArrangementView> lstArrmntView = new ArrayList<>();

        List<Arrangement> lstArrmnt = arrangementRepository.findByType(idTipo);
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

    @Override
    @Transactional
    public ResponseMessage save(HearingFormat hearingFormat, HttpServletRequest request) {
        ResponseMessage response = new ResponseMessage();
        StringBuilder sb = new StringBuilder();

        try {
            hearingFormat = hearingFormatRepository.save(hearingFormat);


            sb.append(request.getContextPath());
            sb.append("/supervisor/hearingFormat/indexFormats.html?id=");
            sb.append(hearingFormat.getCaseDetention().getId());

            response.setHasError(false);
            response.setMessage("Se ha registrado el formato de audiencia con éxito!!");
            response.setUrlToGo(sb.toString());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al guardar el formato de audiencia (serviceImpl)!!!");
            e.printStackTrace();
            response.setHasError(true);
        } finally {
            return response;
        }
    }
}
