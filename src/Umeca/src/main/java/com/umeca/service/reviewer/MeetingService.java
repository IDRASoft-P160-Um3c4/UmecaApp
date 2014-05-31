package com.umeca.service.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MeetingService {
    ResponseMessage createMeeting(Imputed imputed);

    ModelAndView showMeeting(Long id);

    ModelAndView showLegalProcess(Long id);

    ResponseMessage upsertPersonalData(Long idCase, Imputed imputed, SocialEnvironment socialEnvironment, Integer[] physicalCondition, Integer[] activity);


    ModelAndView upsertSocialNetwork(Long id, Long idCase);

    ResponseMessage doUpsertSocialNetwork(PersonSocialNetwork person, Long idCase);

    ResponseMessage deleteSocialNetwork(Long id);

    ModelAndView upsertReference(Long id, Long idCase);

    ResponseMessage doUpsertReference(Reference reference, Long idCase);

    ResponseMessage deleteReference(Long id);

    ModelAndView upsertDrug(Long id, Long idCase);

    ResponseMessage doUpsertDrug(Drug drug, Long idCase);

    ResponseMessage deleteDrug(Long id);
}
