package com.umeca.service.managereval;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("statisticOperatorReportService")
public class StadoImpl implements Stado {
    @Autowired
    SharedUserService userService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    EventRepository eventRepository;



    @Override
    public List<SelectList> getData(int initDate, int endDate, String filter) {

        List<SelectList> data = new ArrayList<>();

        if (filter.equals(Constants.REPORT_OPERATOR_STATISTIC_A)) {
            data = eventRepository.countMeetingByReviewer(initDate, endDate);
        }

        return data;
    }


}
