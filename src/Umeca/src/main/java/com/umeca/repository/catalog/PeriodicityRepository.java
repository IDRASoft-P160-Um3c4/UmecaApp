package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Periodicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("periodicityRepository")
public interface PeriodicityRepository extends JpaRepository<Periodicity,Long> {

    @Query("select p from Periodicity as p where p.isObsolete=false")
    List<Periodicity> findNotObsolete();
}
