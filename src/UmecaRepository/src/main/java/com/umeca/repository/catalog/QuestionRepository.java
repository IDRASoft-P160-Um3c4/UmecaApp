package com.umeca.repository.catalog;

import com.umeca.model.catalog.Question;
import com.umeca.model.catalog.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qQuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long>{
}
