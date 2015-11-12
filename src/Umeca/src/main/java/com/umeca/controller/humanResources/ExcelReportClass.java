package com.umeca.controller.humanResources;

import com.umeca.model.dto.humanResources.AttendanceExcelDto;
import com.umeca.model.dto.humanResources.EmployeeExcelDto;
import com.umeca.model.dto.humanResources.ScheduleDayDto;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.repository.humanResources.ScheduleDayRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.humanResources.StatisticHumanResourcesReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.SystemSettingService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DeveloperII on 09/11/2015.
 */

@Controller
public class ExcelReportClass {


    @Autowired
    private SharedLogExceptionService logException;

    @Autowired
    private SharedUserService sharedUserService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    StatisticHumanResourcesReportService statisticHumanResourcesReportService;

    @Autowired
    ScheduleDayRepository scheduleDayRepository;

    @Autowired
    SystemSettingService systemSettingService;

    @RequestMapping(value = "/humanResources/excelReport/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("/humanResources/excelReport/index");
        return model;
    }


    @RequestMapping(value = "/humanResources/excelReport/jxls", method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response, String initDate, String endDate) {

        Map beans = new HashMap();

        XLSTransformer transformer = new XLSTransformer();
        List<EmployeeExcelDto> employees;
        List<AttendanceExcelDto> attendanceExcelDto = null;
        List<ScheduleDayDto> lstScheduleDay;
        Calendar initCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        Date initDateF = null;
        Date endDateF = null;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";
        initDate = initDate + "/01";
        endDate = endDate + "/01";
        int monthI = 0;
        int monthF = 0;


        try {
            employees = employeeRepository.getAllNoObsoleteEmployeeExcel();
            attendanceExcelDto = statisticHumanResourcesReportService.getAttendanceLog(initDate, endDate);
            lstScheduleDay = scheduleDayRepository.findAllScheduleDays();

            initDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(initDate + initTime);
            endDateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(endDate + endTime);
            initCal.setTime(initDateF);
            endCal.setTime(endDateF);

            monthI = initCal.get(Calendar.MONTH) + 1;
            monthF = endCal.get(Calendar.MONTH) + 1;


            Integer startPeriod = Integer.parseInt(systemSettingService.findOneValue("ATTENDANCE", "PeriodStart"));
            Integer endPeriod = Integer.parseInt(systemSettingService.findOneValue("ATTENDANCE", "PeriodEnd"));

            initCal.set(Calendar.DAY_OF_MONTH, startPeriod);

            if (startPeriod > 1) {
                endCal.set(Calendar.MONTH, monthF);
                endCal.set(Calendar.DAY_OF_MONTH, startPeriod - 1);
            }


            int numberDays;

            numberDays = (int)( (endCal.getTime().getTime() - initCal.getTime().getTime()) / (1000 * 60 * 60 * 24));

            List<Calendar> lstDays = new ArrayList<>();
            for(int i = 0; i < numberDays; i++ ){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(initCal.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, i);
                lstDays.add(calendar);
            }


            for(EmployeeExcelDto employee : employees){


                List<AttendanceExcelDto> attendances = new ArrayList<>();
                for(AttendanceExcelDto attendance : attendanceExcelDto){
                    if(attendance.getEmployeeId().equals(employee.getIdEmployee()) ){
                        attendances.add(attendance);
                    }
                }
                employee.setLstAttendance   (attendances);


                List<ScheduleDayDto> scheduleDays = new ArrayList<>();
                for(ScheduleDayDto scheduleDay : lstScheduleDay){
                    if(scheduleDay.getIdEmployee().equals(employee.getIdEmployee())){
                        scheduleDays.add(scheduleDay);
                    }

                }
                employee.setLstScheduleDay(scheduleDays);

            }


            beans.put("lstEmployees",  employees);



            UUID uid = UUID.randomUUID();
            File temp = File.createTempFile(uid.toString(), ".xls");
            String tempPath = temp.getAbsolutePath();

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jxlsTemplate/humanResourcesReport.xls";

            transformer.transformXLS(realContextPath, beans, tempPath);

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"humanResourcesReport.xls\"");

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
