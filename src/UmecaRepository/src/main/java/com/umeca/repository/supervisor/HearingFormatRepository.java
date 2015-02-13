package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AccomplishmentLogReport;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatDto;
import com.umeca.model.entities.supervisor.SupervisionLogReport;
import com.umeca.model.shared.SelectList;
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
public interface HearingFormatRepository extends JpaRepository<HearingFormat, Long> {

    @Query("SELECT hf.id FROM MonitoringPlan mp " +
            "INNER JOIN mp.caseDetention.hearingFormats hf " +
            "WHERE mp.id =:id and hf.isFinished=true ORDER BY hf.id DESC")
    List<Long> getLastHearingFormatByMonPlan(@Param("id") Long id, Pageable pageable);

    @Query("SELECT new com.umeca.model.entities.supervisor.SupervisionLogReport(hi.name, hi.lastNameP, hi.lastNameM, hf.judgeName, hf.defenderName, " +
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


    @Query("SELECT s.fullname FROM HearingFormat hf " +
            "INNER JOIN hf.caseDetention cd INNER JOIN hf.supervisor s " +
            "WHERE cd.id =:caseId ORDER BY hf.id DESC")
    List<String> findLastFullNameSupervisorByCaseId(@Param("caseId") Long caseId, Pageable pageable);

    @Query("SELECT s.id FROM HearingFormat hf " +
            "INNER JOIN hf.caseDetention cd INNER JOIN hf.supervisor s " +
            "WHERE cd.id =:caseId ORDER BY hf.id DESC")
    List<Long> findLastSupervisorIdByCaseId(@Param("caseId") Long caseId, Pageable pageable);

    @Query("SELECT hf from HearingFormat hf " +
            "INNER JOIN hf.caseDetention cd " +
            "WHERE cd.id =:caseId and hf.isFinished = true ORDER BY hf.id DESC")
    List<HearingFormat> findLastHearingFormatByCaseId(@Param("caseId") Long caseId, Pageable pageable);


    @Query("SELECT MAX (hf.id) from HearingFormat hf " +
            "INNER JOIN hf.caseDetention cd " +
            "WHERE cd.id =:caseId and hf.isFinished = false ORDER BY hf.id DESC")
    Long findHearingFormatIncomplete(@Param("caseId") Long caseId);


    @Query("SELECT new com.umeca.model.shared.SelectList(hfs.arrangementType,hf.registerTime) FROM MonitoringPlan mp " +
            "INNER JOIN mp.caseDetention cd " +
            "INNER JOIN cd.hearingFormats hf " +
            "INNER JOIN hf.hearingFormatSpecs hfs " +
            "WHERE mp.id =:monPlanId and hf.isFinished=true order by hf.registerTime asc")
    List<SelectList> getInfoResolution(@Param("monPlanId") Long monPlanId);

    @Query("SELECT hf.id from HearingFormat hf " +
            "INNER JOIN hf.caseDetention cd " +
            "WHERE cd.id =:caseId ORDER BY hf.id asc")
    List<Long> findHearingFormatIdsByIdCase(@Param("caseId") Long caseId);

    @Query("SELECT max(hf.id) from HearingFormat hf " +
            "INNER JOIN hf.caseDetention cd " +
            "WHERE cd.id =:caseId ORDER BY hf.id asc")
    Long lastHearingFormatIdsByIdCase(@Param("caseId") Long caseId);


    @Query("SELECT max(addr.id) from HearingFormat hf " +
            "INNER JOIN hf.caseDetention cd " +
            "INNER JOIN hf.hearingImputed hi " +
            "INNER JOIN hi.address addr " +
            "WHERE cd.id =:caseId ORDER BY hf.id asc")
    Long getLastFormatAddressByIdCase(@Param("caseId") Long caseId);

    @Query("select new com.umeca.model.entities.supervisor.HearingFormatDto(" +
            //"hf.room," +
            " hf.appointmentDate," +
            " hf.mpName, " +
            "hf.imputedPresence," +
            "ht.description, " +
            "hfs.arrangementType," +
            " hfs.linkageProcess," +
            "hfs.nationalArrangement" +
            ") " +
            "from HearingFormat hf " +
            "inner join hf.hearingFormatSpecs hfs " +
            "left join hf.hearingType ht " +
            "where hf.id=:idFormat")
    HearingFormatDto getInfoToLogCase(@Param("idFormat") Long idFormat);

    @Query("select hf.showNotification from HearingFormat hf where hf.id = :idFormat")
    Boolean getShowNotificationByIdFormat(@Param("idFormat") Long idFormat);


    @Query("SELECT HFS.arrangementType FROM Case C " +
            "INNER JOIN C.hearingFormats HF " +
            "INNER JOIN HF.hearingFormatSpecs HFS " +
            "WHERE C.id=:caseId and HF.isFinished=true order by hf.registerTime asc")
    List<Integer> getLastArrangementType(@Param("caseId") Long caseId, Pageable pageable);

}


