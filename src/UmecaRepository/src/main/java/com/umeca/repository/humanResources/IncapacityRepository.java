package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.IncapacityDto;
import com.umeca.model.entities.humanReources.Incapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qIncapacityRepository")
public interface IncapacityRepository extends JpaRepository<Incapacity, Long> {

    @Query("select new com.umeca.model.dto.humanResources.IncapacityDto(I.id,E.id,I.docName,I.description,I.start,I.end,I.comments) from Incapacity I " +
            "inner join I.employee E " +
            "where I.id=:idIncapacity and E.id=:idEmployee")
    IncapacityDto findIncapacityDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idIncapacity") Long idIncapacity);

    @Query("select I from Incapacity I " +
            "inner join I.employee E " +
            "where I.id=:idIncapacity and E.id=:idEmployee")
    Incapacity findIncapacityByIds(@Param("idEmployee") Long idEmployee, @Param("idIncapacity") Long idIncapacity);

}