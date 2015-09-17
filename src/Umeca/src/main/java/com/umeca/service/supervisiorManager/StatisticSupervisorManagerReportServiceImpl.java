package com.umeca.service.supervisiorManager;

import com.google.gson.Gson;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

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

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public String getData(String initDate, String endDate, String filter, Long idReportType, Long idDistrict){
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
                        lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangement();
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
                        lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangementAndDistrict(idDistrict);
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
                        List<List<ReportList>> supTotal = new ArrayList<>();
                        List<SelectList> arrangements = arrangementRepository.findAllNoObsolete();
                        for (int i = 0; i < arrangements.size(); i++) {
                            List<ReportList> arrangementList = new ArrayList<>();
                            for(int j = 0; j < users.size(); j ++){
                                lstObjects = statisticSupervisorManagerReportRepository.getArrangementByIdAndSupervisorId(users.get(j).getId(), arrangements.get(i).getId(),idDistrict);
                                ReportList arrangement = new ReportList();
                                arrangement.setName(arrangements.get(i).getName());
                                arrangement.setY(0L);
                                arrangement.setX(new Long(j));
                                arrangement.setUser(users.get(j).getName());
                                for (int k = 0; k < lstObjects.size(); k++) {
                                    Object[] obj = (Object[]) lstObjects.get(k);
                                    arrangement.setY(Long.parseLong(obj[1].toString()));
                                }
                                arrangementList.add(arrangement);
                            }
                            supTotal.add(arrangementList);
                        }
                        return gson.toJson(supTotal);

                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_D:
                SelectList drugsMale = new SelectList();
                SelectList drugsFemale = new SelectList();
                drugsMale.setName("Masculino");
                drugsFemale.setName("Femenino");
                drugsMale.setValue(0L);
                drugsFemale.setValue(0L);
                switch (reportTypeRepository.getReportCodeById(idReportType)){
                case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                    lstObjects = statisticSupervisorManagerReportRepository.getNumberOfPeopleByGenderWhoUseDrugsGeneral(initDate + initTime, endDate + endTime);
                    for(int i = 0; i < lstObjects.size(); i++) {
                        Object[] obj = (Object[]) lstObjects.get(i);
                        if(obj[0].toString().equals("1"))
                            drugsFemale.setValue(Long.parseLong(obj[1].toString()));
                        else
                            drugsMale.setValue(Long.parseLong(obj[1].toString()));
                    }
                    data.add(drugsMale);
                    data.add(drugsFemale);
                    return gson.toJson(data);

                case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                    lstObjects = statisticSupervisorManagerReportRepository.getNumberOfPeopleByGenderWhoUseDrugsByDistrict(initDate + initTime, endDate + endTime, idDistrict);
                    for(int i = 0; i < lstObjects.size(); i++) {
                        Object[] obj = (Object[]) lstObjects.get(i);
                        if(obj[0].toString().equals("1"))
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
                    for(int i = 0; i < users.size() ; i++){
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
                        for(int j = 0; j < lstObjects.size(); j++) {
                            Object[] obj = (Object[]) lstObjects.get(j);
                            if(obj[0].toString().equals("1"))
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
        }
        return null;
    }
}
