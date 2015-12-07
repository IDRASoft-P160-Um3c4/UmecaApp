package com.umeca.controller.humanResources;


import com.umeca.model.dto.humanResources.*;
import com.umeca.repository.catalog.StatisticHumanResourcesReportTypeRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.repository.humanResources.ScheduleDayRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.humanResources.StatisticHumanResourcesReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.SystemSettingService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private StatisticHumanResourcesReportService statisticHumanResourcesReportService;

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Autowired
    private SystemSettingService systemSettingService;

    @Autowired
    private StatisticHumanResourcesReportTypeRepository statisticHumanResourcesReportTypeRepository;


    @RequestMapping(value = "/humanResources/excelReport/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/humanResources/excelReport/index");
        return model;
    }


    @RequestMapping(value = "/humanResources/excelReport/jxls", method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response, String initDate, String endDate) {

        try {

            Calendar initCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            Date initDateF = null;
            Date endDateF = null;
          //  initDate = initDate + "/01";
         //   endDate = endDate + "/01";

            int monthI = 0;
            int monthF = 0;

            initDateF = new SimpleDateFormat("yyyy/MM/dd").parse(initDate);
            endDateF = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);

            initCal.setTime(initDateF);
            endCal.setTime(endDateF);

            ExcelAssistanceReportDto excelAssistanceReport = new ExcelAssistanceReportDto(initDateF, endDateF);
            List<EmployeeExcelDto> employees = employeeRepository.getAllNoObsoleteEmployeeExcel();
            List<ScheduleDayDto> lstScheduleDay = scheduleDayRepository.findAllScheduleDays();
            List<AttendanceExcelDto> attendanceExcelDto = statisticHumanResourcesReportService.getAttendanceLog(initDate, endDate);
            List<AttendanceExcelDto> absences = statisticHumanResourcesReportTypeRepository.getAllAbsence(initDateF,endDateF);

            attendanceExcelDto.addAll(absences);



            int numberDays;
            numberDays = (int) ((endCal.getTime().getTime() - initCal.getTime().getTime()) / (1000 * 60 * 60 * 24));
            numberDays += 1;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            List<String> lstDaysStr = new ArrayList<>();
            for (int i = 0; i < numberDays; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(initCal.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, i);
                lstDaysStr.add(sdf.format(calendar.getTime()));
            }

            excelAssistanceReport.setDates(lstDaysStr);

            for (EmployeeExcelDto employee : employees) {

                List<DayAttendanceDto> lstDayAttendance = new ArrayList<>();
                for (int i = 0; i < numberDays; i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(initCal.getTime());
                    calendar.add(Calendar.DAY_OF_MONTH, i);
                    DayAttendanceDto dayAttendanceDto = new DayAttendanceDto(calendar);
                    List<AttendanceExcelDto> lstDayLstAttendance = new ArrayList<>();
                    dayAttendanceDto.setLstAttendance(lstDayLstAttendance);
                    lstDayAttendance.add(dayAttendanceDto);
                }


                employee.setLstDayAttendance(lstDayAttendance);


                List<AttendanceExcelDto> attendances = new ArrayList<>();
                for (AttendanceExcelDto attendance : attendanceExcelDto) {
                    if (attendance.getEmployeeId().equals(employee.getIdEmployee())) {
                        for (int i = 0; i < employee.getLstDayAttendance().size(); i++) {


                            Calendar startDay = Calendar.getInstance();
                            startDay.setTime(employee.getLstDayAttendance().get(i).getDay().getTime());

                            Calendar endDay = Calendar.getInstance();
                            endDay.setTime(employee.getLstDayAttendance().get(i).getDay().getTime());
                            endDay.add(Calendar.HOUR_OF_DAY, 23);
                            endDay.add(Calendar.MINUTE, 59);
                            endDay.add(Calendar.SECOND, 59);

                            if (attendance.getEventTime().after(startDay) &&
                                    attendance.getEventTime().before(endDay)) {
                                employee.getLstDayAttendance().get(i).getLstAttendance().add(attendance);

                            }
                        }
                        attendances.add(attendance);
                    }
                }
                employee.setLstAttendance(attendances);
                List<ScheduleDayDto> scheduleDays = new ArrayList<>();
                for (ScheduleDayDto scheduleDay : lstScheduleDay) {
                    if (scheduleDay.getIdEmployee().equals(employee.getIdEmployee())) {
                        scheduleDays.add(scheduleDay);
                    }
                }
                employee.setLstScheduleDay(scheduleDays);
            }

            excelAssistanceReport.setEmployees(employees);


            Map beans = new HashMap();
            XLSTransformer transformer = new XLSTransformer();

            beans.put("excelAssistanceReport", excelAssistanceReport);

            UUID uid = UUID.randomUUID();
            File temp = File.createTempFile(uid.toString(), ".xls");
            String tempPath = temp.getAbsolutePath();

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jxlsTemplate/humanResourcesExcelReport.xls";


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
            System.out.println(ex);
            logException.Write(ex, this.getClass(), "jxlsMethod", sharedUserService);
        }
    }

}
