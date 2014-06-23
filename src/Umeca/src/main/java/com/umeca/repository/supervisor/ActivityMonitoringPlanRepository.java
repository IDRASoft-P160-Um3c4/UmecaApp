package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ActivityMonitoringPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qActivityMonitoringPlanRepository")
public interface ActivityMonitoringPlanRepository extends JpaRepository<ActivityMonitoringPlan, Long>{

    @Query("SELECT amp FROM ActivityMonitoringPlan amp WHERE amp.id =:actMonPlanId AND amp.monitoringPlan.id =:monPlanId AND amp.caseDetention.id =:caseId")
    ActivityMonitoringPlan findOneValid(@Param("actMonPlanId")Long activityId, @Param("monPlanId")Long monitoringPlanId, @Param("caseId")Long caseId);

    @Query("SELECT amp FROM ActivityMonitoringPlan amp WHERE amp.monitoringPlan.id =:monPlanId AND amp.status<>:status")
    List<ActivityMonitoringPlan> findValidActivitiesBy(@Param("monPlanId")Long monitoringPlanId, @Param("status")String status);
}


