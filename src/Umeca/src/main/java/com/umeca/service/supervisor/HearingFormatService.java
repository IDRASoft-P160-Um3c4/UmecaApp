package com.umeca.service.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.ArrangementView;
import com.umeca.model.entities.supervisor.AssignedArrangement;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Vmware on 05/06/2014.
 */
public interface HearingFormatService {

    ResponseMessage validatePassCredential(String pass);

    HearingFormat fillHearingFormat(HearingFormatView viewFormat);

    List<ArrangementView> getArrangmentLst(Boolean national, Integer tipo);

    HearingFormatView fillNewHearingFormatForView(Long idCase);

    HearingFormatView fillExistHearingFormatForView(Long idFormat);

    ResponseMessage save(HearingFormat hearingFormat, HttpServletRequest request);

    List<ArrangementView> assignedArrangementForView(List<AssignedArrangement> assignedArrangements);
}
