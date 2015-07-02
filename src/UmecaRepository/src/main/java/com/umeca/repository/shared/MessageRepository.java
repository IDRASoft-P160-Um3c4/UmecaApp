package com.umeca.repository.shared;

import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.RelMessageUserReceiver;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Long>{

    @Query("select new com.umeca.model.entities.reviewer.dto.LogNotificationDto(rm.id, m.title, m.body, m.creationDate, m.sender.fullname, 0) " +
            "from RelMessageUserReceiver rm " +
            "inner join rm.message m "+
            "where rm.user.id = :userId and m.isObsolete = false and rm.isObsolete = false ")
    List<LogNotificationDto> getMessagesByUserId(@Param("userId")Long userId, Pageable pageable);
}



