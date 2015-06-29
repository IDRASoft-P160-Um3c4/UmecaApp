
package com.umeca.service.shared;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.SourceVerification;
import com.umeca.model.entities.shared.TabletAssignmentCase;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.shared.TabletAssignmentCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vmware on 06/06/2014.
 */

@Service("mobileService")
public class MobileServiceImpl implements MobileService{

    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private StatusCaseRepository statusCaseRepository;
    @Autowired
    private TabletAssignmentCaseRepository tabletAssignmentCaseRepository;

    @Transactional
    @Override
    public ResponseMessage saveAssignmentCase(Long idCase,Long idUser,String type) {//guarda asignaciones de entrevista de riesgos y de formatos de audiencia

        Case c = caseRepository.findOne(idCase);
        StatusCase stC= statusCaseRepository.findByCode(Constants.CASE_STATUS_TABLET_ASSIGNMENT);

        TabletAssignmentCase assignment = new TabletAssignmentCase();

        assignment.setAssignmentDate(Calendar.getInstance());
        assignment.setCaseDetention(c);
        User u = new User();
        u.setId(idUser);
        assignment.setAssignedUser(u);
        assignment.setIsObsolete(false);
        tabletAssignmentCaseRepository.save(assignment);

        c.setPreviousStateCode(c.getStatus().getName());
        c.setAssignmentType(type);
        c.setStatus(stC);
        caseRepository.save(c);

        return  new ResponseMessage(false, "Se ha guardado la información correctamente");
    }

    @Transactional
    @Override
    public ResponseMessage saveAssignmentCaseWhitSources(Long idCase,String type,String sourcesRel){
        Gson gson = new Gson();
        Case c = caseRepository.findOne(idCase);
        StatusCase stC= statusCaseRepository.findByCode(Constants.CASE_STATUS_TABLET_ASSIGNMENT);

        List<SelectList> lstUsrSourceIds = gson.fromJson(sourcesRel, new TypeToken<List<SelectList>>() {
        }.getType());

        for(SelectList curr :  lstUsrSourceIds) {
            TabletAssignmentCase assignment = new TabletAssignmentCase();
            assignment.setAssignmentDate(Calendar.getInstance());
            assignment.setCaseDetention(c);
            User u = new User();
            u.setId(curr.getId());
            assignment.setAssignedUser(u);
            SourceVerification sourceVerification = new SourceVerification();
            sourceVerification.setId(curr.getAux());
            assignment.setSourceVerification(sourceVerification);
            assignment.setIsObsolete(false);
            tabletAssignmentCaseRepository.save(assignment);
        }

        c.setPreviousStateCode(c.getStatus().getName());
        c.setAssignmentType(type);
        c.setStatus(stC);
        caseRepository.save(c);

        return  new ResponseMessage(false, "Se ha guardado la información correctamente");
    }

    @Transactional
    @Override
    public ResponseMessage unassignCases(Long idCase) {

        Gson gson = new Gson();
        Case c = caseRepository.findOne(idCase);

        List<TabletAssignmentCase> lstAssign = tabletAssignmentCaseRepository.getAssignmentsByCaseId(idCase);

        for(TabletAssignmentCase assignment: lstAssign){
            assignment.setIsObsolete(true);
        }
        tabletAssignmentCaseRepository.save(lstAssign);

        StatusCase stC= statusCaseRepository.findByCode(c.getPreviousStateCode());
        c.setPreviousStateCode(null);
        c.setStatus(stC);
        caseRepository.save(c);

        return  new ResponseMessage(false, "Se ha guardado la información correctamente");
    }



}
