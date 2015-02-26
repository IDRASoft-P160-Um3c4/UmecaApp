package com.umeca.repository.supervisor;

import com.umeca.model.entities.director.agenda.ActivityAgenda;
import com.umeca.model.entities.director.agenda.ActivityAgendaResponse;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository("qActivityAgendaRepository")
public interface ActivityAgendaRepository extends JpaRepository<ActivityAgenda, Long>{

    @Query("SELECT aa FROM ActivityAgenda aa " +
            "WHERE aa.id =:activityId AND aa.userOwner.id =:userId AND aa.status NOT IN :lstStatus ")
    ActivityAgenda findOneValid(@Param("activityId")Long activityId, @Param("userId")Long userId, @Param("lstStatus") List<String> lstStatus);

    @Query("SELECT new com.umeca.model.entities.director.agenda.ActivityAgendaResponse(aa.id," +
            "aa.end, aa.start, aa.priority.id, aa.status, aa.place, aa.description, aa.comments)" +
            "FROM ActivityAgenda aa " +
            "WHERE aa.status NOT IN :lstStatus AND aa.userOwner.id =:userId " +
            "AND (aa.searchStart =:yearmonthStart OR aa.searchEnd =:yearmonthStart OR aa.searchStart =:yearmonthEnd OR aa.searchEnd =:yearmonthEnd) ")
    List<ActivityAgendaResponse> getAllActivitiesByUser( @Param("lstStatus") List<String> lstStatus,
                                                                      @Param("yearmonthStart")int yearmonthStart,
                                                                      @Param("yearmonthEnd")int yearmonthEnd, @Param("userId")Long userId);
}


