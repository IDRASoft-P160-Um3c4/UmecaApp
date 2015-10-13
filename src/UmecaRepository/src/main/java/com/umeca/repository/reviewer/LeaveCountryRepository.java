package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.LeaveCountry;
import com.umeca.model.entities.reviewer.SocialEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("leaveCountryRepository")
public interface LeaveCountryRepository extends JpaRepository<LeaveCountry, Long> {
}
