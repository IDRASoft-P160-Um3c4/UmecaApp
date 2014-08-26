package com.umeca.repository.reviewer;
import com.umeca.model.entities.director.view.CaseRequestDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.CaseRequest;
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
            "respms.fullname ) " +
            "from CaseRequest as cr " +
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
}
