package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.umeca.model.catalog.*;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private ImputedRepository imputedRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private StatusMeetingRepository statusMeetingRepository;

    @Override
    public ResponseMessage createMeeting(Imputed imputed) {
        ResponseMessage result = new ResponseMessage();
        try{
            Case caseDetention = new Case();
            if(imputedRepository.countCaseSameRFC(imputed.getRfc())>0)
                caseDetention.setRecidivist(true);
            else
                caseDetention.setRecidivist(false);
            caseDetention = caseRepository.save(caseDetention);
            Meeting meeting = new Meeting();
            meeting.setCaseDetention(caseDetention);
            StatusMeeting statusMeeting= statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE);
            meeting.setStatus(statusMeeting);
            meeting=meetingRepository.save(meeting);
            imputed.setMeeting(meeting);
            imputedRepository.save(imputed);
            result.setHasError(false);
            result.setMessage("redireccion");
            result.setUrlToGo("/reviewer/meeting/meeting");
        }catch (Exception e){
            result.setHasError(true);
            result.setMessage("Ocurrio un error al crear el expediente. Intente más tarde."+ e.getMessage());
        }
        return result;
    }

    @Autowired
    PhysicalConditionRepository physicalConditionRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    ElectionRepository electionRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Override
    public ModelAndView showMeeting(Long id) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/meeting");
        Gson gson = new Gson();
        ////////////////////Personal data
        String lstPhysicalCondition = gson.toJson(physicalConditionRepository.findAll());
        String lstActivity = gson.toJson(activityRepository.findAll());
        Case caseDetention = caseRepository.findOne(id);
        model.addObject("m",caseDetention.getMeeting());
        if(caseDetention.getMeeting().getSocialEnvironment()!=null){
            if(caseDetention.getMeeting().getSocialEnvironment().getActivities()!=null){
                int tam = caseDetention.getMeeting().getSocialEnvironment().getActivities().size();
                Long[] activity = new Long[tam];
                for (int a=0; a<tam;a++) {
                     activity[a] = caseDetention.getMeeting().getSocialEnvironment().getActivities().get(a).getId();
                }
                model.addObject("activity",gson.toJson(activity));
            }
            if(caseDetention.getMeeting().getSocialEnvironment().getPhysicalConditions()!=null){
                int tam = caseDetention.getMeeting().getSocialEnvironment().getPhysicalConditions().size();
                Long[] physicalCondition=new Long[tam];
                for(int p = 0; p<tam ; p++){
                    physicalCondition[p] = caseDetention.getMeeting().getSocialEnvironment().getPhysicalConditions().get(p).getId();
                }
                model.addObject("physicalCondition",gson.toJson(physicalCondition));
            }
        }
        model.addObject("lstPhysicalCondition", lstPhysicalCondition);
        model.addObject("lstActivity", lstActivity);
        /////////////////////////Social Network

        return model;
    }


    @Override
    public ModelAndView showLegalProcess(Long id) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/legal/index");
        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        Case caseDetention = caseRepository.findOne(id);
        model.addObject("m",caseDetention.getMeeting());
        model.addObject("lstRoles", lstRoles);
        return model;
    }

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Override
    public ResponseMessage upsertPersonalData(Long idCase, Imputed imputed, SocialEnvironment socialEnvironment, Integer[] physicalCondition, Integer[] activity) {
        ResponseMessage result = new ResponseMessage();
        try {
            Case caseDetention = caseRepository.findOne(idCase);
            imputed.setMeeting(caseDetention.getMeeting());
            socialEnvironment.setMeeting(caseDetention.getMeeting());
            imputed.setId(caseDetention.getMeeting().getImputed().getId());
            if(caseDetention.getMeeting().getSocialEnvironment()!=null && caseDetention.getMeeting().getSocialEnvironment().getId()!=null){
                socialEnvironment.setId(caseDetention.getMeeting().getSocialEnvironment().getId());
            }
            imputed.setName(caseDetention.getMeeting().getImputed().getName());
            imputed.setLastNameP(caseDetention.getMeeting().getImputed().getLastNameP());
            imputed.setLastNameM(caseDetention.getMeeting().getImputed().getLastNameM());
            imputed.setDateBirth(caseDetention.getMeeting().getImputed().getDateBirth());
            imputed.setRfc(caseDetention.getMeeting().getImputed().getRfc());
            caseDetention.getMeeting().setImputed(imputed);

            if(imputed.getMaritalStatus()!=null && imputed.getMaritalStatus().getId()!=null){
            MaritalStatus maritalStatus = maritalStatusRepository.findOne(imputed.getMaritalStatus().getId());
                imputed.setMaritalStatus(maritalStatus);
            }
            if(physicalCondition!=null){
                socialEnvironment.setPhysicalConditions(new ArrayList<PhysicalCondition>());
            for (int i =0; i<physicalCondition.length; i++){
               socialEnvironment.getPhysicalConditions().add(physicalConditionRepository.findOne(Long.valueOf(physicalCondition[i])));
            }                           }
            if(activity!=null){
                socialEnvironment.setActivities(new ArrayList<Activity>());
            for (int a = 0; a<activity.length;a++){
                socialEnvironment.getActivities().add(activityRepository.findOne(Long.valueOf(activity[a])));
            }
            }
            caseDetention.getMeeting().setSocialEnvironment(socialEnvironment);
            caseRepository.saveAndFlush(caseDetention);
            result.setHasError(false);
            result.setMessage("Se ha guardado la información exitosamente");
        }catch (Exception e){
           result.setHasError(true);
            result.setMessage("Ha ocurrdio un error al guardar los datos personales. Favor de intentar más tarde"+e.getMessage());
        }
        return result;
    }

    @Autowired
    PersonSocialNetworkRepository personSocialNetworkRepository;
    @Override
    public ModelAndView upsertSocialNetwork(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/socialNetwork/upsert");
        Gson gson = new Gson();
        model.addObject("lstRelationship", gson.toJson(relationshipRepository.findAll()));
        model.addObject("lstElection", gson.toJson(electionRepository.findAll()));
        model.addObject("lstDocumentType", gson.toJson(documentTypeRepository.findAll()));
        if(id!=null){
            PersonSocialNetwork p=personSocialNetworkRepository.findOne(id);
            PersonSocialNetwork pView = new PersonSocialNetwork();
            pView.setId(p.getId());
            pView.setName(p.getName());
            pView.setAge(p.getAge());
            pView.setPhone(p.getPhone());
            model.addObject("p", gson.toJson(pView));
            model.addObject("relId",gson.toJson(p.getRelationship().getId()));
            model.addObject("docId",gson.toJson(p.getDocumentType().getId()));
            model.addObject("depId",gson.toJson(p.getDependent().getId()));
            model.addObject("livId",gson.toJson(p.getLivingWith().getId()));
        }
        model.addObject("idCase", idCase);
        return model;
    }

    @Override
    public ResponseMessage doUpsertSocialNetwork(PersonSocialNetwork person, Long idCase) {
        ResponseMessage result = new ResponseMessage();
        try{
            person.setRelationship(relationshipRepository.findOne(person.getRelationship().getId()));
            person.setDocumentType(documentTypeRepository.findOne(person.getDocumentType().getId()));
            person.setDependent(electionRepository.findOne(person.getDependent().getId()));
            person.setLivingWith(electionRepository.findOne(person.getLivingWith().getId()));
            Case caseDetention = caseRepository.findOne(idCase);
            person.setSocialNetwork(caseDetention.getMeeting().getSocialNetwork());
            if(person.getId()==0){
                   person.setId(null);
            }
            personSocialNetworkRepository.save(person);

        }catch (Exception e){
           result.setHasError(true);
          result.setMessage("Ha ocurrido un error al guardar la persona de red social.");
        }
        return  result;
    }

    @Override
    public ResponseMessage deleteSocialNetwork(Long id) {
        ResponseMessage result = new ResponseMessage();
        try{
        personSocialNetworkRepository.delete(personSocialNetworkRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se elimino la persona de red social exitosamente");
        }catch (Exception e){
            result.setHasError(true);
            result.setMessage("No se ha podido eliminar la persona de red social. Intente más tarde");
        }
        return  result;
    }

    @Autowired
    ReferenceRepository referenceRepository;

    @Override
    public ModelAndView upsertReference(Long id, Long idCase) {
        ModelAndView result = new ModelAndView("/reviewer/meeting/reference/upsert");
        Gson gson = new Gson();
        result.addObject("lstRelationship", gson.toJson(relationshipRepository.findAll()));
        result.addObject("lstElection", gson.toJson(electionRepository.findAll()));
        result.addObject("lstDocumentType", gson.toJson(documentTypeRepository.findAll()));
        if(id!=null){
            Reference reference = referenceRepository.findOne(id);
            result.addObject("r",reference);
            result.addObject("relId",gson.toJson(reference.getRelationship().getId()));
            result.addObject("docId",gson.toJson(reference.getDocumentType().getId()));
        }

        result.addObject("idCase", idCase);
        return result;
    }

    @Override
    public ResponseMessage doUpsertReference(Reference reference, Long idCase) {
        ResponseMessage result = new ResponseMessage();
        try{
            Case caseDetention = caseRepository.findOne(idCase);
            reference.setMeeting(caseDetention.getMeeting());
            reference.setRelationship(relationshipRepository.findOne(reference.getRelationship().getId()));
            reference.setDocumentType(documentTypeRepository.findOne(reference.getDocumentType().getId()));
            if(reference.getId()!=null && reference.getId()==0){
                reference.setId(null);
            }
            referenceRepository.saveAndFlush(reference);
            result.setHasError(false);
            result.setMessage("Se ha guardado correctamente la referencia personal");
        }catch (Exception e){
            result.setHasError(true);
            result.setMessage("Ocurrio un error al guardar la refernecia. Intente más tarde.");
        }
        return result;
    }

    @Override
    public ResponseMessage deleteReference(Long id) {
        ResponseMessage result = new ResponseMessage();
        try{
            referenceRepository.delete(referenceRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se ha eliminado la referencia exitosamente.");
        }catch (Exception e){
            result.setHasError(false);
            result.setMessage("Ocurrio un error al eliminar la referencia. Intente más tarde.");
        }
        return result;
    }

    @Autowired
    DrugTypeRepository drugTypeRepository;

    @Autowired
    PeriodicityRepository periodicityRepository;

    @Autowired
    DrugRepository drugRepository;
    @Override
    public ModelAndView upsertDrug(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/drug/upsert");
        Gson gson = new Gson();
        model.addObject("lstDrugType", gson.toJson(drugTypeRepository.findAll()));
        model.addObject("lstPeriodicity",gson.toJson(periodicityRepository.findAll()));
        if(id!=null && id!=0){
            Drug d = drugRepository.findOne(id);
           model.addObject("d",d);
           model.addObject("typeId",d.getDrugType().getId());
            model.addObject("perId",d.getPeriodicity().getId());
        }
        model.addObject("idCase", idCase);
        return model;
    }

    @Override
    public ResponseMessage doUpsertDrug(Drug drug, Long idCase) {
        ResponseMessage result=new  ResponseMessage();
        try{
            drug.setDrugType(drugTypeRepository.findOne(drug.getDrugType().getId()));
            drug.setPeriodicity(periodicityRepository.findOne(drug.getPeriodicity().getId()));
            drug.setMeeting(caseRepository.findOne(idCase).getMeeting());
            if(drug.getId()!=null && drug.getId()==0){
                drug.setId(null);
            }
            drugRepository.save(drug);
            result.setHasError(false);
            result.setMessage("Se ha guardado la sustancia con éxito");
        }catch (Exception e){
           result.setHasError(true);
           result.setMessage("Ocorrio un error al guardar la sustancia.Inténte más tarde.");
        }
        return  result;
    }

    @Override
    public ResponseMessage deleteDrug(Long id) {
        ResponseMessage result = new ResponseMessage();
        try{
            drugRepository.delete(drugRepository.findOne(id));
            result.setHasError(false);
            result.setMessage("Se elimino la sustancia con éxito");
        }catch (Exception e){
            result.setHasError(true);
            result.setMessage("Ocurrio un error al eliminar la sustancia. Inténte más tarde");
        }
        return result;
    }
}
