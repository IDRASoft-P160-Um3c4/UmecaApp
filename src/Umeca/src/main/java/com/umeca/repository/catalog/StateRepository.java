package com.umeca.repository.catalog;
import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stateRepository")
public interface StateRepository extends JpaRepository<State,Long> {

    @Query("select s from State as s where country.id = :countryId")
    List<State> getStatesByCountry(@Param("countryId") Long countryId);

    @Query("select s from State as s where country.alpha2 = :code")
    List<State> findStatesByCountryAlpha2(@Param("code")String code);
}
