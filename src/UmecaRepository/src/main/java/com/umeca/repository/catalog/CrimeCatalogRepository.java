package com.umeca.repository.catalog;

import com.umeca.model.catalog.CrimeCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("crimeCatalogRepository")
public interface CrimeCatalogRepository extends JpaRepository<CrimeCatalog,Long> {

    @Query("select c from CrimeCatalog as c where c.obsolete = false")
    List<CrimeCatalog> findNotObsolete();
}
