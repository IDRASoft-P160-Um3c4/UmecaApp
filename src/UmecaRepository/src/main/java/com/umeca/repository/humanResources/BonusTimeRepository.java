package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.BonusTimeDto;
import com.umeca.model.entities.timeAttendance.BonusTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 10/22/2015.
 */
@Repository
public interface BonusTimeRepository extends JpaRepository<BonusTime, Long> {

    @Query("select new com.umeca.model.dto.timeAttendance.BonusTimeDto(bt.id, bt.name, bt.eventDate, bt.eventTime, bt.bonusTime, bt.approved) from BonusTimeView bt where bt.id = :idAttendanceLog")
    BonusTimeDto getTimeAttendance(@Param("idAttendanceLog") Long idAttendanceLog);

    @Query("select bt from BonusTime bt where bt.idAttendanceLog = :idAttendanceLog")
    BonusTime getBonusTime(@Param("idAttendanceLog") Long idAttendanceLog);
}