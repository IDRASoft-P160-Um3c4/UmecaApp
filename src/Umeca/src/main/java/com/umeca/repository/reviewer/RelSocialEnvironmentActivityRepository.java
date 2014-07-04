package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.RelSocialEnvironmentActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("rsearRepository")
public interface RelSocialEnvironmentActivityRepository extends JpaRepository<RelSocialEnvironmentActivity,Long> {

}
