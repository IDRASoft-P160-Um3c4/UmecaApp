package com.umeca.repository.reviewer;

import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

    @Query("select i from Imputed as i where name=:name and lastNameP = :lnP and lastNameM = :lnM and dateBirth = :db")
    List<Imputed> findImputedRegister(@Param("name")String name, @Param("lnP")String lastNameP,@Param("lnM")String lastNameM,@Param("db") Date dateBirth);
}
