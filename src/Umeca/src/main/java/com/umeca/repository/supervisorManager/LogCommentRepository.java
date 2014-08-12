package com.umeca.repository.supervisorManager;

import com.umeca.model.entities.supervisorManager.CommentMonitoringPlanNotice;
import com.umeca.model.entities.supervisorManager.LogComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qLogCommentMonitoringPlanRepository")
public interface LogCommentRepository extends JpaRepository<LogComment, Long>{

    @Query("SELECT lcmp.comments FROM LogComment lcmp INNER JOIN lcmp.monitoringPlan mp " +
            "WHERE mp.id =:id AND lcmp.type =:type ORDER BY lcmp.id DESC")
    List<String> getLastCommentByMonPlanIdAndType(@Param("id") Long id, @Param("type") String type, Pageable pageable);

    @Query("SELECT new com.umeca.model.entities.supervisorManager.CommentMonitoringPlanNotice(lcmp.id, lcmp.type, lcmp.action, su.fullname, ru.fullname, " +
            "lcmp.timestamp, lcmp.comments, cd.id, cd.idMP, im.name, im.lastNameP, im.lastNameM) " +
            "FROM LogComment lcmp " +
            "LEFT JOIN lcmp.monitoringPlan.supervisor s " +
            "INNER JOIN lcmp.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN lcmp.senderUser su " +
            "LEFT JOIN lcmp.receiveUser ru " +
            "WHERE (s.id =:userId OR ru.id =:userId) AND su.id <>:userId AND lcmp.isObsolete = false ORDER BY lcmp.id DESC")
    List<CommentMonitoringPlanNotice> getEnabledCommentsByUserId(@Param("userId")Long userId);

    @Query("SELECT new com.umeca.model.entities.supervisorManager.CommentMonitoringPlanNotice(lcmp.id, lcmp.type, lcmp.action, su.fullname, ru.fullname, " +
            "lcmp.timestamp, lcmp.comments, cd.id, cd.idMP, im.name, im.lastNameP, im.lastNameM) FROM LogComment lcmp " +
            "INNER JOIN lcmp.caseDetention cd INNER JOIN cd.meeting.imputed im " +
            "INNER JOIN lcmp.senderUser su LEFT JOIN lcmp.receiveUser ru " +
            "WHERE su.id <>:userId AND lcmp.action IN :lstActions AND lcmp.isObsolete = false ORDER BY lcmp.id DESC")
    List<CommentMonitoringPlanNotice> getEnabledCommentsByManagerSupRole(@Param("lstActions") List<String> lstActions, @Param("userId")Long userId);

    @Query("SELECT lcmp FROM LogComment lcmp " +
            "LEFT JOIN lcmp.monitoringPlan.supervisor s " +
            "LEFT JOIN lcmp.receiveUser ru " +
            "WHERE lcmp.id =:id AND (s.id =:userId OR (s.id IS NULL AND ru.id =:userId))")
    LogComment getCommentByCommentIdAndUserId(@Param("id")Long id, @Param("userId")Long userId);

    @Query("SELECT lcmp FROM LogComment lcmp " +
            "INNER JOIN lcmp.senderUser su " +
            "WHERE lcmp.id =:id AND su.id <>:userId")
    LogComment getCommentByCommentIdAndNotSenderUserId(@Param("id")Long id, @Param("userId")Long userId);

}


