package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.ImputedHome;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imputedHomeRepository")
public interface ImputedHomeRepository extends JpaRepository<ImputedHome, Long> {

    @Query("select A.addressString from Meeting M " +
            "inner join M.imputedHomes IH " +
            "inner join IH.registerType RT " +
            "inner join IH.address A " +
            "where M.id=:idMeet and RT.id = com.umeca.model.shared.Constants.REGYSTER_TYPE_CURRENT")
    List<String> getActualAddressStringByVerificationId(@Param("idMeet") Long idMeet, Pageable pager);
}
