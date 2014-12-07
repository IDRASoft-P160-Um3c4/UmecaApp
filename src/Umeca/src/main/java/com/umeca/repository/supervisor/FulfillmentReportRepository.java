package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FulfillmentReport;
import com.umeca.model.entities.supervisor.FulfillmentReportInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("fulfillmentReportRepository")
public interface FulfillmentReportRepository extends JpaRepository<FulfillmentReport,Long> {
    @Query("SELECT  new com.umeca.model.entities.supervisor.FulfillmentReportInfo(frt.name, fr.timestamp) FROM FulfillmentReport AS fr " +
            "INNER JOIN fr.fulfillmentReportType AS frt " +
            "WHERE fr.monitoringPlan.id=:monPlanId ORDER BY fr.id DESC")
    List<FulfillmentReportInfo> getFulfillmentReportInfoByMonPlanId(@Param("monPlanId") Long monPlanId, Pageable pageable);

}
