package com.umeca.repository.catalog;

import com.umeca.model.catalog.StatusVerification;
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

@Repository("qStatusVerificationRepository")
public interface StatusVerificationRepository extends JpaRepository<StatusVerification,Long> {
    @Query("SELECT s from StatusVerification s where status=:code")
    public StatusVerification findByCode(@Param("code") String code);
}
