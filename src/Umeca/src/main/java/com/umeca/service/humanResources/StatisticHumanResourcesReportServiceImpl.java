package com.umeca.service.humanResources;

import com.google.gson.Gson;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.catalog.StatisticHumanResourcesReportTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("StatisticHumanResourcesReport")
public class StatisticHumanResourcesReportServiceImpl implements StatisticHumanResourcesReportService {
    @Autowired
    SharedUserService userService;
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    ReportTypeRepository reportTypeRepository;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    ArrangementRepository arrangementRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatisticHumanResourcesReportTypeRepository statisticHumanResourcesReportTypeRepository;

    @Override
    public List<SelectList> getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long idEmployee) {
        List<SelectList> data = new ArrayList<>();
        List<Object> lstObjects;
        Gson gson = new Gson();
        Date initDateF = null;
        Date endDateF = null;
        Calendar initCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        int monthI = 0;
        int monthF = 0;

        String initTime = " 00:00:00";
        String endTime = " 23:59:59";


        try {
            initDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(initDate + initTime);
            endDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(endDate + endTime);
            initCal.setTime(initDateF);
            endCal.setTime(endDateF);

            monthI = initCal.get(Calendar.MONTH);
            monthF = endCal.get(Calendar.MONTH);

        } catch (Exception e) {
            logException.Write(e, this.getClass(), "getData", sharedUserService);
        }

        switch (filter) {
            case Constants.REPORT_HUMAN_RESOURCES_STATISTIC_A:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeAbsence(initCal, endCal, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeAbsenceByDistrict(initCal, endCal, idDistrict, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeAbsenceByOperator(initCal, endCal, idEmployee, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;

                }
                break;


            case Constants.REPORT_HUMAN_RESOURCES_STATISTIC_B:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeDelays(initCal, endCal, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeDelaysByDistrict(initCal, endCal, idDistrict, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeDelaysByOperator(initCal, endCal, idEmployee, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;


                }
                break;

            case Constants.REPORT_HUMAN_RESOURCES_STATISTIC_C:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeBonusTime(initCal, endCal, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeBonusTimeByDistrict(initCal, endCal, idDistrict, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        lstObjects = statisticHumanResourcesReportTypeRepository.countEmployeeBonusTimeByOperator(initCal, endCal, idEmployee, monthI, monthF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(Long.parseLong(obj[0].toString()), Long.parseLong(obj[1].toString()), Long.parseLong(obj[2].toString())));
                        }
                        return data;
                }
                break;
        }
        return null;
    }


}
