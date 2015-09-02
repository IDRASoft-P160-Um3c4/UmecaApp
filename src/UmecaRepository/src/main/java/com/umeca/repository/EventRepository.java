package com.umeca.repository;

import com.umeca.model.entities.reviewer.View.CaseReportView;
import com.umeca.model.entities.shared.Event;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository("EventRepository")
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT new com.umeca.model.entities.reviewer.View.CaseReportView(ca.id, im.name, im.lastNameP, im.lastNameM, ev.comments, ev.date) " +
            "FROM Event ev " +
            "INNER JOIN ev.caseDetention ca " +
            "INNER JOIN ca.meeting me " +
            "INNER JOIN me.imputed im " +
            "WHERE ev.id = :id")
    CaseReportView getCaseReportById(@Param("id") Long id);


    @Query("SELECT new com.umeca.model.entities.shared.Event(ev.id, ev.dateId, evT.name, ev.caseDetention)" +
            "FROM Event ev " +
            "INNER JOIN ev.eventType evT " +
            "WHERE ev.caseDetention.id = :id")
    List<Event> getEventsFromCase(@Param("id") Long id);


    @Query("select new com.umeca.model.shared.SelectList(et.id, count(e), et.name) from Event as e " +
            "inner join e.eventType et " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_INTERVIEW_DECLINED or " +
            "et.name = com.umeca.model.shared.Constants.EVENT_CASE_REPORT or " +
            "et.name = com.umeca.model.shared.Constants.EVENT_CASE_OPINION or " +
            "et.name = com.umeca.model.shared.Constants.EVENT_ONLY_INTERVIEW) " +
            "group by et.name " +
            "order by et.id")
    List<SelectList> countCasesByEventOnDate(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);


    @Query("select count( distinct eca) from Event as e " +
            "inner join e.eventType et " +
            "inner join e.caseDetention eca " +
            "inner join eca.meeting ecame " +
            "inner join ecame.drugs ecamed " +
            "inner join ecamed.drugType ecamedt " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_REPORT or " +
            "et.name = com.umeca.model.shared.Constants.EVENT_ONLY_INTERVIEW) " +
            "and (ecamedt.id <> 15)")
    Long countCasesWithDrugsOnDate(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);

    @Query("select count( distinct eca) from Event as e " +
            "inner join e.eventType et " +
            "inner join e.caseDetention eca " +
            "inner join eca.verification ecaver " +
            "inner join ecaver.status ecaversta " +
            "inner join ecaver.meetingVerified ecaverme " +
            "inner join ecaverme.drugs ecavermed " +
            "inner join ecavermed.drugType ecamedt " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_OPINION) " +
            "and (ecamedt.id <> 15 ) " +
            "and (ecaversta.name = com.umeca.model.shared.Constants.VERIFICATION_STATUS_COMPLETE)")
    Long countCasesWithDrugsByOpinionOnDate(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);


    @Query("select count( distinct eca) from Event as e " +
            "inner join e.eventType et " +
            "inner join e.caseDetention eca " +
            "inner join eca.meeting ecame " +
            "inner join ecame.drugs ecamed " +
            "inner join ecamed.drugType ecamedt " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_REPORT or " +
            "et.name = com.umeca.model.shared.Constants.EVENT_ONLY_INTERVIEW)")
    Long countAllCasesForDrugsOnDate(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);


    @Query("select count( distinct eca) from Event as e " +
            "inner join e.eventType et " +
            "inner join e.caseDetention eca " +
            "inner join eca.verification ecaver " +
            "inner join ecaver.status ecaversta " +
            "inner join ecaver.meetingVerified ecaverme " +
            "inner join ecaverme.drugs ecavermed " +
            "inner join ecavermed.drugType ecamedt " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_OPINION) " +
            "and (ecaversta.name = com.umeca.model.shared.Constants.VERIFICATION_STATUS_COMPLETE)")
    Long countAllCasesWithDrugsByOpinionOnDate(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);


    @Query("select  new com.umeca.model.shared.SelectList(i.gender, count(i.gender))" +
            "from Event ev " +
            "inner join ev.caseDetention c " +
            "inner join c.meeting m " +
            "inner  join m.imputed i " +
            "where (ev.dateId between :initDate and :endDate) " +
            "group by i.gender")
    List<SelectList> countMeetingByGender(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);

    @Query("select new com.umeca.model.shared.SelectList(ecameim.gender, count(distinct eca)) from Event as e " +
            "inner join e.eventType et " +
            "inner join e.caseDetention eca " +
            "inner join eca.meeting ecame " +
            "inner join ecame.imputed ecameim " +
            "inner join ecame.drugs ecamed " +
            "inner join ecamed.drugType ecamedt " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_REPORT or " +
            "et.name = com.umeca.model.shared.Constants.EVENT_ONLY_INTERVIEW) " +
            "and (ecamedt.id <> 15) " +
            "group by ecameim.gender")
    List<SelectList> countCasesWithDrugsOnDateByGender(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);

    @Query("select new com.umeca.model.shared.SelectList(ecameim.gender, count(distinct eca)) from Event as e " +
            "inner join e.eventType et " +
            "inner join e.caseDetention eca " +
            "inner join eca.verification ecaver " +
            "inner join ecaver.status ecaversta " +
            "inner join ecaver.meetingVerified ecaverme " +
            "inner join ecaverme.imputed ecameim " +
            "inner join ecaverme.drugs ecavermed " +
            "inner join ecavermed.drugType ecamedt " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_OPINION) " +
            "and (ecamedt.id <> 15 ) " +
            "and (ecaversta.name = com.umeca.model.shared.Constants.VERIFICATION_STATUS_COMPLETE) " +
            "group by ecameim.gender")
    List<SelectList> countCasesWithDrugsByOpinionOnDateByGender(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);


    @Query("select count(ecaversour.id) from Event as e " +
            "inner join e.eventType et " +
            "inner join e.caseDetention eca " +
            "inner join eca.verification ecaver " +
            "inner join ecaver.sourceVerifications ecaversour " +
            "where (e.dateId between :initDate and :endDate) " +
            "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_OPINION) " +
            "and (ecaversour.isAuthorized = true ) " +
            "and (ecaversour.visible = true ) " +
            "and (ecaversour.relationship <> NULL) " +
            "order by et.id")
    Long countSourcesByOpinionOnDate(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);




        @Query("select count(e.id) from Event as e " +
                "inner join e.eventType et " +
                "where (e.dateId between :initDate and :endDate) " +
                "and (et.name = com.umeca.model.shared.Constants.EVENT_CASE_OPINION) " +
                "order by et.id")
        Long countCasesByOpinionOnDate(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);

        @Query("select new com.umeca.model.shared.SelectList(dT.name, COUNT(distinct dT.id)) " +
                "from Event ev " +
                "inner join ev.eventType evT " +
                "inner join ev.caseDetention c " +
                "inner join c.meeting m " +
                "inner join m.drugs d " +
                "inner join d.drugType dT " +
                "where dT.id <> 15 and (ev.dateId between :initDate and :endDate)" +
                "group by dT.id"
                )
        List<SelectList>countTypeofDrugs(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);

}