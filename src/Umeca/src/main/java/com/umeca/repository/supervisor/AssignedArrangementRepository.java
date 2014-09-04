package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AssignedArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qAssignedArrangementRepository")
public interface AssignedArrangementRepository extends JpaRepository<AssignedArrangement, Long> {

    @Query("select AR from AssignedArrangement AR inner join AR.hearingFormat HF where HF.id=:idFormat")
    List<AssignedArrangement> getOldArrangements(@Param("idFormat") Long idFormat);
}

