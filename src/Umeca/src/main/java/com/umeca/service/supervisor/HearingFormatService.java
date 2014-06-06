package com.umeca.service.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatView;

/**
 * Created by Vmware on 05/06/2014.
 */
public interface HearingFormatService {

    ResponseMessage save(HearingFormat hearingFormat);
    HearingFormat fillHearingFormatMeeting(HearingFormatView viewFormat, Case existCase);
    HearingFormat fillHearingFormatConditional(HearingFormatView viewFormat);
    HearingFormatView fillForView(Case existCase);
}
