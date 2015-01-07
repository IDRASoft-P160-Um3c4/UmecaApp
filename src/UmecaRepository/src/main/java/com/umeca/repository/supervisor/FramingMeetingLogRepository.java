package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingMeetingConstants;
import com.umeca.model.entities.supervisor.FramingMeetingLog;
import com.umeca.model.entities.supervisor.FramingReference;
import com.umeca.model.entities.supervisor.FramingReferenceForView;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qFramingMeetingLogRepository")
public interface FramingMeetingLogRepository extends JpaRepository<FramingMeetingLog, Long> {

    @Query("select new com.umeca.model.shared.SelectList(FML.title, FML.finalValue,DATE_FORMAT(FML.logDate, '%d/%l/%Y'),FML.logType) from FramingMeetingLog FML " +
            "inner join FML.framingMeeting FM where FM.id=:idFraming and FML.logType in (:lstLogType) order by FML.logDate asc")
    List<SelectList> getLogByIdFraming(@Param("idFraming") Long idFraming, @Param("lstLogType") List<String> lstLogType);

    @Query("select new com.umeca.model.shared.SelectList(FML.title, FML.finalValue,DATE_FORMAT(FML.logDate, '%d/%l/%Y'),FML.logType) from FramingMeetingLog FML " +
            "inner join FML.framingMeeting FM where FM.id=:idFraming and FML.logType in (:lstLogType) order by FML.logIndex")
    List<SelectList> getTerminatedLogByIdFraming(@Param("idFraming") Long idFraming, @Param("lstLogType") List<String> lstLogType);

    @Query("SELECT distinct (DATE_FORMAT(FML.logDate, '%d/%l/%Y')) from FramingMeetingLog FML " +
            "inner join FML.framingMeeting FM " +
            "where FM.id=:idFraming and FML.logType in (:lstLogType)")
    List<String> getChangeLogDatesByIdFraming(@Param("idFraming") Long idFraming, @Param("lstLogType") List<String> lstLogType);
}


