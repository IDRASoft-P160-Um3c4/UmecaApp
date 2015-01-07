package com.umeca.repository.reviewer;

import com.umeca.model.entities.director.view.CaseRequestDto;
import com.umeca.model.entities.reviewer.CaseRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("caseRequestRepository")
public interface CaseRequestRepository extends JpaRepository<CaseRequest,Long> {

    @Query("select new com.umeca.model.entities.director.view.CaseRequestDto(" +
            "status.description," +
            "cr.requestType.description," +
            "rm.text," +
            "respm.text," +
            "rm.creationDate," +
            "respm.creationDate," +
            "rms.fullname," +
            "respms.fullname," +
            "rt.description) " +
            "from CaseRequest as cr " +
            "LEFT JOIN cr.responseType as rt " +
            "INNER JOIN cr.requestType as rtcr " +
            "INNER JOIN cr.requestMessage as rm " +
            "INNER JOIN rm.sender as rms " +
            "LEFT JOIN cr.responseMessage as respm " +
            "LEFT JOIN respm.sender as respms " +
            "INNER JOIN rm.caseDetention as c " +
            "INNER JOIN c.status as status " +
            "INNER JOIN c.meeting as m " +
            "inner join m.imputed as i where c.id =:idCase order by rm.creationDate asc ")
    List<CaseRequestDto> findRequestByIdCase(@Param("idCase") Long id);

    @Query("select max(r.id) from CaseRequest as r " +
            "inner join r.requestMessage.caseDetention as c " +
            "inner join r.requestType as rt " +
            "where c.id=:idCase and rt.name=:type")
    Long findLastRequestAuhtorizeIdByCase(@Param("idCase")Long c, @Param("type")String stRequestAuthorizeSource);

    @Query("SELECT cr FROM CaseRequest AS cr " +
            "INNER JOIN cr.requestMessage.caseDetention AS c " +
            "INNER JOIN cr.requestType AS rt " +
            "WHERE c.id=:idCase AND rt.name=:type " +
            "ORDER BY cr.id DESC")
    List<CaseRequest> findCaseRequestByCaseAndType(@Param("idCase")Long c, @Param("type")String stRequestType, Pageable pageable);

}
