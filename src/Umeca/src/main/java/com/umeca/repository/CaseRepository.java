package com.umeca.repository;

import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.FindLegalBefore;
import com.umeca.model.entities.reviewer.dto.LogNotificationDto;
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
public interface CaseRepository extends JpaRepository<Case, Long>{

    @Query("SELECT c FROM Case c WHERE c.idFolder =:idFolder")
    Case findByIdFolder(@Param("idFolder")String idFolder);

    @Query("SELECT c FROM Case c WHERE c.idMP =:idMP")
    Case findByIdMP(@Param("idMP")String idMP);

    @Query("select  new com.umeca.model.entities.reviewer.FindLegalBefore(c.idMP,c.idFolder,s.description) from Case as c " +
            "INNER JOIN c.status as s " +
            "INNER JOIN c.meeting.imputed as i " +
            "where i.name=:name and i.lastNameP = :lastNameP and i.lastNameM = :lastNameM and c.id <> :idCase")
    List<FindLegalBefore> findLegalBefore(@Param("idCase")Long id, @Param("name") String name, @Param("lastNameP") String lastNameP, @Param("lastNameM") String lastNameM);


    //obtengo los meeting_incomplete y los incomplete_legal
    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),sm.name,m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.meeting as m " +
            "INNER JOIN c.meeting.status as sm " +
            "INNER JOIN c.meeting.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "where sm.name=:meetingSt and usr.id =:idUser and stc.name=:caseSt order by m.dateCreate asc")
    List<LogNotificationDto> getMeetingIncompleteInfo(@Param("idUser")Long idUser,@Param("meetingSt")String strMeetingSt ,@Param("caseSt")String strCaseSt, Pageable pageable);


    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),stver.name,m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.verification as ver " +
            "INNER JOIN c.verification.status as stver " +
            "INNER JOIN c.verification.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "INNER JOIN c.meeting m " +
            "where stver.name=:verifSt and usr.id =:idUser and stc.name=:caseSt order by c.meeting.dateCreate asc")
    List<LogNotificationDto> getAuthorizedVerificationsInfo(@Param("idUser")Long idUser,@Param("verifSt")String strMeetingSt ,@Param("caseSt")String strCaseSt, Pageable pageable);

    @Query("select  new com.umeca.model.entities.reviewer.dto.LogNotificationDto(c.idFolder,concat(imp.name,' ',imp.lastNameP,' ',imp.lastNameM),stver.name,m.dateCreate) from Case as c " +
            "INNER JOIN c.status as stc " +
            "INNER JOIN c.verification as ver " +
            "INNER JOIN c.verification.status as stver " +
            "INNER JOIN c.verification.reviewer as usr " +
            "INNER JOIN c.meeting.imputed as imp " +
            "INNER JOIN c.meeting m " +
            "where stver.name=:verifSt and stc.name=:caseSt order by c.meeting.dateCreate asc")
    List<LogNotificationDto> getNewSourceStatusCases(@Param("verifSt")String strMeetingSt ,@Param("caseSt")String strCaseSt, Pageable pageable);

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
    List<LogNotificationDto> getMissingSourcesInfo(@Param("idUser")Long idUser,@Param("verifSt")String strMeetingSt ,@Param("caseSt")String strCaseSt, Pageable pageable);

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
    List<LogNotificationDto> getAddMissingSourcesMeetingInfo(@Param("idUser")Long idUser,@Param("verifSt")String strMeetingSt ,@Param("caseSt")String strCaseSt, Pageable pageable);


    @Query("select  new com.umeca.model.dto.CaseInfo(c.id, c.idMP, c.idFolder, i.name, i.lastNameP, i.lastNameM, s.description) from Case as c " +
            "INNER JOIN c.status as s " +
            "INNER JOIN c.meeting.imputed as i " +
            "WHERE c.id = :idCase")
    CaseInfo getInfoById(@Param("idCase") Long idCase);

}