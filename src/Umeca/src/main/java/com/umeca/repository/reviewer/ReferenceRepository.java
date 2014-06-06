package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.PersonSocialNetwork;
import com.umeca.model.entities.reviewer.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("referenceRepository")
public interface ReferenceRepository extends JpaRepository<Reference,Long> {

}
