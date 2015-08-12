package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.SelectList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingTrackingRepository")
public interface ChannelingTrackingRepository extends JpaRepository<Channeling, Long> {

    @Query("SELECT new com.umeca.model.entities.supervisor.ChannelingTrackModel(amp.id, cd.idMP, i.name, i.lastNameP, i.lastNameM" +
            ", ch.name, cht.name, amp.start, amp.end, amp.isJustified, amp.commentsJustification, " +
            "amp.rescheduleAppointment.id, ra.start, ra.end) " +
            "FROM ActivityMonitoringPlan AS amp " +
            "INNER JOIN amp.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed i " +
            "INNER JOIN amp.channeling ch " +
            "INNER JOIN ch.channelingType cht " +
            "LEFT JOIN amp.rescheduleAppointment ra " +
            "WHERE amp.id =:id ")
    ChannelingTrackModel getChannelingTrackByActMonPlanId(@Param("id") Long id);
}
