package com.umeca.repository.shared;

import com.umeca.model.entities.shared.TabletAssignmentCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qTabletAssignmentCaseRepository")
public interface TabletAssignmentCaseRepository extends JpaRepository<TabletAssignmentCase, Long> {

    @Query("SELECT TAC FROM TabletAssignmentCase TAC " +
            "inner join TAC.caseDetention C " +
            "where C.id=:caseId and TAC.isObsolete=false and TAC.downloadedDate is null")
    List<TabletAssignmentCase> getAssignmentsByCaseId(@Param("caseId") Long caseId);


    @Query("SELECT TAC FROM TabletAssignmentCase TAC " +
            "where TAC.id=:assignmentId and TAC.isObsolete=false and TAC.downloadedDate is null")
    TabletAssignmentCase findValidAssignmentByAssignmentId(@Param("assignmentId") Long assignmentId);


    @Query("SELECT TAC FROM TabletAssignmentCase TAC " +
            "inner join TAC.caseDetention C " +
            "where C.id=:caseId and TAC.isObsolete=false")
    List<TabletAssignmentCase> getAssignmentsByCaseIdAll(@Param("caseId") Long caseId);

}


