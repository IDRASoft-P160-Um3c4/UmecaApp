package com.umeca.controller.managereval;

import com.google.gson.Gson;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.StatisticOperatorReportTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.managereval.Stado;
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
public class StatisticOperatorReportController {


    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    StatisticOperatorReportTypeRepository statisticOperatorReportTypeRepository;
    @Autowired
    Stado stado;



    @RequestMapping(value = "/managereval/statisticOperatorReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/managereval/statisticOperatorReport/index");
        Gson gson = new Gson();
        List<SelectList> lstFilter = statisticOperatorReportTypeRepository.getAllNoObsolete();
        model.addObject("lstFilter", gson.toJson(lstFilter));
        return model;
    }



    @RequestMapping(value = "/managereval/statisticOperatorReport/showReport", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, String filterSelected) {
        ModelAndView model = new ModelAndView("/managereval/statisticOperatorReport/showReport");

        String yAxis = "Entrevistas";
        String extraData = null;
        String title = null;
        Long total = Long.valueOf(0);
        Date initDateF = null;
        Date endDateF = null;
        int initId = 0;
        int endId = 0;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";
        try {
            title = statisticOperatorReportTypeRepository.findByCode(filterSelected).getDescription();
            initDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(initDate + initTime);
            endDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(endDate + endTime);

            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            initId = Integer.parseInt(df.format(initDateF));
            endId = Integer.parseInt(df.format(endDateF));


            List<SelectList> data = stado.getData(initId, endId, filterSelected);

            Gson gson = new Gson();


            model.addObject("initDate", initDate);
            model.addObject("endDate", endDate);
            model.addObject("total", total);
            model.addObject("data", gson.toJson(data));
            model.addObject("extraData", extraData);
            model.addObject("title", title);
            model.addObject("yAxis", yAxis);


        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "save", sharedUserService);
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", null);
            model.addObject("extraData", extraData);
            model.addObject("title", title);
            model.addObject("yAxis", yAxis);

        }

        return model;

    }



}
