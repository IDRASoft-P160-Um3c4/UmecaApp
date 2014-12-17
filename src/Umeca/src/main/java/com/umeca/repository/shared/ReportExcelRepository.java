package com.umeca.repository.shared;

import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisor.*;
import com.umeca.service.shared.CrimeService;
import org.springframework.data.domain.Pageable;
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
            "where (stver.id in (:lstStVer)) and (c.id in (:lstCases))")
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
            "left join c.meeting as m " +
            "left join m.imputedHomes ih " +
            "left join ih.address iha " +
            "left join iha.location ihal " +
            "left join c.framingMeeting as fm " +
            "left join fm.framingAddresses as fma " +
            "left join fma.address as fmad " +
            "left join fmad.location as fmadl " +
            "left join c.hearingFormats as hf " +
            "left join hf.hearingImputed as hfi " +
            "left join hfi.address as hfia " +
            "left join hfia.location as hfial " +
            "where (c.id in (:lstCases)) and " +
            "((ihal.id = :locationId) or (fmadl.id = :locationId) or (hfial.id = :locationId))")
    List<Long> findIdCasesByLocation(@Param("lstCases") List<Long> lstCases, @Param("locationId") Long locId);


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

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.jobs as j " +
            "inner join j.registerType as rt " +
            "where ((c.id in (:lstCases)) and ((rt.id <> 1) or (rt.id is null)))")
    List<Long> findIdCasesWithOutActualJob(@Param("lstCases") List<Long> lstCases);

    /*summary querys*/

    @Query("select count(c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.imputed imp " +
            "left join c.framingMeeting as fm " +
            "left join fm.personalData as fmpd " +
            "where (c.id in (:lstCases)) and (imp.gender =:genderB or fmpd.gender=:genderI)")
    Long countGender(@Param("lstCases") List<Long> lstCases, @Param("genderB") Boolean genderB, @Param("genderI") Integer genderI);

    @Query("select count(c.id) from Case as c " +
            "left join c.meeting as m " +
            "left join m.imputed imp " +
            "left join imp.maritalStatus ms " +
            "left join c.framingMeeting as fm " +
            "left join fm.personalData as fmpd " +
            "left join fmpd.maritalStatus as fmpdms " +
            "where (c.id in (:lstCases)) and ((ms.id=:marSt) or (fmpdms.id=:marSt)) ")
    Long countMarSt(@Param("lstCases") List<Long> lstCases, @Param("marSt") Long marSt);

    @Query("select count(c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases)) and rt.id=1")
    Long countCurrentJob(@Param("lstCases") List<Long> lstCases);

    @Query("select count(c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.school sch " +
            "inner join sch.degree deg " +
            "inner join deg.academicLevel alvl " +
            "where (c.id in (:lstCases)) and alvl.id=:acLvl")
    Long countAcLvl(@Param("lstCases") List<Long> lstCases, @Param("acLvl") Long acLvl);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as meet " +
            "inner join meet.status as stmeet " +
            "where (stmeet.name in (:lstStMeet)) and (c.id in (:lstCases))")
    List<Long> findIdCasesByStatusMeetingStr(@Param("lstStMeet") List<String> lstStMeet, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.verification as ver " +
            "inner join ver.status as stver " +
            "where ((stver.name in (:lstStVer)) and (c.id in (:lstCases)))")
    List<Long> findIdCasesByStatusVerificationStr(@Param("lstStVer") List<String> lstStVer, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status as sc " +
            "where (sc.name in (:lstStCase)) and (c.id in (:lstCases))")
    List<Long> findIdCasesByStatusCaseStr(@Param("lstStCase") List<String> lstStCase, @Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.entities.supervisor.HearingFormatInfo(C.id,HF.id,HF.idFolder, HF.idJudicial,HF.room,HF.initTime,HF.endTime, HF.judgeName, HF.mpName, HF.defenderName, " +
            "HFIM.name, HFIM.lastNameP, HFIM.lastNameM, HFIM.birthDate, HFIM.imputeTel, HFIMA.addressString,HFSP.controlDetention, HFSP.imputationFormulation, " +
            "HFSP.extension, HFSP.extDate, HFSP.linkageProcess, HFSP.linkageRoom, HFSP.linkageDate, HFSP.linkageTime, HFSP.arrangementType, HFSP.nationalArrangement, HF.terms, HF.registerTime," +
            "HT.description, HF.imputedPresence, HF.hearingResult, HF.hearingTypeSpecification) " +
            "from Case C " +
            "inner join C.hearingFormats HF " +
            "inner join HF.hearingImputed HFIM " +
            "inner join HFIM.address HFIMA " +
            "inner join HF.hearingFormatSpecs HFSP " +
            "inner join HF.supervisor HFUSR " +
            "left join HF.hearingType HT " +
            "where (C.id in(:lstCasesIds))")
    List<HearingFormatInfo> getHearingFormatInfo(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select ARR.description from HearingFormat HF " +
            "inner join HF.assignedArrangements HFA " +
            "inner join HFA.arrangement ARR " +
            "where (HF.id=:formatId)")
    List<String> getArrangementsByFormat(@Param("formatId") Long formatId);

    @Query("SELECT hf.id FROM Case CD " +
            "inner join CD.hearingFormats hf " +
            "WHERE CD.id =:id and hf.isFinished=true ORDER BY hf.id DESC")
    List<Long> getLastHearingFormatByCase(@Param("id") Long id, Pageable pageable);

    @Query("select CON from HearingFormat HF " +
            "inner join HF.contacts CON " +
            "where (HF.id=:formatId)")
    List<ContactData> getContactsByFormat(@Param("formatId") Long formatId);

    //TODO CAMBIAR OCC.name 1 por el texto de actividades
    @Query("select new com.umeca.model.entities.supervisor.FramingMeetingInfo(CD.id,CD.idFolder,CD.idMP,CD.dateCreate,PD.name,PD.lastNameP,PD.lastNameM,PD.gender," +
            "MS.name,PD.maritalStatusYears,BC.name,BS.name,PD.birthState,PD.birthDate,PD.physicalCondition," +
            "OCC.name, OCC.place, OCC.phone,AFQ.observations,AFQ.addictionTreatment,AFQ.addictionTreatmentInstitute," +
            "AFQ.addictionTreatmentDate,AFQ.addictedAcquaintance,AFQ.relativeAbroad,AFQ.obligationIssue) " +
            "from Case CD " +
            "inner join CD.framingMeeting as FM " +
            "left join FM.personalData PD " +
            "left join PD.maritalStatus MS " +
            "left join PD.birthCountry BC " +
            "left join PD.birthStateCmb BS " +
            "left join FM.occupation OCC " +
            "left join FM.additionalFramingQuestions AFQ " +
            "where (CD.id in (:lstCasesIds))")
    List<FramingMeetingInfo> getFramingMeetingInfo(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.FramingReferenceInfo" +
            "(CD.id,REF.name,REF.phone,REL.name,REF.address,REF.age,REF.occupation,REF.personType,REF.isAccompaniment," +
            "AI.gender,AI.occupationPlace,AL.name,ADD.addressString,REF.specificationRelationship, REF.hasVictimWitnessInfo) " +
            "from Case CD " +
            "inner join CD.framingMeeting FM " +
            "inner join FM.references REF " +
            "inner join REF.relationship REL " +
            "left join REF.accompanimentInfo AI " +
            "left join AI.address ADD " +
            "left join AI.academicLevel AL " +
            "where (CD.id in (:lstCasesIds))")
    List<FramingReferenceInfo> getFramingReferenceInfo(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.catalog.dto.CatalogDto(CD.id,ADD.addressString) from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.framingAddresses FMA " +
            "left join FMA.address ADD " +
            "where (CD.id in (:lstCasesIds))")
    List<CatalogDto> getFramingHomes(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelDrugDto(CDET.id, DT.name, PER.name, DRUG.quantity, DRUG.lastUse, DRUG.specificationType, DRUG.specificationPeriodicity, DRUG.block) " +
            "from Case CDET " +
            "left join CDET.framingMeeting FM " +
            "left join FM.drugs DRUG " +
            "left join DRUG.periodicity PER " +
            "left join DRUG.drugType DT " +
            "where CDET.id in (:lstCasesIds) order by DRUG.block")
    List<ExcelDrugDto> getFramingInfoDrugs(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.catalog.dto.CatalogDto(CD.id,REL.name) from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.additionalFramingQuestions AFQ " +
            "left  join AFQ.addictedAcquaintancesRel AFQR " +
            "left join AFQR.relationship REL " +
            "where (CD.id in (:lstCasesIds))")
    List<CatalogDto> getFramingAddictedAcquaintances(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ObligationIssuesInfo(CD.id, REL.name, AFQR.address) " +
            "from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.additionalFramingQuestions AFQ " +
            "left  join AFQ.relativesAbroadRel AFQR " +
            "left join AFQR.relationship REL " +
            "where (CD.id in (:lstCasesIds))")
    List<ObligationIssuesInfo> getFramingRelativesAbroad(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ObligationIssuesInfo(CD.id, ARR.description, OI.cause) " +
            "from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.additionalFramingQuestions AFQ " +
            "left  join AFQ.obligationIssues OI " +
            "left join OI.arrangement ARR " +
            "where (CD.id in (:lstCasesIds))")
    List<ObligationIssuesInfo> getFramingObligationIssues(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.catalog.dto.CatalogDto(CD.id,FR.name,REL.name, FR.personType) " +
            "from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.selectedSourcesRel SSR " +
            "left  join SSR.framingReference FR " +
            "left join FR.relationship REL " +
            "where (CD.id in (:lstCasesIds))")
    List<CatalogDto> getFramingSelectedSourceRel(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.catalog.dto.CatalogDto(CD.id,FT.description) " +
            "from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.selectedThreatsRel STR " +
            "left  join STR.framingThreat FT " +
            "where (CD.id in (:lstCasesIds))")
    List<CatalogDto> getFramingSelectedThreatsRel(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.catalog.dto.CatalogDto(CD.id, FR.description) " +
            "from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.selectedRisksRel SRR " +
            "left  join SRR.framingRisk FR " +
            "where (CD.id in (:lstCasesIds))")
    List<CatalogDto> getFramingSelectedRiskRel(@Param("lstCasesIds") List<Long> lstCasesIds);

//    @Query("select new com.umeca.model.entities.supervisor.ExcelActivitiesDto(CDET.id,ACT.name,RFMA.specification) " +
//            "from Case CDET " +
//            "inner join CDET.framingMeeting FM " +
//            "left join FM.relFramingMeetingActivities RFMA " +
//            "left join RFMA.activity ACT " +
//            "where CDET.id in (:casesIds)")
//    List<ExcelActivitiesDto> getFramingImputedActivities(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.SchoolDto(CD.id,SCH.id,SCH.name, SCH.name, SCH.address, FM.schoolComments, SCH.specification,SCH.block,AL.name, DEG.name) from Case CD " +
            "inner join CD.framingMeeting FM " +
            "inner join FM.school SCH " +
            "inner join SCH.degree DEG " +
            "inner join DEG.academicLevel AL " +
            "inner join FM.school SCH " +
            "where CD.id in (:casesIds)")
    List<SchoolDto> getAllFramingSchool(@Param("casesIds") List<Long> lstCasesIds);


    @Query("select new com.umeca.model.entities.supervisor.ExcelJobDto(CDET.id,JOB.id,JOB.company,JOB.post,JOB.nameHead,JOB.phone,RT.name,JOB.start,JOB.startPrev,JOB.end,JOB.salaryWeek,JOB.reasonChange,JOB.address, JOB.block) " +
            "from Case CDET " +
            "inner join CDET.framingMeeting FM " +
            "inner join FM.jobs JOB " +
            "inner join JOB.registerType RT " +
            "where CDET.id in (:lstCasesIds) order by JOB.block desc")
    List<ExcelJobDto> getFramingInfoJobs(@Param("lstCasesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelActivitiesDto(CDET.id,ACT.id,ACT.name,FA.description) " +
            "from Case CDET " +
            "inner join CDET.framingMeeting FM " +
            "inner join FM.activities FA " +
            "inner join FA.activity ACT " +
            "where CDET.id in (:lstCasesIds)")
    List<ExcelActivitiesDto> getFramingInfoActivities(@Param("lstCasesIds") List<Long> lstCasesIds);
}
