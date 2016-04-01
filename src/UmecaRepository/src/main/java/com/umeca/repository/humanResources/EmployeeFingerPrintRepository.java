package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.FingerPrintWSDto;
import com.umeca.model.entities.humanReources.EmployeeFingerPrint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qEmployeeFingerPrintRepository")
public interface EmployeeFingerPrintRepository extends JpaRepository<EmployeeFingerPrint, Long> {

    @Query("select  new com.umeca.model.dto.timeAttendance.FingerPrintWSDto(ef.finger, ef.data) " +
            "from EmployeeFingerPrint ef " +
            "inner join ef.employee Em " +
            "where Em.id = :idEmployee")
    List<FingerPrintWSDto> getFingerPrints(@Param("idEmployee") Long idEmployee);


    @Query("select ef " +
            "from EmployeeFingerPrint ef " +
            "inner join ef.employee Em " +
            "where Em.id = :idEmployee and ef.finger = :finger")
    EmployeeFingerPrint getFingerPrint(@Param("idEmployee") Long idEmployee, @Param("finger") int finger);
}