package com.umeca.repository.supervisorManager;

import com.umeca.model.entities.supervisorManager.LogChangeSupervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qLogChangeSupervisorRepository")
public interface LogChangeSupervisorRepository extends JpaRepository<LogChangeSupervisor, Long>{
}


