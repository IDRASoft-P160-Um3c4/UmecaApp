package com.umeca.controller.channelingManager;

import com.google.gson.Gson;
import com.umeca.model.catalog.StatisticChannelingReportType;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.catalog.StatisticChannelingReportTypeRepository;
import com.umeca.repository.catalog.StatisticOperatorReportTypeRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.channelingManager.StatisticChannelingReportService;
import com.umeca.service.managereval.StatisticReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.StatisticSupervisorManagerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ChannelingReportController {
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    StatisticOperatorReportTypeRepository statisticOperatorReportTypeRepository;
    @Autowired
    StatisticReportService statisticReportService;
    @Autowired
    StatisticChannelingReportService statisticChannelingReportService;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ReportTypeRepository reportTypeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatisticChannelingReportTypeRepository statisticChannelingReportTypeRepository;

    @RequestMapping(value = "/channelingManager/statisticReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/channelingManager/statisticReport/index");
        Gson gson = new Gson();
        List<SelectList> lstEvaAct = statisticChannelingReportTypeRepository.getAllNoObsolete();
        List<SelectList> lstDistrict = districtRepository.findNoObsolete();
        List<SelectList> lstReportType = reportTypeRepository.getAllNoObsolete();
        model.addObject("lstDistrict", gson.toJson(lstDistrict));
        model.addObject("lstReportType", gson.toJson(lstReportType));
        model.addObject("lstFilter", gson.toJson(lstEvaAct));
        return model;
    }

    @RequestMapping(value = "/channelingManager/statisticReport/showReport", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, String filterSelected, Long idReportType, Long idDistrict) {
        ModelAndView model = new ModelAndView("/channelingManager/statisticReport/showReportHD");
        Gson gson = new Gson();
        String extraData = null;
        String title = null;
        Long total = Long.valueOf(0);
        try {
            title = statisticChannelingReportTypeRepository.findByCode(filterSelected).getDescription();
            String data;
            data = statisticChannelingReportService.getData(initDate, endDate, filterSelected, idReportType, idDistrict, null);


            if (
                    filterSelected.equals(Constants.REPORT_STATISTIC_MANAGER_REPORT_C) ||
                    filterSelected.equals(Constants.REPORT_STATISTIC_MANAGER_REPORT_Q)
                    ){
                model = new ModelAndView("/channelingManager/statisticReport/showReportFHD");
            }



            if (reportTypeRepository.getReportCodeById(idReportType).equals(Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR)) {
                model = new ModelAndView("/channelingManager/statisticReport/showComplexReportFHD");

                if (
                        filterSelected.equals(Constants.REPORT_STATISTIC_MANAGER_REPORT_C) ||
                                filterSelected.equals(Constants.REPORT_STATISTIC_MANAGER_REPORT_Q)
                        ){
                    model = new ModelAndView("/channelingManager/statisticReport/showComplexReportUHD");
                }


                List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
                SelectList notAssignedSupervisor = new SelectList();
                notAssignedSupervisor.setName("Sin supervisor");
                notAssignedSupervisor.setDescription("Sin supervisor");
                notAssignedSupervisor.setId(0L);
                users.add(notAssignedSupervisor);

                model.addObject("lstSupervisors", gson.toJson(users));
                model.addObject("idDistrict", idDistrict.toString());
                model.addObject("initDate", initDate.toString());
                model.addObject("endDate", endDate.toString());
                model.addObject("filterSelected", filterSelected);
            }

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

            model.addObject("filterSelected",filterSelected);
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

    @RequestMapping(value = "/channelingManager/statisticReport/showLargeReport", method = RequestMethod.GET)
    public ModelAndView showLargeReport(String filterSelected, String initDate, String endDate, Long idDistrict, Long idSupervisor) {

        Gson gson = new Gson();
        Long total = Long.valueOf(0);
        List<SelectList> users = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);



        SelectList notAssignedSupervisor = new SelectList();
        notAssignedSupervisor.setName("Sin supervisor");
        notAssignedSupervisor.setDescription("Sin supervisor");
        notAssignedSupervisor.setId(0L);
        users.add(notAssignedSupervisor);


        String currentSupervisorFullName;


        if (idSupervisor == 0) {
            currentSupervisorFullName = "Sin supervisor";
        } else {
            currentSupervisorFullName = userRepository.getFullNameById(idSupervisor);
        }


        ModelAndView model = new ModelAndView("/channelingManager/statisticReport/showLargeReportHD");
        if (
                filterSelected.equals(Constants.REPORT_STATISTIC_MANAGER_REPORT_C) ||
                        filterSelected.equals(Constants.REPORT_STATISTIC_MANAGER_REPORT_Q)
                ){
            model = new ModelAndView("/channelingManager/statisticReport/showLargeReportFHD");
        }

        model.addObject("lstSupervisors", gson.toJson(users));
        String title = null;
        String extraData = null;
        try {

            //  List<SelectList> data;
            title = statisticChannelingReportTypeRepository.findByCode(filterSelected).getDescription();
            String data;
            data = statisticChannelingReportService.getData(initDate, endDate, filterSelected, 4L, idDistrict, idSupervisor);

            if (idDistrict == 1)
                extraData = "Por operador: " + currentSupervisorFullName + " - Cuatla";
            else if (idDistrict == 2)
                extraData = "Por operador: " + currentSupervisorFullName + " - Cuernavaca";
            else
                extraData = "Por operador: " + currentSupervisorFullName + " - Jojutla";

            model.addObject("idDistrict", idDistrict);
            model.addObject("initDate", initDate);
            model.addObject("endDate", endDate);
            model.addObject("filterSelected", filterSelected);
            model.addObject("total", total);
            model.addObject("data", data);
            model.addObject("extraData", extraData);
            model.addObject("title", title);
            model.addObject("currentSupervisor", idSupervisor);

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
