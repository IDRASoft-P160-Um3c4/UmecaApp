package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
            "amp.end, amp.start, sa.name, ag.name, ag.id, fssr.name, rs.name, amp.status, im.name, im.lastNameP, im.lastNameM, " +
            "sd.fullname, amp.comments, amp.doneTime, mp.generationTime, mp.authorizationTime, mp.posAuthorizationChangeTime, " +
            "ch.id, ch.name, cht.name, amp.channelingAssistance) " +
            "FROM ActivityMonitoringPlan amp " +
            "INNER JOIN amp.monitoringPlan mp " +
            "INNER JOIN mp.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN amp.supervisionActivity sa " +
            "INNER JOIN amp.activityGoal ag " +
            "INNER JOIN amp.framingSelectedSourceRel.framingReference fssr " +
            "INNER JOIN fssr.relationship rs " +
            "LEFT JOIN amp.supervisorDone sd " +
            "LEFT JOIN amp.channeling ch " +
            "LEFT JOIN ch.channelingType cht " +
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


    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanLog(amp.id, amp.start, amp.end, amp.status, amp.supervisionActivity.id, " +
            "amp.framingSelectedSourceRel.id, amp.comments, laa.status) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.monitoringPlan mp INNER JOIN amp.lstAssignedArrangement laa " +
            "WHERE mp.id =:monPlanId AND (laa.status = 0 OR laa.status = 1) ORDER BY amp.start ")
    List<ActivityMonitoringPlanLog> getListAccomplishmentByMonPlanIdToFile(@Param("monPlanId")Long monPlanId);


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
            "WHERE mp.status IN :lstStatus AND amp.isReplaced IS NULL AND (0l = :activityId OR sa.id = :activityId)" +
            "AND amp.status NOT IN :lstActStatus AND (amp.searchStart =:yearmonthStart OR amp.searchEnd =:yearmonthStart OR amp.searchStart =:yearmonthEnd OR amp.searchEnd =:yearmonthEnd) " +
            "AND (:idSup = 0l OR s.id=:idSup) AND (:idCase = 0l OR cd.id = :idCase) " +
            "AND (:findBlock =0 OR ((mp.generationTime < :refTime AND mp.authorizationTime IS NULL) OR (mp.authorizationTime IS NOT NULL AND mp.posAuthorizationChangeTime < :refTime )))" +
            "AND (:findActive = 0 OR (mp.authorizationTime IS NOT NULL " +
            "AND ((mp.posAuthorizationChangeTime IS NOT NULL AND mp.posAuthorizationChangeTime > :refTime) OR (mp.posAuthorizationChangeTime IS NULL))))")
    List<ActivityMonitoringPlanResponse> getAllActivitiesWithFilters( @Param("lstStatus") List<String> lstStatus,
                                                                     @Param("lstActStatus") List<String> lstActStatus, @Param("yearmonthStart")int yearmonthStart,
                                                                     @Param("yearmonthEnd")int yearmonthEnd, @Param("activityId")Long activityId,
                                                                     @Param("idSup") Long idSup,@Param("idCase") Long idCase, @Param("findBlock") Integer findBlock,
                                                                     @Param("refTime") Calendar refTime,@Param("findActive") Integer findActive);


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

    @Query("SELECT new com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangementLog(laa.status, concat(arr.description,'/',aa.description)) " +
            "FROM ActivityMonitoringPlan amp INNER JOIN amp.lstAssignedArrangement laa INNER JOIN laa.assignedArrangement aa " +
            "INNER JOIN aa.arrangement arr WHERE amp.id =:activityId")
    List<ActivityMonitoringPlanArrangementLog> getListActMonPlanArrangementByActivityIdToShow(@Param("activityId")Long activityId);

    @Query("SELECT COUNT(amp.id) FROM ActivityMonitoringPlan amp " +
            "WHERE amp.channeling.id = :channelingId")
    Long countInChanneling(@Param("channelingId")Long channelingId);


    @Query(value = "SELECT \n" +
            "\tamp.*\n" +
            "FROM \n" +
            "\timputed imp \n" +
            "\tinner join meeting mee \n" +
            "\ton imp.id_meeting = mee.id_meeting \n" +
            "\tinner join case_detention csd \n" +
            "\ton mee.id_case = csd.id_case \n" +
            "\tinner join monitoring_plan mp\n" +
            "\ton csd.id_case = mp.id_case\n" +
            "\tINNER JOIN activity_monitoring_plan amp \n" +
            "\tON mp.id_monitoring_plan = amp.id_monitoring_plan\n" +
            "\tinner join supervision_activity sup\n" +
            "\ton amp.id_supervision_activity = sup.id_supervision_activity,\n" +
            "\tassigned_arrangement aar\n" +
            "\tinner join cat_arrangement arr\n" +
            "\ton aar.id_arrangement = arr.id_arrangement\n" +
            "WHERE\n" +
            "\tmp.status = 'AUTORIZADO' AND FIND_IN_SET(aar.id_assigned_arrangement, REPLACE(amp.assigned_arrangements_ids, ' ', '')) \n" +
            "\tAND amp.status = 'NUEVA' \n" +
            "\tAND amp.start <= :date \n" +
            "\tAND amp.end >= :date \n" +
            "\tand imp.id_imputed = :idImputed\n" +
            "\tand sup.code = 'THD'\n" +
            "\tAND arr.id_arrangement IN (\n" +
            "\t\tSELECT \n" +
            "\t\t\tSUBSTRING_INDEX(SUBSTRING_INDEX(t.value_setting, ',', n.n), ',', -1) value\n" +
            "\t\tFROM \n" +
            "\t\t\tsystem_setting t CROSS\n" +
            "\t\t\tJOIN \n" +
            "\t\t\t(\n" +
            "\t\t\tSELECT a.N + b.N * 10 + 1 n\n" +
            "\t\t\tFROM \n" +
            "\t\t\t (\n" +
            "\t\t\t\tSELECT 0 AS N UNION ALL\n" +
            "\t\t\t\tSELECT 1 UNION ALL\n" +
            "\t\t\t\tSELECT 2 UNION ALL\n" +
            "\t\t\t\tSELECT 3 UNION ALL\n" +
            "\t\t\t\tSELECT 4 UNION ALL\n" +
            "\t\t\t\tSELECT 5 UNION ALL\n" +
            "\t\t\t\tSELECT 6 UNION ALL\n" +
            "\t\t\t\tSELECT 7 UNION ALL\n" +
            "\t\t\t\tSELECT 8 UNION ALL\n" +
            "\t\t\t\tSELECT 9) a\n" +
            "\t\t\t,(\n" +
            "\t\t\t\tSELECT 0 AS N UNION ALL\n" +
            "\t\t\t\tSELECT 1 UNION ALL\n" +
            "\t\t\t\tSELECT 2 UNION ALL\n" +
            "\t\t\t\tSELECT 3 UNION ALL\n" +
            "\t\t\t\tSELECT 4 UNION ALL\n" +
            "\t\t\t\tSELECT 5 UNION ALL\n" +
            "\t\t\t\tSELECT 6 UNION ALL\n" +
            "\t\t\t\tSELECT 7 UNION ALL\n" +
            "\t\t\t\tSELECT 8 UNION ALL\n" +
            "\t\t\t\tSELECT 9) b\n" +
            "\t\t\tORDER BY n\n" +
            "\t\t\t) n\n" +
            "\t\tWHERE \n" +
            "\t\t\tn.n <= 1 + (LENGTH(t.value_setting) - LENGTH(REPLACE(t.value_setting, ',', ''))) \n" +
            "\t\t\tAND t.group_setting = 'MONPLAN' \n" +
            "\t\t\tAND t.key_setting = 'ArrangementForAttendance'\n" +
            ")", nativeQuery = true)
    List<ActivityMonitoringPlan> getListAttendanceActivities(@Param("idImputed") long idImputed, @Param("date") Date date);

}


