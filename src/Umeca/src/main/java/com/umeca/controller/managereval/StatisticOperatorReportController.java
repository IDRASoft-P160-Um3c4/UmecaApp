package com.umeca.controller.managereval;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.StatisticOperatorReportTypeRepository;
import com.umeca.repository.catalog.StatisticReportTypeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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



    @RequestMapping(value = "/managereval/statisticOperatorReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/managereval/statisticOperatorReport/index");
        Gson gson = new Gson();
        List<SelectList> lstFilter = statisticOperatorReportTypeRepository.getAllNoObsolete();
        model.addObject("lstFilter", gson.toJson(lstFilter));
        return model;
    }



    @RequestMapping(value = "/managereval/statisticOperatorReport/showReport", method = RequestMethod.POST)
    public ResponseMessage showReport(String filterSelected, String initDate, String endDate) {
        ResponseMessage responseMessage = null;
        ModelAndView model = new ModelAndView("/managereval/statisticOperatorReport/showReport");

        Date initDateF = null;
        Date endDateF = null;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";
        try {
            initDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(initDate + initTime);

            endDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(endDate + endTime);
        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "save", sharedUserService);
            return new ResponseMessage(true, "Error de red, intente mas tarde.");
        }

        return responseMessage;
    }



}
