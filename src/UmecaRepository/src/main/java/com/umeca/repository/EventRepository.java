package com.umeca.repository;

import com.umeca.model.entities.reviewer.View.CaseReportView;
import com.umeca.model.entities.shared.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("EventRepository")
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT new com.umeca.model.entities.reviewer.View.CaseReportView(ca.id, im.name, im.lastNameP, im.lastNameM, ev.comments, ev.date) " +
            "FROM Event ev " +
            "INNER JOIN ev.caseDetention ca " +
            "INNER JOIN ca.meeting me " +
            "INNER JOIN me.imputed im " +
            "WHERE ev.id = :id")
    CaseReportView getCaseReportById(@Param("id")Long id);
}