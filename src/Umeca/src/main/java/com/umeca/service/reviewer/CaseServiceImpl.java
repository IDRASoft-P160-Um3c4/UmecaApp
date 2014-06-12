package com.umeca.service.reviewer;

import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.StatusMeetingRepository;
import com.umeca.repository.reviewer.ImputedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vmware on 06/06/2014.
 */

@Service("caseService")
public class CaseServiceImpl implements CaseService {

    @Autowired
    ImputedRepository imputedRepository;

    @Autowired
    StatusMeetingRepository statusMeetingRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Override
    public Case generateNewCase(Imputed imputed, Integer type) {

        Case caseDet = new Case();

        if (imputedRepository.findImputedRegister(imputed.getName(), imputed.getLastNameP(), imputed.getLastNameM(), imputed.getDateBirth()).size() > 0)
            caseDet.setRecidivist(true);
        else
            caseDet.setRecidivist(false);

        Meeting meeting = new Meeting();
        StatusMeeting statusMeeting = statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE);
        meeting.setStatus(statusMeeting);
        imputed.setMeeting(meeting);
        meeting.setImputed(imputed);
        meeting.setCaseDetention(caseDet);
        meeting.setMeetingType(type);
        caseDet.setMeeting(meeting);

        return caseDet;
    }

    @Override
    @Transactional
    public Case save(Case caseDet) {

        try {
            caseDet = caseRepository.save(caseDet);
        } catch (Exception e) {
            System.out.println("Error al guardar el caso!!");
            System.out.println(e.getMessage());
        }

        return caseDet;
    }

    @Override
    public Case findByIdFolder(String idFolder) {
        return caseRepository.findByIdFolder(idFolder);
    }

}
