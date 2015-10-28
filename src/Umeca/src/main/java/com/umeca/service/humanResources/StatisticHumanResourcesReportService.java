package com.umeca.service.humanResources;


import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;

public interface StatisticHumanResourcesReportService {
    String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long employee);
    JqGridResultModel getDataGrid(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long employee);
}