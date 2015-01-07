package com.umeca.repository.managereval;

import com.umeca.model.entities.reviewer.SourceVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dcortesr on 14/07/14.
 */
@Repository("qSourceVerificationRepository")
public interface SourceVerificationRepository extends JpaRepository<SourceVerification,Long> {
}
