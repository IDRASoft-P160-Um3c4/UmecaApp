package com.umeca.repository.catalog;

import com.umeca.model.catalog.Activity;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("select a from Activity as a where a.isObsolete=false")
    List<Activity> findNotObsolete();

    @Query("select new com.umeca.model.shared.SelectList(a.id,a.name) from Activity a where a.isObsolete=false")
    List<SelectList> getActivitiesNoObsolete();

}
