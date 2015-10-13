package com.umeca.service.shared;

import com.umeca.infrastructure.model.ResponseMessage;

public interface MobileService {

    public ResponseMessage saveAssignmentCase(Long idCase, Long idUser, String type);
    public ResponseMessage saveAssignmentCaseWhitSources(Long idCase, String type, String sourcesRel);
    public ResponseMessage unassignCases(Long idCase);

}
