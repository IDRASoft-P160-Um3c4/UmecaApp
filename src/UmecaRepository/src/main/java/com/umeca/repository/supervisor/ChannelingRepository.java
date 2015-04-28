package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.Channeling;
import com.umeca.model.entities.supervisor.ChannelingCaseView;
import com.umeca.model.entities.supervisor.ChannelingModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingRepository")
public interface ChannelingRepository extends JpaRepository<Channeling, Long> {

    @Query("SELECT new com.umeca.model.entities.supervisor.ChannelingModel(c.id, c.idMP, i.name, i.lastNameP, i.lastNameM, d.id, s.fullname) " +
            "FROM Case AS c " +
            "INNER JOIN c.meeting.imputed i " +
            "INNER JOIN c.district d " +
            "INNER JOIN c.framingMeeting.supervisor s " +
            "WHERE c.id =:caseId ")
    ChannelingModel getChannelingCaseViewByCaseId(@Param("caseId") Long id);


    @Query("SELECT C.consecutive FROM Channeling C " +
            "INNER JOIN C.caseDetention CD " +
            "WHERE CD.id =:caseId ORDER BY C.consecutive DESC")
    List<Long> getLastConsecutiveByCaseId(@Param("caseId") Long caseId, Pageable pageable);
}
