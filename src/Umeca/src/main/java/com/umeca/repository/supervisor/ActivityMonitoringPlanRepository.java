package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository("qActivityMonitoringPlanRepository")
public interface ActivityMonitoringPlanRepository extends JpaRepository<ActivityMonitoringPlan, Long>{

    @Query("SELECT amp FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN amp.caseDetention cd " +
            "WHERE amp.id =:actMonPlanId AND mp.id =:monPlanId AND cd.id =:caseId")
    ActivityMonitoringPlan findOneValid(@Param("actMonPlanId")Long activityId, @Param("monPlanId")Long monitoringPlanId, @Param("caseId")Long caseId);

    @Query("SELECT amp FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "WHERE mp.id =:monPlanId AND amp.status<>:status AND amp.isReplaced IS NULL")
    List<ActivityMonitoringPlan> findValidActivitiesBy(@Param("monPlanId")Long monitoringPlanId, @Param("status")String status);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringGroupInfo(amp.group, COUNT(amp.id)) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "WHERE mp.id =:monPlanId AND amp.status<>:status AND amp.isReplaced IS NULL " +
            "GROUP BY amp.group")
    List<ActivityMonitoringGroupInfo> findGroupInfoBy(@Param("monPlanId")Long monitoringPlanId, @Param("status")String status);

