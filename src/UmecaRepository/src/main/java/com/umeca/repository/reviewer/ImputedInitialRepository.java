package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.ImputedInitial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("imputedInitialRepository")
public interface ImputedInitialRepository extends JpaRepository<ImputedInitial, Long> {
    @Query("SELECT COUNT(i) FROM ImputedInitial i where rfc=:rfc")
    public Long countCaseSameRFC(@Param("rfc") String rfc);

    @Query("select i from ImputedInitial as i where name=:name and lastNameP = :lnP and lastNameM = :lnM and birthDate = :db")
    List<Imputed> findImputedRegister(@Param("name") String name, @Param("lnP") String lastNameP, @Param("lnM") String lastNameM, @Param("db") Date birthDate);
}
