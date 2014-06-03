package com.umeca.repository.catalog;

import com.umeca.model.catalog.Arrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qArrangementRepository")
public interface ArrangementRepository extends JpaRepository<Arrangement, Long>{
}
