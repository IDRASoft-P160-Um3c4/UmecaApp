package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qFramingRiskRepository")
public interface FramingRiskRepository extends JpaRepository<FramingRisk, Long>{
    @Query("select fr from FramingRisk fr where fr.isObsolete=false order by fr.description")
    List<FramingRisk> findNoObsolete ();

}


