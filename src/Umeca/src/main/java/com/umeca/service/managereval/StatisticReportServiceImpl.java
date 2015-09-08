package com.umeca.service.managereval;

import com.umeca.model.catalog.District;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("statisticReportService")
public class StatisticReportServiceImpl implements StatisticReportService {
    @Autowired
    SharedUserService userService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    EventRepository eventRepository;


    @Autowired
    DistrictRepository districtRepository;


    @Override
    public List<SelectList> getData(int initDate, int endDate, String filter) {

        List<SelectList> data = new ArrayList<>();


        //Reportes!!
        if (filter.equals(Constants.REPORT_STATISTIC_A)) {
            data = eventRepository.countCasesByEventOnDate(initDate, endDate);
            data = completeData(data);
        } else if (filter.equals(Constants.REPORT_STATISTIC_B)) {
            data = eventRepository.countMeetingByGender(initDate, endDate);
        } else if (filter.equals(Constants.REPORT_STATISTIC_C)) {
            Long dataA = eventRepository.countCasesWithDrugsOnDate(initDate, endDate);
            Long dataB = eventRepository.countCasesWithDrugsByOpinionOnDate(initDate, endDate);

            Long dataAC = eventRepository.countAllCasesForDrugsOnDate(initDate, endDate);
            Long dataBC = eventRepository.countAllCasesWithDrugsByOpinionOnDate(initDate, endDate);

            Long useDrugs = dataA + dataB;
            Long notUseDrugs = (dataAC + dataBC) - useDrugs;

            SelectList selectListA = new SelectList(new Long(0), notUseDrugs, "No consume");
            SelectList selectListB = new SelectList(new Long(1), useDrugs, "Consume");

            data.add(selectListA);
            data.add(selectListB);

        } else if (filter.equals(Constants.REPORT_STATISTIC_D)) {
            List<SelectList> dataA = eventRepository.countCasesWithDrugsOnDateByGender(initDate, endDate);
            List<SelectList> dataB = eventRepository.countCasesWithDrugsByOpinionOnDateByGender(initDate, endDate);
            data.add(new SelectList(Constants.GENDER_MALE, dataA.get(0).getValue() + dataB.get(0).getValue()));
            data.add(new SelectList(Constants.GENDER_FEMALE, dataA.get(1).getValue() + dataB.get(1).getValue()));

        } else if (filter.equals(Constants.REPORT_STATISTIC_E)) {
            List<SelectList> dataA = eventRepository.countTypeofDrugs(initDate, endDate);
            List<SelectList> dataB = eventRepository.countTypeOfDrugsOpinion(initDate, endDate);

            for(SelectList selectList : dataA){
                data.add(selectList);
            }

            for(SelectList selectList : dataB){
                for(int i = 0; i < data.size(); i++ ){
                    if(selectList.getSubName().equals(data.get(i).getSubName())){
                        data.get(i).setValue(data.get(i).getValue() + selectList.getValue());
                        break;
                    }
                    else{
                        data.add(selectList);
                        break;
                    }

                }
            }

        } else if (filter.equals(Constants.REPORT_STATISTIC_F)) {
            Long extraData = eventRepository.countCasesByOpinionOnDate(initDate, endDate);
            SelectList selectListExtra = new SelectList(new Long(-1111), extraData, "Total entrevista con Opinión");
            data.add(selectListExtra);

            Long dataA = eventRepository.countSourcesByOpinionOnDate(initDate, endDate);
            SelectList selectListA = new SelectList(new Long(0), dataA, "Fuentes");
            data.add(selectListA);
        } else if (filter.equals(Constants.REPORT_STATISTIC_G)) {
            initDate = (initDate / 10000) * 10000;
            endDate = (endDate / 10000) * 10001;

            List<Object> temp = eventRepository.countCasesByYear(initDate, endDate);
            for (Object object : temp) {
                Object[] aux = (Object[]) object;
                data.add(new SelectList(aux[0].toString(), Long.parseLong(aux[1].toString())));
            }
        } else if (filter.equals(Constants.REPORT_STATISTIC_H)) {
            data = eventRepository.countMeetingByDistrict(initDate, endDate);
            data = completeDistrictData(data);
        }

        return data;
    }


    private List<SelectList> completeData(List<SelectList> data) {

        List<SelectList> dataOk = Arrays.asList(new SelectList(), new SelectList(), new SelectList(), new SelectList());
        List<Boolean> isIt = Arrays.asList(false, false, false, false);

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getDescription().equals(Constants.EVENT_INTERVIEW_DECLINED)) {
                isIt.set(2, true);
                dataOk.set(2, data.get(i));
            } else if (data.get(i).getDescription().equals(Constants.EVENT_CASE_REPORT)) {
                isIt.set(1, true);
                dataOk.set(1, data.get(i));
            } else if (data.get(i).getDescription().equals(Constants.EVENT_CASE_OPINION)) {
                isIt.set(0, true);
                dataOk.set(0, data.get(i));
            } else if (data.get(i).getDescription().equals(Constants.EVENT_ONLY_INTERVIEW)) {
                isIt.set(3, true);
                dataOk.set(3, data.get(i));
            }
        }
        if (isIt.contains(false)) {
            for (int i = 0; i < isIt.size(); i++) {
                if (isIt.get(i).equals(false) && i == 0) {
                    dataOk.set(0, new SelectList(new Long(i), new Long(0), Constants.EVENT_CASE_OPINION));
                } else if (isIt.get(i).equals(false) && i == 1) {
                    dataOk.set(1, new SelectList(new Long(i), new Long(0), Constants.EVENT_CASE_REPORT));
                } else if (isIt.get(i).equals(false) && i == 2) {
                    dataOk.set(2, new SelectList(new Long(i), new Long(0), Constants.EVENT_INTERVIEW_DECLINED));
                } else if (isIt.get(i).equals(false) && i == 3) {
                    dataOk.set(3, new SelectList(new Long(i), new Long(0), Constants.EVENT_ONLY_INTERVIEW));
                }
            }
        }

        return dataOk;
    }


    private List<SelectList> completeDistrictData(List<SelectList> data){
        List<SelectList> districts = districtRepository.findNoObsolete();

        if(districts.size() != data.size()){
            List<Boolean> isIt = new ArrayList<Boolean>(Arrays.asList(new Boolean[districts.size()]));
            Collections.fill(isIt, Boolean.FALSE);


            for (int i = 0; i < data.size(); i++){
                districts.get((int) (data.get(i).getId() - 1)).setValue(data.get(i).getValue());
                isIt.set((int) (data.get(i).getId() - 1), true);
            }


            for (int i = 0; i < districts.size(); i++) {
                if(isIt.get(i).equals(false)){
                    districts.get(i).setValue(new Long(0));
                }
            }
            return districts;

        }
        return data;
    }
}
