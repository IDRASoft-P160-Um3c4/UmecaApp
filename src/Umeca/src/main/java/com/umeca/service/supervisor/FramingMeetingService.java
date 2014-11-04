package com.umeca.service.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.dto.AddressDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;

import java.util.List;

/**
 * Created by Vmware on 05/06/2014.
 */
public interface FramingMeetingService {
    ResponseMessage save(FramingMeeting framingMeeting);

    FramingMeeting fillFramingMeeting(FramingMeetingView viewFraming);

    FramingMeetingView fillForView(Case existCase);

    List<FramingReferenceForView> loadExistSources(Long idCase);

    List<FramingSelectedSourceRel> generateSourceRel(Long idCase, String lstJson);

    ResponseMessage saveSelectedItems(Long idCase, FramingEnvironmentAnalysisForView view);

    ResponseMessage saveReference(Case existCase, FramingReference newReference);

    ProcessAccompaniment fillProcessAccompaniment(Long idCase, ProcessAccompanimentForView view);

    ProcessAccompanimentForView fillProcessAccompanimentForView(Long idCase);

    ResponseMessage saveProcessAccompaniment(ProcessAccompaniment processAccompaniment);

    FramingMeeting setActivities(FramingMeeting existFraming, FramingActivitiesForView view);

    ResponseMessage deleteReference(Long id);

    FramingEnvironmentAnalysisForView loadEnvironmentAnalysis(Long idCase);

    FramingImputedPersonalData fillPersonalData(Long idCase, FramingPersonalDataView view);

    FramingPersonalDataView fillPersonalDataForView(Long idCase);

    ResponseMessage saveFramingAddress(Long idCase, AddressDto view);

    ResponseMessage deleteFramingAddress(Long id);

    FramingActivitiesForView fillActivitiesForView(Long idCase);

    ResponseMessage doUpsertDrug(Drug drug, Long idCase);

    ResponseMessage deleteDrug(Long id);

    AdditionalQuestionsForView fillAddtionalQuestionsForView(Long idCase);

    ResponseMessage saveAddQuest(Long idCase, AdditionalQuestionsForView view);

    ResponseMessage doTerminate(Long idCase);

    void fillSaveVerifiedInfo(FramingMeeting existFraming, Meeting verifMeeting);

    ResponseMessage upsertComments(Long idCase, Integer commentType, String comments);
}
