package com.umeca.service.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.ArrangementView;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatView;

import java.util.List;

/**
 * Created by Vmware on 05/06/2014.
 */
public interface HearingFormatService {

    ResponseMessage save(HearingFormat hearingFormat);
    HearingFormat fillHearingFormat(HearingFormatView viewFormat);
    HearingFormatView fillForView(Case existCase,String idFolder);
    List<ArrangementView> getArrangmentLst(String folderId);
}
