package com.umeca.service.humanResources;

import com.google.gson.Gson;
import com.umeca.model.catalog.DrugType;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.catalog.StatisticHumanResourcesReportTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
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
    public String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long idEmployee) {
        List<SelectList> data = new ArrayList<>();
        List<Object> lstObjects;
        Gson gson = new Gson();
        Date initDateF = null;
        Date endDateF = null;
        Calendar initCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

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
            initCal.setTime(initDateF);
            endCal.setTime(endDateF);

        } catch (Exception e) {
            logException.Write(e, this.getClass(), "getData", sharedUserService);
        }

        switch (filter) {
            case Constants.REPORT_HUMAN_RESOURCES_STATISTIC_A:
//                switch (reportTypeRepository.getReportCodeById(idReportType)) {
//                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
//                        return gson.toJson(statisticSupervisorManagerReportRepository.countCasesProsecuted(initId, endId));
//                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
//                        return gson.toJson(statisticSupervisorManagerReportRepository.countCasesProsecutedByDistrict(initId, endId, idDistrict));
//                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
//                }
                break;


            case Constants.REPORT_HUMAN_RESOURCES_STATISTIC_B:

                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticHumanResourcesReportTypeRepository.countEmployeeDelays(initCal, endCal);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticHumanResourcesReportTypeRepository.countEmployeeDelaysByDistrict(initCal, endCal, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        data = statisticHumanResourcesReportTypeRepository.countEmployeeDelaysByOperator(initCal, endCal, idEmployee);
                        return gson.toJson(data);


                }
                break;

            case Constants.REPORT_HUMAN_RESOURCES_STATISTIC_C:
//                switch (reportTypeRepository.getReportCodeById(idReportType)) {
//                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
//                        lstObjects = statisticHumanResourcesReportTypeRepository.getCountCasesByArrangement(initDate + initTime, endDate + endTime);
//                        for (int i = 0; i < lstObjects.size(); i++) {
//                            Object[] obj = (Object[]) lstObjects.get(i);
//                            SelectList selectList = new SelectList();
//                            selectList.setName(obj[0].toString());
//                            selectList.setSubName(obj[0].toString());
//                            selectList.setValue(Long.parseLong(obj[1].toString()));
//                            data.add(selectList);
//                        }
//                        return gson.toJson(data);
//
//                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
//                        lstObjects = statisticHumanResourcesReportTypeRepository.getCountCasesByArrangementAndDistrict(initDate + initTime, endDate + endTime, idDistrict);
//                        for (int i = 0; i < lstObjects.size(); i++) {
//                            Object[] obj = (Object[]) lstObjects.get(i);
//                            SelectList selectList = new SelectList();
//                            selectList.setName(obj[0].toString());
//                            selectList.setSubName(obj[0].toString());
//                            selectList.setValue(Long.parseLong(obj[1].toString()));
//                            data.add(selectList);
//                        }
//                        return gson.toJson(data);
//                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
//                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
//                        List<List<ReportList>> total = new ArrayList<>();
//
//                        for (int i = 0; i < users.size(); i++) {
//                            lstObjects = statisticHumanResourcesReportTypeRepository.getArrangementByIdAndSupervisorId(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
//                            for (int j = 0; j < lstObjects.size(); j++) {
//                                Object[] obj = (Object[]) lstObjects.get(j);
//                                if (i == 0) {
//                                    total.add(new ArrayList<ReportList>());
//                                }
//                                ReportList reportList = new ReportList();
//                                reportList.setId(new Long(j));
//                                reportList.setName(obj[0].toString());
//                                reportList.setUser(users.get(i).getName());
//                                reportList.setX(new Long(i));
//                                reportList.setY(new Long(obj[1].toString()));
//                                total.get(j).add(reportList);
//                            }
//                        }
//                        return gson.toJson(total);
//
//
//                }

        }
        return null;
    }

    private List<Object> completeData(List<Object> finalData, List<Object> data, String supervisor, int x, List<DrugType> drugs) {

        int countNum = 0;
        for (int i = 0; i < drugs.size(); i++) {
            List<ReportList> aux = new ArrayList<>();
            if (finalData.size() > i) {
                aux = (List<ReportList>) finalData.get(i);
            }
            if (data.size() == countNum) {
                aux.add(new ReportList(new Long(i), new Long(0), drugs.get(i).getName(), supervisor, (long) x));

            } else {
                for (int j = countNum; j < data.size(); j++) {
                    Object[] obj = (Object[]) data.get(j);

                    if (drugs.get(i).getId() == Long.parseLong(obj[0].toString())) {
                        aux.add(new ReportList(new Long(i), Long.parseLong(obj[2].toString()), drugs.get(i).getName(), supervisor, (long) x));
                        countNum = j + 1;
                    } else {
                        aux.add(new ReportList(new Long(i), new Long(0), drugs.get(i).getName(), supervisor, (long) x));
                    }
                    break;


                }
            }

            if (finalData.size() <= i) {
                finalData.add(aux);
            } else {
                finalData.set(i, aux);
            }

        }

        return finalData;

    }

    private List<SelectList> completeDrugsData(List<SelectList> finalData, List<Object> data, List<DrugType> drugs) {

        int countNum = 0;


        for (int i = 0; i < drugs.size(); i++) {

            if (data.size() == countNum) {
                finalData.add(new SelectList(drugs.get(i).getName(), new Long(0)));

            } else {
                for (int j = countNum; j < data.size(); j++) {
                    Object[] obj = (Object[]) data.get(j);

                    if (drugs.get(i).getId() == Long.parseLong(obj[0].toString())) {
                        finalData.add(new SelectList(obj[1].toString(), Long.parseLong(obj[2].toString())));
                        countNum = j + 1;
                    } else {
                        finalData.add(new SelectList(drugs.get(i).getName(), new Long(0)));
                    }
                    break;


                }
            }

        }

        return finalData;

    }

    private List<SelectList> completeDoubleData(List<SelectList> data, String firstSrt, String secondSrt) {

        if (data.size() > 0) {
            if (!data.get(0).getName().equals(firstSrt)) {
                SelectList x = data.get(0);
                data.set(0, new SelectList(firstSrt, new Long(0)));
                data.add(x);
            } else if (data.size() < 2)
                data.add(new SelectList(secondSrt, new Long(0)));
        } else {
            data.add(new SelectList(firstSrt, new Long(0)));
            data.add(new SelectList(secondSrt, new Long(0)));
        }

        return data;

    }

    private List<Object> completeDataBySup(List<Object> finalData, List<SelectList> data, String supervisor, int x) {


        List<ReportList> aux1 = new ArrayList<>();
        List<ReportList> aux2 = new ArrayList<>();

        if (finalData.size() > 0) {
            aux1 = (List<ReportList>) finalData.get(0);
            aux2 = (List<ReportList>) finalData.get(1);

            aux1.add(new ReportList(new Long(0), data.get(0).getValue(), data.get(0).getName(), supervisor, (long) x));
            aux2.add(new ReportList(new Long(1), data.get(1).getValue(), data.get(1).getName(), supervisor, (long) x));

            finalData.set(0, aux1);
            finalData.set(1, aux2);

        } else {

            aux1.add(new ReportList(new Long(0), data.get(0).getValue(), data.get(0).getName(), supervisor, (long) x));
            aux2.add(new ReportList(new Long(1), data.get(1).getValue(), data.get(1).getName(), supervisor, (long) x));

            finalData.add(aux1);
            finalData.add(aux2);
        }


        return finalData;
    }

    private List<Object> completeSingleDataBySup(List<Object> finalData, List<SelectList> data, String supervisor, int x) {

        List<ReportList> aux1 = new ArrayList<>();

        if (finalData.size() > 0) {
            aux1 = (List<ReportList>) finalData.get(0);
            aux1.add(new ReportList(new Long(0), data.get(0).getValue(), data.get(0).getName(), supervisor, (long) x));
            finalData.set(0, aux1);
        } else {
            aux1.add(new ReportList(new Long(0), data.get(0).getValue(), data.get(0).getName(), supervisor, (long) x));
            finalData.add(aux1);
        }


        return finalData;
    }

    private List<Object> completeAgeData(List<Object> finalData, List<Object> data, String supervisor, int x, List<SelectList> ages) {


        int countNum = 0;


        for (int i = 0; i < ages.size(); i++) {
            List<ReportList> aux = new ArrayList<>();
            if (finalData.size() > i) {
                aux = (List<ReportList>) finalData.get(i);
            }
            if (data.size() == countNum) {
                aux.add(new ReportList(new Long(i), new Long(0), ages.get(i).getName(), supervisor, (long) x));

            } else {
                for (int j = countNum; j < data.size(); j++) {
                    Object[] obj = (Object[]) data.get(j);

                    if (ages.get(i).getId() == Long.parseLong(obj[0].toString())) {
                        aux.add(new ReportList(new Long(i), Long.parseLong(obj[2].toString()), ages.get(i).getName(), supervisor, (long) x));
                        countNum = j + 1;
                    } else {
                        aux.add(new ReportList(new Long(i), new Long(0), ages.get(i).getName(), supervisor, (long) x));
                    }
                    break;


                }
            }

            if (finalData.size() <= i) {
                finalData.add(aux);
            } else {
                finalData.set(i, aux);
            }

        }

        return finalData;

    }

    private List<Object> completeCrimeData(List<Object> finalData, List<SelectList> data, String supervisor, int x, List<SelectList> crimes) {


        int countNum = 0;


        for (int i = 0; i < crimes.size(); i++) {
            List<ReportList> aux = new ArrayList<>();
            if (finalData.size() > i) {
                aux = (List<ReportList>) finalData.get(i);
            }
            if (data.size() == countNum) {
                aux.add(new ReportList(new Long(i), new Long(0), crimes.get(i).getName(), supervisor, (long) x));

            } else {
                for (int j = countNum; j < data.size(); j++) {

                    if (crimes.get(i).getName().equals(data.get(j).getName())) {
                        aux.add(new ReportList(new Long(i), data.get(j).getValue(), crimes.get(i).getName(), supervisor, (long) x));
                        countNum = j + 1;
                    } else {
                        aux.add(new ReportList(new Long(i), new Long(0), crimes.get(i).getName(), supervisor, (long) x));
                    }
                    break;


                }
            }

            if (finalData.size() <= i) {
                finalData.add(aux);
            } else {
                finalData.set(i, aux);
            }

        }

        return finalData;

    }


}
