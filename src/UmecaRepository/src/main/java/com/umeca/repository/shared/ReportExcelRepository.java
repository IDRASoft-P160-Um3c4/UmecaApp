package com.umeca.repository.shared;

import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisor.*;
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
            "       com.umeca.model.shared.Constants.CASE_STATUS_NOT_PROSECUTE) " +
            "and c.dateObsolete is null)" +
            "and (c.dateCreate between :initDate and :endDate)")
    List<Long> findIdCasesByDates(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    // SE CUENTAN UNICAMENTE LOS CASOS QUE ESTAN EN ESTATUS
    //stCase:ST_CASE_MEETING y stMeeting COMPLETE
    //stCase:ST_CASE_MEETING y stMeeting INCOMPLETE_LEGAL
    //ST_CASE_VERIFICATION_COMPLETE
    //ST_CASE_SOURCE_VALIDATION
    //ST_CASE_TECHNICAL_REVIEW_COMPLETE
    //ST_CASE_TECHNICAL_REVIEW_INCOMPLETE
    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status stCase " +
            "inner join c.meeting m " +
            "inner join m.status stMeeting " +
            "where (stCase.name = com.umeca.model.shared.Constants.CASE_STATUS_MEETING and stMeeting.name = com.umeca.model.shared.Constants.S_MEETING_COMPLETE) " +
            "or (stCase.name = com.umeca.model.shared.Constants.CASE_STATUS_MEETING and stMeeting.name = com.umeca.model.shared.Constants.S_MEETING_INCOMPLETE_LEGAL) " +
            "or (stCase.name in (com.umeca.model.shared.Constants.CASE_STATUS_VERIFICATION_COMPLETE,"+
            "                    " +
            "com.umeca.model.shared.Constants.CASE_STATUS_SOURCE_VALIDATION,"+
            "                    com.umeca.model.shared.Constants.CASE_STATUS_TECHNICAL_REVIEW,"+
            "                    com.umeca.model.shared.Constants.CASE_STATUS_INCOMPLETE_TECHNICAL_REVIEW)) " +
            "and (c.dateCreate between :initDate and :endDate) " +
            "and (m.meetingType=com.umeca.model.shared.HearingFormatConstants.MEETING_PROCEDURAL_RISK)")
    List<Long> getCasesEvaluation(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status stCase " +
            "inner join c.meeting m " +
            "inner join m.status stMeeting " +
            "where (stCase.name = com.umeca.model.shared.Constants.CASE_STATUS_MEETING and stMeeting.name = com.umeca.model.shared.Constants.S_MEETING_COMPLETE) " +
            "or (stCase.name = com.umeca.model.shared.Constants.CASE_STATUS_MEETING and stMeeting.name = com.umeca.model.shared.Constants.S_MEETING_INCOMPLETE_LEGAL) " +
            "and (c.dateCreate between :initDate and :endDate) " +
            "and (m.meetingType=com.umeca.model.shared.HearingFormatConstants.MEETING_PROCEDURAL_RISK)")
    List<Long> getCasesIdsMeeting(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.status stCase " +
            "where (stCase.name in (com.umeca.model.shared.Constants.CASE_STATUS_VERIFICATION_COMPLETE,"+
            "                    com.umeca.model.shared.Constants.CASE_STATUS_TECHNICAL_REVIEW,"+
            "                    com.umeca.model.shared.Constants.CASE_STATUS_INCOMPLETE_TECHNICAL_REVIEW)) " +
            "and (c.dateCreate between :initDate and :endDate) ")
    List<Long> getCasesIdsVerif(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


    //se considera un caso en supervision si tiene al menos un formato de audiencia
    @Query("select distinct(c.id) from Case as c " +
            "inner join c.status st " +
            "inner join c.hearingFormats hf " +
            "where (c.dateCreate between :initDate and :endDate) ")
    List<Long> getCasesSupervision(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

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

    @Query("select new com.umeca.model.shared.SelectList(c.id,dt.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.drugs as md " +
            "inner join md.drugType as dt " +
            "where (c.id in (:lstCases) and md.block=true)")
    List<SelectList> getAllDrugsMeeting(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,dt.id) from Case as c " +
            "inner join c.verification as v " +
            "inner join v.meetingVerified as mv " +
            "inner join mv.drugs as md " +
            "inner join md.drugType as dt " +
            "where (c.id in (:lstCases) and md.block=true)")
    List<SelectList> getAllDrugsVerif(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,dt.id) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.drugs as fmd " +
            "inner join fmd.drugType as dt " +
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
            "and imp.gender is not null)")
    List<SelectList> getAllGenderMeeting(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,imp.gender) from Case as c " +
            "inner join c.verification as v " +
            "inner join v.meetingVerified as mv " +
            "inner join mv.imputed imp " +
            "where ((c.id in (:lstCases)) " +
            "and imp.gender is not null)")
    List<SelectList> getAllGenderVerification(@Param("lstCases") List<Long> lstCases);



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
    List<SelectList> getAllMaritalStatusMeeting(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id,ms.id) from Case as c " +
            "inner join c.verification as v " +
            "inner join v.meetingVerified as mv " +
            "inner join mv.imputed imp " +
            "inner join imp.maritalStatus ms " +
            "where c.id in (:lstCases) ")
    List<SelectList> getAllMaritalStatusVerif(@Param("lstCases") List<Long> lstCases);


    @Query("select new com.umeca.model.shared.SelectList(c.id,fmpdms.id) from Case as c " +
            "inner join c.framingMeeting as fm " +
            "inner join fm.personalData as fmpd " +
            "inner join fmpd.maritalStatus as fmpdms " +
            "where c.id in (:lstCases)")
    List<SelectList> getAllMaritalStatusSup(@Param("lstCases") List<Long> lstCases);

    //rt.id=1 --> trabajo actual
    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases)) and rt.id=1 and j.block=true")
    List<Long> getAllCurrentJobMeeting(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.verification as v " +
            "inner join v.meetingVerified as mv " +
            "inner join mv.jobs as j " +
            "inner join j.registerType as rt " +
            "where (c.id in (:lstCases)) and rt.id=1 and j.block=true")
    List<Long> getAllCurrentJobVerifi(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.meeting as m " +
            "inner join m.jobs as j " +
            "where (c.id in (:lstCases) and j.block=false)")
    List<Long> getAllNoJobMeeting(@Param("lstCases") List<Long> lstCases);

    @Query("select distinct (c.id) from Case as c " +
            "inner join c.verification as v " +
            "inner join v.meetingVerified as mv " +
            "inner join mv.jobs as j " +
            "where (c.id in (:lstCases) and j.block=false)")
    List<Long> getAllNoJobVerif(@Param("lstCases") List<Long> lstCases);

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
    List<SelectList> getAllAcLvlMeeting(@Param("lstCases") List<Long> lstCases);

    @Query("select new com.umeca.model.shared.SelectList(c.id, alvl.id) from Case as c " +
            "inner join c.verification as v " +
            "inner join v.meetingVerified as mv " +
            "inner join mv.school sch " +
            "inner join sch.degree deg " +
            "inner join deg.academicLevel alvl " +
            "where (c.id in (:lstCases))")
    List<SelectList> getAllAcLvlVerif(@Param("lstCases") List<Long> lstCases);

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
            "where (C.id_case in (:lstCases) or C.id_case is null) group by A.id_arrangement order by CC desc", nativeQuery = true)
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

    /*******************************/
    @Query(value = "select distinct (c.id) from Case c  " +
            "inner join c.status stCase " +
            "inner join c.meeting m " +
            "inner join m.status stMeeting " +
            "where (stCase.name = com.umeca.model.shared.Constants.CASE_STATUS_MEETING and stMeeting.name = com.umeca.model.shared.Constants.S_MEETING_COMPLETE) " +
            "or (stCase.name = com.umeca.model.shared.Constants.CASE_STATUS_MEETING and stMeeting.name = com.umeca.model.shared.Constants.S_MEETING_INCOMPLETE_LEGAL) " +
            "and (c.id in (:lstCases)) " +
            "group by GC.description order by CCC desc", nativeQuery = true)
    List<Long> getCountGenderInMeeting(@Param("lstCases") List<Long> lstCases);




}
