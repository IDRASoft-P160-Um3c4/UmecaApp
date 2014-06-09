package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.Constants;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
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

    @Autowired
    CaseService caseService;

    @Autowired
    ArrangementRepository arrangementRepository;

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

        Gson conv = new Gson();

        HearingFormat hearingFormat = new HearingFormat();

        hearingFormat.setNoDate(viewFormat.getNumberDate());
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

        List<AssignedArrangement> lstAssiArrmnt= new ArrayList<>();

        for(ArrangementView arrV : lstAssignedArrnmt){

            if(arrV.getSelVal()==true) {
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
    }

    @Override
    public HearingFormatView fillForView(Case existCase, String idFolder) {


        HearingFormatView hearingFormatView = new HearingFormatView();

        hearingFormatView.setIdFolderCode(idFolder);
        //caso1 no existe anda, viene de suspension condicional
        if (existCase == null) {

            hearingFormatView.setCanSave(true);
            hearingFormatView.setHasHF(false);
            hearingFormatView.setArrangementType(Constants.CONDITIONAL_REPRIEVE_HEARING);

        } else//existe el caso pero no el formato, viene de meeting
            if (existCase != null && existCase.getHearingFormat() == null) {

                hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getDateBirth());
                hearingFormatView.setImputedTel(existCase.getMeeting().getImputed().getCelPhone());
                hearingFormatView.setCanSave(true);
                hearingFormatView.setHasHF(false);
                hearingFormatView.setArrangementType(Constants.MEETING_HEARING);

            } else //existe el caso y el formato, ya se ha guardado cualqueira de los 2 casos
                if (existCase != null && existCase.getHearingFormat() != null) {

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

                    hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
                    hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
                    hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());
                    hearingFormatView.setImputedBirthDate(existCase.getMeeting().getImputed().getDateBirth());
                    hearingFormatView.setImputedTel(existCase.getMeeting().getImputed().getCelPhone());

                    hearingFormatView.setCanSave(false);
                    hearingFormatView.setHasHF(true);
                    hearingFormatView.setArrangementType(existCase.getHearingFormat().getOriginType());
                }

        return hearingFormatView;
    }

    public List<ArrangementView> getArrangmentLst(String folderId) {

        Case caseDet = caseService.findByIdFolder(folderId);

        List<ArrangementView> lstArrmntView = new ArrayList<>();

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
    }
}
