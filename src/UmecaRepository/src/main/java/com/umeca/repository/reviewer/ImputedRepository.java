package com.umeca.repository.reviewer;

import com.umeca.model.dto.humanResources.ImputedDto;
import com.umeca.model.dto.humanResources.ImputedFingerPrintDto;
import com.umeca.model.dto.timeAttendance.UserInfoWsDto;
import com.umeca.model.entities.reviewer.Imputed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 3:55 PM
 */

@Repository("imputedRepository")
public interface ImputedRepository extends JpaRepository<Imputed, Long>{
    @Query("SELECT COUNT(i) FROM Imputed i where rfc=:rfc")
    public Long countCaseSameRFC(@Param("rfc") String rfc);

    @Query("select i from Imputed as i where name=:name and lastNameP = :lnP and lastNameM = :lnM and birthDate = :db")
    List<Imputed> findImputedRegister(@Param("name")String name, @Param("lnP")String lastNameP,@Param("lnM")String lastNameM,@Param("db") Date birthDate);

    @Query("select new com.umeca.model.dto.humanResources.ImputedDto(id, name, lastNameP, lastNameM) from Imputed where id = :imputed")
    List<ImputedDto> getImputed(@Param("imputed")long imputed);

    @Query("select new com.umeca.model.dto.humanResources.ImputedFingerPrintDto(f.id, f.finger, f.data) from ImputedFingerprint as f inner join f.imputed as i where i.id = :imputed")
    List<ImputedFingerPrintDto> getImputedFingerPrint(@Param("imputed")long imputed);


    @Query("select new com.umeca.model.dto.timeAttendance.UserInfoWsDto(id, concat(name,' ',lastNameP,' ',lastNameM)) " +
            "from Imputed ")
    List<UserInfoWsDto> getImputedUsersWs();




}