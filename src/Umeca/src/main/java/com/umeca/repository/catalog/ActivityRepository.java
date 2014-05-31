package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity,Long> {

}
