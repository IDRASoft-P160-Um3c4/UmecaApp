package com.umeca.repository.catalog;

import com.umeca.model.catalog.Arrangement;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qArrangementRepository")
public interface ArrangementRepository extends JpaRepository<Arrangement, Long>{

    @Query("SELECT arr FROM Arrangement arr WHERE arr.type =:typeId and arr.isNational=:isNational and arr.isObsolete=false")
    List<Arrangement> findByType(@Param("isNational")Boolean national, @Param("typeId")Integer typeId);

    @Query("SELECT new com.umeca.model.shared.SelectList(aa.id, aa.arrangement.description, aa.description) FROM HearingFormat hf " +
            "INNER JOIN hf.assignedArrangements aa " +
            "WHERE hf.id =:id")
    List<SelectList> findLstArrangement(@Param("id")Long id);

    @Query("SELECT new com.umeca.model.shared.SelectList(laa.id, arr.description, laa.description) FROM ActivityMonitoringPlan amp " +
            "INNER JOIN amp.lstAssignedArrangement laa INNER JOIN laa.arrangement arr " +
            "WHERE amp.id =:id")
    List<SelectList> findArrangementById(@Param("id") Long id);
}
