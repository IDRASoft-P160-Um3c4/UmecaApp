package com.umeca.repository;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
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
@Repository("qCaseRepository")
public interface CaseRepository extends JpaRepository<Case, Long>{


    @Query("SELECT c FROM Case c WHERE c.idFolder =:idFolder")
    Case findByIdFolder(@Param("idFolder")String idFolder);

    @Query("SELECT c FROM Case c WHERE c.idMP =:idMP")
    Case findByIdMP(@Param("idMP")String idMP);

}


