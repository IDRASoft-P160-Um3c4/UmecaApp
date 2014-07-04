package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ActivityGoal;
import com.umeca.model.entities.supervisor.SupervisionActivity;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qActivityGoalRepository")
public interface ActivityGoalRepository extends JpaRepository<ActivityGoal, Long>{

    @Query("SELECT new com.umeca.model.shared.SelectList(ag.id, ag.name) FROM ActivityGoal ag WHERE ag.isObsolete=false")
    List<SelectList> findAllValid();

    @Query("SELECT new com.umeca.model.shared.SelectList(ag.id, ag.name) FROM ActivityGoal ag")
    List<SelectList> findAllSl();
}


