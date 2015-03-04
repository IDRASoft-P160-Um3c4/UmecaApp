package com.umeca.repository.shared;

import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.entities.supervisorManager.ManagerSupChartInfo;
import com.umeca.model.shared.SelectList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("qReportExcelRepository")
public interface ReportExcelRepository extends JpaRepository<Case, Long> {


    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status st " +
            "where (st.name not in " +
            "      (com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_EVALUATION," +
            "       com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_SUPERVISION," +
            "       com.umeca.model.shared.Constants.CASE_STATUS_NOT_PROSECUTE) and (c.dateCreate between :initDate and :endDate))")
    List<Long> findIdCasesByDates(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting m " +
            "inner join c.status st " +
            "where (st.name not in (com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_EVALUATION,com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_SUPERVISION,com.umeca.model.shared.Constants.CASE_STATUS_NOT_PROSECUTE)) " +
            "and (c.dateCreate between :initDate and :endDate) " +
            "and (m.meetingType=com.umeca.model.shared.HearingFormatConstants.MEETING_PROCEDURAL_RISK)")
    List<Long> countCasesEvaluation(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status st " +
            "left join c.hearingFormats hf " +
            "where (st.name not in (com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_EVALUATION,com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_SUPERVISION,com.umeca.model.shared.Constants.CASE_STATUS_NOT_PROSECUTE)) " +
            "and (c.dateCreate between :initDate and :endDate) " +
            "and hf.id is not null")
    List<Long> countCasesSupervision(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

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
                                   @Param("lstCases") List<Long> lstCases); //no usada

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

    @Query("select new com.umeca.model.shared.SelectList(c.id,md.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.drugs as md " +
            "where (c.id in (:lstCases) and md.block=true)")
    List<SelectList> getAllDrugsEval(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,fmd.id) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.drugs as fmd " +
            "where (c.id in (:lstCases) and fmd.block=true)")
    List<SelectList> getAllDrugsSup(@Param("lstCases") List<Long> lstCases);

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

    @Query("select distinct (cd.id) from Case as cd " +
            "inner join cd.meeting as m " +
            "inner join m.currentCriminalProceeding as ccp " +
            "inner join ccp.crimeList as ccpc " +
            "inner join ccpc.crime c " +
            "inner join c.groupCrime gc " +
            "where ((cd.id in (:lstCases)) and (gc.id in (:lstCrimes)))")
    List<Long> findIdCasesByCrimesInLegal(@Param("lstCases") List<Long> lstCases, @Param("lstCrimes") List<Long> lstCrimes);//casos con crimenes en legales

    @Query("select distinct (cd.id) from Case as cd " +
            "inner join cd.hearingFormats as hf " +
            "inner join hf.crimeList as hfc " +
            "inner join hfc.crime c " +
            "inner join c.groupCrime gc " +
            "where ((cd.id in (:lstCases)) and (gc.id in (:lstCrimes)))")
    List<Long> findIdCasesByCrimesInFormat(@Param("lstCases") List<Long> lstCases, @Param("lstCrimes") List<Long> lstCrimes);//casos con crimenes en legales

    @Query("select distinct (cd.id) from ActivityMonitoringPlan amp " +
            "inner join amp.supervisionActivity sa " +
            "inner join amp.caseDetention cd " +
            "where ((cd.id in (:lstCases)) and (sa.id in (:lstActivities)))")
    List<Long> findIdCasesByMonitoringActivities(@Param("lstCases") List<Long> lstCases, @Param("lstActivities") List<Long> lstActivities);//casos con actividades en el plan de monitoreo

    @Query("select distinct (cd.id) from Case cd " +
            "inner join cd.hearingFormats as hf " +
            "inner join hf.assignedArrangements as aa " +
            "inner join aa.arrangement as arr " +
            "where ((cd.id in (:lstCases)) and (arr.id in (:lstArrangement)))")
    List<Long> findIdCasesByArrangements(@Param("lstCases") List<Long> lstCases, @Param("lstArrangement") List<Long> lstArrangement);//casos con obligaciones en los formatos

    /*summary querys*/

    @Query("select new com.umeca.model.shared.SelectList(c.id,imp.gender) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.imputed imp " +
            "where ((c.id in (:lstCases)) " +
            "and (m.meetingType=com.umeca.model.shared.HearingFormatConstants.MEETING_PROCEDURAL_RISK) " +
            "and imp.gender is not null)")
    List<SelectList> getAllGenderEval(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,fmpd.gender) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.personalData as fmpd " +
            "where (c.id in (:lstCases)) and (fmpd.gender is not null)")
    List<SelectList> getAllGenderSup(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,ms.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.imputed imp " +
            "inner join imp.maritalStatus ms " +
            "where c.id in (:lstCases) ")
    List<SelectList> getAllMaritalStatusEval(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,fmpdms.id) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.personalData as fmpd " +
            "inner join fmpd.maritalStatus as fmpdms " +
            "where c.id in (:lstCases)")
    List<SelectList> getAllMaritalStatusSup(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases)) and rt.id=1 and j.block=true")
    List<Long> getAllCurrentJobEv(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases) and j.block=false)")
    List<Long> getAllNoJobEv(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases)) and rt.id=1 and j.block=true")
    List<Long> getAllCurrentJobSup(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct(c.id) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases) and j.block=false)")
    List<Long> getAllNoJobSup(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id, alvl.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.school sch " +
            "inner join sch.degree deg " +
            "inner join deg.academicLevel alvl " +
            "where (c.id in (:lstCases))")
    List<SelectList> getAllAcLvlEv(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id, alvl.id) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.school sch " +
            "inner join sch.degree deg " +
            "inner join deg.academicLevel alvl " +
            "where (c.id in (:lstCases))")
    List<SelectList> getAllAcLvlSup(@Param("lstCases") List<Long> lstCases);

