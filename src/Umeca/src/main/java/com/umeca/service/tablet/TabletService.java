package com.umeca.service.tablet;

import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;

import java.util.List;

public interface TabletService {

    public TabletCaseDto getAllCaseByIdCase(Long idCase, String assigmentType);


}
