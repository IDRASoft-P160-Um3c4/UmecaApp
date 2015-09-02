package com.umeca.controller.managereval;

import com.google.gson.Gson;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.StatisticReportTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.managereval.StatisticReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StatisticReportController {


    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    StatisticReportTypeRepository statisticReportTypeRepository;
    @Autowired
    private
    StatisticReportService statisticReportService;


    @RequestMapping(value = "/managereval/statisticReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/managereval/statisticReport/index");
        Gson gson = new Gson();
        List<SelectList> lstEvaAct = statisticReportTypeRepository.getAllNoObsolete();
        model.addObject("lstFilter", gson.toJson(lstEvaAct));
        return model;
    }



    @RequestMapping(value = "/managereval/statisticReport/showReport", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, String filterSelected) {
        ModelAndView model = new ModelAndView("/managereval/statisticReport/showReport");

            Long total = Long.valueOf(0);
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


            List<SelectList> data = statisticReportService.getData(initId, endId, filterSelected);
            for ( SelectList temp : data ) {
                total += temp.getValue();
            }

            Gson gson = new Gson();
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", gson.toJson(data));

        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "save", sharedUserService);
            model.addObject("total", total);
            model.addObject("data", null);

        }

        return model;
    }


    @RequestMapping(value = "/managereval/statisticReport/testd3", method = RequestMethod.GET)
    public ModelAndView testd3() {
        ModelAndView model = new ModelAndView("/managereval/statisticReport/testd3");
        return model;
    }

}
