package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.Drug;
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
}
