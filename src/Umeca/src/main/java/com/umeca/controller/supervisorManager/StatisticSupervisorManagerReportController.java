package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.StatisticOperatorReportTypeRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.managereval.StatisticReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.StatisticSupervisorManagerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 04/09/2015.
 */
@Controller
public class StatisticSupervisorManagerReportController {
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    StatisticOperatorReportTypeRepository statisticOperatorReportTypeRepository;
    @Autowired
    StatisticSupervisorManagerReportRepository statisticSupervisorManagerReportRepository;
    @Autowired
    StatisticReportService statisticReportService;
    @Autowired
    StatisticSupervisorManagerReportService statisticSupervisorManagerReportService;

    @RequestMapping(value = "/supervisorManager/statisticReport/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("/supervisorManager/statisticReport/index");
        Gson gson = new Gson();
        List<SelectList> lstEvaAct = statisticSupervisorManagerReportRepository.getAllNoObsolete();
        model.addObject("lstFilter", gson.toJson(lstEvaAct));
        return model;
    }

    @RequestMapping(value = "/supervisorManager/statisticReport/showReport", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, String filterSelected){
        ModelAndView model = new ModelAndView("/supervisorManager/statisticReport/showReport");

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
            title = statisticSupervisorManagerReportRepository.findByCode(filterSelected).getDescription();
            initDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(initDate + initTime);
            endDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(endDate + endTime);

            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            initId = Integer.parseInt(df.format(initDateF));
            endId = Integer.parseInt(df.format(endDateF));


            List<SelectList> data = statisticSupervisorManagerReportService.getData(initId, endId, filterSelected);

            Gson gson = new Gson();

            if(gson.toJson(data).contains("-1111")) {
                SelectList extra = data.remove(0);
                extraData = "Total de entrevistas con opinión: " + extra.getValue();
            }
            for ( SelectList temp : data ) {
                total += temp.getValue();
            }
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", gson.toJson(data));
            model.addObject("extraData", extraData);
            model.addObject("title", title);


        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "save", sharedUserService);
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", null);
            model.addObject("extraData", extraData);
            model.addObject("title", title);

        }

        return model;
    }
}
