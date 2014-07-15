package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFramingAddressRepository")
public interface FramingAddressRepository extends JpaRepository<FramingAddress, Long> {
}

