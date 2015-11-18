package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.ScheduleDayDto;
import com.umeca.model.entities.humanReources.ScheduleDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qScheduleDayRepository")
public interface ScheduleDayRepository extends JpaRepository<ScheduleDay, Long> {

    @Query("select new com.umeca.model.dto.humanResources.ScheduleDayDto(SD.id, SD.dayId, SD.start, SD.end) from ScheduleDay SD " +
            "join SD.employeeSchedule ES " +
            "where ES.id=:idParent order by SD.dayId")
    List<ScheduleDayDto> findScheduleDaysByParentId(@Param("idParent") Long idEmployeeSchedule);


    @Query("select new com.umeca.model.dto.humanResources.ScheduleDayDto(SD.id, SD.dayId, SD.start, SD.end, EMP.id) " +
            "from Employee EMP  " +
            "inner join emp.employeeSchedule.days SD ")
    List<ScheduleDayDto> findAllScheduleDays();

}