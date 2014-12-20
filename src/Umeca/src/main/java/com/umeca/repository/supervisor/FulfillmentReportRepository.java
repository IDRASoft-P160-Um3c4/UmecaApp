package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FulfillmentReport;
import com.umeca.model.entities.supervisor.FulfillmentReportInfo;
import com.umeca.model.shared.SelectList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fulfillmentReportRepository")
public interface FulfillmentReportRepository extends JpaRepository<FulfillmentReport, Long> {
    @Query("SELECT  new com.umeca.model.entities.supervisor.FulfillmentReportInfo(fr.id, frt.name, fr.timestamp) FROM FulfillmentReport AS fr " +
            "INNER JOIN fr.fulfillmentReportType AS frt " +
            "WHERE fr.monitoringPlan.id=:monPlanId ORDER BY fr.id DESC")
    List<FulfillmentReportInfo> getFulfillmentReportInfoByMonPlanId(@Param("monPlanId") Long monPlanId, Pageable pageable);


    @Query("select new com.umeca.model.shared.SelectList(CD.id,FR.id,FRT.code,FR.status) from FulfillmentReport FR " +
            "inner join FR.monitoringPlan MP " +
            "inner join FR.fulfillmentReportType FRT " +
            "inner join MP.caseDetention CD " +
            "where (CD.id in(:lstCases)) and FR.status=com.umeca.model.shared.MonitoringConstants.LOG_ACCOMPLISHMENT_AUTHORIZED")
    List<SelectList> finAllSummaryInfoFulfillmentReportInfo(@Param("lstCases") List<Long> lstCases);

}
