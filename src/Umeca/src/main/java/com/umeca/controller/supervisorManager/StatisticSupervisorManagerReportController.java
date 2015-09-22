package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.catalog.StatisticOperatorReportTypeRepository;
import com.umeca.repository.supervisor.DistrictRepository;
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
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ReportTypeRepository reportTypeRepository;

    @RequestMapping(value = "/supervisorManager/statisticReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/supervisorManager/statisticReport/index");
        Gson gson = new Gson();
        List<SelectList> lstEvaAct = statisticSupervisorManagerReportRepository.getAllNoObsolete();
        List<SelectList> lstDistrict = districtRepository.findNoObsolete();
        List<SelectList> lstReportType = reportTypeRepository.getAllNoObsolete();
        model.addObject("lstDistrict", gson.toJson(lstDistrict));
        model.addObject("lstReportType", gson.toJson(lstReportType));
        model.addObject("lstFilter", gson.toJson(lstEvaAct));
        return model;
    }

    @RequestMapping(value = "/supervisorManager/statisticReport/showReport", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, String filterSelected, Long idReportType, Long idDistrict) {
        ModelAndView model = new ModelAndView("/supervisorManager/statisticReport/showReport");

        String extraData = null;
        String title = null;
        Long total = Long.valueOf(0);
        try {
            title = statisticSupervisorManagerReportRepository.findByCode(filterSelected).getDescription();
            //  List<SelectList> data;
            String data;
            data = statisticSupervisorManagerReportService.getData(initDate, endDate, filterSelected, idReportType, idDistrict);


            if (reportTypeRepository.getReportCodeById(idReportType).equals(Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR)) {
                model = new ModelAndView("/supervisorManager/statisticReport/showComplexReport");
            }

            //     if(filterSelected.equals(Constants.REPORT_STATISTIC_MANAGER_REPORT_C)){
            //       model = new ModelAndView("/supervisorManager/statisticReport/showArrangementReport");
            //    }

            if (idReportType == 1)
                extraData = "General";
            else if (idReportType == 2) {
                if (idDistrict == 1)
                    extraData = "Por operador - Cuatla";
                else if (idDistrict == 2)
                    extraData = "Por operador - Cuernavaca";
                else
                    extraData = "Por operador - Jojutla";
            } else {
                if (idDistrict == 1)
                    extraData = "Por distrito - Cuatla";
                else if (idDistrict == 2)
                    extraData = "Por distrito - Cuernavaca";
                else
                    extraData = "Por distrito - Jojutla";
            }


            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", data);
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
