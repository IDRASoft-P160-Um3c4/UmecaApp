package com.umeca.repository.shared;

import com.umeca.model.entities.reviewer.Verification;
import com.umeca.model.entities.shared.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Long>{

}


