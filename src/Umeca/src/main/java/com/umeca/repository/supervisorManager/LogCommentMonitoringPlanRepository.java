package com.umeca.repository.supervisorManager;

import com.umeca.model.entities.supervisorManager.LogCommentMonitoringPlan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qLogCommentMonitoringPlanRepository")
public interface LogCommentMonitoringPlanRepository extends JpaRepository<LogCommentMonitoringPlan, Long>{

    @Query("SELECT lcmp.comments FROM LogCommentMonitoringPlan lcmp INNER JOIN lcmp.monitoringPlan mp " +
            "WHERE mp.id =:id AND lcmp.type =:type ORDER BY lcmp.id DESC")
    List<String> getLastCommentByMonPlanIdAndType(@Param("id") Long id, @Param("type") String type, Pageable pageable);
}


