package com.umeca.service.humanResources;


import com.umeca.model.dto.humanResources.AttendanceExcelDto;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;

import java.util.List;

public interface StatisticHumanResourcesReportService {
    List<ReportList> getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long idEmployee);

    List<AttendanceExcelDto> getAttendanceLog(String initDate, String endDate);
}