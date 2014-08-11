package com.umeca.repository.catalog;
import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.catalog.HomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("homeTypeRepository")
public interface HomeTypeRepository extends JpaRepository<HomeType,Long> {

    @Query("select ad from HomeType as ad where ad.obsolete=false")
    List<HomeType> findNotObsolete();

}
