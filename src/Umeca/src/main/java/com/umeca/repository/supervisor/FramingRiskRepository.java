package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFramingRiskRepository")
public interface FramingRiskRepository extends JpaRepository<FramingRisk, Long>{

}


