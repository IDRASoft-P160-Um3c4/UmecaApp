package com.umeca.service.channelingManager;


public interface StatisticChannelingReportService {
    String getData(String initDate, String endDate, Long idReportType, Long idDistrict, Long idChannelingType);
    String oldGetData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long idSupervisor);
}