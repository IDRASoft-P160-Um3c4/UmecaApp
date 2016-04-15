package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 4/15/2016.
 */
@Repository
public interface ActivityMonitoringPlanArrangementRepository extends JpaRepository<ActivityMonitoringPlanArrangement, Long> {
}
