package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusMeetingRepository;
import com.umeca.repository.reviewer.ImputedRepository;
import com.umeca.repository.reviewer.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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

    @Override
    public ModelAndView showMeeting(Long id) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/meeting");
        Gson gson = new Gson();
        String lstRoles = gson.toJson(new ResponseMessage());
        Case caseDetention = caseRepository.findOne(id);
        model.addObject("m",caseDetention.getMeeting());
        model.addObject("lstRoles", lstRoles);
        return model;
    }
}
