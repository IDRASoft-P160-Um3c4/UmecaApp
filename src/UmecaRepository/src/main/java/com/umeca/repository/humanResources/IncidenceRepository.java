package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.IncidenceDto;
import com.umeca.model.entities.timeAttendance.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 10/30/2015.
 */
public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

    @Query("select new com.umeca.model.dto.timeAttendance.IncidenceDto(i.id, i.name, i.date, i.reason) from IncidenceView i where i.id = :idIncidence")
    IncidenceDto getIncidence(@Param("idIncidence") Long idIncidence);
}
