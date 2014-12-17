package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingMeetingConstants;
import com.umeca.model.entities.supervisor.FramingReference;
import com.umeca.model.entities.supervisor.FramingReferenceForView;
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
public interface FramingReferenceRepository extends JpaRepository<FramingReference, Long> {


    /*@Query("SELECT new com.umeca.model.shared.SelectList(ssr.id, fr.name, rs.name, " +
            "(case when fr.address is null then '" + FramingMeetingConstants.PERSON_SAME_ADDRESS + "' else fr.address end)) FROM FramingMeeting fm " +
            "INNER JOIN fm.caseDetention cd INNER JOIN fm.selectedSourcesRel ssr INNER JOIN ssr.framingReference fr INNER JOIN fr.relationship rs " +
            "WHERE cd.id =:caseId")
    public List<SelectList> findAllValidByCaseId(@Param("caseId") Long caseId);*/

    @Query("SELECT new com.umeca.model.shared.SelectList(ssr.id, fr.name, rs.name, " +
            "(case when fr.address is null then concat('" + FramingMeetingConstants.PERSON_SAME_ADDRESS + " / ',fr.phone) " +
            "else concat(fr.address,' / ',fr.phone) end)) FROM FramingMeeting fm " +
            "INNER JOIN fm.caseDetention cd INNER JOIN fm.selectedSourcesRel ssr INNER JOIN ssr.framingReference fr INNER JOIN fr.relationship rs " +
            "WHERE cd.id =:caseId")
    public List<SelectList> findAllValidByCaseId(@Param("caseId") Long caseId);

    @Query("SELECT new com.umeca.model.shared.SelectList(fr.id, fr.name, rs.name) FROM FramingMeeting fm " +
            "INNER JOIN fm.caseDetention cd " +
            "INNER JOIN cd.monitoringPlan monP " +
            "INNER JOIN fm.selectedSourcesRel ssr " +
            "INNER JOIN ssr.framingReference fr " +
            "INNER JOIN fr.relationship rs " +
            "WHERE monP.id =:idMonP and fr.isAccompaniment=true")
    public List<SelectList> findAccompanimentReferences(@Param("idMonP") Long idMonP);

    @Query("SELECT fr FROM FramingMeeting fm " +
            "INNER JOIN fm.selectedSourcesRel ssr " +
            "INNER JOIN ssr.framingReference fr " +
            "INNER JOIN fr.relationship rs " +
            "WHERE fm.id=:idFramingMeeting and rs.name=:relImputed")
    public FramingReference findImputedReference(@Param("idFramingMeeting") Long idFramingMeeting, @Param("relImputed") String relImputed);

    @Query("SELECT fr FROM FramingMeeting fm " +
            "INNER JOIN fm.selectedSourcesRel ssr " +
            "INNER JOIN ssr.framingReference fr " +
            "INNER JOIN fr.relationship rs " +
            "WHERE fm.id=:idFramingMeeting and rs.name=:relImputed and fr.personType='OTHER'")
    public FramingReference findReferenceOther(@Param("idFramingMeeting") Long idFramingMeeting, @Param("relImputed") String relImputed);

    @Query("SELECT ref FROM FramingMeeting fm " +
            "INNER JOIN fm.references ref " +
            "INNER JOIN fm.caseDetention cd " +
            "WHERE cd.id =:idCase and ref.isAccompaniment=true")
    public List<FramingReference> getAccompanimentReferencesByIdCase(@Param("idCase") Long idCase);

    @Query("SELECT new com.umeca.model.entities.supervisor.FramingReferenceForView(ref.id, ref.name, r.name) FROM FramingMeeting fm " +
            "INNER JOIN fm.caseDetention cd " +
            "INNER JOIN fm.references ref " +
            "INNER JOIN ref.relationship r " +
            "WHERE cd.id =:idCase and ref.isAccompaniment=true")
    public List<FramingReferenceForView> getAccompanimentRefByIdCase(@Param("idCase") Long idCase);

    @Query("SELECT distinct (FR.id) FROM ActivityMonitoringPlan AMP " +
            "INNER JOIN AMP.caseDetention cd " +
            "INNER JOIN AMP.framingSelectedSourceRel SSR " +
            "INNER JOIN SSR.framingReference FR " +
            "WHERE cd.id =:idCase")
    public List<Long> findDependentReferences(@Param("idCase") Long idCase);

    @Query("SELECT distinct (SSR.id) FROM ActivityMonitoringPlan AMP " +
            "INNER JOIN AMP.caseDetention cd " +
            "INNER JOIN AMP.framingSelectedSourceRel SSR " +
            "WHERE cd.id =:idCase")
    public List<Long> findDependentSourceRel(@Param("idCase") Long idCase);

    @Query("SELECT distinct (fr.id) FROM FramingMeeting fm " +
            "INNER JOIN fm.selectedSourcesRel ssr " +
            "INNER JOIN ssr.framingReference fr " +
            "INNER JOIN fm.caseDetention cd " +
            "WHERE cd.id =:idCase and fr.personType not in (:lstPersonType)")
    public List<Long> findSelectedReferences(@Param("idCase") Long idCase, @Param("lstPersonType") List<String> lstPersonType);


    @Query("SELECT distinct (ssr.id) FROM FramingMeeting fm " +
            "INNER JOIN fm.selectedSourcesRel ssr " +
            "INNER JOIN ssr.framingReference fr " +
            "INNER JOIN fm.caseDetention cd " +
            "WHERE cd.id =:idCase and fr.personType in (:lstPersonType)")
    public List<Long> findSelectedReferencesRel(@Param("idCase") Long idCase, @Param("lstPersonType") List<String> lstPersonType);
}


