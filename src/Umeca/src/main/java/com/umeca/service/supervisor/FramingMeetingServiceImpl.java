package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.supervisor.FramingMeetingRepository;
import com.umeca.repository.supervisor.FramingReferenceRepository;
import com.umeca.repository.supervisor.FramingSelectedSourceRelRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
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

@Service("framingMeetingService")
public class FramingMeetingServiceImpl implements FramingMeetingService {

    @Qualifier("qFramingMeetingRepository")
    @Autowired
    FramingMeetingRepository framingMeetingRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    FramingReferenceRepository framingReferenceRepository;

    @Autowired
    FramingSelectedSourceRelRepository framingSelectedSourceRelRepository;

    @Transactional
    @Override
    public ResponseMessage save(FramingMeeting framingMeeting) {

        ResponseMessage response = new ResponseMessage();
        try {
            framingMeetingRepository.save(framingMeeting);
            response.setHasError(false);
        } catch (Exception e) {
            System.out.println("Error al guardar framingMeeting!!!\n");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage(e.getMessage());
        } finally {
            return response;
        }
    }

    @Override
    public FramingMeeting fillFramingMeeting(FramingMeetingView viewFraming) {

        FramingMeeting framingMeeting = new FramingMeeting();


        return framingMeeting;
    }

    private FramingPersonalDataView fillPersonalDataForView(Case existCase) {

        FramingPersonalDataView pD = new FramingPersonalDataView();

        FramingPersonalDataView pData = new FramingPersonalDataView();

        StringBuilder sb = new StringBuilder();

        sb.append(existCase.getMeeting().getImputed().getName());
        sb.append(" ");
        sb.append(existCase.getMeeting().getImputed().getLastNameP());
        sb.append(" ");
        sb.append(existCase.getMeeting().getImputed().getLastNameM());

        pData.setFullName(sb.toString());
        pData.setGender(existCase.getMeeting().getImputed().getGender());
        pData.setMaritalStatus(existCase.getMeeting().getImputed().getMaritalStatus().getId());
        pData.setIdContry(existCase.getMeeting().getImputed().getBirthCountry().getId());
        pData.setState(existCase.getMeeting().getImputed().getBirthState());
        pData.setBrthDate(existCase.getMeeting().getImputed().getBirthDate());

        List<Long> physCondSel = new ArrayList<>();
           /*
        for (PhysicalCondition pC : existCase.getMeeting().getSocialEnvironment().getPhysicalConditions()) {
            physCondSel.add(pC.getId());
        }
        */

        pData.setPhysicalConditionsSel(physCondSel);


        return pD;
    }

    @Override
    public FramingMeetingView fillForView(Case existCase) {

        FramingMeetingView framingMeetingView = new FramingMeetingView();

        framingMeetingView.setIdFolder(existCase.getIdFolder());
        framingMeetingView.setIdCase(existCase.getId());
        //framingMeetingView.setPersonalData(this.fillPersonalDataForView(existCase));

        return framingMeetingView;
    }

    @Override
    public List<FramingReferenceForView> loadExistSources(Long idCase) {

        List<FramingReferenceForView> lstView= new ArrayList<>();

        List<FramingReference> existSources = caseRepository.findOne(idCase).getFramingMeeting().getReferences();

        for(FramingReference fr : existSources){

            FramingReferenceForView objView = new FramingReferenceForView();
            objView.setId(fr.getId());
            objView.setDescription(fr.getName()+", "+fr.getRelationship());
            objView.setValSel(false);
            lstView.add(objView);
        }

        return lstView;
    }

    public List<FramingSelectedSourceRel> generateSourceRel(Long idCase, String lstJson){

        Type listType = new TypeToken<List<Long>>() {}.getType();

        List<Long> ids = new Gson().fromJson(lstJson, listType);

        FramingMeeting existFraming= caseRepository.findOne(idCase).getFramingMeeting();


        List<FramingSelectedSourceRel> sourceRel= new ArrayList<>();

        for(Long currId : ids){
            FramingSelectedSourceRel rel = new FramingSelectedSourceRel();
            rel.setFramingMeeting(existFraming);
            rel.setFramingReference(framingReferenceRepository.findOne(currId));
            sourceRel.add(rel);
        }

        return sourceRel;
    }

    @Transactional
    public void verifySelectedSources(Long idCase) {

        if(caseRepository.findOne(idCase).getFramingMeeting().getSelectedSourcesRel()!=null&&caseRepository.findOne(idCase).getFramingMeeting().getSelectedSourcesRel().size()>0){
            for(FramingSelectedSourceRel sel:caseRepository.findOne(idCase).getFramingMeeting().getSelectedSourcesRel()){
                framingSelectedSourceRelRepository.delete(sel);
            }
        }
    }
}
