package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisor.HearingFormat;
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
@Repository("qFramingMeetingRepository")
public interface FramingMeetingRepository extends JpaRepository<FramingMeeting, Long> {


    @Query("SELECT hf FROM MonitoringPlan mp " +
            "INNER JOIN mp.caseDetention.hearingFormats hf " +
            "WHERE mp.id =:id ORDER BY hf.id DESC")
    FramingMeeting findByCaseId(@Param("id") Long id);


    @Query("SELECT s.fullname FROM FramingMeeting fm " +
            "INNER JOIN fm.caseDetention cd INNER JOIN fm.supervisor s " +
            "WHERE cd.id =:caseId ORDER BY fm.id DESC")
    List<String> findLastSupervisorByCaseId(@Param("caseId") Long caseId, Pageable pageable);

    @Query("select ADD.addressString from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.framingAddresses FMA " +
            "left join FMA.address ADD " +
            "where CD.id=:idCase order by ADD.id ASC")
    List<String> firstFramingHouseByCase(@Param("idCase") Long idCase, Pageable pageable);

    @Query("select F.id from Fingerprint F " +
            "inner join F.imputed IMP " +
            "where IMP.id=:imputedId")
    List<Long> getFingerIdsByImputed(@Param("imputedId") Long imputedId);

    @Query("select F.id from Fingerprint F " +
            "inner join F.imputed IMP " +
            "where IMP.id=:imputedId")
    Boolean getValidateFingerConf();

    @Query("select new com.umeca.model.shared.SelectList(FT.id,FT.description) from Case C " +
            "inner join C.framingMeeting FM " +
            "inner join FM.selectedThreatsRel STR " +
            "inner join STR.framingThreat FT " +
            "where C.id=:idCase")
    List<Long> getSelectedThreatByIdCase(@Param("idCase") Long idCase);

    @Query("select new com.umeca.model.shared.SelectList(FR.id,FR.description) from Case C " +
            "inner join C.framingMeeting FM " +
            "inner join FM.selectedRisksRel SRR " +
            "inner join SRR.framingRisk FR " +
            "where C.id=:idCase")
    List<Long> getSelectedTRiskByIdCase(@Param("idCase") Long idCase);
}


