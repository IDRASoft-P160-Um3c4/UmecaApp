package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.CourseAchievementDto;
import com.umeca.model.dto.humanResources.EmployeeReferenceDto;
import com.umeca.model.entities.humanReources.CourseAchievement;
import com.umeca.model.entities.humanReources.EmployeeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qEmployeeReferenceRepository")
public interface EmployeeReferenceRepository extends JpaRepository<EmployeeReference, Long> {

    @Query("")
    EmployeeReferenceDto findReferenceDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idReference") Long idReference);

    @Query("")
    EmployeeReference findReferenceByIds(@Param("idEmployee") Long idEmployee, @Param("idReference") Long idReference);

}