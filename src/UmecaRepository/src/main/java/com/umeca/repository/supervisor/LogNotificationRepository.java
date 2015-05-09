package com.umeca.repository.supervisor;

import com.umeca.model.entities.reviewer.LogNotification;
import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qLogNotificationRepository")
public interface LogNotificationRepository extends JpaRepository<LogNotification,Long>{

    @Query("select new com.umeca.model.entities.reviewer.dto.LogNotificationDto(nr.id,nr.subject,nr.message,nr.timestamp,0) from LogNotification nr where nr.isObsolete=false and nr.receiveUser.id=:idUsr order by nr.timestamp")
    List<LogNotificationDto> getReviewerNotifications(@Param("idUsr")Long idUsr, Pageable pageable);

    @Query("select new com.umeca.model.entities.reviewer.dto.LogNotificationDto(nr.id,nr.subject,nr.message,nr.timestamp,0) " +
            "from LogNotification nr " +
            "inner join nr.senderUser SU "+
            "inner join nr.receiveUser RU " +
            "inner join RU.roles r "+
            "inner join SU.roles RO " +
            "where nr.isObsolete=false and r.role = :managerEval")
    List<LogNotificationDto> getManagerEvalNotifications(@Param("managerEval")String role, Pageable pageable);

}
