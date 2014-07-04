package com.umeca.repository.account;

import com.umeca.model.entities.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qUserRepository")
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.enabled = true")
    User findByUsername(@Param("username")String username);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.username=:username AND u.id<>:id")
    Long countByUsername(@Param("username")String username, @Param("id")Long id);

    @Query("SELECT u.id FROM User u WHERE u.username=:username")
    Long findIdByUsername(@Param("username")String username);

    @Query("SELECT u.enabled FROM User u WHERE u.id=:id")
    Boolean isEnabled(@Param("id") Long userId);

    @Query("SELECT new com.umeca.model.entities.account.User(u.id, u.enabled) FROM User u WHERE u.username=:username")
    User getInfoToValidate(@Param("username") String sUsername);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.username=:username AND u.password =:password")
    Long isValidPassworForUsername(@Param("username")String username, @Param("password")String password);

    @Query("SELECT u.password FROM User u WHERE u.id=:id")
    String getEncodedPassword(@Param("id") Long userId);
}
