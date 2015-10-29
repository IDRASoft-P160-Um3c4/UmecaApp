package com.umeca.service.humanResources;


import com.umeca.model.shared.SelectList;

import java.util.List;

public interface StatisticHumanResourcesReportService {
    List<SelectList> getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long idEmployee);
}