package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.RegisterType;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("verificationService")
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    StatusCaseRepository statusCaseRepository;
    @Autowired
    StatusVerificationRepository statusVerificationRepository;
    @Autowired
    SharedUserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SourceVerificationRepository sourceVerificationRepository;

    @Override
    public void createVerification(Case c) {
        c.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_SOURCE_VALIDATION));
        Verification verification = new Verification();
        verification.setCaseDetention(c);
        verification.setStatus(statusVerificationRepository.findByCode(Constants.VERIFICATION_STATUS_NEW_SOURCE));
        verification.setReviewer(userRepository.findOne(userService.GetLoggedUserId()));
        //TODO agregar la entrevista verificada como la incial.
        c.setVerification(verification);
        c.getVerification().setSourceVerifications(convertAllInitSourcesVerif(c));
    }

    @Override
    public List<SourceVerification> convertAllInitSourcesVerif(Case c) {
        List<SourceVerification> svlist = new ArrayList<>();
        List<Reference> rlist = (c.getMeeting().getReferences() == null) ? new ArrayList<Reference>() : c.getMeeting().getReferences();
        for (Reference reference : rlist) {
            SourceVerification sv = new SourceVerification();
            sv.setFullName(reference.getFullName());
            sv.setPhone(reference.getPhone());
            sv.setAge(reference.getAge());
            sv.setAddress(reference.getAddress());
            sv.setRelationship(reference.getRelationship());
            sv.setVisible(Boolean.TRUE);
            sv.setVerification(c.getVerification());
            sv.setAuthorized(Boolean.FALSE);
            svlist.add(sv);
        }
        List<PersonSocialNetwork> psnList = (c.getMeeting().getSocialNetwork() != null && c.getMeeting().getSocialNetwork().getPeopleSocialNetwork() != null)
                ? c.getMeeting().getSocialNetwork().getPeopleSocialNetwork() : new ArrayList<PersonSocialNetwork>();
        String addressImputed = "No se proporcionó dirección del imputado";
        String otherAddresSPN = "No proporcionado";
        if (c.getMeeting().getImputedHomes() != null) {
            for (ImputedHome d : c.getMeeting().getImputedHomes()) {
                if (d.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_CURRENT)) {
                    addressImputed = d.getAddress().getAddressString();
                    break;
                }
            }
        }
        for (PersonSocialNetwork psn : psnList) {
            SourceVerification sv = new SourceVerification();
            sv.setFullName(psn.getName());
            sv.setPhone(psn.getPhone());
            sv.setAge(psn.getAge());
            if (psn.getLivingWith().getId().equals(Constants.ELECTION_YES)) {
                sv.setAddress(addressImputed);
            } else {
                sv.setAddress(psn.getAddress());
            }
            sv.setRelationship(psn.getRelationship());
            sv.setVisible(Boolean.TRUE);
            sv.setAuthorized(Boolean.FALSE);
            sv.setVerification(c.getVerification());
            svlist.add(sv);
        }
        SourceVerification svImputed = new SourceVerification();
        Imputed i = c.getMeeting().getImputed();
        String fullNameI = i.getName() + " " + i.getLastNameP() + " " + i.getLastNameM();
        svImputed.setFullName(fullNameI);
        String phone = "No proporcionado";
        if (i.getCelPhone() != null && !i.getCelPhone().equals("")) {
            phone = i.getCelPhone();
        }
        svImputed.setPhone(phone);
        svImputed.setAge(userService.calculateAge(i.getBirthDate()));
        svImputed.setAddress(addressImputed);
        svImputed.setVisible(Boolean.FALSE);
        svImputed.setAuthorized(Boolean.TRUE);
        svImputed.setVerification(c.getVerification());
        svlist.add(svImputed);
        return svlist;
    }

    @Autowired
    MeetingService meetingService;
    @Autowired
    DrugTypeRepository drugTypeRepository;
    @Autowired
    PeriodicityRepository periodicityRepository;
    @Autowired
    RegisterTypeRepository registerTypeRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    ElectionRepository electionRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Override
    public ModelAndView showVerificationBySource(Long idCase, Long idSource) {
        Gson gson = new Gson();
        ModelAndView model = new ModelAndView("/reviewer/verification/verificationBySource");
        setImputedData(idCase, model);
        ModelAndView aux = meetingService.showMeeting(idCase);
        model.addAllObjects(aux.getModel());
        Case c = caseRepository.findOne(idCase);
        List<Drug> drugs = c.getMeeting().getDrugs();
        if (drugs != null) {
            model.addObject("lstDrugType", gson.toJson(drugTypeRepository.findNotObsolete()));
            model.addObject("lstPeriodicity", gson.toJson(periodicityRepository.findNotObsolete()));
            List<DrugDto> listDrug = new ArrayList<>();
            for (Drug d : drugs) {
                listDrug.add(new DrugDto().dtoDrug(d));
            }
            model.addObject("listDrug", gson.toJson(listDrug));
        }
        List<CatalogDto> catalogDtoList = new ArrayList<>();
        for(RegisterType rt: registerTypeRepository.findAll()){
            CatalogDto cDto = new CatalogDto();
            cDto.setId(rt.getId());
            cDto.setName(rt.getName());
            catalogDtoList.add(cDto);
        }
        model.addObject("lstRegisterType", gson.toJson(catalogDtoList));
        List<Job> jobs = c.getMeeting().getJobs();
        if(jobs!=null){
            List<JobDto> jobDtoList = new ArrayList<>();
            for(Job j: jobs){
                String schedule = (String) scheduleService.getSchedules(j.getId(), Job.class);
                jobDtoList.add(new JobDto().dtoJob(j, schedule));
            }
            model.addObject("listJob",gson.toJson(jobDtoList));
        }
        addressService.fillCatalogAddress(model);
        List<ImputedHome> homes = c.getMeeting().getImputedHomes();
        if(homes!=null){
            List<ImputedHomeDto> imputedHomeDtoList = new ArrayList<>();
            for(ImputedHome ih:homes){
                String schedule = (String) scheduleService.getSchedules(ih.getId(),ImputedHome.class);
                imputedHomeDtoList.add(new ImputedHomeDto().dtoImputedHome(ih,schedule));
            }
            model.addObject("listImputedHome",gson.toJson(imputedHomeDtoList));
        }
        List<PersonSocialNetwork> peopleSN= (c.getMeeting().getSocialNetwork()!=null) ? c.getMeeting().getSocialNetwork().getPeopleSocialNetwork():null;
        if(peopleSN!=null){
            List<SocialNetworkDto> sndto = new ArrayList<>();
            for(PersonSocialNetwork p: c.getMeeting().getSocialNetwork().getPeopleSocialNetwork()){
                sndto.add(new SocialNetworkDto().dtoSocialNetwork(p));
            }
            model.addObject("listSocialNetwork", gson.toJson(sndto));
            List<CatalogDto> lstRelationship=new ArrayList<>();
            for(Relationship r: relationshipRepository.findNotObsolete()){
                CatalogDto ca = new CatalogDto();
                ca.setName(r.getName());
                ca.setId(r.getId());
                lstRelationship.add(ca);
            }
            model.addObject("lstRelationship", gson.toJson(lstRelationship));
            List<CatalogDto> lstElection = new ArrayList<>();
            for(Election r: electionRepository.findAll()){
                CatalogDto ca = new CatalogDto();
                ca.setName(r.getName());
                ca.setId(r.getId());
                lstElection.add(ca);
            }
            model.addObject("lstElection", gson.toJson(lstElection));
            List<CatalogDto> lstDocumentType = new ArrayList<>();
            for(DocumentType r: documentTypeRepository.findNotObsolete()){
                CatalogDto ca = new CatalogDto();
                ca.setName(r.getName());
                ca.setId(r.getId());
                ca.setSpecification(r.getSpecification());
                lstDocumentType.add(ca);
            }
            model.addObject("lstDocumentType", gson.toJson(lstDocumentType));

        }
        List<Reference> references = c.getMeeting().getReferences();
        if(references!=null){
            List<ReferenceDto> lstReference = new ArrayList<>();
            for(Reference r: references){
                lstReference.add(new ReferenceDto().dtoReference(r));
            }
            model.addObject("listReference", gson.toJson(lstReference));
        }
        SourceVerification sv = sourceVerificationRepository.findOne(idSource);
        model.addObject("idCase", idCase);
        model.addObject("idSource", idSource);
        model.addObject("source", gson.toJson(new SourceVerificationDto().dtoSourceVerification(sv)));
        return model;
    }

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    AddressService addressService;
    @Override
    public void setImputedData(Long id, ModelAndView model) {
        Case c = caseRepository.findOne(id);
        model.addObject("idFolder", c.getIdFolder());
        String fullName = c.getMeeting().getImputed().getName() + " " + c.getMeeting().getImputed().getLastNameP() + " " + c.getMeeting().getImputed().getLastNameM();
        model.addObject("fullNameImputed", fullName);
        model.addObject("idCase", id);
    }
}
