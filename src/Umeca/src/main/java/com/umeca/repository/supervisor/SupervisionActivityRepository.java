package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.HearingFormat;
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
@Repository("qSupervisionActivityRepository")
public interface SupervisionActivityRepository extends JpaRepository<SupervisionActivity, Long>{

    @Query("SELECT new com.umeca.model.shared.SelectList(sa.id, sa.name) FROM SupervisionActivity sa WHERE sa.isObsolete=false")
    List<SelectList> findAllValidSl();

    @Query("SELECT new com.umeca.model.shared.SelectList(sa.id, sa.name) FROM SupervisionActivity sa")
    List<SelectList> findAllSl();
}


