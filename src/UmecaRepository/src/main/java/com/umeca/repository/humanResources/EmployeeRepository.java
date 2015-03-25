package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.shared.SelectList;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("qEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select count (E.id) from Employee E " +
            "where lower(E.name)=:nameEm and lower(E.lastNameP)=:lastP and lower(E.lastNameM)=:lastM and E.birthDate=:bDate")
    Long findExistEmployee(@Param("nameEm") String name, @Param("lastP") String lastNameP, @Param("lastM") String lastNameM, @Param("bDate") Date birthDate);

    @Query("select count (E.id) from Employee E " +
            "where lower(E.name)=:nameEm and lower(E.lastNameP)=:lastP and lower(E.lastNameM)=:lastM and E.birthDate=:bDate and E.id <> :idEmployee")
    Long findExistEmployeeWithId(@Param("nameEm") String name, @Param("lastP") String lastNameP, @Param("lastM") String lastNameM, @Param("bDate") Date birthDate, @Param("idEmployee") Long idEmployee);

    @Query("select new com.umeca.model.shared.SelectList(concat(E.name,' ',E.lastNameP,' ',E.lastNameM),P.description) " +
            "from Employee E " +
            "inner join E.post P " +
            "where E.id=:idEmployee")
    SelectList getEmployeeNameRoleById(@Param("idEmployee") Long idEmployee);


    @Query("select P.id from Employee E " +
            "inner join E.photo P " +
            "where E.id=:idEmployee")
    Long getIdPhotoByIdEmployee(@Param("idEmployee") Long idEmployee);

    @Query("select new com.umeca.model.shared.SelectList(E.id,concat(P.description,' - ',E.name,' ',E.lastNameP,' ',E.lastNameM)) from Employee E " +
            "inner join E.post P " +
            "where E.isObsolete=false")
    List<SelectList> getAllNoObsoleteEmployees();
}