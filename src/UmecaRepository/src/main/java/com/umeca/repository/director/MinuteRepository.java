package com.umeca.repository.director;

import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.entities.director.minutes.Minute;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qMinuteRepository")
public interface MinuteRepository extends JpaRepository<Minute, Long> {

    @Query("select new com.umeca.model.dto.director.MinuteDto(M.id, M.title, M.agenda, M.minuteDate, A.id, M.place, M.startTime, M.endTime, M.isFinished) from Minute M " +
            "inner join M.attendant A " +
            "where M.id = :minuteId")
    MinuteDto getMinuteDtoById(@Param("minuteId") Long minuteId);

    @Query("select new com.umeca.model.dto.director.MinuteDto(M.id, M.title, M.agenda, M.minuteDate, A.id, M.place, M.startTime, M.endTime, M.isFinished,A.name, A.lastNameP, A.lastNameP) from Minute M " +
            "inner join M.attendant A " +
            "where M.id = :minuteId")
    MinuteDto getMinuteGrlDataById(@Param("minuteId") Long minuteId);

    @Query("select new com.umeca.model.shared.SelectList(E.id, concat (E.name,' ',E.lastNameP,' ',E.lastNameM,' - ', D.name), R.description) from Minute M " +
            "inner join M.assistants A " +
            "inner join A.employee E " +
            "inner join E.district D " +
            "inner join E.users U " +
            "inner join U.roles R " +
            "where M.id = :minuteId")
    List<SelectList> getAssistantsDtoByMinuteId(@Param("minuteId") Long minuteId);

    @Query("select new com.umeca.model.shared.SelectList(E.id, concat (E.name,' ',E.lastNameP,' ',E.lastNameM,' - ', D.name), R.description) from Minute M " +
            "inner join M.attendant E " +
            "inner join E.district D " +
            "inner join E.users U " +
            "inner join U.roles R " +
            "where M.id = :minuteId")
    List<SelectList> getAattendantByMinuteId(@Param("minuteId") Long minuteId);
}
