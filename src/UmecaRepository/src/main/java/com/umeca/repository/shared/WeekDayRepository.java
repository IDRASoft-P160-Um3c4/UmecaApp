package com.umeca.repository.shared;

import com.umeca.model.dto.humanResources.ScheduleDayDto;
import com.umeca.model.entities.shared.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qWeekDayRepository")
public interface WeekDayRepository extends JpaRepository<WeekDay, Long> {

    @Query("select new com.umeca.model.dto.humanResources.ScheduleDayDto(D.id, D.name) from WeekDay D where D.isObsolete = false")
    List<ScheduleDayDto> findAllNoObsolete();

}
