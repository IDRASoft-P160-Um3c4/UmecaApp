package com.umeca.repository.supervisorManager;

import com.umeca.model.entities.supervisorManager.ImputedMissedAttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("qImputedMissedAttendanceLogRepository")
public interface ImputedMissedAttendanceLogRepository extends JpaRepository<ImputedMissedAttendanceLog, Long> {


    @Query("select distinct (imp.id) " +
            "FROM ImputedMissedAttendanceLog imp " +
            "where (imp.date between :initDate and :endDate) and imp.isObsolete = false")
    public List<Long> getDetainedByPeriod(@Param("initDate") Date initDate, @Param("endDate") Date endDate);
}
