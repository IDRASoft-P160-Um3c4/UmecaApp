package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.EmployeeGeneralDataDto;
import com.umeca.model.entities.humanReources.EmployeeGeneralData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Rata on 05/03/2015.
 */
@Qualifier(value = "qEmployeeGeneralDataRepository")
public interface EmployeeGeneralDataRepository extends JpaRepository<EmployeeGeneralData, Long> {

    @Query("select GD from Employee E inner join E.employeeGeneralData GD where E.id=:idEmployee")
    EmployeeGeneralData getDataByEmployeeId(@Param(value = "idEmployee") Long idEmployee);

    @Query("select new com.umeca.model.dto.humanResources.EmployeeGeneralDataDto(E.id,E.name,E.lastNameP,E.lastNameM,E.gender," +
            "E.birthDate,MS.id,DT.id,GD.email,GD.identificationDesc,GD.phone,GD.certificate,GD.dependents," +
            "GD.noEmployee,GD.datePublicServ,GD.dateEntryUmeca,GD.isCommissioner,GD.noImss,GD.appointment,A.id) " +
            "from Employee E " +
            "left join E.employeeGeneralData GD " +
            "left join GD.identification DT " +
            "left join GD.maritalStatus MS " +
            "left join GD.address A " +
            "where E.id=:idEmployee")
    EmployeeGeneralDataDto getDataDtoByEmployeeId(@Param(value = "idEmployee") Long idEmployee);

    @Query("select new com.umeca.model.dto.humanResources.EmployeeGeneralDataDto(E.name,E.lastNameP,E.lastNameM,E.birthDate, E.gender, MS.name, " +
            "GD.phone, GD.dependents, DT.name, GD.identificationDesc, GD.email,GD.certificate, GD.noEmployee, GD.datePublicServ, GD.dateEntryUmeca, GD.isCommissioner, " +
            "GD.noImss, GD.appointment, A.addressString) "+
            "from Employee E " +
            "left join E.employeeGeneralData GD " +
            "left join GD.identification DT " +
            "left join GD.maritalStatus MS " +
            "left join GD.address A " +
            "where E.id=:idEmployee")
    EmployeeGeneralDataDto getSummaryDataByEmployeeId(@Param(value = "idEmployee") Long idEmployee);

}
