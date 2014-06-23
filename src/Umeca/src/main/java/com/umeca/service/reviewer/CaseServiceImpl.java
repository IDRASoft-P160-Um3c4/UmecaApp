package com.umeca.service.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.FolderConditionalReprieve;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.catalog.StatusMeetingRepository;
import com.umeca.repository.reviewer.ImputedRepository;
import com.umeca.repository.supervisor.FolderConditionalReprieveRepository;
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

    @Autowired
    FolderConditionalReprieveRepository folderConditionalReprieveRepository;


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
            caseRepository.flush();
        } catch (Exception e) {
            System.out.println("Error al guardar el caso!!");
            System.out.println(e.getMessage());
        }

        return caseDet;
    }

    @Override
    @Transactional
    public ResponseMessage saveConditionaReprieveCase(Case caseDet) {

        ResponseMessage resp= new ResponseMessage();

        try {
            FolderConditionalReprieve folderObj = new FolderConditionalReprieve();

            folderObj = folderConditionalReprieveRepository.save(folderObj);

            StringBuilder sb = new StringBuilder();

            sb.append(HearingFormatConstants.FOLDER_CONDITIONAL_REPRIEVE_PREFIX);
            sb.append(folderObj.getId());

            caseDet.setIdFolder(sb.toString());
            caseDet.setFolderConditionalReprieve(folderObj);
            folderObj.setCaseDetention(caseDet);
            caseRepository.save(caseDet);

            resp.setHasError(false);
            resp.setMessage("Se ha guardado el caso con exito.");

        }catch (Exception e){
            System.out.println("Error al guardar el caso por suspension condicional de proceso!!!");
            e.printStackTrace();
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error en el servidor, intente mas tarde");
        }

        return resp;
    }

    @Override
    public Case findByIdFolder(String idFolder) {
        return caseRepository.findByIdFolder(idFolder);
    }

}
