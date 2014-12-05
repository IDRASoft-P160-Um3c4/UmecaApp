package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingThreat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qFramingThreatRepository")
public interface FramingThreatRepository extends JpaRepository<FramingThreat, Long> {
    @Query("select ft from FramingThreat ft where ft.isObsolete=false order by ft.description")
    List<FramingThreat> findNoObsolete();

    @Query("SELECT distinct (ft.id) FROM FramingMeeting fm " +
            "INNER JOIN fm.selectedThreatsRel str " +
            "INNER JOIN str.framingThreat ft " +
            "INNER JOIN fm.caseDetention cd " +
            "WHERE cd.id =:idCase")
    public List<Long> findSelectedThreat(@Param("idCase") Long idCase);

}