package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qFramingRiskRepository")
public interface FramingRiskRepository extends JpaRepository<FramingRisk, Long> {
    @Query("select fr from FramingRisk fr where fr.isObsolete=false order by fr.description")
    List<FramingRisk> findNoObsolete();

    @Query("SELECT distinct (fr.id) FROM FramingMeeting fm " +
            "INNER JOIN fm.selectedRisksRel srr " +
            "INNER JOIN srr.framingRisk fr " +
            "INNER JOIN fm.caseDetention cd " +
            "WHERE cd.id =:idCase")
    public List<Long> findSelectedRisk(@Param("idCase") Long idCase);

}


