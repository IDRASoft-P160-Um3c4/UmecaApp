package com.umeca.service.supervisiorManager;

import com.umeca.model.entities.supervisor.ManagerSupExcelReportInfo;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.shared.ReportExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerSupReportServiceImpl implements ManagerSupReportService {

    @Autowired
    ReportExcelRepository reportExcelRepository;

    public ManagerSupExcelReportInfo getCountByArrangements(ManagerSupExcelReportInfo info, Date initDate, Date endDate) {

        List<String> lstStatus = new ArrayList<String>() {{
            add(Constants.CASE_STATUS_CLOSED);
            add(Constants.CASE_STATUS_PRISON_CLOSED);
            add(Constants.CASE_STATUS_OBSOLETE_EVALUATION);
            add(Constants.CASE_STATUS_OBSOLETE_SUPERVISION);
        }};

        //obtengo los casos con su ultimo formato registrado dentro del rango y que no este en los status
        List<Object> lstObjects = reportExcelRepository.getLastFormatInDates(initDate, endDate, lstStatus);

        List<Long> idsCases = new ArrayList<>();

        //obtengo los casos
        if (lstObjects != null && lstObjects.size() > 0) {
            for (int a = 0; a < lstObjects.size(); a++) {
                Object[] obj = (Object[]) lstObjects.get(a);
                idsCases.add(Long.parseLong(obj[0].toString()));
            }
        }

        //reocupo el query del reporte de excel de director
        List<Object> lstObjArrangement = reportExcelRepository.getCountCasesByArrangement(idsCases);

        List<SelectList> finalResult = new ArrayList<>();

        if (lstObjArrangement != null && lstObjArrangement.size() > 0) {
            for (int a = 0; a < lstObjArrangement.size(); a++) {
                Object[] obj = (Object[]) lstObjArrangement.get(a);
                SelectList sl = new SelectList(Long.parseLong(obj[0].toString()), obj[1].toString());
                finalResult.add(sl);
            }
        }

        info.setLstCasesByArrangement(finalResult);

        return info;
    }
}
