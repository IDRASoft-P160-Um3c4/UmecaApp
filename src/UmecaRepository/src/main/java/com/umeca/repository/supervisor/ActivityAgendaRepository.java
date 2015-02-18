package com.umeca.repository.supervisor;

import com.umeca.model.entities.director.agenda.ActivityAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qActivityAgendaRepository")
public interface ActivityAgendaRepository extends JpaRepository<ActivityAgenda, Long>{


    @Query("SELECT aa FROM ActivityAgenda aa " +
            "WHERE aa.id =:activityId AND aa.userOwner.id =:userId AND aa.status <> :status")
    ActivityAgenda findOneValid(@Param("activityId")Long activityId, @Param("userId")Long userId, @Param("status")String status);
}


