package com.umeca.repository.supervisor;
import com.umeca.model.entities.reviewer.RelSocialEnvironmentActivity;
import com.umeca.model.entities.supervisor.RelFramingMeetingActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("relFramingActivityRepository")
public interface RelFramingMeetingActivityRepository extends JpaRepository<RelFramingMeetingActivity,Long> {

}
