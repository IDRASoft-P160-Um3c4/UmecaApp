package com.umeca.repository.supervisor;

import com.umeca.model.entities.reviewer.LogNotificationReviewer;
import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qLogNotificationReviewerRepository")
public interface LogNotificationReviewerRepository extends JpaRepository<LogNotificationReviewer,Long>{

    @Query("select new com.umeca.model.entities.reviewer.dto.LogNotificationDto(nr.id,nr.subject,nr.message,nr.timestamp) from LogNotificationReviewer nr where nr.isObsolete=false and nr.receiveUser.id=:idUsr order by nr.timestamp")
    List<LogNotificationDto> getReviewerNotifications(@Param("idUsr")Long idUsr);

    @Query("select new com.umeca.model.entities.reviewer.dto.LogNotificationDto(nr.id,nr.subject,nr.message,nr.timestamp, true) " +
            "from LogNotificationReviewer nr " +
            "inner join nr.senderUser SU "+
            "inner join nr.receiveUser RU " +
            "inner join RU.roles r "+
            "inner join SU.roles RO " +
            "where nr.isObsolete=false and r.role = :managerEval")
    List<LogNotificationDto> getManagerEvalNotifications(@Param("managerEval")String role);

}
