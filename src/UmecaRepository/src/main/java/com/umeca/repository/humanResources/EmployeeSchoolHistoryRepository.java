package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.EmployeeSchoolHistoryDto;
import com.umeca.model.entities.humanReources.EmployeeSchoolHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qEmployeeSchoolHistoryRepository")
public interface EmployeeSchoolHistoryRepository extends JpaRepository<EmployeeSchoolHistory, Long> {

    @Query("select new com.umeca.model.dto.humanResources.EmployeeSchoolHistoryDto(E.id, SH.school, D.id, AL.id, SDT.id,SH.specDocType) " +
            "from Employee E " +
            "inner join E.employeeSchoolHistory SH " +
            "inner join SH.schoolDocumentType SDT " +
            "inner join SH.degree D " +
            "inner join D.academicLevel AL " +
            "where E.id =:idEmployee ")
    EmployeeSchoolHistoryDto getEmpSchoolHistDtoByIdEmployee(@Param("idEmployee") Long idEmployee);

    @Query("select SH from Employee E " +
            "inner join E.employeeSchoolHistory SH " +
            "where E.id =:idEmployee ")
    EmployeeSchoolHistory getEmpSchoolHistByIdEmployee(@Param("idEmployee") Long idEmployee);
}