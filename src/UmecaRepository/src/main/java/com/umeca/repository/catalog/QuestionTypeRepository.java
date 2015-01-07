package com.umeca.repository.catalog;

import com.umeca.model.catalog.QuestionType;
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
@Repository("qQuestionTypeRepository")
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long>{
}
