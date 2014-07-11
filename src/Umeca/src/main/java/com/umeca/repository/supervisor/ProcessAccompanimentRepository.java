package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ProcessAccompaniment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qrocessAcompanimentRepository")
public interface ProcessAccompanimentRepository extends JpaRepository<ProcessAccompaniment, Long>{

}


