package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("select l from Location as l where zipCode = :zipCode")
    List<Location> findLocationByZipCode(@Param("zipCode") String zipCode);
}
