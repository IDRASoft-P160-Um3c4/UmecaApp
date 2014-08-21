package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.CaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("caseRequestRepository")
public interface CaseRequestRepository extends JpaRepository<CaseRequest,Long> {

}
