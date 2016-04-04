package com.umeca.service.humanResources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.ImputedDto;
import com.umeca.model.dto.humanResources.ImputedFingerPrintDto;
import com.umeca.model.dto.timeAttendance.AttendanceLogWSDto;
import com.umeca.model.dto.timeAttendance.DeviceDto;
import com.umeca.model.dto.timeAttendance.FingerPrintWSDto;
import com.umeca.model.dto.timeAttendance.UserInfoWsDto;
import com.umeca.model.entities.fingerprint.ImputedFingerprint;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.humanReources.EmployeeFingerPrint;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.timeAttendance.AttendanceLog;
import com.umeca.repository.humanResources.*;
import com.umeca.repository.reviewer.ImputedRepository;
import com.umeca.model.shared.Constants;
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
    @Autowired
    private ImputedRepository imputedRepository;
    @Autowired
    private ImputedFingerPrintRepository imputedFingerPrintRepository;

    @Override
    public ResponseMessage getDevices(String deviceUse) {
        ResponseMessage response;
        Gson gson = new Gson();

        try {
            List<DeviceDto> devices = deviceRepository.findAllNotObsoloteDevicesByUse(deviceUse);
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
                newFinger.setFinger((short)finger);
                newFinger.setData(fingerPrint);
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
    public ResponseMessage updateImputedFingerPrint(String enrollNumber, int finger, String fingerPrint, int operation) {
        ResponseMessage response;

        try {
            if(operation == 0){
                ImputedFingerprint fingerObject = imputedFingerPrintRepository.getFingerPrint(Long.valueOf(enrollNumber), (short)finger);
                if(fingerObject != null){
                    imputedFingerPrintRepository.delete(fingerObject);
                }

                ImputedFingerprint newFinger = new ImputedFingerprint();
                Imputed imputed = imputedRepository.findOne(Long.valueOf(enrollNumber));
                newFinger.setImputed(imputed);
                newFinger.setFinger((short)finger);
                newFinger.setData(fingerPrint);
                newFinger.setTimestamp(Calendar.getInstance().getTime());
                imputedFingerPrintRepository.saveAndFlush(newFinger);
            }
            else if(operation == 1){
                ImputedFingerprint fingerObject = imputedFingerPrintRepository.getFingerPrint(Long.valueOf(enrollNumber), (short)finger);
                imputedFingerPrintRepository.delete(fingerObject);
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

    @Override
    public ResponseMessage getImputed(long imputed){

        ResponseMessage response;
        Gson gson = new Gson();

        try {
            List<ImputedDto> imputedResultArr = imputedRepository.getImputed(imputed);
            ImputedDto imputedResult = imputedResultArr.get(0);

            List<ImputedFingerPrintDto> fingerPrints = imputedRepository.getImputedFingerPrint(imputed);
            imputedResult.setFingerPrints(fingerPrints);

            if (imputedResult != null) {
                response = new ResponseMessage(false, "Datos correctos");
                response.setData(gson.toJson(imputedResult));
            } else {
                response = new ResponseMessage(true, "No existen empleados.");
            }

        } catch (Exception e) {
            response = new ResponseMessage(true, "Ha ocurrido un error, intente nuevamente");
        }

        return response;
    }
}