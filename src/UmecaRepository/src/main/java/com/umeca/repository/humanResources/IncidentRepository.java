package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.IncidentDto;
import com.umeca.model.entities.humanReources.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qIncidentRepository")
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    @Query("select new com.umeca.model.dto.humanResources.IncidentDto(I.id, E.id, IT.id,I.specIncidentType,I.comments,I.reason,I.incidentDate, GF.id) from Incident I " +
            "inner join I.incidentType IT " +
            "inner join I.employee E " +
            "inner join I.file GF " +
            "where I.id=:idIncident and E.id = :idEmployee")
    IncidentDto findIncidentDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idIncident") Long idIncident);

    @Query("select I from Incident I " +
            "inner join I.employee E " +
            "where I.id=:idIncident and E.id = :idEmployee")
    Incident findIncidentByIds(@Param("idEmployee") Long idEmployee, @Param("idIncident") Long idIncident);

}