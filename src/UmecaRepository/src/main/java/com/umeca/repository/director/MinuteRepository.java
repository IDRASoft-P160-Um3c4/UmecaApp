package com.umeca.repository.director;

import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.entities.director.minutes.Minute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
