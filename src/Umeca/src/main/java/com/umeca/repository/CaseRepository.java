package com.umeca.repository;

import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.FindLegalBefore;
import com.umeca.model.entities.reviewer.StatusEvaluation;
import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
import com.umeca.model.entities.supervisor.*;
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
@Repository("qCaseRepository")
public interface CaseRepository extends JpaRepository<Case, Long> {

    @Query("SELECT c FROM Case c WHERE c.idFolder =:idFolder")
    List<Case> findByIdFolder(@Param("idFolder") String idFolder);


    @Query("SELECT c FROM Case c WHERE c.idMP =:idMP")
    Case findByIdMP(@Param("idMP") String idMP);

    @Query("select  new com.umeca.model.entities.reviewer.FindLegalBefore(c.idMP,c.idFolder,s.description) from Case as c " +
            "INNER JOIN c.status as s " +
            "INNER JOIN c.meeting.imputed as i " +
            "where i.name=:name and i.lastNameP = :lastNameP and i.lastNameM = :lastNameM and c.id <> :idCase")
    List<FindLegalBefore> findLegalBefore(@Param("idCase") Long id, @Param("name") String name, @Param("lastNameP") String lastNameP, @Param("lastNameM") String lastNameM);


    //obtengo los meeting_incomplete y los incomplete_legal
    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),sm.name,m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.meeting as m " +
            "INNER JOIN c.meeting.status as sm " +
            "INNER JOIN c.meeting.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "where sm.name=:meetingSt and usr.id =:idUser and stc.name=:caseSt order by m.dateCreate asc")
    List<LogNotificationDto> getMeetingIncompleteInfo(@Param("idUser") Long idUser, @Param("meetingSt") String strMeetingSt, @Param("caseSt") String strCaseSt, Pageable pageable);


    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),stver.name,m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.verification as ver " +
            "INNER JOIN c.verification.status as stver " +
            "INNER JOIN c.verification.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "INNER JOIN c.meeting m " +
            "where stver.name=:verifSt and usr.id =:idUser and stc.name=:caseSt order by c.meeting.dateCreate asc")
    List<LogNotificationDto> getAuthorizedVerificationsInfo(@Param("idUser") Long idUser, @Param("verifSt") String strMeetingSt, @Param("caseSt") String strCaseSt, Pageable pageable);

    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),stver.name,m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.verification as ver " +
            "INNER JOIN c.verification.status as stver " +
            "INNER JOIN c.verification.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "INNER JOIN c.meeting m " +
            "where stver.name=:verifSt and stc.name=:caseSt order by c.meeting.dateCreate asc")
    List<LogNotificationDto> getNewSourceStatusCases(@Param("verifSt") String strMeetingSt, @Param("caseSt") String strCaseSt, Pageable pageable);

    //obtengo verificaciones con fuentes autorizadas no terminadas
    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),'SOURCES_NO_MEETING',m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.verification as ver " +
            "INNER JOIN c.verification.caseDetention as vCase " +
            "INNER JOIN c.verification.status as stver " +
            "INNER JOIN c.verification.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "INNER JOIN c.meeting as m " +
            "where stver.name=:verifSt and usr.id =:idUser and stc.name=:caseSt " +
            "and (select max(sv.id) from SourceVerification sv" +
            "     INNER JOIN sv.verification sVer" +
            "     INNER JOIN sv.verification.caseDetention sCase" +
            "     where sv.dateAuthorized is not null " +
            "     and sv.dateComplete is null " +
            "     and sCase.id=vCase.id) is not null " +
            "order by c.meeting.dateCreate asc")
    List<LogNotificationDto> getMissingSourcesInfo(@Param("idUser") Long idUser, @Param("verifSt") String strMeetingSt, @Param("caseSt") String strCaseSt, Pageable pageable);

    //obtengo verificaciones que necesitan mas fuentes
    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),'NO_SOURCES',m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.verification as ver " +
            "INNER JOIN c.verification.caseDetention as vCase " +
            "INNER JOIN c.verification.status as stver " +
            "INNER JOIN c.verification.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "INNER JOIN c.meeting as m " +
            "where stver.name=:verifSt and usr.id =:idUser and stc.name=:caseSt " +
            "and (select max(sv.id) from SourceVerification sv" +
            "     INNER JOIN sv.verification sVer" +
            "     INNER JOIN sv.verification.caseDetention sCase" +
            "     where sv.dateAuthorized is not null " +
            "     and sv.dateComplete is null and sCase.id=vCase.id) is null " +
            "order by c.meeting.dateCreate asc")
    List<LogNotificationDto> getAddMissingSourcesMeetingInfo(@Param("idUser") Long idUser, @Param("verifSt") String strMeetingSt, @Param("caseSt") String strCaseSt, Pageable pageable);


    @Query("select  new com.umeca.model.dto.CaseInfo(c.id, c.idMP, c.idFolder, i.name, i.lastNameP, i.lastNameM, s.description) from Case as c " +
            "INNER JOIN c.status as s " +
            "INNER JOIN c.meeting.imputed as i " +
            "WHERE c.id = :idCase")
    CaseInfo getInfoById(@Param("idCase") Long idCase);


    /* consultas para obtener informacion para el excel*/
    @Query("select new com.umeca.model.entities.supervisor.ExcelCaseInfoDto(" +
            "CDET.id," +
            "CDET.idFolder," +
            "CDET.idMP," +
            "CDET.dateCreate," +
            "STC.description," +
            "concat(IMP.name,' ',IMP.lastNameP,' ',IMP.lastNameM)," +
            "IMP.nickname," +
            "IMP.gender," +
            "IMP.birthDate," +
            "IBC.name," +
            "IMP.birthState," +
            "IMP.birthMunicipality," +
            "IMP.birthLocation," +
            "IMP.celPhone," +
            "IMS.name," +
            "IMP.yearsMaritalStatus," +
            "IMP.boys," +
            "IMP.dependentBoys," +
            "SE.physicalCondition," +
            "SCH.name," +
            "SCH.phone," +
            "SCH.address, " +
            "DEG.name," +
            "LVL.name," +
            "OC.name," +
            "LOC.name," +
            "LC.state," +
            "LC.timeAgo," +
            "LC.reason," +
            "LC.address," +
            "RA.name," +
            "CRA.name," +
            "LC.media," +
            "CCP.behaviorDetention," +
            "CCP.placeDetention," +
            "CCP.nameVictim," +
            "VR.name," +
            "AV.addressString," +
            "PCP.firstProceeding," +
            "PCP.openProcessNumber," +
            "PCP.numberConvictions," +
            "AAA.name," +
            "ASCPP.name," +
            "APA.name," +
            "TR.id," +
            "TR.totalRisk," +
            "TR.comments," +
            "TR.subtotalsTxt," +
            "VER.id," +
            "OD.name) " +
            "from Case CDET " +
            "inner join CDET.status STC " +
            "inner join CDET.meeting MEET " +
            "inner join MEET.imputed IMP " +
            "left join IMP.birthCountry IBC " +
            "left join IMP.maritalStatus IMS " +
            "left join MEET.socialEnvironment SE " +
            "left join MEET.school SCH " +
            "left join SCH.degree DEG " +
            "left join DEG.academicLevel LVL " +
            "left join MEET.leaveCountry LC " +
            "left join LC.officialDocumentation OD " +
            "left join LC.livedCountry OC " +
            "left join LC.country LOC  " +
            "left join LC.familyAnotherCountry RA " +
            "left join LC.communicationFamily CRA " +
            "left join MEET.currentCriminalProceeding CCP " +
            "left join CCP.relationshipVictim VR " +
            "left join CCP.domicileVictim AV " +
            "left join MEET.previousCriminalProceeding PCP " +
            "left join PCP.complyPM AAA " +
            "left join PCP.complyCSPP ASCPP " +
            "left join PCP.complyProcessAbove APA " +
            "left join CDET.technicalReview TR " +
            "left join CDET.verification VER " +
            "where CDET.id in (:lstIdsCases)" +
            "order by CDET.dateCreate")
    List<ExcelCaseInfoDto> getInfoCases(@Param("lstIdsCases") List<Long> lstIdsCases);
    /**/

    @Query("select new com.umeca.model.entities.supervisor.ExcelActivitiesDto(CDET.id,ACT.name,RSEA.specification) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.socialEnvironment SE " +
            "left join SE.relSocialEnvironmentActivities RSEA " +
            "left join RSEA.activity ACT " +
            "where CDET.id in (:casesIds)")
    List<ExcelActivitiesDto> getInfoImputedActivities(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelImputedHomeDto(CDET.id,IADD.addressString,HT.name, RT.name) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.imputedHomes IH " +
            "left join IH.address IADD " +
            "left join IH.homeType HT " +
            "left join IH.registerType RT " +
            "where CDET.id in (:casesIds)")
    List<ExcelImputedHomeDto> getInfoImputedHomes(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelSocialNetworkDto(CDET.id,PSN.name,REL.name,PSN.age,DT.name,DEP.name,LW.name,PSN.isAccompaniment,PSN.phone,PSN.address) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.socialNetwork SN " +
            "left join SN.peopleSocialNetwork PSN " +
            "left join PSN.dependent DEP " +
            "left join PSN.documentType DT " +
            "left join PSN.livingWith LW " +
            "left join PSN.relationship REL " +
            "where CDET.id in (:casesIds)")
    List<ExcelSocialNetworkDto> getInfoSocialNetwork(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelReferenceDto(CDET.id,REF.fullName,REF.age,DT.name,REL.name,REF.address,REF.phone,REF.isAccompaniment) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.references REF " +
            "left join REF.documentType DT " +
            "left join REF.relationship REL " +
            "where CDET.id in (:casesIds)")
    List<ExcelReferenceDto> getInfoReference(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelJobDto(CDET.id,JOB.post,JOB.nameHead,JOB.company,JOB.phone,JOB.startPrev,JOB.start,JOB.salaryWeek,JOB.end,JOB.reasonChange,JOB.address,RT.name,RT.id) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.jobs JOB " +
            "left join JOB.registerType RT " +
            "where CDET.id in (:casesIds)")
    List<ExcelJobDto> getInfoJobs(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelDrugDto(CDET.id, DT.name, PER.name, DRUG.quantity, DRUG.lastUse, DRUG.specificationType, DRUG.specificationPeriodicity) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.drugs DRUG " +
            "left join DRUG.periodicity PER " +
            "left join DRUG.drugType DT " +
            "where CDET.id in (:casesIds)")
    List<ExcelDrugDto> getInfoDrugs(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelCrimeDto(CDET.id,CRM.name,FED.name,CRM.article) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.currentCriminalProceeding CCP " +
            "left join CCP.crimeList CRM " +
            "left join CRM.federal FED " +
            "where CDET.id in (:casesIds)")
    List<ExcelCrimeDto> getInfoCrimes(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelCoDefDto(CDET.id, CODF.fullName,REL.name) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.currentCriminalProceeding CCP " +
            "left join CCP.coDefendantList CODF " +
            "left join CODF.relationship REL " +
            "where CDET.id in (:casesIds)")
    List<ExcelCoDefDto> getInfoCoDef(@Param("casesIds") List<Long> lstCasesIds);

    //public ExcelTecRevSelQuestDto(Long idCase, String parentCode, String code, String question) {
    @Query("select new com.umeca.model.entities.supervisor.ExcelTecRevSelQuestDto(CDET.id, SECT.code, SUBSECT.code, QUEST.question, SUBSECT.name) " +
            "from Case CDET " +
            "left join CDET.technicalReview TREV " +
            "inner join TREV.questionsSel RELQ " +
            "inner join RELQ.question QUEST " +
            "inner join QUEST.section SUBSECT " +
            "left join SUBSECT.parent SECT " +
            "where CDET.id in (:casesIds)")
    List<ExcelTecRevSelQuestDto> getInfoTecRevSelQuest(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.reviewer.StatusEvaluation(cs.name, sm.name, vs.name) " +
            "from Case as cd " +
            "inner join cd.status as cs " +
            "inner join cd.meeting.status as sm " +
            "left join cd.verification.status as vs where cd.id = :caseId")
    StatusEvaluation getStatusEvaluation(@Param("caseId")Long caseId);


    @Query("select new com.umeca.model.entities.supervisor.ExcelVerificationDto(" +
            "CDET.id," +
            "CDET.idFolder," +
            "CDET.idMP," +
            "CDET.dateCreate," +
            "concat(IMP.name,' ',IMP.lastNameP,' ',IMP.lastNameM)," +
            "IMP.nickname," +
            "sv.fullName," +
            "sv.relationship.name," +
            "sv.age," +
            "sv.address," +
            "sv.id," +
            "st.description) from Case as CDET " +
            "INNER JOIN CDET.meeting.imputed as IMP " +
            "INNER JOIN CDET.status as st "+
            "INNER JOIN CDET.verification as V " +
            "INNER JOIN V.sourceVerifications as sv " +
            "WHERE CDET.id in (:listCaseId) and sv.isAuthorized = true " +
            "order by CDET.dateCreate")
    List<ExcelVerificationDto> getInfoVerification(@Param("listCaseId") List<Long> listCaseId);

    @Query("SELECT COUNT(C) FROM Case C INNER JOIN C.status ST WHERE C.id =:caseId AND ST.name <>:caseStatus")
    Long existsCaseNotClosed(@Param("caseId")Long caseId, @Param("caseStatus")String caseStatusClosed);
}