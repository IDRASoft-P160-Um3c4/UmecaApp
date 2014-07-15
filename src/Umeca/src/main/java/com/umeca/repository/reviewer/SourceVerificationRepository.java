package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.SourceVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("sourceVerificationRepository")
public interface SourceVerificationRepository extends JpaRepository<SourceVerification,Long> {

    @Query("select s from SourceVerification as s where s.verification.caseDetention.id =:idCase and s.visible = false")
    SourceVerification findSourceImputed(@Param("idCase")Long idCase);

    @Query("select s.dateComplete from SourceVerification as s where s.id=:idSource")
    Date getDateCompleteBySource(@Param("idSource")Long idSource);

    @Query("select count(sv) from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "where sv.visible = true and sv.isAuthorized = true AND sv.dateComplete IS NULL and c.id=:idCase")
    Long getNoSourceAvailable(@Param("idCase")Long idCase);

    @Query("select sv.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "where c.id = :idCase")
    List<Long> getAllSourcesByCase(@Param("idCase")Long idCase);
}
