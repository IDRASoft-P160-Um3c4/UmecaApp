package com.umeca.repository.supervisor;

import com.umeca.model.catalog.CloseCause;
import com.umeca.model.entities.supervisor.ActivityGoal;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qCloseCauseRepository")
public interface CloseCauseRepository extends JpaRepository<CloseCause, Long> {

    @Query("select new com.umeca.model.shared.SelectList(CC.id,CC.name) from CloseCause CC where CC.isObsolete=false and CC.isVisible=true")
    public List<SelectList> findVisibleNoObsolete();

    @Query("select CC from CloseCause CC where CC.isObsolete=false and CC.code=:strCode")
    public CloseCause findByCode(@Param("strCode") String strCode);


}


