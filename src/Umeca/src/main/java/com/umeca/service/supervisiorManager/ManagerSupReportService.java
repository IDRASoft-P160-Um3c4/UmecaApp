package com.umeca.service.supervisiorManager;

import com.umeca.model.entities.supervisor.ManagerSupExcelReportInfo;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

/**
 * Created by Vmware on 20/02/2015.
 */
public interface ManagerSupReportService {

    public ManagerSupExcelReportInfo getCountByArrangements(ManagerSupExcelReportInfo info, Date initDate, Date endDate);

}
