package com.umeca.repository.catalog;

import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Location;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select l from Location as l where l.zipCode =:zipCode order by l.name")
    List<Location> findLocationByZipCode(@Param("zipCode") String zipCode);

    @Query("select l from Location as l where l.municipality.id =:idMun " +
            "group by l.name order by l.name")
    List<Location> findLocationByMunId(@Param("idMun") Long idMun);

    @Query("select new com.umeca.model.shared.SelectList(L.id,L.name) from Location L " +
            "INNER JOIN  L.municipality M where M.id =:idMun " +
            "group by L.name order by L.name")
    List<SelectList> findLocByMunId(@Param("idMun") Long idMun);
}
