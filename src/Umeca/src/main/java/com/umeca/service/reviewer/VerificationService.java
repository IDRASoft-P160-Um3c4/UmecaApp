package com.umeca.service.reviewer;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.FieldMeetingSource;
import com.umeca.model.entities.reviewer.SourceVerification;
import com.umeca.model.entities.reviewer.dto.FieldVerified;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public interface VerificationService {
    void createVerification(Case c);

    List<SourceVerification> convertAllInitSourcesVerif(Case c);

    void setImputedData(Long id, ModelAndView model);

    ResponseMessage saveFieldVerifiedIncorrect(List<FieldVerified> list, Long idCase, Long idSource, Long idList);

    ResponseMessage saveFieldVerifiedEqual(String code, Long idCase, Long idSource, Long idList);

    List<FieldMeetingSource>  createAllFieldVerificationOfImputed(Long idCase);

    ResponseMessage saveFieldVerifiedNotKnow(String code, Long idCase, Long idSource, Long idList);

    ResponseMessage terminateMeetingSource(Long idCase, Long idSource);

    void showButtonsSource(ModelAndView model, Long id);

    ModelAndView showChoiceInformation(Long idCase, Integer read);

    ModelAndView showVerificationBySource(Long idCase, Long idSource);


    ModelAndView showChoices(Long idCase, String code, Long idList);

    ResponseMessage saveSchedule(Long idCase, Long idSource, Long idList, String schedule, String code);

    ResponseMessage saveAddressVerification(Long idCase, Long idSource, Long idList, String code, Address address);

    ResponseMessage saveSelectChoice(Long idCase, Long idFieldMeeting, String code, Long idList, String reason);

    ResponseMessage terminateVerification(Long idCase);

    ModelAndView upsertSource(Long idCase, Long id);

    ResponseMessage doUpsertSources(Long idCase, SourceVerification sv);

    ResponseMessage terminateAddSource(Long idCase);

    ResponseMessage verifChoicesBySection(Long idCase, Integer idSection, Long idList, Long idSource, String comment);

    ResponseMessage searchInformationByeSourceCode(Long idCase, Long idSource, String code, Long idList);

    void upsertCaseReport(Long idCase, String reason);
}
