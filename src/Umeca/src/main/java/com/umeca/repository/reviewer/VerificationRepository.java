package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.Verification;
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
@Repository("qVerificationRepository")
public interface VerificationRepository extends JpaRepository<Verification, Long>{

    @Query("SELECT v FROM Verification v WHERE v.id =:idVerification")
    Verification findById(@Param("idVerification")Long idVerification);

    @Query("SELECT V FROM Verification V WHERE V.caseDetention.id =:idCase")
    Verification findByCase(@Param("idCase") Long idCase);
}


