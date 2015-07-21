package com.umeca.repository;

import com.umeca.model.catalog.MaritalStatus;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.dto.tablet.*;
import com.umeca.model.dto.tablet.catalog.*;
import com.umeca.model.dto.victim.VictimDto;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.FindLegalBefore;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.reviewer.StatusEvaluation;
import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
import com.umeca.model.entities.shared.TabletAssignmentInfo;
import com.umeca.model.entities.supervisor.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Query("select  new com.umeca.model.entities.reviewer.FindLegalBefore(c.id,c.idMP,c.idFolder,s.description,c.dateCreate,concat(i.name,' ',i.lastNameP,' ',i.lastNameM),i.birthDate) from Case as c " +
            "INNER JOIN c.status as s " +
            "INNER JOIN c.meeting.imputed as i " +
            "where i.foneticString=:foneticString and c.id <> :idCase")
    List<FindLegalBefore> findLegalBefore(@Param("idCase") Long id, @Param("foneticString") String foneticString);


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

    @Query("select  new com.umeca.model.dto.CaseInfo(c.id, c.idMP, c.idFolder, i.name, i.lastNameP, i.lastNameM, s.description, c.dateNotProsecute) from Case as c " +
            "INNER JOIN c.status as s " +
            "INNER JOIN c.meeting.imputed as i " +
            "WHERE c.id = :idCase")
    CaseInfo getInfoByIdNotProsecute(@Param("idCase") Long idCase);


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
            "IBS.name," +
            "IBM.name," +
            "IBL.name," +
            "IMP.celPhone," +
            "IMS.name," +
            "IMP.yearsMaritalStatus," +
            "IMP.boys," +
            "IMP.dependentBoys," +
            "SE.physicalCondition," +
            "SCH.block," +
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
            "OD.name, " +
            "MP.id) " +
            "from Case CDET " +
            "inner join CDET.status STC " +
            "inner join CDET.meeting MEET " +
            "inner join MEET.imputed IMP " +
            "left join IMP.birthCountry IBC " +
            "left join IMP.maritalStatus IMS " +
            "left join IMP.location IBL " +
            "left join IBL.municipality IBM " +
            "left join IBM.state IBS " +
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
            "left join MEET.previousCriminalProceeding PCP " +
            "left join PCP.complyPM AAA " +
            "left join PCP.complyCSPP ASCPP " +
            "left join PCP.complyProcessAbove APA " +
            "left join CDET.technicalReview TR " +
            "left join CDET.verification VER " +
            "left join CDET.monitoringPlan MP " +
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

    @Query("select new com.umeca.model.entities.supervisor.ExcelImputedHomeDto(CDET.id,IADD.addressString,HT.name, RT.name, concat(ST.name,', ',MUN.name,', ',LOC.name)) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.imputedHomes IH " +
            "left join IH.address IADD " +
            "left join IADD.location LOC " +
            "left join LOC.municipality MUN " +
            "left join MUN.state ST " +
            "left join IH.homeType HT " +
            "left join IH.registerType RT " +
            "where CDET.id in (:casesIds)")
    List<ExcelImputedHomeDto> getInfoImputedHomes(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelSocialNetworkDto(CDET.id,PSN.name,REL.name,PSN.age,DT.name,DEP.name,LW.name,PSN.isAccompaniment,PSN.phone,PSN.address,PSN.specificationRelationship,PSN.block) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.socialNetwork SN " +
            "left join SN.peopleSocialNetwork PSN " +
            "left join PSN.dependent DEP " +
            "left join PSN.documentType DT " +
            "left join PSN.livingWith LW " +
            "left join PSN.relationship REL " +
            "where CDET.id in (:casesIds) order by PSN.block")
    List<ExcelSocialNetworkDto> getInfoSocialNetwork(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelReferenceDto(CDET.id,REF.fullName,REF.age,DT.name,REL.name,REF.address,REF.phone,REF.isAccompaniment, REF.specificationRelationship, REF.block) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.references REF " +
            "left join REF.documentType DT " +
            "left join REF.relationship REL " +
            "where CDET.id in (:casesIds) order by REF.block")
    List<ExcelReferenceDto> getInfoReference(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelJobDto(CDET.id,JOB.post,JOB.nameHead,JOB.company,JOB.phone,JOB.startPrev,JOB.start,JOB.salaryWeek,JOB.end,JOB.reasonChange,JOB.address,RT.name,RT.id, JOB.block) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.jobs JOB " +
            "left join JOB.registerType RT " +
            "where CDET.id in (:casesIds) order by JOB.block")
    List<ExcelJobDto> getInfoJobs(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelDrugDto(CDET.id, DT.name, PER.name, DRUG.quantity, DRUG.lastUse, DRUG.specificationType, DRUG.specificationPeriodicity, DRUG.block) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.drugs DRUG " +
            "left join DRUG.periodicity PER " +
            "left join DRUG.drugType DT " +
            "where CDET.id in (:casesIds) order by DRUG.block")
    List<ExcelDrugDto> getInfoDrugs(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelCrimeDto(CDET.id,CC.name,FED.name,CRM.article, CRM.comment) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.currentCriminalProceeding CCP " +
            "left join CCP.crimeList CRM " +
            "left join CRM.crime as CC " +
            "left join CRM.federal FED " +
            "where CDET.id in (:casesIds)")
    List<ExcelCrimeDto> getInfoCrimes(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.dto.victim.VictimDto(CD.id, VIC.fullname, R.name, VIC.age, VIC.phone, ADD.addressString, VIC.specification) from Case CD " +
            "inner join CD.meeting M " +
            "inner join M.currentCriminalProceeding CCP " +
            "inner join CCP.victims VIC " +
            "inner join VIC.address ADD " +
            "inner join VIC.relationship R " +
            "where CD.id in (:casesIds)")
    List<VictimDto> getInfoVictims(@Param("casesIds") List<Long> lstCasesIds);

    @Query("select new com.umeca.model.entities.supervisor.ExcelCoDefDto(CDET.id, CODF.fullName,REL.name) " +
            "from Case CDET " +
            "inner join CDET.meeting MEET " +
            "left join MEET.currentCriminalProceeding CCP " +
            "left join CCP.coDefendantList CODF " +
            "left join CODF.relationship REL " +
            "where CDET.id in (:casesIds)")
    List<ExcelCoDefDto> getInfoCoDef(@Param("casesIds") List<Long> lstCasesIds);

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
    StatusEvaluation getStatusEvaluation(@Param("caseId") Long caseId);


    @Query("select new com.umeca.model.entities.supervisor.ExcelVerificationDto(" +
            "CDET.id," +
            "CDET.idFolder," +
            "CDET.idMP," +
            "CDET.dateCreate," +
            "concat(IMP.name,' ',IMP.lastNameP,' ',IMP.lastNameM)," +
            "IMP.nickname," +
            "sv.fullName," +
            "rel.name," +
            "sv.age," +
            "sv.address," +
            "sv.id," +
            "st.description) from Case as CDET " +
            "INNER JOIN CDET.meeting.imputed as IMP " +
            "INNER JOIN CDET.status as st " +
            "INNER JOIN CDET.verification as V " +
            "INNER JOIN V.sourceVerifications as sv " +
            "LEFT JOIN sv.relationship as rel " +
            "WHERE (sv.isAuthorized = true) and (CDET.id in (:listCaseId)) " +
            "order by CDET.dateCreate")
    List<ExcelVerificationDto> getInfoVerification(@Param("listCaseId") List<Long> listCaseId);

    @Query("SELECT COUNT(C) FROM Case C INNER JOIN C.status ST WHERE C.id =:caseId AND ST.name <>:caseStatus")
    Long existsCaseNotClosed(@Param("caseId") Long caseId, @Param("caseStatus") String caseStatusClosed);

    @Query("SELECT COUNT(C.id) FROM Case C " +
            "INNER JOIN C.meeting M " +
            "INNER JOIN M.imputed IMP " +
            "WHERE C.idMP=:idMP AND IMP.foneticString=:foneticName AND IMP.birthDate=:bthDate")
    Long findJudicialFoneticBrthDayImputed(@Param("idMP") String idMP, @Param("foneticName") String foneticName, @Param("bthDate") Date bthDate);

    @Query("select new com.umeca.model.entities.supervisor.ExcelVerificationDto(CDET.id, rel.name, vm.name) from Case as CDET " +
            "INNER JOIN CDET.verification as V " +
            "INNER JOIN V.sourceVerifications as sv " +
            "INNER JOIN sv.relationship as rel " +
            "INNER JOIN sv.verificationMethod as vm " +
            "WHERE (sv.isAuthorized = true) and (CDET.id in (:listCaseId)) ")
    List<ExcelVerificationDto> getSourcesVerification(@Param("listCaseId") List<Long> listCaseId);

    @Query("select V.id from Case C " +
            "inner join C.verification V " +
            "where C.id=:caseId")
    Long getVerifIdByCaseId(@Param("caseId") Long caseId);


