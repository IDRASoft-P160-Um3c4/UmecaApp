package com.umeca.repository.supervisor;

import com.umeca.model.entities.director.agenda.ActivityAgenda;
import com.umeca.model.entities.director.agenda.ActivityAgendaNotice;
import com.umeca.model.entities.director.agenda.ActivityAgendaResponse;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanNotice;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    @Query("SELECT new com.umeca.model.entities.director.agenda.ActivityAgendaNotice(aa.id, aa.end, aa.start, aa.place, aa.description, aa.status, p.name, p.color)" +
            "FROM ActivityAgenda aa INNER JOIN aa.priority p " +
            "WHERE aa.userOwner.id =:userId AND aa.start < :today AND aa.status IN :lstActStatus " +
            "ORDER BY aa.start ASC")
    List<ActivityAgendaNotice> getLstActivitiesBeforeTodayByUserId(@Param("userId") Long userId, @Param("lstActStatus") ArrayList<String> lstActStatus,
                                                                           @Param("today") Calendar today, Pageable pageable);


    @Query("SELECT new com.umeca.model.entities.director.agenda.ActivityAgendaNotice(aa.id, aa.end, aa.start, aa.place, aa.description, aa.status, p.name, p.color)" +
            "FROM ActivityAgenda aa INNER JOIN aa.priority p " +
            "WHERE aa.userOwner.id =:userId AND aa.status IN :lstActStatus AND aa.start >= :today AND  aa.end < :tomorrow " +
            "ORDER BY aa.start ASC")
    List<ActivityAgendaNotice> getLstActivitiesByUserIdAndDates(@Param("userId")Long userId, @Param("lstActStatus") ArrayList<String> lstActStatus,
                                                                        @Param("today") Calendar today, @Param("tomorrow") Calendar tomorrow, Pageable pageable);

}
