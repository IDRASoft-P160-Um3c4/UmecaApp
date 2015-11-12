package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.JustifyDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.humanReources.ScheduleDay;
import com.umeca.model.entities.timeAttendance.Absence;
import com.umeca.model.entities.timeAttendance.AbsenceDetail;
import com.umeca.model.entities.timeAttendance.AttendanceLog;
import com.umeca.model.entities.timeAttendance.DelayJustification;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.humanResources.AbsenceDetailRepository;
import com.umeca.repository.humanResources.AbsenceRepository;
import com.umeca.repository.humanResources.AssistenceRepository;
import com.umeca.repository.humanResources.AttendanceLogRepository;
import com.umeca.repository.shared.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 10/13/2015.
 */
@Service
public class AssistenceServiceImpl implements AssistenceService {

    @Autowired
    AssistenceRepository assistenceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SystemSettingRepository systemSettingRepository;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    AbsenceDetailRepository absenceDetailRepository;

    @Autowired
    AttendanceLogRepository attendanceLogRepository;

    @Override
    @Transactional
    public ResponseMessage upsertDevice(JustifyDto justifyDto) throws Exception{

        DelayJustification delayJustification = new DelayJustification();

        if (justifyDto.getId() == null || justifyDto.getId().equals(0L))
            delayJustification = assistenceRepository.findOne(justifyDto.getId());

        if (justifyDto.getId() != null || justifyDto.getId().longValue() > 0L)
            delayJustification.setIdAttendanceLog(justifyDto.getId());

        User user = userRepository.findOne(justifyDto.getIdUser());

        delayJustification.setResponsible(user);
        delayJustification.setApproved(justifyDto.getJustified());
        delayJustification.setComment(justifyDto.getComment());

        assistenceRepository.save(delayJustification);

        if (!delayJustification.isApproved()){ //No se justificó el retardo

            String absenceTimeString = systemSettingRepository.findOneValue("ATTENDANCE", "AbsenceTime");
            String period1String = systemSettingRepository.findOneValue("ATTENDANCE", "PeriodStart");
            String period2String = systemSettingRepository.findOneValue("ATTENDANCE", "PeriodEnd");
            int period1 = Integer.parseInt(period1String);
            int period2 = Integer.parseInt(period2String);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String checkIn = justifyDto.getTime();
            String dateIn = justifyDto.getDate();

            Date eventDate = format.parse(dateIn);

            Calendar cal = Calendar.getInstance();
            cal.setTime(eventDate);
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

            format = new SimpleDateFormat("HH:mm:ss");
            Date p1 = format.parse(checkIn);
            Date p2 = format.parse(absenceTimeString);
            //select sd.start from schedule_days sd where sd.day_id = weekday(a.eventtime) + 1 and sd.id_employee_schedule = e.id_employee_schedule

            Employee employee = assistenceRepository.getEmployee(justifyDto.getId());
            AttendanceLog attendanceLog = attendanceLogRepository.findOne(justifyDto.getId());
            ScheduleDay scheduleDay = assistenceRepository.getScheduleDay(employee.getId(), dayOfWeek);

            if (scheduleDay == null)
                throw new Exception("No aplica el retardo, no es un día laboral");

            Date p3 = new Date(scheduleDay.getStart() * 1000);


            if (p1.getTime() > (p2.getTime() + p3.getTime())) { //Falta automática

                Absence absence = new Absence();
                absence.setEventDate(eventDate);
                absence.setReason("Por registrar entrada después de " + absenceTimeString);
                absence.setType(0);
                absence.setValue(1.0);
                absence.setPeriod(period);
                absence.setResponsible(user);
                absence.setEmployee(employee);
                absence.setClose(false);
                absenceRepository.save(absence);

                AbsenceDetail absenceDetail = new AbsenceDetail();
                absenceDetail.setAttendanceLog(attendanceLog);
                absenceDetail.setAbsence(absence);
                absenceDetailRepository.save(absenceDetail);

            }
            else { //Se acumula la falta en el periodo

                Absence absence = absenceRepository.getAbsencePerPeriod(period, employee.getId());
                double value = -1.0;

                if (absence == null)
                    absence = new Absence();
                else
                    value = absence.getValue();

                absence.setEventDate(eventDate);
                absence.setReason(String.format("Por registrar %d retardo(s) en el periodo %s.", value == -1.0 ? 1 : (value == 0.0 ? 2 : 3), period));
                absence.setType(0);
                absence.setValue(value == -1.0 ? 0.0 : (value == 0.0 ? 0.5 : 1.0));
                absence.setPeriod(period);
                absence.setResponsible(user);
                absence.setEmployee(employee);
                absence.setClose(false);
                absenceRepository.save(absence);

                AbsenceDetail absenceDetail = new AbsenceDetail();
                absenceDetail.setAttendanceLog(attendanceLog);
                absenceDetail.setAbsence(absence);
                absenceDetailRepository.save(absenceDetail);
            }
        }



        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La justificación del retardo ha sido registrado con éxito.");
        return resp;
    }
}