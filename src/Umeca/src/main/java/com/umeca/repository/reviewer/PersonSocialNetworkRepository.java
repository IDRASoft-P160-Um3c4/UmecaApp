package com.umeca.repository.reviewer;
import com.umeca.model.catalog.Activity;
import com.umeca.model.entities.reviewer.PersonSocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("personSocialNetworkRepository")
public interface PersonSocialNetworkRepository extends JpaRepository<PersonSocialNetwork,Long> {

}
