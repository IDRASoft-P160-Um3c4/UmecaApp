package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qFramingReferenceRepository")
public interface FramingReferenceRepository extends JpaRepository<FramingReference, Long>{
}


