package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.RelationshipRepository;
import com.umeca.repository.supervisor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service("framingMeetingService")
public class FramingMeetingServiceImpl implements FramingMeetingService {

    @Qualifier("qFramingMeetingRepository")
    @Autowired
    private FramingMeetingRepository framingMeetingRepository;

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

    @Transactional
    public ResponseMessage saveSelectedSource(Long idCase, String lstSourcesStr) {

        try {
            FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();

            List<FramingSelectedSourceRel> lstExistSources = existFraming.getSelectedSourcesRel();

            if (lstExistSources != null && lstExistSources.size() > 0) {
                for (FramingSelectedSourceRel sel : lstExistSources) {
                    framingSelectedSourceRelRepository.delete(sel);
                }
            }

            existFraming.setSelectedSourcesRel((this.generateSourceRel(idCase, lstSourcesStr)));
            framingMeetingRepository.save(existFraming);
            framingMeetingRepository.flush();

            return new ResponseMessage(false, "Se ha guardado la información con exito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la información. Intente más tarde.");
        }
    }

    public ResponseMessage saveReference(Case existCase, FramingReference newReference) {

        try {
            newReference.setRelationship(relationshipRepository.findOne(newReference.getRelationshipId()));
            newReference.setFramingMeeting(existCase.getFramingMeeting());
            framingReferenceRepository.save(newReference);
            return new ResponseMessage(false, "Se ha guardado la información con exito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la información. Intente más tarde.");
        }

    }

    public ProcessAccompanimentForView fillProcessAccompanimentForView(Long idCase) {
        ProcessAccompanimentForView view = new ProcessAccompanimentForView();


        return view;
    }

    public ProcessAccompaniment fillProcessAccompaniment(ProcessAccompanimentForView view) {
        ProcessAccompaniment processAccompaniment = new ProcessAccompaniment();

        processAccompaniment.setName(view.getName());
        processAccompaniment.setLastNameP(view.getLastNameP());
        processAccompaniment.setLastNameM(view.getLastNameM());
        processAccompaniment.setGender(view.getGender());
        processAccompaniment.setAge(view.getAge());
        processAccompaniment.setPhone(view.getPhone());
        processAccompaniment.setCelphone(view.getCelphone());

        Occupation occup = new Occupation();

        occup.setName(view.getOccName());
        occup.setPlace(view.getOccPlace());
        occup.setPhone(view.getOccPhone());

        processAccompaniment.setOccupation(occup);

        Address address = new Address();
        address.setStreet(view.getStreet());
        address.setInnNum(view.getInnNum());
        address.setOutNum(view.getOutNum());
        address.setLocation(locationRepository.findOne(view.getLocation().getId()));
        address.setAddressString(address.toString());

        processAccompaniment.setAddress(address);

        return processAccompaniment;
    }

    @Transactional
    public ResponseMessage saveProcessAccompaniment(ProcessAccompaniment processAccompaniment){
        try{
            processAccompanimentRepository.save(processAccompaniment);
            return new ResponseMessage(false, "Se ha guardado la información con exito.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la información. Intente más tarde.");
        }
    }


}
