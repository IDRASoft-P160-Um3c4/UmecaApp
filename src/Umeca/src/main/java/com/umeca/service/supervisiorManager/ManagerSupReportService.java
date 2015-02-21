package com.umeca.service.supervisiorManager;

import com.umeca.model.entities.supervisor.ManagerSupExcelReportInfo;
import com.umeca.model.entities.supervisor.ManagerSupReportParams;

/**
 * Created by Vmware on 20/02/2015.
 */
public interface ManagerSupReportService {

    public ManagerSupExcelReportInfo getCountByArrangements(ManagerSupReportParams params, ManagerSupExcelReportInfo info);

}
