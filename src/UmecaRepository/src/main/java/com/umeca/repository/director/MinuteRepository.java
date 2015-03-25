package com.umeca.repository.director;

import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.entities.director.minutes.Minute;
import org.apache.poi.util.LongField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qMinuteRepository")
public interface MinuteRepository extends JpaRepository<Minute, Long> {


    MinuteDto getMinuteDtoById(@Param("minuteId") Long minuteId);
}
