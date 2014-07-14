package com.umeca.repository.supervisorManager;

import com.umeca.model.entities.supervisorManager.LogChangeSupervisor;
import com.umeca.model.entities.supervisorManager.LogCommentMonitoringPlan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qLogChangeSupervisorRepository")
public interface LogChangeSupervisorRepository extends JpaRepository<LogChangeSupervisor, Long>{
}


