package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ActivityGoal;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qActivityGoalRepository")
public interface ActivityGoalRepository extends JpaRepository<ActivityGoal, Long> {

    @Query("SELECT new com.umeca.model.shared.SelectList(ag.id, ag.name, ag.specification) FROM ActivityGoal ag " +
            "inner join ag.activityGroup agroup WHERE ag.isObsolete=false and agroup.code = 'MONITORING_PLAN'")
    List<SelectList> findAllValidMonitoring();

    @Query("SELECT new com.umeca.model.shared.SelectList(ag.id, ag.name) FROM ActivityGoal ag " +
            "inner join ag.activityGroup agroup where agroup.code='MONITORING_PLAN'")
    List<SelectList> findAllSlMonitoring();

    @Query("SELECT DISTINCT new com.umeca.model.shared.SelectList(ag.id, ag.name) FROM ActivityMonitoringPlan amp " +
            "INNER JOIN amp.monitoringPlan mp INNER JOIN amp.activityGoal ag " +
            "WHERE mp.id =:id")
    List<SelectList> findByMonPlanId(@Param("id") Long id);

    @Query("SELECT new com.umeca.model.shared.SelectList(ag.id, ag.name) FROM ActivityGoal ag " +
            "WHERE ag.isObsolete=false")
    List<SelectList> findAllForView();
}


