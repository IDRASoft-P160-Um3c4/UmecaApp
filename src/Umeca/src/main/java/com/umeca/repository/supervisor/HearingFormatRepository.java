package com.umeca.repository.supervisor;

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
@Repository("qHearingFormatRepository")
public interface HearingFormatRepository extends JpaRepository<HearingFormat, Long>{

    @Query("SELECT hf.id FROM MonitoringPlan mp " +
            "INNER JOIN mp.caseDetention.hearingFormats hf " +
            "WHERE mp.id =:id ORDER BY hf.id DESC")
    List<Long> getLastHearingFormatByMonPlan(@Param("id") Long id, Pageable pageable);

    @Query("SELECT new com.umeca.model.entities.supervisor.SupervisionLogReport(hi.name, hi.lastNameP, hi.lastNameM, hf.crimes, hf.judgeName, hf.defenderName, " +
            "hf.mpName, hi.imputeTel, ad.street, ad.outNum, ad.addressString, fm.name, fm.lastNameP, fm.lastNameM, fm.phone) " +
            "FROM HearingFormat hf " +
            "INNER JOIN hf.hearingImputed hi " +
            "INNER JOIN hi.address ad " +
            "LEFT JOIN hf.caseDetention.framingMeeting.processAccompaniment fm " +
            "WHERE hf.id =:id ")
    SupervisionLogReport findSupervisionLogReportById(@Param("id") Long id);

    @Query("SELECT new com.umeca.model.entities.supervisor.AccomplishmentLogReport(hi.name, hi.lastNameP, hi.lastNameM, cd.idMP, ad.addressString) " +
            "FROM HearingFormat hf " +
            "INNER JOIN hf.hearingImputed hi " +
            "INNER JOIN hi.address ad " +
            "INNER JOIN hf.caseDetention cd " +
            "WHERE hf.id =:id ")
    AccomplishmentLogReport findSupervisionLogAccomplishmentById(@Param("id") Long id);
}


