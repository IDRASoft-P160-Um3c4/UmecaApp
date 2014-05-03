package com.umeca.repository.account;

import com.umeca.model.entities.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
