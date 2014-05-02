package com.umeca.repository.Account;

import com.umeca.model.entities.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 3:55 PM
 */

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}

