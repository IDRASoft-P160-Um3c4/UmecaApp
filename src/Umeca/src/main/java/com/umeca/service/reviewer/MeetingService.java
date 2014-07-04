package com.umeca.service.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.CriminalProceedingView;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MeetingService {
    Long createMeeting(Imputed imputed);

    ModelAndView showMeeting(Long id);

    ModelAndView showLegalProcess(Long id);

    ResponseMessage upsertPersonalData(Long idCase, Imputed imputed, SocialEnvironment socialEnvironment, String activities);

    ModelAndView upsertSocialNetwork(Long id, Long idCase);

    ResponseMessage doUpsertSocialNetwork(PersonSocialNetwork person, Long idCase);

    ResponseMessage deleteSocialNetwork(Long id);

    ModelAndView upsertReference(Long id, Long idCase);

    ResponseMessage doUpsertReference(Reference reference, Long idCase);

    ResponseMessage deleteReference(Long id);

    ModelAndView upsertDrug(Long id, Long idCase);

    ResponseMessage doUpsertDrug(Drug drug, Long idCase);

    ResponseMessage deleteDrug(Long id);

    ResponseMessage doUpsertSchool(Long id, School school,String schedules);

    ModelAndView upsertJob(Long id, Long idCase);

    ResponseMessage doUpsertJob(Job job, Long idCase, String sch);

    ResponseMessage deleteJob(Long id);

    ModelAndView upsertAddress(Long id, Long idCase);

    ResponseMessage doUpsertAddress(ImputedHome imputedHome, Long idCase, String sch);

    ResponseMessage deleteAddress(Long id);

    ResponseMessage upsertLeaveCountry(Long id, LeaveCountry leaveCountry);

    ResponseMessage doTerminateMeeting(Meeting meeting, String sch,String activity);

    ResponseMessage validateCreateMeeting(Imputed imputed);

    ResponseMessage saveProceedingLegal(CriminalProceedingView cpv);
}
