package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.ScheduleDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qScheduleDayRepository")
public interface ScheduleDayRepository extends JpaRepository<ScheduleDay, Long> {

}