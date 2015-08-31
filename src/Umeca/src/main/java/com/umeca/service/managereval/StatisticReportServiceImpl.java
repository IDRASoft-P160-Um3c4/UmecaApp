package com.umeca.service.managereval;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service("statisticReportService")
public class StatisticReportServiceImpl implements StatisticReportService {
    @Autowired
    SharedUserService userService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    EventRepository eventRepository;


    @Override
    public List<SelectList> getData(int initDate, int endDate, String filter) {

        List<SelectList> data = null;


        //Reportes!!
        if (filter.equals(Constants.REPORT_STATISTIC_A)) {
            data = eventRepository.countCasesByEventOnDate(initDate, endDate);
            data = completeData(data);
        }
        else if(filter.equals(Constants.REPORT_STATISTIC_B)){

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
                if (isIt.get(i).equals(false) && i == 0){
                    dataOk.set(0, new SelectList(new Long(i), new Long(0), Constants.EVENT_CASE_OPINION));
                }
                else if(isIt.get(i).equals(false) && i == 1){
                    dataOk.set(1, new SelectList(new Long(i), new Long(0), Constants.EVENT_CASE_REPORT));
                }
                else if(isIt.get(i).equals(false) && i == 2){
                    dataOk.set(2, new SelectList(new Long(i), new Long(0), Constants.EVENT_INTERVIEW_DECLINED));
                }
                else if(isIt.get(i).equals(false) && i == 3){
                    dataOk.set(3, new SelectList(new Long(i), new Long(0), Constants.EVENT_ONLY_INTERVIEW));
                }
            }
        }

        return dataOk;
    }
}
