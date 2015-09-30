package com.umeca.service.supervisiorManager;

import com.google.gson.Gson;
import com.umeca.model.catalog.DrugType;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SelectOptsList;
import com.umeca.repository.EventRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.ChannelingInstitutionNameRepository;
import com.umeca.repository.catalog.DrugTypeRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelingInstitutionNameRepository channelingInstitutionNameRepository;

    @Autowired
    DrugTypeRepository drugTypeRepository;


    @Override
    public String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict) {
        List<SelectList> data = new ArrayList<>();
        List<Object> lstObjects;
        Gson gson = new Gson();
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
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "getData", sharedUserService);
        }

        switch (filter) {
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_A:
                return gson.toJson(eventRepository.countCasesProsecuted(initId, endId));
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
                for (Long caseId : casesId) {
                    lstObjects = eventRepository.countEventsByCase(caseId, initId, endId);
                    for (int i = 0; i < lstObjects.size(); i++) {
                        Object[] obj = (Object[]) lstObjects.get(i);
                        SelectList selectList = new SelectList();
                        selectList.setName(obj[0].toString());
                        selectList.setValue(Long.parseLong(obj[1].toString()));

                        switch (obj[0].toString()) {
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
                return gson.toJson(data);

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_C:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangement(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setValue(Long.parseLong(obj[0].toString()));
                            selectList.setName(obj[1].toString());
                            selectList.setSubName(obj[1].toString());
                            data.add(selectList);
                        }
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangementAndDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setValue(Long.parseLong(obj[0].toString()));
                            selectList.setName(obj[1].toString());
                            selectList.setSubName(obj[1].toString());
                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < users.size(); i++) {
                            lstObjects = statisticSupervisorManagerReportRepository.getArrangementByIdAndSupervisorId(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (i == 0) {
                                    total.add(new ArrayList<ReportList>());
                                }
                                ReportList reportList = new ReportList();
                                reportList.setId(new Long(j));
                                reportList.setName(obj[0].toString());
                                reportList.setUser(users.get(i).getName());
                                reportList.setX(new Long(i));
                                reportList.setY(new Long(obj[1].toString()));
                                total.get(j).add(reportList);
                            }
                        }
                        return gson.toJson(total);

                    /*    lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangementAndDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setValue(Long.parseLong(obj[0].toString()));
                            selectList.setName(obj[1].toString());
                            selectList.setSubName(obj[1].toString());
                            data.add(selectList);
                        }
                        return gson.toJson(data); */

                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_D:
                SelectList drugsMale = new SelectList();
                SelectList drugsFemale = new SelectList();
                drugsMale.setName("Masculino");
                drugsFemale.setName("Femenino");
                drugsMale.setValue(0L);
                drugsFemale.setValue(0L);
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.getNumberOfPeopleByGenderWhoUseDrugsGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            if (obj[0].toString().equals("1"))
                                drugsFemale.setValue(Long.parseLong(obj[1].toString()));
                            else
                                drugsMale.setValue(Long.parseLong(obj[1].toString()));
                        }
                        data.add(drugsMale);
                        data.add(drugsFemale);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.getNumberOfPeopleByGenderWhoUseDrugsByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            if (obj[0].toString().equals("1"))
                                drugsFemale.setValue(Long.parseLong(obj[1].toString()));
                            else
                                drugsMale.setValue(Long.parseLong(obj[1].toString()));
                        }
                        data.add(drugsMale);
                        data.add(drugsFemale);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> totalReport = new ArrayList<>();
                        List<ReportList> drugsMaleList = new ArrayList<>();
                        List<ReportList> drugsFemaleList = new ArrayList<>();
                        ReportList drugMale;
                        ReportList drugFemale;
                        for (int i = 0; i < users.size(); i++) {
                            drugMale = new ReportList();
                            drugFemale = new ReportList();
                            drugFemale.setName("Femenino");
                            drugFemale.setX(new Long(i));
                            drugFemale.setUser(users.get(i).getName());
                            drugFemale.setY(0L);
                            drugMale.setName("Masculino");
                            drugMale.setX(new Long(i));
                            drugMale.setUser(users.get(i).getName());
                            drugMale.setY(0L);
                            lstObjects = statisticSupervisorManagerReportRepository.getNumberOfPeopleByGenderWhoUseDrugsByDistrictAndSupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (obj[0].toString().equals("1"))
                                    drugFemale.setY(Long.parseLong(obj[1].toString()));
                                else
                                    drugMale.setY(Long.parseLong(obj[1].toString()));
                            }
                            drugsMaleList.add(drugMale);
                            drugsFemaleList.add(drugFemale);
                        }
                        totalReport.add(drugsMaleList);
                        totalReport.add(drugsFemaleList);
                        return gson.toJson(totalReport);
                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_E:
                List<DrugType> drugs = drugTypeRepository.findNotObsoleteImportant();
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.countTypeofDrugs(initDateF, endDateF);
                        data = completeDrugsData(data, lstObjects, drugs);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.countTypeofDrugsByDistrict(initDateF, endDateF, idDistrict);
                        data = completeDrugsData(data, lstObjects, drugs);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        for (SelectList u : users) {
                            lstObjects = statisticSupervisorManagerReportRepository.countTypeofDrugsByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeData(dataEnd, lstObjects, u.getName(), x, drugs);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_F:
                SelectList casesWithChanneling = new SelectList();
                SelectList casesWithoutChanneling = new SelectList();
                casesWithChanneling.setName("Canalizado");
                casesWithoutChanneling.setName("No canalizado");
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            if (obj[0] != null) {
                                casesWithoutChanneling.setValue(Long.parseLong(obj[0].toString()));
                                casesWithChanneling.setValue(Long.parseLong(obj[1].toString()));
                            }
                        }
                        data.add(casesWithoutChanneling);
                        data.add(casesWithChanneling);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            if (obj[0] != null) {
                                casesWithoutChanneling.setValue(Long.parseLong(obj[0].toString()));
                                casesWithChanneling.setValue(Long.parseLong(obj[1].toString()));
                            }
                        }
                        data.add(casesWithoutChanneling);
                        data.add(casesWithChanneling);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> totalReport = new ArrayList<>();
                        List<ReportList> caseWithChannellingList = new ArrayList<>();
                        List<ReportList> caseWithoutChannellingList = new ArrayList<>();
                        ReportList caseWithChannelling;
                        ReportList caseWithoutChannelling;
                        for (int i = 0; i < users.size(); i++) {
                            caseWithChannelling = new ReportList();
                            caseWithoutChannelling = new ReportList();
                            caseWithChannelling.setName("Canalizado");
                            caseWithChannelling.setX(new Long(i));
                            caseWithChannelling.setUser(users.get(i).getName());
                            caseWithChannelling.setY(0L);
                            caseWithoutChannelling.setName("No Canalizado");
                            caseWithoutChannelling.setX(new Long(i));
                            caseWithoutChannelling.setUser(users.get(i).getName());
                            caseWithoutChannelling.setY(0L);
                            caseWithChannelling.setId(0L);
                            caseWithoutChannelling.setId(1l);
                            lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingByDistrictAndOperator(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (obj[0] != null) {
                                    caseWithoutChannelling.setY(Long.parseLong(obj[0].toString()));
                                    caseWithChannelling.setY(Long.parseLong(obj[1].toString()));
                                }
                            }
                            caseWithChannellingList.add(caseWithChannelling);
                            caseWithoutChannellingList.add(caseWithoutChannelling);
                        }
                        totalReport.add(caseWithChannellingList);
                        totalReport.add(caseWithoutChannellingList);
                        return gson.toJson(totalReport);
                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_G:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.countInstitutionChannelingGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setValue(Long.parseLong(obj[1].toString()));
                            selectList.setName(obj[0].toString());
                            selectList.setSubName(obj[0].toString());
                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.countInstitutionChannelingByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setName(obj[0].toString());
                            selectList.setSubName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));

                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < users.size(); i++) {
                            lstObjects = statisticSupervisorManagerReportRepository.countInstitutionChannelingBySupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (i == 0) {
                                    total.add(new ArrayList<ReportList>());
                                }
                                ReportList reportList = new ReportList();
                                reportList.setId(new Long(j));
                                reportList.setName(obj[0].toString());
                                reportList.setUser(users.get(i).getName());
                                reportList.setX(new Long(i));
                                reportList.setY(new Long(obj[1].toString()));
                                total.get(j).add(reportList);
                            }
                        }
                        return gson.toJson(total);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_H:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countJobs(initDateF, endDateF);
                        return gson.toJson(completeDoubleData(data, "Empleados", "Sin empleo"));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countJobsByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(completeDoubleData(data, "Empleados", "Sin empleo"));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        for (SelectList u : users) {
                            data = statisticSupervisorManagerReportRepository.countJobsByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeDataBySup(dataEnd, completeDoubleData(data, "Empleados", "Sin empleo"), u.getName(), x);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_I:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countClosedCases(initDateF, endDateF);
                        return gson.toJson(completeDoubleData(data, "Cerrados", "Vigentes"));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countClosedCasesByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(completeDoubleData(data, "Cerrados", "Vigentes"));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        List<SelectList> nullSupervisor = statisticSupervisorManagerReportRepository.countClosedCasesByDistrictAndSupervisorNull(initDateF, endDateF, idDistrict);
                        dataEnd = completeDataBySup(dataEnd, completeDoubleData(nullSupervisor, "Cerrados", "Vigentes"), "Sin supervisor", x);
                        x += 1;

                        for (SelectList u : users) {
                            data = statisticSupervisorManagerReportRepository.countClosedCasesByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeDataBySup(dataEnd, completeDoubleData(data, "Cerrados", "Vigentes"), u.getName(), x);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_J:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countClosedCasesTypeGeneral(initDateF, endDateF);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countClosedCasesTypeByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < users.size(); i++) {
                            lstObjects = statisticSupervisorManagerReportRepository.countClosedCasesTypeByOperator(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (i == 0) {
                                    total.add(new ArrayList<ReportList>());
                                }
                                ReportList reportList = new ReportList();
                                reportList.setId(new Long(j));
                                reportList.setName(obj[0].toString());
                                reportList.setUser(users.get(i).getName());
                                reportList.setX(new Long(i));
                                reportList.setY(new Long(obj[1].toString()));
                                total.get(j).add(reportList);
                            }
                        }
                        return gson.toJson(total);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_K:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countWarningMeasure(initDateF, endDateF);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countWarningMeasureByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        List<SelectList> nullSupervisor = statisticSupervisorManagerReportRepository.countWarningMeasureByDistrictAndSupervisorNull(initDateF, endDateF, idDistrict);
                        dataEnd = completeSingleDataBySup(dataEnd, nullSupervisor, "Sin supervisor", x);
                        x += 1;
                        for (SelectList u : users) {
                            data = statisticSupervisorManagerReportRepository.countWarningMeasureByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeSingleDataBySup(dataEnd, data, u.getName(), x);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_L:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countMCWithNonFulfillmentGeneral(initId, endId);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countMCWithNonFulfillByDistrict(initId, endId, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < users.size(); i++) {
                            data = statisticSupervisorManagerReportRepository.countMCWithNonFulfillBySupervisor(initId, endId, idDistrict, users.get(i).getId());
                            for (int j = 0; j < data.size(); j++) {
                                SelectList obj = data.get(j);
                                if (i == 0) {
                                    total.add(new ArrayList<ReportList>());
                                }
                                ReportList reportList = new ReportList();
                                reportList.setId(new Long(j));
                                reportList.setName(obj.getName());
                                reportList.setUser(users.get(i).getName());
                                reportList.setX(new Long(i));
                                reportList.setY(obj.getValue());
                                total.get(j).add(reportList);
                            }
                        }
                        return gson.toJson(total);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_M:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countSCPP(initDateF, endDateF);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countWarningSCPPByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        List<SelectList> nullSupervisor = statisticSupervisorManagerReportRepository.countSCPPByDistrictAndSupervisorNull(initDateF, endDateF, idDistrict);
                        dataEnd = completeSingleDataBySup(dataEnd, nullSupervisor, "Sin supervisor", x);
                        x += 1;
                        for (SelectList u : users) {
                            data = statisticSupervisorManagerReportRepository.countSCPPByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeSingleDataBySup(dataEnd, data, u.getName(), x);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_N:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countSCPPWithNonFulfillmentGeneral(initId, endId);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countSCPPWithNonFulfillByDistrict(initId, endId, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < users.size(); i++) {
                            data = statisticSupervisorManagerReportRepository.countSCPPWithNonFulfillBySupervisor(initId, endId, idDistrict, users.get(i).getId());
                            for (int j = 0; j < data.size(); j++) {
                                SelectList obj = data.get(j);
                                if (i == 0) {
                                    total.add(new ArrayList<ReportList>());
                                }
                                ReportList reportList = new ReportList();
                                reportList.setId(new Long(j));
                                reportList.setName(obj.getName());
                                reportList.setUser(users.get(i).getName());
                                reportList.setX(new Long(i));
                                reportList.setY(obj.getValue());
                                total.get(j).add(reportList);
                            }
                        }
                        return gson.toJson(total);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_O:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countByGender(initDateF, endDateF);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countByGenderAndDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(completeDoubleData(data, "Masculino", "Femenino"));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        for (SelectList u : users) {
                            data = statisticSupervisorManagerReportRepository.countByGenderAndDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeDataBySup(dataEnd, completeDoubleData(data, "Masculino", "Femenino"), u.getName(), x);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_P:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.countByAge(initDateF, endDateF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.countByAgeAndDistrict(initDateF, endDateF, idDistrict);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        List<SelectList> agesList = Arrays.asList(
                                new SelectList(new Long(0), "18 - 25"),
                                new SelectList(new Long(1), "26 - 30"),
                                new SelectList(new Long(2), "31 - 35"),
                                new SelectList(new Long(3), "36 - 40"),
                                new SelectList(new Long(4), "41 - 45"),
                                new SelectList(new Long(5), "46 - 50"),
                                new SelectList(new Long(6), "51 - 55"),
                                new SelectList(new Long(7), "56 - 60"),
                                new SelectList(new Long(8), "61 - 65"),
                                new SelectList(new Long(9), "66 - 70"),
                                new SelectList(new Long(10), "71 - 75"),
                                new SelectList(new Long(11), "76 - 80"),
                                new SelectList(new Long(12), "Más de 80")
                        );
                        for (SelectList u : users) {
                            lstObjects = statisticSupervisorManagerReportRepository.countByAgeAndDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeAgeData(dataEnd, lstObjects, u.getName(), x, agesList);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_Q:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countCrimes(initDateF, endDateF);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countCrimesByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<SelectList> crimes = statisticSupervisorManagerReportRepository.catalogCrimesByDistrict(initDateF, endDateF, idDistrict);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        List<SelectList> nullSupervisor = statisticSupervisorManagerReportRepository.countCrimesByDistrictAndSupervisorNull(initDateF, endDateF, idDistrict);
                        dataEnd = completeCrimeData(dataEnd, nullSupervisor, "Sin supervisor", x, crimes);
                        x += 1;
                        for (SelectList u : users) {
                            data = statisticSupervisorManagerReportRepository.countCrimesByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeCrimeData(dataEnd, data, u.getName(), x, crimes);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_R:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countImputedStudyingGeneral(initDateF, endDateF);
                        if (data.size() == 1) {
                            SelectList selectList = new SelectList();
                            selectList.setValue(0L);
                            if (data.get(0).getName().equals("Estudia"))
                                selectList.setName("No estudia");
                            else
                                selectList.setName("Estudia");
                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countImputedStudyingByDistrict(initDateF, endDateF, idDistrict);
                        if (data.size() == 1) {
                            SelectList selectList = new SelectList();
                            selectList.setValue(0L);
                            if (data.get(0).getName().equals("Estudia"))
                                selectList.setName("No estudia");
                            else
                                selectList.setName("Estudia");
                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();
                        List<ReportList> studyingList = new ArrayList<>();
                        List<ReportList> notStudyingList = new ArrayList<>();
                        ReportList studying;
                        ReportList notStudying;
                        for (int i = 0; i < users.size(); i++) {
                            studying = new ReportList();
                            notStudying = new ReportList();
                            notStudying.setId(0L);
                            notStudying.setName("No estudia");
                            notStudying.setUser(users.get(i).getName());
                            notStudying.setX(new Long(i));
                            notStudying.setY(0L);
                            studying.setId(1L);
                            studying.setName("Estudia");
                            studying.setUser(users.get(i).getName());
                            studying.setX(new Long(i));
                            studying.setY(0L);

                            data = statisticSupervisorManagerReportRepository.countImputedStudyingBySupervisor(initDateF, endDateF, idDistrict, users.get(i).getId());
                            for (int j = 0; j < data.size(); j++) {

                                if (data.get(0).getName().equals("Estudia"))
                                    studying.setY(data.get(0).getValue());
                                else
                                    notStudying.setY(data.get(0).getValue());

                            }
                            studyingList.add(studying);
                            notStudyingList.add(notStudying);

                        }
                        total.add(notStudyingList);
                        total.add(studyingList);
                        return gson.toJson(total);

                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_S:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.countCasesBySchoolGradeGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setName(obj[0].toString());
                            selectList.setSubName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));

                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.countCasesBySchoolGradeAndDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setName(obj[0].toString());
                            selectList.setSubName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));

                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < users.size(); i++) {
                            lstObjects = statisticSupervisorManagerReportRepository.countCasesBySchoolGradeAndSupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (i == 0) {
                                    total.add(new ArrayList<ReportList>());
                                }
                                ReportList reportList = new ReportList();
                                reportList.setId(new Long(j));
                                reportList.setName(obj[0].toString());
                                reportList.setUser(users.get(i).getName());
                                reportList.setX(new Long(i));
                                reportList.setY(new Long(obj[1].toString()));
                                total.get(j).add(reportList);
                            }
                        }
                        return gson.toJson(total);
                }

            case Constants.REPORT_STATISTIC_MANAGER_REPORT_T:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.countAnyChangesInArrangementType(initDateF, endDateF);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
                        }
                        return gson.toJson(completeDoubleData(data, "Cambio de MC a SCPP", "Cambio de SCPP a MC"));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.countAnyChangesInArrangementTypeByDistrict(initDateF, endDateF, idDistrict);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            data.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
                        }
                        return gson.toJson(completeDoubleData(data, "Cambio de MC a SCPP", "Cambio de SCPP a MC"));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        List<Object> nullSupervisor = statisticSupervisorManagerReportRepository.countAnyChangesInArrangementTypeByDistrictAndSupervisorNull(initDateF, endDateF, idDistrict);
                        for (int j = 0; j < nullSupervisor.size(); j++) {
                            Object[] obj = (Object[]) nullSupervisor.get(j);
                            data.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
                        }
                        dataEnd = completeDataBySup(dataEnd, completeDoubleData(data, "Cambio de MC a SCPP", "Cambio de SCPP a MC"), "Sin supervisor", x);
                        x += 1;
                        for (SelectList u : users) {
                            lstObjects = statisticSupervisorManagerReportRepository.countAnyChangesInArrangementTypeByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                data.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
                            }
                            dataEnd = completeDataBySup(dataEnd, completeDoubleData(data, "Cambio de MC a SCPP", "Cambio de SCPP a MC"), u.getName(), x);
                            x += 1;
                            data = new ArrayList<>();
                        }
                        return gson.toJson(dataEnd);


                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_U:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countSubstracted(initDateF, endDateF);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countSubstractedByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        List<SelectList> nullSupervisor = statisticSupervisorManagerReportRepository.countSubstractedByDistrictAndSupervisorNull(initDateF, endDateF, idDistrict);
                        dataEnd = completeSingleDataBySup(dataEnd, nullSupervisor, "Sin supervisor", x);
                        x += 1;
                        for (SelectList u : users) {
                            data = statisticSupervisorManagerReportRepository.countSubstractedByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeSingleDataBySup(dataEnd, data, u.getName(), x);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);


                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_V:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countSuspensionOfSupervisionForPreventivePrisonGeneral(initDateF, endDateF);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countSuspensionOfSupervisionForPreventivePrisonByDistrict(initDateF, endDateF, idDistrict);
                        if (data.size() == 0) {
                            SelectList selectList = new SelectList();
                            selectList.setName("Supensión preventiva en una carpeta diversa");
                            selectList.setValue(0L);
                            data.add(selectList);
                        }
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        break;
                }

        }

        return null;
    }


    public String getReportFilteredBySupervisor(String filterSelected, String initDate, String endDate, Long idDistrict, Long idSupervisor) {
        Gson gson = new Gson();
        List<SelectList> data = new ArrayList<>();
        List<Object> lstObjects;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";


        switch (filterSelected) {
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_C:

                if(idSupervisor == 0){
                    lstObjects = statisticSupervisorManagerReportRepository.getArrangementByNotAssignetSupervisor(initDate + initTime, endDate + endTime, idDistrict);
                }
                else{
                    lstObjects = statisticSupervisorManagerReportRepository.getArrangementByIdAndSupervisorId(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                }


                for (int i = 0; i < lstObjects.size(); i++) {
                    Object[] obj = (Object[]) lstObjects.get(i);
                    SelectList selectList = new SelectList();
                    selectList.setName(obj[0].toString());
                    selectList.setSubName(obj[0].toString());
                    selectList.setValue(Long.parseLong(obj[1].toString()));
                    data.add(selectList);
                }
                break;


        }
        return gson.toJson(data);

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

                    if (drugs.get(i).getId() == Long.parseLong(obj[2].toString())) {
                        finalData.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
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
