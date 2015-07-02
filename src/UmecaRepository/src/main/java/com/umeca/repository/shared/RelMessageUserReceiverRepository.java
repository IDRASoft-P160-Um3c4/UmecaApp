package com.umeca.repository.shared;

import com.umeca.model.entities.shared.RelMessageUserReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("relMessageUserReceiverRepository")
public interface RelMessageUserReceiverRepository extends JpaRepository<RelMessageUserReceiver, Long> {
    @Query("SELECT rm FROM RelMessageUserReceiver rm " +
            "WHERE rm.id =:id AND rm.user.id =:userId")
    RelMessageUserReceiver findOneByIdAndUserId(@Param("id")Long id, @Param("userId")Long userId);
}
