package com.umeca.repository;

import com.umeca.model.catalog.StatusMeeting;
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

@Repository("statusMeetingRepository")
public interface StatusMeetingRepository extends JpaRepository<StatusMeeting, Long>{
    @Query("SELECT s from StatusMeeting s where status=:code")
    public StatusMeeting findByCode(@Param("code") String code);
}