//    @Query("select distinct (c.id) from Case as c " +
//            "inner join c.meeting as meet " +
//            "inner join meet.status as stmeet " +
//            "where (stmeet.name in (:lstStMeet)) and (c.id in (:lstCases))")
//    List<Long> findIdCasesByStatusMeetingStr(@Param("lstStMeet") List<String> lstStMeet, @Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.entities.supervisor.ExcelStatusCasesInfo(c.id, meet.id, meet.dateTerminate, ver.id, " +
            "           ver.dateComplete,tr.id,tr.isFinished,fm.id,fm.isTerminated,mp.id) from Case as c " +
            "inner join c.status as st " +
            "left join c.meeting as meet " +
            "left join c.verification as ver " +
            "left join c.technicalReview as tr " +
            "left join c.framingMeeting as fm " +
            "left join c.monitoringPlan as mp " +
            "where ((c.id in (:lstCases)) " +
            "and (st.name not in " +
            "                   (com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_EVALUATION, " +
            "                    com.umeca.model.shared.Constants.CASE_STATUS_NOT_PROSECUTE, " +
            "                    com.umeca.model.shared.Constants.CASE_STATUS_OBSOLETE_SUPERVISION)))")
    List<ExcelStatusCasesInfo> getAllCasesAtEvaluationSupervision(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(C.id,HF.id) from Case c " +
            "inner join c.hearingFormats HF " +
            "where (HF.isFinished=true and (c.id in (:lstCases))) group by C.id")
    List<SelectList> getAllCasesWithFinishedFormat(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.verification as ver " +
            "inner join ver.status as stver " +
            "where ((stver.name in (:lstStVer)) and (c.id in (:lstCases)))")
    List<Long> findIdCasesByStatusVerificationStr(@Param("lstStVer") List<String> lstStVer, @Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status as sc " +
            "where (sc.name in (:lstStCase)) and (c.id in (:lstCases))")
    List<Long> findIdCasesByStatusCaseStr(@Param("lstStCase") List<String> lstStCase, @Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.entities.supervisor.HearingFormatInfo(C.id,HF.id,HF.idFolder, HF.idJudicial," +//HF.room," +
            "HF.initTime,HF.endTime, HF.judgeName, HF.mpName, HF.defenderName, " +
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
            "where (C.id in(:lstCasesIds)) order by Hf.registerTime asc")
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

    @Query("select new com.umeca.model.catalog.dto.CatalogDto(CD.id,concat(ST.name,', ',MUN.name,', ',LOC.name)) from Case CD " +
            "left join CD.framingMeeting FM " +
            "left join FM.framingAddresses FMA " +
            "left join FMA.address ADD " +
            "left join ADD.location LOC " +
            "left join LOC.municipality MUN " +
            "left join MUN.state ST " +
            "where (CD.id in (:lstCasesIds))")
    List<CatalogDto> getSummaryFramingHomes(@Param("lstCasesIds") List<Long> lstCasesIds);

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

    @Query(value = "select count(distinct C.id_case) as CC, A.description from cat_arrangement A " +
            "left join assigned_arrangement AA on AA.id_arrangement = A.id_arrangement " +
            "left join hearing_format HF on AA.id_hearing_format = HF.id_hearing_format " +
            "left join case_detention C on HF.id_case = C.id_case " +
            "where C.id_case in (:lstCases) or C.id_case is null group by A.description order by CC desc", nativeQuery = true)
    List<Object> getCountCasesByArrangement(@Param("lstCases") List<Long> lstCases);

    @Query(value = "select  count(distinct CD.id_case) as CCC,GC.description from cat_group_crime GC " +
            "left join cat_crime CC on GC.id_group = CC.id_group " +
            "left join crime CR on CC.id_crime_cat = CR.id_crime_cat " +
            "left join hearing_format HF on HF.id_hearing_format = CR.hearingFormat " +
            "left join case_detention CD on HF.id_case = CD.id_case " +
            "where (CD.id_case in (:lstCases) or CD.id_case is null) group by GC.description order by CCC desc", nativeQuery = true)
    List<Object> getCountCasesByCrimeSup(@Param("lstCases") List<Long> lstCases);

    @Query(value = "select  count(distinct CD.id_case) as CCC,GC.description from cat_group_crime GC " +
            "left join cat_crime CC on GC.id_group = CC.id_group " +
            "left join crime CR on CC.id_crime_cat = CR.id_crime_cat " +
            "left join current_criminal_proceeding CCP on CCP.id_current_criminal_proceeding = CR.id_criminal_proceeding " +
            "left join meeting M on CCP.id_meeting = M.id_meeting " +
            "left join case_detention CD on M.id_case = CD.id_case " +
            "where (CD.id_case in (:lstCases) or CD.id_case is null)" +
            "group by GC.description order by CCC desc", nativeQuery = true)
    List<Object> getCountCasesByCrimeEv(@Param("lstCases") List<Long> lstCases);


    /*consultas para reportes de managersup*/

    //traer el ultimo formato de audiencia por caso registrado dentro del rango de fechas
    @Query(value = "select CD.id_case, max(HF.id_hearing_format) from hearing_format HF " +
            "inner join case_detention CD on CD.id_case=HF.id_case " +
            "inner join cat_status_case S on CD.id_status = S.id_status " +
            "where (S.status not in (:lstStatus)) and (HF.register_timestamp between :initDate and :endDate) " +
            "group by CD.id_case ", nativeQuery = true)
    List<Object> getLastFormatInDates(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("lstStatus") List<String> lstStatus);

    //traer el ultimo formato de audiencia por caso registrado dentro del rango de fechas en el distrito indicado
    @Query(value = "select CD.id_case, max(HF.id_hearing_format) from hearing_format HF " +
            "inner join case_detention CD on CD.id_case=HF.id_case " +
            "inner join cat_status_case S on CD.id_status = S.id_status " +
            "inner join cat_district D on CD.id_district=D.id_district " +
            "where (S.status not in (:lstStatus)) and (D.id_district=:districtId) and (HF.register_timestamp between :initDate and :endDate) " +
            "group by CD.id_case ", nativeQuery = true)
    List<Object> getLastFormatInDatesByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("lstStatus") List<String> lstStatus, @Param("districtId") Long districtId);

    //contar los casos para cada medida en los formatos de audiencia
    @Query(value = "select count(distinct C.id_case) as CC, A.description from cat_arrangement A " +
            "left join assigned_arrangement AA on AA.id_arrangement = A.id_arrangement " +
            "left join hearing_format HF on AA.id_hearing_format = HF.id_hearing_format " +
            "left join case_detention C on HF.id_case = C.id_case " +
            "where HF.id_hearing_format in (:lstFormats) or C.id_case is null group by A.description order by CC desc", nativeQuery = true)
    List<Object> getCountCasesByArrangementInFormatsId(@Param("lstFormats") List<Long> lstFormats);

    //traer los casos con drogas en entrevista de encuadre finalizada
    @Query(value = "select CD.id_case, DT.id_drug_type from case_detention CD " +
            "inner join cat_status_case S on CD.id_status = S.id_status " +
            "inner join framing_meeting FM on CD.id_case=FM.id_case " +
            "inner join drug D on D.id_framing_meeting= FM.id_framing_meeting " +
            "inner join cat_drug_type DT on DT.id_drug_type = D.id_drug_type " +
            "where (S.status not in (:lstStatus)) and " +
            "(FM.end_date between :initDate and :endDate) " +
            "and  (FM.is_terminated=true)  " +
//            "and (DT.drug <> 'No consume') " +
            "group by CD.id_case ", nativeQuery = true)
    List<Object> getCasesWithDrugsInFinishedFM(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("lstStatus") List<String> lstStatus);

    //traer los casos con drogas en entrevista de encuadre finalizada para el distrito indicado
    @Query(value = "select CD.id_case, DT.id_drug_type from case_detention CD " +
            "inner join cat_status_case S on CD.id_status = S.id_status " +
            "inner join cat_district D on CD.id_district=D.id_district " +
            "inner join framing_meeting FM on CD.id_case=FM.id_case " +
            "inner join drug DR on DR.id_framing_meeting= FM.id_framing_meeting " +
            "inner join cat_drug_type DT on DT.id_drug_type = DR.id_drug_type " +
            "where (S.status not in (:lstStatus)) " +
            "and (D.id_district=:districtId) " +
            "and (FM.end_date between :initDate and :endDate) " +
            "and  (FM.is_terminated=true)  " +
//            "and (DT.drug <> 'No consume') " +
            "group by CD.id_case ", nativeQuery = true)
    List<Object> getCasesWithDrugsInFinishedFMByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("lstStatus") List<String> lstStatus, @Param("districtId") Long districtId);

    //contar los casos para cada droga en entrevista de encuadre para los casos hallados
    @Query(value = "select count(distinct C.id_case) as CC, DT.drug from case_detention C " +
            "inner join framing_meeting FM on FM.id_case = C.id_case " +
            "inner join drug D on FM.id_framing_meeting = D.id_framing_meeting " +
            "right join cat_drug_type DT on D.id_drug_type = DT.id_drug_type " +
            "where DT.drug <> 'No consume'  " +
            "and  (C.id_case in (:lstCases) or C.id_case is null) " +
            "group by DT.drug order by CC desc ", nativeQuery = true)
    List<Object> getCountCasesByDrugs(@Param("lstCases") List<Long> lstCases);

    //traer los casos en entrevista de encuadre finalizada con algun empleo
    @Query(value = "select CD.id_case, RT.id_register_type from case_detention CD " +
            "inner join cat_status_case S on CD.id_status = S.id_status " +
            "inner join framing_meeting FM on CD.id_case=FM.id_case " +
            "inner join job J on J.id_framing_meeting= FM.id_framing_meeting " +
            "inner join cat_register_type RT on RT.id_register_type = RT.id_register_type " +
            "where (S.status not in (:lstStatus)) and " +
            "(FM.end_date between :initDate and :endDate) " +
            "and (FM.is_terminated=true) " +
            "group by CD.id_case ", nativeQuery = true)
    List<Object> getCasesWithJobInFinishedFM(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("lstStatus") List<String> lstStatus);

    //traer los casos en entrevista de encuadre finalizada con algun empleo en el distrito indicado
    @Query(value = "select CD.id_case, RT.id_register_type from case_detention CD " +
            "inner join cat_status_case S on CD.id_status = S.id_status " +
            "inner join cat_district D on CD.id_district=D.id_district " +
            "inner join framing_meeting FM on CD.id_case=FM.id_case " +
            "inner join job J on J.id_framing_meeting= FM.id_framing_meeting " +
            "inner join cat_register_type RT on RT.id_register_type = RT.id_register_type " +
            "where (S.status not in (:lstStatus)) " +
            "and (D.id_district=:districtId) " +
            "and (FM.end_date between :initDate and :endDate) " +
            "and (FM.is_terminated=true) " +
            "group by CD.id_case ", nativeQuery = true)
    List<Object> getCasesWithJobInFinishedFMByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("lstStatus") List<String> lstStatus, @Param("districtId") Long districtId);

    //contar los casos por tipo de emple oen entrevista de encuadre finalizada
    @Query(value = "select count(distinct C.id_case) as CC, RT.register_type from case_detention C " +
            "left join framing_meeting FM on FM.id_case = C.id_case " +
            "left join job J on J.id_framing_meeting = FM.id_framing_meeting " +
            "right join cat_register_type RT on J.id_register_type = RT.id_register_type " +
            "where (C.id_case in (:lstCases) or C.id_case is null) and (J.block <> false or J.block is null)" +
            "group by RT.register_type order by CC desc ", nativeQuery = true)
    List<Object> getCountCasesByJob(@Param("lstCases") List<Long> lstCases);


    //contar los casos popr tipo de cierre y que hayan sido cerrados dentro del rango de fechas
    @Query(value = "select count(Q1.idCase) as CC, SC.description from cat_status_case SC left join " +
            "(select C.id_case as idCase, S.id_status as idStatus, S.description " +
            "from case_detention C right join cat_status_case S on C.id_status = S.id_status " +
            "where C.close_date between :iDate and :eDate) Q1 on SC.id_status = Q1.idStatus " +
            "where SC.status in (:lstStatus) " +
            "group by SC.status order by CC desc ", nativeQuery = true)
    List<Object> getCountClosedCases(@Param("iDate") Date iDate, @Param("eDate") Date eDate, @Param("lstStatus") List<String> lstStatus);

    //contar los casos popr tipo de cierre y que hayan sido cerrados dentro del rango de fechas en el distrito indicado
    @Query(value = "select count(Q1.idCase) as CC, SC.description from cat_status_case SC left join " +
            "(select C.id_case as idCase, S.id_status as idStatus, S.description " +
            "from case_detention C inner join cat_district D on C.id_district=D.id_district " +
            "right join cat_status_case S on C.id_status = S.id_status " +
            "where (C.close_date between :iDate and :eDate) and (D.id_district=:districtId)) Q1 on SC.id_status = Q1.idStatus " +
            "where SC.status in (:lstStatus) " +
            "group by SC.status order by CC desc ", nativeQuery = true)
    List<Object> getCountClosedCasesByDistrict(@Param("iDate") Date iDate, @Param("eDate") Date eDate, @Param("lstStatus") List<String> lstStatus, @Param("districtId") Long districtId);


    //contar los casos que tienen informacion legal registrada dentro del rango de fechas
    @Query(value = "select count(C.id_case) from case_detention C " +
            "inner join cat_status_case S on C.id_status = S.id_status " +
            "inner join meeting M on M.id_case = C.id_case " +
            "inner join current_criminal_proceeding CCP on CCP.id_meeting = M.id_meeting " +
            "inner join cat_location L on CCP.id_location = L.id_location " +
            "where (M.date_terminate_legal between :iDate and :eDate) " +
            "and (S.status not in (:lstStatus)) " +
            "and L.id_location =:locationId", nativeQuery = true)
    Object getCountCasesByDetentionPlace(@Param("iDate") Date iDate, @Param("eDate") Date eDate, @Param("lstStatus") List<String> lstStatus, @Param("locationId") Long locationId);

    @Query(value = "select count(C.id_case) from case_detention C " +
            "inner join cat_district D on C.id_district=D.id_district " +
            "inner join cat_status_case S on C.id_status = S.id_status " +
            "inner join meeting M on M.id_case = C.id_case " +
            "inner join current_criminal_proceeding CCP on CCP.id_meeting = M.id_meeting " +
            "inner join cat_location L on CCP.id_location = L.id_location " +
            "where (M.date_terminate_legal between :iDate and :eDate) " +
            "and (S.status not in (:lstStatus)) " +
            "and (L.id_location =:locationId) and (D.id_district = :districtId)", nativeQuery = true)
    Object getCountCasesByDetentionPlaceDistrict(@Param("iDate") Date iDate, @Param("eDate") Date eDate, @Param("lstStatus") List<String> lstStatus, @Param("locationId") Long locationId, @Param("districtId") Long districtId);


    //graficos
    //obtener todos los supervisores con casos con plan de monitoreo en un distrito
    @Query("select distinct(SUP.id),SUP.fullname from Case C " +
            "inner join C.district D " +
            "inner join C.monitoringPlan MP " +
            "inner join MP.supervisor SUP " +
            "where (SUP.enabled=true) and (D.id=:districtId)")
    List<Object> getSupervisorsByDistrict(@Param("districtId") Long districtId);

    @Query("select new com.umeca.model.entities.supervisorManager.ManagerSupChartInfo(U.id,U.fullname) from User U " +
            "where (U.enabled=true) and (U.id =:userId)")
    List<ManagerSupChartInfo> getSupervisorsById(@Param("userId") Long userId);

    //obtener la cuenta de los planes de monitoreo registrados dentro del rango de fechas y que tienen resolucion MC o SCPP
    @Query(value = "select count(MP.id_monitoring_plan) as CC,U.id_user from case_detention C " +
            "inner join cat_status_case SC on C.id_status = SC.id_status " +
            "inner join monitoring_plan MP on MP.id_case = C.id_case " +
            "inner join user U on MP.id_user_supervisor = U.id_user " +
            "where (U.enabled = true) " +
            "and (MP.creation_time between :iDate and :eDate) " +
            "and (MP.resolution= :resolutionId) " +
            "and (SC.status not in (:lstStatus)) " +
            "and (U.id_user in (:lstUsers)) " +
            "group by U.id_user order by CC desc", nativeQuery = true)
    List<Object> getCountCasesWithMonitoringPlanResolution(@Param("iDate") Date initDate, @Param("eDate") Date endDate,
                                                           @Param("lstStatus") List<String> lstStatus, @Param("lstUsers") List<Long> lstUsers,
                                                           @Param("resolutionId") Integer resolutionId);

    //obtener la cuenta de los casos con incumplimiento total o parcial registrado dentro del rango de fechas
    @Query(value = "select count(distinct (MP.id_monitoring_plan)) as CC, U.id_user from case_detention C " +
            "inner join cat_status_case SC on C.id_status = SC.id_status " +
            "inner join monitoring_plan MP on MP.id_case = C.id_case " +
            "inner join fulfillment_report FR on FR.id_monitoring_plan = MP.id_monitoring_plan " +
            "inner join cat_fulfillment_report CFR on FR.id_fulfillment_report_type = CFR.id_fulfillment_report_type " +
            "inner join user U on MP.id_user_supervisor = U.id_user " +
            "where (SC.status not in (:lstStatus))  " +
            "and (U.id_user in (:lstUsers)) " +
            "and (FR.timestamp between :iDate and :eDate) " +
            "and (CFR.code =:codeFR) " +
            "and (MP.resolution = :resolutionMP)", nativeQuery = true)
    List<Object> getCountCasesWithFulfillmentReport(@Param("iDate") Date initDate, @Param("eDate") Date endDate,
                                                    @Param("lstStatus") List<String> listStatus, @Param("lstUsers") List<Long> lstUsers,
                                                    @Param("codeFR") String codeFR, @Param("resolutionMP") Integer resolutionMP);

