package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.catalog.Location;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Domicile;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.CatalogService;
import com.umeca.service.reviewer.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public HearingFormat fillHearingFormat(HearingFormatView viewFormat) {


        HearingFormat hearingFormat = new HearingFormat();

        //hearingFormat.setNoDate(viewFormat.getNumberDate());
        hearingFormat.setRegisterTimestamp(new Timestamp(new Date().getTime()));
        hearingFormat.setRoom(viewFormat.getRoom());
        hearingFormat.setInitTime(viewFormat.getInitTime());
        hearingFormat.setEndTime(viewFormat.getEndTime());
        hearingFormat.setJudgeName(viewFormat.getJudgeName());
        hearingFormat.setMpName(viewFormat.getMpName());
        hearingFormat.setDefenderName(viewFormat.getDefenderName());
        hearingFormat.setCrimes(viewFormat.getCrimes());
        hearingFormat.setAdditionalData(viewFormat.getAdditionalData());
        hearingFormat.setTerms(viewFormat.getTerms());
        hearingFormat.setOriginType(viewFormat.getArrangementType());
        hearingFormat.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));
        HearingFormatSpecs hearingFormatSpecs = new HearingFormatSpecs();
        hearingFormatSpecs.setControlDetention(viewFormat.getControlDetention());
        hearingFormatSpecs.setImputationDate(viewFormat.getImputationDate());
        hearingFormatSpecs.setExtension(viewFormat.getExtension());
        hearingFormatSpecs.setLinkageDate(viewFormat.getLinkageDate());
        hearingFormatSpecs.setLinkageTime(viewFormat.getLinkageTime());
        hearingFormatSpecs.setLinkageRoom(viewFormat.getLinkageRoom());
        hearingFormatSpecs.setHearingType(viewFormat.getHearingType());
        hearingFormatSpecs.setHearingFormat(hearingFormat);

        List<ArrangementView> lstAssignedArrnmt = new ArrayList<>();
        Type type = new TypeToken<List<ArrangementView>>() {
        }.getType();

        lstAssignedArrnmt = conv.fromJson(viewFormat.lstArrangement, type);

        List<AssignedArrangement> lstAssiArrmnt = new ArrayList<>();

        for (ArrangementView arrV : lstAssignedArrnmt) {

            if (arrV.getSelVal() == true) {
                AssignedArrangement assArrmnt = new AssignedArrangement();
                assArrmnt.setArrangement(arrangementRepository.findOne(arrV.getId()));
                assArrmnt.setDescription(arrV.getDescription());
                assArrmnt.setHearingFormat(hearingFormat);
                lstAssiArrmnt.add(assArrmnt);
            }
        }

        hearingFormat.setAssignedArrangements(lstAssiArrmnt);
        hearingFormat.setHearingFormatSpecs(hearingFormatSpecs);

        return hearingFormat;
    }*/

    @Override
    public HearingFormatView fillNewHearingFormatForView(Long idCase, Long idFormat) {

        HearingFormatView hearingFormatView = new HearingFormatView();


        //TODO si no hay hearing format debo buscar si el caso existe y si tiene una verificación para obtener los datos de ahi

        //TODO debo buscar si ya existe un hearing format para obtener los datos de ahi y crear el nuevo

        //TODO si no existe hearing format debo traer la información de meeting, ya que se creo el caso por suspensión condicional

        Case existCase = caseRepository.findOne(idCase);

        Integer meetType = existCase.getMeeting().getMeetingType();


        if(meetType.equals(HearingFormatConstants.MEETING_CONDITIONAL_REPRIEVE)) { //si el meeting fue creado como suspension condicional

            if (existCase.getHearingFormats() != null && existCase.getHearingFormats().size() > 0) { //busco si ya existe alguno y tomo la información, si no lleno



            }
        }


/*
        //caso1 no existe anda, viene de suspension condicional
        if (existCase == null) {
            hearingFormatView.setIdFolderCode(idFolder);
            hearingFormatView.setExistImputed(false);
            hearingFormatView.setCanSave(true);
            hearingFormatView.setHasHF(false);
            hearingFormatView.setArrangementType(Constants.CONDITIONAL_REPRIEVE_HEARING);

        } else//existe el caso pero no el formato, viene de meeting
            if (existCase != null && existCase.getHearingFormat() == null) {

                hearingFormatView.setIdFolderCode(existCase.getIdFolder());
                hearingFormatView.setIdJudicialFolderCode(existCase.getIdMP());

                hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getDateBirth());
                hearingFormatView.setImputedTel(existCase.getMeeting().getImputed().getCelPhone());

                if (existCase.getMeeting().getDomiciles() != null && existCase.getMeeting().getDomiciles().size() > 0) {
                    hearingFormatView.setZipCode(existCase.getMeeting().getDomiciles().get(0).getLocation().getZipCode());

                    sta.setDescription(existCase.getMeeting().getDomiciles().get(0).getLocation().getMunicipality().getState().getDescription());

                    mun.setName(existCase.getMeeting().getDomiciles().get(0).getLocation().getMunicipality().getName());
                    mun.setState(sta);

                    loc.setId(existCase.getMeeting().getDomiciles().get(0).getLocation().getId());
                    loc.setName(existCase.getMeeting().getDomiciles().get(0).getLocation().getName());
                    loc.setMunicipality(mun);

                    hearingFormatView.setLocation(conv.toJson(loc));
                    hearingFormatView.setStreet(existCase.getMeeting().getDomiciles().get(0).getStreet());
                    hearingFormatView.setOutNum(existCase.getMeeting().getDomiciles().get(0).getNoOut());
                    hearingFormatView.setInnNum(existCase.getMeeting().getDomiciles().get(0).getNoIn());
                }

                hearingFormatView.setExistImputed(true);
                hearingFormatView.setCanSave(true);
                hearingFormatView.setHasHF(false);
                hearingFormatView.setArrangementType(Constants.MEETING_HEARING);

            } else //existe el caso y el formato, ya se ha guardado cualqueira de los 2 casos
                if (existCase != null && existCase.getHearingFormat() != null) {

                    hearingFormatView.setIdFolderCode(existCase.getIdFolder());
                    hearingFormatView.setIdJudicialFolderCode(existCase.getIdMP());

                    hearingFormatView.setNumberDate(existCase.getHearingFormat().getNoDate());
                    hearingFormatView.setRoom(existCase.getHearingFormat().getRoom());
                    hearingFormatView.setInitTime(existCase.getHearingFormat().getInitTime());
                    hearingFormatView.setEndTime(existCase.getHearingFormat().getEndTime());
                    hearingFormatView.setJudgeName(existCase.getHearingFormat().getJudgeName());
                    hearingFormatView.setMpName(existCase.getHearingFormat().getMpName());
                    hearingFormatView.setDefenderName(existCase.getHearingFormat().getDefenderName());
                    hearingFormatView.setCrimes(existCase.getHearingFormat().getCrimes());
                    hearingFormatView.setAdditionalData(existCase.getHearingFormat().getAdditionalData());
                    hearingFormatView.setTerms(existCase.getHearingFormat().getTerms());

                    hearingFormatView.setControlDetention(existCase.getHearingFormat().getHearingFormatSpecs().getControlDetention());
                    hearingFormatView.setHearingType(existCase.getHearingFormat().getHearingFormatSpecs().getHearingType());
                    hearingFormatView.setImputationDate(existCase.getHearingFormat().getHearingFormatSpecs().getImputationDate());
                    hearingFormatView.setLinkageRoom(existCase.getHearingFormat().getHearingFormatSpecs().getLinkageRoom());
                    hearingFormatView.setLinkageDate(existCase.getHearingFormat().getHearingFormatSpecs().getLinkageDate());
                    hearingFormatView.setLinkageTime(existCase.getHearingFormat().getHearingFormatSpecs().getLinkageTime());
                    hearingFormatView.setExtension(existCase.getHearingFormat().getHearingFormatSpecs().getExtension());

                    if (existCase.getMeeting() != null) {
                        hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                        hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                        hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                        hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getDateBirth());
                        hearingFormatView.setImputedTel(existCase.getMeeting().getImputed().getCelPhone());

                        if (existCase.getMeeting().getDomiciles() != null & existCase.getMeeting().getDomiciles().size() > 0) {

                            hearingFormatView.setZipCode(existCase.getMeeting().getDomiciles().get(0).getLocation().getZipCode());

                            sta.setDescription(existCase.getMeeting().getDomiciles().get(0).getLocation().getMunicipality().getState().getDescription());

                            mun.setName(existCase.getMeeting().getDomiciles().get(0).getLocation().getMunicipality().getName());
                            mun.setState(sta);

                            loc.setId(existCase.getMeeting().getDomiciles().get(0).getLocation().getId());
                            loc.setName(existCase.getMeeting().getDomiciles().get(0).getLocation().getName());
                            loc.setMunicipality(mun);

                            hearingFormatView.setLocation(conv.toJson(loc));
                            hearingFormatView.setStreet(existCase.getMeeting().getDomiciles().get(0).getStreet());
                            hearingFormatView.setOutNum(existCase.getMeeting().getDomiciles().get(0).getNoOut());
                            hearingFormatView.setInnNum(existCase.getMeeting().getDomiciles().get(0).getNoIn());
                        }
                    }

                    hearingFormatView.setExistImputed(true);
                    hearingFormatView.setCanSave(false);
                    hearingFormatView.setHasHF(true);
                    hearingFormatView.setArrangementType(existCase.getHearingFormat().getOriginType());
                }*/

            return hearingFormatView;
    }

    /*
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


    public List<ArrangementView> getArrangmentLst(String folderId) {

        //Case caseDet = caseService.findByIdFolder(folderId);

        List<ArrangementView> lstArrmntView = new ArrayList<>();
/*
        Integer arrmntType = Constants.CONDITIONAL_REPRIEVE_HEARING;

        if (caseDet != null) {

            if (caseDet.getHearingFormat() != null) {

                for (AssignedArrangement assArr : caseDet.getHearingFormat().getAssignedArrangements()) {
                    ArrangementView arrV = new ArrangementView();
                    arrV.setId(assArr.getId());
                    arrV.setName(assArr.getArrangement().getDescription());
                    arrV.setDescription(assArr.getDescription());
                    arrV.setSelVal(true);
                    lstArrmntView.add(arrV);
                }

                return lstArrmntView;
            } else
                arrmntType = Constants.MEETING_HEARING;

        }

        List<Arrangement> lstArrmnt = arrangementRepository.findByType(arrmntType);
        Collections.sort(lstArrmnt, Arrangement.arrangementComparator);

        for (Arrangement arrmnt : lstArrmnt) {
            ArrangementView arrV = new ArrangementView();
            arrV.setId(arrmnt.getId());
            arrV.setName(arrmnt.getDescription());
            arrV.setSelVal(false);
            lstArrmntView.add(arrV);
        }

        return lstArrmntView;
    }*/
}
