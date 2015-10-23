package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingSafetyFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FramingSafetyFactorRepository extends JpaRepository<FramingSafetyFactor, Long> {
    @Query("select ft from FramingSafetyFactor ft where ft.isObsolete=false order by ft.description")
    List<FramingSafetyFactor> findNoObsolete();

    @Query("SELECT distinct (ft.id) FROM FramingMeeting fm " +
            "INNER JOIN fm.selectedSafetyFactorsRel str " +
            "INNER JOIN str.framingSafetyFactor ft " +
            "INNER JOIN fm.caseDetention cd " +
            "WHERE cd.id =:idCase")
    public List<Long> findSelectedSafetyFactor(@Param("idCase") Long idCase);

}