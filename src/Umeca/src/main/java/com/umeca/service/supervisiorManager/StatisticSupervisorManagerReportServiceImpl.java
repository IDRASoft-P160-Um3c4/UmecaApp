package com.umeca.service.supervisiorManager;

import com.google.gson.Gson;
import com.umeca.model.catalog.DrugType;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.catalog.DrugTypeRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
            logException.Write(e, this.getClass(), "save", sharedUserService);
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
                       // List<List<SelectList>> supTotal = new ArrayList<>();
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
                                    // arrangement.setName(arrangements.get(i).getName());

                                    arrangement.setY(Long.parseLong(obj[1].toString()));
                                }
                                arrangementList.add(arrangement);
                            }
                            supTotal.add(arrangementList);
                        }
                        return gson.toJson(supTotal);

                }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_D:
                switch (reportTypeRepository.getReportCodeById(idReportType)){
                case Constants.REPORT_STATISTIC_MANAGER_GENERAL:

                    break;

                case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:

                    break;

                case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:

                    break;
            }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_E:
                switch (reportTypeRepository.getReportCodeById(idReportType)){
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        lstObjects = statisticSupervisorManagerReportRepository.countTypeofDrugs(initDateF, endDateF);
                        for (Object object : lstObjects) {
                            Object[] aux = (Object[]) object;
                            data.add(new SelectList(aux[0].toString(), Long.parseLong(aux[1].toString())));
                        }

                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        lstObjects = statisticSupervisorManagerReportRepository.countTypeofDrugsByDistrict(initDateF, endDateF, idDistrict);
                        for (Object object : lstObjects) {
                            Object[] aux = (Object[]) object;
                            data.add(new SelectList(aux[0].toString(), Long.parseLong(aux[1].toString())));
                        }

                        return gson.toJson(data);

                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        List<DrugType> drugs =  drugTypeRepository.findNotObsoleteImportant();
                        int x = 0;
                        for(SelectList u : users){
                            lstObjects = statisticSupervisorManagerReportRepository.countTypeofDrugsByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeData(dataEnd, lstObjects, u.getName(), x, drugs);
                            x += 1;
                        }
                        return gson.toJson(dataEnd);
                }
        }
        return null;
    }

    private List<Object> completeData(List<Object> finalData, List<Object> data, String supervisor, int x, List<DrugType> drugs) {


        int countNum = 0;


        for(int i = 0; i < drugs.size(); i++) {
            List<ReportList> aux = new ArrayList<>();
            if(finalData.size() > i){
                aux = (List<ReportList>) finalData.get(i);
            }
            if(data.size() == 0){
                aux.add(new ReportList(new Long(x), new Long(0), drugs.get(i).getName(), supervisor, (long) x));

            }else{
                for(int j = countNum; j < data.size(); j++){
                    Object[] obj = (Object[]) data.get(j);

                    if(drugs.get(i).getId() == Long.parseLong(obj[0].toString())){
                        aux.add(new ReportList(new Long(x), Long.parseLong(obj[2].toString()), drugs.get(i).getName(), supervisor, (long) x));
                    }else {
                        aux.add(new ReportList(new Long(x), new Long(0), drugs.get(i).getName(), supervisor, (long) x));
                    }
                    countNum = j + 1;
                    break;


                }
            }

            if(finalData.size() <= i){
                finalData.add(aux);
            }else{
                finalData.set(i, aux);
            }

        }

        return finalData;

    }
}
