package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.SocialEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("socialEnvironmentRepository")
public interface SocialEnvironmentRepository extends JpaRepository<SocialEnvironment, Long> {
}
