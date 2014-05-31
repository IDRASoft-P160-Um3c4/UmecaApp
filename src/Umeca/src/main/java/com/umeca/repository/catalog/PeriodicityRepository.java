package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Periodicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("periodicityRepository")
public interface PeriodicityRepository extends JpaRepository<Periodicity,Long> {

}
