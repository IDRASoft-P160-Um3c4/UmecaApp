package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.RequestAgreement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Rata on 05/03/2015.
 */
@Qualifier(value = "qRequestAgreementRepository")
public interface RequestAgreementRepository extends JpaRepository<RequestAgreement, Long> {

    @Query("select count(RQ.id) from RequestAgreement RQ " +
            "inner join RQ.agreement AG " +
            "where AG.id=:agreementId and RQ.responseDate is null")
    Long countPendingRequestByAgreementId(@Param("agreementId") Long agreementId);

    @Query("select RQ from RequestAgreement RQ " +
            "inner join RQ.agreement AG " +
            "where AG.id=:agreementId and RQ.responseDate is null and RQ.requestType = :requestType order by RQ.id desc")
    List<RequestAgreement> getAllRequestByAgreementIdType(@Param("agreementId") Long agreementId, @Param("requestType") String requestType);

    @Query("select RQ from RequestAgreement RQ " +
            "inner join RQ.agreement AG " +
            "where AG.id=:agreementId and RQ.responseDate is not null and RQ.requestType = :requestType order by RQ.id desc")
    List<RequestAgreement> getAllResponsedRequestByAgreementIdType(@Param("agreementId") Long agreementId, @Param("requestType") String requestType);
}

