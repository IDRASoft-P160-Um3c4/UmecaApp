package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.StatusVerificationRepository;
import com.umeca.service.account.SharedUserService;
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
    public  List<SourceVerification> convertAllInitSourcesVerif(Case c) {
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
        if(c.getMeeting().getImputedHomes()!=null){
            for(ImputedHome d: c.getMeeting().getImputedHomes()){
                if(d.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_CURRENT)){
                    addressImputed =d.getAddress().getAddressString();
                    break;
                }
            }
        }
        for(PersonSocialNetwork psn: psnList){
            SourceVerification sv = new SourceVerification();
            sv.setFullName(psn.getName());
            sv.setPhone(psn.getPhone());
            sv.setAge(psn.getAge());
            if(psn.getLivingWith().getId().equals(Constants.ELECTION_YES)){
                sv.setAddress(addressImputed);
            }else{
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
        String fullNameI = i.getName()+" "+i.getLastNameP()+" "+i.getLastNameM();
        svImputed.setFullName(fullNameI);
        String phone="No proporcionado";
        if(i.getCelPhone()!= null && !i.getCelPhone().equals("")){
            phone=i.getCelPhone();
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
    @Override
    public ModelAndView showVerificationBySource(Long idCase, Long idSource) {
        Gson gson= new Gson();
        ModelAndView model = new ModelAndView("/reviewer/verification/verificationBySource");
        setImputedData(idCase, model);
        ModelAndView aux =  meetingService.showMeeting(idCase);
        model.addAllObjects(aux.getModel());
        Case c = caseRepository.findOne(idCase);
        //model.addObject("listImputedHome", gson.toJson(c.getMeeting().getImputedHomes()));
        return model;
    }

    @Autowired
    CaseRepository caseRepository;

    @Override
    public void setImputedData(Long id, ModelAndView model) {
        Case c = caseRepository.findOne(id);
        model.addObject("idFolder", c.getIdFolder());
        String fullName = c.getMeeting().getImputed().getName() + " " + c.getMeeting().getImputed().getLastNameP() + " " + c.getMeeting().getImputed().getLastNameM();
        model.addObject("fullNameImputed", fullName);
        model.addObject("idCase", id);
    }
}
