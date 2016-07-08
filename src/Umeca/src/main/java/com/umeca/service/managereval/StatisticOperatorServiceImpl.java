package com.umeca.service.managereval;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("StatisticOperatorService")
public class StatisticOperatorServiceImpl implements StatisticOperatorService {
    @Autowired
    SharedUserService userService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;



    @Override
    public List<SelectList> getData(int initDate, int endDate, String filter) {

        List<SelectList> data = new ArrayList<>();

        if (filter.equals(Constants.REPORT_OPERATOR_STATISTIC_A)) {
            List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_REVIEWER);
            List<SelectList> temp = eventRepository.countMeetingByReviewer(initDate, endDate);
            data = completeEvaluatorsData(data, temp, users);
        }

        return data;
    }



    public List<Object> getDataC(int initDate, int endDate, String filter) {

        List<Object> data = new ArrayList<>();

        if (filter.equals(Constants.REPORT_OPERATOR_STATISTIC_B)) {
            List<SelectList> evaluators = eventRepository.evaluators();
            List<ReportList> evaluatorInfo = new ArrayList<>();
            int x = 0;
            for(SelectList e : evaluators){
                evaluatorInfo = eventRepository.countMeetingByEventAndReviewer(initDate, endDate, e.getId());
                data = completeDataOP(data, evaluatorInfo, e.getName(), x);
                x += 1;
            }
        }

        return data;
    }



    private List<Object> completeDataOP(List<Object> finalData, List<ReportList> data, String reviewer, int x) {
        List<ReportList> dataOk = Arrays.asList(new ReportList(), new ReportList(), new ReportList(), new ReportList());

        List<Boolean> isIt = Arrays.asList(false, false, false, false);

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getDescription().equals(Constants.EVENT_INTERVIEW_DECLINED)) {
                isIt.set(2, true);
                data.get(i).setUser(reviewer);
                data.get(i).setX((long) x);
                dataOk.set(2, data.get(i));
            } else if (data.get(i).getDescription().equals(Constants.EVENT_CASE_REPORT)) {
                isIt.set(1, true);
                data.get(i).setUser(reviewer);
                data.get(i).setX((long) x);
                dataOk.set(1, data.get(i));
            } else if (data.get(i).getDescription().equals(Constants.EVENT_CASE_OPINION)) {
                isIt.set(0, true);
                data.get(i).setUser(reviewer);
                data.get(i).setX((long) x);
                dataOk.set(0, data.get(i));
            } else if (data.get(i).getDescription().equals(Constants.EVENT_ONLY_INTERVIEW)) {
                isIt.set(3, true);
                data.get(i).setUser(reviewer);
                data.get(i).setX((long) x);
                dataOk.set(3, data.get(i));
            }
        }
        if (isIt.contains(false)) {
            for (int i = 0; i < isIt.size(); i++) {
                if (isIt.get(i).equals(false) && i == 0) {
                    dataOk.set(0, new ReportList(new Long(i), new Long(0), Constants.EVENT_CASE_OPINION, reviewer, (long) x));
                } else if (isIt.get(i).equals(false) && i == 1) {
                    dataOk.set(1, new ReportList(new Long(i), new Long(0), Constants.EVENT_CASE_REPORT, reviewer, (long) x));
                } else if (isIt.get(i).equals(false) && i == 2) {
                    dataOk.set(2, new ReportList(new Long(i), new Long(0), Constants.EVENT_INTERVIEW_DECLINED, reviewer, (long) x));
                } else if (isIt.get(i).equals(false) && i == 3) {
                    dataOk.set(3, new ReportList(new Long(i), new Long(0), Constants.EVENT_ONLY_INTERVIEW, reviewer, (long) x));
                }
            }
        }

        List<ReportList> first = new ArrayList<>();
        List<ReportList> second = new ArrayList<>();
        List<ReportList> third = new ArrayList<>();
        List<ReportList> fourth = new ArrayList<>();

        if(finalData.size() == dataOk.size()){
            first = (List<ReportList>) finalData.get(0);
            second = (List<ReportList>) finalData.get(1);
            third = (List<ReportList>) finalData.get(2);
            fourth = (List<ReportList>) finalData.get(3);
        }

        first.add(dataOk.get(0));
        second.add(dataOk.get(1));
        third.add(dataOk.get(2));
        fourth.add(dataOk.get(3));


        if(finalData.size() == 0){
            finalData.add(first);
            finalData.add(second);
            finalData.add(third);
            finalData.add(fourth);
        }else{
            finalData.set(0, first);
            finalData.set(1, second);
            finalData.set(2, third);
            finalData.set(3, fourth);
        }

        return finalData;
    }

    private List<SelectList> completeEvaluatorsData(List<SelectList> finalData, List<SelectList> data, List<SelectList> users) {

        for(int i=0;i<users.size(); i++) {
            finalData.add(new SelectList(users.get(i).getId(),new Long(0L),users.get(i).getDescription()));
        }


        for(int i=0;i<finalData.size(); i++) {
            for(int j=0;j<data.size();j++){
                if(finalData.get(i).getId().equals(data.get(j).getId())){
                    finalData.get(i).setValue(data.get(j).getValue());
                }
            }
        }

        return finalData;

    }


}
