package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.catalog.dto.ScheduleDto;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.reviewer.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    CaseRepository caseRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    ImputedHomeRepository imputedHomeRepository;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Override
    public Object getSchedules(Long id, Object classObjetc) {
        List<Schedule> listSchedule = new ArrayList<Schedule>();
        if (classObjetc.equals(School.class)) {
            Case c = caseRepository.findOne(id);
            if (c != null && c.getMeeting() != null && c.getMeeting().getSchool() != null) {
                listSchedule = scheduleRepository.getSchedulesSchool(c.getMeeting().getSchool().getId());
            }
        } else if (classObjetc.equals(Job.class)) {
            Job j = jobRepository.findOne(id);
            listSchedule = scheduleRepository.getSchedulesJob(j.getId());
        } else if (classObjetc.equals(ImputedHome.class)) {
            ImputedHome imputedHome = imputedHomeRepository.findOne(id);
            listSchedule = scheduleRepository.getSchedulesDomicile(imputedHome.getId());
        }
        List<ScheduleDto> listScheduleDto = new ArrayList<ScheduleDto>();
        if (listSchedule != null && listSchedule.size() > 0) {
            for (Schedule s : listSchedule) {
                ScheduleDto sd = new ScheduleDto();
                listScheduleDto.add(sd.dtoSchedule(s));
            }
            Gson gson = new Gson();
            return gson.toJson(listScheduleDto);
        } else {
            return "[]";
        }
    }

    @Override
    public String getSchedulesVerificationValue(Long id, Object classObjetc) {
        List<Schedule> listSchedule = new ArrayList<Schedule>();
        String val = "";
        if (classObjetc.equals(School.class)) {
            Case c = caseRepository.findOne(id);
            if (c != null && c.getMeeting() != null && c.getMeeting().getSchool() != null) {
                listSchedule = scheduleRepository.getSchedulesSchool(c.getMeeting().getSchool().getId());
            }
        } else if (classObjetc.equals(Job.class)) {
            Job j = jobRepository.findOne(id);
            listSchedule = scheduleRepository.getSchedulesJob(j.getId());
        } else if (classObjetc.equals(ImputedHome.class)) {
            ImputedHome imputedHome = imputedHomeRepository.findOne(id);
            listSchedule = scheduleRepository.getSchedulesDomicile(imputedHome.getId());
        }
        if (listSchedule != null && listSchedule.size() > 0) {
            for (Schedule s : listSchedule) {
                val = val + "Día(s): " + s.getDay() + " Inicio: " + s.getStart() + " Fin: " + s.getEnd() + "<br/>";
            }
        }
        return val;
    }

    @Override
    public Boolean saveSchedules(String schedules, Long id, Class classType) {
        Gson gson = new Gson();
        if (schedules != null && schedules != "[]") {
            try {
                List<Schedule> listSchedules = gson.fromJson(schedules, new TypeToken<List<Schedule>>() {
                }.getType());
                List<Schedule> listToDelete = new ArrayList<>();
                if (classType == School.class) {
                    Case c = caseRepository.findOne(id);
                    listToDelete = scheduleRepository.getSchedulesSchool(c.getMeeting().getSchool().getId());

                    for (Schedule schedule : listSchedules) {
                        schedule.setSchool(c.getMeeting().getSchool());
                    }
                }
                if (classType == Job.class) {
                    Job job = jobRepository.findOne(id);
                    listToDelete = scheduleRepository.getSchedulesJob(id);
                    for (Schedule schedule : listSchedules) {
                        schedule.setJob(job);
                    }
                }
                if (classType == ImputedHome.class) {
                    ImputedHome imputedHome = imputedHomeRepository.findOne(id);
                    listToDelete = scheduleRepository.getSchedulesDomicile(id);
                    for (Schedule schedule : listSchedules) {
                        schedule.setImputedHome(imputedHome);
                    }
                }
                if (listToDelete != null) {
                    scheduleRepository.delete(listToDelete);
                }
                scheduleRepository.save(listSchedules);
            } catch (Exception e) {
                logException.Write(e,this.getClass(),"saveSchedules",sharedUserService);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
