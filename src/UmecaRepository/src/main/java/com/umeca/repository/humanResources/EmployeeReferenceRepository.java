package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.EmployeeReferenceDto;
import com.umeca.model.entities.humanReources.EmployeeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qEmployeeReferenceRepository")
public interface EmployeeReferenceRepository extends JpaRepository<EmployeeReference, Long> {

    @Query("select new com.umeca.model.dto.humanResources.EmployeeReferenceDto(ER.id, E.id, ER.name, ER.age, ER.phone, R.id,ER.specRelationship, ER.timeAgo) " +
            "from EmployeeReference ER " +
            "inner join ER.employee E " +
            "inner join ER.relationship R " +
            "where ER.id = :idReference and E.id =:idEmployee")
    EmployeeReferenceDto findReferenceDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idReference") Long idReference);

    @Query("select ER from EmployeeReference ER " +
            "inner join ER.employee E " +
            "where ER.id = :idReference and E.id =:idEmployee")
    EmployeeReference findReferenceByIds(@Param("idEmployee") Long idEmployee, @Param("idReference") Long idReference);

}