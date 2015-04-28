package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.EmployeeSchedule;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qEmployeeScheduleRepository")
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Long> {

    @Query("select new com.umeca.model.shared.SelectList(ES.id,ES.name,ES.description) " +
            "from EmployeeSchedule as ES " +
            "where ES.isObsolete = false and ES.id=:schId")
    SelectList findEmployeeScheduleDtoById(@Param("schId") Long employeeScheduleId);

    @Query("select new com.umeca.model.shared.SelectList(ES.id,ES.name,ES.description) " +
            "from EmployeeSchedule as ES " +
            "where ES.isObsolete = false")
    List<SelectList> findAllEmployeeSchedule();
}