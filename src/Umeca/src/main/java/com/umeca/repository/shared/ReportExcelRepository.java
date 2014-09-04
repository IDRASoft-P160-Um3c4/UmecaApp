package com.umeca.repository.shared;

import com.umeca.model.catalog.Questionary;
import com.umeca.model.entities.director.view.ReportExcelFiltersDto;
import com.umeca.model.entities.reviewer.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("qReportExcelRepository")
public interface ReportExcelRepository extends JpaRepository<Case, Long> {


    @Query("select distinct (c.id) from Case as c where c.dateCreate between :initDate and :endDate")
    List<Long> findIdCasesByDates(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status as sc " +
            "where (sc.id in (:lstStCase)) and (c.id in (:lstCases))")
    List<Long> findIdCasesByStatusCase(@Param("lstStCase") List<Long> lstStCase, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.verification as ver " +
            "inner join ver.status as stver " +
            "where stver.id in :lstStVer and c.id in :lstCases")
    List<Long> findIdCasesByStatusVerification(@Param("lstStVer") List<Long> lstStVer, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as meet " +
            "inner join meet.status as stmeet " +
            "where (stmeet.id in (:lstStMeet)) and (c.id in (:lstCases))")
    List<Long> findIdCasesByStatusMeeting(@Param("lstStMeet") List<Long> lstStMeet, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "left join c.meeting as m " +
            "left join m.imputed as mimp " +
            "left join c.framingMeeting as fm " +
            "left join fm.personalData as fmpd " +
            "where (c.id in (:lstCases)) and ((mimp.gender in (:lstGnBool)) or (fmpd.gender in (:lstGnInt)))")
    List<Long> findIdCasesByGender(@Param("lstGnBool") List<Boolean> lstGndrBool, @Param("lstGnInt") List<Integer> lstGndrInt,
                                   @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "left join c.meeting as m " +
            "left join m.imputed as imp " +
            "left join imp.maritalStatus impms " +
            "left join c.framingMeeting as fm " +
            "left join fm.personalData as fmpd " +
            "left join fmpd.maritalStatus as fmpdms " +
            "where (c.id in (:lstCases)) and " +
            "((impms.id in (:lstMaritalSt)) or (fmpdms.id in (:lstMaritalSt)))")
    List<Long> findIdCasesByMaritalSt(@Param("lstMaritalSt") List<Long> lstMaritalSt, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "left join c.meeting as m " +
            "left join m.drugs as md " +
            "left join c.framingMeeting as fm " +
            "left join fm.drugs as fmd " +
            "where ((md.id in (:lstDrugs)) or (fmd.id in (:lstDrugs))) and (c.id in (:lstCases))")
    List<Long> findIdCasesByDrugs(@Param("lstDrugs") List<Long> lstDrugsIds, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.school as sch " +
            "inner join sch.degree as deg " +
            "inner join deg.academicLevel as alvl " +
            "where (alvl.id in (:lstAcademicLvlIds)) and (c.id in (:lstCases))")
    List<Long> findIdCasesByAcademicLvl(@Param("lstAcademicLvlIds") List<Long> lstAcademicLvl, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.technicalReview as tr " +
            "where (tr.levelRisk in (:lstRiskLvl)) and (c.id in (:lstCases))")
    List<Long> findIdCasesByRiskLvl(@Param("lstRiskLvl") List<Integer> lstRiskLvl, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.hearingFormats as HF " +
            "where HF.id in (select c1.id from Case as c1 " +
            "inner join c1.hearingFormats HF1 " +
            "inner join HF1.hearingFormatSpecs HF1S " +
            "where (c1.id in (:lstCases)) and (HF1S.arrangementType in (:lstHearingType)))")
    List<Long> findIdCasesByHearingType(@Param("lstHearingType") List<Integer> lstHearingType, @Param("lstCases") List<Long> lstCases);


    @Query("select distinct (c.id) from Case as c " +
            "inner join c.monitoringPlan as MonP " +
            "where (c.id in (:lstCases))")
    List<Long> findIdCasesWithMonP(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases)) and rt.id=1")
    List<Long> findIdCasesWithActualJob(@Param("lstCases") List<Long> lstCases);

}
