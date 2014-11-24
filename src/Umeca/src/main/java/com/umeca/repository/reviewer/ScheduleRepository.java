package com.umeca.repository.reviewer;

import com.umeca.model.catalog.dto.ScheduleDto;
import com.umeca.model.entities.reviewer.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("scheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule as s where s.school.id = :idSchool")
    List<Schedule> getSchedulesSchool(@Param("idSchool") Long id);

    @Query("select s from Schedule as s where s.job.id = :idJob")
    List<Schedule> getSchedulesJob(@Param("idJob") Long id);

    @Query("select s from Schedule as s where s.imputedHome.id = :idImputedHome")
    List<Schedule> getSchedulesDomicile(@Param("idImputedHome") Long id);

    @Query("select s from Schedule as s where s.framingActivity.id = :idActivity")
    List<Schedule> getSchedulesActivty(@Param("idActivity") Long idActivity);

    @Query("select new com.umeca.model.catalog.dto.ScheduleDto(s.id,s.day,s.start,s.end) from Schedule as s " +
            "inner join s.framingAddress fa " +
            "inner join fa.framingMeeting fm " +
            "inner join fm.caseDetention cd where cd.id = :caseId")
    List<ScheduleDto> getSchedulesAdressFramingByCaseId(@Param("caseId")Long caseId);

    @Query("select new com.umeca.model.catalog.dto.ScheduleDto(s.id,s.day,s.start,s.end) from Schedule as s inner join s.school sc " +
            "inner join sc.framingMeeting fm " +
            "inner join fm.caseDetention cd where cd.id = :caseId")
    List<ScheduleDto> getSchedulesSchoolFramingByCaseId(@Param("caseId")Long caseId);

    @Query("select new com.umeca.model.catalog.dto.ScheduleDto(s.id,s.day,s.start,s.end) from Schedule as s inner join s.job j " +
            "inner join j.framingMeeting fm " +
            "inner join fm.caseDetention cd where cd.id = :caseId")
    List<ScheduleDto> getSchedulesJobFramingByCaseId(@Param("caseId")Long caseId);

    @Query("select  new com.umeca.model.catalog.dto.ScheduleDto(s.id,s.day,s.start,s.end)  from Schedule as s inner join s.framingActivity fa " +
            "inner join fa.framingMeeting fm " +
            "inner join fm.caseDetention cd " +
            "inner join s.framingActivity fa " +
            "inner join fa.activity a " +
            "where cd.id = :caseId and a.id=:idAct")
    List<ScheduleDto> getSchedulesActivityFramingByCaseId(@Param("caseId")Long caseId, @Param("idAct") Long idAct);
}
