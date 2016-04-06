
package com.umeca.service.shared;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.LogNotificationReviewer;
import com.umeca.model.entities.reviewer.SourceVerification;
import com.umeca.model.entities.shared.TabletAssignmentCase;
import com.umeca.model.entities.shared.TabletRelAssignmentSource;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.shared.TabletAssignmentCaseRepository;
import com.umeca.repository.supervisor.LogNotificationReviewerRepository;
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
public class MobileServiceImpl implements MobileService {

    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private StatusCaseRepository statusCaseRepository;
    @Autowired
    private TabletAssignmentCaseRepository tabletAssignmentCaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogNotificationReviewerRepository logNotificationReviewerRepository;

    @Transactional
    @Override
    public ResponseMessage saveAssignmentCase(Long idCase, Long idUser, String type) {//guarda asignaciones de entrevista de riesgos y de formatos de audiencia

        Case c = caseRepository.findOne(idCase);
        StatusCase stC = statusCaseRepository.findByCode(Constants.CASE_STATUS_TABLET_ASSIGNMENT);

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



        LogNotificationReviewer lnr = new LogNotificationReviewer();
        if (type.equals(Constants.MEETING_ASSIGNMENT_TYPE)){
            List<User> coordinadoresEvaluacion = userRepository.getLstValidUserIdsByRole(Constants.ROLE_EVALUATION_MANAGER);
            User userReceiver = new User();
            if(coordinadoresEvaluacion != null && coordinadoresEvaluacion.size() >0 ) {
                userReceiver = coordinadoresEvaluacion.get(0);
            }else {
                userReceiver = u;
            }
            lnr.setSubject("Entrevista de riesgos procesales asignada a tableta");
            lnr.setMessage("<strong>Carpeta de investigaci&oacute;n: </strong>" + c.getIdFolder() +
                    "<br/><strong>Nombre del imputado: </strong>"+
                    c.getMeeting().getImputed().getName()+
                    " "+ c.getMeeting().getImputed().getLastNameP()+
                    " "+ c.getMeeting().getImputed().getLastNameM()+
                    "<br/><strong>Evaluador: </strong>"+u.getFullname()+"<br/>");
            lnr.setSenderUser(u);
            lnr.setReceiveUser(userReceiver);
            logNotificationReviewerRepository.save(lnr);
        }



        return new ResponseMessage(false, "Se ha guardado la información correctamente");
    }

    @Transactional
    @Override
    public ResponseMessage saveAssignmentCaseWhitSources(Long idCase, String type, String sourcesRel) {
        Gson gson = new Gson();

        Case c = caseRepository.findOne(idCase);
        c.setPreviousStateCode(c.getStatus().getName());
        c.setAssignmentType(Constants.VERIFICATION_ASSIGNMENT_TYPE);
        StatusCase stC = statusCaseRepository.findByCode(Constants.CASE_STATUS_TABLET_ASSIGNMENT);
        c.setStatus(stC);
        caseRepository.save(c);

        List<Long> idsUsr = new ArrayList<>();
        List<SelectList> lstUsrSourceIds = gson.fromJson(sourcesRel, new TypeToken<List<SelectList>>() {
        }.getType());

        //obtengo los ids de los diferentes usuarios
        for (SelectList currSource : lstUsrSourceIds) {
            if (!idsUsr.contains(currSource.getId())) {
                idsUsr.add(currSource.getId());
            }
        }

        //genero las asignaciones con su respectiva lista de fuentes
        for (Long currUsr : idsUsr) {
            TabletAssignmentCase tabletAssignmentCase = new TabletAssignmentCase();
            List<TabletRelAssignmentSource> lstRelSources = new ArrayList<>();

            tabletAssignmentCase.setAssignmentDate(Calendar.getInstance());
            tabletAssignmentCase.setIsObsolete(false);
            tabletAssignmentCase.setCaseDetention(c);

            User u = new User();
            u.setId(currUsr);

            tabletAssignmentCase.setAssignedUser(u);

            tabletAssignmentCaseRepository.save(tabletAssignmentCase);

            for (SelectList currSource : lstUsrSourceIds) {
                if (currSource.getId().equals(currUsr)) {
                    TabletRelAssignmentSource rel = new TabletRelAssignmentSource();
                    SourceVerification sv = new SourceVerification();
                    sv.setId(currSource.getAux());
                    rel.setSourceVerification(sv);
                    rel.setTabletAssignmentCase(tabletAssignmentCase);
                    lstRelSources.add(rel);
                }
            }

            tabletAssignmentCase.setListRelAssignedSources(lstRelSources);
            tabletAssignmentCaseRepository.save(tabletAssignmentCase);
        }



        return new ResponseMessage(false, "Se ha guardado la información correctamente");
    }

    @Transactional
    @Override
    public ResponseMessage unassignCases(Long idCase) {

        Gson gson = new Gson();
        Case c = caseRepository.findOne(idCase);

        List<TabletAssignmentCase> lstAssign = tabletAssignmentCaseRepository.getAssignmentsByCaseIdAll(idCase);

        for (TabletAssignmentCase assignment : lstAssign) {
            assignment.setIsObsolete(true);
        }
        tabletAssignmentCaseRepository.save(lstAssign);

        StatusCase stC = statusCaseRepository.findByCode(c.getPreviousStateCode());
        c.setPreviousStateCode(null);
        c.setStatus(stC);
        caseRepository.save(c);

        return new ResponseMessage(false, "Se ha guardado la información correctamente");
    }


}
