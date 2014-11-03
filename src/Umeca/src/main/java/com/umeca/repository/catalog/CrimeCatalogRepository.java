package com.umeca.repository.catalog;

import com.umeca.model.catalog.AcademicLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("crimeCatalogRepository")
public interface CrimeCatalogRepository extends JpaRepository<AcademicLevel,Long> {


}
