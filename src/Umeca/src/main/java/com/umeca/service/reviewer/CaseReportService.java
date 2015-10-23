package com.umeca.service.reviewer;


import com.umeca.model.entities.reviewer.View.CaseReportView;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileAllSourcesView;

public interface CaseReportService {

    CaseReportView getCaseReportSheetById(Long id);
    TechnicalReviewInfoFileAllSourcesView fillInfoFileAllSourcesFromEvent(Long id);
}
