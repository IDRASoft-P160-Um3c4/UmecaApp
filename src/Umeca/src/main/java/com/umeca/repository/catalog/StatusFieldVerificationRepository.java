package com.umeca.repository.catalog;

import com.umeca.model.catalog.StatusFieldVerification;
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

@Repository("statusFieldVerificationRepository")
public interface StatusFieldVerificationRepository extends JpaRepository<StatusFieldVerification,Long> {

    @Query("select s from StatusFieldVerification as s where s.name=:code")
    public StatusFieldVerification findStatusByCode(@Param("code")String code);
}
