package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRowsModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ReportTypeRepository;
import com.umeca.repository.catalog.StatisticHumanResourcesReportTypeRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.humanResources.StatisticHumanResourcesReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;


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
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

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
            List<ReportList> data;
            data = statisticHumanResourcesReportService.getData(initDate, endDate, filterSelected, idReportType, idDistrict, idEmployee);


            if (idReportType.equals(1L))
                extraData = "General";
            else if (idReportType.equals(2L)){
                if (idDistrict.equals(1L))
                    extraData = "Por distrito - Cuatla";
                else if (idDistrict.equals(2L))
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
            else if(filterSelected.equals(Constants.REPORT_HUMAN_RESOURCES_STATISTIC_C))
                measure = "Horas";
            else
                measure = "Incidencias";

            model.addObject("idDistrict",idDistrict);
            model.addObject("idEmployee", idEmployee);
            model.addObject("idReportType",idReportType);
            model.addObject("filterSelected",filterSelected);
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", gson.toJson(data));
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



    @RequestMapping(value = {"/humanResources/statisticReport/list"} )
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts,String initDate,  String endDate, String filterSelected, Long idReportType, Long idDistrict, Long idEmployee){
        List<ReportList> data;
        try{
            data = statisticHumanResourcesReportService.getData(initDate, endDate, filterSelected, idReportType, idDistrict, idEmployee);
            JqGridResultModel result = new JqGridResultModel();
            List<JqGridRowsModel> jqGridRowsModelList = new ArrayList<>();



            for(int i = 0; i < data.size(); i++){
                JqGridRowsModel jqGridRowsModel = new JqGridRowsModel();
                jqGridRowsModel.setId(i + 1);
                jqGridRowsModel.setCell(data.get(i));
                jqGridRowsModelList.add(jqGridRowsModel);

            }

            result.setPage(1);
            result.setRecords(1);
            result.setTotal(1);
            result.setRows(jqGridRowsModelList);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            logException.Write(e, this.getClass(), "HumanResourcesReport", sharedUserService);
            return null;
        }
    }



    @RequestMapping(value = "/humanResources/statisticReport/jxls", method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response, String initDate, String endDate,String filterSelected,Long idReportType, Long idDistrict, Long idEmployee) {

        Map beans = new HashMap();

        XLSTransformer transformer = new XLSTransformer();



        try {

            List<ReportList> data;
            data = statisticHumanResourcesReportService.getData(initDate, endDate, filterSelected, idReportType, idDistrict, idEmployee);

            beans.put("data", data);


            UUID uid = UUID.randomUUID();

            File temp = File.createTempFile(uid.toString(), ".xls");
            String tempPath = temp.getAbsolutePath();

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jxlsTemplate/reportHumanResources.xls";

            transformer.transformXLS(realContextPath, beans, tempPath);

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"reportHumanResources.xls\"");

            FileInputStream istr = new FileInputStream(tempPath);
            OutputStream ostr = response.getOutputStream();

            int curByte = -1;

            while ((curByte = istr.read()) != -1)
                ostr.write(curByte);

            ostr.flush();
            ostr.close();
            istr.close();
            temp.delete();

        } catch (Exception ex) {
            ex.printStackTrace();
            logException.Write(ex, this.getClass(), "jxlsMethod", sharedUserService);
        }
    }


}
