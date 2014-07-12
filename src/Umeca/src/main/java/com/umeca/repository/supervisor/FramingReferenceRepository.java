package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingMeetingConstants;
import com.umeca.model.entities.supervisor.FramingReference;
import com.umeca.model.shared.SelectList;
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
@Repository("qFramingReferenceRepository")
public interface FramingReferenceRepository extends JpaRepository<FramingReference, Long>{


    @Query("SELECT new com.umeca.model.shared.SelectList(ssr.id, fr.name, rs.name, " +
            "(case when fr.address is null then '" + FramingMeetingConstants.PERSON_SAME_ADDRESS + "' else fr.address end)) FROM FramingMeeting fm " +
            "INNER JOIN fm.caseDetention cd INNER JOIN fm.selectedSourcesRel ssr INNER JOIN ssr.framingReference fr INNER JOIN fr.relationship rs " +
            "WHERE cd.id =:caseId")
    public List<SelectList> findAllValidByCaseId(@Param("caseId") Long caseId);

}


