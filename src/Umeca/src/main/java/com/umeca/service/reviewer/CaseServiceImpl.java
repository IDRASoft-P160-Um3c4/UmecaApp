package com.umeca.service.reviewer;

import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
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

    @Override
    public Case generateNewCase(Imputed imputed) { //equivalente al createMeeting

        Case caseDet = new Case();
        Meeting meeting = new Meeting();

        if (imputedRepository.countCaseSameRFC(imputed.getRfc()) > 0)
            caseDet.setRecidivist(true);
        else
            caseDet.setRecidivist(false);

        StatusMeeting statusMeeting = statusMeetingRepository.findByCode(Constants.S_MEETING_INCOMPLETE);
        meeting.setStatus(statusMeeting);

        imputed.setRfc("AAABBBCC"); //TODO REEMPLAZAR POR EL METODO QUE GENERA EL RFC

        imputed.setMeeting(meeting);
        meeting.setImputed(imputed);

        meeting.setCaseDetention(caseDet);
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
