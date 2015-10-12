package com.umeca.service.channelingManager;

import com.google.gson.Gson;
import com.umeca.model.catalog.DrugType;
import com.umeca.model.entities.supervisor.ContactData;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SelectOptsList;
import com.umeca.repository.EventRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.channelingManager.StatisticChannelingReportRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.StatisticSupervisorManagerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service("statisticChannelingReport")
public class StatisticChannelingReportServiceImpl implements StatisticChannelingReportService {
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

    @Autowired
    StatisticChannelingReportRepository statisticChannelingReportRepository;

    @Autowired
    ChannelingTypeRepository channelingTypeRepository;

    @Override
    public String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict, Long idSupervisor) {
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
            case Constants.REPORT_STATISTIC_CHANNELING_A:
                SelectList casesWithChanneling = new SelectList();
                casesWithChanneling.setName("Canalizado");
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            if (obj[0] != null) {
                                casesWithChanneling.setValue(Long.parseLong(obj[1].toString()));
                            }
                        }
                        data.add(casesWithChanneling);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            if (obj[0] != null) {
                                casesWithChanneling.setValue(Long.parseLong(obj[1].toString()));
                            } else {
                                casesWithChanneling.setValue(0L);
                            }
                        }
                        data.add(casesWithChanneling);
                        return gson.toJson(data);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> totalReport = new ArrayList<>();
                        List<ReportList> caseWithChannellingList = new ArrayList<>();
                        ReportList caseWithChannelling;


                        caseWithChannelling = new ReportList();
                        caseWithChannelling.setName("Canalizado");
                        caseWithChannelling.setX(0L);
                        caseWithChannelling.setUser("Sin supervisor");
                        caseWithChannelling.setY(0L);
                        caseWithChannelling.setId(0L);

                        lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingNotSupervisorAssigned(initDate + initTime, endDate + endTime, idDistrict);
                        for (int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            if (obj[0] != null) {
                                caseWithChannelling.setY(Long.parseLong(obj[1].toString()));
                            }
                        }
                        caseWithChannellingList.add(caseWithChannelling);

                        for (int i = 0; i < users.size(); i++) {
                            caseWithChannelling = new ReportList();
                            caseWithChannelling.setName("Canalizado");
                            caseWithChannelling.setX(new Long(i) + 1);
                            caseWithChannelling.setUser(users.get(i).getName());
                            caseWithChannelling.setY(0L);
                            caseWithChannelling.setId(0L);
                            lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingByDistrictAndOperator(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (obj[0] != null) {
                                    caseWithChannelling.setY(Long.parseLong(obj[1].toString()));
                                }
                            }
                            caseWithChannellingList.add(caseWithChannelling);
                        }
                        totalReport.add(caseWithChannellingList);
                        return gson.toJson(totalReport);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_SINGLE_OPERATOR:

                        if (idSupervisor == 0) {
                            lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingNotSupervisorAssigned(initDate + initTime, endDate + endTime, idDistrict);
                            for (int i = 0; i < lstObjects.size(); i++) {
                                Object[] obj = (Object[]) lstObjects.get(i);
                                if (obj[0] != null) {
                                    casesWithChanneling.setValue(Long.parseLong(obj[1].toString()));
                                } else {
                                    casesWithChanneling.setValue(0L);
                                }
                            }

                        } else {
                            lstObjects = statisticSupervisorManagerReportRepository.getNumberCasesWithChannelingByDistrictAndOperator(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                            for (int i = 0; i < lstObjects.size(); i++) {
                                Object[] obj = (Object[]) lstObjects.get(i);
                                if (obj[0] != null) {
                                    casesWithChanneling.setValue(Long.parseLong(obj[1].toString()));
                                } else {
                                    casesWithChanneling.setValue(0L);
                                }
                            }
                        }
                        data.add(casesWithChanneling);
                        return gson.toJson(data);
                }
            case Constants.REPORT_STATISTIC_CHANNELING_B:
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

                    case Constants.REPORT_STATISTIC_MANAGER_BY_SINGLE_OPERATOR:
                        lstObjects = statisticSupervisorManagerReportRepository.countInstitutionChannelingBySupervisor(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setName(obj[0].toString());
                            selectList.setSubName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));

                            data.add(selectList);
                        }
                        return gson.toJson(data);

                }
            case Constants.REPORT_STATISTIC_CHANNELING_C:
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticChannelingReportRepository.countChannelingTypeGeneral(initDate + initTime, endDate + endTime);
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
                        lstObjects = statisticChannelingReportRepository.countChannelingTypeByDistrict(initDate + initTime, endDate + endTime, idDistrict);
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
                            lstObjects = statisticChannelingReportRepository.countChannelingTypeBySupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
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

                    case Constants.REPORT_STATISTIC_MANAGER_BY_SINGLE_OPERATOR:
                        lstObjects = statisticChannelingReportRepository.countChannelingTypeBySupervisor(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            SelectList selectList = new SelectList();
                            selectList.setName(obj[0].toString());
                            selectList.setSubName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));

                            data.add(selectList);
                        }
                        return gson.toJson(data);

                }
            case Constants.REPORT_STATISTIC_CHANNELING_D:


                SelectList casesAssistance = new SelectList();
                SelectList casesAbsence = new SelectList();
                casesAssistance.setName("Asistencia");
                casesAbsence.setName("Inasistencia");

                casesAssistance.setValue(0L);
                casesAbsence.setValue(0L);


                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticChannelingReportRepository.countAssistanceAndAbsenceGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);

                            if (obj[0].toString().equals("Asistencia")) {
                                casesAssistance.setValue(Long.parseLong(obj[1].toString()));
                            } else {
                                casesAbsence.setValue(Long.parseLong(obj[1].toString()));
                            }

                        }

                        data.add(casesAssistance);
                        data.add(casesAbsence);
                        return gson.toJson(data);


                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticChannelingReportRepository.countAssistanceAndAbsenceByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);

                            if (obj[0].toString().equals("Asistencia")) {
                                casesAssistance.setValue(Long.parseLong(obj[1].toString()));
                            } else {
                                casesAbsence.setValue(Long.parseLong(obj[1].toString()));
                            }

                        }

                        data.add(casesAssistance);
                        data.add(casesAbsence);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();
                        List<List<ReportList>> totalReport = new ArrayList<>();
                        List<ReportList> drugsMaleList = new ArrayList<>();
                        List<ReportList> drugsFemaleList = new ArrayList<>();
                        ReportList assistance;
                        ReportList absence;
                        for (int i = 0; i < users.size(); i++) {
                            assistance = new ReportList();
                            absence = new ReportList();
                            absence.setName("Inasistencia");
                            absence.setX(new Long(i));
                            absence.setUser(users.get(i).getName());
                            absence.setY(0L);
                            absence.setId(1L);
                            assistance.setName("Asistencia");
                            assistance.setX(new Long(i));
                            assistance.setUser(users.get(i).getName());
                            assistance.setY(0L);
                            assistance.setId(0L);
                            lstObjects = statisticChannelingReportRepository.countAssistanceAndAbsenceBySupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (obj[0].toString().equals("Asistencia"))
                                    assistance.setY(Long.parseLong(obj[1].toString()));
                                else
                                    absence.setY(Long.parseLong(obj[1].toString()));
                            }
                            drugsMaleList.add(assistance);
                            drugsFemaleList.add(absence);
                        }
                        totalReport.add(drugsMaleList);
                        totalReport.add(drugsFemaleList);
                        return gson.toJson(totalReport);


                    case Constants.REPORT_STATISTIC_MANAGER_BY_SINGLE_OPERATOR:

                        lstObjects = statisticChannelingReportRepository.countAssistanceAndAbsenceBySupervisor(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);

                            if (obj[0].toString().equals("Asistencia")) {
                                casesAssistance.setValue(Long.parseLong(obj[1].toString()));
                            } else {
                                casesAbsence.setValue(Long.parseLong(obj[1].toString()));
                            }

                        }

                        data.add(casesAssistance);
                        data.add(casesAbsence);
                        return gson.toJson(data);


                }
            case Constants.REPORT_STATISTIC_CHANNELING_E:
                SelectList channelingFinished = new SelectList();
                channelingFinished.setName("Conclusión de la canalización");
                channelingFinished.setValue(0L);
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticChannelingReportRepository.countChannelingFinishedGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);

                            channelingFinished.setValue(Long.parseLong(obj[1].toString()));


                        }
                        data.add(channelingFinished);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticChannelingReportRepository.countChannelingFinishedByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);

                            channelingFinished.setValue(Long.parseLong(obj[1].toString()));


                        }
                        data.add(channelingFinished);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> totalReport = new ArrayList<>();
                        List<ReportList> channelingFinishedList = new ArrayList<>();
                        ReportList channelingFinishedReport;

                        for (int i = 0; i < users.size(); i++) {
                            channelingFinishedReport = new ReportList();

                            channelingFinishedReport.setName("Conclusión de la canalización");
                            channelingFinishedReport.setX(new Long(i));
                            channelingFinishedReport.setUser(users.get(i).getName());
                            channelingFinishedReport.setY(0L);
                            channelingFinishedReport.setId(1L);

                            lstObjects = statisticChannelingReportRepository.countChannelingFinishedBySupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);

                                channelingFinishedReport.setY(Long.parseLong(obj[1].toString()));

                            }
                            channelingFinishedList.add(channelingFinishedReport);

                        }
                        totalReport.add(channelingFinishedList);
                        return gson.toJson(totalReport);
                    case Constants.REPORT_STATISTIC_MANAGER_BY_SINGLE_OPERATOR:
                        lstObjects = statisticChannelingReportRepository.countChannelingFinishedBySupervisor(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            channelingFinished.setValue(Long.parseLong(obj[1].toString()));

                        }
                        data.add(channelingFinished);
                        return gson.toJson(data);
                }

            case Constants.REPORT_STATISTIC_CHANNELING_F:
                SelectList absence = new SelectList();
                absence.setName("Inasistencias");
                absence.setValue(0L);
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticChannelingReportRepository.countAbsenceChannelingGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            absence.setValue(Long.parseLong(obj[1].toString()));
                        }
                        data.add(absence);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticChannelingReportRepository.countAbsenceChannelingByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            absence.setValue(Long.parseLong(obj[1].toString()));
                        }
                        data.add(absence);
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> totalReport = new ArrayList<>();
                        List<ReportList> absenceList = new ArrayList<>();
                        ReportList absenceReport;

                        for (int i = 0; i < users.size(); i++) {
                            absenceReport = new ReportList();

                            absenceReport.setName("Inasistencias");
                            absenceReport.setX(new Long(i));
                            absenceReport.setUser(users.get(i).getName());
                            absenceReport.setY(0L);
                            absenceReport.setId(1L);

                            lstObjects = statisticChannelingReportRepository.countAbsenceChannelingBySupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);

                                absenceReport.setY(Long.parseLong(obj[1].toString()));

                            }
                            absenceList.add(absenceReport);

                        }
                        totalReport.add(absenceList);
                        return gson.toJson(totalReport);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_SINGLE_OPERATOR:
                        lstObjects = statisticChannelingReportRepository.countAbsenceChannelingBySupervisor(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            Object[] obj = (Object[]) lstObjects.get(i);
                            absence.setValue(Long.parseLong(obj[1].toString()));
                        }
                        data.add(absence);
                        return gson.toJson(data);
                }
            case Constants.REPORT_STATISTIC_CHANNELING_G:
                SelectList selectList;
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticChannelingReportRepository.countChannelingDesertedGeneral(initDate + initTime, endDate + endTime);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            selectList = new SelectList();
                            Object[] obj = (Object[]) lstObjects.get(i);

                            selectList.setName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));
                            data.add(selectList);
                        }
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticChannelingReportRepository.countChannelingDesertedByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            selectList = new SelectList();
                            Object[] obj = (Object[]) lstObjects.get(i);

                            selectList.setName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));
                            data.add(selectList);
                        }
                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < users.size(); i++) {
                            lstObjects = statisticChannelingReportRepository.countChannelingDesertedBySupervisor(initDate + initTime, endDate + endTime, idDistrict, users.get(i).getId());
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

                    case Constants.REPORT_STATISTIC_MANAGER_BY_SINGLE_OPERATOR:
                        lstObjects = statisticChannelingReportRepository.countChannelingDesertedBySupervisor(initDate + initTime, endDate + endTime, idDistrict, idSupervisor);
                        for (int i = 0; i < lstObjects.size(); i++) {
                            selectList = new SelectList();
                            Object[] obj = (Object[]) lstObjects.get(i);
                            selectList.setName(obj[0].toString());
                            selectList.setValue(Long.parseLong(obj[1].toString()));
                            data.add(selectList);
                        }
                        return gson.toJson(data);
                }
            case Constants.REPORT_STATISTIC_CHANNELING_H:
                // List<List<ReportList>> total = new ArrayList<>();
                List<SelectOptsList> channelingType = channelingTypeRepository.findNotObsolete();
                switch (reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<List<ReportList>> total = new ArrayList<>();

                        for (int i = 0; i < channelingType.size(); i++) {
                            lstObjects = statisticChannelingReportRepository.countChannelingDesertByType(initDate + initTime, endDate + endTime, channelingType.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if (i == 0) {
                                    total.add(new ArrayList<ReportList>());
                                    total.add(new ArrayList<ReportList>());
                                    total.add(new ArrayList<ReportList>());

                                }
                                ReportList reportList = new ReportList();
                                reportList.setId(0L);
                                reportList.setName("Bajas");
                                reportList.setUser(channelingType.get(i).getName());
                                reportList.setX(new Long(i));
                                reportList.setY(new Long(obj[1].toString()));
                                total.get(j).add(reportList);


                                lstObjects = statisticChannelingReportRepository.countChannelingFinishedByType(initDate + initTime, endDate + endTime, channelingType.get(i).getId());

                                Object[] obj2 = (Object[]) lstObjects.get(j);
                                //   if (i == 0) {
                                //       total.add(new ArrayList<ReportList>());
                                //   }
                                ReportList reportList2 = new ReportList();
                                reportList2.setId(1L);
                                reportList2.setName("Altas");
                                reportList2.setUser(channelingType.get(i).getName());
                                reportList2.setX(new Long(i));
                                reportList2.setY(new Long(obj2[1].toString()));
                                total.get(1).add(reportList2);



                        //        Object[] obj3 = (Object[]) lstObjects.get(j);
                                //   if (i == 0) {
                                //       total.add(new ArrayList<ReportList>());
                                //   }
                                ReportList reportList3 = new ReportList();
                                reportList3.setId(1L);
                                reportList3.setName("Inscritos");
                                reportList3.setUser(channelingType.get(i).getName());
                                reportList3.setX(new Long(i));
                                reportList3.setY(0L);
                                total.get(2).add(reportList3);


                            }

                        }
                        return gson.toJson(total);
                }


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
