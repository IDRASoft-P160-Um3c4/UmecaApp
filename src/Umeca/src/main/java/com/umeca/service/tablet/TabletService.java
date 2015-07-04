package com.umeca.service.tablet;

import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;

import java.util.List;

public interface TabletService {
    TabletCaseDto getAllCaseByIdCase(Long idCase);

    public void synchronizeMeeting(TabletCaseDto tabletCase);
    public void synchronizeVerification(TabletCaseDto tabletCase);

}
