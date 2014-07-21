package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingThreat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qFramingThreatRepository")
public interface FramingThreatRepository extends JpaRepository<FramingThreat, Long>{
    @Query("select ft from FramingThreat ft where ft.isObsolete=false order by ft.description")
    List<FramingThreat> findNoObsolete ();
}