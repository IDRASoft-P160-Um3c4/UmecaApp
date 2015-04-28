package com.umeca.repository.shared;

import com.umeca.model.catalog.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qQuestionaryRepository")
public interface QuestionaryRepository extends JpaRepository<Questionary, Long>{

    @Query("SELECT q FROM Questionary q WHERE q.code =:code AND q.isObsolete = false")
    Questionary findByCode(@Param("code")String code);
}
