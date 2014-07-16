package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AddictedAcquaintanceRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qAddictedAcquaintanceRelRepository")
public interface AddictedAcquaintanceRelRepository extends JpaRepository<AddictedAcquaintanceRel, Long> {
}