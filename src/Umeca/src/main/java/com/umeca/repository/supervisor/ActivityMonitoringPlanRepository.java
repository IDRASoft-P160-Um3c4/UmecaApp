package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qActivityMonitoringPlanRepository")
public interface ActivityMonitoringPlanRepository extends JpaRepository<ActivityMonitoringPlan, Long>{

    @Query("SELECT amp FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN amp.caseDetention cd " +
            "WHERE amp.id =:actMonPlanId AND mp.id =:monPlanId AND cd.id =:caseId")
    ActivityMonitoringPlan findOneValid(@Param("actMonPlanId")Long activityId, @Param("monPlanId")Long monitoringPlanId, @Param("caseId")Long caseId);

    @Query("SELECT amp FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "WHERE mp.id =:monPlanId AND amp.status<>:status")
    List<ActivityMonitoringPlan> findValidActivitiesBy(@Param("monPlanId")Long monitoringPlanId, @Param("status")String status);

    @Query("SELECT count(amp.id) FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "WHERE mp.id =:monPlanId AND amp.status NOT IN :lstStatus")
    Long countActivitiesByStatus(@Param("monPlanId") Long monPlanId, @Param("lstStatus") List<String> lstStatus);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanResponse(amp.id, mp.id, cd.id, cd.idMP," +
            "amp.end, amp.start, amp.supervisionActivity.id, amp.activityGoal.id, amp.status, im.name, im.lastNameP, im.lastNameM)" +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "WHERE mp.supervisor.id =:userId AND mp.status IN :lstStatus " +
            "AND amp.status NOT IN :lstActStatus AND (amp.searchStart =:yearmonthStart OR amp.searchEnd =:yearmonthStart OR amp.searchStart =:yearmonthEnd OR amp.searchEnd =:yearmonthEnd)")
    List<ActivityMonitoringPlanResponse> getAllActivities(@Param("userId")Long userId, @Param("lstStatus") List<String> lstStatus,
                                                          @Param("lstActStatus") List<String> lstActStatus, @Param("yearmonthStart")int yearmonthStart,
                                                          @Param("yearmonthEnd")int yearmonthEnd);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanInfo(amp.id, mp.id, cd.id, cd.idMP, mp.status, " +
            "amp.end, amp.start, sa.name, ag.name, fssr.name, fssr.relationship, amp.status, im.name, im.lastNameP, im.lastNameM, " +
            "sd.fullname, amp.comments, amp.doneTime) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN amp.supervisionActivity sa INNER JOIN amp.activityGoal ag INNER JOIN amp.framingSelectedSourceRel.framingReference fssr LEFT JOIN amp.supervisorDone sd " +
            "WHERE amp.id =:actMonId")
    ActivityMonitoringPlanInfo getActivityInfo(@Param("actMonId")Long actMonId);

    @Query("SELECT amp FROM ActivityMonitoringPlan amp WHERE amp.id =:actMonPlanId AND amp.monitoringPlan.supervisor.id =:userId")
    ActivityMonitoringPlan findByIdAndUserId(@Param("actMonPlanId")Long actMonPlanId, @Param("userId")Long userId);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanLog(amp.id, amp.start, amp.end, amp.status, amp.supervisionActivity.id, " +
            "amp.framingSelectedSourceRel.id, amp.comments) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "WHERE mp.id =:monPlanId ORDER BY amp.start ")
    List<ActivityMonitoringPlanLog> getListByMonPlanId(@Param("monPlanId")Long monPlanId);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangementLog(laa.id, amp.id, aa.id, laa.status) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN amp.lstAssignedArrangement laa INNER JOIN laa.assignedArrangement aa " +
            "WHERE mp.id =:monPlanId ORDER BY amp.id ")
    List<ActivityMonitoringPlanArrangementLog> getListActMonPlanArrangementByMonPlanId(@Param("monPlanId")Long monPlanId);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanLog(amp.id, amp.start, amp.end, amp.status, amp.supervisionActivity.id, " +
            "amp.framingSelectedSourceRel.id, amp.comments) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN amp.lstAssignedArrangement laa " +
            "WHERE mp.id =:monPlanId AND (laa.status = 0 OR laa.status = 1) ORDER BY amp.start ")
    List<ActivityMonitoringPlanLog> getListAccomplishmentByMonPlanId(@Param("monPlanId")Long monPlanId);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangementLog(laa.id, amp.id, aa.id, laa.status) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN amp.lstAssignedArrangement laa INNER JOIN laa.assignedArrangement aa " +
            "WHERE mp.id =:monPlanId  AND (laa.status = 0 OR laa.status = 1) ORDER BY amp.id ")
    List<ActivityMonitoringPlanArrangementLog> getListAccomplishmentActMonPlanArrangementByMonPlanId(@Param("monPlanId")Long monPlanId);
}


