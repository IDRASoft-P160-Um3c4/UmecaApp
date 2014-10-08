package com.umeca.repository.catalog;

import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.ElectionNotApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 28/05/14
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("electionNotApplyRepository")
public interface ElectionNotApplyRepository extends JpaRepository<ElectionNotApply,Long> {
}
