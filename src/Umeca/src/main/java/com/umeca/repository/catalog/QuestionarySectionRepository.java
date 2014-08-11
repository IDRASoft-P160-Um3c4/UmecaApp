package com.umeca.repository.catalog;

import com.umeca.model.catalog.QuestionType;
import com.umeca.model.catalog.QuestionarySection;
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
@Repository("qQuestionarySectionRepository")
public interface QuestionarySectionRepository extends JpaRepository<QuestionarySection, Long>{

    @Query("SELECT sec FROM QuestionarySection sec INNER JOIN sec.questionary as quest where quest.code =:questCode order by sec.code")
    List<QuestionarySection> findSectionsByQuestionaryCode(@Param("questCode") String questCode);

}
