package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AidSource;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qAidSourceRepository")
public interface AidSourceRepository extends JpaRepository<AidSource, Long>{

    @Query("SELECT ag FROM AidSource ag WHERE ag.isObsolete=false")
    List<SelectList> findAllValid();
}


