package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity,Long> {

    @Query("select a from Activity as a where a.isObsolete=false")
    List<Activity> findNotObsolete();

}
