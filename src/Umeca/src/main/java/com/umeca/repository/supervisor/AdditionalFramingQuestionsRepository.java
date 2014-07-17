package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AddictedAcquaintanceRel;
import com.umeca.model.entities.supervisor.AdditionalFramingQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qAdditionalFramingQuestionsRepository")
public interface AdditionalFramingQuestionsRepository extends JpaRepository<AdditionalFramingQuestions, Long> {
}