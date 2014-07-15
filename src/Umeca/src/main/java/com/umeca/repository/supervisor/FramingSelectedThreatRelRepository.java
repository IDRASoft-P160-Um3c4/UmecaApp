
package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingSelectedThreatRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFramingSelectedThreatRelRepository")
public interface FramingSelectedThreatRelRepository extends JpaRepository<FramingSelectedThreatRel, Long> {

}