package com.umeca.repository.supervisorManager;

import com.umeca.model.entities.supervisorManager.RolActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qRolActivityRepository")
public interface RolActivityRepository extends JpaRepository<RolActivity, Long>{
}


