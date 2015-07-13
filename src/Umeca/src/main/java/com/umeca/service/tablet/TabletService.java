package com.umeca.service.tablet;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.dto.tablet.TabletLogCaseDto;
import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.TabletAssignmentInfo;

import java.util.List;

public interface TabletService {

    ResponseMessage getCaseByAssignmentId(Long assignmentId, Long usrId);

    ResponseMessage setDownloadDateToAssignment(Long assignmentId);

    Case synchronizeVerification(TabletCaseDto tabletCase, Long assignmentId);

    Case synchronizeMeeting(TabletCaseDto tabletCase, Long assignmentId);

    Case synchronizeHearingFormats(TabletCaseDto tabletCaseDto, Long assignmentId);

    boolean validateExistCase(String idFolder, String phoneticString, String birthDateStr);

}
