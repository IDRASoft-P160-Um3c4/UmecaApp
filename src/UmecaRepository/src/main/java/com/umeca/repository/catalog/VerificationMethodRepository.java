package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.entities.reviewer.VerificationMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("verificationMethodRepository")
public interface VerificationMethodRepository extends JpaRepository<VerificationMethod,Long> {

    @Query("select vm from VerificationMethod as vm where vm.isObsolete=false order by vm.name")
    List<VerificationMethod> findNoObsolete();

}
