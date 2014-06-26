package com.umeca.repository.supervisor;

import com.umeca.model.entities.shared.LogChangeData;
import com.umeca.model.entities.supervisor.ActivityGoal;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qLogChangeDataRepository")
public interface LogChangeDataRepository extends JpaRepository<LogChangeData, Long>{

}