/*
    //obtener supervisores de los planes de monitoreo con reportes de incumplimiento registrados dentro del rango de fechas
    @Query("select distinct(SUP.id) from FulfillmentReport FR " +
            "inner join FR.fulfillmentReportType FRT " +
            "inner join FR.monitoringPlan MP " +
            "inner join MP.supervisor SUP " +
            "inner join MP.caseDetention C " +
            "inner join C.status S " +
            "where (SUP.enabled=true) " +
            "and (FR.timestamp between :iDate and :eDate) " +
            "and (S.status not in (:lstStatus))")
    List<Long> getFulfillmentSupervisor(@Param("iDate") Date initDate, @Param("eDate") Date endDate, @Param("lstStatus") List<String> listStatus);

    //obtener supervisores de los casos que tengan cambio de resolucion dentro del rango de fechas
    @Query("select distinct(SUP.id) from Case C " +
            "inner join C.status S " +
            "inner join C.monitoringPlan MP " +
            "inner join MP.supervisor SUP " +
            "where (SUP.enabled=true) " +
            "and (C.dateChangeArrangementType between :iDate and :eDate) " +
            "and (S.status not in (:lstStatus))")
    List<Long> getSupervisorCaseWithChanges(@Param("iDate") Date initDate, @Param("eDate") Date endDate, @Param("lstStatus") List<String> listStatus);

    //obtener supervisores de los casos que hayan sido cerrados dentro del rango de fechas
    @Query("select distinct(SUP.id) from Case C " +
            "inner join C.status S " +
            "inner join C.monitoringPlan MP " +
            "inner join MP.supervisor SUP " +
            "where (SUP.enabled=true) " +
            "and (C.closeDate between :iDate and :eDate) " +
            "and (S.status in (:lstStatus))")
    List<Long> getSupervisorClosedCases(@Param("iDate") Date initDate, @Param("eDate") Date endDate, @Param("lstStatus") List<String> listStatus);

    //obtener supervisores de los casos con imputado sustraido dentro del rango de fechas
    @Query("select distinct(SUP.id) from Case C " +
            "inner join C.status S " +
            "inner join C.monitoringPlan MP " +
            "inner join MP.supervisor SUP " +
            "where (SUP.enabled=true) " +
            "and (C.dateSubstracted between :iDate and :eDate) " +
            "and (S.status in (:lstStatus))")
    List<Long> getSupervisorSubstractedCases(@Param("iDate") Date initDate, @Param("eDate") Date endDate, @Param("lstStatus") List<String> listStatus);

    //obtener supervisores de los casos con imputado sustraido dentro del rango de fechas
    @Query("select distinct(SUP.id) from Case C " +
            "inner join C.status S " +
            "inner join C.monitoringPlan MP " +
            "inner join MP.supervisor SUP " +
            "where (SUP.enabled=true) " +
            "and (C.dateSubstracted between :iDate and :eDate) " +
            "and (S.status in (:lstStatus))")
    List<Long> getSupervisorSubstractedCases(@Param("iDate") Date initDate, @Param("eDate") Date endDate, @Param("lstStatus") List<String> listStatus);
*/
}
