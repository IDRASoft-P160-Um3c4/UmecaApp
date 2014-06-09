package com.umeca.repository.catalog;

import com.umeca.model.catalog.PhysicalCondition;
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

@Repository("physicalConditionRepository")
public interface PhysicalConditionRepository extends JpaRepository<PhysicalCondition,Long> {

}
