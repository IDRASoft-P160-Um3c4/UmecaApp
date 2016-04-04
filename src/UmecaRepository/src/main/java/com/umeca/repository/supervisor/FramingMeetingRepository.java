package com.umeca.repository.supervisor;

import com.umeca.model.catalog.dto.ScheduleDto;
import com.umeca.model.entities.supervisor.FramingAddressDto;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisor.FramingReference;
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

    @Query("select F.id from ImputedFingerprint F " +
            "inner join F.imputed IMP " +
            "where IMP.id=:imputedId")
    List<Long> getFingerIdsByImputed(@Param("imputedId") Long imputedId);

    @Query("select F.id from ImputedFingerprint F " +
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

    @Query("select new com.umeca.model.entities.supervisor.FramingAddressDto(FA.id,ADD.addressString, FA.addressRef, FA.timeAgo, FA.timeLive, FA.reasonAnother, FA.phone, FA.reasonChange, FA.specification, RT.name, HT.name) from Case C " +
            "inner join C.framingMeeting FM " +
            "inner join FM.framingAddresses FA " +
            "inner join FA.address ADD " +
            "inner join FA.registerType RT " +
            "inner join FA.homeType HT " +
            "where C.id=:idCase")
    List<FramingAddressDto> getFramingAddressByIdCase(@Param("idCase") Long idCase);

    @Query("select new com.umeca.model.catalog.dto.ScheduleDto(SCH.id, FA.id, SCH.day,SCH.start,SCH.end) from Case C " +
            "inner join C.framingMeeting FM " +
            "inner join FM.framingAddresses FA " +
            "inner join FA.schedule SCH " +
            "where C.id=:idCase")
    List<ScheduleDto> getAddressScheduleByIdCase(@Param("idCase") Long idCase);

    @Query("select new com.umeca.model.catalog.dto.ScheduleDto(SCH.id, FA.id, SCH.day,SCH.start,SCH.end) from FramingMeeting FM " +
            "inner join FM.framingAddresses FA " +
            "inner join FA.schedule SCH " +
            "where FA.id=:idFA")
    List<ScheduleDto> getAddressScheduleByIdFramingAddress(@Param("idFA") Long idFA);

    @Query("select new com.umeca.model.shared.SelectList(FR.id, concat(FR.name,', ',R.name),FR.personType) from FramingMeeting FM " +
            "inner join FM.selectedSourcesRel SSR " +
            "inner join SSR.framingReference FR " +
            "inner join FR.relationship R " +
            "where FM.id=:idFraming and FR.id in (:idsSelected)")
    List<SelectList> getEnvironmentSources(@Param("idFraming") Long idFraming, @Param("idsSelected") List<Long> idsSelected);

    @Query("select FR.description from FramingMeeting FM " +
            "inner join FM.selectedRisksRel SRR " +
            "inner join SRR.framingRisk FR " +
            "where FM.id=:idFraming and FR.id in (:idsSelected)")
    List<String> getRiskByFramingId(@Param("idFraming") Long idFraming, @Param("idsSelected") List<Long> idsSelected);

    @Query("select FT.description from FramingMeeting FM " +
            "inner join FM.selectedThreatsRel STR " +
            "inner join STR.framingThreat FT " +
            "where FM.id=:idFraming and FT.id in (:idsSelected)")
    List<String> getRThreatByFramingId(@Param("idFraming") Long idFraming, @Param("idsSelected") List<Long> idsSelected);

    @Query("select FR from FramingMeeting FM " +
            "inner join FM.references FR " +
            "where FM.id=:idFraming and FR.personType in (:lstPersonType)")
    List<FramingReference> getReferencesByPersonType(@Param("idFraming") Long idFraming, @Param("lstPersonType") List<String> lstPersonType);

    @Query("select fm.id from FramingMeeting fm " +
            "where fm.caseDetention.id = :idCase")
    Long getIdByIdCase(@Param("idCase")Long id);
}


