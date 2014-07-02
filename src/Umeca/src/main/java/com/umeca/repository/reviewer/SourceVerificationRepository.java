package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.SourceVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sourceVerificationRepository")
public interface SourceVerificationRepository extends JpaRepository<SourceVerification,Long> {

}
