package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.IncidentDto;
import com.umeca.model.dto.humanResources.VacationDto;
import com.umeca.model.entities.humanReources.Incident;
import com.umeca.model.entities.humanReources.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qVacationRepository")
public interface VacationRepository extends JpaRepository<Vacation, Long> {

    @Query("select new com.umeca.model.dto.humanResources.VacationDto(V.id,E.id,V.name,V.start,V.end,V.comments) from Vacation V " +
            "inner join V.employee E " +
            "where V.id=:idVacation and E.id=:idEmployee")
    VacationDto findVacationDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idVacation") Long idVacation);

    @Query("select V from Vacation V " +
            "inner join V.employee E " +
            "where V.id=:idVacation and E.id=:idEmployee")
    Vacation findVacationByIds(@Param("idEmployee") Long idEmployee, @Param("idVacation") Long idVacation);

}