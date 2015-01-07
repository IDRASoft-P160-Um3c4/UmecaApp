package com.umeca.repository.catalog;
import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("academicLevelRepository")
public interface AcademicLevelRepository extends JpaRepository<AcademicLevel,Long> {

    @Query("select ad from AcademicLevel as ad where ad.isObsolete=false")
    List<AcademicLevel> findNotObsolete();

    @Query("select new com.umeca.model.shared.SelectList(ad.id, ad.name) from AcademicLevel as ad where ad.isObsolete=false")
    List<SelectList> findNoObsolete();
}
