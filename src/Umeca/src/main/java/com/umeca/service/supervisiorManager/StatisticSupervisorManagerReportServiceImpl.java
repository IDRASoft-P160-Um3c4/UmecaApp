package com.umeca.service.supervisiorManager;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 04/09/2015.
 */

@Service("statisticSupervisorManagerReport")
public class StatisticSupervisorManagerReportServiceImpl implements StatisticSupervisorManagerReportService {
    @Autowired
    SharedUserService userService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StatisticSupervisorManagerReportRepository statisticSupervisorManagerReportRepository;

    @Autowired
    ReportTypeRepository reportTypeRepository;
    @Autowired
    SharedUserService sharedUserService;





    @Override
    public List<SelectList> getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict) {

        List<SelectList>data = new ArrayList<>();
        List<Object> lstObjects;

        Date initDateF = null;
        Date endDateF = null;
        int initId = 0;
        int endId = 0;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";
        try {
            initDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(initDate + initTime);
            endDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(endDate + endTime);
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            initId = Integer.parseInt(df.format(initDateF));
            endId = Integer.parseInt(df.format(endDateF));
        }catch (Exception e){
            logException.Write(e, this.getClass(), "save", sharedUserService);
        }

        switch (filter){
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_A:

                return eventRepository.countCasesProsecuted(initId, endId);
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_B:
                SelectList opinion = new SelectList();
                SelectList report = new SelectList();
                SelectList declined = new SelectList();

                opinion.setName("Opinión");
                report.setName("Informe");
                declined.setName("Negación");

                opinion.setValue(0L);
                report.setValue(0L);
                declined.setValue(0L);


                List<Long> casesId = eventRepository.getIdCasesByEvent(Constants.EVENT_PROSECUTE);
                for(Long caseId : casesId){
                    lstObjects = eventRepository.countEventsByCase(caseId, initId, endId);
                    for(int i = 0 ; i < lstObjects.size(); i++){
                        Object[] obj = (Object[]) lstObjects.get(i);
                        SelectList selectList = new SelectList();
                        selectList.setName(obj[0].toString());
                        selectList.setValue(Long.parseLong(obj[1].toString()));

                        switch (obj[0].toString()){
                            case Constants.EVENT_CASE_OPINION:
                                opinion.setValue(opinion.getValue() + Long.parseLong(obj[1].toString()));
                            break;

                            case Constants.EVENT_CASE_REPORT:
                                report.setValue(opinion.getValue() + Long.parseLong(obj[1].toString()));
                                break;

                            case Constants.EVENT_INTERVIEW_DECLINED:
                                declined.setValue(opinion.getValue() + Long.parseLong(obj[1].toString()));
                                break;
                        }
                    }

                }
                data.add(opinion);
                data.add(report);
                data.add(declined);
            return data;

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_C:
                switch (reportTypeRepository.getReportCodeById(idReportType)){
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangement();
                        for(int i = 0 ; i < lstObjects.size(); i++){
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setValue(Long.parseLong(obj[0].toString()));
                            selectList.setName(obj[1].toString());
                            selectList.setSubName(obj[1].toString());
                            data.add(selectList);
                        }
                        return data;

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangementAndDistrict(idDistrict);
                        for(int i = 0 ; i < lstObjects.size(); i++){
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setValue(Long.parseLong(obj[0].toString()));
                            selectList.setName(obj[1].toString());
                            selectList.setSubName(obj[1].toString());
                            data.add(selectList);
                        }
                        return data;
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:

                        break;
                }


              //  List<Object> lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangementOnlyAssigned();

        }
        return null;
    }
}
