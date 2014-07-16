package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ObligationIssues;
import com.umeca.model.entities.supervisor.RelativesAbroadRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qObligationIssuesRepository")
public interface ObligationIssuesRepository extends JpaRepository<ObligationIssues, Long> {
}