/* CONSULTAS PARA ENVIAR INFORMACION A LA TABLETA*/

    /*DTO CASO*/
    @Query("select new com.umeca.model.dto.tablet.TabletCaseDto(c.id, c.idFolder, c.idMP, c.recidivist, c.dateNotProsecute, c.dateObsolete, c.dateCreate, c.previousStateCode) from Case c " +
            "inner join c.status s " +
            "inner join c.meeting m " +
            "inner join m.status sm " +
            "where c.id=:idCase " +
            "and c.dateObsolete is null")
// +
//            "and s.name= com.umeca.model.shared.Constants.CASE_STATUS_MEETING " +
//            "and sm.name= com.umeca.model.shared.Constants.S_MEETING_INCOMPLETE")
    TabletCaseDto getInfoCaseByCaseId(@Param("idCase") Long idCase);

    /*DTO STATUS CASO*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto(s.id,s.name,s.description) from Case c " +
            "inner join c.status s " +
            "where c.id=:idCase ")
    TabletStatusCaseDto getStatusCaseByCaseId(@Param("idCase") Long idCase);

    /*DTO MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletMeetingDto(m.id, m.meetingType, m.commentReference, m.commentJob, m.commentSchool,m.commentCountry, m.commentHome, m.commentDrug, m.dateCreate, m.dateTerminate) from Case c " +
            "inner join c.meeting m " +
            "where c.id=:idCase")
    TabletMeetingDto getMeetingDataByCaseId(@Param("idCase") Long idCase);

    /*DTO STATUS MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletStatusMeetingDto(s.id,s.name,s.description) from Case c " +
            "inner join c.meeting m " +
            "inner join m.status s " +
            "where c.id=:idCase")
    TabletStatusMeetingDto getStatusMeetingDataByCaseId(@Param("idCase") Long idCase);

    /*DTO REVIEWER MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletUserDto(u.id,u.fullname) from Case c " +
            "inner join c.meeting m " +
            "inner join m.reviewer u " +
            "where c.id=:idCase")
    TabletUserDto getUserMeetingDataByCaseId(@Param("idCase") Long idCase);

    /*DTO STATUS MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletStatusMeetingDto(st.id, st.name, st.description) from Case c " +
            "inner join c.meeting m " +
            "inner join m.status st " +
            "where c.id=:idCase")
    TabletUserDto getStatusMeetingByCaseId(@Param("idCase") Long idCase);

    /*DTO IMPUTED MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletImputedDto(i.id, i.name, i.lastNameP, i.lastNameM, i.foneticString, i.gender, " +
            "i.birthDate, i.celPhone, i.yearsMaritalStatus, i.boys, i.dependentBoys, i.birthMunicipality, " +
            "i.birthState, i.birthLocation, i.nickname) from Case c " +
            "inner join c.meeting m " +
            "inner join m.imputed i " +
            "where c.id=:idCase")
    TabletImputedDto getImputedDataByCaseId(@Param("idCase") Long idCase);

    /*DTO IMPUTED MARITAL STATUS MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletMaritalStatusDto(ms.id,ms.name) from Case c " +
            "inner join c.meeting m " +
            "inner join m.imputed i " +
            "inner join i.maritalStatus ms " +
            "where c.id=:idCase")
    TabletMaritalStatusDto getImputedMaritalStatusByCaseId(@Param("idCase") Long idCase);

    /*DTO IMPUTED BIRTH COUNTRY MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletCountryDto(bc.id, bc.name, bc.alpha2, bc.alpha3, bc.latitude, bc.longitude) from Case c " +
            "inner join c.meeting m " +
            "inner join m.imputed i " +
            "inner join i.birthCountry bc " +
            "where c.id=:idCase")
    TabletCountryDto getImputedBirthCountryByCaseId(@Param("idCase") Long idCase);

    /*DTO IMPUTED LOCATION MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletLocationDto(l.id, l.name, l.abbreviation, l.description, l.zipCode) from Case c " +
            "inner join c.meeting m " +
            "inner join m.imputed i " +
            "inner join i.location l " +
            "where c.id=:idCase")
    TabletLocationDto getImputedLocationByCaseId(@Param("idCase") Long idCase);

    /*DTO SOCIAL NETWORK MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletSocialNetworkDto(sn.id, sn.comment) from Case c " +
            "inner join c.meeting m " +
            "inner join m.socialNetwork sn " +
            "where c.id=:idCase")
    TabletSocialNetworkDto getSocialNetworkByCaseId(@Param("idCase") Long idCase);

    /*DTO PERSON SOCIAL NETWORK MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletPersonSocialNetworkDto(psn.id,psn.name, psn.age, psn.phone, psn.address, psn.specification, psn.isAccompaniment, psn.specificationRelationship, psn.block) from Case c " +
            "inner join c.meeting m " +
            "inner join m.socialNetwork sn " +
            "inner join sn.peopleSocialNetwork psn " +
            "where c.id=:idCase")
    List<TabletPersonSocialNetworkDto> getPeopleSocialNetworkByCaseId(@Param("idCase") Long idCase);

    /*DTO PERSON SOCIAL NETWORK RELATIONSHIP MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletRelationshipDto(r.id,r.name,r.isObsolete, r.specification) from PersonSocialNetwork psn " +
            "inner join psn.relationship r " +
            "where psn.id=:personId")
    TabletRelationshipDto getRelationshipByPersonId(@Param("personId") Long personId);

    /*DTO PERSON SOCIAL NETWORK DEPENDENT MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletElectionDto(d.id,d.name) from PersonSocialNetwork psn " +
            "inner join psn.dependent d " +
            "where psn.id=:personId")
    TabletElectionDto getDependentByPersonId(@Param("personId") Long personId);

    /*DTO PERSON SOCIAL NETWORK LIVING WITH MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletElectionDto(lw.id, lw.name) from PersonSocialNetwork psn " +
            "inner join psn.livingWith lw " +
            "where psn.id=:personId")
    TabletElectionDto getLivingWithByPersonId(@Param("personId") Long personId);

    /*DTO PERSON DOCUMENT TYPE MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletDocumentTypeDto(dt.id, dt.name, dt.isObsolete, dt.specification) from PersonSocialNetwork psn " +
            "inner join psn.documentType dt " +
            "where psn.id=:personId")
    TabletDocumentTypeDto getDocumentTypeByPersonId(@Param("personId") Long personId);

    /*DTO SCHOOL MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletSchoolDto(sch.id, sch.name, sch.phone, sch.address, sch.specification, sch.block) from Case c " +
            "inner join c.meeting m " +
            "inner join m.school sch " +
            "where c.id=:idCase")
    TabletSchoolDto getSchoolByCaseId(@Param("idCase") Long idCase);

    /*DTO DEGREE MEETING*/
    @Query("select new com.umeca.model.dto.tablet.catalog.TabletDegreeDto(d.id,d.name,d.isObsolete) from School sch " +
            "inner join sch.degree d " +
            "where sch.id=:idSchool")
    TabletDegreeDto getDegreeBySchoolId(@Param("idSchool") Long idSchool);

    /*DTO SCHEDULE MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletScheduleDto(sc.id, sc.day, sc.start, sc.end) from School sch " +
            "inner join sch.schedule sc " +
            "where sch.id=:idSchool")
    List<TabletScheduleDto> getScheduleSchoolId(@Param("idSchool") Long idSchool);

    /*DTO SOCIAL ENVIRONMENT MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletSocialEnvironmentDto(se.id,se.physicalCondition,se.comment) from Case c " +
            "inner join c.meeting m " +
            "inner join m.socialEnvironment se " +
            "where c.id=:idCase ")
    TabletSocialEnvironmentDto getSocialEnvironmentByCaseId(@Param("idCase") Long idCase);

    /*DTO SOCIAL ENVIRONMENT REL ACTIVITY MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletRelSocialEnvironmentActivityDto(rsea.id,rsea.specification,act.id,act.name,act.specification,act.isObsolete) from SocialEnvironment se " +
            "inner join se.relSocialEnvironmentActivities rsea " +
            "inner join rsea.activity act " +
            "where se.id=:idSE")
    List<TabletRelSocialEnvironmentActivityDto> getRelSocialEnvironmentActivityBySocialEnvId(@Param("idSE") Long idSE);


    /*DTO SOCIAL ENVIRONMENT MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletLeaveCountryDto(lc.id,lc.timeAgo,lc.reason, lc.state,lc.media, lc.address, lc.timeResidence, lc.specficationImmigranDoc, lc.specificationRelationship," +
            "fac.id,fac.name, cf.id,cf.name,od.id,od.name,llc.id,llc.name, " +
            "inmd.id,inmd.name,inmd.specification,inmd.obsolete, " +
            "cou.id,cou.name,cou.alpha2,cou.alpha3,cou.latitude,cou.longitude," +
            "rel.id, rel.name, rel.isObsolete, rel.specification) " +
            "from Case c " +
            "inner join c.meeting m " +
            "inner join m.leaveCountry lc " +
            "left join lc.familyAnotherCountry fac " +
            "left join lc.communicationFamily cf " +
            "left join lc.officialDocumentation od " +
            "left join lc.livedCountry llc " +
            "left join lc.immigrationDocument inmd " +
            "left join lc.country cou " +
            "left join lc.relationship rel " +
            "where c.id=:idCase ")
    TabletLeaveCountryDto getLeaveCountryByCaseId(@Param("idCase") Long idCase);

    /*DTO REFERENCES MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletReferenceDto(ref.id,ref.fullName,ref.age,ref.address,ref.phone,ref.specification,ref.isAccompaniment,ref.specificationRelationship,ref.block," +
            "dt.id, dt.name,dt.isObsolete,dt.specification," +
            "rel.id,rel.name,rel.isObsolete,rel.specification) " +
            "from Case c " +
            "inner join c.meeting m " +
            "inner join m.references ref " +
            "left join ref.documentType dt " +
            "left join ref.relationship rel " +
            "where c.id=:idCase")
    List<TabletReferenceDto> getReferencesByCaseId(@Param("idCase") Long idCase);

    /*DTO IMPUTED HOME  MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletImputedHomeDto(ih.id,ih.timeLive,ih.reasonChange,ih.description,ih.phone,ih.specification,ih.reasonSecondary," +
            "                                addr.id,addr.street,addr.outNum,addr.innNum,addr.lat,addr.lng,addr.addressString," +
            "                                loc.id,loc.name,loc.abbreviation,loc.description,loc.zipCode," +
            "                                ht.id,ht.name,ht.specification,ht.obsolete," +
            "                                rt.id, rt.name) " +
            "from Case c " +
            "inner join c.meeting m " +
            "inner join m.imputedHomes ih " +
            "left join ih.address addr " +
            "left join addr.location loc " +
            "left join ih.registerType rt " +
            "left join ih.homeType ht " +
            "where c.id=:idCase")
    List<TabletImputedHomeDto> getImputedHomeByCaseId(@Param("idCase") Long idCase);

    /*DTO SCHEDULE IMPUTED HOME MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletScheduleDto(sc.id, sc.day, sc.start, sc.end) from ImputedHome ih " +
            "inner join ih.schedule sc " +
            "where ih.id=:idIH")
    List<TabletScheduleDto> getScheduleByImputedHomeId(@Param("idIH") Long idIH);


    /*DTO SCHEDULE JOB*/
    @Query("select new com.umeca.model.dto.tablet.TabletJobDto(j.id,j.post,j.nameHead,j.company,j.phone,j.startPrev,j.start,j.salaryWeek,j.end,j.reasonChange,j.address,j.block," +
            "                        rt.id,rt.name) " +
            "from Case c " +
            "inner join c.meeting m " +
            "inner join m.jobs j " +
            "left join j.registerType rt " +
            "where c.id=:idCase")
    List<TabletJobDto> getJobByCaseId(@Param("idCase") Long idCase);

    /*DTO SCHEDULE JOB MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletScheduleDto(sc.id, sc.day, sc.start, sc.end) from Job j " +
            "inner join j.schedule sc " +
            "where j.id=:idJob")
    List<TabletScheduleDto> getScheduleByJobId(@Param("idJob") Long idJob);

    /*Long id, String quantity, String lastUse, Boolean block, String specificationType, String specificationPeriodicity, String onsetAge,
                         Long idDT, String nameDT, Boolean specificationDT, Boolean isObsoleteDT,
                         Long idP, String nameP, Boolean isObsoleteP, Boolean specificationP*/

    /*DTO DRUG MEETING*/
    @Query("select new com.umeca.model.dto.tablet.TabletDrugDto(d.id,d.quantity,d.lastUse,d.block,d.specificationType,d.specificationPeriodicity,d.onsetAge," +
            "                         dt.id,dt.name,dt.specification,dt.isObsolete," +
            "                         p.id,p.name,p.isObsolete,p.specification) " +
            "from Case c " +
            "inner join c.meeting m " +
            "inner join m.drugs d " +
            "left join d.drugType dt " +
            "left join d.periodicity p " +
            "where c.id=:idCase")
    List<TabletDrugDto> getDrugByCaseId(@Param("idCase") Long idCase);


    /*DTO VERIFICATION*/
    @Query("select new com.umeca.model.dto.tablet.TabletVerificationDto(v.id,v.dateComplete,v.dateCreate," +
            "                                 rev.id, rev.fullname," +
            "                                 st.id,st.name,st.description) " +
            "from Case c " +
            "inner join c.verification v " +
            "inner join v.status st " +
            "inner join v.reviewer rev " +
            "where c.id=:idCase")
    TabletVerificationDto getVerificationByCaseId(@Param("idCase") Long idCase);


    /*DTO SOURCE VERIFICATION**********************************************************/
    @Query("select new com.umeca.model.dto.tablet.TabletSourceVerificationDto(sv.id,sv.fullName,sv.age,sv.address,sv.phone,sv.isAuthorized,sv.dateComplete,sv.dateAuthorized,sv.specification,sv.visible," +
            "                                       vm.id,vm.name,vm.isObsolete," +
            "                                       r.id,r.name,r.isObsolete,r.specification) " +
            "from TabletRelAssignmentSource trac " +
            "inner join trac.sourceVerification sv " +
            "inner join trac.tabletAssignmentCase tac " +
            "inner join tac.assignedUser au " +
            "inner join tac.caseDetention cd " +
            "left join sv.verificationMethod vm " +
            "left join sv.relationship r " +
            "where cd.id=:idCase and au.id=:idUsr and sv.isAuthorized = true")
    List<TabletSourceVerificationDto> getAssignedSourcesVerificationByCaseIdUsrId(@Param("idCase") Long idCase, @Param("idUsr") Long idUsr);

    @Query("select new com.umeca.model.dto.tablet.TabletSourceVerificationDto(sv.id,sv.fullName,sv.age,sv.address,sv.phone,sv.isAuthorized,sv.dateComplete,sv.dateAuthorized,sv.specification,sv.visible," +
            "                                       vm.id,vm.name,vm.isObsolete," +
            "                                       r.id,r.name,r.isObsolete,r.specification) " +
            "from SourceVerification sv " +
            "inner join sv.verification v " +
            "left join sv.verificationMethod vm " +
            "left join sv.relationship r " +
            "where v.id=:idVerif " +
            "and sv.visible=false and sv.isAuthorized = true")
    TabletSourceVerificationDto getImputedSourceVerificationByVerificationId(@Param("idVerif") Long idVerif);

    /*DTO FIELD MEETING SOURCE */
    @Query("select new com.umeca.model.dto.tablet.TabletFieldMeetingSourceDto(fms.id,fms.value,fms.jsonValue,fms.isFinal,fms.idFieldList,fms.reason," +
            "                                       sfv.id,sfv.name,sfv.description," +
            "                                       fv.id,fv.code,fv.section,fv.sectionCode,fv.fieldName,fv.indexField,fv.isObsolete,fv.idSubsection,fv.type) " +
            "from SourceVerification sv  " +
            "left join sv.fieldMeetingSourceList fms " +
            "left join fms.statusFieldVerification sfv " +
            "left join fms.fieldVerification fv " +
            "where sv.id=:idSource")
    List<TabletFieldMeetingSourceDto> getFieldMeetingSourceBySourceId(@Param("idSource") Long idSource);

    /*DTO HEARING FORMAT*/
    @Query("select new com.umeca.model.dto.tablet.TabletHearingFormatDto(hf.id, hf.registerTime,hf.idFolder, hf.idJudicial, hf.room, hf.appointmentDate, " +
            "hf.initTime , hf.endTime, hf.judgeName, hf.mpName, hf.defenderName, hf.terms, hf.confirmComment, hf.isFinished, hf.comments, hf.umecaDate, hf.umecaTime, hf.hearingTypeSpecification, hf.imputedPresence, hf.hearingResult, hf.previousHearing, hf.showNotification, " +
            "                                  ht.id, ht.description, ht.isObsolete, ht.lock, ht.specification," +
            "                                  hfs.id, hfs.controlDetention, hfs.extension, hfs.imputationFormulation, hfs.imputationDate, hfs.linkageProcess, hfs.linkageRoom, hfs.linkageDate, hfs.extDate, hfs.linkageTime, hfs.arrangementType, hfs.nationalArrangement," +
            "                                  hi.id, hi.name, hi.lastNameP, hi.lastNameM, hi.birthDate, hi.imputeTel, " +
            "                                  addr.id, addr.street, addr.outNum, addr.innNum, addr.lat, addr.lng, addr.addressString," +
            "                                  l.id, l.name, l.abbreviation, l.description, l.zipCode) " +
            "from Case c " +
            "inner join c.hearingFormats hf " +
            "left join hf.hearingType ht " +
            "left join hf.hearingFormatSpecs hfs " +
            "left join hf.hearingImputed hi " +
            "left join hi.address addr " +
            "left join addr.location l " +
            "where c.id=:idCase order by hf.id desc ")
    List<TabletHearingFormatDto> getLastHearingFormatByCaseId(@Param("idCase") Long idCase, Pageable pageable);

    /*DTO ASSIGNED ARRANGEMENT*/
    @Query("select new com.umeca.model.dto.tablet.TabletAssignedArrangementDto(aa.id, aa.description," +
            "                                        a.id, a.description, a.type, a.isNational, a.index, a.isObsolete, a.isDefault, a.isExclusive) " +
            "from HearingFormat hf " +
            "inner join hf.assignedArrangements aa " +
            "inner join aa.arrangement a " +
            "where hf.id=:formatId")
    List<TabletAssignedArrangementDto> getAssignedArrangementByFormatId(@Param("formatId") Long formatId);

    /*DTO CONTACTS*/
    @Query("select new com.umeca.model.dto.tablet.TabletContactDataDto(c.id, c.nameTxt, c.phoneTxt, c.addressTxt) " +
            "from HearingFormat hf " +
            "inner join hf.contacts c " +
            "where hf.id=:formatId")
    List<TabletContactDataDto> getContactsByFormatId(@Param("formatId") Long formatId);

    /*DTO CRIME */
    @Query("select new com.umeca.model.dto.tablet.TabletCrimeDto(cl.id, cl.comment, cl.article," +
            "                          f.id, f.name," +
            "                          c.id, c.name, c.description, c.obsolete) " +
            "from HearingFormat hf " +
            "inner join hf.crimeList cl " +
            "inner join cl.federal f " +
            "inner join cl.crime c " +
            "where hf.id=:formatId")
    List<TabletCrimeDto> getCrimesByFormatId(@Param("formatId") Long formatId);


    @Query("select new com.umeca.model.entities.shared.TabletAssignmentInfo(tac.id, cd.assignmentType) from TabletAssignmentCase tac " +
            "inner join tac.assignedUser au " +
            "inner join tac.caseDetention cd " +
            "where tac.isObsolete = false " +
            "and au.id=:userId")
    List<TabletAssignmentInfo> getAssignmentIdsTypesByUser(@Param("userId") Long userId);

}