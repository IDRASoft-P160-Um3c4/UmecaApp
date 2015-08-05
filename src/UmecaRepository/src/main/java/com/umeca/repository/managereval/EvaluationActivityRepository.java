package com.umeca.repository.managereval;

import com.umeca.model.catalog.EvaluationActivity;
import com.umeca.model.entities.reviewer.SourceVerification;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qEvaluationActivityRepository")
public interface EvaluationActivityRepository extends JpaRepository<EvaluationActivity,Long> {

    @Query("select new com.umeca.model.shared.SelectList(EA.id,EA.name) from EvaluationActivity EA where EA.isObsolete=false")
    List<SelectList> getAllNoObsolete();
}