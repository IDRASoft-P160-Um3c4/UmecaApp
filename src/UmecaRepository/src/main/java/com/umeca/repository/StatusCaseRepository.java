package com.umeca.repository;

import com.umeca.model.catalog.StatusCase;
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

@Repository("statusCaseRepository")
public interface StatusCaseRepository extends JpaRepository<StatusCase,Long> {
    @Query("SELECT s from StatusCase s where s.name=:code")
    public StatusCase findByCode(@Param("code") String code);
}
