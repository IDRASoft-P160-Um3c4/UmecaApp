package com.umeca.repository.catalog;

import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.supervisor.ArrangementView;
import com.umeca.model.shared.RelationModel;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qArrangementRepository")
public interface ArrangementRepository extends JpaRepository<Arrangement, Long> {

    @Query("SELECT arr FROM Arrangement arr WHERE arr.type =:typeId and arr.isNational=:isNational and arr.isObsolete=false")
    List<Arrangement> findByType(@Param("isNational") Boolean national, @Param("typeId") Integer typeId);

    @Query("SELECT new com.umeca.model.shared.SelectList(aa.id, arr.description, aa.description) FROM HearingFormat hf " +
            "INNER JOIN hf.assignedArrangements aa INNER JOIN aa.arrangement arr  " +
            "WHERE hf.id =:id")
    List<SelectList> findLstArrangementByHearingFormatId(@Param("id") Long id);

    @Query("SELECT new com.umeca.model.shared.RelationModel(aa.id, arr.id, arr.description) FROM HearingFormat hf " +
            "INNER JOIN hf.assignedArrangements aa INNER JOIN aa.arrangement arr  " +
            "WHERE hf.id =:id")
    List<RelationModel> findLstHearingFormatIdsAndArrangementIdsByHearingFormatId(@Param("id") Long id);

    @Query("SELECT DISTINCT new com.umeca.model.shared.SelectList(aa.id, arr.description, aa.description) FROM HearingFormat hf " +
            "INNER JOIN hf.assignedArrangements aa INNER JOIN aa.arrangement arr INNER JOIN hf.caseDetention cd " +
            "WHERE cd.id =:caseId")
    List<SelectList> findLstArrangementByCaseId(@Param("caseId") Long caseId);

    @Query("SELECT DISTINCT new com.umeca.model.shared.SelectList(aa.id, arr.description, aa.description, arr.type) FROM HearingFormat hf " +
            "INNER JOIN hf.assignedArrangements aa INNER JOIN aa.arrangement arr INNER JOIN hf.caseDetention cd " +
            "WHERE cd.id =:caseId")
    List<SelectList> findLstArrangementByIdCaseForLog(@Param("caseId") Long caseId);

    /*@Query("SELECT new com.umeca.model.shared.SelectList(aa.id, arr.description, aa.description) FROM ActivityMonitoringPlan amp " +
            "INNER JOIN amp.lstAssignedArrangement laa INNER JOIN laa.assignedArrangement aa INNER JOIN aa.arrangement arr " +
            "WHERE amp.id =:id")*/

    @Query("SELECT new com.umeca.model.shared.OptionList(aa.id, arr.description, aa.description, laa.status) FROM ActivityMonitoringPlan amp " +
            "INNER JOIN amp.lstAssignedArrangement laa INNER JOIN laa.assignedArrangement aa INNER JOIN aa.arrangement arr " +
            "WHERE amp.id =:id")
    List<SelectList> findArrangementById(@Param("id") Long id);

    @Query("SELECT arr.description FROM Arrangement arr " +
            "WHERE arr.id =:arrangementId")
    String findArrangementDescById(@Param("arrangementId") Long arrangementId);


    @Query("SELECT concat(arr.description,', ',aa.description) FROM HearingFormat hf " +
            "INNER JOIN hf.assignedArrangements aa " +
            "INNER JOIN aa.arrangement arr " +
            "WHERE hf.id =:idFormat")
    List<String> findArrangementsByIdFormat(@Param("idFormat") Long idFormat);

    @Query("SELECT new com.umeca.model.entities.supervisor.ArrangementView(arr.id, arr.description, arr.isNational, arr.type) FROM Arrangement arr " +
            "WHERE arr.isObsolete = false")
    List<ArrangementView> findAllArrangementForView();
}
