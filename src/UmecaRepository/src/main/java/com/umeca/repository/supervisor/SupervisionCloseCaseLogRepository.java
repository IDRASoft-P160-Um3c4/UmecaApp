package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AccompanimentInfo;
import com.umeca.model.entities.supervisor.SupervisionCloseCaseLog;
import org.springframework.data.domain.PageRequest;
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
@Repository("qSupervisionCloseCaseLogRepository")
public interface SupervisionCloseCaseLogRepository extends JpaRepository<SupervisionCloseCaseLog, Long> {

    @Query("select SCCL from SupervisionCloseCaseLog SCCL " +
            "inner join SCCL.caseDetention CD " +
            "where CD.id=:idCase order by SCCL.closeDate desc")
    public List<SupervisionCloseCaseLog> findLastCloseLog(@Param("idCase") Long idCase, Pageable pageable);

}


