package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingSelectedRiskRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFramingSelectedRiskRelRepository")
public interface FramingSelectedRiskRelRepository extends JpaRepository<FramingSelectedRiskRel, Long> {

}
