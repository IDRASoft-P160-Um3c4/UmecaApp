package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AddictedAcquaintanceRel;
import com.umeca.model.entities.supervisor.RelativesAbroadRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qRelativeAbroadRelRepository")
public interface RelativeAbroadRelRepository extends JpaRepository<RelativesAbroadRel, Long> {
}