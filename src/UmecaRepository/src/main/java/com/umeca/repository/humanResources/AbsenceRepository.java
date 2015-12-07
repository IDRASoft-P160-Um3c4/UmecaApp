package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.AbsenceDto;
import com.umeca.model.entities.timeAttendance.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 10/28/2015.
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    @Query("select a from Absence a where a.period = :period and a.value < 1.0 and a.employee.id = :idEmployee")
    public Absence getAbsencePerPeriod(@Param("period") String period, @Param("idEmployee") Long idEmployee);

    @Query("select new com.umeca.model.dto.timeAttendance.AbsenceDto(a.id, a.name, a.date, a.type, a.reason, a.value) from AbsenceView a where a.id = :idAbsence")
    public AbsenceDto getAbsence(@Param("idAbsence") Long idAbsence);
}
