package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.AbsenceDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.timeAttendance.Absence;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.humanResources.AbsenceRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.repository.shared.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 10/29/2015.
 */
@Service
public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SystemSettingRepository systemSettingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public ResponseMessage justifyAbsence(AbsenceDto absenceDto) {

        Absence absence = absenceRepository.findOne(absenceDto.getId());
        User user = userRepository.findOne(absenceDto.getIdUser());

        absence.setClose(true);
        absence.setComment(absenceDto.getComment());
        absence.setApproved(absenceDto.isApproved());
        absence.setResponsible(user);
        absenceRepository.save(absence);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La justificación de la falta ha sido registrado con éxito.");
        return resp;
    }

    @Override
    public ResponseMessage addAbsence(AbsenceDto absenceDto) throws Exception{
        Absence absence = new Absence();
        User user = userRepository.findOne(absenceDto.getIdUser());

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = format.parse(absenceDto.getDate());

        String period1String = systemSettingRepository.findOneValue("ATTENDANCE", "PeriodStart");
        String period2String = systemSettingRepository.findOneValue("ATTENDANCE", "PeriodEnd");
        int period1 = Integer.parseInt(period1String);
        int period2 = Integer.parseInt(period2String);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

        String period;

        if (day >= period1 && day < period2)
            period = String.format("%d-%02d-1", year, month + 1);
        else if (day < period1 && month == 0)
            period = String.format("%d-%02d-2", year - 1, 12);
        else if (day < period1)
            period = String.format("%d-%02d-2", year, month);
        else //if (period1 == 1 && day >= period2)
            period = String.format("%d-%02d-2", year, month + 1);

        Employee employee = employeeRepository.findOne(absenceDto.getIdEmployee());

        absence.setEventDate(date);
        absence.setReason(absenceDto.getComment());
        absence.setType(1);
        absence.setComment(null);
        absence.setPeriod(period);
        absence.setValue(1.0);
        absence.setClose(false);

        absence.setResponsible(user);
        absence.setEmployee(employee);

        absenceRepository.save(absence);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La justificación de la falta ha sido registrado con éxito.");
        return resp;
    }
}
