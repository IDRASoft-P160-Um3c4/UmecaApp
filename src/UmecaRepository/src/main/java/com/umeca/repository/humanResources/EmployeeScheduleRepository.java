package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.EmployeeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qEmployeeScheduleRepository")
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Long> {

}