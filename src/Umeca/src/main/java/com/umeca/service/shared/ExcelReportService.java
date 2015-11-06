package com.umeca.service.shared;

import com.umeca.model.entities.director.view.ReportExcelFiltersDto;
import com.umeca.model.entities.supervisor.ReportExcelSummary;

import java.util.List;

/**
 * Created by DeveloperII on 04/11/2015.
 */
public interface ExcelReportService {


    List<Long> findCasesByFilters(List<Long> idsCasesInDateRange, ReportExcelFiltersDto filtersDto);
    List<Long> intersectIds(List<Long> listA, List<Long> listB);
    List<Long> conjunctionIds(List<Long> listA, List<Long> listB);
    ReportExcelSummary fillSummary(String filters);

}
