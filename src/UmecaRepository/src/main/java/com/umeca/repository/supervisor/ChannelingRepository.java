package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.SelectList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingRepository")
public interface ChannelingRepository extends JpaRepository<Channeling, Long> {

    @Query("SELECT new com.umeca.model.entities.supervisor.ChannelingModel(c.id, c.idMP, i.name, i.lastNameP, i.lastNameM, d.id, s.fullname) " +
            "FROM Case AS c " +
            "INNER JOIN c.meeting.imputed i " +
            "INNER JOIN c.district d " +
            "INNER JOIN c.framingMeeting.supervisor s " +
            "WHERE c.id =:caseId ")
    ChannelingModel getChannelingCaseViewByCaseId(@Param("caseId") Long id);

    @Query("SELECT C.consecutive FROM Channeling C " +
            "INNER JOIN C.caseDetention CD " +
            "WHERE CD.id =:caseId ORDER BY C.consecutive DESC")
    List<Long> getLastConsecutiveByCaseId(@Param("caseId") Long caseId, Pageable pageable);

    @Query("SELECT new com.umeca.model.entities.supervisor.ChannelingModel(c.id, cd.id, cd.idMP, i.name, i.lastNameP, i.lastNameM, d.id, s.fullname" +
            ", c.name, c.channelingType.id, c.institutionType.id, c.economicSupport.id, c.preventionType.id, c.educationLevel.id" +
            ", c.specOther, c.institutionName, c.consecutive) " +
            "FROM Channeling AS c " +
            "INNER JOIN c.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed i " +
            "INNER JOIN c.district d " +
            "INNER JOIN cd.framingMeeting.supervisor s " +
            "WHERE c.id =:channelingId AND cd.id =:caseId ")
    ChannelingModel getChannelingViewByCaseId(@Param("caseId")Long id, @Param("channelingId")Long channelingId);

    @Query("SELECT new com.umeca.model.entities.supervisor.ChannelingNotification(c.channelingType.name, i.name, i.lastNameP, i.lastNameM" +
            ", cd.idMP, c.creatorUser.fullname) " +
            "FROM Channeling AS c " +
            "INNER JOIN c.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed i "  +
            "WHERE c.id =:channelingId")
    ChannelingNotification getNotificationInfoWithUser(@Param("channelingId") Long id);


    @Query("SELECT new com.umeca.model.entities.supervisor.ChannelingNotification(c.channelingType.name, i.name, i.lastNameP, i.lastNameM" +
            ", cd.idMP) " +
            "FROM Channeling AS c " +
            "INNER JOIN c.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed i "  +
            "WHERE c.id =:channelingId")
    ChannelingNotification getNotificationInfo(@Param("channelingId") Long id);


    @Query("SELECT new com.umeca.model.shared.SelectList(c.id, c.name, c.channelingType.name) " +
            "FROM Channeling AS c " +
            "WHERE c.caseDetention.id = :caseId")
    List<SelectList> findValidByCaseId(@Param("caseId") Long caseId);

    @Query("SELECT NEW com.umeca.model.entities.supervisor.ChannelingModelSheet(cd.idMP, i.name, i.lastNameP, i.lastNameM, " +
            "i.birthDate, i.gender, i.celPhone, ct.name, c.name, it.name, c.institutionName, c.consecutive) " +
            "FROM Channeling c " +
            "INNER JOIN c.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed i " +
            "INNER JOIN c.channelingType ct " +
            "INNER JOIN c.institutionType it " +
            "WHERE c.id = :id")
    ChannelingModelSheet getChannelingSheetById(@Param("id")Long id);
}
