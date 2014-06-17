package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("countryRepository")
public interface CountryRepository extends JpaRepository<Country,Long> {

    @Query("select c from Country as c where alpha2 = :codeMexico")
    Country findAplha2(@Param("codeMexico")String alpha2Mexico);
}
