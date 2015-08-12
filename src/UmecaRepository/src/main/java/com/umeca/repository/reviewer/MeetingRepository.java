package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.reviewer.View.MeetingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 3:55 PM
 */

@Repository("meetingRepository")
public interface MeetingRepository extends JpaRepository<Meeting, Long>{

    @Query("SELECT new com.umeca.model.entities.reviewer.View.MeetingView(me.id, im.name, im.lastNameP, im.lastNameM, me.dateCreate, me.declineReason) " +
            "FROM Meeting me " +
            "INNER JOIN me.imputed im " +
            "WHERE me.id = :id")
    MeetingView getMeetingSheetById(@Param("id")Long id);
}
