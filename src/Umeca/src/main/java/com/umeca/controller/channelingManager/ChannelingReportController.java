package com.umeca.controller.channelingManager;

import com.google.gson.Gson;
import com.sun.javafx.sg.PGShape;
import com.umeca.model.catalog.StatisticChannelingReportType;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SelectOptsList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.repository.supervisorManager.StatisticSupervisorManagerReportRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.channelingManager.StatisticChannelingReportService;
import com.umeca.service.managereval.StatisticReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.StatisticSupervisorManagerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    ChannelingTypeRepository channelingTypeRepository;
    @Autowired
    ChannelingInstitutionNameRepository channelingInstitutionNameRepository;

    @RequestMapping(value = "/channelingManager/statisticReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/channelingManager/statisticReport/index");
        Gson gson = new Gson();
        List<SelectList> lstEvaAct = statisticChannelingReportTypeRepository.getAllNoObsolete();
        List<SelectList> lstDistrict = districtRepository.findNoObsolete();
        SelectList allDistrict = new SelectList();
        allDistrict.setId(0L);
        allDistrict.setName("Todos los distritos");
        lstDistrict.add(allDistrict);
        model.addObject("lstDistrict", gson.toJson(lstDistrict));
        model.addObject("lstReportType", gson.toJson(lstEvaAct));
        model.addObject("lstFilter", gson.toJson(lstEvaAct));
        return model;
    }

    @RequestMapping(value = "/channelingManager/statisticReport/showReport", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, Long idDistrict, Long idReportType, Long idParameter) {
        ModelAndView model = new ModelAndView("/channelingManager/statisticReport/showReportHD");
        Gson gson = new Gson();
        String extraData = null;
        String title = null;
        Long total = Long.valueOf(0);
        try {


            title = statisticChannelingReportTypeRepository.findById(idReportType).getDescription();
            String data;
            data = statisticChannelingReportService.getData(initDate, endDate, idReportType, idDistrict, idParameter);

            if (idParameter == 0) {
                model = new ModelAndView("channelingManager/statisticReport/showReportChannelingTypeGeneral");
            } else {
                model = new ModelAndView("channelingManager/statisticReport/showReportChannelingType");
            }


            if (idDistrict == 0) {
                extraData = "Todos los distritos";
            } else {
                extraData = districtRepository.findDistrictNameById(idDistrict);
            }


            switch (statisticChannelingReportTypeRepository.findById(idReportType).getName()) {
                case Constants.REPORT_STATISTIC_CHANNELING_H:
                    List<SelectOptsList> lstChannelingType = channelingTypeRepository.findNotObsolete();
                    SelectOptsList allChannelingType = new SelectOptsList(0L, "Todas las canalizaciones", "", "");
                    lstChannelingType.add(allChannelingType);
                    model.addObject("lstChannelingType", gson.toJson(lstChannelingType));
                    break;
                case Constants.REPORT_STATISTIC_CHANNELING_I:
                    List<SelectOptsList> lstChannelingInstitutionName = channelingInstitutionNameRepository.findNotObsolete();
                    SelectOptsList allChannelingInstitution = new SelectOptsList(0L, "Todas las instituciones", "", "");
                    lstChannelingInstitutionName.add(allChannelingInstitution);
                    model.addObject("lstChannelingInstitutionName", gson.toJson(lstChannelingInstitutionName));
                    break;
            }


            model.addObject("reportType",statisticChannelingReportTypeRepository.findById(idReportType).getName());
            model.addObject("idDistrict", idDistrict);
            model.addObject("idReportType", idReportType);
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

    @RequestMapping(value = "/channelingManager/statisticReport/oldShowReport", method = RequestMethod.GET)
    public ModelAndView oldShowReport(String initDate, String endDate, String filterSelected, Long idReportType, Long idDistrict) {
        ModelAndView model = new ModelAndView("/channelingManager/statisticReport/showReportHD");
        Gson gson = new Gson();
        String extraData = null;
        String title = null;
        Long total = Long.valueOf(0);
        try {
            title = statisticChannelingReportTypeRepository.findByCode(filterSelected).getDescription();
            String data;
            data = statisticChannelingReportService.oldGetData(initDate, endDate, filterSelected, idReportType, idDistrict, null);


            if (
                    filterSelected.equals(Constants.REPORT_STATISTIC_CHANNELING_B)
                    ) {
                model = new ModelAndView("/channelingManager/statisticReport/showReportFHD");
            }

            if (filterSelected.equals(Constants.REPORT_STATISTIC_CHANNELING_H)) {
                model = new ModelAndView("channelingManager/statisticReport/showReportChannelingTypeGeneral");
                List<SelectOptsList> lstChannelingType = channelingTypeRepository.findNotObsolete();
                model.addObject("lstChannelingType", gson.toJson(lstChannelingType));
            }


            if (reportTypeRepository.getReportCodeById(idReportType).equals(Constants.REPORT_STATISTIC_MANAGER_BY_OPERATOR)) {
                model = new ModelAndView("/channelingManager/statisticReport/showComplexReportFHD");

                if (
                        filterSelected.equals(Constants.REPORT_STATISTIC_CHANNELING_B)
                        ) {
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

            model.addObject("filterSelected", filterSelected);
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

    @RequestMapping("/channelingManager/statisticReport/showReportChannelingType")
    public ModelAndView showReportByType(String initDate, String endDate, String filterSelected, Long idReportType, Long idDistrict, Long idSupervisor, Long idChannelingType) {
        Gson gson = new Gson();
        ModelAndView model = new ModelAndView("/channelingManager/statisticReport/showReportChannelingType");

        String title = null;
        String extraData = null;
        Long total = Long.valueOf(0);
        try {

            //  List<SelectList> data;
            title = statisticChannelingReportTypeRepository.findByCode(filterSelected).getDescription();
            String data;
            data = statisticChannelingReportService.oldGetData(initDate, endDate, filterSelected, 5L, idDistrict, idSupervisor);

       /*     if (idDistrict == 1)
                extraData = "Por operador: " + currentSupervisorFullName + " - Cuatla";
            else if (idDistrict == 2)
                extraData = "Por operador: " + currentSupervisorFullName + " - Cuernavaca";
            else
                extraData = "Por operador: " + currentSupervisorFullName + " - Jojutla";
                */

            model.addObject("idDistrict", idDistrict);
            model.addObject("initDate", initDate);
            model.addObject("endDate", endDate);
            model.addObject("filterSelected", filterSelected);
            model.addObject("total", total);
            model.addObject("data", data);
            model.addObject("extraData", extraData);
            model.addObject("title", title);
            //      model.addObject("currentSupervisor", idSupervisor);

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
                filterSelected.equals(Constants.REPORT_STATISTIC_CHANNELING_B)
                ) {
            model = new ModelAndView("/channelingManager/statisticReport/showLargeReportFHD");
        }

        model.addObject("lstSupervisors", gson.toJson(users));
        String title = null;
        String extraData = null;
        try {

            //  List<SelectList> data;
            title = statisticChannelingReportTypeRepository.findByCode(filterSelected).getDescription();
            String data;
            data = statisticChannelingReportService.oldGetData(initDate, endDate, filterSelected, 4L, idDistrict, idSupervisor);

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
