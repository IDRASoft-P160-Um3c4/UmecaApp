package com.umeca.service.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
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
    ResponseMessage saveSelectedSource(Long idCase, String lstSourcesStr);
    ResponseMessage saveReference(Case existCase, FramingReference newReference);
    ProcessAccompaniment fillProcessAccompaniment(ProcessAccompanimentForView view);
    ProcessAccompanimentForView fillProcessAccompanimentForView(Long idCase);
    ResponseMessage saveProcessAccompaniment(ProcessAccompaniment processAccompaniment);
}
