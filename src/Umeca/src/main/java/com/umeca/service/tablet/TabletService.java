package com.umeca.service.tablet;

import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;

public interface TabletService {
    TabletCaseDto getAllCaseByIdCase(Long idCase);
}
