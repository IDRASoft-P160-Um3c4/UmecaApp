package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.Channeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("channelingRepository")
public interface ChannelingRepository extends JpaRepository<Channeling, Long> {
}
