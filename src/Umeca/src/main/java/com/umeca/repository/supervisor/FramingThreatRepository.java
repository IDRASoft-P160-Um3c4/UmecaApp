package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingThreat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFramingThreatRepository")
public interface FramingThreatRepository extends JpaRepository<FramingThreat, Long>{

}