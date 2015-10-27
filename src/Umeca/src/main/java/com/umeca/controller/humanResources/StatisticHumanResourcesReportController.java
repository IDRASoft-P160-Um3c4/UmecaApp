package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.catalog.StatisticHumanResourcesReportTypeRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.humanResources.StatisticHumanResourcesReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class StatisticHumanResourcesReportController {
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ReportTypeRepository reportTypeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository  employeeRepository;
    @Autowired
    StatisticHumanResourcesReportTypeRepository statisticHumanResourcesReportTypeRepository;
    @Autowired
    StatisticHumanResourcesReportService statisticHumanResourcesReportService;

    @RequestMapping(value = "/humanResources/statisticReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/humanResources/statisticReport/index");
        Gson gson = new Gson();
        List<SelectList> lstEmployee = employeeRepository.getAllNoObsoleteEmployees();
        List<SelectList> lstDistrict = districtRepository.findNoObsolete();
        List<SelectList> lstReportType = reportTypeRepository.getAllNoObsolete();
        List<SelectList> lstFilter = statisticHumanResourcesReportTypeRepository.getAllNoObsolete();
        model.addObject("lstDistrict", gson.toJson(lstDistrict));
        model.addObject("lstReportType", gson.toJson(lstReportType));
        model.addObject("lstFilter", gson.toJson(lstFilter));
        model.addObject("lstEmployee", gson.toJson(lstEmployee));
        return model;
    }

    @RequestMapping(value = "/humanResources/statisticReport/showReport", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, String filterSelected, Long idReportType, Long idDistrict, Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/statisticReport/showReportHD");
        Gson gson = new Gson();
        String extraData = null;
        String title = null;
        String measure = "x";
        Long total = Long.valueOf(0);
        try {
            title = statisticHumanResourcesReportTypeRepository.findByCode(filterSelected).getDescription();
//            List<SelectList> data;
            String data;
            data = statisticHumanResourcesReportService.getData(initDate, endDate, filterSelected, idReportType, idDistrict, idEmployee);

            if (idReportType == 1)
                extraData = "General";
            else if (idReportType == 2){
                if (idDistrict == 1)
                    extraData = "Por distrito - Cuatla";
                else if (idDistrict == 2)
                    extraData = "Por distrito - Cuernavaca";
                else
                    extraData = "Por distrito - Jojutla";
            } else {

                extraData = "Por operador - " + employeeRepository.getEmployeeNameById(idEmployee);

            }

            if(filterSelected.equals(Constants.REPORT_HUMAN_RESOURCES_STATISTIC_A))
                measure = "Faltas";
            else if(filterSelected.equals(Constants.REPORT_HUMAN_RESOURCES_STATISTIC_B))
                measure = "Retardos";
            else
                measure = "Horas";


            model.addObject("filterSelected",filterSelected);
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", data);
            model.addObject("extraData", extraData);
            model.addObject("title", title);
            model.addObject("measure", measure);
//
        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "HumanResourcesReport", sharedUserService);
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", null);
            model.addObject("extraData", extraData);
            model.addObject("title", title);
            model.addObject("measure", measure);
        }
        return model;
    }


}
