package com.umeca.repository.director;

import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.entities.director.minutes.Minute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qMinuteRepository")
public interface MinuteRepository extends JpaRepository<Minute, Long> {

    @Query("select new com.umeca.model.dto.director.MinuteDto(M.id, M.title, M.agenda, M.minuteDate, A.id, M.place, M.startTime, M.endTime) from Minute M " +
            "inner join M.attendant A " +
            "where M.id = :minuteId and M.isObsolete=false")
    MinuteDto getMinuteDtoById(@Param("minuteId") Long minuteId);


}
