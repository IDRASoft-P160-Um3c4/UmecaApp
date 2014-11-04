package com.umeca.repository.shared;

import com.umeca.model.entities.shared.Victim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface VictimRepository extends JpaRepository<Victim, Long> {

    @Query("select count (v) from Victim as v " +
            "inner join v.criminalProceeding cp " +
            "inner join cp.meeting m " +
            "inner join m.caseDetention as c " +
            "where c.id=:idCase")
    Long  sizeVictimLegalByIdCase(@Param("idCase")Long idCase);
}
