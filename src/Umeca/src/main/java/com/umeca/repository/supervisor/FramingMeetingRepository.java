package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisor.HearingFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qFramingMeetingRepository")
public interface FramingMeetingRepository extends JpaRepository<FramingMeeting, Long>{


    @Query("SELECT hf FROM MonitoringPlan mp " +
            "INNER JOIN mp.caseDetention.hearingFormats hf " +
            "WHERE mp.id =:id ORDER BY hf.id DESC")
    FramingMeeting findByCaseId(@Param("id")Long id);

}


