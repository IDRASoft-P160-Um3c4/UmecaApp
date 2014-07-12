package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingSelectedSourceRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFramingSelectedSourceRelRepository")
public interface FramingSelectedSourceRelRepository extends JpaRepository<FramingSelectedSourceRel, Long> {}
