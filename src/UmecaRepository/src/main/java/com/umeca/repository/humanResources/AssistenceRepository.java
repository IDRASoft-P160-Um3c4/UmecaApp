package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.JustifyDto;
import com.umeca.model.entities.timeAttendance.DelayJustification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 10/13/2015.
 */
@Repository("qAssistenceRepository")
public interface AssistenceRepository extends JpaRepository<DelayJustification, Long> {

    @Query("select new com.umeca.model.dto.timeAttendance.JustifyDto(AL.id, AL.name, AL.textIn, AL.textDt, AL.justified) from DelayJustificationView AL where AL.id = :idAttendanceLog")
    JustifyDto getJustify(@Param(("idAttendanceLog")) Long idAttendanceLog);


    @Query("select DJ from DelayJustification  DJ where DJ.idAttendanceLog = :idAttendanceLog")
    DelayJustification getDelayJustification(@Param("idAttendanceLog") Long idAttendanceLog);
}
