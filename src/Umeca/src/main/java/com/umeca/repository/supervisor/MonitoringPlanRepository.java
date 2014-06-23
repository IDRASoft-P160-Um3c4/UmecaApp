package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ActivityGoal;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanInfo;
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
@Repository("qMonitoringPlanRepository")
public interface MonitoringPlanRepository extends JpaRepository<MonitoringPlan, Long>{

    @Query("SELECT new com.umeca.model.entities.supervisor.MonitoringPlanInfo(mp.id, cd.id, cd.idFolder, im.name, im.lastNameP, im.lastNameM, mp.status) FROM MonitoringPlan mp " +
            "INNER JOIN mp.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed im " +
            "WHERE mp.id =:monPlanId")
    MonitoringPlanInfo getInfoById(@Param("monPlanId")Long monPlanId);

    @Query("SELECT count(mp) FROM MonitoringPlan mp WHERE mp.id =:monPlanId AND mp.supervisor.id =:userId AND mp.caseDetention.id =:caseId AND mp.status IN :lstStatus")
    int isValidToUpsertDelete(@Param("userId")Long userId, @Param("caseId")Long caseId, @Param("monPlanId")Long monPlanId, @Param("lstStatus") List<String> lstStatus);

    @Query("SELECT mp FROM MonitoringPlan mp WHERE mp.id =:monPlanId AND mp.supervisor.id =:userId AND mp.caseDetention.id =:caseId")
    MonitoringPlan getStatus(@Param("userId")Long userId, @Param("caseId")Long caseId, @Param("monPlanId")Long monPlanId);
}

