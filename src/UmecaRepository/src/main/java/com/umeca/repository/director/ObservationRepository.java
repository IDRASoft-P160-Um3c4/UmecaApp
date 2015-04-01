package com.umeca.repository.director;

import com.umeca.model.entities.shared.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qObservationRepository")
public interface ObservationRepository extends JpaRepository<Observation, Long> {
}
