package com.umeca.service.tablet;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;
import com.umeca.model.entities.shared.TabletAssignmentInfo;

import java.util.List;

public interface TabletService {

    public TabletCaseDto getAllCaseByIdCase(Long idCase, String assigmentType);

}
