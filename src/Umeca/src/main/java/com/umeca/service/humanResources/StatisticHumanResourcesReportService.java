package com.umeca.service.humanResources;


public interface StatisticHumanResourcesReportService {
    String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long employee);
}