package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.catalog.dto.ScheduleDto;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.reviewer.*;
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
  DomicileRepository domicileRepository;

    @Override
    public Object getSchedules(Long id, Object classObjetc) {
        List<Schedule> listSchedule = new ArrayList<Schedule>();
        if(classObjetc.equals(School.class)){
            Case c = caseRepository.findOne(id);
          if(c!=null && c.getMeeting()!=null && c.getMeeting().getSchool()!=null){
              listSchedule = scheduleRepository.getSchedulesSchool(c.getMeeting().getSchool().getId());
          }
        }else if(classObjetc.equals(Job.class)){
            Job j = jobRepository.findOne(id);
            listSchedule = scheduleRepository.getSchedulesJob(j.getId());
        }else if(classObjetc.equals(Domicile.class)){
            Domicile domicile = domicileRepository.findOne(id);
            listSchedule = scheduleRepository.getSchedulesDomicile(domicile.getId());
        }
        List<ScheduleDto> listScheduleDto = new ArrayList<ScheduleDto>();
        if(listSchedule!=null && listSchedule.size()>0){
            for(Schedule s : listSchedule){
                ScheduleDto sd = new ScheduleDto();
                listScheduleDto.add(sd.dtoSchedule(s));
            }
            Gson gson = new Gson();
            return gson.toJson(listScheduleDto);
        }else{
            return "";
        }
    }

    @Override
    public Boolean saveSchedules(String schedules, Long id, Class classType) {
        Gson gson = new Gson();
        if(schedules != null && schedules!="[]"){
            try {
                List<Schedule> listSchedules = gson.fromJson(schedules,new TypeToken<List<Schedule>>(){}.getType());
                if(classType == School.class){
                    Case c = caseRepository.findOne(id);
                    for(Schedule schedule: listSchedules){
                      schedule.setSchool(c.getMeeting().getSchool());
                      scheduleRepository.save(schedule);
                    }
                }
                if(classType == Job.class){
                    Job job = jobRepository.findOne(id);
                    for (Schedule schedule: listSchedules){
                        schedule.setJob(job);
                        scheduleRepository.save(schedule);
                    }
                }
                if(classType == Domicile.class){
                    Domicile domicile =  domicileRepository.findOne(id);
                     for (Schedule schedule: listSchedules){
                         schedule.setDomicile(domicile);
                         scheduleRepository.save(schedule);
                     }
                }
            }catch (Exception e){
            }
            return true;
        }else{
         return false;
        }
    }
}