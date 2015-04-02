package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.RequestMinute;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Rata on 05/03/2015.
 */
@Qualifier(value = "qRequestMinuteRepository")
public interface RequestMinuteRepository extends JpaRepository<RequestMinute, Long> {

    @Query("select count(RQ.id) from RequestMinute RQ " +
            "inner join RQ.minute M " +
            "where M.id=:minuteId and RQ.responseDate is null")
    Long countPendingRequestByMinuteId(@Param("agreementId") Long agreementId);

}
