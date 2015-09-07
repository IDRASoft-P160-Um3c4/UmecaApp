package com.umeca.service.supervisiorManager;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.EventRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    @Override
    public List<SelectList> getData(int initDate, int endDate, String filter) {

        List<SelectList>data = new ArrayList<>();

        switch (filter){
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_A:
            return eventRepository.countCasesProsecuted(initDate, endDate);
            case Constants.REPORT_STATISTIC_MANAGER_REPORT_B:

                break;
        }
        return null;
    }
}
