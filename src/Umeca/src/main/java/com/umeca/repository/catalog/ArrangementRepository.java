package com.umeca.repository.catalog;

import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.reviewer.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qArrangementRepository")
public interface ArrangementRepository extends JpaRepository<Arrangement, Long>{

    @Query("SELECT arr FROM Arrangement arr WHERE arr.type =:typeId")
    List<Arrangement> findByType(@Param("typeId")Integer typeId);

}
