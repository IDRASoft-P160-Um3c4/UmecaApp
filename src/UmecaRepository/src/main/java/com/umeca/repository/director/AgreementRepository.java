package com.umeca.repository.director;

import com.umeca.model.entities.director.minutes.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qAgreementRepository")
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
}
