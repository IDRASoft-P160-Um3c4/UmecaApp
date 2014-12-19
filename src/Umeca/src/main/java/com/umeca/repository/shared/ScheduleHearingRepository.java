package com.umeca.repository.shared;

import com.umeca.model.entities.shared.ScheduleHearing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("scheduleHearingRepository")
public interface ScheduleHearingRepository extends JpaRepository<ScheduleHearing, Long> {
}
