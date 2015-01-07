package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AccompanimentInfo;
import com.umeca.model.entities.supervisor.AccomplishmentLogReport;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.SupervisionLogReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qAccompanimentInfoRepository")
public interface AccompanimentInfoRepository extends JpaRepository<AccompanimentInfo, Long> {

    @Query("SELECT AI FROM FramingReference FR " +
            "INNER JOIN FR.accompanimentInfo AI " +
            "WHERE FR.id=:idRef")
    List<AccompanimentInfo> getAccompanimentInfoByIdRef(@Param("idRef") Long idRef, Pageable pageable);

}