    @Query("SELECT count(amp.id) FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "WHERE mp.id =:monPlanId AND amp.status NOT IN :lstStatus")
    Long countActivitiesByNotInLstStatus(@Param("monPlanId") Long monPlanId, @Param("lstStatus") List<String> lstStatus);

    @Query("SELECT count(amp.id) FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "WHERE mp.id =:monPlanId AND amp.status IN :lstStatus")
    Long countActivitiesByInLstStatus(@Param("monPlanId") Long monPlanId, @Param("lstStatus") List<String> lstStatus);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanResponse(amp.id, mp.id, cd.id, cd.idMP," +
            "amp.end, amp.start, amp.supervisionActivity.id, amp.activityGoal.id, amp.status, im.name, im.lastNameP, im.lastNameM)" +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN mp.supervisor s " +
            "WHERE s.id =:userId AND mp.status IN :lstStatus " +
            "AND amp.status NOT IN :lstActStatus AND (amp.searchStart =:yearmonthStart OR amp.searchEnd =:yearmonthStart OR amp.searchStart =:yearmonthEnd OR amp.searchEnd =:yearmonthEnd)")
    List<ActivityMonitoringPlanResponse> getAllActivities(@Param("userId")Long userId, @Param("lstStatus") List<String> lstStatus,
                                                          @Param("lstActStatus") List<String> lstActStatus, @Param("yearmonthStart")int yearmonthStart,
                                                          @Param("yearmonthEnd")int yearmonthEnd);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanInfo(amp.id, mp.id, cd.id, cd.idMP, mp.status, " +
            "amp.end, amp.start, sa.name, ag.name, fssr.name, rs.name, amp.status, im.name, im.lastNameP, im.lastNameM, " +
            "sd.fullname, amp.comments, amp.doneTime, mp.generationTime, mp.authorizationTime, mp.posAuthorizationChangeTime) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN amp.supervisionActivity sa INNER JOIN amp.activityGoal ag INNER JOIN amp.framingSelectedSourceRel.framingReference fssr " +
            "INNER JOIN fssr.relationship rs LEFT JOIN amp.supervisorDone sd " +
            "WHERE amp.id =:actMonId")
    ActivityMonitoringPlanInfo getActivityInfo(@Param("actMonId")Long actMonId);

    @Query("SELECT amp FROM ActivityMonitoringPlan amp WHERE amp.id =:actMonPlanId AND amp.monitoringPlan.supervisor.id =:userId")
    ActivityMonitoringPlan findByIdAndUserId(@Param("actMonPlanId")Long actMonPlanId, @Param("userId")Long userId);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanLog(amp.id, amp.start, amp.end, amp.status, amp.supervisionActivity.id, " +
            "amp.framingSelectedSourceRel.id, amp.comments, user.username) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "INNER JOIN amp.supervisorCreate as user " +
            "WHERE mp.id =:monPlanId ORDER BY amp.start ")
    List<ActivityMonitoringPlanLog> getListByMonPlanId(@Param("monPlanId")Long monPlanId);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanLog(amp.id, amp.start, amp.end, amp.status, amp.supervisionActivity.id, " +
            "amp.framingSelectedSourceRel.id, amp.comments, user.username) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "INNER JOIN amp.supervisorCreate as user " +
            "INNER JOIN amp.lstAssignedArrangement as lstAA " +
            "WHERE mp.id =:monPlanId AND (lstAA.assignedArrangement.id = :assignedArrangementId OR 0 = :assignedArrangementId) " +
            "AND (amp.supervisionActivity.id = :activityId  OR 0 = :activityId)" +
            "ORDER BY amp.start ")
    List<ActivityMonitoringPlanLog> getListByMonPlanIdWhitArrangementId(@Param("monPlanId")Long monPlanId, @Param("assignedArrangementId") Long assignedArrangementId,
                                                                        @Param("activityId") Long activityId);

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

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice(amp.id, cd.id, cd.idMP," +
            "amp.end, amp.start, sa.name, ag.name, im.name, im.lastNameP, im.lastNameM)" +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN amp.supervisionActivity sa INNER JOIN amp.activityGoal ag INNER JOIN mp.supervisor s " +
            "WHERE s.id =:userId AND amp.start < :today AND mp.status NOT IN :lstStatus " +
            "AND amp.status IN :lstActStatus " +
            "ORDER BY amp.start ASC")
    List<ActivityMonitoringPlanNotice> getLstActivitiesBeforeTodayByUserId(@Param("userId") Long userId, @Param("lstStatus") ArrayList<String> lstStatus,
                                                                           @Param("lstActStatus") ArrayList<String> lstActStatus,
                                                                           @Param("today") Calendar today, Pageable pageable);

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice(amp.id, cd.id, cd.idMP," +
            "amp.end, amp.start, sa.name, ag.name, im.name, im.lastNameP, im.lastNameM)" +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN amp.supervisionActivity sa INNER JOIN amp.activityGoal ag INNER JOIN mp.supervisor s " +
            "WHERE s.id =:userId AND mp.status NOT IN :lstStatus " +
            "AND amp.status IN :lstActStatus AND amp.start >= :today AND  amp.end < :tomorrow " +
            "ORDER BY amp.start ASC")
    List<ActivityMonitoringPlanNotice> getLstActivitiesByUserIdAndDates(@Param("userId")Long userId, @Param("lstStatus") ArrayList<String> lstStatus,
                                                                        @Param("lstActStatus") ArrayList<String> lstActStatus,
                                                                        @Param("today") Calendar today, @Param("tomorrow") Calendar tomorrow, Pageable pageable);


    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanResponse(amp.id, mp.id, cd.id, cd.idMP," +
            "amp.end, amp.start, amp.supervisionActivity.id, amp.activityGoal.id, amp.status, im.name, im.lastNameP, im.lastNameM)" +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN mp.supervisor s INNER JOIN amp.supervisionActivity sa " +
            "WHERE s.id =:userId AND mp.status IN :lstStatus AND amp.isReplaced IS NULL AND (0l = :activityId OR sa.id = :activityId)" +
            "AND amp.status NOT IN :lstActStatus AND (amp.searchStart =:yearmonthStart OR amp.searchEnd =:yearmonthStart OR amp.searchStart =:yearmonthEnd OR amp.searchEnd =:yearmonthEnd)")
    List<ActivityMonitoringPlanResponse> getAllActivitiesWithFilters(@Param("userId")Long userId, @Param("lstStatus") List<String> lstStatus,
                                                                     @Param("lstActStatus") List<String> lstActStatus, @Param("yearmonthStart")int yearmonthStart,
                                                                     @Param("yearmonthEnd")int yearmonthEnd, @Param("activityId")Long activityId);


    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice(amp.id, amp.end, amp.start, sa.name, amp.status) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp " +
            "INNER JOIN amp.supervisionActivity sa " +
            "WHERE mp.id =:monPlanId AND amp.status IN :lstStatus " +
            "ORDER BY amp.start ASC")
    List<ActivityMonitoringPlanNotice> getAllActivitiesByMonPlanIdInStatus(@Param("monPlanId")Long monPlanId, @Param("lstStatus") List<String> lstStatus);


    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanInfo(amp.id, mp.id, cd.id, cd.idMP, mp.status, " +
            "amp.end, amp.start, amp.assignedArrangements, sa.name, ag.name, fssr.name, rs.name, amp.status, im.name, im.lastNameP, im.lastNameM, " +
            "sd.fullname, amp.comments, amp.doneTime, ampr.id) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN amp.supervisionActivity sa INNER JOIN amp.activityGoal ag INNER JOIN amp.framingSelectedSourceRel.framingReference fssr " +
            "INNER JOIN fssr.relationship rs LEFT JOIN amp.supervisorDone sd LEFT JOIN amp.actMonPlanToReplace ampr " +
            "WHERE amp.id =:actMonId")
    ActivityMonitoringPlanInfo getActivityInfoFull(@Param("actMonId")Long actMonId);


    @Query("SELECT DISTINCT new com.umeca.model.entities.supervisor.MonitoringPlanDto(mp.id, mp.generationTime, " +
            "mp.authorizationTime, mp.posAuthorizationChangeTime)" +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN mp.supervisor s " +
            "WHERE s.id =:userId AND mp.status IN :lstStatus AND amp.isReplaced IS NULL " +
            "AND amp.status NOT IN :lstActStatus AND (amp.searchStart =:yearmonthStart OR amp.searchEnd =:yearmonthStart OR amp.searchStart =:yearmonthEnd OR amp.searchEnd =:yearmonthEnd)")
    List<MonitoringPlanDto> getAllMonPlanWithFilters(@Param("userId")Long userId, @Param("lstStatus") List<String> lstStatus,
                                                     @Param("lstActStatus") List<String> lstActStatus, @Param("yearmonthStart")int yearmonthStart,
                                                     @Param("yearmonthEnd")int yearmonthEnd);
}


