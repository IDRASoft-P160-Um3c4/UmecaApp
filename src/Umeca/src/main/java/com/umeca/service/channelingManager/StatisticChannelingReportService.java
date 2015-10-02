package com.umeca.service.channelingManager;


public interface StatisticChannelingReportService {
    String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long idSupervisor);
}