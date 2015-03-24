package com.umeca.repository.account;

import com.umeca.model.entities.account.Role;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 3:55 PM
 */

@Repository("qRoleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    @Query("SELECT new com.umeca.model.shared.SelectList(r.id, r.description) FROM Role r")
    public List<SelectList> findSelectList();

    @Query("SELECT r FROM Role r WHERE r.role=:role")
    public Role findByCode(@Param("role") String role);

    @Query("SELECT new com.umeca.model.shared.SelectList(r.id, r.description) FROM Role r WHERE r.role not in (:lstRole)")
    public List<SelectList> findByExcludeCode(@Param("lstRole") List<String> role);

}
