package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingSelectedSourceRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qFramingSelectedSourceRelRepository")
public interface FramingSelectedSourceRelRepository extends JpaRepository<FramingSelectedSourceRel, Long> {

    @Query("SELECT sel FROM FramingSelectedSourceRel sel INNER JOIN sel.framingReference ref WHERE ref.id=:id")
    FramingSelectedSourceRel findSourceRelByIdSource(@Param("id") Long id);

}
