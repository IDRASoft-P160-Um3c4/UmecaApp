package com.umeca.service.supervisiorManager;

import com.umeca.model.shared.SelectList;

import java.util.List;

/**
 * Created by DeveloperII on 04/09/2015.
 */
public interface StatisticSupervisorManagerReportService {
    String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict);
}