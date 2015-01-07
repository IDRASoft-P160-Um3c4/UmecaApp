package com.umeca.service.supervisor;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.dto.AddressDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.supervisor.*;

import java.util.List;

/**
 * Created by Vmware on 05/06/2014.
 */
public interface FramingMeetingService {
    ResponseMessage save(FramingMeeting framingMeeting);

    FramingMeetingView fillForView(Case existCase);

    List<FramingReferenceForView> loadExistSources(Long idCase);


    ResponseMessage saveSelectedItems(Long idCase, FramingEnvironmentAnalysisForView view);

    ResponseMessage saveReference(Case existCase, FramingReference newReference);

//    ProcessAccompaniment fillProcessAccompaniment(Long idCase, ProcessAccompanimentForView view);
//    ProcessAccompanimentForView fillProcessAccompanimentForView(Long idCase);
//    ResponseMessage saveProcessAccompaniment(ProcessAccompaniment processAccompaniment);

    ResponseMessage deleteReference(Long id);

    FramingEnvironmentAnalysisForView loadEnvironmentAnalysis(Long idCase);

    FramingImputedPersonalData fillPersonalData(Long idCase, FramingPersonalDataView view);

    FramingPersonalDataView fillPersonalDataForView(Long idCase);

    ResponseMessage saveFramingAddress(Long idCase, FramingAddressDto view);

    ResponseMessage deleteFramingAddress(Long id);

    ResponseMessage doUpsertDrug(Drug drug, Long idCase);

    ResponseMessage deleteDrug(Long id);

    AdditionalQuestionsForView fillAddtionalQuestionsForView(Long idCase);

    ResponseMessage saveAddQuest(Long idCase, AdditionalQuestionsForView view);

    ResponseMessage doTerminate(Long idCase);

    void fillSaveVerifiedInfo(FramingMeeting existFraming, Meeting verifMeeting);

    ResponseMessage upsertComments(Long idCase, Integer commentType, String comments);

    FramingActivityView fillActivityForView(Long idActivity, Long idCase);

    ResponseMessage saveFramingActivity(FramingActivityView view, Long idCase);

    ResponseMessage deleteFramingActivity(Long id);

    JobDto fillJobForView(Long idJob, Long idCase);

    ResponseMessage saveFramingJob(JobDto view, Long idCase);

    ResponseMessage deleteFramingJob(Long id);

    SchoolDto fillSchoolForView(Long idCase);

    ResponseMessage saveSchool(SchoolDto view);

    ResponseMessage saveVictim(Case existCase, FramingReference newReference);

    FramingAddressDto fillFramingAddressForView(FramingAddress existAddress);

    FramingMeetingLog getFramingPersonalDataLog(FramingMeeting framingMeeting, FramingPersonalDataView personalData, String logType);

    FramingMeetingLog getFramingAddressLog(FramingMeeting framingMeeting, FramingAddressDto framingAddressDto, String logType);

}
