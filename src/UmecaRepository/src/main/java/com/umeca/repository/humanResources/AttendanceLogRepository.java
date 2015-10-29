package com.umeca.repository.humanResources;

import com.umeca.model.entities.timeAttendance.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 10/28/2015.
 */
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
}
