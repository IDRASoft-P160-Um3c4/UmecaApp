package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.entities.reviewer.VerificationMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("verificationMethodRepository")
public interface VerificationMethodRepository extends JpaRepository<VerificationMethod,Long> {

}
