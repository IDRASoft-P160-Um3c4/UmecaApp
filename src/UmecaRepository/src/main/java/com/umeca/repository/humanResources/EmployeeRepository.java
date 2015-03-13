package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("qEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select count (E.id) from Employee E " +
            "where E.name=:nameEm and E.lastNameP=:lastP and E.lastNameM=:lastM and E.birthDate=:bDate")
    Long findExistEmployee(@Param("nameEm") String name, @Param("lastP") String lastNameM, @Param("lastM") String lastNameP, @Param("bDate") Date birthDate);
}