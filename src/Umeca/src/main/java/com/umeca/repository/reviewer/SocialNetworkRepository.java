package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 3:55 PM
 */

@Repository("socialNetworkRepository")
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long>{
}
