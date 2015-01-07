package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.TechnicalReview;
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
@Repository("qTechnicalReviewRepository")
public interface TechnicalReviewRepository extends JpaRepository<TechnicalReview, Long> {
    @Query("select e.id from Case as c " +
            "LEFT JOIN c.technicalReview as e where c.id = :idCase")
    Long getTechnicalReviewByCaseId(@Param("idCase") Long caseId);

/*
    @Query("SELECT q FROM Questionary q WHERE q.code =:code AND q.isObsolete = false")
    Questionary findByCode(@Param("code")String code);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.username=:username AND u.id<>:id")
    Long countByUsername(@Param("username")String username, @Param("id")Long id);

    @Query("SELECT u.id FROM User u WHERE u.username=:username")
    Long findIdByUsername(@Param("username")String username);
*/

    @Query("Select new com.umeca.model.shared.SelectList(Q.value, Q.question) from Verification ver " +
            "INNER JOIN ver.caseDetention cd " +
            "INNER JOIN cd.technicalReview tecR " +
            "INNER JOIN tecR.questionsSel qSel " +
            "INNER JOIN qSel.question Q " +
            "WHERE ver.id=:idVer")
    List<SelectList> getQuestionValuesByCaseId(@Param("idVer") Long idVer);

    @Query("Select new com.umeca.model.shared.SelectList(Q.id, Q.question) from Case cd " +
            "INNER JOIN cd.technicalReview tecR " +
            "INNER JOIN tecR.questionsSel qSel " +
            "INNER JOIN qSel.question Q " +
            "WHERE cd.id=:idCase and Q.value <0")
    List<SelectList> getProceduralRiskByCaseId(@Param("idCase") Long idCase);

    @Query("Select new com.umeca.model.shared.SelectList(Q.id, Q.question) from Case cd " +
            "INNER JOIN cd.technicalReview tecR " +
            "INNER JOIN tecR.questionsSel qSel " +
            "INNER JOIN qSel.question Q " +
            "WHERE cd.id=:idCase and Q.value >0")
    List<SelectList> getCommunityLinksByCaseId(@Param("idCase") Long idCase);
}
