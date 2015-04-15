package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.RequestMinute;
import com.umeca.model.entities.humanReources.RequestMinute;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Rata on 05/03/2015.
 */
@Qualifier(value = "qRequestMinuteRepository")
public interface RequestMinuteRepository extends JpaRepository<RequestMinute, Long> {

    @Query("select count(RQ.id) from RequestMinute RQ " +
            "inner join RQ.minute M " +
            "where M.id=:minuteId and RQ.responseDate is null")
    Long countPendingRequestByMinuteId(@Param("minuteId") Long minuteId);

    @Query("select RQ from  RequestMinute RQ " +
            "inner join RQ.minute M " +
            "where M.id=:minuteId and RQ.responseDate is null and RQ.requestType = :requestType order by RQ.id desc")
    List<RequestMinute> getAllRequestByMinuteIdType(@Param("minuteId") Long minuteId, @Param("requestType") String requestType);

    @Query("select RQ from  RequestMinute RQ " +
            "inner join RQ.minute M " +
            "where M.id=:minuteId and RQ.responseDate is not null and RQ.requestType = :requestType order by RQ.id desc")
    List<RequestMinute> getAllResponsedRequestByMinuteIdType(@Param("minuteId") Long minuteId, @Param("requestType") String requestType);

}
