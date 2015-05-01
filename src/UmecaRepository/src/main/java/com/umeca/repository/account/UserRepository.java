package com.umeca.repository.account;

import com.umeca.model.entities.account.User;
import com.umeca.model.shared.SelectList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.enabled = true")
    User findByUsername(@Param("username") String username);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.username=:username AND u.id<>:id")
    Long countByUsername(@Param("username") String username, @Param("id") Long id);

    @Query("SELECT u.id FROM User u WHERE u.username=:username")
    Long findIdByUsername(@Param("username") String username);

    @Query("SELECT u.enabled FROM User u WHERE u.id=:id")
    Boolean isEnabled(@Param("id") Long userId);

    @Query("SELECT new com.umeca.model.entities.account.User(u.id, u.enabled) FROM User u WHERE u.username=:username")
    User getInfoToValidate(@Param("username") String sUsername);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.username=:username AND u.password =:password")
    Long isValidPassworForUsername(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u.password FROM User u WHERE u.id=:id")
    String getEncodedPassword(@Param("id") Long userId);

    @Query("SELECT DISTINCT new com.umeca.model.shared.SelectList(u.id, u.username, u.fullname) FROM User u " +
            "INNER JOIN u.roles r WHERE r.role=:sRole AND u.enabled = true ORDER BY u.username ASC")
    List<SelectList> getLstValidUsersByRole(@Param("sRole") String sRole);

    @Query("SELECT DISTINCT new com.umeca.model.entities.account.User(u.id, u.username) FROM User u " +
            "INNER JOIN u.roles r WHERE r.role=:sRole AND u.enabled = true ORDER BY u.username ASC")
    List<User> getLstValidUserIdsByRole(@Param("sRole") String sRole);

    @Query("SELECT DISTINCT u.id FROM User u " +
            "INNER JOIN u.roles r WHERE r.role=:sRole AND u.enabled = true ORDER BY u.username ASC")
    List<Long> getIdValidUsersByRole(@Param("sRole") String sRole);

    @Query("SELECT DISTINCT new com.umeca.model.shared.SelectList(u.id, u.username, u.fullname) FROM User u " +
            "INNER JOIN u.roles r WHERE r.role=:sRole AND u.enabled = true AND u.id <> :userId ORDER BY u.username ASC")
    List<SelectList> getLstValidUsersByRoleExceptUserId(@Param("userId") Long userId, @Param("sRole") String sRole);

    @Query("SELECT COUNT(u.id) FROM User u INNER JOIN u.roles r WHERE u.id=:userId AND r.role =:sRole")
    Long isUserInRole(@Param("userId") Long userId, @Param("sRole") String sRole);

    @Query("SELECT r.role FROM User u INNER JOIN u.roles r WHERE u.id=:userId")
    List<String> getLstRolesByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(u.id) FROM User u INNER JOIN u.roles r WHERE u.id=:userId AND r.role IN :roles")
    Long isUserInRoles(@Param("userId") Long userId, @Param("roles") List<String> roles);

    @Query("SELECT new com.umeca.model.shared.SelectList(u.id, u.username, u.fullname, count(mp.id)) FROM MonitoringPlan mp " +
            "LEFT JOIN mp.supervisor as u " +
            "INNER JOIN u.roles r WHERE (r.role=:sRole) AND (u.id in (:listUsers)) and mp.status <> 'TERMINADO'  group by (u.id)")
    List<SelectList> getUsersAssignCase(@Param("sRole") String sRole, @Param("listUsers") List<Long> listUsers);

    @Query("SELECT new com.umeca.model.shared.SelectList(u.id, u.username, R.description) FROM User U " +
            "inner join U.roles R " +
            "where U.enabled = true and lower(U.username) like :str order by U.id")
    List<SelectList> getUserRolesByUsername(@Param("str") String str, Pageable pageable);

    @Query("SELECT distinct new com.umeca.model.shared.SelectList(E.id, concat(E.name,' ',E.lastNameP,' ',E.lastNameM,' - ',D.name), R.description) FROM Employee E " +
            "inner join E.users U " +
            "inner join E.district D " +
            "inner join U.roles R " +
            "where U.enabled = true and (" +
            "lower(E.name) like :str or " +
            "lower(E.lastNameP) like :str or " +
            "lower(E.lastNameM) like :str) " +
            "order by U.id")
    List<SelectList> getUserRolesByEmployeename(@Param("str") String str, Pageable pageable);

    @Query("SELECT new com.umeca.model.shared.SelectList(u.id, u.username, R.description) FROM Employee E " +
            "inner join E.users U " +
            "inner join U.roles R " +
            "where U.enabled = true and E.id=:employeeId order by U.id")
    List<SelectList> getUserRolesByEmployeeId(@Param("employeeId") Long employeeId);



    @Query("SELECT U.id FROM User U " +
            "INNER JOIN U.roles R " +
            "WHERE U.enabled = true and R.role IN :lstRoles")
    List<Long> getLstValidUsersIdByLstRoles(@Param("lstRoles") List<String> lstRoles);
}
