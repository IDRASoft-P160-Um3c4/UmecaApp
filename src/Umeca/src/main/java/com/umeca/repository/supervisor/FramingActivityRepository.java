package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFramingActivityRepository")
public interface FramingActivityRepository extends JpaRepository<FramingActivity, Long> {
}
