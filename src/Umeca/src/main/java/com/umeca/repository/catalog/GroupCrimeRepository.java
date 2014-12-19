package com.umeca.repository.catalog;

import com.umeca.model.catalog.GroupCrime;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 3/11/14
 * Time: 04:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GroupCrimeRepository extends JpaRepository<GroupCrime, Long> {

    @Query("select new com.umeca.model.shared.SelectList(GC.id,GC.name) from GroupCrime GC where GC.isObsolete=false")
    List<SelectList> findAllCrimeGroupsForView();

}
