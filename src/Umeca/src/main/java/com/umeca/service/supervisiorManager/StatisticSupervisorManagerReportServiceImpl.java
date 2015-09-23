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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
    ChannelingInstitutionNameRepository channelingInstitutionNameRepository;

    @Autowired
    DrugTypeRepository drugTypeRepository;


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
                        lstObjects = statisticSupervisorManagerReportRepository.getCountCasesByArrangement(initDate + initTime, endDate + endTime);
                        for(int i = 0; i < lstObjects.size(); i++) {
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
                        List<List<ReportList>> supTotal = new ArrayList<>();
                        List<SelectList> arrangements = arrangementRepository.findAllNoObsolete();
                        for (int i = 0; i < arrangements.size(); i++) {
                            List<ReportList> arrangementList = new ArrayList<>();
                            for(int j = 0; j < users.size(); j ++){
                                lstObjects = statisticSupervisorManagerReportRepository.getArrangementByIdAndSupervisorId(initDate + initTime, endDate + endTime, users.get(j).getId(), arrangements.get(i).getId(),idDistrict);
                                ReportList arrangement = new ReportList();
                                arrangement.setName(arrangements.get(i).getName());
                                arrangement.setY(0L);
                                arrangement.setX(new Long(j));
                                arrangement.setId(new Long(i));
                                arrangement.setUser(users.get(j).getName());
                                for (int k = 0; k < lstObjects.size(); k++) {
                                    Object[] obj = (Object[]) lstObjects.get(k);
                                    arrangement.setY(Long.parseLong(obj[0].toString()));
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
                            if(obj[0].toString().equals("1")) {
                                drugFemale.setY(Long.parseLong(obj[1].toString()));
                                drugFemale.setId(1L);
                            }
                            else {
                                drugMale.setY(Long.parseLong(obj[1].toString()));
                                drugMale.setId(0L);
                            }
                        }
                        drugsMaleList.add(drugMale);
                        drugsFemaleList.add(drugFemale);
                    }
                    totalReport.add(drugsMaleList);
                    totalReport.add(drugsFemaleList);
                    return gson.toJson(totalReport);
            }
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_E:
                List<DrugType> drugs =  drugTypeRepository.findNotObsoleteImportant();
                switch (reportTypeRepository.getReportCodeById(idReportType)){
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
                        for(SelectList u : users){
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
                switch(reportTypeRepository.getReportCodeById(idReportType)){
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
                            if(obj[0] != null) {
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
                   //     List<SelectOptsList> institutions = channelingInstitutionNameRepository.findNotObsolete();
                        List<List<ReportList>> total = new ArrayList<>();
                   //     List<ReportList> institutionList;

                        for(int i = 0; i < users.size(); i++){
                            lstObjects = statisticSupervisorManagerReportRepository.countInstitutionChannelingBySupervisor(initDate + initTime, endDate + endTime, idDistrict,users.get(i).getId());
                            for (int j = 0; j < lstObjects.size(); j++) {
                                Object[] obj = (Object[]) lstObjects.get(j);
                                if(i == 0){
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
                switch(reportTypeRepository.getReportCodeById(idReportType)) {
                    case Constants.REPORT_STATISTIC_MANAGER_GENERAL:
                        data = statisticSupervisorManagerReportRepository.countJobs(initDateF, endDateF);
                        return gson.toJson(completeEmployment(data));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_DISTRICT:
                        data = statisticSupervisorManagerReportRepository.countJobsByDistrict(initDateF, endDateF, idDistrict);
                        return gson.toJson(completeEmployment(data));
                    case Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR:
                        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                        List<Object> dataEnd = new ArrayList<>();
                        int x = 0;
                        for(SelectList u : users){
                            data = statisticSupervisorManagerReportRepository.countJobsByDistrictAndSupervisor(initDateF, endDateF, idDistrict, u.getId());
                            dataEnd = completeEmploymentDataBySup(dataEnd, completeEmployment(data), u.getName(), x);
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
            if(data.size() == countNum){
                aux.add(new ReportList(new Long(i), new Long(0), drugs.get(i).getName(), supervisor, (long) x));

            }else{
                for(int j = countNum; j < data.size(); j++){
                    Object[] obj = (Object[]) data.get(j);

                    if(drugs.get(i).getId() == Long.parseLong(obj[0].toString())){
                        aux.add(new ReportList(new Long(i), Long.parseLong(obj[2].toString()), drugs.get(i).getName(), supervisor, (long) x));
                        countNum = j + 1;
                    }else {
                        aux.add(new ReportList(new Long(i), new Long(0), drugs.get(i).getName(), supervisor, (long) x));
                    }
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


    private List<SelectList> completeDrugsData(List<SelectList> finalData, List<Object> data, List<DrugType> drugs) {

        int countNum = 0;


        for(int i = 0; i < drugs.size(); i++) {

            if(data.size() == countNum){
                finalData.add(new SelectList(drugs.get(i).getName(), new Long(0)));

            }else{
                for(int j = countNum; j < data.size(); j++){
                    Object[] obj = (Object[]) data.get(j);

                    if(drugs.get(i).getId() == Long.parseLong(obj[2].toString())){
                        finalData.add(new SelectList(obj[0].toString(), Long.parseLong(obj[1].toString())));
                        countNum = j + 1;
                    }else {
                        finalData.add(new SelectList(drugs.get(i).getName(), new Long(0)));
                    }
                    break;


                }
            }

        }

        return finalData;

    }


    private List<SelectList> completeEmployment(List<SelectList> data) {

        if(data.size() > 0){
            if(!data.get(0).getName().equals("Empleado")){
                SelectList x = data.get(0);
                data.set(0, new SelectList("Empleado", new Long(0)));
                data.add(x);
            }
            else if(data.size() < 2)
                data.add(new SelectList("Sin empleo", new Long(0)));
        }
        else{
            data.add(new SelectList("Empleado", new Long(0)));
            data.add(new SelectList("Sin empleo", new Long(0)));
        }

        return data;

    }


    private List<Object> completeEmploymentDataBySup(List<Object> finalData, List<SelectList> data, String supervisor, int x) {



        List<ReportList> aux1 = new ArrayList<>();
        List<ReportList> aux2 = new ArrayList<>();

        if(finalData.size() > 0){
            aux1 = (List<ReportList>) finalData.get(0);
            aux2 = (List<ReportList>) finalData.get(1);

            aux1.add(new ReportList(new Long(0), data.get(0).getValue(), data.get(0).getName(), supervisor, (long) x));
            aux2.add(new ReportList(new Long(1), data.get(1).getValue(), data.get(1).getName(), supervisor, (long) x));

            finalData.set(0, aux1);
            finalData.set(1, aux2);

        }else {

            aux1.add(new ReportList(new Long(0), data.get(0).getValue(), data.get(0).getName(), supervisor, (long) x));
            aux2.add(new ReportList(new Long(1), data.get(1).getValue(), data.get(1).getName(), supervisor, (long) x));

            finalData.add(aux1);
            finalData.add(aux2);
        }


        return finalData;
    }


}
