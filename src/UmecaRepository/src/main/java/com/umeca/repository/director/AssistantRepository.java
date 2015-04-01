package com.umeca.repository.director;

import com.umeca.model.entities.director.minutes.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qAssistantRepository")
public interface AssistantRepository extends JpaRepository<Assistant, Long> {

    @Query("select A from Assistant A " +
            "where A.minute.id=:minuteId")
    List<Assistant> getAssistantsByMinuteId(@Param("minuteId") Long minuteId);

    @Query("select E.id from Assistant A " +
            "inner join A.employee E " +
            "inner join A.minute M " +
            "where M.id=:minuteId")
    List<Long> getAssistantsIdsByMinuteIds(@Param("minuteId") Long minuteId);

}
