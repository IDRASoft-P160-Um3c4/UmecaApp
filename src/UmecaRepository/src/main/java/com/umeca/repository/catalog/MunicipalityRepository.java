package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("municipalityRepository")
public interface MunicipalityRepository extends JpaRepository<Municipality,Long> {

    @Query("select m from Municipality as m where m.state.id=:idState order by m.name")
    List<Municipality> findByIdState(@Param("idState")Long idState);
}
