package com.umeca.service.humanResources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.AttendanceLogWSDto;
import com.umeca.model.dto.timeAttendance.DeviceDto;
import com.umeca.model.dto.timeAttendance.FingerPrintWSDto;
import com.umeca.model.dto.timeAttendance.UserInfoWsDto;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.humanReources.EmployeeFingerPrint;
import com.umeca.model.entities.timeAttendance.AttendanceLog;
import com.umeca.repository.humanResources.AttendanceLogRepository;
import com.umeca.repository.humanResources.DeviceRepository;
import com.umeca.repository.humanResources.EmployeeFingerPrintRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service("HumanResourcesWSServiceImpl")
public class HumanResourcesWSServiceImpl implements HumanResourcesWSService {


    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeFingerPrintRepository employeeFingerPrintRepository;
    @Autowired
    private AttendanceLogRepository attendanceLogRepository;

    @Override
    public ResponseMessage getDevices() {
        ResponseMessage response;
        Gson gson = new Gson();

        try {
            List<DeviceDto> devices = deviceRepository.findAllNotObsoloteDevices();
            if (devices != null) {
                response = new ResponseMessage(false, "Datos correctos");
                response.setData(gson.toJson(devices));
            } else {
                response = new ResponseMessage(true, "No hay dispositivos registrados.");
            }

        } catch (Exception e) {
            response = new ResponseMessage(true, "Ha ocurrido un error, intente nuevamente");
        }

        return response;
    }

    @Override
    public ResponseMessage getEmployees() {
        ResponseMessage response;
        Gson gson = new Gson();

        try {
            List<UserInfoWsDto> employees = employeeRepository.getEmployeesWs();
            for (UserInfoWsDto employee : employees){
                List<FingerPrintWSDto> fingerPrints = employeeFingerPrintRepository.getFingerPrints(employee.getId());
                employee.setFingerPrints(fingerPrints);
            }
            if (employees != null) {
                response = new ResponseMessage(false, "Datos correctos");
                response.setData(gson.toJson(employees));
            } else {
                response = new ResponseMessage(true, "No existen empleados.");
            }

        } catch (Exception e) {
            response = new ResponseMessage(true, "Ha ocurrido un error, intente nuevamente");
        }

        return response;
    }

    @Transactional
    @Override
    public ResponseMessage updateUserFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation) {
        ResponseMessage response;

        try {
            if(operation == 0){
                EmployeeFingerPrint fingerObject = employeeFingerPrintRepository.getFingerPrint(Long.valueOf(enrollNumber), finger);
                if(fingerObject != null){
                    employeeFingerPrintRepository.delete(fingerObject);
                }

                EmployeeFingerPrint newFinger = new EmployeeFingerPrint();
                Employee employee = employeeRepository.findOne(Long.valueOf(enrollNumber));
                newFinger.setEmployee(employee);
                newFinger.setFinger(finger);
                newFinger.setFingerprint(fingerPrint);
                employeeFingerPrintRepository.saveAndFlush(newFinger);
            }
            else if(operation == 1){
                EmployeeFingerPrint fingerObject = employeeFingerPrintRepository.getFingerPrint(Long.valueOf(enrollNumber), finger);
                employeeFingerPrintRepository.delete(fingerObject);
            }
            response = new ResponseMessage(false, "Datos actualizados");

        } catch (Exception e) {
            response = new ResponseMessage(true, "Ha ocurrido un error, intente nuevamente");
        }
        return response;
    }

    @Transactional
    @Override
    public ResponseMessage updateAttendanceLogs(String logsList) {
        Gson gson = new Gson();
        List<AttendanceLogWSDto> listLogs = gson.fromJson(logsList,new TypeToken<List<AttendanceLogWSDto>>(){}.getType());

        ResponseMessage response;
        try {
            for (AttendanceLogWSDto log : listLogs){

                Calendar dateC = Calendar.getInstance();
                dateC.setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(log.getDate()));

                AttendanceLog attendanceLog = new AttendanceLog();
                attendanceLog.setEventTime(dateC);
                attendanceLog.setWorkCode((short) log.getWorkCode());
                Employee employee = employeeRepository.findOne(Long.valueOf(log.getEnrollNumber()));
                attendanceLog.setEmployee(employee);
                attendanceLogRepository.saveAndFlush(attendanceLog);
            }
            response = new ResponseMessage(false, "Datos actualizados");

        } catch (Exception e) {
            response = new ResponseMessage(true, "Ha ocurrido un error, intente nuevamente");
        }

        return response;
    }

}
